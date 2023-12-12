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
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.services.online.cud.IServiceRequestCUD_Service;

@RestController
@RequestMapping("/serviceRequestCUDMgmt")
public class ServiceRequestCUD_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Customer_Request_Controller.class);

	@Autowired
	private IServiceRequestCUD_Service serviceRequestCUDServ;

	@PostMapping("/new")
	public ResponseEntity<ServiceRequestMaster_DTO> newCustServiceRequest(
			@RequestBody ServiceRequestMaster_DTO serviceRequestDTO) {
		CompletableFuture<ServiceRequestMaster_DTO> future = serviceRequestCUDServ
				.newCustServiceRequest(serviceRequestDTO);
		ServiceRequestMaster_DTO srvRequestDTO = null;
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
	public void updCustServiceRequest(@RequestBody ServiceRequestMaster_DTO serviceRequestDTO) {
		serviceRequestCUDServ.updCustServiceRequest(serviceRequestDTO);
	}

	@DeleteMapping("/delSelectRequests")
	public void delSelectRequests(@RequestBody CopyOnWriteArrayList<Long> cList) {
		serviceRequestCUDServ.delSelectRequests(cList);
	}

	@DeleteMapping("/delSelectRequestsByParties")
	public void delSelectRequestsByParties(@RequestBody CopyOnWriteArrayList<Long> pList) {
		serviceRequestCUDServ.delSelectRequestsByParties(pList);
	}

	@DeleteMapping("/delSelectRequestsBySuppliers")
	public void delSelectRequestsBySuppliers(@RequestBody CopyOnWriteArrayList<Long> sList) {
		serviceRequestCUDServ.delSelectRequestsBySuppliers(sList);
	}

	@DeleteMapping("/delSelectRequestsBetweenTimes/{fr}/{to}")
	public void delSelectRequestsBetweenTimes(@PathVariable String fr, @PathVariable String to) {
		serviceRequestCUDServ.delSelectRequestsBetweenTimes(fr, to);
	}

	@DeleteMapping("/delSelectRequestsByRefList")
	public void delSelectRequestsByRefList(@RequestBody CopyOnWriteArrayList<Long> rList) {
		serviceRequestCUDServ.delSelectRequestsByRefList(rList);
	}

	@DeleteMapping("/delSelectRequestsByFrLocs")
	public void delSelectRequestsByFrLocs(@RequestBody CopyOnWriteArrayList<Long> fList) {
		serviceRequestCUDServ.delSelectRequestsByFrLocs(fList);
	}

	@DeleteMapping("/delSelectRequestsByToLocs")
	public void delSelectRequestsByToLocs(@RequestBody CopyOnWriteArrayList<Long> fList) {
		serviceRequestCUDServ.delSelectRequestsByToLocs(fList);
	}

	@DeleteMapping("/delSelectRequestsOk")
	public void delSelectRequestsOk() {
		serviceRequestCUDServ.delSelectRequestsOk();
	}

	@DeleteMapping("/delSelectRequestsNotOk")
	public void delSelectRequestsNotOk() {
		serviceRequestCUDServ.delSelectRequestsNotOk();
	}

	@DeleteMapping("/delSelectRequestsDone")
	public void delSelectRequestsDone() {
		serviceRequestCUDServ.delSelectRequestsDone();
	}

	@DeleteMapping("/delSelectRequestsNotDone")
	public void delSelectRequestsNotDone() {
		serviceRequestCUDServ.delSelectRequestsDone();
	}

	@DeleteMapping("/delSelectRequestsFrPartyLat/{lat}")
	public void delSelectRequestsFrPartyLat(@PathVariable Float lat) {
		serviceRequestCUDServ.delSelectRequestsFrPartyLat(lat);
	}

	@DeleteMapping("/delSelectRequestsFrPartyLon/{lon}")
	public void delSelectRequestsFrPartyLon(@PathVariable Float lon) {
		serviceRequestCUDServ.delSelectRequestsFrPartyLon(lon);
	}

	@DeleteMapping("/delAllServiceRequests")
	public void delAllServiceRequests() {
		serviceRequestCUDServ.delAllServiceRequests();
	}

}