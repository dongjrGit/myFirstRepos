package com.yinlian.wssc.web.dto;

import java.math.BigDecimal;

/**
 * 对账订单信息
 * @author Administrator
 *
 */
public class OrderBillsDto {

	/**
	 * 订单ID
	 */
	private Integer id;
	
	/**
	 * 订单金额
	 */
	private BigDecimal price;
	
	/**
	 * 订单总运费
	 */
	private BigDecimal freight;
	
	/**
	 * 优惠金额
	 */
	private BigDecimal discount;
	
	/**
	 * 实际支付
	 */
	private BigDecimal actualPay;
		
	/**
	 * 优惠卷类型
	 */
	private Integer couponUseType;
	
	/**
	 * 优惠卷面值
	 */
	private BigDecimal faceValue;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getActualPay() {
		return actualPay;
	}

	public void setActualPay(BigDecimal actualPay) {
		this.actualPay = actualPay;
	}

	public Integer getCouponUseType() {
		return couponUseType;
	}

	public void setCouponUseType(Integer couponUseType) {
		this.couponUseType = couponUseType;
	}

	public BigDecimal getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
