package location_mgmt.orders.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import common.model.dto.StoreOrderParamsData_DTO;

public interface I_StoreOrderParamsReadPublic_Service
{
public CompletableFuture<CopyOnWriteArrayList<StoreOrderParamsData_DTO>> getAllStoreOrderParams();	
public CompletableFuture<CopyOnWriteArrayList<StoreOrderParamsData_DTO>> getSelectStoreOrderParams(CopyOnWriteArrayList<Long> pSeqNos);
public CompletableFuture<Double> getSelectStoreOrderItemQoh(Long rid);
}