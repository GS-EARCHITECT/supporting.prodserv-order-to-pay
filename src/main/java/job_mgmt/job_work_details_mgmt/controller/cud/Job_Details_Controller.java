package job_mgmt.job_work_details_mgmt.controller.cud;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import job_mgmt.job_work_details_mgmt.model.dto.JobDetailsDTO;
import job_mgmt.job_work_details_mgmt.services.read.I_JobDetailsService;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobDetails")
public class Job_Details_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobDetailsService jobDetailsService;
	
	@PostMapping("/new")
	public ResponseEntity<JobDetailsDTO> newJobService(@RequestBody JobDetailsDTO jobDetailsDTO) 
	{
		JobDetailsDTO JobDetailsDTO2 = jobDetailsService.newJobDetails(jobDetailsDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(JobDetailsDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/job")
	public void updateJob(@RequestBody JobDetailsDTO jobDetailsDTO) 
	{
		jobDetailsService.updJobDetails(jobDetailsDTO);
	}
	
	@GetMapping(value = "/getAllJobDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobDetailsDTO>> getAllJobDetails() 
	{
		ArrayList<JobDetailsDTO> JobDetailsDTOs = jobDetailsService.getAllJobDetails();
		return new ResponseEntity<>(JobDetailsDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/processJobDetails/{jobTemplateSeqNo}/{startDateTime}/{opFlag}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobDetailsDTO>> processJobDetails(@PathVariable Long jobTemplateSeqNo, @PathVariable String startDateTime, @PathVariable BigDecimal opFlag)
	{
		ArrayList<JobDetailsDTO> jobDetailsDTOs = jobDetailsService.processJobDetails(jobTemplateSeqNo, startDateTime, opFlag);;
		return new ResponseEntity<>(jobDetailsDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJob/{jobSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<JobDetailsDTO> getJobBySeqNo(@PathVariable Long jobSeqNo) 
	{
		JobDetailsDTO JobDetailsDTO = jobDetailsService.getJobDetailsById(jobSeqNo);
		return new ResponseEntity<>(JobDetailsDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getJobDetailsForServices", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<JobDetailsDTO>> getSelectJobDetailsForServices(@RequestBody ArrayList<Long> servWorkSeqNos)
	{
		ArrayList<JobDetailsDTO> jobsTemplateDetailsDTOs = jobDetailsService.getSelectJobDetailsForServices(servWorkSeqNos);
		return new ResponseEntity<>(jobsTemplateDetailsDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJobDetailsBetweenPlanTimee/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<JobDetailsDTO>> getSelectJobDetailsBetweenPlanTimes(String frDtTm, String toDtTm)
	{
		ArrayList<JobDetailsDTO> jobsTemplateDetailsDTOs = jobDetailsService.getSelectJobDetailsBetweenPlanTimes(frDtTm, frDtTm);
		return new ResponseEntity<>(jobsTemplateDetailsDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getJobDetailsBetweenActualTimee/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<JobDetailsDTO>> getSelectJobDetailsBetweenActualTimes(String frDtTm, String toDtTm)
	{
		ArrayList<JobDetailsDTO> jobsTemplateDetailsDTOs = jobDetailsService.getSelectJobDetailsBetweenActualTimes(frDtTm, frDtTm);
		return new ResponseEntity<>(jobsTemplateDetailsDTOs, HttpStatus.OK);
	}
	
	@DeleteMapping("/delJobService/{jobSeqNo}")
	public void deleteJob(@PathVariable Long jobSeqNo) 
	{
		jobDetailsService.delJobDetails(jobSeqNo);
	}

	@DeleteMapping("/delAllJobDetails")
	public void deleteAllJobDetails() 
	{
		jobDetailsService.delAllJobDetails();
	}

	@DeleteMapping("/delSelectJobDetails")
	public void deleteSelectJobDetails(@RequestBody ArrayList<Long> servWorkSeqNoList) 
	{
		jobDetailsService.delSelectJobDetails(servWorkSeqNoList);
	}	

	@DeleteMapping("/delSelectJobDetailsForService")
	public void getSelectJobDetailsForService(@RequestBody ArrayList<Long> servWorkSeqNos) 
	{
		jobDetailsService.delSelectJobDetailsForServices(servWorkSeqNos);
	}
	
	@DeleteMapping("/delSelectJobDetailsBetweenPlanTimes/{fr}/{to}")
	public void delSelectJobDetailsBetweenPlanTimes(@PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
		jobDetailsService.delSelectJobDetailsBetweenPlanTimes(frDtTm, toDtTm);
	}
	
	@DeleteMapping("/delSelectJobDetailsBetweenActualTimes/{fr}/{to}")
	public void delSelectJobDetailsBetweenActualTimes(@PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
		jobDetailsService.delSelectJobDetailsBetweenActualTimes(frDtTm, toDtTm);
	}
	
}