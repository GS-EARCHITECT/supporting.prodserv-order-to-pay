package job_mgmt.job_work_master_mgmt.controller.cud;

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

import job_mgmt.job_work_master_mgmt.model.dto.JobMasterDTO;
import job_mgmt.job_work_master_mgmt.services.I_JobMasterService;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobMaster")
public class Job_Master_Controller 
{
	//private static final Logger logger = LoggerFactory.getLogger(Job_Controller.class);

	@Autowired
	private I_JobMasterService jobMasterService;
	
	@PostMapping("/new")
	public ResponseEntity<JobMasterDTO> newJobService(@RequestBody JobMasterDTO jobMasterDTO) 
	{
		JobMasterDTO JobMasterDTO2 = jobMasterService.newJobMaster(jobMasterDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(JobMasterDTO2, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/job")
	public void updateJob(@RequestBody JobMasterDTO jobMasterDTO) 
	{
		jobMasterService.updJobMaster(jobMasterDTO);
	}
	
	@GetMapping(value = "/getAllJobServices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobMasterDTO>> getAllJobServices() 
	{
		ArrayList<JobMasterDTO> JobMasterDTOs = jobMasterService.getAllJobMasters();
		return new ResponseEntity<>(JobMasterDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJobServicesForRefId/{refId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobMasterDTO>> getJobServicesForRefID(@PathVariable String refId) 
	{
		ArrayList<JobMasterDTO> JobMasterDTOs = jobMasterService.getJobServicesForRefId(refId);
		return new ResponseEntity<>(JobMasterDTOs, HttpStatus.OK);
	}

	
	@GetMapping(value = "/getJob/{servWorkSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<JobMasterDTO> getJobBySeqNo(@PathVariable Long servWorkSeqNo) 
	{
		JobMasterDTO JobMasterDTO = jobMasterService.getJobMasterById(servWorkSeqNo);
		return new ResponseEntity<>(JobMasterDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getJobServicesForServiceType", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<JobMasterDTO>> getSelectJobServices(@RequestBody ArrayList<Long> servWorkSeqNos)
	{
		ArrayList<JobMasterDTO> jobsTemplateMasterDTOs = jobMasterService.getSelectJobServices(servWorkSeqNos);
		return new ResponseEntity<>(jobsTemplateMasterDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJobServicesForCompanies", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<JobMasterDTO>> getSelectJobServicesForCompanies(@RequestBody ArrayList<Long> compSeqNos)
	{
		ArrayList<JobMasterDTO> jobsTemplateMasterDTOs = jobMasterService.getSelectJobServicesForCompanies(compSeqNos);
		return new ResponseEntity<>(jobsTemplateMasterDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJobServicesForServices", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<JobMasterDTO>> getSelectJobServicesForServices(ArrayList<Long> servSeqNos)
	{
		ArrayList<JobMasterDTO> jobsTemplateMasterDTOs = jobMasterService.getSelectJobServicesForServices(servSeqNos);
		return new ResponseEntity<>(jobsTemplateMasterDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getJobServicesForServices/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<JobMasterDTO>> getSelectJobServicesBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
		ArrayList<JobMasterDTO> jobsTemplateMasterDTOs = jobMasterService.getSelectJobServicesBetweenTimes(fr, to);
		return new ResponseEntity<>(jobsTemplateMasterDTOs, HttpStatus.OK);
	}

	@DeleteMapping("/delJobService/{servWorkSeqNo}")
	public void deleteJob(@PathVariable Long servWorkSeqNo) 
	{
		jobMasterService.delJobMaster(servWorkSeqNo);
	}

	@DeleteMapping("/delAllJobServices")
	public void deleteAllJobServices() 
	{
		jobMasterService.delAllJobMasters();
	}

	@DeleteMapping("/delSelectJobServices")
	public void deleteSelectJobServices(@RequestBody ArrayList<Long> servWorkSeqNoList) 
	{
		jobMasterService.delSelectJobServices(servWorkSeqNoList);
	}	

	@DeleteMapping("/delSelectJobServicesForCompanies")
	public void delSelectJobServicesForCompanies(@RequestBody ArrayList<Long> compSeqNos) 
	{
		jobMasterService.delSelectJobServicesForCompanies(compSeqNos);
	}
	
	@DeleteMapping("/delSelectJobServicesForServices")
	public void delSelectJobServicesForServices(@RequestBody ArrayList<Long> servSeqNos) 
	{
		jobMasterService.delSelectJobServicesForServices(servSeqNos);
	}

	@DeleteMapping("/delSelectJobServicesForServices/{fr}/{to}")
	public void delSelectJobServicesBetweenTimes(@PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
		jobMasterService.delSelectJobServicesBetweenTimes(frDtTm, frDtTm);
	}
	
	
}