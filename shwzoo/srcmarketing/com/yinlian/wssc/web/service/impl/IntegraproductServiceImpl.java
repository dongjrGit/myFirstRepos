package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.IntegraproductMapper;
import com.yinlian.wssc.web.po.CriteriaIntegra;
import com.yinlian.wssc.web.po.IntegraproductWithBLOBs;
import com.yinlian.wssc.web.service.IntegraproductService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("IntegraproductService")
public class IntegraproductServiceImpl implements IntegraproductService {

	@Autowired
	private IntegraproductMapper integraproductMapper;

	/**
	 * 添加积分商品
	 */
	@Override
	public int insertintepro(IntegraproductWithBLOBs iwb) throws Exception {
		return integraproductMapper.insertSelective(iwb);
	}

	/**
	 * 编辑积分商品
	 */
	@Override
	public int updateintepro(IntegraproductWithBLOBs iwb) throws Exception {
		return integraproductMapper.updateByPrimaryKeySelective(iwb);
	}

	@Override
	public int delById(Integer id) throws Exception {
		return integraproductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageBean getList(CriteriaIntegra criteria, Integer index, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<IntegraproductWithBLOBs> list = integraproductMapper.queryIntegraPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public IntegraproductWithBLOBs getListById(Integer id) throws Exception {
		return integraproductMapper.selectByPrimaryKey(id);
	}

}
