package jobs.job_mgmt.services.online;

import java.util.concurrent.CompletableFuture;

public interface I_JobWorkDetailsSchedule_Service 
{
public CompletableFuture<Void> processJobsForService(Long servWorkSeqNo);	
}