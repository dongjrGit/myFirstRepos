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
 * TBasisCreditCheckrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_CREDIT_CHECKRECORD")
public class TBasisCreditCheckrecord implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisCredit TBasisCredit;
	private String checkResult;
	private String checkOpinion;
	private String updateUser;
	private String updateTime;

	// Constructors

	/** default constructor */
	public TBasisCreditCheckrecord() {
	}

	/** full constructor */
	public TBasisCreditCheckrecord(TBasisCredit TBasisCredit,
			String checkResult, String checkOpinion, String updateUser,
			String updateTime) {
		this.TBasisCredit = TBasisCredit;
		this.checkResult = checkResult;
		this.checkOpinion = checkOpinion;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
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
	@JoinColumn(name = "APP_ID")
	public TBasisCredit getTBasisCredit() {
		return this.TBasisCredit;
	}

	public void setTBasisCredit(TBasisCredit TBasisCredit) {
		this.TBasisCredit = TBasisCredit;
	}

	@Column(name = "CHECK_RESULT", length = 8)
	public String getCheckResult() {
		return this.checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

	@Column(name = "CHECK_OPINION", length = 600)
	public String getCheckOpinion() {
		return this.checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}

	@Column(name = "UPDATE_USER", length = 32)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "UPDATE_TIME", length = 20)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}