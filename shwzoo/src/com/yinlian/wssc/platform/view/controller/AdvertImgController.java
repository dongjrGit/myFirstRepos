package com.yinlian.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techown.wssc.web.po.ScenicWithBLOBs;
import com.techown.wssc.web.po.ZooEditor;
import com.techown.wssc.web.service.ScenicService;
import com.techown.wssc.web.service.ZooEditorService;
import com.yinlian.Enums.AdvertisingType;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.PopupAdTypeEnum;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicService;

@Controller
@RequestMapping("/platform/advertimg")
public class AdvertImgController {
	private static final Logger logger = LoggerFactory.getLogger(AdvertImgController.class);
	@Autowired
	AdverisingService adverisingService;

	@Autowired
	private SpuService spuService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private ZooEditorService zooEditorService;

	@Autowired
	private ScenicService scenicService;

	/**
	 * 显示广告列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/showAdvertImg")
	public String showAdvertImg(String id, HttpServletRequest request) {

		return "platform/advertimg/advertImg_list";
	}

	/**
	 * 显示图片添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/showAdvertImgAdd")
	public String showFindRecordAdd(String annouid, HttpServletRequest request) {
		return "platform/advertimg/advertImg_add";
	}

	/**
	 * 编辑广告图片
	 * 
	 */
	@RequestMapping("/showeditAdvert")
	public String showeditAdvert(@RequestParam("id") Integer id, HttpServletRequest request) {
		Advertising advert = new Advertising();
		String spuname = null;
		String shopname = null;
		String topicname = null;
		try {
			advert = adverisingService.selectByPrimaryKey(id);
			Integer typeid = advert.getTypeid();
			Integer type = advert.getType();
			if (type == AdvertisingType.商品.getValue()) {

				Spu sspu = spuService.queryById(typeid);
				if (sspu != null) {
					spuname = sspu.getName();
				}

			} else if (type == AdvertisingType.店铺.getValue()) {

				Shop shop = shopService.queryById(typeid);
				if (shop != null) {
					shopname = shop.getName();
				}

			} else if (type == AdvertisingType.专题.getValue()) {

				Topic topic = topicService.queryById(typeid);
				if (topic != null) {
					topicname = topic.getTitle();
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		request.setAttribute("advert", advert);
		request.setAttribute("spuname", spuname);
		request.setAttribute("shopname", shopname);
		request.setAttribute("topicname", topicname);

		return "platform/advertimg/advertImg_edit";
	}

	@RequestMapping("/advertactivity")
	public String advertactivity(HttpServletRequest request) {
		Advertising advert = new Advertising();
		String spuname = null;
		String shopname = null;
		String topicname = null;
		try {
			advert = adverisingService.getByAdvertact(PageMarkType.抢购活动.getValue(), null);
			if (advert != null) {
				Integer typeid = advert.getTypeid();
				Integer type = advert.getType();
				if (type == AdvertisingType.商品.getValue()) {

					Spu sspu = spuService.queryById(typeid);
					if (sspu != null) {
						spuname = sspu.getName();
					}

				} else if (type == AdvertisingType.店铺.getValue()) {

					Shop shop = shopService.queryById(typeid);
					if (shop != null) {
						shopname = shop.getName();
					}

				} else if (type == AdvertisingType.专题.getValue()) {

					Topic topic = topicService.queryById(typeid);
					if (topic != null) {
						topicname = topic.getTitle();
					}
				}
			}
		} catch (Exception e) {
			advert = new Advertising();
			e.printStackTrace();
		}
		request.setAttribute("advert", advert);
		request.setAttribute("spuname", spuname);
		request.setAttribute("shopname", shopname);
		request.setAttribute("topicname", topicname);
		return "platform/advertimg/advertactivity";
	}

	/**
	 * 首页活动地址有advertactivity换成getPopupAd
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPopupAd")
	public String getPopupAd(HttpServletRequest request) {
		Advertising advert = new Advertising();
		String spuname = null;
		String shopname = null;
		String topicname = null;
		String scenicname = null;
		String editorTitle = null;
		try {
			advert = adverisingService.getByAdvertact(PageMarkType.抢购活动.getValue(), null);
			if (advert != null) {
				Integer typeid = advert.getTypeid();
				Integer type = advert.getType();
				if (type == PopupAdTypeEnum.商品.getValue()) {
					Spu sspu = spuService.queryById(typeid);
					if (sspu != null) {
						spuname = sspu.getName();
					}
				} else if (type == PopupAdTypeEnum.店铺.getValue()) {
					Shop shop = shopService.queryById(typeid);
					if (shop != null) {
						shopname = shop.getName();
					}
				} else if (type == PopupAdTypeEnum.景点.getValue()) {
					ScenicWithBLOBs scenic = scenicService.selectById(typeid);
					scenicname = scenic.getScenicname();
				} else if (type == PopupAdTypeEnum.图文编辑.getValue()) {
					ZooEditor editor = zooEditorService.getById(typeid, 1);
					if (null != editor) {
						editorTitle = editor.getTitle();
					}else{
						advert.setTypeid(null);
					}
				}
			}
		} catch (Exception e) {
			logger.error("getPopupAd error", e);
			request.setAttribute("message", "系统异常！");
			return "error";
		}
		request.setAttribute("advert", advert);
		request.setAttribute("spuname", spuname);
		request.setAttribute("shopname", shopname);
		request.setAttribute("topicname", topicname);
		request.setAttribute("scenicname", scenicname);
		request.setAttribute("editorTitle", editorTitle);
		return "platform/advertimg/advertactivity";
	}
}
