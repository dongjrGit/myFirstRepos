package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasisDictIgroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_DICT_IGROUP")
public class TBasisDictIgroup implements java.io.Serializable {

	// Fields

	private String groupNo;
	private String groupName;
	private String groupTuan;
	private String groupYou;
	private String groupDai;
	private String groupDistrict;
	private String groupPro;
	private String orgCode;
	private String createuser;
	private String createTime;
	private String updateTime;
	private String beizhu;
	private String beizhu1;
	private String beizhu2;

	// Constructors

	/** default constructor */
	public TBasisDictIgroup() {
	}

	/** minimal constructor */
	public TBasisDictIgroup(String groupNo) {
		this.groupNo = groupNo;
	}

	/** full constructor */
	public TBasisDictIgroup(String groupNo, String groupName, String groupTuan,
			String groupYou, String groupDai, String groupDistrict,
			String groupPro, String orgCode, String createuser,
			String createTime, String updateTime, String beizhu,
			String beizhu1, String beizhu2) {
		this.groupNo = groupNo;
		this.groupName = groupName;
		this.groupTuan = groupTuan;
		this.groupYou = groupYou;
		this.groupDai = groupDai;
		this.groupDistrict = groupDistrict;
		this.groupPro = groupPro;
		this.orgCode = orgCode;
		this.createuser = createuser;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.beizhu = beizhu;
		this.beizhu1 = beizhu1;
		this.beizhu2 = beizhu2;
	}

	// Property accessors
	@Id
	@Column(name = "GROUP_NO", unique = true, nullable = false, length = 20)
	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Column(name = "GROUP_NAME", length = 120)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "GROUP_TUAN", length = 120)
	public String getGroupTuan() {
		return this.groupTuan;
	}

	public void setGroupTuan(String groupTuan) {
		this.groupTuan = groupTuan;
	}

	@Column(name = "GROUP_YOU", length = 120)
	public String getGroupYou() {
		return this.groupYou;
	}

	public void setGroupYou(String groupYou) {
		this.groupYou = groupYou;
	}

	@Column(name = "GROUP_DAI", length = 120)
	public String getGroupDai() {
		return this.groupDai;
	}

	public void setGroupDai(String groupDai) {
		this.groupDai = groupDai;
	}

	@Column(name = "GROUP_DISTRICT", length = 60)
	public String getGroupDistrict() {
		return this.groupDistrict;
	}

	public void setGroupDistrict(String groupDistrict) {
		this.groupDistrict = groupDistrict;
	}

	@Column(name = "GROUP_PRO", length = 60)
	public String getGroupPro() {
		return this.groupPro;
	}

	public void setGroupPro(String groupPro) {
		this.groupPro = groupPro;
	}

	@Column(name = "ORG_CODE", length = 4)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "CREATEUSER", length = 120)
	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	@Column(name = "CREATE_TIME", length = 120)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UPDATE_TIME", length = 120)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "BEIZHU", length = 120)
	public String getBeizhu() {
		return this.beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	@Column(name = "BEIZHU1", length = 120)
	public String getBeizhu1() {
		return this.beizhu1;
	}

	public void setBeizhu1(String beizhu1) {
		this.beizhu1 = beizhu1;
	}

	@Column(name = "BEIZHU2", length = 120)
	public String getBeizhu2() {
		return this.beizhu2;
	}

	public void setBeizhu2(String beizhu2) {
		this.beizhu2 = beizhu2;
	}

}