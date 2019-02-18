package com.yinlian.wssc.web.util;

/**
 * 专题搜索
 * @author Administrator
 *
 */
public class TopicCriteria extends Criteria{
	
	
	private Integer pagemark;
	
	
	private Integer topicmark;
	private Integer topictype;
	
	private String webset;

	private Integer hotis;
	
	/**
	 * 省ID
	 */
	private Integer provinceid;

	public Integer getPagemark() {
		return pagemark;
	}

	public void setPagemark(Integer pagemark) {
		this.pagemark = pagemark;
	}

	public Integer getTopicmark() {
		return topicmark;
	}

	public void setTopicmark(Integer topicmark) {
		this.topicmark = topicmark;
	}

	public Integer getTopictype() {
		return topictype;
	}

	public void setTopictype(Integer topictype) {
		this.topictype = topictype;
	}

	public String getWebset() {
		return webset;
	}

	public void setWebset(String webset) {
		this.webset = webset;
	}

	public Integer getHotis() {
		return hotis;
	}

	public void setHotis(Integer hotis) {
		this.hotis = hotis;
	}

	public Integer getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}
	

}
