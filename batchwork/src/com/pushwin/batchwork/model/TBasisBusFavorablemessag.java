package com.pushwin.batchwork.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TBasisBusFavorablemessag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_BUS_FAVORABLEMESSAG")
public class TBasisBusFavorablemessag implements java.io.Serializable {

	// Fields

	private TBasisBusFavorablemessagId id;
	private String businname;
	private String busintypeid;
	private String area;
	private String dictremark;
	private String updateuser;
	private String updatetime;

	// Constructors

	/** default constructor */
	public TBasisBusFavorablemessag() {
	}

	/** minimal constructor */
	public TBasisBusFavorablemessag(TBasisBusFavorablemessagId id,
			String busintypeid, String area) {
		this.id = id;
		this.busintypeid = busintypeid;
		this.area = area;
	}

	/** full constructor */
	public TBasisBusFavorablemessag(TBasisBusFavorablemessagId id,
			String businname, String busintypeid, String area,
			String dictremark, String updateuser, String updatetime) {
		this.id = id;
		this.businname = businname;
		this.busintypeid = busintypeid;
		this.area = area;
		this.dictremark = dictremark;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "businid", column = @Column(name = "BUSINID", nullable = false, length = 64)),
			@AttributeOverride(name = "businidBracnch", column = @Column(name = "BUSINID_BRACNCH", nullable = false, length = 64)) })
	public TBasisBusFavorablemessagId getId() {
		return this.id;
	}

	public void setId(TBasisBusFavorablemessagId id) {
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

	@Column(name = "AREA", nullable = false, length = 32)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
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

}