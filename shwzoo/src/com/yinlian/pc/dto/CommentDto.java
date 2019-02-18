package com.yinlian.pc.dto;

import java.util.List;

import com.yinlian.wssc.web.po.Images;

public class CommentDto {
	    private Integer id;

	    private Integer orderdetailid;

	    private Integer star;

	    private String content;
 
	    private Integer showname;

	    private Integer starDepict;

	    private Integer starService;

	    private Integer starSpeed;

	    private Integer fkid;
	    
	    private List<Images> showImgList;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getOrderdetailid() {
			return orderdetailid;
		}

		public void setOrderdetailid(Integer orderdetailid) {
			this.orderdetailid = orderdetailid;
		}

		public Integer getStar() {
			return star;
		}

		public void setStar(Integer star) {
			this.star = star;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Integer getShowname() {
			return showname;
		}

		public void setShowname(Integer showname) {
			this.showname = showname;
		}

		public Integer getStarDepict() {
			return starDepict;
		}

		public void setStarDepict(Integer starDepict) {
			this.starDepict = starDepict;
		}

		public Integer getStarService() {
			return starService;
		}

		public void setStarService(Integer starService) {
			this.starService = starService;
		}

		public Integer getStarSpeed() {
			return starSpeed;
		}

		public void setStarSpeed(Integer starSpeed) {
			this.starSpeed = starSpeed;
		}

		public Integer getFkid() {
			return fkid;
		}

		public void setFkid(Integer fkid) {
			this.fkid = fkid;
		}

		public List<Images> getShowImgList() {
			return showImgList;
		}

		public void setShowImgList(List<Images> showImgList) {
			this.showImgList = showImgList;
		}

}
