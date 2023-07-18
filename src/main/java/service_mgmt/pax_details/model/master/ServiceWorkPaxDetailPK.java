package service_mgmt.pax_details.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SERVICE_WORK_PAX_DETAILS database table.
 * 
 */
@Embeddable
public class ServiceWorkPaxDetailPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "SERVICE_WORK_SEQ_NO")
	private Long serviceWorkSeqNo;

	@Column(name = "PARTY_SEQ_NO")
	private Long partySeqNo;

	public ServiceWorkPaxDetailPK() {
	}

	public Long getServiceWorkSeqNo() {
		return this.serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public Long getPartySeqNo() {
		return this.partySeqNo;
	}

	public void setPartySeqNo(Long partySeqNo) {
		this.partySeqNo = partySeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ServiceWorkPaxDetailPK)) {
			return false;
		}
		ServiceWorkPaxDetailPK castOther = (ServiceWorkPaxDetailPK) other;
		return (this.serviceWorkSeqNo == castOther.serviceWorkSeqNo) && (this.partySeqNo == castOther.partySeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.serviceWorkSeqNo ^ (this.serviceWorkSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.partySeqNo ^ (this.partySeqNo >>> 32)));

		return hash;
	}

	public ServiceWorkPaxDetailPK(Long serviceWorkSeqNo, Long partySeqNo) {
		super();
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.partySeqNo = partySeqNo;
	}

}