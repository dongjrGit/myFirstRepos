package com.yinlian.wssc.web.service;
import java.util.List;

import com.yinlian.Enums.PointsRecordsFromTypeEnum;
import com.yinlian.Enums.PointsRecordsTypeEnum;
import com.yinlian.pc.dto.PcPointsDto;
import java.util.List;

import com.yinlian.wssc.search.Pc_PointsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Pointrecords;
import com.yinlian.wssc.web.po.Pointsrecords;


public interface PointRecordService {

	int add(int points, PointsRecordsTypeEnum type, PointsRecordsFromTypeEnum fromType, Integer userid) throws Exception;
	
	public List<PcPointsDto> selectByUserid(Integer userid) throws Exception;
	
	public List<PcPointsDto> selectByType(Integer userid,Integer type) throws Exception;
	
	public int insertPoint(Pointrecords pointrecords,Pointsrecords pointsrecords) throws Exception;

	//int insertPoint(Pointrecords pointrecords) throws Exception;
	
	public List<PcPointsDto> selectByTime(Integer userid,String start,String end) throws Exception;
	
	public List<PcPointsDto> selectByTimes(Integer userid,String start) throws Exception;

	 

}
