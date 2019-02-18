package com.techown.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techown.wssc.web.mapper.ZooNewsMapper;
import com.techown.wssc.web.po.CriteriaNews;
import com.techown.wssc.web.po.ZooNews;
import com.techown.wssc.web.service.ZooNewsService;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("zooNewsService")
public class ZooNewsServiceImpl implements ZooNewsService {
	@Autowired
	private ZooNewsMapper zooNewsMapper;

	@Override
	public PageBean querylist(CriteriaNews criteria, int index, int size) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<ZooNews> list = zooNewsMapper.querylistPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}
	
	@Override
	public List<ZooNews> getNewsListHot(Integer state) {
		List<ZooNews> zooNewsList = zooNewsMapper.getNewsListHot(state);
		return zooNewsList;
	}

	@Override
	public void updateState(Integer id, Integer state, String operator) {
		zooNewsMapper.updateState(id, state, new Date(), operator);
	}

	@Override
	public void dellist(Integer id, String operator) {
		zooNewsMapper.updateDelState(id, new Date(), operator);

	}

	@Override
	public void addNews(ZooNews bean) {
		zooNewsMapper.insert(bean);
	}

	@Override
	public void updateById(ZooNews bean) {
		zooNewsMapper.updateByPrimaryKeyWithBLOBs(bean);
	}

	@Override
	public ZooNews selectById(Integer id) {
		return zooNewsMapper.selectByPrimaryKey(id);
	}

	@Override
	public ZooNews getLastNews(Integer state) {
		List<ZooNews> list = zooNewsMapper.getLastNews(state);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
