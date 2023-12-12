package common.model.master;

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

	@Column(name = "COPY_SERVICE_WORK_SEQ_NO")
	private Long copyServiceWorkSeqNo;

	@Column(name = "AUTO_ALLOC_STATUS")
	private Character autoAllocStatus;

	@Column(name = "BIILEDFLAG")
	private Character billedflag;

	@Column(name = "BILLING_CURRENCY_SEQ_NO")
	private Long billingCurrencySeqNo;

	@Column(name = "BOOKING_SEQ_NO")
	private Long bookingSeqNo;

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Column(name = "DONEFLAG")
	private Character doneflag;

	@Column(name = "JOB_ALLOC_STATUS")
	private Character jobAllocStatus;

	@Column(name = "JOB_TEMPLATE_SEQ_NO")
	private Long jobTemplateSeqNo;

	@Column(name = "JOBAUTOFLAG")
	private Character jobautoflag;

	@Column(name = "MEMBERSHIP_SEQ_NO")
	private Long membershipSeqNo;

	@Column(name = "OKFLAG")
	private Character okflag;

	@Column(name = "ON_DATE")
	private Timestamp onDate;

	@Column(name = "OP_FLAG")
	private Integer opFlag;

	@Column(name = "PARENT_SERVICE_WORK_SEQ_NO")
	private Long parentServiceWorkSeqNo;

	@Column(name = "PARTY_SEQ_NO")
	private Long partySeqNo;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "REQUEST_SEQ_NO")
	private Long requestSeqNo;

	@Column(name = "RES_ALLOC_STATUS")
	private Character resAllocStatus;

	@Column(name = "RES_DIRECT_INDIRECT_FLAG")
	private Character resDirectIndirectFlag;

	@Column(name = "RESAUTOFLAG")
	private Character resautoflag;

	@Column(name = "SERVICE_SEQ_NO")
	private Long serviceSeqNo;

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

	public Character getBilledflag() {
		return billedflag;
	}

	public void setBilledflag(Character billedflag) {
		this.billedflag = billedflag;
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

	public Character getDoneflag() {
		return doneflag;
	}

	public void setDoneflag(Character doneflag) {
		this.doneflag = doneflag;
	}

	public Character getJobAllocStatus() {
		return jobAllocStatus;
	}

	public void setJobAllocStatus(Character jobAllocStatus) {
		this.jobAllocStatus = jobAllocStatus;
	}

	public Long getJobTemplateSeqNo() {
		return jobTemplateSeqNo;
	}

	public void setJobTemplateSeqNo(Long jobTemplateSeqNo) {
		this.jobTemplateSeqNo = jobTemplateSeqNo;
	}

	public Character getJobautoflag() {
		return jobautoflag;
	}

	public void setJobautoflag(Character jobautoflag) {
		this.jobautoflag = jobautoflag;
	}

	public Long getMembershipSeqNo() {
		return membershipSeqNo;
	}

	public void setMembershipSeqNo(Long membershipSeqNo) {
		this.membershipSeqNo = membershipSeqNo;
	}

	public Character getOkflag() {
		return okflag;
	}

	public void setOkflag(Character okflag) {
		this.okflag = okflag;
	}

	public Timestamp getOnDate() {
		return onDate;
	}

	public void setOnDate(Timestamp onDate) {
		this.onDate = onDate;
	}

	public Integer getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(Integer opFlag) {
		this.opFlag = opFlag;
	}

	public Long getParentServiceWorkSeqNo() {
		return parentServiceWorkSeqNo;
	}

	public void setParentServiceWorkSeqNo(Long parentServiceWorkSeqNo) {
		this.parentServiceWorkSeqNo = parentServiceWorkSeqNo;
	}

	public Long getPartySeqNo() {
		return partySeqNo;
	}

	public void setPartySeqNo(Long partySeqNo) {
		this.partySeqNo = partySeqNo;
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

	public Character getResautoflag() {
		return resautoflag;
	}

	public void setResautoflag(Character resautoflag) {
		this.resautoflag = resautoflag;
	}

	public Long getServiceSeqNo() {
		return serviceSeqNo;
	}

	public void setServiceSeqNo(Long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}

	public Long getCopyServiceWorkSeqNo() {
		return copyServiceWorkSeqNo;
	}

	public void setCopyServiceWorkSeqNo(Long copyServiceWorkSeqNo) {
		this.copyServiceWorkSeqNo = copyServiceWorkSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((copyServiceWorkSeqNo == null) ? 0 : copyServiceWorkSeqNo.hashCode());
		result = prime * result + ((jobTemplateSeqNo == null) ? 0 : jobTemplateSeqNo.hashCode());
		result = prime * result + ((serviceSeqNo == null) ? 0 : serviceSeqNo.hashCode());
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
		if (copyServiceWorkSeqNo == null) {
			if (other.copyServiceWorkSeqNo != null)
				return false;
		} else if (!copyServiceWorkSeqNo.equals(other.copyServiceWorkSeqNo))
			return false;
		if (jobTemplateSeqNo == null) {
			if (other.jobTemplateSeqNo != null)
				return false;
		} else if (!jobTemplateSeqNo.equals(other.jobTemplateSeqNo))
			return false;
		if (serviceSeqNo == null) {
			if (other.serviceSeqNo != null)
				return false;
		} else if (!serviceSeqNo.equals(other.serviceSeqNo))
			return false;
		if (serviceWorkSeqNo == null) {
			if (other.serviceWorkSeqNo != null)
				return false;
		} else if (!serviceWorkSeqNo.equals(other.serviceWorkSeqNo))
			return false;
		return true;
	}

	public ServiceWorkMaster(Long serviceWorkSeqNo, Long copyServiceWorkSeqNo, Character autoAllocStatus,
			Character billedflag, Long billingCurrencySeqNo, Long bookingSeqNo, Long createdBy, Character doneflag,
			Character jobAllocStatus, Long jobTemplateSeqNo, Character jobautoflag, Long membershipSeqNo,
			Character okflag, Timestamp onDate, Integer opFlag, Long parentServiceWorkSeqNo, Long partySeqNo,
			String remark, Long requestSeqNo, Character resAllocStatus, Character resDirectIndirectFlag,
			Character resautoflag, Long serviceSeqNo) {
		super();
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.copyServiceWorkSeqNo = copyServiceWorkSeqNo;
		this.autoAllocStatus = autoAllocStatus;
		this.billedflag = billedflag;
		this.billingCurrencySeqNo = billingCurrencySeqNo;
		this.bookingSeqNo = bookingSeqNo;
		this.createdBy = createdBy;
		this.doneflag = doneflag;
		this.jobAllocStatus = jobAllocStatus;
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.jobautoflag = jobautoflag;
		this.membershipSeqNo = membershipSeqNo;
		this.okflag = okflag;
		this.onDate = onDate;
		this.opFlag = opFlag;
		this.parentServiceWorkSeqNo = parentServiceWorkSeqNo;
		this.partySeqNo = partySeqNo;
		this.remark = remark;
		this.requestSeqNo = requestSeqNo;
		this.resAllocStatus = resAllocStatus;
		this.resDirectIndirectFlag = resDirectIndirectFlag;
		this.resautoflag = resautoflag;
		this.serviceSeqNo = serviceSeqNo;
	}

}