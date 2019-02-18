package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Brand {
    private Integer id;

    private String  name;

    private Date    createtime;

    private Integer status;

    private String  img;

    private String  officialurl;

    private Integer flagshipid;

    private String  description;

    private String  createtimestr; // 日期转换成字符串
    
    private Integer deluserid;
    private Boolean isdel;
    private Date deltime;

    public Integer getDeluserid() {
		return deluserid;
	}

	public void setDeluserid(Integer deluserid) {
		this.deluserid = deluserid;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getOfficialurl() {
        return officialurl;
    }

    public void setOfficialurl(String officialurl) {
        this.officialurl = officialurl == null ? null : officialurl.trim();
    }

    public Integer getFlagshipid() {
        return flagshipid;
    }

    public void setFlagshipid(Integer flagshipid) {
        this.flagshipid = flagshipid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * Getter method for property <tt>createtimestr</tt>.
     * 
     * @return property value of createtimestr
     */
    public String getCreatetimestr() {
        this.createtimestr = DateUtil.dateConvert(createtime);
        return createtimestr;
    }

    /**
     * Setter method for property <tt>createtimestr</tt>.
     * 
     * @param createtimestr value to be assigned to property createtimestr
     */
    public void setCreatetimestr(String createtimestr) {
        this.createtimestr = createtimestr;
    }

}