/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ClassStatusEnum;
import com.yinlian.Enums.ClassTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.ProductSpecs;
import com.yinlian.wssc.web.po.ProductSpecsCustom;
import com.yinlian.wssc.web.po.SkuSpecsv;
import com.yinlian.wssc.web.po.Specsvalues;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProductSpecsService;
import com.yinlian.wssc.web.service.SpecsvaluesService;
import com.yinlian.wssc.web.util.BasicConvert;
import com.yinlian.wssc.web.util.CriteriaCommodity;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 商品信息的控制类
 * 
 * @author Administrator
 * @version $Id: CommodityController.java, v 0.1 2016年2月26日 下午1:21:28
 *          Administrator Exp $
 */
@RestController
@RequestMapping("/platform/commodity")
public class CommodityController {

	/**
	 * 日志输出的类
	 */
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductSpecsService productSpecsService;
	@Autowired
	private SpecsvaluesService specsvaluesService;
	@Autowired
	private BrandService brandService;
	
    @Autowired
    private OperaterecordsService operaterecordsService ;

	/**
	 * 查询所有商品分类
	 * 
	 * @return
	 */
	@RequestMapping("/queryAllCategorys")
	public ReusltItem queryAllCategorys() {
		ReusltItem item = new ReusltItem();
		List<CategoryDTO> list = null;
		try {
			list = categoryService.selectAll(0);
			item.setData(list);
			item.setCode(0);
			item.setDesc("success");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询所有商品分类出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"查询所有商品分类出错! 异常信息:", e,
					"/platform/commodity/queryAllCategorys");
		}

