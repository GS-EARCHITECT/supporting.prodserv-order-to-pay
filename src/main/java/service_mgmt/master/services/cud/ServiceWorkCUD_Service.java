package service_mgmt.master.services.cud;

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
import service_mgmt.master.model.dto.ServiceWorkMaster_DTO;
import service_mgmt.master.model.master.ServiceWorkMaster;
import service_mgmt.master.model.repo.cud.ServiceWorkMasterCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceWorkCUDServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class ServiceWorkCUD_Service implements IServiceWorkCUD_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(ServiceWork_Service.class);
	
	@Autowired
    private ServiceWorkMasterCUD_Repo serviceWorkCUDRepo;
	
	@Autowired
    private Executor asyncExecutor;
	
    @Override
	public CompletableFuture<ServiceWorkMaster_DTO> newServiceWork(ServiceWorkMaster_DTO srvwDTO) 
    {    
		CompletableFuture<ServiceWorkMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
  		{
  		ServiceWorkMaster_DTO serviceWorkDTO = new ServiceWorkMaster_DTO();  		
  		if(!serviceWorkCUDRepo.existsById(srvwDTO.getServiceWorkSeqNo()))
  		{	
  		ServiceWorkMaster serviceWorkMaster = serviceWorkCUDRepo.save(this.setServiceWorkMaster(srvwDTO));    
  		serviceWorkDTO = this.getServiceWorkMaster_DTO(serviceWorkMaster);
  		}
  		return serviceWorkDTO;
     	},asyncExecutor);
      	
        return future;
    }

    @Override
	public CompletableFuture<Void> updServiceWork(ServiceWorkMaster_DTO servwDTO) 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	if(serviceWorkCUDRepo.existsById(servwDTO.getServiceWorkSeqNo()))
    	{
        ServiceWorkMaster serviceWorkMaster = this.setServiceWorkMaster(servwDTO);
        serviceWorkMaster.setServiceWorkSeqNo(servwDTO.getServiceWorkSeqNo());
    	serviceWorkCUDRepo.save(serviceWorkMaster);
    	}	
     	},asyncExecutor);      	
        return future;    
    }

    public CompletableFuture<Void> delSelectWorks(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceWorkCUDRepo.delSelectWorks(sList);
    	},asyncExecutor);      	
        return future;
    }
    
	public CompletableFuture<Void> delSelectWorksByParties(CopyOnWriteArrayList<Long> pList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceWorkCUDRepo.delSelectWorksByParties(pList);
    	},asyncExecutor);      	
        return future;
		
	}
	
	public CompletableFuture<Void> delSelectWorksByBookings(CopyOnWriteArrayList<Long> bList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceWorkCUDRepo.delSelectWorksByBookings(bList);
    	},asyncExecutor);      	
        return future;
	}
	
	public CompletableFuture<Void> delSelectWorksByRequests(CopyOnWriteArrayList<Long> rList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceWorkCUDRepo.delSelectWorksByRequests(rList);
    	},asyncExecutor);      	
        return future;	
	}
	
	public CompletableFuture<Void> delSelectWorksByServices(CopyOnWriteArrayList<Long> sList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceWorkCUDRepo.delSelectWorksByServices(sList);
    	},asyncExecutor);      	
        return future;	
	}

	public CompletableFuture<Void> delSelectWorksByCreatedBy(CopyOnWriteArrayList<Long> cList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceWorkCUDRepo.delSelectWorksByServices(cList);
    	},asyncExecutor);      	
        return future;	
	}

	
	public CompletableFuture<Void> delSelectWorksBetweenTimes(String frDtTm, String toDtTm)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
  	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
    	serviceWorkCUDRepo.delSelectWorksBetweenTimes(frTs, toTs);
    	},asyncExecutor);      	
        return future;	
	}
	
    
    @Override
	public CompletableFuture<Void> delAllServiceWorks() 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		serviceWorkCUDRepo.deleteAll();
    	},asyncExecutor);      	
        return future;    
    }
    
    private synchronized CopyOnWriteArrayList<ServiceWorkMaster_DTO> getServWorkDtos(CopyOnWriteArrayList<ServiceWorkMaster> servReqMasters) 
	{
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkDTOs = new CopyOnWriteArrayList<ServiceWorkMaster_DTO>(); 
		for(int i=0; i < servReqMasters.size();i++)
		{		
		serviceWorkDTOs.add(this.getServiceWorkMaster_DTO(servReqMasters.get(i)));
		}		
		return serviceWorkDTOs;
	}
	
	private synchronized ServiceWorkMaster_DTO getServiceWorkMaster_DTO(ServiceWorkMaster servwMaster) 
	{
		ServiceWorkMaster_DTO serviceWorkDTO = new ServiceWorkMaster_DTO();		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceWorkDTO.setOnDate(formatter.format(servwMaster.getOnDate().toLocalDateTime()));
		serviceWorkDTO.setAutoAllocStatus(servwMaster.getAutoAllocStatus());
		serviceWorkDTO.setCreatedBy(servwMaster.getCreatedBy());
		serviceWorkDTO.setPartySeqNo(servwMaster.getPartySeqNo());
		serviceWorkDTO.setBillingCurrencySeqNo(servwMaster.getBillingCurrencySeqNo());
		serviceWorkDTO.setBookingSeqNo(servwMaster.getBookingSeqNo());
		serviceWorkDTO.setJobAllocStatus(servwMaster.getJobAllocStatus());
		serviceWorkDTO.setMembershipSeqNo(servwMaster.getMembershipSeqNo());
		serviceWorkDTO.setParentServiceWorkSeqNo(servwMaster.getParentServiceWorkSeqNo());
		serviceWorkDTO.setRequestSeqNo(servwMaster.getRequestSeqNo());
		serviceWorkDTO.setResAllocStatus(servwMaster.getResAllocStatus());
		serviceWorkDTO.setResDirectIndirectFlag(servwMaster.getResDirectIndirectFlag());
		serviceWorkDTO.setServiceSeqNo(servwMaster.getServiceSeqNo());
		serviceWorkDTO.setServiceWorkSeqNo(servwMaster.getServiceWorkSeqNo());
		serviceWorkDTO.setToBill(servwMaster.getToBill());
		serviceWorkDTO.setStatus(servwMaster.getStatus());
		serviceWorkDTO.setRemark(servwMaster.getRemark());		
		return serviceWorkDTO;
	}
		
	private synchronized ServiceWorkMaster setServiceWorkMaster(ServiceWorkMaster_DTO sWDTO) 
	{
		ServiceWorkMaster serviceWorkMaster		=	new	ServiceWorkMaster();				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(sWDTO.getOnDate(), formatter);
		Timestamp ts = Timestamp.valueOf(dateTime);		
		serviceWorkMaster.setOnDate(ts);
		serviceWorkMaster.setAutoAllocStatus(sWDTO.getAutoAllocStatus());
		serviceWorkMaster.setCreatedBy(sWDTO.getCreatedBy());
		serviceWorkMaster.setPartySeqNo(sWDTO.getPartySeqNo());
		serviceWorkMaster.setBillingCurrencySeqNo(sWDTO.getBillingCurrencySeqNo());
		serviceWorkMaster.setBookingSeqNo(sWDTO.getBookingSeqNo());
		serviceWorkMaster.setJobAllocStatus(sWDTO.getJobAllocStatus());
		serviceWorkMaster.setMembershipSeqNo(sWDTO.getMembershipSeqNo());
		serviceWorkMaster.setParentServiceWorkSeqNo(sWDTO.getParentServiceWorkSeqNo());
		serviceWorkMaster.setRequestSeqNo(sWDTO.getRequestSeqNo());
		serviceWorkMaster.setResAllocStatus(sWDTO.getResAllocStatus());
		serviceWorkMaster.setResDirectIndirectFlag(sWDTO.getResDirectIndirectFlag());
		serviceWorkMaster.setServiceSeqNo(sWDTO.getServiceSeqNo());
		serviceWorkMaster.setToBill(sWDTO.getToBill());
		serviceWorkMaster.setStatus(sWDTO.getStatus());
		serviceWorkMaster.setRemark(sWDTO.getRemark());		
		return serviceWorkMaster;
		}
		
}