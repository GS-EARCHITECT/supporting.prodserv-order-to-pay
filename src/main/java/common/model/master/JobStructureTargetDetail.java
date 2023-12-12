package common.model.master;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the JOB_STRUCTURE_TARGET_DETAILS database table.
 * 
 */
@Entity
@Table(name="JOB_STRUCTURE_TARGET_DETAILS")
public class JobStructureTargetDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JobStructureTargetDetailPK id;

	@Column(name="DD")
	private Integer dd;

	@Column(name="DH")
	private Integer dh;

	@Column(name="DM")
	private Integer dm;

	@Column(name="DMO")
	private Integer dmo;

	@Column(name="DS")
	private Integer ds;

	@Column(name="DW")
	private Integer dw;

	@Column(name="PD")
	private Integer pd;

	@Column(name="PH")
	private Integer ph;

	@Column(name="PM")
	private Integer pm;

	@Column(name="PMO")
	private Integer pmo;

	@Column(name="PS")
	private Integer ps;

	@Column(name="PW")
	private Integer pw;

	@Column(name="REMARK")
	private String remark;

	@Column(name="SEQ_NO")
	private Integer seqNo;

	@Column(name="STATUS")
	private String status;

	public JobStructureTargetDetail() {
	}

	public JobStructureTargetDetailPK getId() {
		return this.id;
	}

	public void setId(JobStructureTargetDetailPK id) {
		this.id = id;
	}

	public Integer getDd() {
		return this.dd;
	}

	public void setDd(Integer dd) {
		this.dd = dd;
	}

	public Integer getDh() {
		return this.dh;
	}

	public void setDh(Integer dh) {
		this.dh = dh;
	}

	public Integer getDm() {
		return this.dm;
	}

	public void setDm(Integer dm) {
		this.dm = dm;
	}

	public Integer getDmo() {
		return this.dmo;
	}

	public void setDmo(Integer dmo) {
		this.dmo = dmo;
	}

	public Integer getDs() {
		return this.ds;
	}

	public void setDs(Integer ds) {
		this.ds = ds;
	}

	public Integer getDw() {
		return this.dw;
	}

	public void setDw(Integer dw) {
		this.dw = dw;
	}

	public Integer getPd() {
		return this.pd;
	}

	public void setPd(Integer pd) {
		this.pd = pd;
	}

	public Integer getPh() {
		return this.ph;
	}

	public void setPh(Integer ph) {
		this.ph = ph;
	}

	public Integer getPm() {
		return this.pm;
	}

	public void setPm(Integer pm) {
		this.pm = pm;
	}

	public Integer getPmo() {
		return this.pmo;
	}

	public void setPmo(Integer pmo) {
		this.pmo = pmo;
	}

	public Integer getPs() {
		return this.ps;
	}

	public void setPs(Integer ps) {
		this.ps = ps;
	}

	public Integer getPw() {
		return this.pw;
	}

	public void setPw(Integer pw) {
		this.pw = pw;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}