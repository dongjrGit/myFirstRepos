package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.BasicConvert;
import com.yinlian.wssc.web.util.CriteriaCommodity;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 商品品牌控制层
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
    @Autowired
    private OperaterecordsService operaterecordsService ;

	/**
	 * 查询出商品品牌的分页信息
	 * 
	 * @param criteria
	 * @param pc
	 * @return
	 */
	@RequestMapping("/queryBrand")
	public ReusltItem queryBrandByCriteria(String name, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
		if (StringUtilsEX.ToInt(page) < 0) {
			item.setCode(-101);
			item.setDesc("分页参数错误，pageindex：" + page);
			return item;
		}
		if (StringUtilsEX.ToInt(size) < 0) {
			item.setCode(-102);
			item.setDesc("分页参数错误，pagesize：" + size);
			return item;
		}
			CriteriaCommodity criteria = new CriteriaCommodity();
			criteria.setBrandname(StringUtilsEX.IsNullOrWhiteSpace(name) ? name : name.trim());
			PageBean pageBean = brandService.selectBrandPage(criteria,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {

			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询商品品牌分页出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand,
					"查询商品品牌分页信息出错! 异常信息:", e,
					"/platform/brand/queryBrand");

		}

		return item;
	}

	/**
	 * 查询出所有的商品品牌
	 * 
	 * @return
	 */
	@RequestMapping("/queryAllBrand")
	public ReusltItem queryAllBrand(String name) {
		ReusltItem item = new ReusltItem();
		try {
			List<Brand> list = brandService.queryAll(StringUtilsEX.IsNullOrWhiteSpace(name) ? name : name.trim());
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询所有商品品牌出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand,
					"查询所有商品品牌信息出错! 异常信息:", e,
					"/platform/brand/queryAllBrand");
		}
		return item;
	}

	/**
	 * 显示商品品牌信息通过id查询出
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/showBrandById")
	public ReusltItem showBrandById(String id) {
		ReusltItem item = new ReusltItem();
		Brand brand = null;
		try {
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("商品品牌ID参数错误id:" + id);
				return item;
			}
			brand = brandService.selectById(StringUtilsEX.ToInt(id));
			item.setCode(0);
			item.setData(brand);
			item.setDesc("查询成功");
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("根据商品品牌ID获取信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand,
					"根据商品品牌ID获取信息出错! 异常信息:", e,
					"/platform/brand/showBrandById");
		}
		return item;
	}

	/**
	 * 修改商品品牌信息
	 * 
	 * @param brand
	 * @return
	 */
	@RequestMapping("/updateBrand")
	public ReusltItem updateBrand(String name,
			String officialurl, String id, String img, String description,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			if (BasicConvert.string2Integer(id) < 0) {
				item.setCode(-101);
				item.setDesc("商品品牌的id参数错误,id" + id);
				return item;
			}

			Brand brand = checkBrandParam(name, img, item);
			if (item.getCode() < 0) {
				return item;
			}
			brand.setId(StringUtilsEX.ToInt(id));
			brand.setDescription(description);
			brand.setOfficialurl(officialurl);
			int result = brandService.updateBrand(brand);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("更新成功");
				 LogHandle.info(LogType.brand, MessageFormat.format("修改商品品牌成功! "
                 	    + "id:{0},name:{1},操作人ID:{2}", 
                 		id,name,  user.getUserId()),"/platform/brand/updateBrand");
				 //异步操作 不影响正常流程
                 ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
     			 cachedThreadPool.execute(new Runnable() {
     				@Override
     				public void run() {
     					try{
     						operaterecordsService.insertOperaterecords(
                             		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                             		user.getUserId(), user.getLoginName(), "goods_brand_update.jsp", "/platform/brand/updateBrand", "修改商品品牌信息");
     					}
     					catch(Exception e){
     						LogHandle.error(LogType.OperateRecords,"修改商品品牌信息操作记录出错! 异常信息:",
     								e, "/platform/brand/updateBrand");
     					}
     					
     				}
     			});
			} else if (result == -401) {
				item.setCode(result);
				item.setDesc("该品牌已存在");
			} else {
				item.setCode(-200);
				item.setDesc("更新失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改商品品牌信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand,
					"修改商品品牌信息出错! 异常信息:", e,
					"/platform/brand/updateBrand");
		}
		return item;
	}

	/**
	 * 添加商品品牌信息
	 * 
	 * @param brand
	 * @return
	 */
	@RequestMapping("/insertBrand")
	public @ResponseBody ReusltItem insertBrand(String name,
			String officialurl, String id, String img, String description) {
		ReusltItem item = new ReusltItem();
		try {
			SessionUser user=SessionState.GetCurrentUser();
			Brand brand = new Brand();
			brand = brandService.getbyName(name.trim());
			if (brand != null) {
				item.setCode(-401);
				item.setDesc(name + "品牌已存在");
				return item;
			}
			brand = checkBrandParam(name, img, item);
			if (item.getCode() < 0) {
				return item;
			}
			brand.setDescription(description);
			brand.setOfficialurl(officialurl);
			brand.setCreatetime(new Date());
			brand.setIsdel(false);
			int result = brandService.insert(brand);
			if (result > 0) {
				item.setCode(0);
				item.setData(brand);
				item.setDesc("添加成功");
				LogHandle.info(LogType.brand, MessageFormat.format("添加商品品牌成功! "
                 	    + "name:{0},操作人ID:{1}", 
                 		name, user.getUserId()),"/platform/brand/insertBrand");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			 cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_brand_add.jsp", "/platform/brand/insertBrand", "添加商品品牌信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加商品品牌信息操作记录出错! 异常信息:",
    								e, "/platform/brand/insertBrand");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setData(brand);
				item.setDesc("添加失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加商品品牌信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand,
					"添加商品品牌信息出错! 异常信息:", e,
					"/platform/brand/insertBrand");
		}
		return item;
	}

	/**
	 * 根据id删除一个商品品牌
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteById")
	public @ResponseBody ReusltItem deleteById(String id,HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			 SessionUser user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("商品品牌ID参数错误id:" + id);
				return item;
			}
			Integer userid=SessionUtil.getSessionUserId(request);
			int result = brandService.deleteBrand(StringUtilsEX.ToInt(id),userid);
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				LogHandle.info(LogType.brand, MessageFormat.format("删除商品品牌成功! "
                 	    + "id:{0},操作人ID:{1}", 
                 	   id, user.getUserId()),"/platform/brand/deleteById");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			 cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "goods_brand_list.jsp", "/platform/brand/deleteById", "删除商品品牌信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除商品品牌信息操作记录出错! 异常信息:",
    								e, "/platform/brand/deleteById");
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
				item.setDesc("删除商品品牌信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand,
					"删除商品品牌信息出错! 异常信息:", e,
					"/platform/brand/deleteById");
		}
		return item;
	}

	// 参数验证
	private Brand checkBrandParam(String name, String img, ReusltItem item)
			throws Exception {
		Brand record = new Brand();

		if (StringUtilsEX.isBlank(name)) {
			item.setCode(-102);
			item.setDesc("商品品牌名称不能为空");
		}
		if (StringUtils.isBlank(name)) {
			item.setCode(-103);
			item.setDesc("商品品牌图片地址不能为空");
		}
		record.setName(name);
		record.setStatus(1);
		record.setImg(img);
		return record;
	}

	/**
	 * 模糊检索所有品牌信息
	 * @param name
	 * @return
	 */
	@RequestMapping("/getBrandWithName")
	public @ResponseBody ReusltItem getBrandWithName(String name) {
		ReusltItem item = new ReusltItem();
		try {
			item.setData(brandService.getBrandWithName(name));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("模糊检索所有品牌信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.brand,
					"模糊检索所有品牌信息出错! 异常信息:", e,
					"/platform/brand/getBrandWithName");
		}
		return item;
	}
}
