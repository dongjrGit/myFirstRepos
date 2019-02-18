/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

/**
 * 会员的查询类
 * @author Administrator
 * @version $Id: CriteriaEmployee.java, v 0.1 2016年3月14日 上午10:59:04 Administrator Exp $
 */
public class CriteriaEmployee extends Criteria {

    private String  username;    // 员工的登录名

    private String  employeename; // 员工姓名

    private Integer roleid;      // 角色id

    private Integer status;      // 状态

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
     * Getter method for property <tt>employeename</tt>.
     * 
     * @return property value of employeename
     */
    public String getEmployeename() {
        return employeename;
    }

    /**
     * Setter method for property <tt>employeename</tt>.
     * 
     * @param employeename value to be assigned to property employeename
     */
    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    /**
     * Getter method for property <tt>roleid</tt>.
     * 
     * @return property value of roleid
     */
    public Integer getRoleid() {
        return roleid;
    }

    /**
     * Setter method for property <tt>roleid</tt>.
     * 
     * @param roleid value to be assigned to property roleid
     */
    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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

}
