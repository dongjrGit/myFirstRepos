package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.AdvertImgTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.SpikeShopStatusEnum;
import com.yinlian.Enums.SpikeTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SpikeSpu;
import com.yinlian.wssc.web.po.Spikeactivity;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 秒杀活动管理控制层
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/SpikeActivity")
public class SpikeActivityController {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory.getLogger(SpikeActivityController.class);

	@Autowired
	private SpikeActivityService spikeActivityService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private AdvertImgService advertImgService;

	SessionUser user = null;
	@Autowired
	private OperaterecordsService operaterecordsService;

	/**
	 * 添加秒杀活动
	 * 
	 * @param sname
	 *            名称
	 * @param stype
	 *            类型 0-秒杀 1-闪购 2-促销
	 * @param img
	 *            促销活动展示图片
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param status
	 *            状态 0-启用 1-禁用
	 * @return
	 */
	@RequestMapping("/add")
	public ReusltItem add(String sname, String stype, String img, String start, String end, String status,
			String listdesc, String detaildesc, String useplatform, String tgclass) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			Spikeactivity activity = new Spikeactivity();
			activity = checkParam(sname, stype, img, start, end, status, listdesc, detaildesc, useplatform, "0", item);
			
			if (activity == null || item.getCode() < 0)
				return item;
			if (activity.getSpiketype() == SpikeTypeEnum.团购.getValue()) {
				activity.setCid(StringUtilsEX.ToInt(tgclass));
			}
			if (spikeActivityService.insert(activity) > 0) {
				item.setCode(0);
				item.setDesc("添加秒杀活动成功");
				LogHandle.info(LogType.Activity, MessageFormat.format("添加秒杀活动成功！添加时间:{0},编号:{1},名称:{2}", new Date(),
						activity.getSpikenum(), sname), "/platform/SpikeActivity/add");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_ExcitingAdd.jsp", "/platform/SpikeActivity/add", "添加秒杀活动");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加秒杀活动操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/add");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("添加秒杀活动失败");
				LogHandle.info(LogType.Activity, MessageFormat.format("添加秒杀活动失败！添加时间:{0},编号:{1},名称:{2}", new Date(),
						activity.getSpikenum(), sname), "/platform/SpikeActivity/add");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("添加秒杀活动错误：" + ex.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Activity, "添加秒杀活动错误，错误详情：" , ex, "/platform/SpikeActivity/add");
		}
		return item;
	}

	/**
	 * 编辑秒杀活动
	 * 
	 * @param id
	 * @param sname
	 *            名称
	 * @param stype
	 *            类型 0-秒杀 1-闪购 2-促销
	 * @param img
	 *            促销活动展示图片
	 * @param start
	 * @param end
	 * @param status
	 * @return
	 */
	@RequestMapping("/update")
	public ReusltItem update(String id, String sname, String stype, String img, String start, String end, String status,
			String listdesc, String detaildesc, String useplatform,String tgclass) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			Spikeactivity activity = new Spikeactivity();
			activity = checkParam(sname, stype, img, start, end, status, listdesc, detaildesc, useplatform, id, item);
			if (activity == null || item.getCode() < 0)
				return item;
			if (activity.getSpiketype() == SpikeTypeEnum.团购.getValue()) {
				activity.setCid(StringUtilsEX.ToInt(tgclass));
			}
			if (spikeActivityService.update(activity) > 0) {
				item.setCode(0);
				item.setDesc("编辑秒杀活动成功");
				LogHandle
						.info(LogType.Activity,
								MessageFormat.format("编辑秒杀活动成功！修改时间:{0},ID:{1},编号:{2},名称:{3}", new Date(),
										activity.getId(), activity.getSpikenum(), sname),
								"/platform/SpikeActivity/update");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_SpikeEdit.jsp", "/platform/SpikeActivity/update", "修改秒杀活动");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改秒杀活动操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/update");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("编辑秒杀活动失败");
				LogHandle
						.info(LogType.Activity,
								MessageFormat.format("编辑秒杀活动失败！修改时间:{0},ID:{1},编号:{2},名称:{3}", new Date(),
										activity.getId(), activity.getSpikenum(), sname),
								"/platform/SpikeActivity/update");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("编辑秒杀活动错误：" + ex.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Activity, "编辑秒杀活动错误，错误详情：" , ex, "/platform/SpikeActivity/update");
		}
		return item;
	}

	private Spikeactivity checkParam(String sname, String stype, String img, String start, String end, String status,
			String listdesc, String detaildesc, String useplatform, String id, ReusltItem item) throws Exception {
		user = SessionState.GetCurrentUser();
		Spikeactivity spike = new Spikeactivity();
		int ID = StringUtilsEX.ToInt(id);
		if (ID < 0) {
			item.setCode(-101);
			item.setDesc("秒杀活动ID参数错误：" + id);
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(sname)) {
			item.setCode(-102);
			item.setDesc("秒杀活动名称不能为空");
			return null;
		}
		
		if (StringUtilsEX.ToInt(stype) == SpikeTypeEnum.促销.getValue()) {
			if (StringUtilsEX.IsNullOrWhiteSpace(img)) {
				item.setCode(-107);
				item.setDesc("促销活动图片参数错误：" + img);
				return null;
			}
		}
		if (StringUtilsEX.ToInt(stype) == SpikeTypeEnum.经彩活动.getValue()) {
			if (StringUtilsEX.IsNullOrWhiteSpace(img)) {
				item.setCode(-107);
				item.setDesc("活动图片参数错误：" + img);
				return null;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(listdesc)) {
				item.setCode(-108);
				item.setDesc("活动列表简不能为空");
				return null;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(detaildesc)) {
				item.setCode(-109);
				item.setDesc("活动详情介绍不能为空");
				return null;
			}
		}

		if (StringUtilsEX.ToInt(status) < 0 || StringUtilsEX.ToInt(status) > 1) {
			item.setCode(-104);
			item.setDesc("秒杀活动状态参数错误：" + status);
			return null;
		}

		if (StringUtilsEX.IsNullOrWhiteSpace(start)) {
			item.setCode(-105);
			item.setDesc("开始时间参数错误：" + start);
			return null;

		}
		if (StringUtilsEX.IsNullOrWhiteSpace(end)) {
			item.setCode(-106);
			item.setDesc("结束时间参数错误：" + end);
			return null;
		}
		if (StringUtilsEX.ToInt(stype) != SpikeTypeEnum.经彩活动.getValue()) {
			if (StringUtilsEX.IsNullOrWhiteSpace(useplatform)) {
				item.setCode(-117);
				item.setDesc("请选择使用平台");
				return null;
			}
		}
		if (ID == 0) {
			spike.setSpikenum(ProductNumUtil.getCouponNum()); // 随机生成活动编号
			spike.setCreatetime(new Date());
			spike.setCreateuserid(user.getUserId()); // 所属用户ID 默认为 1
			spike.setShopid(user.getShopid()); // 所属店铺ID 默认为 1
			spike.setIsdel(false);
		} else {
			spike = spikeActivityService.selectByID(ID);
			if (spike == null) {
				item.setCode(-401);
				item.setDesc("根据ID未能检索到数据");
				LogHandle.error(LogType.Activity, MessageFormat.format("修改秒杀活动错误，根据ID未能检索到数据.ID:{0}", id),
						"/platform/SpikeActivity/update");
				return null;
			}
		}
		if (StringUtilsEX.ToInt(status) == 0) {
			if (StringUtilsEX.ToInt(stype) == SpikeTypeEnum.秒杀.getValue()||StringUtilsEX.ToInt(stype) == SpikeTypeEnum.团购.getValue()) {
				List<Spikeactivity> list = new ArrayList<Spikeactivity>();
				list = spikeActivityService.getByDate(StringUtilsEX.ToDate(start), StringUtilsEX.ToInt(stype), ID);
				if (list.size() > 0) {
					item.setCode(-301);
					item.setDesc("已存在" + start + "时间启用的秒杀活动!");
					logger.error("已存在" + start + "时间启用的秒杀活动!");
					return null;
				}
			}
		}
		spike.setSpikename(sname);
		spike.setSpiketype(StringUtilsEX.ToInt(stype));
		spike.setStatus(StringUtilsEX.ToInt(status));
		spike.setStarttime(StringUtilsEX.ToDate(start));
		spike.setEndtime(StringUtilsEX.ToDate(end));
		spike.setImgurl(img);
		spike.setListdesc(listdesc);
		spike.setDetaildesc(detaildesc);
		spike.setUsesite(useplatform);
		return spike;
	}

	/**
	 * 删除秒杀活动
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public ReusltItem delete(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("活动ID参数错误：" + id);
				return item;
			}
			int userid = user.getUserId();
			if (spikeActivityService.deleteSpike(StringUtilsEX.ToInt(id), userid) > 0) {
				item.setCode(0);
				item.setDesc("删除秒杀活动成功");
				LogHandle.info(LogType.Activity, MessageFormat.format("删除秒杀活动成功！删除时间:{0},ID:{1}", new Date(), id),
						"/platform/SpikeActivity/delete");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_SpikeList.jsp", "/platform/SpikeActivity/delete", "删除秒杀活动");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除秒杀活动操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/delete");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("删除秒杀活动失败");
				LogHandle.info(LogType.Activity, MessageFormat.format("删除秒杀活动失败！删除时间:{0},ID:{1}", new Date(), id),
						"/platform/SpikeActivity/delete");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("删除秒杀活动错误：" + ex.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Activity, "删除秒杀活动错误，错误详情：{0}", ex,
					"/platform/SpikeActivity/delete");
		}
		return item;
	}

	/**
	 * 查询秒杀活动列表
	 * 
	 * @param index
	 * @param size
	 * @param num
	 * @param name
	 * @param stype
	 * @param start1
	 * @param end1
	 * @param start2
	 * @param end2
	 * @return
	 */
	@RequestMapping("/getList")
	public ReusltItem getList(String page, String size, String num, String name, String stype, String start1,
			String end1, String start2, String end2) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0 || StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数非法：" + page + "," + size);
				return item;
			}
			CriteriaActivity cActivity = new CriteriaActivity();
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cActivity.setNum(num);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
				cActivity.setName(name);
			}
			if (StringUtilsEX.ToInt(stype) >= 0) {
				cActivity.setActType(StringUtilsEX.ToInt(stype));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(start1)) {
				cActivity.setStartFrom(StringUtilsEX.ToShortDate(start1));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(end1)) {
				cActivity.setStartTo(StringUtilsEX.ToShortDate(end1));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(start2)) {
				cActivity.setEndFrom(StringUtilsEX.ToShortDate(start2));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(end2)) {
				cActivity.setEndTo(StringUtilsEX.ToShortDate(end2));
			}
			PageBean pBean = spikeActivityService.getListPage(cActivity, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		} catch (Exception ex) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("查询秒杀活动列表错误：" + ex.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Activity,"查询秒杀活动列表错误，错误详情：{0}", ex,
					"/platform/SpikeActivity/getList");
		}
		return item;
	}

	/**
	 * 活动状态更新 启用/禁用
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/changeStatus")
	public ReusltItem changeStatus(String id, String status) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("活动ID参数错误：" + id);
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-101);
				item.setDesc("活动状态参数错误：" + status);
				return item;
			}
			if (StringUtilsEX.ToInt(status) == 0) {
				Spikeactivity spike = spikeActivityService.selectByID(StringUtilsEX.ToInt(id));
				if (spike.getSpiketype() == SpikeTypeEnum.秒杀.getValue()) {
					List<Spikeactivity> list = new ArrayList<Spikeactivity>();
					String startD = DateUtil.dateConvert(spike.getStarttime());
					list = spikeActivityService.getByDate(StringUtilsEX.ToDate(startD), spike.getSpiketype(),
							spike.getId());
					if (list.size() > 0) {
						item.setCode(-301);
						item.setDesc("已存在" + DateUtil.dateConvert(spike.getStarttime()) + "时间启用的秒杀活动!");
						logger.error("已存在" + DateUtil.dateConvert(spike.getStarttime()) + "时间启用的秒杀活动!");
						return null;
					}
				}
			}
			if (spikeActivityService.updateStatus(StringUtilsEX.ToInt(status), StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("修改秒杀活动状态成功");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("修改秒杀活动状态成功！修改时间:{0},ID:{1},状态:{2}", new Date(), id, status),
						"/platform/SpikeActivity/changeStatus");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_SpikeList.jsp", "/platform/SpikeActivity/changeStatus", "修改秒杀活动状态");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改秒杀活动状态操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/changeStatus");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("修改秒杀活动状态失败");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("修改秒杀活动状态失败！修改时间:{0},ID:{1},状态:{2}", new Date(), id, status),
						"/platform/SpikeActivity/changeStatus");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("修改秒杀活动状态错误：" + ex.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Activity,"修改秒杀活动状态错误，错误详情：{0}", ex,
					"/platform/SpikeActivity/changeStatus");
		}
		return item;
	}

	/**
	 * 根据活动ID获取店铺申请记录
	 * 
	 * @param page
	 * @param size
	 * @param id
	 * @return
	 */
	@RequestMapping("/getShopBySpikeID")
	public ReusltItem getShopBySpikeID(String page, String size, String id) {
		ReusltItem item = new ReusltItem();
		try {
			int PageIndex = StringUtilsEX.ToInt(page);
			int PageSize = StringUtilsEX.ToInt(size);
			if (PageSize <= 0 || PageIndex <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数非法：" + page + "," + size);
				return item;
			}
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-102);
				item.setDesc("活动ID参数非法：" + id);
				return item;
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSpikeID(StringUtilsEX.ToInt(id));
			PageBean pageBean = spikeActivityService.GetApplyBySpikeIDPage(criteria, PageIndex, PageSize);
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception ex) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("根据活动ID获取店铺申请记录错误：" + ex.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Activity,"根据活动ID获取店铺申请记录错误，错误详情：{0}", ex,
					"/platform/SpikeActivity/getShopBySpikeID");
		}
		return item;
	}

	/**
	 * 获取店铺申请记录 待审核
	 * 
	 * @param page
	 * @param size
	 * @param num
	 * @param stype
	 * @param start1
	 * @param end1
	 * @param start2
	 * @param end2
	 * @return
	 */
	@RequestMapping("/getShopApplyList")
	public ReusltItem getShopApplyList(String page, String size, String num, String stype, String start1, String end1,
			String start2, String end2) {
		ReusltItem item = new ReusltItem();
		try {
			int PageIndex = StringUtilsEX.ToInt(page);
			int PageSize = StringUtilsEX.ToInt(size);
			if (PageSize <= 0 || PageIndex <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数非法：" + page + "," + size);
				return item;
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setStatus(SpikeShopStatusEnum.提交审核中.getValue());
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				criteria.setNum(num);
			}
			if (StringUtilsEX.ToInt(stype) >= 0) {
				criteria.setActType(StringUtilsEX.ToInt(stype));
			}
			// criteria.setActType(SpikeTypeEnum.闪购.getValue());
			if (!StringUtilsEX.IsNullOrWhiteSpace(start1)) {
				criteria.setStartFrom(StringUtilsEX.ToShortDate(start1));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(end1)) {
				criteria.setStartTo(StringUtilsEX.ToShortDate(end1));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(start2)) {
				criteria.setEndFrom(StringUtilsEX.ToShortDate(start2));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(end2)) {
				criteria.setEndTo(StringUtilsEX.ToShortDate(end2));
			}
			PageBean pBean = spikeActivityService.GetApplyBySpikeIDPage(criteria, PageIndex, PageSize);
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		} catch (Exception ex) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取店铺待审核申请记录错误：" + ex.getMessage());
			}else {
				item.setDesc("系统错误");
			}
			LogHandle.error(LogType.Activity, "获取店铺待审核申请记录错误，错误详情：{0}", ex,
					"/platform/SpikeActivity/getShopApplyList");
		}
		return item;
	}

	/**
	 * 平台审核店铺申请
	 * 
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateCheck")
	public ReusltItem updateCheck(String ids, String status) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			List<Integer> idlist = new ArrayList<Integer>();
			for (String id : ids.split(",")) {
				if (StringUtilsEX.ToInt(id) <= 0) {
					item.setCode(-101);
					item.setDesc("ID参数非法：" + id);
					return item;
				}
				idlist.add(StringUtilsEX.ToInt(id));
			}
			if (StringUtilsEX.ToInt(status) < SpikeShopStatusEnum.审核通过.getValue()
					|| StringUtilsEX.ToInt(status) > SpikeShopStatusEnum.审核不通过.getValue()) {
				item.setCode(-102);
				item.setDesc("审核状态参数非法：" + status);
				return item;
			}
			if (idlist.size() == 1) {
				if (spikeActivityService.updateCheck(idlist.get(0), StringUtilsEX.ToInt(status)) > 0) {
					item.setCode(0);
					item.setDesc("审批店铺申请成功");
					LogHandle.info(LogType.Activity,
							MessageFormat.format("审批店铺申请成功！修改时间:{0},ID:{1},状态:{2}", new Date(), ids, status),
							"/platform/SpikeActivity/updateCheck");
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
										"yxgl_SpikeShopCheck.jsp", "/platform/SpikeActivity/updateCheck", "审批店铺申请");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "审批店铺申请操作记录出错! 异常信息:", e,
										"/platform/SpikeActivity/updateCheck");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("审批店铺申请失败");
					LogHandle.info(LogType.Activity,
							MessageFormat.format("审批店铺申请失败！修改时间:{0},ID:{1},状态:{2}", new Date(), ids, status),
							"/platform/SpikeActivity/updateCheck");
				}
			} else {
				if (spikeActivityService.updateCheckList(idlist, StringUtilsEX.ToInt(status)) > 0) {
					item.setCode(0);
					item.setDesc("批量审批店铺申请成功");
					LogHandle.info(LogType.Activity,
							MessageFormat.format("批量审批店铺申请成功！修改时间:{0},ID集合:{1},状态:{2}", new Date(), ids, status),
							"/platform/SpikeActivity/updateCheck");
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
										"yxgl_SpikeShopCheck.jsp", "/platform/SpikeActivity/updateCheck", "批量审批店铺申请");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "批量审批店铺申请操作记录出错! 异常信息:", e,
										"/platform/SpikeActivity/updateCheck");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("批量审批店铺申请失败");
					LogHandle.info(LogType.Activity,
							MessageFormat.format("批量审批店铺申请失败！修改时间:{0},ID集合:{1},状态:{2}", new Date(), ids, status),
							"/platform/SpikeActivity/updateCheck");
				}
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			LogHandle.error(LogType.Activity, MessageFormat.format("平台审核店铺申请错误，错误详情：{0}", ex.toString()),
					"/platform/SpikeActivity/updateCheck");
		}
		return item;
	}

	/**
	 * 根据活动ID获取参与商品列表
	 * 
	 * @param page
	 * @param size
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSpikeSpuList")
	public ReusltItem getSpikeSpuList(String page, String size, String id) {
		ReusltItem item = new ReusltItem();
		try {
			int PageIndex = StringUtilsEX.ToInt(page);
			int PageSize = StringUtilsEX.ToInt(size);
			if (PageSize <= 0 || PageIndex <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数非法：" + page + "," + size);
				return item;
			}

			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-102);
				item.setDesc("活动ID参数非法：" + id);
				return item;
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSpikeID(StringUtilsEX.ToInt(id));
			PageBean pBean = spikeActivityService.getSpuBySpikeIDPage(criteria, PageIndex, PageSize);
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			LogHandle.error(LogType.Activity, MessageFormat.format("根据活动ID获取参与商品列表错误，错误详情：{0}", ex.toString()),
					"/platform/SpikeActivity/getSpikeSpuList");
		}
		return item;
	}

	// 秒杀参与商品管理

	/**
	 * 秒杀活动添加商品
	 * 
	 * @param spikeid
	 * @param spuid
	 * @param price
	 * @param count
	 * @param isphone
	 * @param orderby
	 * @return
	 */
	@RequestMapping("/addSpikeSpu")
	public ReusltItem addSpikeSpu(String spikeid, String spuid, String price, String count, String isphone,
			String orderby) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			SpikeSpu ss = new SpikeSpu();
			ss = checkSpikeSpu(spikeid, spuid, price, count, isphone, orderby, "0", item);
			if (ss == null || item.getCode() < 0)
				return item;
			if (spikeActivityService.insertSpikeSpu(ss) > 0) {
				item.setCode(0);
				item.setDesc("秒杀活动添加商品成功");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("秒杀活动添加商品成功！添加时间:{0},活动ID:{1},spuID:{2}", new Date(), spikeid, spuid),
						"/platform/SpikeActivity/addSpikeSpu");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_SpikeSpuAdd.jsp", "/platform/SpikeActivity/addSpikeSpu", "添加秒杀活动商品");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加秒杀活动商品操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/addSpikeSpu");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("秒杀活动添加商品失败");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("秒杀活动添加商品失败！添加时间:{0},活动ID:{1},spuID:{2}", new Date(), spikeid, spuid),
						"/platform/SpikeActivity/addSpikeSpu");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			LogHandle.error(LogType.Activity, MessageFormat.format("秒杀活动添加商品错误，错误详情：{0}", ex.toString()),
					"/platform/SpikeActivity/addSpikeSpu");
		}
		return item;
	}

	/**
	 * 编辑秒杀活动库存商品
	 * 
	 * @param id
	 * @param spikeid
	 * @param spuid
	 * @param price
	 * @param count
	 * @param isphone
	 * @param orderby
	 * @return
	 */
	@RequestMapping("/updateSpikeSpu")
	public ReusltItem updateSpikeSpu(String id, String spikeid, String spuid, String price, String count,
			String isphone, String orderby) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			SpikeSpu ss = new SpikeSpu();
			ss = checkSpikeSpu(spikeid, spuid, price, count, isphone, orderby, id, item);
			if (ss == null || item.getCode() < 0)
				return item;

			if (spikeActivityService.updateSpikeSpu(ss) > 0) {
				item.setCode(0);
				item.setDesc("编辑秒杀活动商品成功");
				LogHandle.info(LogType.Activity, MessageFormat.format("编辑秒杀活动商品成功！修改时间:{0},ID:{1},活动ID:{2},SPUID:{3}",
						new Date(), id, spikeid, spuid), "/platform/SpikeActivity/updateSpikeSpu");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_SpikeSpuEdit.jsp", "/platform/SpikeActivity/updateSpikeSpu", "修改秒杀活动库存商品");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改秒杀活动库存商品操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/updateSpikeSpu");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("编辑秒杀活动商品失败");
				LogHandle.info(LogType.Activity, MessageFormat.format("编辑秒杀活动商品失败！修改时间:{0},ID:{1},活动ID:{2},SPUID:{3}",
						new Date(), id, spikeid, spuid), "/platform/SpikeActivity/updateSpikeSpu");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			LogHandle.error(LogType.Activity, MessageFormat.format("编辑秒杀活动商品错误，错误详情：{0}", ex.toString()),
					"/platform/SpikeActivity/updateSpikeSpu");

		}
		return item;
	}

	private SpikeSpu checkSpikeSpu(String spikeid, String spuid, String price, String count, String isphone,
			String orderby, String id, ReusltItem item) throws Exception {
		SpikeSpu ss = new SpikeSpu();
		user = SessionState.GetCurrentUser();
		int ID = StringUtilsEX.ToInt(id);
		int SpikeID = StringUtilsEX.ToInt(spikeid);
		int SpuID = StringUtilsEX.ToInt(spuid);
		int InCount = StringUtilsEX.ToInt(count);
		if (ID < 0) {
			item.setCode(-101);
			item.setDesc("ID参数错误：" + id);
			return null;
		}
		if (SpikeID <= 0) {
			item.setCode(-102);
			item.setDesc("秒杀活动ID参数错误：" + spikeid);
			return null;
		}
		if (SpuID <= 0) {
			item.setCode(-103);
			item.setDesc("商品ID参数错误：" + spuid);
			return null;
		}
		if (StringUtilsEX.ToFloat(price) <= 0.0f) {
			item.setCode(-104);
			item.setDesc("优惠金额参数错误：" + price);
			return null;
		}
		if (InCount <= 0) {
			item.setCode(-105);
			item.setDesc("商品数量参数错误：" + count);
			return null;
		}
		if (StringUtilsEX.ToInt(orderby) <= 0) {
			item.setCode(-106);
			item.setDesc("排序参数错误：" + orderby);
			return null;
		}
		if (StringUtilsEX.ToInt(isphone) < 0 || StringUtilsEX.ToInt(isphone) > 1) {
			item.setCode(-107);
			item.setDesc("是否手机共享参数错误：" + isphone);
			return null;
		}
		// 去除交叉时间活动
		Spikeactivity sact = spikeActivityService.selectByID(SpikeID);
		List<Spikeactivity> sacts = spikeActivityService.Spikeactivitylist(SpuID, 0);
		Date sstart = sact.getStarttime();
		Date eend = sact.getEndtime();
		for (Spikeactivity sp : sacts) {
			Date startDate = sp.getStarttime();
			Date endDate = sp.getEndtime();
			if (sstart.getTime() > startDate.getTime() && sstart.getTime() < endDate.getTime()) {
				item.setCode(-109);
				item.setDesc("此商品已在其他活动时间存在交集");
				return null;
			}
			if (eend.getTime() > startDate.getTime() && eend.getTime() < endDate.getTime()) {
				item.setCode(-109);
				item.setDesc("此商品已在其他活动时间存在交集");
				return null;
			}
		}

		// begin 获取Spu下所有sku商品总库存
		int AllStock = 0;
		List<Sku> Skus = skuService.getListBySpuID(SpuID);
		if (Skus != null && Skus.size() > 0) {
			for (Sku sku : Skus) {
				AllStock += sku.getStock();
			}
		}
		if (AllStock < InCount) {
			item.setCode(-108);
			item.setDesc("活动商品数量大于库存总数，当前库存总数为：" + AllStock);
			return null;
		}
		// end by wangl 20160303

		if (ID == 0) {
			ss.setSpikeid(SpikeID);
			ss.setSpuid(SpuID);
			ss.setStatus(0);
			ss.setShopid(user.getShopid()); // 所属店铺ID 默认为1
		} else if (ID > 0) {
			ss = spikeActivityService.selectSpikeSpuByID(ID);
			if (ss == null) {
				item.setCode(-401);
				item.setDesc("根据ID未能检索到数据");
				LogHandle.error(LogType.Activity, MessageFormat.format("修改秒杀活动商品错误，根据ID未能检索到数据.ID:{0}", id),
						"/platform/SpikeActivity/updateSpikeSpu");
				return null;
			}
		}
		ss.setSpucount(InCount);
		ss.setPrice(StringUtilsEX.ToFloat(price));
		ss.setIsphone(StringUtilsEX.ToInt(isphone));
		ss.setOrderby(StringUtilsEX.ToInt(orderby));
		return ss;
	}

	/**
	 * 删除秒杀活动管理商品
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delSpikeSpu")
	public ReusltItem delSpikeSpu(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误：" + id);
				return item;
			}
			if (spikeActivityService.deleteSpikeSpu(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("删除秒杀活动商品成功");
				LogHandle.info(LogType.Activity, MessageFormat.format("删除秒杀活动商品失败！删除时间:{0},ID:{1}", new Date(), id),
						"/platform/SpikeActivity/delSpikeSpu");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_SpikeSpuList.jsp", "/platform/SpikeActivity/delSpikeSpu", "删除秒杀活动商品");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除秒杀活动商品操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/delSpikeSpu");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("删除秒杀活动商品失败");
				LogHandle.info(LogType.Activity, MessageFormat.format("删除秒杀活动商品失败！删除时间:{0},ID:{1}", new Date(), id),
						"/platform/SpikeActivity/delSpikeSpu");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			LogHandle.error(LogType.Activity, MessageFormat.format("删除秒杀活动商品错误，错误详情:{0}", ex.toString()),
					"/platform/SpikeActivity/delSpikeSpu");
		}
		return item;
	}

	/**
	 * 手机专享 是/否
	 * 
	 * @param id
	 * @param isphone
	 * @return
	 */
	@RequestMapping("/changeIsPhone")
	public ReusltItem changeIsPhone(String id, String isphone) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误：" + id);
				return item;
			}
			if (StringUtilsEX.ToInt(isphone) < 0 || StringUtilsEX.ToInt(isphone) > 1) {
				item.setCode(-102);
				item.setDesc("手机专享参数错误：" + isphone);
				return item;
			}

			if (spikeActivityService.updateIsPhone(StringUtilsEX.ToInt(isphone), StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("修改商品手机专享成功");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("修改商品手机专享成功！修改时间:{0},ID:{1},状态:{2}", new Date(), id, isphone),
						"/platform/SpikeActivity/changeIsPhone");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_SpikeSpuList.jsp", "/platform/SpikeActivity/changeIsPhone", "修改商品手机专享");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改商品手机专享操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/changeIsPhone");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("修改商品手机专享失败");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("修改商品手机专享失败！修改时间:{0},ID:{1},状态:{2}", new Date(), id, isphone),
						"/platform/SpikeActivity/changeIsPhone");
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			LogHandle.error(LogType.Activity, MessageFormat.format("修改商品手机专享错误，错误详情:{0}", ex.toString()),
					"/platform/SpikeActivity/changeIsPhone");
		}
		return item;
	}

	@RequestMapping("/getSpuStartwithName")
	public ReusltItem getSpuStartwithName(String spikeid, String name) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(spikeid) <= 0) {
				item.setCode(-101);
				item.setDesc("活动ID参数错误：" + spikeid);
				return item;
			}
			int shopid = user.getShopid();// 所属店铺ID， 默认为1

			item.setData(spikeActivityService.getSpuStartwithName(shopid, StringUtilsEX.ToInt(spikeid), name));
			item.setCode(0);
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			LogHandle.error(LogType.Activity, MessageFormat.format("获取spu列表错误，错误详情:{0}", ex.toString()),
					"/platform/SpikeActivity/getSpuStartwithName");
		}
		return item;
	}

	@RequestMapping("/getSpuPriceStartwithName")
	public ReusltItem getSpuPriceStartwithName(String spikeid, String name) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(spikeid) <= 0) {
				item.setCode(-101);
				item.setDesc("活动ID参数错误：" + spikeid);
				return item;
			}
			int price = spikeActivityService.getSpuPriceStartwithName(user.getShopid(), StringUtilsEX.ToInt(spikeid),
					name);
			item.setData(price);
			item.setCode(0);
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("获取spu列表商品价格错误，错误详情：" + ex.getLocalizedMessage());
		}
		return item;
	}

	/**
	 * 添加图片
	 * 
	 * @param img
	 * @param groupid
	 * @param sort
	 * @param status
	 * @return
	 */
	@RequestMapping("/addImg")
	public ReusltItem addImg(String img, String spikeid, String sort) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(spikeid) <= 0) {
				item.setCode(-101);
				item.setDesc("经彩活动ID参数错误");
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(img)) {
				item.setCode(-102);
				item.setDesc("图片不能为空");
				return item;
			}
			if (StringUtilsEX.ToInt(sort) <= 0) {
				item.setCode(-103);
				item.setDesc("图片排序参数错误");
				return item;
			}
			AdvertImg adimg = new AdvertImg();
			adimg.setCreattime(new Date());
			adimg.setGroupbyid(StringUtilsEX.ToInt(spikeid));
			adimg.setImgurl(img);
			adimg.setIsdel(false);
			adimg.setShopid(user.getShopid());
			adimg.setSort(StringUtilsEX.ToInt(sort));
			adimg.setStatus(0);
			adimg.setType(AdvertImgTypeEnum.经彩活动.getValue()); // 团购图片

			if (advertImgService.insert(adimg) > 0) {
				item.setCode(0);
				item.setDesc("添加经彩活动图片成功!");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("添加经彩活动图片成功! img:{0},经彩活动ID:{1},用户ID:{2}", img, spikeid, user.getUserId()),
						"/platform/SpikeActivity/addImg");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.添加.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_ExcitingAdd.jsp", "/platform/SpikeActivity/addImg", "添加经彩活动图片");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "添加经彩活动图片操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/addImg");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("添加经彩活动图片成功!");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("添加经彩活动图片成功! id:{0},经彩活动ID:{1},用户ID:{2}", img, spikeid, user.getUserId()),
						"/platform/SpikeActivity/addImg");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("添加经彩活动图片异常：" + e.toString());
			LogHandle.error(LogType.Activity, MessageFormat.format("添加经彩活动图片异常! 异常信息:{0}", e.toString()),
					"/platform/SpikeActivity/addImg");
		}
		return item;
	}

	/**
	 * 删除图片
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delimg")
	public ReusltItem delimg(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("图片ID参数错误");
				return item;
			}
			if (advertImgService.deleteAdvert(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("删除经彩活动图片成功!");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("删除经彩活动图片成功! ID:{0},用户ID:{1}", id, user.getUserId()),
						"/platform/SpikeActivity/delimg");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_ExcitingList.jsp", "/platform/SpikeActivity/delimg", "删除经彩活动图片");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "删除经彩活动图片操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/delimg");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("删除经彩活动图片成功!");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("删除经彩活动图片成功! ID:{0},用户ID:{1}", id, user.getUserId()),
						"/platform/SpikeActivity/delimg");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("删除经彩活动图片异常：" + e.getMessage());
			LogHandle.error(LogType.Activity, MessageFormat.format("删除经彩活动图片异常! 异常信息:{0}", e.getMessage()),
					"/platform/SpikeActivity/delimg");
		}
		return item;
	}

	/**
	 * 更新图片排序
	 * 
	 * @param id
	 * @param sort
	 * @return
	 */
	@RequestMapping("/updateImgSort")
	public ReusltItem updateImgSort(String id, String sort) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("图片ID参数错误");
				return item;
			}
			if (StringUtilsEX.ToInt(sort) <= 0) {
				item.setCode(-102);
				item.setDesc("经彩活动图片排序参数错误");
				return item;
			}
			if (advertImgService.updateOrder(StringUtilsEX.ToInt(sort), StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("更新图片排序成功!");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("更新图片排序成功! ID:{0},用户ID:{1}", id, user.getUserId()),
						"/platform/SpikeActivity/updateImgSort");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"yxgl_ExcitingEdit.jsp", "/platform/SpikeActivity/updateImgSort", "更新图片排序");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "更新图片排序操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/updateImgSort");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("更新图片排序失败!");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("更新图片排序失败! ID:{0},用户ID:{1}", id, user.getUserId()),
						"/platform/SpikeActivity/updateImgSort");
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("更新图片排序异常：" + e.getMessage());
			LogHandle.error(LogType.Activity, MessageFormat.format("更新图片排序异常! 异常信息:{0}", e.getMessage()),
					"/platform/SpikeActivity/updateImgSort");
		}
		return item;
	}

	/**
	 * 查询经彩活动参与用户列表
	 * 
	 * @param index
	 * @param size
	 * @param spikeid
	 * @return
	 */
	@RequestMapping("/getUserListBySpikeID")
	public ReusltItem getUserListBySpikeID(String page, String size, String spikeid) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0 || StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数非法：" + page + "," + size);
				return item;
			}
			CriteriaActivity cActivity = new CriteriaActivity();
			if (StringUtilsEX.ToInt(spikeid) <= 0) {
				item.setCode(-102);
				item.setDesc("经彩获取ID参数非法：" + spikeid);
				return item;
			}
			cActivity.setSpikeID(StringUtilsEX.ToInt(spikeid));
			PageBean pBean = spikeActivityService.getUserListBySpikeID(cActivity, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.toString());
			LogHandle.error(LogType.Activity, MessageFormat.format("查询经彩活动参与用户列表错误，错误详情：{0}", ex.toString()),
					"/platform/SpikeActivity/getUserListBySpikeID");
		}
		return item;
	}

	/**
	 * 使用经彩活动验证码
	 * 
	 * @param spikeuserid
	 * @return
	 */
	@RequestMapping("/useCode")
	public ReusltItem useCode(String spikeuserid) {
		ReusltItem item = new ReusltItem();
		user = SessionState.GetCurrentUser();
		try {
			if (StringUtilsEX.ToInt(spikeuserid) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数非法：" + spikeuserid);
				return item;
			}
			if (spikeActivityService.updateUseCode(StringUtilsEX.ToInt(spikeuserid)) > 0) {
				item.setCode(0);
				item.setDesc("使用成功");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("更新图片排序成功! ID:{0},操作人ID:{1}", spikeuserid, user.getUserId()),
						"/platform/SpikeActivity/useCode");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(), user.getLoginName(),
									"使用经彩活动验证码页面", "/platform/SpikeActivity/useCode", "使用经彩活动验证码");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "使用经彩活动验证码操作记录出错! 异常信息:", e,
									"/platform/SpikeActivity/useCode");
						}

					}
				});
			} else {
				item.setCode(-200);
				item.setDesc("使用失败");
				LogHandle.info(LogType.Activity,
						MessageFormat.format("更新图片排序成功! ID:{0},操作人ID:{1}", spikeuserid, user.getUserId()),
						"/platform/SpikeActivity/useCode");
			}

		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.toString());
			LogHandle.error(LogType.Activity, MessageFormat.format("使用经彩活动验证码错误，错误详情：{0}", ex.toString()),
					"/platform/SpikeActivity/useCode");
		}
		return item;
	}

	/**
	 * 查询秒杀活动列表
	 * 
	 * @param index
	 * @param size
	 * @param num
	 * @param name
	 * @param stype
	 * @param start1
	 * @param end1
	 * @param start2
	 * @param end2
	 * @return
	 */
	@RequestMapping("/getexcitingList")
	public ReusltItem getexcitingList(String page, String size, String num, String name, String start1, String end1,
			String start2, String end2) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0 || StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数非法：" + page + "," + size);
				return item;
			}
			CriteriaActivity cActivity = new CriteriaActivity();
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				cActivity.setNum(num);
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(name)) {
				cActivity.setName(name);
			}
			cActivity.setActType(SpikeTypeEnum.经彩活动.getValue());
			if (!StringUtilsEX.IsNullOrWhiteSpace(start1)) {
				cActivity.setStartFrom(StringUtilsEX.ToShortDate(start1));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(end1)) {
				cActivity.setStartTo(StringUtilsEX.ToShortDate(end1));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(start2)) {
				cActivity.setEndFrom(StringUtilsEX.ToShortDate(start2));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(end2)) {
				cActivity.setEndTo(StringUtilsEX.ToShortDate(end2));
			}
			PageBean pBean = spikeActivityService.getExcitListPage(cActivity, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.toString());
			LogHandle.error(LogType.Activity, MessageFormat.format("查询经彩活动列表错误，错误详情：{0}", ex.toString()),
					"/platform/SpikeActivity/getexcitingList");
		}
		return item;
	}
}
