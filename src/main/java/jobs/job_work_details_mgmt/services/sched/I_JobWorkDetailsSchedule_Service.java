package jobs.job_work_details_mgmt.services.sched;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import common.model.dto.JobWorkDetail_DTO;

public interface I_JobWorkDetailsSchedule_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> processJobWorkDetails(Long jobTemplateSeqNo, String startDateTime, Integer opFlag);
}