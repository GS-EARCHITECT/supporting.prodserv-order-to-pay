package request_mgmt.services.online.cud;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.repo.cud.ServiceRequestMasterCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceRequestCUDServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class ServiceRequestCUD_Service implements IServiceRequestCUD_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(ServiceRequest_Service.class);
	
	@Autowired
    private ServiceRequestMasterCUD_Repo serviceRequestCUDRepo;
	
	@Autowired
    private Executor asyncExecutor;
	
    @Override
	public CompletableFuture<ServiceRequestMaster_DTO> newCustServiceRequest(ServiceRequestMaster_DTO srvReqDTO) 
    {    
		CompletableFuture<ServiceRequestMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
  		{
  		ServiceRequestMaster_DTO serviceRequestDTO = new ServiceRequestMaster_DTO();  		
  		if(!serviceRequestCUDRepo.existsById(srvReqDTO.getRequestSeqNo()))
  		{	
  		ServiceRequestMaster ServiceRequestMaster = serviceRequestCUDRepo.save(this.setServiceRequestMaster(srvReqDTO));    
  		serviceRequestDTO = this.getServiceRequestMaster_DTO(ServiceRequestMaster);
  		}
  		return serviceRequestDTO;
     	},asyncExecutor);
      	
        return future;
    }

    @Override
	public CompletableFuture<Void> updCustServiceRequest(ServiceRequestMaster_DTO servReqDTO) 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	if(serviceRequestCUDRepo.existsById(servReqDTO.getRequestSeqNo()))
    	{
        ServiceRequestMaster ServiceRequestMaster = this.setServiceRequestMaster(servReqDTO);
        ServiceRequestMaster.setRequestSeqNo(servReqDTO.getRequestSeqNo());
    	serviceRequestCUDRepo.save(ServiceRequestMaster);
    	}	
     	},asyncExecutor);      	
        return future;    
    }

    public CompletableFuture<Void> delSelectRequests(CopyOnWriteArrayList<Long> cList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.deleteAllById(cList);;
    	},asyncExecutor);      	
        return future;
    }
    
	public CompletableFuture<Void> delSelectRequestsByParties(CopyOnWriteArrayList<Long> cList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsByParties(cList);
    	},asyncExecutor);      	
        return future;
		
	}
	
	public CompletableFuture<Void> delSelectRequestsBySuppliers(CopyOnWriteArrayList<Long> sList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsBySuppliers(sList);
    	},asyncExecutor);      	
        return future;
	}
	
	public CompletableFuture<Void> delSelectRequestsBetweenTimes(String frDtTm, String toDtTm)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
  	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
    	serviceRequestCUDRepo.delSelectRequestsBetweenTimes(frTs, toTs);
    	},asyncExecutor);      	
        return future;	
	}
	
	public CompletableFuture<Void> delSelectRequestsByRefList(CopyOnWriteArrayList<Long> rList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsByRefList(rList);
    	},asyncExecutor);      	
        return future;	
	}
	
    
	public CompletableFuture<Void> delSelectRequestsByFrLocs(CopyOnWriteArrayList<Long> lList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsByFrLocs(lList);
    	},asyncExecutor);      	
        return future;	
	}
	
	public CompletableFuture<Void> delSelectRequestsByToLocs(CopyOnWriteArrayList<Long> tList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsByToLocs(tList);
    	},asyncExecutor);      	
        return future;	
	}
	
	public CompletableFuture<Void> delSelectRequestsOk()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsOk();
    	},asyncExecutor);      	
        return future;	
	}
	
	public CompletableFuture<Void> delSelectRequestsNotOk()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsNotOk();
    	},asyncExecutor);      	
        return future;	
	}
	
	public CompletableFuture<Void> delSelectRequestsDone()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsDone();
    	},asyncExecutor);      	
        return future;	
	}
	
	public CompletableFuture<Void> delSelectRequestsNotDone()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsNotDone();
    	},asyncExecutor);      	
        return future;	
	}

	public CompletableFuture<Void> delSelectRequestsFrPartyLat(Float lat)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsFrPartyLat(lat);
    	},asyncExecutor);      	
        return future;	
	}
	
	public CompletableFuture<Void> delSelectRequestsFrPartyLon(Float lon)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestCUDRepo.delSelectRequestsFrPartyLon(lon);
    	},asyncExecutor);      	
        return future;	
	}
	
    @Override
    public CompletableFuture<Void> delAllServiceRequests() 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		serviceRequestCUDRepo.deleteAll();
    	},asyncExecutor);      	
        return future;    
    }
    
	private synchronized ServiceRequestMaster_DTO getServiceRequestMaster_DTO(ServiceRequestMaster servReqMaster) {
		ServiceRequestMaster_DTO serviceRequestDTO = new ServiceRequestMaster_DTO();		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceRequestDTO.setDoneFlag(serviceRequestDTO.getDoneFlag());
		serviceRequestDTO.setFrPartyLat(serviceRequestDTO.getFrPartyLat());
		serviceRequestDTO.setFrPartyLocationSeqNo(serviceRequestDTO.getFrPartyLocationSeqNo());
		serviceRequestDTO.setFrPartyLon(serviceRequestDTO.getFrPartyLon());
		serviceRequestDTO.setFrPartySeqNo(serviceRequestDTO.getFrPartySeqNo());
		serviceRequestDTO.setOkFlag(serviceRequestDTO.getOkFlag());
		serviceRequestDTO.setReferenceSeqNo(serviceRequestDTO.getReferenceSeqNo());
		serviceRequestDTO.setRemark(serviceRequestDTO.getRemark());
		serviceRequestDTO.setRequestSeqNo(serviceRequestDTO.getRequestSeqNo());
		serviceRequestDTO.setStatus(serviceRequestDTO.getStatus());
		serviceRequestDTO.setToPartyLocationSeqNo(serviceRequestDTO.getToPartyLocationSeqNo());
		serviceRequestDTO.setToPartySeqNo(serviceRequestDTO.getToPartySeqNo());
		serviceRequestDTO.setRequestDttm(formatter.format(servReqMaster.getRequestDttm().toLocalDateTime()));
		return serviceRequestDTO;
	}
		
	private synchronized ServiceRequestMaster setServiceRequestMaster(ServiceRequestMaster_DTO serviceRequestDTO) 
	{
		ServiceRequestMaster serviceRequestMaster		=	new	ServiceRequestMaster();				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(serviceRequestDTO.getRequestDttm(), formatter);
		Timestamp ts = Timestamp.valueOf(dateTime);		
		serviceRequestMaster.setRequestDttm(ts);
		serviceRequestMaster.setDoneFlag(serviceRequestDTO.getDoneFlag());
		serviceRequestMaster.setFrPartyLat(serviceRequestDTO.getFrPartyLat());
		serviceRequestMaster.setFrPartyLocationSeqNo(serviceRequestDTO.getFrPartyLocationSeqNo());
		serviceRequestMaster.setFrPartyLon(serviceRequestDTO.getFrPartyLon());
		serviceRequestMaster.setFrPartySeqNo(serviceRequestDTO.getFrPartySeqNo());
		serviceRequestMaster.setOkFlag(serviceRequestDTO.getOkFlag());
		serviceRequestMaster.setReferenceSeqNo(serviceRequestDTO.getReferenceSeqNo());
		serviceRequestMaster.setRemark(serviceRequestDTO.getRemark());		
		serviceRequestMaster.setStatus(serviceRequestDTO.getStatus());
		serviceRequestMaster.setToPartyLocationSeqNo(serviceRequestDTO.getToPartyLocationSeqNo());
		serviceRequestMaster.setToPartySeqNo(serviceRequestDTO.getToPartySeqNo());		
		return serviceRequestMaster;
	}

	
}