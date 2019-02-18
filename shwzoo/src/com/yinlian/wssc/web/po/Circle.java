package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Circle {
    private Integer id;

    private String name;

    private Date createtime;
    
    private String createtimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public String getCreatetimeStr() {
		this.createtimeStr = DateUtil.dateConvert(createtime);
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
    
}