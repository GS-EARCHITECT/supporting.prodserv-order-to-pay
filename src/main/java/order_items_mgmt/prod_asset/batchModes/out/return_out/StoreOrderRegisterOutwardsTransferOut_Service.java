package order_items_mgmt.prod_asset.batchModes.out.return_out;

import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import common.services.sched.*;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;

@Service("storeOrderRegisterOutwardsTransferOutServ")
public class StoreOrderRegisterOutwardsTransferOut_Service implements I_StoreOrderRegisterOutwardsTransferOut_Service 
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
	
	
	// Asset TransferOut with Booking, MODE =56
	@Scheduled(fixedRate = 3000)
	public void process_Asset_Alloc() {
		CopyOnWriteArrayList<Long> storeRegisters = storeOrderAssetOutwardsReadPublicRepoOnLine
				.getAllSeqNosForModeNotAllocated(56);
		storeOrderRegisterAssetOutwardsCommonServ.do_Asset_Alloc(storeRegisters);
		return;
	}

	// Resource TransferOut with Booking, MODE =561
	@Scheduled(fixedRate = 3000)
	public void process_Resource_Alloc() {
		CopyOnWriteArrayList<Long> storeRegisters = storeOrderResourceOutwardsReadPublic_Repo
				.getAllSeqNosForMode(561);
		storeOrderRegisterResourceOutwardsCommonServ.do_Resource_Alloc(storeRegisters);
		return;
	}	
}