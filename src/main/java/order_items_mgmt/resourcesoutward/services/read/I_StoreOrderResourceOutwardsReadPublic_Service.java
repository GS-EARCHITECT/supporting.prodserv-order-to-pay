package order_items_mgmt.resourcesoutward.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import order_items_mgmt.resourcesoutward.model.dto.StoreOrderResourceOutward_DTO;

public interface I_StoreOrderResourceOutwardsReadPublic_Service
{
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> getAllStoreOutwards();
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> getSelectOrderOutwards( CopyOnWriteArrayList<Long> sids);
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> getAllRowsForMode( Integer mode);
	public CompletableFuture<CopyOnWriteArrayList<Long>> getAllSeqNosForMode( Long mode);
	public CompletableFuture<Character> getIsBookedStatus( Long storeReqSeqNo);
	public CompletableFuture<Character> getIsDoneStatus( Long storeReqSeqNo);
	public CompletableFuture<Character> getIsOkStatus( Long storeReqSeqNo);
	public CompletableFuture<Float> getOrderRequestedQty( Long storeReqSeqNo);
	public CompletableFuture<Float> getTotalRequestedForJob( Long jobWorkSeqNo,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getTotalQtyRequestedBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getTotalQtyRequestedBeforeDTTM( String dTTm,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getOrderAllocatedQty( Long storeReqSeqNo);
	public CompletableFuture<Float> getTotalAllocatedForJob( Long jobWorkSeqNo,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getTotalQtyAllocatedBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getTotalQtyAllocatedBeforeDTTM( String dTTm,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getOrderBookedQty( Long storeReqSeqNo);
	public CompletableFuture<Float> getTotalBookedForJob( Long jobWorkSeqNo,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getTotalQtyBookedBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getTotalQtyBookedBeforeDTTM( String dTTm,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getMovedQtyForRequest( Long storeReqSeqNo);
	public CompletableFuture<Float> getTotalQtyMovedForResourceForJob( Long jobWorkSeqNo,  Long resourceSeqNo, Integer mode);
	public CompletableFuture<Float> getTotalQtyMovedForResourceBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getTotalRowsForResourcesBeforeThisDTTM( String dTTm,  Long resourceSeqNo,  Integer mode);
	public CompletableFuture<Float> getTotalRowCount();
}