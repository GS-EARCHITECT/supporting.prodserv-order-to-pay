package order_items_mgmt.assetsinward.model.dto;

import java.io.Serializable;

public class StoreOrderAssetInward_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4741562555342005228L;

	private Long storeRequestSeqNo;
	private Long assetSeqNo;
	private Character doneflag;
	private Character flagAllocated;
	private Character flagBooked;
	private Character flagRequested;
	private Long frLocationSeqNo;
	private String fromDttm;
	private Character isBooked;
	private Long jobWorkSeqNo;
	private Long locationSeqNo;
	private Integer modeTxn;
	private Character movedFlag;
	private Character okflag;
	private Long requestedToPartySeqNo;
	private Long requestorSeqNo;
	private Long targetWorkSeqNo;	
	private String toDttm;
	private Long toLocationSeqNo;
	private Long targetSeqNo;

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

	public Long getFrLocationSeqNo() {
		return frLocationSeqNo;
	}

	public void setFrLocationSeqNo(Long frLocationSeqNo) {
		this.frLocationSeqNo = frLocationSeqNo;
	}

	public String getFromDttm() {
		return fromDttm;
	}

	public void setFromDttm(String fromDttm) {
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

	public Long getTargetWorkSeqNo() {
		return targetWorkSeqNo;
	}

	public void setTargetWorkSeqNo(Long targetWorkSeqNo) {
		this.targetWorkSeqNo = targetWorkSeqNo;
	}

	public String getToDttm() {
		return toDttm;
	}

	public void setToDttm(String toDttm) {
		this.toDttm = toDttm;
	}

	public Long getToLocationSeqNo() {
		return toLocationSeqNo;
	}

	public void setToLocationSeqNo(Long toLocationSeqNo) {
		this.toLocationSeqNo = toLocationSeqNo;
	}

	public Long getTargetSeqNo() {
		return targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public StoreOrderAssetInward_DTO(Long storeRequestSeqNo, Long assetSeqNo, Character doneflag,
			Character flagAllocated, Character flagBooked, Character flagRequested, Long frLocationSeqNo,
			String fromDttm, Character isBooked, Long jobWorkSeqNo, Long locationSeqNo, Integer modeTxn,
			Character movedFlag, Character okflag, Long requestedToPartySeqNo, Long requestorSeqNo,
			Long targetWorkSeqNo, String toDttm, Long toLocationSeqNo, Long targetSeqNo) {
		super();
		this.storeRequestSeqNo = storeRequestSeqNo;
		this.assetSeqNo = assetSeqNo;
		this.doneflag = doneflag;
		this.flagAllocated = flagAllocated;
		this.flagBooked = flagBooked;
		this.flagRequested = flagRequested;
		this.frLocationSeqNo = frLocationSeqNo;
		this.fromDttm = fromDttm;
		this.isBooked = isBooked;
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.locationSeqNo = locationSeqNo;
		this.modeTxn = modeTxn;
		this.movedFlag = movedFlag;
		this.okflag = okflag;
		this.requestedToPartySeqNo = requestedToPartySeqNo;
		this.requestorSeqNo = requestorSeqNo;
		this.targetWorkSeqNo = targetWorkSeqNo;
		this.toDttm = toDttm;
		this.toLocationSeqNo = toLocationSeqNo;
		this.targetSeqNo = targetSeqNo;
	}

	public StoreOrderAssetInward_DTO() {
		super();
	}

}