package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ApplyshopMapper;
import com.yinlian.wssc.web.po.Applyshop;
import com.yinlian.wssc.web.service.ApplyshopService;
import com.yinlian.wssc.web.util.CriteriaApplyShop;
import com.yinlian.wssc.web.util.PageBeanUtil;
/**
 * 申请加盟的实现类
 * ApplyshopServiceImpl.java
 * @author Administrator
 * @version $Id: ApplyshopServiceImpl.java, v 0.1 2016年5月13日 下午6:20:29 Administrator Exp $
 */
@Component("applyshopService")
public class ApplyshopServiceImpl implements ApplyshopService {
	@Autowired
    private ApplyshopMapper  applyshopMapper;
	/**
	 * 申请加盟
	 * @see com.yinlian.wssc.web.service.ApplyshopService#inset(com.yinlian.wssc.web.po.Applyshop)
	 */
	@Override
	public int inset(Applyshop applyshop) throws Exception {
		
		return applyshopMapper.insert(applyshop);
	}
	@Override
	public PageBean queryApplyShopList(CriteriaApplyShop criteria, Integer pc,
			Integer ps) throws Exception {
		 PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
                                                                       // 设置其他的参数
                                                                      // 多条件查询
         PageBean pageBean = pageBeanUtil.getPage();
         List<Applyshop> beanList = applyshopMapper.selectApplyShopByPage(pageBeanUtil);
         pageBean.setBeanList(beanList);
         return pageBean;
	}
	/**
	 * 删除申请店铺
	 * @see com.yinlian.wssc.web.service.ApplyshopService#delApplyShop(java.lang.Integer)
	 */
	@Override
	public int delApplyShop(Integer id) throws Exception {
		
		return applyshopMapper.deleteByPrimaryKey(id);
	}
	@Override
	public int updateApplyShop(Integer id) throws Exception {
		Applyshop applyshop = applyshopMapper.selectByPrimaryKey(id);
		applyshop.setIscontact(true);
		return applyshopMapper.updateByPrimaryKey(applyshop);
	}
}
