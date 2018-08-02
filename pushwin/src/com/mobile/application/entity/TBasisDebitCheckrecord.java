package com.mobile.application.entity;
// default package

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
 * TBasisDebitCeckrecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_BASIS_DEBIT_CHECKRECORD")

public class TBasisDebitCheckrecord  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
     private TBasisDebit TBasisDebit;
     private String checkResult;
     private String checkOpinion;
     private String updateUser;
     private String updateTime;


    // Constructors

    /** default constructor */
    public TBasisDebitCheckrecord() {
    }

    
    /** full constructor */
    public TBasisDebitCheckrecord(TBasisDebit TBasisDebit, String checkResult, String checkOpinion, String updateUser, String updateTime) {
        this.TBasisDebit = TBasisDebit;
        this.checkResult = checkResult;
        this.checkOpinion = checkOpinion;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="uuid.hex")
    @Id 
    @GeneratedValue(generator="generator")
    @Column(name="ID", unique=true, nullable=false, length=32)

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="APP_ID")

    public TBasisDebit getTBasisDebit() {
        return this.TBasisDebit;
    }
    
    public void setTBasisDebit(TBasisDebit TBasisDebit) {
        this.TBasisDebit = TBasisDebit;
    }
    
    @Column(name="CHECK_RESULT", length=8)

    public String getCheckResult() {
        return this.checkResult;
    }
    
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
    
    @Column(name="CHECK_OPINION", length=600)

    public String getCheckOpinion() {
        return this.checkOpinion;
    }
    
    public void setCheckOpinion(String checkOpinion) {
        this.checkOpinion = checkOpinion;
    }
    
    @Column(name="UPDATE_USER", length=32)

    public String getUpdateUser() {
        return this.updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
    @Column(name="UPDATE_TIME", length=20)

    public String getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
   








}