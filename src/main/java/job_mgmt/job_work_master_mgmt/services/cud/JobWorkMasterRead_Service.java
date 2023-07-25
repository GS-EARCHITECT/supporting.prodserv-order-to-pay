package job_mgmt.job_work_master_mgmt.services.cud;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import job_mgmt.job_work_master_mgmt.model.dto.JobWorkMaster_DTO;
import job_mgmt.job_work_master_mgmt.model.master.JobWorkMaster;
import job_mgmt.job_work_master_mgmt.model.repo.read.JobWorkMasterRead_Repo;

@Service("jobWorkMasterReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobWorkMasterRead_Service implements I_JobWorkMasterRead_Service 
{
	
	//private static final Logger logger = LoggerFactory.getLogger(JobWorkMasterService.class);

	@Autowired
	private JobWorkMasterRead_Repo jobWorkMasterReadRepo;
	
	@Autowired
	private Executor asyncExecutor;
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getAllJobWorkMasters() throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobList = (CopyOnWriteArrayList<JobWorkMaster>) jobWorkMasterReadRepo.findAll();
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();
		jobDTOs = jobList != null ? this.getJobWorkMaster_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

	return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>>  getSelectJobServices(CopyOnWriteArrayList<Long> servWorkSeqNos) throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobMasters = null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobMasterDTOs = null;

		if (servWorkSeqNos != null) 
		{
			jobMasters = jobWorkMasterReadRepo.getSelectJobServices(servWorkSeqNos);
			if (jobMasters != null) 
			{
				jobMasterDTOs = this.getJobWorkMaster_DTOs(jobMasters);
			}
		}
		return jobMasterDTOs;
		},asyncExecutor);

	return future;


	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>>  getSelectJobServicesForRequests(CopyOnWriteArrayList<Long> reqSeqNos) throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobMasters = null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobMasterDTOs = null;

		if (reqSeqNos != null) {
			jobMasters = jobWorkMasterReadRepo.getSelectJobServicesForRequests(reqSeqNos);
			if (jobMasters != null) {
				jobMasterDTOs = this.getJobWorkMaster_DTOs(jobMasters);
			}
		}
		return jobMasterDTOs;
		},asyncExecutor);

	return future;		
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServicesForServices(CopyOnWriteArrayList<Long> servSeqNos) throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobMasters = null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobMasterDTOs = null;

		if (servSeqNos != null) {
			jobMasters = jobWorkMasterReadRepo.getSelectJobServicesForServices(servSeqNos);
			if (jobMasters != null) {
				jobMasterDTOs = this.getJobWorkMaster_DTOs(jobMasters);
			}
		}		
		return jobMasterDTOs;
		},asyncExecutor);

	return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServicesBetweenTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		CopyOnWriteArrayList<JobWorkMaster> jobMasters = null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobMasterDTOs = null;

		if (frDtTm != null && toDtTm != null) 
		{
			lfrdttm = LocalDateTime.parse(frDtTm, formatter);
			ltodttm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
			Timestamp toDateTime = Timestamp.valueOf(ltodttm);
			jobMasters = null;
			jobMasters = jobWorkMasterReadRepo.getSelectJobServicesBetweenTimes(frDateTime, toDateTime);
			if (jobMasters != null) 
			{
				jobMasterDTOs = this.getJobWorkMaster_DTOs(jobMasters);
			}
		}
			return jobMasterDTOs;
		},asyncExecutor);
		return future;
		
	}

	private synchronized CopyOnWriteArrayList<JobWorkMaster_DTO> getJobWorkMaster_DTOs(CopyOnWriteArrayList<JobWorkMaster> jobMasters) {
		JobWorkMaster_DTO jobDTO = null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();

		for (int i = 0; i < jobMasters.size(); i++) {
			jobDTO = getJobWorkMaster_DTO(jobMasters.get(i));
			jobDTOs.add(jobDTO);
		}
		return jobDTOs;
	}

	private synchronized JobWorkMaster_DTO getJobWorkMaster_DTO(JobWorkMaster jobMaster) {
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
}