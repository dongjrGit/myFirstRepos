package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.wssc.web.mapper.AreaMapper;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.AreaExample;
import com.yinlian.wssc.web.service.AreaService;

/**
 * 地区的业务类
 * @author Administrator
 *
 */

public class AreaServiceImpl implements AreaService {

    /**
     * 输出日志的类
     */

    private static final Logger logger = LoggerFactory.getLogger(SpecsvaluesServiceImpl.class);

    @Autowired
    private AreaMapper          areaMapper;

    /** 
     * @see com.yinlian.wssc.web.service.AreaService#selectByCityCode(java.lang.String)
     */
    @Override
    public List<Area> selectByCityCode(String code) throws Exception {
        AreaExample example = new AreaExample();
        AreaExample.Criteria criteria = example.createCriteria();
        criteria.andCitycodeEqualTo(code);
        return areaMapper.selectByExample(example);
    }

    /**
     * 
     */

    @Override
    public int deleteAreaByCode(String code) throws Exception {
        AreaExample example = new AreaExample();
        AreaExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        return areaMapper.deleteByExample(example);
    }

    /**
     * 
     */

    @Override
    public int updateAreaByCode(String name, String code) throws Exception {
        AreaExample example = new AreaExample();
        AreaExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        List<Area> list = areaMapper.selectByExample(example);
        Area record = list.get(0);
        Area area = new Area();
        area.setName(name);
        area.setCode(code);
        BeanUtils.copyProperties(area, record);

        return areaMapper.updateByPrimaryKeySelective(record);
    }

    /** 
     * @see com.yinlian.wssc.web.service.AreaService#queryByCode(java.lang.String)
     */
    @Override
    public Area queryByCode(String code) throws Exception {
        AreaExample example = new AreaExample();
        AreaExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo(code);
        return areaMapper.selectByExample(example).get(0);
    }

    @Override
    public Area selectByName(String name) throws Exception {

        return areaMapper.selectByName(name);
    }

    /**
     * 添加地区
     * @see com.yinlian.wssc.web.service.AreaService#add(java.lang.String, int, int)
     */
    @Override
    public int add(String name, String code, String fcode) throws Exception {
        Area area = new Area();
        area.setName(name);
        area.setCode(code);
        area.setCitycode(fcode);
        return areaMapper.insert(area);
    }

    /**
     * 查询所有地区
     * @see com.yinlian.wssc.web.service.AreaService#query()
     */
    @Override
    public List<Area> query() throws Exception {
        AreaExample example = new AreaExample();
        return areaMapper.selectByExample(example);
    }

	@Override
	public List selectArea(String parentCode) throws Exception {
		
		return areaMapper.queryArea(parentCode);
	}

	@Override
	public Area selectAreaByName(String name, String code) throws Exception {
		
		return areaMapper.selectCodeByName(name,code);
	}

}
