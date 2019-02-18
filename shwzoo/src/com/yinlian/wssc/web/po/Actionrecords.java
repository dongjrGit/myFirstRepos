package com.yinlian.wssc.web.po;

import java.util.Date;

public class Actionrecords {
    private Integer id;

    private String keyname;

    private String tablename;

    private Integer userid;

    private String username;

    private String ip;

    private String frm;

    private String objtag;

    private Integer step;

    private Integer type;

    private String browser;

    private Date createtime;

    private Date recordtime;

    private String sysinfo;

    private String website;

    private String device;

    private String version;

    private String relationid;

    private String province;

    private String city;

    private String country;

    private String mac;

    private String oncetag;

    private Boolean isnew;

    private Integer iscount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname == null ? null : keyname.trim();
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getFrm() {
        return frm;
    }

    public void setFrm(String frm) {
        this.frm = frm == null ? null : frm.trim();
    }

    public String getObjtag() {
        return objtag;
    }

    public void setObjtag(String objtag) {
        this.objtag = objtag == null ? null : objtag.trim();
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public String getSysinfo() {
        return sysinfo;
    }

    public void setSysinfo(String sysinfo) {
        this.sysinfo = sysinfo == null ? null : sysinfo.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device == null ? null : device.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getRelationid() {
        return relationid;
    }

    public void setRelationid(String relationid) {
        this.relationid = relationid == null ? null : relationid.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getOncetag() {
        return oncetag;
    }

    public void setOncetag(String oncetag) {
        this.oncetag = oncetag == null ? null : oncetag.trim();
    }

    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    public Integer getIscount() {
        return iscount;
    }

    public void setIscount(Integer iscount) {
        this.iscount = iscount;
    }
}