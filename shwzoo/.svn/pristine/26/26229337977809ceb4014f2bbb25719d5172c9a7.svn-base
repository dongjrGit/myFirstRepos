package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.BankCardMapper;
import com.yinlian.wssc.web.po.BankCard;
import com.yinlian.wssc.web.service.BankCardService;

@Component("bankCardService")
public class BankCardServiceImpl implements BankCardService{
	
	@Autowired
	private BankCardMapper   bankCardMapper;
	
	@Override
	public int insert(BankCard bankCard) throws Exception {
		
		return bankCardMapper.insert(bankCard);
	}

	@Override
	public BankCard query(Integer userid, String number) throws Exception {
		
		return bankCardMapper.query(userid,number);
	}

	/**
	 * 根据userid查询银行卡
	 * @see com.yinlian.wssc.web.service.BankCardService#queryBrandCardByUserId(java.lang.Integer)
	 */
	@Override
	public List<BankCard> queryBrandCardByUserId(Integer userid)
			throws Exception {
		
		return bankCardMapper.selectByUserId(userid);
	}
	@Override
	public int selectCount(Integer userid) throws Exception {
		
		return bankCardMapper.selectCount(userid);
	}
	@Override
	public int deleteById(Integer id) throws Exception {
		
		return bankCardMapper.deleteById(id);
	}
}
