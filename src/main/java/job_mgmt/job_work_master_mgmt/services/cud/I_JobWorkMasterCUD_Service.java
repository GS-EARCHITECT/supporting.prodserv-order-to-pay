package job_mgmt.job_work_master_mgmt.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import job_mgmt.job_work_master_mgmt.model.dto.JobWorkMaster_DTO;

public interface I_JobWorkMasterCUD_Service 
{
	public CompletableFuture<JobWorkMaster_DTO> newJobWork(JobWorkMaster_DTO jobWorkDTO);
	public CompletableFuture<Void> updJobWork(JobWorkMaster_DTO jobWorkDTO);
	public CompletableFuture<Void> delSelectJobServices(CopyOnWriteArrayList<Long> servWorkSeqNos);
	public CompletableFuture<Void> delSelectJobServicesForRequests(CopyOnWriteArrayList<Long> reqSeqNos);
	public CompletableFuture<Void> delSelectJobServicesForServices(CopyOnWriteArrayList<Long> servSeqNos);
	public CompletableFuture<Void> delAllJobServices();
	public CompletableFuture<Void> delSelectJobServicesBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<Void> updScheduledServiceWork(Long servWorkSeqNo);

}