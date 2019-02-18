package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Circle;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaShopCategory;


/**
 * 商圈业务类
 * CircleService.java
 * @author Administrator
 * @version $Id: CircleService.java, v 0.1 2016年5月11日 下午4:51:31 Administrator Exp $
 */
public interface CircleService {

	
	/**
	 * 查询所有的商圈
	 * @return
	 */
	PageBean queryAll(Criteria criteria, Integer ipage, Integer isize);

	/**
	 * 查询是否存在该商圈
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Circle queryByName(String name) throws Exception;
	/**
	 * 添加一条商圈
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	int addBusinessDistrict(String name) throws Exception;
	/**
	 * 编辑商圈
	 * 
	 * @param id
	 * @param name
	 * @return
	 * @throws Exception
	 */
	int updatBusinessDistrictById(Integer id, String name) throws Exception;
	/**
	 * 根据id查询商圈
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Circle queryById(Integer id) throws Exception;
	/**
	 * 根据id删除商圈
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delBusinessDistrict(Integer id) throws Exception;
	/**
	 * 分页查询商圈列表
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	PageBean queryBusinessDistrictList(CriteriaShopCategory criteria,
			Integer pc, Integer ps) throws Exception;
	/**
	 * 查询所有商圈
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Circle> getAllList() throws Exception;
	


}
