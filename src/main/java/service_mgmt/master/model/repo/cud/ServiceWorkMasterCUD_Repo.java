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
}