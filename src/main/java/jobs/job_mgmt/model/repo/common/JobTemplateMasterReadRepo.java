package jobs.job_mgmt.model.repo.common;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobTemplateMaster;
import common.model.master.JobTemplateMasterPK;

@Repository("jobTemplateMasterReadRepo")
public interface JobTemplateMasterReadRepo extends JpaRepository<JobTemplateMaster, JobTemplateMasterPK> 
{	
	
	@Query(value = "SELECT * JOB_TEMPLATE_MASTER where JOB_SEQ_NO in :jobTemplateMasterSeqNos ORDER BY JOB_TYPE_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobTemplateMaster> getSelectJobTemplateMasters(@Param("jobTemplateMasterSeqNos") CopyOnWriteArrayList<Long> jobTemplateMasterSeqNos);
	
	@Query(value = "SELECT * JOB_TEMPLATE_MASTER where JOB_SEQ_NO in :jobTemplateMasterSeqNos ORDER BY JOB_TYPE_SEQ_NO",nativeQuery = true) 
	CopyOnWriteArrayList<JobTemplateMaster> getJobsForTemplate(@Param("jobTemplateMasterSeqNo") Long jobTemplateMasterSeqNo);
}