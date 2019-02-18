package com.yinlian.api.app.dto;

public class GroupOrderStateDto {
	
	private Integer dfk; //待付款
	
	private Integer dsh; //待消费
	
	private Integer dpj; //待评价
	
	private Integer tk; //退款

	public Integer getDfk() {
		return dfk;
	}

	public void setDfk(Integer dfk) {
		this.dfk = dfk;
	}

	public Integer getDsh() {
		return dsh;
	}

	public void setDsh(Integer dsh) {
		this.dsh = dsh;
	}

	public Integer getDpj() {
		return dpj;
	}

	public void setDpj(Integer dpj) {
		this.dpj = dpj;
	}

	public Integer getTk() {
		return tk;
	}

	public void setTk(Integer tk) {
		this.tk = tk;
	}
	
}
