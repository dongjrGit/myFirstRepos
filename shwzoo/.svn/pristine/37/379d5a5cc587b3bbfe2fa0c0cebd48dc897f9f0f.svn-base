/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.ShopStatusEnum;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.PageMarkDTo;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.Area;
import com.yinlian.wssc.web.po.Brand;
import com.yinlian.wssc.web.po.City;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Province;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.ShopAuthentication;
import com.yinlian.wssc.web.po.Shopnotice;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.service.AreaService;
import com.yinlian.wssc.web.service.BrandService;
import com.yinlian.wssc.web.service.CityServcie;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.ProvinceServcice;
import com.yinlian.wssc.web.service.ShopAuthenticationService;
import com.yinlian.wssc.web.service.ShopBrandService;
import com.yinlian.wssc.web.service.ShopService;
import com.yinlian.wssc.web.service.ShopnoticeService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.CriteriaAdvering;
import com.yinlian.wssc.web.util.CriteriaCommodity;
import com.yinlian.wssc.web.util.CriteriaShop;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.SessionUtil;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * MyShopController.java
 * @author Administrator
 * @version $Id: MyShopController.java, v 0.1 2016年3月16日 下午12:52:39 Administrator Exp $
 */
@Controller
@RequestMapping("/seller/shop")
public class MyShopController {

    /**
     * 日志输出
     */
    private static final Logger       logger = LoggerFactory.getLogger(MyShopController.class);

    @Autowired
    private ShopBrandService          shopBrandService;
    @Autowired
    private BrandService              brandService;
    @Autowired
    private ShopService               shopService;
    @Autowired
    private UserService               userService;
    @Autowired
    private ShopAuthenticationService shopAuthenticationService;
    @Autowired
    private ShopnoticeService         shopnoticeService;
    @Autowired
    private AreaService               areaService;
    @Autowired
    private CityServcie               cityServcie;
    @Autowired
    private ProvinceServcice          provinceServcice;
    @Autowired
    private ConfigSetService          configSetService;
    @Autowired
    private UsercapitalService        usercapitalService;
    @Autowired
    private OperaterecordsService     operaterecordsService ;
    @Autowired
    private AdverisingService adverisingService;
    
    @Autowired
    private AdvertImgService advertImgService;
    
    SessionUser                 user   = null;