		return item;
	}

	/**
	 * 查询某个分类信息
	 * 
	 * <!--和分类下分类的信息-->
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryCategory")
	public ReusltItem queryCategory(String id) {
		ReusltItem item = new ReusltItem();
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("分类ID参数错误");
				return item;
			}
			CategoryDTO category = categoryService.queryById(StringUtilsEX
					.ToInt(id));
			if (category == null) {
				item.setCode(-200);
				item.setDesc("没有查询到分类信息");
			} else {
				list.add(category);
				item.setData(list);
				item.setCode(0);
				item.setDesc("查询分类信息成功");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询某个分类信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"查询某个分类信息出错! 异常信息:", e,
					"/platform/commodity/queryCategory");
		}
		return item;
	}

	/**
	 * 修改分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/updateCategory")
	public ReusltItem updateCategory(String id, String name, String fatherid,
			String secondid, String sort, String warnnum, String img,
			boolean isvirtual, boolean iseditable) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			Category category = checkCategoryParam(id, name, fatherid,
					secondid, sort, warnnum, img, iseditable, isvirtual, item);
			if (item.getCode() < 0) {
				return item;
			}
			int result = categoryService.updateCategory(category);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改分类信息成功");
				LogHandle.info(
						LogType.commodity,
						MessageFormat.format("修改分类信息成功! "
								+ "id:{0},name:{1},操作人ID:{2}", id, name,
								user.getUserId()),
						"/platform/commodity/updateCategory");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_classedit.jsp", "/platform/commodity/updateCategory", "修改分类信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改分类信息操作记录出错! 异常信息:",
    								e, "/platform/commodity/updateCategory");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("修改分类信息失败");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改分类出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"修改分类信息出错! 异常信息:", e,
					"/platform/commodity/updateCategory");
		}
		return item;
	}

	/**
	 * 删除分类
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCategory")
	public ReusltItem deleteCategory(String id) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("分类ID参数错误");
				return item;
			}

			int result = categoryService.delCategory(StringUtilsEX.ToInt(id),
					user.getUserId(), item);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				LogHandle.info(LogType.commodity,
						MessageFormat.format("删除分类信息成功! " + "id:{0},操作人ID:{1}",
								id, user.getUserId()),
						"/platform/commodity/updateCategory");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_class.jsp", "/platform/commodity/deleteCategory", "删除分类信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除分类信息操作记录出错! 异常信息:",
    								e, "/platform/commodity/deleteCategory");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				if (StringUtilsEX.IsNullOrWhiteSpace(item.getDesc())) {
					item.setDesc("删除失败");
				}

			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除分类出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"删除分类信息出错! 异常信息:", e,
					"/platform/commodity/deleteCategory");
		}
		return item;
	}

	/**
	 * 根据分类id查询商品规格信息 (如果不传分类id就是查询所有的商品规格信息)
	 * 
	 * @param calssId
	 * @return
	 */
	@RequestMapping("/getProSpecsByClassId")
	public ReusltItem getProSpecsByClassId(String classid, String page,
			String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex：" + page);
				return item;
			}
			if (StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pagesize：" + size);
				return item;
			}
			if (StringUtilsEX.ToInt(classid) <= 0) {
				item.setCode(-103);
				item.setDesc("商品分类ID参数错误：" + classid);
				return item;
			}
			CriteriaCommodity criteria = new CriteriaCommodity();
			criteria.setClassid(StringUtilsEX.ToInt(classid));
			criteria.setOrderByClause("OrderBy");
			PageBean pageBean = categoryService
					.seletcProductSpecsPageByClassId(criteria,
							BasicConvert.string2Integer(page),
							BasicConvert.string2Integer(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据分类id查询商品规格信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"根据分类id查询商品规格信息出错! 异常信息:", e,
					"/platform/commodity/getProSpecsByClassId");
		
		}
		return item;
	}

	/**
	 * 查询商品规格信息 (分页)
	 * 
	 * @param calssId
	 * @return
	 */
	@RequestMapping("/queryProSpecsList")
	public ReusltItem queryProductSpecsList(String classid,String classid2,String classid3, String specstypeid,
			String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaCommodity criteria = new CriteriaCommodity();
			if (StringUtilsEX.ToInt(page) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex：" + page);
				return item;
			}
			if (StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pagesize：" + size);
				return item;
			}
			if (StringUtilsEX.ToInt(classid) > 0) {
				criteria.setClassid(StringUtilsEX.ToInt(classid));
			}
			if (StringUtilsEX.ToInt(classid2) > 0) {
				criteria.setClassid2(StringUtilsEX.ToInt(classid2));
			}
			if (StringUtilsEX.ToInt(classid3) > 0) {
				criteria.setClassid3(StringUtilsEX.ToInt(classid3));
			}
			if (StringUtilsEX.ToInt(specstypeid) > 0) {
				criteria.setSpecstypeid(StringUtilsEX.ToInt(specstypeid));
			}
			criteria.setOrderByClause("OrderBy");
			PageBean pageBean = categoryService
					.seletcProductSpecsPageByClassId(criteria,
							BasicConvert.string2Integer(page),
							BasicConvert.string2Integer(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询商品规格信息 (分页)出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"查询商品规格信息 (分页)出错! 异常信息:", e,
					"/platform/commodity/queryProSpecsList");
		
		}
		return item;
	}

	/**
	 * 根据分类id查询规格列表 不分页
	 * 
	 * @param classid
	 * @return
	 */
	@RequestMapping("/getProductSpecsByClassId")
	public ReusltItem getProductSpecsByClassId(String classid) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(classid) < 0) {
				item.setCode(-101);
				item.setDesc("分类id参数错误,classid" + classid);
				return item;
			}
			List<ProductSpecsCustom> specs = productSpecsService
					.querySpecsByClassid(StringUtilsEX.ToInt(classid));
			item.setCode(0);
			item.setData(specs);
			item.setDesc("获取规格列表成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据分类id查询商品规格信息 (不分页)出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"根据分类id查询商品规格信息 (不分页)出错! 异常信息:", e,
					"/platform/commodity/getProductSpecsByClassId");
		}
		return item;
	}

	/**
	 * 根据商品规格名称的id查询规格信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/queryProductSpecsById")
	public ReusltItem queryProductSpecsById(String id) {
		ReusltItem item = new ReusltItem();
		try {
		if (StringUtilsEX.ToInt(id) < 0) {
			item.setCode(-101);
			item.setDesc("规格名称id参数错误,id" + id);
			return item;
		}
			if (StringUtilsEX.ToInt(id) > 0) {
				item.setData(productSpecsService
						.selectById(StringUtilsEX.ToInt(id)));
				item.setCode(0);
				item.setDesc("获取商品规格信息成功");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据商品规格名称的id查询规格信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"根据商品规格名称的id查询规格信息出错! 异常信息:", e,
					"/platform/commodity/queryProductSpecsById");
		}
		return item;
	}

	/**
	 * 增加一个规格 
	 * 
	 * @param name
	 * @param classid
	 * @param orderby
	 * @param status
	 * @param id
	 * @param displaylocation
	 * @param specstypeid
	 * @param isentry
	 * @return
	 */
	@RequestMapping("/addProductSpecs")
	public ReusltItem addProductSpecs(String name, String classid,
			String orderby, String status, String displaylocation,
			String specstypeid, Boolean isentry) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			ProductSpecs productSpecs = checkProductParam(name, classid,
					orderby, status, displaylocation, specstypeid, isentry,
					item);
			if (item.getCode() < 0) {
				return item;
			}
			int result = productSpecsService.insert(productSpecs);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("增加成功");
				LogHandle.info(LogType.commodity,
						MessageFormat.format("增加规格信息成功! "
								+ "name:{0},classid:{1},操作人ID:{2}", name, classid,
								user.getUserId()),
						"/platform/commodity/addProductSpecs");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "增加规格信息页面", "/platform/commodity/addProductSpecs", "增加规格信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"增加规格信息操作记录出错! 异常信息:",
    								e, "/platform/commodity/addProductSpecs");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("增加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("增加规格信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"增加规格信息出错! 异常信息:", e,
					"/platform/commodity/addProductSpecs");
		}

		return item;
	}

	/**
	 * 根据商品规格id修改规格信息
	 * 
	 * @param id
	 * @param typeid
	 * @return
	 */
	@RequestMapping("/updateProductSpecsById")
	public ReusltItem updateProductSpecsById(String name, String classid,
			String orderby, String status, String id, String displaylocation,
			String specstypeid, Boolean isentry) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (BasicConvert.string2Integer(id) < 0) {
				item.setCode(-101);
				item.setDesc("规格名称ID参数错误,id:" + id);
				return item;
			}
			ProductSpecs productSpecs = checkProductParam(name, classid,
					orderby, status, displaylocation, specstypeid, isentry,
					item);
			if (item.getCode() < 0) {
				return item;
			}
			productSpecs.setId(BasicConvert.string2Integer(id));
			int result = productSpecsService
					.updateProductSpecsById(productSpecs);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改成功");
				LogHandle.info(LogType.commodity,
						MessageFormat.format("修改规格信息成功! "
								+ "id:{0},name:{1},classid:{2},操作人ID:{3}", id,name, classid,
								user.getUserId()),
						"/platform/commodity/updateProductSpecsById");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改规格信息页面", "/platform/commodity/updateProductSpecsById", "修改规格信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改规格信息操作记录出错! 异常信息:",
    								e, "/platform/commodity/updateProductSpecsById");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("修改失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改规格信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"修改规格信息出错! 异常信息:", e,
					"/platform/commodity/updateProductSpecsById");
		}

		return item;
	}

	/**
	 * 根据规格id删除规格名称信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteProductSpecsById")
	public ReusltItem deleteProductSpecsById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("规格ID参数错误,id:" + id);
				return item;
			}
			int result = productSpecsService
					.deleteById(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				LogHandle.info(LogType.commodity,
						MessageFormat.format("删除规格信息成功! "
								+ "id:{0},操作人ID:{1}", id,user.getUserId()),
						"/platform/commodity/deleteProductSpecsById");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "prospecs_list.jsp", "/platform/commodity/deleteProductSpecsById", "根据规格id删除规格名称信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除规格信息操作记录出错! 异常信息:",
    								e, "/platform/commodity/deleteProductSpecsById");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除规格信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"删除规格信息出错! 异常信息:", e,
					"/platform/commodity/deleteProductSpecsById");
		}

		return item;
	}

	/**
	 * 根据规格id分页查询规格值信息
	 * 
	 * @param criteria
	 * @return
	 */
	@RequestMapping("/querySpecsValueBySpecsId")
	public ReusltItem querySpecsValueBySpecsId(String specsid, String page,
			String size) {
		ReusltItem item = new ReusltItem();
		if (StringUtilsEX.ToInt(specsid) <= 0) {
			item.setCode(-101);
			item.setDesc("规格id参数错误，specsid：" + specsid);
			return item;
		}
		if (StringUtilsEX.ToInt(page) <= 0) {
			page="1";
		}
		if (StringUtilsEX.ToInt(size) <= 0) {
			size="10";
		}
		try {
			CriteriaCommodity criteria = new CriteriaCommodity();
			criteria.setSpecsid(StringUtilsEX.ToInt(specsid));
			PageBean pageBean = specsvaluesService
					.querySpecsValuePageBySpecsId(criteria,
							StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据规格id分页查询规格值信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"根据规格id分页查询规格值信息出错! 异常信息:", e,
					"/platform/commodity/querySpecsValueBySpecsId");
		}
		return item;
	}

	/**
	 * 根据id删除规格值信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteSpecsValueById")
	public ReusltItem deleteSpecsValueById(String id) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("规格ID参数错误,id:" + id);
				return item;
			}
			SkuSpecsv ssv = specsvaluesService.getbyValueID(StringUtilsEX
					.ToInt(id));
			if (ssv != null) {
				item.setCode(-102);
				item.setDesc("规格值正在被使用，不能删除。id:" + id);
				return item;
			}
			int result = specsvaluesService.deleteById(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				LogHandle.info(LogType.commodity,
						MessageFormat.format("删除规格值信息成功! "
								+ "id:{0},操作人ID:{1}", id,user.getUserId()),
						"/platform/commodity/deleteSpecsValueById");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specs_values.jsp", "/platform/commodity/deleteSpecsValueById", "根据id删除规格值信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除规格值操作记录出错! 异常信息:",
    								e, "/platform/commodity/deleteSpecsValueById");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("删除失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除规格值信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"删除规格值信息出错! 异常信息:", e,
					"/platform/commodity/deleteSpecsValueById");
		}
		return item;
	}

	/**
	 * 插入一个规格值
	 * 
	 * @param value
	 * @return
	 */
	@RequestMapping("/insertSpecsValue")
	public ReusltItem insertSpecsValue(String value, String specsid) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
		if (StringUtilsEX.ToInt(specsid) < 0) {
			item.setCode(-101);
			item.setDesc("规格ID参数错误,id:" + specsid);
			return item;
		}
			Specsvalues record = checkSpecsValueParam(value, specsid, item);
			if (item.getCode() < 0) {
				return item;
			}
			int result = specsvaluesService.insertRecord(record);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("增加成功");
				LogHandle.info(LogType.commodity,
						MessageFormat.format("新增规格值信息成功! "
								+ "value:{0},specsid:{1},操作人ID:{2}", value,specsid,user.getUserId()),
						"/platform/commodity/insertSpecsValue");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specs_values.jsp", "/platform/commodity/insertSpecsValue", "新增规格值信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"新增规格值信息操作记录出错! 异常信息:",
    								e, "/platform/commodity/insertSpecsValue");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("增加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("新增规格值信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"新增规格值信息出错! 异常信息:", e,
					"/platform/commodity/insertSpecsValue");
		}
		return item;
	}

	/**
	 * 根据父ID查询子分类信息
	 * 
	 * @param fatherid
	 * @return
	 */
	@RequestMapping("/getClassByFatherID")
	public ReusltItem getClassByFatherID(String fatherid) {
		ReusltItem item = new ReusltItem();
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		try {
			if (StringUtilsEX.ToInt(fatherid) == -1) {
				item.setCode(0);
				item.setDesc("未选择分类!");
				return item;
			}
			
			list = categoryService.getByFatherID(Integer.parseInt(fatherid));
			item.setDesc("查询子分类信息成功");
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(" 根据父ID查询子分类信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"根据父ID查询子分类信息出错! 异常信息:", e,
					"/platform/commodity/getClassByFatherID");
		}

		return item;
	}

	// 参数验证
	private ProductSpecs checkProductParam(String name, String classid,
			String orderby, String status, String displaylocation,
			String specstypeid, Boolean isentry, ReusltItem item)
			throws Exception {
		ProductSpecs productSpecs = new ProductSpecs();
		if (StringUtilsEX.isBlank(name)) {
			item.setCode(-102);
			item.setDesc("规格名称名称不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(classid) <= 0) {
			item.setCode(-103);
			item.setDesc("所属分类ID参数错误,cid:" + classid);
			return null;
		}
		if (categoryService.queryById(StringUtilsEX.ToInt(classid)) == null) {
			item.setCode(-201);
			item.setDesc("根据所属分类ID未能检索到分类数据,cid:" + classid);
			return null;
		}

		if (StringUtilsEX.ToInt(status) < 0) {
			item.setCode(-104);
			item.setDesc("规格类型状态参数错误,status:" + status);
			return null;
		}
		if (StringUtilsEX.ToInt(orderby) < 0) {
			item.setCode(-105);
			item.setDesc("规格类型排序参数错误,orderby:" + orderby);
			return null;
		}
		productSpecs.setName(name);
		productSpecs.setClassid(StringUtilsEX.ToInt(classid));
		productSpecs.setStatus(StringUtilsEX.ToInt(status));
		productSpecs.setOrderby(StringUtilsEX.ToInt(orderby));
		productSpecs.setDisplaylocation(displaylocation);
		productSpecs.setIsentry(isentry);
		productSpecs.setSpecstypeid(StringUtilsEX.ToInt(specstypeid));
		return productSpecs;
	}

	// 参数验证
	private Specsvalues checkSpecsValueParam(String value, String specsid,
			ReusltItem item) throws Exception {
		Specsvalues record = new Specsvalues();

		if (StringUtilsEX.isBlank(value)) {
			item.setCode(-102);
			item.setDesc("规格值名称不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(specsid) <= 0) {
			item.setCode(-103);
			item.setDesc("所属商品规格ID参数错误,cid:" + specsid);
			return null;
		}
		record.setValue(value);
		record.setSpecsid(StringUtilsEX.ToInt(specsid));
		record.setStatus(0);
		return record;
	}

	/**
	 * 修改规格排序
	 * 
	 * @param orderby
	 * @param id
	 * @return
	 */

	@RequestMapping("/updateOrderBy")
	public ReusltItem updateOrderBy(String orderby, String id) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("规格ID参数错误,id:" + id);
				return item;
			}
			if (StringUtilsEX.ToInt(orderby) < 0) {
				item.setCode(-102);
				item.setDesc("规格排序参数错误,orderby:" + orderby);
				return item;
			}
			if (productSpecsService.updateOrderBy(StringUtilsEX.ToInt(orderby),
					StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("修改规格排序成功");
				LogHandle.info(LogType.commodity,
						MessageFormat.format("修改规格排序成功! "
								+ "id:{0},orderby:{1},操作人ID:{2}", id,orderby,user.getUserId()),
						"/platform/commodity/updateOrderBy");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "prospecs_list.jsp", "/platform/commodity/updateOrderBy", "修改规格排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改规格排序信息操作记录出错! 异常信息:",
    								e, "/platform/commodity/updateOrderBy");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("修改规格排序失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改规格排序出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"修改规格排序出错! 异常信息:", e,
					"/platform/commodity/updateOrderBy");
		}
		return item;
	}

	/**
	 * 修改规格状态 0-启用 1-禁用
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public ReusltItem updateStatus(String status, String id) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			Integer ID = StringUtilsEX.ToInt(id);
			if (id == null || ID < 0) {
				item.setCode(-101);
				item.setDesc("规格ID参数错误,id:" + id);
				return item;
			}
			Integer ss = StringUtilsEX.ToInt(status);
			if (status == null || ss < 0) {
				item.setCode(-102);
				item.setDesc("规格状态参数错误,orderby:" + status);
				return item;
			}
			if (productSpecsService.updateStatus(ss, ID) > 0) {
				item.setCode(0);
				item.setDesc("修改规格状态成功");
				LogHandle.info(LogType.commodity,
						MessageFormat.format("修改规格状态成功! "
								+ "id:{0},status:{1},操作人ID:{2}", id,status,user.getUserId()),
						"/platform/commodity/updateStatus");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "prospecs_list.jsp", "/platform/commodity/updateStatus", "修改规格状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改规格状态信息操作记录出错! 异常信息:",
    								e, "/platform/commodity/updateStatus");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("修改规格状态失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改规格状态出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"修改规格状态出错! 异常信息:", e,
					"/platform/commodity/updateStatus");
		}
		return item;
	}

	/**
	 * 添加分类
	 * 
	 * @param name
	 * @param fatherid
	 * @param sort
	 * @param warnnum
	 * @param iseditable
	 * @param isvirtual
	 * @param item
	 * @return
	 */
	private Category checkCategoryParam(String id, String name,
			String fatherid, String secondid, String sort, String warnnum,
			String img, boolean iseditable, boolean isvirtual, ReusltItem item)
			throws Exception {
		Category category = new Category();

		if (StringUtils.isBlank(name)) {
			item.setCode(-102);
			item.setDesc("分类名称不能为空");
			return null;
		}
		// if (StringUtilsEX.ToInt(fatherid) <= 0) {
		// item.setCode(-103);
		// item.setDesc("分类所属分类不能为空");
		// }
		if (StringUtilsEX.ToInt(sort) < 0) {
			item.setCode(-104);
			item.setDesc("分类排序参数错误");
			return null;
		}
		if (StringUtilsEX.ToInt(warnnum) <= 0) {
			item.setCode(-105);
			item.setDesc("分类预警数量参数错误");
			return null;
		}
		Integer ID = StringUtilsEX.ToInt(id);
		if (ID < 0) {
			item.setCode(-101);
			item.setDesc("分类ID参数错误");
			return null;
		}

		if (ID == 0) {
			category.setIsdel(false);
			category.setCreattime(new Date());

			Integer fid = StringUtilsEX.ToInt(fatherid);
			Integer sid = StringUtilsEX.ToInt(secondid);

			if (fid < 0 || sid < 0) {
				item.setCode(-103);
				item.setDesc("所属类别错误");
				return null;
			}
			if (fid == 0) {
				category.setFatherid(0);
				category.setClasslever(1);
			} else {
				if (sid == 0) {
					category.setFatherid(fid);
					category.setClasslever(2);
					category.setFullpath(fid.toString());
					category.setSearchpath("," + fid + ",");
				} else {
					category.setFatherid(sid);
					category.setClasslever(3);
					category.setFullpath(fid + "," + sid);
					category.setSearchpath("," + fid + "," + sid + ",");
				}
			}
		} else {
			category = categoryService.selectByPrimaryKey(ID);
		}
		category.setStatus(ClassStatusEnum.审核通过.getValue()); // 审核通过
		category.setClasstype(ClassTypeEnum.系统分类.getValue());
		category.setName(name);
		category.setSort(StringUtilsEX.ToInt(sort));
		category.setWarnnum(StringUtilsEX.ToInt(warnnum));
		category.setIseditable(iseditable);
		category.setIsvirtual(isvirtual);
		category.setImageurl(img);
		return category;
	}

	/**
	 * 修改规格可输入状态 true-可输入 false-不可输入
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateIsEntry")
	public ReusltItem updateIsEntry(String isentry, String id) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			Integer ID = StringUtilsEX.ToInt(id);
			if (ID < 0) {
				item.setCode(-101);
				item.setDesc("规格ID参数错误,id:" + id);
				return item;
			}
			Boolean IsEntry = StringUtilsEX.ToBooleanNull(isentry);
			if (IsEntry == null) {
				item.setCode(-102);
				item.setDesc("规格可输入状态参数错误,orderby:" + isentry);
				return item;
			}
			if (productSpecsService.updateIsEntry(IsEntry, ID) > 0) {
				item.setCode(0);
				item.setDesc("修改规格可输入状态成功");
				LogHandle.info(LogType.commodity,
						MessageFormat.format("修改规格可输入状态成功! "
								+ "id:{0},isentry:{1},操作人ID:{2}", id,isentry,user.getUserId()),
						"/platform/commodity/updateIsEntry");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "prospecs_list.jsp", "/platform/commodity/updateIsEntry", "修改规格可输入状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改规格可输入状态操作记录出错! 异常信息:",
    								e, "/platform/commodity/updateIsEntry");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("修改规格可输入状态失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改规格可输入状态出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"修改规格可输入状态出错! 异常信息:", e,
					"/platform/commodity/updateIsEntry");
		}
		return item;
	}

	/**
	 * 修改规格值状态 0-启用 1-禁用
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateValueStatus")
	public ReusltItem updateValueStatus(String status, String id) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			Integer ID = StringUtilsEX.ToInt(id);
			if (id == null || ID < 0) {
				item.setCode(-101);
				item.setDesc("规格值ID参数错误,id:" + id);
				return item;
			}
			Integer ss = StringUtilsEX.ToInt(status);
			if (status == null || ss < 0) {
				item.setCode(-102);
				item.setDesc("规格值状态参数错误,orderby:" + status);
				return item;
			}
			if (specsvaluesService.updateStatus(ss, ID) > 0) {
				LogHandle.info(LogType.commodity,
						MessageFormat.format("修改规格值状态成功! "
								+ "id:{0},status:{1},操作人ID:{2}", id,status,user.getUserId()),
						"/platform/commodity/updateValueStatus");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "specs_values.jsp", "/platform/commodity/updateValueStatus", "修改规格值状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改规格值状态操作记录出错! 异常信息:",
    								e, "/platform/commodity/updateValueStatus");
    					}
    					
    				}
    			});
				item.setCode(0);
				item.setDesc("修改规格值状态成功");
			} else {
				item.setCode(-200);
				item.setDesc("修改规格值状态失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改规格值状态出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"修改规格值状态出错! 异常信息:", e,
					"/platform/commodity/updateValueStatus");
		}
		return item;
	}

	/**
	 * 添加分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/addCategory")
	public ReusltItem addCategory(String name, String fatherid,
			String secondid, String sort, String warnnum, String img,
			boolean isvirtual, boolean iseditable) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			Category category = checkCategoryParam("0", name, fatherid,
					secondid, sort, warnnum, img, iseditable, isvirtual, item);
			if (item.getCode() < 0) {
				return item;
			}
			int result = categoryService.addCategory(category);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("添加分类信息成功");
				LogHandle.info(LogType.commodity, MessageFormat.format("修改分类信息成功! "
						+ "name:{0},操作人ID:{1}", name, user.getUserId()),
						"/platform/commodity/addCategory");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_classadd.jsp", "/platform/commodity/addCategory", "添加分类信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加分类信息操作记录出错! 异常信息:",
    								e, "/platform/commodity/addCategory");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("添加分类信息失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加分类信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"添加分类信息出错! 异常信息:", e,
					"/platform/commodity/addCategory");
		}
		return item;
	}

	/**
	 * 自定义分类审核
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/checkCategory")
	public ReusltItem checkCategory(String id, String status) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("自定义分类ID参数错误：" + id);
				return item;
			}
			Integer Status = StringUtilsEX.ToInt(status);
			if (Status != ClassStatusEnum.审核未通过.getValue()
					&& Status != ClassStatusEnum.审核通过.getValue()) {
				item.setCode(-102);
				item.setDesc("自定义分类状态参数错误：" + status);
				return item;
			}
			Category category = new Category();
			category.setStatus(Status);
			category.setId(StringUtilsEX.ToInt(id));
			if (categoryService.updateStatus(category) > 0) {
				item.setCode(0);
				item.setDesc("审核成功");
				LogHandle.info(LogType.commodity, MessageFormat.format("审核自定义分类成功! "
						+ "id:{0},status:{0},操作人ID:{1}", id,status, user.getUserId()),
						"/platform/commodity/checkCategory");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_customlist.jsp", "/platform/commodity/checkCategory", "修改自定义分类审核");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改自定义分类审核操作记录出错! 异常信息:",
    								e, "/platform/commodity/checkCategory");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("提交审核失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("审核自定义分类出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"审核自定义分类出错! 异常信息:", e,
					"/platform/commodity/checkCategory");
		}
		return item;
	}

	/**
	 * 批量审核自定义分类
	 * 
	 * @param ids
	 * @param statuss
	 * @return
	 */
	@RequestMapping("/checkCategoryList")
	public ReusltItem checkCategoryList(String ids, String status) {
		ReusltItem item = new ReusltItem();
		try {
			List<Integer> idlist = new ArrayList<Integer>();
			SessionUser user = SessionState.GetCurrentUser();
			for (String i : ids.split(",")) {
				if (StringUtilsEX.ToInt(i) <= 0) {
					item.setCode(-101);
					item.setDesc("自定义分类ID参数错误：" + i);
					return item;
				}
				idlist.add(StringUtilsEX.ToInt(i));
			}
			Integer Status = StringUtilsEX.ToInt(status);
			if (Status != ClassStatusEnum.审核未通过.getValue()
					&& Status != ClassStatusEnum.审核通过.getValue()) {
				item.setCode(-102);
				item.setDesc("自定义分类状态参数错误：" + status);
				return item;
			}

			if (categoryService.updateStatusList(Status, idlist) > 0) {
				item.setCode(0);
				item.setDesc("审核成功");
				LogHandle.info(LogType.commodity, MessageFormat.format("批量审核自定义分类成功! "
						+ "ids:{0},status:{0},操作人ID:{1}", ids,status, user.getUserId()),
						"/platform/commodity/checkCategoryList");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_customlist.jsp", "/platform/commodity/checkCategoryList", "修改批量审核自定义分类");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改批量审核自定义分类操作记录出错! 异常信息:",
    								e, "/platform/commodity/checkCategoryList");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("提交审核失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("审核自定义分类出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"批量审核自定义分类出错! 异常信息:", e,
					"/platform/commodity/checkCategoryList");
		}
		return item;
	}

	/**
	 * 获取自定义分类审核列表
	 * 
	 * @param classid
	 * @param shopid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getCheckList")
	public ReusltItem getCheckCategoryList(String classid, String shopid,
			String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaCommodity criteriaCommodity = new CriteriaCommodity();
			if (StringUtilsEX.ToInt(classid) > 0) {
				criteriaCommodity.setFatherid(StringUtilsEX.ToInt(classid));
			}
			if (StringUtilsEX.ToInt(shopid) > 0) {
				criteriaCommodity.setShopid(StringUtilsEX.ToInt(shopid));
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误。pageindex：" + page + ",pagesize:" + size);
			}
			PageBean pBean = categoryService.getCheckList(criteriaCommodity,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);
			item.setDesc("success");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取自定义分类审核列表出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.commodity,
					"获取自定义分类审核列表出错! 异常信息:", e,
					"/platform/commodity/getCheckList");
		}
		return item;
	}
}
