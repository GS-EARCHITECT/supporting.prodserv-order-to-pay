package order_items_mgmt.prod_asset.assetsinward.services.read;

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

import order_items_mgmt.prod_asset.assetsinward.model.dto.StoreOrderAssetInward_DTO;
import order_items_mgmt.prod_asset.assetsinward.model.master.StoreOrderAssetInward;
import order_items_mgmt.prod_asset.assetsinward.model.repo.read.StoreOrderAssetInwardsReadPublic_Repo;

@Service("storeOrderAssetInwardsReadPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderAssetInwardsReadPublic_Service implements I_StoreOrderAssetInwardsReadPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderAssetInwardService.class);

	@Autowired
	private StoreOrderAssetInwardsReadPublic_Repo storeOrderAssetInwardsReadPublicRepo;
	
	@Autowired
	private Executor asyncExecutor;
			
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getAllStoreInwards()  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetInward> stoList = (CopyOnWriteArrayList<StoreOrderAssetInward>) storeOrderAssetInwardsReadPublicRepo.findAll();
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getAllStoreInwardsForJobs(CopyOnWriteArrayList<Long> jids)
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetInward> stoList = (CopyOnWriteArrayList<StoreOrderAssetInward>) storeOrderAssetInwardsReadPublicRepo.getRowsForJobWorks(jids);
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getRowsForJobWorksDone(CopyOnWriteArrayList<Long> jWorkList)
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetInward> stoList = (CopyOnWriteArrayList<StoreOrderAssetInward>) storeOrderAssetInwardsReadPublicRepo.getRowsForJobWorksDone(jWorkList);
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getSelectOrderInwards( CopyOnWriteArrayList<Long> sids)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetInward> stoList = (CopyOnWriteArrayList<StoreOrderAssetInward>) storeOrderAssetInwardsReadPublicRepo.findAllById(sids);
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> getAllRowsForMode( Integer mode)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetInward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetInward> stoList = (CopyOnWriteArrayList<StoreOrderAssetInward>) storeOrderAssetInwardsReadPublicRepo.getAllRowsForMode(mode);
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetInward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetInward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<Long>> getAllSeqNosForMode( Long mode)  
	{
		CompletableFuture<CopyOnWriteArrayList<Long>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<Long> stoList = storeOrderAssetInwardsReadPublicRepo.getAllSeqNosForMode(mode);
		return stoList;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Character> getIsbookedStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderAssetInwardsReadPublicRepo.getIsbookedStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Character> getIsDoneStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderAssetInwardsReadPublicRepo.getIsDoneStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Character> getIsOkStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderAssetInwardsReadPublicRepo.getIsOkStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Character> getOrderRequestedFlag( Long storeReqSeqNo)
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character result = storeOrderAssetInwardsReadPublicRepo.getOrderRequestedFlag(storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}	
	
	public CompletableFuture<Float> getCountRequestedForJob( Long jobWorkSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountRequestedForJob( jobWorkSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getCountRequestedBeforeThisRequest( Long storeReqSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountRequestedBeforeThisRequest( storeReqSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getCountRequestedBeforeDTTM( String dTTm,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountRequestedBeforeDTTM( dTTs,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Character>	getOrderAllocatedFlag( Long storeReqSeqNo)
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
			Character result = storeOrderAssetInwardsReadPublicRepo.getOrderAllocatedFlag( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getCountAllocatedForJob( Long jobWorkSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountAllocatedForJob( jobWorkSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getCountAllocatedBeforeThisRequest( Long storeReqSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountAllocatedBeforeThisRequest( storeReqSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	


	public CompletableFuture<Float> getCountAllocatedBeforeDTTM( String dTTm,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountAllocatedBeforeDTTM( dTTs,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	


	public CompletableFuture<Character> getOrderBookedFlag( Long storeReqSeqNo)
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character result = storeOrderAssetInwardsReadPublicRepo.getOrderBookedFlag( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}

	
	public CompletableFuture<Float> getCountBookedForJob( Long jobWorkSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountBookedForJob( jobWorkSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getCountBookedBeforeThisRequest( Long storeReqSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountBookedBeforeThisRequest( storeReqSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	


	public CompletableFuture<Float> getCountBookedBeforeDTTM( String dTTm,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountBookedBeforeDTTM( dTTs,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	
	public CompletableFuture<Character> getOrderMovedFlag( Long storeReqSeqNo)
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character result = storeOrderAssetInwardsReadPublicRepo.getOrderMovedFlag( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}

	public CompletableFuture<Float> getCountMovedForJob( Long jobWorkSeqNo,  Long assetSeqNo, Integer md)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountMovedForJob( jobWorkSeqNo,  assetSeqNo, md);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getCountMovedBeforeThisRequest( Long storeReqSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountMovedBeforeThisRequest( storeReqSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getCountMovedBeforeDTTM( String dTTm,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderAssetInwardsReadPublicRepo.getCountMovedBeforeDTTM(dTTs, assetSeqNo, mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalRowsForAssetsBeforeThisDTTM( String dTTm,  Long assetSeqNo, Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime rd = LocalDateTime.parse(dTTm, formatter);
		Timestamp dTTs = Timestamp.valueOf(rd);
		Float result = storeOrderAssetInwardsReadPublicRepo.getTotalRowsForAssetsBeforeThisDTTM( dTTs,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalRowCount()
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetInwardsReadPublicRepo.getTotalRowCount();
		return result;
		},asyncExecutor);

	return future;

	}	

	
	private synchronized CopyOnWriteArrayList<StoreOrderAssetInward_DTO> getStoreOrderAssetInward_DTOs(CopyOnWriteArrayList<StoreOrderAssetInward> stoMasters) {
		StoreOrderAssetInward_DTO stoDTO = null;
		CopyOnWriteArrayList<StoreOrderAssetInward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetInward_DTO>();

		for (int i = 0; i < stoMasters.size(); i++) {
			stoDTO = getStoreOrderAssetInward_DTO(stoMasters.get(i));
			stoDTOs.add(stoDTO);
		}
		return stoDTOs;
	}

	private synchronized StoreOrderAssetInward_DTO getStoreOrderAssetInward_DTO(StoreOrderAssetInward storeOrderAssetInward2) 
	{
		StoreOrderAssetInward_DTO storeOrderAssetInward_DTO = new StoreOrderAssetInward_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		storeOrderAssetInward_DTO.setDoneflag(storeOrderAssetInward2.getDoneflag());
		storeOrderAssetInward_DTO.setFromDttm(formatter.format(storeOrderAssetInward2.getFromDttm().toLocalDateTime()));
		storeOrderAssetInward_DTO.setToDttm(formatter.format(storeOrderAssetInward2.getToDttm().toLocalDateTime()));
		storeOrderAssetInward_DTO.setIsBooked(storeOrderAssetInward2.getIsBooked());
		storeOrderAssetInward_DTO.setJobWorkSeqNo(storeOrderAssetInward2.getJobWorkSeqNo());		
		storeOrderAssetInward_DTO.setLocationSeqNo(storeOrderAssetInward2.getLocationSeqNo());
		storeOrderAssetInward_DTO.setModeTxn(storeOrderAssetInward2.getModeTxn());
		storeOrderAssetInward_DTO.setMovedFlag(storeOrderAssetInward2.getMovedFlag());
		storeOrderAssetInward_DTO.setOkflag(storeOrderAssetInward2.getOkflag());
		storeOrderAssetInward_DTO.setFlagAllocated(storeOrderAssetInward2.getFlagAllocated());
		storeOrderAssetInward_DTO.setFlagBooked(storeOrderAssetInward2.getFlagBooked());
		storeOrderAssetInward_DTO.setFlagRequested(storeOrderAssetInward2.getFlagRequested());
		storeOrderAssetInward_DTO.setRequestedToPartySeqNo(storeOrderAssetInward2.getRequestedToPartySeqNo());
		storeOrderAssetInward_DTO.setRequestorSeqNo(storeOrderAssetInward2.getRequestorSeqNo());
		storeOrderAssetInward_DTO.setAssetSeqNo(storeOrderAssetInward2.getAssetSeqNo());
		storeOrderAssetInward_DTO.setStoreRequestSeqNo(storeOrderAssetInward2.getStoreRequestSeqNo());
		storeOrderAssetInward_DTO.setFrLocationSeqNo(storeOrderAssetInward2.getFrLocationSeqNo());
		storeOrderAssetInward_DTO.setToLocationSeqNo(storeOrderAssetInward2.getToLocationSeqNo());
		storeOrderAssetInward_DTO.setTargetWorkSeqNo(storeOrderAssetInward2.getTargetWorkSeqNo());
		return storeOrderAssetInward_DTO;
	}

}