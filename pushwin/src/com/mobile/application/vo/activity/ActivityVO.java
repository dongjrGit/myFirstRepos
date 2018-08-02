package com.mobile.application.vo.activity;

public class ActivityVO {
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
	private String filename;
	private String content;
	private String type;
	
	
	public ActivityVO() {
		super();
	}
	public ActivityVO(String activityId, String issuer, String activityName,
			String activityCode, String activityStartTime,
			String activityEndTime, String activityPhotoName,
			String activityPhotoPath, String activityPhotoLen, String status,
			String createtime, String updatetime, String updateuser,
			String zipPath, String zipLength, String filename, String content,
			String type) {
		super();
		this.activityId = activityId;
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
		this.updatetime = updatetime;
		this.updateuser = updateuser;
		this.zipPath = zipPath;
		this.zipLength = zipLength;
		this.filename = filename;
		this.content = content;
		this.type = type;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getActivityStartTime() {
		return activityStartTime;
	}
	public void setActivityStartTime(String activityStartTime) {
		this.activityStartTime = activityStartTime;
	}
	public String getActivityEndTime() {
		return activityEndTime;
	}
	public void setActivityEndTime(String activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	public String getActivityPhotoName() {
		return activityPhotoName;
	}
	public void setActivityPhotoName(String activityPhotoName) {
		this.activityPhotoName = activityPhotoName;
	}
	public String getActivityPhotoPath() {
		return activityPhotoPath;
	}
	public void setActivityPhotoPath(String activityPhotoPath) {
		this.activityPhotoPath = activityPhotoPath;
	}
	public String getActivityPhotoLen() {
		return activityPhotoLen;
	}
	public void setActivityPhotoLen(String activityPhotoLen) {
		this.activityPhotoLen = activityPhotoLen;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public String getZipPath() {
		return zipPath;
	}
	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}
	public String getZipLength() {
		return zipLength;
	}
	public void setZipLength(String zipLength) {
		this.zipLength = zipLength;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
