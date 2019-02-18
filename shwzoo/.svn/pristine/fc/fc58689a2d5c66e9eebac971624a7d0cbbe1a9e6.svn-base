package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.MenusTreeDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Role;
import com.yinlian.wssc.web.po.RoleMenus;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 角色的业务接口类
 * RoleService.java
 * @author Administrator
 * @version $Id: RoleService.java, v 0.1 2016年3月14日 下午5:56:56 Administrator Exp $
 */
public interface RoleService {

    int insert(Role record) throws Exception;

    int update(Role record) throws Exception;

    int delete(Integer id) throws Exception;

    PageBean getList(Criteria a, Integer page, Integer size) throws Exception;

    List<Role> getRoleStartWithName(String name, Integer shopid) throws Exception;

    Role getByID(Integer id) throws Exception;

    /**
     * 根据shopid查询角色集合
     * @param shopid
     * @return
     */
    List<Role> selectShopRole(Integer shopid) throws Exception;

    int updateStatus(Integer status, Integer id) throws Exception;

    List<MenusTreeDto> getByMenuType(Integer type, Integer menutype) throws Exception;

    int insertList(List<Integer> idlist, Integer roleid) throws Exception;

    int deleteByRoleID(Integer roleid) throws Exception;

    List<RoleMenus> selectByRoleID(Integer roleid) throws Exception;
    /**
     * 验证角色
     * 
     * @param shopId
     * @param name
     * @throws Exception
     */

    List<Role> isHave(int shopId, String name) throws Exception;
	

}
