package common.model.dto;

import java.io.Serializable;

public class StoreOrderAssetInward_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5237595970221571499L;
	private Long storeRequestSeqNo;
	private Long assetSeqNo;
	private Long frLocationSeqNo;
	private String fromDttm;
	private Character isBooked;
	private Long jobWorkSeqNo;
	private String jobitemMode;
	private Long locationSeqNo;
	private Character movedFlag;
	private Character okflag;
	private Character deleteflag;
	private Character doneflag;
	private Character flagAllocated;
	private Character flagBooked;
	private Character flagRequested;
	private Long requestedToPartySeqNo;
	private Long requestorSeqNo;
	private Long targetSeqNo;
	private Long targetWorkSeqNo;
	private String toDttm;
	private Long toLocationSeqNo;
	private Integer modeTxnSeqNo;
	private Long rateSeqNo;
	private String requestParam;

	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

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

	public String getFromDttm() {
		return fromDttm;
	}

	public void setFromDttm(String fromDttm) {
		this.fromDttm = fromDttm;
	}

	public Long getJobWorkSeqNo() {
		return jobWorkSeqNo;
	}

	public void setJobWorkSeqNo(Long jobWorkSeqNo) {
		this.jobWorkSeqNo = jobWorkSeqNo;
	}

	public String getJobitemMode() {
		return jobitemMode;
	}

	public void setJobitemMode(String jobitemMode) {
		this.jobitemMode = jobitemMode;
	}

	public Long getLocationSeqNo() {
		return locationSeqNo;
	}

	public void setLocationSeqNo(Long locationSeqNo) {
		this.locationSeqNo = locationSeqNo;
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

	public Integer getModeTxnSeqNo() {
		return modeTxnSeqNo;
	}

	public void setModeTxnSeqNo(Integer modeTxnSeqNo) {
		this.modeTxnSeqNo = modeTxnSeqNo;
	}

	public Character getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Character isBooked) {
		this.isBooked = isBooked;
	}

	public Long getRateSeqNo() {
		return rateSeqNo;
	}

	public void setRateSeqNo(Long rateSeqNo) {
		this.rateSeqNo = rateSeqNo;
	}

	public StoreOrderAssetInward_DTO() {
		super();
	}

	public StoreOrderAssetInward_DTO(Long storeRequestSeqNo, Long assetSeqNo, Long frLocationSeqNo, String fromDttm,
			Character isBooked, Long jobWorkSeqNo, String jobitemMode, Long locationSeqNo, Character movedFlag,
			Character okflag, Character deleteflag, Character doneflag, Character flagAllocated, Character flagBooked,
			Character flagRequested, Long requestedToPartySeqNo, Long requestorSeqNo, Long targetSeqNo,
			Long targetWorkSeqNo, String toDttm, Long toLocationSeqNo, Integer modeTxnSeqNo, Long rateSeqNo,
			String requestParam) {
		super();
		this.storeRequestSeqNo = storeRequestSeqNo;
		this.assetSeqNo = assetSeqNo;
		this.frLocationSeqNo = frLocationSeqNo;
		this.fromDttm = fromDttm;
		this.isBooked = isBooked;
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.jobitemMode = jobitemMode;
		this.locationSeqNo = locationSeqNo;
		this.movedFlag = movedFlag;
		this.okflag = okflag;
		this.deleteflag = deleteflag;
		this.doneflag = doneflag;
		this.flagAllocated = flagAllocated;
		this.flagBooked = flagBooked;
		this.flagRequested = flagRequested;
		this.requestedToPartySeqNo = requestedToPartySeqNo;
		this.requestorSeqNo = requestorSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.targetWorkSeqNo = targetWorkSeqNo;
		this.toDttm = toDttm;
		this.toLocationSeqNo = toLocationSeqNo;
		this.modeTxnSeqNo = modeTxnSeqNo;
		this.rateSeqNo = rateSeqNo;
		this.requestParam = requestParam;
	}

}