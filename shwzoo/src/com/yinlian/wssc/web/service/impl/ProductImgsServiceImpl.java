package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.mapper.ProductImgsMapper;
import com.yinlian.wssc.web.po.ProductImgs;
import com.yinlian.wssc.web.service.ProductImgsService;

/**
 * 商品图片业务类
 * @author Administrator
 *
 */
public class ProductImgsServiceImpl implements ProductImgsService {
	
	@Autowired
	private ProductImgsMapper productImgsMapper;
	
	
	/**
	 * 删除商品图片
	 */
	public int deleteByPrimaryKey(Integer id)throws Exception{
		return productImgsMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 添加商品图片
	 */
	public int insert(ProductImgs record)throws Exception{
		return productImgsMapper.insert(record);
	}
    
	/**
	 * 根据ID获取图片
	 */
	public ProductImgs selectByPrimaryKey(Integer id)throws Exception{
		return productImgsMapper.selectByPrimaryKey(id);
	}
    
	/**
	 * 编辑图片
	 */
	public int updateByPrimaryKey(ProductImgs record)throws Exception{
		return productImgsMapper.updateByPrimaryKey(record);
	}
    
	/**
	 * 根据skuid获取图片列表
	 */
	public List<ProductImgs> selectBySku(Integer skuid)throws Exception{
		return productImgsMapper.selectBySku(skuid);
	}
    
	/**
	 * 根据spuid获取图片列表
	 */
	public List<ProductImgs> selectBySpu(Integer spuid)throws Exception{
		return productImgsMapper.selectBySpu(spuid);
	}

}