    /**
     * 根据店铺id和品牌的名字 模糊查询分页信息
     * 
     * @param criteria
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryBrandByCriteria")
    public @ResponseBody ReusltItem queryBrandByCriteria(CriteriaCommodity criteria, String page,
                                                         String size) {
        ReusltItem item = new ReusltItem();
        try {

            if (StringUtilsEX.ToInt(page) <= 0) {
                item.setCode(-102);
                item.setDesc("分页参数错误，pageindex：" + page);
                return item;
            }
            if (StringUtilsEX.ToInt(size) <= 0) {
                item.setCode(-103);
                item.setDesc("分页参数错误，pageindex：" + size);
                return item;
            }
            PageBean pageBean;

            pageBean = shopBrandService.selectPage(criteria, StringUtilsEX.ToInt(page),
                StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询品牌信息异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "查询品牌信息异常! 异常信息:{0}", e,
                "shop/queryBrandByCriteria");
        }
        return item;
    }

    /**
     * 删除
     * 
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public @ResponseBody ReusltItem delete(String id, HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) <= 0) {
                item.setCode(-101);
                item.setDesc("id不能为空,id：" + id);
                return item;
            }

            Integer userid = SessionUtil.getSessionUserId(request);
            int result = shopBrandService.deleteById(StringUtilsEX.ToInt(id), userid);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
                SessionUser user=SessionUtil.getSessionUser(request);
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除店铺页面", "/seller/shop/delete", "删除店铺");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加删除店铺操作记录出错! 异常信息:",
    								e, "/seller/shop/delete");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("删除信息失败! 失败信息:{0}", id), "shop/delete");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("删除信息异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,"删除信息异常! 异常信息:{0}", e, "shop/delete");
        }
        return item;
    }

    /**
     * 添加品牌
     * 
     * @param shopid
     * @param name
     * @param description
     * @param img
     * @param officialurl
     * @return
     */
    @RequestMapping("/saveBrand")
    public @ResponseBody ReusltItem saveBrand(String name, String description,
                                              String img, String officialurl) {
        ReusltItem item = new ReusltItem();
        try {
        	SessionUser user=SessionState.GetCurrentUser();
            if (!StringUtils.isNotNull(name)) {
                item.setCode(-101);
                item.setDesc("请输入品牌名");
                return item;
            }
            if (!StringUtils.isNotNull(description)) {
                item.setCode(-102);
                item.setDesc("请输入品牌描述");
                return item;
            }
            if (!StringUtils.isNotNull(img)) {
                item.setCode(-103);
                item.setDesc("请输入品牌图片");
                return item;
            }
            if (!StringUtils.isNotNull(officialurl)) {
                item.setCode(-104);
                item.setDesc("请输入官网");
                return item;
            }
            if (!StringUtils.checkUrl(officialurl)) {
				item.setCode(-105);
				item.setDesc("请输入正确网址");
				return item;
			}
            Brand brand = new Brand();
            brand.setName(name);
            brand.setDescription(description);
            brand.setImg(img);
            brand.setOfficialurl(officialurl);
            brand.setStatus(1);
            brand.setCreatetime(new Date());

            int result = brandService.insertBrandGetId(brand, user.getShopid());
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
             
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "添加品牌页面", "/seller/shop/saveBrand", "添加品牌");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加添加品牌操作记录出错! 异常信息:",
    								e, "/seller/shop/saveBrand");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("修改品牌失败! 失败信息:{0}", user.getShopid()), "shop/saveBrand");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("添加品牌异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("添加品牌异常! 异常信息:{0}", e.toString()), "shop/saveBrand");
        }
        return item;
    }

    @RequestMapping("/saveUser")
    public @ResponseBody ReusltItem saveUser(String userid, String name, String mobile, String email,String shoplogo) {
        ReusltItem item = new ReusltItem();
        Users users=new Users();
        
        try {
            if (StringUtilsEX.ToInt(userid) < 0) {
                item.setCode(-104);
                item.setDesc("联系人的id不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(name)) {
                item.setCode(-101);
                item.setDesc("请输入联系人姓名");
                return item;
            }
            if (!StringUtils.isNotNull(mobile)) {
                item.setCode(-102);
                item.setDesc("请输入联系人手机号码");
                return item;
            }
            if (!StringUtils.isNotNull(email)) {
                item.setCode(-103);
                item.setDesc("请输入联系人邮箱");
                return item;
            }
            Shop shop=shopService.queryByUserId(StringUtilsEX.ToInt(userid));
            if(StringUtils.isNotNull(shoplogo)){
                shop.setImgurl(shoplogo);
                shopService.updateByPrimaryKey(shop);
            }
            //先查出来商铺表中的id，然后再把users表中的数据塞入，更新
            ShopAuthentication authentication = shopAuthenticationService.queryByShopId(shop.getId());
            if(!StringUtilsEX.IsNullOrWhiteSpace(name)){
            	authentication.setPrincipalname(name);
            }
            if(!StringUtilsEX.IsNullOrWhiteSpace(mobile)){
            	authentication.setPrincipalmobile(mobile);
            }
            if(!StringUtilsEX.IsNullOrWhiteSpace(email)){
            	authentication.setPrincipalemail(email);
            }
            shopAuthenticationService.updateByExample(authentication);
            int result = userService.update(StringUtilsEX.ToInt(userid),name, mobile, email);
            
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
                SessionUser user=SessionState.GetCurrentUser();
                
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改会员页面", "/seller/shop/saveUser", "修改会员信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改会员信息操作记录出错! 异常信息:",
    								e, "/seller/shop/saveUser");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("修改用户信息失败! 失败信息:{0}", userid), "shop/saveUser");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("保存用户信息异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                MessageFormat.format("保存用户信息异常! 异常信息:{0}", e.toString()), "shop/saveUser");
        }
        return item;
    }

    /**
     * 获取店铺信息
     * 
     * @param id
     * @return
     */
    @RequestMapping("/queryShopById")
    public @ResponseBody ReusltItem queryShopById(Integer id) {
        ReusltItem item = new ReusltItem();
        try {
            Shop shop = shopService.queryById(id);
            item.setCode(0);
            item.setData(shop);
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("获取店铺信息异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,"获取店铺信息异常! 异常信息:{0}", e, "shop/queryShopById");
        }
        return item;
    }

    /**
     * 根据userid查询user
     * 
     * @param userid
     * @return
     */
    @RequestMapping("/queryUserById")
    public @ResponseBody ReusltItem queryUserById(Integer userid) {
        ReusltItem item = new ReusltItem();
        try {
            Users users = userService.queryById(userid);
            item.setCode(0);
            item.setData(users);
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		 item.setDesc("根据userid查询user异常：" + e.getMessage());
			} else {
					item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
               "根据userid查询user异常! 异常信息:{0}", e,
                "shop/queryUserById");
        }
        return item;
    }

    /**
     * 根据店铺id查询公司的详细信息
     * 
     * @param shopid
     * @return
     */
    @RequestMapping("/queryInfoByShopId")
    public @ResponseBody ReusltItem queryInfoByShopId(String shopid) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(shopid) < 0) {
                item.setCode(-101);
                item.setDesc("店铺id不能为空");
                return item;
            }
            ShopAuthentication shopAuthentication = shopAuthenticationService
                .queryByShopId(StringUtilsEX.ToInt(shopid));
            item.setCode(0);
            item.setData(shopAuthentication);
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询公司的详细信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "查询公司的详细信息异常! 异常信息:{0}", e,
                "shop/queryInfoByShopId");
        }
        return item;
    }

    /**
     * 根据id修改店铺的公司详细信息
     * 
     * @param id
     * @param companyaddress
     * @param companymobile
     * @param companyemail
     * @param companyfox
     * @param postcode
     * @return
     */
    @RequestMapping("/saveShopAuthentication")
    public @ResponseBody ReusltItem saveShopAuthentication(String id, String companyaddress,
                                                           String companymobile,
                                                           String companyemail, String companyfox,
                                                           String postcode,String  BankName,String BankCardNo,String HoderName) {
        ReusltItem item = new ReusltItem();
        try {
            checkParam(id, companyaddress, companymobile, companyemail, companyfox, postcode,BankName,BankCardNo,HoderName,item);
            if (item.getCode() < 0) {
                return item;
            }

            int result = shopAuthenticationService.updateByid(StringUtilsEX.ToInt(id),
                companyaddress, companymobile, companyemail, companyfox, postcode,BankName,BankCardNo,HoderName);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "根据id修改店铺的公司详细信息页面", "/seller/shop/saveShopAuthentication", "根据id修改店铺的公司详细信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加根据id修改店铺的公司详细信息操作记录出错! 异常信息:",
    								e, "/seller/shop/saveShopAuthentication");
    					}
    					
    				}
    			});
                
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("修改店铺联系人信息失败! 失败信息:{0}", id),
                    "shop/saveShopAuthentication");
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("修改店铺公司详细异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "修改店铺公司详细异常! 异常信息:{0}", e,
                "shop/saveShopAuthentication");
        }
        return item;
    }

    /**
     * 根据id修改店铺联系人的信息
     * 
     * @param id
     * @param pName
     * @param pMobile
     * @param pEmail
     * @param pPost
     * @return
     */
    @RequestMapping("/saveContact")
    public @ResponseBody ReusltItem saveContact(String id, String pName, String pMobile,
                                                String pEmail, String pPost) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("参数id不能为空");
                return item;
            }
            if (!StringUtils.isNotNull(pName)) {
                item.setCode(-102);
                item.setDesc("请输入联系人姓名");
                return item;
            }
            if (!StringUtils.isNotNull(pMobile)) {
                item.setCode(-103);
                item.setDesc("请输入联系人手机号码");
                return item;
            }
