package com.mobile.application.service.product.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.lf5.util.StreamUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.util.CompressUtil;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.IProductDao;
import com.mobile.application.dao.impl.BaseDAOImpl;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisProduct;
import com.mobile.application.entity.TBasisProductCheck;
import com.mobile.application.entity.TBasisProductMenu;
import com.mobile.application.entity.TBasisProductOrg;
import com.mobile.application.entity.TBasisProductTemplate;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.service.product.IProductService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.product.ProductMenuVO;
import com.mobile.application.vo.session.SessionVO;

@Service
public class ProductServiceImpl extends BaseDAOImpl<TBasisUser> implements IProductService {

	@Autowired
	private IProductDao productDao;
	@Autowired
	private ServletContext servletContext;
	
	@Override
	@Transactional
	public List<ProductMenuVO> qryProductMenu(String root){
		List<ProductMenuVO> menuVOs = productDao.qryProductMenu();
		if(!"0".equals(root)){

			ProductMenuVO menuVO = new ProductMenuVO("0", "目录列表", "#", "-1", 0, "0", "icon-add","","");

			menuVOs.add(menuVO);
		}
		return menuVOs;
	}
	
	@Override
	@Transactional
	public List<ProductMenuVO> qryProductMenuForPad(String orgCode){
		List<ProductMenuVO> menuVOs = productDao.qryProductMenuForPad(orgCode);
		return menuVOs;
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.menu.IMenuService#editMenu(com.mobile.application.vo.menu.MenuVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void editMenu(HttpSession session,ProductMenuVO menuVO) throws IllegalAccessException, InvocationTargetException, BusinessException {
	    SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
	    if(!"1".equals(sessionVO.getIsAdmin())){
			TBasisUser user = (TBasisUser) this.getByID(TBasisUser.class,sessionVO.getUserId()) ;
			String isAuthority = this.productDao.isAuthority(user,menuVO.getMenuId());
			if("0".equals(isAuthority)){
				throw new BusinessException("1000", "您没有编辑权限。");
			}
		}
		if(!"1".equals(sessionVO.getIsAdmin())){
				TBasisUser user = (TBasisUser) this.getByID(TBasisUser.class,sessionVO.getUserId()) ;
		if(Integer.parseInt(user.getTBasisOrg().getOrgLevel())>=1&&menuVO.getMenuLevel()==1){
			throw new BusinessException("1000", "您没有修改权限。");
		}}
		List<ProductMenuVO> menuVOs = productDao.qryMenuByName(menuVO);
		if(null != menuVOs && menuVOs.size() > 0 && !menuVO.getMenuId().equals(menuVOs.get(0).getMenuId())){
			throw new BusinessException("1000", "相同的菜单名称已存在。");
		}
		TBasisProductTemplate tBasisProductTemplate = null;
		if(StringUtils.isNotBlank(menuVO.getTemplateId())){
			tBasisProductTemplate = (TBasisProductTemplate) productDao.getByID(TBasisProductTemplate.class, menuVO.getTemplateId());
		}
		TBasisProductMenu tBasisProductMenu = (TBasisProductMenu)this.getByID(TBasisProductMenu.class, menuVO.getMenuId());
		com.mobile.application.commmon.util.BeanUtils.copyProperties(menuVO, tBasisProductMenu);
		tBasisProductMenu.setTBasisProductTemplate(tBasisProductTemplate);
		productDao.saveOrUpdate(tBasisProductMenu);
		
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.menu.IMenuService#delMenu(com.mobile.application.vo.menu.MenuVO)
	 */
	@Override
	@Transactional
	public void delMenu(HttpSession session,ProductMenuVO menuVO) throws IllegalAccessException, InvocationTargetException, BusinessException {
		 SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		 TBasisProductMenu tBasisProductMenu = new TBasisProductMenu();
		 List<?> haveProduct = this.productDao.qryProductById(menuVO.getMenuId());
		 if(haveProduct.size()>0){
			 throw new BusinessException("1000", "目录下有产品，不能删除。");
		 }
			if(!"1".equals(sessionVO.getIsAdmin())){
				TBasisUser user = (TBasisUser) this.getByID(TBasisUser.class,sessionVO.getUserId()) ;
				String isAuthority = this.productDao.isAuthority(user,menuVO.getMenuId());
				if("0".equals(isAuthority)){
					throw new BusinessException("1000", "您没有删除权限。");
				}
			}
		
		
		BeanUtils.copyProperties(tBasisProductMenu, menuVO);
		productDao.delete(tBasisProductMenu);
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.menu.IMenuService#addMenu(com.mobile.application.vo.menu.MenuVO)
	 */
	@Override
	@Transactional
	public void addMenu(HttpSession session,ProductMenuVO menuVO) throws IllegalAccessException, InvocationTargetException, BusinessException {
		 SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
			if(!"1".equals(sessionVO.getIsAdmin())){
					TBasisUser user = (TBasisUser) this.get(TBasisUser.class,sessionVO.getUserId()) ;
			if(Integer.parseInt(user.getTBasisOrg().getOrgLevel())>=1&&menuVO.getMenuLevel()==1){
				throw new BusinessException("1000", "您没有添加权限。");
			}}
		
		List<ProductMenuVO> menuVOs = productDao.qryMenuByName(menuVO);
		if(null != menuVOs && menuVOs.size() > 0){
			throw new BusinessException("1000", "相同的菜单名称已存在。");
		}
		TBasisProductTemplate tBasisProductTemplate = null;
		if(StringUtils.isNotBlank(menuVO.getTemplateId())){
			tBasisProductTemplate = (TBasisProductTemplate) productDao.getByID(TBasisProductTemplate.class, menuVO.getTemplateId());
		}
		TBasisProductMenu tBasisProductMenu = new TBasisProductMenu();
		BeanUtils.copyProperties(tBasisProductMenu, menuVO);
		tBasisProductMenu.setTBasisProductTemplate(tBasisProductTemplate);
		tBasisProductMenu.setUpdateUser(sessionVO.getUserId());
		tBasisProductMenu.setCreateTime(DateUtil.format(new Date()));
		productDao.save(tBasisProductMenu);
	}

	@Override
	@Transactional(readOnly = true)
	public CommonVO qryProductList(int pageIndex, int pageSize,HttpSession session, HttpServletRequest request,String userName,String orgId,String productName,String menuId,String onlineTime,String offlineTime,String keyWord) {
		SessionVO sessionvo = (SessionVO) session.getAttribute("sessionVO");
		TBasisUser user = (TBasisUser)this.productDao.getByID(TBasisUser.class, sessionvo.getUserId());
//		if(StringUtils.isBlank(orgId)){
//			orgId = sessionvo.getOrgId();
//		}
		List<?> products = productDao.qryProductList(pageIndex,pageSize,userName,orgId,productName,menuId,onlineTime,offlineTime,keyWord, user);
		String productsSize = productDao.qryProductSize(userName,orgId,productName,menuId,onlineTime,offlineTime,keyWord, user);
		return new CommonVO(true, products, productsSize);
	}

	@Override
	@Transactional
	public CommonVO saveProductInfo(TBasisProduct tBasisProduct,String orgId, HttpSession session) {
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		TBasisProduct savedProduct = new TBasisProduct();
		
		if(StringUtils.isBlank(tBasisProduct.getId())){
			tBasisProduct.setTBasisProductMenu(new TBasisProductMenu(tBasisProduct.getMenuId()));
			tBasisProduct.setStatus("1");
			tBasisProduct.setUpdateTime(DateUtil.format(new Date()));
			tBasisProduct.setUpdateUser(sessionVO.getUserId());
			tBasisProduct.setCreateUser(sessionVO.getUserId());
			productDao.save(tBasisProduct);
			savedProduct = tBasisProduct;
		} else {
			List<?> products = productDao.get(TBasisProduct.class, "id", tBasisProduct.getId());
			TBasisProduct oldProduct = (TBasisProduct) products.get(0);
			com.mobile.application.commmon.util.BeanUtils.copyProperties(tBasisProduct, oldProduct);
			oldProduct.setTBasisProductMenu(new TBasisProductMenu(tBasisProduct.getMenuId()));
			productDao.update(oldProduct);
			savedProduct.setId(oldProduct.getId());
			//删除所有产品机构
			productDao.delorg(tBasisProduct.getId());
		}
		
		//添加机构
		if(!"".equals(orgId)){
			String orgIds[]=orgId.split(",");
		for(int i = 0;i < orgIds.length;i++){
			TBasisProductOrg tBasisProductOrg = new TBasisProductOrg();
			TBasisOrg tBasisOrg = new TBasisOrg();
			tBasisOrg.setOrgId(orgIds[i]);
			tBasisProductOrg.setTBasisOrg(tBasisOrg);
			tBasisProductOrg.setTBasisProduct(savedProduct);
			productDao.save(tBasisProductOrg);
		}}
		return new CommonVO(true, "保存成功", savedProduct);
	}

	@Override
	@Transactional
	public Map<String, Object> product_pictures(String productId) throws IOException {
		
		Map<String, Object> picMap = new HashMap<String, Object>();
		List<Map<String, String>> picList = new ArrayList<Map<String, String>>();
		if(StringUtils.isNotBlank(productId)){
			List<?> products = productDao.get(TBasisProduct.class, "id", productId);
			TBasisProduct product = (TBasisProduct) products.get(0);
			picMap.put("product", product);
			picMap.put("productMenu", product.getTBasisProductMenu());
			TBasisProductTemplate TBasisProductTemplate = product.getTBasisProductMenu().getTBasisProductTemplate();
			picMap.put("productTemplate", product.getTBasisProductMenu().getTBasisProductTemplate());
			String gegDesc  = "";
			String rootPath = (String) SpringProperty.props.get("RootPath");
			//////////////////////
			String pictureDescPath = rootPath + product.getProductFolder() + "/picDesc/"+product.getId()+".txt";
			File pictureDescFile = new File(pictureDescPath);
			FileInputStream fis = null;
			try{
				if(pictureDescFile.getParentFile().exists()){
					fis = new FileInputStream(pictureDescFile);
					byte[] resByte = StreamUtils.getBytes(fis);
					 gegDesc = new String(resByte);
				}
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(null != fis)
					fis.close();
			}
			//////////////////////////
			JSONObject pictureDesc = new JSONObject();
			if(StringUtils.isNotBlank(gegDesc))
				pictureDesc = JSONObject.fromObject(gegDesc);
			if(StringUtils.isNotBlank(product.getProductFolder())){
				File productFolder = new File(rootPath + product.getProductFolder() + "/pic");
				if(!productFolder.exists()){
					picMap.put("picList", picList);
					return picMap;
				}
				for(File file : productFolder.listFiles()){
					Map<String, String> picFileMap = new HashMap<String, String>();
					picFileMap.put("picPath", product.getProductFolder() + "/pic/" + file.getName());
					picFileMap.put("picName", file.getName());
					picFileMap.put("picDesc", pictureDesc.optString(file.getName()));
					picList.add(picFileMap);
				}
			}
		}
		picMap.put("picList", picList);
		return picMap;
	}

	@Override
	@Transactional
	public Map<String, Object> product_info(String productId) {
		Map<String, Object> productInfo = new HashMap<String, Object>();
		Map<String, String> productMap = new HashMap<String, String>();
		String orgValue = "";
		String orgText ="";
		String a[];
		List<Map<String, String>> listCheck = null;
		if(StringUtils.isNotBlank(productId)){
			productMap = productDao.qryProduct(productId);
			listCheck = productDao.qryCheckHistory(productId);
			List<Map<String, String>> Productorg = (List<Map<String, String>>)productDao.qryOrgByProductId(productId);
			for(int i=0;i < Productorg.size();i++){
				if(i == Productorg.size()-1){
					orgValue += Productorg.get(i).get("orgId"); 
					orgText += Productorg.get(i).get("orgName");
			}else{
				orgValue += Productorg.get(i).get("orgId")+","; 
				orgText += Productorg.get(i).get("orgName")+",";
			}
			}
		}
		productInfo.put("product", productMap);
		productInfo.put("orgValue", orgValue);
		productInfo.put("orgText", orgText);
		productInfo.put("checkHistory", listCheck);
		return productInfo;
	}
	
	@Transactional
	@Override
	public CommonVO saveProductPic(MultipartFile pic, String productId, HttpSession session) throws BusinessException, IOException{
		//获取用户登录session
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		//获取文件存放根目录
		String rootPath = (String) SpringProperty.props.get("RootPath");
		//根据产品编号查出当前操作的产品信息
		List<?> products = productDao.get(TBasisProduct.class, "id", productId);
		TBasisProduct product = (TBasisProduct) products.get(0);
		//更新产品信息
		Date currentTime = new Date();
		String currentTimeStr = DateUtil.format(currentTime);
		if(StringUtils.isBlank(product.getProductFolder()))
			product.setProductFolder("/product/" + productId);
		product.setUpdateUser(sessionVO.getUserId());
		product.setUpdateTime(currentTimeStr);
		productDao.update(product);
		
		//写入图片文件
		String suffix = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
		String picPath = rootPath + product.getProductFolder() + "/pic/" + DateUtil.format(currentTime, "yyyyMMddHHmmssSSS")  + suffix;
		File picWriteFile = new File(picPath);
		if(!picWriteFile.getParentFile().exists())
			picWriteFile.getParentFile().mkdirs();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(picPath);
			fos.write(pic.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("500", "文件写入失败");
		} finally {
			fos.close();
		}
		
		productDao.saveProductPic(productId);
		return null;
	}

	@Override
	@Transactional
	public CommonVO removeProductPic(String productId, String picPath, HttpSession session) throws IOException {
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		
		String rootPath = (String) SpringProperty.props.get("RootPath");
		List<?> products = productDao.get(TBasisProduct.class, "id", productId);
		TBasisProduct product = (TBasisProduct) products.get(0);
		Date currentTime = new Date();
		String currentTimeStr = DateUtil.format(currentTime);
		product.setUpdateUser(sessionVO.getUserId());
		product.setUpdateTime(currentTimeStr);
		productDao.update(product);
		//////////////////////删除desc数据
	
		///////////////////
		File file = new File(rootPath + picPath);
		if(file.exists()){
			file.delete();
		}
		
		return null;
	}

	@Override
	@Transactional
	public CommonVO setFacePage(String productId, String picName, HttpSession session) {
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		List<?> products = productDao.get(TBasisProduct.class, "id", productId);
		TBasisProduct product = (TBasisProduct) products.get(0);
		Date currentTime = new Date();
		product.setUpdateUser(sessionVO.getUserId());
		product.setUpdateTime(DateUtil.format(currentTime));
		product.setFacePage(picName);
		productDao.update(product);
		return null;
	}

	@Override
	@Transactional
	public CommonVO savePicDesc(String productId, String picDesc, String picName, HttpSession session) throws IOException {
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		List<?> products = productDao.get(TBasisProduct.class, "id", productId);
		TBasisProduct product = (TBasisProduct) products.get(0);
		Date currentTime = new Date();
		product.setUpdateUser(sessionVO.getUserId());
		product.setUpdateTime(DateUtil.format(currentTime));
		productDao.update(product);
		///////////////////////////////////
		String rootPath = (String) SpringProperty.props.get("RootPath");
		String pictureDescPath = rootPath + product.getProductFolder() + "/picDesc/"+product.getId()+".txt";
		File pictureDescFile = new File(pictureDescPath);
		JSONObject pictureDesc1 = new JSONObject();
		if(!pictureDescFile.getParentFile().exists()){
			pictureDescFile.getParentFile().mkdirs();
			pictureDesc1.put(picName, picDesc);
		}else{
			FileInputStream fis= null;
			try{
				fis = new FileInputStream(pictureDescFile);
				byte[] resByte = StreamUtils.getBytes(fis);
				String desc = new String(resByte);
				pictureDesc1 = JSONObject.fromObject(desc);
	//			picDesc = new String(picDesc.getBytes("iso-8859-1"),"utf-8");
				pictureDesc1.put(picName, picDesc);
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(null != fis)
					fis.close();
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(pictureDescPath);
			fos.write(pictureDesc1.toString().getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fos.close();
		}
		
		
		
		
		
		//////////////////////////////////
		return new CommonVO(true, "保存成功");
	}

	@Override
	@Transactional
	public CommonVO submitForCheck(String productId,String haveTemplatePage, HttpSession session) {
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		List<?> products = productDao.get(TBasisProduct.class, "id", productId);
		TBasisProduct product = (TBasisProduct) products.get(0);
		//判断是否编辑了模板
		if("0".equals(haveTemplatePage)){
			String rootPath = (String) SpringProperty.props.get("RootPath");
			String templatePath = rootPath + product.getProductFolder() + "/template/templateInfo.txt";
			File templateFile = new File(templatePath);
			if(!templateFile.getParentFile().exists()){
				return new CommonVO(false, "请编辑模板");
			}
		}
		Date currentTime = new Date();
		product.setUpdateUser(sessionVO.getUserId());
		product.setUpdateTime(DateUtil.format(currentTime));
		product.setStatus("2");
		productDao.update(product);
		return new CommonVO(true, "提交成功");
	}
	@Override
	@Transactional
	public List<?> qryTemplate() {
		List<?> list = productDao.qryTemplate();
		return list;
	}

	@Override
	@Transactional
	public CommonVO qryKeyByMenuId(String menuId) {
		List<?> productMenus = productDao.get(TBasisProductMenu.class, "menuId", menuId);
		TBasisProductMenu productMenu = (TBasisProductMenu) productMenus.get(0);
		String keyWords = productMenu.getKeyWords();
		if(keyWords==null)
		{
			return new CommonVO(true,"没有关键字");	
		}
		String[] keyWord= keyWords.split(",");
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for(int i=0;i<keyWord.length;i++){
			String key=keyWord[i];
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("keywordid", key);  
			map.put("keywords", key);
			mapList.add(map);
		}
		return new CommonVO(true,mapList,"返回成功");
	}
	
	@Override
	@Transactional
	public CommonVO submitTemplate(String templateInfo, String productId) throws BusinessException, IOException {
		
		//获取用户登录session
//		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		//获取文件存放根目录
		String rootPath = (String) SpringProperty.props.get("RootPath");
		//根据产品编号查出当前操作的产品信息
		List<?> products = productDao.get(TBasisProduct.class, "id", productId);
		if(null == products || products.size() == 0){
			return new CommonVO(false, "没有查询到该产品信息，保存模版信息失败。");
		}
		TBasisProduct product = (TBasisProduct) products.get(0);
		String templatePath = rootPath + "/product/" + productId + "/template/templateInfo.txt";
		File templateFile = new File(templatePath);
		if(!templateFile.getParentFile().exists()){
			templateFile.getParentFile().mkdirs();
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(templatePath);
			fos.write(templateInfo.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("500", "文件写入失败");
		} finally {
			fos.close();
		}
		return new CommonVO(true, "保存成功");
	}

	@Override
	public CommonVO qryTemplateInfo(String productId) throws BusinessException, IOException {
		//获取文件存放根目录
		String rootPath = (String) SpringProperty.props.get("RootPath");
		String templatePath = rootPath + "/product/" + productId + "/template/templateInfo.txt";
		File templateFile = new File(templatePath);
		if(!templateFile.exists())
			return new CommonVO(true, "", 0);
		FileInputStream fis = new FileInputStream(templateFile);
		byte[] resByte = StreamUtils.getBytes(fis);
		return new CommonVO(true, new String(resByte), 0);
	}
	
	@Override
	@Transactional
	public Map<String, Object> qryproductdetail(String productId) {
		List<?> Productorg = productDao.qryOrgByProductId(productId);
		return null;
	}

	@Override
	@Transactional
	public CommonVO productCheckList(int pageIndex, int pageSize,HttpSession session) {
		SessionVO sessionvo = (SessionVO) session.getAttribute("sessionVO");
		TBasisUser user = (TBasisUser)this.productDao.getByID(TBasisUser.class, sessionvo.getUserId());
		List<?> products = productDao.qryProductCheckList(pageIndex,pageSize,user);
		String productsSize = productDao.qryProductCheckSize(user);
		return new CommonVO(true, products, productsSize);
	}

	@Override
	@Transactional
	public void saveProductCheck(String productId, String opinion,
			String status, HttpSession session) {
		 SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		 TBasisProduct tBasisProduct = (TBasisProduct) this.productDao.getByID(TBasisProduct.class, productId);
		 TBasisProductCheck tBasisProductCheck = new TBasisProductCheck();
		 tBasisProductCheck.setCheckUser(sessionVO.getUserId());
		 tBasisProductCheck.setCheckTime(DateUtil.format(new Date()));
		 tBasisProductCheck.setOpinion(opinion);
		 tBasisProductCheck.setTBasisProduct(tBasisProduct);
		if (status.equals("3")) {
			tBasisProduct.setStatus("3");
		} else {
			tBasisProduct.setStatus("4");
		}
		this.save(tBasisProduct);
		this.save(tBasisProductCheck);

		String rootPath = SpringProperty.props.getProperty("RootPath");

		String productFord = rootPath + tBasisProduct.getProductFolder();
		String zipFilePath = rootPath + "/product" + File.separator + productId
				+ ".zip";
		// String zipFilePath = productFord + ".zip";
		File zipFile = new File(zipFilePath);
		if (zipFile.exists())
			zipFile.delete();
		CompressUtil.zip(productFord, zipFilePath, true, null);
		tBasisProduct.setZipLength(zipFile.length() + "");
		tBasisProduct.setProductZip("/product/" + productId + ".zip");
		tBasisProduct.setZipUptime(DateUtil.format(new Date()));
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> checkUpdateProduct(String orgCode) {
		List<?> productSampleInfos = productDao.checkUpdateProduct(orgCode);
		return productSampleInfos;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<?> qryProductForDownload(String productIds) {
		//获取文件存放根目录
		String rootPath = (String) SpringProperty.props.get("RootPath");
		List<Map<String, Object>> productDetailInfos = productDao.qryProductForDownload(productIds);
		ObjectMapper mapper = new ObjectMapper();
		for (Map<String, Object> productMap : productDetailInfos) {
			String productZip = (String) productMap.get("productZip");
			productMap.put("zipName", FilenameUtils.getName(productZip));
			String templatePath = rootPath + "/product/" + productMap.get("productId") + "/template/templateInfo.txt";
			File templateFile = new File(templatePath);
			
			//如果模版编号为空，或模版信息文件不存在，则返回空模版信息给PAD端
			if(StringUtils.isBlank((String)productMap.get("templateId")) || !templateFile.exists()) {
				productMap.put("templateInfos", null);
			} else {
				try{
					FileInputStream fis = new FileInputStream(templateFile);
					byte[] resByte = StreamUtils.getBytes(fis);
					Map<String, Object> templateMap = new HashMap<String, Object>();
					if(null != resByte && resByte.length > 0){
						templateMap = mapper.readValue(new String(resByte), Map.class);
						productMap.put("templateInfos", templateMap);
					} else {
						productMap.put("templateInfos", "");
					}
				} catch (Exception e){
					e.printStackTrace();
					return null;
				}
			}
			
			//图片文字描述信息
			String pictureDescPath = rootPath + "/product/" + productMap.get("productId") + "/picDesc/" + productMap.get("productId") + ".txt";
			File picDescFile = new File(pictureDescPath);
			if(!picDescFile.exists()){
				productMap.put("picDesc", null);
			} else {
				try{
					Map<String, Object> picDescMap = new HashMap<String, Object>();
					FileInputStream fis = new FileInputStream(picDescFile);
					byte[] resByte = StreamUtils.getBytes(fis);
					if(null != resByte && resByte.length > 0){
						picDescMap = mapper.readValue(new String(resByte), Map.class);
						productMap.put("picDesc", picDescMap);
					} else {
						productMap.put("picDesc", null);
					}
				} catch (Exception e){
					e.printStackTrace();
					return null;
				}
			}
		}
		
		return productDetailInfos;
	}

	@Override
	@Transactional
	public CommonVO removeProduct(String productId, HttpSession session) {
		//检查是否保存和审核不通过
		TBasisProduct tBasisProduct = (TBasisProduct) this.productDao.getByID(TBasisProduct.class, productId);
		if("1".equals(tBasisProduct.getStatus())||"4".equals(tBasisProduct.getStatus())||"6".equals(tBasisProduct.getStatus())){
			this.productDao.delete(tBasisProduct);
			/////删除文件信息
			String rootPath = SpringProperty.props.getProperty("RootPath");
			String sPath = rootPath + tBasisProduct.getProductFolder();
			File file = new File(rootPath + tBasisProduct.getProductFolder() );
			if(file.exists()){
				this.deleteDirectory(sPath);
			}
			
			
		}else{
			return new CommonVO(false, "删除失败，只能删除保存、审核不通过和下架的产品");
		}
				
		return new CommonVO(true, "删除成功");
	}
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = this.deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public boolean deleteFile(String sPath) {  
		boolean flag = false;  
		File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	} 
	/** 
	 *  根据路径删除指定的目录或文件，无论存在与否 
	 *@param sPath  要删除的目录或文件 
	 *@return 删除成功返回 true，否则返回 false。 
	 */  
	public boolean DeleteFolder(String sPath) {  
		boolean flag = false;  
		File file = new File(sPath);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(sPath);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	}  
	@Override
	@Transactional
	public CommonVO qryApkId() {
		return new CommonVO(true,this.productDao.qryApkId(),null);
	} 
	
	@Override
	@Transactional
	public CommonVO qryProductById(String menuId) {
		return new CommonVO(true,this.productDao.qryProductById(menuId),null);
	}

	@Override
	@Transactional
	public CommonVO updateProdutStatus(String status, String productId) {
		productDao.updateProdutStatus(status, productId);
		return new CommonVO(true,"操作成功");
	} 
	@Override
	@Transactional
	public TBasisProduct getProductInfo(String productId) {
		TBasisProduct tBasisProduct = (TBasisProduct) this.productDao.getByID(TBasisProduct.class, productId);
		return tBasisProduct;
	} 
}
