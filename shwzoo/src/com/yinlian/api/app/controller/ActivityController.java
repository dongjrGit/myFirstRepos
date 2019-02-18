package com.yinlian.api.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 活动列表
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api/app/activity")
public class ActivityController {

	@Autowired
	private SpikeActivityService spikeActivityService;


	/**
	 * 限时秒杀
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/findmshd", produces = "text/html;charset=UTF-8")
	public String findmshd(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			Integer ipage = StringUtilsEX.ToIntNull(page);
  			Integer isize = StringUtilsEX.ToIntNull(size);
  			if (ipage == null || ipage <= 0) {
  				ipage = 1;
  			}
  			if (isize == null || isize <= 0) {
  				isize = 10;
  			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setUsesite(ActivityUsePlatformEnum.app.getValue());
			item.setData(spikeActivityService.getmsActivityList(criteria,ipage, isize, item));
			item.setCode(0);
			return item.toJson();
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "获取秒杀活动错误：{0}", e, "/topic/findmshd");
		}
		return item.toJson();
	}

	
	/**
	 * 获取闪购列表
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/getSgList", produces = "text/html;charset=UTF-8")
	public String getSgList(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		String logpath = "activity/" + ch + "/getSgList";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSort("desc");
			criteria.setOrderByClause("b.orderby");
			criteria.setUsesite(ActivityUsePlatformEnum.app.getValue());
			PageBean pBean = spikeActivityService.getSgSpuByPage(criteria,StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
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
			item.setDesc("获取闪购活动商品列表异常：" + e.toString());
			LogHandle.error(LogType.Api,"获取闪购活动商品列表异常! 异常信息:{0}", e, logpath);
		}

		return item.toJson();
	}

	/**
	 * 获取促销活动信息
	 * 
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/getcxActivity", produces = "text/html;charset=UTF-8")
	public String getcxActivity(String ch) {
		BaseResult item = new BaseResult();
		String logpath = "activity/" + ch + "/getcxActivity";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			item.setData(spikeActivityService.getcxActivity());
			item.setCode(0);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			item.setDesc("获取促销活动信息异常：" + e.toString());
			LogHandle.error(LogType.Api,"获取促销活动信息异常! 异常信息:{0}", e, logpath);
		}
		return item.toJson();
	}

	/**
	 * 根据促销活动ID获取参与商品信息
	 * 
	 * @param ch
	 * @param actid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/getSpuList", produces = "text/html;charset=UTF-8")
	public String getSpuList(String ch, String actid, String page, String size) {
		ReusltItem item = new ReusltItem();
		String logpath = "activity/" + ch + "/getSpuList";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(actid) <= 0) {
				item.setCode(-103);
				item.setDesc("活动ID参数错误！");
				return item.toJson();
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSort("desc");
			criteria.setOrderByClause("a.orderby");
			//
			criteria.setSpikeID(StringUtilsEX.ToInt(actid));
			PageBean pBean = spikeActivityService.getSpuListByID(criteria,
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
			LogHandle.error(LogType.Api,"获取促销活动参与商品信息异常! 异常信息:{0}", e,
					logpath);
		}
		return item.toJson();
	}

	/**
	 * 经彩活动 -最新活动商品列表
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/getLatestAct", produces = "text/html;charset=UTF-8")
	public String getLatestAct(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		String logpath = "activity/" + ch + "/getLatestAct";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSort("desc");
			criteria.setOrderByClause("a.starttime");
			// 未过期
			criteria.setIsout(1);
			PageBean pBean = spikeActivityService.getExcitingByPage(criteria,
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
			LogHandle.error(LogType.Api,"获取经彩活动-最新活动商品列表异常! 异常信息:{0}", e,
					logpath);
		}
		return item.toJson();
	}

	/**
	 * 经彩活动 -往期活动商品列表
	 * 
	 * @param ch
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/getPastAct", produces = "text/html;charset=UTF-8")
	public String getPastAct(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		String logpath = "activity/" + ch + "/getPastAct";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSort("desc");
			criteria.setOrderByClause("a.starttime");
			// 已过期
			criteria.setIsout(0);
			PageBean pBean = spikeActivityService.getExcitingByPage(criteria,
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
			LogHandle.error(LogType.Api,"获取经彩活动-往期活动商品列表异常! 异常信息:{0}", e,
					logpath);
		}
		return item.toJson();
	}

	/**
	 * 根据经彩活动ID获取经彩活动详情
	 * @param ch
	 * @param id
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getDetailAct", produces = "text/html;charset=UTF-8")
	public String getDetailAct(String ch, String id, String token) {
		ReusltItem item = new ReusltItem();
		String logpath = "activity/" + ch + "/getDetailAct";
		try {
			Integer userid = 0;
			if (!StringUtilsEX.IsNullOrWhiteSpace(token)) {
				SessionUser user = SessionState.GetCurrentUser(token);
				if (user.getCode() == 0) {
					userid = user.getUserId();
				}
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-102);
				item.setDesc("经彩活动ID参数错误！");
				return item.toJson();
			}
			item.setData(spikeActivityService.getBySpikeid(
					StringUtilsEX.ToInt(id), userid));
			item.setCode(0);

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取经彩活动详情异常! 异常信息:{0}", e, logpath);
		}
		return item.toJson();
	}

	/**
	 * 获取经彩活动验证码
	 * @param ch
	 * @param id
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getActcode", produces = "text/html;charset=UTF-8")
	public String getActcode(String ch, String id, String token) {
		ReusltItem item = new ReusltItem();
		String logpath="activity/"+ch+"/getActcode";
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
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
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-103);
				item.setDesc("经彩活动ID参数错误！");
				return item.toJson();
			}
			String code=spikeActivityService.addUserSpike(StringUtilsEX.ToInt(id), user.getUserId());
			if(!StringUtilsEX.IsNullOrWhiteSpace(code)){
				item.setCode(0);
				item.setDesc("获取验证码成功");
			}else{
				item.setCode(-200);
				item.setDesc("获取验证码失败");
			}
			item.setData(code);
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api,"获取经彩活动验证码异常! 异常信息:{0}", e, logpath);
		}
		return item.toJson();

	}
}
