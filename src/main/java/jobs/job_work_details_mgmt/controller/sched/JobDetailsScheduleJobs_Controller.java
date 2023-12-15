package jobs.job_work_details_mgmt.controller.sched;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import common.model.dto.JobWorkDetail_DTO;
import jobs.job_work_details_mgmt.services.sched.I_JobWorkDetailsSchedule_Service;

@RestController
@RequestMapping("/jobDetailsShedulerMgmt")
public class JobDetailsScheduleJobs_Controller {
	// private static final Logger logger =
	// LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobWorkDetailsSchedule_Service jobWorkDetailsSchedulerServ;

	@GetMapping(value = "/processJobDetails/{jobTemplateSeqNo}/{startDateTime}/{opFlag}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobWorkDetail_DTO>> processJobDetails(@PathVariable Long jobTemplateSeqNo,
			@PathVariable String startDateTime, @PathVariable Integer opFlag) 
	{
		CompletableFuture<CopyOnWriteArrayList<JobWorkDetail_DTO>> completableFuture = jobWorkDetailsSchedulerServ.processJobWorkDetails(jobTemplateSeqNo, startDateTime,	opFlag);
		CopyOnWriteArrayList<JobWorkDetail_DTO> jobDetailsList=null;
		try {
			jobDetailsList = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(jobDetailsList, HttpStatus.OK);
	}

}