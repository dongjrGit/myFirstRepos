package com.mobile.application.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasisBusStores entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "STORE_DISCOUNT")
public class StoreDiscount implements java.io.Serializable {

	// Fields

	private String org_code;
	private String store_number;
	private String plan_number;
	private Integer term;
	private float discount;
	private Integer discount_term;
	private String create_user;
	private Date create_date;
	private String update_user;
	private Date update_date;
	private String status;

	// Constructors

	/** default constructor */
	public StoreDiscount() {
	}


	/** full constructor */
	public StoreDiscount(String org_code, String store_number,
			String plan_number, Integer term, float discount, Integer discount_term,
			String create_user, Date create_date, String update_user,
			Date update_date, String status) {
		super();
		this.org_code = org_code;
		this.store_number = store_number;
		this.plan_number = plan_number;
		this.term = term;
		this.discount = discount;
		this.discount_term = discount_term;
		this.create_user = create_user;
		this.create_date = create_date;
		this.update_user = update_user;
		this.update_date = update_date;
		this.status = status;
	}
	

	@Id
	@Column(name = "ORG_CODE",length = 3)
	public String getOrg_code() {
		return org_code;
	}


	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	

	@Id
	@Column(name = "STORE_NUMBER", length = 9)
	public String getStore_number() {
		return store_number;
	}


	public void setStore_number(String store_number) {
		this.store_number = store_number;
	}


	@Id
	@Column(name = "PLAN_NUMBER", length = 5)
	public String getPlan_number() {
		return plan_number;
	}


	public void setPlan_number(String plan_number) {
		this.plan_number = plan_number;
	}


	@Id
	@Column(name = "TERM")
	public Integer getTerm() {
		return term;
	}


	public void setTerm(Integer term) {
		this.term = term;
	}



	@Id
	@Column(name = "DISCOUNT")
	public float getDiscount() {
		return discount;
	}


	public void setDiscount(float discount) {
		this.discount = discount;
	}


	@Id
	@Column(name = "DISCOUNT_TERM")
	public Integer getDiscount_term() {
		return discount_term;
	}


	public void setDiscount_term(Integer discount_term) {
		this.discount_term = discount_term;
	}


	@Id
	@Column(name = "CREATE_USER", length = 20)
	public String getCreate_user() {
		return create_user;
	}


	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}


	@Id
	@Column(name = "CREATE_DATE")
	public Date getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	@Id
	@Column(name = "UPDATE_USER", length = 20)
	public String getUpdate_user() {
		return update_user;
	}


	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}


	@Id
	@Column(name = "UPDATE_DATE")
	public Date getUpdate_date() {
		return update_date;
	}


	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}


	@Id
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}