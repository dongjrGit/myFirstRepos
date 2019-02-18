package com.yinlian.wssc.web.po;

public class VSpecsinfo {
    private Integer skuid;

    private Integer valueid;

    private String value;

    private Integer specsid;

    private String name;

    private Integer typeid;

    private String typename;

    private Integer typeorder;

    private Integer specsorder;

    private String displaylocation;

    private String fullpath;

    private Integer classid;

    private Integer typestatus;

    private Integer valuestatus;

    private Integer specsstatus;


    public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	public Integer getValueid() {
        return valueid;
    }

    public void setValueid(Integer valueid) {
        this.valueid = valueid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getSpecsid() {
        return specsid;
    }

    public void setSpecsid(Integer specsid) {
        this.specsid = specsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Integer getTypeorder() {
        return typeorder;
    }

    public void setTypeorder(Integer typeorder) {
        this.typeorder = typeorder;
    }

    public Integer getSpecsorder() {
        return specsorder;
    }

    public void setSpecsorder(Integer specsorder) {
        this.specsorder = specsorder;
    }

    public String getDisplaylocation() {
        return displaylocation;
    }

    public void setDisplaylocation(String displaylocation) {
        this.displaylocation = displaylocation == null ? null : displaylocation.trim();
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath == null ? null : fullpath.trim();
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getTypestatus() {
        return typestatus;
    }

    public void setTypestatus(Integer typestatus) {
        this.typestatus = typestatus;
    }

    public Integer getValuestatus() {
        return valuestatus;
    }

    public void setValuestatus(Integer valuestatus) {
        this.valuestatus = valuestatus;
    }

    public Integer getSpecsstatus() {
        return specsstatus;
    }

    public void setSpecsstatus(Integer specsstatus) {
        this.specsstatus = specsstatus;
    }
}