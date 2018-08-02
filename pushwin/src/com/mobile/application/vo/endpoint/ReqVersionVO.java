package com.mobile.application.vo.endpoint;

public class ReqVersionVO {
	String bankFlag; //银行标示（区分南京银行，昆山银行，日照银行，根据此标示返回对应银行的业务数据） 

	public String getBankFlag() {
		return bankFlag;
	}

	public void setBankFlag(String bankFlag) {
		this.bankFlag = bankFlag;
	}
	
	

}
