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

import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisUser;

@Entity
@Table(name = "T_BASIS_DEVICE_TRANSFER_LOG")
public class TBasisDeviceTransferLog implements java.io.Serializable {
	// Fields
	private static final long serialVersionUID = 1L;
	private String logId;
	private String pinId;
	private String simId;
	private TBasisUser operatorUser;//操作人ID
	private Date operatorTime;//操作时间
	private String operatorType;//操作类型
	private TBasisUser receiveUser;//接收人ID
	private String updateUserId;//更新人ID
	private Date updateTime;//更新人时间
	

	
	public TBasisDeviceTransferLog(String logId, String pinId, String simId,
			TBasisUser operatorUser, Date operatorTime,
			String operatorType, TBasisUser receiveUser,String updateUserId,Date updateTime){
	        super();		
	        this.logId = logId;
			this.pinId = pinId;
			this.simId = simId;
			this.operatorUser = operatorUser;
			this.operatorTime = operatorTime;
			this.operatorType = operatorType;
			this.receiveUser = receiveUser;
			this.updateUserId = updateUserId;
			this.updateTime = updateTime;
		} 
	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "LOG_ID", unique = true, nullable = false, length = 32)
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OPERATOR_USER")
	public TBasisUser getOperatorUser() {
		return operatorUser;
	}

	public void setOperatorUser(TBasisUser operatorUser) {
		this.operatorUser = operatorUser;
	}
	


	@Column(name = "OPERATOR_TIME")
	public Date getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Date operatorTime) {
		this.operatorTime = operatorTime;
	}

	@Column(name = "OPERATOR_TYPE")
	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIVE_USER_ID")
	public TBasisUser getReceiveUser() {
		return receiveUser;
	}

	public void setReceiveUser(TBasisUser receiveUser) {
		this.receiveUser = receiveUser;
	}



	@Column(name = "UPDATE_USER_ID")
	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	


	
	public TBasisDeviceTransferLog(){
       super();		
	} 

}
