package com.mobile.application.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IKnowledgeDao;
import com.mobile.application.entity.TBasisKnowledgeMenu;
import com.mobile.application.vo.product.ProductMenuVO;

@Repository
public class KnowledgeDaoImpl extends BaseDAOImpl<TBasisKnowledgeMenu> implements IKnowledgeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductMenuVO> qryMenuByName(ProductMenuVO menuVO){
		String hql = "select new com.mobile.application.vo.product.ProductMenuVO(menuId, menuName, menuDesc, menuPid, menuLevel, menuSort, menuIcon) from TBasisKnowledgeMenu where menuName=:menuName";
		return this.getCurrentSession().createQuery(hql).setParameter("menuName", menuVO.getMenuName()).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductMenuVO> qryProductMenu() {
		return this.getCurrentSession().createQuery("select new com.mobile.application.vo.product.ProductMenuVO(menuId, menuName, menuDesc, menuPid, menuLevel, menuSort, menuIcon) from TBasisKnowledgeMenu").list();
	}

}
