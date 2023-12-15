package common.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class JobWorkDetailTimesDiff_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6406725451682620492L;
	private Long interMilliDiff;
	private Long intraMilliDiff;
	private Integer opFlag;
	private Boolean noSchdFlagInteger;
	private Long oldjobWorkSeqNo;
	private Long jobSeqNo;
	private Timestamp oldFrom;
	private Timestamp newFrom;
	private Timestamp oldTo;
	private Timestamp newTo;

	public Long getInterMilliDiff() {
		return interMilliDiff;
	}

	public void setInterMilliDiff(Long interMilliDiff) {
		this.interMilliDiff = interMilliDiff;
	}

	public Long getIntraMilliDiff() {
		return intraMilliDiff;
	}

	public void setIntraMilliDiff(Long intraMilliDiff) {
		this.intraMilliDiff = intraMilliDiff;
	}

	public Integer getOpFlag() {
		return opFlag;
	}

	public void setOpFlag(Integer opFlag) {
		this.opFlag = opFlag;
	}

	public Boolean getNoSchdFlagInteger() {
		return noSchdFlagInteger;
	}

	public void setNoSchdFlagInteger(Boolean noSchdFlagInteger) {
		this.noSchdFlagInteger = noSchdFlagInteger;
	}

	public Long getOldjobWorkSeqNo() {
		return oldjobWorkSeqNo;
	}

	public void setOldjobWorkSeqNo(Long oldjobWorkSeqNo) {
		this.oldjobWorkSeqNo = oldjobWorkSeqNo;
	}

	
	
	public Timestamp getOldFrom() {
		return oldFrom;
	}

	public void setOldFrom(Timestamp oldFrom) {
		this.oldFrom = oldFrom;
	}

	public Timestamp getNewFrom() {
		return newFrom;
	}

	public void setNewFrom(Timestamp newFrom) {
		this.newFrom = newFrom;
	}

	public Timestamp getOldTo() {
		return oldTo;
	}

	public void setOldTo(Timestamp oldTo) {
		this.oldTo = oldTo;
	}

	public Timestamp getNewTo() {
		return newTo;
	}

	public void setNewTo(Timestamp newTo) {
		this.newTo = newTo;
	}

	
	
	public Long getJobSeqNo() {
		return jobSeqNo;
	}

	public void setJobSeqNo(Long jobSeqNo) {
		this.jobSeqNo = jobSeqNo;
	}

	public JobWorkDetailTimesDiff_DTO(Long interMilliDiff, Long intraMilliDiff, Integer opFlag,
			Boolean noSchdFlagInteger, Long oldjobWorkSeqNo, Long jobSeqNo, Timestamp oldFrom, Timestamp newFrom,
			Timestamp oldTo, Timestamp newTo) {
		super();
		this.interMilliDiff = interMilliDiff;
		this.intraMilliDiff = intraMilliDiff;
		this.opFlag = opFlag;
		this.noSchdFlagInteger = noSchdFlagInteger;
		this.oldjobWorkSeqNo = oldjobWorkSeqNo;
		this.jobSeqNo = jobSeqNo;
		this.oldFrom = oldFrom;
		this.newFrom = newFrom;
		this.oldTo = oldTo;
		this.newTo = newTo;
	}

	public JobWorkDetailTimesDiff_DTO() {
		super();
	}

}