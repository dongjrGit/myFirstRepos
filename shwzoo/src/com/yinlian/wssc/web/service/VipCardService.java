package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.po.Card;

public interface VipCardService {
	
	/**
	 * 办理一张vipcard
	 * @param card
	 * @return
	 * @throws Exception
	 */
	public int insert(Card card) throws Exception;
	/**
	 * 通过userid查询银行卡
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	Card  quertByUserId(int userid) throws Exception;
}
