package jobs.job_target_details.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the JOB_TYPE_TARGET_DETAILS database table.
 * 
 */
@Embeddable
public class JobTargetDetailPK implements Serializable 
{
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="JOB_SEQ_NO")
	private Long jobSeqNo;

	@Column(name="TARGET_SEQ_NO")
	private Long targetSeqNo;

	public JobTargetDetailPK() {
	}
	
	public Long getJobSeqNo() {
		return this.jobSeqNo;
	}
	public void setJobSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}
	public Long getTargetSeqNo() {
		return this.targetSeqNo;
	}
	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobTargetDetailPK)) {
			return false;
		}
		JobTargetDetailPK castOther = (JobTargetDetailPK)other;
		return 
			(this.jobSeqNo == castOther.jobSeqNo)
			&& (this.targetSeqNo == castOther.targetSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.jobSeqNo ^ (this.jobSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.targetSeqNo ^ (this.targetSeqNo >>> 32)));
		
		return hash;
	}
}