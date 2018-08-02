package com.mobile.application.entity;

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
 * TBasisPosition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_POSITION")
public class TBasisPosition implements java.io.Serializable {

	// Fields

	private String id;
	private String latitude;
	private String longitude;
	private String devicePin;
	private String positionType;
	private String updateTime;
	private TBasisUser TBasisUser;

	// Constructors

	/** default constructor */
	public TBasisPosition() {
	}

	/** full constructor */
	public TBasisPosition(String latitude, String longitude, String devicePin,
			String positionType, String updateTime, TBasisUser TBasisUser) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.devicePin = devicePin;
		this.positionType = positionType;
		this.updateTime = updateTime;
		this.TBasisUser = TBasisUser;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "LATITUDE", length = 20)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "LONGITUDE", length = 20)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "DEVICE_PIN", length = 50)
	public String getDevicePin() {
		return this.devicePin;
	}

	public void setDevicePin(String devicePin) {
		this.devicePin = devicePin;
	}

	@Column(name = "POSITION_TYPE", length = 2)
	public String getPositionType() {
		return this.positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	@Column(name = "UPDATE_TIME", length = 20)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATE_USER")
	public TBasisUser getTBasisUser() {
		return this.TBasisUser;
	}

	public void setTBasisUser(TBasisUser TBasisUser) {
		this.TBasisUser = TBasisUser;
	}

}