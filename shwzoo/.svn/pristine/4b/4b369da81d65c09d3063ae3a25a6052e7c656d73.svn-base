package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.FloorproductMapper;
import com.yinlian.wssc.web.po.Floorproduct;
import com.yinlian.wssc.web.service.FloorproductService;
import com.yinlian.wssc.web.util.CriteriaFloorproduct;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 楼层管理
 * @author admin
 *
 */
@Component("FloorproductService")
public class FloorproductServiceImpl implements  FloorproductService {

	private static final Logger logger = LoggerFactory.getLogger(AccountsServiceImpl.class);
	
	@Autowired
	private   FloorproductMapper     floorproductMapper;
	
	@Override
	public int insert(Floorproduct floorproduct) throws Exception {
		
		return floorproductMapper.insert(floorproduct);
	}

	@Override
	public int update(Floorproduct floorproduct) throws Exception {
		
		return floorproductMapper.updateByPrimaryKeySelective(floorproduct);
	}

	@Override
	public int delect(Integer id) throws Exception {
		
		return floorproductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Floorproduct queryById(Integer id) throws Exception {
		
		return floorproductMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageBean queryFloorListByCriteria(CriteriaFloorproduct criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Floorproduct> beanList = floorproductMapper.selectFloorByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
	}

}
