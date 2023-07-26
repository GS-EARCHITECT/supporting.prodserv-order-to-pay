package job_mgmt.job_work_master_mgmt.services.read;

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
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getAllJobWorks()
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobList = (CopyOnWriteArrayList<JobWorkMaster>) jobWorkMasterReadRepo.findAll();
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();
		jobDTOs = jobList != null ? this.getJobWork_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

		return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorks(CopyOnWriteArrayList<Long> servWorkSeqNos) 
	{
		
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobList = (CopyOnWriteArrayList<JobWorkMaster>) jobWorkMasterReadRepo.getSelectJobServices(servWorkSeqNos);
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();
		jobDTOs = jobList != null ? this.getJobWork_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorksForRequests(CopyOnWriteArrayList<Long> reqSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobList = (CopyOnWriteArrayList<JobWorkMaster>) jobWorkMasterReadRepo.getSelectJobServicesForRequests(reqSeqNos);
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();
		jobDTOs = jobList != null ? this.getJobWork_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

		return future;
	}

	@Override
	 public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorksForServices(CopyOnWriteArrayList<Long> servSeqNos)
	 {
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobList = (CopyOnWriteArrayList<JobWorkMaster>) jobWorkMasterReadRepo.getSelectJobServicesForServices(servSeqNos);
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();
		jobDTOs = jobList != null ? this.getJobWork_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

		return future;
	 }
	
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getJobWorksToBeScheduledForRequests(CopyOnWriteArrayList<Long> reqSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobList = (CopyOnWriteArrayList<JobWorkMaster>) jobWorkMasterReadRepo.getJobsToBeScheduledForRequests(reqSeqNos);
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();
		jobDTOs = jobList != null ? this.getJobWork_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getJobWorksToBeScheduled() 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobList = (CopyOnWriteArrayList<JobWorkMaster>) jobWorkMasterReadRepo.getJobsToBeScheduled();
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();
		jobDTOs = jobList != null ? this.getJobWork_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

		return future;
	}

	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorksBetweenTimes(String frDtTm, String toDtTm) 
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
			if (jobMasters != null) {
				jobMasterDTOs = this.getJobWork_DTOs(jobMasters);
			}
		}
			return jobMasterDTOs;
		},asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getJobServiceForRefNos(CopyOnWriteArrayList<String> jobrefIds) 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkMaster> jobList = (CopyOnWriteArrayList<JobWorkMaster>) jobWorkMasterReadRepo.getJobServiceForRefNos(jobrefIds);
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();
		jobDTOs = jobList != null ? this.getJobWork_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

		return future;
	}

	private synchronized CopyOnWriteArrayList<JobWorkMaster_DTO> getJobWork_DTOs(CopyOnWriteArrayList<JobWorkMaster> jobWorkMasters) 
	{
		JobWorkMaster_DTO jobDTO = null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkMaster_DTO>();

		for (int i = 0; i < jobWorkMasters.size(); i++) {
			jobDTO = getJobWork_DTO(jobWorkMasters.get(i));
			jobDTOs.add(jobDTO);
		}
		return jobDTOs;
	}

	private synchronized JobWorkMaster_DTO getJobWork_DTO(JobWorkMaster jobMaster) 
	{
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