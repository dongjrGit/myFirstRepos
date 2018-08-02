package com.mobile.application.service.log;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.entity.TBasisLogDetail;
import com.mobile.application.entity.TBasisLogType;
import com.mobile.application.vo.CommonVO;

public interface ILogService{

	/**
	 * Description : 获取操作日志类型列表
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param N/A
	 * @return 查询结果集
	 * @exception BusinessException
	 */
	CommonVO qryLogType(String modelName);

	/**
	 * Description :保存操作日志类型
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param url : 操作请求相对路径
	 * @param modelName : 模块名称
	 * @param operaName : 操作名称
	 * @param description : 操作描述
	 * @param option : 新增、修改
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	CommonVO saveLogType(String url, String modelName, String operaName,
			String description, String option) throws BusinessException;

	/**
	 * Description :删除操作日志类型
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param String uri : 操作请求相对路径
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	CommonVO removeLogType(String uri);

	/**
	 * Description : 获取操作日志类型列表，主要用于系统启动完成时，加载到ServletContext
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param N/A
	 * @return 将日志类型封装成map返回
	 * @exception BusinessException
	 */
	Map<String, TBasisLogType> qryLogTypeMap();

	/**
	 * Description : 保存日志详细，记录系统操作日志
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param 系统详细操作日志实体类TBasisLogDetail
	 * @return N/A
	 * @exception BusinessException
	 */
	void saveLogDetail(TBasisLogDetail tBasisLogDetail);

	/**
	 * Description : 获取后台操作日志列表，主要用于系统启动完成时，加载到ServletContext
	 * @author 罗杨
	 * @version 1.01
	 * @param endTime 
	 * @see N/A
	 * @param N/A
	 * @return 将操作日志封装成map返回
	 * @exception BusinessException
	 */
	Map<String, Object> qryLogDetail(int indexPage, int indexSize, String modelName, String userCode, String startTime, String endTime);

	void exportLogReportExcel(HttpServletResponse response, String modelName, String userCode, String startTime, String endTime);
	
}
