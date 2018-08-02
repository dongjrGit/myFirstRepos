package com.music.util;

public class CommonVO {
	// 执行是否成功  1成功   0失败
	private String isSuccess;
	// 返回信息
	private String msg;
	// 返回数据
	private Object data;
	// 返回总记录条数
	private String total;
	
	public CommonVO() {
		super();
	}

	public CommonVO(String isSuccess, String msg) {
		super();
		this.isSuccess = isSuccess;
		this.msg = msg;
	}

	public CommonVO(String isSuccess, String msg, Object data) {
		super();
		this.isSuccess = isSuccess;
		this.msg = msg;
		this.data = data;
	}

	public CommonVO(String isSuccess, String msg, Object data, String total) {
		super();
		this.isSuccess = isSuccess;
		this.msg = msg;
		this.data = data;
		this.total = total;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CommonVO [isSuccess=" + isSuccess + ", msg=" + msg + ", data=" + data + ", total=" + total + "]";
	}
	
}
