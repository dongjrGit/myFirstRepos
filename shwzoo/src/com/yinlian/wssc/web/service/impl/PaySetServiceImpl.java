package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.mapper.PaysetMapper;
import com.yinlian.wssc.web.po.Payset;
import com.yinlian.wssc.web.po.PaysetExample;
import com.yinlian.wssc.web.service.PaySetService;
import com.yinlian.wssc.web.util.StringUtilsEX;

public class PaySetServiceImpl implements PaySetService {
	
	/**
	 * 日志输出的类
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(PaySetServiceImpl.class);
	
	@Autowired
	private PaysetMapper paysetMapper;

	/**
	 * 
	 */
	@Override
	public List<Payset> queryPaySetList() throws Exception {
		
		PaysetExample example = new PaysetExample();
		List<Payset> list = paysetMapper.selectByExample(example);
		
		return list;
	}

	/**
	 * 
	 */
	@Override
	public int deletePaySetById(Integer id) throws Exception {
		if (id==null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为空");
				throw new IllegalArgumentException("The parameter id is null");
			}
		}
		return paysetMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 
	 */
	@Override
	public int updatePaySetById(Payset payset) throws Exception {
		if (payset==null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为空");
				throw new IllegalArgumentException("The parameter payset is null");
			}
		}
		
		return paysetMapper.updateByPrimaryKey(payset);
	}

	@Override
	public Payset selectPaySetById(String id) throws Exception {
		if (id==null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为空");
				throw new IllegalArgumentException("The parameter id is null");
			}
		}
		return paysetMapper.selectByPrimaryKey(StringUtilsEX.ToInt(id));
	}
	
	/**
	 * 
	 */

	@Override
	public int addPaySet(Payset payset) throws Exception {
		
		return paysetMapper.insert(payset);
	}

}
