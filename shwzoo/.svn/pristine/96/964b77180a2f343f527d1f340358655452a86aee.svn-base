package com.yinlian.wssc.platform.controller;

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
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 团购
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/platform/groupbuy")
public class GroupBuyController {

	@Autowired
	private GroupBuyService groupBuyService;
	@Autowired
	private AdvertImgService advertImgService;
	
	SessionUser user = null;
	@Autowired
    private OperaterecordsService operaterecordsService ;

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
	 * @param status
	 * @return
	 */
	@RequestMapping("/add")
	public ReusltItem add(String title, String cprice, String oprice,
			String starttime, String endtime, String type, String content,
			String buynotes, String isanytime, String isbook, String isexpired,
			String detaildesc, String listdesc, String stock,String status,String useplatform ) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			GroupbuyWithBLOBs gBloBs = new GroupbuyWithBLOBs();
			gBloBs = checkParam(title, cprice, oprice, starttime, endtime, type,
					content, buynotes, isanytime, isbook, isexpired,
					detaildesc, listdesc, stock,status,useplatform, "0", item);
			if (item.getCode() < 0) {
				return item;
			}
			if (groupBuyService.insert(gBloBs) > 0) {
				item.setCode(0);
				item.setDesc("添加团购成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"添加团购成功! 标题:{0},用户ID:{1}", title, user.getUserId()),
						"/platform/groupbuy/add");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_GroupBuyAdd.jsp", "/platform/groupbuy/add", "添加团购");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加团购操作记录出错! 异常信息:",
    								e, "/platform/groupbuy/add");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("添加团购失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"添加团购失败! 标题:{0},用户ID:{1}", title, user.getUserId()),
						"/platform/groupbuy/add");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("添加团购异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,
					"添加团购异常! 异常信息:", e,
					"/platform/groupbuy/add");
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
			String isexpired, String detaildesc, String listdesc, String stock,String status,String useplatform) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			GroupbuyWithBLOBs gBloBs = new GroupbuyWithBLOBs();
			gBloBs = checkParam(title, cprice, oprice, starttime, endtime, type,
					content, buynotes, isanytime, isbook, isexpired,
					detaildesc, listdesc, stock,status,useplatform,id, item);
			if (item.getCode() < 0) {
				return item;
			}
			if (groupBuyService.updateByPrimaryKeyWithBLOBs(gBloBs) > 0) {
				item.setCode(0);
				item.setDesc("编辑团购成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"编辑团购成功! id:{0},标题:{1},用户ID:{2}", id, title,
						user.getUserId()), "/platform/groupbuy/update");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_GroupBuyEdit.jsp", "/platform/groupbuy/update", "修改团购");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改团购操作记录出错! 异常信息:",
    								e, "/platform/groupbuy/update");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("编辑团购失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"编辑团购失败! id:{0},标题:{1},用户ID:{2}", id, title,
						user.getUserId()), "/platform/groupbuy/update");
			}

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("编辑团购异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,
					"编辑团购异常! 异常信息:", e,
					"/platform/groupbuy/add");
		}
		return item;
	}

	private GroupbuyWithBLOBs checkParam(String title, String cprice,
			String oprice, String start, String end, String type,
			String content, String buynotes, String isanytime, String isbook,
			String isexpired, String detaildesc, String listdesc, String stock,String status,String useplatform,
			String id, ReusltItem item) throws Exception {
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
		}else{
			gb.setStock(StringUtilsEX.ToInt(stock));
		}
		gb.setTitle(title.trim());
		gb.setContent(content);
		gb.setBuynotes(buynotes);
		gb.setCprice(StringUtilsEX.ToFloat(cprice));
		gb.setOprice(StringUtilsEX.ToFloat(oprice));
		gb.setDetaildesc(detaildesc);
		gb.setIsanytime(StringUtilsEX.ToBoolean(isanytime));
		gb.setIsbook(StringUtilsEX.ToBoolean(isbook));
		gb.setIsexpired(StringUtilsEX.ToBoolean(isexpired));
		gb.setListdesc(listdesc);
		gb.setType(StringUtilsEX.ToInt(type));
		gb.setValidityend(StringUtilsEX.ToDate(end));
		gb.setValiditystart(StringUtilsEX.ToDate(start));
		gb.setStatus(StringUtilsEX.ToInt(status));
		gb.setUsesite(useplatform);
		//对数据做一次加密
		String haCode=gb.getBuynotes()+gb.getContent()+gb.getDetaildesc()+gb.getListdesc()
				+gb.getTitle()+gb.getCprice()+gb.getId()+gb.getOprice()+gb.getShopid()
				+gb.getStatus()+gb.getStock()+gb.getType()+gb.getCreattime()+gb.getDeltime()
				+gb.getIsanytime()+gb.getIsbook()+gb.getIsdel()+gb.getIsexpired()+gb.getValidityend()+gb.getValiditystart()+gb.getUsesite();		
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
						"/platform/groupbuy/delete");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_GroupBuyList.jsp", "/platform/groupbuy/delete", "删除团购");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除团购操作记录出错! 异常信息:",
    								e, "/platform/groupbuy/delete");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("删除团购失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"删除团购失败! id:{0},用户ID:{1}", id, user.getUserId()),
						"/platform/groupbuy/delete");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("删除团购异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,
					"删除团购异常! 异常信息:", e,
					"/platform/groupbuy/delete");
		}
		return item;
	}

	/**
	 * 获取团购列表
	 * @param title
	 * @param type
	 * @param shopid
	 * @param startfrom
	 * @param startto
	 * @param endfrom
	 * @param endto
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getList")
	public ReusltItem getList(String title, String type, String shopid,
			String startfrom, String startto, String endfrom, String endto,
			String page, String size) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			CriteriaGroupBuy cGroupBuy = new CriteriaGroupBuy();
			if (!StringUtilsEX.IsNullOrWhiteSpace(title)) {
				cGroupBuy.setName(title);
			}
			if (StringUtilsEX.ToInt(shopid) > 0) {
				cGroupBuy.setShopid(StringUtilsEX.ToInt(shopid));
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
			PageBean pageBean=groupBuyService.getListByPage(cGroupBuy, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取团购列表异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,
					"获取团购列表异常! 异常信息:", e,
					"/platform/groupbuy/getList");
		}
		return item;
	}
	
	/**
	 * 根据团购ID获取图片信息
	 * @param id  团购ID
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getImgList")
	public ReusltItem getImgList(String id,String page,String size){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item;
			}
			if(StringUtilsEX.ToInt(id) <= 0){
				item.setCode(-102);
				item.setDesc("团购ID参数错误");
				return item;
			}
			CriteriaGroupBuy cGroupBuy = new CriteriaGroupBuy();
			cGroupBuy.setId(StringUtilsEX.ToInt(id));
			cGroupBuy.setOrderByClause("sort");
			cGroupBuy.setSort("desc");
			PageBean pageBean=advertImgService.getListByPage(cGroupBuy, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取团购图片列表异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.Activity,
					"获取团购图片列表异常! 异常信息:", e,
					"/platform/groupbuy/getImgList");
		}
		return item;
	}

	/**
	 * 添加图片
	 * @param img
	 * @param groupid
	 * @param sort
	 * @param status
	 * @return
	 */
	@RequestMapping("/addImg")
	public ReusltItem addImg(String img,String groupid,String sort,String status){
		ReusltItem item = new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(groupid)<=0){
				item.setCode(-101);
				item.setDesc("团购ID参数错误");
				return item;
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(img)){
				item.setCode(-102);
				item.setDesc("团购图片不能为空");
				return item;
			}
			if(StringUtilsEX.ToInt(sort)<=0){
				item.setCode(-103);
				item.setDesc("团购图片排序参数错误");
				return item;
			}
			if(StringUtilsEX.ToInt(status)<0){
				item.setCode(-103);
				item.setDesc("团购图片状态参数错误");
				return item;
			}
			AdvertImg adimg=new AdvertImg();
			adimg.setCreattime(new Date());
			adimg.setGroupbyid(StringUtilsEX.ToInt(groupid));
			adimg.setImgurl(img);
			adimg.setIsdel(false);
			adimg.setShopid(user.getShopid());
			adimg.setSort(StringUtilsEX.ToInt(sort));
			adimg.setStatus(StringUtilsEX.ToInt(status));
			adimg.setType(AdvertImgTypeEnum.团购.getValue());    //团购图片
			if(advertImgService.insert(adimg)>0){
				item.setCode(0);
				item.setDesc("添加团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"添加团购图片成功! img:{0},团购ID:{1},用户ID:{2}", img,groupid, user.getUserId()),
						"/platform/groupbuy/addImg");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_GroupBuyImgEdit.jsp", "/platform/groupbuy/addImg", "添加图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加图片操作记录出错! 异常信息:",
    								e, "/platform/groupbuy/addImg");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("添加团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"添加团购图片成功! id:{0},团购ID:{1},用户ID:{2}",img,groupid, user.getUserId()),
						"/platform/groupbuy/addImg");
			}
		}
		 catch (Exception e) {
			 item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("添加团购图片异常：" + e.getMessage());
				} else {
						item.setDesc("系统错误！");
				}
				LogHandle.error(LogType.Activity,
						"添加团购图片异常! 异常信息:", e,
						"/platform/groupbuy/addImg");
			}
		return item;
	}

	/**
	 * 编辑图片
	 * @param id
	 * @param img
	 * @param groupid
	 * @param sort
	 * @param status
	 * @return
	 */
	@RequestMapping("/editImg")
	public ReusltItem editImg(String id,String img,String groupid,String sort,String status){
		ReusltItem item = new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("图片ID参数错误");
				return item;
			}
			if(StringUtilsEX.ToInt(groupid)<=0){
				item.setCode(-102);
				item.setDesc("团购ID参数错误");
				return item;
			}
			if(StringUtilsEX.IsNullOrWhiteSpace(img)){
				item.setCode(-103);
				item.setDesc("团购图片不能为空");
				return item;
			}
			if(StringUtilsEX.ToInt(sort)<=0){
				item.setCode(-104);
				item.setDesc("团购图片排序参数错误");
				return item;
			}
			if(StringUtilsEX.ToInt(status)<0){
				item.setCode(-105);
				item.setDesc("团购图片状态参数错误");
				return item;
			}
			AdvertImg adimg=advertImgService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
			if(adimg==null){
				item.setCode(-201);
				item.setDesc("根据图片ID未能检索到图片信息");
				return item;
			}
			adimg.setGroupbyid(StringUtilsEX.ToInt(groupid));
			adimg.setImgurl(img);
			adimg.setSort(StringUtilsEX.ToInt(sort));
			adimg.setStatus(StringUtilsEX.ToInt(status));
			if(advertImgService.updateByPrimaryKey(adimg)>0){
				item.setCode(0);
				item.setDesc("编辑团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"编辑团购图片成功! img:{0},ID:{1},用户ID:{2}", img,id, user.getUserId()),
						"/platform/groupbuy/editImg");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_GroupBuyImgEdit.jsp", "/platform/groupbuy/editImg", "修改图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改图片操作记录出错! 异常信息:",
    								e, "/platform/groupbuy/editImg");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("编辑团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"编辑团购图片成功! id:{0},ID:{1},用户ID:{2}",img,id, user.getUserId()),
						"/platform/groupbuy/editImg");
			}
		}
		 catch (Exception e) {
			 item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("编辑团购图片异常：" + e.getMessage());
				} else {
						item.setDesc("系统错误！");
				}
				LogHandle.error(LogType.Activity,
						"编辑团购图片异常! 异常信息:", e,
						"/platform/groupbuy/editImg");
			}
		return item;
	}

	/**
	 * 删除图片
	 * @param id
	 * @return
	 */
	@RequestMapping("/delimg")
	public ReusltItem delimg(String id){
		ReusltItem item = new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("图片ID参数错误");
				return item;
			}
			if(advertImgService.deleteAdvert(StringUtilsEX.ToInt(id))>0){
				item.setCode(0);
				item.setDesc("删除团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"删除团购图片成功! ID:{0},用户ID:{1}",id, user.getUserId()),
						"/platform/groupbuy/delimg");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_GroupBuyImgList.jsp", "/platform/groupbuy/delimg", "删除图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"删除图片操作记录出错! 异常信息:",
    								e, "/platform/groupbuy/delimg");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("删除团购图片成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"删除团购图片成功! ID:{0},用户ID:{1}",id, user.getUserId()),
						"/platform/groupbuy/delimg");
			}
		}
		catch (Exception e) {
			 item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("删除团购图片异常：" + e.getMessage());
				} else {
						item.setDesc("系统错误！");
				}
			LogHandle.error(LogType.Activity,
					"删除团购图片异常! 异常信息:", e,
					"/platform/groupbuy/delimg");
		}
		return item;
	}
	/**
	 * 更新图片排序
	 * @param id
	 * @param sort
	 * @return
	 */
	@RequestMapping("/updateImgSort")
	public ReusltItem updateImgSort(String id,String sort){
		ReusltItem item = new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("图片ID参数错误");
				return item;
			}	
			if(StringUtilsEX.ToInt(sort)<=0){
				item.setCode(-102);
				item.setDesc("团购图片排序参数错误");
				return item;
			}
			if(advertImgService.updateOrder(StringUtilsEX.ToInt(sort), StringUtilsEX.ToInt(id))>0){
				item.setCode(0);
				item.setDesc("更新图片排序成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"更新图片排序成功! ID:{0},用户ID:{1}", id, user.getUserId()),
						"/platform/groupbuy/updateImgSort");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_GroupBuyImgList.jsp", "/platform/groupbuy/updateImgSort", "修改图片排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改图片排序操作记录出错! 异常信息:",
    								e, "/platform/groupbuy/updateImgSort");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("更新图片排序失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"更新图片排序失败! ID:{0},用户ID:{1}",id, user.getUserId()),
						"/platform/groupbuy/updateImgSort");
			}
		}
		 catch (Exception e) {
			 item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("更新图片排序异常：" + e.getMessage());
				} else {
						item.setDesc("系统错误！");
				}
				LogHandle.error(LogType.Activity,
						"更新图片排序异常! 异常信息:", e,
						"/platform/groupbuy/updateImgSort");
			}
		return item;
	}
	/**
	 * 更新团购状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public ReusltItem updateStatus(String id,String status){
		ReusltItem item = new ReusltItem();
		try{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(id)<=0){
				item.setCode(-101);
				item.setDesc("ID参数错误");
				return item;
			}	
			if(StringUtilsEX.ToInt(status)<0){
				item.setCode(-102);
				item.setDesc("团购状态参数错误");
				return item;
			}
			if(groupBuyService.updateStatus(StringUtilsEX.ToInt(status), StringUtilsEX.ToInt(id))>0){
				item.setCode(0);
				item.setDesc("更新状态成功!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"更新状态成功! ID:{0},用户ID:{1}", id, user.getUserId()),
						"/platform/groupbuy/updateStatus");
				//异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "yxgl_GroupBuyList.jsp", "/platform/groupbuy/updateStatus", "修改团购状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"修改团购状态操作记录出错! 异常信息:",
    								e, "/platform/groupbuy/updateStatus");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("更新状态失败!");
				LogHandle.info(LogType.Activity, MessageFormat.format(
						"更新状态失败! ID:{0},用户ID:{1}",id, user.getUserId()),
						"/platform/groupbuy/updateStatus");
			}
		}
		 catch (Exception e) {
			 item.setCode(-900);
				if (DebugConfig.BLUETOOTH_DEBUG) {
					item.setDesc("更新状态异常：" + e.getMessage());
				} else {
						item.setDesc("系统错误！");
				}
				LogHandle.error(LogType.Activity,
						"更新状态异常! 异常信息:", e,
						"/platform/groupbuy/updateStatus");
			}
		return item;
	}
}
