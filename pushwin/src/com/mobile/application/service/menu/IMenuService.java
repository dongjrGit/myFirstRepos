package com.mobile.application.service.menu;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.menu.MenuVO;

public interface IMenuService {

	/**
	 * 查询菜单列表
	 * 1.查询数据库中的菜单列表
	 * 2.拼接根目录信息
	 * @return
	 */
	List<MenuVO> qryMenu();
	/**
	 * 编辑菜单
	 * @param menuVO
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws BusinessException
	 */
	void editMenu(MenuVO menuVO) throws IllegalAccessException, InvocationTargetException, BusinessException;
	/**
	 * 删除菜单
	 * @param menuVO
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	void delMenu(MenuVO menuVO) throws IllegalAccessException, InvocationTargetException;
	/**
	 * 添加菜单
	 * @param menuVO
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	void addMenu(MenuVO menuVO) throws IllegalAccessException, InvocationTargetException;
	CommonVO qryActions(String menuId);
	com.mobile.application.vo.CommonVO saveActions(String actionIdList,
			String menuId);

}
