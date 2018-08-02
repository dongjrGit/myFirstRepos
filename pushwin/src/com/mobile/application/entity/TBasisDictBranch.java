package com.mobile.application.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TBasisDictBranch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_DICT_BRANCH")
public class TBasisDictBranch implements java.io.Serializable {

	// Fields

	private TBasisDictBranchId id;
	private String businname;
	private String busintypeid;
	private String status;
	private String dictremark;
	private String updateuser;
	private String updatetime;
	private String productCode;
	
	// Constructors

	/** default constructor */
	public TBasisDictBranch() {
	}

	/** minimal constructor */
	public TBasisDictBranch(TBasisDictBranchId id, String busintypeid,
			String status) {
		this.id = id;
		this.busintypeid = busintypeid;
		this.status = status;
	}

	/** full constructor */
	public TBasisDictBranch(TBasisDictBranchId id, String businname,
			String busintypeid, String status, String dictremark,
			String updateuser, String updatetime) {
		this.id = id;
		this.businname = businname;
		this.busintypeid = busintypeid;
		this.status = status;
		this.dictremark = dictremark;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "businid", column = @Column(name = "BUSINID", nullable = false, length = 64)),
			@AttributeOverride(name = "businidBracnch", column = @Column(name = "BUSINID_BRACNCH", nullable = false, length = 64)) })
	public TBasisDictBranchId getId() {
		return this.id;
	}

	public void setId(TBasisDictBranchId id) {
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
	@Column(name = "PRODUCTCODE", length = 5)
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "TBasisDictBranch [id=" + id + ", businname=" + businname
				+ ", busintypeid=" + busintypeid + ", status=" + status
				+ ", dictremark=" + dictremark + ", updateuser=" + updateuser
				+ ", updatetime=" + updatetime + "]";
	}

}