package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the JOB_STRUCTURE_TARGET_DETAILS database table.
 * 
 */
@Embeddable
public class JobStructureTargetDetailPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "JOB_SEQ_NO")
	private Long jobSeqNo;

	@Column(name = "PAR_JOB_SEQ_NO")
	private Long parJobSeqNo;

	@Column(name = "JOB_CLASS_SEQ_NO")
	private Long jobClassSeqNo;

	@Column(name = "TARGET_SEQ_NO")
	private Long targetSeqNo;

	@Column(name = "PAR_TARGET_SEQ_NO")
	private Long parTargetSeqNo;

	@Column(name = "PAR_JOB_CLASS_SEQ_NO")
	private Long parJobClassSeqNo;

	public JobStructureTargetDetailPK() {
	}

	public Long getJobSeqNo() {
		return this.jobSeqNo;
	}

	public void setJobSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	public Long getParJobSeqNo() {
		return this.parJobSeqNo;
	}

	public void setParJobSeqNo(Long parJobSeqNo) {
		this.parJobSeqNo = parJobSeqNo;
	}

	public Long getJobClassSeqNo() {
		return this.jobClassSeqNo;
	}

	public void setJobClassSeqNo(Long jobClassSeqNo) {
		this.jobClassSeqNo = jobClassSeqNo;
	}

	public Long getTargetSeqNo() {
		return this.targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public Long getParJobClassSeqNo() {
		return this.parJobClassSeqNo;
	}

	public void setParJobClassSeqNo(Long parJobClassSeqNo) {
		this.parJobClassSeqNo = parJobClassSeqNo;
	}

	public Long getParTargetSeqNo() {
		return parTargetSeqNo;
	}

	public void setParTargetSeqNo(Long parTargetSeqNo) {
		this.parTargetSeqNo = parTargetSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobClassSeqNo == null) ? 0 : jobClassSeqNo.hashCode());
		result = prime * result + ((jobSeqNo == null) ? 0 : jobSeqNo.hashCode());
		result = prime * result + ((parJobClassSeqNo == null) ? 0 : parJobClassSeqNo.hashCode());
		result = prime * result + ((parJobSeqNo == null) ? 0 : parJobSeqNo.hashCode());
		result = prime * result + ((parTargetSeqNo == null) ? 0 : parTargetSeqNo.hashCode());
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
		JobStructureTargetDetailPK other = (JobStructureTargetDetailPK) obj;
		if (jobClassSeqNo == null) {
			if (other.jobClassSeqNo != null)
				return false;
		} else if (!jobClassSeqNo.equals(other.jobClassSeqNo))
			return false;
		if (jobSeqNo == null) {
			if (other.jobSeqNo != null)
				return false;
		} else if (!jobSeqNo.equals(other.jobSeqNo))
			return false;
		if (parJobClassSeqNo == null) {
			if (other.parJobClassSeqNo != null)
				return false;
		} else if (!parJobClassSeqNo.equals(other.parJobClassSeqNo))
			return false;
		if (parJobSeqNo == null) {
			if (other.parJobSeqNo != null)
				return false;
		} else if (!parJobSeqNo.equals(other.parJobSeqNo))
			return false;
		if (parTargetSeqNo == null) {
			if (other.parTargetSeqNo != null)
				return false;
		} else if (!parTargetSeqNo.equals(other.parTargetSeqNo))
			return false;
		if (targetSeqNo == null) {
			if (other.targetSeqNo != null)
				return false;
		} else if (!targetSeqNo.equals(other.targetSeqNo))
			return false;
		return true;
	}

	public JobStructureTargetDetailPK(Long jobSeqNo, Long parJobSeqNo, Long jobClassSeqNo, Long targetSeqNo,
			Long parTargetSeqNo, Long parJobClassSeqNo) {
		super();
		this.jobSeqNo = jobSeqNo;
		this.parJobSeqNo = parJobSeqNo;
		this.jobClassSeqNo = jobClassSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.parTargetSeqNo = parTargetSeqNo;
		this.parJobClassSeqNo = parJobClassSeqNo;
	}

}