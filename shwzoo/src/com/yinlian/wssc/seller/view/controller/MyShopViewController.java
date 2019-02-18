/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.seller.view.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yinlian.Enums.AdvertisingType;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.SessionUtil;

/**
 * 卖家的店铺的显示层
 * ShopViewController.java
 * @author Administrator
 * @version $Id: ShopViewController.java, v 0.1 2016年3月16日 上午11:42:22 Administrator Exp $
 */
@Controller
@RequestMapping("/seller/shop")
public class MyShopViewController {

    /**
     * 日志输出
     */
    private static final Logger logger = LoggerFactory.getLogger(MyShopViewController.class);
    @Autowired
    private UserService         userService;
    
    @Autowired
    private ShopService shopService;
    @Autowired
    private AdverisingService adverisingService;
    
    @Autowired
    private SpuService spuService;

    /**
     * 显示卖家 店铺的品牌页面
     * 
     * @param shopid
     * @param model
     * @return
     */
    @RequestMapping("/showBrandInfo")
    public String showBrandInfo(Integer shopid, HttpServletRequest request) {
        //model.addAttribute("shopid", 9); //写死了
        try {
            shopid = SessionUtil.getSessionUserShopId(request);
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("shopid", shopid);
        return "seller/myshop/brandInfo";
    }

    /**
     * 显示卖家添加 品牌的页面
     * 
     * @param shopid
     * @param model
     * @return
     */
    @RequestMapping("/showAddBrand")
    public String showAddBrand(Integer shopid, Model model) {
        model.addAttribute("shopid", shopid);
        return "seller/myshop/addBrand";
    }

    /**
     * 显示店铺的详细信息页面 
     * 
     * @param shopid
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping("/showShopInfo")
    public String showShopInfo(Integer shopid, Integer userid, HttpServletRequest request) {
        // model.addAttribute("shopid", 9); //店铺id写死
        //model.addAttribute("userid", 9); // 用户id写死
        try {
            shopid = SessionUtil.getSessionUserShopId(request);
            userid = SessionUtil.getSessionUserId(request);
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("shopid", shopid);
        request.setAttribute("userid", userid);
        return "seller/myshop/shopInfo";
    }

    /**
     * 显示公司的信息页面
     * 
     * @param shopid
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping("/showCompanyInfo")
    public String showCompanyInfo(Integer shopid, Integer userid, HttpServletRequest request) {
        try {
            shopid = SessionUtil.getSessionUserShopId(request);
            Shop shop=shopService.queryById(shopid);
            request.setAttribute("bankname", shop.getBankname());
            request.setAttribute("bankcardno", shop.getBankcardno());
            request.setAttribute("holdername", shop.getHodername());
            request.setAttribute("status", shop.getStatus());
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("shopid", shopid);
        return "seller/myshop/companyInfo";
    }

    /**
     * 显示店铺修改状态的页面
     * 
     * @param shopid
     * @param model
     * @return
     */
    @RequestMapping("/showShopStatusChange")
    public String showShopStatusChange(Integer shopid, HttpServletRequest request) {
        try {
            shopid = SessionUtil.getSessionUserShopId(request);
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("shopid", shopid);
        return "seller/myshop/shopStauChange";
    }

    /**
     * 显示店铺公告页面
     * 
     * @param shopid
     * @param model
     * @return
     */
    @RequestMapping("/showShopNotice")
    public String showShopNotice(Integer shopid, String editUser, HttpServletRequest request) {
        try {
            SessionUser sessionUser = SessionUtil.getSessionUser(request);
            shopid = sessionUser.getShopid();
            Integer userid = sessionUser.getUserId();
            request.setAttribute("editUser", sessionUser.getName());
        } catch (Exception e) {
            logger.error("", e);
        }
        request.setAttribute("shopid", shopid);

        request.setAttribute("cur", "ssc");
        request.setAttribute("dcur", "dsnc");
        return "seller/myshop/shopNotice";
    }

    /**
     * 显示店铺通告编辑页面
     * 
     * @param shopid
     * @param editUser
     * @param cur
     * @param dcur
     * @return
     */
    @RequestMapping("/showShopNoticeEdit")
    public String showShopNoticeEdit(String id, String shopid, String editUser, String cur,
                                     String dcur, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("shopid", shopid);
        model.addAttribute("editUser", editUser);
        model.addAttribute("cur", cur);
        model.addAttribute("dcur", dcur);
        return "seller/myshop/shopNoticeEdit";
    }
    
    /**
     * 广告管理
     * @return
     */
    @RequestMapping("/showAdvertImg")
    public String showAdvertImg(){
    	 return "seller/myshop/advertImg_list";
    }
    /**
     * 显示图片添加页面
     * @return
     */
    @RequestMapping("/showAdvertImgAdd")
    public String showFindRecordAdd(String annouid, HttpServletRequest request) {
        return "seller/myshop/advertImg_add";
    }

    /**
     * 编辑广告图片
     * 
     */
    @RequestMapping("/showeditAdvert")
    public String showeditAdvert(@RequestParam("id") Integer id, HttpServletRequest request) {
    	 Advertising advert = new Advertising();
         String spuname=null;
         String shopname=null;
         String topicname=null;
         try {
             advert = adverisingService.selectByPrimaryKey(id);
             Integer typeid=advert.getTypeid();
             Integer type=advert.getType();
             if(type==AdvertisingType.商品.getValue()){
             	Spu sspu=spuService.queryById(typeid);
             	if(sspu!=null){
             		spuname=sspu.getName();
             	}
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         request.setAttribute("advert", advert);
         request.setAttribute("spuname", spuname);
         request.setAttribute("shopname", shopname);
         request.setAttribute("topicname", topicname);
         return "seller/myshop/advertImg_edit";
    }
}
