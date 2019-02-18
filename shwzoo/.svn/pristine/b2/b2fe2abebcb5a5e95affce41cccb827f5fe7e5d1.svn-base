package com.yinlian.wssc.web.service;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;

public interface OperateLogService {

	/**
	 * 添加操作记录
	 * @param recordstype 操作类型枚举
	 * @param recordsfrom 来源枚举
	 * @param userid 操作人id
	 * @param username 操作人用户名
	 * @param ip 操作人ip
	 * @param page 页面名称
	 * @param controller 接口路径
	 * @param desc 描述说明
	 * @throws Exception
	 */
	void addLog(OperateRecordsTypeEnum recordstype, OperateRecordsFromEnum recordsfrom, int userid, String username, String ip,
			String page, String controller, String desc) throws Exception;

}
