package com.yinlian.wssc.web.service.impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.AccountNoMapper;
import com.yinlian.wssc.web.po.AccountNo;
import com.yinlian.wssc.web.service.AcctountNoService;

@Component("acctountNoService")
public class AcctountNoServiceImpl implements AcctountNoService {

	@Autowired
	protected AccountNoMapper acctountNoMapper;
    private Lock lock = new ReentrantLock();// 锁对象 
	@Override
	public String getNo() throws Exception {
		lock.lock();
		try {
			Integer no = acctountNoMapper.getNo();
			if (no == null) {
				no = 1;
			}
			AccountNo orm = new AccountNo();
			orm.setNo(no + 1);
			acctountNoMapper.insert(orm);
//			StringBuilder rsl = new StringBuilder();
//			for (int i = 0; i < 7 - String.valueOf(no).length(); i++) {
//				rsl.append("0");
//			}			
//			rsl.append(no);
			return String.format("%07d", orm.getNo());
		} finally {
			lock.unlock();
		}
	}

}
