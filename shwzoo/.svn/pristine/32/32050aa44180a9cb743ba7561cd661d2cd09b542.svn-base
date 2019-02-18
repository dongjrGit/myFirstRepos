package com.yinlian.wssc.seller.view.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.ShopCategoryDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.CircleService;
import com.yinlian.wssc.web.service.MenuService;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.ShopcategoryService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.JsonUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@Controller
@RequestMapping("/seller")
public class DlzcController {
    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(DlzcController.class);

    @Autowired
    private AccountsService     accountsService;
    @Autowired
    private MenuService         menuService;
    @Autowired
    private ShopcategoryService shopcategoryService;
    @Autowired
    private ShopService         shopService;
    @Autowired
    private CircleService         circleService;
    @Autowired
    private  UserService         userService;
    @Autowired
	private NewsService newsService;
    @RequestMapping("/login")
    public String login() {
        return "seller/dlzc/Login";
    }

    @RequestMapping("/register")
    public String showRegister(Model model) {
        try {
            List<ShopCategoryDto> list = shopcategoryService.getAllList();
            model.addAttribute("categorys", list);
            model.addAttribute("slist", circleService.getAllList());
        } catch (Exception e) {
            logger.error("", e);
        }
        return "seller/user/register_Company";
    }
    @RequestMapping("/user/fwxy")
    public String fwxy(HttpServletRequest request) {
    	SnewsWithBLOBs swb = newsService.selSingle(211);
     	request.setAttribute("fwxy", swb.getContent());
        return "seller/user/fwxy";
    }
    @RequestMapping("/registers")
    public String showregisters() {
        return "menber/register";
    }

    /* @RequestMapping("/messagelist")
     public String messagelist() {
         return "seller/messagelist";
     }*/
    /*@RequestMapping("/showHome")
    public String show	() {z
        return "seller/h";
    }*/

    /* @RequestMapping("/dlzc/toLogin")
     public String toLogin(String name, String password, HttpServletRequest request) {
         password = DEndecryptUtil.get_instances().passwordEncrypt(password);
         Accounts accounts;
         try {
             accounts = accountsService.queryByNameAndPassword(name, password);
             if (accounts != null) {
                 HttpSession session = request.getSession();
                 session.setAttribute("current_user", accounts);
                 request.setAttribute("accounts", accounts);
                 List<Menus> menus = menuService.queryAllPlatformMenus(0);
                 request.setAttribute("menus", menus);
                 return "/seller/index";
             }

         } catch (Exception e) {
             logger.error("", e);
         }

         return "/seller/dlzc/Login";
     }*/
    
    /**
     * 卖家的单点登录
     * @param token
     * @param request
     * @return
     */
    @RequestMapping("/simpleLogin")
    public String simpleLogin(String token, HttpServletResponse  response,HttpServletRequest request) {
    	ReusltItem item = new ReusltItem();
    	try {
    		//通过token 得到username
    		//TODO 通过token 得到username
    		
        	String username="Wangwu";
        	Integer[] array={UserTypeEnum.Employee.getValue(),UserTypeEnum.Seller.getValue()};
        	Object[] rls=accountsService.querybyName(username,array);
        	String userInfo = "";
        	switch (Integer.parseInt(rls[0].toString())) {
            case 0:
                userInfo = JsonUtil.getJsonStringFromObject(rls[1]);
                break;
            default:
                item.setCode(-104);
                item.setDesc("登录失败！");
                request.setAttribute("item", item);
                return "redirect:/seller/login";
           }
        	String token1=UUID.randomUUID().toString();
            SessionUser sessionUser= (SessionUser)rls[1];
            SessionState.SetSessionUser(token, sessionUser);
            Cookie cookie=new Cookie(ConstanValue.TOKEN_KEY, token1);
            response.addCookie(cookie);
            Integer shopStatus = shopService.queryById(sessionUser.getShopid()).getStatus();
            if(shopStatus!=null){
            	switch (shopStatus) {
    			case 0:
    			case 3:
    				return  "redirect:/seller/shop/showCompanyInfo";
    			case 6:
    				 item.setDesc("店铺已删除，不可登录！");
    	             request.setAttribute("item", item);
    	             return "redirect:/seller/login";
    			case 1:
    			case 2:
    			case 4:
    			case 5:
    				return "redirect:/seller/index";
    			default:
    				item.setDesc("店铺状态错误！");
    	            request.setAttribute("item", item);
    	            return "redirect:/seller/login";
    			}
            }else{
            	item.setDesc("店铺状态错误！");
	            request.setAttribute("item", item);
	            return "redirect:/seller/login";
            }
            
            
		} catch (Exception e) {
			 LogHandle.error(LogType.Login,
	            		MessageFormat.format("卖家登录错误，详情：{0}", e.getMessage()));
			item.setCode(-900);
			item.setDesc("异常：" + e.getMessage());
			request.setAttribute("item", item);
			return "redirect:/seller/login";
		}
    }
    
    
    @RequestMapping("/user/findPwd")
    public String findPwd(String setup,String type,String userid,HttpServletRequest request){
    	try {
			
		/*	List<Api_ArticlesDto> articledto=new ArrayList<Api_ArticlesDto>();
			articledto = articlesService.selectNews(ArticleNavType.安全问题.getValue());
			Integer count=0;
			if(articledto!=null&&articledto.size()>0){
				
				count=articledto.size();
			}*/
			if(StringUtilsEX.ToInt(userid)>0){
				Users users=userService.queryById(StringUtilsEX.ToInt(userid));
				if(users!=null){
					request.setAttribute("phone", users.getMobile());
					request.setAttribute("emails", users.getEmail());
				}
			}
			request.setAttribute("setup", StringUtilsEX.ToInt(setup));
			request.setAttribute("type", StringUtilsEX.ToInt(type));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "/seller/dlzc/findPwd";
    }
}
