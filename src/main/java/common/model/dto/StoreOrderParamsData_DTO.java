package common.model.dto;

import java.io.Serializable;

public class StoreOrderParamsData_DTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5203705694796229081L;
	private Long storeRequestSeqNo;
	private String locationParams;
	private String requestParams;

	public Long getStoreRequestSeqNo() {
		return storeRequestSeqNo;
	}

	public void setStoreRequestSeqNo(Long storeRequestSeqNo) {
		this.storeRequestSeqNo = storeRequestSeqNo;
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

	public StoreOrderParamsData_DTO(Long storeRequestSeqNo, String locationParams, String requestParams) {
		super();
		this.storeRequestSeqNo = storeRequestSeqNo;
		this.locationParams = locationParams;
		this.requestParams = requestParams;
	}

	public StoreOrderParamsData_DTO() {
		super();
	}

}