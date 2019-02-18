package com.pushwin.batchwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pushwin.batchwork.dao.IProductDao;
import com.pushwin.batchwork.service.IProductService;

@Service("productService")
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	IProductDao productDao;
	
	@Override
	@Transactional
	public void productOffOnline(){
		productDao.productOffOnline();
	}
}
