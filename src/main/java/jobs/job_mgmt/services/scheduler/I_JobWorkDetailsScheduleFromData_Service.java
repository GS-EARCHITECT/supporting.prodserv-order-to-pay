package jobs.job_mgmt.services.scheduler;

import java.util.concurrent.CompletableFuture;

public interface I_JobWorkDetailsScheduleFromData_Service 
{
	public CompletableFuture<Void> processJobWorkDetailsFromData(Long newServWorkSeqNo, Long servWorkSeqNo, String startDateTime, Integer opFlag,Long mgrSeqNo);
}