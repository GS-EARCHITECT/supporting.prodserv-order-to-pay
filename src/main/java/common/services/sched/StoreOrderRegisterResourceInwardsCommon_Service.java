package common.services.sched;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;
import order_items_mgmt.prod_asset.allocation.model.repo.scheduler.*;

@Service("storeOrderRegisterResourceInwardsCommonServ")
public class StoreOrderRegisterResourceInwardsCommon_Service
		implements I_StoreOrderRegisterResourceInwardsCommon_Service {

	// private static final Logger logger =
	// LoggerFactory.getLogger(StoreRegisterService.class);

	@Autowired
	private StoreOrderResourceInwardsCUDPublic_Repo storeOrderResourceInwardsCUDPublicRepo;

	@Autowired
	private StoreOrderResourceInwardsReadPublic_Repo storeOrderResourceInwardsReadPublicRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<Void> do_Resource_Alloc(CopyOnWriteArrayList<Long> storeRegisters) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			Long store_seq_no = (long) 0;
			Float reqQty = (float) 0;
			Float allQty = (float) 0;

			for (int i = 0; i < storeRegisters.size(); i++) {
				store_seq_no = storeRegisters.get(i);
				reqQty = storeOrderResourceInwardsReadPublicRepo.getOrderRequestedQty(store_seq_no);
				allQty = storeOrderResourceInwardsReadPublicRepo.getOrderAllocatedQty(store_seq_no);

				if (allQty == null || allQty == 0) {
					storeOrderResourceInwardsCUDPublicRepo.addQtyAllocated(reqQty, store_seq_no);
				}
			}
			return;
		}, asyncExecutor);
		return future;
	}

}