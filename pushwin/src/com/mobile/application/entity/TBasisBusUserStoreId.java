package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TBasisBusUserStoreId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TBasisBusUserStoreId implements java.io.Serializable {

	// Fields

	private String usercode;
	private String storenumber;

	// Constructors

	/** default constructor */
	public TBasisBusUserStoreId() {
	}

	/** full constructor */
	public TBasisBusUserStoreId(String usercode, String storenumber) {
		this.usercode = usercode;
		this.storenumber = storenumber;
	}

	// Property accessors

	@Column(name = "USERCODE", nullable = false, length = 32)
	public String getUsercode() {
		return this.usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	@Column(name = "STORENUMBER", nullable = false, length = 128)
	public String getStorenumber() {
		return this.storenumber;
	}

	public void setStorenumber(String storenumber) {
		this.storenumber = storenumber;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TBasisBusUserStoreId))
			return false;
		TBasisBusUserStoreId castOther = (TBasisBusUserStoreId) other;

		return ((this.getUsercode() == castOther.getUsercode()) || (this
				.getUsercode() != null && castOther.getUsercode() != null && this
				.getUsercode().equals(castOther.getUsercode())))
				&& ((this.getStorenumber() == castOther.getStorenumber()) || (this
						.getStorenumber() != null
						&& castOther.getStorenumber() != null && this
						.getStorenumber().equals(castOther.getStorenumber())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUsercode() == null ? 0 : this.getUsercode().hashCode());
		result = 37
				* result
				+ (getStorenumber() == null ? 0 : this.getStorenumber()
						.hashCode());
		return result;
	}

}