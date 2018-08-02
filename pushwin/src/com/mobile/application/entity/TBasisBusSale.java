package com.mobile.application.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * TBasisBusSale entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_BUS_SALE")
public class TBasisBusSale implements java.io.Serializable {

	// Fields

	private TBasisBusSaleId id;
	private String businname;
	private String busintypeid;
	private String dictremark;
	private String saledistinct;
	private String updateuser;
	private String updatetime;
	
	private String dictId;
	

	// Constructors
	@Transient
	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	/** default constructor */
	public TBasisBusSale() {
	}

	/** minimal constructor */
	public TBasisBusSale(TBasisBusSaleId id) {
		this.id = id;
	}

	/** full constructor */
	public TBasisBusSale(TBasisBusSaleId id, String businname,
			String busintypeid, String dictremark, String saledistinct,
			String updateuser, String updatetime) {
		this.id = id;
		this.businname = businname;
		this.busintypeid = busintypeid;
		this.dictremark = dictremark;
		this.saledistinct = saledistinct;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "businid", column = @Column(name = "BUSINID", nullable = false, length = 64)),
			@AttributeOverride(name = "erea", column = @Column(name = "EREA", nullable = false, length = 32)) })
	public TBasisBusSaleId getId() {
		return this.id;
	}

	public void setId(TBasisBusSaleId id) {
		this.id = id;
	}

	@Column(name = "BUSINNAME", length = 128)
	public String getBusinname() {
		return this.businname;
	}

	public void setBusinname(String businname) {
		this.businname = businname;
	}

	@Column(name = "BUSINTYPEID", length = 64)
	public String getBusintypeid() {
		return this.busintypeid;
	}

	public void setBusintypeid(String busintypeid) {
		this.busintypeid = busintypeid;
	}

	@Column(name = "DICTREMARK")
	public String getDictremark() {
		return this.dictremark;
	}

	public void setDictremark(String dictremark) {
		this.dictremark = dictremark;
	}

	@Column(name = "SALEDISTINCT")
	public String getSaledistinct() {
		return this.saledistinct;
	}

	public void setSaledistinct(String saledistinct) {
		this.saledistinct = saledistinct;
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