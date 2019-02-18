package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.Pointsrule;

/**
 * 积分规则的业务类
 * @author Administrator
 *
 */
public interface PointsruleService {

	/**
	 * 根据userlevel查询积分规则
	 * @param userlevel
	 * @return
	 * @throws Exception
	 */
	List<Pointsrule> querPointsruleByUserLevel(Integer userlevel) throws Exception;

	/**
	 * 添加积分设置
	 * @param pointsrule
	 * @return
	 * @throws Exception
	 */
	int addPointsrule(Pointsrule pointsrule) throws Exception;

	/**
	 * 根据name和userlevel查询积分规则
	 * @param userlevel
	 * @param name
	 * @return
	 */
	List<Pointsrule> querPointsruleByUserLevelAndName(Integer userlevel, String name) throws Exception;

	/**
	 * 编辑积分配置 
	 * @param pointsrule
	 * @return
	 * @throws Exception
	 */
	int updatePointsruleByUserLevel(Pointsrule pointsrule) throws Exception;

	List<Pointsrule> queryPointsrule(Integer userid) throws Exception;

}
