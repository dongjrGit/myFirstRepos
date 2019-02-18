package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Logistics;
import com.yinlian.wssc.web.po.LogisticsExample;
import com.yinlian.wssc.web.util.CriteriaLogistics;

public interface LogisticsService {
	Logistics getById(Integer id) throws Exception;

	void svae(Integer id, String name, String code, Integer sort) throws Exception;

	PageBean getList(CriteriaLogistics cl, Integer spage, Integer ssize) throws Exception;

	int  deletebyid(Integer id) throws  Exception;
	
	String getByCode(String logistcode)throws Exception ;

	List<Logistics> selectByExample(LogisticsExample example);
}
