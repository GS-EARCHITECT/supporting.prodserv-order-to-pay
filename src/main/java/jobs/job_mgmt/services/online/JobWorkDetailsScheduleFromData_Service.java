package jobs.job_mgmt.services.online;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import common.api.DateUtil;
import common.model.dto.*;
import common.model.master.*;
import jobs.job_mgmt.model.repo.common.*;
import jobs.job_mgmt.model.repo.online.*;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

@Service("jobWorkDetailsFromDataServOnLine")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobWorkDetailsScheduleFromData_Service implements I_JobWorkDetailsScheduleFromData_Service
{

	private static final Logger logger = LoggerFactory.getLogger(JobWorkDetailsScheduleFromData_Service.class);

	@Autowired
	private JobTripRepo jobTripRepoOnline;

	@Autowired
	private JobWorkDetailsRead_Repo jobWorkDetailsReadRepo;

	@Autowired
	private JobWorkDetailsCUD_Repo jobWorkDetailsCUDRepoOnline;

	@Autowired
	private ServiceWorkMasterRead_Repo serviceWorkReadRepo;
	
	@Autowired
	private ServiceWorkMasterCUD_Repo serviceWorkCUDRepoOnline;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<Void> processJobsForService(Long newServWorkSeqNo) 
	{
		
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			Optional<ServiceWorkMaster> servWorkMaster = serviceWorkReadRepo.findById(newServWorkSeqNo);
			Long mgr_seq_no = null;			
			String startDateTime = null;
			Integer opFlag = null;
			Long copyservWorkSeqNo = (long) 0;

			if (servWorkMaster.get() != null) 
			{
				mgr_seq_no = servWorkMaster.get().getCreatedBy();
				startDateTime = formatter.format(servWorkMaster.get().getOnDate().toLocalDateTime());
				opFlag = servWorkMaster.get().getOpFlag();
				copyservWorkSeqNo = servWorkMaster.get().getCopyServiceWorkSeqNo();

				if (copyservWorkSeqNo != null && copyservWorkSeqNo > 0) 
				{
				this.processJobWorkDetailsFromData(newServWorkSeqNo, copyservWorkSeqNo,	startDateTime, opFlag, mgr_seq_no);
				}
				serviceWorkCUDRepoOnline.updJobAllocStatus(newServWorkSeqNo, 'Y');				
			}
			return;
		}, asyncExecutor);
		return future;
	}
	
	private CompletableFuture<Void> processJobWorkDetailsFromData(Long newServWorkSeqNo, Long servWorkSeqNo,
			String startDateTime, Integer opFlag, Long mgrSeqNo) {
			CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime ldt = LocalDateTime.parse(startDateTime, formatter);
			Instant t1 = null;
			Instant t2 = null;
			Long intraMilli = (long) 0;
			Long interMilli = (long) 0;
			JobWorkDetail jobDetails5 = null;
			JobTripDetails jobTripDetails = null;
			CopyOnWriteArrayList<JobWorkDetailTimesDiff_DTO> jobWorkDetailTimesDiff_DTOs = new CopyOnWriteArrayList<JobWorkDetailTimesDiff_DTO>();
			JobWorkDetailTimesDiff_DTO jobWorkDetailTimesDiff_DTO = null;
			CopyOnWriteArrayList<JobWorkDetail> jobDetailsList = jobWorkDetailsReadRepo.getSelectJobWorkDetailForService(servWorkSeqNo);
			jobDetailsList.sort((o1, o2) -> o1.getJobWorkSeqNo().compareTo(o2.getJobWorkSeqNo()));
			Timestamp currDate = Timestamp.valueOf(ldt);
			Timestamp endDate = currDate;
			
			if (jobDetailsList != null && jobDetailsList.size() > 0) 
			{
				/* job pass start
				for (int i = 0; i < jobDetailsList.size(); i++) 
				{
					if (jobDetailsList.get(i).getActStartDate() != null || jobDetailsList.get(i).getPlanStartDate() != null) 
					{
						j = i;
						found= true;
						break;
					}
					else 
					{
						jobWorkDetailTimesDiff_DTO = new JobWorkDetailTimesDiff_DTO();
						interMilli=(long) 0;
						intraMilli=(long) 0;
						jobWorkDetailTimesDiff_DTO.setInterMilliDiff(interMilli);
						jobWorkDetailTimesDiff_DTO.setIntraMilliDiff(intraMilli);
						jobWorkDetailTimesDiff_DTO.setOpFlag(opFlag);
						jobWorkDetailTimesDiff_DTO.setNoSchdFlagInteger(true);
						jobWorkDetailTimesDiff_DTO.setOldjobWorkSeqNo(jobDetailsList.get(i).getJobWorkSeqNo());
						jobWorkDetailTimesDiff_DTOs.add(jobWorkDetailTimesDiff_DTO);
					}
					
				}
				

				if(found)
				{
					
				if (jobDetailsList.get(j).getActStartDate() != null || jobDetailsList.get(j).getPlanStartDate() != null) 
				{
					if (jobDetailsList.get(j).getActStartDate() != null) {
						currDate = jobDetailsList.get(j).getActStartDate();
						
					} else {
						currDate = jobDetailsList.get(j).getPlanStartDate();
						
					}
					endDate = currDate;
				}
				*/
				
				try {

					// pass 1 - Create time diff list
					for (int i = 0; i < jobDetailsList.size(); i++) 
					{
						
						jobWorkDetailTimesDiff_DTO = new JobWorkDetailTimesDiff_DTO();
						if (jobDetailsList.get(i).getActStartDate() != null	|| jobDetailsList.get(i).getPlanStartDate() != null) 
						{
							if (jobDetailsList.get(i).getActStartDate() != null)
							{
								currDate = jobDetailsList.get(i).getActStartDate();
								
							} else {
								currDate = jobDetailsList.get(i).getPlanStartDate();								
							}
							
							interMilli = Duration.between(endDate.toInstant(), currDate.toInstant()).toMillis();
									
							if (jobDetailsList.get(i).getActStartDate() != null) 
							{
								t1 = jobDetailsList.get(i).getActStartDate().toInstant();
								t2 = jobDetailsList.get(i).getActEndDate().toInstant();
								endDate = jobDetailsList.get(i).getActEndDate();
							} else {
								t1 = jobDetailsList.get(i).getPlanStartDate().toInstant();
								t2 = jobDetailsList.get(i).getPlanEndDate().toInstant();
								endDate = jobDetailsList.get(i).getPlanEndDate();
							}
							intraMilli = Duration.between(t1, t2).toMillis();
							jobWorkDetailTimesDiff_DTO.setNoSchdFlagInteger(false);
						} 
						else 
						{
							interMilli=(long) 0;
							intraMilli=(long) 0;
							jobWorkDetailTimesDiff_DTO.setNoSchdFlagInteger(true);						
						}
						jobWorkDetailTimesDiff_DTO.setInterMilliDiff(interMilli);
						jobWorkDetailTimesDiff_DTO.setIntraMilliDiff(intraMilli);						
						jobWorkDetailTimesDiff_DTO.setOpFlag(opFlag);
						jobWorkDetailTimesDiff_DTO.setOldjobWorkSeqNo(jobDetailsList.get(i).getJobWorkSeqNo());
						jobWorkDetailTimesDiff_DTO.setJobSeqNo(jobDetailsList.get(i).getJobSeqNo());
						jobWorkDetailTimesDiff_DTO.setOldFrom(jobDetailsList.get(i).getPlanStartDate());
						jobWorkDetailTimesDiff_DTO.setOldTo(jobDetailsList.get(i).getPlanEndDate());
						jobWorkDetailTimesDiff_DTOs.add(jobWorkDetailTimesDiff_DTO);
						logger.info("before job work seq no :" + jobDetailsList.get(i).getJobWorkSeqNo());
						logger.info("before intermilli :" + jobWorkDetailTimesDiff_DTOs.get(i).getInterMilliDiff());
					}
				} catch (Exception e) {
					logger.info("error :" + e.getMessage());
				}
				currDate = Timestamp.valueOf(ldt);
				endDate = currDate;
				Timestamp oldendDate = null;
				//}

				jobWorkDetailTimesDiff_DTOs.sort((o1, o2) -> o1.getOldjobWorkSeqNo().compareTo(o2.getOldjobWorkSeqNo()));
				
				// pass 2 - Create new time list
				if (jobWorkDetailTimesDiff_DTOs != null && jobWorkDetailTimesDiff_DTOs.size() > 0) 
				{
					for (int i = 0; i < jobWorkDetailTimesDiff_DTOs.size(); i++) 
					{
						jobDetails5 = new JobWorkDetail();
						jobDetails5.setDoneflag('N');
						jobDetails5.setJobSeqNo(jobDetailsList.get(i).getJobSeqNo());
						jobDetails5.setManagerSeqNo(mgrSeqNo);
						jobDetails5.setOkflag('N');
						jobDetails5.setParentJobWorkSeqNo(jobDetailsList.get(i).getParentJobWorkSeqNo());
						jobDetails5.setSeqNo(jobDetailsList.get(i).getSeqNo());						
						jobDetails5.setJobTemplateSeqNo(jobDetailsList.get(i).getJobTemplateSeqNo());
						jobDetails5.setServiceWorkSeqNo(newServWorkSeqNo);
						jobDetails5.setTargetClassSeqNo(jobDetailsList.get(i).getTargetClassSeqNo());
						jobDetails5.setTargetSeqNo(jobDetailsList.get(i).getTargetSeqNo());
						
						if(!jobWorkDetailTimesDiff_DTOs.get(i).getNoSchdFlagInteger())
						{							
						logger.info("after job work seq no :" + jobDetailsList.get(i).getJobWorkSeqNo());
						logger.info("old from :" + jobWorkDetailTimesDiff_DTOs.get(i).getOldFrom());
						logger.info("old to :" + jobWorkDetailTimesDiff_DTOs.get(i).getOldTo());
						logger.info("after start 1 :" + currDate);	
						t1 = endDate.toInstant();
						t2 = t1.plusMillis(jobWorkDetailTimesDiff_DTOs.get(i).getInterMilliDiff());
						currDate = Timestamp.from(t2);
						endDate = Timestamp.from(t2.plusMillis(jobWorkDetailTimesDiff_DTOs.get(i).getIntraMilliDiff()));						
						logger.info("after intermilli :" + jobWorkDetailTimesDiff_DTOs.get(i).getInterMilliDiff());
						logger.info("after new end date :"+endDate);						
						logger.info("after start 2 :" + currDate);
												
						if (opFlag.compareTo(Integer.valueOf(1)) == 0) 
						{
							jobDetails5.setPlanStartDate(currDate);
							jobDetails5.setPlanEndDate(endDate);
							oldendDate=jobWorkDetailTimesDiff_DTOs.get(i).getOldTo();
						}
						else 
						{
							jobDetails5.setActStartDate(currDate);
							jobDetails5.setActEndDate(endDate);
							oldendDate=jobWorkDetailTimesDiff_DTOs.get(i).getOldTo();
						}
						}
						jobDetails5=jobWorkDetailsCUDRepoOnline.save(jobDetails5);
						
						//if(jobWorkDetailTimesDiff_DTOs.get(i).getNoSchdFlagInteger())
						//{
						jobTripDetails = jobTripRepoOnline.checkJobDetails(jobWorkDetailTimesDiff_DTOs.get(i).getOldjobWorkSeqNo());
						
						if(jobTripDetails!=null)
						{	
						if (opFlag.compareTo(Integer.valueOf(1)) == 0)
						{							
						endDate=getTripDetailsFromData(jobDetails5.getJobWorkSeqNo(),jobWorkDetailTimesDiff_DTOs.get(i).getOldjobWorkSeqNo(), jobWorkDetailTimesDiff_DTOs.get(i).getJobSeqNo(), endDate, oldendDate, 1);																	
						}
						else
						{
						endDate=getTripDetailsFromData(jobDetails5.getJobWorkSeqNo(),jobWorkDetailTimesDiff_DTOs.get(i).getOldjobWorkSeqNo(), jobWorkDetailTimesDiff_DTOs.get(i).getJobSeqNo(), endDate, oldendDate,  0);	
						}
						}
						//}
																							
					}
						
				}

			}
			return;
		}, asyncExecutor);

		return future;
	}

	private Timestamp getTripDetailsFromData(Long jobWorkSeqNo, Long refJobWorkSeqNo, Long jobSeqNo, Timestamp planStDate, Timestamp oldplanStDate, Integer opFlag)
	{
	CopyOnWriteArrayList<Timestamp> outList = new CopyOnWriteArrayList<Timestamp>();
	JobTripDetails jobTripDetails2 = null;
	CopyOnWriteArrayList<JobTripDetails> jobTripDetails = null;
	JobTripDetailsPK jobTripDetailsPK2 = null;	
	Timestamp reTimestamp = null;;
	boolean stFlag = true;
	Timestamp pl_EndDate = null;
	//Timestamp ac_EndDate = null;
	long milliseconds_intra = 0, milliseconds_inter  = 0;		
			
	if(opFlag==1)
	{	
	jobTripDetails = jobTripRepoOnline.getJobPlanDetails(refJobWorkSeqNo);
	}
	else
	{
	jobTripDetails = jobTripRepoOnline.getJobActualDetails(refJobWorkSeqNo);
	}
	
	if(opFlag==1)
	{
	if(jobTripDetails!=null)
	{	
	for (int i = 0; i < jobTripDetails.size(); i++) 
	{
		milliseconds_intra = 0;
		milliseconds_inter  = 0;
		
		if(stFlag)
		{
		stFlag=false;
		if(oldplanStDate!=null)
		{
		milliseconds_inter = jobTripDetails.get(i).getPlan_start_date().getTime() - oldplanStDate.getTime();
		planStDate = DateUtil.addMilli(planStDate, milliseconds_inter);
		}
		}
		else
		{				
		if(jobTripDetails.get(i).getPlan_start_date()!=null)
		{	
		milliseconds_inter = jobTripDetails.get(i).getPlan_start_date().getTime() - jobTripDetails.get(i-1).getPlan_end_date().getTime();		
		}		
		planStDate = DateUtil.addMilli(pl_EndDate, milliseconds_inter);		
		}
			
		if(jobTripDetails.get(i).getPlan_start_date()!=null && jobTripDetails.get(i).getPlan_end_date()!=null)
		{
		milliseconds_intra = jobTripDetails.get(i).getPlan_end_date().getTime() - jobTripDetails.get(i).getPlan_start_date().getTime();
		}		
		pl_EndDate = DateUtil.addMilli(planStDate, milliseconds_intra);
				
		jobTripDetails2 = new JobTripDetails();
		jobTripDetailsPK2 = new JobTripDetailsPK(jobWorkSeqNo, jobTripDetails.get(i).getJob_Trip_pk().getModeSeqNo(),jobTripDetails.get(i).getJob_Trip_pk().getFromLocationSeqNo(), jobTripDetails.get(i).getJob_Trip_pk().getToLocationSeqNo()); 
		jobTripDetails2.setJob_Trip_pk(jobTripDetailsPK2);
		jobTripDetails2.setPlan_start_date(planStDate);
		jobTripDetails2.setPlan_end_date(pl_EndDate);
		jobTripDetails2.setJobSeqNo(jobSeqNo);
		outList.add(pl_EndDate);
		jobTripRepoOnline.save(jobTripDetails2);
	}
	reTimestamp = Collections.max(outList);
	logger.info("MAX PLAN END DATE :"+reTimestamp);
	}
	else
	{
	logger.info("trips EMPTY");	
	}	
	}
	
	/*
	if(opFlag==0) 
	{
		if(jobTripDetails!=null)
		{
		for (int i = 0; i < jobTripDetails.size(); i++) 
		{
			milliseconds_intra = 0;
			milliseconds_inter  = 0;
			
			if(stFlag)
			{
			stFlag=false;	
			}
			else
			{				
			if(jobTripDetails.get(i).getAct_start_date()!=null)
			{	
			milliseconds_inter = jobTripDetails.get(i).getAct_start_date().getTime() - jobTripDetails.get(i-1).getAct_end_date().getTime();		
			}		
			actStDate = DateUtil.addMilli(ac_EndDate, milliseconds_inter);		
			}
				
			if(jobTripDetails.get(i).getAct_start_date()!=null && jobTripDetails.get(i).getAct_end_date()!=null)
			{
			milliseconds_intra = jobTripDetails.get(i).getAct_end_date().getTime() - jobTripDetails.get(i).getAct_start_date().getTime();
			}		
			ac_EndDate = DateUtil.addMilli(actStDate, milliseconds_intra);
					
			jobTripDetails2 = new JobTripDetails();
			jobTripDetailsPK2 = new JobTripDetailsPK(jobWorkSeqNo, jobTripDetails.get(i).getJob_Trip_pk().getModeSeqNo(),jobTripDetails.get(i).getJob_Trip_pk().getFromLocationSeqNo(), jobTripDetails.get(i).getJob_Trip_pk().getToLocationSeqNo()); 
			jobTripDetails2.setJob_Trip_pk(jobTripDetailsPK2);
			jobTripDetails2.setAct_start_date(actStDate);
			jobTripDetails2.setAct_end_date(ac_EndDate);
			jobTripDetails2.setJobSeqNo(jobSeqNo);
			outList.add(ac_EndDate);
			jobTripRepoOnline.save(jobTripDetails2);
		}
		reTimestamp = Collections.max(outList);
		}
		else
		{
		logger.info("trips EMPTY");	
		}	
	}		
	*/
	
	return reTimestamp;	
	}

