package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.BankCard;

public interface BankCardService {
	/**
	 * 添加银行卡
	 * @param bankCard
	 * @return
	 * @throws Exception
	 */
	public int insert(BankCard  bankCard) throws Exception;
	
	public BankCard  query(Integer userid,String number) throws Exception;

	/**
	 * 根据userid查询银行卡
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<BankCard> queryBrandCardByUserId(Integer userid) throws Exception;
	
	/**
	 * 查询银行卡的数量
	 * @param userid    会员ID
	 * @return
	 * @throws Exception
	 */
	public int selectCount(Integer userid) throws Exception;
	
	public int deleteById(Integer id)throws Exception;

}
