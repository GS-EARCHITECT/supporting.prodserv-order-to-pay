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
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.services.online.read.IServiceRequestRead_Service;

@RestController
@RequestMapping("/serviceRequestReadMgmt")
public class ServiceRequestRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Customer_Request_Controller.class);

	@Autowired
	private IServiceRequestRead_Service serviceRequestReadServ;

	@GetMapping(value = "/getAllServiceRequests", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getAllServiceRequests() {

		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getAllServiceRequests();
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequests(
			@RequestBody CopyOnWriteArrayList<Long> cList) {

		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequests(cList);
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsByParties", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByParties(
			@RequestBody CopyOnWriteArrayList<Long> cList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsByParties(cList);
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBySuppliers(
			@RequestBody CopyOnWriteArrayList<Long> pList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsBySuppliers(pList);
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBetweenTimes(
			@PathVariable String fr, @PathVariable String to) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsBetweenTimes(fr, to);
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsByRefList", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByRefList(
			@RequestBody CopyOnWriteArrayList<Long> rList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsByRefList(rList);
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsByFrLocs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByFrLocs(
			@RequestBody CopyOnWriteArrayList<Long> fList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsByFrLocs(fList);
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsByToLocs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByToLocs(
			@RequestBody CopyOnWriteArrayList<Long> fList) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsByToLocs(fList);
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsOk", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsOk() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsOk();
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsNotOk", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsNoOk() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsNotOk();
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsDone", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsDone() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsDone();
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsNotDone", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsNotDone() {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsNotDone();
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsFrPartyLat/{lat}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsFrPartyLat(
			@PathVariable Float lat) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsFrPartyLat(lat);
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

	@GetMapping(value = "/getSelectRequestsFrPartyLon/{lon}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> getSelectRequestsFrPartyLon(
			@PathVariable Float lon) {
		CompletableFuture<CopyOnWriteArrayList<ServiceRequestMaster_DTO>> future = serviceRequestReadServ
				.getSelectRequestsFrPartyLon(lon);
		CopyOnWriteArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs = null;
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

}