package com.yinlian.wssc.web.po;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pointsrecords {
    private Integer id;

    private Integer points;

    private Integer type;

    private Integer fromtype;

    private Date createtime;

    private Integer userid;
    
    private String createtimestr;
    
    private String ct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFromtype() {
        return fromtype;
    }

    public void setFromtype(Integer fromtype) {
        this.fromtype = fromtype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

	public String getCreatetimestr() {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		this.createtimestr=sim.format(createtime);
		return createtimestr;
	}

	public void setCreatetimestr(String createtimestr) {
		this.createtimestr = createtimestr;
	}

	public String getCt() {
		SimpleDateFormat sim=new SimpleDateFormat("yyyyMMdd");
		this.ct=sim.format(createtime);
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}
	
	
}