//            if (!StringUtils.isNotNull(pEmail)) {
//                item.setCode(-104);
//                item.setDesc("请输入联系人邮箱");
//                return item;
//            }
//            if (!StringUtils.isNotNull(pPost)) {
//                item.setCode(-105);
//                item.setDesc("请输入联系人职位");
//                return item;
//            }

            int result = shopAuthenticationService.updateContact(StringUtilsEX.ToInt(id), pName,
                pMobile, pEmail, pPost);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "根据id修改店铺联系人的信息页面", "/seller/shop/saveContact", "根据id修改店铺联系人的信息");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加根据id修改店铺联系人的信息操作记录出错! 异常信息:",
    								e, "/seller/shop/saveContact");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("修改店铺联系人信息失败! 失败信息:{0}", id), "shop/saveContact");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("修改店铺联系人信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "修改店铺联系人信息异常! 异常信息:{0}", e, "shop/saveContact");
        }
        return item;
    }

    /**
     * 校验参数
     * @param id
     * @param companyaddress
     * @param companymobile
     * @param companyemail
     * @param companyfox
     * @param postcode
     * @param item
     */
    private void checkParam(String id, String companyaddress, String companymobile,
                            String companyemail, String companyfox, String postcode,String BankName,String BankCardNo,String HoderName,ReusltItem item) {

        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("参数id不能为空");
        }
        if (!StringUtils.isNotNull(companyaddress)) {
            item.setCode(-102);
            item.setDesc("请输入公司详细地址");
        }
        /*if (!StringUtils.isNotNull(companymobile)) {
            item.setCode(-103);
            item.setDesc("请输入联系人手机号码");
        }*/
        /*if (!StringUtils.isNotNull(companyemail)) {
            item.setCode(-104);
            item.setDesc("请输入邮箱");
        }*/
        if (!StringUtils.isNotNull(companyfox)) {
            item.setCode(-105);
            item.setDesc("请输入传真");
        }
        if (!StringUtils.isNotNull(postcode)) {
            item.setCode(-106);
            item.setDesc("请输入邮政编码");
        }
        /*if (!StringUtils.isNotNull(BankName)) {
            item.setCode(-107);
            item.setDesc("请输入开户行名称");
        }*/
        /*if (!StringUtils.isNotNull(BankCardNo)) {
            item.setCode(-108);
            item.setDesc("输入银行卡卡号");
        }*/
        /*if (!StringUtils.isNotNull(HoderName)) {
            item.setCode(-109);
            item.setDesc("请输入持有人");
        }*/
          
    }

    /**
     * 修改店铺的状态
     * 
     * @param status
     * @param id
     * @param reason
     * @return
     */
    @RequestMapping("/changeStatus")
    public @ResponseBody ReusltItem changeStatus(String status, String id, String reason) {
        ReusltItem item = new ReusltItem();
        try {
            Integer _status = StringUtilsEX.ToInt(status);
            if (_status < 0) {
                item.setCode(-101);
                item.setDesc("请选择状态");
                return item;
            }
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-102);
                item.setDesc("参数id错误");
                return item;
            }
            if (!StringUtils.isNotNull(reason)) {
                item.setCode(-103);
                item.setDesc("请填写原因");
                return item;
            }
