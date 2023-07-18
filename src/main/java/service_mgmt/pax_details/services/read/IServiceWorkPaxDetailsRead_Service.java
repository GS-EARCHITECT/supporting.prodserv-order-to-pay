package service_mgmt.pax_details.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import service_mgmt.pax_details.model.dto.ServiceWorkPaxDetail_DTO;

public interface IServiceWorkPaxDetailsRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> getAllServiceWorkPaxs();
	public CompletableFuture<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> getSelectWorkPaxs(CopyOnWriteArrayList<Long> sList);
		
}