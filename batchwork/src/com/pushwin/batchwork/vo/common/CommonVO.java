package com.pushwin.batchwork.vo.common;

public class CommonVO {
	private boolean success;

	private String msg;

	private Object rows;
	
	private Object data;

	private String total;

	public CommonVO() {
	}

	public CommonVO(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public CommonVO(boolean success, String msg, Object rows, String total) {
		this.success = success;
		this.msg = msg;
		this.rows = rows;
		this.total = total;
	}
	
	public CommonVO(boolean success, Object data, String total) {
		this.success = success;
		this.data = data;
		this.total = total;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
