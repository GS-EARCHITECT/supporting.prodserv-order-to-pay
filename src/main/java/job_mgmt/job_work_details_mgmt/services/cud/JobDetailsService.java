package job_mgmt.job_work_details_mgmt.services.cud;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
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
import job_mgmt.job_work_details_mgmt.model.dto.JobDetails_DTO;
import job_mgmt.job_work_details_mgmt.model.repo.cud.JobDetailsRepo;
import job_mgmt.job_work_master_mgmt.model.repo.read.JobWorkMasterRepo;

@Service("jobDetailsReadServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobDetailsReadService implements I_JobDetailsRead_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(JobDetailsService.class);

	@Autowired
	private JobDetailsRepo jobDetailsRepo;

	@Override
	public JobDetails_DTO newJobDetail(JobDetails_DTO jobDTO) 
	{
		JobDetails JobDetails = jobDetailsRepo.save(this.setJobDetails(jobDTO));
		jobDTO = getJobDetails_DTO(JobDetails);
		return jobDTO;
	}

	@Override
	public ArrayList<JobDetails_DTO> getAllJobDetails() {
		ArrayList<JobDetails> jobList = (ArrayList<JobDetails>) jobDetailsRepo.findAll();
		ArrayList<JobDetails_DTO> jobDTOs = new ArrayList<JobDetails_DTO>();
		jobDTOs = jobList != null ? this.getJobDetails_DTOs(jobList) : null;
		return jobDTOs;
	}

	@Override
	public ArrayList<JobDetails_DTO> getSelectJobDetails(ArrayList<Long> jobSeqNos) {
		ArrayList<JobDetails> jobDetails = null;
		ArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (jobSeqNos != null) {
			jobDetails = jobDetailsRepo.getSelectJobDetails(jobSeqNos);
			if (jobDetails != null) {
				jobDetailsDTOs = this.getJobDetails_DTOs(jobDetails);
			}
		}
		return jobDetailsDTOs;
	}

	@Override
	public ArrayList<JobDetails_DTO> getSelectJobDetailsForService(Long servWorkSeqNo) {
		ArrayList<JobDetails> jobDetails = null;
		ArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (servWorkSeqNo != null) {
			jobDetails = jobDetailsRepo.getSelectJobDetailsForService(servWorkSeqNo);
			if (jobDetails != null) {
				jobDetailsDTOs = this.getJobDetails_DTOs(jobDetails);
			}
		}
		return jobDetailsDTOs;
	}

	@Override
	public ArrayList<JobDetails_DTO> getSelectJobDetailsForServices(ArrayList<Long> servWorkSeqNos) {
		ArrayList<JobDetails> jobDetails = null;
		ArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (servWorkSeqNos != null) {
			jobDetails = jobDetailsRepo.getSelectJobDetailsForServices(servWorkSeqNos);
			if (jobDetails != null) {
				jobDetailsDTOs = this.getJobDetails_DTOs(jobDetails);
			}
		}
		return jobDetailsDTOs;
	}

	@Override
	public ArrayList<JobDetails_DTO> getSelectJobDetailsBetweenPlanTimes(String frDtTm, String toDtTm) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		ArrayList<JobDetails> jobDetails = null;
		ArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (frDtTm != null && toDtTm != null) {
			lfrdttm = LocalDateTime.parse(frDtTm, formatter);
			ltodttm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
			Timestamp toDateTime = Timestamp.valueOf(ltodttm);
			jobDetails = null;
			jobDetails = jobDetailsRepo.getSelectJobDetailsBetweenPlanTimes(frDateTime, toDateTime);
			if (jobDetails != null) {
				jobDetailsDTOs = this.getJobDetails_DTOs(jobDetails);
			}
		}
		return jobDetailsDTOs;
	}

	@Override
	public ArrayList<JobDetails_DTO> getSelectJobDetailsBetweenActualTimes(String frDtTm, String toDtTm) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		ArrayList<JobDetails> jobDetails = null;
		ArrayList<JobDetails_DTO> jobDetailsDTOs = null;

		if (frDtTm != null && toDtTm != null) {
			lfrdttm = LocalDateTime.parse(frDtTm, formatter);
			ltodttm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
			Timestamp toDateTime = Timestamp.valueOf(ltodttm);
			jobDetails = null;
			jobDetails = jobDetailsRepo.getSelectJobDetailsBetweenActualTimes(frDateTime, toDateTime);
			if (jobDetails != null) {
				jobDetailsDTOs = this.getJobDetails_DTOs(jobDetails);
			}
		}
		return jobDetailsDTOs;
	}

	@Override
	public JobDetails_DTO getJobDetailsById(Long serviceWorkSeqNo) {
		Optional<JobDetails> jobDetails = null;
		JobDetails_DTO jobDetailsDTO = null;

		if (serviceWorkSeqNo != null) {
			jobDetails = jobDetailsRepo.findById(serviceWorkSeqNo);
			if (jobDetails.isPresent()) {
				jobDetailsDTO = getJobDetails_DTO(jobDetails.get());
			}
		}
		return jobDetailsDTO;
	}

	@Override
	public void updJobDetails(JobDetails_DTO jobDetailsDTO) {
		Optional<JobDetails> jobDetails = null;

		if (jobDetailsDTO != null) {
			jobDetails = jobDetailsRepo.findById(jobDetailsDTO.getServiceWorkSeqNo());
			@SuppressWarnings("unused")
			JobDetails_DTO jobDetailsDTO2 = null;
			JobDetails jobDetails2 = null;

			if (jobDetails.isPresent()) {
				jobDetails2 = this.setJobDetails(jobDetailsDTO);
				jobDetailsRepo.save(jobDetails2);
			}
		}
	}

	@Override
	public void delJobDetails(Long jobDetailsSeqNo) {
		if (jobDetailsRepo.existsById(jobDetailsSeqNo)) {
			jobDetailsRepo.deleteById(jobDetailsSeqNo);
		}
	}

	@Override
	public void delAllJobDetails() {
		jobDetailsRepo.deleteAll();
	}

	@Override
	public void delSelectJobDetails(ArrayList<Long> jobSeqNos) {
		jobDetailsRepo.delSelectJobDetails(jobSeqNos);
	}

	@Override
	public void delSelectJobDetailsForServices(ArrayList<Long> servWorkSeqNos) {
		jobDetailsRepo.delSelectJobDetailsForServices(servWorkSeqNos);
	}

	@Override
	public void delSelectJobDetailsBetweenPlanTimes(String frDtTm, String toDtTm) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		lfrdttm = LocalDateTime.parse(frDtTm, formatter);
		ltodttm = LocalDateTime.parse(toDtTm, formatter);
		Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
		Timestamp toDateTime = Timestamp.valueOf(ltodttm);
		jobDetailsRepo.delSelectJobDetailsBetweenPlanPlanTimes(frDateTime, toDateTime);
		return;
	}

	@Override
	public void delSelectJobDetailsBetweenActualTimes(String frDtTm, String toDtTm) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		lfrdttm = LocalDateTime.parse(frDtTm, formatter);
		ltodttm = LocalDateTime.parse(toDtTm, formatter);
		Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
		Timestamp toDateTime = Timestamp.valueOf(ltodttm);
		jobDetailsRepo.delSelectJobDetailsBetweenPlanActualTimes(frDateTime, toDateTime);
		return;
	}

	private ArrayList<JobDetails_DTO> getJobDetails_DTOs(ArrayList<JobDetails> jobDetails) {
		JobDetails_DTO jobDTO = null;
		ArrayList<JobDetails_DTO> jobDTOs = new ArrayList<JobDetails_DTO>();

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