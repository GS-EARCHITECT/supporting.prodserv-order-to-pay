package common.services.sched;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import order_items_mgmt.prod_asset.allocation.model.repo.scheduler.*;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;

@Service("storeOrderRegisterAssetOutwardsCommonServ")
public class StoreOrderRegisterAssetOutwardsCommon_Service implements I_StoreOrderRegisterAssetOutwardsCommon_Service {

	// private static final Logger logger =
	// LoggerFactory.getLogger(StoreRegisterService.class);

	@Autowired
	private StoreOrderAssetOutwardsCUDPublic_Repo storeOrderAssetOutwardsCUDPublicRepo;

	@Autowired
	private StoreOrderAssetOutwardsReadPublic_Repo storeOrderAssetOutwardsReadPublicRepo;

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<Void> do_Asset_Alloc(CopyOnWriteArrayList<Long> storeRegisters) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		Long asset_seq_no = (long) 0;
		Long store_seq_no = (long) 0;
		Long max_issue_store_seq_no = (long) 0;
		Long max_recv_store_seq_no = (long) 0;
		boolean vFlag = false;
		Character st = null;

		for (int i = 0; i < storeRegisters.size(); i++) {
			store_seq_no = storeRegisters.get(i);
			asset_seq_no = storeOrderAssetOutwardsReadPublicRepo.getAssetSeqNo(store_seq_no);
			max_recv_store_seq_no = storeOrderAssetOutwardsReadPublicRepo
					.getLatestAllocationBeforeThisRequest(store_seq_no, asset_seq_no);
			max_issue_store_seq_no = storeOrderAssetOutwardsReadPublicRepo
					.getLatestAllocationBeforeThisRequest(store_seq_no, asset_seq_no);

			if (max_issue_store_seq_no < max_recv_store_seq_no) 
			{
				vFlag = true;
			} else {
				st = webClient.get().uri("/assetPublicReadManagement/getStatus/" + asset_seq_no).retrieve()
						.bodyToMono(Character.class).block();
				if (Character.toUpperCase(st) != 'Y') {
					vFlag = true;
				}
			}

			if (vFlag) {
				storeOrderAssetOutwardsCUDPublicRepo.setFlagAllocated('Y', store_seq_no);
			}
		}		
		return;
		}, asyncExecutor);
		return future;

	}

}