package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.AdvertImgTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.GroupbuyWithBLOBs;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.GroupBuyService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaGroupBuy;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.MD5Util;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 卖家团购
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller/shopgroupbuy")
public class ShopGroupBuyController {

	@Autowired
	private GroupBuyService groupBuyService;
	@Autowired
	private AdvertImgService advertImgService;
    @Autowired
	private OperaterecordsService operaterecordsService ;


	SessionUser user = null;

	/**
	 * 添加团购
	 * 
	 * @param title
	 * @param cprice
	 * @param oprice
	 * @param start
	 * @param end
	 * @param type
	 * @param content
	 * @param buynotes
	 * @param isanytime
	 * @param isbook
	 * @param isexpired
	 * @param detaildesc
	 * @param listdesc
	 * @param stock
	 * @return
	 */
	@RequestMapping("/add")
	public ReusltItem add(String title, String cprice, String oprice,
			String starttime, String endtime, String type, String content,
			String buynotes, String isanytime, String isbook, String isexpired,
			String detaildesc, String listdesc, String stock, String status,String useplatform) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			GroupbuyWithBLOBs gBloBs = new GroupbuyWithBLOBs();
			gBloBs = checkParam(title, cprice, oprice, starttime, endtime,
					type, content, buynotes, isanytime, isbook, isexpired,
					detaildesc, listdesc, stock, status,useplatform, "0", item);
			if (item.getCode() < 0) {
				return item;
			}
			if (groupBuyService.insert(gBloBs) > 0) {
				item.setCode(0);
				item.setDesc("添加团购成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"添加团购成功! 标题:{0},用户ID:{1}", title, user.getUserId()),
						"shopgroupbuy/add");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "添加团购页面", "/seller/shopgroupbuy/add", "添加团购");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加添加团购操作记录出错! 异常信息:",
    								e, "/seller/shopgroupbuy/add");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("添加团购失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"添加团购失败! 标题:{0},用户ID:{1}", title, user.getUserId()),
						"shopgroupbuy/add");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("添加团购异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"添加团购异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/add");
		}
		return item;
	}

	/**
	 * 编辑团购
	 * 
	 * @param id
	 * @param title
	 * @param cprice
	 * @param oprice
	 * @param start
	 * @param end
	 * @param type
	 * @param content
	 * @param buynotes
	 * @param isanytime
	 * @param isbook
	 * @param isexpired
	 * @param detaildesc
	 * @param listdesc
	 * @param stock
	 * @return
	 */
	@RequestMapping("/update")
	public ReusltItem update(String id, String title, String cprice,
			String oprice, String starttime, String endtime, String type,
			String content, String buynotes, String isanytime, String isbook,
			String isexpired, String detaildesc, String listdesc, String stock,
			String status,String useplatform) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			GroupbuyWithBLOBs gBloBs = new GroupbuyWithBLOBs();
			gBloBs = checkParams(title, cprice, oprice, starttime, endtime,
					type, content, buynotes, isanytime, isbook, isexpired,
					detaildesc, listdesc, stock, status,useplatform, id, item);
			if (item.getCode() < 0) {
				return item;
			}
			if (groupBuyService.updateByPrimaryKeyWithBLOBs(gBloBs) > 0) {
				item.setCode(0);
				item.setDesc("编辑团购成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"编辑团购成功! id:{0},标题:{1},用户ID:{2}", id, title,
						user.getUserId()), "shopgroupbuy/update");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "编辑团购页面", "/seller/shopgroupbuy/update", "编辑团购");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加编辑团购操作记录出错! 异常信息:",
    								e, "/seller/shopgroupbuy/update");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("编辑团购失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"编辑团购失败! id:{0},标题:{1},用户ID:{2}", id, title,
						user.getUserId()), "shopgroupbuy/update");
			}

		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("编辑团购异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"编辑团购异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/add");
		}
		return item;
	}

	private GroupbuyWithBLOBs checkParam(String title, String cprice,
			String oprice, String start, String end, String type,
			String content, String buynotes, String isanytime, String isbook,
			String isexpired, String detaildesc, String listdesc, String stock,
			String status,String useplatform, String id, ReusltItem item) throws Exception {
		GroupbuyWithBLOBs gb = new GroupbuyWithBLOBs();
		user = SessionState.GetCurrentUser();
		int ID = StringUtilsEX.ToInt(id);
		if (ID < 0) {
			item.setCode(-101);
			item.setDesc("团购ID参数错误，id：" + id);
			return null;
		}
		if (StringUtilsEX.ToFloat(cprice) <= 0.0f) {
			item.setCode(-102);
			item.setDesc("团购现价参数错误，现价：" + cprice);
			return null;
		}
		if (StringUtilsEX.ToFloat(oprice) <= 0.0f) {
			item.setCode(-103);
			item.setDesc("团购原价参数错误，原价：" + oprice);
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(start)) {
			item.setCode(-104);
			item.setDesc("团购开始时间不能为空");
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(end)) {
			item.setCode(-105);
			item.setDesc("团购结束时间不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(type) < 0) {
			item.setCode(-106);
			item.setDesc("团购类型参数错误，类型：" + type);
			return null;
		}
		if (StringUtilsEX.ToBooleanNull(isanytime) == null) {
			item.setCode(-107);
			item.setDesc("是否随时退参数错误");
			return null;
		}
		if (StringUtilsEX.ToBooleanNull(isbook) == null) {
			item.setCode(-108);
			item.setDesc("是否可预订参数错误");
			return null;
		}
		if (StringUtilsEX.ToBooleanNull(isexpired) == null) {
			item.setCode(-109);
			item.setDesc("是否随时退参数错误");
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(useplatform)) {
			item.setCode(-117);
			item.setDesc("请选择使用平台");
			return null;
		}
		if (StringUtilsEX.ToInt(status) < 0) {
			item.setCode(-110);
			item.setDesc("团购状态参数错误，类型：" + status);
			return null;
		}
		if (ID == 0) {
			gb.setCreattime(new Date());
			gb.setShopid(user.getShopid());
			gb.setIsdel(false);
			gb.setSalescount(0);
		} else {
			gb = groupBuyService.selectByPrimaryKey(ID);
		}
		if (StringUtilsEX.ToInt(stock) < 0) {
			gb.setStock(-99);
		} else {
			gb.setStock(StringUtilsEX.ToInt(stock));
		}
		gb.setTitle(title.trim());
		gb.setContent(content);
		gb.setBuynotes(buynotes);
		gb.setCprice(StringUtilsEX.ToFloat(cprice));
		gb.setOprice(StringUtilsEX.ToFloat(oprice));
		gb.setDetaildesc(detaildesc);
		gb.setIsanytime(StringUtilsEX.toBooleanForOS(isanytime));
		gb.setIsbook(StringUtilsEX.toBooleanForOS(isbook));
		gb.setIsexpired(StringUtilsEX.toBooleanForOS(isexpired));
		gb.setListdesc(listdesc);
		gb.setType(StringUtilsEX.ToInt(type));
		gb.setValidityend(StringUtilsEX.ToDate(end));
		gb.setValiditystart(StringUtilsEX.ToDate(start));
		gb.setStatus(StringUtilsEX.ToInt(status));
		gb.setUsesite(useplatform);
		// 对数据做一次加密
		String haCode = gb.getBuynotes() + gb.getContent() + gb.getDetaildesc()
				+ gb.getListdesc() + gb.getTitle() + gb.getCprice()
				+ gb.getId() + gb.getOprice() + gb.getShopid() + gb.getStatus()
				+ gb.getStock() + gb.getType() + gb.getCreattime()
				+ gb.getDeltime() + gb.getIsanytime() + gb.getIsbook()
				+ gb.getIsdel() + gb.getIsexpired() + gb.getValidityend()
				+ gb.getValiditystart()+gb.getUsesite();
		gb.setHacode(MD5Util.encodeByMD5(haCode));
		return gb;
	}

	private GroupbuyWithBLOBs checkParams(String title, String cprice,
			String oprice, String start, String end, String type,
			String content, String buynotes, String isanytime, String isbook,
			String isexpired, String detaildesc, String listdesc, String stock,
			String status,String  useplatform,String id, ReusltItem item) throws Exception {
		GroupbuyWithBLOBs gb = new GroupbuyWithBLOBs();
		user = SessionState.GetCurrentUser();
		int ID = StringUtilsEX.ToInt(id);
		if (ID < 0) {
			item.setCode(-101);
			item.setDesc("团购ID参数错误，id：" + id);
			return null;
		}
		if (StringUtilsEX.ToFloat(cprice) <= 0.0f) {
			item.setCode(-102);
			item.setDesc("团购现价参数错误，现价：" + cprice);
			return null;
		}
		if (StringUtilsEX.ToFloat(oprice) <= 0.0f) {
			item.setCode(-103);
			item.setDesc("团购原价参数错误，原价：" + oprice);
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(start)) {
			item.setCode(-104);
			item.setDesc("团购开始时间不能为空");
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(end)) {
			item.setCode(-105);
			item.setDesc("团购结束时间不能为空");
			return null;
		}
		if (StringUtilsEX.ToInt(type) < 0) {
			item.setCode(-106);
			item.setDesc("团购类型参数错误，类型：" + type);
			return null;
		}
		if (StringUtilsEX.ToBooleanNull(isanytime) == null) {
			item.setCode(-107);
			item.setDesc("是否随时退参数错误");
			return null;
		}
		if (StringUtilsEX.ToBooleanNull(isbook) == null) {
			item.setCode(-108);
			item.setDesc("是否可预订参数错误");
			return null;
		}
		if (StringUtilsEX.ToBooleanNull(isexpired) == null) {
			item.setCode(-109);
			item.setDesc("是否随时退参数错误");
			return null;
		}
		if (StringUtilsEX.ToInt(status) < 0) {
			item.setCode(-110);
			item.setDesc("团购状态参数错误，类型：" + status);
			return null;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(useplatform)) {
			item.setCode(-117);
			item.setDesc("请选择使用平台");
			return null;
		}
		
		if (ID == 0) {
			gb.setCreattime(new Date());
			gb.setShopid(user.getShopid());
			gb.setIsdel(false);
			gb.setSalescount(0);
		} else {
			gb = groupBuyService.selectByPrimaryKey(ID);
		}
		if (StringUtilsEX.ToInt(stock) < 0) {
			gb.setStock(-99);
		} else {
			gb.setStock(StringUtilsEX.ToInt(stock));
		}
		gb.setTitle(title.trim());
		gb.setContent(content);
		gb.setBuynotes(buynotes);
		gb.setCprice(StringUtilsEX.ToFloat(cprice));
		gb.setOprice(StringUtilsEX.ToFloat(oprice));
		gb.setDetaildesc(detaildesc);
		/*gb.setIsanytime(StringUtilsEX.toBooleanForOS(isanytime));
		gb.setIsbook(StringUtilsEX.toBooleanForOS(isbook));
		gb.setIsexpired(StringUtilsEX.toBooleanForOS(isexpired));*/
		gb.setIsanytime(StringUtilsEX.ToBooleanNull(isanytime));
		gb.setIsbook(StringUtilsEX.ToBooleanNull(isbook));
		gb.setIsexpired(StringUtilsEX.ToBooleanNull(isexpired));
		gb.setListdesc(listdesc);
		gb.setType(StringUtilsEX.ToInt(type));
		gb.setValidityend(StringUtilsEX.ToDate(end));
		gb.setValiditystart(StringUtilsEX.ToDate(start));
		gb.setStatus(StringUtilsEX.ToInt(status));
		gb.setUsesite(useplatform);
		// 对数据做一次加密
		String haCode = gb.getBuynotes() + gb.getContent() + gb.getDetaildesc()
				+ gb.getListdesc() + gb.getTitle() + gb.getCprice()
				+ gb.getId() + gb.getOprice() + gb.getShopid() + gb.getStatus()
				+ gb.getStock() + gb.getType() + gb.getCreattime()
				+ gb.getDeltime() + gb.getIsanytime() + gb.getIsbook()
				+ gb.getIsdel() + gb.getIsexpired() + gb.getValidityend()
				+ gb.getValiditystart()+gb.getUsesite();
		gb.setHacode(MD5Util.encodeByMD5(haCode));
		return gb;
	}

	/**
	 * 删除团购
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public ReusltItem delete(String id) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("团购ID参数错误");
				return item;
			}
			if (groupBuyService.deleteGroup(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("删除团购成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"删除团购成功! id:{0},用户ID:{1}", id, user.getUserId()),
						"shopgroupbuy/delete");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除团购页面", "/seller/shopgroupbuy/delete", "删除团购");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加删除团购操作记录出错! 异常信息:",
    								e, "/seller/shopgroupbuy/delete");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("删除团购失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"删除团购失败! id:{0},用户ID:{1}", id, user.getUserId()),
						"shopgroupbuy/delete");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("删除团购异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"删除团购异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/delete");
		}
		return item;
	}

	/**
	 * 获取团购列表
	 * 
	 * @param title
	 * @param type
	 * @param startfrom
	 * @param startto
	 * @param endfrom
	 * @param endto
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getList")
	public ReusltItem getList(String title, String type, String startfrom,
			String startto, String endfrom, String endto, String page,
			String size) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			CriteriaGroupBuy cGroupBuy = new CriteriaGroupBuy();
			cGroupBuy.setShopid(user.getShopid());
			if (!StringUtilsEX.IsNullOrWhiteSpace(title)) {
				cGroupBuy.setName(title);
			}
			if (StringUtilsEX.ToInt(type) >= 0) {
				cGroupBuy.setType(StringUtilsEX.ToInt(type));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(startfrom)) {
				cGroupBuy.setStartFrom(StringUtilsEX.ToDate(startfrom));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(startto)) {
				cGroupBuy.setStartTo(StringUtilsEX.ToDate(startto));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endfrom)) {
				cGroupBuy.setEndFrom(StringUtilsEX.ToDate(endfrom));
			}
			if (!StringUtilsEX.IsNullOrWhiteSpace(endto)) {
				cGroupBuy.setEndTo(StringUtilsEX.ToDate(endto));
			}
			PageBean pageBean = groupBuyService.getListByPage(cGroupBuy,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取团购列表异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"获取团购列表异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/getList");
		}
		return item;
	}

	/**
	 * 根据团购ID获取图片信息
	 * 
	 * @param id
	 *            团购ID
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getImgList")
	public ReusltItem getImgList(String id, String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-102);
				item.setDesc("团购ID参数错误");
				return item;
			}
			CriteriaGroupBuy cGroupBuy = new CriteriaGroupBuy();
			cGroupBuy.setId(StringUtilsEX.ToInt(id));
			cGroupBuy.setOrderByClause("sort");
			cGroupBuy.setSort("desc");
			PageBean pageBean = advertImgService.getListByPage(cGroupBuy,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取团购图片列表异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"获取团购图片列表异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/getImgList");
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
	@RequestMapping("/addimg")
	public ReusltItem addImg(String img, String groupid, String sort,
			String status) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(groupid) <= 0) {
				item.setCode(-101);
				item.setDesc("团购ID参数错误");
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(img)) {
				item.setCode(-102);
				item.setDesc("团购图片不能为空");
				return item;
			}
			if (StringUtilsEX.ToInt(sort) <= 0) {
				item.setCode(-103);
				item.setDesc("团购图片排序参数错误");
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-103);
				item.setDesc("团购图片状态参数错误");
				return item;
			}
			AdvertImg adimg = new AdvertImg();
			adimg.setCreattime(new Date());
			adimg.setGroupbyid(StringUtilsEX.ToInt(groupid));
			adimg.setImgurl(img);
			adimg.setIsdel(false);
			adimg.setShopid(user.getShopid());
			adimg.setSort(StringUtilsEX.ToInt(sort));
			adimg.setStatus(StringUtilsEX.ToInt(status));
			adimg.setType(AdvertImgTypeEnum.团购.getValue()); // 团购图片
			if (advertImgService.insert(adimg) > 0) {
				item.setCode(0);
				item.setDesc("添加团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"添加团购图片成功! img:{0},团购ID:{1},用户ID:{2}", img, groupid,
						user.getUserId()), "shopgroupbuy/addImg");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "添加团购图片页面", "/seller/shopgroupbuy/addimg", "添加团购图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加添加团购图片操作记录出错! 异常信息:",
    								e, "/seller/shopgroupbuy/addimg");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("添加团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"添加团购图片成功! id:{0},团购ID:{1},用户ID:{2}", img, groupid,
						user.getUserId()), "shopgroupbuy/addImg");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("添加团购图片异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"添加团购图片异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/addImg");
		}
		return item;
	}

	/**
	 * 编辑图片
	 * 
	 * @param id
	 * @param img
	 * @param groupid
	 * @param sort
	 * @param status
	 * @return
	 */
	@RequestMapping("/editimg")
	public ReusltItem editImg(String id, String img, String groupid,
			String sort, String status) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("图片ID参数错误");
				return item;
			}
			if (StringUtilsEX.ToInt(groupid) <= 0) {
				item.setCode(-102);
				item.setDesc("团购ID参数错误");
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(img)) {
				item.setCode(-103);
				item.setDesc("团购图片不能为空");
				return item;
			}
			if (StringUtilsEX.ToInt(sort) <= 0) {
				item.setCode(-104);
				item.setDesc("团购图片排序参数错误");
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-105);
				item.setDesc("团购图片状态参数错误");
				return item;
			}
			AdvertImg adimg = advertImgService.selectByPrimaryKey(StringUtilsEX
					.ToInt(id));
			if (adimg == null) {
				item.setCode(-201);
				item.setDesc("根据图片ID未能检索到图片信息");
				return item;
			}
			adimg.setGroupbyid(StringUtilsEX.ToInt(groupid));
			adimg.setImgurl(img);
			adimg.setSort(StringUtilsEX.ToInt(sort));
			adimg.setStatus(StringUtilsEX.ToInt(status));
			if (advertImgService.updateByPrimaryKey(adimg) > 0) {
				item.setCode(0);
				item.setDesc("编辑团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"编辑团购图片成功! img:{0},ID:{1},用户ID:{2}", img, id,
						user.getUserId()), "shopgroupbuy/editImg");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "编辑团购图片页面", "/seller/shopgroupbuy/editImg", "编辑团购图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加编辑团购图片操作记录出错! 异常信息:",
    								e, "/seller/shopgroupbuy/editImg");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("编辑团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"编辑团购图片成功! id:{0},ID:{1},用户ID:{2}", img, id,
						user.getUserId()), "shopgroupbuy/editImg");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("编辑团购图片异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"编辑团购图片异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/editImg");
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
				item.setDesc("删除团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"删除团购图片成功! ID:{0},用户ID:{1}", id, user.getUserId()),
						"shopgroupbuy/delimg");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除团购图片页面", "/seller/shopgroupbuy/delimg", "删除团购图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加编辑团购图片操作记录出错! 异常信息:",
    								e, "/seller/shopgroupbuy/delimg");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("删除团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"删除团购图片成功! ID:{0},用户ID:{1}", id, user.getUserId()),
						"shopgroupbuy/delimg");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("删除团购图片异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"删除团购图片异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/delimg");
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
				item.setDesc("团购图片排序参数错误");
				return item;
			}
			if (advertImgService.updateOrder(StringUtilsEX.ToInt(sort),
					StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("更新图片排序成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"更新图片排序成功! ID:{0},用户ID:{1}", id, user.getUserId()),
						"shopgroupbuy/updateImgSort");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "更新图片排序页面", "/seller/shopgroupbuy/updateImgSort", "更新图片排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加更新图片排序操作记录出错! 异常信息:",
    								e, "/seller/shopgroupbuy/updateImgSort");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("更新图片排序失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"更新图片排序失败! ID:{0},用户ID:{1}", id, user.getUserId()),
						"shopgroupbuy/updateImgSort");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("更新图片排序异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"更新图片排序异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/updateImgSort");
		}
		return item;
	}

	/**
	 * 更新团购状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public ReusltItem updateStatus(String id, String status) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("ID参数错误");
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-102);
				item.setDesc("团购状态参数错误");
				return item;
			}
			if (groupBuyService.updateStatus(StringUtilsEX.ToInt(status),
					StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("更新状态成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"更新状态成功! ID:{0},用户ID:{1}", id, user.getUserId()),
						"shopgroupbuy/updateStatus");
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "更新团购状态页面", "/seller/shopgroupbuy/updateStatus", "更新团购状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加更新团购状态操作记录出错! 异常信息:",
    								e, "/seller/shopgroupbuy/updateStatus");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("更新状态失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"更新状态失败! ID:{0},用户ID:{1}", id, user.getUserId()),
						"shopgroupbuy/updateStatus");
			}
		} catch (Exception e) {
			item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("更新状态异常：" + e.toString());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,"更新状态异常! 异常信息:{0}", e,
					"/seller/shopgroupbuy/updateStatus");
		}
		return item;
	}
}
