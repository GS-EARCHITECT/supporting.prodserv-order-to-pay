package jobs.job_mgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jobs.job_mgmt.services.online.I_JobWorkDetailsSchedule_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobs")
public class Job_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobWorkDetailsSchedule_Service jobWorkDetailsSchedulerServOnLine;

	@GetMapping(value = "/{servWorkSeqNo}")
	public void processJobWorkDetails(@PathVariable Long servWorkSeqNo)
	{			
	jobWorkDetailsSchedulerServOnLine.processJobsForService(servWorkSeqNo);
	return;		
	}
	
}
