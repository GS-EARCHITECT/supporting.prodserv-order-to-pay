package order_items_mgmt.prod_asset.assetsinward.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import common.model.dto.StoreOrderAssetInward_DTO;


public interface I_StoreOrderAssetInwardsCUDPublic_Service
{
	public CompletableFuture<StoreOrderAssetInward_DTO> newStoreOrderInward(StoreOrderAssetInward_DTO storeOrderAssetInward_DTO);
	public CompletableFuture<Void> updStoreOrderInward(StoreOrderAssetInward_DTO storeOrderAssetInward_DTO);
	public CompletableFuture<Void> delStoreOrderInward(Long rno);
	public CompletableFuture<Void> delSelectStoreOrdersInward(CopyOnWriteArrayList<Long> rnos);
	public CompletableFuture<Void> setFlagRequested( Character flag,  Long storeReqSeqNo);
	public CompletableFuture<Void> setFlagBooked( Character flag,  Long storeReqSeqNo);
	public CompletableFuture<Void> setFlagAllocated( Character flag,  Long storeReqSeqNo);
	public CompletableFuture<Void> setFlagMoved( Character flag,  Long storeReqSeqNo);
	public CompletableFuture<Void> updateBookStatus( Long storeReqSeqNo,  Character st);
	public CompletableFuture<Void> clearBookStatus( Long storeReqSeqNo);
	public CompletableFuture<Void> updateOKFlag( Long storeReqSeqNo,  Character st);
	public CompletableFuture<Void> clearOKFlag( Long storeReqSeqNo);
	public CompletableFuture<Void> updateDoneFlag( Long storeReqSeqNo,  Character st);
	public CompletableFuture<Void> clearDoneFlag( Long storeReqSeqNo);
}