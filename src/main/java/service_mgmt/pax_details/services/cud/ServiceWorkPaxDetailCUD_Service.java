package service_mgmt.pax_details.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service_mgmt.pax_details.model.dto.ServiceWorkPaxDetail_DTO;
import service_mgmt.pax_details.model.master.ServiceWorkPaxDetail;
import service_mgmt.pax_details.model.master.ServiceWorkPaxDetailPK;
import service_mgmt.pax_details.model.repo.cud.ServiceWorkPaxDetailsCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceWorkPaxDetailsCUDServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class ServiceWorkPaxDetailCUD_Service implements IServiceWorkPaxDetailsCUD_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(ServiceWorkPaxDetail_Service.class);
	
	@Autowired
    private ServiceWorkPaxDetailsCUD_Repo serviceWorkPaxDetailsCUDRepo;
	
	@Autowired
    private Executor asyncExecutor;
	
	@Override
	public CompletableFuture<ServiceWorkPaxDetail_DTO> newServiceWorkPaxDetail(ServiceWorkPaxDetail_DTO swDTO) 
    {    
		CompletableFuture<ServiceWorkPaxDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
  		{
  		ServiceWorkPaxDetailPK serviceWorkPaxDetailPK = new ServiceWorkPaxDetailPK();
  		serviceWorkPaxDetailPK.setPartySeqNo(swDTO.getPartySeqNo());
  		serviceWorkPaxDetailPK.setServiceWorkSeqNo(swDTO.getServiceWorkSeqNo());
  		ServiceWorkPaxDetail_DTO serviceWorkPaxDetailsDTO = new ServiceWorkPaxDetail_DTO();  		
  		if(!serviceWorkPaxDetailsCUDRepo.existsById(serviceWorkPaxDetailPK))
  		{	
  		ServiceWorkPaxDetail serviceWorkPaxDetails = serviceWorkPaxDetailsCUDRepo.save(this.setServiceWorkPaxDetail(swDTO));    
  		serviceWorkPaxDetailsDTO = this.getServiceWorkPaxDetail_DTO(serviceWorkPaxDetails);
  		}
  		return serviceWorkPaxDetailsDTO;
     	},asyncExecutor);
      	
        return future;
    }

    @Override
	public CompletableFuture<Void> updServiceWorkPaxDetail(ServiceWorkPaxDetail_DTO swDTO) 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
 	  	ServiceWorkPaxDetailPK serviceWorkPaxDetailPK = new ServiceWorkPaxDetailPK();
  	  	serviceWorkPaxDetailPK.setPartySeqNo(swDTO.getPartySeqNo());
  	  	serviceWorkPaxDetailPK.setServiceWorkSeqNo(swDTO.getServiceWorkSeqNo());

    	if(serviceWorkPaxDetailsCUDRepo.existsById(serviceWorkPaxDetailPK))
    	{
        ServiceWorkPaxDetail serviceWorkPaxDetails = this.setServiceWorkPaxDetail(swDTO);        
    	serviceWorkPaxDetailsCUDRepo.save(serviceWorkPaxDetails);
    	}	
     	},asyncExecutor);      	
        return future;    
    }

    public CompletableFuture<Void> delSelectWorkPaxs(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceWorkPaxDetailsCUDRepo.delSelectWorkPaxs(sList);
    	},asyncExecutor);      	
        return future;
    }
    
	@Override
	public CompletableFuture<Void> delAllWorkPaxDetails() 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		serviceWorkPaxDetailsCUDRepo.deleteAll();
    	},asyncExecutor);      	
        return future;    
    }
    
    private synchronized CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO> getServWorkPaxDetailsDtos(CopyOnWriteArrayList<ServiceWorkPaxDetail> servReqMasters) 
	{
		CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO> serviceWorkPaxDetailsDTOs = new CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>(); 
		for(int i=0; i < servReqMasters.size();i++)
		{		
		serviceWorkPaxDetailsDTOs.add(this.getServiceWorkPaxDetail_DTO(servReqMasters.get(i)));
		}		
		return serviceWorkPaxDetailsDTOs;
	}
	
	private synchronized ServiceWorkPaxDetail_DTO getServiceWorkPaxDetail_DTO(ServiceWorkPaxDetail servReqMaster) 
	{
		ServiceWorkPaxDetail_DTO serviceWorkPaxDetailsDTO = new ServiceWorkPaxDetail_DTO();
		serviceWorkPaxDetailsDTO.setPartySeqNo(servReqMaster.getId().getPartySeqNo());
		serviceWorkPaxDetailsDTO.setServiceWorkSeqNo(servReqMaster.getId().getServiceWorkSeqNo());
		serviceWorkPaxDetailsDTO.setStatus(servReqMaster.getStatus());
		serviceWorkPaxDetailsDTO.setRemark(servReqMaster.getRemark());		
		return serviceWorkPaxDetailsDTO;
		}
		
	private synchronized ServiceWorkPaxDetail setServiceWorkPaxDetail(ServiceWorkPaxDetail_DTO serviceWorkPaxDetailsDTO) 
	{
		ServiceWorkPaxDetail serviceWorkPaxDetails		=	new	ServiceWorkPaxDetail();				
		ServiceWorkPaxDetailPK serviceWorkPaxDetailPK = new ServiceWorkPaxDetailPK();
  	  	serviceWorkPaxDetailPK.setPartySeqNo(serviceWorkPaxDetailsDTO.getPartySeqNo());
  	  	serviceWorkPaxDetailPK.setServiceWorkSeqNo(serviceWorkPaxDetailsDTO.getServiceWorkSeqNo());
		serviceWorkPaxDetails.setId(serviceWorkPaxDetailPK);
		serviceWorkPaxDetails.setRemark(serviceWorkPaxDetailsDTO.getRemark());
		serviceWorkPaxDetails.setStatus(serviceWorkPaxDetailsDTO.getStatus());
		return serviceWorkPaxDetails;
	}
	
}