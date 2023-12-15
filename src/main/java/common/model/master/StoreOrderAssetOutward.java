package common.model.master;

import java.io.Serializable;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the STORE_ORDERASSET_INWARDS database table.
 * 
 */
@Entity
@Table(name = "STORE_ORDERASSET_OUTWARDS")
public class StoreOrderAssetOutward implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8310482730496752495L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_REQUEST_SEQUENCE")
	@SequenceGenerator(name = "STORE_REQUEST_SEQUENCE", sequenceName = "STORE_REQUEST_SEQUENCE", allocationSize = 1)
	@Column(name = "STORE_REQUEST_SEQ_NO")
	private Long storeRequestSeqNo;

	@Column(name = "ASSET_SEQ_NO")
	private Long assetSeqNo;

	@Column(name = "FR_LOCATION_SEQ_NO")
	private Long frLocationSeqNo;

	@Column(name = "FROM_DTTM")
	private Timestamp fromDttm;

	@Column(name = "JOB_WORK_SEQ_NO")
	private Long jobWorkSeqNo;

	@Column(name = "LOCATION_SEQ_NO")
	private Long locationSeqNo;

	@Column(name = "MODE_TXN")
	private Integer modeTxn;

	@Column(name = "REQUESTED_TO_PARTY_SEQ_NO")
	private Long requestedToPartySeqNo;

	@Column(name = "REQUESTOR_SEQ_NO")
	private Long requestorSeqNo;

	@Column(name = "TARGET_SEQ_NO")
	private Long targetSeqNo;

	@Column(name = "TARGET_WORK_SEQ_NO")
	private Long targetWorkSeqNo;

	@Column(name = "TO_DTTM")
	private Timestamp toDttm;

	@Column(name = "TO_LOCATION_SEQ_NO")
	private Long toLocationSeqNo;

	@Column(name = "IS_BOOKED")
	private Character isBooked;

	@Column(name = "MOVED_FLAG")
	private Character movedFlag;

	@Column(name = "OKFLAG")
	private Character okflag;

	@Column(name = "DELETEFLAG")
	private Character deleteflag;

	@Column(name = "DONEFLAG")
	private Character doneflag;

	@Column(name = "FLAG_ALLOCATED")
	private Character flagAllocated;

	@Column(name = "FLAG_BOOKED")
	private Character flagBooked;

	@Column(name = "FLAG_REQUESTED")
	private Character flagRequested;

	public Long getStoreRequestSeqNo() {
		return storeRequestSeqNo;
	}

	public void setStoreRequestSeqNo(Long storeRequestSeqNo) {
		this.storeRequestSeqNo = storeRequestSeqNo;
	}

	public Long getAssetSeqNo() {
		return assetSeqNo;
	}

	public void setAssetSeqNo(Long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}

	public Long getFrLocationSeqNo() {
		return frLocationSeqNo;
	}

	public void setFrLocationSeqNo(Long frLocationSeqNo) {
		this.frLocationSeqNo = frLocationSeqNo;
	}

	public Timestamp getFromDttm() {
		return fromDttm;
	}

	public void setFromDttm(Timestamp fromDttm) {
		this.fromDttm = fromDttm;
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

	public Character getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Character isBooked) {
		this.isBooked = isBooked;
	}

	public Character getMovedFlag() {
		return movedFlag;
	}

	public void setMovedFlag(Character movedFlag) {
		this.movedFlag = movedFlag;
	}

	public Character getOkflag() {
		return okflag;
	}

	public void setOkflag(Character okflag) {
		this.okflag = okflag;
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

	public Character getFlagAllocated() {
		return flagAllocated;
	}

	public void setFlagAllocated(Character flagAllocated) {
		this.flagAllocated = flagAllocated;
	}

	public Character getFlagBooked() {
		return flagBooked;
	}

	public void setFlagBooked(Character flagBooked) {
		this.flagBooked = flagBooked;
	}

	public Character getFlagRequested() {
		return flagRequested;
	}

	public void setFlagRequested(Character flagRequested) {
		this.flagRequested = flagRequested;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetSeqNo == null) ? 0 : assetSeqNo.hashCode());
		result = prime * result + ((jobWorkSeqNo == null) ? 0 : jobWorkSeqNo.hashCode());
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
		StoreOrderAssetOutward other = (StoreOrderAssetOutward) obj;
		if (assetSeqNo == null) {
			if (other.assetSeqNo != null)
				return false;
		} else if (!assetSeqNo.equals(other.assetSeqNo))
			return false;
		if (jobWorkSeqNo == null) {
			if (other.jobWorkSeqNo != null)
				return false;
		} else if (!jobWorkSeqNo.equals(other.jobWorkSeqNo))
			return false;
		if (storeRequestSeqNo == null) {
			if (other.storeRequestSeqNo != null)
				return false;
		} else if (!storeRequestSeqNo.equals(other.storeRequestSeqNo))
			return false;
		return true;
	}

	public StoreOrderAssetOutward(Long storeRequestSeqNo, Long assetSeqNo, Long frLocationSeqNo, Timestamp fromDttm,
			Long jobWorkSeqNo, Long locationSeqNo, Integer modeTxn, Long requestedToPartySeqNo, Long requestorSeqNo,
			Long targetSeqNo, Long targetWorkSeqNo, Timestamp toDttm, Long toLocationSeqNo, Character isBooked,
			Character movedFlag, Character okflag, Character deleteflag, Character doneflag, Character flagAllocated,
			Character flagBooked, Character flagRequested) {
		super();
		this.storeRequestSeqNo = storeRequestSeqNo;
		this.assetSeqNo = assetSeqNo;
		this.frLocationSeqNo = frLocationSeqNo;
		this.fromDttm = fromDttm;
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.locationSeqNo = locationSeqNo;
		this.modeTxn = modeTxn;
		this.requestedToPartySeqNo = requestedToPartySeqNo;
		this.requestorSeqNo = requestorSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.targetWorkSeqNo = targetWorkSeqNo;
		this.toDttm = toDttm;
		this.toLocationSeqNo = toLocationSeqNo;
		this.isBooked = isBooked;
		this.movedFlag = movedFlag;
		this.okflag = okflag;
		this.deleteflag = deleteflag;
		this.doneflag = doneflag;
		this.flagAllocated = flagAllocated;
		this.flagBooked = flagBooked;
		this.flagRequested = flagRequested;
	}

	public StoreOrderAssetOutward() {
		super();
	}

}