package com.yinlian.wssc.web.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.ProductUpdaterecordsMapper;
import com.yinlian.wssc.web.po.ProductUpdaterecords;
import com.yinlian.wssc.web.service.ProductUpdateRecordsService;
import com.yinlian.wssc.web.util.GetIpAddresss;

@Component("productUpdateRecordsService")
public class ProductUpdateRecordsServiceImpl implements ProductUpdateRecordsService {
	
	private static final Logger  logger = LoggerFactory.getLogger(ProductUpdateRecordsServiceImpl.class);
	
	@Autowired
	private  ProductUpdaterecordsMapper     productUpdaterecordsMapper;
	
			

	@Override
	public int insertProSXJ(String field, String oldValue, String newValue, Integer spuId, Integer userid,
			String username,String ip) throws Exception {
		ProductUpdaterecords records=new ProductUpdaterecords();
		records.setField(field);
		records.setOldvalue(oldValue);
		records.setNewvalue(newValue);
		records.setSpuId(spuId);
		records.setCreatetime(new Date());
		records.setCreateuserid(userid);
		records.setCreateusername(username);
		//String ip = GetIpAddresss.getIpAddr();
		records.setIp(ip);
		return productUpdaterecordsMapper.insertSelective(records);
	}

	@Override
	public int insertProTJ(String field, String oldValue, String newValue, Integer skuId, Integer userid,
			String username,String ip) throws Exception {
		
		ProductUpdaterecords records=new ProductUpdaterecords();
		records.setField(field);
		records.setOldvalue(oldValue);
		records.setNewvalue(newValue);
		records.setSkuId(skuId);
		records.setCreatetime(new Date());
		records.setCreateuserid(userid);
		records.setCreateusername(username);
		//String ip = GetIpAddresss.getIpAddr();
		records.setIp(ip);
		return productUpdaterecordsMapper.insertSelective(records);
	}
	
}
