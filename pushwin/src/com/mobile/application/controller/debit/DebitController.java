package com.mobile.application.controller.debit;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.service.debit.IDebitService;
import com.mobile.application.vo.CommonVO;

@Controller
@RequestMapping("/debit")
public class DebitController {
	@Autowired
	private IDebitService debitService;
	
	/**
	 * 进入借记卡审核列表页面
	 */
	@RequestMapping("/debitCheckInit")
	public ModelAndView initCreditCheck(){
		return new ModelAndView("debit/debit_checklist", null);
	}
	
	/**
	 * 查询借记卡审核列表
	 * @param pageIndex
	 * @param pageSize
	 * @param name 客户、经理名字
	 * @return
	 */
	@RequestMapping("/checkList")
	@ResponseBody
	public CommonVO debitCheckList(String pageIndex, String pageSize,String name,String status,String startTime,String endTime ,String accountType ){
		return debitService.qryDebitCheckList(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), name,status,startTime,endTime, accountType);
	}
	
	/** 查询借记卡审核进件详细信息
	 * @param debitId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/checkDetail")
	public ModelAndView creditCheckDetail(String debitId) throws BusinessException{
		Map<String, Object> debitDetail = debitService.qryDebitCheckDetail(debitId);
		return new ModelAndView("debit/debit_checkdetail", debitDetail);
	}
	
	/**
	 * 查询借记卡审核历史信息
	 * @param debitId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/debitRecord")
	@ResponseBody
	public CommonVO creditCheckRecord(String debitId) throws BusinessException{
		CommonVO checkRecord = debitService.qryDebitCheckRecord(debitId);
		return checkRecord;
	}
	
	/**
	 * 提交借记卡审核结果
	 * @param debitId 进件Id
	 * @param checkResult 审核结果
	 * @param checkOpinion 审核意见
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/submitCheckResult")
	@ResponseBody
	public CommonVO submitCheckResult(String debitId, String checkResult, String checkOpinion, HttpServletRequest request) throws BusinessException{
		CommonVO commonVO = debitService.submitCheckResult(debitId, checkResult, checkOpinion, request);
		return commonVO;
	}
}
