package request_mgmt.services.batch;

import java.util.concurrent.CompletableFuture;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.master.ServiceRequestMaster_SDTO;

public interface IClientBatchRequest_Service 
{
public CompletableFuture<ServiceRequestMaster_SDTO> getCRequestSDTO(ServiceRequestMaster_SDTO srvReqSDTO);
public CompletableFuture<ServiceRequestMaster_SDTO> getCRequestSDTOFromMaster(ServiceRequestMaster srvReq);
}