package com.yinlian.wssc.web.mapper;

import java.util.List;

import com.yinlian.wssc.web.po.BankCard;

public interface BankCardMapper {
	
	int insert(BankCard bankcard);
	
	BankCard query(Integer userid,String number);

	/**
	 * 根据userid查询银行卡
	 * 
	 * @param userid
	 * @return
	 */
	List<BankCard> selectByUserId(Integer userid);
	
	int selectCount(Integer userid);
	
	int deleteById(Integer id);
}
