package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.OrderactivityHistoryMapper;
import com.yinlian.wssc.web.po.OrderactivityHistory;
import com.yinlian.wssc.web.service.OrderactivityHistoryService;

@Component("orderactivityHistoryService")
public class OrderactivityHistoryServiceImpl implements OrderactivityHistoryService{

	@Autowired
	private OrderactivityHistoryMapper orderactivityHistoryMapper;
	
	@Override
	public int inserts(List<OrderactivityHistory> records) throws Exception {
		int count=0;
		for (OrderactivityHistory record : records) {
			count+=orderactivityHistoryMapper.insert(record);
		}
		return count;
	}
}
