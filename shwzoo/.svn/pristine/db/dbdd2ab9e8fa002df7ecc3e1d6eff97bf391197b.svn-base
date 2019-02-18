package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.OrderBackupMapper;
import com.yinlian.wssc.web.po.OrderBackup;
import com.yinlian.wssc.web.service.OrderBackupService;

@Component("orderBackupService")
public class OrderBackupServiceImpl implements OrderBackupService {

	@Autowired
	private OrderBackupMapper orderBackupMapper;

	@Override
	public void add(List<OrderBackup> ordersbc) throws Exception {
		for (OrderBackup record : ordersbc) {
			orderBackupMapper.insert(record);
		}
	}
}
