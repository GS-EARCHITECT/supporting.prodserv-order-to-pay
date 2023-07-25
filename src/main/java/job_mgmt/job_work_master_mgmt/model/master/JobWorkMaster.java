package job_mgmt.job_work_master_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
/**
 * The persistent class for the JOB_MASTER database table.
 * 
 */
@Entity
@Table(name = "JOB_WORK_MASTER")
public class JobWorkMaster implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_WORK_SEQ_NO")
	@SequenceGenerator(name = "SERVICE_WORK_SEQ_NO", sequenceName = "SERVICE_WORK_SEQ_NO", allocationSize = 1)
	@Column(name = "SERVICE_WORK_SEQ_NO")
	private Long serviceWorkSeqNo;

	@Column(name = "REQUEST_SEQ_NO")
	private Long requestSeqNo;

	@Column(name = "CREATED_ON")
	private Timestamp createdOn;

	@Column(name = "JOB_TEMPLATE_SEQ_NO")
	private Long jobTemplateSeqNo;

	@Column(name = "OP_FLAG")
	private Integer opFlag;

	@Column(name = "MANAGER_SEQ_NO")
	private Long managerSeqNo;

	@Column(name = "SERVICE_SEQ_NO")
	private Long serviceSeqNo;

	@Column(name = "JOB_REF_ID")
	private String jobRefId;

	@Column(name = "SCHEDULE_TYPE")
	private Character schType;

	@Column(name = "SCHEDULED_FLAG")
	private Character schFlag;

	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public Long getRequestSeqNo() {
		return requestSeqNo;
	}

	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
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

	public Long getServiceSeqNo() {
		return serviceSeqNo;
	}

	public void setServiceSeqNo(Long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}

	public String getJobRefId() {
		return jobRefId;
	}

	public void setJobRefId(String jobRefId) {
		this.jobRefId = jobRefId;
	}

	public Character getSchType() {
		return schType;
	}

	public void setSchType(Character schType) {
		this.schType = schType;
	}

	public Character getSchFlag() {
		return schFlag;
	}

	public void setSchFlag(Character schFlag) {
		this.schFlag = schFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((requestSeqNo == null) ? 0 : requestSeqNo.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((jobRefId == null) ? 0 : jobRefId.hashCode());
		result = prime * result + ((jobTemplateSeqNo == null) ? 0 : jobTemplateSeqNo.hashCode());
		result = prime * result + ((managerSeqNo == null) ? 0 : managerSeqNo.hashCode());
		result = prime * result + ((opFlag == null) ? 0 : opFlag.hashCode());
		result = prime * result + ((schFlag == null) ? 0 : schFlag.hashCode());
		result = prime * result + ((schType == null) ? 0 : schType.hashCode());
		result = prime * result + ((serviceSeqNo == null) ? 0 : serviceSeqNo.hashCode());
		result = prime * result + (int) (serviceWorkSeqNo ^ (serviceWorkSeqNo >>> 32));
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
		JobWorkMaster other = (JobWorkMaster) obj;
		if (requestSeqNo == null) {
			if (other.requestSeqNo != null)
				return false;
		} else if (!requestSeqNo.equals(other.requestSeqNo))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (jobRefId == null) {
			if (other.jobRefId != null)
				return false;
		} else if (!jobRefId.equals(other.jobRefId))
			return false;
		if (jobTemplateSeqNo == null) {
			if (other.jobTemplateSeqNo != null)
				return false;
		} else if (!jobTemplateSeqNo.equals(other.jobTemplateSeqNo))
			return false;
		if (managerSeqNo == null) {
			if (other.managerSeqNo != null)
				return false;
		} else if (!managerSeqNo.equals(other.managerSeqNo))
			return false;
		if (opFlag == null) {
			if (other.opFlag != null)
				return false;
		} else if (!opFlag.equals(other.opFlag))
			return false;
		if (schFlag == null) {
			if (other.schFlag != null)
				return false;
		} else if (!schFlag.equals(other.schFlag))
			return false;
		if (schType == null) {
			if (other.schType != null)
				return false;
		} else if (!schType.equals(other.schType))
			return false;
		if (serviceSeqNo == null) {
			if (other.serviceSeqNo != null)
				return false;
		} else if (!serviceSeqNo.equals(other.serviceSeqNo))
			return false;
		if (serviceWorkSeqNo != other.serviceWorkSeqNo)
			return false;
		return true;
	}

	public Integer getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(Integer opFlag) {
		this.opFlag = opFlag;
	}

	public JobWorkMaster() {
		super();
	}

	public JobWorkMaster(Long serviceWorkSeqNo, Long requestSeqNo, Timestamp createdOn, Long jobTemplateSeqNo,
			Integer opFlag, Long managerSeqNo, Long serviceSeqNo, String jobRefId, Character schType,
			Character schFlag) {
		super();
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.requestSeqNo = requestSeqNo;
		this.createdOn = createdOn;
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.opFlag = opFlag;
		this.managerSeqNo = managerSeqNo;
		this.serviceSeqNo = serviceSeqNo;
		this.jobRefId = jobRefId;
		this.schType = schType;
		this.schFlag = schFlag;
	}

}