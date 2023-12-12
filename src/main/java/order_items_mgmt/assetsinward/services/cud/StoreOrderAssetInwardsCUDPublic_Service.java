package order_items_mgmt.assetsinward.services.cud;

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

import order_items_mgmt.assetsinward.model.dto.StoreOrderAssetInward_DTO;
import order_items_mgmt.assetsinward.model.master.StoreOrderAssetInward;
import order_items_mgmt.assetsinward.model.repo.cud.StoreOrderAssetInwardsCUDPublic_Repo;

@Service("storeOrderAssetInwardsCUDPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderAssetInwardsCUDPublic_Service implements I_StoreOrderAssetInwardsCUDPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderAssetInwardService.class);

	@Autowired
	private StoreOrderAssetInwardsCUDPublic_Repo storeOrderAssetInwardsCUDPublicRepo;
	
	@Autowired
	private Executor asyncExecutor;
			
	@Override
	public CompletableFuture<StoreOrderAssetInward_DTO> newStoreOrderInward(StoreOrderAssetInward_DTO storeOrderAssetInward_DTO)  
	{
		CompletableFuture<StoreOrderAssetInward_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		StoreOrderAssetInward_DTO stoDTO = null;	
		if(!storeOrderAssetInwardsCUDPublicRepo.existsById(storeOrderAssetInward_DTO.getStoreRequestSeqNo()))
		{		
		stoDTO = this.getStoreOrderAssetInward_DTO(storeOrderAssetInwardsCUDPublicRepo.save(this.setStoreOrderAssetInward(storeOrderAssetInward_DTO)));
		}
		return stoDTO;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updStoreOrderInward(StoreOrderAssetInward_DTO storeOrderAssetInward_DTO)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		StoreOrderAssetInward sto = null;	
		if(storeOrderAssetInwardsCUDPublicRepo.existsById(storeOrderAssetInward_DTO.getStoreRequestSeqNo()))
		{	
		sto = this.setStoreOrderAssetInward(storeOrderAssetInward_DTO);
		sto.setStoreRequestSeqNo(storeOrderAssetInward_DTO.getStoreRequestSeqNo()); 
		storeOrderAssetInwardsCUDPublicRepo.save(sto);
		}
		return;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Void> delStoreOrderInward(Long rno)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		
		storeOrderAssetInwardsCUDPublicRepo.deleteById(rno);
		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> delSelectStoreOrdersInward(CopyOnWriteArrayList<Long> rnos)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		
		storeOrderAssetInwardsCUDPublicRepo.deleteAllById(rnos);;
		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setFlagRequested( Character flag,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.setFlagRequested( flag,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setFlagBooked( Character flag,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.setFlagBooked( flag,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setFlagAllocated( Character flag,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.setFlagAllocated( flag,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setFlagMoved( Character flag,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.setFlagMoved( flag,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateBookStatus( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.updateBookStatus( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearBookStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.clearBookStatus( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateOKFlag( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.updateOKFlag( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearOKFlag( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.clearOKFlag( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateDoneFlag( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.updateDoneFlag( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearDoneFlag( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderAssetInwardsCUDPublicRepo.clearDoneFlag( storeReqSeqNo);		
		return;
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
		storeOrderAssetInward_DTO.setDoneflag(storeOrderAssetInward2.getDoneflag());
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
	
	private synchronized StoreOrderAssetInward setStoreOrderAssetInward(StoreOrderAssetInward_DTO storeOrderAssetInward_DTO) 
	{
		StoreOrderAssetInward storeOrderAssetInward2 = new StoreOrderAssetInward();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime fd = LocalDateTime.parse(storeOrderAssetInward_DTO.getFromDttm(), formatter);
		LocalDateTime td = LocalDateTime.parse(storeOrderAssetInward_DTO.getToDttm(), formatter);
		Timestamp fs = Timestamp.valueOf(fd);		
		Timestamp ts = Timestamp.valueOf(td);		
		storeOrderAssetInward2.setFromDttm(fs);
		storeOrderAssetInward2.setToDttm(ts);
		storeOrderAssetInward2.setDoneflag(storeOrderAssetInward_DTO.getDoneflag());
		storeOrderAssetInward2.setIsBooked(storeOrderAssetInward_DTO.getIsBooked());
		storeOrderAssetInward2.setJobWorkSeqNo(storeOrderAssetInward_DTO.getJobWorkSeqNo());
		storeOrderAssetInward2.setDoneflag(storeOrderAssetInward_DTO.getDoneflag());
		storeOrderAssetInward2.setLocationSeqNo(storeOrderAssetInward_DTO.getLocationSeqNo());
		storeOrderAssetInward2.setModeTxn(storeOrderAssetInward_DTO.getModeTxn());
		storeOrderAssetInward2.setMovedFlag(storeOrderAssetInward_DTO.getMovedFlag());
		storeOrderAssetInward2.setOkflag(storeOrderAssetInward_DTO.getOkflag());
		storeOrderAssetInward2.setFlagAllocated(storeOrderAssetInward_DTO.getFlagAllocated());
		storeOrderAssetInward2.setFlagBooked(storeOrderAssetInward_DTO.getFlagBooked());
		storeOrderAssetInward2.setFlagRequested(storeOrderAssetInward_DTO.getFlagRequested());
		storeOrderAssetInward2.setRequestedToPartySeqNo(storeOrderAssetInward_DTO.getRequestedToPartySeqNo());
		storeOrderAssetInward2.setRequestorSeqNo(storeOrderAssetInward_DTO.getRequestorSeqNo());
		storeOrderAssetInward2.setAssetSeqNo(storeOrderAssetInward_DTO.getAssetSeqNo());
		storeOrderAssetInward2.setFrLocationSeqNo(storeOrderAssetInward_DTO.getFrLocationSeqNo());
		storeOrderAssetInward2.setToLocationSeqNo(storeOrderAssetInward_DTO.getToLocationSeqNo());
		storeOrderAssetInward2.setTargetWorkSeqNo(storeOrderAssetInward_DTO.getTargetWorkSeqNo());
		return storeOrderAssetInward2;
	}

}