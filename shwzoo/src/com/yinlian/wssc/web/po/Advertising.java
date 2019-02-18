package com.yinlian.wssc.web.po;

public class Advertising {
	private Integer id;

    private String title;

    private String imag;

    private String url;

    private Integer orderby;

    private String content;

    private Integer type;

    private Integer display;

    private Integer status;
    
    private Integer webset;
    
    private Integer typeid;
    
    private Integer pagemark;
    
    private Integer pagemarkid;
    
    private Integer shopid;

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

    public String getImag() {
        return imag;
    }

    public void setImag(String imag) {
        this.imag = imag == null ? null : imag.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getWebSet() {
        return webset;
    }
    public void setWebSet(Integer webset) {
        this.webset = webset;
    }

	public Integer getWebset() {
		return webset;
	}

	public void setWebset(Integer webset) {
		this.webset = webset;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getPagemark() {
		return pagemark;
	}

	public void setPagemark(Integer pagemark) {
		this.pagemark = pagemark;
	}

	public Integer getPagemarkid() {
		return pagemarkid;
	}

	public void setPagemarkid(Integer pagemarkid) {
		this.pagemarkid = pagemarkid;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
}