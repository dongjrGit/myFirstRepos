package com.yinlian.wssc.web.po;

import java.util.Date;

public class Searchkey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column searchkey.ID
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column searchkey.KeyWords
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    private String keywords;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column searchkey.OrderBy
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    private Integer orderby;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column searchkey.KeyType
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    private Integer keytype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column searchkey.CreateUserID
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    private Integer createuserid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column searchkey.CreateTime
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column searchkey.UseSites
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    private String usesites;
    
    

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column searchkey.ID
     *
     * @return the value of searchkey.ID
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column searchkey.ID
     *
     * @param id the value for searchkey.ID
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column searchkey.KeyWords
     *
     * @return the value of searchkey.KeyWords
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column searchkey.KeyWords
     *
     * @param keywords the value for searchkey.KeyWords
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column searchkey.OrderBy
     *
     * @return the value of searchkey.OrderBy
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public Integer getOrderby() {
        return orderby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column searchkey.OrderBy
     *
     * @param orderby the value for searchkey.OrderBy
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column searchkey.KeyType
     *
     * @return the value of searchkey.KeyType
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public Integer getKeytype() {
        return keytype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column searchkey.KeyType
     *
     * @param keytype the value for searchkey.KeyType
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public void setKeytype(Integer keytype) {
        this.keytype = keytype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column searchkey.CreateUserID
     *
     * @return the value of searchkey.CreateUserID
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public Integer getCreateuserid() {
        return createuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column searchkey.CreateUserID
     *
     * @param createuserid the value for searchkey.CreateUserID
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column searchkey.CreateTime
     *
     * @return the value of searchkey.CreateTime
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column searchkey.CreateTime
     *
     * @param createtime the value for searchkey.CreateTime
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column searchkey.UseSites
     *
     * @return the value of searchkey.UseSites
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public String getUsesites() {
        return usesites;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column searchkey.UseSites
     *
     * @param usesites the value for searchkey.UseSites
     *
     * @mbggenerated Wed Jun 22 17:27:23 CST 2016
     */
    public void setUsesites(String usesites) {
        this.usesites = usesites == null ? null : usesites.trim();
    }


}