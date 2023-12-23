package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the JOBASSETRES_LOCATION_RULES database table.
 * 
 */
@Entity
@Table(name = "JOBASSETRES_LOCATION_RULES")
public class JobAssetResLocationRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOBASSETRESOURCE_SEQUENCE")
	@SequenceGenerator(name = "JOBASSETRESOURCE_SEQUENCE", sequenceName = "JOBASSETRESOURCE_SEQUENCE", allocationSize = 1)
	@Column(name = "JOBASSETRESOURCE_LOC_SEQ_NO")
	private Long jobassetresourceLocSeqNo;

	@Column(name = "ANYLOC_FLAG")
	private Character anylocFlag;

	@Column(name = "ASSET_SEQ_NO")
	private Long assetSeqNo;

	@Column(name = "EXACT_FLAG")
	private Character exactFlag;

	@Column(name = "JOB_SEQ_NO")
	private Long jobSeqNo;

	@Column(name = "LESSTHAN_DISTANCE")
	private Double lessthanDistance;

	@Column(name = "RESOURCE_SEQ_NO")
	private Long resourceSeqNo;

	public JobAssetResLocationRule() {
	}

	public Long getJobassetresourceLocSeqNo() {
		return jobassetresourceLocSeqNo;
	}

	public void setJobassetresourceLocSeqNo(Long jobassetresourceLocSeqNo) {
		this.jobassetresourceLocSeqNo = jobassetresourceLocSeqNo;
	}

	public Character getAnylocFlag() {
		return anylocFlag;
	}

	public void setAnylocFlag(Character anylocFlag) {
		this.anylocFlag = anylocFlag;
	}

	public Long getAssetSeqNo() {
		return assetSeqNo;
	}

	public void setAssetSeqNo(Long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}

	public Character getExactFlag() {
		return exactFlag;
	}

	public void setExactFlag(Character exactFlag) {
		this.exactFlag = exactFlag;
	}

	public Long getJobSeqNo() {
		return jobSeqNo;
	}

	public void setJobSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	public Double getLessthanDistance() {
		return lessthanDistance;
	}

	public void setLessthanDistance(Double lessthanDistance) {
		this.lessthanDistance = lessthanDistance;
	}

	public Long getResourceSeqNo() {
		return resourceSeqNo;
	}

	public void setResourceSeqNo(Long resourceSeqNo) {
		this.resourceSeqNo = resourceSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetSeqNo == null) ? 0 : assetSeqNo.hashCode());
		result = prime * result + ((jobSeqNo == null) ? 0 : jobSeqNo.hashCode());
		result = prime * result + ((resourceSeqNo == null) ? 0 : resourceSeqNo.hashCode());
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
		JobAssetResLocationRule other = (JobAssetResLocationRule) obj;
		if (assetSeqNo == null) {
			if (other.assetSeqNo != null)
				return false;
		} else if (!assetSeqNo.equals(other.assetSeqNo))
			return false;
		if (jobSeqNo == null) {
			if (other.jobSeqNo != null)
				return false;
		} else if (!jobSeqNo.equals(other.jobSeqNo))
			return false;
		if (resourceSeqNo == null) {
			if (other.resourceSeqNo != null)
				return false;
		} else if (!resourceSeqNo.equals(other.resourceSeqNo))
			return false;
		return true;
	}

	public JobAssetResLocationRule(Long jobassetresourceLocSeqNo, Character anylocFlag, Long assetSeqNo,
			Character exactFlag, Long jobSeqNo, Double lessthanDistance, Long resourceSeqNo) {
		super();
		this.jobassetresourceLocSeqNo = jobassetresourceLocSeqNo;
		this.anylocFlag = anylocFlag;
		this.assetSeqNo = assetSeqNo;
		this.exactFlag = exactFlag;
		this.jobSeqNo = jobSeqNo;
		this.lessthanDistance = lessthanDistance;
		this.resourceSeqNo = resourceSeqNo;
	}

}