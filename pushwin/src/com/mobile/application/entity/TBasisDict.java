package com.mobile.application.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TbBusinDict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_DICT")
public class TBasisDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TBasisDictId id;
	private String businname;
	private String status;
	private String dictremark;
	private String updateuser;
	private String updatetime;
	
	// Constructors

	/** default constructor */
	public TBasisDict() {
	}

	/** minimal constructor */
	public TBasisDict(TBasisDictId id, String status) {
		this.id = id;
		this.status = status;
	}

	/** full constructor */
	public TBasisDict(TBasisDictId id, String businname, String status,
			String dictremark, String updateuser, String updatetime) {
		this.id = id;
		this.businname = businname;
		this.status = status;
		this.dictremark = dictremark;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
	@AttributeOverride(name = "BUSINID", column = @Column(name = "BUSINID", nullable = false, length = 64)),
	@AttributeOverride(name = "BUSINTYPEID", column = @Column(name = "BUSINTYPEID", nullable = false, length = 64)) })
	public TBasisDictId getId() {
		return this.id;
	}

	public void setId(TBasisDictId id) {
		this.id = id;
	}

	@Column(name = "BUSINNAME", length = 128)
	public String getBusinname() {
		return this.businname;
	}

	public void setBusinname(String businname) {
		this.businname = businname;
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
    @Column(name = "UPDATETIME", length = 19)
	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "TBasisDict [id=" + id + ", businname=" + businname
				+ ", status=" + status + ", dictremark=" + dictremark
				+ ", updateuser=" + updateuser + ", updatetime=" + updatetime
				+ "]";
	}
	
	
}