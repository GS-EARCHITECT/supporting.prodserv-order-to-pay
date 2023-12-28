package order_items_mgmt.prod_asset.resourcesinward.services.batch;

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
import order_items_mgmt.prod_asset.resourcesinward.model.repo.read.StoreOrderResourceInwardsReadPublic_Repo;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.repo.read.ServiceRequestMasterRead_Repo;

@Service("storeOrderResourceInwardsBatchPublicServ")
public class StoreOrderResourceInwardsBatchPublic_Service implements I_StoreOrderResourceInwardsBatchPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderResourceInwardService.class);

	@Autowired
	private StoreOrderResourceInwardsReadPublic_Repo storeOrderResourceInwardsReadPublicRepo;
	
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
		CopyOnWriteArrayList<StoreOrderResourceInward> storeOrderResourceInwards = storeOrderResourceInwardsReadPublicRepo.getAllOrderResourceInwards();
		JobWorkDetail jobWorkDetail = null;
		ServiceRequestMaster serviceRequestMaster = null;
		ServiceWorkMaster serviceWorkMaster = null;
		Double lat = null;
		Double lon = null;
				
		if(storeOrderResourceInwards!=null && storeOrderResourceInwards.size()>0)
		{
		for (int i = 0; i < storeOrderResourceInwards.size(); i++) 
		{
		jobWorkDetail = jobWorkDetailsReadRepo.findById(storeOrderResourceInwards.get(i).getJobWorkSeqNo()).get();	
		serviceWorkMaster = serviceWorkReadRepo.findById(jobWorkDetail.getServiceWorkSeqNo()).get();
		serviceRequestMaster = serviceRequestReadRepo.findById(serviceWorkMaster.getServiceSeqNo()).get();
		lat =  serviceRequestMaster.getFrPartyLat();
		lon = serviceRequestMaster.getFrPartyLon();		
		storeOrderParamsCUDPublicServ.setSelectJobAssetResLocationParam(storeOrderResourceInwards.get(i).getStoreRequestSeqNo(), jobWorkDetail.getJobSeqNo(), storeOrderResourceInwards.get(i).getResourceSeqNo(), lat, lon);
		}			
		}
		
	return ;

	}
	
}