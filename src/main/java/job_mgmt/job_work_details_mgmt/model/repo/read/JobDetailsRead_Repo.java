package job_mgmt.job_work_details_mgmt.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import job_mgmt.job_work_details_mgmt.model.details.JobDetails;

@Repository("jobDetailsReadRepo")
public interface JobDetailsRead_Repo extends JpaRepository<JobDetails, Long> 
{	
	@Query(value = "SELECT * FROM JOB_DETAILS where job_work_seq_no in :jobSeqNos ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobDetails> getSelectJobDetails(@Param("jobSeqNos") CopyOnWriteArrayList<Long> jobSeqNos);

	@Query(value = "SELECT * FROM JOB_DETAILS where SERVICE_WORK_SEQ_NO = :servWorkSeqNo ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobDetails> getSelectJobDetailsForService(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Query(value = "SELECT * FROM JOB_DETAILS where SERVICE_WORK_SEQ_NO in :servWorkSeqNos ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobDetails> getSelectJobDetailsForServices(@Param("servWorkSeqNos") CopyOnWriteArrayList<Long> servWorkSeqNos);
	
	@Query(value = "SELECT * FROM JOB_DETAILS where ((:frDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)) and (:toDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE))) ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobDetails> getSelectJobDetailsBetweenPlanTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM JOB_DETAILS where ((:frDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)) and (:toDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE))) ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobDetails> getSelectJobDetailsBetweenActualTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
}