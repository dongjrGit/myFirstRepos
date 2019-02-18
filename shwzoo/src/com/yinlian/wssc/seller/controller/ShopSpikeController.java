package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.SpikeShopStatusEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.controller.SpikeActivityController;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.po.SpikeSpu;
import com.yinlian.wssc.web.po.Spikeshop;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.SkuService;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller/ShopSpike")
public class ShopSpikeController {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(SpikeActivityController.class);

	@Autowired
	private SpikeActivityService spikeActivityService;

	@Autowired
	private SkuService skuService;
	@Autowired
	private OperaterecordsService operaterecordsService ;

	SessionUser user=null;
	// 店铺申请活动管理

	/**
	 * 获取店铺申请记录 待审核
	 * 
	 * @param index
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
	public ReusltItem getShopApplyList(String page, String size, String num,
			String stype, String start1, String end1, String start2, String end2) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			int PageIndex = StringUtilsEX.ToInt(page);
			int PageSize = StringUtilsEX.ToInt(size);
			if (PageSize <= 0 || PageIndex <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数非法：" + page + "," + size);
				return item;
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setShopid(user.getUserId());
			if (!StringUtilsEX.IsNullOrWhiteSpace(num)) {
				criteria.setNum(num);
			}
			if (StringUtilsEX.ToInt(stype) >= 0) {
				criteria.setActType(StringUtilsEX.ToInt(stype));
			}
//			criteria.setActType(SpikeTypeEnum.闪购.getValue());
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
			
			PageBean pBean = spikeActivityService.GetShopApplyByPage(
					criteria, PageIndex, PageSize);
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("获取店铺申请记录错误，错误详情：" + ex.getLocalizedMessage());
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
			user=SessionState.GetCurrentUser();
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
			criteria.setShopid(user.getShopid()); // 所属店铺ID 默认为 1
			PageBean pBean = spikeActivityService.getSpuBySpikeIDPage(criteria,
					PageIndex, PageSize);
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("根据活动ID获取参与商品列表错误，错误详情：" + ex.getLocalizedMessage());
		}
		return item;
	}

	// 秒杀活动店铺申请管理

	/**
	 * 店铺申请活动
	 * @param spikeid
	 * @return
	 */
	@RequestMapping("/addSpikeShop")
	public ReusltItem addSpikeShop(String spikeid) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			Spikeshop ss = new Spikeshop();
			if (StringUtilsEX.ToInt(spikeid) <= 0) {
				item.setCode(-101);
				item.setDesc("活动ID参数非法：" + spikeid);
				return item;
			}
			ss.setShopid(user.getShopid()); // 所属店铺ID 默认为 1
			ss.setSpikeid(StringUtilsEX.ToInt(spikeid));
			ss.setStatus(SpikeShopStatusEnum.提交审核中.getValue());
			ss.setCreatetime(new Date());
			ss.setCreateuserid(user.getUserId()); // 用户ID 默认为 1

