/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaTopic;

/**
 * 商品品牌的业务类
 * @author Administrator
 * @version $Id: BrandService.java, v 0.1 2016年2月29日 下午3:03:36 Administrator Exp $
 */
public interface BrandService {

    /**
     *  查询出所有的商品品牌的分页信息
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     * @throws Exception
     */
    public PageBean selectBrandPage(Criteria criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 修改商品品牌的信息
     * 
     * @param brand
     * @return 
     * @throws Exception
     */
    public int updateBrand(Brand brand) throws Exception;

    /**
     * 根据id查询商品品牌
     * @param id
     * @return
     */
    public Brand selectById(Integer id) throws Exception;

    /**
     * 增加一个商品品牌
     * @param brand
     * @return 
     */
    public int insert(Brand brand) throws Exception;

    /**
     * 
     * @param id
     * @return 
     */
    public int deleteBrand(Integer id) throws Exception;

    /**
     * 查询出所有的商品品牌
     * @return
     */
    public List<Brand> queryAll(String name) throws Exception;

    /**
     * 
     * @return
     */
    public List<Brand> selectBrandList() throws Exception;

    /**
     * 新增店铺下的品牌
     * @param brand
     * @param shopid
     * @return
     */
    public int insertBrandGetId(Brand brand, Integer shopid) throws Exception;
    
    /**
     * 店铺获取关联品牌 品牌名称模糊检索
     * @param shopid
     * @param name
     * @return
     * @throws Exception
     */
    List<Brand> getBrandWithNameShop(Integer shopid, String name) throws Exception;
    
    /**
     * 品牌名称模糊检索获取品牌列表
     * @param name
     * @return
     * @throws Exception
     */
    List<Brand> getBrandWithName(String name) throws Exception;
    
    List<Brand> selectBrandByShop(Integer shopid) throws Exception;
    
    /**
     * 查询所有的品牌信息
     * @return
     * @throws Exception
     */
    List<Brand>  selectAll() throws Exception;
    
    /**
     * 分頁查詢品牌主題
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return 
     */
    PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria, Integer pc, Integer ps)
                                                                                           throws Exception;
    
    /**
     * 逻辑删除品牌
     * @param id
     * @param userid
     * @return
     * @throws Exception
     */
    int deleteBrand(int id,int userid)throws Exception;
    
    /**
     * 验证品牌是否存在
     * @param name
     * @return
     * @throws Exception
     */
    Brand getbyName(String name)throws Exception;
}
