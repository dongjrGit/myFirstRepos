package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisLogDetail;

public interface ILogDao extends IBaseDAO<TBasisLogDetail>{

	List<?> qryLogType(String modelName);

	List<?> qryLogDetail(String modelName, String userCode, String startTime, String endTime);

	List<?> qryLogDetail(int indexPage, int indexSize, String modelName, String userCode, String startTime, String endTime);

	List<?> getExportLog(String modelName, String userCode,
			String startTime, String endTime);

}
