package com.yinlian.wssc.web.po;

import java.util.Date;

public class Invoice {
    private Integer id;

    private Integer userid;

    private Integer orderid;

    private Integer type;

    private Integer titletype;

    private String title;

    private Integer vaildflag;

    private Date deldate;

    private String content;

    private String mobile;

    private String email;

    private Integer status;

    private Date createdate;

    private Date printdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid ;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTitletype() {
        return titletype;
    }

    public void setTitletype(Integer titletype) {
        this.titletype = titletype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getVaildflag() {
        return vaildflag;
    }

    public void setVaildflag(Integer vaildflag) {
        this.vaildflag = vaildflag;
    }

    public Date getDeldate() {
        return deldate;
    }

    public void setDeldate(Date deldate) {
        this.deldate = deldate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getPrintdate() {
        return printdate;
    }

    public void setPrintdate(Date printdate) {
        this.printdate = printdate;
    }
}