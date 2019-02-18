package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.CircleDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.CircleMapper;
import com.yinlian.wssc.web.po.Circle;
import com.yinlian.wssc.web.po.CircleExample;
import com.yinlian.wssc.web.po.Shopcategory;
/**
 * 商圈业务实现类
 */
import com.yinlian.wssc.web.service.CircleService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaShopCategory;
import com.yinlian.wssc.web.util.PageBeanUtil;


@Component("cricleService")

public class CircleSeviceImpl implements CircleService {

	
	@Autowired
	private CircleMapper        circleMapper;
	
	/**
	 * 查询是否存在改商圈
	 * @see com.yinlian.wssc.web.service.CircleService#queryByName(java.lang.String)
	 */
	@Override
	public Circle queryByName(String name) throws Exception {
		CircleExample example = new CircleExample();
		CircleExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<Circle> list = circleMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}else {
			return null;
		}
	}
	/**
	 * 添加一条商圈
	 * @see com.yinlian.wssc.web.service.CircleService#addBusinessDistrict(java.lang.String)
	 */
	@Override
	public int addBusinessDistrict(String name) throws Exception {
		Circle circle = new Circle();
		circle.setCreatetime(new Date());
		circle.setName(name);
		return circleMapper.insert(circle);
	}
	/**
	 * 编辑商圈
	 * @see com.yinlian.wssc.web.service.CircleService#updatBusinessDistrictById(java.lang.Integer, java.lang.String)
	 */
	@Override
	public int updatBusinessDistrictById(Integer id, String name)
			throws Exception {
		Circle circle = circleMapper.selectByPrimaryKey(id);
		circle.setName(name);
		circle.setCreatetime(new Date());
		return circleMapper.updateByPrimaryKey(circle);
	}
	/**
	 * 根据id查询商圈
	 * @see com.yinlian.wssc.web.service.CircleService#queryById(java.lang.Integer)
	 */
	@Override
	public Circle queryById(Integer id) throws Exception {
		
		return circleMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据id删除商圈
	 * @see com.yinlian.wssc.web.service.CircleService#delBusinessDistrict(java.lang.Integer)
	 */
	@Override
	public int delBusinessDistrict(Integer id) throws Exception {
		
		return circleMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 分页查询商圈列表
	 * @see com.yinlian.wssc.web.service.CircleService#queryBusinessDistrictList(com.yinlian.wssc.web.util.CriteriaShopCategory, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryBusinessDistrictList(CriteriaShopCategory criteria,
			Integer pc, Integer ps) throws Exception {
             PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
                                                                            // 设置其他的参数
                                                                            // 多条件查询
             PageBean pageBean = pageBeanUtil.getPage();
             List<Shopcategory> beanList = circleMapper.selectBusinessDistrictByPage(pageBeanUtil);
             pageBean.setBeanList(beanList);
             return pageBean;
	}
	@Override
	public List<Circle> getAllList() throws Exception {
		CircleExample example = new CircleExample();
		return circleMapper.selectByExample(example);
	}

	@Override
	public PageBean queryAll(Criteria criteria, Integer ipage, Integer isize) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria,ipage, isize);
		PageBean pageBean = pageBeanUtil.getPage();
		List<CircleDto> beanList = circleMapper.queryAllByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}


}
