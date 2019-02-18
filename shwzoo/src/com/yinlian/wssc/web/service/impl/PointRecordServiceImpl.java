package com.yinlian.wssc.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.PointsRecordsFromTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.pc.dto.AfterserviceDto;
import com.yinlian.pc.dto.PcPointsDto;
import java.util.List;

import com.yinlian.wssc.search.Pc_PointsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.PointrecordsMapper;
import com.yinlian.wssc.web.mapper.PointsrecordsMapper;
import com.yinlian.wssc.web.po.Pointrecords;
import com.yinlian.wssc.web.po.Pointsrecords;
import com.yinlian.wssc.web.service.PointRecordService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("pointRecordService")
public class PointRecordServiceImpl  implements PointRecordService{

	@Autowired
	private PointsrecordsMapper pointsrecordsMapper;
	
	@Autowired
	private PointrecordsMapper  pointrecordsMapper;
	
	
	
	
	@Override
	public int add(int points, PointsRecordsTypeEnum type, PointsRecordsFromTypeEnum fromType, Integer userid)
			throws Exception {
		Pointsrecords pointrecords=new Pointsrecords();
		pointrecords.setPoints(points);
		pointrecords.setType(type.getValue());
		pointrecords.setFromtype (fromType.getValue());
		pointrecords.setCreatetime (new Date());
		pointrecords.setUserid(userid);
		return pointsrecordsMapper.insert(pointrecords);
	}
	
	
	
	
	
	@Override
	public List<PcPointsDto> selectByUserid(Integer userid) throws Exception {
		List<PcPointsDto> pointrecords=pointrecordsMapper.selectByUserid(userid);
		if(pointrecords==null){
			return null;
		}else{
			return pointrecords;
		}
	}
	@Override
	public List<PcPointsDto> selectByType(Integer userid,Integer type) throws Exception {
		List<PcPointsDto> pointrecords=pointrecordsMapper.selectByType(userid,type);
		if(pointrecords==null){
			return null;
		}else{
			return pointrecords;
		}
	}
	
	@Override
	public int insertPoint(Pointrecords pointrecords, Pointsrecords pointsrecords)
			throws Exception {
		int i=pointrecordsMapper.insert(pointrecords);
		int j=pointsrecordsMapper.insert(pointsrecords);
		if(i==1&&j==1){
			return 1;
		}
		return 0;
	}

	@Override
	public List<PcPointsDto> selectByTime(Integer userid,String start,String end) throws Exception {
		List<PcPointsDto> pointrecords=pointrecordsMapper.selectByTime(userid,start,end);
		if(pointrecords==null){
			return null;
		}else{
			return pointrecords;
		}
	}
	
	@Override
	public List<PcPointsDto> selectByTimes(Integer userid,String start) throws Exception {
		List<PcPointsDto> pointrecords=pointrecordsMapper.selectByTimes(userid,start);
		if(pointrecords==null){
			return null;
		}else{
			return pointrecords;
		}
	}

}
