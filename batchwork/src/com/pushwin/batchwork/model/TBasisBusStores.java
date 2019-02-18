package com.pushwin.batchwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasisBusStores entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_BUS_STORES")
public class TBasisBusStores implements java.io.Serializable {

	// Fields

	private String businid;
	private String businidbranch;
	private String oraarea;
	private String businame;
	private String storesdis;
	private String storetype;
	private String storepro;
	private String orapo;
	private String beizhu1;
	private String beizhu2;
	private String beizhu3;
	private String beizhu4;
	private String beizhu5;
	private String beizhu6;
	private String status;
	private String dictremark;
	private String updateuser;
	private String updatetime;
	private String strooption;

	// Constructors

	/** default constructor */
	public TBasisBusStores() {
	}

	/** minimal constructor */
	public TBasisBusStores(String businid, String businidbranch,
			String oraarea, String businame, String storesdis,
			String storetype, String storepro, String orapo) {
		this.businid = businid;
		this.businidbranch = businidbranch;
		this.oraarea = oraarea;
		this.businame = businame;
		this.storesdis = storesdis;
		this.storetype = storetype;
		this.storepro = storepro;
		this.orapo = orapo;
	}

	/** full constructor */
	public TBasisBusStores(String businid, String businidbranch,
			String oraarea, String businame, String storesdis,
			String storetype, String storepro, String orapo, String beizhu1,
			String beizhu2, String beizhu3, String beizhu4, String beizhu5,
			String beizhu6, String status, String dictremark,
			String updateuser, String updatetime, String strooption) {
		this.businid = businid;
		this.businidbranch = businidbranch;
		this.oraarea = oraarea;
		this.businame = businame;
		this.storesdis = storesdis;
		this.storetype = storetype;
		this.storepro = storepro;
		this.orapo = orapo;
		this.beizhu1 = beizhu1;
		this.beizhu2 = beizhu2;
		this.beizhu3 = beizhu3;
		this.beizhu4 = beizhu4;
		this.beizhu5 = beizhu5;
		this.beizhu6 = beizhu6;
		this.status = status;
		this.dictremark = dictremark;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
		this.strooption = strooption;
	}

	// Property accessors
	@Id
	@Column(name = "BUSINID", unique = true, nullable = false, length = 64)
	public String getBusinid() {
		return this.businid;
	}

	public void setBusinid(String businid) {
		this.businid = businid;
	}

	@Column(name = "BUSINIDBRANCH", nullable = false, length = 64)
	public String getBusinidbranch() {
		return this.businidbranch;
	}

	public void setBusinidbranch(String businidbranch) {
		this.businidbranch = businidbranch;
	}

	@Column(name = "ORAAREA", nullable = false, length = 64)
	public String getOraarea() {
		return this.oraarea;
	}

	public void setOraarea(String oraarea) {
		this.oraarea = oraarea;
	}

	@Column(name = "BUSINAME", nullable = false, length = 64)
	public String getBusiname() {
		return this.businame;
	}

	public void setBusiname(String businame) {
		this.businame = businame;
	}

	@Column(name = "STORESDIS", nullable = false, length = 64)
	public String getStoresdis() {
		return this.storesdis;
	}

	public void setStoresdis(String storesdis) {
		this.storesdis = storesdis;
	}

	@Column(name = "STORETYPE", nullable = false, length = 64)
	public String getStoretype() {
		return this.storetype;
	}

	public void setStoretype(String storetype) {
		this.storetype = storetype;
	}

	@Column(name = "STOREPRO", nullable = false, length = 64)
	public String getStorepro() {
		return this.storepro;
	}

	public void setStorepro(String storepro) {
		this.storepro = storepro;
	}

	@Column(name = "ORAPO", nullable = false, length = 64)
	public String getOrapo() {
		return this.orapo;
	}

	public void setOrapo(String orapo) {
		this.orapo = orapo;
	}

	@Column(name = "BEIZHU1", length = 256)
	public String getBeizhu1() {
		return this.beizhu1;
	}

	public void setBeizhu1(String beizhu1) {
		this.beizhu1 = beizhu1;
	}

	@Column(name = "BEIZHU2", length = 64)
	public String getBeizhu2() {
		return this.beizhu2;
	}

	public void setBeizhu2(String beizhu2) {
		this.beizhu2 = beizhu2;
	}

	@Column(name = "BEIZHU3", length = 64)
	public String getBeizhu3() {
		return this.beizhu3;
	}

	public void setBeizhu3(String beizhu3) {
		this.beizhu3 = beizhu3;
	}

	@Column(name = "BEIZHU4", length = 64)
	public String getBeizhu4() {
		return this.beizhu4;
	}

	public void setBeizhu4(String beizhu4) {
		this.beizhu4 = beizhu4;
	}

	@Column(name = "BEIZHU5", length = 64)
	public String getBeizhu5() {
		return this.beizhu5;
	}

	public void setBeizhu5(String beizhu5) {
		this.beizhu5 = beizhu5;
	}

	@Column(name = "BEIZHU6", length = 64)
	public String getBeizhu6() {
		return this.beizhu6;
	}

	public void setBeizhu6(String beizhu6) {
		this.beizhu6 = beizhu6;
	}

	@Column(name = "STATUS", length = 1)
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

	@Column(name = "STROOPTION", length = 32)
	public String getStrooption() {
		return this.strooption;
	}

	public void setStrooption(String strooption) {
		this.strooption = strooption;
	}

}