package com.yinlian.wap.dto;

public class CookieCartDto {

	// / <summary>
	// / SpuId
	// / </summary>
	private Integer SpuId;
	// / <summary>
	// / 店铺Id
	// / </summary>
	private Integer ShopId;
	// / <summary>
	// / 商品ID
	// / </summary>
	private Integer GoodsID;

	// / <summary>
	// / 商品数量
	// / </summary>
	private Integer GoodsCount;

	// / <summary>
	// / 商品类型
	// / </summary>
	private Integer CartType;

	// [DefaultValue(false)]
	private boolean IsSelected;

	// / <summary>
	// / 店铺活动Id
	// / </summary>
	// [DefaultValue(0)]
	private Integer ShopActId;

	// / <summary>
	// / Spu活动Id
	// / </summary>
	// [DefaultValue(0)]
	private Integer SpuActId;

	// / <summary>
	// / 闪购或秒杀Id
	// / </summary>
	private Integer SpikeID;
	/**
	 * 购物车子表ID
	 */
	private Integer shopcartproid;

	public Integer getSpuId() {
		return SpuId;
	}

	public void setSpuId(Integer spuId) {
		SpuId = spuId;
	}

	public Integer getShopId() {
		return ShopId;
	}

	public void setShopId(Integer shopId) {
		ShopId = shopId;
	}

	public Integer getGoodsID() {
		return GoodsID;
	}

	public void setGoodsID(Integer goodsID) {
		GoodsID = goodsID;
	}

	public Integer getGoodsCount() {
		return GoodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		GoodsCount = goodsCount;
	}

	public Integer getCartType() {
		return CartType;
	}

	public void setCartType(Integer cartType) {
		CartType = cartType;
	}

	public boolean isIsSelected() {
		return IsSelected;
	}

	public void setIsSelected(boolean isSelected) {
		IsSelected = isSelected;
	}

	public Integer getShopActId() {
		return ShopActId;
	}

	public void setShopActId(Integer shopActId) {
		ShopActId = shopActId;
	}

	public Integer getSpuActId() {
		return SpuActId;
	}

	public void setSpuActId(Integer spuActId) {
		SpuActId = spuActId;
	}

	public Integer getSpikeID() {
		return SpikeID;
	}

	public void setSpikeID(Integer spikeID) {
		SpikeID = spikeID;
	}

	public Integer getShopcartproid() {
		return shopcartproid;
	}

	public void setShopcartproid(Integer shopcartproid) {
		this.shopcartproid = shopcartproid;
	}
}
