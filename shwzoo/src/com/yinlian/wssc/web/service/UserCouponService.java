package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.UserCoupon;

public interface UserCouponService {
	/**
	 * 查询会员对应的优惠卷已领张数
	 * @param couponId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int selectCounts(Integer couponId,Integer userId) throws Exception;
	/**
	 * 用户领取一张优惠卷
	 * @param userCoupon
	 * @return
	 * @throws Exception
	 */
	public int insert(UserCoupon userCoupon)throws Exception;
	
	public List<UserCoupon> selectByUserId(Integer userid) throws Exception;
	
}
