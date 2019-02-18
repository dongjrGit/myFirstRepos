package com.yinlian.wssc.search;

import com.yinlian.Enums.ShopProType;

public class ProductCriteria implements ICriteria {
    // 排序条件
    private String orderByClause; // order by子句 如果不为空就排序 否则就不排序

    private String sort;         // 排序方式 默认是升序

    /**
     * 分类Id
     */
    private String cid;
    /**
     * 店铺id
     */
    private String sid;
    /**
     * 状态
     */
    private String status;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 产品编号
     */
    private String num;
    /**
     * 产品预警开始
     */
    private Integer warnmin;
    /**
     * 产品预警结束
     */
    private Integer warnmax;
    /**
     * 产品库存开始
     */
    private Integer stockmin;
    /**
     * 产品库存结束
     */
    private Integer stockmax;
    /**
     * 品牌id
     */
    private String bid;
    /**
     * 是否是直营
     */
    private String isowend;
    /**
     * 站点
     */
    private String webset;
    /**
     * 是否包邮
     */
    private String isposttag;
   
    private String idin;
    
    /**
     * 分类全路径
     */
    private String fullpath;
    /**
     * 是否打折 0-否 1-是
     */
    private Integer isoffer;
    
    private  Integer   spuid;
    
    private   Integer   showy; //年
    
    private   Integer   showm; //月
    
    private   Integer   showd; //日
    
    
	public Integer getShowy() {
		return showy;
	}

	public void setShowy(Integer showy) {
		this.showy = showy;
	}

	public Integer getShowm() {
		return showm;
	}

	public void setShowm(Integer showm) {
		this.showm = showm;
	}

	public Integer getShowd() {
		return showd;
	}

	public void setShowd(Integer showd) {
		this.showd = showd;
	}

	public Integer getSpuid() {
		return spuid;
	}

	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}

	public String getIsposttag() {
		return isposttag;
	}

	public void setIsposttag(String isposttag) {
		this.isposttag = isposttag;
	}

	public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        if (!("").equals(cid) && cid != null) {
            cid = cid.trim();
        }
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) throws Exception {
        if (!("").equals(status) && status != null) {
            try {
                if (Integer.parseInt(status) == 2) {
                    // 下架状态包括已审核和下架
                    status = "2,4";
                }
            } catch (Exception ex) {
            }

        }
        this.status = status;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (("").equals(name) || name == null) {
            name = "";
        }
        this.name = name.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        if (("").equals(num) || num == null) {
            num = "";
        }
        this.num = num.trim();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getIsowend() {
        return isowend;
    }

    public void setIsowend(String isowend) {
        if (!("").equals(isowend) && isowend != null) {
            if (isowend.trim().toLowerCase().equals("true")) {
                isowend = String.valueOf(ShopProType.直营.getValue());
            } else {
                isowend = String.valueOf(ShopProType.店铺.getValue());
            }
        }
        this.isowend = isowend;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getWebSet() {
        return webset;
    }

    public void setWebSet(String webset) {        
        this.webset = webset;
    }
    public Integer getWarnmin() {
		return warnmin;
	}

	public void setWarnmin(Integer warnmin) {
		this.warnmin = warnmin;
	}
	public Integer getWarnmax() {
		return warnmax;
	}

	public void setWarnmax(Integer warnmax) {
		this.warnmax = warnmax;
	}
	public Integer getStockmin() {
		return stockmin;
	}

	public void setStockmin(Integer stockmin) {
		this.stockmin = stockmin;
	}
	public Integer getStockmax() {
		return stockmax;
	}

	public void setStockmax(Integer stockmax) {
		this.stockmax = stockmax;
	}

	public String getWebset() {
		return webset;
	}

	public void setWebset(String webset) {
		this.webset = webset;
	}

	public String getIdin() {
		return idin;
	}

	public void setIdin(String idin) {
		this.idin = idin;
	}

	public String getFullpath() {
		return fullpath;
	}

	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}

	public Integer getIsoffer() {
		return isoffer;
	}

	public void setIsoffer(Integer isoffer) {
		this.isoffer = isoffer;
	}
}
