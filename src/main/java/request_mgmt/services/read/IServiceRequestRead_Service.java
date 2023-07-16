package request_mgmt.services.read;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;

public interface IServiceRequestRead_Service 
{
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getAllServiceRequests();
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequests(ArrayList<Long> cList);
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByCompanies(ArrayList<Long> cList);
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByPeople(ArrayList<Long> pList);
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBySuppliers(ArrayList<Long> sList);
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatus(ArrayList<Long> rList);
	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatusBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getAllServiceStatusDetails();		
}