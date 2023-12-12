package order_items_mgmt.prod_asset.resourcesinward.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import order_items_mgmt.prod_asset.resourcesinward.model.dto.StoreOrderResourceInward_DTO;

public interface I_StoreOrderResourceInwardsCUDPublic_Service
{
	public CompletableFuture<StoreOrderResourceInward_DTO> newStoreOrderInward(StoreOrderResourceInward_DTO storeOrderResourceInward_DTO);
	public CompletableFuture<Void> updStoreOrderInward(StoreOrderResourceInward_DTO storeOrderResourceInward_DTO);
	public CompletableFuture<Void> delStoreOrderInward(Long rno);
	public CompletableFuture<Void> delSelectStoreOrdersInward(CopyOnWriteArrayList<Long> rnos);
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