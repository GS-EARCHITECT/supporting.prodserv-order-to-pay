package request_mgmt.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import request_mgmt.model.master.ServiceRequestMaster;

@Repository("serviceRequestReadRepo")
public interface ServiceRequestMasterRead_Repo extends JpaRepository<ServiceRequestMaster, Long> 
{
@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE request_seq_no in :rList order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequests(@Param("rList") CopyOnWriteArrayList<Long> rList);

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE FR_PARTY_SEQ_NO in :pList order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsByParties(@Param("pList") CopyOnWriteArrayList<Long> pList);

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE TO_PARTY_SEQ_NO in :sList order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsBySuppliers(@Param("sList") CopyOnWriteArrayList<Long> sList);

@Query(value = "SELECT * FROM SERVICE_REQUEST_MASTER where (REQUEST_DTTM >= :frDtTm and REQUEST_DTTM <= :toDtTm) ORDER BY request_sEQ_NO",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE REFERENCE_SEQ_NO in :rList order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsByRefList(@Param("rList") CopyOnWriteArrayList<Long> rList);

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE FR_PARTY_LOCATION_SEQ_NO in :lList order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsByFrLocs(@Param("lList") CopyOnWriteArrayList<Long> lList);

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE TO_PARTY_LOCATION_SEQ_NO in :tList order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsByToLocs(@Param("tList") CopyOnWriteArrayList<Long> tList);

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE upper(trim(okflag)) = 'Y' order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsOk();

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE upper(trim(okflag)) <> 'Y' order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsNotOk();

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE upper(trim(doneflag)) = 'Y' order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsDone();

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE upper(trim(doneflag)) <> 'Y' order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsNotDone();

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE FR_PARTY_LAT = :lat order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsFrPartyLat(@Param("lat") Float lat);

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE FR_PARTY_LON = :lon order by request_seq_no",nativeQuery = true)
CopyOnWriteArrayList<ServiceRequestMaster> getSelectRequestsFrPartyLon(@Param("lon") Float lon);
}