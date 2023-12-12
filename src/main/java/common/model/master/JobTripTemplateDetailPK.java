package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the LOGI_JOBTRIPTEMPLATE_DETAILS database table.
 * 
 */
@Embeddable
public class JobTripTemplateDetailPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -171222876983184041L;

	@Column(name="JOB_SEQ_NO")
	private Long jobTypeSeqNo;

	@Column(name="JOB_TEMPLATE_SEQ_NO")
	private Long jobTemplateSeqNo;

	@Column(name="SEQ_NO")
	private Integer seqNo;

	@Column(name="TARGET_CLASS_SEQ_NO")
	private Long targetTypeSeqNo;

	public JobTripTemplateDetailPK() {
	}
	public Long getJobTypeSeqNo() {
		return this.jobTypeSeqNo;
	}
	public void setJobTypeSeqNo(Long jobTypeSeqNo) {
		this.jobTypeSeqNo = jobTypeSeqNo;
	}
	public Long getJobTemplateSeqNo() {
		return this.jobTemplateSeqNo;
	}
	public void setJobTemplateSeqNo(Long jobTemplateSeqNo) {
		this.jobTemplateSeqNo = jobTemplateSeqNo;
	}
	public Integer getSeqNo() {
		return this.seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public Long getTargetTypeSeqNo() {
		return this.targetTypeSeqNo;
	}
	public void setTargetTypeSeqNo(Long targetTypeSeqNo) {
		this.targetTypeSeqNo = targetTypeSeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobTripTemplateDetailPK)) {
			return false;
		}
		JobTripTemplateDetailPK castOther = (JobTripTemplateDetailPK)other;
		return 
			(this.jobTypeSeqNo == castOther.jobTypeSeqNo)
			&& (this.jobTemplateSeqNo == castOther.jobTemplateSeqNo)
			&& (this.seqNo == castOther.seqNo)
			&& (this.targetTypeSeqNo == castOther.targetTypeSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.jobTypeSeqNo ^ (this.jobTypeSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.jobTemplateSeqNo ^ (this.jobTemplateSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.seqNo ^ (this.seqNo >>> 32)));
		hash = hash * prime + ((int) (this.targetTypeSeqNo ^ (this.targetTypeSeqNo >>> 32)));
		
		return hash;
	}
	public JobTripTemplateDetailPK(Long jobTypeSeqNo, Long jobTemplateSeqNo, Integer seqNo, Long targetTypeSeqNo) {
		super();
		this.jobTypeSeqNo = jobTypeSeqNo;
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.seqNo = seqNo;
		this.targetTypeSeqNo = targetTypeSeqNo;
	}
	
}