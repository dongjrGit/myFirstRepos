package com.mobile.application.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TBasisDictPlantype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_DICT_PLANTYPE")
public class TBasisDictPlantype implements java.io.Serializable {

	// Fields

	private TBasisDictPlantypeId id;
	private String businid;
	private String businname;
	private String low;
	private String floorlow;
	private String cusclas;
	private String panpro;
	private String status;
	private String dictremark;
	private String updateuser;
	private String updatetime;
	private String beizhu1;

	// Constructors

	/** default constructor */
	public TBasisDictPlantype() {
	}

	/** minimal constructor */
	public TBasisDictPlantype(TBasisDictPlantypeId id, String businid,
			String low, String floorlow, String cusclas, String panpro,
			String status) {
		this.id = id;
		this.businid = businid;
		this.low = low;
		this.floorlow = floorlow;
		this.cusclas = cusclas;
		this.panpro = panpro;
		this.status = status;
	}

	/** full constructor */
	public TBasisDictPlantype(TBasisDictPlantypeId id, String businid,
			String businname, String low, String floorlow, String cusclas,
			String panpro, String status, String dictremark, String updateuser,
			String updatetime, String beizhu1) {
		this.id = id;
		this.businid = businid;
		this.businname = businname;
		this.low = low;
		this.floorlow = floorlow;
		this.cusclas = cusclas;
		this.panpro = panpro;
		this.status = status;
		this.dictremark = dictremark;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
		this.beizhu1 = beizhu1;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "busintypeid", column = @Column(name = "BUSINTYPEID", nullable = false, length = 64)),
			@AttributeOverride(name = "businidbranch", column = @Column(name = "BUSINIDBRANCH", nullable = false, length = 64)),
			@AttributeOverride(name = "oraarea", column = @Column(name = "ORAAREA", nullable = false, length = 64)) })
	public TBasisDictPlantypeId getId() {
		return this.id;
	}

	public void setId(TBasisDictPlantypeId id) {
		this.id = id;
	}

	@Column(name = "BUSINID", nullable = false, length = 64)
	public String getBusinid() {
		return this.businid;
	}

	public void setBusinid(String businid) {
		this.businid = businid;
	}

	@Column(name = "BUSINNAME", length = 256)
	public String getBusinname() {
		return this.businname;
	}

	public void setBusinname(String businname) {
		this.businname = businname;
	}

	@Column(name = "LOW", nullable = false, length = 64)
	public String getLow() {
		return this.low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	@Column(name = "FLOORLOW", nullable = false, length = 64)
	public String getFloorlow() {
		return this.floorlow;
	}

	public void setFloorlow(String floorlow) {
		this.floorlow = floorlow;
	}

	@Column(name = "CUSCLAS", nullable = false, length = 64)
	public String getCusclas() {
		return this.cusclas;
	}

	public void setCusclas(String cusclas) {
		this.cusclas = cusclas;
	}

	@Column(name = "PANPRO", nullable = false, length = 64)
	public String getPanpro() {
		return this.panpro;
	}

	public void setPanpro(String panpro) {
		this.panpro = panpro;
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

	@Column(name = "BEIZHU1", length = 25)
	public String getBeizhu1() {
		return this.beizhu1;
	}

	public void setBeizhu1(String beizhu1) {
		this.beizhu1 = beizhu1;
	}

}