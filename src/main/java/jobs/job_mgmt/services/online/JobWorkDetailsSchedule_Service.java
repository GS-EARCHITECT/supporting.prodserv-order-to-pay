package jobs.job_mgmt.services.online;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import common.model.master.JobTripDetails;
import common.model.master.JobTripDetailsPK;
import common.model.master.JobWorkDetail;
import common.api.DateUtil;
import common.model.dto.JobWorkDetail_DTO;
import common.model.master.*;
import jobs.job_mgmt.model.dto.JobTripReturn_DTO;
import jobs.job_mgmt.model.repo.common.*;
import jobs.job_mgmt.model.repo.online.*;
import java.sql.Timestamp;
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

@Service("jobWorkDetailsSchedulerServOnLine")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobWorkDetailsSchedule_Service implements I_JobWorkDetailsSchedule_Service 
{

	private static final Logger logger = LoggerFactory.getLogger(JobWorkDetailsSchedule_Service.class);

	@Autowired
	private JobsTripTemplateRepo jobTripsTemplateRepo;

	@Autowired
	private JobTripRepo jobTripRepoOnline;

	@Autowired
	private ServiceWorkMasterRead_Repo serviceWorkReadRepo;

	@Autowired
	private JobTemplateDetailsRead_Repo jobTemplateRepo;

	@Autowired
	private ServiceWorkMasterCUD_Repo serviceWorkCUDRepoOnline;

	@Autowired
	private JobWorkDetailsRead_Repo jobWorkDetailsReadRepo;

	@Autowired
	private JobStructureTargetDetailsRead_Repo jobStructureTargetDetailsReadRepo;

	@Autowired
	private JobWorkDetailsCUD_Repo jobWorkDetailsCUDRepoOnline;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<Void> processJobsForService(Long servWorkSeqNo) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			Optional<ServiceWorkMaster> servWorkMaster = serviceWorkReadRepo.findById(servWorkSeqNo);
			Long mgr_seq_no = null;
			Long jobTemplateSeqNo = null;
			String startDateTime = null;
			Integer opFlag = null;
			Long copyservWorkSeqNo = (long) 0;

			if (servWorkMaster.get() != null) 
			{
				jobTemplateSeqNo = servWorkMaster.get().getJobTemplateSeqNo();
				mgr_seq_no = servWorkMaster.get().getCreatedBy();
				startDateTime = formatter.format(servWorkMaster.get().getOnDate().toLocalDateTime());
				opFlag = servWorkMaster.get().getOpFlag();
				copyservWorkSeqNo = servWorkMaster.get().getCopyServiceWorkSeqNo();

				if (copyservWorkSeqNo == null || copyservWorkSeqNo <= 0) {
					this.doJobsForTemplate(servWorkSeqNo, jobTemplateSeqNo, mgr_seq_no, startDateTime, opFlag).join();
				}


				serviceWorkCUDRepoOnline.updJobAllocStatus(servWorkSeqNo, 'Y');
				logger.info("updated service work  :" + servWorkSeqNo);
			}
			return;
		}, asyncExecutor);
		return future;
	}

	private CompletableFuture<Void> doJobsForTemplate(Long servWorkSeqNo, Long jobTemplateSeqNo, Long mgr_seq_no,
			String startDateTime, Integer opFlag) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			Long jobWorkSeqNo = (long) 0;
			JobTripDetails jobTripDetails = null;
			CopyOnWriteArrayList<JobWorkDetail> jobMasters2 = jobWorkDetailsReadRepo
					.getSelectJobWorkDetailForService(servWorkSeqNo);

			if (jobMasters2 != null && jobMasters2.size() > 0) {
				jobWorkDetailsCUDRepoOnline.delSelectJobWorkDetailsForService(servWorkSeqNo);
			}

			for (int l = 0; l < jobMasters2.size(); l++) {
				jobWorkSeqNo = jobMasters2.get(l).getJobWorkSeqNo();
				jobTripDetails = jobTripRepoOnline.checkJobDetails(jobWorkSeqNo);

				if (jobTripDetails != null) {
					jobTripRepoOnline.delJobDetails(jobWorkSeqNo);
				}
			}

			this.processJobWorkDetailsForMaster(servWorkSeqNo, jobTemplateSeqNo, startDateTime, opFlag, mgr_seq_no);

			return;
		}, asyncExecutor);
		return future;

	}

	// MASTER TEMPLATE BASIS - START
	private CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> processJobWorkDetailsForMaster(
			Long servWorkSeqNo, Long jobTemplateSeqNo, String startDateTime, Integer opFlag, Long mgrSeqNo) {
			
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime ldt = LocalDateTime.parse(startDateTime, formatter);
			Timestamp currDate = Timestamp.valueOf(ldt);
			Timestamp endDate = currDate;
			JobTripReturn_DTO jobTripReturn_DTO = null;
			Long seq_no = (long) 1;
			Integer months = 0, weeks = 0, days = 0, hours = 0, minutes = 0, seconds = 0, months_dur = 0, weeks_dur = 0,
					days_dur = 0, hours_dur = 0, mins_dur = 0, seconds_dur = 0;
			CopyOnWriteArrayList<JobTemplateDetail> jobsTemplateDetails = null;
			CopyOnWriteArrayList<JobTemplateDetail> jobsTemplateDetailsListFromParent = new CopyOnWriteArrayList<JobTemplateDetail>();
			CopyOnWriteArrayList<JobStructureTargetDetail> jobTypeStructureDetails = null;
			JobTemplateDetail jobTemplateDetailsFromParent = null;
			JobTemplateDetailPK jobTemplateDetailsPK = null;
			JobWorkDetail jobDetails5 = null;
			JobWorkDetail jobDetails4 = null;
			CopyOnWriteArrayList<JobWorkDetail> jobDetailsList = null;
			CopyOnWriteArrayList<JobWorkDetail_DTO> jobDetailsDTOsList = null;

			/* Prepare Sequence Array from job_templates_details */
			jobsTemplateDetails = jobTemplateRepo.getJobsForTemplate(jobTemplateSeqNo);

			if (jobsTemplateDetails != null) 
			{
				jobDetailsList = new CopyOnWriteArrayList<JobWorkDetail>();
				jobsTemplateDetails.sort((o1, o2) -> o1.getId().getSeqNo().compareTo(o2.getId().getSeqNo()));

				// Update Detailed Job Template
				for (int i = 0; i < jobsTemplateDetails.size(); i++) {
					jobTypeStructureDetails = null;
					jobTypeStructureDetails = jobStructureTargetDetailsReadRepo.getSelectJobStructureTargetDetailsForParent(jobsTemplateDetails.get(i).getId().getJOB_SEQ_NO(),	jobsTemplateDetails.get(i).getId().getTargetSeqNo());					
					if (jobTypeStructureDetails != null && jobTypeStructureDetails.size() > 0) 
					{
						jobsTemplateDetails.get(i).setDurationFlag('Y');
						jobsTemplateDetailsListFromParent = new CopyOnWriteArrayList<JobTemplateDetail>();

						for (int j = 0; j < jobTypeStructureDetails.size(); j++) {
							jobTemplateDetailsFromParent = new JobTemplateDetail();
							jobTemplateDetailsFromParent.setDaysPlus(jobTypeStructureDetails.get(j).getPd());
							jobTemplateDetailsFromParent.setMonthsPlus(jobTypeStructureDetails.get(j).getPmo());
							jobTemplateDetailsFromParent.setWeeksPlus(jobTypeStructureDetails.get(j).getPw());
							jobTemplateDetailsFromParent.setHoursPlus(jobTypeStructureDetails.get(j).getPh());
							jobTemplateDetailsFromParent.setMinutesPlus(jobTypeStructureDetails.get(j).getPm());
							jobTemplateDetailsFromParent.setSecondsPlus(jobTypeStructureDetails.get(j).getPs());
							jobTemplateDetailsFromParent.setDurDays(jobTypeStructureDetails.get(j).getDd());
							jobTemplateDetailsFromParent.setDurWeeks(jobTypeStructureDetails.get(j).getDw());
							jobTemplateDetailsFromParent.setDurMonths(jobTypeStructureDetails.get(j).getDmo());
							jobTemplateDetailsFromParent.setDurHours(jobTypeStructureDetails.get(j).getDh());
							jobTemplateDetailsFromParent.setDurMinutes(jobTypeStructureDetails.get(j).getDm());
							jobTemplateDetailsFromParent.setDurSeconds(jobTypeStructureDetails.get(j).getDs());
							jobTemplateDetailsPK = new JobTemplateDetailPK();
							jobTemplateDetailsPK
									.setJobTemplateSeqNo(jobsTemplateDetails.get(i).getId().getJobTemplateSeqNo());
							jobTemplateDetailsPK.setJOB_SEQ_NO(jobTypeStructureDetails.get(j).getId().getJobSeqNo());
							jobTemplateDetailsPK
									.setTargetSeqNo(jobTypeStructureDetails.get(j).getId().getTargetSeqNo());
							jobTemplateDetailsFromParent.setId(jobTemplateDetailsPK);
							jobsTemplateDetailsListFromParent.add(jobTemplateDetailsFromParent);
						}
						jobsTemplateDetails.addAll(i + 1, jobsTemplateDetailsListFromParent);
					}
				}

				seq_no = (long) 1;
				for (int i = 0; i < jobsTemplateDetails.size(); i++) {
					jobsTemplateDetails.get(i).getId().setSeqNo(seq_no);
					logger.info("job :"+jobsTemplateDetails.get(i).getId().getSeqNo());
					seq_no++;
				}

										
				// Create Job Schedule
				for (int i = 0; i < jobsTemplateDetails.size(); i++) 
				{
					jobDetails5 = new JobWorkDetail();
					jobDetails5.setJobTemplateSeqNo(jobsTemplateDetails.get(i).getId().getJobTemplateSeqNo());
					jobDetails5.setTargetSeqNo(jobsTemplateDetails.get(i).getId().getTargetSeqNo());
					jobDetails5.setJobSeqNo(jobsTemplateDetails.get(i).getId().getJOB_SEQ_NO());
					jobDetails5.setSeqNo(jobsTemplateDetails.get(i).getId().getSeqNo());
					jobDetails5.setTargetClassSeqNo(jobsTemplateDetails.get(i).getId().getTargetClassSeqNo());
					jobDetails5.setServiceWorkSeqNo(servWorkSeqNo);
					jobDetails5.setManagerSeqNo(mgrSeqNo);
					jobDetails5.setDoneflag('N');
					jobDetails5.setOkflag('N');

					if (jobsTemplateDetails.get(i).getDurationFlag() == null
							|| !jobsTemplateDetails.get(i).getDurationFlag().equals('Y')) 
					{
						
							months_dur = (jobsTemplateDetails.get(i).getDurMonths() == null) ? 0
									: jobsTemplateDetails.get(i).getDurMonths();
							weeks_dur = (jobsTemplateDetails.get(i).getDurWeeks() == null) ? 0
									: jobsTemplateDetails.get(i).getDurWeeks();
							days_dur = (jobsTemplateDetails.get(i).getDurDays() == null) ? 0
									: jobsTemplateDetails.get(i).getDurDays();
							hours_dur = (jobsTemplateDetails.get(i).getDurHours() == null) ? 0
									: jobsTemplateDetails.get(i).getDurHours();
							mins_dur = (jobsTemplateDetails.get(i).getDurMinutes() == null) ? 0
									: jobsTemplateDetails.get(i).getDurMinutes();
							seconds_dur = (jobsTemplateDetails.get(i).getDurSeconds() == null) ? 0
									: jobsTemplateDetails.get(i).getDurSeconds();
							months = (jobsTemplateDetails.get(i).getMonthsPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getMonthsPlus();
							weeks = (jobsTemplateDetails.get(i).getWeeksPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getWeeksPlus();
							days = (jobsTemplateDetails.get(i).getDaysPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getDaysPlus();
							hours = (jobsTemplateDetails.get(i).getHoursPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getHoursPlus();
							minutes = (jobsTemplateDetails.get(i).getMinutesPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getMinutesPlus();
							seconds = (jobsTemplateDetails.get(i).getSecondsPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getSecondsPlus();

							if (months_dur != null && months_dur > 0) {
								days_dur = days_dur + (months_dur * 4 * 7);
							}

							if (months != null && months > 0) {
								days = days + (months * 4 * 7);
							}

							if (weeks_dur != null && weeks_dur > 0) {
								days_dur = days_dur + (weeks_dur * 7);
							}

							if (weeks != null && weeks > 0) {
								days_dur = days_dur + (weeks * 7);
							}

							currDate = DateUtil.addDays(endDate, days, hours, minutes, seconds);
							endDate = DateUtil.addDays(currDate, days_dur, hours_dur, mins_dur, seconds_dur);

							if (opFlag.compareTo(Integer.valueOf(1)) == 0) {
								jobDetails5.setPlanStartDate(currDate);
								jobDetails5.setPlanEndDate(endDate);
							} else {
								jobDetails5.setActStartDate(currDate);
								jobDetails5.setActEndDate(endDate);
							}
													
					}
						
					jobDetailsList.add(jobDetails5);
					jobDetails4 = jobWorkDetailsCUDRepoOnline.save(jobDetails5);
					
					if (jobsTemplateDetails.get(i).getJobType() != null	&& jobsTemplateDetails.get(i).getJobType().toUpperCase().equals("MOVE")) 
					{						
					JobTripTemplateDetail jobTripTemplateDetail = jobTripsTemplateRepo.getJobTripsStatusTemplate(jobsTemplateDetails.get(i).getId().getJobTemplateSeqNo(),jobsTemplateDetails.get(i).getId().getJOB_SEQ_NO());

						if (jobTripTemplateDetail != null) 
						{
							jobTripReturn_DTO = setTripDetailsFromMaster(jobDetails4.getJobWorkSeqNo(), jobDetails4.getJobSeqNo(),
									jobsTemplateDetails.get(i).getId().getJobTemplateSeqNo(), endDate, opFlag).join();
							currDate = jobTripReturn_DTO.getStartFrom();
							endDate = jobTripReturn_DTO.getEndOn();
							
							if (opFlag.compareTo(Integer.valueOf(1)) == 0)
							{								
							logger.info("bef plan start :"+currDate);
							logger.info("bef plan end :"+endDate);
							logger.info("job work seq :"+jobDetails4.getJobWorkSeqNo());							
							jobWorkDetailsCUDRepoOnline.updPlanStartAndEnd(currDate, endDate, jobDetails4.getJobWorkSeqNo());	
							logger.info("plan start :"+currDate);
							logger.info("plan end :"+endDate);
							}
							else
							{
							jobWorkDetailsCUDRepoOnline.updActualStartAndEnd(currDate, endDate, jobDetails4.getJobWorkSeqNo());	
							}
							
							/*days = (jobsTemplateDetails.get(i).getDaysPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getDaysPlus();
							hours = (jobsTemplateDetails.get(i).getHoursPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getHoursPlus();
							minutes = (jobsTemplateDetails.get(i).getMinutesPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getMinutesPlus();
							seconds = (jobsTemplateDetails.get(i).getSecondsPlus() == null) ? 0
									: jobsTemplateDetails.get(i).getSecondsPlus();
							currDate = DateUtil.addDays(endDate, days, hours, minutes, seconds);*/
						}
					}

				}

				if (jobDetailsList != null) {
					jobDetailsDTOsList = this.getJobWorkDetail_DTOs(jobDetailsList);
				}
			}

			return jobDetailsDTOsList;
		}, asyncExecutor);
		return future;
	}

	private CompletableFuture<JobTripReturn_DTO> setTripDetailsFromMaster(Long jobSeqNo, Long job_seq_no, Long template_seq_no,
			Timestamp startDate, Integer opFlag)
	{
		CompletableFuture<JobTripReturn_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
			Timestamp endDate = null;
			CopyOnWriteArrayList<Timestamp> endDatesList = new CopyOnWriteArrayList<Timestamp>();
			Timestamp startDate2 = startDate;
			JobTripDetails jobTripDetails = null;
			JobTripDetailsPK jobTripDetailsPK = null;
			Long fr_Loc_Seq_no, to_Loc_Seq_no = null;
			Integer mode_opt, days_dur, hours_dur, mins_dur, seconds_dur = 0;
			Integer days, hours, minutes, seconds;
			CopyOnWriteArrayList<JobTripTemplateDetail> jobsTripTemplateDetails = null;

			// Prepare Sequence Array from job_templates_details
			jobsTripTemplateDetails = jobTripsTemplateRepo.getJobTripsForTemplate(template_seq_no, job_seq_no);
			jobsTripTemplateDetails.sort((o1, o2) -> o1.getId().getSeqNo().compareTo(o2.getId().getSeqNo()));
			JobTripReturn_DTO jobTripReturn_DTO = new JobTripReturn_DTO();;
			boolean stFlag = true;
			
			for (int i = 0; i < jobsTripTemplateDetails.size(); i++) {
				days_dur = 0;
				hours_dur = 0;
				mins_dur = 0;
				seconds_dur = 0;
				days_dur = (jobsTripTemplateDetails.get(i).getDur_days() == null) ? 0
						: jobsTripTemplateDetails.get(i).getDur_days();
				hours_dur = (jobsTripTemplateDetails.get(i).getDur_hours() == null) ? 0
						: jobsTripTemplateDetails.get(i).getDur_hours();
				mins_dur = (jobsTripTemplateDetails.get(i).getDur_miinutes() == null) ? 0
						: jobsTripTemplateDetails.get(i).getDur_miinutes();
				seconds_dur = (jobsTripTemplateDetails.get(i).getDur_seconds() == null) ? 0
						: jobsTripTemplateDetails.get(i).getDur_seconds();
				days = (jobsTripTemplateDetails.get(i).getDaysPlus() == null) ? 0
						: jobsTripTemplateDetails.get(i).getDaysPlus();
				hours = (jobsTripTemplateDetails.get(i).getHoursPlus() == null) ? 0
						: jobsTripTemplateDetails.get(i).getHoursPlus();
				minutes = (jobsTripTemplateDetails.get(i).getMinutesPlus() == null) ? 0
						: jobsTripTemplateDetails.get(i).getMinutesPlus();
				seconds = (jobsTripTemplateDetails.get(i).getSecondsPlus() == null) ? 0
						: jobsTripTemplateDetails.get(i).getSecondsPlus();
				endDate = DateUtil.addDays(startDate2, days_dur, hours_dur, mins_dur, seconds_dur);
				endDatesList.add(endDate);
				fr_Loc_Seq_no = jobsTripTemplateDetails.get(i).getFromLocationSeqNo();
				to_Loc_Seq_no = jobsTripTemplateDetails.get(i).getToLocationSeqNo();
				mode_opt = jobsTripTemplateDetails.get(i).getModeSeqNo();
				jobTripDetails = new JobTripDetails();
				jobTripDetailsPK = new JobTripDetailsPK(jobSeqNo, mode_opt, fr_Loc_Seq_no, to_Loc_Seq_no);
				jobTripDetails.setJob_Trip_pk(jobTripDetailsPK);
				jobTripDetails.setJobSeqNo(job_seq_no);				
				startDate2 = DateUtil.addDays(endDate, days, hours, minutes, seconds);
				
				if (opFlag == 1) 
				{
					jobTripDetails.setPlan_start_date(startDate2);
					jobTripDetails.setPlan_end_date(endDate);
				}
				else 
				{
					jobTripDetails.setAct_start_date(startDate2);
					jobTripDetails.setAct_end_date(endDate);
				}
				
				jobTripRepoOnline.save(jobTripDetails);
				
				if(stFlag)
				{
				stFlag=false;	
				jobTripReturn_DTO.setStartFrom(startDate2);	
				}
			}

			Timestamp retTimestamp = Collections.max(endDatesList);
			jobTripReturn_DTO.setEndOn(retTimestamp);

			return jobTripReturn_DTO;
		}, asyncExecutor);
		return future;
	}

	// MASTER TEMPLATE BASIS - END
	private synchronized CopyOnWriteArrayList<JobWorkDetail_DTO> getJobWorkDetail_DTOs(
			CopyOnWriteArrayList<JobWorkDetail> jobDetails) {
		JobWorkDetail_DTO jobDTO = null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDTOs = new CopyOnWriteArrayList<JobWorkDetail_DTO>();
		for (int i = 0; i < jobDetails.size(); i++) {
			jobDTO = this.getJobWorkDetail_DTO(jobDetails.get(i));
			jobDTOs.add(jobDTO);
		}
		return jobDTOs;
	}

	private synchronized JobWorkDetail_DTO getJobWorkDetail_DTO(JobWorkDetail jobDetails) {
		JobWorkDetail_DTO jobDetailsDTO = new JobWorkDetail_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		if (jobDetails.getActEndDate() != null)
			jobDetailsDTO.setActEndDate(formatter.format(jobDetails.getActEndDate().toLocalDateTime()));

		if (jobDetails.getActStartDate() != null)
			jobDetailsDTO.setActStartDate(formatter.format(jobDetails.getActStartDate().toLocalDateTime()));

		if (jobDetails.getPlanStartDate() != null)
			jobDetailsDTO.setPlanStartDate(formatter.format(jobDetails.getPlanStartDate().toLocalDateTime()));

		if (jobDetails.getPlanEndDate() != null)
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

}