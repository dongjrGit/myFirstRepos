package com.yinlian.wssc.web.po;

import java.util.Date;
import java.util.List;

public class Freight {
    private Integer           id;

    private String            name;

    private Integer           isexemptionpostage;

    private Integer           pricingmode;

    private Integer           transportmode;

    private Integer           status;

    private Integer           shopid;

    private Date              createtime;

    private Integer           num;

    private String            description;
    
    private Integer           iscondition;        //是否满足条件

    private List<FreightAttr> freightAttrs;      //模板详细的配置信息

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

    public Integer getIsexemptionpostage() {
        return isexemptionpostage;
    }

    public void setIsexemptionpostage(Integer isexemptionpostage) {
        this.isexemptionpostage = isexemptionpostage;
    }

    public Integer getPricingmode() {
        return pricingmode;
    }

    public void setPricingmode(Integer pricingmode) {
        this.pricingmode = pricingmode;
    }

    public Integer getTransportmode() {
        return transportmode;
    }

    public void setTransportmode(Integer transportmode) {
        this.transportmode = transportmode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getIscondition() {
		return iscondition;
	}

	public void setIscondition(Integer iscondition) {
		this.iscondition = iscondition;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * Getter method for property <tt>freightAttrs</tt>.
     * 
     * @return property value of freightAttrs
     */
    public List<FreightAttr> getFreightAttrs() {
        return freightAttrs;
    }

    /**
     * Setter method for property <tt>freightAttrs</tt>.
     * 
     * @param freightAttrs value to be assigned to property freightAttrs
     */
    public void setFreightAttrs(List<FreightAttr> freightAttrs) {
        this.freightAttrs = freightAttrs;
    }

}