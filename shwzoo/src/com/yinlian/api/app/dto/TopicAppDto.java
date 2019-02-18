/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.dto;

import java.util.List;

import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.po.TopicRelate;

/**
 * 
 * TopicAppDto.java
 * @author Liang.ma.s
 * @version $Id: TopicAppDto.java, v 0.1 2016年4月7日 下午4:07:13 Administrator Exp $
 */
public class TopicAppDto extends Topic {
	
	private List<Topic> tList;
    
	private List<SpuDto> spuList; // 相关专题下的 店铺，spu，子专题的 列表信息
    
    private List<Shop> shoplist;
    
    private List<TopicRelate>  topicList;
    

	public List<Topic> gettList() {
		return tList;
	}

	public void settList(List<Topic> tList) {
		this.tList = tList;
	}

	public List<SpuDto> getSpuList() {
		return spuList;
	}

	public void setSpuList(List<SpuDto> spuList) {
		this.spuList = spuList;
	}

	public List<Shop> getShoplist() {
		return shoplist;
	}

	public void setShoplist(List<Shop> shoplist) {
		this.shoplist = shoplist;
	}

	public List<TopicRelate> getTopicList() {
		return topicList;
	}

	public void setTopicList(List<TopicRelate> topicList) {
		this.topicList = topicList;
	}
    
    
}
