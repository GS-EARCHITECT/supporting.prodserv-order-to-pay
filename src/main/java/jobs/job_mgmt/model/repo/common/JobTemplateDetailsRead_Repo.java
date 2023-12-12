package jobs.job_mgmt.model.repo.common;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobTemplateDetail;
import common.model.master.JobTemplateDetailPK;

@Repository("jobTemplateDetailsReadRepo")
public interface JobTemplateDetailsRead_Repo extends JpaRepository<JobTemplateDetail, JobTemplateDetailPK> 
{
	@Query(value = "SELECT * JOB_TEMPLATE_DETAILS where JOB_TEMPLATE_SEQ_NO in :jobTemplateDetailsSeqNos ORDER BY JOB_TEMPLATE_SEQ_NO", nativeQuery = true)
	CopyOnWriteArrayList<JobTemplateDetail> getSelectJobTemplateDetails(@Param("jobTemplateDetailsSeqNos") CopyOnWriteArrayList<Long> jobTemplateDetailsSeqNos);
	
	@Query(value = "SELECT * FROM JOB_TEMPLATE_DETAILS where job_template_seq_no = :jobTemplateSeqNo ORDER BY seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobTemplateDetail> getJobsForTemplate(@Param("jobTemplateSeqNo") Long jobTemplateSeqNo);
	
}