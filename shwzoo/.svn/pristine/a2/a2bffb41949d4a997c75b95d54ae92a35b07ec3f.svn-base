package com.yinlian.wssc.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.CardMapper;
import com.yinlian.wssc.web.po.Card;
import com.yinlian.wssc.web.service.VipCardService;

@Component("vipCardService")
public class VipCardServiceImpl implements VipCardService{
	
	/**
	 * 输出日志的控制类
	 */
	private static final Logger logger = LoggerFactory.getLogger(VipCardServiceImpl.class);
	
	@Autowired
	private CardMapper       cardMapper;
	
	/**
	 * 插入一条办卡记录
	 */
	@Override
	public int insert(Card card) throws Exception {
		
		return cardMapper.insert(card);
	}
	
	/**
	 * 根据用户ID查询办卡信息
	 */
	@Override
	public Card quertByUserId(int userid) throws Exception {
		
		return cardMapper.selectByUserId(userid);
	}

}
