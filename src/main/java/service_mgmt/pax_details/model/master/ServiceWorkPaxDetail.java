package service_mgmt.pax_details.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the SERVICE_WORK_PAX_DETAILS database table.
 * 
 */
@Entity
@Table(name = "SERVICE_WORK_PAX_DETAILS")
public class ServiceWorkPaxDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ServiceWorkPaxDetailPK id;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "STATUS")
	private String status;

	public ServiceWorkPaxDetail() {
	}

	public ServiceWorkPaxDetailPK getId() {
		return this.id;
	}

	public void setId(ServiceWorkPaxDetailPK id) {
		this.id = id;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ServiceWorkPaxDetail(ServiceWorkPaxDetailPK id, String remark, String status) {
		super();
		this.id = id;
		this.remark = remark;
		this.status = status;
	}

}