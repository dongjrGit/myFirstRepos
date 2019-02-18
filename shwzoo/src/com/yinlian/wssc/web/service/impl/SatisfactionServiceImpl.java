package com.yinlian.wssc.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.SatisfactionMapper;
import com.yinlian.wssc.web.po.Satisfaction;
import com.yinlian.wssc.web.service.SatisfactionService;

@Component("satisfactionService")
public class SatisfactionServiceImpl implements SatisfactionService {
	
	private static final Logger  logger = LoggerFactory.getLogger(SatisfactionServiceImpl.class);
	
	@Autowired
	private SatisfactionMapper   satisfactionMapper;
	
	
	@Override
	public int insertSatisfa(Satisfaction satisfaction) throws Exception {
		
		return satisfactionMapper.insert(satisfaction);
	}


	@Override
	public Satisfaction selectByDetailId(Integer orderdetailId) throws Exception {
		
		return satisfactionMapper.selectBydetailId(orderdetailId);
	}

}
