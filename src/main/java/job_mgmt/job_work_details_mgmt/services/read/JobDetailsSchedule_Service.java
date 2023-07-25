package job_mgmt.job_work_details_mgmt.services.read;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import job_mgmt.job_work_details_mgmt.controller.DateUtil;
import job_mgmt.job_work_details_mgmt.model.details.JobDetails;
import job_mgmt.job_work_details_mgmt.model.repo.cud.JobDetailsRepo;
import job_mgmt.job_work_master_mgmt.model.repo.read.JobWorkMasterRepo;

@Service("jobDetailsScheduleServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobDetailsSchedule_Service implements I_JobDetailsSchedule_Service 
{

	private static final Logger logger = LoggerFactory.getLogger(JobDetailsSchedule_Service.class);

	@Autowired
	private JobWorkMasterRepo jobWorkMasterRepo;

	@Autowired
	private JobDetailsRepo jobDetailsRepo;


	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	@Scheduled(fixedRate = 3000)
	public void scheduleJobs() 
	{
		
		logger.info("started schedule task");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		CopyOnWriteArrayList<JobMaster> jobMasters = jobMasterRepo.getJobsToBeScheduled();
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
				doJobsForTemplate(servWorkSeqNo, jobTemplateSeqNo, mgr_seq_no, startDateTime, opFlag);
				jobMasterRepo.updateScheduledServiceWork(servWorkSeqNo);
			}
		}
		logger.info("stopped schedule task");
		return;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public CopyOnWriteArrayList<Long> doJobsForTemplate(Long servWorkSeqNo, Long jobTemplateSeqNo, Long mgr_seq_no,
			String startDateTime, BigDecimal opFlag) {
		CopyOnWriteArrayList<Long> jobSeqNos = new CopyOnWriteArrayList<Long>();
		
		CopyOnWriteArrayList<JobDetails> jobMasters2 = jobDetailsRepo.getSelectJobDetailsForService(servWorkSeqNo);
		if (jobMasters2 != null && jobMasters2.size() > 0) 
		{
			jobDetailsRepo.delSelectJobDetailsForService(servWorkSeqNo);
		}

		CopyOnWriteArrayList<JobDetails_DTO> jobDetailsDTOs = this.processJobDetails(jobTemplateSeqNo, startDateTime, opFlag);

		// Create Job Schedule
		for (int i = 0; i < jobDetailsDTOs.size(); i++) 
		{
			jobDetailsDTOs.get(i).setManagerSeqNo(mgr_seq_no);
			jobDetailsDTOs.get(i).setServiceWorkSeqNo(servWorkSeqNo);
			jobSeqNos.add(jobDetailsRepo.save(this.setJobDetails(jobDetailsDTOs.get(i))).getJobSeqNo());
		}

		return jobSeqNos;
	}

	public CopyOnWriteArrayList<JobDetails_DTO> processJobDetails(Long jobTemplateSeqNo, String startDateTime, BigDecimal opFlag) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.parse(startDateTime, formatter);
		Timestamp currDate = Timestamp.valueOf(ldt);
		Timestamp endDate = null;
		Long seq_no = (long) 1;
		Long jobTypeSeqNo, jobTargetSeqNo;
		Integer months = 0, weeks = 0, days = 0, hours = 0, minutes = 0, seconds = 0, months_dur = 0, weeks_dur = 0, days_dur = 0, hours_dur = 0, mins_dur = 0, seconds_dur = 0;
		CopyOnWriteArrayList<JobTemplateDetails> jobsTemplateDetails = null;
		CopyOnWriteArrayList<JobTemplateDetails> jobsTemplateDetailsListFromParent = new CopyOnWriteArrayList<JobTemplateDetails>();
		CopyOnWriteArrayList<JobTypeStructureDetails> jobTypeStructureDetails = null;
		JobTemplateDetails jobTemplateDetailsFromParent = null;
		JobTemplateDetailsPK jobTemplateDetailsPK = null;
		JobDetails jobDetails5 = null;
		CopyOnWriteArrayList<JobDetails> jobDetailsList = null;
		CopyOnWriteArrayList<JobDetails_DTO> jobDetailsDTOsList = null;

		/* Prepare Sequence Array from job_templates_details */
		jobsTemplateDetails = jobTemplateDetailsRepo.getJobsForTemplate(jobTemplateSeqNo);

		if (jobsTemplateDetails != null) 
		{
			jobDetailsList = new CopyOnWriteArrayList<JobDetails>();
			jobsTemplateDetails.sort((o1, o2) -> o1.getId().getSeqNo().compareTo(o2.getId().getSeqNo()));

			// Update Detailed Job Template
			for (int i = 0; i < jobsTemplateDetails.size(); i++) 
			{
				jobTypeStructureDetails = null;
				jobTypeStructureDetails = jobTypeStructureDetailsRepo.getSelectJobTypeStructureDetailsForParent
				(
					jobsTemplateDetails.get(i).getId().getJobTypeSeqNo(),
					jobsTemplateDetails.get(i).getId().getTargetSeqNo()
				);

				if (jobTypeStructureDetails != null && jobTypeStructureDetails.size() > 0) 
				{
					jobsTemplateDetails.get(i).setDurationFlag('Y');
					jobsTemplateDetailsListFromParent = new CopyOnWriteArrayList<JobTemplateDetails>();

					for (int j = 0; j < jobTypeStructureDetails.size(); j++) 
					{
						jobTemplateDetailsFromParent = new JobTemplateDetails();
						jobTemplateDetailsPK = new JobTemplateDetailsPK();
						jobTemplateDetailsPK.setJobTemplateSeqNo(jobsTemplateDetails.get(i).getId().getJobTemplateSeqNo());
						jobTemplateDetailsPK.setJobTypeSeqNo(jobTypeStructureDetails.get(j).getId().getJobTypeSeqNo());
						jobTemplateDetailsPK.setTargetSeqNo(jobTypeStructureDetails.get(j).getTargetSeqNo());
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
				jobTypeSeqNo = jobsTemplateDetails.get(i).getId().getJobTypeSeqNo();
				jobTargetSeqNo = jobsTemplateDetails.get(i).getId().getTargetSeqNo();
				jobDetails5 = new JobDetails();

				if (jobsTemplateDetails.get(i).getDurationFlag() == null
						|| !jobsTemplateDetails.get(i).getDurationFlag().equals('Y')) {
					months_dur = jobTypeMasterRepo.getJobTypeDurMonths(jobTypeSeqNo);
					weeks_dur = jobTypeMasterRepo.getJobTypeDurWeeks(jobTypeSeqNo);
					days_dur = jobTypeMasterRepo.getJobTypeDurDays(jobTypeSeqNo);
					hours_dur = jobTypeMasterRepo.getJobTypeDurHours(jobTypeSeqNo);
					mins_dur = jobTypeMasterRepo.getJobTypeDurMinutes(jobTypeSeqNo);
					seconds_dur = jobTypeMasterRepo.getJobTypeDurSeconds(jobTypeSeqNo);
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
					endDate = DateUtil.addDays(currDate, months_dur, weeks_dur, days_dur, hours_dur,
							mins_dur, seconds_dur);

					if (opFlag.compareTo(BigDecimal.valueOf(1)) == 0) {
						jobDetails5.setPlanStartDate(currDate);
						jobDetails5.setPlanEndDate(endDate);
					} else {
						jobDetails5.setActStartDate(currDate);
						jobDetails5.setActEndDate(endDate);
					}

				}

				jobDetails5.setJobTemplateSeqNo(jobTemplateSeqNo);
				jobDetails5.setTargetSeqNo(jobTargetSeqNo);
				jobDetails5.setJobTypeSeqNo(jobTypeSeqNo);
				jobDetails5.setSeqNo(seq_no);
				jobDetailsList.add(jobDetails5);

				if (jobsTemplateDetails.get(i).getDurationFlag() == null
						|| !jobsTemplateDetails.get(i).getDurationFlag().equals('Y')) {
					currDate = DateUtil.addDays(endDate, months, weeks, days, hours, minutes, seconds);
				}

			}

		}
		
		if(jobDetailsList!=null)
		{
		for (int j = 0; j < jobDetailsList.size(); j++) 
		{
		jobDetailsDTOsList = this.getJobDetails_DTOs(jobDetailsList);
		}	
		}
		
		return jobDetailsDTOsList;
	}


	private CopyOnWriteArrayList<JobDetails_DTO> getJobDetails_DTOs(CopyOnWriteArrayList<JobDetails> jobDetails) {
		JobDetails_DTO jobDTO = null;
		CopyOnWriteArrayList<JobDetails_DTO> jobDTOs = new CopyOnWriteArrayList<JobDetails_DTO>();

		for (int i = 0; i < jobDetails.size(); i++) {
			jobDTO = getJobDetails_DTO(jobDetails.get(i));
			jobDTOs.add(jobDTO);
		}
		return jobDTOs;
	}

	private JobDetails_DTO getJobDetails_DTO(JobDetails jobDetails) {
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

	private JobDetails setJobDetails(JobDetails_DTO cDTO) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime plst = LocalDateTime.parse(cDTO.getPlanStartDate(), formatter);
		LocalDateTime plto = LocalDateTime.parse(cDTO.getPlanEndDate(), formatter);
		LocalDateTime acst = LocalDateTime.parse(cDTO.getActStartDate(), formatter);
		LocalDateTime acto = LocalDateTime.parse(cDTO.getActEndDate(), formatter);
		Timestamp pFrDateTime = Timestamp.valueOf(plst);
		Timestamp pToDateTime = Timestamp.valueOf(plto);
		Timestamp aFrDateTime = Timestamp.valueOf(acst);
		Timestamp aToDateTime = Timestamp.valueOf(acto);
		JobDetails jobDetails = new JobDetails();
		jobDetails.setActEndDate(aToDateTime);
		jobDetails.setActStartDate(aFrDateTime);
		jobDetails.setPlanStartDate(pFrDateTime);
		jobDetails.setPlanEndDate(pToDateTime);
		jobDetails.setJobTypeSeqNo(cDTO.getJobTypeSeqNo());
		jobDetails.setManagerSeqNo(cDTO.getManagerSeqNo());
		jobDetails.setParentJobSeqNo(cDTO.getParentJobSeqNo());
		jobDetails.setServiceWorkSeqNo(cDTO.getServiceWorkSeqNo());
		jobDetails.setRemarks(cDTO.getRemarks());
		jobDetails.setStatus(cDTO.getStatus());
		return jobDetails;
	}
}