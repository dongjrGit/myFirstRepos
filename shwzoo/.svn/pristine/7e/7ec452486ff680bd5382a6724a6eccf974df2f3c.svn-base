package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaCommodity;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller/shopcategory")
public class ShopCategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BrandService brandService;

	SessionUser user = null;
	 @Autowired
	private OperaterecordsService operaterecordsService ;

	/**
	 * 查询店铺下商品分类
	 * 
	 * @return
	 */
	@RequestMapping("/queryCategorys")
	public ReusltItem queryCategorys(String fid, String name,
			String status, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			CriteriaCommodity criteriaCommodity = new CriteriaCommodity();
			if (StringUtilsEX.ToInt(fid) > 0) {
				criteriaCommodity.setFatherid(StringUtilsEX.ToInt(fid));
			}
			if (StringUtilsEX.ToInt(status) >= 0) {
				criteriaCommodity.setClassStatus(StringUtilsEX.ToInt(status));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
				criteriaCommodity.setName(name);
			}
			// 根据用户获取店铺ID
			criteriaCommodity.setShopid(user.getShopid());
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误。pageindex：" + page + ",pagesize:" + size);
			}
			PageBean pBean = categoryService.getClassByShopPage(
					criteriaCommodity, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);
			item.setDesc("success");
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询店铺下商品分类出现的异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product,
					"查询店铺下商品分类出现的异常信息:", e,
					"/seller/shopcategory/queryCategorys");
			
		}

		return item;
	}

	/**
	 * 修改自定义分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/updateCategory")
	public ReusltItem updateCategory(String id, String name,
			String firstID, String secondID, String sort) {
		ReusltItem item = new ReusltItem();
		try {
			Category category = checkCategoryParam(id, name, firstID, secondID,
					sort, item);
			if (item.getCode() < 0) {
				return item;
			}
			int result = categoryService.updateCategory(category);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("修改分类信息成功");
				LogHandle.info(LogType.Product, MessageFormat.format(
						"修改分类信息成功! ID:{0},分类名称:{1}", id, name),
						"shopcategory/updateCategory");
				 SessionUser user=SessionState.GetCurrentUser();
	                //异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "修改自定义分类页面", "/seller/shopcategory/updateCategory", "修改自定义分类");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"添加修改自定义分类操作记录出错! 异常信息:",
	    								e, "/seller/shopcategory/updateCategory");
	    					}
	    					
	    				}
	    			});
			} else {
				item.setCode(-200);
				item.setDesc("修改分类信息失败");
				LogHandle.info(LogType.Product, MessageFormat.format(
						"修改分类信息失败! ID:{0},分类名称:{1}", id, name),
						"shopcategory/updateCategory");
			}

		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("修改自定义分类出现的异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "修改自定义分类出现的异常:" , e,
					"/seller/shopcategory/updateCategory");
		}
		return item;
	}

	/**
	 * 删除自定义分类
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCategory")
	public ReusltItem deleteCategory(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("分类ID参数错误");
				return item;
			} else {

				int result = categoryService.deleteCatetory(
						StringUtilsEX.ToInt(id), user.getUserId());
				if (result > 0) {
					item.setCode(0);
					item.setDesc("删除成功");
					LogHandle.info(LogType.Product,
							MessageFormat.format("删除分类信息成功! ID:{0}", id),
							"shopcategory/deleteCategory");
					 SessionUser user=SessionState.GetCurrentUser();
		                //异步操作 不影响正常流程
		                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		    			cachedThreadPool.execute(new Runnable() {
		    				@Override
		    				public void run() {
		    					try{
		    						operaterecordsService.insertOperaterecords(
		                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
		                            		user.getUserId(), user.getLoginName(), "删除自定义分类页面", "/seller/shopcategory/deleteCategory", "删除自定义分类");
		    					}
		    					catch(Exception e){
		    						LogHandle.error(LogType.OperateRecords,"添加删除自定义分类操作记录出错! 异常信息:",
		    								e, "/seller/shopcategory/deleteCategory");
		    					}
		    					
		    				}
		    			});
				} else {
					item.setCode(-200);
					item.setDesc("删除失败");
					LogHandle.info(LogType.Product,
							MessageFormat.format("删除分类信息失败! ID:{0}", id),
							"shopcategory/deleteCategory");

				}
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("修改自定义分类出现的异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "删除自定义分类出现的异常:",e,
					"/seller/shopcategory/updateCategory");
		}
		return item;
	}

	/**
	 * 添加自定义分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/addCategory")
	public ReusltItem addCategory(String name, String firstID,
			String secondID,  String sort) {
		ReusltItem item = new ReusltItem();
		try {
			Category category = checkCategoryParam("0", name, firstID,
					secondID, sort, item);
			if (item.getCode() < 0) {
				return item;
			}
			int result = categoryService.addCategory(category);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("添加分类信息成功");
				LogHandle.info(LogType.Product,
						MessageFormat.format("添加分类信息成功! 分类名称:{0}", name),
						"shopcategory/addCategory");
				 SessionUser user=SessionState.GetCurrentUser();
	                //异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "添加自定义分类页面", "/seller/shopcategory/addCategory", "添加自定义分类");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"添加添加自定义分类操作记录出错! 异常信息:",
	    								e, "/seller/shopcategory/addCategory");
	    					}
	    					
	    				}
	    			});
			} else {
				item.setCode(-200);
				item.setDesc("添加分类信息失败");
				LogHandle.info(LogType.Product,
						MessageFormat.format("添加分类信息失败! 分类名称:{0}", name),
						"shopcategory/addCategory");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("添加自定义分类出现的异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "添加自定义分类信息异常:",e,
					"/seller/shopcategory/addCategory");
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
			String fatherid, String secondid, String sort,
			ReusltItem item) throws Exception {
		Category category = new Category();
		user = SessionState.GetCurrentUser();
		if (StringUtils.isBlank(name)) {
			item.setCode(-101);
			item.setDesc("分类名称不能为空");
			return null;
		}
		Integer ID = StringUtilsEX.ToInt(id);
		if (ID < 0) {
			item.setCode(-102);
			item.setDesc("分类ID参数错误");
			return null;
		}
		if (StringUtilsEX.ToInt(sort) < 0) {
			item.setCode(-103);
			item.setDesc("分类排序参数错误，sort：" + sort);
			return null;
		}

		if (ID == 0) {
			category.setIsdel(false);
			category.setWarnnum(0);
			category.setIseditable(true);
			category.setIsvirtual(false);
			category.setCreattime(new Date());

			category.setShopid(user.getShopid());// 店铺ID，默认为1

			Integer fid = StringUtilsEX.ToInt(fatherid);
			Integer sid = StringUtilsEX.ToInt(secondid);
			if (fid < 0 || sid < 0) {
				item.setCode(-103);
				item.setDesc("所属类别错误");
			}

			category.setFatherid(sid);
			category.setClasslever(4);
			category.setFullpath(fid + "," + sid);
			category.setSearchpath("," + fid + "," + sid + ",");
		} else {
			category = categoryService.selectByPrimaryKey(ID);
		}
		category.setStatus(ClassStatusEnum.未提交.getValue()); // 审核通过
		category.setClasstype(ClassTypeEnum.商家自定义.getValue());
		category.setName(name);
		category.setSort(StringUtilsEX.ToInt(sort));

		return category;
	}

	/**
	 * 自定义分类提交审核
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/subReview")
	public ReusltItem subReview(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("自定义分类ID参数错误：" + id);
				return item;
			}
			Category category = new Category();
			category.setStatus(ClassStatusEnum.审核中.getValue());
			category.setId(StringUtilsEX.ToInt(id));
			if (categoryService.updateStatus(category) > 0) {
				item.setCode(0);
				item.setDesc("提交审核成功");
				LogHandle.info(LogType.Product,
						MessageFormat.format("提交审核分类信息成功! id:{0}", id),
						"shopcategory/subReview");
				 SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改自定义分类状态页面", "/seller/shopcategory/subReview", "修改自定义分类状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改自定义分类状态操作记录出错! 异常信息:",
    								e, "/seller/shopcategory/subReview");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("提交审核失败");
				LogHandle.info(LogType.Product,
						MessageFormat.format("提交审核分类信息失败! id:{0}", id),
						"shopcategory/subReview");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("自定义分类提交审核异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "自定义分类提交审核异常:",e,
					"/seller/shopcategory/subReview");
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
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(fatherid) < 0) {
				item.setCode(-101);
				item.setDesc("参数错误，fatherid：" + fatherid);
				return item;
			}
			// 获取店铺对应ID，先默认为1

			list = categoryService.getFatherByShop(Integer.parseInt(fatherid),
					user.getShopid());
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("根据父ID查询子分类信息异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "根据父ID查询子分类信息异常:" , e,
					"/seller/shopcategory/getClassByFatherID");
		}

		return item;
	}

	@RequestMapping("/getByClassShop")
	public ReusltItem getByClassShop(String classid) {
		ReusltItem item = new ReusltItem();
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(classid) < 0) {
				item.setCode(-101);
				item.setDesc("参数错误，classid：" + classid);
				return item;
			}
			// 获取店铺对应ID，先默认为1

			list = categoryService.getByClassShop(StringUtilsEX.ToInt(classid),
					user.getShopid());
			item.setDesc("查询子分类信息成功");
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("查询子分类信息异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "查询子分类信息异常:",e,
					"/seller/shopcategory/getByClassShop");
		}

		return item;
	}

	@RequestMapping("/getBrandWithName")
	public @ResponseBody ReusltItem getBrandWithName(String name) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			int shopid = user.getShopid();
			item.setData(brandService.getBrandWithNameShop(shopid, name));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("模糊检索品牌列表异常:" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Product, "模糊检索品牌列表异常:",e,
					"/seller/shopcategory/getBrandWithName");
		}
		return item;
	}
}
