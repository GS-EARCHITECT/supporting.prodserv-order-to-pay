package service_mgmt.pax_details.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import service_mgmt.pax_details.model.master.ServiceWorkPaxDetail;
import service_mgmt.pax_details.model.master.ServiceWorkPaxDetailPK;

@Repository("serviceWorkPaxDetailsCUDRepo")
public interface ServiceWorkPaxDetailsCUD_Repo extends JpaRepository<ServiceWorkPaxDetail, ServiceWorkPaxDetailPK> 
{
@Query(value = "delete FROM SERVICE_WORK_PAX_DETAILS WHERE service_work_seq_no in :sList",nativeQuery = true)
void delSelectWorkPaxs(@Param("sList") CopyOnWriteArrayList<Long> sList);
}