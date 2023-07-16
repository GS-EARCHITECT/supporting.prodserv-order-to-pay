package service_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the SERVICE_WORK_MASTER database table.
 * 
 */
@Entity
@Table(name = "SERVICE_WORK_MASTER")
public class ServiceWorkMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_work_seq_no")
	@SequenceGenerator(name = "service_work_seq_no", sequenceName = "service_work_seq_no", allocationSize = 1)
	@Column(name = "SERVICE_WORK_SEQ_NO")
	private Long serviceWorkSeqNo;

	@Column(name = "AUTO_ALLOC_STATUS")
	private Character autoAllocStatus;

	@Column(name = "BILLING_CURRENCY_SEQ_NO")
	private Long billingCurrencySeqNo;

	@Column(name = "BOOKING_SEQ_NO")
	private Long bookingSeqNo;

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Column(name = "JOB_ALLOC_STATUS")
	private Character jobAllocStatus;

	@Column(name = "MEMBERSHIP_SEQ_NO")
	private Long membershipSeqNo;

	@Column(name = "ON_DATE")
	private Timestamp onDate;

	@Column(name = "PARENT_SERVICE_WORK_SEQ_NO")
	private Long parentServiceWorkSeqNo;

	@Column(name = "PERSON_SEQ_NO")
	private Long personSeqNo;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "REQUEST_SEQ_NO")
	private Long requestSeqNo;

	@Column(name = "RES_ALLOC_STATUS")
	private Character resAllocStatus;

	@Column(name = "RES_DIRECT_INDIRECT_FLAG")
	private Character resDirectIndirectFlag;

	@Column(name = "SERVICE_SEQ_NO")
	private Long serviceSeqNo;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "TO_BILL")
	private String toBill;

	public ServiceWorkMaster() {
	}

	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public Character getAutoAllocStatus() {
		return autoAllocStatus;
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

	public Timestamp getOnDate() {
		return onDate;
	}

	public void setOnDate(Timestamp onDate) {
		this.onDate = onDate;
	}

	public Long getParentServiceWorkSeqNo() {
		return parentServiceWorkSeqNo;
	}

	public void setParentServiceWorkSeqNo(Long parentServiceWorkSeqNo) {
		this.parentServiceWorkSeqNo = parentServiceWorkSeqNo;
	}

	public Long getPersonSeqNo() {
		return personSeqNo;
	}

	public void setPersonSeqNo(Long personSeqNo) {
		this.personSeqNo = personSeqNo;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serviceWorkSeqNo == null) ? 0 : serviceWorkSeqNo.hashCode());
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
		ServiceWorkMaster other = (ServiceWorkMaster) obj;
		if (serviceWorkSeqNo == null) {
			if (other.serviceWorkSeqNo != null)
				return false;
		} else if (!serviceWorkSeqNo.equals(other.serviceWorkSeqNo))
			return false;
		return true;
	}

	public ServiceWorkMaster(Long serviceWorkSeqNo, Character autoAllocStatus, Long billingCurrencySeqNo,
			Long bookingSeqNo, Long createdBy, Character jobAllocStatus, Long membershipSeqNo, Timestamp onDate,
			Long parentServiceWorkSeqNo, Long personSeqNo, String remark, Long requestSeqNo, Character resAllocStatus,
			Character resDirectIndirectFlag, Long serviceSeqNo, String status, String toBill) {
		super();
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.autoAllocStatus = autoAllocStatus;
		this.billingCurrencySeqNo = billingCurrencySeqNo;
		this.bookingSeqNo = bookingSeqNo;
		this.createdBy = createdBy;
		this.jobAllocStatus = jobAllocStatus;
		this.membershipSeqNo = membershipSeqNo;
		this.onDate = onDate;
		this.parentServiceWorkSeqNo = parentServiceWorkSeqNo;
		this.personSeqNo = personSeqNo;
		this.remark = remark;
		this.requestSeqNo = requestSeqNo;
		this.resAllocStatus = resAllocStatus;
		this.resDirectIndirectFlag = resDirectIndirectFlag;
		this.serviceSeqNo = serviceSeqNo;
		this.status = status;
		this.toBill = toBill;
	}

}