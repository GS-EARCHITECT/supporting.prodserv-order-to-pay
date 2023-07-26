package job_mgmt.job_work_master_mgmt.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import job_mgmt.job_work_master_mgmt.model.dto.JobWorkMaster_DTO;
import job_mgmt.job_work_master_mgmt.services.cud.I_JobWorkMasterCUD_Service;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobWorkMasterCUDMgmt")
public class JobWorkMasterCUD_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobWorkMasterCUD_Service jobWorkMasterCUDService;
	
	@PostMapping("/new")
	public ResponseEntity<JobWorkMaster_DTO> newJobWork(@RequestBody JobWorkMaster_DTO jobMasterDTO) 
	{
		CompletableFuture<JobWorkMaster_DTO> jCompletableFuture = jobWorkMasterCUDService.newJobWork(jobMasterDTO); 
		JobWorkMaster_DTO JobWorkMaster_DTO2=null;
		try {
			JobWorkMaster_DTO2 = jCompletableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(JobWorkMaster_DTO2, HttpStatus.CREATED);
	}
	
	@PutMapping("/job")
	public void updateJob(@RequestBody JobWorkMaster_DTO jobMasterDTO) 
	{
		jobWorkMasterCUDService.updJobWork(jobMasterDTO);
	}
	
	@PutMapping("/updScheduledServiceWork/{sseqNo}")
	public void updScheduledServiceWork(@PathVariable Long sseqNo) 
	{
		jobWorkMasterCUDService.updScheduledServiceWork(sseqNo);
	}
	
	@DeleteMapping("/delSelectJobServices")
	public void delSelectJobServices(@RequestBody CopyOnWriteArrayList<Long> servWorkSeqNos) 
	{
		jobWorkMasterCUDService.delSelectJobServices(servWorkSeqNos);
	}

	@DeleteMapping("/delAllJobServices")
	public void deleteAllJobServices() 
	{
		jobWorkMasterCUDService.delAllJobServices();
	}

	@DeleteMapping("/delSelectJobServicesForRequests")
	public void deleteSelectJobServices(@RequestBody CopyOnWriteArrayList<Long> reqSeqNoList) 
	{
		jobWorkMasterCUDService.delSelectJobServicesForRequests(reqSeqNoList);
	}	

	@DeleteMapping("/delSelectJobServicesForServices")
	public void delSelectJobServicesForServices(@RequestBody CopyOnWriteArrayList<Long> sSeqNos) 
	{
		jobWorkMasterCUDService.delSelectJobServicesForServices(sSeqNos);
	}
	
	@DeleteMapping("/delSelectJobServicesBetweenTimes/{fr}/{to}")
	public void delSelectJobServicesBetweenTimes(@PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
		jobWorkMasterCUDService.delSelectJobServicesBetweenTimes(frDtTm, frDtTm);
	}
	
	
}