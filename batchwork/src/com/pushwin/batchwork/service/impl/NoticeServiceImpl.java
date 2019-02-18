package com.pushwin.batchwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushwin.batchwork.dao.INoticeDao;
import com.pushwin.batchwork.service.INoticeService;

@Service("noticeService")
public class NoticeServiceImpl implements INoticeService{
	@Autowired
	private INoticeDao noticeDao;
	
	@Override
	@Transactional
	public void noticeExpire(){
		noticeDao.noticeExpire();
	}
}
