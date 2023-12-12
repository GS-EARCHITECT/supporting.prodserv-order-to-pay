package request_mgmt.services.online.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.model.master.ServiceRequestStatusDetailsPK;

public interface IServiceRequestStatusCUD_Service 
{
	public CompletableFuture<ServiceRequestStatusDetails_DTO> newCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqStatusDTO);
	public CompletableFuture<Void> updCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqDTO);
	public CompletableFuture<Void> delSelectRequestStatus(CopyOnWriteArrayList<ServiceRequestStatusDetailsPK> rList);
	public CompletableFuture<Void> delSelectRequestStatusBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<Void> delAllRequestStatus();		
}