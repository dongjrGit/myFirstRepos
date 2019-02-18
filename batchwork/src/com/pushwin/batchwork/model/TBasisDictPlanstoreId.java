package com.pushwin.batchwork.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TBasisDictPlanstoreId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TBasisDictPlanstoreId implements java.io.Serializable {

	// Fields

	private String businid;
	private String businidbranch;

	// Constructors

	/** default constructor */
	public TBasisDictPlanstoreId() {
	}

	/** full constructor */
	public TBasisDictPlanstoreId(String businid, String businidbranch) {
		this.businid = businid;
		this.businidbranch = businidbranch;
	}

	// Property accessors

	@Column(name = "BUSINID", nullable = false, length = 64)
	public String getBusinid() {
		return this.businid;
	}

	public void setBusinid(String businid) {
		this.businid = businid;
	}

	@Column(name = "BUSINIDBRANCH", nullable = false, length = 64)
	public String getBusinidbranch() {
		return this.businidbranch;
	}

	public void setBusinidbranch(String businidbranch) {
		this.businidbranch = businidbranch;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TBasisDictPlanstoreId))
			return false;
		TBasisDictPlanstoreId castOther = (TBasisDictPlanstoreId) other;

		return ((this.getBusinid() == castOther.getBusinid()) || (this
				.getBusinid() != null && castOther.getBusinid() != null && this
				.getBusinid().equals(castOther.getBusinid())))
				&& ((this.getBusinidbranch() == castOther.getBusinidbranch()) || (this
						.getBusinidbranch() != null
						&& castOther.getBusinidbranch() != null && this
						.getBusinidbranch()
						.equals(castOther.getBusinidbranch())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBusinid() == null ? 0 : this.getBusinid().hashCode());
		result = 37
				* result
				+ (getBusinidbranch() == null ? 0 : this.getBusinidbranch()
						.hashCode());
		return result;
	}

}