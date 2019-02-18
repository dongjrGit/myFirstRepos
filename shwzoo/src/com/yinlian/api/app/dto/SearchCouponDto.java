package com.yinlian.api.app.dto;

import java.util.List;

/**
 * 结算页优惠卷查询条件
 * @author Administrator
 *
 */
public class SearchCouponDto {
	
	private Integer shopid;  //店铺ID
	
	private Float money;     //商品金额
	
	private List<Prolist> prolist;   //商品信息
	
	public Integer getShopid() {
		return shopid;
	}
	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}

	public List<Prolist> getProlist() {
		return prolist;
	}
	public void setProlist(List<Prolist> prolist) {
		this.prolist = prolist;
	}

	public static class Prolist{
		private Integer proid;      //商品ID
		
		private Float promoney;     //商品金额

		public Integer getProid() {
			return proid;
		}

		public void setProid(Integer proid) {
			this.proid = proid;
		}

		public Float getPromoney() {
			return promoney;
		}

		public void setPromoney(Float promoney) {
			this.promoney = promoney;
		}
		
	}
	
}
