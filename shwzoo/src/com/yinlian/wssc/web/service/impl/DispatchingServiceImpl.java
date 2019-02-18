package com.yinlian.wssc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.DispatchingMapper;
import com.yinlian.wssc.web.po.Dispatching;
import com.yinlian.wssc.web.service.DispatchingService;

@Component("dispatchingService")
public class DispatchingServiceImpl  implements DispatchingService{

	@Autowired
	private  DispatchingMapper  dispatchingMapper;
	
	@Override
	public int insert(Dispatching disp) throws Exception {
		return dispatchingMapper.insert(disp);
	}

	@Override
	public Dispatching queryByOrderGroupCode(String groupCode) throws Exception {
		
		return dispatchingMapper.selectGroupCode(groupCode);
	}
}
