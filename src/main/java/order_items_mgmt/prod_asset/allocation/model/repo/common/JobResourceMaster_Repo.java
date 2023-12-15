package order_items_mgmt.prod_asset.allocation.model.repo.common;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.master.JobResourceMaster;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
@Repository("jobResourceMasterRepo")
public interface JobResourceMaster_Repo extends JpaRepository<JobResourceMaster, Long> 
{	
@Query(value = "select * from JOB_RESource_MASTER b where (b.JOB_SEQ_NO=:jobTypeSeqNo and b.TARGET_SEQ_NO=:tSeqNo and b.fr_loc_seq_no=:frLocSeqNo and b.to_loc_seq_no=:toLocSeqNo and b.mode_seq_no=:modeSeqNo)",nativeQuery = true) 
CopyOnWriteArrayList<JobResourceMaster> getResourcesForJobType(@Param("jobTypeSeqNo") Long jobTypeSeqNo, @Param("tSeqNo") Long tSeqNo, @Param("modeSeqNo") Integer modeSeqNo, @Param("frLocSeqNo") Long frLocSeqNo, @Param("toLocSeqNo") Long toLocSeqNo);

@Query(value = "select * from JOB_RESource_MASTER b where (rownum=1 and b.resource_seq_no = :resSeqNo and b.JOB_SEQ_NO=:jobTypeSeqNo and b.TARGET_SEQ_NO=:tSeqNo and b.fr_loc_seq_no=:frLocSeqNo and b.to_loc_seq_no=:toLocSeqNo and b.mode_seq_no=:modeSeqNo)",nativeQuery = true) 
JobResourceMaster getResourceForJobType(@Param("jobTypeSeqNo") Integer jobTypeSeqNo, @Param("tSeqNo") Long tSeqNo, @Param("modeSeqNo") Integer modeSeqNo, @Param("frLocSeqNo") Long frLocSeqNo, @Param("toLocSeqNo") Long toLocSeqNo, @Param("resSeqNo") Long resSeqNo);

@Query(value = "select * from JOB_RESOURCE_MASTER b where (b.resource_seq_no=:resourceSeqNo and b.mode_seq_no=:modeSeqNo and b.JOB_SEQ_NO=:jobSeqNo and b.TARGET_SEQ_NO=:tSeqNo and b.fr_loc_seq_no=:frLocSeqNo and b.to_loc_seq_no=:toLocSeqNo)",nativeQuery = true) 
CopyOnWriteArrayList<JobResourceMaster> getResourcesForJobResourceDetails(@Param("jobSeqNo") Long jobSeqNo,@Param("resourceSeqNo") Long resourceSeqNo,@Param("tSeqNo") Long tSeqNo,@Param("modeSeqNo") Integer modeSeqNo,@Param("frLocSeqNo") Long frLocSeqNo,@Param("toLocSeqNo") Long toLocSeqNo,@Param("jobItemMode") String jobItemMode);
}