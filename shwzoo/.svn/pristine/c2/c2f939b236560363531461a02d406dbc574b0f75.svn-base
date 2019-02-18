/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Searchattr;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 搜索属性的接口类
 * @author Administrator
 * @version $Id: SearchattrService.java, v 0.1 2016年2月29日 下午5:18:29 Administrator Exp $
 */
public interface SearchattrService {

    /**
     * 查询搜索属性的分页信息
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     * @throws Exception
     */
    public PageBean selectSearchattrPage(Criteria criteria, Integer pc, Integer ps)
                                                                                   throws Exception;

    /**
     * 查询搜索属性值的分页信息
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     * @throws Exception
     */
    public PageBean selectSearchvaluePage(Criteria criteria, Integer pc, Integer ps)
                                                                                    throws Exception;

    /**
     * 根据搜索属性的id查询搜索属性的信息
     * @param id
     * @return
     */
    public Searchattr selectSearchAttrById(Integer id) throws Exception;

    /**
     * 更新搜索属性的信息
     * @param searchattr
     * @return 
     */
    public int updateRecord(Searchattr searchattr) throws Exception;

    /**
     * 根据主键删除对象
     * @param id
     * @return 
     */
    public int deleteSearchAttrById(Integer id) throws Exception;

    /**
     * 增加一个搜索属性
     * @param searchattr
     * @return 
     */
    public int insert(Searchattr searchattr) throws Exception;
}
