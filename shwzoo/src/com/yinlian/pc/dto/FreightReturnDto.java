package com.yinlian.pc.dto;

/**
 * 运费返回集合
 * @author Administrator
 *
 */
public class FreightReturnDto {

	private Integer id;        //运费模板id
	private Integer shopid;    //店铺id
	private Double price;      //运费
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
