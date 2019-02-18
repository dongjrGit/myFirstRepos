package com.yinlian.wssc.web.po;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.yinlian.wssc.platform.validata.UsersShopAddValidate;
import com.yinlian.wssc.platform.validata.UsersShopValidate;

public class Accounts {
    private Integer id;
    @NotBlank(message = "用户名不能为空", groups = { UsersShopValidate.class })
    private String  loginname;

	private String  password;

    private String  phone;

    private String  email;

    private Integer usertype;

    private Integer userid;

    private Integer status;

    private Date    createtime;

    private Boolean isdel;

    private Date    deltime;

    private Integer deluserid;

    private Integer roleid;
    
    private Integer channelType;

    @NotBlank(message = "确认密码不能为空", groups = { UsersShopAddValidate.class })
    private String  confirmpassword; // 确认密码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Date getDeltime() {
        return deltime;
    }

    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    public Integer getDeluserid() {
        return deluserid;
    }

    public void setDeluserid(Integer deluserid) {
        this.deluserid = deluserid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    /**
     * Getter method for property <tt>confirmpassword</tt>.
     * 
     * @return property value of confirmpassword
     */
    public String getConfirmpassword() {
        return confirmpassword;
    }

    /**
     * Setter method for property <tt>confirmpassword</tt>.
     * 
     * @param confirmpassword value to be assigned to property confirmpassword
     */
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

}