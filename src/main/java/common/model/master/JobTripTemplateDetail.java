package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the LOGI_JOBTRIPTEMPLATE_DETAILS database table.
 * 
 */
@Entity
@Table(name="JOB_TRIPTEMPLATE_DETAILS")
public class JobTripTemplateDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JobTripTemplateDetailPK id;

	@Column(name="DAYS_PLUS")
	private Integer daysPlus;
	
	@Column(name="MODE_SEQ_NO")
	private Integer modeSeqNo;

	@Column(name="FROM_LOCATION_SEQ_NO")
	private Long fromLocationSeqNo;

	@Column(name="HOURS_PLUS")
	private Integer hoursPlus;

	@Column(name="JOB_CLASS_SEQ_NO")
	private Long jobCategorySeqNo;

	@Column(name="JOB_TYPE")
	private String jobType;

	@Column(name="MINUTES_PLUS")
	private Integer minutesPlus;

	@Column(name="MODE_TRIP")
	private Integer modeTrip;

	@Column(name="PREDECESSOR_SEQ_NO")
	private Long predecessorSeqNo;

	@Column(name="SECONDS_PLUS")
	private Integer secondsPlus;

	@Column(name="TARGET_SEQ_NO")
	private Long targetSeqNo;

	@Column(name="TO_LOCATION_SEQ_NO")
	private Long toLocationSeqNo;
	
	@Column(name = "DUR_DAYS")
	private Integer dur_days;
	
	@Column(name = "DUR_HOURS")
	private Integer dur_hours;
	
	@Column(name = "DUR_MINUTES")
	private Integer dur_miinutes;
	
	@Column(name = "DUR_SECONDS")
	private Integer dur_seconds;

	public JobTripTemplateDetail() {
	}

	public JobTripTemplateDetailPK getId() {
		return this.id;
	}

	public Integer getDaysPlus() {
		return daysPlus;
	}

	public void setDaysPlus(Integer daysPlus) {
		this.daysPlus = daysPlus;
	}

	public Long getFromLocationSeqNo() {
		return fromLocationSeqNo;
	}

	public void setFromLocationSeqNo(Long fromLocationSeqNo) {
		this.fromLocationSeqNo = fromLocationSeqNo;
	}

	public Integer getHoursPlus() {
		return hoursPlus;
	}

	public void setHoursPlus(Integer hoursPlus) {
		this.hoursPlus = hoursPlus;
	}

	public Long getJobCategorySeqNo() {
		return jobCategorySeqNo;
	}

	public void setJobCategorySeqNo(Long jobCategorySeqNo) {
		this.jobCategorySeqNo = jobCategorySeqNo;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public Integer getMinutesPlus() {
		return minutesPlus;
	}

	public void setMinutesPlus(Integer minutesPlus) {
		this.minutesPlus = minutesPlus;
	}

	public Integer getModeTrip() {
		return modeTrip;
	}

	public void setModeTrip(Integer modeTrip) {
		this.modeTrip = modeTrip;
	}

	public Long getPredecessorSeqNo() {
		return predecessorSeqNo;
	}

	public void setPredecessorSeqNo(Long predecessorSeqNo) {
		this.predecessorSeqNo = predecessorSeqNo;
	}

	public Integer getSecondsPlus() {
		return secondsPlus;
	}

	public void setSecondsPlus(Integer secondsPlus) {
		this.secondsPlus = secondsPlus;
	}

	public Long getTargetSeqNo() {
		return targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public Long getToLocationSeqNo() {
		return toLocationSeqNo;
	}

	public void setToLocationSeqNo(Long toLocationSeqNo) {
		this.toLocationSeqNo = toLocationSeqNo;
	}

	public void setId(JobTripTemplateDetailPK id) {
		this.id = id;
	}

	public Integer getDur_days() {
		return dur_days;
	}

	public void setDur_days(Integer dur_days) {
		this.dur_days = dur_days;
	}

	public Integer getDur_hours() {
		return dur_hours;
	}

	public void setDur_hours(Integer dur_hours) {
		this.dur_hours = dur_hours;
	}

	public Integer getDur_miinutes() {
		return dur_miinutes;
	}

	public void setDur_miinutes(Integer dur_miinutes) {
		this.dur_miinutes = dur_miinutes;
	}

	public Integer getDur_seconds() {
		return dur_seconds;
	}

	public void setDur_seconds(Integer dur_seconds) {
		this.dur_seconds = dur_seconds;
	}

	public Integer getModeSeqNo() {
		return modeSeqNo;
	}

	public void setModeSeqNo(Integer modeSeqNo) {
		this.modeSeqNo = modeSeqNo;
	}

	public JobTripTemplateDetail(JobTripTemplateDetailPK id, Integer daysPlus, Integer modeSeqNo,
			Long fromLocationSeqNo, Integer hoursPlus, Long jobCategorySeqNo, String jobType, Integer minutesPlus,
			Integer modeTrip, Long predecessorSeqNo, Integer secondsPlus, Long targetSeqNo,
			Long toLocationSeqNo, Integer dur_days, Integer dur_hours, Integer dur_miinutes, Integer dur_seconds) {
		super();
		this.id = id;
		this.daysPlus = daysPlus;
		this.modeSeqNo = modeSeqNo;
		this.fromLocationSeqNo = fromLocationSeqNo;
		this.hoursPlus = hoursPlus;
		this.jobCategorySeqNo = jobCategorySeqNo;
		this.jobType = jobType;
		this.minutesPlus = minutesPlus;
		this.modeTrip = modeTrip;		
		this.predecessorSeqNo = predecessorSeqNo;
		this.secondsPlus = secondsPlus;
		this.targetSeqNo = targetSeqNo;
		this.toLocationSeqNo = toLocationSeqNo;
		this.dur_days = dur_days;
		this.dur_hours = dur_hours;
		this.dur_miinutes = dur_miinutes;
		this.dur_seconds = dur_seconds;
	}

		
}