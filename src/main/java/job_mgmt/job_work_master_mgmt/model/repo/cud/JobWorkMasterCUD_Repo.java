package job_mgmt.job_work_master_mgmt.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import job_mgmt.job_work_master_mgmt.model.master.JobWorkMaster;

@Repository("jobWorkMasterCUDRepo")
public interface JobWorkMasterCUD_Repo extends CrudRepository<JobWorkMaster, Long> 
{
	@Query(value = "DELETE FROM JOB_WORK_MASTER where SERVICE_WORK_SEQ_NO in :servWorkSeqNos",nativeQuery = true) 
	void delSelectJobServices(@Param("servWorkSeqNos") CopyOnWriteArrayList<Long> servWorkSeqNos);
	
	@Query(value = "DELETE FROM JOB_WORK_MASTER where REQUEST_SEQ_NO in :reqSeqNos",nativeQuery = true) 
	void delSelectJobServicesForRequests(@Param("reqSeqNos") CopyOnWriteArrayList<Long> reqSeqNos);
	
	@Query(value = "DELETE FROM JOB_WORK_MASTER where SERVICE_SEQ_NO in :servSeqNos",nativeQuery = true) 
	void delSelectJobServicesForServices(@Param("servSeqNos") CopyOnWriteArrayList<Long> servSeqNos);
	
	@Query(value = "DELETE FROM JOB_WORK_MASTER where (CREATED_ON >= :frDtTm and CREATED_ON <= :toDtTm) ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
	void delSelectJobServicesBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

	@Modifying
	@Query(value = "update JOB_WORK_MASTER set SCHEDULED_FLAG = 'Y' where SERVICE_WORK_SEQ_NO = :servWorkSeqNo",nativeQuery = true) 
	void updateScheduledServiceWork(@Param("servWorkSeqNo") Long servWorkSeqNo);

}