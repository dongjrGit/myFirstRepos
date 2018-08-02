package com.mobile.application.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisDistrict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_DISTRICT")
public class TBasisDistrict implements java.io.Serializable {

	// Fields

	private String districtId;
	private TBasisCity TBasisCity;
	private String districtName;
	private String updateUser;
	private Timestamp updateTime;

	// Constructors

	/** default constructor */
	public TBasisDistrict() {
	}

	/** full constructor */
	public TBasisDistrict(TBasisCity TBasisCity, String districtName,
			String updateUser, Timestamp updateTime) {
		this.TBasisCity = TBasisCity;
		this.districtName = districtName;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "DISTRICT_ID", unique = true, nullable = false, length = 64)
	public String getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID")
	public TBasisCity getTBasisCity() {
		return this.TBasisCity;
	}

	public void setTBasisCity(TBasisCity TBasisCity) {
		this.TBasisCity = TBasisCity;
	}

	@Column(name = "DISTRICT_NAME", length = 256)
	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Column(name = "UPDATE_USER", length = 64)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "UPDATE_TIME", length = 11)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}