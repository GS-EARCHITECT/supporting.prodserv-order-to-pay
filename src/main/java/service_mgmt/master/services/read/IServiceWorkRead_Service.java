package service_mgmt.master.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import service_mgmt.master.model.dto.ServiceWorkMaster_DTO;

public interface IServiceWorkRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getAllServiceWorks();
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorks(CopyOnWriteArrayList<Long> sList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByParties(CopyOnWriteArrayList<Long> pList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByBookings(CopyOnWriteArrayList<Long> bList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByRequests(CopyOnWriteArrayList<Long> rList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByServices(CopyOnWriteArrayList<Long> sList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByCreatedBy(CopyOnWriteArrayList<Long> cList);
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksBillPending();
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksForAutoAllocJobsNotAllocated();
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksForAutoAllocResourcesNotAllocated();	
}