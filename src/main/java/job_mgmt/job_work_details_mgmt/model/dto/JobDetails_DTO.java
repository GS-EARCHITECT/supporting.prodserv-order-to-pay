package job_mgmt.job_work_details_mgmt.model.dto;

import java.io.Serializable;

public class JobDetails_DTO implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3431763572172440976L;
	private long jobWorkSeqNo;
	private long seqNo;
	private Long jobTemplateSeqNo;
	private Long targetSeqNo;
	private String actEndDate;
	private String actStartDate;
	private Long jobSeqNo;
	private Long managerSeqNo;
	private Long parentJobWorkSeqNo;
	private String planEndDate;
	private String planStartDate;
	private Long serviceWorkSeqNo;
	private String remarks;
	private String status;

	public long getJobSeqNo() {
		return jobWorkSeqNo;
	}

	public void setJobSeqNo(long jobWorkSeqNo) {
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

	public Long getJobTypeSeqNo() {
		return jobSeqNo;
	}

	public void setJobTypeSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	public Long getManagerSeqNo() {
		return managerSeqNo;
	}

	public void setManagerSeqNo(Long managerSeqNo) {
		this.managerSeqNo = managerSeqNo;
	}

	public Long getParentJobSeqNo() {
		return parentJobWorkSeqNo;
	}

	public void setParentJobSeqNo(Long parentJobWorkSeqNo) {
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

	public Long getServiceWorkSeqNo() {
		return serviceWorkSeqNo;
	}

	public void setServiceWorkSeqNo(Long serviceWorkSeqNo) {
		this.serviceWorkSeqNo = serviceWorkSeqNo;
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

	public Long getJobTemplateSeqNo() {
		return jobTemplateSeqNo;
	}

	public void setJobTemplateSeqNo(Long jobTemplateSeqNo) {
		this.jobTemplateSeqNo = jobTemplateSeqNo;
	}

	public JobDetails_DTO() {
		super();
	}

	public Long getTargetSeqNo() {
		return targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(long seqNo) {
		this.seqNo = seqNo;
	}

	public JobDetails_DTO(long jobWorkSeqNo, long seqNo, Long jobTemplateSeqNo, Long targetSeqNo, String actEndDate,
			String actStartDate, Long jobSeqNo, Long managerSeqNo, Long parentJobWorkSeqNo, String planEndDate,
			String planStartDate, Long serviceWorkSeqNo, String remarks, String status) 
	{
		super();
		this.jobWorkSeqNo = jobWorkSeqNo;
		this.seqNo = seqNo;
		this.jobTemplateSeqNo = jobTemplateSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.actEndDate = actEndDate;
		this.actStartDate = actStartDate;
		this.jobSeqNo = jobSeqNo;
		this.managerSeqNo = managerSeqNo;
		this.parentJobWorkSeqNo = parentJobWorkSeqNo;
		this.planEndDate = planEndDate;
		this.planStartDate = planStartDate;
		this.serviceWorkSeqNo = serviceWorkSeqNo;
		this.remarks = remarks;
		this.status = status;
	}

}