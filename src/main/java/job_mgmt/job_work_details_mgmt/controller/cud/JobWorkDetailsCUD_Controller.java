package job_mgmt.job_work_details_mgmt.controller.cud;

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
import job_mgmt.job_work_details_mgmt.model.dto.JobWorkDetail_DTO;
import job_mgmt.job_work_details_mgmt.services.cud.I_JobWorkDetailsCUD_Service;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobWorkDetailsCUDMgmt")
public class JobWorkDetailsCUD_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobWorkDetailsCUD_Service jobWorkDetailsCUDService;
	
	@PostMapping("/new")
	public ResponseEntity<JobWorkDetail_DTO> newJobService(@RequestBody JobWorkDetail_DTO jobDetailDTO) 
	{
		JobWorkDetail_DTO jobWorkDetail_DTO=null;
		try {
			CompletableFuture<JobWorkDetail_DTO> completableFuture = jobWorkDetailsCUDService.newJobWorkDetail(jobDetailDTO);
			jobWorkDetail_DTO = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(jobWorkDetail_DTO, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/job")
	public void updateJobWork(@RequestBody JobWorkDetail_DTO jobDetailsDTO) 
	{
		jobWorkDetailsCUDService.updJobWorkDetail(jobDetailsDTO);				
		return;
	}
	
	@DeleteMapping("/delAllJobDetails")
	public void delAllJobDetails() 
	{
		jobWorkDetailsCUDService.delAllJobWorkDetails();
	}

	@DeleteMapping("/delSelectJobDetails")
	public void delSelectJobDetails(@RequestBody CopyOnWriteArrayList<Long> jobWorkSeqNoList) 
	{
		jobWorkDetailsCUDService.delSelectJobWorkDetails(jobWorkSeqNoList);
	}	

	@DeleteMapping("/delSelectJobDetailsForService")
	public void delSelectJobDetailsForService(@RequestBody CopyOnWriteArrayList<Long> servWorkSeqNos) 
	{
		jobWorkDetailsCUDService.delSelectJobWorkDetailsForServices(servWorkSeqNos);
	}
	
	@DeleteMapping("/delSelectJobDetailsBetweenPlanTimes/{fr}/{to}")
	public void delSelectJobDetailsBetweenPlanTimes(@PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
		jobWorkDetailsCUDService.delSelectJobWorkDetailsBetweenPlanTimes(frDtTm, toDtTm);
	}
	
	@DeleteMapping("/delSelectJobDetailsBetweenActualTimes/{fr}/{to}")
	public void delSelectJobDetailsBetweenActualTimes(@PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
		jobWorkDetailsCUDService.delSelectJobWorkDetailsBetweenActualTimes(frDtTm, toDtTm);
	}
	
}