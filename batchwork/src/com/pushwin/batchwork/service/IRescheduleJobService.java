package com.pushwin.batchwork.service;

import com.pushwin.batchwork.vo.common.CommonVO;


public interface IRescheduleJobService {
		public CommonVO queryTaskList();
		public CommonVO queryTaskHistory(String taskID);
}
