package jobs.job_mgmt.model.repo.online;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.ServiceMovementMaster;

@Repository("serviceMovementRepoOnLine")
public interface ServiceMovementRepo extends JpaRepository<ServiceMovementMaster, Long> 
{
	
	@Query(value = "SELECT * FROM SERVICE_MOVEMENT_MASTER where service_work_seq_no = :servWorkSeqNo and rownum = 1",nativeQuery = true) 
	Optional<ServiceMovementMaster> getServiceMovementCatStatus(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Query(value = "SELECT * FROM SERVICE_MOVEMENT_MASTER where (service_work_seq_no <> :servWorkSeqNo and service_seq_no = :servSeqNo and from_location_seq_no = :fromLocSeqNo and to_location_seq_no = :toLocSeqNo and rownum = 1)",nativeQuery = true) 
	ServiceMovementMaster getAnyOtherMovementService(@Param("servWorkSeqNo") Long servWorkSeqNo, @Param("servSeqNo") Long servSeqNo, @Param("fromLocSeqNo") Long fromLocSeqNo, @Param("toLocSeqNo") Long toLocSeqNo);
		
	@Query(value = "SELECT * FROM SERVICE_MOVEMENT_MASTER where service_work_seq_no = :servWorkSeqNo and rownum = 1",nativeQuery = true) 
	ServiceMovementMaster getServiceMovementDetails(@Param("servWorkSeqNo") Long servWorkSeqNo);
	
	@Query(value = "SELECT fromdata_alloc_flag FROM SERVICE_MOVEMENT_MASTER where service_work_seq_no = :servWorkSeqNo",nativeQuery = true) 
	Optional<Character> getServiceMovementAllocStatus(@Param("servWorkSeqNo") Long servWorkSeqNo);
}