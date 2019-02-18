package com.techown.wssc.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techown.wssc.web.mapper.ZooMapMapper;
import com.techown.wssc.web.po.ZooMap;
import com.techown.wssc.web.service.ZooMapService;
@Component("zooMapService")
public class ZooMapServiceImpl implements ZooMapService {

	@Autowired
	private ZooMapMapper zooMapMapper;
	
	@Override
	public List<ZooMap> querylist(Integer type, Integer state) {
		return zooMapMapper.querylist(type,state);
	}

	@Override
	public void addZooMap(ZooMap zooMap) {
		zooMapMapper.insert(zooMap);
	}

	@Override
	public void updateState(Integer id, Integer srcState, Integer tgtState,Date updateTime) {
		zooMapMapper.updateState(id,srcState,tgtState,updateTime);
	}

	@Override
	public List<ZooMap> queryMaxTime() {
		return zooMapMapper.queryMaxTime();
	}

	@Override
	public HashMap<String,Object> queryLastMap(Integer type,Integer state) {
		return zooMapMapper.queryLastMap(type,state);
	}
}
