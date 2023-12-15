package jobs.job_mgmt.model.repo.common;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobStructureTargetDetail;
import common.model.master.JobStructureTargetDetailPK;

@Repository("jobStructureTargetDetailsReadRepo")
public interface JobStructureTargetDetailsRead_Repo extends JpaRepository<JobStructureTargetDetail, JobStructureTargetDetailPK> 
{	
	@Query(value = "SELECT * from JOB_STRUCTURE_TARGET_DETAILS where JOB_SEQ_NO in :jobSeqNos ORDER BY JOB_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobStructureTargetDetail> getSelectJobStructureTargetDetailsForJobs(@Param(value = "jobSeqNos") CopyOnWriteArrayList<Long> jobSeqNos);
	
	@Query(value = "SELECT * from JOB_STRUCTURE_TARGET_DETAILS where (par_JOB_SEQ_NO = :jobSeqNo and par_target_SEQ_NO = :parTargetSeqNo) ORDER BY SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobStructureTargetDetail> getSelectJobStructureTargetDetailsForParent(@Param(value = "jobSeqNo") Long jobSeqNo, @Param(value = "parTargetSeqNo") Long parTargetSeqNo);
	
	@Query(value = "SELECT * from JOB_STRUCTURE_TARGET_DETAILS where (par_JOB_SEQ_NO in :jobSeqNos and par_target_SEQ_NO in :parTargetSeqNos) ORDER BY SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobStructureTargetDetail> getSelectJobStructureTargetDetailForParents(@Param(value = "jobSeqNos") CopyOnWriteArrayList<Long> jobSeqNos, @Param(value = "parTargetSeqNo") CopyOnWriteArrayList<Long> parTargetSeqNos);
	
}