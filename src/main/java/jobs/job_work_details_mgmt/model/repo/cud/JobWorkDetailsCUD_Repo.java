package jobs.job_work_details_mgmt.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobWorkDetail;

@Repository("jobWorkDetailsCUDRepo")
public interface JobWorkDetailsCUD_Repo extends CrudRepository<JobWorkDetail, Long> 
{
	
	@Query(value = "DELETE FROM JOB_WORK_DETAILS where JOB_WORK_SEQ_NO in :jobWorkSeqNos",nativeQuery = true) 
	void delSelectJobWorkDetails(@Param("jobWorkSeqNos") CopyOnWriteArrayList<Long> jobWorkSeqNos);
	
	@Query(value = "DELETE FROM JOB_WORK_DETAILS where SERVICE_WORK_SEQ_NO in :servWorkSeqNos",nativeQuery = true) 
	void delSelectJobWorkDetailsForServices(@Param("servWorkSeqNos") CopyOnWriteArrayList<Long> servWorkSeqNos);
	
	@Modifying
	@Query(value = "DELETE FROM JOB_WORK_DETAILS where SERVICE_WORK_SEQ_NO = :servWorkSeqNo",nativeQuery = true) 
	void delSelectJobWorkDetailsForService(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Query(value = "DELETE FROM JOB_WORK_DETAILS where ((:frDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)) and (:toDtTm NOT BETWEEN (PLAN_START_DATE and PLAN_END_DATE)))",nativeQuery = true)
	void delSelectJobWorkDetailsBetweenPlanTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

	@Query(value = "DELETE FROM JOB_WORK_DETAILS where ((:frDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)) and (:toDtTm NOT BETWEEN (ACT_START_DATE and ACT_END_DATE)))",nativeQuery = true)
	void delSelectJobWorkDetailsBetweenActualTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);	
}