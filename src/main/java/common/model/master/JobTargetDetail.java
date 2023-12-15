package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "JOB_TARGET_DETAILS")
public class JobTargetDetail implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JobTargetDetailPK id;

	@Column(name = "DUR_DAYS")
	private Float durDays;

	@Column(name = "DUR_HOURS")
	private Float durHours;

	@Column(name = "DUR_MINUTES")
	private Float durMinutes;

	@Column(name = "DUR_MONTHS")
	private Float durMonths;

	@Column(name = "DUR_SECONDS")
	private Float durSeconds;

	@Column(name = "DUR_WEEKS")
	private Float durWeeks;

	@Column(name = "DURATION")
	private Float duration;

	@Column(name = "DURATION_SEQ_NO")
	private Long durationSeqNo;

	@Column(name = "RATE_SEQ_NO")
	private Long rateSeqNo;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "UNIT_RATE")
	private Float unitRate;

	public JobTargetDetail() {
	}

	public JobTargetDetailPK getId() {
		return id;
	}

	public void setId(JobTargetDetailPK id) {
		this.id = id;
	}

	public Float getDurDays() {
		return durDays;
	}

	public void setDurDays(Float durDays) {
		this.durDays = durDays;
	}

	public Float getDurHours() {
		return durHours;
	}

	public void setDurHours(Float durHours) {
		this.durHours = durHours;
	}

	public Float getDurMinutes() {
		return durMinutes;
	}

	public void setDurMinutes(Float durMinutes) {
		this.durMinutes = durMinutes;
	}

	public Float getDurMonths() {
		return durMonths;
	}

	public void setDurMonths(Float durMonths) {
		this.durMonths = durMonths;
	}

	public Float getDurSeconds() {
		return durSeconds;
	}

	public void setDurSeconds(Float durSeconds) {
		this.durSeconds = durSeconds;
	}

	public Float getDurWeeks() {
		return durWeeks;
	}

	public void setDurWeeks(Float durWeeks) {
		this.durWeeks = durWeeks;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public Long getDurationSeqNo() {
		return durationSeqNo;
	}

	public void setDurationSeqNo(Long durationSeqNo) {
		this.durationSeqNo = durationSeqNo;
	}

	public Long getRateSeqNo() {
		return rateSeqNo;
	}

	public void setRateSeqNo(Long rateSeqNo) {
		this.rateSeqNo = rateSeqNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getUnitRate() {
		return unitRate;
	}

	public void setUnitRate(Float unitRate) {
		this.unitRate = unitRate;
	}

	public JobTargetDetail(JobTargetDetailPK id, Float durDays, Float durHours, Float durMinutes,
			Float durMonths, Float durSeconds, Float durWeeks, Float duration, Long durationSeqNo,
			Long rateSeqNo, String remarks, String status, Float unitRate) {
		super();
		this.id = id;
		this.durDays = durDays;
		this.durHours = durHours;
		this.durMinutes = durMinutes;
		this.durMonths = durMonths;
		this.durSeconds = durSeconds;
		this.durWeeks = durWeeks;
		this.duration = duration;
		this.durationSeqNo = durationSeqNo;
		this.rateSeqNo = rateSeqNo;
		this.remarks = remarks;
		this.status = status;
		this.unitRate = unitRate;
	}

}