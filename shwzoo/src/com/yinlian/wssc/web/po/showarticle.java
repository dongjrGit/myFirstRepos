package com.yinlian.wssc.web.po;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

public class showarticle {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.ID
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.Title
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.ImgOne
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private String imgone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.ImgTwo
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private String imgtwo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.ImgThr
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private String imgthr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.UserID
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.NickName
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.ProID
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private Integer proid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.ProName
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private String proname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.ProPrice
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private Float proprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.ProImg
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private String proimg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.CreateTime
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private Date createtime;

    private String createtimetr;
    
    
    

	public String getCreatetimetr() {
		this.createtimetr=DateUtil.dateConvert(this.createtime);
		return createtimetr;
	}

	public void setCreatetimetr(String createtimetr) {
		this.createtimetr = createtimetr;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column showarticle.Content
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.ID
     *
     * @return the value of showarticle.ID
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.ID
     *
     * @param id the value for showarticle.ID
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.Title
     *
     * @return the value of showarticle.Title
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.Title
     *
     * @param title the value for showarticle.Title
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.ImgOne
     *
     * @return the value of showarticle.ImgOne
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public String getImgone() {
        return imgone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.ImgOne
     *
     * @param imgone the value for showarticle.ImgOne
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setImgone(String imgone) {
        this.imgone = imgone == null ? null : imgone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.ImgTwo
     *
     * @return the value of showarticle.ImgTwo
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public String getImgtwo() {
        return imgtwo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.ImgTwo
     *
     * @param imgtwo the value for showarticle.ImgTwo
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setImgtwo(String imgtwo) {
        this.imgtwo = imgtwo == null ? null : imgtwo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.ImgThr
     *
     * @return the value of showarticle.ImgThr
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public String getImgthr() {
        return imgthr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.ImgThr
     *
     * @param imgthr the value for showarticle.ImgThr
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setImgthr(String imgthr) {
        this.imgthr = imgthr == null ? null : imgthr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.UserID
     *
     * @return the value of showarticle.UserID
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.UserID
     *
     * @param userid the value for showarticle.UserID
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.NickName
     *
     * @return the value of showarticle.NickName
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.NickName
     *
     * @param nickname the value for showarticle.NickName
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.ProID
     *
     * @return the value of showarticle.ProID
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public Integer getProid() {
        return proid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.ProID
     *
     * @param proid the value for showarticle.ProID
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setProid(Integer proid) {
        this.proid = proid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.ProName
     *
     * @return the value of showarticle.ProName
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public String getProname() {
        return proname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.ProName
     *
     * @param proname the value for showarticle.ProName
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.ProPrice
     *
     * @return the value of showarticle.ProPrice
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public Float getProprice() {
        return proprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.ProPrice
     *
     * @param proprice the value for showarticle.ProPrice
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setProprice(Float proprice) {
        this.proprice = proprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.ProImg
     *
     * @return the value of showarticle.ProImg
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public String getProimg() {
        return proimg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.ProImg
     *
     * @param proimg the value for showarticle.ProImg
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setProimg(String proimg) {
        this.proimg = proimg == null ? null : proimg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.CreateTime
     *
     * @return the value of showarticle.CreateTime
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.CreateTime
     *
     * @param createtime the value for showarticle.CreateTime
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column showarticle.Content
     *
     * @return the value of showarticle.Content
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column showarticle.Content
     *
     * @param content the value for showarticle.Content
     *
     * @mbggenerated Thu Jul 07 12:08:50 CST 2016
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}