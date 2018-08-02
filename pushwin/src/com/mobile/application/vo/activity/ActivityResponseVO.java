package com.mobile.application.vo.activity;

import java.util.List;

public class ActivityResponseVO {
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
	private List<ActivityPicVO> activityPicVOs;
	
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
	public List<ActivityPicVO> getActivityPicVOs() {
		return activityPicVOs;
	}
	public void setActivityPicVOs(List<ActivityPicVO> activityPicVOs) {
		this.activityPicVOs = activityPicVOs;
	}
}
