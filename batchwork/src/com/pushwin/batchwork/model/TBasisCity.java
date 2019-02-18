package com.pushwin.batchwork.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisCity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_CITY")
public class TBasisCity implements java.io.Serializable {

	// Fields

	private String cityId;
	private TBasisProvinces TBasisProvinces;
	private String cityName;
	private String zipcode;
	private String updateUser;
	private Timestamp updateTime;
	private Set<TBasisDistrict> TBasisDistricts = new HashSet<TBasisDistrict>(0);

	// Constructors

	/** default constructor */
	public TBasisCity() {
	}

	/** full constructor */
	public TBasisCity(TBasisProvinces TBasisProvinces, String cityName,
			String zipcode, String updateUser, Timestamp updateTime,
			Set<TBasisDistrict> TBasisDistricts) {
		this.TBasisProvinces = TBasisProvinces;
		this.cityName = cityName;
		this.zipcode = zipcode;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.TBasisDistricts = TBasisDistricts;
	}

	// Property accessors
	//@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	//@GeneratedValue(generator = "generator")
	@Column(name = "CITY_ID", unique = true, nullable = false, length = 64)
	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCE_ID")
	public TBasisProvinces getTBasisProvinces() {
		return this.TBasisProvinces;
	}

	public void setTBasisProvinces(TBasisProvinces TBasisProvinces) {
		this.TBasisProvinces = TBasisProvinces;
	}

	@Column(name = "CITY_NAME", length = 256)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "ZIPCODE", length = 12)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TBasisCity")
	public Set<TBasisDistrict> getTBasisDistricts() {
		return this.TBasisDistricts;
	}

	public void setTBasisDistricts(Set<TBasisDistrict> TBasisDistricts) {
		this.TBasisDistricts = TBasisDistricts;
	}

}