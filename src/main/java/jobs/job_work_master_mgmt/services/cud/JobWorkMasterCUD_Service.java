package jobs.job_work_master_mgmt.services.cud;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jobs.job_work_master_mgmt.model.dto.JobWorkMaster_DTO;
import jobs.job_work_master_mgmt.model.master.JobWorkMaster;
import jobs.job_work_master_mgmt.model.repo.cud.JobWorkMasterCUD_Repo;

@Service("jobWorkMasterCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobWorkMasterCUD_Service implements I_JobWorkMasterCUD_Service {

	// private static final Logger logger =
	// LoggerFactory.getLogger(JobWorkMasterService.class);

	@Autowired
	private JobWorkMasterCUD_Repo jobWorkMasterCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<JobWorkMaster_DTO> newJobWork(JobWorkMaster_DTO jobWorkDTO) {
		CompletableFuture<JobWorkMaster_DTO> future = CompletableFuture.supplyAsync(() -> {
			JobWorkMaster_DTO serviceRequestDTO = new JobWorkMaster_DTO();
			if (!jobWorkMasterCUDRepo.existsById(jobWorkDTO.getServiceWorkSeqNo())) {
				JobWorkMaster jobWorkMaster = jobWorkMasterCUDRepo.save(this.setJobWork(jobWorkDTO));
				serviceRequestDTO = this.getJobWork_DTO(jobWorkMaster);
			}
			return serviceRequestDTO;
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Void> updJobWork(JobWorkMaster_DTO jobWorkDTO) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			if (jobWorkMasterCUDRepo.existsById(jobWorkDTO.getServiceWorkSeqNo())) {
				JobWorkMaster jobWorkMaster = this.setJobWork(jobWorkDTO);
				jobWorkMaster.setServiceWorkSeqNo(jobWorkDTO.getServiceWorkSeqNo());
				jobWorkMasterCUDRepo.save(jobWorkMaster);
			}
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<Void> updScheduledServiceWork(Long servWorkSeqNo)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
			if (jobWorkMasterCUDRepo.existsById(servWorkSeqNo)) 
					{
				jobWorkMasterCUDRepo.updateScheduledServiceWork(servWorkSeqNo);
			}
		}, asyncExecutor);
		return future;
	}

	
	@Override
	public CompletableFuture<Void> delSelectJobServices(CopyOnWriteArrayList<Long> servWorkSeqNos) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			jobWorkMasterCUDRepo.delSelectJobServices(servWorkSeqNos);
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Void> delSelectJobServicesForRequests(CopyOnWriteArrayList<Long> reqSeqNos) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			jobWorkMasterCUDRepo.delSelectJobServicesForRequests(reqSeqNos);
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Void> delSelectJobServicesForServices(CopyOnWriteArrayList<Long> servSeqNos) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			jobWorkMasterCUDRepo.delSelectJobServicesForServices(servSeqNos);
		}, asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<Void> delAllJobServices() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			jobWorkMasterCUDRepo.deleteAll();
		}, asyncExecutor);

		return future;
	}	
	
	@Override
	public CompletableFuture<Void> delSelectJobServicesBetweenTimes(String frDtTm, String toDtTm) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
			LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frTs = Timestamp.valueOf(frDTTm);
			Timestamp toTs = Timestamp.valueOf(toDTTm);
			jobWorkMasterCUDRepo.delSelectJobServicesBetweenTimes(frTs, toTs);
		}, asyncExecutor);

		return future;
	}

	private synchronized CopyOnWriteArrayList<JobWorkMaster_DTO> getJobWork_DTOs(
			CopyOnWriteArrayList<JobWorkMaster> jobMasters) {
		JobWorkMaster_DTO jobDTO = null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();

		for (int i = 0; i < jobMasters.size(); i++) {
			jobDTO = getJobWork_DTO(jobMasters.get(i));
			jobDTOs.add(jobDTO);
		}
		return jobDTOs;
	}

	private synchronized JobWorkMaster_DTO getJobWork_DTO(JobWorkMaster jobMaster) {
		JobWorkMaster_DTO jobMasterDTO = new JobWorkMaster_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		jobMasterDTO.setCreatedOn(formatter.format(jobMaster.getCreatedOn().toLocalDateTime()));
		jobMasterDTO.setRequestSeqNo(jobMaster.getRequestSeqNo());
		jobMasterDTO.setJobTemplateSeqNo(jobMaster.getJobTemplateSeqNo());
		jobMasterDTO.setManagerSeqNo(jobMaster.getManagerSeqNo());
		jobMasterDTO.setServiceSeqNo(jobMaster.getServiceSeqNo());
		jobMasterDTO.setServiceWorkSeqNo(jobMaster.getServiceWorkSeqNo());
		jobMasterDTO.setJobRefId(jobMasterDTO.getJobRefId());
		jobMasterDTO.setSchFlag(jobMasterDTO.getSchFlag());
		jobMasterDTO.setSchType(jobMasterDTO.getSchType());
		jobMasterDTO.setOpFlag(jobMasterDTO.getOpFlag());
		return jobMasterDTO;
	}

	private synchronized JobWorkMaster setJobWork(JobWorkMaster_DTO jobMaster_DTO) {
		JobWorkMaster jobMaster = new JobWorkMaster();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(jobMaster_DTO.getCreatedOn(), formatter);
		Timestamp ts = Timestamp.valueOf(dateTime);
		jobMaster.setCreatedOn(ts);
		jobMaster.setRequestSeqNo(jobMaster_DTO.getRequestSeqNo());
		jobMaster.setJobTemplateSeqNo(jobMaster_DTO.getJobTemplateSeqNo());
		jobMaster.setManagerSeqNo(jobMaster_DTO.getManagerSeqNo());
		jobMaster.setServiceSeqNo(jobMaster_DTO.getServiceSeqNo());
		jobMaster.setJobRefId(jobMaster_DTO.getJobRefId());
		jobMaster.setSchFlag(jobMaster_DTO.getSchFlag());
		jobMaster.setSchType(jobMaster_DTO.getSchType());
		jobMaster.setOpFlag(jobMaster_DTO.getOpFlag());
		return jobMaster;
	}

}