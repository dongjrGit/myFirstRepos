package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Shopnotice {
    private Integer id;

    private Integer shopid;

    private String  title;

    private String  content;

    private Date    sendtime;

    private String  edituser;

    private String  sendtimeStr; //发布时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
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

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getEdituser() {
        return edituser;
    }

    public void setEdituser(String edituser) {
        this.edituser = edituser == null ? null : edituser.trim();
    }

    /**
     * Getter method for property <tt>sendtimeStr</tt>.
     * 
     * @return property value of sendtimeStr
     */
    public String getSendtimeStr() {
        this.sendtimeStr = DateUtil.dateConvert(sendtime);
        return sendtimeStr;
    }

    /**
     * Setter method for property <tt>sendtimeStr</tt>.
     * 
     * @param sendtimeStr value to be assigned to property sendtimeStr
     */
    public void setSendtimeStr(String sendtimeStr) {
        this.sendtimeStr = sendtimeStr;
    }

}