package common.model.dto;

import java.io.Serializable;

public class ServiceMovementDTO implements Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7796979376011209204L;
	private Integer service_work_seq_no;
	private Integer from_location_seq_no;
	private Integer to_location_seq_no;
	private String remark;
	private String status;
	private Integer service_cat_seq_no;
	
	public Integer getService_work_seq_no() {
		return service_work_seq_no;
	}
	
	public void setService_work_seq_no(Integer service_work_seq_no) {
		this.service_work_seq_no = service_work_seq_no;
	}
	public Integer getFrom_location_seq_no() {
		return from_location_seq_no;
	}
	public void setFrom_location_seq_no(Integer from_location_seq_no) {
		this.from_location_seq_no = from_location_seq_no;
	}
	public Integer getTo_location_seq_no() {
		return to_location_seq_no;
	}
	public void setTo_location_seq_no(Integer to_location_seq_no) {
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
	public Integer getService_type_seq_no() {
		return service_cat_seq_no;
	}
	public void setService_type_seq_no(Integer service_cat_seq_no) {
		this.service_cat_seq_no = service_cat_seq_no;
	}
	public ServiceMovementDTO(Integer service_work_seq_no, Integer from_location_seq_no, Integer to_location_seq_no,
			String remark, String status, Integer service_cat_seq_no) {
		super();
		this.service_work_seq_no = service_work_seq_no;
		this.from_location_seq_no = from_location_seq_no;
		this.to_location_seq_no = to_location_seq_no;
		this.remark = remark;
		this.status = status;
		this.service_cat_seq_no = service_cat_seq_no;
	}
	public ServiceMovementDTO() {
		super();
	}
	
}