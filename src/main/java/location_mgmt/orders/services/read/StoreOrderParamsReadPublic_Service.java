package location_mgmt.orders.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import common.model.dto.*;
import common.model.master.*;
import location_mgmt.orders.model.repo.read.StoreOrderParamsReadPublic_Repo;
import reactor.core.publisher.Flux;

@Service("storeOrderParamsReadPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderParamsReadPublic_Service implements I_StoreOrderParamsReadPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderParamsDataService.class);

	@Autowired
	private StoreOrderParamsReadPublic_Repo storeOrderParamsReadPublicRepoOnLine;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private Executor asyncExecutor;
			
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderParamsData_DTO>> getAllStoreOrderParams()  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderParamsData_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderParamsData> storeOrderParamsDatas =  storeOrderParamsReadPublicRepoOnLine.getAllStoreOrderParams();
		CopyOnWriteArrayList<StoreOrderParamsData_DTO> storeOrderParamsData_DTOs = getStoreOrderParamsData_DTOs(storeOrderParamsDatas);		
		return storeOrderParamsData_DTOs;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderParamsData_DTO>> getSelectStoreOrderParams(CopyOnWriteArrayList<Long> pSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderParamsData_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{	
		CopyOnWriteArrayList<StoreOrderParamsData> storeOrderParamsDatas =  storeOrderParamsReadPublicRepoOnLine.getSelectStoreOrderParams(pSeqNos);
		CopyOnWriteArrayList<StoreOrderParamsData_DTO> storeOrderParamsData_DTOs = getStoreOrderParamsData_DTOs(storeOrderParamsDatas);		
		return storeOrderParamsData_DTOs;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Double> getSelectStoreOrderItemQoh(Long rid)
	{
		CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> 
		{	
		Flux<ResourceLocationMaster_DTO> specs = null;
		Double tQoh = (double) 0;
		CopyOnWriteArrayList<ResourceLocationMaster_DTO> specLst = new CopyOnWriteArrayList<ResourceLocationMaster_DTO>();
		specs = webClient.get().uri(
				"/resourceLocationPublicReadManagement/getLocationsByResource/"+rid)
				.retrieve().bodyToFlux(ResourceLocationMaster_DTO.class);
		specs.collectList().subscribe(specLst::addAll);
		
		if(specLst!=null)
		{
		for (int i = 0; i < specLst.size(); i++) 
		{
		tQoh = tQoh + specLst.get(i).getQty();	
		}
		}
		
		return tQoh;
		},asyncExecutor);

	return future;
	}
	
	private synchronized CopyOnWriteArrayList<StoreOrderParamsData_DTO> getStoreOrderParamsData_DTOs(CopyOnWriteArrayList<StoreOrderParamsData> stoMasters) {
		StoreOrderParamsData_DTO stoDTO = null;
		CopyOnWriteArrayList<StoreOrderParamsData_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderParamsData_DTO>();

		for (int i = 0; i < stoMasters.size(); i++) {
			stoDTO = getStoreOrderParamsData_DTO(stoMasters.get(i));
			stoDTOs.add(stoDTO);
		}
		return stoDTOs;
	}

	private synchronized StoreOrderParamsData_DTO getStoreOrderParamsData_DTO(StoreOrderParamsData storeOrderParamData) 
	{
		StoreOrderParamsData_DTO storeOrderParamsData_DTO = new StoreOrderParamsData_DTO();
		storeOrderParamsData_DTO.setLocationParams(storeOrderParamData.getLocationParams());
		storeOrderParamsData_DTO.setRequestParams(storeOrderParamData.getRequestParams());
		storeOrderParamsData_DTO.setStoreRequestSeqNo(storeOrderParamData.getStoreOrderParamsDataPK().getStoreRquestSeqNo());
		return storeOrderParamsData_DTO;
	}

}