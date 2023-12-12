package order_items_mgmt.prod_asset.resourcesoutward.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import order_items_mgmt.prod_asset.resourcesoutward.model.dto.StoreOrderResourceOutward_DTO;

public interface I_StoreOrderResourceOutwardsCUDPublic_Service
{
	public CompletableFuture<StoreOrderResourceOutward_DTO> newStoreOrderOutward(StoreOrderResourceOutward_DTO storeOrderResourceOutward_DTO);
	public CompletableFuture<Void> updStoreOrderOutward(StoreOrderResourceOutward_DTO storeOrderResourceOutward_DTO);
	public CompletableFuture<Void> delStoreOrderOutward(Long rno);
	public CompletableFuture<Void> delSelectStoreOrdersOutward(CopyOnWriteArrayList<Long> rnos);
	public CompletableFuture<Void> addQtyRequested( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> subQtyRequested( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> setQtyRequested( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> addQtyBooked( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> subQtyBooked( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> setQtyBooked( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> addQtyAllocated( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> subQtyAllocated( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> setQtyAllocated( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> addQtyMoved( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> subQtyMoved( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> setQtyMoved( Float qty,  Long storeReqSeqNo);
	public CompletableFuture<Void> updateBookStatus( Long storeReqSeqNo,  Character st);
	public CompletableFuture<Void> clearBookStatus( Long storeReqSeqNo);
	public CompletableFuture<Void> updateOKFlag( Long storeReqSeqNo,  Character st);
	public CompletableFuture<Void> clearOKFlag( Long storeReqSeqNo);
	public CompletableFuture<Void> updateDoneFlag( Long storeReqSeqNo,  Character st);
	public CompletableFuture<Void> clearDoneFlag( Long storeReqSeqNo);
}