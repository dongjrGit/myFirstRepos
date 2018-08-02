package com.mobile.application.vo.endpoint;

public class ReqRedisVo {
	private String orgFullname; //银行行名
	private String bankCode; //银行行号

	public String getOrgFullname() {
		return orgFullname;
	}

	public void setOrgFullname(String orgFullname) {
		this.orgFullname = orgFullname;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

}
