package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.BrowseHistoryDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Browsehistory;
import com.yinlian.wssc.web.util.CriteriaBrowsehistory;

/**
 * 会员浏览记录的业务类
 * @author Administrator
 *
 */
public interface BrowsehistoryService {

	/**
	 * 分页查询会员最新浏览历史
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	PageBean queryBrowsehistoryByCriteria(CriteriaBrowsehistory criteria,
			Integer pc, Integer ps) throws Exception;


	/**
	 * 根据userid查询浏览记录
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	List<Browsehistory> queryByUserId(Integer userid) throws Exception;

	/**
	 * 根据userid更改浏览记录
	 * @param browsehistory
	 * @return
	 * @throws Exception
	 */
	int updateBrowsehistoryByUserId(Browsehistory browsehistory) throws Exception;


	/**
	 * 查询会员浏览记录明细
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	List<BrowseHistoryDto> queryDetailByUserId(Integer userid) throws Exception;


	void insert(Browsehistory browsehistory) throws Exception;
	
	void deleteByPrimaryKey(Integer id) throws Exception;

}
