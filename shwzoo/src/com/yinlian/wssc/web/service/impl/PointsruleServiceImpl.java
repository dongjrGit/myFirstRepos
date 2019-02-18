package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.PointsruleMapper;
import com.yinlian.wssc.web.po.Pointsrule;
import com.yinlian.wssc.web.po.PointsruleExample;
import com.yinlian.wssc.web.service.PointsruleService;

@Component("/pointsruleService")
public class PointsruleServiceImpl implements PointsruleService {
	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(PointsruleServiceImpl.class);
	
	@Autowired
	private PointsruleMapper pointsruleMapper;
	/**
	 * 根据userlevel查询积分规则
	 * @see com.yinlian.wssc.web.service.PointsruleService#querPointsruleByUserLevel(java.lang.String)
	 */
	@Override
	public List<Pointsrule> querPointsruleByUserLevel(Integer userlevel) {
		PointsruleExample example = new PointsruleExample();
		PointsruleExample.Criteria criteria = example.createCriteria();
		criteria.andUserlevelEqualTo(userlevel);
		return pointsruleMapper.selectByExample(example);
	}
	/**
	 * 添加积分设置
	 * @see com.yinlian.wssc.web.service.PointsruleService#addPointsrule(com.yinlian.wssc.web.po.Pointsrule)
	 */
	@Override
	public int addPointsrule(Pointsrule pointsrule) throws Exception {
		
		return pointsruleMapper.insertSelective(pointsrule);
	}
	/**
	 * 根据name和userlevel查询积分规则
	 * @see com.yinlian.wssc.web.service.PointsruleService#querPointsruleByUserLevelAndName(java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<Pointsrule> querPointsruleByUserLevelAndName(Integer userlevel,
			String name) throws Exception {
		PointsruleExample example = new PointsruleExample();
		PointsruleExample.Criteria criteria = example.createCriteria();
		criteria.andUserlevelEqualTo(userlevel);
		criteria.andNameEqualTo(name);
		return pointsruleMapper.selectByExample(example);
	}
	/**
	 * 编辑积分配置 
	 * @see com.yinlian.wssc.web.service.PointsruleService#updatePointsruleByUserLevel(com.yinlian.wssc.web.po.Pointsrule)
	 */
	@Override
	public int updatePointsruleByUserLevel(Pointsrule pointsrule)
			throws Exception {
		
		return pointsruleMapper.updateByPrimaryKey(pointsrule);
	}
	@Override
	public List<Pointsrule> queryPointsrule(Integer userid) throws Exception {
		
		 
		return null;
	}

}
