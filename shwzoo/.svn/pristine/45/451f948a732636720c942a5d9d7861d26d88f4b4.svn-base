package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.HotcityMapper;
import com.yinlian.wssc.web.po.CriteriaHotCity;
import com.yinlian.wssc.web.po.Hotcity;
import com.yinlian.wssc.web.service.HotCityService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("HotCityService")
public class HotCityServiceImpl implements HotCityService {

	@Autowired
	private HotcityMapper hotcityMapper;

	@Override
	public PageBean getHotCity(CriteriaHotCity criteria, Integer index, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<Hotcity> list = hotcityMapper.queryHotCityPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public int insertHotCity(Hotcity hotcity) throws Exception {
		return hotcityMapper.insertSelective(hotcity);
	}

	@Override
	public int updateHotCity(Hotcity hotcity) throws Exception {
		return hotcityMapper.updateByPrimaryKeySelective(hotcity);
	}

	@Override
	public int deleteHotCity(Integer id) throws Exception {
		return hotcityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Hotcity selHotCityById(Integer id) throws Exception {
		return hotcityMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Hotcity> getAllList() throws Exception {
		return hotcityMapper.getAllList();
	}

	@Override
	public Hotcity selCity(String code) throws Exception {
		return hotcityMapper.selCity(code);
	}

}
