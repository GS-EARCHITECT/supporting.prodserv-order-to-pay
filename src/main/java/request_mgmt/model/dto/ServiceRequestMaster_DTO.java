package request_mgmt.model.dto;

import java.io.Serializable;

public class ServiceRequestMaster_DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6253937430640952512L;
	private Long requestSeqNo;
	private Double frPartyLat;
	private Long frPartyLocationSeqNo;
	private Double frPartyLon;
	private Long frPartySeqNo;
	private Long referenceSeqNo;
	private String requestDttm;
	private Long toPartyLocationSeqNo;
	private Long toPartySeqNo;
	private String remark;
	private String status;
	private Character doneFlag;
	private Character okFlag;

	public Long getRequestSeqNo() {
		return requestSeqNo;
	}

	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public Double getFrPartyLat() {
		return frPartyLat;
	}

	public void setFrPartyLat(Double frPartyLat) {
		this.frPartyLat = frPartyLat;
	}

	public Long getFrPartyLocationSeqNo() {
		return frPartyLocationSeqNo;
	}

	public void setFrPartyLocationSeqNo(Long frPartyLocationSeqNo) {
		this.frPartyLocationSeqNo = frPartyLocationSeqNo;
	}

	public Double getFrPartyLon() {
		return frPartyLon;
	}

	public void setFrPartyLon(Double frPartyLon) {
		this.frPartyLon = frPartyLon;
	}

	public Long getFrPartySeqNo() {
		return frPartySeqNo;
	}

	public void setFrPartySeqNo(Long frPartySeqNo) {
		this.frPartySeqNo = frPartySeqNo;
	}

	public Long getReferenceSeqNo() {
		return referenceSeqNo;
	}

	public void setReferenceSeqNo(Long referenceSeqNo) {
		this.referenceSeqNo = referenceSeqNo;
	}

	public String getRequestDttm() {
		return requestDttm;
	}

	public void setRequestDttm(String requestDttm) {
		this.requestDttm = requestDttm;
	}

	public Long getToPartyLocationSeqNo() {
		return toPartyLocationSeqNo;
	}

	public void setToPartyLocationSeqNo(Long toPartyLocationSeqNo) {
		this.toPartyLocationSeqNo = toPartyLocationSeqNo;
	}

	public Long getToPartySeqNo() {
		return toPartySeqNo;
	}

	public void setToPartySeqNo(Long toPartySeqNo) {
		this.toPartySeqNo = toPartySeqNo;
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

	public Character getDoneFlag() {
		return doneFlag;
	}

	public void setDoneFlag(Character doneFlag) {
		this.doneFlag = doneFlag;
	}

	public Character getOkFlag() {
		return okFlag;
	}

	public void setOkFlag(Character okFlag) {
		this.okFlag = okFlag;
	}

	public ServiceRequestMaster_DTO() {
		super();
	}

	public ServiceRequestMaster_DTO(Long requestSeqNo, Double frPartyLat, Long frPartyLocationSeqNo, Double frPartyLon,
			Long frPartySeqNo, Long referenceSeqNo, String requestDttm, Long toPartyLocationSeqNo, Long toPartySeqNo,
			String remark, String status, Character doneFlag, Character okFlag) {
		super();
		this.requestSeqNo = requestSeqNo;
		this.frPartyLat = frPartyLat;
		this.frPartyLocationSeqNo = frPartyLocationSeqNo;
		this.frPartyLon = frPartyLon;
		this.frPartySeqNo = frPartySeqNo;
		this.referenceSeqNo = referenceSeqNo;
		this.requestDttm = requestDttm;
		this.toPartyLocationSeqNo = toPartyLocationSeqNo;
		this.toPartySeqNo = toPartySeqNo;
		this.remark = remark;
		this.status = status;
		this.doneFlag = doneFlag;
		this.okFlag = okFlag;
	}

}