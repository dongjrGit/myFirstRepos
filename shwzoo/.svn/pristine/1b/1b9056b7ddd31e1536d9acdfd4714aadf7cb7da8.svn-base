package com.yinlian.wssc.seller.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Menus;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.service.MenuService;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yl.soft.log.LogHandle;

@Controller
public class Login_SellerController {
	
	   @Autowired
	    private MenuService        menuService;
	   
	    @Autowired
	    private MessageService     messageService;

	    @Autowired
	    private UsercapitalService usercapitalService;
	    
	    @Autowired
	    private ShopService        shopService;
	   /**
	     * 显示卖家主页面
	     * 
	     * @param model
	     * @return
	     */
	    @RequestMapping("/seller/index")
	    public String indexSeller(Model model, HttpServletRequest request) {
	        List<Menus> menus = new ArrayList<Menus>();
            SessionUser sessionUser = SessionState.GetCurrentUser();
	        try {
	        	 Integer userid = sessionUser.getUserId();
	        	  if (userid != null) {
	        		 String loginname = sessionUser.getLoginName();
	        		 Shop shop= shopService.queryById(sessionUser.getShopid());
	        		 if(loginname != null && shop.getUserid().equals(userid)){
	        			 menus = menuService.queryAllSellerMenus(0);
	        		 }else{
	        			 List<Integer> rigths = sessionUser.getRights();
	                      menus = menuService.queryByRrigth(rigths);
	        		 }	
	        		  request.setAttribute("list", menus);
	                  request.setAttribute("name", loginname);
	                  request.setAttribute("userId", userid);
	                  Integer count= messageService.getCount(userid);
	  	              request.setAttribute("count", count);
	              }	            
	            
	        } catch (Exception e) {
	            LogHandle.error(LogType.SellerShopManagement,
	                "获取卖家的 菜单集合错误，详情：", e, "/seller/index");

	        }
	        return "seller/index";
	    }
	    
	    @RequestMapping("/seller/messagelist")
	    public String messageSeller(Model model, HttpServletRequest request) {
	        List<Menus> menus = null;
	        try {
	            //获取卖家的 菜单集合
	            menus = menuService.queryAllSellerMenus(0);
	            model.addAttribute("list", menus);
	            SessionUser sessionUser = SessionUtil.getSessionUser(request);
	            String loginName = sessionUser.getLoginName();
	            request.setAttribute("name", loginName);
	            Integer userid = sessionUser.getUserId();
	            request.setAttribute("userId", userid);
	            Integer count= messageService.getCount(userid);
//	            List<Messages> list = messageService.queryByUserId(userid);
//	            Integer count = 0;
//	            if (list != null && list.size() > 0) {
//	                count = list.size();
//	            }
	            request.setAttribute("count", count);
	        } catch (Exception e) {
	            LogHandle.error(LogType.SellerShopManagement,
	                "获取卖家的 菜单集合错误，详情：", e, "/seller/messagelist");

	        }
	        return "seller/messagelist";
	    }
	    
	    /**
	     * 显示卖家左边的页面
	     * 
	     * @return
	     */
	    @RequestMapping("/seller/letf")
	    public String letf() {

	        return "seller/partialLeftside";
	    }
	    
	    /**
	     * 注册成功后结果页
	     * @return
	     */
	    @RequestMapping("/seller/regsuccess")
	    public String audit(){
	    	return "seller/user/reg_success";
	    }
	    

	    /**
	     * 显示卖家首页
	     * 
	     * @return
	     */
	    @RequestMapping("/seller/home")
	    public String home(HttpServletRequest request) {
	        Integer userid;boolean shopowned=false;
	        try {
	            SessionUser sessionUser = SessionUtil.getSessionUser(request);
	            String loginName = sessionUser.getLoginName();
	            request.setAttribute("name", loginName);
	            userid = sessionUser.getUserId();
	            request.setAttribute("userId", userid);
	            Shop shop = shopService.queryById(sessionUser.getShopid());
	            if (shop != null) {
	                request.setAttribute("shopname", shop.getName());
	                request.setAttribute("img", shop.getImgurl());
	                if(shop.getUserid().equals(userid)){
	                	shopowned=true;
	                }
	            }
	            Usercapital usercapital =new Usercapital();
	            if(shopowned==true){
	            	usercapital = usercapitalService.queryByUserId(userid);
	            }
	            request.setAttribute("shopowned", shopowned);
	            if (usercapital != null) {
	                request.setAttribute("balance", usercapital.getBalances());
	                request.setAttribute("freezeMonkey", usercapital.getFreezemoneys());
	                request.setAttribute("bond", usercapital.getBond());
	            } else {
	                request.setAttribute("balance", 0.00);
	                request.setAttribute("freezeMonkey", 0.00);
	                request.setAttribute("bond", 0.00);
	            }

	        } catch (Exception e) {
	            LogHandle.error(LogType.SellerShopManagement,
	                "获取卖家的 菜单集合错误，详情：{0}", e, "/seller/home");
	        }

	        return "seller/homePage";
	    }
	    /**
	     * 根据父id查询子菜单
	     * 
	     * @param fatherid
	     * @return
	     */
	    @RequestMapping("/seller/queryByFatherId")
	    public @ResponseBody ReusltItem queryByFatherId(Integer fathreid) {
	        ReusltItem item = new ReusltItem();
	        try {
	            List<Menus> menus = menuService.queryAllSellerMenus(fathreid);
	            item.setCode(0);
	            item.setData(menus);
	        } catch (Exception e) {
	            LogHandle.error(LogType.Menu, "根据父ID获取子菜单列表异常：", e,
	                "/seller/queryByFatherId");
	            item.setCode(-900);
	            item.setDesc("异常：" + e.getMessage());
	        }
	        return item;
	    }
	    
	    
}
