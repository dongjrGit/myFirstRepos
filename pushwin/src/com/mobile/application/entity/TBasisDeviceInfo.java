package com.mobile.application.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TbEmmUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_DEVICE")
public class TBasisDeviceInfo implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private String reId;
	private String pinId;
	private String simId;
	private String userId;
	private String orgId;
	private String recipientTime;
	private String padStatus;
	private String delFlag;
	private String updateTime;
	
	public TBasisDeviceInfo(){
       super();		
	} 
	
	public TBasisDeviceInfo(String reId, String pinId, String simId,
			String userId, String orgId, String recipientTime,
			String padStatus, String delFlag,String updateTime){
	        super();		
	        this.reId = reId;
			this.pinId = pinId;
			this.simId = simId;
			this.userId = userId;
			this.orgId = orgId;
			this.recipientTime = recipientTime;
			this.padStatus = padStatus;
			this.delFlag = delFlag;
			this.updateTime = updateTime;
		} 
	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "RE_ID", unique = true, nullable = false, length = 32)
	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}
	@Column(name = "PIN_ID")
	public String getPinId() {
		return pinId;
	}
	public void setPinId(String pinId) {
		this.pinId = pinId;
	}
	@Column(name = "SIM_ID")
	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}
	
	@Column(name = "RECIPIENT_TIME", length = 20)
	public String getRecipientTime() {
		return recipientTime;
	}

	public void setRecipientTime(String recipientTime) {
		this.recipientTime = recipientTime;
	}

	@Column(name = "PAD_STATUS")
	public String getPadStatus() {
		return padStatus;
	}

	public void setPadStatus(String padStatus) {
		this.padStatus = padStatus;
	}
	@Column(name = "DEL_FLAG")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Column(name = "UPDATE_TIME", length = 20)	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "ORG_ID", length = 32)
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
	