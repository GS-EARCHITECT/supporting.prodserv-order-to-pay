package request_mgmt.services.online.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.model.master.ServiceRequestStatusDetailsPK;

public interface IServiceRequestStatusRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatus(CopyOnWriteArrayList<ServiceRequestStatusDetailsPK> rList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatusBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> getAllServiceStatusDetails();
}