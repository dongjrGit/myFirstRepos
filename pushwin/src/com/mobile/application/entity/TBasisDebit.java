package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * TBasisDebit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_BASIS_DEBIT")
public class TBasisDebit  implements java.io.Serializable {


    // Fields    

     private String id;
     private String name;
     private String idNo;
     private String phone;
     private String userId;
     private String updateTime;
     private String status;
     private String customerId;
     private String accountType;


    // Constructors

    /** default constructor */
    public TBasisDebit() {
    }

    
    /** full constructor */
    public TBasisDebit(String name, String idNo, String phone, String userId, String updateTime, String status, String customerId) {
        this.name = name;
        this.idNo = idNo;
        this.phone = phone;
        this.userId = userId;
        this.updateTime = updateTime;
        this.status = status;
        this.customerId = customerId;
    }

   
 // Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
    @Column(name="ID", unique=true, nullable=false, length=32)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Column(name="NAME", length=32)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="ID_NO", length=20)

    public String getIdNo() {
        return this.idNo;
    }
    
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    
    @Column(name="PHONE", length=20)

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="USER_ID", length=32)

    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    @Column(name="UPDATE_TIME", length=20)

    public String getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
    @Column(name="STATUS", length=20)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="CUSTOMER_ID", length=20)

    public String getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    
    @Column(name="ACCOUNT_TYPE", length=20)
	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
   








}