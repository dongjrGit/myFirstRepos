package com.yinlian.pc.dto;

/**
 * 获取运费参数
 * @author Administrator
 *
 */
public class FreightParamDto {

	private Integer shopid;          //店铺ID
	private Integer procount;	     //商品总数量
	private Double proprice;         //商品总价格
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public Integer getProcount() {
		return procount;
	}
	public void setProcount(Integer procount) {
		this.procount = procount;
	}
	public Double getProprice() {
		return proprice;
	}
	public void setProprice(Double proprice) {
		this.proprice = proprice;
	}
	
	
}
