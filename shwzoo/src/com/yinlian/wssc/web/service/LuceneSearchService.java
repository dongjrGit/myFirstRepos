package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.ProLuceneDto;

public interface LuceneSearchService {
	
	public void createIndex()throws Exception;
	
	public List<ProLuceneDto> getProidList(String keys)throws Exception;
}
