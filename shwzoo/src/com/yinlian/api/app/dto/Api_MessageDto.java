package com.yinlian.api.app.dto;

import java.util.Date;

/**
 * app站内消息返回数据
 * 
 * @author Administrator
 *
 */
public class Api_MessageDto {

	private Integer id;        //消息ID
	private String title;      //消息标题
	private String content;    //消息内容
	private Integer type;      //消息类型
	private Date sendtime;     //发送时间
	private Integer status;    //状态         0-已读 1-未读
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
