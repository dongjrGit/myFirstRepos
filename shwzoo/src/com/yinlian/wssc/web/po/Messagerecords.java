package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Messagerecords {
    private Integer id;

    private String  mobile;

    private Date    sendtime;

    private Integer type;

    private Integer userid;

    private Integer senduser;

    private String  title;

    private String  content;

    private String  username;   //收件人

    private String  sendtimestr; //发送的时间

    private String  sendname;   // 发件人

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSenduser() {
        return senduser;
    }

    public void setSenduser(Integer senduser) {
        this.senduser = senduser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
     * Getter method for property <tt>sendtimestr</tt>.
     * 
     * @return property value of sendtimestr
     */
    public String getSendtimestr() {
        this.sendtimestr = DateUtil.dateConvert(sendtime);
        return sendtimestr;
    }

    /**
     * Setter method for property <tt>sendtimestr</tt>.
     * 
     * @param sendtimestr value to be assigned to property sendtimestr
     */
    public void setSendtimestr(String sendtimestr) {
        this.sendtimestr = sendtimestr;
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

}