package com.techown.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techown.wssc.web.mapper.ZooBannerMapper;
import com.techown.wssc.web.po.CriteriaBanner;
import com.techown.wssc.web.po.ZooBanner;
import com.techown.wssc.web.service.ZooBannerService;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.PageBeanUtil;
@Component("zooBannerService")
public class ZooBannerServiceImpl implements ZooBannerService {
	@Autowired
	private ZooBannerMapper  zooBannerMapper;
	@Override
	public PageBean querylist(CriteriaBanner criteria, int index, int size) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<ZooBanner> list = zooBannerMapper.querylistPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}
	@Override
	public void updateStatus(Integer id, Integer status, String operator) {
		zooBannerMapper.updateStatus(id,status,new Date(),operator);
	}
	@Override
	public void dellist(Integer id) {
		zooBannerMapper.deleteByPrimaryKey(id);
	}
	@Override
	public void addBanner(ZooBanner bean) {
		zooBannerMapper.insert(bean);
	}
	@Override
	public ZooBanner selectById(Integer id) {
		return zooBannerMapper.selectByPrimaryKey(id);
	}
	@Override
	public void updateById(ZooBanner bean) {
		zooBannerMapper.updateByPrimaryKey(bean);
	}
	@Override
	public List<ZooBanner> getAppBanner(Integer status) {
		return zooBannerMapper.getAppBanner(status);
	}
	@Override
	public List<ZooBanner> listByTypeId(Integer id) {
		return zooBannerMapper.listByTypeId(id);
	}

}
