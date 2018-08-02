package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisKnowledgeMenu;
import com.mobile.application.vo.product.ProductMenuVO;

public interface IKnowledgeDao extends IBaseDAO<TBasisKnowledgeMenu>{

	List<ProductMenuVO> qryMenuByName(ProductMenuVO menuVO);

	List<ProductMenuVO> qryProductMenu();

}
