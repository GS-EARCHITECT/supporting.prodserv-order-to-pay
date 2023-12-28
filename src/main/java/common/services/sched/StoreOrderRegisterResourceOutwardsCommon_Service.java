package common.services.sched;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import common.api.I_StoreOrderCommonAPI_Service;
import common.model.master.StoreOrderResourceOutward;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;
import order_items_mgmt.prod_asset.allocation.model.repo.scheduler.*;

@Service("storeOrderRegisterResourceOutwardsCommonServ")
public class StoreOrderRegisterResourceOutwardsCommon_Service
		implements I_StoreOrderRegisterResourceOutwardsCommon_Service {

	// private static final Logger logger =
	// LoggerFactory.getLogger(StoreRegisterService.class);

	@Autowired
	private StoreOrderResourceOutwardsCUDPublic_Repo storeOrderResourceOutwardsCUDPublicRepo;

	@Autowired
	private StoreOrderResourceOutwardsReadPublic_Repo storeOrderResourceOutwardsReadPublicRepo;

	@Autowired
	private I_StoreOrderCommonAPI_Service storeOrderCommonAPIServ;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<Void> do_Resource_Alloc(CopyOnWriteArrayList<Long> storeRegisters) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			Long item_seq_no_curr = (long) 0;
			Long store_seq_no_curr = (long) 0;
			Long jw_seq_no_curr = (long) 0;
			float requested_qty = (float) 0;
			float book_qty = (float) 0;
			float eqoh = (float) 0;
			float qoh = (float) 0;
			float eff = (float) 0;
			StoreOrderResourceOutward sMaster = null;
			CompletableFuture<Float> futureQ = null;
			CompletableFuture<Float> futureE = null;

			if (storeRegisters != null) {
				for (int i = 0; i < storeRegisters.size(); i++) {
					book_qty = 0;
					store_seq_no_curr = storeRegisters.get(i);
					sMaster = storeOrderResourceOutwardsReadPublicRepo.findById(store_seq_no_curr).get();

					if (Character.toUpperCase(sMaster.getIsBooked()) != 'Y') {
						item_seq_no_curr = sMaster.getResourceSeqNo();
						jw_seq_no_curr = sMaster.getJobWorkSeqNo();
						requested_qty = sMaster.getQtyRequested();
						futureQ = storeOrderCommonAPIServ.getQohByJobResRule(store_seq_no_curr, jw_seq_no_curr,
								item_seq_no_curr);
						qoh = futureQ.join();
						futureE = storeOrderCommonAPIServ.getEffectiveQoh(store_seq_no_curr, item_seq_no_curr,
								sMaster.getFromDttm());
						eff = futureE.join();
						eqoh = qoh - eff;

						if (requested_qty <= eqoh) {
							// logger.info("Perfect Alloc & Deducting From Items :"+needed_qty);
							storeOrderResourceOutwardsCUDPublicRepo.addQtyAllocated(requested_qty, store_seq_no_curr);
						} else {
							storeOrderResourceOutwardsCUDPublicRepo.addQtyAllocated(eqoh, store_seq_no_curr);
							book_qty = requested_qty - eqoh;
							storeOrderResourceOutwardsCUDPublicRepo.addQtyBooked(book_qty, store_seq_no_curr);
							storeOrderResourceOutwardsCUDPublicRepo.updateDoneFlag(store_seq_no_curr, 'Y');
						}
					}

				}
			}

			return;
		}, asyncExecutor);

		return future;
	}

}