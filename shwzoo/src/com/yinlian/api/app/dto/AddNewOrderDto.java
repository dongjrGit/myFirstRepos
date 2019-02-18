package com.yinlian.api.app.dto;

import java.util.List;

public class AddNewOrderDto {

	/**
	 * 店铺Id
	 */
    public int shopID ;

    /**
     * 总费用
     */
    public double totalMoney ;

    /**
     * 优惠金额
     */
    public double delMoney ;

    /**
     * 运费金额
     */
    public double freightMoney ;

    /**
     * 优惠卷Id
     */
    public Integer couponID ;

    /**
     * 活动Id
     */
    public Integer activityID ;

    /**
     * 是否要发票(0不要发票 1要发票)
     */
    public Integer isInvoice ;

    /**
     * 备注
     */
    public String remark ;

    public List<AddNewDetailDto> details ;
    

	public int getShopID() {
		return shopID;
	}

	public void setShopID(int shopID) {
		this.shopID = shopID;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public double getDelMoney() {
		return delMoney;
	}

	public void setDelMoney(double delMoney) {
		this.delMoney = delMoney;
	}

	public double getFreightMoney() {
		return freightMoney;
	}

	public void setFreightMoney(double freightMoney) {
		this.freightMoney = freightMoney;
	}

	public Integer getCouponID() {
		return couponID;
	}

	public void setCouponID(Integer couponID) {
		this.couponID = couponID;
	}

	public Integer getActivityID() {
		return activityID;
	}

	public void setActivityID(Integer activityID) {
		this.activityID = activityID;
	}

	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<AddNewDetailDto> getDetails() {
		return details;
	}

	public void setDetails(List<AddNewDetailDto> details) {
		this.details = details;
	}
}
