package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.Enums.StoreLevelEnum;
import com.yinlian.wssc.web.config.StoreLevelCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.StoreLevel;

/**
 * 店铺等级
 * @author mjx
 *
 */
public interface StoreLevelService {
	public int update(StoreLevel orm) throws Exception;
	public int add(StoreLevel orm) throws Exception;
	public int delById(int id) throws Exception;
	public StoreLevel getById(int id) throws Exception;
	public PageBean getList(StoreLevelCriteria where,int pg,int size) throws Exception;
	public List<StoreLevel> getAllList() throws Exception;
	public int updateStatusById(StoreLevelEnum status,int id) throws Exception;
	public int updateSortById(int sort,int id) throws Exception;
	
}