			if (spikeActivityService.insertSpikeshop(ss) > 0) {
				item.setCode(0);
				item.setDesc("店铺申请秒杀活动成功");
				logger.info(MessageFormat.format(
						"店铺申请秒杀活动成功！添加时间:{0},活动ID:{1},店铺ID:{2}", new Date(),
						spikeid, 1));
				 SessionUser user=SessionState.GetCurrentUser();
	                //异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "店铺申请秒杀活动页面", "/seller/ShopSpike/addSpikeShop", "店铺申请秒杀活动");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"添加店铺申请秒杀活动操作记录出错! 异常信息:",
	    								e, "/seller/ShopSpike/addSpikeShop");
	    					}
	    					
	    				}
	    			});
			} else {
				item.setCode(-200);
				item.setDesc("店铺申请秒杀活动失败");
				logger.warn(MessageFormat.format(
						"店铺申请秒杀活动失败！添加时间:{0},活动ID:{1},店铺ID:{2}", new Date(),
						spikeid, 1));
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("店铺申请秒杀活动错误，错误详情：" + ex.getLocalizedMessage());
		}
		return item;
	}

	/**店铺申请活动 申请不通过后 继续申请
	 * 
	 * @param spikeshopid
	 * @return
	 */
	@RequestMapping("/updateSpikeShop")
	public ReusltItem updateSpikeShop(String spikeshopid) {
		ReusltItem item = new ReusltItem();
		try {
			Spikeshop ss = new Spikeshop();
			if (StringUtilsEX.ToInt(spikeshopid) <= 0) {
				item.setCode(-101);
				item.setDesc("申请ID参数非法：" + spikeshopid);
				return item;
			}
			if (spikeActivityService.updateCheck(
					StringUtilsEX.ToInt(spikeshopid),
					SpikeShopStatusEnum.提交审核中.getValue()) > 0) {
				item.setCode(0);
				item.setDesc("店铺重新申请秒杀活动成功");
				logger.info(MessageFormat.format(
						"店铺重新申请秒杀活动成功！修改时间:{0},ID:{1},店铺ID:{2}", new Date(),
						spikeshopid, 1));
				 SessionUser user=SessionState.GetCurrentUser();
	                //异步操作 不影响正常流程
	                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    			cachedThreadPool.execute(new Runnable() {
	    				@Override
	    				public void run() {
	    					try{
	    						operaterecordsService.insertOperaterecords(
	                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
	                            		user.getUserId(), user.getLoginName(), "店铺重新申请秒杀活动页面", "/seller/ShopSpike/updateSpikeShop", "店铺重新申请秒杀活动");
	    					}
	    					catch(Exception e){
	    						LogHandle.error(LogType.OperateRecords,"添加店铺重新申请秒杀活动操作记录出错! 异常信息:",
	    								e, "/seller/ShopSpike/updateSpikeShop");
	    					}
	    					
	    				}
	    			});

			} else {
				item.setCode(-200);
				item.setDesc("店铺重新申请秒杀活动失败");
				logger.warn(MessageFormat.format(
						"店铺重新申请秒杀活动失败！修改时间:{0},ID:{1},店铺ID:{2}", new Date(),
						spikeshopid, 1));
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("店铺申请秒杀活动错误，错误详情：" + ex.getLocalizedMessage());
		}
		return item;
	}

	/**
	 * 店铺退出秒杀活动
	 * @param id
	 * @return
	 */
	@RequestMapping("/delSpikeShop")
	public ReusltItem delSpikeShop(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数非法：" + id);
				return item;
			}
			if (spikeActivityService.deleteSpikeshop(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("店铺退出秒杀活动成功");
				logger.info(MessageFormat.format(
						"店铺退出秒杀活动成功！退出时间:{0},ID:{1},店铺ID:{2}", new Date(), id,
						1));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "店铺退出秒杀活动页面", "/seller/ShopSpike/delSpikeShop", "店铺退出秒杀活动");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加店铺退出秒杀活动操作记录出错! 异常信息:",
    								e, "/seller/ShopSpike/delSpikeShop");
    					}
    					
    				}
    			});

			} else {
				item.setCode(-200);
				item.setDesc("店铺退出秒杀活动失败");
				logger.warn(MessageFormat.format(
						"店铺退出秒杀活动失败！退出时间:{0},ID:{1},店铺ID:{2}", new Date(), id,
						1));
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("店铺退出秒杀活动错误，错误详情：" + ex.getLocalizedMessage());
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
	public ReusltItem addSpikeSpu(String spikeid, String spuid, String price,
			String count, String isphone, String orderby) {
		ReusltItem item = new ReusltItem();
		try {
			SpikeSpu ss = new SpikeSpu();
			ss = checkSpikeSpu(spikeid, spuid, price, count, isphone, orderby,
					"0", item);
			if (ss == null || item.getCode() < 0)
				return item;
			if (spikeActivityService.insertSpikeSpu(ss) > 0) {
				item.setCode(0);
				item.setDesc("秒杀活动添加商品成功");
				logger.info(MessageFormat.format(
						"秒杀活动添加商品成功！添加时间:{0},活动ID:{1},spuID:{2}", new Date(),
						spikeid, spuid));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "秒杀活动添加商品页面", "/seller/ShopSpike/addSpikeSpu", "秒杀活动添加商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加秒杀活动添加商品操作记录出错! 异常信息:",
    								e, "/seller/ShopSpike/addSpikeSpu");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("秒杀活动添加商品失败");
				logger.warn(MessageFormat.format(
						"秒杀活动添加商品失败！添加时间:{0},活动ID:{1},spuID:{2}", new Date(),
						spikeid, spuid));
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("秒杀活动添加商品错误，错误详情：{0}" + ex.getLocalizedMessage());
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
	public ReusltItem updateSpikeSpu(String id, String spikeid, String spuid,
			String price, String count, String isphone, String orderby) {
		ReusltItem item = new ReusltItem();
		try {
			SpikeSpu ss = new SpikeSpu();
			ss = checkSpikeSpu(spikeid, spuid, price, count, isphone, orderby,
					id, item);
			if (ss == null || item.getCode() < 0)
				return item;

			if (spikeActivityService.updateSpikeSpu(ss) > 0) {
				item.setCode(0);
				item.setDesc("编辑秒杀活动商品成功");
				logger.info(MessageFormat.format(
						"编辑秒杀活动商品成功！修改时间:{0},ID:{1},活动ID:{2},SPUID:{3}",
						new Date(), id, spikeid, spuid));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "编辑秒杀活动库存商品页面", "/seller/ShopSpike/updateSpikeSpu", "编辑秒杀活动库存商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加编辑秒杀活动库存商品操作记录出错! 异常信息:",
    								e, "/seller/ShopSpike/updateSpikeSpu");
    					}
    					
    				}
    			});

			} else {
				item.setCode(-200);
				item.setDesc("编辑秒杀活动商品失败");
				logger.warn(MessageFormat.format(
						"编辑秒杀活动商品失败！修改时间:{0},ID:{1},活动ID:{2},SPUID:{3}",
						new Date(), id, spikeid, spuid));
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("编辑秒杀活动商品错误，错误详情：" + ex.getLocalizedMessage());
		}
		return item;
	}

	private SpikeSpu checkSpikeSpu(String spikeid, String spuid, String price,
			String count, String isphone, String orderby, String id,
			ReusltItem item) throws Exception {
		SpikeSpu ss = new SpikeSpu();
user=SessionState.GetCurrentUser();
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
		if (StringUtilsEX.ToInt(isphone) < 0
				|| StringUtilsEX.ToInt(isphone) > 1) {
			item.setCode(-107);
			item.setDesc("是否手机共享参数错误：" + isphone);
			return null;
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
				logger.error(MessageFormat.format(
						"修改秒杀活动商品错误，根据ID未能检索到数据.ID:{0}", id));
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
			if (StringUtilsEX.ToInt(id) < 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误：" + id);
				return item;
			}
			if (spikeActivityService.deleteSpikeSpu(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("删除秒杀活动商品成功");
				logger.info(MessageFormat.format("删除秒杀活动商品失败！删除时间:{0},ID:{1}",
						new Date(), id));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除秒杀活动管理商品页面", "/seller/ShopSpike/delSpikeSpu", "删除秒杀活动管理商品商品");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加删除秒杀活动管理商品操作记录出错! 异常信息:",
    								e, "/seller/ShopSpike/delSpikeSpu");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("删除秒杀活动商品失败");
				logger.warn(MessageFormat.format("删除秒杀活动商品失败！删除时间:{0},ID:{1}",
						new Date(), id));
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("删除秒杀活动商品错误，错误详情：" + ex.getLocalizedMessage());
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
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误：" + id);
				return item;
			}
			if (StringUtilsEX.ToInt(isphone) < 0
					|| StringUtilsEX.ToInt(isphone) > 1) {
				item.setCode(-102);
				item.setDesc("手机专享参数错误：" + isphone);
				return item;
			}

			if (spikeActivityService.updateIsPhone(StringUtilsEX.ToInt(isphone),StringUtilsEX.ToInt(id)
					) > 0) {
				item.setCode(0);
				item.setDesc("修改商品手机专享成功");
				logger.info(MessageFormat.format(
						"修改商品手机专享成功！修改时间:{0},ID:{1},状态:{2}", new Date(), id,
						isphone));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改商品手机专享页面", "/seller/ShopSpike/changeIsPhone", "修改商品手机专享");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改商品手机专享操作记录出错! 异常信息:",
    								e, "/seller/ShopSpike/changeIsPhone");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("修改商品手机专享失败");
				logger.warn(MessageFormat.format(
						"修改商品手机专享失败！修改时间:{0},ID:{1},状态:{2}", new Date(), id,
						isphone));
			}
		} catch (Exception ex) {
			item.setCode(-900);
			item.setDesc("系统错误：" + ex.getMessage());
			logger.error("修改商品手机专享错误，错误详情：" + ex.getLocalizedMessage());
		}
		return item;
	}
	
	@RequestMapping("/getSpuStartwithName")
    public ReusltItem getSpuStartwithName(String spikeid,String name){
    	ReusltItem item=new ReusltItem();
    	try{
    		user=SessionState.GetCurrentUser();
    		  if (StringUtilsEX.ToInt(spikeid) <= 0)
              {
              	item.setCode(-101);
              	item.setDesc("活动ID参数错误：" + spikeid);
                  return item;
              }
//    		  int shopid=1;//所属店铺ID， 默认为1
    		  item.setData(spikeActivityService.getSpuStartwithName(user.getShopid(), StringUtilsEX.ToInt(spikeid), name));
    		  item.setCode(0);
    	}
    	 catch (Exception ex)
        {
        	item.setCode(-900);
            item.setDesc("系统错误："+ex.getMessage());
            logger.error("获取spu列表错误，错误详情："+ex.getLocalizedMessage());
       }
    	 return item;
    }
	
	@RequestMapping("/getSpuPriceStartwithName")
	public ReusltItem getSpuPriceStartwithName(String name,String spikeid){
		ReusltItem item=new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(spikeid) <= 0)
            {
            	item.setCode(-101);
            	item.setDesc("活动ID参数错误：" + spikeid);
                return item;
            }
			int price=spikeActivityService.getSpuPriceStartwithName(user.getShopid(), StringUtilsEX.ToInt(spikeid), name);
			item.setData(price);
  		  	item.setCode(0);
		} catch (Exception ex) {
			item.setCode(-900);
            item.setDesc("系统错误："+ex.getMessage());
            logger.error("获取spu列表商品价格错误，错误详情："+ex.getLocalizedMessage());
		}
		return item;
	}
	
}





