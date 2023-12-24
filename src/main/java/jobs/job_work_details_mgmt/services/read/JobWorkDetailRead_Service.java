package jobs.job_work_details_mgmt.services.read;

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
import common.model.dto.JobWorkDetail_DTO;
import common.model.master.JobWorkDetail;
import jobs.job_mgmt.model.repo.common.*;

@Service("jobWorkDetailsReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobWorkDetailRead_Service implements I_JobWorkDetailsRead_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(JobWorkDetailService.class);

	@Autowired
	private JobWorkDetailsRead_Repo jobWorkDetailsReadRepo;
	
	@Autowired
	private Executor asyncExecutor;
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getAllJobWorkDetails() throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkDetail> jobList = (CopyOnWriteArrayList<JobWorkDetail>) jobWorkDetailsReadRepo.findAll();
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkDetail_DTO>();
		jobDTOs = jobList != null ? this.getJobWorkDetail_DTOs(jobList) : null;
		return jobDTOs;
		},asyncExecutor);

	return future;
		
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetails(CopyOnWriteArrayList<Long> jobSeqNos) throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkDetail> jobDetails = null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDetailsDTOs = null;

		if (jobSeqNos != null) 
		{
			jobDetails = jobWorkDetailsReadRepo.getSelectJobWorkDetail(jobSeqNos);
			jobDetailsDTOs = jobDetails != null ? this.getJobWorkDetail_DTOs(jobDetails) : null;
		}
		return jobDetailsDTOs;
		},asyncExecutor);

	return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetailsForServices(CopyOnWriteArrayList<Long> servWorkSeqNos) throws InterruptedException, ExecutionException 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<JobWorkDetail> jobDetails = null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDetailsDTOs = null;

		if (servWorkSeqNos != null) 
		{
			jobDetails = jobWorkDetailsReadRepo.getSelectJobWorkDetailForServices(servWorkSeqNos);
			jobDetailsDTOs = jobDetails != null ? this.getJobWorkDetail_DTOs(jobDetails) : null;
		}
			return jobDetailsDTOs;
			},asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetailsBetweenPlanTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException
	{
		
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		CopyOnWriteArrayList<JobWorkDetail> jobDetails = null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDetailsDTOs = null;

		if (frDtTm != null && toDtTm != null) {
			lfrdttm = LocalDateTime.parse(frDtTm, formatter);
			ltodttm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
			Timestamp toDateTime = Timestamp.valueOf(ltodttm);
			jobDetails = null;
			jobDetails = jobWorkDetailsReadRepo.getSelectJobWorkDetailBetweenPlanTimes(frDateTime, toDateTime);
			jobDetailsDTOs = jobDetails != null ? this.getJobWorkDetail_DTOs(jobDetails) : null;
		}
			return jobDetailsDTOs;
			},asyncExecutor);

		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetailsBetweenActualTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		CopyOnWriteArrayList<JobWorkDetail> jobDetails = null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDetailsDTOs = null;

		if (frDtTm != null && toDtTm != null) {
			lfrdttm = LocalDateTime.parse(frDtTm, formatter);
			ltodttm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
			Timestamp toDateTime = Timestamp.valueOf(ltodttm);
			jobDetails = null;
			jobDetails = jobWorkDetailsReadRepo.getSelectJobWorkDetailBetweenActualTimes(frDateTime, toDateTime);
		}
		return jobDetailsDTOs;
		},asyncExecutor);

		return future;
	}

	private synchronized CopyOnWriteArrayList<JobWorkDetail_DTO> getJobWorkDetail_DTOs(CopyOnWriteArrayList<JobWorkDetail> jobDetails) {
		JobWorkDetail_DTO jobDTO = null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkDetail_DTO>();

		for (int i = 0; i < jobDetails.size(); i++) {
			jobDTO = getJobWorkDetail_DTO(jobDetails.get(i));
			jobDTOs.add(jobDTO);
		}
		return jobDTOs;
	}

	private synchronized JobWorkDetail_DTO getJobWorkDetail_DTO(JobWorkDetail jobDetail) 
	{
		JobWorkDetail_DTO jobDetailDTO = new JobWorkDetail_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		jobDetailDTO.setActEndDate(formatter.format(jobDetail.getActEndDate().toLocalDateTime()));
		jobDetailDTO.setActStartDate(formatter.format(jobDetail.getActStartDate().toLocalDateTime()));
		jobDetailDTO.setPlanStartDate(formatter.format(jobDetail.getPlanStartDate().toLocalDateTime()));
		jobDetailDTO.setPlanEndDate(formatter.format(jobDetail.getPlanEndDate().toLocalDateTime()));
		jobDetailDTO.setJobSeqNo(jobDetail.getJobSeqNo());
		jobDetailDTO.setJobWorkSeqNo(jobDetail.getJobWorkSeqNo());
		jobDetailDTO.setManagerSeqNo(jobDetail.getManagerSeqNo());
		jobDetailDTO.setParentJobWorkSeqNo(jobDetail.getParentJobWorkSeqNo());
		jobDetailDTO.setServiceWorkSeqNo(jobDetail.getServiceWorkSeqNo());
		jobDetailDTO.setDoneflag('N');
		jobDetailDTO.setOkflag('N');
		return jobDetailDTO;
	}

}