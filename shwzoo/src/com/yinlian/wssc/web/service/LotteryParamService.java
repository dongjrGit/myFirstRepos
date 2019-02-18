/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;
import java.util.Map;

import com.yinlian.wssc.web.po.LotteryParam;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年5月18日 下午1:49:00 Exp $
 */
public interface LotteryParamService {

    /**
     *@param type 
     * @return
     */
    LotteryParam selectByType(Integer type) throws Exception;

    /**
     *@return
     */
    Map<String, Object> queryName() throws Exception;

    /**
     *@return
     */
    Map<String, Object> queryValue() throws Exception;

    /**
     *@return
     */
    List<LotteryParam> selectList() throws Exception;

    /**
     *@param id
     * @return 
     */
    LotteryParam queryById(Integer id) throws Exception;

    /**
     *@param id
     *@return
     */
    int deleteById(Integer id) throws Exception;

    /**
     *@param param
     *@return
     */
    int insert(LotteryParam param) throws Exception;

    /**
     *@param param
     *@return
     */
    int update(LotteryParam param) throws Exception;

    /**
     * 根据规格id 获取奖项参数
     * @param ruleid
     * @return
     */
	LotteryParam getByLId(Integer ruleid) throws Exception;

	
}
