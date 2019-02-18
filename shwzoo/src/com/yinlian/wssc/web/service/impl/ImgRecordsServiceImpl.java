package com.yinlian.wssc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.ImgrecordsMapper;
import com.yinlian.wssc.web.po.Imgrecords;
import com.yinlian.wssc.web.service.ImgRecordsService;

@Component("imgRecordsService")
public class ImgRecordsServiceImpl implements ImgRecordsService {

	@Autowired 
	public ImgrecordsMapper imgrecordsMapper;
	@Override
	public int add(Imgrecords record) throws Exception {
	return imgrecordsMapper.insert(record);
	}

}
