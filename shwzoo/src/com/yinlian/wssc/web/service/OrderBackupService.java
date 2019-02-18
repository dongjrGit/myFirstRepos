package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.OrderBackup;

public interface OrderBackupService {

	/**
	 * 批量备份 订单信息
	 * @param ordersbc
	 * @throws Exception
	 */
	void add(List<OrderBackup> ordersbc) throws Exception;

}
