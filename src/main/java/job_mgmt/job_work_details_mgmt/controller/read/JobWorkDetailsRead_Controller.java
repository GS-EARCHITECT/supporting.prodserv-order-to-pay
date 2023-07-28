package job_mgmt.job_work_details_mgmt.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import job_mgmt.job_work_details_mgmt.model.dto.JobWorkDetail_DTO;
import job_mgmt.job_work_details_mgmt.services.read.I_JobWorkDetailsRead_Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobWorkDetailsReadMgmt")
public class JobWorkDetailsRead_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobWorkDetailsRead_Service jobWorkDetailsReadService;
	
	@GetMapping(value = "/getAllJobWorkDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobWorkDetail_DTO>> getAllJobWorkDetails() 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> jobWorkDetail_DTOs=null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobWorkDetailList=null;
		try {
			jobWorkDetail_DTOs = jobWorkDetailsReadService.getAllJobWorkDetails();
			jobWorkDetailList = jobWorkDetail_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkDetailList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJobWorkDetailsForServices", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetailsForServices(@RequestBody CopyOnWriteArrayList<Long> servWorkSeqNos)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> jobWorkDetail_DTOs=null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobWorkDetailList=null;
		try {
			jobWorkDetail_DTOs = jobWorkDetailsReadService.getSelectJobWorkDetailsForServices(servWorkSeqNos);
			jobWorkDetailList = jobWorkDetail_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkDetailList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJobWorkDetailsBetweenPlanTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetailsBetweenPlanTimes(String frDtTm, String toDtTm)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> jobWorkDetail_DTOs=null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobWorkDetailList=null;
		try {
			jobWorkDetail_DTOs = jobWorkDetailsReadService.getSelectJobWorkDetailsBetweenPlanTimes(frDtTm, toDtTm);
			jobWorkDetailList = jobWorkDetail_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkDetailList, HttpStatus.OK);
	}

	@GetMapping(value = "/getJobWorkDetailsBetweenActualTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobWorkDetail_DTO>> getSelectJobWorkDetailsBetweenActualTimes(String frDtTm, String toDtTm)
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> jobWorkDetail_DTOs=null;
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobWorkDetailList=null;
		try {
			jobWorkDetail_DTOs = jobWorkDetailsReadService.getSelectJobWorkDetailsBetweenActualTimes(frDtTm, toDtTm);
			jobWorkDetailList = jobWorkDetail_DTOs.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobWorkDetailList, HttpStatus.OK);	}
	
}