package order_items_mgmt.resourcesoutward.services.read;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import order_items_mgmt.resourcesoutward.model.dto.StoreOrderResourceOutward_DTO;
import order_items_mgmt.resourcesoutward.model.master.StoreOrderResourceOutward;
import order_items_mgmt.resourcesoutward.model.repo.read.StoreOrderResourceOutwardsReadPublic_Repo;

@Service("storeOrderResourceOutwardsReadPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderResourceOutwardsReadPublic_Service implements I_StoreOrderResourceOutwardsReadPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderResourceOutwardService.class);

	@Autowired
	private StoreOrderResourceOutwardsReadPublic_Repo storeOrderResourceOutwardsReadPublicRepo;
	
	@Autowired
	private Executor asyncExecutor;
			
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> getAllStoreOutwards()  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderResourceOutward> stoList = (CopyOnWriteArrayList<StoreOrderResourceOutward>) storeOrderResourceOutwardsReadPublicRepo.findAll();
		CopyOnWriteArrayList<StoreOrderResourceOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderResourceOutward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> getSelectOrderOutwards( CopyOnWriteArrayList<Long> sids)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderResourceOutward> stoList = (CopyOnWriteArrayList<StoreOrderResourceOutward>) storeOrderResourceOutwardsReadPublicRepo.findAllById(sids);
		CopyOnWriteArrayList<StoreOrderResourceOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderResourceOutward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> getAllRowsForMode( Integer mode)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderResourceOutward> stoList = (CopyOnWriteArrayList<StoreOrderResourceOutward>) storeOrderResourceOutwardsReadPublicRepo.getAllRowsForMode(mode);
		CopyOnWriteArrayList<StoreOrderResourceOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderResourceOutward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<Long>> getAllSeqNosForMode( Long mode)  
	{
		CompletableFuture<CopyOnWriteArrayList<Long>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<Long> stoList = storeOrderResourceOutwardsReadPublicRepo.getAllSeqNosForMode(mode);
		return stoList;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Character> getIsBookedStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderResourceOutwardsReadPublicRepo.getIsbookedStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Character> getIsDoneStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderResourceOutwardsReadPublicRepo.getIsDoneStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Character> getIsOkStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderResourceOutwardsReadPublicRepo.getIsOkStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getOrderRequestedQty( Long storeReqSeqNo)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getOrderRequestedQty(storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}	
	
	public CompletableFuture<Float> getTotalRequestedForJob( Long jobWorkSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalRequestedForJob( jobWorkSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalQtyRequestedBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyRequestedBeforeThisRequest( storeReqSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalQtyRequestedBeforeDTTM( String dTTm,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyRequestedBeforeDTTM( dTTs,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float>	getOrderAllocatedQty( Long storeReqSeqNo)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getOrderAllocatedQty( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getTotalAllocatedForJob( Long jobWorkSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalAllocatedForJob( jobWorkSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalQtyAllocatedBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyAllocatedBeforeThisRequest( storeReqSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	


	public CompletableFuture<Float> getTotalQtyAllocatedBeforeDTTM( String dTTm,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyAllocatedBeforeDTTM( dTTs,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	


	public CompletableFuture<Float> getOrderBookedQty( Long storeReqSeqNo)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getOrderBookedQty( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}

	
	public CompletableFuture<Float> getTotalBookedForJob( Long jobWorkSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalBookedForJob( jobWorkSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalQtyBookedBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyBookedBeforeThisRequest( storeReqSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	


	public CompletableFuture<Float> getTotalQtyBookedBeforeDTTM( String dTTm,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyBookedBeforeDTTM( dTTs,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	
	public CompletableFuture<Float> getMovedQtyForRequest( Long storeReqSeqNo)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getMovedQtyForRequest( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}

	public CompletableFuture<Float> getTotalQtyMovedForResourceForJob( Long jobWorkSeqNo,  Long resourceSeqNo, Integer md)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyMovedForResourceForJob( jobWorkSeqNo,  resourceSeqNo, md);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getTotalQtyMovedForResourceBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalQtyMovedForResourceBeforeThisRequest( storeReqSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getTotalRowsMovedForResourcesBeforeThisDTTM( String dTTm,  Long resourceSeqNo,  Long storeReqSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalRowsMovedForResourcesBeforeThisDTTM(dTTs, resourceSeqNo, mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalRowsForResourcesBeforeThisDTTM( String dTTm,  Long resourceSeqNo, Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderResourceOutwardsReadPublicRepo.	getTotalRowsForResourcesBeforeThisDTTM( dTTs,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalRowCount()
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceOutwardsReadPublicRepo.getTotalRowCount();
		return result;
		},asyncExecutor);

	return future;

	}	

	
	private synchronized CopyOnWriteArrayList<StoreOrderResourceOutward_DTO> getStoreOrderResourceOutward_DTOs(CopyOnWriteArrayList<StoreOrderResourceOutward> stoMasters) {
		StoreOrderResourceOutward_DTO stoDTO = null;
		CopyOnWriteArrayList<StoreOrderResourceOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceOutward_DTO>();

		for (int i = 0; i < stoMasters.size(); i++) {
			stoDTO = getStoreOrderResourceOutward_DTO(stoMasters.get(i));
			stoDTOs.add(stoDTO);
		}
		return stoDTOs;
	}

	private synchronized StoreOrderResourceOutward_DTO getStoreOrderResourceOutward_DTO(StoreOrderResourceOutward storeOrderResourceOutward) 
	{
		StoreOrderResourceOutward_DTO storeOrderResourceOutward_DTO = new StoreOrderResourceOutward_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		storeOrderResourceOutward_DTO.setDoneflag(storeOrderResourceOutward.getDoneflag());
		storeOrderResourceOutward_DTO.setFromDttm(formatter.format(storeOrderResourceOutward.getFromDttm().toLocalDateTime()));
		storeOrderResourceOutward_DTO.setToDttm(formatter.format(storeOrderResourceOutward.getToDttm().toLocalDateTime()));
		storeOrderResourceOutward_DTO.setIsBooked(storeOrderResourceOutward.getIsBooked());
		storeOrderResourceOutward_DTO.setJobWorkSeqNo(storeOrderResourceOutward.getJobWorkSeqNo());
		storeOrderResourceOutward_DTO.setDoneflag(storeOrderResourceOutward.getDoneflag());
		storeOrderResourceOutward_DTO.setLocationSeqNo(storeOrderResourceOutward.getLocationSeqNo());
		storeOrderResourceOutward_DTO.setModeTxn(storeOrderResourceOutward.getModeTxn());
		storeOrderResourceOutward_DTO.setMovedQty(storeOrderResourceOutward.getMovedQty());
		storeOrderResourceOutward_DTO.setOkflag(storeOrderResourceOutward.getOkflag());
		storeOrderResourceOutward_DTO.setQtyAllocated(storeOrderResourceOutward.getQtyAllocated());
		storeOrderResourceOutward_DTO.setQtyBooked(storeOrderResourceOutward.getQtyBooked());
		storeOrderResourceOutward_DTO.setQtyRequested(storeOrderResourceOutward.getQtyRequested());
		storeOrderResourceOutward_DTO.setQtyUnitSeqNo(storeOrderResourceOutward.getQtyUnitSeqNo());
		storeOrderResourceOutward_DTO.setRequestedToPartySeqNo(storeOrderResourceOutward.getRequestedToPartySeqNo());
		storeOrderResourceOutward_DTO.setRequestorSeqNo(storeOrderResourceOutward.getRequestorSeqNo());
		storeOrderResourceOutward_DTO.setResourceSeqNo(storeOrderResourceOutward.getResourceSeqNo());
		storeOrderResourceOutward_DTO.setStoreRequestSeqNo(storeOrderResourceOutward.getStoreRequestSeqNo());
		storeOrderResourceOutward_DTO.setFrLocationSeqNo(storeOrderResourceOutward.getFrLocationSeqNo());
		storeOrderResourceOutward_DTO.setToLocationSeqNo(storeOrderResourceOutward.getToLocationSeqNo());
		storeOrderResourceOutward_DTO.setTargetWorkSeqNo(storeOrderResourceOutward.getTargetWorkSeqNo());
		return storeOrderResourceOutward_DTO;
	}

}