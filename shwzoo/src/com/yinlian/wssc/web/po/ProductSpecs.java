package com.yinlian.wssc.web.po;

public class ProductSpecs {
    private Integer id;

    private String  name;

    private Integer classid;

    private Integer status;

    private Integer orderby;

    private String  displaylocation;

    private Boolean isentry;

    private Integer specstypeid;
    
    private String classFullpath;
    
    private String classFullName;

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

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    public String getDisplaylocation() {
        return displaylocation;
    }

    public void setDisplaylocation(String displaylocation) {
        this.displaylocation = displaylocation == null ? null : displaylocation.trim();
    }

    public Boolean getIsentry() {
        return isentry;
    }

    public void setIsentry(Boolean isentry) {
        this.isentry = isentry;
    }

    public Integer getSpecstypeid() {
        return specstypeid;
    }

    public void setSpecstypeid(Integer specstypeid) {
        this.specstypeid = specstypeid;
    }

	public String getClassFullName() {
		return classFullName;
	}

	public void setClassFullName(String classFullName) {
		this.classFullName = classFullName;
	}

	public String getClassFullpath() {
		return classFullpath;
	}

	public void setClassFullpath(String classFullpath) {
		this.classFullpath = classFullpath;
	}
}