package com.yinlian.wssc.web.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.ConfigdictionaryMapper;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.service.ConfigSetService;


/**
 * 
 * @author Administrator
 *
 */
@Component("configSetService")
public class ConfigSetServiceImpl implements ConfigSetService {
	
	
	
	@Autowired
	private ConfigdictionaryMapper configdictionaryMapper;
	
	/**
	 * 日志输出的类
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigSetServiceImpl.class);
	


	@Override
	public int updateConfigSetByType(Configdictionary configdictionary)
			throws Exception {
		if (configdictionary == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为空");
				throw new IllegalArgumentException("The parameter Configdictionary is null!");
				
			}
		}
		
		return configdictionaryMapper.updateByPrimaryKey(configdictionary);
	}



	@Override
	public Configdictionary showConfigSetByType(Integer stype) throws Exception {
		
		if (stype == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为空");
				throw new IllegalArgumentException("The parameter stype is null!");
				
			}
		}
		return configdictionaryMapper.selectByPrimaryType(stype);
	}



	@Override
	public int addConfigSet(Configdictionary configdictionary) throws Exception {
		
		if (configdictionary == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为空");
				throw new IllegalArgumentException("The parameter configdictionary is null!");
				
			}
		}
		return configdictionaryMapper.insertSelective(configdictionary);
	}



}
