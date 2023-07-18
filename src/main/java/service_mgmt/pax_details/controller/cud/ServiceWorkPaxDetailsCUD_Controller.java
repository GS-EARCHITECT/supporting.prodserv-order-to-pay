package service_mgmt.pax_details.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service_mgmt.pax_details.model.dto.ServiceWorkPaxDetail_DTO;
import service_mgmt.pax_details.services.cud.IServiceWorkPaxDetailsCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/serviceWorkPaxDetailsCUDMgmt")
public class ServiceWorkPaxDetailsCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_WorkPaxDetails_Controller.class);

	@Autowired
	private IServiceWorkPaxDetailsCUD_Service serviceWorkPaxDetailsCUDServ;

	@PostMapping("/new")
	public ResponseEntity<ServiceWorkPaxDetail_DTO> newWorkPaxDetails(@RequestBody ServiceWorkPaxDetail_DTO serviceWorkPaxDetailsDTO) 
	{
		CompletableFuture<ServiceWorkPaxDetail_DTO> future = serviceWorkPaxDetailsCUDServ.newServiceWorkPaxDetail(serviceWorkPaxDetailsDTO);
		ServiceWorkPaxDetail_DTO srvWorkPaxDetailsDTO=null;
		try {
			srvWorkPaxDetailsDTO = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		HttpHeaders httpHeaders = new HttpHeaders();		
		return new ResponseEntity<>(srvWorkPaxDetailsDTO, httpHeaders, HttpStatus.CREATED);
	}
		
	@PutMapping("/updServiceWorkPaxDetails")
	public void updServiceWorkPaxDetails(@RequestBody ServiceWorkPaxDetail_DTO serviceWorkPaxDetailsDTO) 
	{
		serviceWorkPaxDetailsCUDServ.updServiceWorkPaxDetail(serviceWorkPaxDetailsDTO);		
	}

	@DeleteMapping("/delSelectWorkPaxDetails")
	public void delSelectWorkPaxDetails(@RequestBody CopyOnWriteArrayList<Long> cList)
	{
		serviceWorkPaxDetailsCUDServ.delSelectWorkPaxs(cList);
	}

	
	@DeleteMapping("/delAllWorkPaxDetails")
	public void deleteAllWorkPaxDetailss() 
	{
		serviceWorkPaxDetailsCUDServ.delAllWorkPaxDetails();
	}
	
	
}