package jobs.job_mgmt.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class JobTripReturn_DTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1567352038518870286L;
	private Timestamp startFrom;
	private Timestamp endOn;

	public Timestamp getStartFrom() {
		return startFrom;
	}

	public void setStartFrom(Timestamp startFrom) {
		this.startFrom = startFrom;
	}

	public Timestamp getEndOn() {
		return endOn;
	}

	public void setEndOn(Timestamp endOn) {
		this.endOn = endOn;
	}

	public JobTripReturn_DTO(Timestamp startFrom, Timestamp endOn) {
		super();
		this.startFrom = startFrom;
		this.endOn = endOn;
	}

	public JobTripReturn_DTO() {
		super();
	}

}