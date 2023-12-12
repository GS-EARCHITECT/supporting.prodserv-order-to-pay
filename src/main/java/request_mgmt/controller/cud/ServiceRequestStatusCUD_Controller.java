package request_mgmt.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.model.master.ServiceRequestStatusDetailsPK;
import request_mgmt.services.online.cud.IServiceRequestStatusCUD_Service;

@RestController
@RequestMapping("/serviceRequestCUDMgmt")
public class ServiceRequestStatusCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Request_Controller.class);

	@Autowired
	private IServiceRequestStatusCUD_Service serviceRequestStatusCUDServ;

	@PostMapping("/")
	public ResponseEntity<ServiceRequestStatusDetails_DTO> newCustServiceRequestStatus(
			@RequestBody ServiceRequestStatusDetails_DTO serviceRequestStatusRequestDTO) {
		
		CompletableFuture<ServiceRequestStatusDetails_DTO> future = serviceRequestStatusCUDServ
				.newCustServiceRequestStatus(serviceRequestStatusRequestDTO);
		ServiceRequestStatusDetails_DTO serviceRequestDTO2=null;
		try {
			serviceRequestDTO2 = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(serviceRequestDTO2, httpHeaders, HttpStatus.CREATED);
	}
		
	@PutMapping("/updCustServiceRequestStatus")
	public void updCustServiceRequestStatus(@RequestBody ServiceRequestStatusDetails_DTO serviceRequestStatusDTO)
	{
		serviceRequestStatusCUDServ.updCustServiceRequestStatus(serviceRequestStatusDTO);
	}
	
	@DeleteMapping("/delSelectRequestStatus")
	public void delSelectRequestStatus(@RequestBody CopyOnWriteArrayList<ServiceRequestStatusDetailsPK> rList) 
	{
		serviceRequestStatusCUDServ.delSelectRequestStatus(rList);
	}
	
	@DeleteMapping("/delSelectRequestStatusBetweenTimes/{fr}/{to}")
	public void delSelectRequestStatusBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{		
		serviceRequestStatusCUDServ.delSelectRequestStatusBetweenTimes(fr, to);
	}
	
	@DeleteMapping("/delAllRequestStatus()")
	public void delAllRequestStatus()
	{
		serviceRequestStatusCUDServ.delAllRequestStatus();
	}
}