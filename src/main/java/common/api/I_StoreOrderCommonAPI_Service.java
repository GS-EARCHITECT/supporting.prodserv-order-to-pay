package common.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public interface I_StoreOrderCommonAPI_Service
{
    public CompletableFuture<Float> getQohByResourceLocations(Long rSeqNo, CopyOnWriteArrayList<Long> llist);
    public CompletableFuture<Float> getQohByAllResourceLocations(Long rSeqNo);
    public CompletableFuture<Void> updQohByResourceLocation(Long rSeqNo, Long locSeqNo, Float qoh);
}