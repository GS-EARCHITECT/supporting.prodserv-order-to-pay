package service_mgmt.master.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service_mgmt.master.model.dto.ServiceWorkMaster_DTO;
import service_mgmt.master.services.read.IServiceWorkRead_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/serviceWorkReadMgmt")
public class ServiceWorkRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Customer_Work_Controller.class);

	@Autowired
	private IServiceWorkRead_Service serviceWorkReadServ;

	@GetMapping(value = "/getAllServiceWorks", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getAllServiceWorks() {

		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = serviceWorkReadServ
				.getAllServiceWorks();
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkMaster_DTOs = null;
		try {
			serviceWorkMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(serviceWorkMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectWorks", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorks(
			@RequestBody CopyOnWriteArrayList<Long> cList) {

		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = serviceWorkReadServ
				.getSelectWorks(cList);
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkMaster_DTOs = null;
		try {
			serviceWorkMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(serviceWorkMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectWorksByParties", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByParties(
			@RequestBody CopyOnWriteArrayList<Long> pList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = serviceWorkReadServ
				.getSelectWorksByParties(pList);
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkMaster_DTOs = null;
		try {
			serviceWorkMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(serviceWorkMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectWorksByBookings", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByBookings(
			@RequestBody CopyOnWriteArrayList<Long> bList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = serviceWorkReadServ
				.getSelectWorksByBookings(bList);
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkMaster_DTOs = null;
		try {
			serviceWorkMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(serviceWorkMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectWorksByRequests", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByRequests(
			@RequestBody CopyOnWriteArrayList<Long> rList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = serviceWorkReadServ
				.getSelectWorksByRequests(rList);
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkMaster_DTOs = null;
		try {
			serviceWorkMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(serviceWorkMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectWorksByServices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByServices(
			@RequestBody CopyOnWriteArrayList<Long> sList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = serviceWorkReadServ
				.getSelectWorksByServices(sList);
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkMaster_DTOs = null;
		try {
			serviceWorkMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(serviceWorkMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectWorksByCreatedBy", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksByCreatedBy(
			@RequestBody CopyOnWriteArrayList<Long> cList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = serviceWorkReadServ
				.getSelectWorksByCreatedBy(cList);
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkMaster_DTOs = null;
		try {
			serviceWorkMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(serviceWorkMaster_DTOs, HttpStatus.OK);

	}

	@GetMapping(value = "/getSelectWorksBetweenTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> getSelectWorksBetweenTimes(
			@PathVariable String fr, @PathVariable String to) {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkMaster_DTO>> future = serviceWorkReadServ
				.getSelectWorksBetweenTimes(fr, to);
		CopyOnWriteArrayList<ServiceWorkMaster_DTO> serviceWorkMaster_DTOs = null;
		try {
			serviceWorkMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(serviceWorkMaster_DTOs, HttpStatus.OK);
	}

}