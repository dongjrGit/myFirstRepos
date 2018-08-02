package com.mobile.application.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TBasisBusCompabymemo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_BUS_COMPABYMEMO")
public class TBasisBusCompabymemo implements java.io.Serializable {

	// Fields

	private TBasisBusCompabymemoId id;
	private String businname;
	private String busintypeid;
	private String orgArea;
	private String status;
	private String dictremark;
	private String updateuser;
	private String updatetime;
	private String companymeomo1;
	private String companymeomo2;
	private String companymeomo3;
	private String companymeomo4;
	private String companymeomo5;

	// Constructors

	/** default constructor */
	public TBasisBusCompabymemo() {
	}

	/** minimal constructor */
	public TBasisBusCompabymemo(TBasisBusCompabymemoId id, String busintypeid,
			String status) {
		this.id = id;
		this.busintypeid = busintypeid;
		this.status = status;
	}

	/** full constructor */
	public TBasisBusCompabymemo(TBasisBusCompabymemoId id, String businname,
			String busintypeid, String orgArea, String status,
			String dictremark, String updateuser, String updatetime,
			String companymeomo1, String companymeomo2, String companymeomo3,
			String companymeomo4, String companymeomo5) {
		this.id = id;
		this.businname = businname;
		this.busintypeid = busintypeid;
		this.orgArea = orgArea;
		this.status = status;
		this.dictremark = dictremark;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
		this.companymeomo1 = companymeomo1;
		this.companymeomo2 = companymeomo2;
		this.companymeomo3 = companymeomo3;
		this.companymeomo4 = companymeomo4;
		this.companymeomo5 = companymeomo5;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "businid", column = @Column(name = "BUSINID", nullable = false, length = 64)),
			@AttributeOverride(name = "businidBracnch", column = @Column(name = "BUSINID_BRACNCH", nullable = false, length = 64)) })
	public TBasisBusCompabymemoId getId() {
		return this.id;
	}

	public void setId(TBasisBusCompabymemoId id) {
		this.id = id;
	}

	@Column(name = "BUSINNAME", length = 128)
	public String getBusinname() {
		return this.businname;
	}

	public void setBusinname(String businname) {
		this.businname = businname;
	}

	@Column(name = "BUSINTYPEID", nullable = false, length = 64)
	public String getBusintypeid() {
		return this.busintypeid;
	}

	public void setBusintypeid(String busintypeid) {
		this.busintypeid = busintypeid;
	}

	@Column(name = "ORG_AREA", length = 23)
	public String getOrgArea() {
		return this.orgArea;
	}

	public void setOrgArea(String orgArea) {
		this.orgArea = orgArea;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DICTREMARK")
	public String getDictremark() {
		return this.dictremark;
	}

	public void setDictremark(String dictremark) {
		this.dictremark = dictremark;
	}

	@Column(name = "UPDATEUSER", length = 32)
	public String getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	@Column(name = "UPDATETIME", length = 23)
	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "COMPANYMEOMO_1", length = 64)
	public String getCompanymeomo1() {
		return this.companymeomo1;
	}

	public void setCompanymeomo1(String companymeomo1) {
		this.companymeomo1 = companymeomo1;
	}

	@Column(name = "COMPANYMEOMO_2", length = 64)
	public String getCompanymeomo2() {
		return this.companymeomo2;
	}

	public void setCompanymeomo2(String companymeomo2) {
		this.companymeomo2 = companymeomo2;
	}

	@Column(name = "COMPANYMEOMO_3", length = 64)
	public String getCompanymeomo3() {
		return this.companymeomo3;
	}

	public void setCompanymeomo3(String companymeomo3) {
		this.companymeomo3 = companymeomo3;
	}

	@Column(name = "COMPANYMEOMO_4", length = 64)
	public String getCompanymeomo4() {
		return this.companymeomo4;
	}

	public void setCompanymeomo4(String companymeomo4) {
		this.companymeomo4 = companymeomo4;
	}

	@Column(name = "COMPANYMEOMO_5", length = 64)
	public String getCompanymeomo5() {
		return this.companymeomo5;
	}

	public void setCompanymeomo5(String companymeomo5) {
		this.companymeomo5 = companymeomo5;
	}

}