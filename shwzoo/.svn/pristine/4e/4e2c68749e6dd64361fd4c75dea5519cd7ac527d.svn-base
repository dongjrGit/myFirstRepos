package com.yinlian.wssc.web.po;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

public class Menus {
    private Integer     id;

    private String      name;

    private Integer     menutype;

    private String      menuurl;

    private Integer     level;

    private Integer     fatherid;

    private Integer     status;

    private Boolean     isdel;

    private Date        createtime;

    private Date        deltime;

    private Integer     deluser;

    private String      fullpath;

    private Integer     type;

    private Boolean     haschild;

    private List<Menus> childrens; // 菜单的子菜单

    private String      json;

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

    public Integer getMenutype() {
        return menutype;
    }

    public void setMenutype(Integer menutype) {
        this.menutype = menutype;
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getDeltime() {
        return deltime;
    }

    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    public Integer getDeluser() {
        return deluser;
    }

    public void setDeluser(Integer deluser) {
        this.deluser = deluser;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath == null ? null : fullpath.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getHaschild() {
        return haschild;
    }

    public void setHaschild(Boolean haschild) {
        this.haschild = haschild;
    }

    /**
     * Getter method for property <tt>childrens</tt>.
     * 
     * @return property value of childrens
     */
    public List<Menus> getChildrens() {
        return childrens;
    }

    /**
     * Setter method for property <tt>childrens</tt>.
     * 
     * @param childrens value to be assigned to property childrens
     */
    public void setChildrens(List<Menus> childrens) {
        this.childrens = childrens;
    }

    /**
     * Getter method for property <tt>json</tt>.
     * 
     * @return property value of json
     */
    public String getJson() {
        this.json = toJson();
        return json;
    }

    /**
     * Setter method for property <tt>json</tt>.
     * 
     * @param json value to be assigned to property json
     */
    public void setJson(String json) {
        this.json = json;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this.childrens);
    }

}