package common.model.dto;

import java.io.Serializable;

public class LocationVector_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3254399826631545955L;
	private Long locationSeqNo;
	private String locationId;
	private Long mapidSeqNo;
	private String placeVector;
	private Long specificationSeqNo;
	private Double lat;
	private Double lon;

	public Long getLocationSeqNo() {
		return locationSeqNo;
	}

	public void setLocationSeqNo(Long locationSeqNo) {
		this.locationSeqNo = locationSeqNo;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Long getMapidSeqNo() {
		return mapidSeqNo;
	}

	public void setMapidSeqNo(Long mapidSeqNo) {
		this.mapidSeqNo = mapidSeqNo;
	}

	public String getPlaceVector() {
		return placeVector;
	}

	public void setPlaceVector(String placeVector) {
		this.placeVector = placeVector;
	}

	public Long getSpecificationSeqNo() {
		return specificationSeqNo;
	}

	public void setSpecificationSeqNo(Long specificationSeqNo) {
		this.specificationSeqNo = specificationSeqNo;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public LocationVector_DTO() {
		super();
	}

}