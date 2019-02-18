package com.yinlian.wssc.web.service;


import java.util.List;

import com.yinlian.wssc.web.dto.UserfinanceDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.util.CriteriaFinance;

public interface UserFinanceService {

	/**
	 * 根据单号获取用户财务列表
	 * 
	 * @param code
	 *            单号
	 * @return
	 * @throws Exception
	 */
	List<Userfinance> getListByNumber(String code) throws Exception;

	/**
	 * 财务Id 获取用户财务信息
	 * 
	 * @param status
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Userfinance getUserFinance(Integer status, int id) throws Exception;

	/**
	 * 分页查询
	 * 
	 * @param criteria
	 * @param pc
	 *            当前页
	 * @param ps
	 *            每页大小
	 * @return
	 */
	PageBean selectPage(CriteriaFinance criteria, Integer pc, Integer ps) throws Exception;

	int insert(Userfinance userfinance) throws Exception;
	
	List<Userfinance> getUseridFinance(int userID) throws Exception;
	
	List<Userfinance> getUseridandTimeFinance(int userID,String start,String end) throws Exception;
	
	List<Userfinance> getUseridandMonthFinance(int userID,String time) throws Exception;

	PageBean selectShopFinancePage(CriteriaFinance criteria, int index, int size)throws Exception;

	List<UserfinanceDto> selectShopFinanceList(CriteriaFinance criteria)throws Exception;
	
	
}
