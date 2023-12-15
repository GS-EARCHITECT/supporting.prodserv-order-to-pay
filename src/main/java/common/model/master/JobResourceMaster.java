package common.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the JOB_RESOURCE_MASTER database table.
 * 
 */
@Entity
@Table(name = "JOB_RESOURCE_MASTER")
public class JobResourceMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private JobResourceMasterPK id;

	@Column(name = "DIRECTIOBFLAG")
	private Character directionflag;

	@Column(name = "RATE_SEQ_NO")
	private Long rateSeqNo;

	@Column(name = "RETURNFLAG")
	private Character returnflag;

	@Column(name = "QTY")
	private Float qty;

	@Column(name = "QTY_SEQ_NO")
	private Long qtySeqNo;

	@Column(name = "UNIT_RATE")
	private Float unitRate;

	public JobResourceMaster() {
	}

	public JobResourceMasterPK getId() {
		return this.id;
	}

	public void setId(JobResourceMasterPK id) {
		this.id = id;
	}

	public Character getDirectionflag() {
		return this.directionflag;
	}

	public void setDirectionflag(Character directionflag) {
		this.directionflag = directionflag;
	}

	public Float getQty() {
		return this.qty;
	}

	public void setQty(Float qty) {
		this.qty = qty;
	}

	public Long getQtySeqNo() {
		return this.qtySeqNo;
	}

	public void setQtySeqNo(Long qtySeqNo) {
		this.qtySeqNo = qtySeqNo;
	}

	public Long getRateSeqNo() {
		return this.rateSeqNo;
	}

	public void setRateSeqNo(Long rateSeqNo) {
		this.rateSeqNo = rateSeqNo;
	}

	public Character getReturnflag() {
		return this.returnflag;
	}

	public void setReturnflag(Character returnflag) {
		this.returnflag = returnflag;
	}

	public Float getUnitRate() {
		return this.unitRate;
	}

	public void setUnitRate(Float unitRate) {
		this.unitRate = unitRate;
	}

	public JobResourceMaster(JobResourceMasterPK id, Character directionflag, Long rateSeqNo, Character returnflag,
			Float qty, Long qtySeqNo, Float unitRate) {
		super();
		this.id = id;
		this.directionflag = directionflag;
		this.rateSeqNo = rateSeqNo;
		this.returnflag = returnflag;
		this.qty = qty;
		this.qtySeqNo = qtySeqNo;
		this.unitRate = unitRate;
	}

}