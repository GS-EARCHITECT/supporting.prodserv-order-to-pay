package jobs.job_mgmt.model.repo.scheduler;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.master.JobTripDetails;
import common.model.master.JobTripDetailsPK;

@Transactional(propagation=Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
@Repository("jobTripRepo")
public interface JobTripRepo extends JpaRepository<JobTripDetails, JobTripDetailsPK> 
{	
	
	@Query(value = "SELECT * FROM JOB_TRIP_DETAILS where JOB_WORK_SEQ_NO = :jobWorkSeqNo ORDER BY JOB_WORK_SEQ_NO asc, PLAN_START_DATE asc",nativeQuery = true) 
	CopyOnWriteArrayList<JobTripDetails> getJobPlanDetails(@Param("jobWorkSeqNo") Long jobWorkSeqNo);

	@Query(value = "SELECT * FROM JOB_TRIP_DETAILS where JOB_WORK_SEQ_NO = :jobWorkSeqNo ORDER BY JOB_WORK_SEQ_NO asc, ACT_START_DATE asc",nativeQuery = true) 
	CopyOnWriteArrayList<JobTripDetails> getJobActualDetails(@Param("jobWorkSeqNo") Long jobWorkSeqNo);
	
	@Modifying
	@Query(value = "delete FROM JOB_TRIP_DETAILS where JOB_WORK_SEQ_NO = :jobWorkSeqNo",nativeQuery = true) 
	void delJobDetails(@Param("jobWorkSeqNo") Long jobWorkSeqNo);
	
	@Query(value = "select * FROM JOB_TRIP_DETAILS where (ROWNUM = 1 and JOB_WORK_SEQ_NO = :jobWorkSeqNo)",nativeQuery = true) 
	JobTripDetails checkJobDetails(@Param("jobWorkSeqNo") Long jobWorkSeqNo);
}