/*
 * @(#) WebsitesConfigService.java 2016年6月23日
 *
 * Copyright (c) 2016, GKLSoft Technology. All Rights Reserved.
 * GKLSoft  Technology. CONFIDENTIAL
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.dto.WebsitesConfigDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.WebsitesConfig;

public interface WebsitesConfigService {
	
	int insertAdd(WebsitesConfig websitesConfig);

	


	PageBean getListByPage(Integer ipage, Integer isize);


	int deltel(Integer toInt);
	
	WebsitesConfigDto selectById(Integer id);
	




	int updateById(WebsitesConfig dto);




	WebsitesConfigDto selectByUserId(Integer integer);
	
	WebsitesConfig selectConfig()throws Exception;


}
