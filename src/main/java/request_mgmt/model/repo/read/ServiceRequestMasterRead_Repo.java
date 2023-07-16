package request_mgmt.model.repo.read;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import request_mgmt.model.master.ServiceRequestMaster;

@Repository("serviceRequestReadRepo")
public interface ServiceRequestMasterRead_Repo extends JpaRepository<ServiceRequestMaster, Long> 
{
@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE request_seq_no in :rList order by request_seq_no",nativeQuery = true)
ArrayList<ServiceRequestMaster> getSelectRequests(@Param("rList") ArrayList<Long> rList);
	
@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE company_seq_no in :cList order by request_seq_no",nativeQuery = true)
ArrayList<ServiceRequestMaster> getSelectRequestsByCompanies(@Param("cList") ArrayList<Long> cList);

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE person_seq_no in :pList order by request_seq_no",nativeQuery = true)
ArrayList<ServiceRequestMaster> getSelectRequestsByPeople(@Param("pList") ArrayList<Long> pList);

@Query(value = "select * FROM SERVICE_REQUEST_MASTER WHERE supplier_seq_no in :sList order by request_seq_no",nativeQuery = true)
ArrayList<ServiceRequestMaster> getSelectRequestsBySuppliers(@Param("sList") ArrayList<Long> sList);

@Query(value = "SELECT * FROM SERVICE_REQUEST_MASTER where (REQUEST_DATE >= :frDtTm and REQUEST_DATE <= :toDtTm) ORDER BY request_sEQ_NO",nativeQuery = true)
ArrayList<ServiceRequestMaster> getSelectRequestsBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
}