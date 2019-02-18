/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 经采豆的业务类
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月26日 下午9:35:42 Exp $
 */
public interface HistorybeansService {

    /**
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     */
    PageBean selectPage(Criteria criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 删除
     *@param id
     *@return
     *@throws Exception
     */
    int deleteById(Integer id) throws Exception;

}
