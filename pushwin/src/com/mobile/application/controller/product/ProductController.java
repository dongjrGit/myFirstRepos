package com.mobile.application.controller.product;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.lf5.util.StreamUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.entity.TBasisProduct;
import com.mobile.application.service.product.IProductService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.product.ProductMenuVO;

/** 
 * Copy Right Information   :Techown, 2015
 * Project                  : pushwin
 * Author					: 姜瑞
 * JDK version used         : jdk1.6
 * Comments                 : 产品中心
 * Version                  : 1.01
 * Modification history     : 2015.9.27
 * Sr Date   Modified By   Why & What is modified
 * 1. 2015.10.20 姜瑞     新建 
 **/
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@RequestMapping("/menu")
	public String initProductMenu(){
		return "product/product_menu";
	}
	
	@RequestMapping("/qryMenu")
	@ResponseBody
	public List<ProductMenuVO> qryProductMenu(String root, HttpSession session, HttpServletRequest request) {
		return productService.qryProductMenu(root);
	}
	
	/**
	 * 菜单编辑
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
	@RequestMapping("/editMenu")
	@ResponseBody
	public CommonVO edit(@RequestBody ProductMenuVO menuVO, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, BusinessException {
			productService.editMenu(session,menuVO);
			return new CommonVO(true, "保存成功", null, null);
	}
	
	/**
	 * 菜单新增
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
	@RequestMapping("/addMenu")
	@ResponseBody
	public CommonVO add(@RequestBody ProductMenuVO menuVO, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException, BusinessException {
		productService.addMenu(session,menuVO);
		return new CommonVO(true, "添加成功", null, null);
	}
	
	/**
	 * 删除菜单
	 * 
	 * @param session
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping("/delMenu")
	@ResponseBody
	public CommonVO delete(@RequestBody ProductMenuVO menuVO, HttpSession session, HttpServletRequest request) throws IllegalAccessException, InvocationTargetException,BusinessException {
		productService.delMenu(session,menuVO);
		return new CommonVO(true, "删除成功", null, null);
	}
	
	/**
	 * Description : 返回产品编辑列表界面
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 返回产品编辑页面
	 * @exception
	 */
	@RequestMapping("/editProduct")
	public String editProduct(){
		return "product/product_edit";
	}
	
	/**
	 * Description : 返回产品文字信息编辑页面
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 返回产品文字信息编辑页面
	 * @exception
	 */
	@RequestMapping("/productInfo")
	public ModelAndView product_info(String productId){
		Map<String, Object> productInfo = productService.product_info(productId);
		return new ModelAndView("product/product_info", productInfo);
	}
	
	/**
	 * @throws IOException 
	 * Description : 返回产品图片信息编辑页面
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 返回产品图片信息编辑页面
	 * @exception
	 */
	@RequestMapping("/productPictures")
	public ModelAndView product_pictures(String productId) throws IOException{
		
		Map<String, Object> picMap = productService.product_pictures(productId);
		return new ModelAndView("product/product_pictures", picMap);
	}
	
	/**
	 * Description : 查询产品列表
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 产品列表
	 * @exception
	 */
	@RequestMapping("/productList")
	@ResponseBody
	public CommonVO qryProductList(String pageIndex, String pageSize,HttpSession session,String userName,String orgId,String productName,String menuId,String onlineTime,String offlineTime,String keyWord, HttpServletRequest request){
		return productService.qryProductList( Integer.parseInt(pageIndex), Integer.parseInt(pageSize),session, request,userName,orgId,productName,menuId,onlineTime,offlineTime,keyWord);
	}
	
	/**
	 * Description : 查询产品列表
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 产品列表
	 * @exception
	 */
	@RequestMapping("/saveProductInfo")
	@ResponseBody
	public CommonVO saveProductInfo(TBasisProduct tBasisProduct,String orgId,HttpSession session){
		return productService.saveProductInfo(tBasisProduct,orgId, session);
	}
	
	/**
	 * Description : 保存产品图片
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 处理结果
	 * @exception
	 */
	@RequestMapping("/saveProductPic")
	@ResponseBody
	public CommonVO saveProductInfo(@RequestParam(value = "picFile", required = false)MultipartFile pic, String productId, HttpSession session) throws BusinessException, IOException{
		return productService.saveProductPic(pic, productId, session);
	}
	
	/**
	 * Description : 删除产品图片
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 处理结果
	 * @exception
	 */
	@RequestMapping("/removeProductPic")
	@ResponseBody
	public CommonVO removeProductPic(String productId, String picPath, HttpSession session) throws BusinessException, IOException{
		return productService.removeProductPic(productId, picPath, session);
	}
	
	/**
	 * Description : 设置产品首页图片
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 处理结果
	 * @exception
	 */
	@RequestMapping("/setFacePage")
	@ResponseBody
	public CommonVO setFacePage(String productId, String picName, HttpSession session) throws BusinessException, IOException{
		return productService.setFacePage(productId, picName, session);
	}
	
	/**
	 * Description : 保存图片文字描述
     * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return CommonVO
	 * @exception  
	 */
	@RequestMapping("/savePicDesc")
	@ResponseBody
	public CommonVO savePicDesc(String productId, String picDesc, String picName, HttpSession session) throws BusinessException, IOException{
		return productService.savePicDesc(productId, picDesc, picName, session);
	}
	/**
	 * Description : 产品提交审核
     * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return CommonVO
	 * @exception  
	 */
	@RequestMapping("/submitForCheck")
	@ResponseBody
	public CommonVO submitForCheck(String productId, String haveTemplatePage, HttpSession session){
		CommonVO commonVO = productService.submitForCheck(productId,haveTemplatePage, session);
		return commonVO;
	}
	
	/**
	 * Description : 模版信息提交
     * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return CommonVO
	 * @exception  
	 */
	@RequestMapping("/submitTemplate")
	@ResponseBody
	public CommonVO submitTemplate(HttpSession session, HttpServletRequest request) throws IOException, BusinessException{
		InputStream is = request.getInputStream();
		String productId = request.getParameter("productId");
		byte[] reqByte = StreamUtils.getBytes(is);
		String reqString = new String(reqByte, "UTF-8");
		CommonVO commonVO = productService.submitTemplate(reqString, productId);
		return commonVO;
	}
	
	/**
	 * Description : 模版信息提交
     * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return CommonVO
	 * @exception  
	 */
	@RequestMapping("/qryTemplateInfo")
	@ResponseBody
	public CommonVO qryTemplateInfo(String productId, HttpSession session) throws IOException, BusinessException{
		CommonVO commonVO = productService.qryTemplateInfo(productId);
		return commonVO;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/qryTemplatePage")
	public ModelAndView qryTemplatePage(String productId, String returnView, HttpSession session, HttpServletResponse response) throws IOException, BusinessException{
		CommonVO commonVO = productService.qryTemplateInfo(productId);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(commonVO.getMsg())){
			map = mapper.readValue(commonVO.getMsg(), Map.class);
		}
		return new ModelAndView(returnView.replace(".html", ""), map);
	}
	
	@RequestMapping("/editTemplatePage")
	public ModelAndView editTemplatePage(String productId, String returnView, HttpSession session, HttpServletResponse response) throws IOException, BusinessException{
		CommonVO commonVO = productService.qryTemplateInfo(productId);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("templateInfos", StringUtils.isNotBlank(commonVO.getMsg()) ? commonVO.getMsg() : "null");
		TBasisProduct tBasisProduct = this.productService.getProductInfo(productId);
		map.put("offlineTime", tBasisProduct.getOfflineTime());
		map.put("onlineTime", tBasisProduct.getOnlineTime());
		return new ModelAndView(returnView.replace(".html", ""), map);
	}
	/**
	 * Description : 查询产品模版列表
     * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return CommonVO
	 * @exception  
	 */
	@RequestMapping("/qryTemplate")
	@ResponseBody
	public List<?> qryTemplate(){
		List<?> templates = productService.qryTemplate();
		return templates;
	}
	/**
	 * Description : 根据目录查询关键字
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param menuId
	 * @return CommonVO
	 * @exception  
	 */
	@RequestMapping("/qryKeyByMenuId")
	@ResponseBody
	public CommonVO qryKeyByMenuId(String menuId, HttpSession session){
		CommonVO commonVO = productService.qryKeyByMenuId(menuId);
		return commonVO;
	}
	/**
	 * Description : 进入产品查询页面
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 页面路径
	 * @exception  
	 */
	@RequestMapping("/product_qry")
	public String  productQry(HttpSession session){
		return "product/product_qry";
	}
	/**
	 * @throws IOException 
	 * Description : 进入产品查询详细页面
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 页面路径 产品数据
	 * @exception  
	 */
	@RequestMapping("/qryProductdetail")
	public ModelAndView qryProductdetail(String productId) throws IOException{
		Map<String, Object> productInfo = productService.product_info(productId);
		Map<String, Object> picMap = productService.product_pictures(productId);
		productInfo.put("picList", picMap.get("picList"));
		Map<String, Object> qryproductdetail = productService.qryproductdetail(productId);
		return new ModelAndView("product/product_detail", productInfo);
		
	}
	
	/**
	 * Description : 进入产品审核页面
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 页面路径 产品数据
	 * @exception  
	 */
	@RequestMapping("/productCheck")
	public String productCheck(HttpSession session){
		return "product/product_check";
		
	}
	/**
	 * Description : 
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return  产品数据
	 * @exception  
	 */
	@RequestMapping("/productCheckList")
	@ResponseBody
	public CommonVO productCheckList(String pageIndex, String pageSize,HttpSession session){
		return productService.productCheckList(Integer.parseInt(pageIndex), Integer.parseInt(pageSize),session);
		
	}
	/**
	 * @throws IOException 
	 * Description : 进入产品审核详细页面
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 页面路径 产品数据
	 * @exception  
	 */
	@RequestMapping("/qryProductCheckdetail")
	public ModelAndView qryProductCheckdetail(String productId) throws IOException{
		Map<String, Object> productInfo = productService.product_info(productId);
		Map<String, Object> picMap = productService.product_pictures(productId);
		productInfo.put("picList", picMap.get("picList"));
		return new ModelAndView("product/product_check_detail", productInfo);
		
	}
	
	/**
	 * Description : 保存产品审核结果
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 页面路径 产品数据
	 * @exception  
	 */
	@RequestMapping("/saveProductCheck")
		@ResponseBody
	public CommonVO saveProductCheck(String productId,String opinion ,String status,HttpSession session ){
		  productService.saveProductCheck(productId,opinion,status,session);
		return new CommonVO(true, "保存成功");
		
	}
	/**
	 * Description : 删除产品
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 结果集
	 * @exception  
	 */
	@RequestMapping("/removeProduct")
    @ResponseBody
	public CommonVO removeProduct(String productId,HttpSession session ){
		CommonVO commonVO = productService.removeProduct(productId,session);
		return commonVO;
		
	}
	/**
	 * Description : 查交易
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 结果集
	 * @exception  
	 */
	@RequestMapping("/qryApkId")
    @ResponseBody
	public CommonVO qryApkId(){
		CommonVO commonVO = productService.qryApkId();
		return commonVO;
		
	}
	/**
	 * Description : 目录下产品
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 结果集
	 * @exception  
	 */
	@RequestMapping("/qryProductById")
    @ResponseBody
	public CommonVO qryProductById(String menuId){
		CommonVO commonVO = productService.qryProductById(menuId);
		return commonVO;
		
	}
	/**
	 * Description : 更新产品状态
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 结果集
	 * @exception  
	 */
	@RequestMapping("/updateProdutStatus")
    @ResponseBody
	public CommonVO updateProdutStatus(String status, String productId){
		CommonVO commonVO = productService.updateProdutStatus(status, productId);
		return commonVO;
		
	}
	
}
