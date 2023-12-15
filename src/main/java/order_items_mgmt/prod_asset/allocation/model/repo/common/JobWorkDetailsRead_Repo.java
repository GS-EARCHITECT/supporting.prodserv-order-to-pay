package order_items_mgmt.prod_asset.allocation.model.repo.common;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import common.model.master.JobWorkDetail;

@Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
@Repository("jobWorkDetailsReadRepoForResources")
public interface JobWorkDetailsRead_Repo extends JpaRepository<JobWorkDetail, Long> 
{	
	@Query(value = "SELECT * FROM JOB_work_DETAILS where job_work_seq_no in :jobSeqNos ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetail(@Param("jobSeqNos") CopyOnWriteArrayList<Long> jobSeqNos);

	@Query(value = "SELECT * FROM JOB_work_DETAILS where SERVICE_WORK_SEQ_NO = :servWorkSeqNo ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetailForService(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Query(value = "SELECT * FROM JOB_work_DETAILS where (SERVICE_WORK_SEQ_NO = :servWorkSeqNo and plan_start_date IS NULL and act_start_date IS NULL) ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetailForServiceNotSchduled(@Param("servWorkSeqNo") Long servWorkSeqNo);
		
	@Query(value = "SELECT * FROM JOB_work_DETAILS where SERVICE_WORK_SEQ_NO in :servWorkSeqNos ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetailForServices(@Param("servWorkSeqNos") CopyOnWriteArrayList<Long> servWorkSeqNos);
	
	@Query(value = "SELECT * FROM JOB_work_DETAILS where ((:frDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)) and (:toDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE))) ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetailBetweenPlanTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM JOB_work_DETAILS where ((:frDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)) and (:toDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE))) ORDER BY job_work_seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkDetail> getSelectJobWorkDetailBetweenActualTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT job_work_seq_no FROM JOB_work_DETAILS where (ROWNUM=1 and SERVICE_WORK_SEQ_NO = :servWorkSeqNo and JOB_TEMPLATE_SEQ_NO = :templateSeqNo and job_SEQ_NO = :jobSeqNo)",nativeQuery = true) 
	Long getJobForServiceWork(@Param("servWorkSeqNo") Long servWorkSeqNo, @Param("templateSeqNo") Long templateSeqNo, @Param("jobSeqNo") Long jobSeqNo);

	
}