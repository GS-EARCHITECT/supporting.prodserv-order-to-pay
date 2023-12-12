package jobs.job_work_master_mgmt.model.dto;

import java.io.Serializable;

public class JobWorkMaster_DTO implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private Long serviceWorkSeqNo;
	private Long requestSeqNo;
	private String createdOn;
	private Long jobTemplateSeqNo;
	private Integer opFlag;
	private Long managerSeqNo;
	private Long serviceSeqNo;
	private String jobRefId;
	private Character schType;
	private Character schFlag;

	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
	}

	public Long getRequestSeqNo() {
		return requestSeqNo;
	}

	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public Long getJobTemplateSeqNo() {
		return jobTemplateSeqNo;
	}

	public void setJobTemplateSeqNo(Long jobTemplateSeqNo) {
		this.jobTemplateSeqNo = jobTemplateSeqNo;
	}

	public Integer getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(Integer opFlag) {
		this.opFlag = opFlag;
	}

	public Long getManagerSeqNo() {
		return managerSeqNo;
	}

	public void setManagerSeqNo(Long managerSeqNo) {
		this.managerSeqNo = managerSeqNo;
	}

	public Long getServiceSeqNo() {
		return serviceSeqNo;
	}

	public void setServiceSeqNo(Long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}

	public String getJobRefId() {
		return jobRefId;
	}

	public void setJobRefId(String jobRefId) {
		this.jobRefId = jobRefId;
	}

	public Character getSchType() {
		return schType;
	}

	public void setSchType(Character schType) {
		this.schType = schType;
	}

	public Character getSchFlag() {
		return schFlag;
	}

	public void setSchFlag(Character schFlag) {
		this.schFlag = schFlag;
	}

	public JobWorkMaster_DTO(Long serviceWorkSeqNo, Long requestSeqNo, String createdOn, Long jobTemplateSeqNo,
			Integer opFlag, Long managerSeqNo, Long serviceSeqNo, String jobRefId, Character schType,
			Character schFlag) {
		super();
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.requestSeqNo = requestSeqNo;
		this.createdOn = createdOn;
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.opFlag = opFlag;
		this.managerSeqNo = managerSeqNo;
		this.serviceSeqNo = serviceSeqNo;
		this.jobRefId = jobRefId;
		this.schType = schType;
		this.schFlag = schFlag;
	}

	public JobWorkMaster_DTO() {
		super();
	}

}