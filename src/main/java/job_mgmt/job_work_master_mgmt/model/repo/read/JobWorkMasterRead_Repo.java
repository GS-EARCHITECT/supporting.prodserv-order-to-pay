package job_mgmt.job_work_master_mgmt.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import job_mgmt.job_work_master_mgmt.model.master.JobWorkMaster;

@Repository("jobWorkMasterReadRepo")
public interface JobWorkMasterRead_Repo extends JpaRepository<JobWorkMaster, Long> 
{
	@Query(value = "SELECT * FROM JOB_MASTER where SERVICE_WORK_SEQ_NO in :servWorkSeqNos ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkMaster> getSelectJobServices(@Param("servWorkSeqNos") CopyOnWriteArrayList<Long> servWorkSeqNos);

	@Query(value = "SELECT * FROM JOB_MASTER where REQUEST_SEQ_NO in :reqSeqNos ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkMaster> getSelectJobServicesForRequests(@Param("reqSeqNos") CopyOnWriteArrayList<Long> reqSeqNos);
	
	@Query(value = "SELECT * FROM JOB_MASTER where (REQUEST_SEQ_NO in :reqSeqNos and upper(trim(SCHEDULE_TYPE))= 'Y' and upper(trim(SCHEDULED_FLAG))<> 'Y') ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkMaster> getJobsToBeScheduledForRequests(@Param("reqSeqNos") CopyOnWriteArrayList<Long> reqSeqNos);
	
	@Query(value = "SELECT * FROM JOB_MASTER where (upper(trim(SCHEDULE_TYPE))= 'Y' and upper(trim(SCHEDULED_FLAG))<> 'Y') ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkMaster> getJobsToBeScheduled();
	
	@Query(value = "SELECT * FROM JOB_MASTER where SERVICE_SEQ_NO in :servSeqNos ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkMaster> getSelectJobServicesForServices(@Param("servSeqNos") CopyOnWriteArrayList<Long> servSeqNos);
	
	@Query(value = "SELECT * FROM JOB_MASTER where trim(job_ref_id) = :jobrefId ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkMaster> getJobServiceForRefNo(@Param("jobrefId") String jobrefId);
	
	@Query(value = "SELECT * FROM JOB_MASTER where (CREATED_ON >= :frDtTm and CREATED_ON <= :toDtTm) ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobWorkMaster> getSelectJobServicesBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
}