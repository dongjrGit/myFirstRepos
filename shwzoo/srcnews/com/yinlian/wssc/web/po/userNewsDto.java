package com.yinlian.wssc.web.po;

public class userNewsDto {
	private String CreateTime;
	private String title;
	private String subtitle;
	private String imgurl;
	private String newsid;
	
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
}
