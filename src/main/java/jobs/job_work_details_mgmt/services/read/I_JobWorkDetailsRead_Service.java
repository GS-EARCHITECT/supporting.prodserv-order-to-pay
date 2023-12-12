package jobs.job_work_details_mgmt.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

import jobs.job_work_details_mgmt.model.dto.JobWorkDetail_DTO;

public interface I_JobWorkDetailsRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getAllJobWorkDetails() throws InterruptedException, ExecutionException;
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetails(CopyOnWriteArrayList<Long> jobSeqNos) throws InterruptedException, ExecutionException;
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetailsForServices(CopyOnWriteArrayList<Long> servWorkSeqNos) throws InterruptedException, ExecutionException;
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetailsBetweenPlanTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException;
	public CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetailsBetweenActualTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException;
}