package request_mgmt.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import request_mgmt.model.master.ServiceRequestMaster;

@Repository("serviceRequestCUDRepo")
public interface ServiceRequestMasterCUD_Repo extends JpaRepository<ServiceRequestMaster, Long> 
{
@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE request_seq_no in :rList",nativeQuery = true)
void delSelectRequests(@Param("rList") CopyOnWriteArrayList<Long> rList);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE FR_PARTY_SEQ_NO in :pList ",nativeQuery = true)
void delSelectRequestsByParties(@Param("pList") CopyOnWriteArrayList<Long> pList);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE supplier_seq_no in :sList",nativeQuery = true)
void delSelectRequestsBySuppliers(@Param("sList") CopyOnWriteArrayList<Long> sList);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER where (REQUEST_DTTM >= :frDtTm and REQUEST_DTTM <= :toDtTm) ",nativeQuery = true)
void delSelectRequestsBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE REFERENCE_SEQ_NO in :rList ",nativeQuery = true)
void delSelectRequestsByRefList(@Param("rList") CopyOnWriteArrayList<Long> rList);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE FR_PARTY_LOCATION_SEQ_NO in :lList ",nativeQuery = true)
void delSelectRequestsByFrLocs(@Param("lList") CopyOnWriteArrayList<Long> lList);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE TO_PARTY_LOCATION_SEQ_NO in :tList ",nativeQuery = true)
void delSelectRequestsByToLocs(@Param("tList") CopyOnWriteArrayList<Long> tList);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE upper(trim(okflag)) = 'Y' ",nativeQuery = true)
void delSelectRequestsOk();

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE upper(trim(okflag)) <> 'Y' ",nativeQuery = true)
void delSelectRequestsNotOk();

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE upper(trim(doneflag)) = 'Y' ",nativeQuery = true)
void delSelectRequestsDone();

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE upper(trim(doneflag)) <> 'Y' ",nativeQuery = true)
void delSelectRequestsNotDone();

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE FR_PARTY_LAT = :lat ",nativeQuery = true)
void delSelectRequestsFrPartyLat(@Param("lat") Float lat);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE FR_PARTY_LON = :lon ",nativeQuery = true)
void delSelectRequestsFrPartyLon(@Param("lon") Float lon);

}