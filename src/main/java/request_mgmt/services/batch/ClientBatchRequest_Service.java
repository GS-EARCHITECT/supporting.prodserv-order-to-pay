package request_mgmt.services.batch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.master.ServiceRequestMaster_SDTO;
import request_mgmt.model.repo.cud.ServiceRequestMasterCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("cRequestBatchServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class ClientBatchRequest_Service implements IClientBatchRequest_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(ServiceRequest_Service.class);
	
	@Autowired
    private Executor asyncExecutor;
	
	@Autowired
    private ServiceRequestMasterCUD_Repo serviceRequestCUDRepo;
		
    @Override
	public CompletableFuture<ServiceRequestMaster_SDTO> getCRequestSDTO(ServiceRequestMaster_SDTO srvReqSDTO) 
    {    
		CompletableFuture<ServiceRequestMaster_SDTO> future = CompletableFuture.supplyAsync(() -> 
  		{
  		ServiceRequestMaster_SDTO serviceRequestDTO = getServiceRequestMaster_SDTO(serviceRequestCUDRepo.save(setServiceRequestMaster(srvReqSDTO)));
  		return serviceRequestDTO;
     	},asyncExecutor);
      	
        return future;
    }
	
	@Override
	public CompletableFuture<ServiceRequestMaster_SDTO> getCRequestSDTOFromMaster(ServiceRequestMaster srvReq) 
    {    
		CompletableFuture<ServiceRequestMaster_SDTO> future = CompletableFuture.supplyAsync(() -> 
  		{
  		ServiceRequestMaster_SDTO serviceRequestDTO = getServiceRequestMaster_SDTO(srvReq);
  		return serviceRequestDTO;
     	},asyncExecutor);
      	
        return future;
    }
    
	private synchronized ServiceRequestMaster_SDTO getServiceRequestMaster_SDTO(ServiceRequestMaster srvReqMaster)
	{
		ServiceRequestMaster_SDTO serviceRequestDTO = new ServiceRequestMaster_SDTO();
		serviceRequestDTO.setRequestSeqNo(serviceRequestDTO.getRequestSeqNo());
		serviceRequestDTO.setDoneFlag(serviceRequestDTO.getDoneFlag());
		serviceRequestDTO.setFrPartyLat(serviceRequestDTO.getFrPartyLat());
		serviceRequestDTO.setFrPartyLocationSeqNo(serviceRequestDTO.getFrPartyLocationSeqNo());
		serviceRequestDTO.setFrPartyLon(serviceRequestDTO.getFrPartyLon());
		serviceRequestDTO.setFrPartySeqNo(serviceRequestDTO.getFrPartySeqNo());
		serviceRequestDTO.setOkFlag(serviceRequestDTO.getOkFlag());
		serviceRequestDTO.setReferenceSeqNo(serviceRequestDTO.getReferenceSeqNo());
		serviceRequestDTO.setRemark(serviceRequestDTO.getRemark());		
		serviceRequestDTO.setStatus(serviceRequestDTO.getStatus());
		serviceRequestDTO.setToPartyLocationSeqNo(serviceRequestDTO.getToPartyLocationSeqNo());
		serviceRequestDTO.setToPartySeqNo(serviceRequestDTO.getToPartySeqNo());
		serviceRequestDTO.setRequestDttm(serviceRequestDTO.getRequestDttm());
		return serviceRequestDTO;
	}
		
	private synchronized ServiceRequestMaster setServiceRequestMaster(ServiceRequestMaster_SDTO serviceRequestSDTO) 
	{
		ServiceRequestMaster serviceRequestMaster		=	new	ServiceRequestMaster();				
		serviceRequestMaster.setRequestDttm(serviceRequestSDTO.getRequestDttm());
		serviceRequestMaster.setDoneFlag(serviceRequestSDTO.getDoneFlag());
		serviceRequestMaster.setFrPartyLat(serviceRequestSDTO.getFrPartyLat());
		serviceRequestMaster.setFrPartyLocationSeqNo(serviceRequestSDTO.getFrPartyLocationSeqNo());
		serviceRequestMaster.setFrPartyLon(serviceRequestSDTO.getFrPartyLon());
		serviceRequestMaster.setFrPartySeqNo(serviceRequestSDTO.getFrPartySeqNo());
		serviceRequestMaster.setOkFlag(serviceRequestSDTO.getOkFlag());
		serviceRequestMaster.setReferenceSeqNo(serviceRequestSDTO.getReferenceSeqNo());
		serviceRequestMaster.setRemark(serviceRequestSDTO.getRemark());		
		serviceRequestMaster.setStatus(serviceRequestSDTO.getStatus());
		serviceRequestMaster.setToPartyLocationSeqNo(serviceRequestSDTO.getToPartyLocationSeqNo());
		serviceRequestMaster.setToPartySeqNo(serviceRequestSDTO.getToPartySeqNo());		
		return serviceRequestMaster;
	}
	
}