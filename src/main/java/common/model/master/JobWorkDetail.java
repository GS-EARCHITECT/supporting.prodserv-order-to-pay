package common.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "JOB_WORK_DETAILS")
public class JobWorkDetail implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOB_WORK_SEQUENCE")
	@SequenceGenerator(name = "JOB_WORK_SEQUENCE", sequenceName = "JOB_WORK_SEQUENCE", allocationSize = 1)	
	@Column(name = "JOB_WORK_SEQ_NO")
	private Long jobWorkSeqNo;

	@Column(name = "ACT_END_DATE")
	private Timestamp actEndDate;

	@Column(name = "ACT_START_DATE")
	private Timestamp actStartDate;

	@Column(name = "DONEFLAG")
	private Character doneflag;

	@Column(name = "JOB_SEQ_NO")
	private Long jobSeqNo;

	@Column(name = "JOB_TEMPLATE_SEQ_NO")
	private Long jobTemplateSeqNo;

	@Column(name = "MANAGER_SEQ_NO")
	private Long managerSeqNo;

	@Column(name = "OKFLAG")
	private Character okflag;

	@Column(name = "PARENT_JOB_WORK_SEQ_NO")
	private Long parentJobWorkSeqNo;

	@Column(name = "PLAN_END_DATE")
	private Timestamp planEndDate;

	@Column(name = "PLAN_START_DATE")
	private Timestamp planStartDate;

	@Column(name = "SEQ_NO")
	private Long seqNo;

	@Column(name = "SERVICE_WORK_SEQ_NO")
	private Long serviceWorkSeqNo;

	@Column(name = "TARGET_SEQ_NO")
	private Long targetSeqNo;

	@Column(name = "TARGET_CLASS_SEQ_NO")
	private Long targetClassSeqNo;

	public Long getJobWorkSeqNo() {
		return jobWorkSeqNo;
	}

	public void setJobWorkSeqNo(Long jobWorkSeqNo) {
		this.jobWorkSeqNo = jobWorkSeqNo;
	}

	public Timestamp getActEndDate() {
		return actEndDate;
	}

	public void setActEndDate(Timestamp actEndDate) {
		this.actEndDate = actEndDate;
	}

	public Timestamp getActStartDate() {
		return actStartDate;
	}

	public void setActStartDate(Timestamp actStartDate) {
		this.actStartDate = actStartDate;
	}

	public Character getDoneflag() {
		return doneflag;
	}

	public void setDoneflag(Character doneflag) {
		this.doneflag = doneflag;
	}

	public Long getJobSeqNo() {
		return jobSeqNo;
	}

	public void setJobSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	public Long getJobTemplateSeqNo() {
		return jobTemplateSeqNo;
	}

	public void setJobTemplateSeqNo(Long jobTemplateSeqNo) {
		this.jobTemplateSeqNo = jobTemplateSeqNo;
	}

	public Long getManagerSeqNo() {
		return managerSeqNo;
	}

	public void setManagerSeqNo(Long managerSeqNo) {
		this.managerSeqNo = managerSeqNo;
	}

	public Character getOkflag() {
		return okflag;
	}

	public void setOkflag(Character okflag) {
		this.okflag = okflag;
	}

	public Long getParentJobWorkSeqNo() {
		return parentJobWorkSeqNo;
	}

	public void setParentJobWorkSeqNo(Long parentJobWorkSeqNo) {
		this.parentJobWorkSeqNo = parentJobWorkSeqNo;
	}

	public Timestamp getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Timestamp planEndDate) {
		this.planEndDate = planEndDate;
	}

	public Timestamp getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Timestamp planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public Long getTargetSeqNo() {
		return targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public Long getTargetClassSeqNo() {
		return targetClassSeqNo;
	}

	public void setTargetClassSeqNo(Long targetClassSeqNo) {
		this.targetClassSeqNo = targetClassSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobSeqNo == null) ? 0 : jobSeqNo.hashCode());
		result = prime * result + ((jobTemplateSeqNo == null) ? 0 : jobTemplateSeqNo.hashCode());
		result = prime * result + (int) (jobWorkSeqNo ^ (jobWorkSeqNo >>> 32));
		result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
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
		JobWorkDetail other = (JobWorkDetail) obj;
		if (jobSeqNo == null) {
			if (other.jobSeqNo != null)
				return false;
		} else if (!jobSeqNo.equals(other.jobSeqNo))
			return false;
		if (jobTemplateSeqNo == null) {
			if (other.jobTemplateSeqNo != null)
				return false;
		} else if (!jobTemplateSeqNo.equals(other.jobTemplateSeqNo))
			return false;
		if (jobWorkSeqNo != other.jobWorkSeqNo)
			return false;
		if (seqNo == null) {
			if (other.seqNo != null)
				return false;
		} else if (!seqNo.equals(other.seqNo))
			return false;
		if (serviceWorkSeqNo == null) {
			if (other.serviceWorkSeqNo != null)
				return false;
		} else if (!serviceWorkSeqNo.equals(other.serviceWorkSeqNo))
			return false;
		return true;
	}

	public JobWorkDetail(Long jobWorkSeqNo, Timestamp actEndDate, Timestamp actStartDate, Character doneflag,
			Long jobSeqNo, Long jobTemplateSeqNo, Long managerSeqNo, Character okflag, Long parentJobWorkSeqNo,
			Timestamp planEndDate, Timestamp planStartDate, Long seqNo, Long serviceWorkSeqNo, Long targetSeqNo,
			Long targetClassSeqNo) {
		super();
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.actEndDate = actEndDate;
		this.actStartDate = actStartDate;
		this.doneflag = doneflag;
		this.jobSeqNo = jobSeqNo;
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.managerSeqNo = managerSeqNo;
		this.okflag = okflag;
		this.parentJobWorkSeqNo = parentJobWorkSeqNo;
		this.planEndDate = planEndDate;
		this.planStartDate = planStartDate;
		this.seqNo = seqNo;
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.targetClassSeqNo = targetClassSeqNo;
	}

	public JobWorkDetail() {
		super();
	}

}