package order_items_mgmt.prod_asset.assetsinward.services.batch;

import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.dto.*;
import common.model.master.*;
import common.model.repo.JobAssetMaster_Repo;
import jobs.job_mgmt.model.repo.common.JobWorkDetailsRead_Repo;
import jobs.job_mgmt.model.repo.common.ServiceWorkMasterRead_Repo;
import location_mgmt.orders.services.cud.StoreOrderParamsCUDPublic_Service;
import order_items_mgmt.prod_asset.assetsinward.model.repo.read.StoreOrderAssetInwardsReadPublic_Repo;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.repo.read.ServiceRequestMasterRead_Repo;

@Service("storeOrderAssetInwardsBatchPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderAssetInwardsBatchPublic_Service implements I_StoreOrderAssetInwardsBatchPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderAssetInwardService.class);

	@Autowired
	private StoreOrderAssetInwardsReadPublic_Repo storeOrderAssetInwardsReadPublicRepo;
	
	@Autowired
	private ServiceWorkMasterRead_Repo serviceWorkReadRepo;
	
	@Autowired
	private StoreOrderParamsCUDPublic_Service storeOrderParamsCUDPublicServ; 
	
	@Autowired
	private ServiceRequestMasterRead_Repo serviceRequestReadRepo;
	
	@Autowired
	private	JobWorkDetailsRead_Repo jobWorkDetailsReadRepo;
	
	@Scheduled(fixedRate=3000)
	@Override
	public void distanceLocationAlloc()  
	{
		CopyOnWriteArrayList<StoreOrderAssetInward> storeOrderAssetInwards = storeOrderAssetInwardsReadPublicRepo.getAllOrderAssetInwards();
		JobWorkDetail jobWorkDetail = null;
		ServiceRequestMaster serviceRequestMaster = null;
		ServiceWorkMaster serviceWorkMaster = null;
		Double lat = null;
		Double lon = null;
				
		if(storeOrderAssetInwards!=null && storeOrderAssetInwards.size()>0)
		{
		for (int i = 0; i < storeOrderAssetInwards.size(); i++) 
		{
		jobWorkDetail = jobWorkDetailsReadRepo.findById(storeOrderAssetInwards.get(i).getJobWorkSeqNo()).get();	
		serviceWorkMaster = serviceWorkReadRepo.findById(jobWorkDetail.getServiceWorkSeqNo()).get();
		serviceRequestMaster = serviceRequestReadRepo.findById(serviceWorkMaster.getServiceSeqNo()).get();
		lat =  serviceRequestMaster.getFrPartyLat();
		lon = serviceRequestMaster.getFrPartyLon();		
		storeOrderParamsCUDPublicServ.setSelectJobAssetResLocationParam(storeOrderAssetInwards.get(i).getStoreRequestSeqNo(), jobWorkDetail.getJobSeqNo(), storeOrderAssetInwards.get(i).getAssetSeqNo(), lat, lon);
		}			
		}
		
	return ;

	}
	
}