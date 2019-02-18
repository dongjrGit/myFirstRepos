package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.GroupBuyDetailDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Groupbuy;
import com.yinlian.wssc.web.po.GroupbuyWithBLOBs;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 团购业务层
 * 
 * @author Administrator
 *
 */
public interface GroupBuyService {

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	int insert(GroupbuyWithBLOBs record) throws Exception;

	/**
	 * 根据团购ID获取
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	GroupbuyWithBLOBs selectByPrimaryKey(Integer id) throws Exception;

	/**
	 * 编辑
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	int updateByPrimaryKeyWithBLOBs(GroupbuyWithBLOBs record) throws Exception;

	/**
	 * 编辑
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	int updateByPrimaryKey(Groupbuy record) throws Exception;

	/**
	 * 获取团购列表
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean getListByPage(Criteria criteria, Integer page, Integer size)
			throws Exception;

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteGroup(int id) throws Exception;
	/**
	 * 更改状态
	 * @param status
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int updateStatus(int status,int id)throws Exception;
	
	/**
	 * API获取团购活动列表
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean getGroupByPage(Criteria criteria, Integer page, Integer size)
			throws Exception;
	/**
	 * API获取团购活动详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	GroupBuyDetailDto getDetail(Integer id)throws Exception;

	List<Groupbuyorder> getGroupbuyCode(String groupnum) throws Exception;
}
