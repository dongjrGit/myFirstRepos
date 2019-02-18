package com.yinlian.wssc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.ReceiveinfoMapper;
import com.yinlian.wssc.web.po.Receiveinfo;
import com.yinlian.wssc.web.service.ReceiveInfoService;

@Component("receiveInfoService")
public class ReceiveInfoServiceImpl implements ReceiveInfoService {
	
	@Autowired
	private ReceiveinfoMapper receiveinfoMapper;

	@Override
	public int insert(Receiveinfo receive) throws Exception {
		return receiveinfoMapper.insert(receive);
	}

	@Override
	public Receiveinfo queryByOrderGroupId(String  ordergroupcode) throws Exception {
		
		return receiveinfoMapper.selectByOrderGroupId(ordergroupcode);
	}
}
