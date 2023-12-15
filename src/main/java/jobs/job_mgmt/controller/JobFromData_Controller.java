package jobs.job_mgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jobs.job_mgmt.services.online.I_JobWorkDetailsScheduleFromData_Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobsFromData")
public class JobFromData_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private I_JobWorkDetailsScheduleFromData_Service jobWorkDetailsFromDataServOnLine;

	@GetMapping(value = "/fromData/{newServWorkSeqNo}")
	public void processJobWorkDetailsFromData(@PathVariable Long newServWorkSeqNo)
	{
		jobWorkDetailsFromDataServOnLine.processJobsForService(newServWorkSeqNo);
		return;		
	}
	
}
