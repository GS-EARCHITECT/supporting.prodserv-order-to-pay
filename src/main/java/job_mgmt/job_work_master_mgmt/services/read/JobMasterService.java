package job_mgmt.job_work_master_mgmt.services.read;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import job_mgmt.job_work_master_mgmt.model.dto.JobMasterDTO;
import job_mgmt.job_work_master_mgmt.model.master.JobMaster;
import job_mgmt.job_work_master_mgmt.model.repo.read.JobMasterRepo;

@Service("jobMasterServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobMasterService implements I_JobMasterService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(JobMasterService.class);

	@Autowired
	private JobMasterRepo jobMasterRepo;

	@Override
	public JobMasterDTO newJobMaster(JobMasterDTO jobDTO) {
		JobMaster JobMaster = jobMasterRepo.save(this.setJobMaster(jobDTO));
		jobDTO = getJobMasterDTO(JobMaster);
		return jobDTO;
	}

	@Override
	public ArrayList<JobMasterDTO> getAllJobMasters() {
		ArrayList<JobMaster> jobList = (ArrayList<JobMaster>) jobMasterRepo.findAll();
		ArrayList<JobMasterDTO> jobDTOs = new ArrayList<JobMasterDTO>();
		jobDTOs = jobList != null ? this.getJobMasterDTOs(jobList) : null;
		return jobDTOs;
	}

	@Override
	public ArrayList<JobMasterDTO> getSelectJobServices(ArrayList<Long> servWorkSeqNos) {
		ArrayList<JobMaster> jobMasters = null;
		ArrayList<JobMasterDTO> jobMasterDTOs = null;

		if (servWorkSeqNos != null) {
			jobMasters = jobMasterRepo.getSelectJobServices(servWorkSeqNos);
			if (jobMasters != null) {
				jobMasterDTOs = this.getJobMasterDTOs(jobMasters);
			}
		}
		return jobMasterDTOs;
	}

	@Override
	public ArrayList<JobMasterDTO> getSelectJobServicesForCompanies(ArrayList<Long> compSeqNos) {
		ArrayList<JobMaster> jobMasters = null;
		ArrayList<JobMasterDTO> jobMasterDTOs = null;

		if (compSeqNos != null) {
			jobMasters = jobMasterRepo.getSelectJobServicesForCompanies(compSeqNos);
			if (jobMasters != null) {
				jobMasterDTOs = this.getJobMasterDTOs(jobMasters);
			}
		}
		return jobMasterDTOs;
	}

	@Override
	 public ArrayList<JobMasterDTO> getJobServicesForRefId(String jobRefId)
	 {
			ArrayList<JobMaster> jobMasters = null;
			ArrayList<JobMasterDTO> jobMasterDTOs = null;

			if (jobRefId != null) {
				jobMasters = jobMasterRepo.getJobServiceForRefNo(jobRefId.trim());
				if (jobMasters != null) {
					jobMasterDTOs = this.getJobMasterDTOs(jobMasters);
				}
			}
			return jobMasterDTOs;
		}
	
	
	@Override
	public ArrayList<JobMasterDTO> getSelectJobServicesForServices(ArrayList<Long> servSeqNos) {
		ArrayList<JobMaster> jobMasters = null;
		ArrayList<JobMasterDTO> jobMasterDTOs = null;

		if (servSeqNos != null) {
			jobMasters = jobMasterRepo.getSelectJobServicesForServices(servSeqNos);
			if (jobMasters != null) {
				jobMasterDTOs = this.getJobMasterDTOs(jobMasters);
			}
		}
		return jobMasterDTOs;
	}

	@Override
	public ArrayList<JobMasterDTO> getSelectJobServicesBetweenTimes(String frDtTm, String toDtTm) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		ArrayList<JobMaster> jobMasters = null;
		ArrayList<JobMasterDTO> jobMasterDTOs = null;

		if (frDtTm != null && toDtTm != null) {
			lfrdttm = LocalDateTime.parse(frDtTm, formatter);
			ltodttm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
			Timestamp toDateTime = Timestamp.valueOf(ltodttm);
			jobMasters = null;
			jobMasters = jobMasterRepo.getSelectJobServicesBetweenTimes(frDateTime, toDateTime);
			if (jobMasters != null) {
				jobMasterDTOs = this.getJobMasterDTOs(jobMasters);
			}
		}
		return jobMasterDTOs;
	}

	@Override
	public JobMasterDTO getJobMasterById(Long serviceWorkSeqNo) {
		Optional<JobMaster> jobMaster = null;
		JobMasterDTO jobMasterDTO = null;

		if (serviceWorkSeqNo != null) {
			jobMaster = jobMasterRepo.findById(serviceWorkSeqNo);
			if (jobMaster.isPresent()) {
				jobMasterDTO = getJobMasterDTO(jobMaster.get());
			}
		}
		return jobMasterDTO;
	}

	@Override
	public void updJobMaster(JobMasterDTO jobMasterDTO) {
		Optional<JobMaster> jobMaster = null;

		if (jobMasterDTO != null) {
			jobMaster = jobMasterRepo.findById(jobMasterDTO.getServiceWorkSeqNo());
			@SuppressWarnings("unused")
			JobMasterDTO jobMasterDTO2 = null;
			JobMaster jobMaster2 = null;

			if (jobMaster.isPresent()) {
				jobMaster2 = this.setJobMaster(jobMasterDTO);
				jobMasterRepo.save(jobMaster2);
			}
		}
	}

	@Override
	public void delJobMaster(Long jobMasterSeqNo) {
		if (jobMasterRepo.existsById(jobMasterSeqNo)) {
			jobMasterRepo.deleteById(jobMasterSeqNo);
		}
	}

	@Override
	public void delAllJobMasters() {
		jobMasterRepo.deleteAll();
	}

	@Override
	public void delSelectJobServices(ArrayList<Long> servWorkSeqNos) {
		jobMasterRepo.delSelectJobServices(servWorkSeqNos);
	}

	@Override
	public void delSelectJobServicesForCompanies(ArrayList<Long> compSeqNos) {
		jobMasterRepo.delSelectJobServicesForCompanies(compSeqNos);
	}

	@Override
	public void delSelectJobServicesForServices(ArrayList<Long> servSeqNos) {
		jobMasterRepo.delSelectJobServicesForServices(servSeqNos);
	}

	@Override
	public void delSelectJobServicesBetweenTimes(String frDtTm, String toDtTm) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime lfrdttm = null;
		LocalDateTime ltodttm = null;
		ArrayList<JobMasterDTO> jobMasterDTOs = null;
		lfrdttm = LocalDateTime.parse(frDtTm, formatter);
		ltodttm = LocalDateTime.parse(toDtTm, formatter);
		Timestamp frDateTime = Timestamp.valueOf(lfrdttm);
		Timestamp toDateTime = Timestamp.valueOf(ltodttm);
		jobMasterRepo.delSelectJobServicesBetweenTimes(frDateTime, toDateTime);
		return;
	}

	private ArrayList<JobMasterDTO> getJobMasterDTOs(ArrayList<JobMaster> jobMasters) {
		JobMasterDTO jobDTO = null;
		ArrayList<JobMasterDTO> jobDTOs = new ArrayList<JobMasterDTO>();

		for (int i = 0; i < jobMasters.size(); i++) {
			jobDTO = getJobMasterDTO(jobMasters.get(i));
			jobDTOs.add(jobDTO);
		}
		return jobDTOs;
	}

	private JobMasterDTO getJobMasterDTO(JobMaster jobMaster) {
		JobMasterDTO jobMasterDTO = new JobMasterDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		jobMasterDTO.setCreatedOn(formatter.format(jobMaster.getCreatedOn().toLocalDateTime()));
		jobMasterDTO.setCompanySeqNo(jobMaster.getCompanySeqNo());
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

	private JobMaster setJobMaster(JobMasterDTO cDTO) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime crrdttm = LocalDateTime.parse(cDTO.getCreatedOn(), formatter);
		Timestamp frDateTime = Timestamp.valueOf(crrdttm);
		JobMaster jobMaster = new JobMaster();
		jobMaster.setCompanySeqNo(cDTO.getCompanySeqNo());
		jobMaster.setCreatedOn(frDateTime);
		jobMaster.setJobTemplateSeqNo(cDTO.getJobTemplateSeqNo());
		jobMaster.setManagerSeqNo(cDTO.getManagerSeqNo());
		jobMaster.setServiceSeqNo(cDTO.getServiceSeqNo());
		jobMaster.setJobRefId(cDTO.getJobRefId());
		jobMaster.setSchFlag(cDTO.getSchFlag());
		jobMaster.setSchType(cDTO.getSchType());
		jobMaster.setOpFlag(cDTO.getOpFlag());
		return jobMaster;
	}
}