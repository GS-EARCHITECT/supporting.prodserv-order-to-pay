package request_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the SERVICE_REQUEST_MASTER database table.
 * 
 */
@Entity
@Table(name = "SERVICE_REQUEST_MASTER")
public class ServiceRequestMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_request_seq_no")
	@SequenceGenerator(name = "customer_request_seq_no", allocationSize = 1)
	@Column(name = "REQUEST_SEQ_NO")
	private Long requestSeqNo;

	@Column(name = "FR_PARTY_LAT")
	private Float frPartyLat;

	@Column(name = "FR_PARTY_LOCATION_SEQ_NO")
	private Long frPartyLocationSeqNo;

	@Column(name = "FR_PARTY_LON")
	private Float frPartyLon;

	@Column(name = "FR_PARTY_SEQ_NO")
	private Long frPartySeqNo;

	@Column(name = "REFERENCE_SEQ_NO")
	private Long referenceSeqNo;

	@Column(name = "REQUEST_DTTM")
	private Timestamp requestDttm;

	@Column(name = "TO_PARTY_LOCATION_SEQ_NO")
	private Long toPartyLocationSeqNo;

	@Column(name = "TO_PARTY_SEQ_NO")
	private Long toPartySeqNo;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DONEFLAG")
	private Character doneFlag;

	@Column(name = "OKFLAG")
	private Character okFlag;

	public ServiceRequestMaster() {
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((frPartySeqNo == null) ? 0 : frPartySeqNo.hashCode());
		result = prime * result + ((requestSeqNo == null) ? 0 : requestSeqNo.hashCode());
		result = prime * result + ((toPartySeqNo == null) ? 0 : toPartySeqNo.hashCode());
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
		ServiceRequestMaster other = (ServiceRequestMaster) obj;
		if (frPartySeqNo == null) {
			if (other.frPartySeqNo != null)
				return false;
		} else if (!frPartySeqNo.equals(other.frPartySeqNo))
			return false;
		if (requestSeqNo == null) {
			if (other.requestSeqNo != null)
				return false;
		} else if (!requestSeqNo.equals(other.requestSeqNo))
			return false;
		if (toPartySeqNo == null) {
			if (other.toPartySeqNo != null)
				return false;
		} else if (!toPartySeqNo.equals(other.toPartySeqNo))
			return false;
		return true;
	}

	public ServiceRequestMaster(Long requestSeqNo, Float frPartyLat, Long frPartyLocationSeqNo, Float frPartyLon,
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

}