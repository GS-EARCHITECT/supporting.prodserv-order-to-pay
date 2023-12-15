package location_mgmt.orders.model.repo.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import common.model.master.StoreOrderParamsData;
import common.model.master.StoreOrderParamsDataPK;

@Repository("storeOrderParamsReadPublicRepoOnLine")
public interface StoreOrderParamsReadPublic_Repo extends JpaRepository<StoreOrderParamsData, StoreOrderParamsDataPK> 
{
@Query(value = "SELECT * from STORE_ORDERPARAMS_DATA order by STORE_REQUEST_SEQ_NO",nativeQuery = true)	
public CopyOnWriteArrayList<StoreOrderParamsData> getAllStoreOrderParams();	

@Query(value = "SELECT * from STORE_ORDERPARAMS_DATA where STORE_REQUEST_SEQ_NO in :storeReqSeqNos order by STORE_REQUEST_SEQ_NO",nativeQuery = true)
public CompletableFuture<CopyOnWriteArrayList<StoreOrderParamsData>> getSelectStoreOrderParams(CopyOnWriteArrayList<Long> storeReqSeqNos);
} 
