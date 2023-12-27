package common.services.sched;

import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import order_items_mgmt.prod_asset.allocation.model.repo.common.*;
import order_items_mgmt.prod_asset.allocation.model.repo.online.*;

@Service("storeOrderRegisterInwardsCommonServ")
public class StoreOrderRegisterInwardsCommon_Service implements I_StoreOrderRegisterInwardsCommon_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreRegisterService.class);
	
	@Autowired
    private StoreOrderAssetInwardsCUDPublic_Repo storeOrderAssetInwardsCUDPublicRepoOnLine;
		
	@Autowired
    private StoreOrderAssetInwardsReadPublic_Repo storeOrderAssetInwardsReadPublicRepoOnLine;
	
	@Autowired
    private StoreOrderAssetOutwardsReadPublic_Repo storeOrderAssetOutwardsReadPublicRepoOnLine;
	
	@Autowired
	private WebClient webClient;
	
	public void do_Alloc(CopyOnWriteArrayList<Long> storeRegisters) 
    {	    	
		Long asset_seq_no = (long) 0;
		Long store_seq_no = (long) 0;
		Long max_issue_store_seq_no = (long) 0;
		Long max_recv_store_seq_no = (long) 0;
		boolean vFlag = false;
		Character st = null;
    	    	
    	for (int i = 0; i < storeRegisters.size(); i++) 
    	{
    		store_seq_no = storeRegisters.get(i);
    		asset_seq_no = storeOrderAssetInwardsReadPublicRepoOnLine.getAssetSeqNo(store_seq_no); 
    		max_recv_store_seq_no = storeOrderAssetInwardsReadPublicRepoOnLine.getLatestAllocationBeforeThisRequest(store_seq_no, asset_seq_no);
    		max_issue_store_seq_no = storeOrderAssetOutwardsReadPublicRepoOnLine.getLatestAllocationBeforeThisRequest(store_seq_no, asset_seq_no);
    	
    		if(max_issue_store_seq_no > max_recv_store_seq_no)
    		{
    		vFlag = true;	
    		}
    		else
    		{
    		st = webClient.get()
						.uri("/assetPublicReadManagement/getStatus/" + asset_seq_no).retrieve()
						.bodyToMono(Character.class).block();
    		if(Character.toUpperCase(st)!='Y')
	    	{
    		vFlag = true;
	    	}	
			}
    		
    		if (vFlag) 
    		{
			storeOrderAssetInwardsCUDPublicRepoOnLine.setFlagAllocated('Y', store_seq_no);	
			}
    	}
    		return;
    }
	
}