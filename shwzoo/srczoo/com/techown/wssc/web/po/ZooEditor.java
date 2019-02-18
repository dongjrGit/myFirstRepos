package com.techown.wssc.web.po;

import java.util.Date;

import data.ParseUtil;

public class ZooEditor {
	/**
	 * 主键ID自增长
	 */
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 1:banner图对应的图文编辑 2:首页广告对应的图文编辑
	 */
	private Integer type;
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 修改时间
	 */
	private Date updatetime;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 修改人
	 */
	private String operator;
	/**
	 * 0:已删除 1:未删除
	 */
	private Integer delstate;
	/**
	 * 图文编辑器的内容
	 */
	private String content;

	private String createTimeStr;

	public String getCreateTimeStr() {
		if (null != createtime) {
			this.createTimeStr = ParseUtil.parseDateToString(createtime, "yyyy-MM-dd");
		}
		return createTimeStr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDelstate() {
		return delstate;
	}

	public void setDelstate(Integer delstate) {
		this.delstate = delstate;
	}
}