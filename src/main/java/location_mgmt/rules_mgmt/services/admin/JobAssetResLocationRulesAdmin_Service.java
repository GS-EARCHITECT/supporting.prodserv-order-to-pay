package location_mgmt.rules_mgmt.services.admin;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.dto.JobAssetResLocationRule_DTO;
import common.model.master.JobAssetResLocationRule;
import location_mgmt.rules_mgmt.model.repo.admin.JobAssetResLocationRulesAdmin_Repo;

@Service("jobAssetResLocationRulesAdminServ")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobAssetResLocationRulesAdmin_Service implements I_JobAssetResLocationRulesAdmin_Service
{
	@Autowired
	public JobAssetResLocationRulesAdmin_Repo jobAssetResLocationRulesAdminRepo;

	// private static final Logger logger =
	// LoggerFactory.getLogger(CostCalcServ.class);

	public JobAssetResLocationRule_DTO newJobAssetResLocationRule(JobAssetResLocationRule_DTO lMasters) 
	{
		JobAssetResLocationRule jobAssetResLocationRule = jobAssetResLocationRulesAdminRepo.save(this.setJobAssetResLocationRule(lMasters));
		lMasters = getJobAssetResLocationRule_DTO(jobAssetResLocationRule);
		return lMasters;
	}

	public ArrayList<JobAssetResLocationRule_DTO> getAllJobAssetResLocationRules() {
		ArrayList<JobAssetResLocationRule> assetSensorList = (ArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesAdminRepo.findAll();
		ArrayList<JobAssetResLocationRule_DTO> lMasterss = assetSensorList != null ? this.getJobAssetResLocationRule_DTOs(assetSensorList) : null;
		return lMasterss;
	}

	public ArrayList<JobAssetResLocationRule_DTO> getSelectJobAssetResLocationRules(ArrayList<Long> ids) {
		ArrayList<JobAssetResLocationRule> lMasters = (ArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesAdminRepo.findAllById(ids);
		ArrayList<JobAssetResLocationRule_DTO> jobAssetResLocationRule_DTOs = lMasters != null ? this.getJobAssetResLocationRule_DTOs(lMasters) : null;
		return jobAssetResLocationRule_DTOs;
	}
	
    public ArrayList<JobAssetResLocationRule_DTO> getSelectJobAssetResLocationRulesByJobs(ArrayList<Long> jList)
    {
		ArrayList<JobAssetResLocationRule> lMasters = (ArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesAdminRepo.getSelectJobAssetResLocationRulesByJobs(jList);;
		ArrayList<JobAssetResLocationRule_DTO> jobAssetResLocationRule_DTOs = lMasters != null ? this.getJobAssetResLocationRule_DTOs(lMasters) : null;
		return jobAssetResLocationRule_DTOs;
	}
    
    public ArrayList<JobAssetResLocationRule_DTO> getSelectJobAssetResLocationRulesByAssets(ArrayList<Long> aList)
    {
		ArrayList<JobAssetResLocationRule> lMasters = (ArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesAdminRepo.getSelectJobAssetResLocationRulesByAssets(aList);
		ArrayList<JobAssetResLocationRule_DTO> jobAssetResLocationRule_DTOs = lMasters != null ? this.getJobAssetResLocationRule_DTOs(lMasters) : null;
		return jobAssetResLocationRule_DTOs;
	}
    
    public ArrayList<JobAssetResLocationRule_DTO> getSelectJobAssetResLocationRulesByResources(ArrayList<Long> rList)
    {
		ArrayList<JobAssetResLocationRule> lMasters = (ArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesAdminRepo.getSelectJobAssetResLocationRulesByResources(rList);
		ArrayList<JobAssetResLocationRule_DTO> jobAssetResLocationRule_DTOs = lMasters != null ? this.getJobAssetResLocationRule_DTOs(lMasters) : null;
		return jobAssetResLocationRule_DTOs;
	}

	
	
	
	public void updJobAssetResLocationRule(JobAssetResLocationRule_DTO lMaster) 
	{
		JobAssetResLocationRule jobAssetResLocationRule = this.setJobAssetResLocationRule(lMaster);
		
		if (jobAssetResLocationRulesAdminRepo.existsById(lMaster.getJobassetresourceLocSeqNo())) 
		{
			jobAssetResLocationRulesAdminRepo.save(jobAssetResLocationRule);
		}
		return;
	}

	public void delAllJobAssetResLocationRules() {
		jobAssetResLocationRulesAdminRepo.deleteAll();
	}

	public void delSelectJobAssetResLocationRules(ArrayList<Long> jobAssetResLocationRulePKs)
	{
		if (jobAssetResLocationRulePKs != null) {
			jobAssetResLocationRulesAdminRepo.deleteAllById(jobAssetResLocationRulePKs);
		}
	}

	public void delSelectJobAssetResLocationRulesByJobs(ArrayList<Long> jList)
	{
		if (jList != null) {
			jobAssetResLocationRulesAdminRepo.delSelectJobAssetResLocationRulesByJobs(jList);
		}
	}

	public void delSelectJobAssetResLocationRulesByAssets(ArrayList<Long> aList)
	{
		if (aList != null) {
			jobAssetResLocationRulesAdminRepo.delSelectJobAssetResLocationRulesByAssets(aList);
		}
	}
	
	public void delSelectJobAssetResLocationRulesByResources(ArrayList<Long> rList)
	{
		if (rList != null) {
			jobAssetResLocationRulesAdminRepo.delSelectJobAssetResLocationRulesByResources(rList);
		}
	}
	
	private ArrayList<JobAssetResLocationRule_DTO> getJobAssetResLocationRule_DTOs(ArrayList<JobAssetResLocationRule> lMasters) {
		JobAssetResLocationRule_DTO lDTO = null;
		ArrayList<JobAssetResLocationRule_DTO> lMasterDTOs = new ArrayList<JobAssetResLocationRule_DTO>();
		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getJobAssetResLocationRule_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}
	
	

	private JobAssetResLocationRule_DTO getJobAssetResLocationRule_DTO(JobAssetResLocationRule lMaster) 
	{
		JobAssetResLocationRule_DTO lDTO = new JobAssetResLocationRule_DTO();
		lDTO.setAnylocFlag(lMaster.getAnylocFlag());		
		lDTO.setAssetSeqNo(lMaster.getAssetSeqNo());
		lDTO.setExactFlag(lMaster.getExactFlag());
		lDTO.setJobassetresourceLocSeqNo(lMaster.getJobassetresourceLocSeqNo());
		lDTO.setJobSeqNo(lMaster.getJobSeqNo());
		lDTO.setLessthanDistance(lMaster.getLessthanDistance());
		lDTO.setResourceSeqNo(lMaster.getResourceSeqNo());
		return lDTO;
	}

	private JobAssetResLocationRule setJobAssetResLocationRule(JobAssetResLocationRule_DTO lDTO) {
		JobAssetResLocationRule lMaster = new JobAssetResLocationRule();		 
		lMaster.setAnylocFlag(lDTO.getAnylocFlag());		
		lMaster.setAssetSeqNo(lDTO.getAssetSeqNo());
		lMaster.setExactFlag(lDTO.getExactFlag());		
		lMaster.setJobSeqNo(lDTO.getJobSeqNo());
		lMaster.setLessthanDistance(lDTO.getLessthanDistance());
		lMaster.setResourceSeqNo(lDTO.getResourceSeqNo());
		return lMaster;
	}

}
