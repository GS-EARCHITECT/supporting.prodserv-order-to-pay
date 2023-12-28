package common.api;

import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public interface I_StoreOrderCommonAPI_Service
{
	public CompletableFuture<Float> getQohByJobResRule(Long sSeqNo, Long jWSeqNo, Long iSeqNo);
    public CompletableFuture<Float> getQohByResourceLocations(Long rSeqNo, CopyOnWriteArrayList<Long> llist);
    public CompletableFuture<Float> getQohByAllResourceLocations(Long rSeqNo);
    public CompletableFuture<Void>  updQohByResourceLocation(Long rSeqNo, Long locSeqNo, Float qoh);
    public CompletableFuture<Float> getEffectiveQoh(Long eff_storeSeqNo, Long eff_itemSeqNo, Timestamp dTTm);
}