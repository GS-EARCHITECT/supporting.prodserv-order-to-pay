package request_mgmt.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.model.master.ServiceRequestStatusDetailsPK;
import request_mgmt.services.online.read.IServiceRequestStatusRead_Service;

@RestController
@RequestMapping("/serviceRequestStatusReadMgmt")
public class ServiceRequestStatusRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Request_Controller.class);

	@Autowired
	private IServiceRequestStatusRead_Service serviceRequestStatusReadServ;

	@GetMapping(value = "/getSelectRequestStatus", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatus(@RequestBody CopyOnWriteArrayList<ServiceRequestStatusDetailsPK> sList)
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> future = serviceRequestStatusReadServ.getSelectRequestStatus(sList);
		CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDetails_DTOs=null;
		try {
			serviceRequestStatusDetails_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestStatusDetails_DTOs, HttpStatus.OK);	
		}

	@GetMapping(value = "/getSelectRequestStatusBetweenTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatusBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> future = serviceRequestStatusReadServ.getSelectRequestStatusBetweenTimes(fr, to);
		CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDetails_DTOs=null;
		try {
			serviceRequestStatusDetails_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestStatusDetails_DTOs, HttpStatus.OK);	
		}
	
	@GetMapping(value = "/getAllServiceStatusDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> getAllServiceStatusDetails()
	{
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO>> future = serviceRequestStatusReadServ.getAllServiceStatusDetails();
		CopyOnWriteArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDetails_DTOs=null;
		try {
			serviceRequestStatusDetails_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestStatusDetails_DTOs, HttpStatus.OK);	
		}
	
}