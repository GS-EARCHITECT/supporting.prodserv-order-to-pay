package job_mgmt.job_work_details_mgmt.model.details;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the JOB_TEMPLATE_DETAILS database table.
 * 
 */
@Embeddable
public class JobTemplateDetailPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8329910430372319488L;

	@Column(name = "JOB_TEMPLATE_SEQ_NO")
	private Long jobTemplateSeqNo;

	@Column(name = "JOB_LEVEL_NO")
	private Long jobLevelNo;

	@Column(name = "SEQ_NO")
	private Long seqNo;

	@Column(name = "JOB_SEQ_NO")
	private Long JOB_SEQ_NO;

	@Column(name = "TARGET_SEQ_NO")
	private Long targetSeqNo;

	@Column(name = "TARGET_CLASS_SEQ_NO")
	private Long targetClassSeqNo;

	public JobTemplateDetailPK() {
	}

	public Long getJobTemplateSeqNo() {
		return jobTemplateSeqNo;
	}

	public void setJobTemplateSeqNo(Long jobTemplateSeqNo) {
		this.jobTemplateSeqNo = jobTemplateSeqNo;
	}

	public Long getJobLevelNo() {
		return jobLevelNo;
	}

	public void setJobLevelNo(Long jobLevelNo) {
		this.jobLevelNo = jobLevelNo;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public Long getJobClassSeqNo() {
		return JOB_SEQ_NO;
	}

	public void setJobClassSeqNo(Long JOB_SEQ_NO) {
		this.JOB_SEQ_NO = JOB_SEQ_NO;
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

	public Long getJOB_SEQ_NO() {
		return JOB_SEQ_NO;
	}

	public void setJOB_SEQ_NO(Long jOB_SEQ_NO) {
		JOB_SEQ_NO = jOB_SEQ_NO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((JOB_SEQ_NO == null) ? 0 : JOB_SEQ_NO.hashCode());
		result = prime * result + ((jobLevelNo == null) ? 0 : jobLevelNo.hashCode());
		result = prime * result + ((jobTemplateSeqNo == null) ? 0 : jobTemplateSeqNo.hashCode());
		result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
		result = prime * result + ((targetClassSeqNo == null) ? 0 : targetClassSeqNo.hashCode());
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
		JobTemplateDetailPK other = (JobTemplateDetailPK) obj;
		if (JOB_SEQ_NO == null) {
			if (other.JOB_SEQ_NO != null)
				return false;
		} else if (!JOB_SEQ_NO.equals(other.JOB_SEQ_NO))
			return false;
		if (jobLevelNo == null) {
			if (other.jobLevelNo != null)
				return false;
		} else if (!jobLevelNo.equals(other.jobLevelNo))
			return false;
		if (jobTemplateSeqNo == null) {
			if (other.jobTemplateSeqNo != null)
				return false;
		} else if (!jobTemplateSeqNo.equals(other.jobTemplateSeqNo))
			return false;
		if (seqNo == null) {
			if (other.seqNo != null)
				return false;
		} else if (!seqNo.equals(other.seqNo))
			return false;
		if (targetClassSeqNo == null) {
			if (other.targetClassSeqNo != null)
				return false;
		} else if (!targetClassSeqNo.equals(other.targetClassSeqNo))
			return false;
		if (targetSeqNo == null) {
			if (other.targetSeqNo != null)
				return false;
		} else if (!targetSeqNo.equals(other.targetSeqNo))
			return false;
		return true;
	}

	public JobTemplateDetailPK(Long jobTemplateSeqNo, Long jobLevelNo, Long seqNo, Long jOB_SEQ_NO, Long targetSeqNo,
			Long targetClassSeqNo) {
		super();
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.jobLevelNo = jobLevelNo;
		this.seqNo = seqNo;
		JOB_SEQ_NO = jOB_SEQ_NO;
		this.targetSeqNo = targetSeqNo;
		this.targetClassSeqNo = targetClassSeqNo;
	}

}