package service_mgmt.master.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service_mgmt.master.model.dto.ServiceWorkMaster_DTO;
import service_mgmt.master.services.cud.IServiceWorkCUD_Service;

@CrossOrigin
@RestController
@RequestMapping("/serviceWorkCUDMgmt")
public class ServiceWorkCUD_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Customer_Request_Controller.class);

	@Autowired
	private IServiceWorkCUD_Service serviceWorkCUDServ;

	@PostMapping("/new")
	public ResponseEntity<ServiceWorkMaster_DTO> newServiceWork(@RequestBody ServiceWorkMaster_DTO serviceRequestDTO) {
		CompletableFuture<ServiceWorkMaster_DTO> future = serviceWorkCUDServ.newServiceWork(serviceRequestDTO);
		ServiceWorkMaster_DTO srvRequestDTO = null;
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

	@PutMapping("/updServiceWork")
	public void updServiceWork(@RequestBody ServiceWorkMaster_DTO swtDTO) {
		serviceWorkCUDServ.updServiceWork(swtDTO);
	}

	@DeleteMapping("/delSelectServiceWorks")
	public void delSelectServiceWorks(@RequestBody CopyOnWriteArrayList<Long> cList) {
		serviceWorkCUDServ.delSelectWorks(cList);
	}

	@DeleteMapping("/delSelectWorksByParties")
	public void delSelectWorksByParties(@RequestBody CopyOnWriteArrayList<Long> pList) {
		serviceWorkCUDServ.delSelectWorksByParties(pList);
	}

	@DeleteMapping("/delSelectWorksByBookings")
	public void delSelectWorksByBookings(@RequestBody CopyOnWriteArrayList<Long> bList) {
		serviceWorkCUDServ.delSelectWorksByBookings(bList);
	}

	@DeleteMapping("/delSelectWorksByRequests")
	public void delSelectWorksByRequests(@RequestBody CopyOnWriteArrayList<Long> rList) {
		serviceWorkCUDServ.delSelectWorksByRequests(rList);
	}

	@DeleteMapping("/delSelectWorksByServices")
	public void delSelectWorksByServices(@RequestBody CopyOnWriteArrayList<Long> sList) {
		serviceWorkCUDServ.delSelectWorksByServices(sList);
	}

	@DeleteMapping("/delSelectWorksByCreatedBy")
	public void delSelectWorksByCreatedBy(@RequestBody CopyOnWriteArrayList<Long> cList) {
		serviceWorkCUDServ.delSelectWorksByCreatedBy(cList);
	}

	@DeleteMapping("/delSelectServiceWorksBetweenTimes/{fr}/{to}")
	public void delSelectServiceWorksBetweenTimes(@PathVariable String fr, @PathVariable String to) {
		serviceWorkCUDServ.delSelectWorksBetweenTimes(fr, to);
	}

	@DeleteMapping("/delAllServiceWorks")
	public void deleteAllServiceWorks() {
		serviceWorkCUDServ.delAllServiceWorks();
	}

}