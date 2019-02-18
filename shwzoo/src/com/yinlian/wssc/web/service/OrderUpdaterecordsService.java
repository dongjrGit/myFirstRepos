package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.po.OrderUpdaterecords;

public interface OrderUpdaterecordsService {

	/**
	 * 新增订单操作记录 
	 * @param ou
	 * @return
	 */
	int addOrderRecords(OrderUpdaterecords ou) throws Exception ;
	
    int addOrderUpadateRecords(String field, String oldValue,String newValue,Integer orderid,Integer userid,String username,String ip) throws Exception;
}
