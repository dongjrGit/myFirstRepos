package com.yinlian.wssc.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.mapper.SmsMapper;
import com.yinlian.wssc.web.po.Smsrecord;
import com.yinlian.wssc.web.service.SmsService;

public class SmsServiceImpl implements SmsService {
	
	/**
	 * 日志输出的类
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@Autowired
	private SmsMapper smsMapper;

	@Override
	public int addSms(Smsrecord sms) throws Exception {
		// TODO Auto-generated method stub
		return smsMapper.insert(sms);
	}

	@Override
	public Smsrecord selectSmsByPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		
		return smsMapper.selectByPhone(phone);
		
	}

	

}
