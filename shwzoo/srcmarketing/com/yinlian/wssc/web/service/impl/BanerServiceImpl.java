package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.BanerMapper;
import com.yinlian.wssc.web.po.Baner;
import com.yinlian.wssc.web.po.CriteriaBaner;
import com.yinlian.wssc.web.service.BanerService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("BanerService")
public class BanerServiceImpl implements BanerService {

	@Autowired
	private BanerMapper banerMapper;

	@Override
	public int insertBaner(Baner baner) throws Exception {
		return banerMapper.insertSelective(baner);
	}

	@Override
	public int updateBanerById(Baner baner) throws Exception {
		return banerMapper.updateByPrimaryKeySelective(baner);
	}

	@Override
	public Baner selBanerById(Integer id) throws Exception {
		return banerMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageBean selBaner(CriteriaBaner criteria, Integer index, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<Baner> list = banerMapper.queryBanerPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public int delBaner(Integer id) throws Exception {
		return banerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Baner> getList() throws Exception {
		return banerMapper.getList();
	}

}
