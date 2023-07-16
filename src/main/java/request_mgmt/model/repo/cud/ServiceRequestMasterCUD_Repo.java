package request_mgmt.model.repo.cud;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import request_mgmt.model.master.ServiceRequestMaster;

@Repository("serviceRequestCUDRepo")
public interface ServiceRequestMasterCUD_Repo extends JpaRepository<ServiceRequestMaster, Long> 
{
@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE request_seq_no in :rList",nativeQuery = true)
void delSelectRequests(@Param("rList") ArrayList<Long> rList);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE company_seq_no in :cList",nativeQuery = true)
void delSelectRequestsByCompanies(@Param("cList") ArrayList<Long> cList);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE person_seq_no in :pList",nativeQuery = true)
void delSelectRequestsByPeople(@Param("pList") ArrayList<Long> pList);

@Query(value = "delete FROM SERVICE_REQUEST_MASTER WHERE supplier_seq_no in :sList",nativeQuery = true)
void delSelectRequestsBySuppliers(@Param("sList") ArrayList<Long> sList);

@Query(value = "DELETE FROM SERVICE_REQUEST_MASTER where (REQUEST_DATE >= :frDtTm and REQUEST_DATE <= :toDtTm)",nativeQuery = true)
void delSelectRequestsBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
}