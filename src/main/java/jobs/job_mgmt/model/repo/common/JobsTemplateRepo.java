package jobs.job_mgmt.model.repo.common;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobTemplateDetail;
import common.model.master.JobTemplateDetailPK;
import common.model.master.JobTemplateMaster;

@Repository("jobTemplateRepo")
public interface JobsTemplateRepo extends JpaRepository<JobTemplateDetail, JobTemplateDetailPK> 
{
	
	@Query(value = "SELECT * FROM JOBTEMPLATE_DETAILS where job_template_seq_no = :jobTemplateSeqNo ORDER BY seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobTemplateDetail> getJobsForTemplate(@Param("jobTemplateSeqNo") Integer jobTemplateSeqNo);
	
	@Query(value = "SELECT * FROM JOBTEMPLATE_DETAILS where (ROWNUM = 1 and job_template_seq_no = :jobTemplateSeqNo and job_type_seq_no= :jobTypeSeqNo and seq_no = :seqNo and target_type_seq_no = :tTypeSeqNo)",nativeQuery = true) 
	CopyOnWriteArrayList<JobTemplateDetail> getJobTemplateForType(@Param("jobTemplateSeqNo ") Integer jobTemplateSeqNo, @Param("jobTypeSeqNo") Integer jobTypeSeqNo, @Param("seqNo") Integer seqNo, @Param("tTypeSeqNo") Integer tTypeSeqNo);
	
	@Query(value = "SELECT * FROM JOBTEMPLATE_MASTER where SERVICE_CATEGORY_SEQ_NO = :servCatSeqNo ",nativeQuery = true) 
	CopyOnWriteArrayList<JobTemplateMaster> getJobTemplatesForServiceCat(@Param("servCatSeqNo") Integer servCatSeqNo);
	
	@Query(value = "SELECT * FROM JOBTEMPLATE_DETAILS where (ROWNUM = 1 and job_template_seq_no = :jobTemplateSeqNo and job_type_seq_no= :jobTypeSeqNo and seq_no = :seqNo)",nativeQuery = true) 
	Optional<JobTemplateDetail> getJobDuration(@Param("jobTemplateSeqNo ") Integer jobTemplateSeqNo, @Param("jobTypeSeqNo") Integer jobTypeSeqNo);
	
	
/*	
	@Query(value = "INSERT INTO LOGI_PRODUCT_MASTER (PRODUCT_SEQ_NO,PRODUCT,REMARKS,ASSET_ID,PARENT_PRODUCT_SEQ_NO,STATUS) \r\n" + 
			"	select LOGI_LOCATION_SEQ_NO.NEXTVAL,'new','myremarks','assetssid',2,'no' from DUAL d\r\n" + 
			"	where not exists (SELECT 1 FROM  LOGI_PRODUCT_MASTER x WHERE  x.PRODUCT_SEQ_NO = 1 )",nativeQuery = true) 
	void delProjectStatus(@Param("projectSeqNo") Integer projectSeqNo);
	*/
}