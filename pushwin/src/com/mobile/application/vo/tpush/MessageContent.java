package com.mobile.application.vo.tpush;


public class MessageContent {
	
	private String messageType; // 1-公告 2-通知
	private String sendUser;
	private String sendName;
	private String receiveUser;
	private String receiveName;
	private String content;
	private String title;
	private String sendTime;
	private String isRead;
	public MessageContent() {
		super();
	}
	public MessageContent(String messageType, String sendUser, String sendName, String title, String content, String sendTime) {
		this.messageType = messageType;
		this.sendUser = sendUser;
		this.sendName = sendName;
		this.title = title;
		this.content = content;
		this.sendTime = sendTime;
	}
	public MessageContent(String messageType, String sendUser, String sendName,
			String receiveUser, String receiveName, String content, String sendTime) {
		super();
		this.messageType = messageType;
		this.sendUser = sendUser;
		this.sendName = sendName;
		this.receiveUser = receiveUser;
		this.receiveName = receiveName;
		this.content = content;
		this.sendTime = sendTime;
	}
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	public String getReceiveUser() {
		return receiveUser;
	}
	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
