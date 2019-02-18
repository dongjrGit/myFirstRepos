package com.yinlian.wssc.web.dto;

import java.util.Date;

import data.ParseUtil;

public class GoodConsultDto {

	private Integer id;
	
	private String consultname;
	
	private String replyname;
	
	private String sellname;
	
	private String shopname;
	
	private String pronumstr;
	
	private String spuname;
	
	private Integer consulttype;
	
	private String consultcontent;
	
	private String replycontent;
	
	private Date consultdate;
	
	private String consultdateStr;
	
	private Date replydate;
	
	private String replydateStr;
	
	private Integer status;
	
	private String proImg;
	
	

	public String getProImg() {
		return proImg;
	}

	public void setProImg(String proImg) {
		this.proImg = proImg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConsultname() {
		return consultname;
	}

	public void setConsultname(String consultname) {
		this.consultname = consultname;
	}

	public String getReplyname() {
		return replyname;
	}

	public void setReplyname(String replyname) {
		this.replyname = replyname;
	}

	public String getSellname() {
		return sellname;
	}

	public void setSellname(String sellname) {
		this.sellname = sellname;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getPronumstr() {
		return pronumstr;
	}

	public void setPronumstr(String pronumstr) {
		this.pronumstr = pronumstr;
	}

	public String getSpuname() {
		return spuname;
	}

	public void setSpuname(String spuname) {
		this.spuname = spuname;
	}

	public Integer getConsulttype() {
		return consulttype;
	}

	public void setConsulttype(Integer consulttype) {
		this.consulttype = consulttype;
	}

	public String getConsultcontent() {
		return consultcontent;
	}

	public void setConsultcontent(String consultcontent) {
		this.consultcontent = consultcontent;
	}

	public String getReplycontent() {
		return replycontent;
	}

	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}

	public Date getConsultdate() {
		return consultdate;
	}

	public void setConsultdate(Date consultdate) {
		this.consultdate = consultdate;
	}

	public String getConsultdateStr() {
		this.consultdateStr=ParseUtil.parseDateToString(consultdate, "yyyy-MM-dd HH:mm:ss");
		return consultdateStr;
	}

	public void setConsultdateStr(String consultdateStr) {
		this.consultdateStr = consultdateStr;
	}

	public Date getReplydate() {
		return replydate;
	}

	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}

	public String getReplydateStr() {
		this.replydateStr=ParseUtil.parseDateToString(replydate, "yyyy-MM-dd HH:mm:ss");
		return replydateStr;
	}

	public void setReplydateStr(String replydateStr) {
		this.replydateStr = replydateStr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
