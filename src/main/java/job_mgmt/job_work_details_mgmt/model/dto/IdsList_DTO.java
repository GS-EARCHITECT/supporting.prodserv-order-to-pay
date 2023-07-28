package job_mgmt.job_work_details_mgmt.model.dto;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

public class IdsList_DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3431763572172440976L;
	private CopyOnWriteArrayList<Long> ids;
	private CopyOnWriteArrayList<Long> tids;

	public CopyOnWriteArrayList<Long> getIds() {
		return ids;
	}

	public void setIds(CopyOnWriteArrayList<Long> ids) {
		this.ids = ids;
	}

	public CopyOnWriteArrayList<Long> getTids() {
		return tids;
	}

	public void setTids(CopyOnWriteArrayList<Long> tids) {
		this.tids = tids;
	}

	public IdsList_DTO(CopyOnWriteArrayList<Long> ids, CopyOnWriteArrayList<Long> tids) {
		super();
		this.ids = ids;
		this.tids = tids;
	}

	public IdsList_DTO() {
		super();
	}

}