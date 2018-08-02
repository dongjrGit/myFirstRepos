package com.mobile.application.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * author by chenji
 * time      2017/02/24
 */

@Entity
@Table(name = "T_BASIS_BUS_SPECIALCOST_ADD")
public class TBasisiBusSpecialCostAdd implements Serializable {

	/** 版本号 **/
	private static final long serialVersionUID = 1L;

	/** 表的索引 **/
	private String id;
	/** 特殊客户码代码 **/
	private String busInId;
	/** 商户中文名称 **/
	private String busInName;
	/** 组织机构号 **/
	private String busInId_Bracnch;
	/** 诚意贷或购易贷类型 **/
	private String busInTypeId;
	/** 时间 **/
	private String updateTime;

	/** default constructor */
	public TBasisiBusSpecialCostAdd() {
		// TODO Auto-generated constructor stub
	}

	public TBasisiBusSpecialCostAdd(String id, String busInId,
			String busInName, String busInId_Bracnch, String busInTypeId,
			String updateTime) {
		super();
     	this.id = id;
		this.busInId = busInId;
		this.busInName = busInName;
		this.busInId_Bracnch = busInId_Bracnch;
		this.busInTypeId = busInTypeId;
		this.updateTime = updateTime;
	}
	
	@Id
	@Column(name = "id", nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "businid", nullable = false, length = 64)
	public String getBusInId() {
		return busInId;
	}

	public void setBusInId(String busInId) {
		this.busInId = busInId;
	}

	@Column(name = "businname", length = 128)
	public String getBusInName() {
		return busInName;
	}

	public void setBusInName(String busInName) {
		this.busInName = busInName;
	}

	@Column(name = "businid_bracnch", nullable = false, length = 64)
	public String getBusInId_Bracnch() {
		return busInId_Bracnch;
	}

	public void setBusInId_Bracnch(String busInId_Bracnch) {
		this.busInId_Bracnch = busInId_Bracnch;
	}

	@Column(name = "busintypeid", nullable = false, length = 64)
	public String getBusInTypeId() {
		return busInTypeId;
	}

	public void setBusInTypeId(String busInTypeId) {
		this.busInTypeId = busInTypeId;
	}

	@Column(name = "updatetime", length = 23)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
