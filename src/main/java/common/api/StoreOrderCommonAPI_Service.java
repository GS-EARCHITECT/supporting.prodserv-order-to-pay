package common.api;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import common.model.master.JobWorkDetail;
import common.model.master.ServiceWorkMaster;
import common.model.master.StoreOrderParamsData;
import common.model.master.StoreOrderParamsDataPK;
import jobs.job_mgmt.model.repo.common.JobWorkDetailsRead_Repo;
import jobs.job_mgmt.model.repo.common.ServiceWorkMasterRead_Repo;
import location_mgmt.orders.model.repo.read.StoreOrderParamsReadPublic_Repo;
import order_items_mgmt.prod_asset.allocation.model.repo.common.StoreOrderResourceOutwardsReadPublic_Repo;
import reactor.core.publisher.Mono;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.repo.read.ServiceRequestMasterRead_Repo;


@Service("storeOrderCommonAPIServ")
public class StoreOrderCommonAPI_Service implements I_StoreOrderCommonAPI_Service {

	// private static final Logger logger =
	// LoggerFactory.getLogger(StoreRegisterService.class);

	@Autowired
	private StoreOrderParamsReadPublic_Repo storeOrderParamsReadPublicRepoOnLine;
	
	@Autowired
	private ServiceWorkMasterRead_Repo serviceWorkReadRepo;
	
	@Autowired
	private ServiceRequestMasterRead_Repo serviceRequestReadRepo;
	
	@Autowired
	private	JobWorkDetailsRead_Repo jobWorkDetailsReadRepo;
	
	@Autowired
	private StoreOrderResourceOutwardsReadPublic_Repo storeOrderResourceOutwardsReadPublicRepo;
	
	@Autowired
	private WebClient webClient;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<Float> getQohByJobResRule(Long sSeqNo, Long jWSeqNo, Long iSeqNo) 
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		JobWorkDetail jobWorkDetail = null;
		ServiceRequestMaster serviceRequestMaster = null;
		ServiceWorkMaster serviceWorkMaster = null;			
			
		Float qoh = (float) 0;	
		StoreOrderParamsDataPK storeOrderParamsDataPK = new StoreOrderParamsDataPK();
		storeOrderParamsDataPK.setStoreRquestSeqNo(sSeqNo);
		Optional<StoreOrderParamsData> storeOrderParamsData = storeOrderParamsReadPublicRepoOnLine.findById(storeOrderParamsDataPK);  
		String locStr = "";	
		CopyOnWriteArrayList<Long> locList = null;
		String[] locStrList = null; 
				
		if(storeOrderParamsData.get()==null)
		{
		jobWorkDetail = jobWorkDetailsReadRepo.findById(jWSeqNo).get();	
		serviceWorkMaster = serviceWorkReadRepo.findById(jobWorkDetail.getServiceWorkSeqNo()).get();
		serviceRequestMaster = serviceRequestReadRepo.findById(serviceWorkMaster.getServiceSeqNo()).get();	
		
		if(serviceRequestMaster.getToPartyLocationSeqNo()!=null)
		{
		locList = new CopyOnWriteArrayList<Long>();
		locList.add(serviceRequestMaster.getToPartyLocationSeqNo());		
		CompletableFuture<Float> future2 = getQohByResourceLocations(iSeqNo, locList);
		qoh = future2.join();
		}
		else
		{	
		qoh = (float) -1;
		}
		}
		else
		{
		locStr = storeOrderParamsData.get().getLocationParams();	
		locStrList = locStr.split(",");
		locList = new CopyOnWriteArrayList<Long>();
		for (int i = 0; i < locStrList.length; i++) 
		{
		locList.add(Long.parseLong(locStrList[i]));	
		}
		CompletableFuture<Float> future2 = getQohByResourceLocations(iSeqNo, locList);
		qoh = future2.join();		
		}	
		return qoh;
		}, asyncExecutor);
		return future;
	}
	
	
	public CompletableFuture<Float> getQohByResourceLocations(Long rSeqNo, CopyOnWriteArrayList<Long> llist) 
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {			
			
			Float qoh = (float) 0;
			Mono<Float> qohMono = null;

			// Flux.fromIterable(llist), Long.class).retrieve()
			// Mono.just(products), new ParameterizedTypeReference<List<Product>>() {}

			qohMono = webClient.method(HttpMethod.GET)
					.uri("/resourceLocationPublicReadManagement/getQohByResourceLocations/"+rSeqNo)
					.body(Mono.just(llist), new ParameterizedTypeReference<CopyOnWriteArrayList<Long>>() {
					}).retrieve().bodyToMono(Float.class);
			qoh = qohMono.block();
			return qoh;
		}, asyncExecutor);
		return future;
	}

	
	public CompletableFuture<Float> getQohByAllResourceLocations(Long rSeqNo) 
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {
			Float qoh = (float) 0;
			Mono<Float> qohMono = null;

			// Flux.fromIterable(llist), Long.class).retrieve()
			// Mono.just(products), new ParameterizedTypeReference<List<Product>>() {}

			qohMono = webClient.method(HttpMethod.GET)
					.uri("/resourceLocationPublicReadManagement/getQohByAllResourceLocations/"+rSeqNo)
					.body(Mono.just(rSeqNo), Long.class).retrieve().bodyToMono(Float.class);
			qoh = qohMono.block();
			return qoh;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> updQohByResourceLocation(Long rSeqNo, Long locSeqNo, Float qoh) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {			

			// Flux.fromIterable(llist), Long.class).retrieve()
			// Mono.just(products), new ParameterizedTypeReference<List<Product>>() {}

			webClient.method(HttpMethod.GET)
					.uri("/resourceLocationPublicCUDManagement/setPartyLocationResourceQoh/"+rSeqNo+"/"+qoh+"/"+locSeqNo)
					.body(Mono.just(rSeqNo), Long.class).retrieve().bodyToMono(Float.class).block();
			return;
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Float> getEffectiveQoh(Long eff_storeSeqNo, Long eff_itemSeqNo, Timestamp dTTm) 
    {
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> {			
		Float eff_totAllocQty = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyAllocatedForResourceBeforeThisRequestAnyModeWithDates(eff_storeSeqNo, eff_itemSeqNo, dTTm);
		Float eff_totReqQty = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyAllocatedForResourceBeforeThisRequestAnyModeWithDates(eff_storeSeqNo, eff_itemSeqNo, dTTm);
		return (eff_totReqQty - eff_totAllocQty);		
		}, asyncExecutor);
		return future;	    
	    
		}

	
	
}