package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.FeedbacktypeMapper;
import com.yinlian.wssc.web.po.Feedbacktype;
import com.yinlian.wssc.web.service.FeedBackTypeService;

@Component("feedBackTypeService")
public class FeedBackTypeServiceImpl implements FeedBackTypeService{
	
	private static final Logger  logger = LoggerFactory.getLogger(FeedBackTypeServiceImpl.class);
	
	@Autowired
	private   FeedbacktypeMapper   feedbacktypeMapper;
	
	@Override
	public List<Feedbacktype> selectAll() {
		
		return feedbacktypeMapper.selectAll();
	}

}
