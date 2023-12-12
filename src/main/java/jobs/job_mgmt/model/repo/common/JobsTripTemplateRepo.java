package jobs.job_mgmt.model.repo.common;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobTripTemplateDetail;
import common.model.master.JobTripTemplateDetailPK;

@Repository("jobTripsTemplateRepo")
public interface JobsTripTemplateRepo extends JpaRepository<JobTripTemplateDetail, JobTripTemplateDetailPK> 
{

	@Query(value = "SELECT * FROM JOB_TRIPTEMPLATE_DETAILS where (job_template_seq_no = :jobTemplateSeqNo and job_seq_no = :jobSeqNo) ORDER BY job_template_seq_no, job_seq_no, seq_no",nativeQuery = true) 
	CopyOnWriteArrayList<JobTripTemplateDetail> getJobTripsForTemplate(@Param("jobTemplateSeqNo") Long jobTemplateSeqNo, @Param("jobSeqNo") Long jobSeqNo);
	
	@Query(value = "SELECT * FROM JOB_TRIPTEMPLATE_DETAILS where (rownum = 1 and job_template_seq_no = :jobTemplateSeqNo and job_seq_no = :jobSeqNo) ORDER BY job_template_seq_no, job_seq_no, seq_no",nativeQuery = true) 
	JobTripTemplateDetail getJobTripsStatusTemplate(@Param("jobTemplateSeqNo") Long jobTemplateSeqNo, @Param("jobSeqNo") Long jobSeqNo);
	
/*	
 * 
	@Query(value = "INSERT INTO PRODUCT_MASTER (PRODUCT_SEQ_NO,PRODUCT,REMARKS,ASSET_ID,PARENT_PRODUCT_SEQ_NO,STATUS) \r\n" + 
			"	select LOCATION_SEQ_NO.NEXTVAL,'new','myremarks','assetssid',2,'no' from DUAL d\r\n" + 
			"	where not exists (SELECT 1 FROM  PRODUCT_MASTER x WHERE  x.PRODUCT_SEQ_NO = 1 )",nativeQuery = true) 
	void delProjectStatus(@Param("projectSeqNo") Long projectSeqNo);
	*/
}