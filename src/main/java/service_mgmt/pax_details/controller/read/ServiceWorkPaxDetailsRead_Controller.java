package service_mgmt.pax_details.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service_mgmt.pax_details.model.dto.ServiceWorkPaxDetail_DTO;
import service_mgmt.pax_details.services.read.IServiceWorkPaxDetailsRead_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/serviceWorkPaxDetailsReadMgmt")
public class ServiceWorkPaxDetailsRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Customer_WorkPaxDetails_Controller.class);

	@Autowired
	private IServiceWorkPaxDetailsRead_Service serviceWorkPaxDetailsReadServ;

	@GetMapping(value = "/getAllServiceWorkPaxDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> getAllServiceWorkPaxDetails() {
		CompletableFuture<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> future = serviceWorkPaxDetailsReadServ
				.getAllServiceWorkPaxs();
		CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO> serviceWorkPaxDetail_DTOs = null;
		try {
			serviceWorkPaxDetail_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(serviceWorkPaxDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectWorkPaxDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> getSelectWorkPaxDetails(
			@RequestBody CopyOnWriteArrayList<Long> cList) {

		CompletableFuture<CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO>> future = serviceWorkPaxDetailsReadServ
				.getSelectWorkPaxs(cList);
		CopyOnWriteArrayList<ServiceWorkPaxDetail_DTO> serviceWorkPaxDetail_DTOs = null;
		try {
			serviceWorkPaxDetail_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(serviceWorkPaxDetail_DTOs, HttpStatus.OK);
	}

}