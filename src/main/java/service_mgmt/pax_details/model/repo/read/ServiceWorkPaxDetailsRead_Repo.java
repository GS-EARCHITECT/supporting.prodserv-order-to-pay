package service_mgmt.pax_details.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service_mgmt.pax_details.model.master.ServiceWorkPaxDetail;
import service_mgmt.pax_details.model.master.ServiceWorkPaxDetailPK;

@Repository("serviceWorkPaxDetailsReadRepo")
public interface ServiceWorkPaxDetailsRead_Repo extends JpaRepository<ServiceWorkPaxDetail, ServiceWorkPaxDetailPK> 
{
	@Query(value = "select * FROM SERVICE_WORK_PAX_DETAILS WHERE service_work_seq_no in :sList order by service_work_seq_no",nativeQuery = true)
	CopyOnWriteArrayList<ServiceWorkPaxDetail> getSelectWorkPaxs(@Param("sList") CopyOnWriteArrayList<Long> sList);
}		
	