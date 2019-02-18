package com.yinlian.wssc.web.po;

import java.util.Date;

public class Topic {
    private Integer id;

    private Integer fatherid;

    private Integer type;

    private Integer mark;

    private String description;

    private String imgurl;

    private String url;

    private Integer floorappid;

    private String title;

    private Boolean isdelete;
    
    private String floorname;
    
    private Integer sort;
    
    private Integer status;
   
    private String webset;
    private Integer pagetag;
    
    private  String name;
    
    private Integer hotis;
    
    private Date addtime;
    
    private Integer provinceid;
    
    private Boolean issys;
    
    /**
     * 背景颜色
     */
    private String backcolour;
    
    public Integer getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getHotis() {
		return hotis;
	}

	public void setHotis(Integer hotis) {
		this.hotis = hotis;
	}

	public String getName() {
    	this.name=title;
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getFloorappid() {
        return floorappid;
    }

    public void setFloorappid(Integer floorappid) {
        this.floorappid = floorappid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

	public String getFloorname() {
		return floorname;
	}

	public void setFloorname(String floorname) {
		this.floorname = floorname;
	}
	public String getWebset() {
		return webset;
	}

	public void setWebset(String webset) {
		this.webset = webset;
	}
	public Integer getPagetag() {
        return pagetag;
    }

    public void setPagetag(Integer pagetag) {
        this.pagetag = pagetag;
    }

	public Boolean getIssys() {
		return issys;
	}

	public void setIssys(Boolean issys) {
		this.issys = issys;
	}

	public String getBackcolour() {
		return backcolour;
	}

	public void setBackcolour(String backcolour) {
		this.backcolour = backcolour;
	}

}