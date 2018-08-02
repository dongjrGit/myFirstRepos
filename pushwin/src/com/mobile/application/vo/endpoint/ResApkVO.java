package com.mobile.application.vo.endpoint;

public class ResApkVO {
	private String dealId;
	private String dealChannel; //交易渠道
	private String dealCode; //交易代码
	private String dealName; //交易名称
	private String dealDescription; //交易描述
	private String relatedApk; //关联APK
	private String pageApk; //
	private String dealType; //交易类型
	private String isOffline;//是否离线
	private String imgName;// 交易图标
	
	public String getDealChannel() {
		return dealChannel;
	}
	public void setDealChannel(String dealChannel) {
		this.dealChannel = dealChannel;
	}
	public String getDealCode() {
		return dealCode;
	}
	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public String getDealDescription() {
		return dealDescription;
	}
	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}
	public String getRelatedApk() {
		return relatedApk;
	}
	public void setRelatedApk(String relatedApk) {
		this.relatedApk = relatedApk;
	}
	public String getPageApk() {
		return pageApk;
	}
	public void setPageApk(String pageApk) {
		this.pageApk = pageApk;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getIsOffline() {
		return isOffline;
	}
	public void setIsOffline(String isOffline) {
		this.isOffline = isOffline;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dealId == null) ? 0 : dealId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResApkVO other = (ResApkVO) obj;
		if (dealId == null) {
			if (other.dealId != null)
				return false;
		} else if (!dealId.equals(other.dealId))
			return false;
		return true;
	}
}
