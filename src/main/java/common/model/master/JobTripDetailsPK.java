package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class JobTripDetailsPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7190476070113243162L;

	@Column(name = "JOB_WORK_SEQ_NO")
	private Long jobWorkSeqNo;

	@Column(name = "MODE_SEQ_NO")
	private Integer modeSeqNo;

	@Column(name = "from_location_seq_no")
	private Long fromLocationSeqNo;

	@Column(name = "to_location_seq_no")
	private Long toLocationSeqNo;

	public Long getFromLocationSeqNo() {
		return fromLocationSeqNo;
	}

	public void setFromLocationSeqNo(Long fromLocationSeqNo) {
		this.fromLocationSeqNo = fromLocationSeqNo;
	}

	public Long getToLocationSeqNo() {
		return toLocationSeqNo;
	}

	public void setToLocationSeqNo(Long toLocationSeqNo) {
		this.toLocationSeqNo = toLocationSeqNo;
	}

	public Integer getModeSeqNo() {
		return modeSeqNo;
	}

	public void setModeSeqNo(Integer modeSeqNo) {
		this.modeSeqNo = modeSeqNo;
	}

	public JobTripDetailsPK() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromLocationSeqNo == null) ? 0 : fromLocationSeqNo.hashCode());
		result = prime * result + ((jobWorkSeqNo == null) ? 0 : jobWorkSeqNo.hashCode());
		result = prime * result + ((modeSeqNo == null) ? 0 : modeSeqNo.hashCode());
		result = prime * result + ((toLocationSeqNo == null) ? 0 : toLocationSeqNo.hashCode());
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
		JobTripDetailsPK other = (JobTripDetailsPK) obj;
		if (fromLocationSeqNo == null) {
			if (other.fromLocationSeqNo != null)
				return false;
		} else if (!fromLocationSeqNo.equals(other.fromLocationSeqNo))
			return false;
		if (jobWorkSeqNo == null) {
			if (other.jobWorkSeqNo != null)
				return false;
		} else if (!jobWorkSeqNo.equals(other.jobWorkSeqNo))
			return false;
		if (modeSeqNo == null) {
			if (other.modeSeqNo != null)
				return false;
		} else if (!modeSeqNo.equals(other.modeSeqNo))
			return false;
		if (toLocationSeqNo == null) {
			if (other.toLocationSeqNo != null)
				return false;
		} else if (!toLocationSeqNo.equals(other.toLocationSeqNo))
			return false;
		return true;
	}

	public Long getJobWorkSeqNo() {
		return jobWorkSeqNo;
	}

	public void setJobWorkSeqNo(Long jobWorkSeqNo) {
		this.jobWorkSeqNo = jobWorkSeqNo;
	}

	public JobTripDetailsPK(Long jobWorkSeqNo, Integer modeSeqNo, Long fromLocationSeqNo, Long toLocationSeqNo) {
		super();
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.modeSeqNo = modeSeqNo;
		this.fromLocationSeqNo = fromLocationSeqNo;
		this.toLocationSeqNo = toLocationSeqNo;
	}

}