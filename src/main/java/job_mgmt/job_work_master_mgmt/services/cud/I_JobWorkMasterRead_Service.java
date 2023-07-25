package job_mgmt.job_work_master_mgmt.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import job_mgmt.job_work_master_mgmt.model.dto.JobWorkMaster_DTO;

public interface I_JobWorkMasterRead_Service 
{
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getAllJobWorkMasters() throws InterruptedException, ExecutionException;
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServices(CopyOnWriteArrayList<Long> servWorkSeqNos) throws InterruptedException, ExecutionException;
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServicesForRequests(CopyOnWriteArrayList<Long> reqSeqNos) throws InterruptedException, ExecutionException;
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServicesForServices(CopyOnWriteArrayList<Long> servSeqNos) throws InterruptedException, ExecutionException;
	abstract public CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServicesBetweenTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException;
}