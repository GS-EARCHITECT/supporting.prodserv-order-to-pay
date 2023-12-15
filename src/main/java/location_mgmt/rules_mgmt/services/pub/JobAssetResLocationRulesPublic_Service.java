package location_mgmt.rules_mgmt.services.pub;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.dto.JobAssetResLocationRule_DTO;
import common.model.master.JobAssetResLocationRule;
import location_mgmt.rules_mgmt.model.repo.pub.JobAssetResLocationRulesPublic_Repo;

@Service("jobAssetResLocationRulesPublicServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class JobAssetResLocationRulesPublic_Service implements I_JobAssetResLocationRulesPublic_Service {
	@Autowired
	public JobAssetResLocationRulesPublic_Repo jobAssetResLocationRulesPublicRepo;

	@Autowired
	private Executor asyncExecutor;

	// private static final Logger logger =
	// LoggerFactory.getLogger(CostCalcServ.class);

	public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getAllJobAssetResLocationRules() {
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<JobAssetResLocationRule> JobAssetResLocationRuleList = (CopyOnWriteArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesPublicRepo
							.findAll();
					CopyOnWriteArrayList<JobAssetResLocationRule_DTO> lMasterss = JobAssetResLocationRuleList != null
							? this.getJobAssetResLocationRule_DTOs(JobAssetResLocationRuleList)
							: null;
					return lMasterss;
				}, asyncExecutor);

		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRules(
			CopyOnWriteArrayList<Long> jList) {
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<JobAssetResLocationRule> JobAssetResLocationRuleList = (CopyOnWriteArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesPublicRepo
							.findAllById(jList);
					CopyOnWriteArrayList<JobAssetResLocationRule_DTO> lMasterss = JobAssetResLocationRuleList != null
							? this.getJobAssetResLocationRule_DTOs(JobAssetResLocationRuleList)
							: null;
					return lMasterss;
				}, asyncExecutor);

		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByJobs(
			CopyOnWriteArrayList<Long> jList) {
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<JobAssetResLocationRule> JobAssetResLocationRuleList = (CopyOnWriteArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesPublicRepo
							.getSelectJobAssetResLocationRulesByJobs(jList);
					CopyOnWriteArrayList<JobAssetResLocationRule_DTO> lMasterss = JobAssetResLocationRuleList != null
							? this.getJobAssetResLocationRule_DTOs(JobAssetResLocationRuleList)
							: null;
					return lMasterss;
				}, asyncExecutor);

		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByAssets(
			CopyOnWriteArrayList<Long> aList) {
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<JobAssetResLocationRule> JobAssetResLocationRuleList = (CopyOnWriteArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesPublicRepo
							.getSelectJobAssetResLocationRulesByAssets(aList);
					CopyOnWriteArrayList<JobAssetResLocationRule_DTO> lMasterss = JobAssetResLocationRuleList != null
							? this.getJobAssetResLocationRule_DTOs(JobAssetResLocationRuleList)
							: null;
					return lMasterss;
				}, asyncExecutor);

		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> getSelectJobAssetResLocationRulesByResources(
			CopyOnWriteArrayList<Long> rList) {
		CompletableFuture<CopyOnWriteArrayList<JobAssetResLocationRule_DTO>> future = CompletableFuture
				.supplyAsync(() -> {
					CopyOnWriteArrayList<JobAssetResLocationRule> JobAssetResLocationRuleList = (CopyOnWriteArrayList<JobAssetResLocationRule>) jobAssetResLocationRulesPublicRepo
							.getSelectJobAssetResLocationRulesByResources(rList);
					CopyOnWriteArrayList<JobAssetResLocationRule_DTO> lMasterss = JobAssetResLocationRuleList != null
							? this.getJobAssetResLocationRule_DTOs(JobAssetResLocationRuleList)
							: null;
					return lMasterss;
				}, asyncExecutor);

		return future;
	}

	private synchronized CopyOnWriteArrayList<JobAssetResLocationRule_DTO> getJobAssetResLocationRule_DTOs(
			CopyOnWriteArrayList<JobAssetResLocationRule> lMasters) {
		JobAssetResLocationRule_DTO lDTO = null;
		CopyOnWriteArrayList<JobAssetResLocationRule_DTO> lMasterDTOs = new CopyOnWriteArrayList<JobAssetResLocationRule_DTO>();
		for (int i = 0; i < lMasters.size(); i++) {
			lDTO = getJobAssetResLocationRule_DTO(lMasters.get(i));
			lMasterDTOs.add(lDTO);
		}
		return lMasterDTOs;
	}

	private synchronized JobAssetResLocationRule_DTO getJobAssetResLocationRule_DTO(JobAssetResLocationRule lMaster) {
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

}
