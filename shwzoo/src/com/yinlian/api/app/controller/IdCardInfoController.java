package com.yinlian.api.app.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.search.IdCardCriteria;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.service.IdCardinfoService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.IdCardVerifyUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/app/idcard")
public class IdCardInfoController {

	@Autowired
	private IdCardinfoService idCardinfoService;

	/**
	 * 添加身份信息
	 * 
	 * @param token
	 * @param name
	 * @param phone
	 * @param idcard
	 * @param ch
	 * @return
	 */
	@RequestMapping(value = "/addidcard", produces = "text/html;charset=UTF-8")
	public @ResponseBody String addcard(String token, String name, String ch,
			String phone, String idcard) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(token)) {
				item.setCode(-101);
				item.setDesc("token不能为空");
				return item.toJson();
			}
			SessionUser user = SessionState.GetCurrentUser(token);
			if (user.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-102);
				item.setDesc("姓名不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(phone)) {
				item.setCode(-103);
				item.setDesc("手机号不能为空！");
				return item.toJson();
			}
			if (!phone.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(idcard)) {
				item.setCode(-119);
				item.setDesc("身份证不能为空");
				return item.toJson();
			}
			if (!idcard.matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")) {
				item.setCode(-120);
				item.setDesc("身份证格式错误！");
				return item.toJson();
			}
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			int userid = user.getUserId();
			Idcardinfo idcardinfo = new Idcardinfo();
			idcardinfo.setUserid(userid);
			idcardinfo.setName(name);
			idcardinfo.setPhone(phone);
			idcardinfo.setCard(idcard);
			idcardinfo.setCreatetime(new Date());
			idcardinfo.setIsdel(0);
			int temp = idCardinfoService.insert(idcardinfo);
			if (temp == 0) {
				item.setCode(-200);
				item.setDesc("添加身份信息失败");
			} else {
				item.setCode(0);
				item.setDesc("添加身份信息成功");
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "添加身份信息异常! 异常信息:{0}", e,
					"/idcard/addidcard");
		}
		return item.toJson();
	}

	/**
	 * 查询身份信息
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */

	@RequestMapping(value = "/findID", produces = "text/html;charset=UTF-8")
	public @ResponseBody String totalPoints(String token, String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			} else {
				int userId = sessionUser.getUserId();
				List<Idcardinfo> idcardinfos = idCardinfoService
						.quertByUserId(userId);
				item.setCode(0);
				item.setDesc("查询身份信息成功");
				item.setData(idcardinfos);
			}
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "查询身份信息异常! 异常信息:{0}", e,
					"idcard/findID");
		}

		return item.toJson();
	}

	/**
	 * 购买门票时判断身份信息
	 * 
	 * @param token
	 * @param ch
	 * @param idcard
	 * @return
	 */
	@RequestMapping(value = "/checkcardinfo", produces = "text/html;charset=UTF-8")
	public @ResponseBody String checkCardInfo(String token, String ch,
			String idcard) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(idcard)) {
				item.setCode(-102);
				item.setDesc("身份证号参数错误");
				return item.toJson();
			}
			IdCardCriteria criteria = new IdCardCriteria();
			criteria.setUserid(sessionUser.getUserId());
			criteria.setIdcard(idcard);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			// 获取当前月第一天：
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
			criteria.setBegintime(format.parse(format.format(c.getTime())));
			// 获取当前月最后一天
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.DAY_OF_MONTH,
					ca.getActualMaximum(Calendar.DAY_OF_MONTH));
			criteria.setEndtime(format.parse(format.format(ca.getTime())));
			Integer count = idCardinfoService.checkcardinfo(criteria);
			item.setCode(0);
			item.setData(count);
		} catch (Exception e) {
			e.printStackTrace();
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "购买门票时判断身份信息异常! 异常信息:{0}", e,
					"idcard/checkcardinfo");
		}
		return item.toJson();
	}

	/**
	 * 删除身份信息
	 * 
	 * @param token
	 * @param ch
	 * @param idcard
	 * @return
	 */
	@RequestMapping(value = "/delCardInfo", produces = "text/html;charset=UTF-8")
	public @ResponseBody String delCardInfo(String token, String ch,
			String idcard) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			SessionUser sessionUser = new SessionUser();
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setCode(-401);
				item.setDesc("请先登陆！");
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(idcard)) {
				item.setCode(-102);
				item.setDesc("身份证号参数错误");
				return item.toJson();
			}
			Idcardinfo idcardinfo = new Idcardinfo();
			idcardinfo.setIsdel(1);
			idcardinfo.setDeltime(new Date());
			idcardinfo.setUserid(sessionUser.getUserId());
			idcardinfo.setCard(idcard);
			if (idCardinfoService.delCardInfo(idcardinfo) > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
			} else {
				item.setCode(-200);
				item.setDesc("删除失败");
				return item.toJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误!");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "删除身份信息异常! 异常信息:{0}", e,
					"idcard/delCardInfo");
		}
		return item.toJson();
	}

	/**
	 * 校验身份信息参数
	 * 
	 * @param name
	 * @param phone
	 * @param idcard
	 * @return
	 */
	@RequestMapping(value = "/checklegal", produces = "text/html;charset=UTF-8")
	public @ResponseBody String checkLegal(String name, String phone,
			String idcard) {
		BaseResult item = new BaseResult();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-102);
				item.setDesc("姓名不能为空！");
				return item.toJson();
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(phone)) {
				item.setCode(-103);
				item.setDesc("手机号不能为空！");
				return item.toJson();
			}
			if (!phone.matches("1\\d{10}")) {
				item.setCode(-118);
				item.setDesc("手机号格式错误！");
				return item.toJson();
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(idcard)) {
				if (idcard.matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")) {
					if (!IdCardVerifyUtil.IdCardComplex(idcard)) {
						item.setCode(-120);
						item.setDesc("身份证格式错误！");
						return item.toJson();
					}
				} else {
					item.setCode(-120);
					item.setDesc("身份证格式错误！");
					return item.toJson();
				}
				// if (!idcard.matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)")) {
				// item.setCode(-120);
				// item.setDesc("身份证格式错误！");
				// return item.toJson();
				// }
			}
			item.setCode(0);
			return item.toJson();
		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.Api, "校验身份信息参数异常! 异常信息:{0}", e,
					"/idcard/checklegal");
		}
		return item.toJson();
	}

}
