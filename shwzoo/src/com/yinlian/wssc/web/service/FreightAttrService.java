/*
 * YinLian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.FreightAttr;
import com.yinlian.wssc.web.util.CriteriaFreightAttr;

/**
 * FreightAttrService.java
 * @author Liang.ma.s
 * @version $Id: FreightAttrService.java, v 0.1 2016年4月5日 上午11:01:22 Administrator Exp $
 */
public interface FreightAttrService {

    /**
     * 根据模板id查询模板的详细细信息
     * @param id 模板id
     * @return
     */
    List<FreightAttr> selectByFreightId(Integer id) throws Exception;

	PageBean queryAttrByFrightId(CriteriaFreightAttr criteria, Integer pc, Integer ps) throws Exception;

}
