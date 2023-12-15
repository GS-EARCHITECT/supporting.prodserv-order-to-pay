package jobs.job_work_details_mgmt.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobWorkDetail;

@Repository("jobWorkDetailsReadRepo")
public interface JobWorkDetailsRead_Repo extends JpaRepository<JobWorkDetail, Long> 
{	
	@Query(value = "SELECT * FROM JOB_DETAILS where job_work_seq_no in :jobSeqNos ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetail(@Param("jobSeqNos") CopyOnWriteArrayList<Long> jobSeqNos);

	@Query(value = "SELECT * FROM JOB_DETAILS where SERVICE_WORK_SEQ_NO = :servWorkSeqNo ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetailForService(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Query(value = "SELECT * FROM JOB_DETAILS where SERVICE_WORK_SEQ_NO in :servWorkSeqNos ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetailForServices(@Param("servWorkSeqNos") CopyOnWriteArrayList<Long> servWorkSeqNos);
	
	@Query(value = "SELECT * FROM JOB_DETAILS where ((:frDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)) and (:toDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE))) ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetailBetweenPlanTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM JOB_DETAILS where ((:frDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)) and (:toDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE))) ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetailBetweenActualTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
}