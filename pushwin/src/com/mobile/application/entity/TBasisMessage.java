package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_MESSAGE")
public class TBasisMessage implements java.io.Serializable {

	// Fields

	private String messageId;
	private String messageContent;
	private String receiveUserId;
	private String receiveUserName;
	private String sendUserId;
	private String sendUserName;
	private String messageTime;
	private String isRead;

	// Constructors

	/** default constructor */
	public TBasisMessage() {
	}

	/** full constructor */
	public TBasisMessage(String messageContent, String receiveUserId,
			String receiveUserName, String replyUserId, String replyUserName,
			String messageTime, String isRead) {
		this.messageContent = messageContent;
		this.receiveUserId = receiveUserId;
		this.receiveUserName = receiveUserName;
		this.sendUserId = replyUserId;
		this.sendUserName = replyUserName;
		this.messageTime = messageTime;
		this.isRead = isRead;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "MESSAGE_ID", unique = true, nullable = false, length = 32)
	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Column(name = "MESSAGE_CONTENT", length = 240)
	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Column(name = "RECEIVE_USER_ID", length = 32)
	public String getReceiveUserId() {
		return this.receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	@Column(name = "RECEIVE_USER_NAME", length = 60)
	public String getReceiveUserName() {
		return this.receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	@Column(name = "SEND_USER_ID", length = 32)
	public String getSendUserId() {
		return this.sendUserId;
	}

	public void setSendUserId(String replyUserId) {
		this.sendUserId = replyUserId;
	}

	@Column(name = "SEND_USER_NAME", length = 60)
	public String getSendUserName() {
		return this.sendUserName;
	}

	public void setSendUserName(String replyUserName) {
		this.sendUserName = replyUserName;
	}

	@Column(name = "MESSAGE_TIME", length = 20)
	public String getMessageTime() {
		return this.messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	@Column(name = "IS_READ", length = 1)
	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
}