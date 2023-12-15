package common.model.dto;

import java.io.Serializable;

public class StoreOrderResourceInward_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2018033973783620277L;
	private Long storeRequestSeqNo;
	private Character okflag;
	private Character deleteflag;
	private Character doneflag;
	private Long frLocationSeqNo;
	private String fromDttm;
	private Character isBooked;
	private Long jobWorkSeqNo;
	private String jobitemMode;
	private Long locationSeqNo;
	private Float movedQty;
	private Float qtyAllocated;
	private Float qtyBooked;
	private Float qtyRequested;
	private Long qtyUnitSeqNo;
	private Long requestedToPartySeqNo;
	private Long requestorSeqNo;
	private Long resourceSeqNo;
	private Long targetSeqNo;
	private Long targetWorkSeqNo;
	private String toDttm;
	private Long toLocationSeqNo;
	private Integer modeTxnSeqNo;

	public Long getStoreRequestSeqNo() {
		return storeRequestSeqNo;
	}

	public void setStoreRequestSeqNo(Long storeRequestSeqNo) {
		this.storeRequestSeqNo = storeRequestSeqNo;
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

	public Float getMovedQty() {
		return movedQty;
	}

	public void setMovedQty(Float movedQty) {
		this.movedQty = movedQty;
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

	public StoreOrderResourceInward_DTO(Long storeRequestSeqNo, Character okflag, Character deleteflag, Character doneflag,
			Long frLocationSeqNo, String fromDttm, Character isBooked, Long jobWorkSeqNo, String jobitemMode,
			Long locationSeqNo, Float movedQty, Float qtyAllocated, Float qtyBooked, Float qtyRequested,
			Long qtyUnitSeqNo, Long requestedToPartySeqNo, Long requestorSeqNo, Long resourceSeqNo, Long targetSeqNo,
			Long targetWorkSeqNo, String toDttm, Long toLocationSeqNo, Integer modeTxnSeqNo) {
		super();
		this.storeRequestSeqNo = storeRequestSeqNo;
		this.okflag = okflag;
		this.deleteflag = deleteflag;
		this.doneflag = doneflag;
		this.frLocationSeqNo = frLocationSeqNo;
		this.fromDttm = fromDttm;
		this.isBooked = isBooked;
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.jobitemMode = jobitemMode;
		this.locationSeqNo = locationSeqNo;
		this.movedQty = movedQty;
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
		this.modeTxnSeqNo = modeTxnSeqNo;
	}

	public StoreOrderResourceInward_DTO() {
		super();
	}

}