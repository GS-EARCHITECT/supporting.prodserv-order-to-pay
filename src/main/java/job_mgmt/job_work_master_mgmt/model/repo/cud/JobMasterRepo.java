package job_mgmt.job_work_master_mgmt.model.repo.cud;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import job_mgmt.job_work_master_mgmt.model.master.JobMaster;

@Repository("jobMasterRepo")
public interface JobMasterRepo extends CrudRepository<JobMaster, Long> 
{
	@Query(value = "SELECT * FROM JOB_MASTER where SERVICE_WORK_SEQ_NO in :servWorkSeqNos ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	ArrayList<JobMaster> getSelectJobServices(@Param("servWorkSeqNos") ArrayList<Long> servWorkSeqNos);

	@Query(value = "SELECT * FROM JOB_MASTER where COMPANY_SEQ_NO in :compSeqNos ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	ArrayList<JobMaster> getSelectJobServicesForCompanies(@Param("compSeqNos") ArrayList<Long> compSeqNos);
	
	@Query(value = "SELECT * FROM JOB_MASTER where (COMPANY_SEQ_NO in :compSeqNos and upper(trim(SCHEDULE_TYPE))= 'Y' and upper(trim(SCHEDULED_FLAG))<> 'Y') ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	ArrayList<JobMaster> getJobsToBeScheduledForCompanies(@Param("compSeqNos") ArrayList<Long> compSeqNos);
	
	@Query(value = "SELECT * FROM JOB_MASTER where (upper(trim(SCHEDULE_TYPE))= 'Y' and upper(trim(SCHEDULED_FLAG))<> 'Y') ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	ArrayList<JobMaster> getJobsToBeScheduled();
	
	@Query(value = "SELECT * FROM JOB_MASTER where SERVICE_SEQ_NO in :servSeqNos ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	ArrayList<JobMaster> getSelectJobServicesForServices(@Param("servSeqNos") ArrayList<Long> servSeqNos);
	
	@Query(value = "SELECT * FROM JOB_MASTER where trim(job_ref_id) = :jobrefId ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	ArrayList<JobMaster> getJobServiceForRefNo(@Param("jobrefId") String jobrefId);
	
	@Query(value = "SELECT * FROM JOB_MASTER where (CREATED_ON >= :frDtTm and CREATED_ON <= :toDtTm) ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	ArrayList<JobMaster> getSelectJobServicesBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "DELETE FROM JOB_MASTER where SERVICE_WORK_SEQ_NO in :servWorkSeqNos",nativeQuery = true) 
	void delSelectJobServices(@Param("servWorkSeqNos") ArrayList<Long> servWorkSeqNos);
	
	@Query(value = "DELETE FROM JOB_MASTER where COMPANY_SEQ_NO in :compSeqNos",nativeQuery = true) 
	void delSelectJobServicesForCompanies(@Param("compSeqNos") ArrayList<Long> compSeqNos);
	
	@Query(value = "DELETE FROM JOB_MASTER where SERVICE_SEQ_NO in :servSeqNos",nativeQuery = true) 
	void delSelectJobServicesForServices(@Param("servSeqNos") ArrayList<Long> servSeqNos);
	
	@Modifying
	@Query(value = "update JOB_MASTER set SCHEDULED_FLAG = 'Y' where SERVICE_WORK_SEQ_NO = :servWorkSeqNo",nativeQuery = true) 
	void updateScheduledServiceWork(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Query(value = "DELETE FROM JOB_MASTER where (CREATED_ON >= :frDtTm and CREATED_ON <= :toDtTm) ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	void delSelectJobServicesBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

}