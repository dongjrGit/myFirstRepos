package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.search.Pc_PointsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Pointsrecords;
import com.yinlian.wssc.web.util.CriteriaMemberPoints;

public interface PointsRecordService {
	int addPoints(Integer userid) throws Exception;
	
	List<Pointsrecords> selectByuserid(Integer userid) throws Exception;
	
	List<Pointsrecords> selectByuser(Integer userid) throws Exception;
	
	/**
	 * 用户积分变更
	 * @param points
	 * @param userid
	 * @param fromtype
	 * @param recordtype
	 * @return
	 * @throws Exception
	 */
	public int updateUserPoint(int points,int userid,int fromtype,int recordtype)throws Exception;
	
	/**
	 * 查询签到可得积分
	 * @param userId
	 * @return
	 */
	Integer getPoints(Integer userId) throws Exception;
	
	/**
	 * 查询没有用户的签到记录
	 * @param userId
	 * @return
	 */
	List<Pointsrecords> queryByUserId(Integer userId) throws Exception;
	
	/**
	 * 查询连续签到的天数
	 * @param userId
	 * @return
	 */
	int getContinousCount(Integer userId) throws Exception;

	PageBean pointListByPage(Pc_PointsCriteria criteria, Integer pc, Integer ps) throws Exception;

	PageBean PlatformpointListByPage(CriteriaMemberPoints criteria, Integer pc, Integer ps) throws Exception;
}
