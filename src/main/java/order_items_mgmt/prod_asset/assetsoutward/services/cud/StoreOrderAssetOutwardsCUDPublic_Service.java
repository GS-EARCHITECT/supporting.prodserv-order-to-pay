package order_items_mgmt.prod_asset.assetsoutward.services.cud;

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
import order_items_mgmt.prod_asset.assetsoutward.model.repo.cud.StoreOrderAssetOutwardsCUDPublic_Repo;

@Service("storeOrderAssetOutwardsCUDPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderAssetOutwardsCUDPublic_Service implements I_StoreOrderAssetOutwardsCUDPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderAssetOutwardService.class);

	@Autowired
	private StoreOrderAssetOutwardsCUDPublic_Repo storeOrderAssetOutwardsCUDPublicRepo;
	
	@Autowired
	private Executor asyncExecutor;
			
	@Override
	public CompletableFuture<StoreOrderAssetOutward_DTO> newStoreOrderOutward(StoreOrderAssetOutward_DTO storeOrderAssetOutward_DTO)  
	{
		CompletableFuture<StoreOrderAssetOutward_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		StoreOrderAssetOutward_DTO stoDTO = null;	
		if(!storeOrderAssetOutwardsCUDPublicRepo.existsById(storeOrderAssetOutward_DTO.getStoreRequestSeqNo()))
		{		
		stoDTO = this.getStoreOrderAssetOutward_DTO(storeOrderAssetOutwardsCUDPublicRepo.save(this.setStoreOrderAssetOutward(storeOrderAssetOutward_DTO)));
		}
		return stoDTO;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updStoreOrderOutward(StoreOrderAssetOutward_DTO storeOrderAssetOutward_DTO)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		StoreOrderAssetOutward sto = null;	
		if(storeOrderAssetOutwardsCUDPublicRepo.existsById(storeOrderAssetOutward_DTO.getStoreRequestSeqNo()))
		{	
		sto = this.setStoreOrderAssetOutward(storeOrderAssetOutward_DTO);
		sto.setStoreRequestSeqNo(storeOrderAssetOutward_DTO.getStoreRequestSeqNo()); 
		storeOrderAssetOutwardsCUDPublicRepo.save(sto);
		}
		return;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Void> delStoreOrderOutward(Long rno)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		
		storeOrderAssetOutwardsCUDPublicRepo.deleteById(rno);
		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> delSelectStoreOrdersOutward(CopyOnWriteArrayList<Long> rnos)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		
		storeOrderAssetOutwardsCUDPublicRepo.deleteAllById(rnos);;
		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setFlagRequested( Character flag,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.setFlagRequested( flag,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setFlagBooked( Character flag,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.setFlagBooked( flag,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setFlagAllocated( Character flag,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.setFlagAllocated( flag,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setFlagMoved( Character flag,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.setFlagMoved( flag,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateBookStatus( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.updateBookStatus( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearBookStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.clearBookStatus( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateOKFlag( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.updateOKFlag( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearOKFlag( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.clearOKFlag( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateDoneFlag( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.updateDoneFlag( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearDoneFlag( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetOutwardsCUDPublicRepo.clearDoneFlag( storeReqSeqNo);		
		return;
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
		storeOrderAssetOutward_DTO.setDoneflag(storeOrderAssetOutward.getDoneflag());
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
	
	private synchronized StoreOrderAssetOutward setStoreOrderAssetOutward(StoreOrderAssetOutward_DTO storeOrderAssetOutward_DTO) 
	{
		StoreOrderAssetOutward storeOrderAssetOutward = new StoreOrderAssetOutward();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime fd = LocalDateTime.parse(storeOrderAssetOutward_DTO.getFromDttm(), formatter);
		LocalDateTime td = LocalDateTime.parse(storeOrderAssetOutward_DTO.getToDttm(), formatter);
		Timestamp fs = Timestamp.valueOf(fd);		
		Timestamp ts = Timestamp.valueOf(td);		
		storeOrderAssetOutward.setFromDttm(fs);
		storeOrderAssetOutward.setToDttm(ts);
		storeOrderAssetOutward.setDoneflag(storeOrderAssetOutward_DTO.getDoneflag());
		storeOrderAssetOutward.setIsBooked(storeOrderAssetOutward_DTO.getIsBooked());
		storeOrderAssetOutward.setJobWorkSeqNo(storeOrderAssetOutward_DTO.getJobWorkSeqNo());
		storeOrderAssetOutward.setDoneflag(storeOrderAssetOutward_DTO.getDoneflag());
		storeOrderAssetOutward.setLocationSeqNo(storeOrderAssetOutward_DTO.getLocationSeqNo());
		storeOrderAssetOutward.setModeTxn(storeOrderAssetOutward_DTO.getModeTxn());
		storeOrderAssetOutward.setMovedFlag(storeOrderAssetOutward_DTO.getMovedFlag());
		storeOrderAssetOutward.setOkflag(storeOrderAssetOutward_DTO.getOkflag());
		storeOrderAssetOutward.setFlagAllocated(storeOrderAssetOutward_DTO.getFlagAllocated());
		storeOrderAssetOutward.setFlagBooked(storeOrderAssetOutward_DTO.getFlagBooked());
		storeOrderAssetOutward.setFlagRequested(storeOrderAssetOutward_DTO.getFlagRequested());
		storeOrderAssetOutward.setRequestedToPartySeqNo(storeOrderAssetOutward_DTO.getRequestedToPartySeqNo());
		storeOrderAssetOutward.setRequestorSeqNo(storeOrderAssetOutward_DTO.getRequestorSeqNo());
		storeOrderAssetOutward.setAssetSeqNo(storeOrderAssetOutward_DTO.getAssetSeqNo());
		storeOrderAssetOutward.setFrLocationSeqNo(storeOrderAssetOutward_DTO.getFrLocationSeqNo());
		storeOrderAssetOutward.setToLocationSeqNo(storeOrderAssetOutward_DTO.getToLocationSeqNo());
		storeOrderAssetOutward.setTargetWorkSeqNo(storeOrderAssetOutward_DTO.getTargetWorkSeqNo());
		return storeOrderAssetOutward;
	}

}