package common.model.dto;

import java.io.Serializable;

public class ResourceAllocationFailureDTO implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3780710289056873086L;
	private Long allocSeqNo;
	private Long resSeqNo;	
	private Long assetSeqNo;
	private Long jobSeqNo;
	public Long getAllocSeqNo() {
		return allocSeqNo;
	}
	public void setAllocSeqNo(Long allocSeqNo) {
		this.allocSeqNo = allocSeqNo;
	}
	public Long getResSeqNo() {
		return resSeqNo;
	}
	public void setResSeqNo(Long resSeqNo) {
		this.resSeqNo = resSeqNo;
	}
	public Long getAssetSeqNo() {
		return assetSeqNo;
	}
	public void setAssetSeqNo(Long assetSeqNo) {
		this.assetSeqNo = assetSeqNo;
	}
	public Long getJobSeqNo() {
		return jobSeqNo;
	}
	public void setJobSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}
	public ResourceAllocationFailureDTO(Long allocSeqNo, Long resSeqNo, Long assetSeqNo, Long jobSeqNo) {
		super();
		this.allocSeqNo = allocSeqNo;
		this.resSeqNo = resSeqNo;
		this.assetSeqNo = assetSeqNo;
		this.jobSeqNo = jobSeqNo;
	}
	public ResourceAllocationFailureDTO() {
		super();
	}
}