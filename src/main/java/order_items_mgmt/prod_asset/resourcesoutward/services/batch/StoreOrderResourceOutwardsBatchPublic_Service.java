package order_items_mgmt.prod_asset.resourcesoutward.services.batch;

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
import common.model.repo.JobResourceMaster_Repo;
import jobs.job_mgmt.model.repo.common.JobWorkDetailsRead_Repo;
import jobs.job_mgmt.model.repo.common.ServiceWorkMasterRead_Repo;
import location_mgmt.orders.services.cud.StoreOrderParamsCUDPublic_Service;
import order_items_mgmt.prod_asset.resourcesoutward.model.repo.read.StoreOrderResourceOutwardsReadPublic_Repo;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.repo.read.ServiceRequestMasterRead_Repo;

@Service("storeOrderResourceOutwardsBatchPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderResourceOutwardsBatchPublic_Service implements I_StoreOrderResourceOutwardsBatchPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderResourceOutwardService.class);

	@Autowired
	private StoreOrderResourceOutwardsReadPublic_Repo storeOrderResourceOutwardsReadPublicRepo;
	
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
		CopyOnWriteArrayList<StoreOrderResourceOutward> storeOrderResourceOutwards = storeOrderResourceOutwardsReadPublicRepo.getAllOrderResourceOutwards();
		JobWorkDetail jobWorkDetail = null;
		ServiceRequestMaster serviceRequestMaster = null;
		ServiceWorkMaster serviceWorkMaster = null;
		Double lat = null;
		Double lon = null;
				
		if(storeOrderResourceOutwards!=null && storeOrderResourceOutwards.size()>0)
		{
		for (int i = 0; i < storeOrderResourceOutwards.size(); i++) 
		{
		jobWorkDetail = jobWorkDetailsReadRepo.findById(storeOrderResourceOutwards.get(i).getJobWorkSeqNo()).get();	
		serviceWorkMaster = serviceWorkReadRepo.findById(jobWorkDetail.getServiceWorkSeqNo()).get();
		serviceRequestMaster = serviceRequestReadRepo.findById(serviceWorkMaster.getServiceSeqNo()).get();
		lat =  serviceRequestMaster.getFrPartyLat();
		lon = serviceRequestMaster.getFrPartyLon();		
		storeOrderParamsCUDPublicServ.setSelectJobAssetResLocationParam(storeOrderResourceOutwards.get(i).getStoreRequestSeqNo(), jobWorkDetail.getJobSeqNo(), storeOrderResourceOutwards.get(i).getResourceSeqNo(), lat, lon);
		}			
		}
		
	return ;

	}
	
}