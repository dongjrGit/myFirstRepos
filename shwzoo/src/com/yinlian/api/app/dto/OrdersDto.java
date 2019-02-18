package com.yinlian.api.app.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yinlian.wssc.web.util.DateUtil;

public class OrdersDto {
	//订单编号
	private Integer id ;
	
	private String code;
	 private String groupcode;
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
    //发票类型
    private Integer invoiceType;
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
    //支付时间
    private Date payDate; 
    //订单创建时间
    private String _orderDate;
    //订单取消或退款原因
    private String reason;
    private String cancelimg;
    private String cancelreason;
    private String shopname;
    private String shopimgurl;
    private String logisticname;
    private String logisiticcode;
    //优惠金额
    private Double discount;
    private boolean isremind;
    
    private String qrcode;
    
    private String name;
    private String phone;
    private String idcard;
    
    private String longitude;
    private String latitude;
    
    private Integer shoptype;
    
    public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public Integer getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getLogisticname() {
		return logisticname;
	}
	public void setLogisticname(String logisticname) {
		this.logisticname = logisticname;
	}
	public String getLogisiticcode() {
		return logisiticcode;
	}
	public void setLogisiticcode(String logisiticcode) {
		this.logisiticcode = logisiticcode;
	}
	public String getShopimgurl() {
		return shopimgurl;
	}
	public void setShopimgurl(String shopimgurl) {
		this.shopimgurl = shopimgurl;
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
    public String getShopname(){
    	return shopname;
    }
    public void setShopname(String shopname)
    {
    	this.shopname = shopname;
    }
    public String getGroupcode() {
		return groupcode;
	}
	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public boolean isIsremind() {
		return isremind;
	}
	public void setIsremind(boolean isremind) {
		this.isremind = isremind;
	}
	public String getCancelimg() {
		return cancelimg;
	}
	public void setCancelimg(String cancelimg) {
		this.cancelimg = cancelimg;
	}
	public String getCancelreason() {
		return cancelreason;
	}
	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
	}
	public Integer getShoptype() {
		return shoptype;
	}
	public void setShoptype(Integer shoptype) {
		this.shoptype = shoptype;
	}
	 
	
	
}   
