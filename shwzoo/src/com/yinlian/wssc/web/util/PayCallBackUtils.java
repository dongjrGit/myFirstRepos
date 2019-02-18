package com.yinlian.wssc.web.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.web.service.OrderStatusService;

public class PayCallBackUtils {
	private final Lock queueLock = new ReentrantLock();
	static PayCallBackUtils _PayCallBackUtils;
	private PayCallBackUtils() {

	}

	public static PayCallBackUtils getPayCallBackUtils() {
		if (_PayCallBackUtils == null)
			_PayCallBackUtils = new PayCallBackUtils();
		return _PayCallBackUtils;
	}

	public int add(String groupNum, String userip, String paynum, String price, Integer paytype, BaseResult item,
			OrderStatusService orderStatusService) throws Exception {
		queueLock.lock();
		try {		
			return orderStatusService.updatePayforAlipayCode(groupNum, userip, paynum, price, paytype, item);
		} finally {
			queueLock.unlock();
		}
	}

}
