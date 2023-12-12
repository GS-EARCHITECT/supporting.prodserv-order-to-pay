package jobs.job_work_master_mgmt.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import jobs.job_work_master_mgmt.model.dto.JobWorkMaster_DTO;

public interface I_JobWorkMasterRead_Service 
{
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getAllJobWorks();
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorks(CopyOnWriteArrayList<Long> servWorkSeqNos);
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorksForRequests(CopyOnWriteArrayList<Long> compSeqNos);
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorksForServices(CopyOnWriteArrayList<Long> servSeqNos);
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorksBetweenTimes(String frDtTm, String toDtTm);
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getJobWorksToBeScheduledForRequests(CopyOnWriteArrayList<Long> reqSeqNos);
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getJobWorksToBeScheduled();
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getJobServiceForRefNos(CopyOnWriteArrayList<String> jobrefIds);	
}