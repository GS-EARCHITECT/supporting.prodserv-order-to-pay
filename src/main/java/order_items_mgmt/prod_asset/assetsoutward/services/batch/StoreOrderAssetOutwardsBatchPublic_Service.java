package order_items_mgmt.prod_asset.assetsoutward.services.batch;

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
import order_items_mgmt.prod_asset.assetsoutward.model.repo.read.StoreOrderAssetOutwardsReadPublic_Repo;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.repo.read.ServiceRequestMasterRead_Repo;

@Service("storeOrderAssetOutwardsBatchPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderAssetOutwardsBatchPublic_Service implements I_StoreOrderAssetOutwardsBatchPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderAssetOutwardService.class);

	@Autowired
	private StoreOrderAssetOutwardsReadPublic_Repo storeOrderAssetOutwardsReadPublicRepo;
	
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
		CopyOnWriteArrayList<StoreOrderAssetOutward> storeOrderAssetOutwards = storeOrderAssetOutwardsReadPublicRepo.getAllOrderAssetOutwards();
		JobWorkDetail jobWorkDetail = null;
		ServiceRequestMaster serviceRequestMaster = null;
		ServiceWorkMaster serviceWorkMaster = null;
		Double lat = null;
		Double lon = null;
				
		if(storeOrderAssetOutwards!=null && storeOrderAssetOutwards.size()>0)
		{
		for (int i = 0; i < storeOrderAssetOutwards.size(); i++) 
		{
		jobWorkDetail = jobWorkDetailsReadRepo.findById(storeOrderAssetOutwards.get(i).getJobWorkSeqNo()).get();	
		serviceWorkMaster = serviceWorkReadRepo.findById(jobWorkDetail.getServiceWorkSeqNo()).get();
		serviceRequestMaster = serviceRequestReadRepo.findById(serviceWorkMaster.getServiceSeqNo()).get();
		lat =  serviceRequestMaster.getFrPartyLat();
		lon = serviceRequestMaster.getFrPartyLon();		
		storeOrderParamsCUDPublicServ.setSelectJobAssetResLocationParam(storeOrderAssetOutwards.get(i).getStoreRequestSeqNo(), jobWorkDetail.getJobSeqNo(), storeOrderAssetOutwards.get(i).getAssetSeqNo(), lat, lon);
		}			
		}
		
	return ;

	}
	
}