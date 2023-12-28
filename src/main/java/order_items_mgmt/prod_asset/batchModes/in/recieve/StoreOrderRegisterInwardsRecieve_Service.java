package order_items_mgmt.prod_asset.batchModes.in.recieve;

import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import common.services.sched.*;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;

@Service("storeOrderRegisterInwardsRecieveServ")
public class StoreOrderRegisterInwardsRecieve_Service implements I_StoreOrderRegisterInwardsRecieve_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreRegisterService.class);
		
	@Autowired
	private StoreOrderAssetInwardsReadPublic_Repo storeOrderAssetInwardsReadPublicRepoOnLine;

	@Autowired
	private StoreOrderResourceInwardsReadPublic_Repo storeOrderResourceInwardsReadPublic_Repo;

	@Autowired
	private I_StoreOrderRegisterAssetInwardsCommon_Service storeOrderRegisterAssetInwardsCommonServ;

	@Autowired
	private I_StoreOrderRegisterResourceInwardsCommon_Service storeOrderRegisterResourceInwardsCommonServ;

		
	//* Asset Recieve, MODE=7
	@Scheduled(fixedRate=3000)
	public void process_Asset_Alloc() 
    {	    	
		CopyOnWriteArrayList<Long> storeRegisters = storeOrderAssetInwardsReadPublicRepoOnLine.getAllSeqNosForModeNotAllocated(7);    	
		storeOrderRegisterAssetInwardsCommonServ.do_Asset_Alloc(storeRegisters);		
    	return;
    }
	
	// * Resource Recieve, MODE=77
		@Scheduled(fixedRate = 3000)
		public void process_Resource_Alloc() {
			CopyOnWriteArrayList<Long> storeRegisters = storeOrderResourceInwardsReadPublic_Repo
					.getAllSeqNosForMode(77);
			storeOrderRegisterResourceInwardsCommonServ.do_Resource_Alloc(storeRegisters);
			return;
		}
	
}