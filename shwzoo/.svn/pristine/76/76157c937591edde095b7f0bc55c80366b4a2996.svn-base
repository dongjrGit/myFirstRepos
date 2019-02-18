/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.api.app.dto.CitySpDto;
import com.yinlian.wssc.web.mapper.CityMapper;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.CityExample;
import com.yinlian.wssc.web.service.CityServcie;

/**
 * 城市的业务类实现层
 * @author Administrator
 * @version $Id: CityServcieImpl.java, v 0.1 2016年3月10日 下午8:34:17 Administrator Exp $
 */
public class CityServcieImpl implements CityServcie {

    @Autowired
    private CityMapper cityMapper;

    /** 
     * @see com.yinlian.wssc.web.service.CityServcie#selectAll(java.lang.Integer)
     */
    @Override
    public List<City> selectByProvinceCode(String code) throws Exception {
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        criteria.andProvincecodeEqualTo(code);
        return cityMapper.selectByExample(example);
    }

    /**
     * 
     */

    @Override
    public int deleteCityByCode(String code) throws Exception {
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        return cityMapper.deleteByExample(example);
    }

    /**
     * 
     */

    @Override
    public int updateCityByCode(String name, String code) throws Exception {
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<City> list = cityMapper.selectByExample(example);
        City recode = list.get(0);
        City city = new City();
        city.setName(name);
        city.setCode(code);
        BeanUtils.copyProperties(city, recode);
        return cityMapper.updateByPrimaryKeySelective(recode);
    }

    /** 
     * @see com.yinlian.wssc.web.service.CityServcie#queryByCode(java.lang.String)
     */
    @Override
    public City queryByCode(String code) throws Exception {
        CityExample example = new CityExample();
        CityExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<City> list = cityMapper.selectByExample(example);
        City record = list.get(0);
        return record;
    }

    @Override
    public City selectByName(String name) throws Exception {

        return cityMapper.selectByName(name);
    }

    /**
     * 添加城市
     * @see com.yinlian.wssc.web.service.CityServcie#add(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int add(String name, String code, String fcode) throws Exception {
        City city = new City();
        city.setName(name);
        city.setCode(code);
        city.setProvincecode(fcode);
        return cityMapper.insert(city);
    }

    /**
     * 查询所有城市
     * @see com.yinlian.wssc.web.service.CityServcie#query()
     */
    @Override
    public List<City> query() throws Exception {
        CityExample example = new CityExample();

        return cityMapper.selectByExample(example);
    }

	@Override
	public List selectCity(String parentCode) throws Exception {
		
		return cityMapper.queryCity(parentCode);
	}

	@Override
	public City selectCityByName(String name, String code) throws Exception {
		
		return cityMapper.queryCityCode(name,code);
	}

	@Override
	public List<CitySpDto> getCityAllList() throws Exception {
		return cityMapper.getCityAllList();
	}
}
