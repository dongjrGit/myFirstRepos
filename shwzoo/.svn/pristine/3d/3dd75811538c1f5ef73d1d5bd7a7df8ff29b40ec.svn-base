package com.techown.wssc.web.po;

import java.util.Date;

import data.ParseUtil;

public class ZooMap {
	/**
	 * 主键ID，自动生成
	 */
	private Integer id;
	/**
	 * 地图备注名称
	 */
	private String name;
	/**
	 * http地址
	 */
	private String url;
	/**
	 * fastdfs地址
	 */
	private String path;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 类型 1：园区地图 2：游园地图
	 */
	private Integer type;
	/**
	 * 创建人
	 */
	private String operator;
	/**
	 * 状态 默认1 ， 0：无效，1：有效
	 */
	private Integer state;

	private String ext1;

	private String ext2;

	/** ==================================================== */
	private String createTimeStr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path == null ? null : path.trim();
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator == null ? null : operator.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1 == null ? null : ext1.trim();
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2 == null ? null : ext2.trim();
	}

	public String getCreateTimeStr() {
		if (null != createTime) {
			this.createTimeStr = ParseUtil.parseDateToString(createTime, "yyyy-MM-dd");
		}
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

}