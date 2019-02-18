package com.yinlian.wssc.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.OrderUpdaterecordsMapper;
import com.yinlian.wssc.web.po.OrderUpdaterecords;
import com.yinlian.wssc.web.service.OrderUpdaterecordsService;
import com.yinlian.wssc.web.util.GetIpAddresss;

@Component("orderUpdaterecordsService")
public class OrderUpdaterecordsServiceImpl implements OrderUpdaterecordsService {

	@Autowired
	private  OrderUpdaterecordsMapper orderUpdaterecordsMapper;
	
	@Override
	public int addOrderRecords(OrderUpdaterecords record) throws Exception {
		return orderUpdaterecordsMapper.insert(record);
	}

	@Override
	public int addOrderUpadateRecords(String field, String oldValue, String newValue, Integer orderid, Integer userid,
			String username,String ip) throws Exception {
		OrderUpdaterecords records=new OrderUpdaterecords();
		records.setField(field);
		records.setOldvalue(oldValue);
		records.setNewvalue(newValue);
		records.setOrderid(orderid);
		records.setCreatetime(new Date());
		records.setCreateuserid(userid);
		records.setCreateusername(username);
		//String ip = GetIpAddresss.getIpAddr();
		records.setIp(ip);
		return orderUpdaterecordsMapper.insertSelective(records);
	}

}
