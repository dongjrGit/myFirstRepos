package com.yinlian.api.app.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.ChannelTypeEnum;
import com.yinlian.Enums.UserStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.UserDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.CriteriaCoupon;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SmsUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 第三方接口
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/app/thirdparty")
public class ThirdPartyController {
	
	@Autowired
	private CouponService couponService;
	@Autowired
	private UserService userService;
	
	/**
	 * 获取可领取的优惠券列表
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getCouponList", produces = "text/html;charset=UTF-8")
	public String getCouponList(String ch, String page,
			String size, HttpServletResponse response) {
		ReusltItem item = new ReusltItem();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-103);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			CriteriaCoupon criteria = new CriteriaCoupon();
			criteria.setSort("desc");
			criteria.setOrderByClause("CreateTime");
			criteria.setUseplatform(ActivityUsePlatformEnum.app.getValue());
			PageBean pBean = couponService.getUserCouponList(criteria,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Coupon, "获取优惠券列表异常，信息：" , e,
					"/thirdparty/getCouponList");
		}
		return item.toJson();
	}

	/**
	 * 领取优惠券
	 * @param phone 手机号
	 * @param ch 通道 
	 * @param couponid 优惠卷ID
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/takeCoupon", produces = "text/html;charset=UTF-8")
	public String takeCoupon(String phone, String ch, String couponid,HttpServletResponse response) {
		BaseResult item = new BaseResult();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(phone)) {
				item.setCode(-101);
				item.setDesc("手机号(phone)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(couponid) <= 0) {
				item.setCode(-102);
				item.setDesc("优惠券ID(couponId)参数错误！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-103);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			//手机号获取第三方用户
			List<UserDto> userslist=userService.queryByPhone(phone,ChannelTypeEnum.thirdparty.getValue());
			UserDto user=null;
			//判断是否存在该手机用户 存在赋值用户信息 不存在创建后赋值
			if (userslist.size()>0) {
				user=userslist.get(0);
			}else {
				int pwd=(int)((Math.random()*9+1)*100000);
				Users users=new Users();
				users.setMobile(phone);
				users.setUsername("third_"+phone);
				users.setIsmobilecheck(false);
				users.setIsemailcheck(false);
				users.setStatus(UserStatusEnum.正常.getValue());
				users.setPoints(0);
				users.setTotalpoints(0);
				users.setPassword(pwd+"");
				//添加用户信息
				userService.insert(users, phone, users.getUsername(), pwd+"", "",ChannelTypeEnum.thirdparty.getValue());
				user=userService.queryByPhone(phone,ChannelTypeEnum.thirdparty.getValue()).get(0);
				SmsUtil.sendMessage(phone, "【中绿生活】您好,您在中绿的用户名为:"+phone+",登录密码为:"+pwd+"");
			}
			
			// int userId = 1;
			// 领取优惠券
			if (couponService.addUserCoupon(StringUtilsEX.ToInt(couponid),
					user.getId(), user.getLoginName(), item) > 0) {
				item.setCode(0);
				item.setDesc("领取优惠券成功");
				LogHandle.info(LogType.Coupon, MessageFormat.format(
						"领取优惠券成功! 优惠券id：{0},用户ID:{1}", couponid,
						user.getId()), "CouponInfo/takeCoupon");
			} else {
				if (item.getCode() == 0)
					item.setCode(-200);
				LogHandle.info(LogType.Coupon, MessageFormat.format(
						"领取优惠券失败! 优惠券id：{0},用户ID:{1}", couponid,
						user.getId()), "CouponInfo/takeCoupon");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			e.printStackTrace();
			LogHandle.error(LogType.Coupon, "领取优惠券异常，信息：" + e,
					"CouponInfo/takeCoupon");
		}
		return item.toJson();
	}
}
