package job_mgmt.job_work_details_mgmt.model.details;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the JOB_TEMPLATE_DETAILS database table.
 * 
 */
@Entity
@Table(name = "JOB_TEMPLATE_DETAILS")
public class JobTemplateDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JobTemplateDetailPK id;

	@Column(name = "WEEKS_PLUS")
	private Integer weeksPlus;

	@Column(name = "MONTHS_PLUS")
	private Integer monthsPlus;

	@Column(name = "DAYS_PLUS")
	private Integer daysPlus;

	@Column(name = "DUR_DAYS")
	private Integer durDays;

	@Column(name = "DUR_HOURS")
	private Integer durHours;

	@Column(name = "DUR_MINUTES")
	private Integer durMinutes;

	@Column(name = "DUR_MONTHS")
	private Integer durMonths;

	@Column(name = "DUR_SECONDS")
	private Integer durSeconds;

	@Column(name = "DUR_WEEKS")
	private Integer durWeeks;

	@Column(name = "HOURS_PLUS")
	private Integer hoursPlus;

	@Column(name = "MINUTES_PLUS")
	private Integer minutesPlus;

	@Column(name = "PREDECESSOR_SEQ_NO")
	private Integer predecessorSeqNo;

	@Column(name = "SECONDS_PLUS")
	private Integer secondsPlus;

	@Column(name = "DURATION_FLAG")
	private Character durationFlag;

	public JobTemplateDetail() {
	}

	public JobTemplateDetailPK getId() {
		return id;
	}

	public void setId(JobTemplateDetailPK id) {
		this.id = id;
	}

	public Integer getWeeksPlus() {
		return weeksPlus;
	}

	public void setWeeksPlus(Integer weeksPlus) {
		this.weeksPlus = weeksPlus;
	}

	public Integer getMonthsPlus() {
		return monthsPlus;
	}

	public void setMonthsPlus(Integer monthsPlus) {
		this.monthsPlus = monthsPlus;
	}

	public Integer getDaysPlus() {
		return daysPlus;
	}

	public void setDaysPlus(Integer daysPlus) {
		this.daysPlus = daysPlus;
	}

	public Integer getDurDays() {
		return durDays;
	}

	public void setDurDays(Integer durDays) {
		this.durDays = durDays;
	}

	public Integer getDurHours() {
		return durHours;
	}

	public void setDurHours(Integer durHours) {
		this.durHours = durHours;
	}

	public Integer getDurMinutes() {
		return durMinutes;
	}

	public void setDurMinutes(Integer durMinutes) {
		this.durMinutes = durMinutes;
	}

	public Integer getDurMonths() {
		return durMonths;
	}

	public void setDurMonths(Integer durMonths) {
		this.durMonths = durMonths;
	}

	public Integer getDurSeconds() {
		return durSeconds;
	}

	public void setDurSeconds(Integer durSeconds) {
		this.durSeconds = durSeconds;
	}

	public Integer getDurWeeks() {
		return durWeeks;
	}

	public void setDurWeeks(Integer durWeeks) {
		this.durWeeks = durWeeks;
	}

	public Integer getHoursPlus() {
		return hoursPlus;
	}

	public void setHoursPlus(Integer hoursPlus) {
		this.hoursPlus = hoursPlus;
	}

	public Integer getMinutesPlus() {
		return minutesPlus;
	}

	public void setMinutesPlus(Integer minutesPlus) {
		this.minutesPlus = minutesPlus;
	}

	public Integer getPredecessorSeqNo() {
		return predecessorSeqNo;
	}

	public void setPredecessorSeqNo(Integer predecessorSeqNo) {
		this.predecessorSeqNo = predecessorSeqNo;
	}

	public Integer getSecondsPlus() {
		return secondsPlus;
	}

	public void setSecondsPlus(Integer secondsPlus) {
		this.secondsPlus = secondsPlus;
	}

	public Character getDurationFlag() {
		return durationFlag;
	}

	public void setDurationFlag(Character durationFlag) {
		this.durationFlag = durationFlag;
	}

	public JobTemplateDetail(JobTemplateDetailPK id, Integer weeksPlus, Integer monthsPlus, Integer daysPlus,
			Integer durDays, Integer durHours, Integer durMinutes, Integer durMonths, Integer durSeconds,
			Integer durWeeks, Integer hoursPlus, Integer minutesPlus, Integer predecessorSeqNo, Integer secondsPlus,
			Character durationFlag) {
		super();
		this.id = id;
		this.weeksPlus = weeksPlus;
		this.monthsPlus = monthsPlus;
		this.daysPlus = daysPlus;
		this.durDays = durDays;
		this.durHours = durHours;
		this.durMinutes = durMinutes;
		this.durMonths = durMonths;
		this.durSeconds = durSeconds;
		this.durWeeks = durWeeks;
		this.hoursPlus = hoursPlus;
		this.minutesPlus = minutesPlus;
		this.predecessorSeqNo = predecessorSeqNo;
		this.secondsPlus = secondsPlus;
		this.durationFlag = durationFlag;
	}

}