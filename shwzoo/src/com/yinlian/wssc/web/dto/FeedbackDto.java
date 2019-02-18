package com.yinlian.wssc.web.dto;

import java.util.Date;

import data.ParseUtil;

public class FeedbackDto {

	private Integer id;
	
	private String title;
	
	private String  content;
	
    private Date    creattime;

    private String  username;

    private Integer status;
    
    private String createtimetr;
    
    private Integer type;
    
    private String phone;
    
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreattime() {
		return creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatetimetr() {
		this.createtimetr=ParseUtil.parseDateToString(creattime, "yyyy-MM-dd HH:mm:ss");
		return createtimetr;
	}

	public void setCreatetimetr(String createtimetr) {
		this.createtimetr = createtimetr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
}
