package com.yinlian.wssc.web.po;

import java.util.Date;

import data.ParseUtil;

public class SnewsImg {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.id
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.keyword
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String keyword;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.tag
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String tag;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.url
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String url;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.title
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.tabloid
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String tabloid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.ishead
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer ishead;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.isindex
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer isindex;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.isrecommend
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer isrecommend;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.istop
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer istop;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.author
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String author;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.src
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String src;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.createtime
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Date createtime;

	private String createtimetr;

	public String getCreatetimetr() {
		this.createtimetr = ParseUtil.parseDateToString(createtime, "yyyy-MM-dd HH:mm:ss");
		return createtimetr;
	}

	public void setCreatetimetr(String createtimetr) {
		this.createtimetr = createtimetr;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.state
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer state;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.cid
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer cid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.ctype
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer ctype;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.updatetime
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Date updatetime;

	private String updatetimetr;

	public String getUpdatetimetr() {
		this.updatetimetr = ParseUtil.parseDateToString(updatetime, "yyyy-MM-dd HH:mm:ss");
		return updatetimetr;
	}

	public void setUpdatetimetr(String updatetimetr) {
		this.updatetimetr = updatetimetr;
	}

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.imgurl
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String imgurl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.sort
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer sort;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.yconut
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer yconut;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.ex1
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer ex1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.ex2
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private Integer ex2;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.ex3
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String ex3;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.ex4
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String ex4;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.ex5
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String ex5;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.ex6
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String ex6;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column s_news_img.ex7
	 * 
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	private String ex7;

	private String province;

	private String city;

	private String area;

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

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.id
	 * 
	 * @return the value of s_news_img.id
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.id
	 * 
	 * @param id
	 *            the value for s_news_img.id
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.keyword
	 * 
	 * @return the value of s_news_img.keyword
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.keyword
	 * 
	 * @param keyword
	 *            the value for s_news_img.keyword
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword == null ? null : keyword.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.tag
	 * 
	 * @return the value of s_news_img.tag
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.tag
	 * 
	 * @param tag
	 *            the value for s_news_img.tag
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setTag(String tag) {
		this.tag = tag == null ? null : tag.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.url
	 * 
	 * @return the value of s_news_img.url
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.url
	 * 
	 * @param url
	 *            the value for s_news_img.url
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.title
	 * 
	 * @return the value of s_news_img.title
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.title
	 * 
	 * @param title
	 *            the value for s_news_img.title
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.tabloid
	 * 
	 * @return the value of s_news_img.tabloid
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getTabloid() {
		return tabloid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.tabloid
	 * 
	 * @param tabloid
	 *            the value for s_news_img.tabloid
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setTabloid(String tabloid) {
		this.tabloid = tabloid == null ? null : tabloid.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.ishead
	 * 
	 * @return the value of s_news_img.ishead
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getIshead() {
		return ishead;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.ishead
	 * 
	 * @param ishead
	 *            the value for s_news_img.ishead
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setIshead(Integer ishead) {
		this.ishead = ishead;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.isindex
	 * 
	 * @return the value of s_news_img.isindex
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getIsindex() {
		return isindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.isindex
	 * 
	 * @param isindex
	 *            the value for s_news_img.isindex
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setIsindex(Integer isindex) {
		this.isindex = isindex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.isrecommend
	 * 
	 * @return the value of s_news_img.isrecommend
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getIsrecommend() {
		return isrecommend;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.isrecommend
	 * 
	 * @param isrecommend
	 *            the value for s_news_img.isrecommend
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setIsrecommend(Integer isrecommend) {
		this.isrecommend = isrecommend;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.istop
	 * 
	 * @return the value of s_news_img.istop
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getIstop() {
		return istop;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.istop
	 * 
	 * @param istop
	 *            the value for s_news_img.istop
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setIstop(Integer istop) {
		this.istop = istop;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.author
	 * 
	 * @return the value of s_news_img.author
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.author
	 * 
	 * @param author
	 *            the value for s_news_img.author
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.src
	 * 
	 * @return the value of s_news_img.src
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.src
	 * 
	 * @param src
	 *            the value for s_news_img.src
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setSrc(String src) {
		this.src = src == null ? null : src.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.createtime
	 * 
	 * @return the value of s_news_img.createtime
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.createtime
	 * 
	 * @param createtime
	 *            the value for s_news_img.createtime
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.state
	 * 
	 * @return the value of s_news_img.state
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.state
	 * 
	 * @param state
	 *            the value for s_news_img.state
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.cid
	 * 
	 * @return the value of s_news_img.cid
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getCid() {
		return cid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.cid
	 * 
	 * @param cid
	 *            the value for s_news_img.cid
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.ctype
	 * 
	 * @return the value of s_news_img.ctype
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getCtype() {
		return ctype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.ctype
	 * 
	 * @param ctype
	 *            the value for s_news_img.ctype
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.updatetime
	 * 
	 * @return the value of s_news_img.updatetime
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.updatetime
	 * 
	 * @param updatetime
	 *            the value for s_news_img.updatetime
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.imgurl
	 * 
	 * @return the value of s_news_img.imgurl
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getImgurl() {
		return imgurl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.imgurl
	 * 
	 * @param imgurl
	 *            the value for s_news_img.imgurl
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl == null ? null : imgurl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.sort
	 * 
	 * @return the value of s_news_img.sort
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.sort
	 * 
	 * @param sort
	 *            the value for s_news_img.sort
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.yconut
	 * 
	 * @return the value of s_news_img.yconut
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getYconut() {
		return yconut;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.yconut
	 * 
	 * @param yconut
	 *            the value for s_news_img.yconut
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setYconut(Integer yconut) {
		this.yconut = yconut;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.ex1
	 * 
	 * @return the value of s_news_img.ex1
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getEx1() {
		return ex1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.ex1
	 * 
	 * @param ex1
	 *            the value for s_news_img.ex1
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setEx1(Integer ex1) {
		this.ex1 = ex1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.ex2
	 * 
	 * @return the value of s_news_img.ex2
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public Integer getEx2() {
		return ex2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.ex2
	 * 
	 * @param ex2
	 *            the value for s_news_img.ex2
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setEx2(Integer ex2) {
		this.ex2 = ex2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.ex3
	 * 
	 * @return the value of s_news_img.ex3
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getEx3() {
		return ex3;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.ex3
	 * 
	 * @param ex3
	 *            the value for s_news_img.ex3
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setEx3(String ex3) {
		this.ex3 = ex3 == null ? null : ex3.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.ex4
	 * 
	 * @return the value of s_news_img.ex4
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getEx4() {
		return ex4;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.ex4
	 * 
	 * @param ex4
	 *            the value for s_news_img.ex4
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setEx4(String ex4) {
		this.ex4 = ex4 == null ? null : ex4.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.ex5
	 * 
	 * @return the value of s_news_img.ex5
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getEx5() {
		return ex5;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.ex5
	 * 
	 * @param ex5
	 *            the value for s_news_img.ex5
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setEx5(String ex5) {
		this.ex5 = ex5 == null ? null : ex5.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.ex6
	 * 
	 * @return the value of s_news_img.ex6
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getEx6() {
		return ex6;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.ex6
	 * 
	 * @param ex6
	 *            the value for s_news_img.ex6
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setEx6(String ex6) {
		this.ex6 = ex6 == null ? null : ex6.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column s_news_img.ex7
	 * 
	 * @return the value of s_news_img.ex7
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public String getEx7() {
		return ex7;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column s_news_img.ex7
	 * 
	 * @param ex7
	 *            the value for s_news_img.ex7
	 * @mbggenerated Wed Sep 07 10:55:33 CST 2016
	 */
	public void setEx7(String ex7) {
		this.ex7 = ex7 == null ? null : ex7.trim();
	}
}