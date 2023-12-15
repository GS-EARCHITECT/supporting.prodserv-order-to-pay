package order_items_mgmt.prod_asset.allocation.services.online;

import java.util.concurrent.CompletableFuture;

public interface I_ResourceAllocMgmt_Service 
{
public CompletableFuture<Void> resource_OnlineAlloc(Long servWorkSeqNo);
}