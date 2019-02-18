package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.BrowseHistoryDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.BrowsehistoryMapper;
import com.yinlian.wssc.web.po.Browsehistory;
import com.yinlian.wssc.web.po.BrowsehistoryExample;
import com.yinlian.wssc.web.service.BrowsehistoryService;
import com.yinlian.wssc.web.util.CriteriaBrowsehistory;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("browsehistoryService")
public class BrowsehistoryServiceImpl implements BrowsehistoryService {
	/**
	 * 输出日志的控制类
	 */

	@Autowired
	private BrowsehistoryMapper browsehistoryMapper;
	/**
	 * 分页查询会员最新浏览历史
	 * @see com.yinlian.wssc.web.service.BrowsehistoryService#queryBrowsehistoryByCriteria(com.yinlian.wssc.web.util.CriteriaBrowsehistory, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryBrowsehistoryByCriteria(
			CriteriaBrowsehistory criteria, Integer pc, Integer ps) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,pc,ps);
		PageBean pageBean = pageBeanUtil.getPage();
		List<BrowseHistoryDto> beanlist = browsehistoryMapper.selectBrowsehistoryByPage(pageBeanUtil);
		pageBean.setBeanList(beanlist);
		return pageBean;
	}
	/**
	 * 
	 * @see com.yinlian.wssc.web.service.BrowsehistoryService#queryById(java.lang.Integer)
	 */
	/**
	 * 根据userid查询浏览记录
	 * @see com.yinlian.wssc.web.service.BrowsehistoryService#queryByUserId(java.lang.Integer)
	 */
	@Override
	public List<Browsehistory> queryByUserId(Integer userid) throws Exception {
		BrowsehistoryExample example = new BrowsehistoryExample();
		BrowsehistoryExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userid);
		return browsehistoryMapper.selectByExample(example);
	}
	/**
	 * 根据userid更改浏览记录
	 * @see com.yinlian.wssc.web.service.BrowsehistoryService#updateBrowsehistoryByUserId(com.yinlian.wssc.web.po.Browsehistory)
	 */
	@Override
	public int updateBrowsehistoryByUserId(Browsehistory browsehistory)
			throws Exception {
		return browsehistoryMapper.updateByPrimaryKey(browsehistory);
	}
	/**
	 * 查询会员浏览记录明细
	 * @see com.yinlian.wssc.web.service.BrowsehistoryService#queryDetailByUserId(java.lang.Integer)
	 */
	@Override
	public List<BrowseHistoryDto> queryDetailByUserId(Integer userid)
			throws Exception {
		
		return browsehistoryMapper.selectDetailByUserId(userid);
	}
	@Override
	public void insert(Browsehistory browsehistory) throws Exception {
		browsehistoryMapper.insertSelective(browsehistory);
	}
	@Override
	public void deleteByPrimaryKey(Integer id) throws Exception{
		browsehistoryMapper.deleteByPrimaryKey(id);
	}
	
}
