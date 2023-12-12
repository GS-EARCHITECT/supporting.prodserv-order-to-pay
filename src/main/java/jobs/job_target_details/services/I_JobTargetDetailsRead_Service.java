package jobs.job_target_details.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

import jobs.job_target_details.model.dto.JobTargetDetail_DTO;
import jobs.job_target_details.model.master.JobTargetDetailPK;

public interface I_JobTargetDetailsRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<JobTargetDetail_DTO>> getAllJobTargetDetails() throws InterruptedException, ExecutionException;
	public CompletableFuture<CopyOnWriteArrayList<JobTargetDetail_DTO>> getAllJobTargetDetailsByIds(CopyOnWriteArrayList<JobTargetDetailPK> jobTargetDetailsPks) throws InterruptedException, ExecutionException;
	public CompletableFuture<CopyOnWriteArrayList<JobTargetDetail_DTO>> getSelectJobTargetDetails(CopyOnWriteArrayList<Long> jobTargetDetailsSeqNos) throws InterruptedException, ExecutionException;
	public CompletableFuture<Float> getJobDurDays(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException;
	public CompletableFuture<Float> getJobDurHours(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException;
	public CompletableFuture<Float> getJobDurSeconds(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException;
	public CompletableFuture<Float> getJobDurMinutes(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException;
	public CompletableFuture<Float> getJobDurWeeks(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException;
	public CompletableFuture<Float> getJobDurMonths(Long jobSeqNo, Long trgSeqNo) throws InterruptedException, ExecutionException;
}