package request_mgmt.controller.cud;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
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
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.services.cud.IServiceRequestCUD_Service;

@RestController
@RequestMapping("/serviceRequestCUDMgmt")
public class ServiceRequestCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Request_Controller.class);

	@Autowired
	private IServiceRequestCUD_Service serviceRequestCUDServ;

	/* CUSTOMER REQUEST */	
	@PostMapping("/new")
	public ResponseEntity<ServiceRequestMaster_DTO> newRequest(@RequestBody ServiceRequestMaster_DTO serviceRequestDTO) 
	{
		CompletableFuture<ServiceRequestMaster_DTO> future = serviceRequestCUDServ.newCustServiceRequest(serviceRequestDTO);
		ServiceRequestMaster_DTO srvRequestDTO=null;
		try {
			srvRequestDTO = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		HttpHeaders httpHeaders = new HttpHeaders();		
		return new ResponseEntity<>(srvRequestDTO, httpHeaders, HttpStatus.CREATED);
	}
		
	@PutMapping("/updCustServiceRequest")
	public void updCustServiceRequest(@RequestBody ServiceRequestMaster_DTO serviceRequestDTO) 
	{
		serviceRequestCUDServ.updCustServiceRequest(serviceRequestDTO);		
	}

	@DeleteMapping("/delSelectRequests")
	public void delSelectRequests(@RequestBody ArrayList<Long> cList)
	{
		serviceRequestCUDServ.delSelectRequests(cList);
	}

	@DeleteMapping("/delSelectRequestsByCompanies")
	public void delSelectRequestsByCompanies(@RequestBody ArrayList<Long> cList)
	{
	serviceRequestCUDServ.delSelectRequestsByCompanies(cList);
	}
	
	@DeleteMapping("/delSelectRequestsByPeople")
	public void delSelectRequestsByPeople(@RequestBody ArrayList<Long> pList)
	{
	serviceRequestCUDServ.delSelectRequestsByPeople(pList);
	}

	@DeleteMapping("/delSelectRequestsBySuppliers")
	public void delSelectRequestsBySuppliers(@RequestBody ArrayList<Long> sList)
	{
	serviceRequestCUDServ.delSelectRequestsBySuppliers(sList);
	}
	
	@DeleteMapping("/delSelectRequestsBetweenTimes/{fr}/{to}")
	public void delSelectRequestsBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
	serviceRequestCUDServ.delSelectRequestsBetweenTimes(fr, to);
	}
	
	@DeleteMapping("/delAllRequests")
	public void deleteAllRequests() 
	{
		serviceRequestCUDServ.delAllRequests();
	}
	
	/* CUSTOMER REQUEST STATUS */
	
	@PostMapping("/")
	public ResponseEntity<ServiceRequestStatusDetails_DTO> newCustServiceRequestStatus(
			@RequestBody ServiceRequestStatusDetails_DTO serviceRequestStatusRequestDTO) {
		
		CompletableFuture<ServiceRequestStatusDetails_DTO> future = serviceRequestCUDServ
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
		serviceRequestCUDServ.updCustServiceRequestStatus(serviceRequestStatusDTO);
	}
	
	@DeleteMapping("/delSelectRequestStatus")
	public void delSelectRequestStatus(@RequestBody ArrayList<Long> rList) 
	{
		serviceRequestCUDServ.delSelectRequests(rList);
	}
	
	@DeleteMapping("/delSelectRequestStatusBetweenTimes/{fr}/{to}")
	public void delSelectRequestStatusBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{		
	serviceRequestCUDServ.delSelectRequestsBetweenTimes(fr, to);
	}
	
	@DeleteMapping("/delAllRequestStatus()")
	public void delAllRequestStatus()
	{
		serviceRequestCUDServ.delAllRequestStatus();
	}
}