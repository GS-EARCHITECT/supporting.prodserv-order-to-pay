package jobs.job_work_details_mgmt.services.sched;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import common.api.DateUtil;
import common.model.dto.IdsList_DTO;
import common.model.dto.JobWorkDetail_DTO;
import common.model.master.JobStructureTargetDetail;
import common.model.master.JobTemplateDetail;
import common.model.master.JobTemplateDetailPK;
import common.model.master.JobWorkDetail;
import jobs.job_work_details_mgmt.model.repo.cud.JobWorkDetailsCUD_Repo;
import jobs.job_mgmt.model.repo.common.*;
import jobs.job_work_master_mgmt.model.master.JobWorkMaster;
import jobs.job_work_master_mgmt.model.repo.cud.JobWorkMasterCUD_Repo;
import jobs.job_work_master_mgmt.model.repo.read.JobWorkMasterRead_Repo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("jobWorkDetailsSchedulerServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobWorkDetailsSchedule_Service implements I_JobWorkDetailsSchedule_Service 
{

	private static final Logger logger = LoggerFactory.getLogger(JobWorkDetailsSchedule_Service.class);

	@Autowired
	private WebClient webClient;

	@Autowired
	private Executor asyncExecutor;

	@Autowired
	private JobWorkMasterRead_Repo jobWorkMasterReadRepo;

	@Autowired
	private JobWorkMasterCUD_Repo jobWorkMasterCUDRepo;
	
	@Autowired
	private JobWorkDetailsRead_Repo jobWorkDetailsReadRepo;

	@Autowired
	private JobWorkDetailsCUD_Repo jobWorkDetailsCUDRepo;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	@Scheduled(fixedRate = 3000)
	public CompletableFuture<Void> scheduleJobs() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		logger.info("started schedule task");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		CopyOnWriteArrayList<JobWorkMaster> jobMasters = jobWorkMasterReadRepo.getJobsToBeScheduled();
		Long servWorkSeqNo = null;
		Long mgr_seq_no = null;
		Long jobTemplateSeqNo = null;
		Integer opFlag = null;
		String startDateTime = null;

		if (jobMasters != null) {
			for (int i2 = 0; i2 < jobMasters.size(); i2++) {
				servWorkSeqNo = jobMasters.get(i2).getServiceWorkSeqNo();
				jobTemplateSeqNo = jobMasters.get(i2).getJobTemplateSeqNo();
				mgr_seq_no = jobMasters.get(i2).getManagerSeqNo();
				startDateTime = formatter.format(jobMasters.get(i2).getCreatedOn().toLocalDateTime());
				opFlag = jobMasters.get(i2).getOpFlag();
				this.doJobsForTemplate(servWorkSeqNo, jobTemplateSeqNo, mgr_seq_no, startDateTime, opFlag);
				jobWorkMasterCUDRepo.updateScheduledServiceWork(servWorkSeqNo);
			}
		}
		logger.info("stopped schedule task");
		return ;
		}, asyncExecutor);		
		return future;					

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public CompletableFuture<Void> doJobsForTemplate(Long servWorkSeqNo, Long jobTemplateSeqNo, Long mgr_seq_no,
			String startDateTime, Integer opFlag) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		CopyOnWriteArrayList<Long> jobSeqNos = new CopyOnWriteArrayList<Long>();
		
		CopyOnWriteArrayList<JobWorkDetail> jobMasters2 = jobWorkDetailsReadRepo.getSelectJobWorkDetailForService(servWorkSeqNo);
		
		if (jobMasters2 != null && jobMasters2.size() > 0) 
		{
			jobWorkDetailsCUDRepo.delSelectJobWorkDetailsForService(servWorkSeqNo);
		}

		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> completableFuture = this.processJobWorkDetails(jobTemplateSeqNo, startDateTime, opFlag);
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDetailsDTOs = completableFuture.join();
		
		// Create Job Schedule
		for (int i = 0; i < jobDetailsDTOs.size(); i++) 
		{
			jobDetailsDTOs.get(i).setManagerSeqNo(mgr_seq_no);
			jobDetailsDTOs.get(i).setServiceWorkSeqNo(servWorkSeqNo);
			jobWorkDetailsReadRepo.save(this.setJobWorkDetail(jobDetailsDTOs.get(i))).getJobSeqNo();
		}
		return;
		}, asyncExecutor);		
		return future;

	}

	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> processJobWorkDetails(Long jobTemplateSeqNo, String startDateTime, Integer opFlag) 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.parse(startDateTime, formatter);
		Timestamp currDate = Timestamp.valueOf(ldt);
		Timestamp endDate = null;
		Long seq_no = (long) 1;
		Long jobSeqNo, jobTargetSeqNo;
		Integer months = 0, weeks = 0, days = 0, hours = 0, minutes = 0, seconds = 0, months_dur = 0, weeks_dur = 0, days_dur = 0, hours_dur = 0, mins_dur = 0, seconds_dur = 0;
		CopyOnWriteArrayList<JobTemplateDetail> jobsTemplateDetails = null;
		CopyOnWriteArrayList<JobTemplateDetail> jobsTemplateDetailsListFromParent = new CopyOnWriteArrayList<JobTemplateDetail>();
		CopyOnWriteArrayList<JobStructureTargetDetail> jobTypeStructureDetails = null;
		JobTemplateDetail jobTemplateDetailsFromParent = null;
		JobTemplateDetailPK jobTemplateDetailsPK = null;
		JobWorkDetail jobDetails5 = null;
		CopyOnWriteArrayList<JobWorkDetail> jobDetailsList = null;
		CopyOnWriteArrayList<Long> ids = null;
		CopyOnWriteArrayList<Long> tids = null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDetailsDTOsList = null;
		IdsList_DTO idsList_DTO = new IdsList_DTO(); 

		/* Prepare Sequence Array from job_templates_details */
		List<JobTemplateDetail> specList = new CopyOnWriteArrayList<JobTemplateDetail>();
		Flux<JobTemplateDetail> specs = webClient.get().uri("/jobTemplateDetails//"+jobTemplateSeqNo).retrieve().bodyToFlux(JobTemplateDetail.class);
		specList = (List<JobTemplateDetail>) specs.collectList().block(Duration.ofSeconds(10L));			
		jobsTemplateDetails = (CopyOnWriteArrayList<JobTemplateDetail>)specList;
		//jobsTemplateDetails = jobTemplateDetailsRepo.getJobsForTemplate(jobTemplateSeqNo);

		if (jobsTemplateDetails != null) 
		{
			jobDetailsList = new CopyOnWriteArrayList<JobWorkDetail>();
			jobsTemplateDetails.sort((o1, o2) -> o1.getId().getSeqNo().compareTo(o2.getId().getSeqNo()));

			ids = new CopyOnWriteArrayList<Long>();
			tids = new CopyOnWriteArrayList<Long>();
			
			for (int i = 0; i < jobsTemplateDetails.size(); i++)
			{
			ids.add(jobsTemplateDetails.get(i).getId().getJOB_SEQ_NO());
			tids.add(jobsTemplateDetails.get(i).getId().getTargetSeqNo());
			}
			
			idsList_DTO.setIds(ids);
			idsList_DTO.setTids(tids);
			
			// Update Detailed Job Template
			for (int i = 0; i < jobsTemplateDetails.size(); i++) 
			{
				jobTypeStructureDetails = null;
				List<JobStructureTargetDetail> trgList = new CopyOnWriteArrayList<JobStructureTargetDetail>();
				Flux<JobStructureTargetDetail> trgFlux = webClient.method(HttpMethod.GET).uri("/jobStructureTargetDetailsReadMgmt/getSelectJobStructureTargetDetailForParent/getSelectJobStructureTargetDetailsForParents").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).
				body(Mono.just(idsList_DTO), IdsList_DTO.class).retrieve().bodyToFlux(JobStructureTargetDetail.class);
				trgFlux.collectList().subscribe(trgList::addAll);							
				jobTypeStructureDetails = (CopyOnWriteArrayList<JobStructureTargetDetail>)trgList;

/*				jobTypeStructureDetails = jobTypeStructureDetailsRepo.getSelectJobStructureTargetDetailForParent
				(
					jobsTemplateDetails.get(i).getId().getJobTypeSeqNo(),
					jobsTemplateDetails.get(i).getId().getTargetSeqNo()
				);*/

				if (jobTypeStructureDetails != null && jobTypeStructureDetails.size() > 0) 
				{
					jobsTemplateDetails.get(i).setDurationFlag('Y');
					jobsTemplateDetailsListFromParent = new CopyOnWriteArrayList<JobTemplateDetail>();

					for (int j = 0; j < jobTypeStructureDetails.size(); j++) 
					{
						jobTemplateDetailsFromParent = new JobTemplateDetail();
						jobTemplateDetailsPK = new JobTemplateDetailPK();
						jobTemplateDetailsPK.setJobTemplateSeqNo(jobsTemplateDetails.get(i).getId().getJobTemplateSeqNo());
						jobTemplateDetailsPK.setJOB_SEQ_NO(jobTypeStructureDetails.get(j).getId().getJobSeqNo());
						jobTemplateDetailsPK.setTargetSeqNo(jobTypeStructureDetails.get(j).getId().getTargetSeqNo());
						jobTemplateDetailsFromParent.setId(jobTemplateDetailsPK);
						jobsTemplateDetailsListFromParent.add(jobTemplateDetailsFromParent);
					}
					jobsTemplateDetails.addAll(i + 1, jobsTemplateDetailsListFromParent);
				}
			}

			seq_no = (long) 1;
			for (int i = 0; i < jobsTemplateDetails.size(); i++) {
				jobsTemplateDetails.get(i).getId().setSeqNo(seq_no);
				seq_no++;
			}

			// Create Job Schedule
			for (int i = 0; i < jobsTemplateDetails.size(); i++) {
				seq_no = jobsTemplateDetails.get(i).getId().getSeqNo();
				jobSeqNo = jobsTemplateDetails.get(i).getId().getJOB_SEQ_NO();
				jobTargetSeqNo = jobsTemplateDetails.get(i).getId().getTargetSeqNo();
				jobDetails5 = new JobWorkDetail();

				if (jobsTemplateDetails.get(i).getDurationFlag() == null
						|| !jobsTemplateDetails.get(i).getDurationFlag().equals('Y')) 
				{
					//months_dur = jobTypeMasterRepo.getJobTypeDurMonths(jobTypeSeqNo);
					CompletableFuture<Float> f1 = getJobDuration(jobSeqNo, jobTargetSeqNo, "getJobDurMonths");
					months_dur = Math.round(f1.join());
					
					//weeks_dur = jobTypeMasterRepo.getJobTypeDurWeeks(jobTypeSeqNo);
					CompletableFuture<Float> f2 = getJobDuration(jobSeqNo, jobTargetSeqNo, "getJobDurWeeks");
					weeks_dur = Math.round(f2.join());
					
					//days_dur = jobTypeMasterRepo.getJobTypeDurDays(jobTypeSeqNo);
					CompletableFuture<Float> f3 = getJobDuration(jobSeqNo, jobTargetSeqNo, "getJobDurDays");
					days_dur = Math.round(f3.join());
					
					//hours_dur = jobTypeMasterRepo.getJobTypeDurHours(jobTypeSeqNo);
					CompletableFuture<Float> f4 = getJobDuration(jobSeqNo, jobTargetSeqNo, "getJobDurHours");
					hours_dur = Math.round(f4.join());
					
					//mins_dur = jobTypeMasterRepo.getJobTypeDurMinutes(jobTypeSeqNo);
					CompletableFuture<Float> f5 = getJobDuration(jobSeqNo, jobTargetSeqNo, "getJobDurMins");
					mins_dur = Math.round(f5.join());
					
					//seconds_dur = jobTypeMasterRepo.getJobTypeDurSeconds(jobTypeSeqNo);
					CompletableFuture<Float> f6 = getJobDuration(jobSeqNo, jobTargetSeqNo, "getJobDurSeconds");
					seconds_dur = Math.round(f6.join());
					
					months_dur = (months_dur == null) ? 0 : months_dur;
					months = (jobsTemplateDetails.get(i).getMonthsPlus() == null) ? 0
							: jobsTemplateDetails.get(i).getMonthsPlus();
					weeks_dur = (weeks_dur == null) ? 0 : weeks_dur;
					weeks = (jobsTemplateDetails.get(i).getWeeksPlus() == null) ? 0
							: jobsTemplateDetails.get(i).getWeeksPlus();
					days_dur = (days_dur == null) ? 0 : days_dur;
					days = (jobsTemplateDetails.get(i).getDaysPlus() == null) ? 0
							: jobsTemplateDetails.get(i).getDaysPlus();
					hours_dur = (hours_dur == null) ? 0 : hours_dur;
					hours = (jobsTemplateDetails.get(i).getHoursPlus() == null) ? 0
							: jobsTemplateDetails.get(i).getHoursPlus();
					mins_dur = (mins_dur == null) ? 0 : mins_dur;
					minutes = (jobsTemplateDetails.get(i).getMinutesPlus() == null) ? 0
							: jobsTemplateDetails.get(i).getMinutesPlus();
					seconds_dur = (seconds_dur == null) ? 0 : seconds_dur;
					seconds = (jobsTemplateDetails.get(i).getSecondsPlus() == null) ? 0
							: jobsTemplateDetails.get(i).getSecondsPlus();
					endDate = DateUtil.addDays(currDate, days_dur, hours_dur,mins_dur, seconds_dur);

					if (opFlag.compareTo(Integer.valueOf(1)) == 0) {
						jobDetails5.setPlanStartDate(currDate);
						jobDetails5.setPlanEndDate(endDate);
					} else {
						jobDetails5.setActStartDate(currDate);
						jobDetails5.setActEndDate(endDate);
					}

				}
				
				jobDetails5.setJobTemplateSeqNo(jobTemplateSeqNo);
				jobDetails5.setTargetSeqNo(jobTargetSeqNo);
				jobDetails5.setJobSeqNo(jobSeqNo);
				jobDetails5.setSeqNo(seq_no);
				jobDetailsList.add(jobDetails5);

				if (jobsTemplateDetails.get(i).getDurationFlag() == null
						|| !jobsTemplateDetails.get(i).getDurationFlag().equals('Y')) {
					currDate = DateUtil.addDays(endDate, days, hours, minutes, seconds);
				}

			}

		}
		
		if(jobDetailsList!=null)
		{
		for (int j = 0; j < jobDetailsList.size(); j++) 
		{
		jobDetailsDTOsList = this.getJobWorkDetail_DTOs(jobDetailsList);
		}	
		}
		return jobDetailsDTOsList;		
		}, asyncExecutor);		
		return future;		
	}


	private CopyOnWriteArrayList<JobWorkDetail_DTO> getJobWorkDetail_DTOs(CopyOnWriteArrayList<JobWorkDetail> jobDetails) {
		JobWorkDetail_DTO jobDTO = null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkDetail_DTO>();

		for (int i = 0; i < jobDetails.size(); i++) {
			jobDTO = getJobWorkDetail_DTO(jobDetails.get(i));
			jobDTOs.add(jobDTO);
		}
		return jobDTOs;
	}

	private JobWorkDetail_DTO getJobWorkDetail_DTO(JobWorkDetail jobDetails) {
		JobWorkDetail_DTO jobDetailsDTO = new JobWorkDetail_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		jobDetailsDTO.setActEndDate(formatter.format(jobDetails.getActEndDate().toLocalDateTime()));
		jobDetailsDTO.setActStartDate(formatter.format(jobDetails.getActStartDate().toLocalDateTime()));
		jobDetailsDTO.setPlanStartDate(formatter.format(jobDetails.getPlanStartDate().toLocalDateTime()));
		jobDetailsDTO.setPlanEndDate(formatter.format(jobDetails.getPlanEndDate().toLocalDateTime()));
		jobDetailsDTO.setJobSeqNo(jobDetails.getJobSeqNo());
		jobDetailsDTO.setJobWorkSeqNo(jobDetails.getJobWorkSeqNo());
		jobDetailsDTO.setManagerSeqNo(jobDetails.getManagerSeqNo());
		jobDetailsDTO.setParentJobWorkSeqNo(jobDetails.getParentJobWorkSeqNo());
		jobDetailsDTO.setServiceWorkSeqNo(jobDetails.getServiceWorkSeqNo());
		jobDetailsDTO.setDoneflag('N');
		jobDetailsDTO.setOkflag('N');		
		return jobDetailsDTO;
	}

	private synchronized JobWorkDetail setJobWorkDetail(JobWorkDetail_DTO cDTO) 
	{
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
		jobDetails.setJobSeqNo(cDTO.getJobSeqNo());
		jobDetails.setManagerSeqNo(cDTO.getManagerSeqNo());
		jobDetails.setParentJobWorkSeqNo(cDTO.getParentJobWorkSeqNo());
		jobDetails.setServiceWorkSeqNo(cDTO.getServiceWorkSeqNo());
		jobDetails.setDoneflag('N');
		jobDetails.setOkflag('N');		
		return jobDetails;
	}
	
	public CompletableFuture<Float> getJobDuration(Long jobSeqNo, Long trgSeqNo, String uriStr) 
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Mono<Float> durMono = webClient.method(HttpMethod.GET).uri("/jobTargetDetailsReadMgmt/"+uriStr.trim()+"/{jobSeqNo}/{trgSeqNo}").retrieve().bodyToMono(Float.class);
		Float f2 = durMono.block();
		return f2;
		}, asyncExecutor);		
		return future;						
	}
}
