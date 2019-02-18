package com.yinlian.wssc.web.po;

import java.util.Date;

public class Hispass {
    private Integer id;

    private String password;

    private Integer accountid;

    private Integer status;

    private Date createtime;

    private Date failuretime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getFailuretime() {
        return failuretime;
    }

    public void setFailuretime(Date failuretime) {
        this.failuretime = failuretime;
    }
}