package com.yinlian.wssc.web.po;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class Groupbuyorder {
    private Integer id;

    private Integer buyerid;

    private Integer shopid;

    private String ordercode;

    private Date createtime;

    private String groupbuycode;

    private Date starttime;

    private Date endtime;

    private Integer status;
    
    private Float orderprice;
    
    private Float payprice;
    
    private Integer count;
    
    private Integer isdel;

    private Date deltime;
    
    private String starttimestr;
    
    private String endtimestr;
    
    private String createtimestr;
    
    private Float discount;
    
    private Date paytime;
    private String paytimestr;
    
    private Integer beanscount;
    private Integer pcouponid;
    private Integer scouponid;
    private Float receivableprice;
    
    public Integer getBeanscount() {
		return beanscount;
	}

	public void setBeanscount(Integer beanscount) {
		this.beanscount = beanscount;
	}

	public Integer getPcouponid() {
		return pcouponid;
	}

	public void setPcouponid(Integer pcouponid) {
		this.pcouponid = pcouponid;
	}

	public Integer getScouponid() {
		return scouponid;
	}

	public void setScouponid(Integer scouponid) {
		this.scouponid = scouponid;
	}

	public Float getReceivableprice() {
		return receivableprice;
	}

	public void setReceivableprice(Float receivableprice) {
		this.receivableprice = receivableprice;
	}

	public Date getPaytime() {
		return paytime;
	}

	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}

	public String getPaytimestr() {
		return DateUtil.dateConvert(this.paytime);
	}

	public void setPaytimestr(String paytimestr) {
		this.paytimestr = paytimestr;
	}

	public Float getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(Float orderprice) {
		this.orderprice = orderprice;
	}

	public Float getPayprice() {
		return payprice;
	}

	public void setPayprice(Float payprice) {
		this.payprice = payprice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Integer buyerid) {
        this.buyerid = buyerid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getGroupbuycode() {
        return groupbuycode;
    }

    public void setGroupbuycode(String groupbuycode) {
        this.groupbuycode = groupbuycode == null ? null : groupbuycode.trim();
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

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Date getDeltime() {
        return deltime;
    }

    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }
    SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public String getStarttimestr() {
		this.starttimestr=sim.format(starttime);
		return starttimestr;
	}

	public void setStarttimestr(String starttimestr) {
		this.starttimestr = starttimestr;
	}

	public String getEndtimestr() {
		this.endtimestr=sim.format(endtime);
		return endtimestr;
	}

	public void setEndtimestr(String endtimestr) {
		this.endtimestr = endtimestr;
	}

	public String getCreatetimestr() {
		this.createtimestr=sim.format(createtime);
		return createtimestr;
	}

	public void setCreatetimestr(String createtimestr) {
		this.createtimestr = createtimestr;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}
}