/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.ClassDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaTopic;

/**
 * 商品分类的业务层
 * @author Administrator
 * @version $Id: CategoryService.java, v 0.1 2016年2月26日 下午3:14:40 Administrator Exp $
 */
public interface CategoryService {
/*
    *//**
     * 获取到所以得分类（包含二级和三级分类）
     * 
     * @return
     * @throws Exception
     *//*
    public List<CategoryDTO> selectAllCategorys(Integer fatherid) throws Exception;
*/
    /**
     * 获取到所以得分类（包含二级和三级分类）
     * 
     * @return
     * @throws Exception
     */
    public List<CategoryDTO> selectAll(int fatherid) throws Exception;
    
    /**
     * 根据主键查询分类信息
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public CategoryDTO queryById(Integer id) throws Exception;

    /**
     * 修改Category
     * 
     * @param category
     * @throws Exception
     */
    public int updateCategory(Category category) throws Exception;

    /**
     * 删除一个分类
     *  级联删除他下面的分类(改变isdel的属性)
     * @param id
     * @return 
     * @throws Exception
     */
    public int deletCatefory(Integer id) throws Exception;

    /**
     * 根据分类id查询商品规格信息分页信息
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     * @throws Exception
     */
    public PageBean seletcProductSpecsPageBySonClassId(Criteria criteria, Integer pc, Integer ps) throws Exception;



    /**
     * 根据父ID获取子分类信息
     * @param fatherid
     * @return
     */
    public List<CategoryDTO> getByFatherID(Integer fatherid) throws Exception;
    
    /**
     * 添加Category
     * 
     * @param category
     * @throws Exception
     */
    public int addCategory(Category category) throws Exception;
    
    /**
     * 删除分类
     * @param id
     * @param userid
     * @return
     * @throws Exception
     */
    public int delCategory(Integer id,Integer userid,ReusltItem item) throws Exception;
    /**
     * 商家删除自定义分类
     * @param id
     * @param userid
     * @return
     * @throws Exception
     */
    public int deleteCatetory(Integer id,Integer userid) throws Exception;
    
    public Category selectByPrimaryKey(Integer id)throws Exception;

    public String GetFullNamePath(String ids)throws Exception;
    
    /**
     * 根据第三级分类ID和店铺ID获取自定义分类
     * @param classid
     * @param shopid
     * @return
     * @throws Exception
     */
    List<CategoryDTO> getByClassShop(Integer classid,Integer shopid)throws Exception;
    
    /**
     * 根据店铺获取自定义分类列表
     * @param a 查询类
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    PageBean getClassByShopPage(Criteria a,Integer page,Integer size)throws Exception;
    
    /**
     * 获取店铺经营范围（经营的分类）
     * @param fatherid
     * @param shopid
     * @return
     * @throws Exception
     */
    List<CategoryDTO> selectCategoryByShop(Integer fatherid,Integer shopid) throws Exception;
    
    /**
     * 自定义分类提交/审核
     * @param category
     * @return
     * @throws Exception
     */
    public int updateStatus(Category category) throws Exception;
    
    List<CategoryDTO> getFatherByShop(Integer fatherid,Integer shopid) throws Exception; 
        
    public int updateStatusList(Integer status,List<Integer> idlist) throws Exception;
    
    public PageBean getCheckList(Criteria c,Integer page,Integer size)throws Exception;

    /**
     * 手机端 专用 获取分类列表
     * @param fid1 一级分类id
     * @return
     * @throws Exception
     */
	 List<ClassDto> getApiList(Integer fid1) throws Exception;

	 /**
	  * 分頁查詢分類專題
	  * 
	  * @param criteria
	  * @param pc
	  * @param ps
	  * @return
	  */
	public PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps);
	
	/**
	 * 查询所有的父类分类
	 * @return
	 * @throws Exception
	 */
	List<Category> queryFirstClass() throws Exception;
	List<Category> queryAllList() throws Exception;
	/**
	 * 查询所有的子类分类
	 * @param fatherid
	 * @return
	 * @throws Exception
	 */
	List<Category> queryChildrenClass(Integer fatherid) throws Exception;

	PageBean seletcProductSpecsPageByClassId(Criteria criteria, Integer pc,Integer ps) throws Exception;
	
	
	
	List<Category> querySan() throws Exception;

	public Category queryFatherClass(Integer id) throws Exception;
}
