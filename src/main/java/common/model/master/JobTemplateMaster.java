package common.model.master;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the JOB_TEMPLATE_MASTER database table.
 * 
 */
@Entity
@Table(name="JOB_TEMPLATE_MASTER")
public class JobTemplateMaster implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JobTemplateMasterPK id;

	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "STATUS")
	private String status;

	@Column(name="SCHEDULE_BASIS")
	private String scheduleBasis;

	public JobTemplateMaster() {
	}

	public JobTemplateMasterPK getId() {
		return this.id;
	}

	public void setId(JobTemplateMasterPK id) {
		this.id = id;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getScheduleBasis() {
		return this.scheduleBasis;
	}

	public void setScheduleBasis(String scheduleBasis) {
		this.scheduleBasis = scheduleBasis;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JobTemplateMaster(JobTemplateMasterPK id, String remarks, String status, String scheduleBasis) {
		super();
		this.id = id;
		this.remarks = remarks;
		this.status = status;
		this.scheduleBasis = scheduleBasis;
	}
	
}