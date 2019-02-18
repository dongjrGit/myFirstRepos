package com.techown.wssc.platform.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techown.Enums.BannerType;
import com.techown.wssc.web.po.ScenicWithBLOBs;
import com.techown.wssc.web.po.ZooBanner;
import com.techown.wssc.web.po.ZooEditor;
import com.techown.wssc.web.service.ScenicService;
import com.techown.wssc.web.service.ZooBannerService;
import com.techown.wssc.web.service.ZooEditorService;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;

@Controller
@RequestMapping("/zoo/banner")
public class ZooBannerViewController {
	private static Logger logger = LoggerFactory.getLogger(ZooBannerViewController.class);
	@Autowired
	private ZooBannerService zooBannerService;
	@Autowired
	private SpuService spuService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ScenicService scenicService;
	@Autowired
	private ZooEditorService zooEditorService;

	/**
	 * 跳转列表页
	 * 
	 * @param request
	 * @param type
	 * @param state
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		return "platform/zoo/bannerList";
	}

	/**
	 * 跳转添加和编辑页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/listedit")
	public String listedit(Integer id, HttpServletRequest request) {
		logger.info("zoobanner listedit id {}", id);
		try {
			if (null != id) {
				String spuname = "";
				String shopname = "";
				String scenicname = "";
				String editorTitle="";
				ZooBanner bean = zooBannerService.selectById(id);
				Integer typeid = bean.getTypeId();
				Integer type = bean.getType();
				if (type == BannerType.PRODUCT.getCode()) {
					Spu sspu = spuService.queryById(typeid);
					if (sspu != null) {
						spuname = sspu.getName();
					}
				} else if (type == BannerType.SHOP.getCode()) {
					Shop shop = shopService.queryById(typeid);
					if (shop != null) {
						shopname = shop.getName();
					}
				} else if (type == BannerType.SCENIC.getCode()) {
					ScenicWithBLOBs scenic = scenicService.selectById(typeid);
					scenicname = scenic.getScenicname();
				} else if(type == BannerType.EDITOR.getCode()){
				    ZooEditor editor = zooEditorService.getById(typeid);
				    editorTitle = editor.getTitle();
				}
				request.setAttribute("bean", bean);
				request.setAttribute("spuname", spuname);
				request.setAttribute("shopname", shopname);
				request.setAttribute("scenicname", scenicname);
				request.setAttribute("editorTitle", editorTitle);
			}
		} catch (Exception e) {
			logger.error("zoobanner listedit id {} !\r\n exception", id, e);
		}
		return "/platform/zoo/bannerEdit";
	}
}
