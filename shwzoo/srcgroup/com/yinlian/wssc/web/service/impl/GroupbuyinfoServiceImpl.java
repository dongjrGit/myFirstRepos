package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.dto.Groupbuyinfo_Api_Dto;
import com.yinlian.wssc.search.Groupbuyinfo_Api_Criteria;
import com.yinlian.wssc.search.P_Groupbuyinfo_Criteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.GroupbuyinfoMapper;
import com.yinlian.wssc.web.mapper.VGroupShopMapper;
import com.yinlian.wssc.web.po.Groupbuyinfo;
import com.yinlian.wssc.web.po.VGroupShop;
import com.yinlian.wssc.web.service.GroupbuyinfoService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("groupbuyinfoService")
public class GroupbuyinfoServiceImpl implements GroupbuyinfoService {

	@Autowired
	private GroupbuyinfoMapper groupbuyinfoMapper;
	@Autowired
	private VGroupShopMapper vGroupShopMapper;

	public int add(Groupbuyinfo po) throws Exception {
		return groupbuyinfoMapper.insert(po);
	}

	public int update(Groupbuyinfo po) throws Exception {
		return groupbuyinfoMapper.updateByPrimaryKey(po);
	}

	public int del(int id) throws Exception {
		return groupbuyinfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Groupbuyinfo getById(int id) throws Exception {
		return groupbuyinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageBean getList(P_Groupbuyinfo_Criteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
		PageBean pageBean = pageBeanUtil.getPage();
		List<VGroupShop> beanList = vGroupShopMapper.getListPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public PageBean getV_G_SkuList(P_Groupbuyinfo_Criteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
		PageBean pageBean = pageBeanUtil.getPage();
		// List<VGroupSku> beanList = vGroupSkuMapper.getListPage(pageBeanUtil);
		// pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public Integer changestate(Integer id, Integer state) throws Exception {
		return groupbuyinfoMapper.changestate(id, state);
	}

	@Override
	public PageBean getApiList(Groupbuyinfo_Api_Criteria criteria, Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Groupbuyinfo_Api_Dto> beanList = vGroupShopMapper.getApiListPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public int editAudit(Integer id, Integer auditing) throws Exception {
		return groupbuyinfoMapper.updateAudit(id, auditing);
	}

	@Override
	public VGroupShop getV_G_ShopById(int id) throws Exception {

		return vGroupShopMapper.getById(id);
	}
}
