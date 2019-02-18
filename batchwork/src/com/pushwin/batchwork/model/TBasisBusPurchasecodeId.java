package com.pushwin.batchwork.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TBasisBusPurchasecodeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TBasisBusPurchasecodeId implements java.io.Serializable {

	// Fields

	private String businid;
	private String businidBracnch;

	// Constructors

	/** default constructor */
	public TBasisBusPurchasecodeId() {
	}

	/** full constructor */
	public TBasisBusPurchasecodeId(String businid, String businidBracnch) {
		this.businid = businid;
		this.businidBracnch = businidBracnch;
	}

	// Property accessors

	@Column(name = "BUSINID", nullable = false, length = 64)
	public String getBusinid() {
		return this.businid;
	}

	public void setBusinid(String businid) {
		this.businid = businid;
	}

	@Column(name = "BUSINID_BRACNCH", nullable = false, length = 64)
	public String getBusinidBracnch() {
		return this.businidBracnch;
	}

	public void setBusinidBracnch(String businidBracnch) {
		this.businidBracnch = businidBracnch;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TBasisBusPurchasecodeId))
			return false;
		TBasisBusPurchasecodeId castOther = (TBasisBusPurchasecodeId) other;

		return ((this.getBusinid() == castOther.getBusinid()) || (this
				.getBusinid() != null && castOther.getBusinid() != null && this
				.getBusinid().equals(castOther.getBusinid())))
				&& ((this.getBusinidBracnch() == castOther.getBusinidBracnch()) || (this
						.getBusinidBracnch() != null
						&& castOther.getBusinidBracnch() != null && this
						.getBusinidBracnch().equals(
								castOther.getBusinidBracnch())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBusinid() == null ? 0 : this.getBusinid().hashCode());
		result = 37
				* result
				+ (getBusinidBracnch() == null ? 0 : this.getBusinidBracnch()
						.hashCode());
		return result;
	}

}