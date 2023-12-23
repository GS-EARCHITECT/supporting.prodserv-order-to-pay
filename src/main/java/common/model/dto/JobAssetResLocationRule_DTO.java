package common.model.dto;

import java.io.Serializable;

public class JobAssetResLocationRule_DTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9105949698608415692L;
	private Long jobassetresourceLocSeqNo;
	private Character anylocFlag;
	private Long assetSeqNo;
	private Character exactFlag;
	private Long jobSeqNo;
	private Double lessthanDistance;
	private Long resourceSeqNo;

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

	public JobAssetResLocationRule_DTO(Long jobassetresourceLocSeqNo, Character anylocFlag, Long assetSeqNo,
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

	public JobAssetResLocationRule_DTO() {
		super();
	}

}