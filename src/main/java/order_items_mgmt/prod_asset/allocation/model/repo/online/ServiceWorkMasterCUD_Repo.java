package order_items_mgmt.prod_asset.allocation.model.repo.online;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import common.model.master.ServiceWorkMaster;

@Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
@Repository("serviceWorkCUDRepoOnLineForResources")
public interface ServiceWorkMasterCUD_Repo extends JpaRepository<ServiceWorkMaster, Long> 
{	
@Modifying(clearAutomatically = true)	
@Query(value = "update SERVICE_WORK_MASTER set RES_ALLOC_STATUS = upper(trim(:st)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updResourceAllocStatus(@Param("id") Long id, @Param("st") Character st);

@Modifying(clearAutomatically = true)
@Query(value = "update SERVICE_WORK_MASTER set RESAUTOFLAG = upper(trim(:fl)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updResourceAutoFlag(@Param("id") Long id, @Param("fl") Character fl);

@Modifying(clearAutomatically = true)
@Query(value = "update SERVICE_WORK_MASTER set JOB_ALLOC_STATUS = upper(trim(:st)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updJobAllocStatus(@Param("id") Long id, @Param("st") Character st);

@Modifying(clearAutomatically = true)
@Query(value = "update SERVICE_WORK_MASTER set JOBAUTOFLAG = upper(trim(:fl)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updJobAutoFlag(@Param("id") Long id, @Param("fl") Character fl);

@Modifying(clearAutomatically = true)
@Query(value = "update SERVICE_WORK_MASTER set billedflag = upper(trim(:st)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updSelectWorkBillStatus(@Param("id") Long id, @Param("st") Character st);

@Modifying(clearAutomatically = true)
@Query(value = "update SERVICE_WORK_MASTER set OKFLAG = upper(trim(:fl)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updOkFlag(@Param("id") Long id, @Param("fl") Character fl);

@Modifying(clearAutomatically = true)
@Query(value = "update SERVICE_WORK_MASTER set DONEFLAG = upper(trim(:fl)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updDoneFlag(@Param("id") Long id, @Param("fl") Character fl);

@Modifying
@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE service_work_seq_no in :sList",nativeQuery = true)
void delSelectWorks(@Param("sList") CopyOnWriteArrayList<Long> sList);
	
@Modifying
@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE party_seq_no in :pList",nativeQuery = true)
void delSelectWorksByParties(@Param("pList") CopyOnWriteArrayList<Long> pList);

@Modifying
@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE party_seq_no in :bList",nativeQuery = true)
void delSelectWorksByBookings(@Param("bList") CopyOnWriteArrayList<Long> bList);

@Modifying
@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE request_seq_no in :rList",nativeQuery = true)
void delSelectWorksByRequests(@Param("rList") CopyOnWriteArrayList<Long> rList);

@Modifying
@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE service_seq_no in :sList",nativeQuery = true)
void delSelectWorksByServices(@Param("sList") CopyOnWriteArrayList<Long> sList);

@Modifying
@Query(value = "SELECT * FROM SERVICE_WORK_MASTER where created_by in :cList",nativeQuery = true)
void delSelectWorksByCreatedBy(@Param("cList") CopyOnWriteArrayList<Long> cList);

@Modifying
@Query(value = "SELECT * FROM SERVICE_WORK_MASTER where (ON_DATE >= :frDtTm and ON_DATE <= :toDtTm)",nativeQuery = true)
void delSelectWorksBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

@Modifying
@Query(value = "delete FROM SERVICE_WORK_MASTER where upper(trim(billedflag)) <> 'Y'",nativeQuery = true)
void delSelectWorksBillPending();

@Modifying
@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE (upper(trim(OKFLAG)) ='Y' and upper(trim(DONEFLAG)) <>'Y' and upper(trim(JOBAUTOFLAG)) = 'Y' and upper(trim(JOB_ALLOC_STATUS)) <>'Y') order by service_work_seq_no",nativeQuery = true)
void delSelectWorksForAutoAllocJobsNotAllocated();

@Modifying
@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE (upper(trim(OKFLAG)) ='Y' and upper(trim(DONEFLAG)) <>'Y' and upper(trim(RESAUTOFLAG)) = 'Y' and upper(trim(RES_ALLOC_STATUS)) <>'Y') order by service_work_seq_no",nativeQuery = true)
void delSelectWorksForAutoAllocResourcesNotAllocated();

@Modifying
@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE upper(trim(okflag)) = 'Y'",nativeQuery = true)
void delWorksDone();

@Modifying
@Query(value = "DELETE FROM JOB_WORK_MASTER where SERVICE_WORK_SEQ_NO in :servWorkSeqNos",nativeQuery = true) 
void delSelectJobServices(@Param("servWorkSeqNos") CopyOnWriteArrayList<Long> servWorkSeqNos);

@Modifying
@Query(value = "DELETE FROM JOB_WORK_MASTER where REQUEST_SEQ_NO in :reqSeqNos",nativeQuery = true) 
void delSelectJobServicesForRequests(@Param("reqSeqNos") CopyOnWriteArrayList<Long> reqSeqNos);

@Modifying
@Query(value = "DELETE FROM JOB_WORK_MASTER where SERVICE_SEQ_NO in :servSeqNos",nativeQuery = true) 
void delSelectJobServicesForServices(@Param("servSeqNos") CopyOnWriteArrayList<Long> servSeqNos);

@Modifying
@Query(value = "DELETE FROM JOB_WORK_MASTER where (CREATED_ON >= :frDtTm and CREATED_ON <= :toDtTm) ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
void delSelectJobServicesBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

@Modifying(clearAutomatically = true)
@Query(value = "update JOB_WORK_MASTER set SCHEDULED_FLAG = 'Y' where SERVICE_WORK_SEQ_NO = :servWorkSeqNo",nativeQuery = true) 
void updateScheduledServiceWork(@Param("servWorkSeqNo") Long servWorkSeqNo);

}