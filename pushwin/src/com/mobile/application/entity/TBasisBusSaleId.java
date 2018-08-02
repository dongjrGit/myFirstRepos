package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TBasisBusSaleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TBasisBusSaleId implements java.io.Serializable {

	// Fields

	private String businid;
	private String erea;

	// Constructors

	/** default constructor */
	public TBasisBusSaleId() {
	}

	/** full constructor */
	public TBasisBusSaleId(String businid, String erea) {
		this.businid = businid;
		this.erea = erea;
	}

	// Property accessors

	@Column(name = "BUSINID", nullable = false, length = 64)
	public String getBusinid() {
		return this.businid;
	}

	public void setBusinid(String businid) {
		this.businid = businid;
	}

	@Column(name = "EREA", nullable = false, length = 32)
	public String getErea() {
		return this.erea;
	}

	public void setErea(String erea) {
		this.erea = erea;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TBasisBusSaleId))
			return false;
		TBasisBusSaleId castOther = (TBasisBusSaleId) other;

		return ((this.getBusinid() == castOther.getBusinid()) || (this
				.getBusinid() != null && castOther.getBusinid() != null && this
				.getBusinid().equals(castOther.getBusinid())))
				&& ((this.getErea() == castOther.getErea()) || (this.getErea() != null
						&& castOther.getErea() != null && this.getErea()
						.equals(castOther.getErea())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBusinid() == null ? 0 : this.getBusinid().hashCode());
		result = 37 * result
				+ (getErea() == null ? 0 : this.getErea().hashCode());
		return result;
	}

}