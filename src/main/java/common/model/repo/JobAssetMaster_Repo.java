package common.model.repo;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import common.model.master.JobAssetMaster;

@Repository("jobAssetMasterRepoCommon")
public interface JobAssetMaster_Repo extends JpaRepository<JobAssetMaster, Long> 
{	
@Query(value = "select * from JOB_ASSET_MASTER b where (b.mode_seq_no=:modeSeqNo and b.JOB_SEQ_NO=:jobSeqNo and b.TARGET_SEQ_NO=:tSeqNo and b.fr_loc_seq_no=:frLocSeqNo and b.to_loc_seq_no=:toLocSeqNo)",nativeQuery = true) 
CopyOnWriteArrayList<JobAssetMaster> getAssetsForJobType(@Param("jobSeqNo") Long jobSeqNo, @Param("tSeqNo") Long tSeqNo, @Param("modeSeqNo") Integer modeSeqNo, @Param("frLocSeqNo") Long frLocSeqNo, @Param("toLocSeqNo") Long toLocSeqNo);

@Query(value = "select * from JOB_ASSET_MASTER b where (rownum=1 and b.asset_seq_no= :assetSeqNo and b.mode_seq_no=:modeSeqNo and b.JOB_SEQ_NO=:jobSeqNo and b.TARGET_SEQ_NO=:tSeqNo and b.fr_loc_seq_no=:frLocSeqNo and b.to_loc_seq_no=:toLocSeqNo)",nativeQuery = true) 
JobAssetMaster getAssetForJobType(@Param("jobSeqNo") Long jobSeqNo, @Param("tSeqNo") Long tSeqNo, @Param("modeSeqNo") Integer modeSeqNo, @Param("frLocSeqNo") Long frLocSeqNo, @Param("toLocSeqNo") Long toLocSeqNo, @Param("assetSeqNo") Long assetSeqNo);

@Query(value = "select * from JOB_ASSET_MASTER b where (b.asset_seq_no=:assetSeqNo and b.mode_seq_no=:modeSeqNo and b.JOB_SEQ_NO=:jobSeqNo and b.TARGET_SEQ_NO=:tSeqNo and b.fr_loc_seq_no=:frLocSeqNo and b.to_loc_seq_no=:toLocSeqNo)",nativeQuery = true) 
CopyOnWriteArrayList<JobAssetMaster> getAssetsForJobAssetDetails(@Param("jobSeqNo") Long jobSeqNo,@Param("assetSeqNo") Long assetSeqNo,@Param("tSeqNo") Long tSeqNo,@Param("modeSeqNo") Integer modeSeqNo,@Param("frLocSeqNo") Long frLocSeqNo,@Param("toLocSeqNo") Long toLocSeqNo,@Param("jobItemMode") String jobItemMode);

@Query(value = "select count(*) from JOB_ASSET_MASTER b where (b.asset_seq_no=:assetSeqNo and b.mode_seq_no=:modeSeqNo and b.JOB_SEQ_NO=:jobSeqNo and b.TARGET_SEQ_NO=:tSeqNo and b.fr_loc_seq_no=:frLocSeqNo and b.to_loc_seq_no=:toLocSeqNo)",nativeQuery = true) 
Float getCountOfAssetsForJobAssetDetails(@Param("jobSeqNo") Long jobSeqNo,@Param("assetSeqNo") Long assetSeqNo,@Param("tSeqNo") Long tSeqNo,@Param("modeSeqNo") Integer modeSeqNo,@Param("frLocSeqNo") Long frLocSeqNo,@Param("toLocSeqNo") Long toLocSeqNo,@Param("jobItemMode") String jobItemMode);

@Query(value = "select * from JOB_ASSET_MASTER b where (rownum=1 and b.asset_seq_no=:assetSeqNo and b.mode_seq_no=:modeSeqNo and b.JOB_SEQ_NO=:jobSeqNo and b.TARGET_SEQ_NO=:tSeqNo and b.fr_loc_seq_no=:frLocSeqNo and b.to_loc_seq_no=:toLocSeqNo)",nativeQuery = true) 
JobAssetMaster getAssetForJobAssetDetails(@Param("jobSeqNo") Long jobSeqNo,@Param("assetSeqNo") Long assetSeqNo,@Param("tSeqNo") Long tSeqNo,@Param("modeSeqNo") Integer modeSeqNo,@Param("frLocSeqNo") Long frLocSeqNo,@Param("toLocSeqNo") Long toLocSeqNo,@Param("jobItemMode") String jobItemMode);
}