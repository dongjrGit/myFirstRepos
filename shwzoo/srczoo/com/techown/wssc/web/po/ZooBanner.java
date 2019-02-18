package com.techown.wssc.web.po;

import java.util.Date;

public class ZooBanner {
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 页面标识
	 */
	private Integer pageMark;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图片路径
	 */
	private String img;

	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 类型ID
	 */
	private Integer typeId;
	/**
	 * 链接
	 */
	private String url;
	/**
	 * 状态 0：禁用 1：启用
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 操作人
	 */
	private String operator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPageMark() {
		return pageMark;
	}

	public void setPageMark(Integer pageMark) {
		this.pageMark = pageMark;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator == null ? null : creator.trim();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}
}