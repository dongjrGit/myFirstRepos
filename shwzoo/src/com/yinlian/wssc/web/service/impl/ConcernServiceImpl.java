package com.yinlian.wssc.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.mapper.ConcernMapper;
import com.yinlian.wssc.web.po.Concern;
import com.yinlian.wssc.web.service.ConcernService;

public class ConcernServiceImpl implements ConcernService{
	
	private static final Logger  logger = LoggerFactory.getLogger(ConcernServiceImpl.class);
	
	@Autowired
	private ConcernMapper       concernMapper;
	
	@Override
	public int add(Concern concern) throws Exception {
		
		return concernMapper.insert(concern);
	}

}
