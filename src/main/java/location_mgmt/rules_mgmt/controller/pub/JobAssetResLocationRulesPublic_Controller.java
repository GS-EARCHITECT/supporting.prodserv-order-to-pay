package location_mgmt.rules_mgmt.controller.pub;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import common.model.dto.JobAssetResLocationRule_DTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import location_mgmt.rules_mgmt.services.pub.I_JobAssetResLocationRulesPublic_Service;

@RestController
@RequestMapping("/jobAssetResLocationRulesPublicMgmt")
public class JobAssetResLocationRulesPublic_Controller 
{

	//private static final Logger logger = LoggerFactory.getLogger(JobAssetResLocationRule_JobAssetResLocationRule_Cuntroller.class);

	@Autowired
	private I_JobAssetResLocationRulesPublic_Service jobAssetResLocationRulesPublicServ;
	
	@GetMapping(value = "/getAllJobAssetResLocationRules", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getAllJobAssetResLocationRule_DTOs() 
	{
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = jobAssetResLocationRulesPublicServ.getAllJobAssetResLocationRules();
		CopyOnWriteArrayList<JobAssetResLocationRule_DTO> JobAssetResLocationRuleDTOs = future.join();		
		return new ResponseEntity<>(JobAssetResLocationRuleDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectJobAssetResLocationRules", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRules(@RequestBody CopyOnWriteArrayList<Long> jobAssetResLocationRuleCuSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = jobAssetResLocationRulesPublicServ.getSelectJobAssetResLocationRules(jobAssetResLocationRuleCuSeqNos);		
		CopyOnWriteArrayList<JobAssetResLocationRule_DTO> JobAssetResLocationRuleDTOs = future.join();
		return new ResponseEntity<>(JobAssetResLocationRuleDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectJobAssetResLocationRulesByJobs", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByJobs(@RequestBody CopyOnWriteArrayList<Long> jSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = jobAssetResLocationRulesPublicServ.getSelectJobAssetResLocationRulesByJobs(jSeqNos);		
		CopyOnWriteArrayList<JobAssetResLocationRule_DTO> JobAssetResLocationRuleDTOs = future.join();
		return new ResponseEntity<>(JobAssetResLocationRuleDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectJobAssetResLocationRulesByAssets", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByAssets(@RequestBody CopyOnWriteArrayList<Long> aSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = jobAssetResLocationRulesPublicServ.getSelectJobAssetResLocationRulesByAssets(aSeqNos);		
		CopyOnWriteArrayList<JobAssetResLocationRule_DTO> JobAssetResLocationRuleDTOs = future.join();
		return new ResponseEntity<>(JobAssetResLocationRuleDTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectJobAssetResLocationRulesByResources", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByResources(@RequestBody CopyOnWriteArrayList<Long> rSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = jobAssetResLocationRulesPublicServ.getSelectJobAssetResLocationRulesByResources(rSeqNos);		
		CopyOnWriteArrayList<JobAssetResLocationRule_DTO> JobAssetResLocationRuleDTOs = future.join();
		return new ResponseEntity<>(JobAssetResLocationRuleDTOs, HttpStatus.OK);
	}
	
	}