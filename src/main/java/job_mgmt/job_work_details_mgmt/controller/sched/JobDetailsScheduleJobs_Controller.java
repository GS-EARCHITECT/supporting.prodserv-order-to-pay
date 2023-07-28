package job_mgmt.job_work_details_mgmt.controller.sched;

import java.util.ArrayList;
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
import job_mgmt.job_work_details_mgmt.model.dto.JobDetails_DTO;
import job_mgmt.job_work_details_mgmt.services.read.I_JobDetailsRead_Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import job_mgmt.job_work_details_mgmt.services.read.I_JobDetailsSchedule_Service;

@RestController
@RequestMapping("/jobDetailsReadMgmt")
public class JobDetailsScheduleJobs_Controller {
	// private static final Logger logger =
	// LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobDetailsSchedule_Service jobDetailsReadService;

	@GetMapping(value = "/processJobDetails/{jobTemplateSeqNo}/{startDateTime}/{opFlag}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobDetails_DTO>> processJobDetails(@PathVariable Long jobTemplateSeqNo,
			@PathVariable String startDateTime, @PathVariable Integer opFlag) 
	{
		ArrayList<JobDetails_DTO> jobDetailsDTOs = jobDetailsService.processJobDetails(jobTemplateSeqNo, startDateTime,
				opFlag);
		;
		return new ResponseEntity<>(jobDetailsDTOs, HttpStatus.OK);
	}

}