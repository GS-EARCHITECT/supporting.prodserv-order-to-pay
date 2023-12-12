package service_mgmt.master.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service_mgmt.master.model.master.ServiceWorkMaster;

@Repository("serviceWorkCUDRepo")
public interface ServiceWorkMasterCUD_Repo extends JpaRepository<ServiceWorkMaster, Long> 
{	
@Query(value = "update SERVICE_WORK_MASTER set RES_ALLOC_STATUS = upper(trim(:st)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updResourceAllocStatus(@Param("id") Long id, @Param("st") Character st);

@Query(value = "update SERVICE_WORK_MASTER set RESAUTOFLAG = upper(trim(:fl)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updResourceAutoFlag(@Param("id") Long id, @Param("st") Character fl);

@Query(value = "update SERVICE_WORK_MASTER set JOB_ALLOC_STATUS = upper(trim(:st)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updJobAllocStatus(@Param("id") Long id, @Param("st") Character st);

@Query(value = "update SERVICE_WORK_MASTER set JOBAUTOFLAG = upper(trim(:fl)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updJobAutoFlag(@Param("id") Long id, @Param("st") Character fl);

@Query(value = "update SERVICE_WORK_MASTER set billedflag = upper(trim(:st)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updSelectWorkBillStatus(@Param("id") Long id, @Param("st") Character st);

@Query(value = "update SERVICE_WORK_MASTER set OKFLAG = upper(trim(:fl)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updOkFlag(@Param("id") Long id, @Param("st") Character fl);

@Query(value = "update SERVICE_WORK_MASTER set DONEFLAG = upper(trim(:fl)) WHERE service_work_seq_no = :id",nativeQuery = true)
void updDoneFlag(@Param("id") Long id, @Param("st") Character fl);

@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE service_work_seq_no in :rList",nativeQuery = true)
void delSelectWorks(@Param("sList") CopyOnWriteArrayList<Long> sList);
	
@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE party_seq_no in :cList",nativeQuery = true)
void delSelectWorksByParties(@Param("pList") CopyOnWriteArrayList<Long> pList);

@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE party_seq_no in :bList",nativeQuery = true)
void delSelectWorksByBookings(@Param("bList") CopyOnWriteArrayList<Long> bList);

@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE request_seq_no in :rList",nativeQuery = true)
void delSelectWorksByRequests(@Param("rList") CopyOnWriteArrayList<Long> rList);

@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE service_seq_no in :sList",nativeQuery = true)
void delSelectWorksByServices(@Param("sList") CopyOnWriteArrayList<Long> sList);

@Query(value = "SELECT * FROM SERVICE_WORK_MASTER where created_by in :cList",nativeQuery = true)
void delSelectWorksByCreatedBy(@Param("cList") CopyOnWriteArrayList<Long> cList);

@Query(value = "SELECT * FROM SERVICE_WORK_MASTER where (ON_DATE >= :frDtTm and ON_DATE <= :toDtTm)",nativeQuery = true)
void delSelectWorksBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

@Query(value = "delete FROM SERVICE_WORK_MASTER where upper(trim(billedflag)) <> 'Y'",nativeQuery = true)
void delSelectWorksBillPending();

@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE (upper(trim(OKFLAG)) ='Y' and upper(trim(DONEFLAG)) <>'Y' and upper(trim(JOBAUTOFLAG)) = 'Y' and upper(trim(JOB_ALLOC_STATUS)) <>'Y') order by service_work_seq_no",nativeQuery = true)
void delSelectWorksForAutoAllocJobsNotAllocated();

@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE (upper(trim(OKFLAG)) ='Y' and upper(trim(DONEFLAG)) <>'Y' and upper(trim(RESAUTOFLAG)) = 'Y' and upper(trim(RES_ALLOC_STATUS)) <>'Y') order by service_work_seq_no",nativeQuery = true)
void delSelectWorksForAutoAllocResourcesNotAllocated();

@Query(value = "delete FROM SERVICE_WORK_MASTER WHERE upper(trim(okflag)) = 'Y'",nativeQuery = true)
void delWorksDone();
}