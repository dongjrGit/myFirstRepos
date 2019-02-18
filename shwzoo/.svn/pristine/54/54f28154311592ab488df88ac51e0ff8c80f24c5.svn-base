package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.mapper.RoleMenusMapper;
import com.yinlian.wssc.web.po.RoleMenus;
import com.yinlian.wssc.web.service.RoleMenusService;
/**
 * 角色菜单的业务实现类
 * @author admin
 *
 */
@Component("roleMenuService")
public class RoleMenusServiceImpl implements RoleMenusService{
	@Autowired
	private RoleMenusMapper           roleMenusMapper;

	
	//@Override
	public List<RoleMenus> selectByRoleId(Integer roleId) {
		
		List<RoleMenus> list=new ArrayList<RoleMenus>();
		try {
			list=roleMenusMapper.selectByRoleID(roleId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
