package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;
/**
 * 商品详情页获取闪购数据
 * @author Administrator
 *
 */
public class sgSpuDto {
	
	//活动ID
    private int id ;
    //优惠价格
    private Float price ;
    //结束时间
    private Date endTime ;
    //活动商品库存
    private Integer spucount;
    
	public Integer getSpucount() {
		return spucount;
	}
	public void setSpucount(Integer spucount) {
		this.spucount = spucount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getEndTime() {
		return DateUtil.dateConvert(this.endTime);
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
    

}
