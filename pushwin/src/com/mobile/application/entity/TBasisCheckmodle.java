package com.mobile.application.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * TBasisCheckmodle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_BASIS_CHECKMODLE")

public class TBasisCheckmodle  implements java.io.Serializable {


    // Fields    

     private String modleCode;
     private String modleName;
     private String modlePath;
     private String modleTable;
     private String updateTime;
     private String updateUser;


    // Constructors

    /** default constructor */
    public TBasisCheckmodle() {
    }

    
    /** full constructor */
    public TBasisCheckmodle(String modleName, String modlePath, String modleTable, String updateTime, String updateUser) {
        this.modleName = modleName;
        this.modlePath = modlePath;
        this.modleTable = modleTable;
        this.updateTime = updateTime;
        this.updateUser = updateUser;
    }

   
    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id 
    @GeneratedValue(generator="generator")
    
    @Column(name="MODLE_CODE", unique=true, nullable=false, length=32)

    public String getModleCode() {
        return this.modleCode;
    }
    
    public void setModleCode(String modleCode) {
        this.modleCode = modleCode;
    }
    
    @Column(name="MODLE_NAME", length=60)

    public String getModleName() {
        return this.modleName;
    }
    
    public void setModleName(String modleName) {
        this.modleName = modleName;
    }
    
    @Column(name="MODLE_PATH", length=60)

    public String getModlePath() {
        return this.modlePath;
    }
    
    public void setModlePath(String modlePath) {
        this.modlePath = modlePath;
    }
    
    @Column(name="MODLE_TABLE", length=20)

    public String getModleTable() {
        return this.modleTable;
    }
    
    public void setModleTable(String modleTable) {
        this.modleTable = modleTable;
    }
    
    @Column(name="UPDATE_TIME", length=20)

    public String getUpdateTime() {
        return this.updateTime;
    }
    
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
    @Column(name="UPDATE_USER", length=20)

    public String getUpdateUser() {
        return this.updateUser;
    }
    
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
   








}