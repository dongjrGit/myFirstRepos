package com.techown.wssc.web.service;

import java.util.List;

import com.techown.wssc.web.po.CriteriaNews;
import com.techown.wssc.web.po.ZooNews;
import com.yinlian.wssc.web.interceptor.PageBean;

public interface ZooNewsService {
	/**
	 * 列表查询
	 * 
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 */
	PageBean querylist(CriteriaNews criteria, int index, int size);
	/**
	 * 首页动物科普新闻
	 * @param criteria
	 * @return
	 */
	List<ZooNews> getNewsListHot(Integer state);
	
	/**
	 * 修改发布状态
	 * 
	 * @param id
	 * @param state
	 * @param operator
	 */
	void updateState(Integer id, Integer state, String operator);

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 * @param operator
	 */
	void dellist(Integer id, String operator);
	/**
	 * 添加新闻
	 * @param bean
	 */
	void addNews(ZooNews bean);
	/**
	 * 修改
	 * @param bean
	 */
	void updateById(ZooNews bean);
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	ZooNews selectById(Integer id);
	/**
	 * 获取最新的新闻
	 * @param state
	 * @return
	 */
	ZooNews getLastNews(Integer state);

}
