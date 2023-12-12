package order_items_mgmt.prod_asset.resourcesinward.services.read;

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

import order_items_mgmt.prod_asset.resourcesinward.model.dto.StoreOrderResourceInward_DTO;
import order_items_mgmt.prod_asset.resourcesinward.model.master.StoreOrderResourceInward;
import order_items_mgmt.prod_asset.resourcesinward.model.repo.read.StoreOrderResourceInwardsReadPublic_Repo;

@Service("storeOrderResourceInwardsReadPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderResourceInwardsReadPublic_Service implements I_StoreOrderResourceInwardsReadPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderResourceInwardService.class);

	@Autowired
	private StoreOrderResourceInwardsReadPublic_Repo storeOrderResourceInwardsReadPublicRepo;
	
	@Autowired
	private Executor asyncExecutor;
			
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getAllStoreInwards()  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderResourceInward> stoList = (CopyOnWriteArrayList<StoreOrderResourceInward>) storeOrderResourceInwardsReadPublicRepo.findAll();
		CopyOnWriteArrayList<StoreOrderResourceInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderResourceInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getRowsForJobWorks(CopyOnWriteArrayList<Long> jWorkList)
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderResourceInward> stoList = (CopyOnWriteArrayList<StoreOrderResourceInward>) storeOrderResourceInwardsReadPublicRepo.getRowsForJobWorks(jWorkList);
		CopyOnWriteArrayList<StoreOrderResourceInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderResourceInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}	
	
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getRowsForJobWorksDone(CopyOnWriteArrayList<Long> jWorkList)
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderResourceInward> stoList = (CopyOnWriteArrayList<StoreOrderResourceInward>) storeOrderResourceInwardsReadPublicRepo.getRowsForJobWorksDone(jWorkList);
		CopyOnWriteArrayList<StoreOrderResourceInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderResourceInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}	
	
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getSelectOrderInwards( CopyOnWriteArrayList<Long> sids)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderResourceInward> stoList = (CopyOnWriteArrayList<StoreOrderResourceInward>) storeOrderResourceInwardsReadPublicRepo.findAllById(sids);
		CopyOnWriteArrayList<StoreOrderResourceInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderResourceInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> getAllRowsForMode( Integer mode)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderResourceInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderResourceInward> stoList = (CopyOnWriteArrayList<StoreOrderResourceInward>) storeOrderResourceInwardsReadPublicRepo.getAllRowsForMode(mode);
		CopyOnWriteArrayList<StoreOrderResourceInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderResourceInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<Long>> getAllSeqNosForMode( Long mode)  
	{
		CompletableFuture<CopyOnWriteArrayList<Long>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<Long> stoList = storeOrderResourceInwardsReadPublicRepo.getAllSeqNosForMode(mode);
		return stoList;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Character> getIsBookedStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderResourceInwardsReadPublicRepo.getIsbookedStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Character> getIsDoneStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderResourceInwardsReadPublicRepo.getIsDoneStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Character> getIsOkStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderResourceInwardsReadPublicRepo.getIsOkStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getOrderRequestedQty( Long storeReqSeqNo)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getOrderRequestedQty(storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}	
	
	public CompletableFuture<Float> getTotalRequestedForJob( Long jobWorkSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalRequestedForJob( jobWorkSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalQtyRequestedBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalQtyRequestedBeforeThisRequest( storeReqSeqNo,  resourceSeqNo,  mode);
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
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalQtyRequestedBeforeDTTM( dTTs,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float>	getOrderAllocatedQty( Long storeReqSeqNo)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getOrderAllocatedQty( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getTotalAllocatedForJob( Long jobWorkSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalAllocatedForJob( jobWorkSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalQtyAllocatedBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalQtyAllocatedBeforeThisRequest( storeReqSeqNo,  resourceSeqNo,  mode);
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
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalQtyAllocatedBeforeDTTM( dTTs,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	


	public CompletableFuture<Float> getOrderBookedQty( Long storeReqSeqNo)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getOrderBookedQty( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}

	
	public CompletableFuture<Float> getTotalBookedForJob( Long jobWorkSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalBookedForJob( jobWorkSeqNo,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalQtyBookedBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalQtyBookedBeforeThisRequest( storeReqSeqNo,  resourceSeqNo,  mode);
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
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalQtyBookedBeforeDTTM( dTTs,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	
	public CompletableFuture<Float> getMovedQtyForRequest( Long storeReqSeqNo)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getMovedQtyForRequest( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}

	public CompletableFuture<Float> getTotalQtyMovedForResourceForJob( Long jobWorkSeqNo,  Long resourceSeqNo, Integer md)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalQtyMovedForResourceForJob( jobWorkSeqNo,  resourceSeqNo, md);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getTotalQtyMovedForResourceBeforeThisRequest( Long storeReqSeqNo,  Long resourceSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalQtyMovedForResourceBeforeThisRequest( storeReqSeqNo,  resourceSeqNo,  mode);
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
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalRowsMovedForResourcesBeforeThisDTTM(dTTs, resourceSeqNo, mode);
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
		Float result = storeOrderResourceInwardsReadPublicRepo.	getTotalRowsForResourcesBeforeThisDTTM( dTTs,  resourceSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalRowCount()
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderResourceInwardsReadPublicRepo.getTotalRowCount();
		return result;
		},asyncExecutor);

	return future;

	}	

	
	private synchronized CopyOnWriteArrayList<StoreOrderResourceInward_DTO> getStoreOrderResourceInward_DTOs(CopyOnWriteArrayList<StoreOrderResourceInward> stoMasters) {
		StoreOrderResourceInward_DTO stoDTO = null;
		CopyOnWriteArrayList<StoreOrderResourceInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderResourceInward_DTO>();

		for (int i = 0; i < stoMasters.size(); i++) {
			stoDTO = getStoreOrderResourceInward_DTO(stoMasters.get(i));
			stoDTOs.add(stoDTO);
		}
		return stoDTOs;
	}

	private synchronized StoreOrderResourceInward_DTO getStoreOrderResourceInward_DTO(StoreOrderResourceInward storeOrderResourceInward) 
	{
		StoreOrderResourceInward_DTO storeOrderResourceInward_DTO = new StoreOrderResourceInward_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		storeOrderResourceInward_DTO.setDoneflag(storeOrderResourceInward.getDoneflag());
		storeOrderResourceInward_DTO.setFromDttm(formatter.format(storeOrderResourceInward.getFromDttm().toLocalDateTime()));
		storeOrderResourceInward_DTO.setToDttm(formatter.format(storeOrderResourceInward.getToDttm().toLocalDateTime()));
		storeOrderResourceInward_DTO.setIsBooked(storeOrderResourceInward.getIsBooked());
		storeOrderResourceInward_DTO.setJobWorkSeqNo(storeOrderResourceInward.getJobWorkSeqNo());
		storeOrderResourceInward_DTO.setDoneflag(storeOrderResourceInward.getDoneflag());
		storeOrderResourceInward_DTO.setLocationSeqNo(storeOrderResourceInward.getLocationSeqNo());
		storeOrderResourceInward_DTO.setModeTxn(storeOrderResourceInward.getModeTxn());
		storeOrderResourceInward_DTO.setMovedQty(storeOrderResourceInward.getMovedQty());
		storeOrderResourceInward_DTO.setOkflag(storeOrderResourceInward.getOkflag());
		storeOrderResourceInward_DTO.setQtyAllocated(storeOrderResourceInward.getQtyAllocated());
		storeOrderResourceInward_DTO.setQtyBooked(storeOrderResourceInward.getQtyBooked());
		storeOrderResourceInward_DTO.setQtyRequested(storeOrderResourceInward.getQtyRequested());
		storeOrderResourceInward_DTO.setQtyUnitSeqNo(storeOrderResourceInward.getQtyUnitSeqNo());
		storeOrderResourceInward_DTO.setRequestedToPartySeqNo(storeOrderResourceInward.getRequestedToPartySeqNo());
		storeOrderResourceInward_DTO.setRequestorSeqNo(storeOrderResourceInward.getRequestorSeqNo());
		storeOrderResourceInward_DTO.setResourceSeqNo(storeOrderResourceInward.getResourceSeqNo());
		storeOrderResourceInward_DTO.setStoreRequestSeqNo(storeOrderResourceInward.getStoreRequestSeqNo());
		storeOrderResourceInward_DTO.setFrLocationSeqNo(storeOrderResourceInward.getFrLocationSeqNo());
		storeOrderResourceInward_DTO.setToLocationSeqNo(storeOrderResourceInward.getToLocationSeqNo());
		storeOrderResourceInward_DTO.setTargetWorkSeqNo(storeOrderResourceInward.getTargetWorkSeqNo());
		return storeOrderResourceInward_DTO;
	}

}