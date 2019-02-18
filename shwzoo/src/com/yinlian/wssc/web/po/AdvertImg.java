package com.yinlian.wssc.web.po;

import java.util.Date;

public class AdvertImg {
	private Integer id;

	private String imgurl;

	private String url;

	private Integer type;

	private Integer groupbyid;

	private Integer shopid;

	private Integer annouid;

	private Boolean isdel;

	private Integer position;

	private Integer sort;

	private Integer status;

	private Date creattime;

	private String typename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl == null ? null : imgurl.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGroupbyid() {
		return groupbyid;
	}

	public void setGroupbyid(Integer groupbyid) {
		this.groupbyid = groupbyid;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public Integer getAnnouid() {
		return annouid;
	}

	public void setAnnouid(Integer annouid) {
		this.annouid = annouid;
	}

	public Boolean getIsdel() {
		return isdel;
	}

	public void setIsdel(Boolean isdel) {
		this.isdel = isdel;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreattime() {
		return creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	/**
	 * Getter method for property <tt>typename</tt>.
	 * 
	 * @return property value of typename
	 */
	public String getTypename() {
		if (this.type == null) {
			this.typename = "";
		} else {
			switch (this.type) {
			case 1:
				this.typename = "首页";
				break;

			case 2:
				this.typename = "店铺";
				break;
			case 3:
				this.typename = "团购";
				break;
			case 4:
				this.typename = "分类";
				break;

			default:
				this.typename = "默认";
				break;

			}
		}
		return typename;
	}

	/**
	 * Setter method for property <tt>typename</tt>.
	 * 
	 * @param typename
	 *            value to be assigned to property typename
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}

}