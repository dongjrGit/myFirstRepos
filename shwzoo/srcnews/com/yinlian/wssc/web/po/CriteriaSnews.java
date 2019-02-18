package com.yinlian.wssc.web.po;

import com.yinlian.wssc.web.util.Criteria;

public class CriteriaSnews extends Criteria {

	private String id;
	
	private String title;

	private String startTime;

	private String endTime;

	private Integer ctype;

	private String fpath;

	private Integer state;

	private String province;

	private String city;

	private String area;
 
	private Integer isrecommend;//是否推荐 0:推荐，1不推荐  （后台这样设置的）
	private Integer istop;//是否置顶 0:置顶，1不置顶  （后台这样设置的）
	private Integer cid; //新闻分类id
	
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getIstop() {
		return istop;
	}
	public void setIstop(Integer istop) {
		this.istop = istop;
	}
	
	public Integer getIsrecommend() {
		return isrecommend;
	}
	public void setIsrecommend(Integer isrecommend) {
		this.isrecommend = isrecommend;
	}
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getFpath() {
		return fpath;
	}

	public void setFpath(String fpath) {
		this.fpath = fpath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getCtype() {
		return ctype;
	}

	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
