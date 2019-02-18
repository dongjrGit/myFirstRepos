package com.yinlian.pc.dto;

import java.util.List;

import com.yinlian.api.app.dto.AvailableCouponDto;

/**
 * pc端优惠劵领取页
 * @author Administrator
 *
 */

public class CouponGetDto {

	private Integer shopid;                  //店铺ID
	private String shopname;                //店铺名称
	private List<AvailableCouponDto> list;   //店铺下优惠劵
	
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public List<AvailableCouponDto> getList() {
		return list;
	}
	public void setList(List<AvailableCouponDto> list) {
		this.list = list;
	}
	
}
