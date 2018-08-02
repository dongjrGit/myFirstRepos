package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisMenu;
import com.mobile.application.vo.menu.MenuVO;

public interface IMenuDao  extends IBaseDAO<TBasisMenu>{

	List<MenuVO> qryMenu();

	void editMenu(TBasisMenu tBasisMenu);

	void delMenu(TBasisMenu tBasisMenu);

	void addMenu(TBasisMenu tBasisMenu);

	List<MenuVO> qryMenuByName(MenuVO menuVO);

	List<?> qryActions(String menuId);

	void deleteAction(String menuId);

}
