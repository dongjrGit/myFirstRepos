package com.mobile.application.service.credit;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.vo.CommonVO;

public interface ICreditService {
	public CommonVO qryCreditCheckList(int indexPage, int indexSize,String name, String creditKind, String status, String startTime, String endTime);

	public Map<String, Object> qryCreditCheckDetail(String creditId) throws BusinessException;

	public CommonVO qrycreditCheckRecord(String creditId);

	public CommonVO submitCheckResult(String creditId, String checkResult,
			String checkOpinion, HttpServletRequest request);
}
