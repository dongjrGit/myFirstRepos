package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ZL_BaseInfoMapper;
import com.yinlian.wssc.web.po.ZL_BaseInfo;
import com.yinlian.wssc.web.po.ZL_BaseInfoExample;
import com.yinlian.wssc.web.po.giftcard;
import com.yinlian.wssc.web.service.ZL_BaseInfoService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("ZL_BaseInfoService")
public class ZL_BaseInfoServiceImpl implements ZL_BaseInfoService {

	@Autowired
	private ZL_BaseInfoMapper baseinfo;
	
	@Override
	public int countByExample(ZL_BaseInfoExample example) {
		// TODO Auto-generated method stub
		return baseinfo.countByExample(example);
	}

	@Override
	public int deleteByExample(ZL_BaseInfoExample example) {
		// TODO Auto-generated method stub
		return baseinfo.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return baseinfo.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ZL_BaseInfo record) {
		// TODO Auto-generated method stub
		return baseinfo.insert(record);
	}

	@Override
	public int insertSelective(ZL_BaseInfo record) {
		// TODO Auto-generated method stub
		return baseinfo.insertSelective(record);
	}

	@Override
	public PageBean selectListPage(Criteria criteria, Integer pc, Integer ps) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
		PageBean pBean = pageBeanUtil.getPage();
		List<ZL_BaseInfo> list=baseinfo.selectListPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public List<ZL_BaseInfo> selectByExample(ZL_BaseInfoExample example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ZL_BaseInfo selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return baseinfo.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(ZL_BaseInfo record,
			ZL_BaseInfoExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByExample(ZL_BaseInfo record, ZL_BaseInfoExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(ZL_BaseInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ZL_BaseInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ZL_BaseInfo> selectGroupBy() {
		 
		return baseinfo.selectGroupBy();
	}

}
