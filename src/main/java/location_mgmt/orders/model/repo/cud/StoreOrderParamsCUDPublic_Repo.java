package location_mgmt.orders.model.repo.cud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.*;

@Repository("storeOrderParamsCUDPublicRepoOnLine")
public interface StoreOrderParamsCUDPublic_Repo extends JpaRepository<StoreOrderParamsData, StoreOrderParamsDataPK> 
{
	@Modifying(clearAutomatically = true)
	@Query(value="update STORE_ORDERPARAMS_DATA set REQUEST_PARAMS = :rParam where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
	void updStoreOrderParamRequest(@Param(value = "rParam") String rParam, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

	@Modifying(clearAutomatically = true)
	@Query(value="update STORE_ORDERPARAMS_DATA set LOCATION_PARAMS = :lParam where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
	void updStoreOrderParamLocation(@Param(value = "lParam") String lParam, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

	
} 
