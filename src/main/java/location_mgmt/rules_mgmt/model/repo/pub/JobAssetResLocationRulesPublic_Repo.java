package location_mgmt.rules_mgmt.model.repo.pub;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobAssetResLocationRule;

@Repository("jobAssetResLocationRulesPublicRepo")
public interface JobAssetResLocationRulesPublic_Repo extends JpaRepository<JobAssetResLocationRule, Long> 
{
	
	@Query(value = "SELECT * FROM JOBASSETRES_LOCATION_RULES where (rownum=1 and JOB_SEQ_NO = :jSeqNo and resource_seq_no = :rSeqNo and lessthan_distance >= 0)",nativeQuery = true) 
	JobAssetResLocationRule getSelectJobAssetResLocationRulesByJobResource(@Param("jSeqNo") Long jSeqNo, @Param("rSeqNo") Long rSeqNo);

	@Query(value = "SELECT * FROM JOBASSETRES_LOCATION_RULES where JOB_SEQ_NO in :jList ORDER BY JOBASSETRESOURCE_LOC_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobAssetResLocationRule> getSelectJobAssetResLocationRulesByJobs(@Param("jList") CopyOnWriteArrayList<Long> jList);

	@Query(value = "SELECT * FROM JOBASSETRES_LOCATION_RULES where ASSET_SEQ_NO in :aList ORDER BY JOBASSETRESOURCE_LOC_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobAssetResLocationRule> getSelectJobAssetResLocationRulesByAssets(@Param("aList") CopyOnWriteArrayList<Long> aList);

	@Query(value = "SELECT * FROM JOBASSETRES_LOCATION_RULES where RESOURCE_SEQ_NO in :rList ORDER BY JOBASSETRESOURCE_LOC_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobAssetResLocationRule> getSelectJobAssetResLocationRulesByResources(@Param("rList") CopyOnWriteArrayList<Long> rList);
	
	@Query(value = "SELECT * FROM JOBASSETRES_LOCATION_RULES where lessthan_distance > 0 ORDER BY JOBASSETRESOURCE_LOC_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobAssetResLocationRule> getSelectJobAssetResLocationRulesForDistanceRule();
} 
