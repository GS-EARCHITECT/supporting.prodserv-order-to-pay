package job_mgmt.job_work_details_mgmt.services.sched;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import job_mgmt.job_work_details_mgmt.model.dto.JobWorkDetail_DTO;

public interface I_JobWorkDetailsSchedule_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> processJobWorkDetail(Long jobTemplateSeqNo, String startDateTime, Integer opFlag);
}