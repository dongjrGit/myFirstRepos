package com.mobile.application.vo.endpoint;

import java.util.List;
import java.util.Set;

public class WrapResApkVO {
	
	private String zipPath; //zip包下载路径
	private String zipName; //zip包名称
	private String zipLength; //zip包文件大小
	private String lastTime; //服务器时间 yyyy-mm-dd hh:MM:ss
	private Set<ResApkVO> resApkVOs; //交易列表
	
	public String getZipPath() {
		return zipPath;
	}
	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public Set<ResApkVO> getResApkVOs() {
		return resApkVOs;
	}
	public void setResApkVOs(Set<ResApkVO> resApkVOs) {
		this.resApkVOs = resApkVOs;
	}
	public String getZipName() {
		return zipName;
	}
	public void setZipName(String zipName) {
		this.zipName = zipName;
	}
	public String getZipLength() {
		return zipLength;
	}
	public void setZipLength(String zipLength) {
		this.zipLength = zipLength;
	}
}
