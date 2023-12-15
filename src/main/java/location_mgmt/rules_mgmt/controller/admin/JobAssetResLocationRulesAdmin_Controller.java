package location_mgmt.rules_mgmt.controller.admin;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import common.model.dto.JobAssetResLocationRule_DTO;
import location_mgmt.rules_mgmt.services.admin.I_JobAssetResLocationRulesAdmin_Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/jobAssetResLocationRulesAdminMgmt")
public class JobAssetResLocationRulesAdmin_Controller 
{

	//private static final Logger logger = LoggerFactory.getLogger(JobAssetResLocationRule_JobAssetResLocationRule_Cuntroller.class);

	@Autowired
	private I_JobAssetResLocationRulesAdmin_Service jobAssetResLocationRulesAdminServ;
	
	@PostMapping("/new")
	public ResponseEntity<JobAssetResLocationRule_DTO> newJobAssetResLocationRule(@RequestBody JobAssetResLocationRule_DTO partyMembershipDTO) {
		JobAssetResLocationRule_DTO partyMembershipDTO2 = jobAssetResLocationRulesAdminServ.newJobAssetResLocationRule(partyMembershipDTO);
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(partyMembershipDTO2, httpHeaders, HttpStatus.CREATED);
	}

	
	@GetMapping(value = "/getAllJobAssetResLocationRules", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobAssetResLocationRule_DTO>> getAllJobAssetResLocationRule_DTOs() 
	{
		ArrayList<JobAssetResLocationRule_DTO> partyMembershipDTOs = jobAssetResLocationRulesAdminServ.getAllJobAssetResLocationRules();
		//logger.info("size :"+partyMembershipDTOs.size());
		return new ResponseEntity<>(partyMembershipDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectJobAssetResLocationRules", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRules(@RequestBody ArrayList<Long> partyMembershipCuSeqNos) {
		ArrayList<JobAssetResLocationRule_DTO> partyMembershipDTOs = jobAssetResLocationRulesAdminServ.getSelectJobAssetResLocationRules(partyMembershipCuSeqNos);		
		return new ResponseEntity<>(partyMembershipDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectJobAssetResLocationRulesByJobs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByJobs(@RequestBody ArrayList<Long> jSeqNos) {
		ArrayList<JobAssetResLocationRule_DTO> partyMembershipDTOs = jobAssetResLocationRulesAdminServ.getSelectJobAssetResLocationRulesByJobs(jSeqNos);		
		return new ResponseEntity<>(partyMembershipDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectJobAssetResLocationRulesByAssets", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByAssets(@RequestBody ArrayList<Long> aSeqNos) {
		ArrayList<JobAssetResLocationRule_DTO> partyMembershipDTOs = jobAssetResLocationRulesAdminServ.getSelectJobAssetResLocationRulesByAssets(aSeqNos);		
		return new ResponseEntity<>(partyMembershipDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectJobAssetResLocationRulesByResources", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByResources(@RequestBody ArrayList<Long> rSeqNos) {
		ArrayList<JobAssetResLocationRule_DTO> partyMembershipDTOs = jobAssetResLocationRulesAdminServ.getSelectJobAssetResLocationRulesByResources(rSeqNos);		
		return new ResponseEntity<>(partyMembershipDTOs, HttpStatus.OK);
	}
	
	@PutMapping("/updJobAssetResLocationRule")
	public void updateJobAssetResLocationRule(@RequestBody JobAssetResLocationRule_DTO partyMembershipDTO) {
		jobAssetResLocationRulesAdminServ.updJobAssetResLocationRule(partyMembershipDTO);
	}
	
	@DeleteMapping("/delSelectJobAssetResLocationRules")
	public void deleteSelectJobAssetResLocationRules(@RequestBody ArrayList<Long> coSeqNoList) {
		jobAssetResLocationRulesAdminServ.delSelectJobAssetResLocationRules(coSeqNoList);
	}
	
	@GetMapping(value = "/delSelectJobAssetResLocationRulesByAssets", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delSelectJobAssetResLocationRulesByAssets(@RequestBody ArrayList<Long> aSeqNos) {
		jobAssetResLocationRulesAdminServ.delSelectJobAssetResLocationRulesByAssets(aSeqNos);		
		return;
	}
	
	@GetMapping(value = "/delSelectJobAssetResLocationRulesByJobs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delSelectMembershipsForParties(@RequestBody ArrayList<Long> jSeqNos) {
		jobAssetResLocationRulesAdminServ.delSelectJobAssetResLocationRulesByJobs(jSeqNos);		
		return;
	}
	
	@GetMapping(value = "/delSelectJobAssetResLocationRulesByResources", produces = { MediaType.APPLICATION_JSON_VALUE })
	public void delSelectJobAssetResLocationRulesByResources(@RequestBody ArrayList<Long> rSeqNos) {
		jobAssetResLocationRulesAdminServ.delSelectJobAssetResLocationRulesByResources(rSeqNos);		
		return;
	}	
	
	@DeleteMapping("/delAllJobAssetResLocationRules")
	public void deleteAllJobAssetResLocationRules() {
		jobAssetResLocationRulesAdminServ.delAllJobAssetResLocationRules();
	}
	}