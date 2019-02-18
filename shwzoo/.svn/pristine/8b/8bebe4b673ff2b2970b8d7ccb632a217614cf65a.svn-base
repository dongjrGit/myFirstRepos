package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.OrderactivityChildHistory;

public interface OrderactivityChildHistoryService {

	int inserts(List<OrderactivityChildHistory> ocrecords) throws Exception;

	/**
	 * 根据订单编号 获取订单活动历史记录信息
	 * @param code 订单编号 
	 * @return
	 * @throws Exception
	 */
	List<OrderactivityChildHistory> getListByOrderCode(String code) throws Exception;

	int  updateStatusById(Integer id, Integer status)throws Exception;

	/**
	 * 付款成功后更新活动历史记录信息状态
	 * @param orderNum
	 * @param orderid
	 * @param payMoney
	 * @return
	 * @throws Exception
	 */
	int updatePayMoney(String orderNum, int orderid, float payMoney)throws Exception;

}
