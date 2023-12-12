package order_items_mgmt.resourcesoutward.services.cud;

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
import order_items_mgmt.resourcesoutward.model.repo.cud.StoreOrderResourceOutwardsCUDPublic_Repo;

@Service("storeOrderResourceOutwardsCUDPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class StoreOrderResourceOutwardsCUDPublic_Service implements I_StoreOrderResourceOutwardsCUDPublic_Service 
{

//	private static final Logger logger = LoggerFactory.getLogger(StoreOrderResourceOutwardService.class);

	@Autowired
	private StoreOrderResourceOutwardsCUDPublic_Repo storeOrderResourceOutwardsCUDPublicRepo;
	
	@Autowired
	private Executor asyncExecutor;
			
	@Override
	public CompletableFuture<StoreOrderResourceOutward_DTO> newStoreOrderOutward(StoreOrderResourceOutward_DTO storeOrderResourceOutward_DTO)  
	{
		CompletableFuture<StoreOrderResourceOutward_DTO> future = CompletableFuture.supplyAsync(() -> 
		{
		StoreOrderResourceOutward_DTO stoDTO = null;	
		if(!storeOrderResourceOutwardsCUDPublicRepo.existsById(storeOrderResourceOutward_DTO.getStoreRequestSeqNo()))
		{		
		stoDTO = this.getStoreOrderResourceOutward_DTO(storeOrderResourceOutwardsCUDPublicRepo.save(this.setStoreOrderResourceOutward(storeOrderResourceOutward_DTO)));
		}
		return stoDTO;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updStoreOrderOutward(StoreOrderResourceOutward_DTO storeOrderResourceOutward_DTO)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		StoreOrderResourceOutward sto = null;	
		if(storeOrderResourceOutwardsCUDPublicRepo.existsById(storeOrderResourceOutward_DTO.getStoreRequestSeqNo()))
		{	
		sto = this.setStoreOrderResourceOutward(storeOrderResourceOutward_DTO);
		sto.setStoreRequestSeqNo(storeOrderResourceOutward_DTO.getStoreRequestSeqNo()); 
		storeOrderResourceOutwardsCUDPublicRepo.save(sto);
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
		
		storeOrderResourceOutwardsCUDPublicRepo.deleteById(rno);
		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> delSelectStoreOrdersOutward(CopyOnWriteArrayList<Long> rnos)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		
		storeOrderResourceOutwardsCUDPublicRepo.deleteAllById(rnos);;
		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> addQtyRequested( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.addQtyRequested( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> subQtyRequested( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.subQtyRequested( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setQtyRequested( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.setQtyRequested( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> addQtyBooked( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.addQtyBooked( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> subQtyBooked( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.subQtyBooked( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}
	
	@Override
	public CompletableFuture<Void> setQtyBooked( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.setQtyBooked( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> addQtyAllocated( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.addQtyAllocated( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> subQtyAllocated( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.subQtyAllocated( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setQtyAllocated( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.setQtyAllocated( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> addQtyMoved( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.addQtyMoved( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> subQtyMoved( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.subQtyMoved( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> setQtyMoved( Float qty,  Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.setQtyMoved( qty,  storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateBookStatus( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.updateBookStatus( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearBookStatus( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.clearBookStatus( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateOKFlag( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.updateOKFlag( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearOKFlag( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.clearOKFlag( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> updateDoneFlag( Long storeReqSeqNo,  Character st)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.updateDoneFlag( storeReqSeqNo,  st);		
		return;
		},asyncExecutor);

	return future;

	}

	@Override
	public CompletableFuture<Void> clearDoneFlag( Long storeReqSeqNo)  
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
		storeOrderResourceOutwardsCUDPublicRepo.clearDoneFlag( storeReqSeqNo);		
		return;
		},asyncExecutor);

	return future;

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
	
	private synchronized StoreOrderResourceOutward setStoreOrderResourceOutward(StoreOrderResourceOutward_DTO storeOrderResourceOutward_DTO) 
	{
		StoreOrderResourceOutward storeOrderResourceOutward = new StoreOrderResourceOutward();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime fd = LocalDateTime.parse(storeOrderResourceOutward_DTO.getFromDttm(), formatter);
		LocalDateTime td = LocalDateTime.parse(storeOrderResourceOutward_DTO.getToDttm(), formatter);
		Timestamp fs = Timestamp.valueOf(fd);		
		Timestamp ts = Timestamp.valueOf(td);		
		storeOrderResourceOutward.setFromDttm(fs);
		storeOrderResourceOutward.setToDttm(ts);
		storeOrderResourceOutward.setDoneflag(storeOrderResourceOutward_DTO.getDoneflag());
		storeOrderResourceOutward.setIsBooked(storeOrderResourceOutward_DTO.getIsBooked());
		storeOrderResourceOutward.setJobWorkSeqNo(storeOrderResourceOutward_DTO.getJobWorkSeqNo());
		storeOrderResourceOutward.setDoneflag(storeOrderResourceOutward_DTO.getDoneflag());
		storeOrderResourceOutward.setLocationSeqNo(storeOrderResourceOutward_DTO.getLocationSeqNo());
		storeOrderResourceOutward.setModeTxn(storeOrderResourceOutward_DTO.getModeTxn());
		storeOrderResourceOutward.setMovedQty(storeOrderResourceOutward_DTO.getMovedQty());
		storeOrderResourceOutward.setOkflag(storeOrderResourceOutward_DTO.getOkflag());
		storeOrderResourceOutward.setQtyAllocated(storeOrderResourceOutward_DTO.getQtyAllocated());
		storeOrderResourceOutward.setQtyBooked(storeOrderResourceOutward_DTO.getQtyBooked());
		storeOrderResourceOutward.setQtyRequested(storeOrderResourceOutward_DTO.getQtyRequested());
		storeOrderResourceOutward.setQtyUnitSeqNo(storeOrderResourceOutward_DTO.getQtyUnitSeqNo());
		storeOrderResourceOutward.setRequestedToPartySeqNo(storeOrderResourceOutward_DTO.getRequestedToPartySeqNo());
		storeOrderResourceOutward.setRequestorSeqNo(storeOrderResourceOutward_DTO.getRequestorSeqNo());
		storeOrderResourceOutward.setResourceSeqNo(storeOrderResourceOutward_DTO.getResourceSeqNo());
		storeOrderResourceOutward.setFrLocationSeqNo(storeOrderResourceOutward_DTO.getFrLocationSeqNo());
		storeOrderResourceOutward.setToLocationSeqNo(storeOrderResourceOutward_DTO.getToLocationSeqNo());
		storeOrderResourceOutward.setTargetWorkSeqNo(storeOrderResourceOutward_DTO.getTargetWorkSeqNo());
		return storeOrderResourceOutward;
	}

}