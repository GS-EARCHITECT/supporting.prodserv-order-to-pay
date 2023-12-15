package order_items_mgmt.prod_asset.resourcesinward.services.cud;

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
import common.model.dto.*;
import common.model.master.*;
import order_items_mgmt.prod_asset.resourcesinward.model.repo.cud.*;

@Service("storeOrderResourceInwardsCUDPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderResourceInwardsCUDPublic_Service implements I_StoreOrderResourceInwardsCUDPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderResourceInwardService.class);

	@Autowired
	private StoreOrderResourceInwardsCUDPublic_Repo storeOrderResourceInwardsCUDPublicRepo;
	
	@Autowired
	private Executor asyncExecutor;
			
	@Override
	public CompletableFuture<StoreOrderResourceInward_DTO> newStoreOrderInward(StoreOrderResourceInward_DTO storeOrderResourceInward_DTO)  
	{
		CompletableFuture<StoreOrderResourceInward_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		StoreOrderResourceInward_DTO stoDTO = null;	
		if(!storeOrderResourceInwardsCUDPublicRepo.existsById(storeOrderResourceInward_DTO.getStoreRequestSeqNo()))
		{		
		stoDTO = this.getStoreOrderResourceInward_DTO(storeOrderResourceInwardsCUDPublicRepo.save(this.setStoreOrderResourceInward(storeOrderResourceInward_DTO)));
		}
		return stoDTO;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updStoreOrderInward(StoreOrderResourceInward_DTO storeOrderResourceInward_DTO)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		StoreOrderResourceInward sto = null;	
		if(storeOrderResourceInwardsCUDPublicRepo.existsById(storeOrderResourceInward_DTO.getStoreRequestSeqNo()))
		{	
		sto = this.setStoreOrderResourceInward(storeOrderResourceInward_DTO);
		sto.setStoreRequestSeqNo(storeOrderResourceInward_DTO.getStoreRequestSeqNo()); 
		storeOrderResourceInwardsCUDPublicRepo.save(sto);
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
		
		storeOrderResourceInwardsCUDPublicRepo.deleteById(rno);
		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> delSelectStoreOrdersInward(CopyOnWriteArrayList<Long> rnos)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		
		storeOrderResourceInwardsCUDPublicRepo.deleteAllById(rnos);;
		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> addQtyRequested( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.addQtyRequested( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> subQtyRequested( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.subQtyRequested( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setQtyRequested( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.setQtyRequested( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> addQtyBooked( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.addQtyBooked( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> subQtyBooked( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.subQtyBooked( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Void> setQtyBooked( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.setQtyBooked( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> addQtyAllocated( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.addQtyAllocated( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> subQtyAllocated( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.subQtyAllocated( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setQtyAllocated( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.setQtyAllocated( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> addQtyMoved( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.addQtyMoved( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> subQtyMoved( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.subQtyMoved( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setQtyMoved( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.setQtyMoved( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateBookStatus( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.updateBookStatus( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearBookStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.clearBookStatus( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateOKFlag( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.updateOKFlag( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearOKFlag( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.clearOKFlag( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateDoneFlag( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.updateDoneFlag( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearDoneFlag( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceInwardsCUDPublicRepo.clearDoneFlag( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

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
		storeOrderResourceInward_DTO.setModeTxnSeqNo(storeOrderResourceInward.getModeTxn());
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
		storeOrderResourceInward_DTO.setFrLocationSeqNo(storeOrderResourceInward.getFrLocationSeqN());
		storeOrderResourceInward_DTO.setToLocationSeqNo(storeOrderResourceInward.getToLocationSeqNo());
		storeOrderResourceInward_DTO.setTargetWorkSeqNo(storeOrderResourceInward.getTargetWorkSeqNo());
		return storeOrderResourceInward_DTO;
	}
	
	private synchronized StoreOrderResourceInward setStoreOrderResourceInward(StoreOrderResourceInward_DTO storeOrderResourceInward_DTO) 
	{
		StoreOrderResourceInward storeOrderResourceInward = new StoreOrderResourceInward();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime fd = LocalDateTime.parse(storeOrderResourceInward_DTO.getFromDttm(), formatter);
		LocalDateTime td = LocalDateTime.parse(storeOrderResourceInward_DTO.getToDttm(), formatter);
		Timestamp fs = Timestamp.valueOf(fd);		
		Timestamp ts = Timestamp.valueOf(td);		
		storeOrderResourceInward.setFromDttm(fs);
		storeOrderResourceInward.setToDttm(ts);
		storeOrderResourceInward.setDoneflag(storeOrderResourceInward_DTO.getDoneflag());
		storeOrderResourceInward.setIsBooked(storeOrderResourceInward_DTO.getIsBooked());
		storeOrderResourceInward.setJobWorkSeqNo(storeOrderResourceInward_DTO.getJobWorkSeqNo());
		storeOrderResourceInward.setDoneflag(storeOrderResourceInward_DTO.getDoneflag());
		storeOrderResourceInward.setLocationSeqNo(storeOrderResourceInward_DTO.getLocationSeqNo());
		storeOrderResourceInward.setModeTxn(storeOrderResourceInward_DTO.getModeTxnSeqNo());
		storeOrderResourceInward.setMovedQty(storeOrderResourceInward_DTO.getMovedQty());
		storeOrderResourceInward.setOkflag(storeOrderResourceInward_DTO.getOkflag());
		storeOrderResourceInward.setQtyAllocated(storeOrderResourceInward_DTO.getQtyAllocated());
		storeOrderResourceInward.setQtyBooked(storeOrderResourceInward_DTO.getQtyBooked());
		storeOrderResourceInward.setQtyRequested(storeOrderResourceInward_DTO.getQtyRequested());
		storeOrderResourceInward.setQtyUnitSeqNo(storeOrderResourceInward_DTO.getQtyUnitSeqNo());
		storeOrderResourceInward.setRequestedToPartySeqNo(storeOrderResourceInward_DTO.getRequestedToPartySeqNo());
		storeOrderResourceInward.setRequestorSeqNo(storeOrderResourceInward_DTO.getRequestorSeqNo());
		storeOrderResourceInward.setResourceSeqNo(storeOrderResourceInward_DTO.getResourceSeqNo());
		storeOrderResourceInward.setFrLocationSeqN(storeOrderResourceInward_DTO.getFrLocationSeqNo());
		storeOrderResourceInward.setToLocationSeqNo(storeOrderResourceInward_DTO.getToLocationSeqNo());
		storeOrderResourceInward.setTargetWorkSeqNo(storeOrderResourceInward_DTO.getTargetWorkSeqNo());
		return storeOrderResourceInward;
	}

}