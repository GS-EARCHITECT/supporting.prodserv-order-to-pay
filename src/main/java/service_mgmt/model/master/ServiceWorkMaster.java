package service_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the SERVICE_WORK_MASTER database table.
 * 
 */
@Entity
@Table(name="SERVICE_WORK_MASTER")
@NamedQuery(name="ServiceWorkMaster.findAll", query="SELECT s FROM ServiceWorkMaster s")
public class ServiceWorkMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SERVICE_WORK_SEQ_NO")
	private long serviceWorkSeqNo;

	@Column(name="AUTO_ALLOC_STATUS")
	private String autoAllocStatus;

	@Column(name="BILLING_CURRENCY_SEQ_NO")
	private BigDecimal billingCurrencySeqNo;

	@Column(name="BOOKING_SEQ_NO")
	private BigDecimal bookingSeqNo;

	@Column(name="CREATED_BY")
	private BigDecimal createdBy;

	@Column(name="JOB_ALLOC_STATUS")
	private String jobAllocStatus;

	@Column(name="MEMBERSHIP_SEQ_NO")
	private BigDecimal membershipSeqNo;

	@Column(name="ON_DATE")
	private Timestamp onDate;

	@Column(name="PARENT_SERVICE_WORK_SEQ_NO")
	private BigDecimal parentServiceWorkSeqNo;

	@Column(name="PERSON_SEQ_NO")
	private BigDecimal personSeqNo;

	private String remarks;

	@Column(name="REQUEST_SEQ_NO")
	private BigDecimal requestSeqNo;

	@Column(name="RES_ALLOC_STATUS")
	private String resAllocStatus;

	@Column(name="RES_DIRECT_INDIRECT_FLAG")
	private String resDirectIndirectFlag;

	@Column(name="\"SERVICE\"")
	private String service;

	@Column(name="SERVICE_SEQ_NO")
	private BigDecimal serviceSeqNo;

	private String status;

	@Column(name="TO_BILL")
	private String toBill;

	public ServiceWorkMaster() {
	}

	public long getServiceWorkSeqNo() {
		return this.serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public String getAutoAllocStatus() {
		return this.autoAllocStatus;
	}

	public void setAutoAllocStatus(String autoAllocStatus) {
		this.autoAllocStatus = autoAllocStatus;
	}

	public BigDecimal getBillingCurrencySeqNo() {
		return this.billingCurrencySeqNo;
	}

	public void setBillingCurrencySeqNo(BigDecimal billingCurrencySeqNo) {
		this.billingCurrencySeqNo = billingCurrencySeqNo;
	}

	public BigDecimal getBookingSeqNo() {
		return this.bookingSeqNo;
	}

	public void setBookingSeqNo(BigDecimal bookingSeqNo) {
		this.bookingSeqNo = bookingSeqNo;
	}

	public BigDecimal getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public String getJobAllocStatus() {
		return this.jobAllocStatus;
	}

	public void setJobAllocStatus(String jobAllocStatus) {
		this.jobAllocStatus = jobAllocStatus;
	}

	public BigDecimal getMembershipSeqNo() {
		return this.membershipSeqNo;
	}

	public void setMembershipSeqNo(BigDecimal membershipSeqNo) {
		this.membershipSeqNo = membershipSeqNo;
	}

	public Timestamp getOnDate() {
		return this.onDate;
	}

	public void setOnDate(Timestamp onDate) {
		this.onDate = onDate;
	}

	public BigDecimal getParentServiceWorkSeqNo() {
		return this.parentServiceWorkSeqNo;
	}

	public void setParentServiceWorkSeqNo(BigDecimal parentServiceWorkSeqNo) {
		this.parentServiceWorkSeqNo = parentServiceWorkSeqNo;
	}

	public BigDecimal getPersonSeqNo() {
		return this.personSeqNo;
	}

	public void setPersonSeqNo(BigDecimal personSeqNo) {
		this.personSeqNo = personSeqNo;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getRequestSeqNo() {
		return this.requestSeqNo;
	}

	public void setRequestSeqNo(BigDecimal requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public String getResAllocStatus() {
		return this.resAllocStatus;
	}

	public void setResAllocStatus(String resAllocStatus) {
		this.resAllocStatus = resAllocStatus;
	}

	public String getResDirectIndirectFlag() {
		return this.resDirectIndirectFlag;
	}

	public void setResDirectIndirectFlag(String resDirectIndirectFlag) {
		this.resDirectIndirectFlag = resDirectIndirectFlag;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public BigDecimal getServiceSeqNo() {
		return this.serviceSeqNo;
	}

	public void setServiceSeqNo(BigDecimal serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToBill() {
		return this.toBill;
	}

	public void setToBill(String toBill) {
		this.toBill = toBill;
	}

}