package com.mobile.application.entity;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * TBasisShootMater entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="T_BASIS_SHOOT_MATER"
)

public class TBasisShootMater  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String materId;
     private String materPid;
     private String materName;
     private String materLevel;
     private String shootRequire;
     private String materType;
     private String creatUser;
     private String creatTime;


    // Constructors

    /** default constructor */
    public TBasisShootMater() {
    }

	/** minimal constructor */
    public TBasisShootMater(String materId) {
        this.materId = materId;
    }
    
    /** full constructor */
    public TBasisShootMater(String materId, String materPid, String materName, String materLevel, String shootRequire, String materType, String creatUser, String creatTime) {
        this.materId = materId;
        this.materPid = materPid;
        this.materName = materName;
        this.materLevel = materLevel;
        this.shootRequire = shootRequire;
        this.materType = materType;
        this.creatUser = creatUser;
        this.creatTime = creatTime;
    }

   
    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
    @Column(name="MATER_ID", unique=true, nullable=false, length=32)

    public String getMaterId() {
        return this.materId;
    }
    
    public void setMaterId(String materId) {
        this.materId = materId;
    }
    
    @Column(name="MATER_PID", length=32)

    public String getMaterPid() {
        return this.materPid;
    }
    
    public void setMaterPid(String materPid) {
        this.materPid = materPid;
    }
    
    @Column(name="MATER_NAME", length=60)

    public String getMaterName() {
        return this.materName;
    }
    
    public void setMaterName(String materName) {
        this.materName = materName;
    }
    
    @Column(name="MATER_LEVEL", length=2)

    public String getMaterLevel() {
        return this.materLevel;
    }
    
    public void setMaterLevel(String materLevel) {
        this.materLevel = materLevel;
    }
    
    @Column(name="SHOOT_REQUIRE", length=120)

    public String getShootRequire() {
        return this.shootRequire;
    }
    
    public void setShootRequire(String shootRequire) {
        this.shootRequire = shootRequire;
    }
    
    @Column(name="MATER_TYPE", length=20)

    public String getMaterType() {
        return this.materType;
    }
    
    public void setMaterType(String materType) {
        this.materType = materType;
    }
    
    @Column(name="CREAT_USER", length=32)

    public String getCreatUser() {
        return this.creatUser;
    }
    
    public void setCreatUser(String creatUser) {
        this.creatUser = creatUser;
    }
    
    @Column(name="CREAT_TIME", length=20)

    public String getCreatTime() {
        return this.creatTime;
    }
    
    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }
   
}