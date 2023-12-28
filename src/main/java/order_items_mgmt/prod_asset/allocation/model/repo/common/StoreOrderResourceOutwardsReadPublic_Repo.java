package order_items_mgmt.prod_asset.allocation.model.repo.common;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.master.StoreOrderResourceOutward;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
@Repository("storeOrderResourceOutwardsReadPublicRepo")
public interface StoreOrderResourceOutwardsReadPublic_Repo extends JpaRepository<StoreOrderResourceOutward, Long> 
{

//Requested Status
@Query(value = "SELECT is_booked FROM STORE_ORDERRESOURCE_OUTWARDS where STORE_REQUEST_SEQ_NO=:storeReqSeqNo",nativeQuery = true) 
Character getIsbookedStatus(@Param("storeReqSeqNo") Long storeReqSeqNo);

@Query(value = "SELECT doneflag FROM STORE_ORDERRESOURCE_OUTWARDS where STORE_REQUEST_SEQ_NO=:storeReqSeqNo",nativeQuery = true) 
Character getIsDoneStatus(@Param("storeReqSeqNo") Long storeReqSeqNo);

@Query(value = "SELECT okflag FROM STORE_ORDERRESOURCE_OUTWARDS where STORE_REQUEST_SEQ_NO=:storeReqSeqNo",nativeQuery = true) 
Character getIsOkStatus(@Param("storeReqSeqNo") Long storeReqSeqNo);

//Requested QTY Queries
@Query(value = "SELECT COALESCE(qty_Requested,0) FROM STORE_ORDERRESOURCE_OUTWARDS where STORE_REQUEST_SEQ_NO=:storeReqSeqNo",nativeQuery = true) 
Float getOrderRequestedQty(@Param("storeReqSeqNo") Long storeReqSeqNo);

@Query(value = "SELECT COALESCE(SUM(qty_requested),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO= :resourceSeqNo and job_work_SEQ_NO = :jobWorkSeqNo and mode_txn = :mode and upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
Float getTotalRequestedForJob(@Param("jobWorkSeqNo") Long jobWorkSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_requested),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and STORE_REQUEST_SEQ_NO < :storeReqSeqNo and mode_txn= :mode",nativeQuery = true) 
Float getTotalQtyRequestedBeforeThisRequest(@Param("storeReqSeqNo") Long storeReqSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_requested),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and FROM_DTTM < :dTTm and mode_txn= :mode",nativeQuery = true) 
Float getTotalQtyRequestedBeforeDTTM(@Param("dTTm") Timestamp dTTm, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_reqquested),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and STORE_REQUEST_SEQ_NO<:storeReqSeqNo and upper(trim(doneflag))<> 'Y' and (FROM_DTTM < :dTTm and TO_DTTM = null)",nativeQuery = true) 
Float getTotalQtyRequestedForResourceBeforeThisRequestAnyModeWithDates(@Param("storeReqSeqNo") Long storeReqSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("dTTm") Timestamp dTTm);

// Allocated QTY Queries
@Query(value = "SELECT COALESCE(qty_Allocated,0) FROM STORE_ORDERRESOURCE_OUTWARDS where STORE_REQUEST_SEQ_NO=:storeReqSeqNo",nativeQuery = true) 
Float getOrderAllocatedQty(@Param("storeReqSeqNo") Long storeReqSeqNo);

@Query(value = "SELECT COALESCE(SUM(qty_Allocated),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO= :resourceSeqNo and job_work_SEQ_NO = :jobWorkSeqNo and mode_txn = :mode and upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
Float getTotalAllocatedForJob(@Param("jobWorkSeqNo") Long jobWorkSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_Allocated),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and STORE_REQUEST_SEQ_NO < :storeReqSeqNo and mode_txn= :mode",nativeQuery = true) 
Float getTotalQtyAllocatedBeforeThisRequest(@Param("storeReqSeqNo") Long storeReqSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_Allocated),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and FROM_DTTM < :dTTm and mode_txn= :mode",nativeQuery = true) 
Float getTotalQtyAllocatedBeforeDTTM(@Param("dTTm") Timestamp dTTm, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_Allocated),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and STORE_REQUEST_SEQ_NO<:storeReqSeqNo and upper(trim(doneflag))<> 'Y' and (FROM_DTTM < :dTTm and TO_DTTM = null)",nativeQuery = true) 
Float getTotalQtyAllocatedForResourceBeforeThisRequestAnyModeWithDates(@Param("storeReqSeqNo") Long storeReqSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("dTTm") Timestamp dTTm);

//Booked QTY Queries
@Query(value = "SELECT COALESCE(qty_Booked,0) FROM STORE_ORDERRESOURCE_OUTWARDS where STORE_REQUEST_SEQ_NO=:storeReqSeqNo",nativeQuery = true) 
Float getOrderBookedQty(@Param("storeReqSeqNo") Long storeReqSeqNo);

@Query(value = "SELECT COALESCE(SUM(qty_Booked),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO= :resourceSeqNo and job_work_SEQ_NO = :jobWorkSeqNo and mode_txn = :mode and upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
Float getTotalBookedForJob(@Param("jobWorkSeqNo") Long jobWorkSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_Booked),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and STORE_REQUEST_SEQ_NO < :storeReqSeqNo and mode_txn= :mode",nativeQuery = true) 
Float getTotalQtyBookedBeforeThisRequest(@Param("storeReqSeqNo") Long storeReqSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_Booked),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and FROM_DTTM < :dTTm and mode_txn= :mode",nativeQuery = true) 
Float getTotalQtyBookedBeforeDTTM(@Param("dTTm") Timestamp dTTm, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

//Moved QTY Queries
@Query(value = "SELECT COALESCE(SUM(qty_moved),0) FROM STORE_ORDERRESOURCE_OUTWARDS where STORE_REQUEST_SEQ_NO=:storeReqSeqNo",nativeQuery = true) 
Float getMovedQtyForRequest(@Param("storeReqSeqNo") Long storeReqSeqNo);

@Query(value = "SELECT COALESCE(SUM(qty_moved),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and job_work_SEQ_NO< :jobWorkSeqNo and mode_txn = :mode and upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
Float getTotalQtyMovedForResourceForJob(@Param("jobWorkSeqNo") Long jobWorkSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_moved),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and STORE_REQUEST_SEQ_NO<:storeReqSeqNo and mode_txn = :mode and upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
Float getTotalQtyMovedForResourceBeforeThisRequest(@Param("storeReqSeqNo") Long storeReqSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_moved),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and STORE_REQUEST_SEQ_NO<:storeReqSeqNo and mode_txn = :mode and FROM_DTTM < :dTTm  and upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
Float getTotalRowsMovedForResourcesBeforeThisDTTM(@Param("dTTm") Timestamp dTTm, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(SUM(qty_moved),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and STORE_REQUEST_SEQ_NO<:storeReqSeqNo and upper(trim(doneflag))<> 'Y' and (FROM_DTTM < :dTTm and TO_DTTM = null)",nativeQuery = true) 
Float getTotalQtyMovedForResourceBeforeThisRequestAnyModeWithDates(@Param("storeReqSeqNo") Long storeReqSeqNo, @Param("resourceSeqNo") Long resourceSeqNo, @Param("dTTm") Timestamp dTTm);

//Other QTY Queries
@Query(value = "SELECT COALESCE(count(*),0) FROM STORE_ORDERRESOURCE_OUTWARDS",nativeQuery = true) 
Float getTotalRowCount();

@Query(value = "SELECT * FROM STORE_ORDERRESOURCE_OUTWARDS where upper(trim(doneflag))<> 'Y' ORDER BY STORE_REQUEST_SEQ_NO, Resource_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<StoreOrderResourceOutward> getAllOrderResourceOutwards();

@Query(value = "SELECT * FROM STORE_ORDERRESOURCE_OUTWARDS  where MODE_TXN= :mode and upper(trim(doneflag))<> 'Y' ORDER BY STORE_REQUEST_SEQ_NO, Resource_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<StoreOrderResourceOutward> getAllRowsForMode(@Param("mode") Integer mode);

@Query(value = "SELECT store_request_seq_no FROM STORE_ORDERRESOURCE_OUTWARDS where MODE_TXN=:mode and upper(trim(doneflag))<> 'Y'  ORDER BY STORE_REQUEST_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<Long> getAllSeqNosForMode(@Param("mode") Long mode);

@Query(value = "SELECT store_request_seq_no FROM STORE_ORDERRESOURCE_OUTWARDS where MODE_TXN=:mode and upper(trim(doneflag))<> 'Y'  ORDER BY STORE_REQUEST_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<Long> getAllSeqNosForMode(@Param("mode") Integer mode);

@Query(value = "SELECT COALESCE(count(*),0) FROM STORE_ORDERRESOURCE_OUTWARDS where Resource_SEQ_NO=:resourceSeqNo and mode_txn = :mode and FROM_DTTM < :dTTm  and upper(trim(doneflag))<> 'Y'",nativeQuery = true) 
Float getTotalRowsForResourcesBeforeThisDTTM(@Param("dTTm") Timestamp dTTm, @Param("resourceSeqNo") Long resourceSeqNo, @Param("mode") Integer mode);

@Query(value = "SELECT * FROM STORE_ORDERRESOURCE_OUTWARDS  where job_work_seq_no in :jWorkList ORDER BY STORE_REQUEST_SEQ_NO, resource_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<StoreOrderResourceOutward> getRowsForJobWorks(@Param("jWorkList") CopyOnWriteArrayList<Long> jWorkList);

@Query(value = "SELECT * FROM STORE_ORDERRESOURCE_OUTWARDS  where job_work_seq_no in :jWorkList and upper(trim(doneflag))= 'Y' ORDER BY STORE_REQUEST_SEQ_NO, resource_SEQ_NO",nativeQuery = true) 
CopyOnWriteArrayList<StoreOrderResourceOutward> getRowsForJobWorksDone(@Param("jWorkList") CopyOnWriteArrayList<Long> jWorkList);
} 

