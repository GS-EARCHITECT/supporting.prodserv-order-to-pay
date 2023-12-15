package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the STORE_ORDERPARAMS_DATA database table.
 * 
 */
@Entity
@Table(name = "STORE_ORDERPARAMS_DATA")
public class StoreOrderParamsData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StoreOrderParamsDataPK storeOrderParamsDataPK;

	@Column(name = "LOCATION_PARAMS")
	private String locationParams;

	@Column(name = "REQUEST_PARAMS")
	private String requestParams;

	public StoreOrderParamsData() {
	}

	public StoreOrderParamsDataPK getStoreOrderParamsDataPK() {
		return storeOrderParamsDataPK;
	}

	public void setStoreOrderParamsDataPK(StoreOrderParamsDataPK storeOrderParamsDataPK) {
		this.storeOrderParamsDataPK = storeOrderParamsDataPK;
	}

	public String getLocationParams() {
		return locationParams;
	}

	public void setLocationParams(String locationParams) {
		this.locationParams = locationParams;
	}

	public String getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(String requestParams) {
		this.requestParams = requestParams;
	}

	public StoreOrderParamsData(StoreOrderParamsDataPK storeOrderParamsDataPK, String locationParams,
			String requestParams) {
		super();
		this.storeOrderParamsDataPK = storeOrderParamsDataPK;
		this.locationParams = locationParams;
		this.requestParams = requestParams;
	}

}