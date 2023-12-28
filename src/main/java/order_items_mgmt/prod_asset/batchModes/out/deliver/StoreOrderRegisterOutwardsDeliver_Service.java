package order_items_mgmt.prod_asset.batchModes.out.deliver;

import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import common.services.sched.*;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;

@Service("storeOrderRegisterOutwardsDeliverServ")
public class StoreOrderRegisterOutwardsDeliver_Service implements I_StoreOrderRegisterOutwardsDeliver_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreRegisterService.class);
	
	@Autowired
	private StoreOrderAssetOutwardsReadPublic_Repo storeOrderAssetOutwardsReadPublicRepoOnLine;

	@Autowired
	private StoreOrderResourceOutwardsReadPublic_Repo storeOrderResourceOutwardsReadPublic_Repo;

	@Autowired
	private I_StoreOrderRegisterAssetOutwardsCommon_Service storeOrderRegisterAssetOutwardsCommonServ;

	@Autowired
	private I_StoreOrderRegisterResourceOutwardsCommon_Service storeOrderRegisterResourceOutwardsCommonServ;
	
	
	// Asset Delivery with Booking, MODE =12
	@Scheduled(fixedRate = 3000)
	public void process_Asset_Alloc() {
		CopyOnWriteArrayList<Long> storeRegisters = storeOrderAssetOutwardsReadPublicRepoOnLine
				.getAllSeqNosForModeNotAllocated(12);
		storeOrderRegisterAssetOutwardsCommonServ.do_Asset_Alloc(storeRegisters);
		return;
	}

	// Resource SALE with Booking, MODE =121
	@Scheduled(fixedRate = 3000)
	public void process_Resource_Alloc() {
		CopyOnWriteArrayList<Long> storeRegisters = storeOrderResourceOutwardsReadPublic_Repo
				.getAllSeqNosForMode(121);
		storeOrderRegisterResourceOutwardsCommonServ.do_Resource_Alloc(storeRegisters);
		return;
	}	
}