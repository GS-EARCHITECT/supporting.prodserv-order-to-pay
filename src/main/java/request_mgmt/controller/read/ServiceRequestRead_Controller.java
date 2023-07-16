package request_mgmt.controller.read;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
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
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.services.read.IServiceRequestRead_Service;

@RestController
@RequestMapping("/serviceRequestReadMgmt")
public class ServiceRequestRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Request_Controller.class);

	@Autowired
	private IServiceRequestRead_Service serviceRequestReadServ;

	/* CUSTOMER REQUEST */	
	
	@GetMapping(value = "/getAllServiceRequests", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getAllServiceRequests() {
		
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ.getAllServiceRequests();
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectRequests", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequests(@RequestBody ArrayList<Long> cList) {
		
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ.getSelectRequests(cList);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectRequestsByCompanies", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByCompanies(@RequestBody ArrayList<Long> cList)
	{
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ.getSelectRequestsByCompanies(cList);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);	}
	
	@GetMapping(value = "/getSelectRequestsByPeople", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByPeople(@RequestBody ArrayList<Long> pList)
	{
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ.getSelectRequestsByPeople(pList);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);	
		}

	@GetMapping(value = "/getSelectRequestsBySuppliers", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBySuppliers(@RequestBody ArrayList<Long> sList)
	{
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ.getSelectRequestsBySuppliers(sList);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);	
		}
	
	@GetMapping(value = "/getSelectRequestsBetweenTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ.getSelectRequestsBetweenTimes(fr, to);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);	
		}
	
	
	/* CUSTOMER REQUEST STATUS */

	@GetMapping(value = "/getSelectRequestStatus", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatus(@RequestBody ArrayList<Long> rList)
	{
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = serviceRequestReadServ.getSelectRequestStatus(rList);
		ArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDetails_DTOs=null;
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
	public ResponseEntity<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatusBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = serviceRequestReadServ.getSelectRequestStatusBetweenTimes(fr, to);
		ArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDetails_DTOs=null;
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
	public ResponseEntity<ArrayList<ServiceRequestStatusDetails_DTO>> getAllServiceStatusDetails()
	{
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = serviceRequestReadServ.getAllServiceStatusDetails();
		ArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDetails_DTOs=null;
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