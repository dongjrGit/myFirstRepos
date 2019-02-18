package com.yinlian.wssc.web.po;

public class V_Hp_Sku {

	private Integer id;
	private Integer kid;
	private Integer type;
	private Integer sort;
	private String name;
	private String imgurl;
	private String imgurl_app;
	private Double price;
	private Double appprice;
	private String shopname;
	private String himgurl;
	private int spuid;

	public int getSpuid() {
		return spuid;
	}

	public void setSpuid(int spuid) {
		this.spuid = spuid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKid() {
		return kid;
	}

	public void setKid(Integer kid) {
		this.kid = kid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getImgurl_app() {
		return imgurl_app;
	}

	public void setImgurl_app(String imgurl_app) {
		this.imgurl_app = imgurl_app;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAppprice() {
		return appprice;
	}

	public void setAppprice(Double appprice) {
		this.appprice = appprice;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getHimgurl() {
		return himgurl;
	}

	public void setHimgurl(String himgurl) {
		this.himgurl = himgurl;
	}

}
