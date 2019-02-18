/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.util.Date;

/**
 * CriteriaMessage.java
 * @author Administrator
 * @version $Id: CriteriaMessage.java, v 0.1 2016年3月21日 下午8:11:42 Administrator Exp $
 */
public class CriteriaMessage extends Criteria {

    private Integer userid;  //用户id

    private Integer status;  //状态

    private String  username; //收件人
    
    private String  sendid; //发件人id

    private Date    begin;   //开始时间

    private Date    end;     //结束时间

    private Integer type;    // 信息类型 

    private String  sendname; //发件人
    
    private Integer  usertype; //发件人
    
    private String  mobile;

    /**
     * Getter method for property <tt>userid</tt>.
     * 
     * @return property value of userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * Setter method for property <tt>userid</tt>.
     * 
     * @param userid value to be assigned to property userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * Getter method for property <tt>status</tt>.
     * 
     * @return property value of status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     * 
     * @param status value to be assigned to property status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>username</tt>.
     * 
     * @return property value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for property <tt>username</tt>.
     * 
     * @param username value to be assigned to property username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter method for property <tt>begin</tt>.
     * 
     * @return property value of begin
     */
    public Date getBegin() {
        return begin;
    }

    /**
     * Setter method for property <tt>begin</tt>.
     * 
     * @param begin value to be assigned to property begin
     */
    public void setBegin(Date begin) {
        this.begin = begin;
    }

    /**
     * Getter method for property <tt>end</tt>.
     * 
     * @return property value of end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Setter method for property <tt>end</tt>.
     * 
     * @param end value to be assigned to property end
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * Getter method for property <tt>type</tt>.
     * 
     * @return property value of type
     */
    public Integer getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     * 
     * @param type value to be assigned to property type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>sendname</tt>.
     * 
     * @return property value of sendname
     */
    public String getSendname() {
        return sendname;
    }

    /**
     * Setter method for property <tt>sendname</tt>.
     * 
     * @param sendname value to be assigned to property sendname
     */
    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

	public String getSendid() {
		return sendid;
	}

	public void setSendid(String sendid) {
		this.sendid = sendid;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
