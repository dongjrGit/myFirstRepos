package com.mobile.application.controller.credit;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.service.credit.ICreditService;
import com.mobile.application.vo.CommonVO;

@Controller
@RequestMapping("/credit")
public class CreditController {
	
	@Autowired
	private ICreditService creditService;
	
	/**
	 * 进入信用卡审核列表页面
	 */
	@RequestMapping("/creditCheckInit")
	public ModelAndView initCreditCheck(){
		return new ModelAndView("credit/credit_checklist", null);
	}
	
	/**
	 * 查询信用卡审核列表
	 * @param pageIndex
	 * @param pageSize
	 * @param name 客户、经理名字
	 * @return
	 */
	@RequestMapping("/checkList")
	@ResponseBody
	public CommonVO creditCheckList(String pageIndex, String pageSize,String name,String creditKind,String status,String startTime,String endTime ){
		return creditService.qryCreditCheckList(Integer.parseInt(pageIndex), Integer.parseInt(pageSize),name,creditKind,status,startTime,endTime);
	}
	
	/**
	 * 查询信用卡审核进件详细信息
	 * @param creditId 进件Id
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/checkDetail")
	public ModelAndView creditCheckDetail(String creditId) throws BusinessException{
		Map<String, Object> creditDetail = creditService.qryCreditCheckDetail(creditId);
		return new ModelAndView("credit/credit_checkdetail", creditDetail);
	}
	
	/**
	 * 查询信用卡审核历史信息
	 * @param creditId 进件Id
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/checkRecord")
	@ResponseBody
	public CommonVO creditCheckRecord(String creditId) throws BusinessException{
		CommonVO checkRecord = creditService.qrycreditCheckRecord(creditId);
		return checkRecord;
	}
	
	/**
	 * 提交信用卡审核结果
	 * @param creditId 进件Id
	 * @param checkResult 审核结果
	 * @param checkOpinion 审核意见
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/submitCheckResult")
	@ResponseBody
	public CommonVO submitCheckResult(String creditId, String checkResult, String checkOpinion, HttpServletRequest request) throws BusinessException{
		CommonVO commonVO = creditService.submitCheckResult(creditId, checkResult, checkOpinion, request);
		return commonVO;
	}
	
}
