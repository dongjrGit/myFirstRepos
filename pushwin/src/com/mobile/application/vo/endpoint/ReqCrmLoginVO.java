package com.mobile.application.vo.endpoint;

public class ReqCrmLoginVO {

	private String userId; //员工号
	private String token; //令牌
	private String status; //交易状态 000000:交易成功 000001:员工号为空  000002:员工号不存在  000003:其他错误 
	private String msg; //交易返回消息
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
