/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.Enums.SpecsDisplayEnum;
import com.yinlian.wssc.web.dto.SpecsValueDto;
import com.yinlian.wssc.web.mapper.ProductSpecsMapper;
import com.yinlian.wssc.web.mapper.SpecsvaluesMapper;
import com.yinlian.wssc.web.po.ProductSpecs;
import com.yinlian.wssc.web.po.ProductSpecsCustom;
import com.yinlian.wssc.web.po.ProductSpecsExample;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.po.SpecsvaluesExample;
import com.yinlian.wssc.web.service.ProductSpecsService;

/**
 * 
 * @author Administrator
 * @version $Id: ProductSpecsServiceImpl.java, v 0.1 2016年2月29日 上午9:31:20
 *          Administrator Exp $
 */
public class ProductSpecsServiceImpl implements ProductSpecsService {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(ProductSpecsServiceImpl.class);

	@Autowired
	private ProductSpecsMapper productSpecsMapper;
    @Autowired
    private SpecsvaluesMapper   specsvaluesMapper;
	/**
	 * @see com.yinlian.wssc.web.service.ProductSpecsService#selectById(java.lang.Integer)
	 */
	@Override
	public ProductSpecs selectById(Integer id) throws Exception {
		if (id == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException("The parameter id is null!");
			}
		}
		return productSpecsMapper.selectByPrimaryKey(id);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ProductSpecsService#updateProductSpecsById(com.yinlian.wssc.web.po.ProductSpecs)
	 */
	@Override
	public int updateProductSpecsById(ProductSpecs productSpecs)
			throws Exception {
		if (productSpecs == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException(
						"The parameter productSpecs is null!");
			}
		}
		return productSpecsMapper.updateByPrimaryKey(productSpecs);
	}
    /**
     * @see	com.yinlian.wssc.web.service.ProductSpecsService#updateOrderBy(com.yinlian.wssc.web.po.ProductSpecs)
     */
	@Override
	public int deleteById(Integer id) throws Exception {
		return productSpecsMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @see com.yinlian.wssc.web.service.ProductSpecsService#insert(com.yinlian.wssc.web.po.ProductSpecs)
	 */
	@Override
	public int insert(ProductSpecs productSpecs) throws Exception {
		if (productSpecs == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException(
						"The parameter productSpecs is null!");
			}
		}
		return productSpecsMapper.insert(productSpecs);
	}

    @Override
    public int updateOrderBy(Integer orderby, Integer id) throws Exception {
        //		ProductSpecs specs = new ProductSpecs();
        //		specs.setOrderby(orderby);
        //		specs.setId(id);
        return productSpecsMapper.updateOrderBy(orderby, id);
    }
    /**
     * 
     */
    @Override
    public int updateStatus(Integer status, Integer id) throws Exception {

        return productSpecsMapper.updateStatus(status, id);
    }

    @Override
    public int updateIsEntry(Boolean isentry, Integer id) throws Exception {
        ProductSpecs specs = new ProductSpecs();
        specs.setIsentry(isentry);
        specs.setId(id);
        return productSpecsMapper.updateIsEntry(specs);
    }

    /** 
     * @see com.yinlian.wssc.web.service.ProductSpecsService#querySpecsByClassid(java.lang.Integer)
     */
    @Override
    public List<ProductSpecsCustom> querySpecsByClassid(Integer classid) throws Exception {
        if (classid == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("参数为null");
                throw new IllegalArgumentException("The parameter classid is null!");
            }
        }
        List<ProductSpecsCustom> list = productSpecsMapper.selectSpecsByClassid(classid);
        SpecsvaluesExample example = new SpecsvaluesExample();
        SpecsvaluesExample.Criteria criteria = example.createCriteria();
        for (ProductSpecsCustom specs : list) {
            Integer specsid = specs.getId();
            criteria.andSpecsidEqualTo(specsid);
            List<Specsvalues> values = specsvaluesMapper.selectByExample(example);
            specs.setValues(values);
        }
        return list;
    }
	/**
	 * 根据分类ID获取规格及规格值信息
	 * 
	 * @param classid
	 * @return
	 * @throws Exception
	 */
	public List<SpecsValueDto> getByClassId(Integer classid) throws Exception {
		List<ProductSpecs> specslist = productSpecsMapper.getByClassId(classid);
		List<Specsvalues> svalusList = new ArrayList<Specsvalues>();
		List<SpecsValueDto> dtolist = new ArrayList<SpecsValueDto>();
		SpecsValueDto dto = null;
		List<ProductSpecs> specslist1=specslist.stream().filter(x->x.getDisplaylocation().
				startsWith(SpecsDisplayEnum.商品介绍.getValue().toString())).collect(Collectors.toList());
		if(specslist1!=null){
			for (ProductSpecs productSpecs : specslist1) {
				dto = new SpecsValueDto();
				dto.setclassID(productSpecs.getClassid());
				dto.setspecsID(productSpecs.getId());
				dto.setspecsName(productSpecs.getName());
				dto.setspecsStatus(productSpecs.getStatus());
				dto.setspecsTypeID(productSpecs.getSpecstypeid());
				dto.setorderBy(productSpecs.getOrderby());
				dto.setdisplayLocation(productSpecs.getDisplaylocation());
				dto.setisEntry(productSpecs.getIsentry());
				dto.setIsmust(1);
				svalusList = specsvaluesMapper.getBySpecsID(productSpecs.getId());
				dto.setvaluelist(svalusList);
				dtolist.add(dto);
			}
		}		
		List<ProductSpecs> specslist2=specslist.stream().filter(x->x.getDisplaylocation().contains(SpecsDisplayEnum.商品介绍.getValue().toString())
				==false).collect(Collectors.toList());
		if(specslist2!=null){
			for (ProductSpecs proSpecs : specslist2) {
				dto = new SpecsValueDto();
				dto.setclassID(proSpecs.getClassid());
				dto.setspecsID(proSpecs.getId());
				dto.setspecsName(proSpecs.getName());
				dto.setspecsStatus(proSpecs.getStatus());
				dto.setspecsTypeID(proSpecs.getSpecstypeid());
				dto.setorderBy(proSpecs.getOrderby());
				dto.setdisplayLocation(proSpecs.getDisplaylocation());
				dto.setisEntry(proSpecs.getIsentry());
				dto.setIsmust(0);
				svalusList = specsvaluesMapper.getBySpecsID(proSpecs.getId());
				dto.setvaluelist(svalusList);
				dtolist.add(dto);
			}
		}
		
		return dtolist;
	}
	
	
	/**
	 * 根据分类ID获取规格及规格值信息
	 * 
	 * @param classid
	 * @return
	 * @throws Exception
	 */
	public List<SpecsValueDto> getByFatherClassId(Integer classid) throws Exception {
		List<ProductSpecs> specslist = productSpecsMapper.getByFatherClassId(classid);
		List<Specsvalues> svalusList = new ArrayList<Specsvalues>();
		List<SpecsValueDto> dtolist = new ArrayList<SpecsValueDto>();
		SpecsValueDto dto = null;
		List<ProductSpecs> specslist1=specslist.stream().filter(x->x.getDisplaylocation().
				startsWith(SpecsDisplayEnum.商品介绍.getValue().toString())).collect(Collectors.toList());
		if(specslist1!=null){
			for (ProductSpecs productSpecs : specslist1) {
				dto = new SpecsValueDto();
				dto.setclassID(productSpecs.getClassid());
				dto.setspecsID(productSpecs.getId());
				dto.setspecsName(productSpecs.getName());
				dto.setspecsStatus(productSpecs.getStatus());
				dto.setspecsTypeID(productSpecs.getSpecstypeid());
				dto.setorderBy(productSpecs.getOrderby());
				dto.setdisplayLocation(productSpecs.getDisplaylocation());
				dto.setisEntry(productSpecs.getIsentry());
				dto.setIsmust(1);
				svalusList = specsvaluesMapper.getBySpecsID(productSpecs.getId());
				dto.setvaluelist(svalusList);
				dtolist.add(dto);
			}
		}		
		List<ProductSpecs> specslist2=specslist.stream().filter(x->x.getDisplaylocation().contains(SpecsDisplayEnum.商品介绍.getValue().toString())
				==false).collect(Collectors.toList());
		if(specslist2!=null){
			for (ProductSpecs proSpecs : specslist2) {
				dto = new SpecsValueDto();
				dto.setclassID(proSpecs.getClassid());
				dto.setspecsID(proSpecs.getId());
				dto.setspecsName(proSpecs.getName());
				dto.setspecsStatus(proSpecs.getStatus());
				dto.setspecsTypeID(proSpecs.getSpecstypeid());
				dto.setorderBy(proSpecs.getOrderby());
				dto.setdisplayLocation(proSpecs.getDisplaylocation());
				dto.setisEntry(proSpecs.getIsentry());
				dto.setIsmust(0);
				svalusList = specsvaluesMapper.getBySpecsID(proSpecs.getId());
				dto.setvaluelist(svalusList);
				dtolist.add(dto);
			}
		}
		
		return dtolist;
	}
	/**
	 * 根据搜索属性表查询规格表的id
	 * @see com.yinlian.wssc.web.service.ProductSpecsService#queryIdBySearchAttr(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ProductSpecs queryIdBySearchAttr(Integer classid, String name)
			throws Exception {
		ProductSpecsExample example = new ProductSpecsExample();
		ProductSpecsExample.Criteria criteria = example.createCriteria();
		criteria.andClassidEqualTo(classid);
		criteria.andNameEqualTo(name);
		List<ProductSpecs> list = productSpecsMapper.selectByExample(example);
		ProductSpecs productSpecs = list.get(0);
		return productSpecs;
	}
	
}
