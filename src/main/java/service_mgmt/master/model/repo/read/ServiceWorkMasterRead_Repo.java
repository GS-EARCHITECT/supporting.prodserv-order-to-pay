package service_mgmt.master.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service_mgmt.master.model.master.ServiceWorkMaster;

@Repository("serviceWorkReadRepo")
public interface ServiceWorkMasterRead_Repo extends JpaRepository<ServiceWorkMaster, Long> 
{
@Query(value = "select * FROM SERVICE_WORK_MASTER WHERE service_work_seq_no in :rList order by service_work_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceWorkMaster> getSelectWorks(@Param("sList") CopyOnWriteArrayList<Long> sList);
	
@Query(value = "select * FROM SERVICE_WORK_MASTER WHERE party_seq_no in :cList order by service_work_seq_no",nativeQuery = true)
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
}