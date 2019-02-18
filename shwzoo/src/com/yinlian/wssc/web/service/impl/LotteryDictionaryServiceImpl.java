/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.LotteryDictionaryMapper;
import com.yinlian.wssc.web.po.LotteryDictionary;
import com.yinlian.wssc.web.po.LotteryDictionaryExample;
import com.yinlian.wssc.web.service.LotteryDictionaryService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.PropertiesUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * LotteryDictionaryServiceImpl.java
 * 
 * @author Liang.ma.s
 * @version $Id: LotteryDictionaryServiceImpl.java, v 0.1 2016年4月12日 上午11:16:51
 *          Administrator Exp $
 */
@Component("lotteryDictionaryService")
public class LotteryDictionaryServiceImpl implements LotteryDictionaryService {

	@Autowired
	private LotteryDictionaryMapper lotteryDictionaryMapper;

	/**
	 * @see com.yinlian.wssc.web.service.LotteryDictionaryService#getMap()
	 */
	@Override
	public Map<String, Double> getMap() throws Exception {
		Map<String, Double> map = new HashMap<String, Double>();
		LotteryDictionaryExample example = new LotteryDictionaryExample();
		List<LotteryDictionary> list = lotteryDictionaryMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for (LotteryDictionary record : list) {
				String probability = record.getProbability(); // 中奖概率
				map.put(String.valueOf(record.getMark()), Double.parseDouble(probability));
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String s = "0.00075";

		int index = s.lastIndexOf(".");// 寻找小数点的索引位置，若不是小数，则为-1
		System.out.println("小数点位置：" + index);
		int len = 0;
		if (index > -1) {
			len = s.substring(index + 1).length();// 取得小数点后的数值，不包括小数点
			s += len == 1 ? "0" : "";
		}
		System.out.println("小数点后面字符串的长度：" + len);
		double fenmu = Math.pow(10, len);
		System.out.println("精确到：" + fenmu);
		System.out.println(s);
		double f = StringUtilsEX.ToDouble(s);
		String fenzi = String.valueOf(fenmu * f);
		System.out.println("概率" + fenzi);

		BigDecimal decimal = new BigDecimal(fenzi).setScale(0, BigDecimal.ROUND_HALF_UP);
		System.out.println(decimal);
	}

	/**
	 * @see com.yinlian.wssc.web.service.LotteryDictionaryService#qeury()
	 */
	@Override
	public List<LotteryDictionary> qeury1() throws Exception {

		LotteryDictionaryExample example = new LotteryDictionaryExample();
		List<LotteryDictionary> list = lotteryDictionaryMapper.selectByExample(example);
		return list;
	}

	@Override
	public Map<String, Object> qeury() throws Exception {

		LotteryDictionaryExample example = new LotteryDictionaryExample();
		List<LotteryDictionary> list = lotteryDictionaryMapper.selectByExample(example);
		Map<String, Object> map = new HashMap<String, Object>();
		list.stream().forEach(x -> {
			if (x != null)
				map.put(String.valueOf(x.getMark()), x);
		});
		return map;
	}

	/**
	 * 查询所有
	 * 
	 * @see com.yinlian.wssc.web.service.LotteryDictionaryService#queryAll()
	 */
	@Override
	public PageBean queryAllByCriteria(Criteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
		PageBean pageBean = pageBeanUtil.getPage();
		List<LotteryDictionary> beanlist = lotteryDictionaryMapper.selectAllByPage(pageBeanUtil);
		pageBean.setBeanList(beanlist);
		return pageBean;
	}

	/**
	 * 根据id查询规则信息
	 * 
	 * @see com.yinlian.wssc.web.service.LotteryDictionaryService#queryById(java.lang.Integer)
	 */
	@Override
	public LotteryDictionary queryById(Integer id) throws Exception {

		return lotteryDictionaryMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据id修改规则
	 * 
	 * @see com.yinlian.wssc.web.service.LotteryDictionaryService#updatLotteryRuleById(java.lang.Integer,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int updatLotteryRuleById(Integer id, String name, String description, String probability, String value,
			String mark) throws Exception {
		LotteryDictionary lotteryDictionary = lotteryDictionaryMapper.selectByPrimaryKey(id);
		lotteryDictionary.setName(name);
		lotteryDictionary.setDescription(description);
		lotteryDictionary.setProbability(probability);
		if (StringUtils.isNotNull(value)) {
			lotteryDictionary.setValue(Integer.valueOf(value));
		}
		lotteryDictionary.setMark(StringUtilsEX.ToInt(mark));
		return lotteryDictionaryMapper.updateByPrimaryKey(lotteryDictionary);
	}

	/**
	 * 添加一条规则
	 * 
	 * @see com.yinlian.wssc.web.service.LotteryDictionaryService#addLotteryRule(java.lang.Integer,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int addLotteryRule(String name, String description, String probability, String value, String mark)
			throws Exception {
		LotteryDictionary lotteryDictionary = new LotteryDictionary();
		lotteryDictionary.setName(name);
		lotteryDictionary.setDescription(description);
		lotteryDictionary.setProbability(probability);
		if (StringUtils.isNotNull(value)) {
			lotteryDictionary.setValue(Integer.valueOf(value));
		}
		lotteryDictionary.setMark(StringUtilsEX.ToInt(mark));
		//Properties properties = PropertiesUtil.getProperties("cfg.properties");
		//lotteryDictionary.setMark(Integer.valueOf(properties.get("fist_prize").toString()));
		return lotteryDictionaryMapper.insert(lotteryDictionary);
	}

	/**
	 * @see com.yinlian.wssc.web.service.LotteryDictionaryService#selectAll()
	 */
	@Override
	public List<LotteryDictionary> selectAll() throws Exception {
		LotteryDictionaryExample example = new LotteryDictionaryExample();

		return lotteryDictionaryMapper.selectByExample(example);
	}

	@Override
	public int deleteById(Integer id) throws Exception {

		return lotteryDictionaryMapper.deleteByPrimaryKey(id);
	}

}
