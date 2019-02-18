package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.UserCouponMapper;
import com.yinlian.wssc.web.po.UserCoupon;
import com.yinlian.wssc.web.service.UserCouponService;
@Component("userCouponService")
public class UserCouponServiceImpl implements UserCouponService{

	private static final Logger  logger = LoggerFactory.getLogger(UserCouponServiceImpl.class);
	
	@Autowired
	private      UserCouponMapper     userCouponMapper;       
	
	@Override
	public int selectCounts(Integer couponId, Integer userId) throws Exception {
		
		List<UserCoupon>  list=new  ArrayList<UserCoupon>();
		list=userCouponMapper.selectCount(couponId, userId);
		if(list!=null){
			return list.size();
		}
		return 0;
	}

	@Override
	public int insert(UserCoupon userCoupon) throws Exception {

		return userCouponMapper.insert(userCoupon);
	}

	@Override
	public List<UserCoupon> selectByUserId(Integer userid) throws Exception {
		
		return userCouponMapper.selectByUserId(userid);
	}

}
