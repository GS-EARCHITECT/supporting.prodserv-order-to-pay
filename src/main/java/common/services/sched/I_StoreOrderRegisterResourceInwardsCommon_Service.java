package common.services.sched;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public interface I_StoreOrderRegisterResourceInwardsCommon_Service
{
	public CompletableFuture<Void> do_Resource_Alloc(CopyOnWriteArrayList<Long> storeRegisters);
}