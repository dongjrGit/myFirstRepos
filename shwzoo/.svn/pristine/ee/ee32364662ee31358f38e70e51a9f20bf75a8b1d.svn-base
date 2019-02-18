package com.yinlian.wssc.seller.controller;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PackageTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.controller.PackageController;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.SkuPackage;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.PackageService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/seller/ShopPackage")
public class ShopPackageController {

	private static final Logger logger = LoggerFactory
			.getLogger(ShopPackageController.class);
	@Autowired
	private PackageService packageService;
	@Autowired
	private OperaterecordsService operaterecordsService ;
	
	SessionUser user=null;
	/**
	 * 添加组合商品
	 * 
	 * @param name
	 * @param count
	 * @param starttime
	 * @param endtime
	 * @param orderby
	 * @param status
	 * @return
	 */
	@RequestMapping("/addPackage")
	public @ResponseBody ReusltItem addPackage(String name, String count,
			String start, String end, String orderby, String status,String useplatform) {
		ReusltItem item = new ReusltItem();
		try {
			Package pack = new Package();
			pack = checkParam(name, count, start, end, orderby, status,useplatform,
					"0", item);
			if (item.getCode() < 0) {
				return item;
			}
			if (packageService.insert(pack) > 0) {
				logger.info(String.format("添加组合商品成功！编号:{0},名称:{1}",
						pack.getNum(), name));
				item.setCode(0);
				item.setDesc("添加组合商品成功！");
				SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
               ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                        		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                        		user.getUserId(), user.getLoginName(), "添加组合商品页面", "/seller/ShopPackage/addPackage", "添加组合商品");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加添加组合商品操作记录出错! 异常信息:",
								e, "/seller/ShopPackage/addPackage");
					}
					
				}
			});
			} else {
				logger.warn(String.format("添加组合商品失败！名称:{0}", name));
				item.setCode(-200);
				item.setDesc("添加组合商品失败！");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加组合商品异常，异常信息：" + e.getLocalizedMessage());
			logger.error(PackageController.class + "添加组合商品出现异常："
					+ e.getMessage());
		}
		return item;
	}

	/**
	 * 编辑组合商品
	 * 
	 * @param id
	 * @param name
	 * @param count
	 * @param starttime
	 * @param endtime
	 * @param orderby
	 * @param status
	 * @return
	 */
	@RequestMapping("/updatePackage")
	public @ResponseBody ReusltItem updatePackage(String id, String name,
			String count, String start, String end, String orderby,
			String status,String useplatform) {
		ReusltItem item = new ReusltItem();
		try {
			Package pack = new Package();
			pack = checkParam(name, count, start, end, orderby, status,useplatform,
					id, item);
			if (item.getCode() < 0) {
				return item;
			}
			if (packageService.update(pack) > 0) {
				logger.info(String.format("编辑组合商品成功！id:{0},编号:{1}", id,
						pack.getNum()));
				item.setCode(0);
				item.setDesc("编辑组合商品成功！");
				SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
             ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                      		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                      		user.getUserId(), user.getLoginName(), "编辑组合商品页面", "/seller/ShopPackage/updatePackage", "编辑组合商品");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加编辑组合商品操作记录出错! 异常信息:",
								e, "/seller/ShopPackage/updatePackage");
					}
					
				}
			});
			} else {
				logger.warn(String.format("编辑组合商品失败！id:{0},编号:{1}", id,
						pack.getNum()));
				item.setCode(-200);
				item.setDesc("编辑组合商品失败！");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("编辑组合商品异常，异常信息：" + e.getLocalizedMessage());
			logger.error(PackageController.class + "添加组合商品出现异常："
					+ e.getMessage());
		}
		return item;
	}

	private Package checkParam(String name, String count, String starttime,
			String endtime, String orderby, String status, String useplatform,String id,
			ReusltItem item) throws Exception {
		user=SessionState.GetCurrentUser();
		Package pack = new Package();
		Integer ID = StringUtilsEX.ToInt(id);
		if (ID < 0) {
			item.setCode(-101);
			item.setDesc("组合商品ID参数错误，id：" + id);
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
			item.setCode(-102);
			item.setDesc("组合商品名称不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(count) <= 0) {
			item.setCode(-103);
			item.setDesc("组合商品数量参数错误，count：" + count);
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(starttime)) {
			item.setCode(-104);
			item.setDesc("组合商品开始时间不能为空");
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(endtime)) {
			item.setCode(-105);
			item.setDesc("组合商品结束时间不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(orderby) < 0) {
			item.setCode(-106);
			item.setDesc("组合商品排序参数错误，orderby：" + orderby);
			return null;
		}
		if (StringUtilsEX.ToInt(status) < 0) {
			item.setCode(-107);
			item.setDesc("组合商品状态参数错误，status：" + status);
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(useplatform)) {
			item.setCode(-117);
			item.setDesc("请选择使用平台");
			return null;
		}

		if (ID == 0) {
			pack.setNum(ProductNumUtil.getCouponNum());
			pack.setCreatetime(new Date());
			pack.setCreateuserid(user.getUserId()); // 操作人ID 默认为1
			pack.setShopid(user.getShopid()); // 所属店铺ID 默认为1
		} else {
			pack = packageService.getByID(ID);
		}
		pack.setIscheck(false);
		pack.setName(name);
		pack.setCount(StringUtilsEX.ToInt(count));
		pack.setStarttime(StringUtilsEX.ToDate(starttime));
		pack.setEndtime(StringUtilsEX.ToDate(endtime));
		pack.setOrderby(StringUtilsEX.ToInt(orderby));
		pack.setStatus(StringUtilsEX.ToInt(status));
		pack.setPacktype(PackageTypeEnum.组合商品.getValue());
		pack.setUsesite(useplatform);
		return pack;
	}

	/**
	 * 删除组合商品
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePackage")
	public @ResponseBody ReusltItem deletePackage(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("组合商品ID参数错误");
				return item;
			}
			if (packageService.delete(StringUtilsEX.ToInt(id)) > 0) {
				logger.info(String.format("删除组合商品成功！id:{0}", id));
				item.setCode(0);
				item.setDesc("删除组合商品成功！");
				SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
           ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                    		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                    		user.getUserId(), user.getLoginName(), "删除组合商品页面", "/seller/ShopPackage/deletePackage", "删除组合商品");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加删除组合商品操作记录出错! 异常信息:",
								e, "/seller/ShopPackage/deletePackage");
					}
					
				}
			});
			} else {
				logger.warn(String.format("删除组合商品失败！id:{0}", id));
				item.setCode(-200);
				item.setDesc("删除组合商品失败！");
			}

		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除组合商品异常，异常信息：" + e.getLocalizedMessage());
			logger.error(PackageController.class + "删除组合商品出现异常："
					+ e.getMessage());
		}
		return item;
	}

	/**
	 * 获取组合商品列表
	 * 
	 * @param num
	 * @param name
	 * @param status
	 * @param shopid
	 * @param startf
	 * @param startt
	 * @param endf
	 * @param endt
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getList")
	public @ResponseBody ReusltItem getList(String num, String name,
			String status, String startf, String startt,
			String endf, String endt, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			CriteriaActivity cActivity = new CriteriaActivity();
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cActivity.setNum(num);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
				cActivity.setName(name);
			}
			cActivity.setShopid(user.getShopid()); //所属店铺ID 默认为 1
			if (StringUtilsEX.ToInt(status) >= 0) {
				cActivity.setStatus(StringUtilsEX.ToInt(status));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(startf)) {
				cActivity.setStartFrom(StringUtilsEX.ToShortDate(startf));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(startt)) {
				cActivity.setStartTo(StringUtilsEX.ToShortDate(startt));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endf)) {
				cActivity.setEndFrom(StringUtilsEX.ToShortDate(endf));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endt)) {
				cActivity.setEndTo(StringUtilsEX.ToShortDate(endt));
			}

			PageBean pBean = packageService.getPackageByPage(cActivity,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			logger.error(PackageController.class + "获取组合商品列表出现异常："
					+ e.getMessage());
		}
		return item;
	}

	/**
	 * 组合商品审核
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateIsCheck")
	public @ResponseBody ReusltItem updateIsCheck(String id){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误，id:" + id);
				return item;
			}
			if (packageService.updateCheck(StringUtilsEX.ToInt(id), true) > 0) {
				item.setCode(0);
				item.setDesc("审核组合商品成功");
				logger.info(String
						.format("审核组合商品成功! ID:{0}", id));
				SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
               ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                  		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                  		user.getUserId(), user.getLoginName(), "组合商品审核页面", "/seller/ShopPackage/updateIsCheck", "组合商品审核");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加组合商品审核操作记录出错! 异常信息:",
								e, "/seller/ShopPackage/updateIsCheck");
					}
					
				}
			});
			} else {
				item.setCode(-200);
				item.setDesc("审核组合商品失败");
				logger.warn(String
						.format("审核组合商品失败! ID:{0}", id));
			}

		} catch (Exception e) {
			logger.error("审核组合商品出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}
	/**
	 * 编辑组合商品状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public @ResponseBody ReusltItem updateStatus(String id,String status){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误，id:" + id);
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-102);
				item.setDesc("状态参数错误，status:" + status);
				return item;
			}
			if (packageService.updateStatus(StringUtilsEX.ToInt(id),StringUtilsEX.ToInt(status)
					) > 0) {
				item.setCode(0);
				item.setDesc("编辑组合商品状态成功");
				logger.info(String
						.format("编辑组合商品状态成功! ID:{0},状态:{1}", id, status));
				SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
               ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                		user.getUserId(), user.getLoginName(), "编辑组合商品状态页面", "/seller/ShopPackage/updateStatus", "编辑组合商品状态");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加编辑组合商品状态操作记录出错! 异常信息:",
								e, "/seller/ShopPackage/updateStatus");
					}
					
				}
			});
			} else {
				item.setCode(-200);
				item.setDesc("编辑组合商品状态失败");
				logger.warn(String
						.format("编辑组合商品状态失败! ID:{0},状态:{1}", id, status));
			}

		} catch (Exception e) {
			logger.error("编辑组合商品状态出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}
	
	
	/**
	 * 添加关联库存商品
	 * 
	 * @param packageid
	 * @param skuid
	 * @param price
	 * @return
	 */
	@RequestMapping("/addSkuPackage")
	public @ResponseBody ReusltItem addSkuPackage(String packageid,
			String skuid, String price) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(packageid) <= 0) {
				item.setCode(-101);
				item.setDesc("组合商品ID参数错误");
				return item;
			}
			if (StringUtilsEX.ToInt(skuid) <= 0) {
				item.setCode(-102);
				item.setDesc("库存商品ID参数错误");
				return item;
			}
			if (StringUtilsEX.ToFloat(price) <= 0.0f) {
				item.setCode(-103);
				item.setDesc("优惠价格参数错误");
				return item;
			}
			SkuPackage sp=new SkuPackage();
			sp.setPackageid(StringUtilsEX.ToInt(packageid));
			sp.setSkuid(StringUtilsEX.ToInt(skuid));
			sp.setSkuprice(StringUtilsEX.ToFloat(price));
			if(packageService.addSkuPackage(sp)>0){
				item.setCode(0);
				item.setDesc("添加关联库存成功");
				logger.info(String.format("添加关联库存成功！组合商品id:{0},库存商品id:{1}", packageid,skuid));
				SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
             ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
              		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
              		user.getUserId(), user.getLoginName(), "添加关联库存商品页面", "/seller/ShopPackage/addSkuPackage", "添加关联库存商品");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加添加关联库存商品操作记录出错! 异常信息:",
								e, "/seller/ShopPackage/addSkuPackage");
					}
					
				}
			});
			}
			else{
				item.setCode(-200);
				item.setDesc("添加关联库存失败");
				logger.warn(String.format("添加关联库存失败！组合商品id:{0},库存商品id:{1}", packageid,skuid));
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加关联库存异常，异常信息：" + e.getLocalizedMessage());
			logger.error(PackageController.class + "添加关联库存出现异常："
					+ e.getMessage());
		}
		return item;
	}

	/**
	 * 删除组合商品关联库存
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteSkuPackage")
	public @ResponseBody ReusltItem deleteSkuPackage(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误");
				return item;
			}
			if (packageService.deleteSkuPackage(StringUtilsEX.ToInt(id)) > 0) {
				logger.info(String.format("删除组合商品关联库存成功！id:{0}", id));
				item.setCode(0);
				item.setDesc("删除组合商品关联库存成功！");
				SessionUser user = SessionState.GetCurrentUser();
				  //异步操作 不影响正常流程
               ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
			   cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try{
						operaterecordsService.insertOperaterecords(
            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
            		user.getUserId(), user.getLoginName(), "删除组合商品关联库存页面", "/seller/ShopPackage/deleteSkuPackage", "删除组合商品关联库存");
					}
					catch(Exception e){
						LogHandle.error(LogType.OperateRecords,"添加删除组合商品关联库存操作记录出错! 异常信息:",
								e, "/seller/ShopPackage/deleteSkuPackage");
					}
					
				}
			});
			} else {
				logger.warn(String.format("删除组合商品关联库存失败！id:{0}", id));
				item.setCode(-200);
				item.setDesc("删除组合商品关联库存失败！");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除组合商品关联库存异常，异常信息：" + e.getLocalizedMessage());
			logger.error(PackageController.class + "删除组合商品关联库存出现异常："
					+ e.getMessage());
		}
		return item;
	}

	/**
	 * 获取组合商品关联库存列表
	 * @param packageid
	 * @return
	 */
	@RequestMapping("/getSkuByPackageID")
	public @ResponseBody ReusltItem getSkuByPackageID(String packageid) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(packageid) <= 0) {
				item.setCode(-101);
				item.setDesc("组合商品ID参数错误");
				return item;
			}
			item.setData(packageService.getByPackageID(StringUtilsEX.ToInt(packageid)));
			item.setCode(0);
			
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取组合商品关联库存列表异常，异常信息：" + e.getLocalizedMessage());
			logger.error(PackageController.class + "获取组合商品关联库存列表出现异常："
					+ e.getMessage());
		}
		return item;
	}
	/**
	 * 模糊检索sku列表
	 * @param packageid
	 * @param name
	 * @return
	 */
	@RequestMapping("/getSkuStartwithName")
	public @ResponseBody ReusltItem getSkuStartwithName(String packageid,String name){
		ReusltItem item = new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(packageid)<0){
				item.setCode(-101);
				item.setDesc("组合商品ID参数错误");
				return item;
			}
//			int shopid=1; //所属店铺ID 默认为1
			item.setData(packageService.getSkuStartwithName(user.getShopid(), StringUtilsEX.ToInt(packageid), name));
			item.setCode(0);
		}catch(Exception e){
			item.setCode(-900);
			logger.error(PackageController.class + "模糊检索sku列表出现异常："
					+ e.getMessage());
		}
		return item;
	}

}
