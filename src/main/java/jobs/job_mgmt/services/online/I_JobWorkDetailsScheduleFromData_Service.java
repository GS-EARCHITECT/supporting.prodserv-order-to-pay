package jobs.job_mgmt.services.online;

import java.util.concurrent.CompletableFuture;

public interface I_JobWorkDetailsScheduleFromData_Service 
{
public CompletableFuture<Void> processJobsForService(Long newServWorkSeqNo);
}