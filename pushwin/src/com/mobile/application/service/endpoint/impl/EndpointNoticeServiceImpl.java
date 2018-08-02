package com.mobile.application.service.endpoint.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.dao.INoticeDao;
import com.mobile.application.service.endpoint.IEndpointNoticeService;
import com.mobile.application.vo.CommonVO;

@Service
public class EndpointNoticeServiceImpl implements IEndpointNoticeService{

	@Autowired
	INoticeDao noticeDao;
	
	@Override
	@Transactional(readOnly = true)
	public CommonVO qryNoticeList(String userId, String noticeType, String pageIndex, String indexSize) {
		String noticeCount = noticeDao.qryNoticeCount(userId, noticeType, false);
		List<?> noticeList = noticeDao.qryNoticeList(userId, noticeType,pageIndex, indexSize);
		return new CommonVO(true, noticeList, noticeCount);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CommonVO qryNoticeCount(String userId, String noticeType) {
		String noticeCount = noticeDao.qryNoticeCount(userId, noticeType, true);
		return new CommonVO(true, new ArrayList(), noticeCount);
	}
	
	@Override
	@Transactional
	public CommonVO readNotice(String pushNoticeId) {
		noticeDao.readNotice(pushNoticeId);
		return new CommonVO(true, "更新成功");
	}

	@Override
	@Transactional
	public CommonVO qryNoRedNoticeList(String userId, String noticeType) {
		List<?> noticeList = noticeDao.qryNoRedNoticeList(userId, noticeType);
		return  new CommonVO(true, noticeList, "");
	}
}
