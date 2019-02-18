package com.yinlian.wap.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yinlian.api.app.dto.OrderdetailDto;
import com.yinlian.wssc.web.util.DateUtil;

public class OrdersDto {
	//订单编号
			private Integer id ;
			
			private String code;
			//订单状态
			private Integer status ;
			//收货人
			private String consignee;
			//收货人地址
			private String address;
			private String provincename;
			private String cityname;
			private String areaname;
		    //收货人电话
		    private String telPhone;
		    //支付方式
		    private Integer payType;
		    //发货时间
		    private Date deliverDate;
		   //发货时间
		    private String _deliverDate;
		    //订单备注
		    private String remark;
		    //订单总运费
		    private Float freight;
		    //发票抬头
		    private String invoiceTitle;    
		    //发票内容
		    private String invoiceContent;
		    //配送方式
		    private Integer transportMode;
		    //数量
		    private int count;
		    //订单总金额
		    private BigDecimal price;
		    //实际支付
		    private Double actualPay;
		    //优惠券抵扣
		    private Double CouponPay;
		    //积分抵扣
		    private Double pointsPay;
		    //经彩豆抵扣
		    private Double pulsePay;
		    //订单创建时间
		    private Date orderDate;
		    //订单创建时间
		    private String _orderDate;
		    //店铺名称
		    private String shopname;
		    private String shopimgurl;
		    private Integer shopid;
		    private Integer sellerid;
		    private Date payDate;
			
		    public Date getPayDate() {
				return payDate;
			}
			public void setPayDate(Date payDate) {
				this.payDate = payDate;
			}
			public Date getOrderDate() {
				return orderDate;
			}
			public void setOrderDate(Date orderDate) {
				this.orderDate = orderDate;
			}
			public String getCode() {
				return code;
			}
			public void setCode(String code) {
				this.code = code;
			}
			public String getProvincename() {
				return provincename;
			}
			public void setProvincename(String provincename) {
				this.provincename = provincename;
			}
			public String getCityname() {
				return cityname;
			}
			public void setCityname(String cityname) {
				this.cityname = cityname;
			}
			public String getAreaname() {
				return areaname;
			}
			public void setAreaname(String areaname) {
				this.areaname = areaname;
			}
			public Integer getTransportMode() {
				return transportMode;
			}
			public void setTransportMode(Integer transportMode) {
				this.transportMode = transportMode;
			}
			
			private List<OrderdetailDto> children;
		    
			public List<OrderdetailDto> getChildren() {
				return children;
			}
			public void setChildren(List<OrderdetailDto> children) {
				this.children = children;
			}
			public Integer getId() {
				return id;
			}
			public void setId(Integer id) {
				this.id = id;
			}
			public Integer getStatus() {
				return status;
			}
			public void setStatus(Integer status) {
				this.status = status;
			}
			public String getConsignee() {
				return consignee;
			}
			public void setConsignee(String consignee) {
				this.consignee = consignee;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public String getTelPhone() {
				return telPhone;
			}
			public void setTelPhone(String telPhone) {
				this.telPhone = telPhone;
			}
			public Integer getPayType() {
				return payType;
			}
			public void setPayType(Integer payType) {
				this.payType = payType;
			}
			public Date getDeliverDate() {
				return deliverDate;
			}
			public void setDeliverDate(Date deliverDate) {
				this.deliverDate = deliverDate;
			}
			public String getRemark() {
				return remark;
			}
			public void setRemark(String remark) {
				this.remark = remark;
			}
			public Float getFreight() {
				return freight;
			}
			public void setFreight(Float freight) {
				this.freight = freight;
			}
			
			public String getInvoiceTitle() {
				return invoiceTitle;
			}
			public void setInvoiceTitle(String invoiceTitle) {
				this.invoiceTitle = invoiceTitle;
			}
			public String getInvoiceContent() {
				return invoiceContent;
			}
			public void setInvoiceContent(String invoiceContent) {
				this.invoiceContent = invoiceContent;
			}
			
			public int getCount() {
				return count;
			}
			public void setCount(int count) {
				this.count = count;
			}
			public BigDecimal getPrice() {
				return price;
			}
			public void setPrice(BigDecimal price) {
				this.price = price;
			}
			public Double getActualPay() {
				return actualPay;
			}
			public void setActualPay(Double actualPay) {
				this.actualPay = actualPay;
			}
			public Double getCouponPay() {
				return CouponPay;
			}
			public void setCouponPay(Double couponPay) {
				CouponPay = couponPay;
			}
			public Double getPointsPay() {
				return pointsPay;
			}
			public void setPointsPay(Double pointsPay) {
				this.pointsPay = pointsPay;
			}
			public Double getPulsePay() {
				return pulsePay;
			}
			public void setPulsePay(Double pulsePay) {
				this.pulsePay = pulsePay;
			}
			
			public String get_deliverDate() {
				this._deliverDate = DateUtil.datePattren(deliverDate);
				return _deliverDate;
			}
			public void set_deliverDate(String _deliverDate) {
				this._deliverDate = _deliverDate;
			}
			public String get_orderDate() {
				this._orderDate = DateUtil.datePattren(orderDate);
				return _orderDate;
			}
			public void set_orderDate(String _orderDate) {
				this._orderDate = _orderDate;
			}
			public String getShopname() {
				return shopname;
			}
			public void setShopname(String shopname) {
				this.shopname = shopname;
			}
			public String getShopimgurl() {
				return shopimgurl;
			}
			public void setShopimgurl(String shopimgurl) {
				this.shopimgurl = shopimgurl;
			}
			public Integer getShopid()
		    {
		    	return shopid;
		    }
		    public void setShopid(Integer shopid)
		    {
		    	this.shopid = shopid;
		    }
		    public Integer getSellerid() {
				return sellerid;
			}

			public void setSellerid(Integer sellerid) {
				this.sellerid = sellerid;
			}
			
}
