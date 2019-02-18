package com.yinlian.wssc.web.dto;

import java.util.Date;

import com.yinlian.wssc.web.util.DateUtil;

/**
 * 文章管理返回的属性
 * ArticlesDto.java
 * @author Administrator
 * @version $Id: ArticlesDto.java, v 0.1 2016年5月18日 下午3:27:27 Administrator Exp $
 */
public class ArticlesDto {
	 private Integer id;

	    private String title;

	    private String digest;

	    private String bytitle;

	    private Integer classfyid;

	    private String classfy;

	    private Date sendtime;
	    
	    private String _sendtime;

	    private Integer clicknum;

	    private Integer sendid;

	    private String sendpeople;

	    private Integer status;

	    private Boolean istop;

	    private Boolean ishead;

	    private Integer type;

	    private String imgurl;

	    private String artcontent;
	    
	    private Integer partid;
	    
	    private String artcontenthtml;
	    
	    private String webSet;
	    
	    private Integer supId;
	    
	    private String supName;
	    
	    private String navclassfyName;
	    
	    public String get_sendtime() {
	    	this._sendtime = DateUtil.datePattren(sendtime);
	    	return _sendtime;
		}

		public void set_sendtime(String _sendtime) {
			this._sendtime = _sendtime;
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
	        this.title = title == null ? null : title.trim();
	    }

	    public String getDigest() {
	        return digest;
	    }

	    public void setDigest(String digest) {
	        this.digest = digest == null ? null : digest.trim();
	    }

	    public String getBytitle() {
	        return bytitle;
	    }

	    public void setBytitle(String bytitle) {
	        this.bytitle = bytitle == null ? null : bytitle.trim();
	    }

	    public Integer getClassfyid() {
	        return classfyid;
	    }

	    public void setClassfyid(Integer classfyid) {
	        this.classfyid = classfyid;
	    }

	    public String getClassfy() {
	        return classfy;
	    }

	    public void setClassfy(String classfy) {
	        this.classfy = classfy == null ? null : classfy.trim();
	    }

	    public Date getSendtime() {
	        return sendtime;
	    }

	    public void setSendtime(Date sendtime) {
	        this.sendtime = sendtime;
	    }

	    public Integer getClicknum() {
	        return clicknum;
	    }

	    public void setClicknum(Integer clicknum) {
	        this.clicknum = clicknum;
	    }

	    public Integer getSendid() {
	        return sendid;
	    }

	    public void setSendid(Integer sendid) {
	        this.sendid = sendid;
	    }

	    public String getSendpeople() {
	        return sendpeople;
	    }

	    public void setSendpeople(String sendpeople) {
	        this.sendpeople = sendpeople == null ? null : sendpeople.trim();
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }

	    public Boolean getIstop() {
	        return istop;
	    }

	    public void setIstop(Boolean istop) {
	        this.istop = istop;
	    }

	    public Boolean getIshead() {
	        return ishead;
	    }

	    public void setIshead(Boolean ishead) {
	        this.ishead = ishead;
	    }

	    public Integer getType() {
	        return type;
	    }

	    public void setType(Integer type) {
	        this.type = type;
	    }

	    public String getImgurl() {
	        return imgurl;
	    }

	    public void setImgurl(String imgurl) {
	        this.imgurl = imgurl == null ? null : imgurl.trim();
	    }

	    public String getArtcontent() {
	    	 if (artcontent != null && artcontent.indexOf("\"") > 0) {
	         	this.artcontent= artcontent.replace("<","&lt;");
	         	this.artcontent= artcontent.replace(">","&gt;");
	             this.artcontent = artcontent.replace("\"", "'");
	         }
	        return artcontent;
	    }

	    public void setArtcontent(String artcontent) {
	        this.artcontent = artcontent == null ? null : artcontent.trim();
	    }

		public Integer getPartid() {
			return partid;
		}

		public void setPartid(Integer partid) {
			this.partid = partid;
		}

		public String getArtcontenthtml() {
			return this.artcontent;
		}

		public void setArtcontenthtml(String artcontenthtml) {
			this.artcontenthtml = artcontenthtml;
		}

		public String getWebSet() {
			return webSet;
		}

		public void setWebSet(String webSet) {
			this.webSet = webSet;
		}

		public Integer getSupId() {
			return supId;
		}

		public void setSupId(Integer supId) {
			this.supId = supId;
		}

		public String getSupName() {
			return supName;
		}

		public void setSupName(String supName) {
			this.supName = supName;
		}

		public String getNavclassfyName() {
			return navclassfyName;
		}

		public void setNavclassfyName(String navclassfyName) {
			this.navclassfyName = navclassfyName;
		}
}
