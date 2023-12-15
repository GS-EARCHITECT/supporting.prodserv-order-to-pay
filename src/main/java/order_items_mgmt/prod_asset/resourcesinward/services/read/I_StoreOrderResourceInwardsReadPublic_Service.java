package order_items_mgmt.prod_asset.resourcesinward.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import common.model.dto.StoreOrderResourceInward_DTO;

public interface I_StoreOrderResourceInwardsReadPublic_Service
{
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getAllStoreInwards();
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getRowsForJobWorks(CopyOnWriteArrayList<Long> jWorkList);
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getRowsForJobWorksDone(CopyOnWriteArrayList<Long> jWorkList);
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getSelectOrderInwards( CopyOnWriteArrayList<Long> sids);
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getAllRowsForMode( Integer mode);
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