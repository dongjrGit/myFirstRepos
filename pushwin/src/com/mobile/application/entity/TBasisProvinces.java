package com.mobile.application.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisProvinces entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_PROVINCES")
public class TBasisProvinces implements java.io.Serializable {

	// Fields

	private String provinceId;
	private String provinceName;
	private String orgId;
	private String updateUser;
	private Timestamp updateTime;
	private Set<TBasisCity> TBasisCities = new HashSet<TBasisCity>(0);

	// Constructors

	/** default constructor */
	public TBasisProvinces() {
	}

	/** full constructor */
	public TBasisProvinces(String provinceName, String orgId,
			String updateUser, Timestamp updateTime,
			Set<TBasisCity> TBasisCities) {
		this.provinceName = provinceName;
		this.orgId = orgId;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.TBasisCities = TBasisCities;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "PROVINCE_ID", unique = true, nullable = false, length = 64)
	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "PROVINCE_NAME", length = 256)
	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Column(name = "ORG_ID", length = 64)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TBasisProvinces")
	public Set<TBasisCity> getTBasisCities() {
		return this.TBasisCities;
	}

	public void setTBasisCities(Set<TBasisCity> TBasisCities) {
		this.TBasisCities = TBasisCities;
	}

}