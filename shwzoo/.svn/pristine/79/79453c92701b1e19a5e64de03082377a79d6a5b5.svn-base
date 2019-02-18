package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PackageTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Package;
import com.yinlian.wssc.web.po.SkuPackage;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.PackageService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/platform/package")
public class PackageController {

	@Autowired
	private PackageService packageService;
	
	SessionUser user=null;
	@Autowired
    private OperaterecordsService operaterecordsService ;
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
			String starttime, String endtime, String orderby, String status,String useplatform ) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			Package pack = new Package();
			pack = checkParam(name, count, starttime, endtime, orderby, status,useplatform,
					"0", item);
			if (item.getCode() < 0) {
				return item;
			}
			if (packageService.insert(pack) > 0) {
				LogHandle.info(LogType.Activity, MessageFormat.format("添加组合商品成功! 编号:{0},名称:{1},用户ID:{2}",
						pack.getNum(),name,user.getUserId()), "package/addPackage");
				item.setCode(0);
				item.setDesc("添加组合商品成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_PackageAdd.jsp", "/platform/package/addPackage", "添加组合商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加组合商品操作记录出错! 异常信息:",
    								e, "/platform/package/addPackage");
    					}
    					
    				}
    			});
			} else {
				LogHandle.info(LogType.Activity, MessageFormat.format("添加组合商品失败! 编号:{0},名称:{1},用户ID:{2}",
						pack.getNum(),name,user.getUserId()), "package/addPackage");
				item.setCode(-200);
				item.setDesc("添加组合商品失败！");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加组合商品异常，异常信息：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity, MessageFormat.format("添加组合商品出现异常， 信息:{0}",
					e.getMessage()), "package/addPackage");
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
			String count, String starttime, String endtime, String orderby,
			String status,String useplatform) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			Package pack = new Package();
			pack = checkParam(name, count, starttime, endtime, orderby, status,useplatform,
					id, item);
			if (item.getCode() < 0) {
				return item;
			}
			if (packageService.update(pack) > 0) {
				LogHandle.info(LogType.Activity, MessageFormat.format("编辑组合商品成功! id:{0},编号:{1},名称:{2},用户ID:{3}",
						id,pack.getNum(),name,user.getUserId()), "package/updatePackage");
				item.setCode(0);
				item.setDesc("编辑组合商品成功！");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_PackageEdit.jsp", "/platform/package/updatePackage", "修改组合商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改组合商品操作记录出错! 异常信息:",
    								e, "/platform/package/updatePackage");
    					}
    					
    				}
    			});
			} else {
				LogHandle.info(LogType.Activity, MessageFormat.format("编辑组合商品失败! id:{0},编号:{1},名称:{2},用户ID:{3}",
						id,pack.getNum(),name,user.getUserId()), "package/updatePackage");
				item.setCode(0);
				item.setCode(-200);
				item.setDesc("编辑组合商品失败！");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑组合商品异常，异常信息：" + e.getLocalizedMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity, "编辑组合商品出现异常， 信息:{0}",
					e, "package/updatePackage");
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
		pack.setUsesite(useplatform);
		pack.setPacktype(PackageTypeEnum.组合商品.getValue());

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
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("组合商品ID参数错误");
				return item;
			}
			if (packageService.delete(StringUtilsEX.ToInt(id)) > 0) {
				LogHandle.info(LogType.Activity, MessageFormat.format("删除组合商品成功! id:{0},用户ID:{1}",
						id,user.getUserId()), "package/deletePackage");
				item.setCode(0);
				item.setDesc("删除组合商品成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_PackageList.jsp", "/platform/package/deletePackage", "删除组合商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除组合商品操作记录出错! 异常信息:",
    								e, "/platform/package/deletePackage");
    					}
    					
    				}
    			});
			} else {
				LogHandle.info(LogType.Activity, MessageFormat.format("删除组合商品成功! id:{0},用户ID:{1}",
						id,user.getUserId()), "package/deletePackage");
				item.setCode(-200);
				item.setDesc("删除组合商品失败");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除组合商品异常，异常信息：" + e.getLocalizedMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity, "删除组合商品出现异常， 信息:{0}",
					e, "/platform/package/deletePackage");
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
			String status, String shopid, String startf, String startt,
			String endf, String endt, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
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
			if (StringUtilsEX.ToInt(shopid) > 0) {
				cActivity.setShopid(StringUtilsEX.ToInt(shopid));
			}
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
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除组合商品异常，异常信息：" + e.getLocalizedMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"获取组合商品列表出现异常， 信息:{0}",
					e, "/platform/package/getList");
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
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误，id:" + id);
				return item;
			}
			if (packageService.updateCheck(StringUtilsEX.ToInt(id), true) > 0) {
				item.setCode(0);
				item.setDesc("审核组合商品成功");
				LogHandle.info(LogType.Activity, MessageFormat.format("审核组合商品成功! id:{0},用户ID:{1}",
						id,user.getUserId()), "package/updateIsCheck");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_PackageCheck.jsp", "/platform/package/updateIsCheck", "审核组合商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"审核组合商品操作记录出错! 异常信息:",
    								e, "/platform/package/updateIsCheck");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("审核组合商品失败");
				LogHandle.info(LogType.Activity, MessageFormat.format("审核组合商品失败! id:{0},用户ID:{1}",
						id,user.getUserId()), "package/updateIsCheck");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("审核组合商品出现的异常：" + e.getLocalizedMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"审核组合商品出现的异常信息:" + e.getMessage(), "package/updateIsCheck");
		}
		return item;
	}
	/**
	 * 组合商品批量审核
	 * @param ids
	 * @return
	 */
	@RequestMapping("/updateIsCheckList")
	public @ResponseBody ReusltItem updateIsCheckList(String ids){
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			List<Integer> idlist=new ArrayList<Integer>();
			for (String id : ids.split(",")) {
				if (StringUtilsEX.ToInt(id) <= 0) {
					item.setCode(-101);
					item.setDesc("ID参数错误，id:" + id);
					return item;
				}
				idlist.add(StringUtilsEX.ToInt(id));
			}		
			if (packageService.updateCheckList(idlist, true) > 0) {
				item.setCode(0);
				item.setDesc("批量审核组合商品成功");
				LogHandle.info(LogType.Activity, MessageFormat.format("批量审核组合商品成功! id集合:{0},用户ID:{1}",
						ids,user.getUserId()), "package/updateIsCheckList");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_PackageCheck.jsp", "/platform/package/updateIsCheckList", "批量审核组合商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"批量审核组合商品操作记录出错! 异常信息:",
    								e, "/platform/package/updateIsCheckList");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("批量审核组合商品失败");
				LogHandle.info(LogType.Activity, MessageFormat.format("批量审核组合商品失败! id集合:{0},用户ID:{1}",
						ids,user.getUserId()), "package/updateIsCheckList");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("批量审核组合商品出现的异常：" + e.getLocalizedMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"批量审核组合商品出现的异常信息:" + e.getMessage(), "/platform/package/updateIsCheckList");
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
			user=SessionState.GetCurrentUser();
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
				LogHandle.info(LogType.Activity, MessageFormat.format("编辑组合商品状态成功! id:{0},状态:{1},用户ID:{2}",
						id, status,user.getUserId()), "package/updateStatus");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_PackageList.jsp", "/platform/package/updateStatus", "修改组合商品状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改组合商品状态操作记录出错! 异常信息:",
    								e, "/platform/package/updateStatus");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("编辑组合商品状态失败");
				LogHandle.info(LogType.Activity, MessageFormat.format("编辑组合商品状态失败! id:{0},状态:{1},用户ID:{2}",
						id, status,user.getUserId()), "package/updateStatus");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑组合商品状态异常：" + e.getLocalizedMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"编辑组合商品状态异常信息:" + e.getMessage(), "package/updateStatus");
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
			user=SessionState.GetCurrentUser();
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
				LogHandle.info(LogType.Activity, MessageFormat.format("添加关联库存成功！组合商品id:{0},库存商品id:{1},用户ID:{2}",
						packageid, skuid,user.getUserId()), "package/addSkuPackage");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_PackageSku.jsp", "/platform/package/addSkuPackage", "添加关联库存");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加关联库存操作记录出错! 异常信息:",
    								e, "/platform/package/addSkuPackage");
    					}
    					
    				}
    			});
			}
			else{
				item.setCode(-200);
				item.setDesc("添加关联库存失败");
				LogHandle.info(LogType.Activity, MessageFormat.format("添加关联库存失败！组合商品id:{0},库存商品id:{1},用户ID:{2}",
						packageid, skuid,user.getUserId()), "package/addSkuPackage");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加关联库存异常，异常信息：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"添加关联库存异常，异常信息:" + e.getMessage(), "package/addSkuPackage");
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
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误");
				return item;
			}
			if (packageService.deleteSkuPackage(StringUtilsEX.ToInt(id)) > 0) {
				LogHandle.info(LogType.Activity, MessageFormat.format("删除组合商品关联库存成功！id:{0},用户ID:{1}",
						id, user.getUserId()), "package/deleteSkuPackage");
				item.setCode(0);
				item.setDesc("删除组合商品关联库存成功");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_PackageSku.jsp", "/platform/package/deleteSkuPackage", "删除组合商品关联库存");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除组合商品关联库存操作记录出错! 异常信息:",
    								e, "/platform/package/deleteSkuPackage");
    					}
    					
    				}
    			});
			} else {
				LogHandle.info(LogType.Activity, MessageFormat.format("删除组合商品关联库存失败！id:{0},用户ID:{1}",
						id, user.getUserId()), "package/deleteSkuPackage");
				item.setCode(-200);
				item.setDesc("删除组合商品关联库存失败！");
			}
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除组合商品关联库存异常，异常信息：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"删除组合商品关联库存出现异常:" + e.getMessage(),
					"package/deleteSkuPackage");
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
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取组合商品关联库存列表异常，异常信息：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"获取组合商品关联库存列表异常:" + e.getMessage(),
					"/platform/package/getSkuByPackageID");
		}
		return item;
	}
	

	/**
	 * 获取组合商品审核列表
	 * 
	 * @param num
	 * @param name
	 * @param shopid
	 * @param startf
	 * @param startt
	 * @param endf
	 * @param endt
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getCheckList")
	public @ResponseBody ReusltItem getCheckList(String num, String name,
		    String shopid, String startf, String startt,
			String endf, String endt, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
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
			if (StringUtilsEX.ToInt(shopid) > 0) {
				cActivity.setShopid(StringUtilsEX.ToInt(shopid));
			}
			cActivity.setStatus(0);
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
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取组合商品关联库存列表异常，异常信息：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"获取组合商品审核列表出现异常:" + e.getMessage(),
					"/platform/package/getCheckList");
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
			int shopid=user.getShopid(); //所属店铺ID 
			item.setData(packageService.getSkuStartwithName(shopid, StringUtilsEX.ToInt(packageid), name));
			item.setCode(0);
		}catch(Exception e){
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取组合商品关联库存列表异常，异常信息：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"模糊检索sku列表出现异常:" + e.getMessage(),
					"/platform/package/getSkuStartwithName");
		}
		return item;
	}

}
