package com.yinlian.wssc.web.po;

public class Employee {
    private Integer id;

    private String  username;

    private String  password;

    private Integer shopid;

    private String  empnum;

    private String  realname;

    private String  mobile;

    private Integer roleid;

    private Boolean isdel;

    private Boolean islock;

    private Integer status;

    private String  email;

    private String  mark;

    private String  tel;

    private String  shopname; // 所属店铺的名字

    private String  rolename; //角色名字

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getEmpnum() {
        return empnum;
    }

    public void setEmpnum(String empnum) {
        this.empnum = empnum == null ? null : empnum.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Boolean getIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * Getter method for property <tt>shopname</tt>.
     * 
     * @return property value of shopname
     */
    public String getShopname() {
        return shopname;
    }

    /**
     * Setter method for property <tt>shopname</tt>.
     * 
     * @param shopname value to be assigned to property shopname
     */
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    /**
     * Getter method for property <tt>rolename</tt>.
     * 
     * @return property value of rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * Setter method for property <tt>rolename</tt>.
     * 
     * @param rolename value to be assigned to property rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

}