/*
            if (_status == ShopStatusEnum.Open.getValue()) {
                Configdictionary config = configSetService
                    .showConfigSetByType(ConfigSetTypeEnum.店铺保证金.getValue());
                if(config==null){
                	  item.setCode(-105);
                      item.setDesc("平台未配置店铺最低保证金,请联系平台管理员");
                      return item;
                }
                float value = StringUtilsEX.ToFloat(config.getValue());
                Shop shop = shopService.queryById(StringUtilsEX.ToInt(id));
                Usercapital usercapital = usercapitalService.queryByUserId(shop.getUserid());
                if (usercapital.getBond() < value) {
                    item.setCode(-104);
                    item.setDesc("店铺保证金不足,请充值");
                    return item;
                }

            }*/
            SessionUser sessionUser = SessionState.GetCurrentUser();
            int result = shopService.updateSellerStatus(StringUtilsEX.ToInt(id),
            		_status, reason, sessionUser.getUserId(),
                sessionUser.getLoginName());
            if (result > 0) {
                item.setCode(0);
                item.setDesc("修改成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改店铺的状态页面", "/seller/shop/changeStatus", "修改店铺的状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改店铺的状态操作记录出错! 异常信息:",
    								e, "/seller/shop/changeStatus");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("修改失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("修改店铺的状态异常! 异常信息:{id},{status}", id, status),
                    "shop/changeStatus");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("修改店铺联系人信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
               "修改店铺的状态异常! 异常信息:{0}", e, "shop/changeStatus");
        }
        return item;
    }

    /**
     * 增加店铺通告
     * 
     * @param shopid
     * @param title
     * @param content
     * @param edituser
     * @return
     */
    @RequestMapping("/addShopNotice")
    public @ResponseBody ReusltItem addShopNotice(String shopid, String title, String content) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(shopid) < 0) {
                item.setCode(-101);
                item.setDesc("参数店铺id错误");
                return item;
            }
            if (!StringUtils.isNotNull(title)) {
                item.setCode(-102);
                item.setDesc("请输入标题");
                return item;
            }
            if (!StringUtils.isNotNull(content)) {
                item.setCode(-103);
                item.setDesc("请输入内容");
                return item;
            }
            SessionUser sessionUser = SessionState.GetCurrentUser();
            String edituser = "";
            if (sessionUser.getCode() == 0) {
                edituser = sessionUser.getLoginName();
            }

            int result = shopnoticeService.insert(StringUtilsEX.ToInt(shopid), title, content,
                edituser);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("添加成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "增加店铺通告页面", "/seller/shop/addShopNotice", "增加店铺通告");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加增加店铺通告操作记录出错! 异常信息:",
    								e, "/seller/shop/addShopNotice");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("添加失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("添加失败! 错误信息:{0}", shopid), "shop/addShopNotice");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("增加店铺通告异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
               "增加店铺通告异常! 异常信息:{0}", e, "shop/addShopNotice");
        }
        return item;
    }

    /**
     * 修改店铺通告
     * 
     * @param id
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/editShopNotice")
    public @ResponseBody ReusltItem editShopNotice(String id, String title, String content) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("参数id错误");
                return item;
            }
            if (!StringUtils.isNotNull(title)) {
                item.setCode(-102);
                item.setDesc("请输入标题");
                return item;
            }
            if (!StringUtils.isNotNull(content)) {
                item.setCode(-103);
                item.setDesc("请输入内容");
                return item;
            }

            int result = shopnoticeService.update(StringUtilsEX.ToInt(id), title, content);
            if (result > 0) {
                item.setCode(0);
                item.setDesc("更新成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "修改店铺通告页面", "/seller/shop/editShopNotice", "修改店铺通告");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加修改店铺通告操作记录出错! 异常信息:",
    								e, "/seller/shop/editShopNotice");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("更新失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("修改店铺通告失败! 错位信息:{0}", id), "shop/editShopNotice");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("修改店铺通告异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "修改店铺通告异常! 异常信息:{0}", e, "shop/editShopNotice");
        }
        return item;
    }

    /**
     * 根据id查询通告信息
     * 
     * @param id
     * @return
     */
    @RequestMapping("/queryNoticById")
    public @ResponseBody ReusltItem queryNoticById(String id) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("参数id错误");
            return item;
        }
        try {
            Shopnotice record = shopnoticeService.selectById(StringUtilsEX.ToInt(id));
            item.setCode(0);
            item.setData(record);
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("查询通告信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "查询通告信息异常! 异常信息:{0}", e, "shop/queryNoticById");
        }
        return item;
    }

    /**
     * 根据id删除
     * 
     * @param id
     * @return
     */
    @RequestMapping("/deleteNotice")
    public @ResponseBody ReusltItem deleteNotice(String id) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("参数id错误");
            return item;
        }
        try {
            int result = shopnoticeService.deleteById(StringUtilsEX.ToInt(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
                SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "根据id删除店铺通告页面", "/seller/shop/deleteNotice", "根据id删除店铺通告");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加根据id删除店铺通告操作记录出错! 异常信息:",
    								e, "/seller/shop/deleteNotice");
    					}
    					
    				}
    			});
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
                LogHandle.error(LogType.SellerShopManagement,
                    MessageFormat.format("删除通告信息失败! 异常信息:{0}", id), "shop/deleteNotice");
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("删除通告信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
               "删除通告信息异常! 异常信息:{0}", e, "shop/deleteNotice");
        }
        return item;
    }

    /**
     * 分页查询 某个店铺下的所有通告
     * 
     * @param shopid
     * @param title
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryShopNoticesByCriteria")
    public @ResponseBody ReusltItem queryShopNoticesByCriteria(String shopid, String title,
                                                               String page, String size) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(shopid) < 0) {
            item.setCode(-101);
            item.setDesc("参数店铺id错误");
            return item;
        }
        if (StringUtilsEX.ToInt(page) <= 0) {
            item.setCode(-102);
            item.setDesc("分页参数错误，pageindex：" + page);
            return item;
        }
        if (StringUtilsEX.ToInt(size) <= 0) {
            item.setCode(-103);
            item.setDesc("分页参数错误，pageindex：" + size);
            return item;
        }
        PageBean pageBean;
        try {
            CriteriaShop criteria = new CriteriaShop();
            criteria.setShopid(StringUtilsEX.ToInt(shopid));
            criteria.setTitle(title);
            pageBean = shopnoticeService.queryShopNoticesByCriteria(criteria,
                StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("获取通告分页信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "获取通告分页信息异常! 异常信息:{0}", e,
                "shop/queryShopNoticesByCriteria");
        }
        return item;
    }

    /**
     * 根据code查询区域信息
     * 
     * @param type
     * @param code
     * @return
     */
    @RequestMapping("/queryArearByCode")
    public @ResponseBody ReusltItem queryArearByCode(String type, String code) {
        ReusltItem item = new ReusltItem();
        try {
            switch (type) {
                case "3": //查询省
                    Province province = provinceServcice.queryByCode(code);
                    item.setCode(0);
                    item.setData(province);
                    break;

                case "2": //查询市
                    City city = cityServcie.queryByCode(code);
                    item.setCode(0);
                    item.setData(city);
                    break;
                case "1": //查询区
                    Area area = areaService.queryByCode(code);
                    item.setCode(0);
                    item.setData(area);
                    break;
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("根据code查询区域信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "根据code查询区域信息异常! 异常信息:{0}", e,
                "shop/queryArearByCode");
        }
        return item;
    }

    /**
     * 查询地区
     * 
     * @param type
     * @param code
     * @return
     */
    @RequestMapping("/queryRegion")
    public @ResponseBody ReusltItem queryRegion(String type, String code) {
        ReusltItem item = new ReusltItem();
        try {
            switch (StringUtilsEX.ToInt(type)) {
                case 0:
                    //查询 省
                    List<Province> list = provinceServcice.selectAll();
                    item.setCode(0);
                    item.setData(list);
                    break;

                case 1:
                    // 查询市
                    List<City> cities = cityServcie.selectByProvinceCode(code);
                    item.setCode(0);
                    item.setData(cities);
                    break;
                case 2:
                    //查询区
                    List<Area> areas = areaService.selectByCityCode(code);
                    item.setCode(0);
                    item.setData(areas);
                    break;
            }
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
        		item.setDesc("根据查询区域信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "根据查询区域信息异常! 异常信息:{0}", e, "shop/queryRegion");
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
    public @ResponseBody ReusltItem updateStatus(String status, String id,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
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
            	LogHandle.info(LogType.advertimg, MessageFormat.format("修改广告状态成功! ID:{0},状态:{1}", id, status));
                item.setCode(0);
                item.setDesc("修改广告状态成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "advertImg_list.jsp", "/platform/advertimg/updateStatus", "修改广告状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.SellerShopManagement,"修改广告状态操作记录出错! 异常信息:",
    								e, "/shop/updateStatus");
    					}
    					
    				}
    			});
            } else {
            	LogHandle.info(LogType.advertimg, MessageFormat.format("修改广告状态失败! ID:{0},状态:{1}", id, status));
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
            LogHandle.error(LogType.SellerShopManagement,
               "修改广告状态出错! 异常信息:", e, "/shop/updateStatus");
        }
        return item;
    }
    
    /**
     * 分页广告图片
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryAdvert")
    public @ResponseBody ReusltItem queryAdvert(String page, String size, String type,
                                                String status, String position) {
    	user=SessionState.GetCurrentUser();
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
         if (!StringUtilsEX.IsNullOrWhiteSpace(position)&&StringUtilsEX.ToInt(position)<0) {
             item.setCode(-105);
             item.setDesc("显示位置错误：" + position);
             return item;
         }
        try {
        	CriteriaAdvering criteria = new CriteriaAdvering();
        	criteria.setType(StringUtilsEX.ToInt(type));
            criteria.setStatus(StringUtilsEX.ToInt(status));
            criteria.setDisplay(StringUtilsEX.ToInt(position));
            criteria.setShopid(user.getShopid());
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
            LogHandle.error(LogType.SellerShopManagement,
                "查询分页广告图片出错! 异常信息:", e, "/shop/queryAdvert");
 
        }
        return item;
    }
    
    @RequestMapping("/getPageMark")
    public @ResponseBody ReusltItem getPageMark() {
        ReusltItem item = new ReusltItem();
        try {
        	PageMarkType[] array = PageMarkType.values();

            List<PageMarkDTo> list = new ArrayList<PageMarkDTo>();
            for (int i = 0; i < array.length; i++) {
            	PageMarkDTo dTo = new PageMarkDTo();
            	dTo.setCode(PageMarkType.values()[i].getValue());
            	dTo.setName(PageMarkType.values()[i].name());
                list.add(dTo);
            }
            item.setCode(0);
            item.setData(list);
        } catch (Exception e) {
        	item.setCode(-900);
        	if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取所有的页面标识异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle
                .error(LogType.SellerShopManagement, MessageFormat.format("获取所有的页面标识异常! 异常信息:{0}", e.getMessage()),
                    "/shop/getPageMark");
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
    public @ResponseBody ReusltItem deleteAdvertById(String id,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        if (StringUtilsEX.ToInt(id) < 0) {
            item.setCode(-101);
            item.setDesc("参数id错误");
            return item;
        }
        try {
        	user=SessionUtil.getSessionUser(request);
            int result = adverisingService.deleteAdvertising(StringUtilsEX.ToInt(id));
            if (result > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "advertImg_list.jsp", "/platform/advertimg/deleteAdvertById", "根据id删除图片");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.SellerShopManagement,"根据id删除图片操作记录出错! 异常信息:",
    								e, "/shop/deleteAdvertById");
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
            LogHandle.error(LogType.SellerShopManagement,
                "根据id删除图片出错! 异常信息:", e, "/shop/deleteAdvertById");
 
        }
        return item;
    }
    
    /**
     * 修改排序
     * @param id
     * @param setValue
     * @param type
     * @return
     */
    @RequestMapping("/SetSort")
    public @ResponseBody ReusltItem SetSort(String id, String setValue,HttpServletRequest request) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionUtil.getSessionUser(request);
            AdvertImg advertimg = advertImgService.selectByPrimaryKey(StringUtilsEX.ToInt(id));
            advertimg.setSort(StringUtilsEX.ToInt(setValue));
            int temp = advertImgService.updateByPrimaryKey(advertimg);

            if (temp > 0) {
                item.setCode(0);
                item.setDesc("保存成功");
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                            		user.getUserId(), user.getLoginName(), "advertImg_edit.jsp", "/platform/advertimg/SetSort", "修改排序");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.SellerShopManagement,"修改排序操作记录出错! 异常信息:",
    								e, "/shop/SetSort");
    					}
    					
    				}
    			});
            }
        } catch (Exception e) {
            item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("修改排序出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.SellerShopManagement,
                "修改排序出错! 异常信息:", e, "/shop/SetSort");
        }

        return item;
    }
    
    /**
     * 广告添加
     * @return
     */
    @RequestMapping("/edit")
    public @ResponseBody ReusltItem edit(String imgurl,String url,String status,String title,String pagemark,String type,String spuid,String shopid,String topicid,String display,String id,String webset) {
        ReusltItem item = new ReusltItem();
        try {
        	user=SessionState.GetCurrentUser();
        	if(StringUtilsEX.ToInt(type)<0){
        		item.setCode(-101);
        		item.setDesc("请输入大于0的整数");
        		return item;
        	}
            if (StringUtils.isBlanck(imgurl)) {
                item.setCode(-105);
                item.setDesc("请上传图片");
                return item;
            }
            if(StringUtilsEX.ToInt(webset)<0){
        		item.setCode(-103);
        		item.setDesc("请输正确的站点标识");
        		return item;
        	}
            Advertising  advert=new Advertising();
            advert.setTitle(title);
            advert.setImag(imgurl);
            advert.setStatus(StringUtilsEX.ToInt(status));
            advert.setDisplay(1);
            advert.setPagemark(StringUtilsEX.ToInt(pagemark));
            advert.setWebSet(StringUtilsEX.ToInt(webset));
            advert.setType(StringUtilsEX.ToInt(type));
            Integer _type=StringUtilsEX.ToInt(type);
            if(_type==AdvertisingType.外部链接.getValue()){
            	advert.setUrl(url);
            }else if(_type==AdvertisingType.商品.getValue()){
            	advert.setTypeid(StringUtilsEX.ToInt(spuid));
            	if (StringUtilsEX.ToInt(webset)==WebSetEnum.pc.getValue()) {
            		advert.setUrl("/web/products/proinfo.html?spuid="+spuid);	
				}else if (StringUtilsEX.ToInt(webset)==WebSetEnum.wap.getValue()) {
					advert.setUrl("/wap/products/ProDetail.html?spuid="+spuid);
				}else if (StringUtilsEX.ToInt(webset)==WebSetEnum.app.getValue()) {
					advert.setUrl("url://prodetailed|||"+spuid);
				}
            }else if(_type==AdvertisingType.店铺.getValue()){
            	advert.setTypeid(StringUtilsEX.ToInt(shopid));
            	if (StringUtilsEX.ToInt(webset)==WebSetEnum.pc.getValue()) {
            		advert.setUrl("/web/shop/details.html?shopId="+shopid);	
				}else if (StringUtilsEX.ToInt(webset)==WebSetEnum.wap.getValue()) {
					advert.setUrl("/wap/shop/index.html?id="+shopid+"&href=/wap/index.html");
				}else if (StringUtilsEX.ToInt(webset)==WebSetEnum.app.getValue()) {
					advert.setUrl("url://shopdetail|||"+shopid+"&href=/wap/index.html");
				}
            }else if(_type==AdvertisingType.专题.getValue()){
            	advert.setTypeid(StringUtilsEX.ToInt(topicid));
            	if (StringUtilsEX.ToInt(webset)==WebSetEnum.app.getValue()) {
            		advert.setUrl("/");	
				}else if (StringUtilsEX.ToInt(webset)==WebSetEnum.wap.getValue()) {
					advert.setUrl("/wap/gettopic.html?topicid="+topicid);
				}else if (StringUtilsEX.ToInt(webset)==WebSetEnum.pc.getValue()) {
					advert.setUrl("/web/products/topicprolist.html?topicid="+topicid);
				}
            }
            if (StringUtils.isBlanck(id)) { //添加
            	advert.setShopid(user.getShopid());
                int result = adverisingService.insert(advert);
                if (result > 0) {
                    item.setCode(0);
                    item.setDesc("添加成功");
                    LogHandle.info(LogType.advertimg, MessageFormat.format("添加广告成功! ID:{0},状态:{1}", id, imgurl),"/platform/advertimg/edit");
                    //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "advertImg_add.jsp", "/platform/advertimg/edit", "添加广告图片");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"添加广告操作记录出错! 异常信息:",
        								e, "/platform/advertimg/edit");
        					}
        					
        				}
        			});
                } else {
                    item.setCode(-200);
                    item.setDesc("添加失败");
                    LogHandle.info(LogType.advertimg, MessageFormat.format("添加广告失败! ID:{0},状态:{1}", id, status),"/platform/advertimg/edit");
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
                    LogHandle.info(LogType.SellerShopManagement, MessageFormat.format("修改广告成功! ID:{0},状态:{1}", id, status),"/shop/edit");
                    //异步操作 不影响正常流程
                    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        			cachedThreadPool.execute(new Runnable() {
        				@Override
        				public void run() {
        					try{
        						operaterecordsService.insertOperaterecords(
                                		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.系统后台.getValue(), 
                                		user.getUserId(), user.getLoginName(), "advertImg_edit.jsp", "/shop/edit", "修改广告图片");
        					}
        					catch(Exception e){
        						LogHandle.error(LogType.OperateRecords,"修改广告操作记录出错! 异常信息:",
        								e, "/platform/advertimg/edit");
        					}
        					
        				}
        			});
                } else {
                    item.setCode(-200);
                    item.setDesc("修改失败");
                    LogHandle.info(LogType.SellerShopManagement, MessageFormat.format("修改广告失败! ID:{0},状态:{1}", id, status),"/shop/edit");
                }
            }

        } catch (Exception e) {
            item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("保存广告信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
        	LogHandle.error(LogType.SellerShopManagement,"保存广告信息信息出错! 异常信息:",
					e, "/shop/edit");
        }

        return item;
    }
}
