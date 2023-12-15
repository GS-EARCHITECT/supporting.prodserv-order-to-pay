package jobs.job_work_details_mgmt.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import common.model.dto.JobWorkDetail_DTO;

public interface I_JobWorkDetailsCUD_Service 
{
public CompletableFuture<JobWorkDetail_DTO> newJobWorkDetail(JobWorkDetail_DTO jobWorkDTO);
public CompletableFuture<Void> updJobWorkDetail(JobWorkDetail_DTO jobWorkDetailDTO);
public CompletableFuture<Void> delAllJobWorkDetails();
public CompletableFuture<Void> delSelectJobWorkDetails(CopyOnWriteArrayList<Long> jobWorkSeqNos);
public CompletableFuture<Void> delSelectJobWorkDetailsForServices(CopyOnWriteArrayList<Long> servWorkSeqNos);
public CompletableFuture<Void> delSelectJobWorkDetailsBetweenPlanTimes(String frDtTm, String toDtTm);
public CompletableFuture<Void> delSelectJobWorkDetailsBetweenActualTimes(String frDtTm, String toDtTm);
}