package order_items_mgmt.prod_asset.assetsoutward.services.read;

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

import order_items_mgmt.prod_asset.assetsoutward.model.dto.StoreOrderAssetOutward_DTO;
import order_items_mgmt.prod_asset.assetsoutward.model.master.StoreOrderAssetOutward;
import order_items_mgmt.prod_asset.assetsoutward.model.repo.read.StoreOrderAssetOutwardsReadPublic_Repo;

@Service("storeOrderAssetOutwardsReadPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderAssetOutwardsReadPublic_Service implements I_StoreOrderAssetOutwardsReadPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderAssetOutwardService.class);

	@Autowired
	private StoreOrderAssetOutwardsReadPublic_Repo storeOrderAssetOutwardsReadPublicRepo;
	
	@Autowired
	private Executor asyncExecutor;
			
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> getAllStoreOutwards()  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetOutward> stoList = (CopyOnWriteArrayList<StoreOrderAssetOutward>) storeOrderAssetOutwardsReadPublicRepo.findAll();
		CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetOutward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;
	}
	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> getRowsForJobWorks(CopyOnWriteArrayList<Long> jWorkList)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetOutward> stoList = (CopyOnWriteArrayList<StoreOrderAssetOutward>) storeOrderAssetOutwardsReadPublicRepo.getRowsForJobWorks(jWorkList);
		CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetOutward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> getRowsForJobWorksDone(CopyOnWriteArrayList<Long> jWorkList)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetOutward> stoList = (CopyOnWriteArrayList<StoreOrderAssetOutward>) storeOrderAssetOutwardsReadPublicRepo.getRowsForJobWorksDone(jWorkList);
		CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetOutward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	
	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> getSelectOrderOutwards( CopyOnWriteArrayList<Long> sids)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetOutward> stoList = (CopyOnWriteArrayList<StoreOrderAssetOutward>) storeOrderAssetOutwardsReadPublicRepo.findAllById(sids);
		CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetOutward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> getAllRowsForMode( Integer mode)  
	{
		CompletableFuture<CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<StoreOrderAssetOutward> stoList = (CopyOnWriteArrayList<StoreOrderAssetOutward>) storeOrderAssetOutwardsReadPublicRepo.getAllRowsForMode(mode);
		CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>();
		stoDTOs = stoList != null ? this.getStoreOrderAssetOutward_DTOs(stoList) : null;
		return stoDTOs;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<Long>> getAllSeqNosForMode( Long mode)  
	{
		CompletableFuture<CopyOnWriteArrayList<Long>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<Long> stoList = storeOrderAssetOutwardsReadPublicRepo.getAllSeqNosForMode(mode);
		return stoList;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Character> getIsbookedStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderAssetOutwardsReadPublicRepo.getIsbookedStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Character> getIsDoneStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderAssetOutwardsReadPublicRepo.getIsDoneStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Character> getIsOkStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character st = storeOrderAssetOutwardsReadPublicRepo.getIsOkStatus(storeReqSeqNo);
		return st;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Character> getOrderRequestedFlag( Long storeReqSeqNo)
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character result = storeOrderAssetOutwardsReadPublicRepo.getOrderRequestedFlag(storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}	
	
	public CompletableFuture<Float> getCountRequestedForJob( Long jobWorkSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountRequestedForJob( jobWorkSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getCountRequestedBeforeThisRequest( Long storeReqSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountRequestedBeforeThisRequest( storeReqSeqNo,  assetSeqNo,  mode);
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
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountRequestedBeforeDTTM( dTTs,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Character>	getOrderAllocatedFlag( Long storeReqSeqNo)
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character result = storeOrderAssetOutwardsReadPublicRepo.getOrderAllocatedFlag( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getCountAllocatedForJob( Long jobWorkSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountAllocatedForJob( jobWorkSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getCountAllocatedBeforeThisRequest( Long storeReqSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountAllocatedBeforeThisRequest( storeReqSeqNo,  assetSeqNo,  mode);
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
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountAllocatedBeforeDTTM( dTTs,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	


	public CompletableFuture<Character> getOrderBookedFlag( Long storeReqSeqNo)
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
			Character result = storeOrderAssetOutwardsReadPublicRepo.getOrderBookedFlag( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}

	
	public CompletableFuture<Float> getCountBookedForJob( Long jobWorkSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountBookedForJob( jobWorkSeqNo,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getCountBookedBeforeThisRequest( Long storeReqSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountBookedBeforeThisRequest( storeReqSeqNo,  assetSeqNo,  mode);
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
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountBookedBeforeDTTM( dTTs,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	
	public CompletableFuture<Character> getOrderMovedFlag( Long storeReqSeqNo)
	{
		CompletableFuture<Character> future = CompletableFuture.supplyAsync(() -> 
		{
		Character result = storeOrderAssetOutwardsReadPublicRepo.getOrderMovedFlag( storeReqSeqNo);
		return result;
		},asyncExecutor);

	return future;

	}

	public CompletableFuture<Float> getCountMovedForJob( Long jobWorkSeqNo,  Long assetSeqNo, Integer md)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountMovedForJob( jobWorkSeqNo,  assetSeqNo, md);
		return result;
		},asyncExecutor);

	return future;

	}
	
	public CompletableFuture<Float> getCountMovedBeforeThisRequest( Long storeReqSeqNo,  Long assetSeqNo,  Integer mode)
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountMovedBeforeThisRequest( storeReqSeqNo,  assetSeqNo,  mode);
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
		Float result = storeOrderAssetOutwardsReadPublicRepo.getCountMovedBeforeDTTM(dTTs, assetSeqNo, mode);
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
		Float result = storeOrderAssetOutwardsReadPublicRepo.	getTotalRowsForAssetsBeforeThisDTTM( dTTs,  assetSeqNo,  mode);
		return result;
		},asyncExecutor);

	return future;

	}	

	public CompletableFuture<Float> getTotalRowCount()
	{
		CompletableFuture<Float> future = CompletableFuture.supplyAsync(() -> 
		{
		Float result = storeOrderAssetOutwardsReadPublicRepo.getTotalRowCount();
		return result;
		},asyncExecutor);

	return future;

	}	

	
	private synchronized CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> getStoreOrderAssetOutward_DTOs(CopyOnWriteArrayList<StoreOrderAssetOutward> stoMasters) {
		StoreOrderAssetOutward_DTO stoDTO = null;
		CopyOnWriteArrayList<StoreOrderAssetOutward_DTO> stoDTOs = new CopyOnWriteArrayList<StoreOrderAssetOutward_DTO>();

		for (int i = 0; i < stoMasters.size(); i++) {
			stoDTO = getStoreOrderAssetOutward_DTO(stoMasters.get(i));
			stoDTOs.add(stoDTO);
		}
		return stoDTOs;
	}

	private synchronized StoreOrderAssetOutward_DTO getStoreOrderAssetOutward_DTO(StoreOrderAssetOutward storeOrderAssetOutward) 
	{
		StoreOrderAssetOutward_DTO storeOrderAssetOutward_DTO = new StoreOrderAssetOutward_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		storeOrderAssetOutward_DTO.setDoneflag(storeOrderAssetOutward.getDoneflag());
		storeOrderAssetOutward_DTO.setFromDttm(formatter.format(storeOrderAssetOutward.getFromDttm().toLocalDateTime()));
		storeOrderAssetOutward_DTO.setToDttm(formatter.format(storeOrderAssetOutward.getToDttm().toLocalDateTime()));
		storeOrderAssetOutward_DTO.setIsBooked(storeOrderAssetOutward.getIsBooked());
		storeOrderAssetOutward_DTO.setJobWorkSeqNo(storeOrderAssetOutward.getJobWorkSeqNo());		
		storeOrderAssetOutward_DTO.setLocationSeqNo(storeOrderAssetOutward.getLocationSeqNo());
		storeOrderAssetOutward_DTO.setModeTxn(storeOrderAssetOutward.getModeTxn());
		storeOrderAssetOutward_DTO.setMovedFlag(storeOrderAssetOutward.getMovedFlag());
		storeOrderAssetOutward_DTO.setOkflag(storeOrderAssetOutward.getOkflag());
		storeOrderAssetOutward_DTO.setFlagAllocated(storeOrderAssetOutward.getFlagAllocated());
		storeOrderAssetOutward_DTO.setFlagBooked(storeOrderAssetOutward.getFlagBooked());
		storeOrderAssetOutward_DTO.setFlagRequested(storeOrderAssetOutward.getFlagRequested());
		storeOrderAssetOutward_DTO.setRequestedToPartySeqNo(storeOrderAssetOutward.getRequestedToPartySeqNo());
		storeOrderAssetOutward_DTO.setRequestorSeqNo(storeOrderAssetOutward.getRequestorSeqNo());
		storeOrderAssetOutward_DTO.setAssetSeqNo(storeOrderAssetOutward.getAssetSeqNo());
		storeOrderAssetOutward_DTO.setStoreRequestSeqNo(storeOrderAssetOutward.getStoreRequestSeqNo());
		storeOrderAssetOutward_DTO.setFrLocationSeqNo(storeOrderAssetOutward.getFrLocationSeqNo());
		storeOrderAssetOutward_DTO.setToLocationSeqNo(storeOrderAssetOutward.getToLocationSeqNo());
		storeOrderAssetOutward_DTO.setTargetWorkSeqNo(storeOrderAssetOutward.getTargetWorkSeqNo());
		return storeOrderAssetOutward_DTO;
	}

}