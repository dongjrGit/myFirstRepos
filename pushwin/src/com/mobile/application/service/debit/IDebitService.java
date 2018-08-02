package com.mobile.application.service.debit;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.vo.CommonVO;

public interface IDebitService {

	public CommonVO qryDebitCheckList(int indexPage, int indexSize,String name, String status, String startTime, String endTime, String accountType);

	public Map<String, Object> qryDebitCheckDetail(String debitId) throws BusinessException;

	public CommonVO qryDebitCheckRecord(String debitId);

	public CommonVO submitCheckResult(String debitId, String checkResult,
			String checkOpinion, HttpServletRequest request);

}
