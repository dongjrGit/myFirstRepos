package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisNoticePushlist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_NOTICE_PUSHLIST")
public class TBasisNoticePushlist implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisNotice TBasisNotice;
	private String userId;
	private String isRead;

	// Constructors

	/** default constructor */
	public TBasisNoticePushlist() {
	}

	/** full constructor */
	public TBasisNoticePushlist(TBasisNotice TBasisNotice, String userId,
			String isRead) {
		this.TBasisNotice = TBasisNotice;
		this.userId = userId;
		this.isRead = isRead;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NOTICE_ID")
	public TBasisNotice getTBasisNotice() {
		return this.TBasisNotice;
	}

	public void setTBasisNotice(TBasisNotice TBasisNotice) {
		this.TBasisNotice = TBasisNotice;
	}

	@Column(name = "USER_ID", length = 32)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "IS_READ", length = 2)
	public String getIsRead() {
		return this.isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

}