package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Messages {
    private Integer id;

    private String  title;

    private Integer type;

    private Integer status;

    private Integer userid;

    private Integer senduserid;

    private String  sendusername;

    private Date    sendtime;

    private String  content;

    private String  sendtimestr;
    
    private String  username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(Integer senduserid) {
        this.senduserid = senduserid;
    }

    public String getSendusername() {
        return sendusername;
    }

    public void setSendusername(String sendusername) {
        this.sendusername = sendusername == null ? null : sendusername.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * Getter method for property <tt>sendtimestr</tt>.
     * 
     * @return property value of sendtimestr
     */
    public String getSendtimestr() {
        this.sendtimestr = DateUtil.dateConvert(this.sendtime);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}