package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.HighspecialtyMapper;
import com.yinlian.wssc.web.po.CriteriaVHpSku;
import com.yinlian.wssc.web.po.Highspecialty;
import com.yinlian.wssc.web.po.V_Hp_Sku;
import com.yinlian.wssc.web.service.HighSpecialtyService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("/HighSpecialtyService")
public class HighSpecialtyServiceImpl implements HighSpecialtyService {

	@Autowired
	private HighspecialtyMapper highspecialtyMapper;

	@Override
	public PageBean getList(CriteriaVHpSku criteria, Integer index, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<V_Hp_Sku> list = highspecialtyMapper.getListPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public int insertHiSpe(Highspecialty highspecialty) throws Exception {
		return highspecialtyMapper.insertSelective(highspecialty);
	}

	@Override
	public int updateHiSpe(Highspecialty highspecialty) throws Exception {
		return highspecialtyMapper.updateByPrimaryKeySelective(highspecialty);
	}

	@Override
	public int deleteHiSpeById(Integer id) throws Exception {
		return highspecialtyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Highspecialty selListById(Integer id) throws Exception {
		return highspecialtyMapper.selectByPrimaryKey(id);
	}

	@Override
	public int setType(Integer id, Integer state) throws Exception {
		return highspecialtyMapper.setType(id, state);
	}

	@Override
	public V_Hp_Sku selVById(Integer id) throws Exception {
		return highspecialtyMapper.selVById(id);
	}

	@Override
	public List<Highspecialty> getByRecommend() throws Exception {
		return highspecialtyMapper.getByRecommend();
	}

	@Override
	public PageBean getApplist(AppNewsCriteria criteria, Integer page, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<V_Hp_Sku> list = highspecialtyMapper.getapplistPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

}
