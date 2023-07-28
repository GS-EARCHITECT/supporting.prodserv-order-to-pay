package job_mgmt.job_work_details_mgmt.model.details;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the JOB_STRUCTURE_TARGET_DETAILS database table.
 * 
 */
@Entity
@Table(name = "JOB_STRUCTURE_TARGET_DETAILS")
public class JobStructureTargetDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JobStructureTargetDetailPK id;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "SEQ_NO")
	private Long seqNo;

	@Column(name = "STATUS")
	private String status;

	public JobStructureTargetDetail() {
	}

	public JobStructureTargetDetailPK getId() {
		return this.id;
	}

	public void setId(JobStructureTargetDetailPK id) {
		this.id = id;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JobStructureTargetDetail(JobStructureTargetDetailPK id, String remark, Long seqNo,
			String status) {
		super();
		this.id = id;
		this.remark = remark;
		this.seqNo = seqNo;
		this.status = status;
	}
	
	

}