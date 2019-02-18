/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.api.app.dto.Api_ProvinceDto;
import com.yinlian.pc.dto.ProvinceDto;
import com.yinlian.pc.dto.TopicBig;
import com.yinlian.wssc.web.mapper.AreaMapper;
import com.yinlian.wssc.web.mapper.CityMapper;
import com.yinlian.wssc.web.mapper.ProvinceMapper;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.AreaExample;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.CityExample;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.ProvinceExample;
import com.yinlian.wssc.web.service.ProvinceServcice;

/**
 * 
 * @author Administrator
 * @version $Id: ProvinceServciceImpl.java, v 0.1 2016年3月10日 下午8:31:50 Administrator Exp $
 */
public class ProvinceServciceImpl implements ProvinceServcice {

    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private CityMapper     cityMapper;
    @Autowired
    private AreaMapper     areaMapper;

    /** 
     * @see com.yinlian.wssc.web.service.ProvinceServcice#selectAll()
     */
    @Override
    public List<Province> selectAll() throws Exception {
        ProvinceExample example = new ProvinceExample();
        List<Province> list = provinceMapper.selectByExample(example);

        return list;
    }

    /**
     * 查询一级所有的区域
     */

    @Override
    public List<Province> selectList() throws Exception {
        ProvinceExample example = new ProvinceExample();
        List<Province> list = provinceMapper.selectByExample(example);
        for (Province province : list) {
            CityExample cityExample = new CityExample();
            CityExample.Criteria criteria = cityExample.createCriteria();
            String code = province.getCode();
            criteria.andProvincecodeEqualTo(code);
            List<City> cities = cityMapper.selectByExample(cityExample);
            for (City city : cities) {
                AreaExample areaExample = new AreaExample();
                AreaExample.Criteria aCriteria = areaExample.createCriteria();
                String cityCode = city.getCode();
                aCriteria.andCitycodeEqualTo(cityCode);
                List<Area> areas = areaMapper.selectByExample(areaExample);
                city.setList(areas);
            }

            province.setList(cities);
        }
        return list;
    }

    /**
     * 
     */

    @Override
    public int deleteProvinceByCode(String code) throws Exception {
        ProvinceExample example = new ProvinceExample();
        ProvinceExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        return provinceMapper.deleteByExample(example);
    }

    @Override
    public int updateProvinceByCode(String name, String code) throws Exception {
        ProvinceExample example = new ProvinceExample();
        ProvinceExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<Province> list = provinceMapper.selectByExample(example);
        Province recode = list.get(0);
        Province province = new Province();
        province.setName(name);
        province.setCode(code);
        BeanUtils.copyProperties(province, recode);

        return provinceMapper.updateByPrimaryKeySelective(recode);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ProvinceServcice#queryByCode(java.lang.String)
     */
    @Override
    public Province queryByCode(String code) throws Exception {
        ProvinceExample example = new ProvinceExample();
        ProvinceExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<Province> list = provinceMapper.selectByExample(example);
        Province recode=null;
        if(list.size()>0){
         recode = list.get(0);
        }
        return recode;
    }

    /**
     * 根据code查询省
     * @see com.yinlian.wssc.web.service.ProvinceServcice#selectProvinceByCode(java.lang.String)
     */
    @Override
    public List<Province> selectProvinceByCode(String code) throws Exception {
        ProvinceExample example = new ProvinceExample();
        ProvinceExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<Province> list = provinceMapper.selectByExample(example);
        return list;
    }

    @Override
    public Province selectProvinceByName(String name) throws Exception {

        return provinceMapper.selectByName(name);
    }

    /**
     * 添加城市
     * @see com.yinlian.wssc.web.service.ProvinceServcice#add(java.lang.String, java.lang.String)
     */
    
    @Override
    public int add(String name, String code) throws Exception {
        Province province = new Province();
        province.setCode(code);
        province.setName(name);
        return provinceMapper.insert(province);
    }

    /**
     * 查询所有的省
     * @see com.yinlian.wssc.web.service.ProvinceServcice#query()
     */
    @Override
    public List<Province> query() throws Exception {
        ProvinceExample example = new ProvinceExample();

        return provinceMapper.selectByExample(example);
    }

	@Override
	public List selectPro() throws Exception {
		 ProvinceExample example = new ProvinceExample();
	     List<Province> list = provinceMapper.selectByExample(example);
	     return list;
	}

	@Override
	public List<TopicBig> findBigAll() throws Exception {
		return provinceMapper.selectBigAll();
	}

	@Override
	public Province findById(Integer provinceid) throws Exception {
		return provinceMapper.selectByPrimaryKey(provinceid);
	}

	@Override
	public List<ProvinceDto> findProBigAll() throws Exception {
		return provinceMapper.selectProBigAll();
	}
	@Override //Api_AreasDto
	public List<Api_ProvinceDto> getplaces() throws Exception{
		return provinceMapper.getplaces();
	}
}
