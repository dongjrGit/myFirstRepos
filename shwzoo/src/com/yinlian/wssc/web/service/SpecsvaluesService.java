/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.SkuSpecsv;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 
 * @author Administrator
 * @version $Id: Specsvalues.java, v 0.1 2016年2月29日 上午11:04:56 Administrator Exp $
 */
public interface SpecsvaluesService {

    /**
     * 根据规格id查询规格值得分页信息
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     * @throws Exception
     */
    PageBean querySpecsValuePageBySpecsId(Criteria criteria, Integer pc, Integer ps)
                                                                                    throws Exception;

    /**
     * 根据id删除规格值信息
     * @param id
     * @return 
     */
    int deleteById(Integer id) throws Exception;

    /**
     * 插入一个规格值
     * @param record
     * @return 
     */
    int insertRecord(Specsvalues record) throws Exception;
    
    /**
     * 修改规格值状态
     * @param status
     * @param id
     * @return
     * @throws Exception
     */
    int updateStatus(Integer status,Integer id) throws Exception;
    
    /**
     * 删除规格值时验证
     * @param svid
     * @return
     * @throws Exception
     */
    SkuSpecsv getbyValueID(int svid)throws Exception;

    /**
     * 判断是否有该属性值
     * 
     * @param specsid
     * @param value
     * @return
     */
	int ishave(Integer specsid, String value) throws Exception;
}
