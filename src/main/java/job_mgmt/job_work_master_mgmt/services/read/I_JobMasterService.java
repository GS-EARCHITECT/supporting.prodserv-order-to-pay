package job_mgmt.job_work_master_mgmt.services.read;

import java.util.ArrayList;

import job_mgmt.job_work_master_mgmt.model.dto.JobMasterDTO;

public interface I_JobMasterService 
{
	abstract public JobMasterDTO newJobMaster(JobMasterDTO jobMasterDTO);
	abstract public void updJobMaster(JobMasterDTO jobMasterDTO);
	abstract public ArrayList<JobMasterDTO> getAllJobMasters();
	abstract public JobMasterDTO getJobMasterById(Long serviceWorkSeqNo);
	abstract public void delJobMaster(Long serviceWorkNo);
	abstract public void delAllJobMasters();
	abstract public ArrayList<JobMasterDTO> getSelectJobServices(ArrayList<Long> servWorkSeqNos);
	abstract public ArrayList<JobMasterDTO> getJobServicesForRefId(String jobRefId);
	abstract public ArrayList<JobMasterDTO> getSelectJobServicesForCompanies(ArrayList<Long> compSeqNos);
	abstract public ArrayList<JobMasterDTO> getSelectJobServicesForServices(ArrayList<Long> servSeqNos);
	abstract public ArrayList<JobMasterDTO> getSelectJobServicesBetweenTimes(String frDtTm, String toDtTm);
	abstract public void delSelectJobServices(ArrayList<Long> servWorkSeqNos);
	abstract public void delSelectJobServicesForCompanies(ArrayList<Long> compSeqNos);
	abstract public void delSelectJobServicesForServices(ArrayList<Long> servSeqNos);
	abstract public void delSelectJobServicesBetweenTimes(String frDtTm, String toDtTm);
}