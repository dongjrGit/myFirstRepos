/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.MenusTreeDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Menus;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 菜单的业务的接口类
 * @author Administrator
 * @version $Id: MenuService.java, v 0.1 2016年3月9日 下午2:05:22 Administrator Exp $
 */
public interface MenuService {

    /**
     * 获取所有平台的一级菜单
     * @param fatherid 为父id 等于0 是一级菜单
     * @return
     */
    List<Menus> queryAllPlatformMenus(Integer fatherid) throws Exception;

    int insert(Menus record) throws Exception;

    Menus getByID(Integer id);

    int update(Menus record, List<Menus> menulist) throws Exception;

    int delete(Integer id) throws Exception;

    PageBean getMenuByPage(Criteria criteria, Integer page, Integer size) throws Exception;

    List<Menus> selectByFullpath(String fullpath) throws Exception;

    List<Menus> getListByFatherid(Integer fid) throws Exception;

    List<MenusTreeDto> getTreeByType(Criteria criteria) throws Exception;

    /**
     * 查询所有的卖家菜单
     * @param fatherid 父类id
     * @return
     */
    List<Menus> queryAllSellerMenus(int fatherid) throws Exception;

    /**
     * 根据权限id集合查询权限集合
     * @param rigths
     * @return
     */
    List<Menus> queryByRrigth(List<Integer> rigths) throws Exception;
    
    int deletemenu(int userid,int id) throws Exception;

}
