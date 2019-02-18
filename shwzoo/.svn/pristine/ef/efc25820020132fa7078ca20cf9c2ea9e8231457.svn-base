/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.LotteryStatusEnum;
import com.yinlian.Enums.PointsRecordsFromTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.search.ICriteria;
import com.yinlian.wssc.web.dto.LotteryDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.LotteryMapper;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Lottery;
import com.yinlian.wssc.web.po.LotteryDictionary;
import com.yinlian.wssc.web.po.LotteryParam;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.service.LotteryParamService;
import com.yinlian.wssc.web.service.LotteryService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * LotteryServiceImpl.java
 * 
 * @author Liang.ma.s
 * @version $Id: LotteryServiceImpl.java, v 0.1 2016年4月11日 上午11:09:15
 *          Administrator Exp $
 */
@Component("lotteryService")
public class LotteryServiceImpl implements LotteryService {

	@Autowired
	private LotteryMapper lotteryMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private ConfigSetService configSetService;
	@Autowired
	private LotteryParamService lotteryParamService;
	@Autowired
	private CouponService couponService;

	/**
	 * 分页查询中奖名单
	 * 
	 * @see com.yinlian.wssc.web.service.LotteryService#queryLotteryAllByCriteria(com.yinlian.wssc.web.util.Criteria,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryLotteryAllByCriteria(ICriteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Lottery> beanlist = lotteryMapper.selectLotteryAllByPage(pageBeanUtil);
		pageBean.setBeanList(beanlist);
		return pageBean;
	}

	/**
	 * 根据id删除中奖人
	 * 
	 * @see com.yinlian.wssc.web.service.LotteryService#delLottery(java.lang.String)
	 */
	@Override
	public int delLottery(Integer id) throws Exception {
		Lottery lottery = lotteryMapper.selectByPrimaryKey(id);
		lottery.setIsdel(true);
		return lotteryMapper.updateByPrimaryKey(lottery);
	}

	/**
	 * @see com.yinlian.wssc.web.service.LotteryService#selectByUserIdCurrentDay(java.lang.Integer,
	 *      java.util.Date, java.util.Date)
	 */
	@Override
	public List<Lottery> selectByUserIdCurrentDay(Integer userid, Date startTime, Date endTime) throws Exception {
		return lotteryMapper.selectByUserIdCurrentDay(userid, startTime, endTime);
	}

	@Override
	public void insert(Integer userid, String username, String mobile, LotteryDictionary dto,
			List<LotteryDictionary> list, BaseResult item) throws Exception {
		Configdictionary configdictionary = configSetService.showConfigSetByType(ConfigSetTypeEnum.每人每天中奖次数.getValue());
		int lcount = StringUtilsEX.ToInt(configdictionary.getValue());
		List<Lottery> lotteryList = selectByUserIdCurrentDay(userid, DateUtil.getStartTime(), DateUtil.getEndTime());

		if (lotteryList != null && lotteryList.size() == lcount) {
			dto= list.stream().filter(x -> x.getMark() == 10).collect(Collectors.toList()).get(0);
			//dto = rlist.get(0);
			//desc = "";
			item.setCode(0);
			item.setData(dto.getId());
			item.setDesc(dto.getDescription());
			//item.setDesc("每人每天只能中奖" + lcount + "次");
			//item.setCode(-103);
			return;
		}
		item.setCode(0);
		List<LotteryDictionary> rlist;
		LotteryParam pro;
		String desc = null;
		switch (dto.getMark()) {
		case 1:// 优惠卷处理
			pro = lotteryParamService.getByLId(dto.getId());
			if (pro == null) {
				rlist = list.stream().filter(x -> x.getMark() == 10).collect(Collectors.toList());
				dto = rlist.get(0);
				desc = "";
				break;
			}
			BaseResult item1 = new BaseResult();
			couponService.addUserCoupon(pro.getGroupid(), userid, username, item1);
			if (item1.getCode() <= 0) {
				rlist = list.stream().filter(x -> x.getMark() == 10).collect(Collectors.toList());
				dto = rlist.get(0);
				desc = "";
			} else {
				desc = "恭喜 " + mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length()) + "中 "
						+ pro.getDescription();
			}
			break;
		case 2:// 商品处理
			pro = lotteryParamService.getByLId(dto.getId());
			if (pro != null && pro.getValue() >= 0) {
				desc = "恭喜 " + mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length()) + "中 "
						+ pro.getDescription();
				pro.setValue(pro.getValue() - 1);
				lotteryParamService.update(pro);
			} else {
				rlist = list.stream().filter(x -> x.getMark() == 10).collect(Collectors.toList());
				dto = rlist.get(0);
				desc = "";
			}
			break;
		case 3:// 积分处理
			pro = lotteryParamService.getByLId(dto.getId());
			if (pro != null) {
				userService.updatePoint(userid, pro.getValue(), PointsRecordsTypeEnum.增加,
						PointsRecordsFromTypeEnum.活动增送);
				desc = "恭喜 " + mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length()) + "中 "
						+ pro.getValue() + "积分!";
			} else {
				rlist = list.stream().filter(x -> x.getMark() == 10).collect(Collectors.toList());
				dto = rlist.get(0);
				desc = "";
			}
			break;
		default:
			desc = "";
			break;
		}

		item.setData(dto.getId());
		item.setDesc(dto.getDescription());
		if (dto.getMark() == 10) {
			return;
		}
		Lottery lottery = new Lottery();
		lottery.setUserid(userid);
		lottery.setDescription(desc);
		lottery.setMark(dto.getMark());
		lottery.setMobile(mobile);
		lottery.setCreattime(new Date());
		lottery.setIsdel(false);
		lottery.setStatus(LotteryStatusEnum.已赠送.getValue());

		lotteryMapper.insert(lottery);

	}

	@Override
	public List<LotteryDto> queryNewLottery() throws Exception {
		
		return lotteryMapper.selectNewLottery();
	}

}
