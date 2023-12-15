package common.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the STORE_ORDERRESOURCE_INWARDS database table.
 * 
 */
@Entity
@Table(name = "STORE_ORDERRESOURCE_OUTWARDS")
public class StoreOrderResourceOutward implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -206530073046086260L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_REQUEST_SEQUENCE")
	@SequenceGenerator(name = "STORE_REQUEST_SEQUENCE", sequenceName = "STORE_REQUEST_SEQUENCE", allocationSize = 1)
	@Column(name = "STORE_REQUEST_SEQ_NO")
	private Long storeRequestSeqNo;

	@Column(name = "DELETEFLAG")
	private Character deleteflag;

	@Column(name = "DONEFLAG")
	private Character doneflag;

	@Column(name = "FR_LOCATION_SEQ_N")
	private Long frLocationSeqN;

	@Column(name = "FROM_DTTM")
	private Timestamp fromDttm;

	@Column(name = "IS_BOOKED")
	private Character isBooked;

	@Column(name = "JOB_WORK_SEQ_NO")
	private Long jobWorkSeqNo;

	@Column(name = "LOCATION_SEQ_NO")
	private Long locationSeqNo;

	@Column(name = "MODE_TXN")
	private Integer modeTxn;

	@Column(name = "MOVED_QTY")
	private Float movedQty;

	@Column(name = "OKFLAG")
	private Character okflag;

	@Column(name = "QTY_ALLOCATED")
	private Float qtyAllocated;

	@Column(name = "QTY_BOOKED")
	private Float qtyBooked;

	@Column(name = "QTY_REQUESTED")
	private Float qtyRequested;

	@Column(name = "QTY_UNIT_SEQ_NO")
	private Long qtyUnitSeqNo;

	@Column(name = "REQUESTED_TO_PARTY_SEQ_NO")
	private Long requestedToPartySeqNo;

	@Column(name = "REQUESTOR_SEQ_NO")
	private Long requestorSeqNo;

	@Column(name = "RESOURCE_SEQ_NO")
	private Long resourceSeqNo;

	@Column(name = "TARGET_SEQ_NO")
	private Long targetSeqNo;

	@Column(name = "TARGET_WORK_SEQ_NO")
	private Long targetWorkSeqNo;

	@Column(name = "TO_DTTM")
	private Timestamp toDttm;

	@Column(name = "TO_LOCATION_SEQ_NO")
	private Long toLocationSeqNo;

	public Long getStoreRequestSeqNo() {
		return storeRequestSeqNo;
	}

	public void setStoreRequestSeqNo(Long storeRequestSeqNo) {
		this.storeRequestSeqNo = storeRequestSeqNo;
	}

	public Character getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Character deleteflag) {
		this.deleteflag = deleteflag;
	}

	public Character getDoneflag() {
		return doneflag;
	}

	public void setDoneflag(Character doneflag) {
		this.doneflag = doneflag;
	}

	public Long getFrLocationSeqN() {
		return frLocationSeqN;
	}

	public void setFrLocationSeqN(Long frLocationSeqN) {
		this.frLocationSeqN = frLocationSeqN;
	}

	public Timestamp getFromDttm() {
		return fromDttm;
	}

	public void setFromDttm(Timestamp fromDttm) {
		this.fromDttm = fromDttm;
	}

	public Character getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Character isBooked) {
		this.isBooked = isBooked;
	}

	public Long getJobWorkSeqNo() {
		return jobWorkSeqNo;
	}

	public void setJobWorkSeqNo(Long jobWorkSeqNo) {
		this.jobWorkSeqNo = jobWorkSeqNo;
	}

	public Long getLocationSeqNo() {
		return locationSeqNo;
	}

	public void setLocationSeqNo(Long locationSeqNo) {
		this.locationSeqNo = locationSeqNo;
	}

	public Integer getModeTxn() {
		return modeTxn;
	}

	public void setModeTxn(Integer modeTxn) {
		this.modeTxn = modeTxn;
	}

	public Float getMovedQty() {
		return movedQty;
	}

	public void setMovedQty(Float movedQty) {
		this.movedQty = movedQty;
	}

	public Character getOkflag() {
		return okflag;
	}

	public void setOkflag(Character okflag) {
		this.okflag = okflag;
	}

	public Float getQtyAllocated() {
		return qtyAllocated;
	}

	public void setQtyAllocated(Float qtyAllocated) {
		this.qtyAllocated = qtyAllocated;
	}

	public Float getQtyBooked() {
		return qtyBooked;
	}

	public void setQtyBooked(Float qtyBooked) {
		this.qtyBooked = qtyBooked;
	}

	public Float getQtyRequested() {
		return qtyRequested;
	}

	public void setQtyRequested(Float qtyRequested) {
		this.qtyRequested = qtyRequested;
	}

	public Long getQtyUnitSeqNo() {
		return qtyUnitSeqNo;
	}

	public void setQtyUnitSeqNo(Long qtyUnitSeqNo) {
		this.qtyUnitSeqNo = qtyUnitSeqNo;
	}

	public Long getRequestedToPartySeqNo() {
		return requestedToPartySeqNo;
	}

	public void setRequestedToPartySeqNo(Long requestedToPartySeqNo) {
		this.requestedToPartySeqNo = requestedToPartySeqNo;
	}

	public Long getRequestorSeqNo() {
		return requestorSeqNo;
	}

	public void setRequestorSeqNo(Long requestorSeqNo) {
		this.requestorSeqNo = requestorSeqNo;
	}

	public Long getResourceSeqNo() {
		return resourceSeqNo;
	}

	public void setResourceSeqNo(Long resourceSeqNo) {
		this.resourceSeqNo = resourceSeqNo;
	}

	public Long getTargetSeqNo() {
		return targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public Long getTargetWorkSeqNo() {
		return targetWorkSeqNo;
	}

	public void setTargetWorkSeqNo(Long targetWorkSeqNo) {
		this.targetWorkSeqNo = targetWorkSeqNo;
	}

	public Timestamp getToDttm() {
		return toDttm;
	}

	public void setToDttm(Timestamp toDttm) {
		this.toDttm = toDttm;
	}

	public Long getToLocationSeqNo() {
		return toLocationSeqNo;
	}

	public void setToLocationSeqNo(Long toLocationSeqNo) {
		this.toLocationSeqNo = toLocationSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobWorkSeqNo == null) ? 0 : jobWorkSeqNo.hashCode());
		result = prime * result + ((resourceSeqNo == null) ? 0 : resourceSeqNo.hashCode());
		result = prime * result + ((storeRequestSeqNo == null) ? 0 : storeRequestSeqNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreOrderResourceOutward other = (StoreOrderResourceOutward) obj;
		if (jobWorkSeqNo == null) {
			if (other.jobWorkSeqNo != null)
				return false;
		} else if (!jobWorkSeqNo.equals(other.jobWorkSeqNo))
			return false;
		if (resourceSeqNo == null) {
			if (other.resourceSeqNo != null)
				return false;
		} else if (!resourceSeqNo.equals(other.resourceSeqNo))
			return false;
		if (storeRequestSeqNo == null) {
			if (other.storeRequestSeqNo != null)
				return false;
		} else if (!storeRequestSeqNo.equals(other.storeRequestSeqNo))
			return false;
		return true;
	}

	public StoreOrderResourceOutward(Long storeRequestSeqNo, Character deleteflag, Character doneflag,
			Long frLocationSeqN, Timestamp fromDttm, Character isBooked, Long jobWorkSeqNo, Long locationSeqNo,
			Integer modeTxn, Float movedQty, Character okflag, Float qtyAllocated, Float qtyBooked, Float qtyRequested,
			Long qtyUnitSeqNo, Long requestedToPartySeqNo, Long requestorSeqNo, Long resourceSeqNo, Long targetSeqNo,
			Long targetWorkSeqNo, Timestamp toDttm, Long toLocationSeqNo) {
		super();
		this.storeRequestSeqNo = storeRequestSeqNo;
		this.deleteflag = deleteflag;
		this.doneflag = doneflag;
		this.frLocationSeqN = frLocationSeqN;
		this.fromDttm = fromDttm;
		this.isBooked = isBooked;
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.locationSeqNo = locationSeqNo;
		this.modeTxn = modeTxn;
		this.movedQty = movedQty;
		this.okflag = okflag;
		this.qtyAllocated = qtyAllocated;
		this.qtyBooked = qtyBooked;
		this.qtyRequested = qtyRequested;
		this.qtyUnitSeqNo = qtyUnitSeqNo;
		this.requestedToPartySeqNo = requestedToPartySeqNo;
		this.requestorSeqNo = requestorSeqNo;
		this.resourceSeqNo = resourceSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.targetWorkSeqNo = targetWorkSeqNo;
		this.toDttm = toDttm;
		this.toLocationSeqNo = toLocationSeqNo;
	}

	public StoreOrderResourceOutward() {
		super();
	}

	
}