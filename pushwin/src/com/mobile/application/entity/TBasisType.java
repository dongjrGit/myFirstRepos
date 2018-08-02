package com.mobile.application.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TbBusinType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_TYPE")
public class TBasisType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String busintypeid;
	private String busintypename;
	private String businremark;
	private String status;
	private String updateuser;
	private String updatetime;

	// Constructors

	/** default constructor */
	public TBasisType() {
	}

	/** minimal constructor */
	public TBasisType(String busintypeid, String status) {
		this.busintypeid = busintypeid;
		this.status = status;
	}

	/** full constructor */
	public TBasisType(String busintypeid, String busintypename,
			String businremark, String status, String updateuser,
			String updatetime) {
		this.busintypeid = busintypeid;
		this.busintypename = busintypename;
		this.businremark = businremark;
		this.status = status;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
	}

	// Property accessors
	@Id
	@Column(name = "BUSINTYPEID", nullable = false, length = 64)
	public String getBusintypeid() {
		return this.busintypeid;
	}

	public void setBusintypeid(String busintypeid) {
		this.busintypeid = busintypeid;
	}

	@Column(name = "BUSINTYPENAME", length = 128)
	public String getBusintypename() {
		return this.busintypename;
	}

	public void setBusintypename(String busintypename) {
		this.busintypename = busintypename;
	}

	@Column(name = "BUSINREMARK")
	public String getBusinremark() {
		return this.businremark;
	}

	public void setBusinremark(String businremark) {
		this.businremark = businremark;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "TBasisType [busintypeid=" + busintypeid + ", busintypename="
				+ busintypename + ", businremark=" + businremark + ", status="
				+ status + ", updateuser=" + updateuser + ", updatetime="
				+ updatetime + "]";
	}

}