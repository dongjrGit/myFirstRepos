package com.mobile.application.service.knowledge.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.dao.IKnowledgeDao;
import com.mobile.application.dao.IProductDao;
import com.mobile.application.entity.TBasisKnowledgeMenu;
import com.mobile.application.entity.TBasisProductMenu;
import com.mobile.application.service.knowledge.IKnowledgeService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.product.ProductMenuVO;


@Service
public class KnowledgeServiceImpl implements IKnowledgeService {
	@Autowired
	private IKnowledgeDao knowledgeDao;
	@Autowired
	private ServletContext servletContext;

		@Override
		@Transactional
		public void addMenu(ProductMenuVO menuVO) throws IllegalAccessException, InvocationTargetException, BusinessException {
			List<ProductMenuVO> menuVOs = knowledgeDao.qryMenuByName(menuVO);
			if(null != menuVOs && menuVOs.size() > 0){
				throw new BusinessException("1000", "相同的菜单名称已存在。");
			}
			TBasisKnowledgeMenu tBasisKnowledgeMenu = new TBasisKnowledgeMenu();
			BeanUtils.copyProperties(tBasisKnowledgeMenu, menuVO);
			knowledgeDao.save(tBasisKnowledgeMenu);
		}
		

		@Override
		@Transactional
		public void delMenu(ProductMenuVO menuVO) throws IllegalAccessException, InvocationTargetException {
			TBasisKnowledgeMenu tBasisKnowledgeMenu = new TBasisKnowledgeMenu();
			BeanUtils.copyProperties(tBasisKnowledgeMenu, menuVO);
			knowledgeDao.delete(tBasisKnowledgeMenu);
		}

	@Override
	@Transactional
	public void editMenu(ProductMenuVO menuVO) throws IllegalAccessException, InvocationTargetException, BusinessException {
		List<ProductMenuVO> menuVOs = knowledgeDao.qryMenuByName(menuVO);
		if(null != menuVOs && menuVOs.size() > 0 && !menuVO.getMenuId().equals(menuVOs.get(0).getMenuId())){
			throw new BusinessException("1000", "相同的菜单名称已存在。");
		}
		TBasisKnowledgeMenu tBasisKnowledgeMenu = new TBasisKnowledgeMenu();
		BeanUtils.copyProperties(tBasisKnowledgeMenu, menuVO);
		knowledgeDao.saveOrUpdate(tBasisKnowledgeMenu);
	}

	@Override
	@Transactional
	public List<ProductMenuVO> qryKnowledgeMenu(String root) {
		List<ProductMenuVO> menuVOs = knowledgeDao.qryProductMenu();
		if(!"0".equals(root)){
			ProductMenuVO menuVO = new ProductMenuVO("0", "菜单列表", "#", "-1", 0, "0", "icon-add","");
			menuVOs.add(menuVO);
		}
		return menuVOs;
	}


}
