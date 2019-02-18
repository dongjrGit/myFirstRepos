package com.yinlian.api.app.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商户api订单列表数据格式
 * @author Administrator
 *
 */
public class Api_GroupOrderDto {

	public Api_GroupOrderDto()
    {
		orderlist = new ArrayList<Api_ListOrderDto>();
    }
	private String gcode;       //订单组编号
	
	private Date orderdate;     //下单时间
	
	private Integer status;     //订单状态

	private List<Api_ListOrderDto> orderlist;  //订单集合（根据供应商分组）
	
	private List<Api_OrderDetaiBaselDto> detaislist;

	public List<Api_OrderDetaiBaselDto> getDetaislist() {
		return detaislist;
	}

	public void setDetaislist(List<Api_OrderDetaiBaselDto> detaislist) {
		this.detaislist = detaislist;
	}

	public String getGcode() {
		return gcode;
	}

	public void setGcode(String gcode) {
		this.gcode = gcode;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Api_ListOrderDto> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<Api_ListOrderDto> orderlist) {
		this.orderlist = orderlist;
	}

}
