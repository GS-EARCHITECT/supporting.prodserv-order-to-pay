package job_mgmt.job_work_master_mgmt.controller.read;

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
import job_mgmt.job_work_master_mgmt.model.dto.JobWorkMaster_DTO;
import job_mgmt.job_work_master_mgmt.services.cud.I_JobWorkMasterRead_Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobWorkMasterReadMgmt")
public class JobWorkMasterRead_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobWorkMasterRead_Service jobWorkMasterReadServ;
	
	@GetMapping(value = "/getAllJobServices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getAllJobServices() 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getAllJobWorkMasters();
			jobWorkMasterList=jobWorkMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkMasterList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectJobServices", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServices(@RequestBody CopyOnWriteArrayList<Long> servWorkSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getSelectJobServices(servWorkSeqNos);
			jobWorkMasterList=jobWorkMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkMasterList, HttpStatus.OK);}
	
	@GetMapping(value = "/getJobServicesForRequests", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServicesForRequests(@RequestBody CopyOnWriteArrayList<Long> compSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getAllJobWorkMasters();
			jobWorkMasterList=jobWorkMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkMasterList, HttpStatus.OK);}
	
	@GetMapping(value = "/getJobServicesForServices", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServicesForServices(CopyOnWriteArrayList<Long> servSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getSelectJobServicesForServices(servSeqNos);
			jobWorkMasterList=jobWorkMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkMasterList, HttpStatus.OK);
		}
		
	@GetMapping(value = "/getJobServicesBetweenTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobServicesBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getSelectJobServicesBetweenTimes(fr, to);
			jobWorkMasterList=jobWorkMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkMasterList, HttpStatus.OK);}	
	}
