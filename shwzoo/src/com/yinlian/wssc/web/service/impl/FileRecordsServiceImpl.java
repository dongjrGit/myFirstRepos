package com.yinlian.wssc.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.FileRecordsMapper;
import com.yinlian.wssc.web.po.FileRecords;
import com.yinlian.wssc.web.service.FileRecordsService;

@Component("fileRecordsService")
public class FileRecordsServiceImpl implements FileRecordsService {

	@Autowired 
	public FileRecordsMapper filerecordsMapper;
	@Override
	public int add(FileRecords record) throws Exception {
	return filerecordsMapper.insert(record);
	}

}
