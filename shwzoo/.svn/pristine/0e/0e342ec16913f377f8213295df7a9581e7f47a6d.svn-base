/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.SpecsValueDto;
import com.yinlian.wssc.web.po.ProductSpecs;
import com.yinlian.wssc.web.po.ProductSpecsCustom;

/**
 * 商品规格信息的业务层
 * @author Administrator
 * @version $Id: ProductSpecsService.java, v 0.1 2016年2月29日 上午9:29:46 Administrator Exp $
 */
public interface ProductSpecsService {

    /**
     * 根据商品规格的id查询商品规格的信息
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public ProductSpecs selectById(Integer id) throws Exception;

    /**
     * 根据商品规格id修改规格名称信息
     * @param productSpecs
     * @return 
     */
    public int updateProductSpecsById(ProductSpecs productSpecs) throws Exception;

    /**
     * 删除规格名称信息根据id
     * @param productSpecs
     * @return 
     */
    public int deleteById(Integer id) throws Exception;

    /**
     * 增加一条商品规格的信息
     * @param productSpecs
     * @return
     */
    public int insert(ProductSpecs productSpecs) throws Exception;

    /**
     * 修改商品规格排序  
     * @param id
     * @param orderby
     * @return
     * @throws Exception
     */
    public int updateOrderBy(Integer orderby, Integer id) throws Exception;

    /**
     * 修改规格状态 0-启用 1-禁用
     * @param status
     * @param id
     * @return
     * @throws Exception
     */
    public int updateStatus(Integer status, Integer id) throws Exception;

    /**
     * 修改规格可输入状态 true-可输入 false-不可输入
     * @param isentry
     * @param id
     * @return
     * @throws Exception
     */
    public int updateIsEntry(Boolean isentry, Integer id) throws Exception;

    /**
     * 
     * @param classid
     * @return
     */
    public List<ProductSpecsCustom> querySpecsByClassid(Integer classid) throws Exception;
	
	
	/**
	 * 根据分类ID获取规格及规格值信息
	 * @param classid
	 * @return
	 * @throws Exception
	 */
	List<SpecsValueDto> getByClassId(Integer classid)throws Exception;
	
	/**
	 * 根据分类ID获取规格及规格值信息
	 * @param classid
	 * @return
	 * @throws Exception
	 */
	List<SpecsValueDto> getByFatherClassId(Integer classid)throws Exception;

	/**
	 * 根据搜索属性表查询规格表的id
	 * 
	 * @param classid
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public ProductSpecs queryIdBySearchAttr(Integer classid, String name) throws Exception; 
	
}
