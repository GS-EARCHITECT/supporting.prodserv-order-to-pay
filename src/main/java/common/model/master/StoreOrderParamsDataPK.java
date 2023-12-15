package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
public class StoreOrderParamsDataPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4441794786401349536L;
	@Column(name = "STORE_REQUEST_SEQ_NO")
	private Long storeRquestSeqNo;

	public Long getStoreRquestSeqNo() {
		return storeRquestSeqNo;
	}

	public void setStoreRquestSeqNo(Long storeRquestSeqNo) {
		this.storeRquestSeqNo = storeRquestSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((storeRquestSeqNo == null) ? 0 : storeRquestSeqNo.hashCode());
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
		StoreOrderParamsDataPK other = (StoreOrderParamsDataPK) obj;
		if (storeRquestSeqNo == null) {
			if (other.storeRquestSeqNo != null)
				return false;
		} else if (!storeRquestSeqNo.equals(other.storeRquestSeqNo))
			return false;
		return true;
	}

	public StoreOrderParamsDataPK(Long storeRquestSeqNo) {
		super();
		this.storeRquestSeqNo = storeRquestSeqNo;
	}

	public StoreOrderParamsDataPK() {
		super();
	}

}