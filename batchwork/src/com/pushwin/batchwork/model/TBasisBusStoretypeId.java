package com.pushwin.batchwork.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TBasisBusStoretypeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TBasisBusStoretypeId implements java.io.Serializable {

	// Fields

	private String storeTypeNumber;
	private String storeTypeName;
	private String updateTime;
	private String companymemo1;
	private String companymemo2;
	private String companymemo3;
	private String companymemo4;
	private String companymemo5;

	// Constructors

	/** default constructor */
	public TBasisBusStoretypeId() {
	}

	/** minimal constructor */
	public TBasisBusStoretypeId(String storeTypeNumber, String storeTypeName,
			String updateTime) {
		this.storeTypeNumber = storeTypeNumber;
		this.storeTypeName = storeTypeName;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public TBasisBusStoretypeId(String storeTypeNumber, String storeTypeName,
			String updateTime, String companymemo1, String companymemo2,
			String companymemo3, String companymemo4, String companymemo5) {
		this.storeTypeNumber = storeTypeNumber;
		this.storeTypeName = storeTypeName;
		this.updateTime = updateTime;
		this.companymemo1 = companymemo1;
		this.companymemo2 = companymemo2;
		this.companymemo3 = companymemo3;
		this.companymemo4 = companymemo4;
		this.companymemo5 = companymemo5;
	}

	// Property accessors

	@Column(name = "STORE_TYPE_NUMBER", nullable = false, length = 6)
	public String getStoreTypeNumber() {
		return this.storeTypeNumber;
	}

	public void setStoreTypeNumber(String storeTypeNumber) {
		this.storeTypeNumber = storeTypeNumber;
	}

	@Column(name = "STORE_TYPE_NAME", nullable = false, length = 20)
	public String getStoreTypeName() {
		return this.storeTypeName;
	}

	public void setStoreTypeName(String storeTypeName) {
		this.storeTypeName = storeTypeName;
	}

	@Column(name = "UPDATE_TIME", nullable = false, length = 20)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "COMPANYMEMO1", length = 25)
	public String getCompanymemo1() {
		return this.companymemo1;
	}

	public void setCompanymemo1(String companymemo1) {
		this.companymemo1 = companymemo1;
	}

	@Column(name = "COMPANYMEMO2", length = 25)
	public String getCompanymemo2() {
		return this.companymemo2;
	}

	public void setCompanymemo2(String companymemo2) {
		this.companymemo2 = companymemo2;
	}

	@Column(name = "COMPANYMEMO3", length = 25)
	public String getCompanymemo3() {
		return this.companymemo3;
	}

	public void setCompanymemo3(String companymemo3) {
		this.companymemo3 = companymemo3;
	}

	@Column(name = "COMPANYMEMO4", length = 25)
	public String getCompanymemo4() {
		return this.companymemo4;
	}

	public void setCompanymemo4(String companymemo4) {
		this.companymemo4 = companymemo4;
	}

	@Column(name = "COMPANYMEMO5", length = 25)
	public String getCompanymemo5() {
		return this.companymemo5;
	}

	public void setCompanymemo5(String companymemo5) {
		this.companymemo5 = companymemo5;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TBasisBusStoretypeId))
			return false;
		TBasisBusStoretypeId castOther = (TBasisBusStoretypeId) other;

		return ((this.getStoreTypeNumber() == castOther.getStoreTypeNumber()) || (this
				.getStoreTypeNumber() != null
				&& castOther.getStoreTypeNumber() != null && this
				.getStoreTypeNumber().equals(castOther.getStoreTypeNumber())))
				&& ((this.getStoreTypeName() == castOther.getStoreTypeName()) || (this
						.getStoreTypeName() != null
						&& castOther.getStoreTypeName() != null && this
						.getStoreTypeName()
						.equals(castOther.getStoreTypeName())))
				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
						.getUpdateTime() != null
						&& castOther.getUpdateTime() != null && this
						.getUpdateTime().equals(castOther.getUpdateTime())))
				&& ((this.getCompanymemo1() == castOther.getCompanymemo1()) || (this
						.getCompanymemo1() != null
						&& castOther.getCompanymemo1() != null && this
						.getCompanymemo1().equals(castOther.getCompanymemo1())))
				&& ((this.getCompanymemo2() == castOther.getCompanymemo2()) || (this
						.getCompanymemo2() != null
						&& castOther.getCompanymemo2() != null && this
						.getCompanymemo2().equals(castOther.getCompanymemo2())))
				&& ((this.getCompanymemo3() == castOther.getCompanymemo3()) || (this
						.getCompanymemo3() != null
						&& castOther.getCompanymemo3() != null && this
						.getCompanymemo3().equals(castOther.getCompanymemo3())))
				&& ((this.getCompanymemo4() == castOther.getCompanymemo4()) || (this
						.getCompanymemo4() != null
						&& castOther.getCompanymemo4() != null && this
						.getCompanymemo4().equals(castOther.getCompanymemo4())))
				&& ((this.getCompanymemo5() == castOther.getCompanymemo5()) || (this
						.getCompanymemo5() != null
						&& castOther.getCompanymemo5() != null && this
						.getCompanymemo5().equals(castOther.getCompanymemo5())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getStoreTypeNumber() == null ? 0 : this.getStoreTypeNumber()
						.hashCode());
		result = 37
				* result
				+ (getStoreTypeName() == null ? 0 : this.getStoreTypeName()
						.hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		result = 37
				* result
				+ (getCompanymemo1() == null ? 0 : this.getCompanymemo1()
						.hashCode());
		result = 37
				* result
				+ (getCompanymemo2() == null ? 0 : this.getCompanymemo2()
						.hashCode());
		result = 37
				* result
				+ (getCompanymemo3() == null ? 0 : this.getCompanymemo3()
						.hashCode());
		result = 37
				* result
				+ (getCompanymemo4() == null ? 0 : this.getCompanymemo4()
						.hashCode());
		result = 37
				* result
				+ (getCompanymemo5() == null ? 0 : this.getCompanymemo5()
						.hashCode());
		return result;
	}

}