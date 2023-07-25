package job_mgmt.job_work_details_mgmt.controller.read;

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
import job_mgmt.job_work_details_mgmt.model.dto.JobDetails_DTO;
import job_mgmt.job_work_details_mgmt.services.read.I_JobDetailsRead_Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobDetailsReadMgmt")
public class JobDetailsRead_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobDetailsRead_Service jobDetailsReadService;
	
	@GetMapping(value = "/getAllJobDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobDetails_DTO>> getAllJobDetails() 
	{
		CopyOnWriteArrayList<JobDetails_DTO> JobDetails_DTOs=null;
		try {
			JobDetails_DTOs = jobDetailsReadService.getAllJobDetails();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(JobDetails_DTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJobDetailsForServices", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobDetails_DTO>> getSelectJobDetailsForServices(@RequestBody CopyOnWriteArrayList<Long> servWorkSeqNos)
	{
		CopyOnWriteArrayList<JobDetails_DTO> jobsTemplateDetailsDTOs=null;
		try {
			jobsTemplateDetailsDTOs = jobDetailsReadService.getSelectJobDetailsForServices(servWorkSeqNos);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobsTemplateDetailsDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJobDetailsBetweenPlanTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobDetails_DTO>> getSelectJobDetailsBetweenPlanTimes(String frDtTm, String toDtTm)
	{
		CopyOnWriteArrayList<JobDetails_DTO> jobsTemplateDetailsDTOs=null;
		try {
			jobsTemplateDetailsDTOs = jobDetailsReadService.getSelectJobDetailsBetweenPlanTimes(frDtTm, frDtTm);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobsTemplateDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getJobDetailsBetweenActualTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CopyOnWriteArrayList<JobDetails_DTO>> getSelectJobDetailsBetweenActualTimes(String frDtTm, String toDtTm)
	{
		CopyOnWriteArrayList<JobDetails_DTO> jobsTemplateDetailsDTOs=null;
		try {
			jobsTemplateDetailsDTOs = jobDetailsReadService.getSelectJobDetailsBetweenActualTimes(frDtTm, frDtTm);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(jobsTemplateDetailsDTOs, HttpStatus.OK);
	}
	
}