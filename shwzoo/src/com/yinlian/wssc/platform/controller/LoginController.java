package com.yinlian.wssc.platform.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.UserTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.EmployeeService;
import com.yinlian.wssc.web.service.RoleMenusService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.JsonUtil;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping("/platform/user")
public class LoginController {
	
	@Autowired
	private AccountsService     accountsService;
	@Autowired
	private UserService         userService;
	@Autowired
	private EmployeeService     employeeService;
	
	@Autowired
	private ShopService         shopService;
	
	@Autowired
	private RoleMenusService    roleMenusService;
	
	@RequestMapping("/toLogin")
	public @ResponseBody ReusltItem  toLogin(String name,String pwd,String ChannelType,String LoginType){
		ReusltItem item=new ReusltItem();
		try {
		if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
			item.setCode(-102);
			item.setDesc("登录名(name)不能为空！");
			return item;
		}
		if (StringUtilsEX.IsNullOrWhiteSpace(pwd)) {
			item.setCode(-103);
			item.setDesc("密码(pwd)不能为空！");
			return item;
		}
		String token=UUID.randomUUID().toString();
		//session.setAttribute(ConstanValue.TOKEN_KEY, token);
		/*Accounts accounts=null;
		SessionUser sessionUser=new SessionUser();
		*/
			
			/* Integer[] array = { UserTypeEnum.Seller.getValue(), UserTypeEnum.Employee.getValue() };
	            accounts = accountsService.queryLogin(UserName, Pwd, array);*/
			Integer[] array={UserTypeEnum.SupAdmin.getValue(),UserTypeEnum.Admin.getValue()};
			 String userInfo = "";
	            Object[] rls= accountsService.queryLogin(name, pwd, array);
	            switch (Integer.parseInt(rls[0].toString())) {
	            case 0:
	                userInfo = JsonUtil.getJsonStringFromObject(rls[1]);
	                break;
	            case -1:
	                item.setCode(-104);
	                item.setDesc("登录失败,用户名或密码错误！");
	                return item;
	            case -2:
	                item.setCode(-105);
	                item.setDesc("登录失败,账户被锁定！");
	                return item;
	            case -3:
	                item.setCode(-106);
	                item.setDesc("登录失败,账户异常！");
	                return item;
	            default:
	                item.setCode(-104);
	                item.setDesc("登录失败,用户名或密码错误！");
	                return item;
	           }
	            SessionUser sessionUser= (SessionUser)rls[1];
	            SessionState.SetSessionUser(token, sessionUser);
	            Users users=userService.queryById(sessionUser.getUserId());
	            item.setToken(token);
	            item.setCode(0);
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("Token", token);
	            map.put("Channel", ChannelType);
	            map.put("sessionUser", sessionUser);
	            map.put("UserID",sessionUser.getUserId());
	            map.put("UserName", sessionUser.getLoginName());
	            map.put("Mobile",users.getMobile());
	            map.put("Email",users.getEmail());
	           /* Shop shop=shopService.queryById(sessionUser.getShopid());
	            if(shop!=null){
	            	map.put("ShopStatus", shop.getStatus());
	            }else{
	            	map.put("ShopStatus", shop.getStatus());
	            }*/
	            item.setData(JsonUtil.getJsonStringFromMap(map));
	            //System.out.println("+++++++++++++++++"+shopService.queryById(shopId.get(0)).getStatus());
	            
		
			/*Accounts account=accountsService.queryOperator(name, pwd, UserTypeEnum.SupAdmin.getValue());
			if(account!=null){
				accounts=account;
			}else{
				accounts=accountsService.queryOperator(name, pwd, UserTypeEnum.Admin.getValue());
			}*/
			/*if(accounts==null){
				item.setCode(-200);
				item.setDesc("登录名或密码错误");
			}else{
				Integer userId=accounts.getUserid();
				Integer shopId=0;
				List<Shop> list=shopService.selectListByUserId(userId);
				
				if(list!=null&&list.size()>0){
					shopId=list.get(0).getId();
				}
				
				List<Integer> shopId=new ArrayList<Integer>();
				for (int i = 0; i < list.size(); i++) {
					shopId.add(list.get(i).getId());
				}
				
				Redis_UserInfo.Set("channel"+userId, ChannelType);
				Redis_UserInfo.Set("token"+userId, token);
				String LoginName=accounts.getLoginname();
				List<Integer> rights=new ArrayList<Integer>();
				List<RoleMenus> roleMenus=roleMenusService.selectByRoleId(accounts.getRoleid());	
				if(roleMenus!=null&&roleMenus.size()>0){
					for (int i = 0; i < roleMenus.size(); i++) {
						rights.add(roleMenus.get(i).getMenusid());
					}
				}else{
					rights=null;
				}
				
				String realName="";
				int userType=accounts.getUsertype();
				if(userType==UserTypeEnum.Employee.getValue()){
					
					realName=employeeService.selectById(accounts.getUserid()).getRealname();
					
				}else{
					
					realName=userService.queryById(accounts.getUserid()).getRealname();
				}
				sessionUser.setUserId(userId);
				sessionUser.setName(realName);
				sessionUser.setLoginName(LoginName);
				sessionUser.setRights(rights);
				sessionUser.setShopid(shopId);
				SessionState.SetSessionUser(token, sessionUser);	
				item.setToken(token);
				item.setCode(0);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("Token", token);
				map.put("Channel", ChannelType);
				map.put("sessionUser", sessionUser);
				item.setData(JsonUtil.getJsonStringFromMap(map));		
			}*/
			
		} catch (Exception e) {		
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("平台登录异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			 LogHandle.error(LogType.Login,"平台登录错误，详情：", e,"/platform/user/toLogin");
		}
		return item;
	}
	
	/*@RequestMapping("/exit")
	public String exit(HttpSession session ){
		String token=session.getAttribute(ConstanValue.TOKEN_KEY).toString();
		Redis_UserInfo.Del(token);
		session.invalidate();
		return "redirect:/platform/login";
	}*/
}
