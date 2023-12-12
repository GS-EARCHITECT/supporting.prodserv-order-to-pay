package service_mgmt.master.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import service_mgmt.master.model.dto.ServiceWorkMaster_DTO;

public interface IServiceWorkCUD_Service 
{
	public CompletableFuture<ServiceWorkMaster_DTO> newServiceWork(ServiceWorkMaster_DTO srvwDTO);
	public CompletableFuture<Void> updServiceWork(ServiceWorkMaster_DTO servwDTO);
	public CompletableFuture<Void> updResourceAllocStatus( Long id,  Character st);
	public CompletableFuture<Void> updResourceAutoFlag( Long id,  Character fl);
	public CompletableFuture<Void> updJobAllocStatus( Long id,  Character st);
	public CompletableFuture<Void> updJobAutoFlag( Long id,  Character fl);
	public CompletableFuture<Void> updOkFlag( Long id,  Character fl);
	public CompletableFuture<Void> updDoneFlag( Long id,  Character fl);
	public CompletableFuture<Void> updSelectWorkBillStatus(Long id, Character st);
	public CompletableFuture<Void> delSelectWorks(CopyOnWriteArrayList<Long> sList);
	public CompletableFuture<Void> delSelectWorksBillPending();
	public CompletableFuture<Void> delSelectWorksByParties(CopyOnWriteArrayList<Long> pList);
	public CompletableFuture<Void> delSelectWorksByBookings(CopyOnWriteArrayList<Long> bList);
	public CompletableFuture<Void> delSelectWorksByRequests(CopyOnWriteArrayList<Long> rList);
	public CompletableFuture<Void> delSelectWorksByServices(CopyOnWriteArrayList<Long> sList);
	public CompletableFuture<Void> delSelectWorksByCreatedBy(CopyOnWriteArrayList<Long> cList);
	public CompletableFuture<Void> delSelectWorksBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<Void> delSelectWorksForAutoAllocJobsNotAllocated();
	public CompletableFuture<Void> delSelectWorksForAutoAllocResourcesNotAllocated();
	public CompletableFuture<Void> delWorksDone();
	public CompletableFuture<Void> delAllServiceWorks();
	
}