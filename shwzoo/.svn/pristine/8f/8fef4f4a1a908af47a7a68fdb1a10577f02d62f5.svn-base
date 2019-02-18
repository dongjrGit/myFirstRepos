package com.yinlian.api.app.dto;

import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.util.DateUtil;

public class CommentBaseDto {
	private Integer id;
	private String title;
	private Integer star;
	private Integer userid;
	private String username;
	private Date createtime;
    private String content; //评论内容
    private String imgUrl;//用户图片
    private List<Images> ShowImgList; //评论图片列表
    
    
    
	public List<Images> getShowImgList() {
		return ShowImgList;
	}

	public void setShowImgList(List<Images> showImgList) {
		ShowImgList = showImgList;
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
		this.title = title;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
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
		this.username = username;
	}

	public String getCreatetime() {
		return DateUtil.dateConvert(this.createtime);
	}

	public void setCreatetime(Date  createtime) {
		this.createtime = createtime;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
