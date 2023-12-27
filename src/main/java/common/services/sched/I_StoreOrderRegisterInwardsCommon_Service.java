package common.services.sched;

import java.util.concurrent.CopyOnWriteArrayList;

public interface I_StoreOrderRegisterInwardsCommon_Service
{
	public void do_Alloc(CopyOnWriteArrayList<Long> storeRegisters);
}