package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.search.Pc_GoodConsultCriteria;
import com.yinlian.wssc.web.dto.GoodConsultDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.GoodconsultMapper;
import com.yinlian.wssc.web.po.Goodconsult;
import com.yinlian.wssc.web.service.GoodConsultService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaGoodConsult;
import com.yinlian.wssc.web.util.PageBeanUtil;
@Component("goodConsultService")
public class GoodConsultServiceImpl implements GoodConsultService {
	/**
	 * 输出日志的控制类
	 */
	private static final Logger logger = LoggerFactory.getLogger(UsercollectServiceImpl.class);

	@Autowired
	private GoodconsultMapper goodconsultMapper;
	/**
	 * 分页查询咨询列表
	 * @see com.yinlian.wssc.web.service.GoodConsultService#queryGoodConsultByCriteria(com.yinlian.wssc.web.util.CriteriaGoodConsult, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryGoodConsultByCriteria(Criteria criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<GoodConsultDto> beanList = goodconsultMapper.selectGoodConsuByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
		return pageBean;
	}
	@Override
	public PageBean queryPage(CriteriaGoodConsult consult, Integer integer,
			Integer integer2) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(consult,integer, integer2);
		 PageBean pageBean = pageBeanUtil.getPage();
		 List<GoodConsultDto> beanList = goodconsultMapper.queryPage(pageBeanUtil);
		 pageBean.setBeanList(beanList);
		return pageBean;
	}
	/**
	 * 根据id查询商品咨询
	 * @see com.yinlian.wssc.web.service.GoodConsultService#selectGoodConsultById(java.lang.Integer)
	 */
	@Override
	public Goodconsult selectGoodConsultById(Integer id) throws Exception {
		
		return goodconsultMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据id修改商品咨询
	 * @see com.yinlian.wssc.web.service.GoodConsultService#updateById(java.lang.Integer)
	 */
	@Override
	public int updateById(Goodconsult goodconsult) throws Exception {
		
		return goodconsultMapper.updateByPrimaryKey(goodconsult);
	}
	@Override
	public PageBean queryUserGoodConsultByCriteria(
			Pc_GoodConsultCriteria criteria, Integer pc, Integer ps)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<GoodConsultDto> beanList = goodconsultMapper.selectUserGoodConsuByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
		return pageBean;
	}
	@Override
	public int addConsult(Goodconsult goodconsult) throws Exception {
		return goodconsultMapper.insert(goodconsult);
	}
	

}
