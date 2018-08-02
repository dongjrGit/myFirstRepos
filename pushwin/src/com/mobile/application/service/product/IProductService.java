package com.mobile.application.service.product;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.entity.TBasisProduct;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.product.ProductMenuVO;

public interface IProductService {

	List<ProductMenuVO> qryProductMenu(String root);

	void editMenu(HttpSession session, ProductMenuVO menuVO) throws IllegalAccessException,
			InvocationTargetException, BusinessException;

	void delMenu(HttpSession session,ProductMenuVO menuVO) throws IllegalAccessException,
			InvocationTargetException,BusinessException;

	void addMenu(HttpSession session,ProductMenuVO menuVO) throws IllegalAccessException,
			InvocationTargetException, BusinessException;

	CommonVO qryProductList(int pageIndex, int pageSize, HttpSession session, HttpServletRequest request,String userName,String orgId,String productName,String menuId,String onlineTime,String offlineTime,String keyWord);

	CommonVO saveProductInfo(TBasisProduct tBasisProduct, String orgId, HttpSession session);

	Map<String, Object> product_pictures(String productId) throws IOException;

	Map<String, Object> product_info(String productId);

	CommonVO saveProductPic(MultipartFile pic, String productId, HttpSession session) throws BusinessException, IOException;

	CommonVO removeProductPic(String productId, String picPath, HttpSession session) throws IOException;

	CommonVO setFacePage(String productId, String picName, HttpSession session);

	CommonVO savePicDesc(String productId, String picDesc, String picName, HttpSession session) throws UnsupportedEncodingException, IOException;

	CommonVO submitForCheck(String productId, String haveTemplatePage, HttpSession session);

	List<?> qryTemplate();
	
	CommonVO qryKeyByMenuId(String menuId);

	CommonVO submitTemplate(String templateInfo, String productId) throws BusinessException, IOException;
	
	Map<String, Object> qryproductdetail(String productId);

	CommonVO qryTemplateInfo(String productId) throws BusinessException,
			IOException;

	CommonVO productCheckList(int pageIndex, int pageSize, HttpSession session);

	void saveProductCheck(String productId, String opinion, String status,
			HttpSession session);

	List<?> checkUpdateProduct(String orgCode);

	List<?> qryProductForDownload(String productIds);

	CommonVO removeProduct(String productId, HttpSession session);


	CommonVO qryApkId();

	CommonVO qryProductById(String menuId);


	List<ProductMenuVO> qryProductMenuForPad(String orgCode);

	CommonVO updateProdutStatus(String status, String productId);

	TBasisProduct getProductInfo(String productId);
}
