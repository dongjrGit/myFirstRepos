/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.wssc.web.dto.MenusTreeDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.MenusMapper;
import com.yinlian.wssc.web.mapper.MenusMapperCustom;
import com.yinlian.wssc.web.po.Menus;
import com.yinlian.wssc.web.service.MenuService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 菜单业务类的实现层
 * 
 * @author Administrator
 * @version $Id: MenuServiceImpl.java, v 0.1 2016年3月9日 下午2:13:49 Administrator
 *          Exp $
 */
public class MenuServiceImpl implements MenuService {

    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    private MenusMapper         menusMapper;
    @Autowired
    private MenusMapperCustom   menusMapperCustom;

    /**
     * @see com.yinlian.wssc.web.service.MenuService#queryAllMenus(java.lang.Integer)
     */
    @Override
    public List<Menus> queryAllPlatformMenus(Integer fatherid) throws Exception {

        return queryPlatformMenus(fatherid);
    }

    /*
     * 递归方法
     */
    private List<Menus> queryPlatformMenus(Integer fatherid) throws Exception {

        List<Menus> list = menusMapper.selectAllPlatformMenus(fatherid);
        if (list != null && list.size() > 0) {
            for (Menus menus : list) {
                if (menus != null) {
                    List<Menus> childs = queryPlatformMenus(menus.getId());
                    menus.setChildrens(childs);
                }
            }
        }
        return list;
    }

    /**
     * 添加菜单
     * @param record
     * @return
     * @throws Exception
     */
    public int insert(Menus record) throws Exception {
        Integer returns = 0;

        returns = menusMapper.insertMenu(record);
        //返回插入主键 修改全路径
        if (record.getFatherid() == 0) {
            record.setFullpath(record.getId().toString());
        } else {
            record.setFullpath(record.getFullpath() + "," + record.getId());
        }
        returns = menusMapper.updateFullPath(record);

        return returns;
    }

    /**
     * 根据ID获取菜单信息
     * @param id
     * @return
     */
    public Menus getByID(Integer id) {
        return menusMapper.selectByPrimaryKey(id);
    }

    /**
     * 编辑菜单
     * @param record
     * @return
     * @throws Exception
     */
    public int update(Menus record, List<Menus> menulist) throws Exception {
        List<Menus> listnew = menulist;
        menusMapper.updateByPrimaryKey(record);
        for (Menus menu : listnew) {
            if (menu.getFatherid() == record.getId()) {
                menulist = changeFullpath(listnew, record);
            } else
                menulist = changeFullpath(listnew, menu);
            if (menu.getId() == record.getId()) {
                menu.setFullpath(record.getFullpath());
                menu.setLevel(record.getLevel());
            }
        }
        return menusMapper.updateMenuList(menulist);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Integer id) throws Exception {
        return menusMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取菜单列表
     * @param criteria 条件筛选
     * @param page
     * @param size
     * @return
     * @throws Exception
     */
    public PageBean getMenuByPage(Criteria criteria, Integer page, Integer size) throws Exception {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
        PageBean pageBean = pageBeanUtil.getPage();

        List<Menus> beanList = menusMapper.selectMenuByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);

        return pageBean;
    }

    /**
     * 根据全路径获取菜单列表
     * @param fullpath
     * @return
     * @throws Exception
     */
    public List<Menus> selectByFullpath(String fullpath) throws Exception {
        return menusMapper.selectByFullpath(fullpath);
    }

    /**
     * 修改父菜单下所有子菜单的路径
     * @param list
     * @param m
     * @return
     */
    private List<Menus> changeFullpath(List<Menus> list, Menus m) {
        for (Menus menu : list) {
            if (menu.getFatherid() == m.getId()) {
                menu.setFullpath(m.getFullpath() + "," + menu.getId());
                menu.setLevel(m.getLevel() + 1);
            }
        }
        return list;
    }

    /**
     * 根据父id获取子菜单列表
     */
    public List<Menus> getListByFatherid(Integer fid) throws Exception {
        return menusMapper.getListByFatherid(fid);
    }

    /**
     * 根据菜单类型获取菜单树
     */
    public List<MenusTreeDto> getTreeByType(Criteria criteria) throws Exception {
        return menusMapperCustom.getTreeByType(criteria);
    }

    /**
     * @see com.yinlian.wssc.web.service.MenuService#queryAllSellerMenus(int)
     */
    @Override
    public List<Menus> queryAllSellerMenus(int fatherid) throws Exception {

        return querySellerMenus(fatherid);
    }

    /*
     * 递归方法
     */
    private List<Menus> querySellerMenus(Integer fatherid) throws Exception {

        List<Menus> list = menusMapper.selectAllSellerMenus(fatherid);
        if (list != null && list.size() > 0) {
            for (Menus menus : list) {
                if (menus != null) {
                    List<Menus> childs = querySellerMenus(menus.getId());
                    menus.setChildrens(childs);
                }
            }
        }
        return list;
    }

    /**
     * @see com.yinlian.wssc.web.service.MenuService#queryByRrigth(java.util.List)
     */
    @Override
    public List<Menus> queryByRrigth(List<Integer> rigths) throws Exception {
    	List<Menus> listall = menusMapper.selectByRigth(rigths);
    	List<Menus> list =new ArrayList<Menus>();

    	 if (listall != null && listall.size() > 0) {    		 
    		 
             for (Menus menus : listall) {
                 if (menus != null) {
                	 List<Menus> childs=listall.stream().filter(x->x.getFatherid().equals(menus.getId())).collect(Collectors.toList());
                     menus.setChildrens(childs);
                 }
             }
             
             list=listall.stream().filter(x->x.getFatherid()==0).collect(Collectors.toList());
         }
        return list;
    }

	@Override
	public int deletemenu(int userid,int id) throws Exception {
		Menus menus=new Menus();
		menus.setDeluser(userid);
		menus.setIsdel(true);
		menus.setId(id);
		return menusMapper.deletemenu(menus);
	}
}
