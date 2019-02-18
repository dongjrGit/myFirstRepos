package com.yinlian.api.app.dto;

public class ProductDto {
	/**
	 * 标识Id(spu)
	 */
	private int id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 价格
	 */
	private Float Price;
	/**
	 * 销量
	 */
	private Integer count;
	/**
	 * 排序
	 */
	private int sort;
	/**
	 * 图片
	 */
	private String img;
	/**
	 * 是否有赠品(备用)
	 */
	private Boolean isgift;
	/**
	 * 是否是秒杀(备用)
	 */
	private Boolean isseckill;

	/**
	 * 库存
	 */
	private int stock;
	
	/**
	 * 是否直营
	 */
	private Boolean isowned;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getPrice() {
		return Price;
	}

	public void setPrice(Float price) {
		Price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Boolean getIsgift() {
		return isgift;
	}

	public void setIsgift(Boolean isgift) {
		this.isgift = isgift;
	}

	public Boolean getIsseckill() {
		return isseckill;
	}

	public void setIsseckill(Boolean isseckill) {
		this.isseckill = isseckill;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}


	public Boolean getIsowned() {
		return isowned;
	}

	public void setIsowned(Boolean isowned) {
		this.isowned = isowned;
	}


}
