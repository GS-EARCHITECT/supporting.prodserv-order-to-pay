package common.model.dto;

import java.io.Serializable;

public class JobWorkDetail_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 920828495010340561L;
	private Long jobWorkSeqNo;
	private String actEndDate;
	private String actStartDate;
	private Character doneflag;
	private Long jobSeqNo;	
	private Long jobTemplateSeqNo;
	private Long managerSeqNo;
	private Character okflag;
	private Long parentJobWorkSeqNo;
	private String planEndDate;
	private String planStartDate;
	private Long seqNo;
	private Long serviceWorkSeqNo;
	private Long targetSeqNo;
	private Long targetWorkSeqNo;

	public Long getJobWorkSeqNo() {
		return jobWorkSeqNo;
	}

	public void setJobWorkSeqNo(Long jobWorkSeqNo) {
		this.jobWorkSeqNo = jobWorkSeqNo;
	}

	public String getActEndDate() {
		return actEndDate;
	}

	public void setActEndDate(String actEndDate) {
		this.actEndDate = actEndDate;
	}

	public String getActStartDate() {
		return actStartDate;
	}

	public void setActStartDate(String actStartDate) {
		this.actStartDate = actStartDate;
	}

	public Character getDoneflag() {
		return doneflag;
	}

	public void setDoneflag(Character doneflag) {
		this.doneflag = doneflag;
	}

	public Long getJobSeqNo() {
		return jobSeqNo;
	}

	public void setJobSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	public Long getJobTemplateSeqNo() {
		return jobTemplateSeqNo;
	}

	public void setJobTemplateSeqNo(Long jobTemplateSeqNo) {
		this.jobTemplateSeqNo = jobTemplateSeqNo;
	}

	public Long getManagerSeqNo() {
		return managerSeqNo;
	}

	public void setManagerSeqNo(Long managerSeqNo) {
		this.managerSeqNo = managerSeqNo;
	}

	public Character getOkflag() {
		return okflag;
	}

	public void setOkflag(Character okflag) {
		this.okflag = okflag;
	}

	public Long getParentJobWorkSeqNo() {
		return parentJobWorkSeqNo;
	}

	public void setParentJobWorkSeqNo(Long parentJobWorkSeqNo) {
		this.parentJobWorkSeqNo = parentJobWorkSeqNo;
	}

	public String getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(String planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public Long getTargetSeqNo() {
		return targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public Long getTargetWorkSeqNo() {
		return targetWorkSeqNo;
	}

	public void setTargetWorkSeqNo(Long targetWorkSeqNo) {
		this.targetWorkSeqNo = targetWorkSeqNo;
	}

	public JobWorkDetail_DTO(Long jobWorkSeqNo, String actEndDate, String actStartDate, Character doneflag,
			Long jobSeqNo, Long jobTemplateSeqNo, Long managerSeqNo, Character okflag,
			Long parentJobWorkSeqNo, String planEndDate, String planStartDate, Long seqNo, Long serviceWorkSeqNo,
			Long targetSeqNo, Long targetWorkSeqNo) {
		super();
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.actEndDate = actEndDate;
		this.actStartDate = actStartDate;
		this.doneflag = doneflag;
		this.jobSeqNo = jobSeqNo;
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.managerSeqNo = managerSeqNo;
		this.okflag = okflag;
		this.parentJobWorkSeqNo = parentJobWorkSeqNo;
		this.planEndDate = planEndDate;
		this.planStartDate = planStartDate;
		this.seqNo = seqNo;
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.targetWorkSeqNo = targetWorkSeqNo;
	}

	public JobWorkDetail_DTO() {
		super();
	}

}