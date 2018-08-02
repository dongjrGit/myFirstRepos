package com.mobile.application.dao;

import java.util.List;
import java.util.Map;

import com.mobile.application.entity.TBasisProductMenu;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.product.ProductMenuVO;

public interface IProductDao extends IBaseDAO<TBasisProductMenu> {

	List<ProductMenuVO> qryProductMenu();

	List<ProductMenuVO> qryMenuByName(ProductMenuVO menuVO);

	List<?> qryProductList(int pageIndex, int pageSize, String userName, String orgId, String productName, String menuId, String onlineTime, String offlineTime, String keyWord, TBasisUser user);

	Map<String, String> qryProduct(String productId);

	CommonVO saveProductPic(String productId);

	List<?> qryTemplate();

	void delorg(String productId);
	
	List<?> qryOrgByProductId(String productId);

	String qryProductSize(String userName, String orgId, String productName, String menuId, String onlineTime, String offlineTime, String keyWord, TBasisUser user);

	List<Map<String, String>> qryCheckHistory(String productId);

	List<?> qryProductCheckList(int pageIndex, int pageSize, TBasisUser user);

	String qryProductCheckSize(TBasisUser user);

	List<?> checkUpdateProduct(String orgCode);

	List<Map<String, Object>> qryProductForDownload(String productIds);

	List<?> qryApkId();

	List<?> qryProductById(String menuId);

	List<ProductMenuVO> qryProductMenuForPad(String orgCode);

	void updateProdutStatus(String status, String productId);

	String isAuthority(TBasisUser user, String menuId);

}
