package com.mobile.application.vo.login;

public class UserMenuVO {
	private String id;
	private String text;
	private String pid;
	private String iconCls;
	private String url;
	private String sort;
	private String iconPosition;
	
	public UserMenuVO(){}
	
	public UserMenuVO(String id, String text, String pid, String iconCls,
			String url, String sort, String iconPosition) {
		super();
		this.id = id;
		this.text = text;
		this.pid = pid;
		this.iconCls = iconCls;
		this.url = url;
		this.sort = sort;
		this.iconPosition = iconPosition;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIconPosition() {
		return iconPosition;
	}
	public void setIconPosition(String iconPosition) {
		this.iconPosition = iconPosition;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
