package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the SALES_MASTER database table.
 * 
 */
@Entity
@Table(name = "SERVICE_MOVEMENT_MASTER")
public class ServiceMovementMaster implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1164301367514104663L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long service_work_seq_no;

	@Column(name = "FROM_LOCATION_SEQ_NO")
	private Long from_location_seq_no;

	@Column(name = "TO_LOCATION_SEQ_NO")
	private Long to_location_seq_no;

	@Column(name = "REMARKS")
	private String remark;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SERVICE_SEQ_NO")
	private Long service_seq_no;

	public Long getService_work_seq_no() {
		return service_work_seq_no;
	}

	public void setService_work_seq_no(Long service_work_seq_no) {
		this.service_work_seq_no = service_work_seq_no;
	}

	public Long getFrom_location_seq_no() {
		return from_location_seq_no;
	}

	public void setFrom_location_seq_no(Long from_location_seq_no) {
		this.from_location_seq_no = from_location_seq_no;
	}

	public Long getTo_location_seq_no() {
		return to_location_seq_no;
	}

	public void setTo_location_seq_no(Long to_location_seq_no) {
		this.to_location_seq_no = to_location_seq_no;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getService_seq_no() {
		return service_seq_no;
	}

	public void setService_seq_no(Long service_seq_no) {
		this.service_seq_no = service_seq_no;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((service_seq_no == null) ? 0 : service_seq_no.hashCode());
		result = prime * result + ((service_work_seq_no == null) ? 0 : service_work_seq_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceMovementMaster other = (ServiceMovementMaster) obj;
		if (service_seq_no == null) {
			if (other.service_seq_no != null)
				return false;
		} else if (!service_seq_no.equals(other.service_seq_no))
			return false;
		if (service_work_seq_no == null) {
			if (other.service_work_seq_no != null)
				return false;
		} else if (!service_work_seq_no.equals(other.service_work_seq_no))
			return false;
		return true;
	}

	public ServiceMovementMaster(Long service_work_seq_no, Long from_location_seq_no, Long to_location_seq_no,
			String remark, String status, Long service_seq_no) {
		super();
		this.service_work_seq_no = service_work_seq_no;
		this.from_location_seq_no = from_location_seq_no;
		this.to_location_seq_no = to_location_seq_no;
		this.remark = remark;
		this.status = status;
		this.service_seq_no = service_seq_no;
	}

	public ServiceMovementMaster() {
		super();
	}

}