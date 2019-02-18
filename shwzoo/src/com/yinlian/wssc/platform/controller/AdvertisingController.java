package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.AdvertisingType;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.PopupAdTypeEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.AdvertTypeDto;
import com.yinlian.wssc.web.dto.PageMarkDTo;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.util.CriteriaAdvering;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 广告的 AdvertimgController.java
 * 
 * @author sssssssl.m
 * @version $Id: AdvertimgController.java, v 0.1 2016年4月13日 下午5:03:19
 *          Administrator Exp $
 */
@Controller
@RequestMapping("/platform/adverising")
public class AdvertisingController {
	private static final Logger logger = LoggerFactory.getLogger(AdvertisingController.class);

	@Autowired
	AdverisingService adverisingService;

	SessionUser user = null;
	@Autowired
	private OperaterecordsService operaterecordsService;

	/**
	 * 广告添加
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	public @ResponseBody ReusltItem edit(String imgurl, String url, String status, String title,
			String pagemark, String type, String spuid, String shopid, String topicid,
			String display, String id, String webset, String orderby) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(display) < 0) {
				item.setCode(-102);
				item.setDesc("请选择显示位置");
				return item;
			}
			if (StringUtilsEX.ToInt(webset) < 0) {
				item.setCode(-103);
				item.setDesc("请输正确的站点标识");
				return item;
			}
			if (StringUtils.isBlanck(imgurl)) {
				item.setCode(-105);
				item.setDesc("请上传图片");
				return item;
			}
			if (StringUtilsEX.ToInt(type) < 0) {
				item.setCode(-101);
				item.setDesc("请选择跳转类型");
				return item;
			}
			if (StringUtilsEX.ToInt(type) == AdvertisingType.店铺.getValue()) {
				if (StringUtilsEX.IsNullOrWhiteSpace(shopid)) {
					item.setCode(-101);
					item.setDesc("请填写店铺名称");
					return item;
				}
			} else {
				if (StringUtilsEX.IsNullOrWhiteSpace(spuid)) {
					item.setCode(-101);
					item.setDesc("请填写商品名称");
					return item;
				}
			}
			if (StringUtilsEX.ToInt(orderby) < 0) {
				orderby = "0";
			}
			Advertising advert = new Advertising();
			advert.setTitle(title);
			advert.setImag(imgurl);
			advert.setStatus(StringUtilsEX.ToInt(status));
			advert.setDisplay(StringUtilsEX.ToInt(display));
			advert.setOrderby(StringUtilsEX.ToInt(orderby));
			advert.setPagemark(StringUtilsEX.ToInt(pagemark));
			advert.setWebSet(StringUtilsEX.ToInt(webset));
			advert.setType(StringUtilsEX.ToInt(type));
			Integer _type = StringUtilsEX.ToInt(type);
			if (_type == AdvertisingType.外部链接.getValue()) {
				if (StringUtils.isBlanck(url)) {
					item.setCode(-105);
					item.setDesc("请填写链接地址");
					return item;
				}
				advert.setUrl(url);
				// if (StringUtilsEX.ToInt(webset)==WebSetEnum.app.getValue()) {
				// advert.setUrl("url://link|||"+url);
				// }
			} else if (_type == AdvertisingType.商品.getValue()) {
				advert.setTypeid(StringUtilsEX.ToInt(spuid));
				if (StringUtilsEX.ToInt(webset) == WebSetEnum.pc.getValue()) {
					advert.setUrl("/web/products/proinfo.html?spuid=" + spuid);
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.wap.getValue()) {
					advert.setUrl("/wap/products/ProDetail.html?spuid=" + spuid);
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.app.getValue()) {
					advert.setUrl("url://prodetailed|||" + spuid);
				}
			} else if (_type == AdvertisingType.店铺.getValue()) {
				advert.setTypeid(StringUtilsEX.ToInt(shopid));
				if (StringUtilsEX.ToInt(webset) == WebSetEnum.pc.getValue()) {
					advert.setUrl("/web/shop/details.html?shopId=" + shopid);
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.wap.getValue()) {
					advert.setUrl("/wap/shop/index.html?id=" + shopid + "&href=/wap/index.html");
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.app.getValue()) {
					advert.setUrl("url://shopdetail|||" + shopid + "&href=/wap/index.html");
				}
			} else if (_type == AdvertisingType.专题.getValue()) {
				advert.setTypeid(StringUtilsEX.ToInt(topicid));
				if (StringUtilsEX.ToInt(webset) == WebSetEnum.app.getValue()) {
					advert.setUrl("url://topicdetail|||" + topicid);
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.wap.getValue()) {
					advert.setUrl("/wap/gettopic.html?topicid=" + topicid);
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.pc.getValue()) {
					advert.setUrl("/web/products/topicprolist.html?topicid=" + topicid);
				}
			}
			if (StringUtils.isBlanck(id)) { // 添加
				advert.setShopid(user.getShopid());
				int result = adverisingService.insert(advert);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("添加成功");
					LogHandle.info(LogType.advertimg,
							MessageFormat.format("添加广告成功! ID:{0},状态:{1}", id, imgurl),
							"/platform/advertimg/edit");
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(
										OperateRecordsTypeEnum.添加.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(),
										user.getLoginName(), "advertImg_add.jsp",
										"/platform/advertimg/edit", "添加广告图片");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "添加广告操作记录出错! 异常信息:", e,
										"/platform/advertimg/edit");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("添加失败");
					LogHandle.info(LogType.advertimg,
							MessageFormat.format("添加广告失败! ID:{0},状态:{1}", id, status),
							"/platform/advertimg/edit");
				}
			} else {
				if (StringUtilsEX.ToInt(id) < 0) {
					item.setCode(-101);
					item.setDesc("参数id错误");
					return item;
				}
				advert.setId(StringUtilsEX.ToInt(id));
				int result = adverisingService.updateByPrimaryKey(advert);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("修改成功");
					LogHandle.info(LogType.advertimg,
							MessageFormat.format("修改广告成功! ID:{0},状态:{1}", id, status),
							"/platform/advertimg/edit");
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(
										OperateRecordsTypeEnum.修改.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(),
										user.getLoginName(), "advertImg_edit.jsp",
										"/platform/advertimg/edit", "修改广告图片");
							} catch (Exception e) {
								LogHandle.error(LogType.OperateRecords, "修改广告操作记录出错! 异常信息:", e,
										"/platform/advertimg/edit");
							}

						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("修改失败");
					LogHandle.info(LogType.advertimg,
							MessageFormat.format("修改广告失败! ID:{0},状态:{1}", id, status),
							"/platform/advertimg/edit");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("保存广告信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.advertimg, "保存广告信息信息出错! 异常信息:", e, "/platform/advertimg/edit");
		}

		return item;
	}

	/**
	 * 广告添加
	 * 
	 * @return
	 */
	@RequestMapping("/savePopupAd")
	public @ResponseBody ReusltItem savePopupAd(String imgurl, String url, String status,
			String title, String pagemark, String type, String spuid, String shopid, String topicid,
			String display, String id, String webset, String orderby, String scenicId,
			String editorId) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionState.GetCurrentUser();
			if (StringUtilsEX.ToInt(display) < 0) {
				item.setCode(-102);
				item.setDesc("请选择显示位置");
				return item;
			}
			if (StringUtilsEX.ToInt(webset) < 0) {
				item.setCode(-103);
				item.setDesc("请输正确的站点标识");
				return item;
			}
			if (StringUtils.isBlanck(imgurl)) {
				item.setCode(-105);
				item.setDesc("请上传图片");
				return item;
			}
			if (StringUtilsEX.ToInt(type) < 0) {
				item.setCode(-101);
				item.setDesc("请选择跳转类型");
				return item;
			}
			if (StringUtilsEX.ToInt(type) == PopupAdTypeEnum.店铺.getValue()) {
				if (StringUtilsEX.IsNullOrWhiteSpace(shopid)) {
					item.setCode(-101);
					item.setDesc("请填写店铺名称");
					return item;
				}
			} else if (StringUtilsEX.ToInt(type) == PopupAdTypeEnum.商品.getValue()) {
				if (StringUtilsEX.IsNullOrWhiteSpace(spuid)) {
					item.setCode(-101);
					item.setDesc("请填写商品名称");
					return item;
				}
			} else if (StringUtilsEX.ToInt(type) == PopupAdTypeEnum.图文编辑.getValue()) {
				if (StringUtilsEX.IsNullOrWhiteSpace(editorId)) {
					item.setCode(-101);
					item.setDesc("请填写图文标题");
					return item;
				}
			} else if (StringUtilsEX.ToInt(type) == PopupAdTypeEnum.景点.getValue()) {
				if (StringUtilsEX.IsNullOrWhiteSpace(scenicId)) {
					item.setCode(-101);
					item.setDesc("请填写景点设施");
					return item;
				}
			}
			if (StringUtilsEX.ToInt(orderby) < 0) {
				orderby = "0";
			}
			Advertising advert = new Advertising();
			advert.setTitle(title);
			advert.setImag(imgurl);
			advert.setStatus(StringUtilsEX.ToInt(status));
			advert.setDisplay(StringUtilsEX.ToInt(display));
			advert.setOrderby(StringUtilsEX.ToInt(orderby));
			advert.setPagemark(StringUtilsEX.ToInt(pagemark));
			advert.setWebSet(StringUtilsEX.ToInt(webset));
			advert.setType(StringUtilsEX.ToInt(type));
			Integer _type = StringUtilsEX.ToInt(type);
			if (_type == PopupAdTypeEnum.外部链接.getValue()) {
				if (StringUtils.isBlanck(url)) {
					item.setCode(-105);
					item.setDesc("请填写链接地址");
					return item;
				}
				advert.setUrl(url);
			} else if (_type == PopupAdTypeEnum.商品.getValue()) {
				advert.setTypeid(StringUtilsEX.ToInt(spuid));
				if (StringUtilsEX.ToInt(webset) == WebSetEnum.pc.getValue()) {
					advert.setUrl("/web/products/proinfo.html?spuid=" + spuid);
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.wap.getValue()) {
					advert.setUrl("/wap/products/ProDetail.html?spuid=" + spuid);
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.app.getValue()) {
					advert.setUrl("url://prodetailed|||" + spuid);
				}
			} else if (_type == PopupAdTypeEnum.店铺.getValue()) {
				advert.setTypeid(StringUtilsEX.ToInt(shopid));
				if (StringUtilsEX.ToInt(webset) == WebSetEnum.pc.getValue()) {
					advert.setUrl("/web/shop/details.html?shopId=" + shopid);
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.wap.getValue()) {
					advert.setUrl("/wap/shop/index.html?id=" + shopid + "&href=/wap/index.html");
				} else if (StringUtilsEX.ToInt(webset) == WebSetEnum.app.getValue()) {
					advert.setUrl("url://shopdetail|||" + shopid + "&href=/wap/index.html");
				}
			} else if (_type == PopupAdTypeEnum.景点.getValue()) {
				advert.setTypeid(StringUtilsEX.ToInt(scenicId));
				advert.setUrl(scenicId);
			} else if (_type == PopupAdTypeEnum.图文编辑.getValue()) {
				advert.setTypeid(StringUtilsEX.ToInt(editorId));
				advert.setUrl(editorId);
			}
			if (StringUtils.isBlanck(id)) { // 添加
				advert.setShopid(user.getShopid());
				int result = adverisingService.insert(advert);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("添加成功");
					logger.info("savePopupAd success id {}, status {}", id, status);
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(
										OperateRecordsTypeEnum.添加.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(),
										user.getLoginName(), "advertImg_add.jsp",
										"/platform/advertimg/edit", "添加广告图片");
							} catch (Exception e) {
								logger.error("add savePopupAd operate records error", e);
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("添加失败");
					logger.error(
							"add savePopupAd fail imgurl {}, url {}, status {}, title {}, pagemark {}, type {}, spuid {}, shopid {}, topicid {}, display {}, id {}, webset {}, orderby {}, scenicId {}, editorId {}",
							imgurl, url, status, title, pagemark, type, spuid, shopid, topicid,
							display, id, webset, orderby, scenicId, editorId);
				}
			} else {
				if (StringUtilsEX.ToInt(id) < 0) {
					item.setCode(-101);
					item.setDesc("参数id错误");
					return item;
				}
				advert.setId(StringUtilsEX.ToInt(id));
				int result = adverisingService.updateByPrimaryKey(advert);
				if (result > 0) {
					item.setCode(0);
					item.setDesc("修改成功");
					logger.info("update savePopupAd success id {}, status {}", id, status);
					// 异步操作 不影响正常流程
					ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
					cachedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							try {
								operaterecordsService.insertOperaterecords(
										OperateRecordsTypeEnum.修改.getValue(),
										OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(),
										user.getLoginName(), "advertImg_edit.jsp",
										"/platform/advertimg/edit", "修改广告图片");
							} catch (Exception e) {
								logger.error("update savePopupAd operate records error", e);
							}
						}
					});
				} else {
					item.setCode(-200);
					item.setDesc("修改失败");
					logger.error(
							"update savePopupAd fail imgurl {}, url {}, status {}, title {}, pagemark {}, type {}, spuid {}, shopid {}, topicid {}, display {}, id {}, webset {}, orderby {}, scenicId {}, editorId {}",
							imgurl, url, status, title, pagemark, type, spuid, shopid, topicid,
							display, id, webset, orderby, scenicId, editorId);
				}
			}
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("系统错误！");
			logger.error(
					"savePopupAd error imgurl {}, url {}, status {}, title {}, pagemark {}, type {}, spuid {}, shopid {}, topicid {}, display {}, id {}, webset {}, orderby {}, scenicId {}, editorId {}",
					imgurl, url, status, title, pagemark, type, spuid, shopid, topicid, display, id,
					webset, orderby, scenicId, editorId, e);
		}
		return item;
	}

	@RequestMapping("/getPageMark")
	public @ResponseBody ReusltItem getPageMark() {
		ReusltItem item = new ReusltItem();
		try {
			PageMarkType[] array = PageMarkType.values();

			List<PageMarkDTo> list = new ArrayList<PageMarkDTo>();
			/*for (int i = 0; i < array.length; i++) {
				PageMarkDTo dTo = new PageMarkDTo();
				dTo.setCode(PageMarkType.values()[i].getValue());
				dTo.setName(PageMarkType.values()[i].name());
			    list.add(dTo);
			}*/
			for (int i = 0; i < 4; i = i + 3) {
				PageMarkDTo dTo = new PageMarkDTo();
				dTo.setCode(PageMarkType.values()[i].getValue());
				dTo.setName(PageMarkType.values()[i].name());
				list.add(dTo);
			}
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("获取所有的页面标识异常：" + e.getMessage());
			LogHandle.error(LogType.advertimg,
					MessageFormat.format("获取所有的页面标识异常! 异常信息:{0}", e.getMessage()),
					"adverising/getPageMark");
		}
		return item;
	}

	/**
	 * 分页广告图片
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/queryAdvert")
	public @ResponseBody ReusltItem queryAdvert(String page, String size, String type,
			String status, String position) {
		ReusltItem item = new ReusltItem();
		if (StringUtilsEX.ToInt(page) <= 0) {
			item.setCode(-101);
			item.setDesc("分页参数错误：" + page);
			return item;
		}
		if (StringUtilsEX.ToInt(size) <= 0) {
			item.setCode(-102);
			item.setDesc("分页参数错误：" + size);
			return item;
		}
		if (!StringUtilsEX.IsNullOrWhiteSpace(position) && StringUtilsEX.ToInt(position) < 0) {
			item.setCode(-105);
			item.setDesc("显示位置错误：" + position);
			return item;
		}
		try {
			CriteriaAdvering criteria = new CriteriaAdvering();
			criteria.setType(StringUtilsEX.ToInt(type));
			criteria.setStatus(StringUtilsEX.ToInt(status));
			criteria.setDisplay(StringUtilsEX.ToInt(position));
			criteria.setOrderByClause("ID");
			criteria.setSort("desc");
			PageBean pageBean = adverisingService.getListByPage(criteria, StringUtilsEX.ToInt(page),
					StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pageBean.getBeanList());
			item.setMaxRow(pageBean.getTr());
			item.setPageIndex(pageBean.getPc());
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("查询分页广告图片出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.advertimg, "查询分页广告图片出错! 异常信息:", e,
					"/platform/adverising/queryAdvert");

		}
		return item;
	}

	/**
	 * 根据id删除图片
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAdvertById")
	public @ResponseBody ReusltItem deleteAdvertById(String id, HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		if (StringUtilsEX.ToInt(id) < 0) {
			item.setCode(-101);
			item.setDesc("参数id错误");
			return item;
		}
		try {
			user = SessionUtil.getSessionUser(request);
			int result = adverisingService.deleteAdvertising(StringUtilsEX.ToInt(id));
			if (result > 0) {
				item.setCode(0);
				item.setDesc("删除成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(
									OperateRecordsTypeEnum.删除.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(),
									user.getLoginName(), "advertImg_list.jsp",
									"/platform/advertimg/deleteAdvertById", "根据id删除图片");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "根据id删除图片操作记录出错! 异常信息:", e,
									"/platform/advertimg/deleteAdvertById");
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
				item.setDesc("根据id删除图片出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.advertimg, "根据id删除图片出错! 异常信息:", e,
					"/platform/advertimg/deleteAdvertById");

		}
		return item;
	}

	/**
	 * 禁用/启用
	 * 
	 * @param status
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public @ResponseBody ReusltItem updateStatus(String status, String id,
			HttpServletRequest request) {
		ReusltItem item = new ReusltItem();
		try {
			user = SessionUtil.getSessionUser(request);
			Integer ID = StringUtilsEX.ToInt(id);
			if (id == null || ID < 0) {
				item.setCode(-101);
				item.setDesc("广告ID参数错误,id:" + id);
				return item;
			}
			Integer ss = StringUtilsEX.ToInt(status);
			if (status == null || ss < 0) {
				item.setCode(-102);
				item.setDesc("广告状态参数错误,orderby:" + status);
				return item;
			}
			if (adverisingService.updateStatus(ss, ID) > 0) {
				LogHandle.info(LogType.advertimg,
						MessageFormat.format("修改广告状态成功! ID:{0},状态:{1}", id, status));
				item.setCode(0);
				item.setDesc("修改广告状态成功");
				// 异步操作 不影响正常流程
				ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
				cachedThreadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							operaterecordsService.insertOperaterecords(
									OperateRecordsTypeEnum.修改.getValue(),
									OperateRecordsFromEnum.系统后台.getValue(), user.getUserId(),
									user.getLoginName(), "advertImg_list.jsp",
									"/platform/advertimg/updateStatus", "修改广告状态");
						} catch (Exception e) {
							LogHandle.error(LogType.OperateRecords, "修改广告状态操作记录出错! 异常信息:", e,
									"/platform/advertimg/updateStatus");
						}

					}
				});
			} else {
				LogHandle.info(LogType.advertimg,
						MessageFormat.format("修改广告状态失败! ID:{0},状态:{1}", id, status));
				item.setCode(-200);
				item.setDesc("修改广告状态失败");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("修改广告状态出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.advertimg, "修改广告状态出错! 异常信息:", e,
					"/platform/advertimg/updateStatus");

		}
		return item;
	}

	/**
	 * 得到广告的所有跳转类型
	 * 
	 * @return
	 */
	@RequestMapping("/getAdvertType")
	public @ResponseBody ReusltItem getAdvertType() {
		ReusltItem item = new ReusltItem();
		try {
			AdvertisingType[] array = AdvertisingType.values();

			List<AdvertTypeDto> list = new ArrayList<AdvertTypeDto>();
			/*for (int i = 0; i < array.length; i++) {
				AdvertTypeDto dTo = new AdvertTypeDto();
				dTo.setCode(AdvertisingType.values()[i].getValue());
				dTo.setName(AdvertisingType.values()[i].name());
			    list.add(dTo);
			}*/
			for (int i = 1; i < 3; i++) {
				AdvertTypeDto dTo = new AdvertTypeDto();
				dTo.setCode(AdvertisingType.values()[i].getValue());
				dTo.setName(AdvertisingType.values()[i].name());
				list.add(dTo);
			}
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("保存广告信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.advertimg, "保存广告信息信息出错! 异常信息:", e, "/platform/advertimg/edit");
		}
		return item;
	}

	/**
	 * 得到广告的所有跳转类型
	 * 
	 * @return
	 */
	@RequestMapping("/getPopupAdType")
	public @ResponseBody ReusltItem getPopupAdType() {
		ReusltItem item = new ReusltItem();
		try {
			List<AdvertTypeDto> list = new ArrayList<AdvertTypeDto>();
			for (int i = 1; i < PopupAdTypeEnum.values().length; i++) {
				AdvertTypeDto dTo = new AdvertTypeDto();
				dTo.setCode(PopupAdTypeEnum.values()[i].getValue());
				dTo.setName(PopupAdTypeEnum.values()[i].name());
				list.add(dTo);
			}
			item.setCode(0);
			item.setData(list);
		} catch (Exception e) {
			item.setCode(-900);
			item.setDesc("系统错误！");
			logger.error("getPopupAdType error", e);
		}
		return item;
	}
}
