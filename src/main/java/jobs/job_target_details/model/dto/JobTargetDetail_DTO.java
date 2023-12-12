package jobs.job_target_details.model.dto;

import java.io.Serializable;

public class JobTargetDetail_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3155705397624659763L;
	private Long jobSeqNo;
	private Long targetSeqNo;
	private Float durDays;
	private Float durHours;
	private Float durMinutes;
	private Float durMonths;
	private Float durSeconds;
	private Float durWeeks;
	private Float duration;
	private Long durationSeqNo;
	private Long rateSeqNo;
	private String remarks;
	private String status;
	private Float unitRate;

	public Long getJobSeqNo() {
		return jobSeqNo;
	}

	public void setJobSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	public Long getTargetSeqNo() {
		return targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
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

	public JobTargetDetail_DTO(Long jobSeqNo, Long targetSeqNo, Float durDays, Float durHours, Float durMinutes,
			Float durMonths, Float durSeconds, Float durWeeks, Float duration, Long durationSeqNo, Long rateSeqNo,
			String remarks, String status, Float unitRate) {
		super();
		this.jobSeqNo = jobSeqNo;
		this.targetSeqNo = targetSeqNo;
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

	public JobTargetDetail_DTO() {
		super();
	}

}