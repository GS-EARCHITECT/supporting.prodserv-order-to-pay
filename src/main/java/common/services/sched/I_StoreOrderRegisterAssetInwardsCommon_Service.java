package common.services.sched;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public interface I_StoreOrderRegisterAssetInwardsCommon_Service
{
public CompletableFuture<Void> do_Asset_Alloc(CopyOnWriteArrayList<Long> storeRegisters);	
}