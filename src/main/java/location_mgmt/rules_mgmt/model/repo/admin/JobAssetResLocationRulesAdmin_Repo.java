package location_mgmt.rules_mgmt.model.repo.admin;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobAssetResLocationRule;

@Repository("jobAssetResLocationRulesAdminRepo")
public interface JobAssetResLocationRulesAdmin_Repo extends JpaRepository<JobAssetResLocationRule, Long> 
{
	@Query(value = "SELECT * FROM JOBASSETRES_LOCATION_RULES where JOB_SEQ_NO in :jList ORDER BY JOBASSETRESOURCE_LOC_SEQ_NO",nativeQuery = true) 
	ArrayList<JobAssetResLocationRule> getSelectJobAssetResLocationRulesByJobs(@Param("jList") ArrayList<Long> jList);

	@Query(value = "SELECT * FROM JOBASSETRES_LOCATION_RULES where ASSET_SEQ_NO in :aList ORDER BY JOBASSETRESOURCE_LOC_SEQ_NO",nativeQuery = true) 
	ArrayList<JobAssetResLocationRule> getSelectJobAssetResLocationRulesByAssets(@Param("aList") ArrayList<Long> aList);

	@Query(value = "SELECT * FROM JOBASSETRES_LOCATION_RULES where RESOURCE_SEQ_NO in :rList ORDER BY JOBASSETRESOURCE_LOC_SEQ_NO",nativeQuery = true) 
	ArrayList<JobAssetResLocationRule> getSelectJobAssetResLocationRulesByResources(@Param("rList") ArrayList<Long> rList);
	
	@Query(value = "DELETE FROM JOBASSETRES_LOCATION_RULES where JOB_SEQ_NO in :jList ",nativeQuery = true) 
	void delSelectJobAssetResLocationRulesByJobs(@Param("jList") ArrayList<Long> jList);

	@Query(value = "DELETE FROM JOBASSETRES_LOCATION_RULES where ASSET_SEQ_NO in :aList ",nativeQuery = true) 
	void delSelectJobAssetResLocationRulesByAssets(@Param("aList") ArrayList<Long> aList);

	@Query(value = "DELETE FROM JOBASSETRES_LOCATION_RULES where RESOURCE_SEQ_NO in :rList ",nativeQuery = true) 
	void delSelectJobAssetResLocationRulesByResources(@Param("rList") ArrayList<Long> rList);

} 
