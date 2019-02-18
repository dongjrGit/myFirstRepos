package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.ProductImgs;

public interface ProductImgsService {

	
	
	    int deleteByPrimaryKey(Integer id)throws Exception;

	    int insert(ProductImgs record)throws Exception;
	    
	    ProductImgs selectByPrimaryKey(Integer id)throws Exception;
	    
	    int updateByPrimaryKey(ProductImgs record)throws Exception;
	    
	    List<ProductImgs> selectBySku(Integer skuid)throws Exception;
	    
	    List<ProductImgs> selectBySpu(Integer spuid)throws Exception;
}
