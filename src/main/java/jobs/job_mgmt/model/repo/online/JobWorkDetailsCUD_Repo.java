package jobs.job_mgmt.model.repo.online;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobWorkDetail;

@Transactional
@Repository("jobWorkDetailsCUDRepoOnLine")
public interface JobWorkDetailsCUD_Repo extends JpaRepository<JobWorkDetail, Long> 
{
	@Modifying(clearAutomatically = true)
	@Query(value = "update JOB_WORK_DETAILS set PLAN_START_DATE = :plStDtTm, PLAN_END_DATE = :plEndDtTm where JOB_WORK_SEQ_NO = :jobWorkSeqNo",nativeQuery = true)
	void updPlanStartAndEnd(@Param("plStDtTm") Timestamp plStDtTm, @Param("plEndDtTm") Timestamp plEndDtTm, @Param("jobWorkSeqNo") Long jobWorkSeqNo);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update JOB_WORK_DETAILS set ACT_START_DATE = :acStDtTm, ACT_END_DATE = :acEndDtTm where JOB_WORK_SEQ_NO = :jobWorkSeqNo",nativeQuery = true)
	void updActualStartAndEnd(@Param("acStDtTm") Timestamp acStDtTm, @Param("acEndDtTm") Timestamp acEndDtTm, @Param("jobWorkSeqNo") Long jobWorkSeqNo);
	
	@Modifying
	@Query(value = "DELETE FROM JOB_WORK_DETAILS where JOB_WORK_SEQ_NO in :jobWorkSeqNos",nativeQuery = true) 
	void delSelectJobWorkDetails(@Param("jobWorkSeqNos") CopyOnWriteArrayList<Long> jobWorkSeqNos);
	
	@Modifying
	@Query(value = "DELETE FROM JOB_WORK_DETAILS where SERVICE_WORK_SEQ_NO in :servWorkSeqNos",nativeQuery = true) 
	void delSelectJobWorkDetailsForServices(@Param("servWorkSeqNos") CopyOnWriteArrayList<Long> servWorkSeqNos);
	
	@Modifying
	@Query(value = "DELETE FROM JOB_WORK_DETAILS where SERVICE_WORK_SEQ_NO = :servWorkSeqNo",nativeQuery = true) 
	void delSelectJobWorkDetailsForService(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Modifying
	@Query(value = "DELETE FROM JOB_WORK_DETAILS where ((:frDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)) and (:toDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)))",nativeQuery = true)
	void delSelectJobWorkDetailsBetweenPlanTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

	@Modifying
	@Query(value = "DELETE FROM JOB_WORK_DETAILS where ((:frDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)) and (:toDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)))",nativeQuery = true)
	void delSelectJobWorkDetailsBetweenActualTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);	
}