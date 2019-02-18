package com.yinlian.wssc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.ShopormemberstatushistoryMapper;
import com.yinlian.wssc.web.po.Shopormemberstatushistory;
import com.yinlian.wssc.web.service.ShopstatushistoryService;

@Component("shopstatushistoryService")
public class ShopstatushistoryServiceImpl implements ShopstatushistoryService{

	@Autowired 
	private ShopormemberstatushistoryMapper     shopormemberstatushistoryMapper;
	
	@Override
	public Shopormemberstatushistory queryNewStatus(Integer objid)
			throws Exception {
		
		return shopormemberstatushistoryMapper.selectNewStatus(objid);
	}

}
