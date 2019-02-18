/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.LotteryParamTypeEnum;
import com.yinlian.wssc.web.mapper.LotteryParamMapper;
import com.yinlian.wssc.web.po.LotteryParam;
import com.yinlian.wssc.web.po.LotteryParamExample;
import com.yinlian.wssc.web.service.LotteryParamService;
import com.yinlian.wssc.web.util.ConstanValue;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年5月18日 下午1:49:18 Exp $
 */
@Component("lotteryParamService")
public class LotteryParamServiceImpl implements LotteryParamService {

    @Autowired
    private LotteryParamMapper lotteryParamMapper;

    /**
     * @see com.yinlian.wssc.web.service.LotteryParamService#selectByType()
     */
    @Override
    public LotteryParam selectByType(Integer type) throws Exception {
        LotteryParam lotteryParam = new LotteryParam();
        LotteryParamExample example = new LotteryParamExample();
        LotteryParamExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(type);
        List<LotteryParam> list = lotteryParamMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            lotteryParam = list.get(0);
        }
        return lotteryParam;
    }

    /**
     * @see com.yinlian.wssc.web.service.LotteryParamService#query()
     */
    @Override
    public Map<String, Object> queryName() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String name1 = lotteryParamMapper.selectNameByType(LotteryParamTypeEnum.代金卷.getValue());
        String name2 = lotteryParamMapper.selectNameByType(LotteryParamTypeEnum.商品.getValue());
        map.put(ConstanValue.FIRST_PRIZE, name1);
        map.put(ConstanValue.SECOND_PRIZE, name2);
        return map;
    }

    /**
     * @see com.yinlian.wssc.web.service.LotteryParamService#queryValue()
     */
    @Override
    public Map<String, Object> queryValue() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        int productvalue = lotteryParamMapper.selectValueByType(LotteryParamTypeEnum.商品.getValue());
        int beanvalue = lotteryParamMapper.selectValueByType(LotteryParamTypeEnum.积分.getValue());
        map.put(ConstanValue.SECOND_PRIZE, productvalue);
        map.put(ConstanValue.THRID_PRIZE, beanvalue);
        return map;
    }

    /**
     * @see com.yinlian.wssc.web.service.LotteryParamService#selectList()
     */
    @Override
    public List<LotteryParam> selectList() throws Exception {
        LotteryParamExample example = new LotteryParamExample();
        return lotteryParamMapper.selectByExample(example);
    }

    /**
     * @see com.yinlian.wssc.web.service.LotteryParamService#queryById(java.lang.Integer)
     */
    @Override
    public LotteryParam queryById(Integer id) throws Exception {

        return lotteryParamMapper.selectByPrimaryKey(id);
    }

    /**
     * @see com.yinlian.wssc.web.service.LotteryParamService#deleteById(java.lang.Integer)
     */
    @Override
    public int deleteById(Integer id) throws Exception {

        return lotteryParamMapper.deleteByPrimaryKey(id);
    }

    /**
     * @see com.yinlian.wssc.web.service.LotteryParamService#insert(com.yinlian.wssc.web.po.LotteryParam)
     */
    @Override
    public int insert(LotteryParam param) throws Exception {
       /* Integer type = param.getType();
        LotteryParamExample example = new LotteryParamExample();
        LotteryParamExample.Criteria criteria = example.createCriteria();
        criteria.andTypeEqualTo(type);
        List<LotteryParam> list = lotteryParamMapper.selectByExample(example);
        if (list != null && list.size() > 0) {

            return -1;
        }*/
        return lotteryParamMapper.insert(param);
    }

    /**
     * @see com.yinlian.wssc.web.service.LotteryParamService#update(com.yinlian.wssc.web.po.LotteryParam)
     */
    @Override
    public int update(LotteryParam param) throws Exception {
        return lotteryParamMapper.updateByPrimaryKey(param);
    }

	@Override
	public LotteryParam getByLId(Integer ruleid) throws Exception {
	
		return lotteryParamMapper.getByLId(ruleid);
	}

	
}
