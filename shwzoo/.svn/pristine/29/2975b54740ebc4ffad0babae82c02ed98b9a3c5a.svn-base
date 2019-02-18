package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.Enums.StoreLevelEnum;
import com.yinlian.wssc.web.config.StoreLevelCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.StoreLevelMapper;
import com.yinlian.wssc.web.po.StoreLevel;
import com.yinlian.wssc.web.service.StoreLevelService;

public class StoreLevelServiceImpl implements StoreLevelService{
	@Autowired
	private StoreLevelMapper storeLevelMapper;
	@Override
	public int update(StoreLevel orm) throws Exception {
		return storeLevelMapper.update(orm);
	}

	@Override
	public int add(StoreLevel orm) throws Exception {
		return storeLevelMapper.insert(orm);
		
	}

	@Override
	public int delById(int id)  throws Exception{
		return storeLevelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public StoreLevel getById(int id) throws Exception {
		return storeLevelMapper.getById(id);
	}

	@Override
	public PageBean getList(StoreLevelCriteria where,int pg,int size) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreLevel> getAllList() throws Exception {
		return storeLevelMapper.getAllList();
	}

	@Override
	public int updateStatusById(StoreLevelEnum status,int id) throws Exception {
		StoreLevel orm=new  StoreLevel();
		orm.setStatus(status.getValue());
		orm.id=id;
		return storeLevelMapper.updateStatusById(orm);
	}

	@Override
	public int updateSortById(int sort,int id) throws Exception {
		StoreLevel orm=new  StoreLevel();
		orm.setSort(sort);
		orm.id=id;
		return storeLevelMapper.updateSortById(orm);
	}

}
