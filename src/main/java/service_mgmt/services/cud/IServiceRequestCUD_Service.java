package service_mgmt.services.cud;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;

public interface IServiceRequestCUD_Service 
{
	public CompletableFuture<ServiceRequestMaster_DTO> newCustServiceRequest(ServiceRequestMaster_DTO srvReqDTO);
	public CompletableFuture<Void> updCustServiceRequest(ServiceRequestMaster_DTO servReqDTO);
	public CompletableFuture<Void> delSelectRequests(ArrayList<Long> cList);
	public CompletableFuture<Void> delSelectRequestsByCompanies(ArrayList<Long> cList);
	public CompletableFuture<Void> delSelectRequestsByPeople(ArrayList<Long> pList);
	public CompletableFuture<Void> delSelectRequestsBySuppliers(ArrayList<Long> sList);
	public CompletableFuture<Void> delSelectRequestsBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<Void> delAllRequests();
	public CompletableFuture<ServiceRequestStatusDetails_DTO> newCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqStatusDTO);
	public CompletableFuture<Void> updCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqDTO);
	public CompletableFuture<Void> delSelectRequestStatus(ArrayList<Long> rList);
	public CompletableFuture<Void> delSelectRequestStatusBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<Void> delAllRequestStatus();		
}