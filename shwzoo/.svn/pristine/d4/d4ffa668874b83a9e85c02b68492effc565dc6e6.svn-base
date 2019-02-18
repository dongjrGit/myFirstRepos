package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.GroupBuyDetailDto;
import com.yinlian.api.app.dto.GroupBuyDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.GroupbuyMapper;
import com.yinlian.wssc.web.mapper.GroupbuyMapperCustom;
import com.yinlian.wssc.web.po.Groupbuy;
import com.yinlian.wssc.web.po.GroupbuyWithBLOBs;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.service.GroupBuyService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("groupBuyService")
public class GroupBuyServiceImpl implements GroupBuyService {

	@Autowired
	private GroupbuyMapper groupbuyMapper;
	
	@Autowired
	private GroupbuyMapperCustom groupbuyMapperCustom;

	public int insert(GroupbuyWithBLOBs record) throws Exception {
		return groupbuyMapper.insert(record);
	}

	public GroupbuyWithBLOBs selectByPrimaryKey(Integer id) throws Exception {
		return groupbuyMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeyWithBLOBs(GroupbuyWithBLOBs record)
			throws Exception {
		return groupbuyMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(Groupbuy record) throws Exception {
		return groupbuyMapper.updateByPrimaryKey(record);
	}

	public PageBean getListByPage(Criteria criteria, Integer page, Integer size)
			throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();
		List<Groupbuy> list = groupbuyMapper.getListByPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	public int deleteGroup(int id) throws Exception {
		Groupbuy gb = new Groupbuy();
		gb.setId(id);
		gb.setIsdel(true);
		gb.setDeltime(new Date());
		return groupbuyMapper.deleteGroup(gb);
	}
	
	public int updateStatus(int status,int id)throws Exception{
		return groupbuyMapper.updateStatus(status, id);
	}
	
	public PageBean getGroupByPage(Criteria criteria, Integer page, Integer size)
			throws Exception{
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();
		List<GroupBuyDto> list = groupbuyMapperCustom.getGroupByPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}
	
	public GroupBuyDetailDto getDetail(Integer id)throws Exception{
		return groupbuyMapperCustom.getDetail(id);
	}

	@Override
	public List<Groupbuyorder> getGroupbuyCode(String groupnum)
			throws Exception {
		
		//return groupbuyMapper.getGroupOrderByGroupCode(groupnum);
		return null;
	}
}