// OLD TRIP BASIS
		@SuppressWarnings("unused")
		private Timestamp setTripDetailsFromDatass(Long jobWorkSeqNo, Long refJobWorkSeqNo, Long jobSeqNo,
				Timestamp planStDate, Timestamp oldplanStDate, Timestamp actStDate, Integer opFlag) {
			CopyOnWriteArrayList<Timestamp> outList = new CopyOnWriteArrayList<Timestamp>();
			JobTripDetails jobTripDetails2 = null;
			CopyOnWriteArrayList<JobTripDetails> jobTripDetails = null;
			JobTripDetailsPK jobTripDetailsPK2 = null;
			Timestamp reTimestamp = null;
			;
			boolean stFlag = true;
			Timestamp pl_EndDate = null;
			Timestamp ac_EndDate = null;
			long milliseconds_intra = 0, milliseconds_inter = 0;
			logger.info("planned strt :" + planStDate);
			logger.info("ref job  :" + refJobWorkSeqNo);

			if (opFlag == 1) {
				jobTripDetails = jobTripRepoOnline.getJobPlanDetails(refJobWorkSeqNo);
			} else {
				jobTripDetails = jobTripRepoOnline.getJobActualDetails(refJobWorkSeqNo);
			}

			if (opFlag == 1) {
				if (jobTripDetails != null) {
					for (int i = 0; i < jobTripDetails.size(); i++) {
						milliseconds_intra = 0;
						milliseconds_inter = 0;

						if (stFlag) {
							stFlag = false;
							if (oldplanStDate != null) {
								milliseconds_inter = jobTripDetails.get(i).getPlan_start_date().getTime()
										- oldplanStDate.getTime();
								planStDate = DateUtil.addMilli(planStDate, milliseconds_inter);
							}
						} else {
							if (jobTripDetails.get(i).getPlan_start_date() != null) {
								milliseconds_inter = jobTripDetails.get(i).getPlan_start_date().getTime()
										- jobTripDetails.get(i - 1).getPlan_end_date().getTime();
							}
							planStDate = DateUtil.addMilli(pl_EndDate, milliseconds_inter);
						}

						if (jobTripDetails.get(i).getPlan_start_date() != null
								&& jobTripDetails.get(i).getPlan_end_date() != null) {
							milliseconds_intra = jobTripDetails.get(i).getPlan_end_date().getTime()
									- jobTripDetails.get(i).getPlan_start_date().getTime();
						}
						pl_EndDate = DateUtil.addMilli(planStDate, milliseconds_intra);

						jobTripDetails2 = new JobTripDetails();
						jobTripDetailsPK2 = new JobTripDetailsPK(jobWorkSeqNo,
								jobTripDetails.get(i).getJob_Trip_pk().getModeSeqNo(),
								jobTripDetails.get(i).getJob_Trip_pk().getFromLocationSeqNo(),
								jobTripDetails.get(i).getJob_Trip_pk().getToLocationSeqNo());
						jobTripDetails2.setJob_Trip_pk(jobTripDetailsPK2);
						jobTripDetails2.setPlan_start_date(planStDate);
						jobTripDetails2.setPlan_end_date(pl_EndDate);
						jobTripDetails2.setJobSeqNo(jobSeqNo);
						outList.add(pl_EndDate);
						jobTripRepoOnline.save(jobTripDetails2);
					}
					reTimestamp = Collections.max(outList);
				} else {
					logger.info("trips EMPTY");
				}
			}

			if (opFlag == 0) {
				if (jobTripDetails != null) {
					for (int i = 0; i < jobTripDetails.size(); i++) {
						milliseconds_intra = 0;
						milliseconds_inter = 0;

						if (stFlag) {
							stFlag = false;
						} else {
							if (jobTripDetails.get(i).getAct_start_date() != null) {
								milliseconds_inter = jobTripDetails.get(i).getAct_start_date().getTime()
										- jobTripDetails.get(i - 1).getAct_end_date().getTime();
							}
							actStDate = DateUtil.addMilli(ac_EndDate, milliseconds_inter);
						}

						if (jobTripDetails.get(i).getAct_start_date() != null
								&& jobTripDetails.get(i).getAct_end_date() != null) {
							milliseconds_intra = jobTripDetails.get(i).getAct_end_date().getTime()
									- jobTripDetails.get(i).getAct_start_date().getTime();
						}
						ac_EndDate = DateUtil.addMilli(actStDate, milliseconds_intra);

						jobTripDetails2 = new JobTripDetails();
						jobTripDetailsPK2 = new JobTripDetailsPK(jobWorkSeqNo,
								jobTripDetails.get(i).getJob_Trip_pk().getModeSeqNo(),
								jobTripDetails.get(i).getJob_Trip_pk().getFromLocationSeqNo(),
								jobTripDetails.get(i).getJob_Trip_pk().getToLocationSeqNo());
						jobTripDetails2.setJob_Trip_pk(jobTripDetailsPK2);
						jobTripDetails2.setAct_start_date(actStDate);
						jobTripDetails2.setAct_end_date(ac_EndDate);
						jobTripDetails2.setJobSeqNo(jobSeqNo);
						outList.add(ac_EndDate);
						jobTripRepoOnline.save(jobTripDetails2);
					}
					reTimestamp = Collections.max(outList);
				} else {
					logger.info("trips EMPTY");
				}
			}

			return reTimestamp;
		}

		// OLD TRIP BASIS - END

	
	
}