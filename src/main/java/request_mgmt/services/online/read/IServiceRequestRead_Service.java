package request_mgmt.services.online.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import request_mgmt.model.dto.ServiceRequestMaster_DTO;

public interface IServiceRequestRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getAllServiceRequests();
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequests(CopyOnWriteArrayList<Long> cList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByParties(CopyOnWriteArrayList<Long> pList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBySuppliers(CopyOnWriteArrayList<Long> sList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByRefList(CopyOnWriteArrayList<Long> rList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByFrLocs(CopyOnWriteArrayList<Long> lList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByToLocs(CopyOnWriteArrayList<Long> tList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsOk();
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsNotOk();
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsDone();
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsNotDone();
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsFrPartyLat(Float lat);
	public CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsFrPartyLon(Float lon);

}