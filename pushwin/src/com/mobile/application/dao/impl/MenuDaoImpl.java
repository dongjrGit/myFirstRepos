package com.mobile.application.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IMenuDao;
import com.mobile.application.entity.TBasisMenu;
import com.mobile.application.vo.menu.MenuVO;

@Repository
public class MenuDaoImpl extends BaseDAOImpl<TBasisMenu> implements IMenuDao {
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuVO> qryMenu(){
		return this.getCurrentSession().createQuery("select new com.mobile.application.vo.menu.MenuVO(menuId, menuName, menuUrl, menuPid, menuLevel, menuSort, menuIcon, iconPosition) from TBasisMenu").list();
	}

	@Override
	public void editMenu(TBasisMenu tBasisMenu) {
		this.saveOrUpdate(tBasisMenu);
	}

	@Override
	public void delMenu(TBasisMenu tBasisMenu) {
		this.delete(tBasisMenu);
	}

	@Override
	public void addMenu(TBasisMenu tBasisMenu) {
		this.save(tBasisMenu);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MenuVO> qryMenuByName(MenuVO menuVO){
		String hql = "select new com.mobile.application.vo.menu.MenuVO(menuId, menuName, menuUrl, menuPid, menuLevel, menuSort, menuIcon, iconPosition) from TBasisMenu where menuName=:menuName";
		return this.getCurrentSession().createQuery(hql).setParameter("menuName", menuVO.getMenuName()).list();
	}
	
	@Override
	public void deleteAction(String menuId){
		String hql = "delete from TBasisMenuAction tma where tma.TBasisMenu.menuId =:menuId";
		this.getCurrentSession().createQuery(hql).setParameter("menuId", menuId).executeUpdate();
	}
	
	@Override
	public List<?> qryActions(String menuId) {
		String hql;
		if (StringUtils.isNotBlank(menuId)) {
			hql = "select new map(tma.TBasisSystemAction.id as id, tma.TBasisSystemAction.actionName as actionName, tma.TBasisSystemAction.actionUrl as actionUrl, tma.TBasisSystemAction.actionModel as actionModel) from TBasisMenuAction tma where tma.TBasisMenu.menuId=:menuId";
		} else {
			hql = "select new map(id as id, actionName as actionName, actionUrl as actionUrl, actionModel as actionModel) from TBasisSystemAction";
		}
		Query query =  this.getCurrentSession().createQuery(hql);
		if (StringUtils.isNotBlank(menuId)) {
			query.setParameter("menuId", menuId);
		}
		return query.list();
	}
}
