package job_mgmt.job_work_details_mgmt.services.read;

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
import job_mgmt.job_work_details_mgmt.model.details.JobDetails;
import job_mgmt.job_work_details_mgmt.model.dto.JobDetails_DTO;
import job_mgmt.job_work_details_mgmt.model.repo.read.JobDetailsRead_Repo;

@Service("jobDetailsReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobDetailsRead_Service implements I_JobDetailsRead_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(JobDetailsService.class);

	@Autowired
	private JobDetailsRead_Repo jobDetailsReadRepo;
	
	@Autowired
	private Executor asyncExecutor;
	
	@Override
	public CopyOnWriteArrayList<JobDetails_DTO> getAllJobDetails() throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobDetails_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobDetails> jobList = (CopyOnWriteArrayList<JobDetails>) jobDetailsReadRepo.findAll();
		CopyOnWriteArrayList<JobDetails_DTO> jobDTOs = new CopyOnWriteArrayList<JobDetails_DTO>();
		jobDTOs = jobList != null ? this.getJobDetails_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

	return future.get();
		
	}

	@Override
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetails(CopyOnWriteArrayList<Long> jobSeqNos) throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobDetails_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobDetails> jobDetails = null;
		CopyOnWriteArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (jobSeqNos != null) 
		{
			jobDetails = jobDetailsReadRepo.getSelectJobDetails(jobSeqNos);
			jobDetailsDTOs = jobDetails != null ? this.getJobDetails_DTOs(jobDetails) : null;
		}
		return jobDetailsDTOs;
		},asyncExecutor);

	return future.get();
	}

	@Override
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetailsForService(Long servWorkSeqNo) throws InterruptedException, ExecutionException 
	{
		
		CompletableFuture<CopyOnWriteArrayList<JobDetails_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobDetails> jobDetails = null;
		CopyOnWriteArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (servWorkSeqNo != null) 
		{
			jobDetails = jobDetailsReadRepo.getSelectJobDetailsForService(servWorkSeqNo);
			jobDetailsDTOs = jobDetails != null ? this.getJobDetails_DTOs(jobDetails) : null;
		}
		return jobDetailsDTOs;
		},asyncExecutor);

	return future.get();
	}

	@Override
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetailsForServices(CopyOnWriteArrayList<Long> servWorkSeqNos) throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobDetails_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobDetails> jobDetails = null;
		CopyOnWriteArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (servWorkSeqNos != null) 
		{
			jobDetails = jobDetailsReadRepo.getSelectJobDetailsForServices(servWorkSeqNos);
			jobDetailsDTOs = jobDetails != null ? this.getJobDetails_DTOs(jobDetails) : null;
		}
			return jobDetailsDTOs;
			},asyncExecutor);

		return future.get();
	}

	@Override
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetailsBetweenPlanTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException
	{
		
		CompletableFuture<CopyOnWriteArrayList<JobDetails_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		CopyOnWriteArrayList<JobDetails> jobDetails = null;
		CopyOnWriteArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (frDtTm != null && toDtTm != null) {
			lfrdttm = LocalDateTime.parse(frDtTm, formatter);
			ltodttm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
			Timestamp toDateTime = Timestamp.valueOf(ltodttm);
			jobDetails = null;
			jobDetails = jobDetailsReadRepo.getSelectJobDetailsBetweenPlanTimes(frDateTime, toDateTime);
			jobDetailsDTOs = jobDetails != null ? this.getJobDetails_DTOs(jobDetails) : null;
		}
			return jobDetailsDTOs;
			},asyncExecutor);

		return future.get();
	}

	@Override
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetailsBetweenActualTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException
	{
		CompletableFuture<CopyOnWriteArrayList<JobDetails_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		CopyOnWriteArrayList<JobDetails> jobDetails = null;
		CopyOnWriteArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (frDtTm != null && toDtTm != null) {
			lfrdttm = LocalDateTime.parse(frDtTm, formatter);
			ltodttm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
			Timestamp toDateTime = Timestamp.valueOf(ltodttm);
			jobDetails = null;
			jobDetails = jobDetailsReadRepo.getSelectJobDetailsBetweenActualTimes(frDateTime, toDateTime);
		}
		return jobDetailsDTOs;
		},asyncExecutor);

		return future.get();
	}

	private synchronized CopyOnWriteArrayList<JobDetails_DTO> getJobDetails_DTOs(CopyOnWriteArrayList<JobDetails> jobDetails) {
		JobDetails_DTO jobDTO = null;
		CopyOnWriteArrayList<JobDetails_DTO> jobDTOs = new CopyOnWriteArrayList<JobDetails_DTO>();

		for (int i = 0; i < jobDetails.size(); i++) {
			jobDTO = getJobDetails_DTO(jobDetails.get(i));
			jobDTOs.add(jobDTO);
		}
		return jobDTOs;
	}

	private synchronized JobDetails_DTO getJobDetails_DTO(JobDetails jobDetails) 
	{
		JobDetails_DTO jobDetailsDTO = new JobDetails_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		jobDetailsDTO.setActEndDate(formatter.format(jobDetails.getActEndDate().toLocalDateTime()));
		jobDetailsDTO.setActStartDate(formatter.format(jobDetails.getActStartDate().toLocalDateTime()));
		jobDetailsDTO.setPlanStartDate(formatter.format(jobDetails.getPlanStartDate().toLocalDateTime()));
		jobDetailsDTO.setPlanEndDate(formatter.format(jobDetails.getPlanEndDate().toLocalDateTime()));
		jobDetailsDTO.setJobSeqNo(jobDetails.getJobSeqNo());
		jobDetailsDTO.setJobTypeSeqNo(jobDetails.getJobTypeSeqNo());
		jobDetailsDTO.setManagerSeqNo(jobDetails.getManagerSeqNo());
		jobDetailsDTO.setParentJobSeqNo(jobDetails.getParentJobSeqNo());
		jobDetailsDTO.setServiceWorkSeqNo(jobDetails.getServiceWorkSeqNo());
		jobDetailsDTO.setRemarks(jobDetails.getRemarks());
		jobDetailsDTO.setStatus(jobDetails.getStatus());
		return jobDetailsDTO;
	}

}