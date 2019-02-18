package com.yinlian.api.wap.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.dto.LotteryDto;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.LotteryDictionary;
import com.yinlian.wssc.web.po.Lotteryrecord;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.LotteryDictionaryService;
import com.yinlian.wssc.web.service.LotteryParamService;
import com.yinlian.wssc.web.service.LotteryService;
import com.yinlian.wssc.web.service.LotteryrecordService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.CookieUtils;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/api/wap/waplottery")
public class WapLotteryController {
	
	@Autowired
	private LotteryService lotteryService;
	@Autowired
	private UserService userService;
	@Autowired
	private LotteryDictionaryService lotteryDictionaryService;
	@Autowired
	private LotteryrecordService lotteryrecordService;
	@Autowired
	private ConfigSetService configSetService;
	@Autowired
	private LotteryParamService lotteryParamService;
	@SuppressWarnings("unused")
	private SessionUser sessionUser = null;

	private Integer userid = null;
	@SuppressWarnings("unused")
	private String userName = null;

	@RequestMapping(value = "/lottery", produces = "text/html;charset=UTF-8")
	public @ResponseBody String lottery(String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			item.setData(lotteryDictionaryService.qeury1());
		} catch (Exception ex) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "抽奖异常! 异常信息:{0}", ex, "lottery/lottery");
		}
		return item.toJson();
	}

	/**
	 * 查询每人每天抽奖次
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getcount", produces = "text/html;charset=UTF-8")
	public @ResponseBody String getcount(String ch,HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			Configdictionary configd = configSetService.showConfigSetByType(ConfigSetTypeEnum.每人每天抽奖次数.getValue());
			int count = StringUtilsEX.ToInt(configd.getValue());
			SessionUser  sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() == 0) {
				userid = sessionUser.getUserId();
				userName = sessionUser.getLoginName();
				Lotteryrecord lotteryrecord = lotteryrecordService.selectByUserIdCurrentDay(userid,
						DateUtil.getStartTime(), DateUtil.getEndTime());
				if (lotteryrecord != null) {
					count = count - lotteryrecord.getCount();
				}
			}
			request.setAttribute("count", count);
			item.setData(count);//剩余抽奖次数
		} catch (Exception ex) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "抽奖异常! 异常信息:{0}", ex, "lottery/getcount");
		}
		return item.toJson();
	}

	/**
	 * 查询最新 20条 中奖信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getlottery", produces = "text/html;charset=UTF-8")
	public  @ResponseBody String getlottery(String ch) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}
			/*BaseCriteria c = new BaseCriteria();
			c.setOrderByClause("creattime");
			c.setSort("desc");*/
			//lotteryService.queryLotteryAllByCriteria(c, 1, 20);
			List<LotteryDto> list=lotteryService.queryNewLottery();
			item.setData(list);
		} catch (Exception ex) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(ex.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap, "抽奖异常! 异常信息:{0}", ex, "lottery/getlottery");
		}
		return item.toJson();

	}

	/**
	 * 显示抽奖页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showlottery", produces = "text/html;charset=UTF-8")
	public String showlottery(String ch, Model model,HttpServletRequest request) {
		try {
			Map<String, Object> map = lotteryDictionaryService.qeury();
			LotteryDictionary dictionarygroup = (LotteryDictionary) map.get(ConstanValue.FIRST_PRIZE);
			LotteryDictionary dictionaryproduct = (LotteryDictionary) map.get(ConstanValue.SECOND_PRIZE);
			LotteryDictionary dictionarybeans = (LotteryDictionary) map.get(ConstanValue.THRID_PRIZE);
			Integer groupvalue = null;
			Integer productvalue = null;
			Integer beanvalue = null;
			if (dictionarygroup != null) {
				groupvalue = dictionarygroup.getValue();
			}
			if (dictionaryproduct != null) {
				productvalue = dictionaryproduct.getValue();
			}
			if (dictionarybeans != null) {
				beanvalue = dictionarybeans.getValue();
			}
			Map<String, Object> mapParam = lotteryParamService.queryName();
			String groupname = (String) mapParam.get(ConstanValue.FIRST_PRIZE);
			String productname = (String) mapParam.get(ConstanValue.SECOND_PRIZE);
			// couponService.getLotteryCoupon();

			Configdictionary configd = configSetService.showConfigSetByType(ConfigSetTypeEnum.每人每天抽奖次数.getValue());
			int ccounter = StringUtilsEX.ToInt(configd.getValue());
			Configdictionary configdictionary = configSetService
					.showConfigSetByType(ConfigSetTypeEnum.每人每天中奖次数.getValue());
			int lcount = StringUtilsEX.ToInt(configdictionary.getValue());
			model.addAttribute("ccounter", ccounter);
			model.addAttribute("lcount", lcount);
			model.addAttribute("groupvalue", groupvalue);
			model.addAttribute("productvalue", productvalue);
			model.addAttribute("beanvalue", beanvalue);
			model.addAttribute("groupname", groupname);
			model.addAttribute("productname", productname);

		} catch (Exception e) {
			LogHandle.error(LogType.wap, "抽奖异常! 异常信息:{0}", e, "lottery/showlottery");
		}
		return "app/lottery";
	}

	/**
	 * 抽奖接口
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/award", produces = "text/html;charset=UTF-8")
	public @ResponseBody String award(String ch, HttpServletRequest request) {
		BaseResult item = new BaseResult();
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-108);
				item.setDesc("登录通道参数错误");
				return item.toJson();
			}

			Configdictionary configd = configSetService.showConfigSetByType(ConfigSetTypeEnum.每人每天抽奖次数.getValue());
			int count = StringUtilsEX.ToInt(configd.getValue());
			SessionUser  sessionUser = new SessionUser();
			String token = CookieUtils.getTokenFromCookie(request);
			sessionUser = SessionState.GetCurrentUser(token);
			if (sessionUser.getCode() != 0) {
				item.setDesc("请先登录");
				item.setCode(-401);
				return item.toJson();
			}
			userid = sessionUser.getUserId();
			userName = sessionUser.getLoginName();
			Users user = userService.queryById(userid);
			Lotteryrecord lotteryrecord = lotteryrecordService.selectByUserIdCurrentDay(userid, DateUtil.getStartTime(),
					DateUtil.getEndTime());
			int counter = 0;
			if (lotteryrecord != null) {
				counter = lotteryrecord.getCount();
			}
			if (lotteryrecord != null && counter < count) {

				counter++;
				lotteryrecord.setCount(counter);
				lotteryrecordService.update(lotteryrecord);
			} else if (lotteryrecord != null && counter >= count) {
				item.setCode(-101);
				item.setDesc(",已用完，每天抽奖" + count + "次");
				return item.toJson();
			} else {
				counter++;
				lotteryrecordService.insert(userid, user.getMobile(), user.getUsername(), counter);
			}
			List<Prize> prizes = new ArrayList<Prize>();
			List<LotteryDictionary> list = lotteryDictionaryService.qeury1();
			for (LotteryDictionary lottery : list) {
				Prize prize = new Prize();
				prize.setPrizeId(lottery.getId());
				prize.setProbability(BigDecimal.valueOf(Double.valueOf(lottery.getProbability())));
				prize.setQuantity(999999999);
				prizes.add(prize);
			}
			int kid = getAngle(prizes);
			List<LotteryDictionary> rlist = list.stream().filter(x -> x.getId() == kid).collect(Collectors.toList());
			if (rlist.size() <= 0) {
				rlist = list.stream().filter(x -> x.getMark() == 10).collect(Collectors.toList());
			}
			item.setCode(0);
			LotteryDictionary dto = rlist.get(0);

			lotteryService.insert(sessionUser.getId(), sessionUser.getLoginName(), user.getMobile(), dto, list, item);

		} catch (Exception e) {
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			item.setCode(-900);
			LogHandle.error(LogType.wap,"抽奖异常! 异常信息:{0}", e, "lottery/award");

		}
		return item.toJson();
	}

	class Prize {
		/**
		 * 奖品唯一标示
		 */
		private Integer prizeId;

		/**
		 * 中奖概率
		 */
		private BigDecimal probability;

		/**
		 * 奖品数量
		 */
		private Integer quantity;

		public Integer getPrizeId() {
			return prizeId;
		}

		public void setPrizeId(Integer prizeId) {
			this.prizeId = prizeId;
		}

		public BigDecimal getProbability() {
			return probability;
		}

		public void setProbability(BigDecimal probability) {
			this.probability = probability;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
	}

	public int getAngle(List<Prize> prizes) {
		int mulriple = 100000000;
		int lastScope = 0;
		// 洗牌，打乱奖品次序
		Collections.shuffle(prizes);
		Map<Integer, int[]> prizeScopes = new HashMap<Integer, int[]>();
		Map<Integer, Integer> prizeQuantity = new HashMap<Integer, Integer>();
		for (Prize prize : prizes) {
			int prizeId = prize.getPrizeId();
			// 划分区间
			int currentScope = lastScope + prize.getProbability().multiply(new BigDecimal(mulriple)).intValue();
			prizeScopes.put(prizeId, new int[] { lastScope + 1, currentScope });
			prizeQuantity.put(prizeId, prize.getQuantity());

			lastScope = currentScope;
		}

		// 获取1-1000000之间的一个随机数
		int luckyNumber = new Random().nextInt(mulriple);
		int luckyPrizeId = 0;
		// 查找随机数所在的区间
		if ((null != prizeScopes) && !prizeScopes.isEmpty()) {
			Set<Entry<Integer, int[]>> entrySets = prizeScopes.entrySet();
			for (Map.Entry<Integer, int[]> m : entrySets) {
				int key = m.getKey();
				if (luckyNumber >= m.getValue()[0] && luckyNumber <= m.getValue()[1] && prizeQuantity.get(key) > 0) {
					luckyPrizeId = key;
					break;
				}
			}
		}
		return luckyPrizeId;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/*private Object[][] init() throws Exception {
		List<LotteryDictionary> list = lotteryDictionaryService.qeury1();

		Object[][] prizeArr = new Object[list.size()][5];
		int index = 0;
		int start = 0;
		for (LotteryDictionary ld : list) {
			prizeArr[index][0] = index + 1;
			prizeArr[index][1] = start + 1;
			start += (int) Math.floor(Double.valueOf(ld.getProbability()) * 10000000 * 360);
			prizeArr[index][2] = start;
			prizeArr[index][3] = ld.getDescription();
			prizeArr[index][4] = String.valueOf(ld.getMark());
			index++;
		}*/

		/*
		 * LotteryDictionary dictionary1 = (LotteryDictionary)
		 * map.get(ConstanValue.FIRST_PRIZE); LotteryDictionary dictionary2 =
		 * (LotteryDictionary) map.get(ConstanValue.SECOND_PRIZE);
		 * LotteryDictionary dictionary3 = (LotteryDictionary)
		 * map.get(ConstanValue.THRID_PRIZE); LotteryDictionary dictionary4 =
		 * (LotteryDictionary) map.get(ConstanValue.NO_PRIZE); Object[][]
		 * prizeArr = new Object[][] { { 1, 1, 29, dictionary1.getDescription(),
		 * ConstanValue.FIRST_PRIZE }, { 2, 31, 90,
		 * dictionary2.getDescription(), ConstanValue.SECOND_PRIZE }, { 3, 91,
		 * 150, dictionary3.getDescription(), ConstanValue.THRID_PRIZE }, { 4,
		 * 151, 210, dictionary3.getDescription(), ConstanValue.THRID_PRIZE }, {
		 * 5, 211, 270, dictionary3.getDescription(), ConstanValue.THRID_PRIZE
		 * }, { 6, 270, 330, dictionary4.getDescription(), ConstanValue.NO_PRIZE
		 * } };
		 */

	/*	return prizeArr;
	}*/

	/*
	 * 
	 */
	/*private Object[] angle(Object[][] prizeArr, Map<String, Double> map) throws Exception {
		Integer obj[] = new Integer[prizeArr.length];
		for (int i = 0; i < prizeArr.length; i++) {
			obj[i] = Integer.parseInt((String) prizeArr[i][4]);
		}
		Map<String, Object> map2 = getRand(obj, map);
		Integer prizeId = (Integer) map2.get("prizeId");

		int start = new Random().nextInt((Integer) prizeArr[prizeId][2] - (Integer) prizeArr[prizeId][1]);
		int end = (Integer) prizeArr[prizeId][1];
		int angle = start + end;
		String msg = (String) prizeArr[prizeId][3];// 提示信息
		Integer slp = (Integer) map2.get("SLP");
		return new Object[] { angle, prizeId, msg, slp };
	}*/

	/*private Map<String, Object> getRand(Integer obj[], Map<String, Double> map) throws Exception {
		Integer result = null;
		Map<String, Object> targetMap = new HashMap<String, Object>();

		Map<String, Object> mapD = lotteryDictionaryService.qeury();

		LotteryDictionary dictionaryproduct = (LotteryDictionary) mapD.get(ConstanValue.SECOND_PRIZE);
		LotteryDictionary dictionarybeans = (LotteryDictionary) mapD.get(ConstanValue.THRID_PRIZE);

		Integer productvalue = null;
		Integer beanvalue = null;
		if (dictionaryproduct != null) {
			productvalue = dictionaryproduct.getValue();
		}
		if (dictionarybeans != null) {
			beanvalue = dictionarybeans.getValue();
		}

		Map<String, Object> mapParam = lotteryParamService.queryValue();
		int productvalueparam = (int) mapParam.get(ConstanValue.SECOND_PRIZE);
		int beanvalueparam = (int) mapParam.get(ConstanValue.THRID_PRIZE);

		double f1 = 1 - map.get(ConstanValue.FIRST_PRIZE);
		double s2 = f1 - map.get(ConstanValue.SECOND_PRIZE);
		double t3 = s2 - map.get(ConstanValue.THRID_PRIZE);
		double random = new Random().nextInt(mapD.size() + 1);
		System.out.println("随机数：" + random);
		if (random > f1 && random <= 1) {
			for (Integer k : obj) {
				if (ConstanValue.FIRST_PRIZE.equals(String.valueOf(obj[k]))) {
					LotteryParam lotteryParam = lotteryParamService
							.selectByType(Integer.valueOf(ConstanValue.FIRST_PRIZE));
					BaseResult item = new BaseResult();
					int code = couponService.addUserCoupon(lotteryParam.getGroupid(), userid, userName, item);
					if (code > 0) {
						result = k;
					} else {
						result = -100;
					}
					break;
				}
			}
		} else if (random > s2 && random <= f1) {
			for (Integer k : obj) {
				if (ConstanValue.SECOND_PRIZE.equals(String.valueOf(obj[k]))) {
					if (productvalueparam < productvalue) {
						result = 100;
					} else {
						result = k;
						targetMap.put("SLP", productvalueparam - productvalue);
					}
					break;
				}
			}
		} else if (random > t3 && random <= s2) {
			for (Integer k : obj) {
				if (ConstanValue.THRID_PRIZE.equals(String.valueOf(obj[k]))) {
					if (beanvalueparam < beanvalue) {
						result = -100;
					} else {
						result = k;
						targetMap.put("SLP", beanvalueparam - beanvalue);
					}
					break;
				}
			}
		} else {
			result = 100;
		}
		System.out.println("中奖的结果位置：" + result);
		targetMap.put("prizeId", result);

		return targetMap;
	}*/

	/*private Map<String, Double> getMap() throws Exception {

		return lotteryDictionaryService.getMap();
	}*/

}
