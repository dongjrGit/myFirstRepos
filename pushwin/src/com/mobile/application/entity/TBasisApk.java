package com.mobile.application.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TDealManager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_APK")
public class TBasisApk implements java.io.Serializable {

	// Fields

	private String dealId;
	private String dealChannel;
	private String dealCode;
	private String dealName;
	private String dealDescription;
	private String relatedApk;
	private String pageApk;
	private String dealType;
	private String isOffline;
	private String delFlag;
	
	private String imgPath;// 交易图标
	private String imgLength;
	private String imgUpdateTime;// 交易图标名字
	
	private String updateUserId;
	private String updateTime;
	private Set<TBasisRoleApk> TBasisRoleApks = new HashSet<TBasisRoleApk>(0);

	// Constructors

	/** default constructor */
	public TBasisApk() {
	}
	
	
	
	public TBasisApk(String dealId, String dealChannel, String dealCode,
			String dealName, String dealDescription, String relatedApk,
			String pageApk, String dealType, String isOffline, String delFlag,
			String imgPath, String imgLength, String imgUpdateTime,
			String updateUserId, String updateTime) {
		super();
		this.dealId = dealId;
		this.dealChannel = dealChannel;
		this.dealCode = dealCode;
		this.dealName = dealName;
		this.dealDescription = dealDescription;
		this.relatedApk = relatedApk;
		this.pageApk = pageApk;
		this.dealType = dealType;
		this.isOffline = isOffline;
		this.delFlag = delFlag;
		this.imgPath = imgPath;
		this.imgLength = imgLength;
		this.imgUpdateTime = imgUpdateTime;
		this.updateUserId = updateUserId;
		this.updateTime = updateTime;
	}



	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getDealId() {
		return this.dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	
	
	

	@Column(name = "DEAL_CHANNEL", length = 50)
	public String getDealChannel() {
		return this.dealChannel;
	}

	public void setDealChannel(String dealChannel) {
		this.dealChannel = dealChannel;
	}

	@Column(name = "DEAL_CODE", length = 10)
	public String getDealCode() {
		return this.dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	@Column(name = "DEAL_NAME", length = 50)
	public String getDealName() {
		return this.dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	@Column(name = "DEAL_DESCRIPTION", length = 200)
	public String getDealDescription() {
		return this.dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	@Column(name = "RELATED_APK", length = 50)
	public String getRelatedApk() {
		return this.relatedApk;
	}

	public void setRelatedApk(String relatedApk) {
		this.relatedApk = relatedApk;
	}

	@Column(name = "PAGE_APK", length = 50)
	public String getPageApk() {
		return this.pageApk;
	}

	public void setPageApk(String pageApk) {
		this.pageApk = pageApk;
	}

	@Column(name = "DEAL_TYPE", length = 32)
	public String getDealType() {
		return this.dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	@Column(name = "IS_OFFLINE", length = 2)
	public String getIsOffline() {
		return this.isOffline;
	}

	public void setIsOffline(String isOffline) {
		this.isOffline = isOffline;
	}

	@Column(name = "DEL_FLAG", length = 10)
	public String getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Column(name = "UPDATE_USER", length = 50)
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Column(name = "UPDATE_TIME", length = 20)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "IMG_PATH")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	@Column(name = "IMG_UPDATETIME")
	public String getImgUpdateTime() {
		return imgUpdateTime;
	}

	public void setImgUpdateTime(String imgUpdateTime) {
		this.imgUpdateTime = imgUpdateTime;
	}
	
	@Column(name = "IMG_LENGTH")
	public String getImgLength() {
		return imgLength;
	}

	public void setImgLength(String imgLength) {
		this.imgLength = imgLength;
	}

	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE, mappedBy = "TBasisRole")
	public Set<TBasisRoleApk> getTBasisRoleApks() {
		return TBasisRoleApks;
	}

	public void setTBasisRoleApks(Set<TBasisRoleApk> tBasisRoleApks) {
		TBasisRoleApks = tBasisRoleApks;
	}
	
}