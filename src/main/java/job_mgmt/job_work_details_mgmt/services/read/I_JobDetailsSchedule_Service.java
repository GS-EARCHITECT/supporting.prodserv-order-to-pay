package job_mgmt.job_work_details_mgmt.services.read;

import java.util.concurrent.CopyOnWriteArrayList;
import job_mgmt.job_work_details_mgmt.model.dto.JobDetails_DTO;

public interface I_JobDetailsSchedule_Service 
{
	public CopyOnWriteArrayList<JobDetails_DTO> processJobDetails(Long jobTemplateSeqNo, String startDateTime, Integer opFlag);
}