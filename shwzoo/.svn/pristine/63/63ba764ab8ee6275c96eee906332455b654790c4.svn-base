package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.LogisticsMapper;
import com.yinlian.wssc.web.po.Logistics;
import com.yinlian.wssc.web.po.LogisticsExample;
import com.yinlian.wssc.web.service.LogisticsService;
import com.yinlian.wssc.web.util.CriteriaLogistics;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("logisticsService")
public class LogisticsServiceImpl implements LogisticsService {

	@Autowired
	private LogisticsMapper logisticsMapper;

	@Override
	public Logistics getById(Integer id) throws Exception {
		return logisticsMapper.selectByPrimaryKey(id);
	}

	@Override
	public void svae(Integer id, String name, String code, Integer sort) throws Exception {
		Logistics record = null;
		if (id != null && id > 0)
			record = logisticsMapper.selectByPrimaryKey(id);
		else
			record = new Logistics();
		record.setCode(code);
		record.setName(name);
		record.setSort(sort);
		if (id != null && id > 0) {
			logisticsMapper.updateByPrimaryKey(record);
		} else {
			logisticsMapper.insert(record);
		}

	}

	@Override
	public PageBean getList(CriteriaLogistics cl, Integer spage, Integer ssize) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(cl, spage, ssize);
        PageBean pageBean = pageBeanUtil.getPage();

        List<Logistics> beanList = logisticsMapper.getListByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
	}

	@Override
	public int deletebyid(Integer id) throws Exception {
		return logisticsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public String getByCode(String logistcode) throws Exception {		
		return logisticsMapper.getByCode(logistcode);
	}

	@Override
	public List<Logistics> selectByExample(LogisticsExample example) {
		// TODO Auto-generated method stub
		return logisticsMapper.selectByExample(example);
	}

}
