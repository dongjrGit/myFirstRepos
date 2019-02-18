/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.Enums.UserStatusEnum;
import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wap.dto.ShowarticleDto;
import com.yinlian.wssc.platform.vo.AccountsVo;
import com.yinlian.wssc.web.dto.AccountsDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.dto.UsersInfoDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.AccountsMapper;
import com.yinlian.wssc.web.mapper.AccountsMapperCustom;
import com.yinlian.wssc.web.mapper.EmployeeMapper;
import com.yinlian.wssc.web.mapper.MessagesMapper;
import com.yinlian.wssc.web.mapper.RoleMenusMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.UserAttrMapper;
import com.yinlian.wssc.web.mapper.UserDepartMapper;
import com.yinlian.wssc.web.mapper.UsercapitalMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.mapper.UserslevelMapper;
import com.yinlian.wssc.web.mapper.showarticleMapper;
import com.yinlian.wssc.web.mapper.showuserMapper;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.po.Employee;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.po.RoleMenus;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.UserAttr;
import com.yinlian.wssc.web.po.UserDepart;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.po.Userslevel;
import com.yinlian.wssc.web.po.showarticle;
import com.yinlian.wssc.web.po.showuser;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.showarticleService;
import com.yinlian.wssc.web.service.showuserService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.CriteriaSku;
import com.yinlian.wssc.web.util.CriteriaUser;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.UserXml;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 * @version $Id: AccountsServiceImpl.java, v 0.1 2016年2月26日 下午1:27:03
 *          Administrator Exp $
 */
@Component("showuserService")
public class ShowuserServiceImpl implements showuserService {

	@Autowired
	private showuserMapper   showuserMapper;
	@Autowired
	private showarticleMapper  showarticleMapper;
	@Autowired
	private  UsersMapper      usersMapper;

	@Override
	public int insertshowuser(showuser vo) throws Exception {
		
		return showuserMapper.insertSelective(vo);
	}
	@Override
	public PageBean getLikeList(Integer pc, Integer ps, CriteriaSku aoc)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(aoc, pc, ps);// 还可以
		// 设置其他的参数
		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<showarticle> beanList = showarticleMapper.getLikePage(pageBeanUtil);
		List<ShowarticleDto> list=new ArrayList<ShowarticleDto>();
		for(int i=0;i<beanList.size();i++){
			ShowarticleDto dto=new ShowarticleDto();
			Integer showid=beanList.get(i).getId();
			int  count=showuserMapper.selectCount(showid);
			Users users=usersMapper.selectByPrimaryKey(beanList.get(i).getUserid());
			dto.setId(beanList.get(i).getId());
			dto.setImgone(beanList.get(i).getImgone());
			dto.setImgtwo(beanList.get(i).getImgtwo());
			dto.setImgthr(beanList.get(i).getImgthr());
			dto.setUserid(beanList.get(i).getUserid());
			dto.setNickname(beanList.get(i).getNickname());
			dto.setProid(beanList.get(i).getProid());
			dto.setProname(beanList.get(i).getProname());
			dto.setProprice(beanList.get(i).getProprice());
			dto.setProimg(beanList.get(i).getProimg());
			dto.setCreatetime(beanList.get(i).getCreatetime());
			if(users!=null){
				dto.setImgurl(users.getImgurl());
			}
			dto.setLikeCount(count);
			list.add(dto);
		}
		pageBean.setBeanList(list);
		return pageBean;
	}

   
    

}
