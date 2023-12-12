package jobs.job_work_details_mgmt.model.details;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the JOB_DETAILS database table.
 * 
 */
@Entity
@Table(name = "JOB_WORK_DETAILS")
public class JobWorkDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOB_WORK_SEQUENCE")
	@SequenceGenerator(name = "JOB_WORK_SEQUENCE", sequenceName = "JOB_WORK_SEQUENCE", allocationSize = 1)
	@Column(name = "JOB_WORK_SEQ_NO")
	private Long jobWorkSeqNo;

	@Column(name = "TARGET_SEQ_NO")
	private Long targetSeqNo;

	@Column(name = "JOB_TEMPLATE_SEQ_NO")
	private Long jobTemplateSeqNo;

	@Column(name = "ACT_END_DATE")
	private Timestamp actEndDate;

	@Column(name = "ACT_START_DATE")
	private Timestamp actStartDate;

	@Column(name = "JOB_SEQ_NO")
	private Long jobSeqNo;

	@Column(name = "SEQ_NO")
	private Long seqNo;

	@Column(name = "MANAGER_SEQ_NO")
	private Long managerSeqNo;

	@Column(name = "PARENT_JOB_WORK_SEQ_NO")
	private Long parentJobWorkSeqNo;

	@Column(name = "PLAN_END_DATE")
	private Timestamp planEndDate;

	@Column(name = "PLAN_START_DATE")
	private Timestamp planStartDate;

	@Column(name = "SERVICE_WORK_SEQ_NO")
	private Long serviceWorkSeqNo;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "STATUS")
	private String status;

	public Long getJobSeqNo() {
		return jobWorkSeqNo;
	}

	public void setJobSeqNo(Long jobWorkSeqNo) {
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

	public Long getJobWorkSeqNo() {
		return jobWorkSeqNo;
	}

	public void setJobWorkSeqNo(Long jobWorkSeqNo) {
		this.jobWorkSeqNo = jobWorkSeqNo;
	}

	public Long getParentJobWorkSeqNo() {
		return parentJobWorkSeqNo;
	}

	public void setParentJobWorkSeqNo(Long parentJobWorkSeqNo) {
		this.parentJobWorkSeqNo = parentJobWorkSeqNo;
	}

	public Long getManagerSeqNo() {
		return managerSeqNo;
	}

	public void setManagerSeqNo(Long managerSeqNo) {
		this.managerSeqNo = managerSeqNo;
	}

	public Long getParentJobSeqNo() {
		return parentJobWorkSeqNo;
	}

	public void setParentJobSeqNo(Long parentJobWorkSeqNo) {
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

	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTargetSeqNo() {
		return targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public Long getJobTemplateSeqNo() {
		return jobTemplateSeqNo;
	}

	public void setJobTemplateSeqNo(Long jobTemplateSeqNo) {
		this.jobTemplateSeqNo = jobTemplateSeqNo;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public JobWorkDetail(Long jobWorkSeqNo, Long targetSeqNo, Long jobTemplateSeqNo, Timestamp actEndDate,
			Timestamp actStartDate, Long jobSeqNo, Long seqNo, Long managerSeqNo, Long parentJobWorkSeqNo,
			Timestamp planEndDate, Timestamp planStartDate, Long serviceWorkSeqNo, String remarks, String status) {
		super();
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.actEndDate = actEndDate;
		this.actStartDate = actStartDate;
		this.jobSeqNo = jobSeqNo;
		this.seqNo = seqNo;
		this.managerSeqNo = managerSeqNo;
		this.parentJobWorkSeqNo = parentJobWorkSeqNo;
		this.planEndDate = planEndDate;
		this.planStartDate = planStartDate;
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.remarks = remarks;
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actEndDate == null) ? 0 : actEndDate.hashCode());
		result = prime * result + ((actStartDate == null) ? 0 : actStartDate.hashCode());
		result = prime * result + ((jobSeqNo == null) ? 0 : jobSeqNo.hashCode());
		result = prime * result + ((jobTemplateSeqNo == null) ? 0 : jobTemplateSeqNo.hashCode());
		result = prime * result + ((jobWorkSeqNo == null) ? 0 : jobWorkSeqNo.hashCode());
		result = prime * result + ((managerSeqNo == null) ? 0 : managerSeqNo.hashCode());
		result = prime * result + ((parentJobWorkSeqNo == null) ? 0 : parentJobWorkSeqNo.hashCode());
		result = prime * result + ((planEndDate == null) ? 0 : planEndDate.hashCode());
		result = prime * result + ((planStartDate == null) ? 0 : planStartDate.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
		result = prime * result + ((serviceWorkSeqNo == null) ? 0 : serviceWorkSeqNo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((targetSeqNo == null) ? 0 : targetSeqNo.hashCode());
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
		if (actEndDate == null) {
			if (other.actEndDate != null)
				return false;
		} else if (!actEndDate.equals(other.actEndDate))
			return false;
		if (actStartDate == null) {
			if (other.actStartDate != null)
				return false;
		} else if (!actStartDate.equals(other.actStartDate))
			return false;
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
		if (jobWorkSeqNo == null) {
			if (other.jobWorkSeqNo != null)
				return false;
		} else if (!jobWorkSeqNo.equals(other.jobWorkSeqNo))
			return false;
		if (managerSeqNo == null) {
			if (other.managerSeqNo != null)
				return false;
		} else if (!managerSeqNo.equals(other.managerSeqNo))
			return false;
		if (parentJobWorkSeqNo == null) {
			if (other.parentJobWorkSeqNo != null)
				return false;
		} else if (!parentJobWorkSeqNo.equals(other.parentJobWorkSeqNo))
			return false;
		if (planEndDate == null) {
			if (other.planEndDate != null)
				return false;
		} else if (!planEndDate.equals(other.planEndDate))
			return false;
		if (planStartDate == null) {
			if (other.planStartDate != null)
				return false;
		} else if (!planStartDate.equals(other.planStartDate))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (targetSeqNo == null) {
			if (other.targetSeqNo != null)
				return false;
		} else if (!targetSeqNo.equals(other.targetSeqNo))
			return false;
		return true;
	}

	public JobWorkDetail() {
		super();
	}

}