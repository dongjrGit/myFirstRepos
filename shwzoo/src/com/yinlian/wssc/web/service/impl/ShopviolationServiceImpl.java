package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.VoilationShopDTO;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.ShopormemberstatushistoryMapper;
import com.yinlian.wssc.web.mapper.ShopviolationMapper;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Shopormemberstatushistory;
import com.yinlian.wssc.web.po.Shopviolation;
import com.yinlian.wssc.web.service.ShopviolationService;
import com.yinlian.wssc.web.util.CriteriaVoilationShop;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("shopviolationService")
public class ShopviolationServiceImpl implements ShopviolationService{
	
	@Autowired
	private ShopMapper      shopMapper;
	
	@Autowired
	private ShopormemberstatushistoryMapper   shopormemberstatushistoryMapper;
	
	@Autowired
	private ShopviolationMapper   shopviolationMapper;
	
	@Override
	public int insertviolation(Shop shop, Shopormemberstatushistory shopstatus,
			Shopviolation shopviolation) throws Exception {
		int temp=shopMapper.updateByPrimaryKey(shop);
		int temp1=shopormemberstatushistoryMapper.insertSelective(shopstatus);
		int temp2=shopviolationMapper.insertSelective(shopviolation);
		if((temp>0)&&(temp1>0)&&(temp2>0)){
			return 1;
		}else{
			return 0;
		}
		 
	}

	@Override
	public int delete(Integer violationId) throws Exception {
		
		return 0;
	}

	@Override
	public Shopviolation queryBykey(Integer id) throws Exception {
		
		return shopviolationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int delviolation(Shopormemberstatushistory shopstatus, Shop shop,
			Shopviolation shopviolation) {
		int temp=shopMapper.updateByPrimaryKeySelective(shop);
		int temp1=shopormemberstatushistoryMapper.updateByPrimaryKeySelective(shopstatus);
		int temp2=shopviolationMapper.updateByPrimaryKeySelective(shopviolation);
		if((temp>0)&&(temp1>0)&&(temp2>0)){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public PageBean queryShopVoilationListByCriteria(
			CriteriaVoilationShop criteria, Integer pc, Integer ps) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
		PageBean pageBean = pageBeanUtil.getPage();
		List<VoilationShopDTO> beanList = shopviolationMapper.selectVoilationShopByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
		
	}

	@Override
	public PageBean queryShopHistoryVoilationListByCriteria(
			CriteriaVoilationShop criteria, Integer pc, Integer ps) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
		PageBean pageBean = pageBeanUtil.getPage();
		List<VoilationShopDTO> beanList = shopviolationMapper.selectHistoryVoilationShopByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

}
