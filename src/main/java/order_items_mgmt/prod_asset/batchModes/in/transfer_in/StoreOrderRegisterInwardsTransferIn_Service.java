package order_items_mgmt.prod_asset.batchModes.in.transfer_in;

import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import common.services.sched.I_StoreOrderRegisterInwardsCommon_Service;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;

@Service("storeOrderRegisterInwardsTransferInServ")
public class StoreOrderRegisterInwardsTransferIn_Service implements I_StoreOrderRegisterInwardsTransferIn_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreRegisterService.class);
		
	@Autowired
    private StoreOrderAssetInwardsReadPublic_Repo storeOrderAssetInwardsReadPublicRepoOnLine;
	
	@Autowired
    private I_StoreOrderRegisterInwardsCommon_Service storeOrderRegisterInwardsCommonServ;
		
	//* TransferIn, MODE=24
	@Scheduled(fixedRate=3000)
	public void process_Alloc() 
    {	    	
		CopyOnWriteArrayList<Long> storeRegisters = storeOrderAssetInwardsReadPublicRepoOnLine.getAllSeqNosForModeNotAllocated(24);    	
		storeOrderRegisterInwardsCommonServ.do_Alloc(storeRegisters);		
    	return;
    }
	
}