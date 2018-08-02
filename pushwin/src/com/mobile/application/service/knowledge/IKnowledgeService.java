package com.mobile.application.service.knowledge;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.vo.product.ProductMenuVO;

public interface IKnowledgeService {

	List<ProductMenuVO> qryKnowledgeMenu(String root);

	void addMenu(ProductMenuVO menuVO) throws IllegalAccessException, InvocationTargetException, BusinessException;

	void editMenu(ProductMenuVO menuVO) throws IllegalAccessException, InvocationTargetException, BusinessException;

	void delMenu(ProductMenuVO menuVO) throws IllegalAccessException, InvocationTargetException;

	

}
