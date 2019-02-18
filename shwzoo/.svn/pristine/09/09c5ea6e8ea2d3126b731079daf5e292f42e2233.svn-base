/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.pc.dto.FreightParamDto;
import com.yinlian.pc.dto.FreightReturnDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.po.FreightAttr;
import com.yinlian.wssc.web.po.Sendtemplate;
import com.yinlian.wssc.web.po.V_Freights;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaSendTemplate;

/**
 * 商品状态记录接口
 * 
 * @author Administrator
 * @version $Id: FreightService.java, v 0.1 2016年3月7日 下午7:49:22 Administrator
 *          Exp $
 */
public interface ProductUpdateRecordsService {

	
	/**
	 * 商品上下架状态变更记录
	 * @param field
	 * @param oldValue
	 * @param newValue
	 * @param spuId
	 * @param userid
	 * @param username
	 * @return
	 * @throws Exception
	 */
    int insertProSXJ(String field, String oldValue,String newValue,Integer spuId,Integer userid,String username,String ip) throws Exception;
    
    int insertProTJ(String field, String oldValue,String newValue,Integer skuId,Integer userid,String username,String ip) throws Exception;
}
