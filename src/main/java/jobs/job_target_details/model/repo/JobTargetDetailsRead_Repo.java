package jobs.job_target_details.model.repo;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import common.model.master.JobTargetDetail;
import common.model.master.JobTargetDetailPK;

@Repository("jobTargetDetailsReadRepo")
public interface JobTargetDetailsRead_Repo extends JpaRepository<JobTargetDetail, JobTargetDetailPK> 
{	
	@Query(value = "SELECT * from  JOB_TARGET_DETAILS where JOB_SEQ_NO in :jobTargetDetailsSeqNos ORDER BY JOB_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobTargetDetail> getSelectJobTargetDetails(@Param("jobTargetDetailsSeqNos") CopyOnWriteArrayList<Long> jobTargetDetailsSeqNos);
	
	@Query(value = "SELECT coalesce(DUR_DAYS,0) from  JOB_TARGET_DETAILS where (JOB_SEQ_NO = :jobSeqNo and TARGET_SEQ_NO= :trgSeqNo)",nativeQuery = true) 
	Float getJobDurDays(@Param("jobSeqNo") Long jobSeqNo, @Param("trgSeqNo") Long trgSeqNo);
	
	@Query(value = "SELECT coalesce(DUR_HOURS,0) from  JOB_TARGET_DETAILS where (JOB_SEQ_NO = :jobSeqNo and TARGET_SEQ_NO= :trgSeqNo)",nativeQuery = true) 
	Float getJobDurHours(@Param("jobSeqNo") Long jobSeqNo, @Param("trgSeqNo") Long trgSeqNo);
	
	@Query(value = "SELECT coalesce(DUR_SECONDS,0) from  JOB_TARGET_DETAILS where (JOB_SEQ_NO = :jobSeqNo and TARGET_SEQ_NO= :trgSeqNo) ",nativeQuery = true) 
	Float getJobDurSeconds(@Param("jobSeqNo") Long jobSeqNo, @Param("trgSeqNo") Long trgSeqNo);
	
	@Query(value = "SELECT coalesce(DUR_MINUTES,0) from  JOB_TARGET_DETAILS where (JOB_SEQ_NO = :jobSeqNo and TARGET_SEQ_NO= :trgSeqNo)",nativeQuery = true) 
	Float getJobDurMinutes(@Param("jobSeqNo") Long jobSeqNo, @Param("trgSeqNo") Long trgSeqNo);
	
	@Query(value = "SELECT coalesce(DUR_WEEKS,0) from JOB_TARGET_DETAILS where (JOB_SEQ_NO = :jobSeqNo and TARGET_SEQ_NO= :trgSeqNo) ",nativeQuery = true) 
	Float getJobDurWeeks(@Param("jobSeqNo") Long jobSeqNo, @Param("trgSeqNo") Long trgSeqNo);
			
	@Query(value = "SELECT coalesce(DUR_MONTHS,0) from  JOB_TARGET_DETAILS where (JOB_SEQ_NO = :jobSeqNo and TARGET_SEQ_NO= :trgSeqNo)",nativeQuery = true) 
	Float getJobDurMonths(@Param("jobSeqNo") Long jobSeqNo, @Param("trgSeqNo") Long trgSeqNo);	
}