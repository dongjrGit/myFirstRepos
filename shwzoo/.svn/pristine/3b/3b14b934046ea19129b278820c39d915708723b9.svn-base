package com.yinlian.wssc.web.po;

import com.yinlian.wssc.web.util.DateUtil;

public class SpuWithBLOBs extends Spu {
    private String description;

    private String afterservice;

    private String spuinfo;

    private String packinglist;

    private String creattimeStr; // 创建时间

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAfterservice() {    
        return afterservice;
    }

    public void setAfterservice(String afterservice) {
        this.afterservice = afterservice == null ? null : afterservice.trim();
    }

    public String getSpuinfo() {      
        return spuinfo;
    }

    public void setSpuinfo(String spuinfo) {
        this.spuinfo = spuinfo == null ? null : spuinfo.trim();
    }

    public String getPackinglist() {
        return packinglist;
    }

    public void setPackinglist(String packinglist) {
        this.packinglist = packinglist == null ? null : packinglist.trim();
    }

    /**
     * Getter method for property <tt>creattimeStr</tt>.
     * 
     * @return property value of creattimeStr
     */
    public String getCreattimeStr() {
        this.creattimeStr = DateUtil.dateConvert(super.getCreatetime());
        return creattimeStr;
    }

    /**
     * Setter method for property <tt>creattimeStr</tt>.
     * 
     * @param creattimeStr value to be assigned to property creattimeStr
     */
    public void setCreattimeStr(String creattimeStr) {
        this.creattimeStr = creattimeStr;
    }

}