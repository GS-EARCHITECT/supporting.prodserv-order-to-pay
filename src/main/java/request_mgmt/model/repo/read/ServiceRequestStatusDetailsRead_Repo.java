package request_mgmt.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import request_mgmt.model.master.ServiceRequestStatusDetails;
import request_mgmt.model.master.ServiceRequestStatusDetailsPK;

@Repository("serviceRequestStatusReadRepo")
public interface ServiceRequestStatusDetailsRead_Repo extends JpaRepository<ServiceRequestStatusDetails, ServiceRequestStatusDetailsPK> 
{
	@Query(value = "select * FROM SERVICE_REQUEST_STATUS WHERE request_seq_no in :rList order by request_seq_no",nativeQuery = true)
	CopyOnWriteArrayList<ServiceRequestStatusDetails> getSelectRequestStatus(@Param("rList") CopyOnWriteArrayList<Long> rList);

	@Query(value = "SELECT * FROM SERVICE_REQUEST_STATUS where (ON_DATE >= :frDtTm and ON_DATE <= :toDtTm) ORDER BY request_sEQ_NO",nativeQuery = true)
	CopyOnWriteArrayList<ServiceRequestStatusDetails> getSelectRequestStatusBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

}