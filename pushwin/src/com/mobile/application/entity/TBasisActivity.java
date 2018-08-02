package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_ACTIVITY")
public class TBasisActivity implements java.io.Serializable {

	// Fields

	private String activityId;
	private String issuer;
	private String activityName;
	private String activityCode;
	private String activityStartTime;
	private String activityEndTime;
	private String activityPhotoName;
	private String activityPhotoPath;
	private String activityPhotoLen;
	private String status;
	private String createtime;
	private String updatetime;
	
	private String updateuser;
	private String zipPath;
	private String zipLength;
	
	@Column(name = "UPDATEUSER", length = 32)
	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	// Constructors
	@Column(name = "UPDATETIME", length = 32)
	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	/** default constructor */
	public TBasisActivity() {
	}

	/** full constructor */
	public TBasisActivity(String issuer, String activityName,
			String activityCode, String activityStartTime,
			String activityEndTime, String activityPhotoName,
			String activityPhotoPath, String activityPhotoLen, String status,String createtime) {
		this.issuer = issuer;
		this.activityName = activityName;
		this.activityCode = activityCode;
		this.activityStartTime = activityStartTime;
		this.activityEndTime = activityEndTime;
		this.activityPhotoName = activityPhotoName;
		this.activityPhotoPath = activityPhotoPath;
		this.activityPhotoLen = activityPhotoLen;
		this.status = status;
		this.createtime = createtime;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ACTIVITY_ID", unique = true, nullable = false, length = 32)
	public String getActivityId() {
		return this.activityId;
	}
	@Column(name = "CREATE_TIME", length = 32)
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	@Column(name = "ISSUER", length = 128)
	public String getIssuer() {
		return this.issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	@Column(name = "ACTIVITY_NAME", length = 32)
	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Column(name = "ACTIVITY_CODE", length = 32)
	public String getActivityCode() {
		return this.activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	@Column(name = "ACTIVITY_START_TIME", length = 256)
	public String getActivityStartTime() {
		return this.activityStartTime;
	}

	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}

	@Column(name = "ACTIVITY_END_TIME", length = 256)
	public String getActivityEndTime() {
		return this.activityEndTime;
	}

	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
	}

	@Column(name = "ACTIVITY_PHOTO_NAME", length = 128)
	public String getActivityPhotoName() {
		return this.activityPhotoName;
	}

	public void setActivityPhotoName(String activityPhotoName) {
		this.activityPhotoName = activityPhotoName;
	}

	@Column(name = "ACTIVITY_PHOTO_PATH", length = 512)
	public String getActivityPhotoPath() {
		return this.activityPhotoPath;
	}

	public void setActivityPhotoPath(String activityPhotoPath) {
		this.activityPhotoPath = activityPhotoPath;
	}

	@Column(name = "ACTIVITY_PHOTO_LEN", length = 128)
	public String getActivityPhotoLen() {
		return this.activityPhotoLen;
	}

	public void setActivityPhotoLen(String activityPhotoLen) {
		this.activityPhotoLen = activityPhotoLen;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "ZIP_PATH", length = 120)
	public String getZipPath() {
		return zipPath;
	}

	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}
	
	@Column(name = "ZIP_LENGTH", length = 8)
	public String getZipLength() {
		return zipLength;
	}

	public void setZipLength(String zipLength) {
		this.zipLength = zipLength;
	}

}