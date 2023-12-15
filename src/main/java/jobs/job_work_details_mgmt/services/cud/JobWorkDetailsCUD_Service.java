package jobs.job_work_details_mgmt.services.cud;

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
import common.model.dto.JobWorkDetail_DTO;
import common.model.master.JobWorkDetail;
import jobs.job_work_details_mgmt.model.repo.cud.JobWorkDetailsCUD_Repo;

@Service("jobWorkDetailsCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobWorkDetailsCUD_Service implements I_JobWorkDetailsCUD_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(JobWorkDetailService.class);

	@Autowired
	private JobWorkDetailsCUD_Repo jobWorkDetailsCUDRepo;

	@Autowired
	private Executor asyncExecutor;
	
	@Override
	public CompletableFuture<JobWorkDetail_DTO> newJobWorkDetail(JobWorkDetail_DTO jobWorkDTO) 
	{
		CompletableFuture<JobWorkDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		JobWorkDetail_DTO jobWorkDTO2 = null;	
		if(!jobWorkDetailsCUDRepo.existsById(jobWorkDTO.getJobWorkSeqNo()))
		{	
		jobWorkDTO2 = getJobWorkDetail_DTO(jobWorkDetailsCUDRepo.save(this.setJobWorkDetail(jobWorkDTO)));
		}
		return jobWorkDTO2;		
		},asyncExecutor);

	return future;
	}

	@Override
	public CompletableFuture<Void> updJobWorkDetail(JobWorkDetail_DTO jobWorkDetailDTO) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		if (jobWorkDetailsCUDRepo.existsById(jobWorkDetailDTO.getJobWorkSeqNo()))
		jobWorkDetailsCUDRepo.save(this.setJobWorkDetail(jobWorkDetailDTO));		
		return;		
		},asyncExecutor);
		
		return future;
	}

	
	@Override
	public CompletableFuture<Void> delAllJobWorkDetails() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		jobWorkDetailsCUDRepo.deleteAll();		
		return;		
		},asyncExecutor);		
	
		return future;
	}

	@Override
	public CompletableFuture<Void> delSelectJobWorkDetails(CopyOnWriteArrayList<Long> jobWorkSeqNos) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		jobWorkDetailsCUDRepo.delSelectJobWorkDetails(jobWorkSeqNos);		
		return;		
		},asyncExecutor);		
		return future;
	}

	@Override
	public CompletableFuture<Void> delSelectJobWorkDetailsForServices(CopyOnWriteArrayList<Long> servWorkSeqNos) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		jobWorkDetailsCUDRepo.delSelectJobWorkDetailsForServices(servWorkSeqNos);		
		return;		
		},asyncExecutor);		
		return future;
	}

	@Override
	public CompletableFuture<Void> delSelectJobWorkDetailsBetweenPlanTimes(String frDtTm, String toDtTm) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		lfrdttm = LocalDateTime.parse(frDtTm, formatter);
		ltodttm = LocalDateTime.parse(toDtTm, formatter);
		Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
		Timestamp toDateTime = Timestamp.valueOf(ltodttm);
		jobWorkDetailsCUDRepo.delSelectJobWorkDetailsBetweenPlanTimes(frDateTime, toDateTime);
		return;		
		},asyncExecutor);		
		return future;
	}

	@Override
	public CompletableFuture<Void> delSelectJobWorkDetailsBetweenActualTimes(String frDtTm, String toDtTm) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		lfrdttm = LocalDateTime.parse(frDtTm, formatter);
		ltodttm = LocalDateTime.parse(toDtTm, formatter);
		Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
		Timestamp toDateTime = Timestamp.valueOf(ltodttm);
		jobWorkDetailsCUDRepo.delSelectJobWorkDetailsBetweenActualTimes(frDateTime, toDateTime);
		return;		
		},asyncExecutor);		
		return future;
	
	}

	private synchronized JobWorkDetail_DTO getJobWorkDetail_DTO(JobWorkDetail jobDetail) 
	{
		JobWorkDetail_DTO jobWorkDetailDTO = new JobWorkDetail_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		jobWorkDetailDTO.setActEndDate(formatter.format(jobDetail.getActEndDate().toLocalDateTime()));
		jobWorkDetailDTO.setActStartDate(formatter.format(jobDetail.getActStartDate().toLocalDateTime()));
		jobWorkDetailDTO.setPlanStartDate(formatter.format(jobDetail.getPlanStartDate().toLocalDateTime()));
		jobWorkDetailDTO.setPlanEndDate(formatter.format(jobDetail.getPlanEndDate().toLocalDateTime()));
		jobWorkDetailDTO.setJobSeqNo(jobDetail.getJobSeqNo());
		jobWorkDetailDTO.setJobWorkSeqNo(jobDetail.getJobSeqNo());
		jobWorkDetailDTO.setManagerSeqNo(jobDetail.getManagerSeqNo());
		jobWorkDetailDTO.setServiceWorkSeqNo(jobDetail.getServiceWorkSeqNo());
		jobWorkDetailDTO.setParentJobWorkSeqNo(jobDetail.getParentJobWorkSeqNo());		
		jobWorkDetailDTO.setDoneflag('N');
		jobWorkDetailDTO.setOkflag('N');		
		return jobWorkDetailDTO;
	}

	private JobWorkDetail setJobWorkDetail(JobWorkDetail_DTO cDTO) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime plst = LocalDateTime.parse(cDTO.getPlanStartDate(), formatter);
		LocalDateTime plto = LocalDateTime.parse(cDTO.getPlanEndDate(), formatter);
		LocalDateTime acst = LocalDateTime.parse(cDTO.getActStartDate(), formatter);
		LocalDateTime acto = LocalDateTime.parse(cDTO.getActEndDate(), formatter);
		Timestamp pFrDateTime = Timestamp.valueOf(plst);
		Timestamp pToDateTime = Timestamp.valueOf(plto);
		Timestamp aFrDateTime = Timestamp.valueOf(acst);
		Timestamp aToDateTime = Timestamp.valueOf(acto);
		JobWorkDetail jobDetails = new JobWorkDetail();
		jobDetails.setActEndDate(aToDateTime);
		jobDetails.setActStartDate(aFrDateTime);
		jobDetails.setPlanStartDate(pFrDateTime);
		jobDetails.setPlanEndDate(pToDateTime);
		jobDetails.setManagerSeqNo(cDTO.getManagerSeqNo());
		jobDetails.setParentJobWorkSeqNo(cDTO.getParentJobWorkSeqNo());
		jobDetails.setServiceWorkSeqNo(cDTO.getServiceWorkSeqNo());
		jobDetails.setDoneflag('N');
		jobDetails.setOkflag('N');
		return jobDetails;
	}
}