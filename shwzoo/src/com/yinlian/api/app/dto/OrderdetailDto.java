package com.yinlian.api.app.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderdetailDto {
	private Integer id;
	
	private String productnum;

    private String productimg;

    private String productname;

    private BigDecimal productprice;

    private Integer productcount;
    private Integer productid;
    private String ordercode;
    private Integer orderid;
    private Integer detailIsComment;
    private Integer spuid;
    private Integer status;
    
    private Date usetime;
    
    public Date getUsetime() {
		return usetime;
	}
	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}
	public Integer getDetailIsComment() {
		return detailIsComment;
	}
	public void setDetailIsComment(Integer detailIsComment) {
		this.detailIsComment = detailIsComment;
	}
	public Integer getOrderid(){
    	return orderid;
    }
    public void setOrderid(Integer orderid){
    	this.orderid=orderid;
    }
    public String getOrdercode() {
		return ordercode;
	}

	public Integer getSpuid() {
		return spuid;
	}
	public void setSpuid(Integer spuid) {
		this.spuid = spuid;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
 
    public Integer getId(){
    	return id;
    }
    public void setId(Integer Id){
    	this.id=Id;
    }
    public Integer getProductid(){
    	return productid;
    }
    public void setProductid(Integer productid){
    	this.productid=productid;
    }
	public String getProductnum() {
		return productnum;
	}

	public void setProductnum(String productnum) {
		this.productnum = productnum;
	}

	public String getProductimg() {
		return productimg;
	}

	public void setProductimg(String productimg) {
		this.productimg = productimg;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public BigDecimal getProductprice() {
		return productprice;
	}

	public void setProductprice(BigDecimal productprice) {
		this.productprice = productprice;
	}

	public Integer getProductcount() {
		return productcount;
	}

	public void setProductcount(Integer productcount) {
		this.productcount = productcount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
}
