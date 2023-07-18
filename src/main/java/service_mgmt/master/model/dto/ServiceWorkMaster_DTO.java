package service_mgmt.master.model.dto;

import java.io.Serializable;

public class ServiceWorkMaster_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5825991339136084922L;
	private Long serviceWorkSeqNo;
	private Long partySeqNo;
	private Character autoAllocStatus;
	private Long billingCurrencySeqNo;
	private Long bookingSeqNo;
	private Long createdBy;
	private Character jobAllocStatus;
	private Long membershipSeqNo;
	private String onDate;
	private Long parentServiceWorkSeqNo;
	private String remark;
	private Long requestSeqNo;
	private Character resAllocStatus;
	private Character resDirectIndirectFlag;
	private Long serviceSeqNo;
	private String status;
	private String toBill;

	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public Character getAutoAllocStatus() {
		return autoAllocStatus;
	}

	public Long getPartySeqNo() {
		return partySeqNo;
	}

	public void setPartySeqNo(Long partySeqNo) {
		this.partySeqNo = partySeqNo;
	}

	public void setAutoAllocStatus(Character autoAllocStatus) {
		this.autoAllocStatus = autoAllocStatus;
	}

	public Long getBillingCurrencySeqNo() {
		return billingCurrencySeqNo;
	}

	public void setBillingCurrencySeqNo(Long billingCurrencySeqNo) {
		this.billingCurrencySeqNo = billingCurrencySeqNo;
	}

	public Long getBookingSeqNo() {
		return bookingSeqNo;
	}

	public void setBookingSeqNo(Long bookingSeqNo) {
		this.bookingSeqNo = bookingSeqNo;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Character getJobAllocStatus() {
		return jobAllocStatus;
	}

	public void setJobAllocStatus(Character jobAllocStatus) {
		this.jobAllocStatus = jobAllocStatus;
	}

	public Long getMembershipSeqNo() {
		return membershipSeqNo;
	}

	public void setMembershipSeqNo(Long membershipSeqNo) {
		this.membershipSeqNo = membershipSeqNo;
	}

	public String getOnDate() {
		return onDate;
	}

	public void setOnDate(String onDate) {
		this.onDate = onDate;
	}

	public Long getParentServiceWorkSeqNo() {
		return parentServiceWorkSeqNo;
	}

	public void setParentServiceWorkSeqNo(Long parentServiceWorkSeqNo) {
		this.parentServiceWorkSeqNo = parentServiceWorkSeqNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getRequestSeqNo() {
		return requestSeqNo;
	}

	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public Character getResAllocStatus() {
		return resAllocStatus;
	}

	public void setResAllocStatus(Character resAllocStatus) {
		this.resAllocStatus = resAllocStatus;
	}

	public Character getResDirectIndirectFlag() {
		return resDirectIndirectFlag;
	}

	public void setResDirectIndirectFlag(Character resDirectIndirectFlag) {
		this.resDirectIndirectFlag = resDirectIndirectFlag;
	}

	public Long getServiceSeqNo() {
		return serviceSeqNo;
	}

	public void setServiceSeqNo(Long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToBill() {
		return toBill;
	}

	public void setToBill(String toBill) {
		this.toBill = toBill;
	}

	public ServiceWorkMaster_DTO(Long serviceWorkSeqNo, Long partySeqNo, Character autoAllocStatus,
			Long billingCurrencySeqNo, Long bookingSeqNo, Long createdBy, Character jobAllocStatus,
			Long membershipSeqNo, String onDate, Long parentServiceWorkSeqNo, String remark,
			Long requestSeqNo, Character resAllocStatus, Character resDirectIndirectFlag, Long serviceSeqNo,
			String status, String toBill) {
		super();
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.partySeqNo = partySeqNo;
		this.autoAllocStatus = autoAllocStatus;
		this.billingCurrencySeqNo = billingCurrencySeqNo;
		this.bookingSeqNo = bookingSeqNo;
		this.createdBy = createdBy;
		this.jobAllocStatus = jobAllocStatus;
		this.membershipSeqNo = membershipSeqNo;
		this.onDate = onDate;
		this.parentServiceWorkSeqNo = parentServiceWorkSeqNo;
		this.remark = remark;
		this.requestSeqNo = requestSeqNo;
		this.resAllocStatus = resAllocStatus;
		this.resDirectIndirectFlag = resDirectIndirectFlag;
		this.serviceSeqNo = serviceSeqNo;
		this.status = status;
		this.toBill = toBill;
	}

	public ServiceWorkMaster_DTO() {
		super();
	}

}