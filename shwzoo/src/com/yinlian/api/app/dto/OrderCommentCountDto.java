package com.yinlian.api.app.dto;

public class OrderCommentCountDto {


	private Integer pcypj; //已评价
	
	private Integer pcdimage; //待追加图片
	
	private Integer pcdpj; //待评价
	
	
	public Integer getPcypj() {
		return pcypj;
	}

	public void setPcypj(Integer pcypj) {
		this.pcypj = pcypj;
	}

	public Integer getPcdimage() {
		return pcdimage;
	}

	public void setDimage(Integer pcdimage) {
		this.pcdimage = pcdimage;
	}

	public Integer getPcdpj() {
		return pcdpj;
	}

	public void setPcdpj(Integer pcdpj) {
		this.pcdpj = pcdpj;
	}

	
}
