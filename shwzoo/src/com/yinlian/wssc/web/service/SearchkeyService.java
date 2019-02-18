/*
 * @(#) SearchkeyService.java 2016年6月23日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.SearchkeyDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Searchkey;

public interface SearchkeyService {

	/**
	 * 列表
	 * 
	 * @author kh.wang
	 * @since 2016年6月23日
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	 PageBean getListByPage(int page, int size) throws Exception;
	 
	 
	 boolean deltel(int id)throws Exception;



	boolean orderByUpd(SearchkeyDto dto)throws Exception;



	boolean insert(Searchkey record)throws Exception;
	
	public List<Searchkey> getSearchkeys(int usesites)throws Exception;
	
}
