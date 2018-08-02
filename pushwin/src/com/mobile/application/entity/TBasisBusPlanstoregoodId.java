package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TBasisBusPlanstoregoodId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TBasisBusPlanstoregoodId implements java.io.Serializable {

	// Fields

	private String businid;
	private String storetypeid;

	// Constructors

	/** default constructor */
	public TBasisBusPlanstoregoodId() {
	}

	/** full constructor */
	public TBasisBusPlanstoregoodId(String businid, String storetypeid) {
		this.businid = businid;
		this.storetypeid = storetypeid;
	}

	// Property accessors

	@Column(name = "BUSINID", nullable = false, length = 64)
	public String getBusinid() {
		return this.businid;
	}

	public void setBusinid(String businid) {
		this.businid = businid;
	}

	@Column(name = "STORETYPEID", nullable = false, length = 64)
	public String getStoretypeid() {
		return this.storetypeid;
	}

	public void setStoretypeid(String storetypeid) {
		this.storetypeid = storetypeid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TBasisBusPlanstoregoodId))
			return false;
		TBasisBusPlanstoregoodId castOther = (TBasisBusPlanstoregoodId) other;

		return ((this.getBusinid() == castOther.getBusinid()) || (this
				.getBusinid() != null && castOther.getBusinid() != null && this
				.getBusinid().equals(castOther.getBusinid())))
				&& ((this.getStoretypeid() == castOther.getStoretypeid()) || (this
						.getStoretypeid() != null
						&& castOther.getStoretypeid() != null && this
						.getStoretypeid().equals(castOther.getStoretypeid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBusinid() == null ? 0 : this.getBusinid().hashCode());
		result = 37
				* result
				+ (getStoretypeid() == null ? 0 : this.getStoretypeid()
						.hashCode());
		return result;
	}

}