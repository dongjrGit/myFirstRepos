package com.pushwin.batchwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushwin.batchwork.dao.IRescheduleJobDAO;
import com.pushwin.batchwork.service.IRescheduleJobService;
import com.pushwin.batchwork.vo.common.CommonVO;

@Service
public class RescheduleJobServiceImpl implements IRescheduleJobService{

	@Autowired
	IRescheduleJobDAO dao;
	
	@Override
	@Transactional
	public CommonVO queryTaskList() {
		List<?> triggerList = dao.query(null);
		return new CommonVO(true, "«Î«Û≥…π¶", triggerList, triggerList.size() + "");
	}
	@Override
	@Transactional
	public CommonVO queryTaskHistory(String taskID) {
		return null;
	}
}
