package com.yinlian.wssc.web.po;

import java.util.Date;

public class Browsehistory {
    private Integer id;

    private Integer userid;

    private Integer spuid;

    private Integer channeltype;

    private Date browsetime;

    private Date createtime;

    private Integer vaildflag;

    private Date deldate;

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

    public Integer getSpuid() {
        return spuid;
    }

    public void setSpuid(Integer spuid) {
        this.spuid = spuid;
    }

    public Integer getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(Integer channeltype) {
        this.channeltype = channeltype;
    }

    public Date getBrowsetime() {
        return browsetime;
    }

    public void setBrowsetime(Date browsetime) {
        this.browsetime = browsetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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
}