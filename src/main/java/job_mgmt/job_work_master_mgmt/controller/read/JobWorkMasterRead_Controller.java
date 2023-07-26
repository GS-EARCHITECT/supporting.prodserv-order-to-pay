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
import job_mgmt.job_work_master_mgmt.services.read.I_JobWorkMasterRead_Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobWorkMasterReadMgmt")
public class JobWorkMasterRead_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobWorkMasterRead_Service jobWorkMasterReadServ;
	
	@GetMapping(value = "/getAllJobWorks", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getAllJobWorks() 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getAllJobWorks();
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
	
	@GetMapping(value = "/getSelectJobWorks", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorks(@RequestBody CopyOnWriteArrayList<Long> servWorkSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getSelectJobWorks(servWorkSeqNos);
			jobWorkMasterList=jobWorkMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkMasterList, HttpStatus.OK);}
	
	@GetMapping(value = "/getJobWorksForRequests", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorksForRequests(@RequestBody CopyOnWriteArrayList<Long> reqSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getSelectJobWorksForRequests(reqSeqNos);
			jobWorkMasterList=jobWorkMaster_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkMasterList, HttpStatus.OK);}
	
	@GetMapping(value = "/getJobWorksForServices", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorksForWorks(CopyOnWriteArrayList<Long> servSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getSelectJobWorksForServices(servSeqNos);
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
	
	@GetMapping(value = "/getJobWorksToBeScheduledForRequests", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getJobWorksToBeScheduledForRequests(CopyOnWriteArrayList<Long> reqSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getJobWorksToBeScheduledForRequests(reqSeqNos);
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
	
	@GetMapping(value = "/getJobServiceForRefNos", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getJobServiceForRefNos(@PathVariable CopyOnWriteArrayList<String> jobrefIds)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getJobServiceForRefNos(jobrefIds);
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
	
	@GetMapping(value = "/getJobWorksToBeScheduled", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getJobWorksToBeScheduled()
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getJobWorksToBeScheduled();
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
	
	@GetMapping(value = "/getJobWorksBetweenTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkMaster_DTO>> getSelectJobWorksBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkMaster_DTO>> jobWorkMaster_DTOs=null;
		CopyOnWriteArrayList<JobWorkMaster_DTO> jobWorkMasterList=null;
		try {
			jobWorkMaster_DTOs = jobWorkMasterReadServ.getSelectJobWorksBetweenTimes(fr, to);
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
