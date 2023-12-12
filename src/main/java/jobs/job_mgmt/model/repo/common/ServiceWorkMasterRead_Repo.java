package jobs.job_mgmt.model.repo.common;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import common.model.master.ServiceWorkMaster;

@Repository("serviceWorkReadRepo")
public interface ServiceWorkMasterRead_Repo extends JpaRepository<ServiceWorkMaster, Long> 
{
@Query(value = "select * FROM SERVICE_WORK_MASTER WHERE (upper(trim(OKFLAG)) ='Y' and upper(trim(DONEFLAG)) <>'Y' and upper(trim(JOBAUTOFLAG)) = 'Y' and upper(trim(JOB_ALLOC_STATUS)) <>'Y') order by service_work_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorksForAutoAllocJobsNotAllocated();

@Query(value = "select * FROM SERVICE_WORK_MASTER WHERE (upper(trim(OKFLAG)) ='Y' and upper(trim(DONEFLAG)) <>'Y' and upper(trim(RESAUTOFLAG)) = 'Y' and upper(trim(RES_ALLOC_STATUS)) <>'Y') order by service_work_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorksForAutoAllocResourcesNotAllocated();

@Query(value = "select * FROM SERVICE_WORK_MASTER WHERE party_seq_no in :pList order by service_work_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorksByParties(@Param("pList") CopyOnWriteArrayList<Long> pList);

@Query(value = "select * FROM SERVICE_WORK_MASTER WHERE party_seq_no in :bList order by service_work_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorksByBookings(@Param("bList") CopyOnWriteArrayList<Long> bList);

@Query(value = "select * FROM SERVICE_WORK_MASTER WHERE request_seq_no in :rList order by service_work_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorksByRequests(@Param("rList") CopyOnWriteArrayList<Long> rList);

@Query(value = "select * FROM SERVICE_WORK_MASTER WHERE service_seq_no in :sList order by service_work_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorksByServices(@Param("sList") CopyOnWriteArrayList<Long> sList);

@Query(value = "SELECT * FROM SERVICE_WORK_MASTER where created_by in :cList ORDER BY service_work_sEQ_NO",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorksByCreatedBy(@Param("cList") CopyOnWriteArrayList<Long> cList);

@Query(value = "SELECT * FROM SERVICE_WORK_MASTER where (ON_DATE >= :frDtTm and ON_DATE <= :toDtTm) ORDER BY service_work_sEQ_NO",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorksBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

@Query(value = "select JOB_TEMPLATE_SEQ_NO FROM SERVICE_WORK_MASTER WHERE service_work_seq_no = :id",nativeQuery = true)
Long getJobTemplateSeqNo(@Param("id") Long id);

@Query(value = "SELECT * FROM SERVICE_WORK_MASTER where upper(trim(billedflag)) <> 'Y' ORDER BY service_work_sEQ_NO",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorksBillPending();

@Query(value = "SELECT * FROM JOB_MASTER where SERVICE_WORK_SEQ_NO in :servWorkSeqNos ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<ServiceWorkMaster> getSelectJobServices(@Param("servWorkSeqNos") CopyOnWriteArrayList<Long> servWorkSeqNos);

@Query(value = "SELECT * FROM JOB_MASTER where REQUEST_SEQ_NO in :reqSeqNos ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<ServiceWorkMaster> getSelectJobServicesForRequests(@Param("reqSeqNos") CopyOnWriteArrayList<Long> reqSeqNos);

@Query(value = "SELECT * FROM JOB_MASTER where (REQUEST_SEQ_NO in :reqSeqNos and upper(trim(SCHEDULE_TYPE))= 'Y' and upper(trim(SCHEDULED_FLAG))<> 'Y') ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<ServiceWorkMaster> getJobsToBeScheduledForRequests(@Param("reqSeqNos") CopyOnWriteArrayList<Long> reqSeqNos);
	
@Query(value = "SELECT * FROM SERVICE_WORK_MASTER where (upper(trim(SCHEDULE_TYPE))= 'Y' and upper(trim(SCHEDULED_FLAG))<> 'Y') ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<ServiceWorkMaster> getJobsToBeScheduled();

@Query(value = "SELECT * FROM JOB_MASTER where SERVICE_SEQ_NO in :servSeqNos ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<ServiceWorkMaster> getSelectJobServicesForServices(@Param("servSeqNos") CopyOnWriteArrayList<Long> servSeqNos);

@Query(value = "SELECT * FROM JOB_MASTER where upper(trim(job_ref_id)) in :jobrefIds ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<ServiceWorkMaster> getJobServiceForRefNos(@Param("jobrefIds") CopyOnWriteArrayList<String> jobrefIds);

@Query(value = "SELECT * FROM JOB_MASTER where (CREATED_ON >= :frDtTm and CREATED_ON <= :toDtTm) ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<ServiceWorkMaster> getSelectJobServicesBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);


}