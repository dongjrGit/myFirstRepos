package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.dto.MenusTreeDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.MenusMapperCustom;
import com.yinlian.wssc.web.mapper.RoleMapper;
import com.yinlian.wssc.web.mapper.RoleMenusMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.po.Role;
import com.yinlian.wssc.web.po.RoleExample;
import com.yinlian.wssc.web.po.RoleMenus;
import com.yinlian.wssc.web.service.RoleService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaMenu;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 角色的业务实现类
 * RoleServiceImpl.java
 * @author Administrator
 * @version $Id: RoleServiceImpl.java, v 0.1 2016年3月14日 下午5:56:41 Administrator Exp $
 */
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper        roleMapper;
    @Autowired
    private MenusMapperCustom menuMapperCustom;
    @Autowired
    private RoleMenusMapper   roleMenusMapper;
    
	@Autowired
	private ShopMapper shopMapper;

    public int insert(Role record) throws Exception {
        return roleMapper.insert(record);
    }

    public int update(Role record) throws Exception {
        return roleMapper.updateByPrimaryKey(record);
    }

    public int delete(Integer id) throws Exception {
    	Role role=roleMapper.selectByPrimaryKey(id);
    	role.setIsdel(true);
    	return roleMapper.updateByPrimaryKey(role);
//        return roleMapper.deleteByPrimaryKey(id);
    }

    public PageBean getList(Criteria a, Integer page, Integer size) throws Exception {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(a, page, size);// 还可以
        PageBean pageBean = pageBeanUtil.getPage();

        List<Role> beanList = roleMapper.getRoleByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    public List<Role> getRoleStartWithName(String name, Integer shopid) throws Exception {
        return roleMapper.getRoleStartWithName(shopid, name);
    }

    public Role getByID(Integer id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }

    /** 
     * @see com.yinlian.wssc.web.service.RoleService#selectShopRole(java.lang.Integer)
     */
    @Override
    public List<Role> selectShopRole(Integer shopid) throws Exception {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        criteria.andShopidEqualTo(shopid);
        criteria.andIsdelEqualTo(false);
        return roleMapper.selectByExample(example);
    }

    public int updateStatus(Integer status, Integer id) throws Exception {
        Role record = new Role();
        record.setId(id);
        record.setStatus(status);

        return roleMapper.updateStatus(record);
    }

    /**
     * 获取对应菜单树
     * @param type 菜单类型 菜单/按钮
     * @param menutype 平台类型 平台/卖家
     * @return
     * @throws Exception
     */
    public List<MenusTreeDto> getByMenuType(Integer type, Integer menutype) throws Exception {

        CriteriaMenu cMenu = new CriteriaMenu();
        cMenu.setType(type);
        cMenu.setMenutype(menutype);
        return menuMapperCustom.getTreeByType(cMenu);
    }

    /**
     * 添加角色关联菜单
     * @param idlist 菜单ID集合
     * @param roleid 角色ID
     */
    public int insertList(List<Integer> idlist, Integer roleid) throws Exception {

        Integer ret = 0;
        List<RoleMenus> rmlist = new ArrayList<RoleMenus>();
        RoleMenus rMenus = null;
        for (Integer mid : idlist) {
            rMenus = new RoleMenus();
            rMenus.setMenusid(mid);
            rMenus.setRoleid(roleid);
            rmlist.add(rMenus);
        }
        //先删除原先关联菜单
        ret = roleMenusMapper.deleteByRoleID(roleid);
        //再插入新关联菜单
        ret = roleMenusMapper.insertList(rmlist);
        return ret;
    }

    /**
     * 根据角色ID删除关联菜单
     */
    public int deleteByRoleID(Integer roleid) throws Exception {
        return roleMenusMapper.deleteByRoleID(roleid);
    }

    /**
     * 根据角色ID获取关联菜单
     */
    public List<RoleMenus> selectByRoleID(Integer roleid) throws Exception {
        return roleMenusMapper.selectByRoleID(roleid);
    }
    /**
     * 验证角色
     * @see com.yinlian.wssc.web.service.RoleService#isHave(int, java.lang.String)
     */

	@Override
	public List<Role> isHave(int shopId, String name) throws Exception {
		RoleExample example = new RoleExample();
		RoleExample.Criteria criteria = example.createCriteria();
		criteria.andShopidEqualTo(shopId);
		criteria.andNameEqualTo(name);
		criteria.andIsdelEqualTo(false);
		
		return roleMapper.selectByExample(example);
	}
	

}
