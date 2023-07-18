package service_mgmt.pax_details.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import service_mgmt.pax_details.model.dto.ServiceWorkPaxDetail_DTO;

public interface IServiceWorkPaxDetailsCUD_Service 
{
	public CompletableFuture<ServiceWorkPaxDetail_DTO> newServiceWorkPaxDetail(ServiceWorkPaxDetail_DTO srvReqDTO);
	public CompletableFuture<Void> updServiceWorkPaxDetail(ServiceWorkPaxDetail_DTO servReqDTO);
	public CompletableFuture<Void> delAllWorkPaxDetails();
	public CompletableFuture<Void> delSelectWorkPaxs(CopyOnWriteArrayList<Long> sList);
}