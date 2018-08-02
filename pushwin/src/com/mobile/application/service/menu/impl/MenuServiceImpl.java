package com.mobile.application.service.menu.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.dao.IMenuDao;
import com.mobile.application.entity.TBasisMenu;
import com.mobile.application.entity.TBasisMenuAction;
import com.mobile.application.entity.TBasisSystemAction;
import com.mobile.application.service.menu.IMenuService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.menu.MenuVO;

@Service
public class MenuServiceImpl implements IMenuService{
	
	@Autowired
	IMenuDao menuDao;
	
	@Override
	@Transactional
	public List<MenuVO> qryMenu(){
		MenuVO menuVO = new MenuVO("0", "菜单列表", "#", "-1", 0, "0", "icon-add", null);
		List<MenuVO> menuVOs = menuDao.qryMenu();
		menuVOs.add(menuVO);
		return menuVOs;
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.menu.IMenuService#editMenu(com.mobile.application.vo.menu.MenuVO)
	 */
	@Override
	@Transactional
	public void editMenu(MenuVO menuVO) throws IllegalAccessException, InvocationTargetException, BusinessException {
		List<MenuVO> menuVOs = menuDao.qryMenuByName(menuVO);
		if(null != menuVOs && menuVOs.size() > 0 && !menuVO.getMenuId().equals(menuVOs.get(0).getMenuId())){
			throw new BusinessException("1000", "相同的菜单名称已存在。");
		}
		TBasisMenu tBasisMenu = new TBasisMenu();
		BeanUtils.copyProperties(tBasisMenu, menuVO);
		menuDao.editMenu(tBasisMenu);
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.menu.IMenuService#delMenu(com.mobile.application.vo.menu.MenuVO)
	 */
	@Override
	@Transactional
	public void delMenu(MenuVO menuVO) throws IllegalAccessException, InvocationTargetException {
		TBasisMenu tBasisMenu = new TBasisMenu();
		BeanUtils.copyProperties(tBasisMenu, menuVO);
		menuDao.delMenu(tBasisMenu);
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.menu.IMenuService#addMenu(com.mobile.application.vo.menu.MenuVO)
	 */
	@Override
	@Transactional
	public void addMenu(MenuVO menuVO) throws IllegalAccessException, InvocationTargetException {
		TBasisMenu tBasisMenu = new TBasisMenu();
		BeanUtils.copyProperties(tBasisMenu, menuVO);
		menuDao.addMenu(tBasisMenu);
	}

	@Override
	@Transactional(readOnly=true)
	public CommonVO qryActions(String menuId) {
		return new CommonVO(true, menuDao.qryActions(menuId), null);
	}

	@Override
	@Transactional
	public CommonVO saveActions(String actionIds, String menuId) {
		menuDao.deleteAction(menuId);
		String[] actionIdArray = actionIds.split(",");
		for (String actionId : actionIdArray) {
			TBasisSystemAction tBasisSystemAction = new TBasisSystemAction();
			tBasisSystemAction.setId(actionId);
			TBasisMenu tBasisMenu = new TBasisMenu();
			tBasisMenu.setMenuId(menuId);
			TBasisMenuAction tBasisMenuAction = new TBasisMenuAction(tBasisMenu, tBasisSystemAction);
			menuDao.save(tBasisMenuAction);
		}
		return new CommonVO(true, "保存成功");
	}
}
