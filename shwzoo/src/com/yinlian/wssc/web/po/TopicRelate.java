package com.yinlian.wssc.web.po;

public class TopicRelate {
    private Integer id;

    private Integer topicid;

    private Integer relatedid;

    private String imgurl;
    
    private Integer sort;
    
    private Integer classifyid;
    
    private String classifyname;
    
    
    
	public Integer getClassifyid() {
		return classifyid;
	}

	public void setClassifyid(Integer classifyid) {
		this.classifyid = classifyid;
	}

	public String getClassifyname() {
		return classifyname;
	}

	public void setClassifyname(String classifyname) {
		this.classifyname = classifyname;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTopicid() {
        return topicid;
    }

    public void setTopicid(Integer topicid) {
        this.topicid = topicid;
    }

    public Integer getRelatedid() {
        return relatedid;
    }

    public void setRelatedid(Integer relatedid) {
        this.relatedid = relatedid;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }
}