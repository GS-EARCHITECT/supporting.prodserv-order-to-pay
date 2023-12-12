package request_mgmt.services.online.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import request_mgmt.model.dto.ServiceRequestMaster_DTO;

public interface IServiceRequestCUD_Service 
{
	public CompletableFuture<ServiceRequestMaster_DTO> newCustServiceRequest(ServiceRequestMaster_DTO srvReqDTO);
	public CompletableFuture<Void> updCustServiceRequest(ServiceRequestMaster_DTO servReqDTO);
	public CompletableFuture<Void> delSelectRequests(CopyOnWriteArrayList<Long> cList);
	public CompletableFuture<Void> delSelectRequestsByParties(CopyOnWriteArrayList<Long> pList);
	public CompletableFuture<Void> delSelectRequestsBySuppliers(CopyOnWriteArrayList<Long> sList);
	public CompletableFuture<Void> delSelectRequestsBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<Void> delSelectRequestsByRefList(CopyOnWriteArrayList<Long> rList);
	public CompletableFuture<Void> delSelectRequestsByFrLocs(CopyOnWriteArrayList<Long> lList);
	public CompletableFuture<Void> delSelectRequestsByToLocs(CopyOnWriteArrayList<Long> tList);
	public CompletableFuture<Void> delSelectRequestsOk();
	public CompletableFuture<Void> delSelectRequestsNotOk();
	public CompletableFuture<Void> delSelectRequestsDone();
	public CompletableFuture<Void> delSelectRequestsNotDone();
	public CompletableFuture<Void> delSelectRequestsFrPartyLat(Float lat);
	public CompletableFuture<Void> delSelectRequestsFrPartyLon(Float lon);
	public CompletableFuture<Void> delAllServiceRequests();	
}