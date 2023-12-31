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
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.model.master.ServiceRequestStatusDetails;
import request_mgmt.model.master.ServiceRequestStatusDetailsPK;
import request_mgmt.model.repo.cud.ServiceRequestStatusDetailsCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceRequestStatusCUDServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class ServiceRequestStatusCUD_Service implements IServiceRequestStatusCUD_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(ServiceRequest_Service.class);
	
	@Autowired
    private Executor asyncExecutor;
	
	@Autowired
	private ServiceRequestStatusDetailsCUD_Repo serviceRequestStatusCUDRepo;

    @Override
    public CompletableFuture<ServiceRequestStatusDetails_DTO> newCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqStatusDTO) 
    {
    	CompletableFuture<ServiceRequestStatusDetails_DTO> future = CompletableFuture.supplyAsync(() -> 
  		{
    	ServiceRequestStatusDetails_DTO serviceRequestStatusDTO=new ServiceRequestStatusDetails_DTO();    	
    	ServiceRequestStatusDetailsPK serviceRequestDetailsPK = new ServiceRequestStatusDetailsPK();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    	LocalDateTime dateTime = LocalDateTime.parse(serviceRequestStatusDTO.getOnDate(), formatter);
        Timestamp ts = Timestamp.valueOf(dateTime);
        serviceRequestDetailsPK.setOnDate(ts);
        serviceRequestDetailsPK.setRequestSeqNo(srvReqStatusDTO.getRequestSeqNo());
        if(!serviceRequestStatusCUDRepo.existsById(serviceRequestDetailsPK))
        {   
        serviceRequestStatusDTO = this.getServiceRequestStatusDTO(serviceRequestStatusCUDRepo.save(this.setServiceRequestStatusDetails(srvReqStatusDTO)));
        }
        return serviceRequestStatusDTO;
     	},asyncExecutor);
    	
	return future;
    }    
    
	@Override
	public CompletableFuture<Void> updCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqDTO) 
    {
	 	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime onDTTm = LocalDateTime.parse(srvReqDTO.getOnDate(), formatter);
  	  	ServiceRequestStatusDetailsPK serviceRequestDetailsPK = new ServiceRequestStatusDetailsPK();
  	  	Timestamp onTs = Timestamp.valueOf(onDTTm);
  	  	serviceRequestDetailsPK.setOnDate(onTs);
  	    serviceRequestDetailsPK.setRequestSeqNo(srvReqDTO.getRequestSeqNo());
  	    
    	if(serviceRequestStatusCUDRepo.existsById(serviceRequestDetailsPK))
    	{    	
    	serviceRequestStatusCUDRepo.save(this.setServiceRequestStatusDetails(srvReqDTO));
    	}	
     	},asyncExecutor);      	
        return future;    
    }
    
	public CompletableFuture<Void> delSelectRequestStatus(CopyOnWriteArrayList<ServiceRequestStatusDetailsPK> rList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		serviceRequestStatusCUDRepo.deleteAllById(rList);
    	},asyncExecutor);      	
        return future;
	}
	
	public CompletableFuture<Void> delSelectRequestStatusBetweenTimes(String frDtTm, String toDtTm)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
  	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
  	  	serviceRequestStatusCUDRepo.delSelectRequestStatusBetweenTimes(frTs, toTs);
    	},asyncExecutor);      	
        return future;
	}
	
	public CompletableFuture<Void> delAllRequestStatus()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		serviceRequestStatusCUDRepo.deleteAll();
    	},asyncExecutor);      	
        return future;
	}
	
	
	private synchronized ServiceRequestStatusDetails_DTO getServiceRequestStatusDTO(ServiceRequestStatusDetails servReqStatusDetails) 
	{
		ServiceRequestStatusDetails_DTO serviceRequestStatusDTO = new ServiceRequestStatusDetails_DTO();
		serviceRequestStatusDTO.setRequestSeqNo(servReqStatusDetails.getId().getRequestSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceRequestStatusDTO.setOnDate(formatter.format(servReqStatusDetails.getId().getOnDate().toLocalDateTime()));
		serviceRequestStatusDTO.setRemark(servReqStatusDetails.getRemark());
		serviceRequestStatusDTO.setStatus(servReqStatusDetails.getStatus());
		return serviceRequestStatusDTO;
		}
	
	private synchronized ServiceRequestStatusDetails setServiceRequestStatusDetails(ServiceRequestStatusDetails_DTO  serviceRequestStatusDetailsDTO) 
	{
		ServiceRequestStatusDetails serviceRequestStatusDetails	=	new	ServiceRequestStatusDetails();		
		ServiceRequestStatusDetailsPK serviceRequestDetailsPK = new ServiceRequestStatusDetailsPK();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(serviceRequestStatusDetailsDTO.getOnDate(), formatter);
	    Timestamp timestamp = Timestamp.valueOf(dateTime);
	    serviceRequestDetailsPK.setOnDate(timestamp);
	    serviceRequestDetailsPK.setRequestSeqNo(serviceRequestStatusDetailsDTO.getRequestSeqNo());
	    serviceRequestStatusDetails.setId(serviceRequestDetailsPK);		
		serviceRequestStatusDetails.setStatus(serviceRequestStatusDetailsDTO.getStatus());
	    serviceRequestStatusDetails.setRemark(serviceRequestStatusDetailsDTO.getRemark());	
		return serviceRequestStatusDetails;
	}
	
}