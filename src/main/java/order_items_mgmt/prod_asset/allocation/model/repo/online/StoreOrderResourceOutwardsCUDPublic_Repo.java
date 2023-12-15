package order_items_mgmt.prod_asset.allocation.model.repo.online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import common.model.master.StoreOrderResourceOutward;

@Transactional(propagation=Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
@Repository("storeOrderResourceOutwardsCUDPublicRepoOnLine")
public interface StoreOrderResourceOutwardsCUDPublic_Repo extends JpaRepository<StoreOrderResourceOutward, Long> 
{

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_REQUESTED = QTY_REQUESTED + :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void addQtyRequested(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);
	
@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_REQUESTED = QTY_REQUESTED - :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void subQtyRequested(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_REQUESTED = :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void setQtyRequested(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_BOOKED = QTY_BOOKED + :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void addQtyBooked(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_BOOKED = QTY_BOOKED - :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void subQtyBooked(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_BOOKED = :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void setQtyBooked(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_Allocated = QTY_Allocated + :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void addQtyAllocated(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_Allocated = QTY_Allocated - :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void subQtyAllocated(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_Allocated = :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void setQtyAllocated(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_Moved = QTY_Moved + :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void addQtyMoved(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_Moved = QTY_Moved - :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void subQtyMoved(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set QTY_Moved = :qty where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void setQtyMoved(@Param(value = "qty") Float qty, @Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set IS_BOOKED = :st where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void updateBookStatus(@Param(value = "storeReqSeqNo") Long storeReqSeqNo, @Param(value = "st") Character st);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set IS_BOOKED = ' ' where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void clearBookStatus(@Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set OKFLAG = :st where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void updateOKFlag(@Param(value = "storeReqSeqNo") Long storeReqSeqNo, @Param(value = "st") Character st);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set OKFLAG = ' ' where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void clearOKFlag(@Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set DONEFLAG = :st where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void updateDoneFlag(@Param(value = "storeReqSeqNo") Long storeReqSeqNo, @Param(value = "st") Character st);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERRESOURCE_OUTWARDS set DONEFLAG = ' ' where STORE_REQUEST_SEQ_NO = :storeReqSeqNo", nativeQuery = true)
void clearDoneFlag(@Param(value = "storeReqSeqNo") Long storeReqSeqNo);

@Modifying(clearAutomatically = true)
@Query(value="update STORE_ORDERASSET_OUTWARDS set DeleteFLAG = 'Y' where job_work_seq_no = :jobWorkSeqNo", nativeQuery = true)
void markDeleteByJob(@Param(value = "jobWorkSeqNo") Long jobWorkSeqNo);

} 
