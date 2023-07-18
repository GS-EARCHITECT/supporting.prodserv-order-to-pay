package service_mgmt.pax_details.model.dto;

import java.io.Serializable;

public class ServiceWorkPaxDetail_DTO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7084784544313789621L;
	private Long serviceWorkSeqNo;
	private Long partySeqNo;
	private String remark;
	private String status;
	
	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public Long getPartySeqNo() {
		return partySeqNo;
	}

	public void setPartySeqNo(Long partySeqNo) {
		this.partySeqNo = partySeqNo;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ServiceWorkPaxDetail_DTO(Long serviceWorkSeqNo, Long partySeqNo) {
		super();
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.partySeqNo = partySeqNo;
	}

	public ServiceWorkPaxDetail_DTO() {
		super();
	}

}