package request_mgmt.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

public class ServiceRequestMaster_SDTO implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7980084246533038662L;
	private Long requestSeqNo;
	private Float frPartyLat;
	private Long frPartyLocationSeqNo;
	private Float frPartyLon;
	private Long frPartySeqNo;
	private Long referenceSeqNo;
	private Timestamp requestDttm;
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

	public Float getFrPartyLat() {
		return frPartyLat;
	}

	public void setFrPartyLat(Float frPartyLat) {
		this.frPartyLat = frPartyLat;
	}

	public Long getFrPartyLocationSeqNo() {
		return frPartyLocationSeqNo;
	}

	public void setFrPartyLocationSeqNo(Long frPartyLocationSeqNo) {
		this.frPartyLocationSeqNo = frPartyLocationSeqNo;
	}

	public Float getFrPartyLon() {
		return frPartyLon;
	}

	public void setFrPartyLon(Float frPartyLon) {
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

	public Timestamp getRequestDttm() {
		return requestDttm;
	}

	public void setRequestDttm(Timestamp requestDttm) {
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

	public ServiceRequestMaster_SDTO(Long requestSeqNo, Float frPartyLat, Long frPartyLocationSeqNo, Float frPartyLon,
			Long frPartySeqNo, Long referenceSeqNo, Timestamp requestDttm, Long toPartyLocationSeqNo, Long toPartySeqNo,
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

	public ServiceRequestMaster_SDTO() {
		super();
	}

}