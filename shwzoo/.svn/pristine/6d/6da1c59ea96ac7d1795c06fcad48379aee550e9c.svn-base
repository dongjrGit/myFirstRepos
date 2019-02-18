package com.techown.wssc.web.service;

import java.util.List;

import com.techown.wssc.web.po.CriteriaBanner;
import com.techown.wssc.web.po.ZooBanner;
import com.yinlian.wssc.web.interceptor.PageBean;

public interface ZooBannerService {
	/**
	 * 分页查询
	 * 
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 */
	PageBean querylist(CriteriaBanner criteria, int index, int size);

	/**
	 * 修改启用/禁用
	 * 
	 * @param id
	 * @param status
	 * @param operator
	 */
	void updateStatus(Integer id, Integer status, String operator);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void dellist(Integer id);

	/**
	 * 添加
	 * 
	 * @param bean
	 */
	void addBanner(ZooBanner bean);

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	ZooBanner selectById(Integer id);

	/**
	 * 编辑
	 * 
	 * @param bean
	 */
	void updateById(ZooBanner bean);

	/**
	 * 根据状态查询banner表
	 * 
	 * @param status
	 * @return
	 */
	List<ZooBanner> getAppBanner(Integer status);
	/**
	 * 根据typeId查询
	 * @param typeId
	 * @return
	 */
	List<ZooBanner> listByTypeId(Integer typeId);

}
