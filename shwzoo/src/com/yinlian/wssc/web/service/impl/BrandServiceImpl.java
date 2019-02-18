/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.dto.TopicRelateInfo;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.BrandMapper;
import com.yinlian.wssc.web.mapper.ShopBrandMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.BrandExample;
import com.yinlian.wssc.web.po.ShopBrand;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 品牌的业务类
 * @author Administrator
 * @version $Id: BrandService.java, v 0.1 2016年2月29日 下午3:04:58 Administrator Exp $
 */
public class BrandServiceImpl implements BrandService {

    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(SpecsvaluesServiceImpl.class);

    @Autowired
    private BrandMapper         brandMapper;
    @Autowired
    private ShopBrandMapper     shopBrandMapper;
    @Autowired
    private ShopMapper          shopMapper;

    /** 
     * @see com.yinlian.wssc.web.service.BrandService#selectBrandPage(com.yinlian.wssc.web.util.Criteria, java.lang.Integer)
     */
    @Override
    public PageBean selectBrandPage(Criteria criteria, Integer pc, Integer ps) throws Exception {
        if (criteria == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Criteria is null!");
            }
        }
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Brand> beanList = brandMapper.selectBrandPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /** 
     * @return 
     * @see com.yinlian.wssc.web.service.BrandService#updateBrand(com.yinlian.wssc.web.po.Brand)
     */
    @Override
    public int updateBrand(Brand brand) throws Exception {
        if (brand == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter Brand is null!");
            }
        }
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        criteria.andIdNotEqualTo(brand.getId());
        criteria.andNameEqualTo(brand.getName());
        List<Brand> list = brandMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return -401;
        }
        return brandMapper.updateByPrimaryKey(brand);
    }

    /** 
     * @see com.yinlian.wssc.web.service.BrandService#selectById(java.lang.Integer)
     */
    @Override
    public Brand selectById(Integer id) throws Exception {
        if (id == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter id is null!");
            }
        }

        return brandMapper.selectByPrimaryKey(id);
    }

    /** 
     * @return 
     * @see com.yinlian.wssc.web.service.BrandService#insert(com.yinlian.wssc.web.po.Brand)
     */
    @Override
    public int insert(Brand brand) throws Exception {
        if (brand == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter brand is null!");
            }
        }
        return brandMapper.insert(brand);
    }

    /** 
     * @return 
     * @see com.yinlian.wssc.web.service.BrandService#deleteBrand(java.lang.Integer)
     */
    @Override
    public int deleteBrand(Integer id) throws Exception {
        if (id == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter id is null!");
            }
        }
       return brandMapper.deleteByPrimaryKey(id);
    }

    /** 
     * @see com.yinlian.wssc.web.service.BrandService#queryAll()
     */
    @Override
    public List<Brand> queryAll(String name) throws Exception {
        BrandExample example = new BrandExample();
        BrandExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + name + "%");
        return brandMapper.selectByExampleWithBLOBs(example);
    }

    /** 
     * @see com.yinlian.wssc.web.service.BrandService#selectBrandList()
     */
    @Override
    public List<Brand> selectBrandList() throws Exception {
        BrandExample example = new BrandExample();
        return brandMapper.selectByExample(example);
    }

    /** 
     * @see com.yinlian.wssc.web.service.BrandService#insertBrandGetId(com.yinlian.wssc.web.po.Brand, java.lang.Integer)
     */
    @Override
    public int insertBrandGetId(Brand brand, Integer shopid) throws Exception {
    	
    	Brand brd=brandMapper.getbyName(brand.getName());
    	 int brandid =0;
    	if(brd!=null){
    		brandid = brd.getId();
    	}
    	else{
    		 brandMapper.insertBrandGetId(brand);	        
             brandid = brand.getId();
    	}     
        ShopBrand record = new ShopBrand();
        record.setBrandid(brandid);
        record.setShopid(shopid);
        record.setCheckstatus(0); // 新增的审核状态 为0 表示待审核
        record.setIsdel(false);
        record.setCreatetime(new Date());
        return shopBrandMapper.insert(record);
    }
    
    /**
     * 店铺获取关联品牌 品牌名称模糊检索
     * @param shopid
     * @param name
     * @return
     * @throws Exception
     */
    public List<Brand> getBrandWithNameShop(Integer shopid, String name) throws Exception{
    	return brandMapper.getBrandWithNameShop(shopid, name);
    }
    
    /**
     * 品牌名称模糊检索获取品牌列表
     * @param name
     * @return
     * @throws Exception
     */
    public List<Brand> getBrandWithName(String name) throws Exception{
    	return brandMapper.getBrandWithName(name);
    }
    
    public List<Brand> selectBrandByShop(Integer shopid) throws Exception{
    	return brandMapper.selectBrandByShop(shopid);
    }

	@Override
	public List<Brand> selectAll() throws Exception {
		
		return brandMapper.queryAll();
	}

	@Override
	public PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps) throws Exception {
		  	PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
	        PageBean pageBean = pageBeanUtil.getPage();
	        List<TopicRelateInfo> beanList = brandMapper.selectTopicByPage(pageBeanUtil);
	        pageBean.setBeanList(beanList);
	        return pageBean;
	}

	@Override
	public int deleteBrand(int id,int userid) throws Exception {
		Brand brand=brandMapper.selectByPrimaryKey(id);
		shopBrandMapper.deleteByBrandid(id);
		brand.setIsdel(true);
		brand.setDeluserid(userid);
		brandMapper.deleteBrand(brand);
		return 1;
	}

	@Override
	public Brand getbyName(String name) throws Exception {		
		return brandMapper.getbyName(name);
	}
}
