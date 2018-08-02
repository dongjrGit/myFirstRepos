package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TBasisBusIgroupId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TBasisBusIgroupId implements java.io.Serializable {

	// Fields

	private String groupNo;
	private String orgCode;

	// Constructors

	/** default constructor */
	public TBasisBusIgroupId() {
	}

	/** full constructor */
	public TBasisBusIgroupId(String groupNo, String orgCode) {
		this.groupNo = groupNo;
		this.orgCode = orgCode;
	}

	// Property accessors

	@Column(name = "GROUP_NO", nullable = false, length = 20)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Column(name = "ORG_CODE", nullable = false, length = 4)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TBasisBusIgroupId))
			return false;
		TBasisBusIgroupId castOther = (TBasisBusIgroupId) other;

		return ((this.getGroupNo() == castOther.getGroupNo()) || (this
				.getGroupNo() != null && castOther.getGroupNo() != null && this
				.getGroupNo().equals(castOther.getGroupNo())))
				&& ((this.getOrgCode() == castOther.getOrgCode()) || (this
						.getOrgCode() != null && castOther.getOrgCode() != null && this
						.getOrgCode().equals(castOther.getOrgCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupNo() == null ? 0 : this.getGroupNo().hashCode());
		result = 37 * result
				+ (getOrgCode() == null ? 0 : this.getOrgCode().hashCode());
		return result;
	}

}