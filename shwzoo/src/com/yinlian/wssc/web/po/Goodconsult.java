package com.yinlian.wssc.web.po;

import java.util.Date;

public class Goodconsult {
    private Integer id;

    private Integer sellerid;

    private Integer spuid;

    private Integer consultid;

    private Integer replyid;

    private Integer consulttype;

    private String consultcontent;

    private String replycontent;

    private Date consultdate;

    private Date replydate;

    private Integer vaildflag;

    private Date deldate;

    private Integer status;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getSpuid() {
        return spuid;
    }

    public void setSpuid(Integer spuid) {
        this.spuid = spuid;
    }

    public Integer getConsultid() {
        return consultid;
    }

    public void setConsultid(Integer consultid) {
        this.consultid = consultid;
    }

    public Integer getReplyid() {
        return replyid;
    }

    public void setReplyid(Integer replyid) {
        this.replyid = replyid;
    }

    public Integer getConsulttype() {
        return consulttype;
    }

    public void setConsulttype(Integer consulttype) {
        this.consulttype = consulttype;
    }

    public String getConsultcontent() {
        return consultcontent;
    }

    public void setConsultcontent(String consultcontent) {
        this.consultcontent = consultcontent == null ? null : consultcontent.trim();
    }

    public String getReplycontent() {
        return replycontent;
    }

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent == null ? null : replycontent.trim();
    }

    public Date getConsultdate() {
        return consultdate;
    }

    public void setConsultdate(Date consultdate) {
        this.consultdate = consultdate;
    }

    public Date getReplydate() {
        return replydate;
    }

    public void setReplydate(Date replydate) {
        this.replydate = replydate;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}