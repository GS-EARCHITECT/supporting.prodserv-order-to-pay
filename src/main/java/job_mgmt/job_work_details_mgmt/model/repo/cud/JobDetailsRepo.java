package job_mgmt.job_work_details_mgmt.model.repo.cud;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import job_mgmt.job_work_details_mgmt.model.details.JobDetails;

@Repository("jobDetailsRepo")
public interface JobDetailsRepo extends CrudRepository<JobDetails, Long> 
{
	
	@Query(value = "SELECT * FROM JOB_DETAILS where JOB_SEQ_NO in :jobSeqNos ORDER BY JOB_SEQ_NO",nativeQuery = true) 
	ArrayList<JobDetails> getSelectJobDetails(@Param("jobSeqNos") ArrayList<Long> jobSeqNos);

	@Query(value = "SELECT * FROM JOB_DETAILS where SERVICE_WORK_SEQ_NO = :servWorkSeqNo ORDER BY JOB_SEQ_NO",nativeQuery = true) 
	ArrayList<JobDetails> getSelectJobDetailsForService(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Query(value = "SELECT * FROM JOB_DETAILS where SERVICE_WORK_SEQ_NO in :servWorkSeqNos ORDER BY JOB_SEQ_NO",nativeQuery = true) 
	ArrayList<JobDetails> getSelectJobDetailsForServices(@Param("servWorkSeqNos") ArrayList<Long> servWorkSeqNos);
	
	@Query(value = "SELECT * FROM JOB_DETAILS where ((:frDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)) and (:toDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE))) ORDER BY JOB_SEQ_NO",nativeQuery = true) 
	ArrayList<JobDetails> getSelectJobDetailsBetweenPlanTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM JOB_DETAILS where ((:frDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)) and (:toDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE))) ORDER BY JOB_SEQ_NO",nativeQuery = true) 
	ArrayList<JobDetails> getSelectJobDetailsBetweenActualTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "DELETE FROM JOB_DETAILS where JOB_SEQ_NO in :jobSeqNos",nativeQuery = true) 
	void delSelectJobDetails(@Param("jobSeqNos") ArrayList<Long> jobSeqNos);
	
	@Query(value = "DELETE FROM JOB_DETAILS where SERVICE_WORK_SEQ_NO in :servWorkSeqNos",nativeQuery = true) 
	void delSelectJobDetailsForServices(@Param("servWorkSeqNos") ArrayList<Long> servWorkSeqNos);
	
	@Modifying
	@Query(value = "DELETE FROM JOB_DETAILS where SERVICE_WORK_SEQ_NO = :servWorkSeqNo",nativeQuery = true) 
	void delSelectJobDetailsForService(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Query(value = "DELETE FROM JOB_DETAILS where ((:frDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)) and (:toDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)))",nativeQuery = true)
	void delSelectJobDetailsBetweenPlanPlanTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

	@Query(value = "DELETE FROM JOB_DETAILS where ((:frDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)) and (:toDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)))",nativeQuery = true)
	void delSelectJobDetailsBetweenPlanActualTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);	
}