package com.yinlian.wssc.web.po;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Announcement {
    private Integer id;

    private String title;

    private String content;

    private Date creattime;

    private Integer userid;

    private Integer sort;
    
    private String creattimestr;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

	public String getCreattimestr() {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		this.creattimestr=sim.format(creattime);
		return creattimestr;
	}

	public void setCreattimestr(String creattimestr) {
		this.creattimestr = creattimestr;
	}
}