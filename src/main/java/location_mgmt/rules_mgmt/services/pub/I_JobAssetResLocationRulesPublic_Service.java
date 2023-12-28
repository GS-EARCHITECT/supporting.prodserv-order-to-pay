package location_mgmt.rules_mgmt.services.pub;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import common.model.dto.JobAssetResLocationRule_DTO;

public interface I_JobAssetResLocationRulesPublic_Service
{
    public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getAllJobAssetResLocationRules();
    public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRules(CopyOnWriteArrayList<Long> jList);
    public CompletableFuture<JobAssetResLocationRule_DTO> getSelectJobAssetResLocationRuleByJobResource(Long jSeqNo, Long rSeqNo);
    public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByJobs(CopyOnWriteArrayList<Long> jList);
    public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByAssets(CopyOnWriteArrayList<Long> aList);
    public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByResources(CopyOnWriteArrayList<Long> rList);
  }