package location_mgmt.orders.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import common.model.dto.StoreOrderParamsData_DTO;
import common.model.master.StoreOrderParamsDataPK;

public interface I_StoreOrderParamsCUDPublic_Service
{
	public CompletableFuture<StoreOrderParamsData_DTO> newStoreOrderParam(StoreOrderParamsData_DTO storeOrderParamsData_DTO);
	public CompletableFuture<Void> updStoreOrderParam(StoreOrderParamsData_DTO storeOrderParamsData_DTO);
	public CompletableFuture<Void> updStoreOrderParamRequest(String reqStr, Long storeReqSeqNo);
	public CompletableFuture<Void> updStoreOrderParamLocation(String locStr, Long storeReqSeqNo);
	public CompletableFuture<Void> delAllStoreOrderParams();
	public CompletableFuture<Void> delSelectStoreOrderParams(CopyOnWriteArrayList<StoreOrderParamsDataPK> pnos);
}