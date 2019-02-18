package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.CompleteStatus;
import com.yinlian.wssc.web.mapper.OrderactivityChildHistoryMapper;
import com.yinlian.wssc.web.po.OrderactivityChildHistory;
import com.yinlian.wssc.web.service.OrderactivityChildHistoryService;

@Component("orderactivityChildHistoryService")
public class OrderactivityChildHistoryServiceImpl implements OrderactivityChildHistoryService {

	@Autowired
	private OrderactivityChildHistoryMapper orderactivityChildHistoryMapper;

	@Override
	public int inserts(List<OrderactivityChildHistory> ocrecords) throws Exception {
		int i = 0;
		for (OrderactivityChildHistory record : ocrecords) {
			i += orderactivityChildHistoryMapper.insert(record);
		}
		return i;
	}

	@Override
	public List<OrderactivityChildHistory> getListByOrderCode(String code) throws Exception {
		
		return orderactivityChildHistoryMapper.getListByOrderCode(code);
	}

	@Override
	public int updateStatusById(Integer id, Integer status) throws Exception {
		return orderactivityChildHistoryMapper.updateStatusById(id,status);
	}

	@Override
	public int updatePayMoney(String orderNum, int orderid, float payMoney) throws Exception {
		return orderactivityChildHistoryMapper.updatePayMoney(orderNum,orderid,payMoney,CompleteStatus.已完成.getValue());
	}

}
