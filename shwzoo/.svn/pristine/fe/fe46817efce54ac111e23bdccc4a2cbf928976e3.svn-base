package com.yinlian.api.wap.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.SearchCouponDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/coupon")
public class WapCouponController {
	@Autowired
	private CouponService couponService;
	

	@Autowired
	private OperaterecordsService operaterecordsService;

	/**
	 * 领取优惠券
	 * 
	 * @param token
	 * @param couponid  
	 * @return
	 */
	@RequestMapping(value = "/takeCoupon", produces = "text/html;charset=UTF-8")
	public @ResponseBody String takeCoupon(String couponid, String ch,
			HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {

			if (StringUtilsEX.ToInt(couponid) <= 0) {
				item.setCode(-102);
				item.setDesc("优惠券ID参数错误！");
				return item.toJson();
			}
			SessionUser user = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登录！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-103);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}

			// 领取优惠券
			if (couponService.addUserCoupon(StringUtilsEX.ToInt(couponid),
					user.getUserId(), user.getLoginName(), item) > 0) {
				// userId ,loginname, item) > 0) {
				item.setCode(0);
				item.setDesc("领取优惠券成功");
				LogHandle.info(LogType.Coupon, MessageFormat.format(
						"领取优惠券成功! 优惠券id：{0},用户ID:{1}", couponid,
						user.getUserId()), "CouponInfo/takeCoupon");
				ExecutorService executorService=Executors.newCachedThreadPool();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							SessionUser user=SessionState.GetCurrentUser();
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.web前台.getValue(), user.getId(), user.getLoginName(), "coupon.html", "/api/wap/coupon/takeCoupon", "领取优惠券");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords,"领取优惠券 异常信息:",
    								e, "/api/wap/coupon/takeCoupon");
						}
					}
				});
			} else {
				if (item.getCode() == 0)
					item.setCode(-200);
				LogHandle.info(LogType.Coupon, MessageFormat.format(
						"领取优惠券失败! 优惠券id：{0},用户ID:{1}", couponid,
						user.getUserId()), "CouponInfo/takeCoupon");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Coupon, "领取优惠券异常，信息：" + e,
					"CouponInfo/takeCoupon");
		}
		return item.toJson();
	}
	
	/**
	 * 获取用户优惠券
	 * 
	 * @param token
	 * @param str
	 * @return
	 */
	@RequestMapping(value = "/getOrderCoupon", produces = "text/html;charset=UTF-8")
	public String getOrderCoupon(String ch,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			String personstr = request.getParameter("paramstr");
			// String personstr =str;
			
			SessionUser user = SessionState.GetCurrentUser();
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("用户未登录");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-102);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			int userid = user.getUserId();
			// int userid=1;
			if (!StringUtilsEX.IsNullOrWhiteSpace(personstr)) {
				JSONArray json = JSONArray.fromObject(personstr);
				
				@SuppressWarnings("unchecked")
				List<SearchCouponDto> searchCouponDtos = (List<SearchCouponDto>) JSONArray.toCollection(json, SearchCouponDto.class);
				item.setData(couponService.getOrderCoupon(searchCouponDtos, userid,ActivityUsePlatformEnum.wap.getValue()));
			} else {
				return item.toJson();
			}

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Coupon, "获取用户优惠券异常，信息：" + e, "CouponInfo/getCoupons");
		}
		return item.toJson();
	}
}
