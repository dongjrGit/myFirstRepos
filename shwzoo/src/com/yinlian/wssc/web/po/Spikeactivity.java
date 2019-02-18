package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Spikeactivity {
    private Integer id;

    private String spikenum;

    private String spikename;

    private Integer spiketype;

    private Date starttime;

    private Date endtime;

    private Integer status;

    private Integer orderby;

    private Boolean isdel;

    private Date deltime;

    private Integer deluserid;

    private Date createtime;

    private Integer createuserid;
    
    private Integer shopid;
    
    private String imgurl;
    
    private String starttimestr;

    private String endtimestr;
    
    private String listdesc;
    private String detaildesc;
    
    private String usesite;
    
    private Integer cid;

    public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getUsesite() {
		return usesite;
	}

	public void setUsesite(String usesite) {
		this.usesite = usesite;
	}

	public String getListdesc() {
		return listdesc;
	}

	public void setListdesc(String listdesc) {
		this.listdesc = listdesc;
	}

	public String getDetaildesc() {
		 if (detaildesc != null && detaildesc.indexOf("\"") > 0) {
             this.detaildesc = detaildesc.replace("\"", "'");
         }
		return detaildesc;
	}

	public void setDetaildesc(String detaildesc) {
		this.detaildesc = detaildesc;
	}

	public String getStarttimestr() {
    	this.starttimestr=DateUtil.dateConvert(this.starttime);
		return starttimestr;
	}

	public void setStarttimestr(String starttimestr) {
		this.starttimestr = starttimestr;
	}

	public String getEndtimestr() {
		this.endtimestr=DateUtil.dateConvert(this.endtime);
		return endtimestr;
	}

	public void setEndtimestr(String endtimestr) {
		this.endtimestr = endtimestr;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpikenum() {
        return spikenum;
    }

    public void setSpikenum(String spikenum) {
        this.spikenum = spikenum == null ? null : spikenum.trim();
    }

    public String getSpikename() {
        return spikename;
    }

    public void setSpikename(String spikename) {
        this.spikename = spikename == null ? null : spikename.trim();
    }

    public Integer getSpiketype() {
        return spiketype;
    }

    public void setSpiketype(Integer spiketype) {
        this.spiketype = spiketype;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
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

    public Integer getDeluserid() {
        return deluserid;
    }

    public void setDeluserid(Integer deluserid) {
        this.deluserid = deluserid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
}