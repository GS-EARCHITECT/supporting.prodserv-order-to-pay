package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the JOB_ASSET_MASTER database table.
 * 
 */
@Embeddable
public class JobAssetMasterPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ASSET_SEQ_NO")
	private Long assetSeqNo;

	@Column(name = "JOB_TEMPLATE_SEQ_NO")
	private Long jobTemplateSeqNo;

	@Column(name = "JOB_SEQ_NO")
	private Long jobSeqNo;

	@Column(name = "TARGET_SEQ_NO")
	private Long targetSeqNo;

	@Column(name = "FR_LOC_SEQ_NO")
	private Long frLocSeqNo;

	@Column(name = "TO_LOC_SEQ_NO")
	private Long toLocSeqNo;

	@Column(name = "MODE_SEQ_NO")
	private Long modeSeqNo;

	@Column(name = "SEQ_NO")
	private Long seqNo;

	@Column(name = "TRIP_SEQ_NO")
	private Long tripSeqNo;

	@Column(name = "JOBITEM_MODE")
	private String jobitemMode;

	public JobAssetMasterPK() {
	}

	public Long getAssetSeqNo() {
		return this.assetSeqNo;
	}

	public void setAssetSeqNo(Long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}

	public Long getJobTemplateSeqNo() {
		return this.jobTemplateSeqNo;
	}

	public void setJobTemplateSeqNo(Long jobTemplateSeqNo) {
		this.jobTemplateSeqNo = jobTemplateSeqNo;
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

	public Long getFrLocSeqNo() {
		return this.frLocSeqNo;
	}

	public void setFrLocSeqNo(Long frLocSeqNo) {
		this.frLocSeqNo = frLocSeqNo;
	}

	public Long getToLocSeqNo() {
		return this.toLocSeqNo;
	}

	public void setToLocSeqNo(Long toLocSeqNo) {
		this.toLocSeqNo = toLocSeqNo;
	}

	public Long getModeSeqNo() {
		return this.modeSeqNo;
	}

	public void setModeSeqNo(Long modeSeqNo) {
		this.modeSeqNo = modeSeqNo;
	}

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public Long getTripSeqNo() {
		return this.tripSeqNo;
	}

	public void setTripSeqNo(Long tripSeqNo) {
		this.tripSeqNo = tripSeqNo;
	}

	public String getJobitemMode() {
		return this.jobitemMode;
	}

	public void setJobitemMode(String jobitemMode) {
		this.jobitemMode = jobitemMode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JobAssetMasterPK)) {
			return false;
		}
		JobAssetMasterPK castOther = (JobAssetMasterPK) other;
		return (this.assetSeqNo == castOther.assetSeqNo) && (this.jobTemplateSeqNo == castOther.jobTemplateSeqNo)
				&& (this.jobSeqNo == castOther.jobSeqNo) && (this.targetSeqNo == castOther.targetSeqNo)
				&& (this.frLocSeqNo == castOther.frLocSeqNo) && (this.toLocSeqNo == castOther.toLocSeqNo)
				&& (this.modeSeqNo == castOther.modeSeqNo) && (this.seqNo == castOther.seqNo)
				&& (this.tripSeqNo == castOther.tripSeqNo) && this.jobitemMode.equals(castOther.jobitemMode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.assetSeqNo ^ (this.assetSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.jobTemplateSeqNo ^ (this.jobTemplateSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.jobSeqNo ^ (this.jobSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.targetSeqNo ^ (this.targetSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.frLocSeqNo ^ (this.frLocSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.toLocSeqNo ^ (this.toLocSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.modeSeqNo ^ (this.modeSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.seqNo ^ (this.seqNo >>> 32)));
		hash = hash * prime + ((int) (this.tripSeqNo ^ (this.tripSeqNo >>> 32)));
		hash = hash * prime + this.jobitemMode.hashCode();

		return hash;
	}

	public JobAssetMasterPK(Long assetSeqNo, Long jobTemplateSeqNo, Long jobSeqNo, Long targetSeqNo, Long frLocSeqNo,
			Long toLocSeqNo, Long modeSeqNo, Long seqNo, Long tripSeqNo, String jobitemMode) {
		super();
		this.assetSeqNo = assetSeqNo;
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.jobSeqNo = jobSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.frLocSeqNo = frLocSeqNo;
		this.toLocSeqNo = toLocSeqNo;
		this.modeSeqNo = modeSeqNo;
		this.seqNo = seqNo;
		this.tripSeqNo = tripSeqNo;
		this.jobitemMode = jobitemMode;
	}

}