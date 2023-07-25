package job_mgmt.job_work_details_mgmt.services.read;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import job_mgmt.job_work_details_mgmt.model.dto.JobDetails_DTO;

public interface I_JobDetailsRead_Service 
{
	public CopyOnWriteArrayList<JobDetails_DTO> getAllJobDetails() throws InterruptedException, ExecutionException;
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetails(CopyOnWriteArrayList<Long> jobSeqNos) throws InterruptedException, ExecutionException;
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetailsForService(Long servWorkSeqNo) throws InterruptedException, ExecutionException;
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetailsForServices(CopyOnWriteArrayList<Long> servWorkSeqNos) throws InterruptedException, ExecutionException;
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetailsBetweenPlanTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException;
	public CopyOnWriteArrayList<JobDetails_DTO> getSelectJobDetailsBetweenActualTimes(String frDtTm, String toDtTm) throws InterruptedException, ExecutionException;
}