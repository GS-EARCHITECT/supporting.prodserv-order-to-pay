package location_mgmt.rules_mgmt.services.admin;

import java.util.ArrayList;
import common.model.dto.JobAssetResLocationRule_DTO;

public interface I_JobAssetResLocationRulesAdmin_Service
{
    public JobAssetResLocationRule_DTO newJobAssetResLocationRule(JobAssetResLocationRule_DTO jobAssetResLocationRule_DTO);
    public ArrayList<JobAssetResLocationRule_DTO> getAllJobAssetResLocationRules();
    public ArrayList<JobAssetResLocationRule_DTO> getSelectJobAssetResLocationRules(ArrayList<Long> jList);
    public ArrayList<JobAssetResLocationRule_DTO> getSelectJobAssetResLocationRulesByJobs(ArrayList<Long> jList);
    public ArrayList<JobAssetResLocationRule_DTO> getSelectJobAssetResLocationRulesByAssets(ArrayList<Long> aList);
    public ArrayList<JobAssetResLocationRule_DTO> getSelectJobAssetResLocationRulesByResources(ArrayList<Long> rList);
    public void updJobAssetResLocationRule(JobAssetResLocationRule_DTO jobAssetResLocationRule_DTO);    
    public void delAllJobAssetResLocationRules();
    public void delSelectJobAssetResLocationRules(ArrayList<Long> aList);
    public void delSelectJobAssetResLocationRulesByJobs(ArrayList<Long> jList);
    public void delSelectJobAssetResLocationRulesByAssets(ArrayList<Long> aList);
    public void delSelectJobAssetResLocationRulesByResources(ArrayList<Long> rList);
  }