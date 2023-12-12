package common.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "JOB_TRIP_DETAILS")
public class JobTripDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4333389366774575486L;

	@EmbeddedId
	private JobTripDetailsPK job_Trip_pk;

	@Column(name = "JOB_SEQ_NO")
	private Long jobSeqNo;

	@Column(name = "PLAN_START_DATE")
	private Timestamp plan_start_date;

	@Column(name = "PLAN_END_DATE")
	private Timestamp plan_end_date;

	@Column(name = "ACT_START_DATE")
	private Timestamp act_start_date;

	@Column(name = "ACT_END_DATE")
	private Timestamp act_end_date;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "REMARKS")
	private String remarks;

	public JobTripDetailsPK getJob_Trip_pk() {
		return job_Trip_pk;
	}

	public void setJob_Trip_pk(JobTripDetailsPK job_Trip_pk) {
		this.job_Trip_pk = job_Trip_pk;
	}

	public Timestamp getPlan_start_date() {
		return plan_start_date;
	}

	public void setPlan_start_date(Timestamp plan_start_date) {
		this.plan_start_date = plan_start_date;
	}

	public Timestamp getPlan_end_date() {
		return plan_end_date;
	}

	public void setPlan_end_date(Timestamp plan_end_date) {
		this.plan_end_date = plan_end_date;
	}

	public Timestamp getAct_start_date() {
		return act_start_date;
	}

	public void setAct_start_date(Timestamp act_start_date) {
		this.act_start_date = act_start_date;
	}

	public Timestamp getAct_end_date() {
		return act_end_date;
	}

	public void setAct_end_date(Timestamp act_end_date) {
		this.act_end_date = act_end_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getJobSeqNo() {
		return jobSeqNo;
	}

	public void setJobSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	public JobTripDetails() {
		super();
	}

	public JobTripDetails(JobTripDetailsPK job_Trip_pk, Long jobSeqNo, Timestamp plan_start_date,
			Timestamp plan_end_date, Timestamp act_start_date, Timestamp act_end_date, String status, String remarks) {
		super();
		this.job_Trip_pk = job_Trip_pk;
		this.jobSeqNo = jobSeqNo;
		this.plan_start_date = plan_start_date;
		this.plan_end_date = plan_end_date;
		this.act_start_date = act_start_date;
		this.act_end_date = act_end_date;
		this.status = status;
		this.remarks = remarks;
	}

}