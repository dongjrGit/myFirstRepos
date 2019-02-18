package com.yinlian.wssc.seller.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Enums.ConfigSetTypeEnum;
import com.yinlian.Enums.MenusTypeEnum;
import com.yinlian.Enums.OperateRecordsFromEnum;
import com.yinlian.Enums.OperateRecordsTypeEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Configdictionary;
import com.yinlian.wssc.web.po.Role;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.ConfigSetService;
import com.yinlian.wssc.web.service.OperaterecordsService;
import com.yinlian.wssc.web.service.RoleService;
import com.yinlian.wssc.web.util.CriteriaMenu;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/seller/shoprole")
public class ShopRoleController {


	@Autowired
	private RoleService roleService;
	@Autowired
	private AccountsService accountsService;
	
	 @Autowired
	 private ConfigSetService    configsetService;
	 @Autowired
		private OperaterecordsService     operaterecordsService ;
	  
	SessionUser user=null;
	
	private static final Logger logger = LoggerFactory
			.getLogger(ShopRoleController.class);

	/**
	 * 添加角色
	 * 
	 * @param name
	 * @param status
	 * @param desc
	 * @return
	 */
	@RequestMapping("/addRole")
	public @ResponseBody ReusltItem addRole(String name, String status,
			String desc) {
		ReusltItem item = new ReusltItem();
		try {
			user=SessionState.GetCurrentUser();
			int shopId = user.getShopid();
			List<Role> list = roleService.isHave(shopId,name);
			if (list.size() > 0) {
				item.setCode(-103);
				item.setDesc("该角色已存在");
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-101);
				item.setDesc("角色名称不能为空");
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-102);
				item.setDesc("角色状态参数错误，status:" + status);
				return item;
			}
			//获取店铺下角色个数
			List<Role> rolelist=roleService.selectShopRole(user.getShopid());
			if(rolelist!=null){
				Configdictionary config=configsetService.showConfigSetByType(ConfigSetTypeEnum.角色个数.getValue());
				if(config!=null){
					if(config.getValue()!=null){
						if(rolelist.size()>=Integer.valueOf(config.getValue())){
							item.setCode(-104);
							item.setDesc("角色个数超过限制，不能添加。");
							return item;
						}
					}
					
				}
				
			}
			Role role = new Role();
			role.setCreatetime(new Date());
			role.setIsdel(false);
			role.setShopid(shopId);
			role.setName(name.trim());
			role.setStatus(StringUtilsEX.ToInt(status));
			role.setDescription(desc);
			if (roleService.insert(role) > 0) {
				item.setCode(0);
				item.setDesc("添加角色成功");
				logger.info(MessageFormat.format("添加角色成功!名称:{0}", name));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), " 添加角色页面", "/seller/shoprole/addRole", "添加角色");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加添加角色操作记录出错! 异常信息:",
    								e, "/seller/shoprole/addRole");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("添加角色失败");
				logger.warn(MessageFormat.format("添加角色失败!名称:{0}", name));
			}
		} catch (Exception e) {
			logger.error("添加角色出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}

	/**
	 * 编辑角色
	 * 
	 * @param id
	 * @param name
	 * @param status
	 * @param desc
	 * @return
	 */
	@RequestMapping("/updateRole")
	public @ResponseBody ReusltItem updateRole(String id, String name,
			String status, String desc) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("角色ID参数错误，id:" + id);
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(name)) {
				item.setCode(-102);
				item.setDesc("角色名称不能为空");
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-103);
				item.setDesc("角色状态参数错误，status:" + status);
				return item;
			}
			Role role = roleService.getByID(StringUtilsEX.ToInt(id));
			role.setName(name.trim());
			role.setStatus(StringUtilsEX.ToInt(status));
			role.setDescription(desc);
			if (roleService.update(role) > 0) {
				item.setCode(0);
				item.setDesc("编辑角色成功");
				logger.info(MessageFormat.format("编辑角色成功! ID:{0},名称:{1}", id, name));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "编辑角色页面", "/seller/shoprole/updateRole", "编辑角色");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加编辑角色操作记录出错! 异常信息:",
    								e, "/seller/shoprole/updateRole");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("编辑角色失败");
				logger.warn(MessageFormat.format("编辑角色失败!ID:{0},名称:{1}", id, name));
			}

		} catch (Exception e) {
			logger.error("编辑角色出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}

	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRole")
	public @ResponseBody ReusltItem deleteRole(String id) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("角色ID参数错误，id:" + id);
				return item;
			}
			if(accountsService.getByRoleID(StringUtilsEX.ToInt(id))!=null){
				item.setCode(-102);
				item.setDesc("角色已添加到账户，不能删除！");
				return item;
			}
			if (roleService.delete(StringUtilsEX.ToInt(id)) > 0) {
				item.setCode(0);
				item.setDesc("删除角色成功");
				logger.info(MessageFormat.format("删除角色成功! ID:{0}", id));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "删除角色页面", "/seller/shoprole/deleteRole", "删除角色");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加删除角色操作记录出错! 异常信息:",
    								e, "/seller/shoprole/deleteRole");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("删除角色失败");
				logger.warn(MessageFormat.format("删除角色失败!ID:{0}", id));
			}
		} catch (Exception e) {
			logger.error("删除角色出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}
	
	/**
	 * 查询店铺角色列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("/getShopRoleList")
	public @ResponseBody ReusltItem getShopRoleList(String name,String page,String size){
		ReusltItem item = new ReusltItem();
		try
		{
			user=SessionState.GetCurrentUser();
			if(StringUtilsEX.ToInt(page)<=0 || StringUtilsEX.ToInt(size)<=0){
				item.setCode(-101);
				item.setDesc("分页参数错误，pageindex:" + page+",pagesize:"+size);
				return item;
			}
			CriteriaMenu cMenu=new CriteriaMenu();
			if(!StringUtilsEX.IsNullOrWhiteSpace(name)){
				cMenu.setRolename(name);
			}
			//所属店铺默认为 1
			cMenu.setShopid(user.getShopid());
			
			PageBean pBean=roleService.getList(cMenu, StringUtilsEX.ToInt(page),StringUtilsEX.ToInt(size));
			item.setCode(0);
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
		}
		catch (Exception e) {
			logger.error("查询店铺角色列表出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}
	
	/**
	 * 禁用/启用 角色状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public @ResponseBody ReusltItem updateStatus(String id,String status){
		ReusltItem item = new ReusltItem();
		try
		{
			if (StringUtilsEX.ToInt(id) <= 0) {
				item.setCode(-101);
				item.setDesc("角色ID参数错误，id:" + id);
				return item;
			}
			if (StringUtilsEX.ToInt(status) < 0) {
				item.setCode(-102);
				item.setDesc("角色状态参数错误，status:" + status);
				return item;
			}
			if(roleService.updateStatus(StringUtilsEX.ToInt(status), StringUtilsEX.ToInt(id))>0){
				item.setCode(0);
				item.setDesc("编辑角色状态成功");
				logger.info(MessageFormat.format("编辑角色状态成功! ID:{0},状态:{1}", id,status));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.修改.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "编辑角色状态页面", "/seller/shoprole/updateStatus", "编辑角色状态");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加编辑角色状态操作记录出错! 异常信息:",
    								e, "/seller/shoprole/updateStatus");
    					}
    					
    				}
    			});
			}else{
				item.setCode(-200);
				item.setDesc("编辑角色状态失败");
				logger.warn(MessageFormat.format("编辑角色状态失败! ID:{0},状态:{1}", id,status));
			}
			
		}
		catch (Exception e) {
			logger.error("编辑角色状态出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}
	
	/**
	 * 获取卖家中心菜单
	 * @return
	 */
	@RequestMapping("/getShopMenusTree")
	public @ResponseBody ReusltItem getShopMenusTree(){
		ReusltItem item = new ReusltItem();
		try
		{			
			//type 0-菜单 1-按钮
			item.setData(roleService.getByMenuType(0, MenusTypeEnum.卖家中心.getValue()));
			item.setCode(0);
		}
		catch (Exception e) {
			logger.error("获取菜单树结构出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}
	
	/**
	 * 角色编辑权限
	 * @param menuids
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/addRoleMenu")
	public @ResponseBody ReusltItem addRoleMenu(String menuids,String roleid){
		ReusltItem item = new ReusltItem();
		try {
			List<Integer> idlist = new ArrayList<Integer>();
			if (StringUtilsEX.ToInt(roleid) <= 0) {
				item.setCode(-101);
				item.setDesc("角色ID参数错误，roleid:" + roleid);
				return item;
			}
			for (String id : menuids.split(",")) {
				if (StringUtilsEX.ToInt(id) <= 0) {
					item.setCode(-102);
					item.setDesc("菜单ID参数错误，id:" + id);
					return item;
				}
				idlist.add(StringUtilsEX.ToInt(id));
			}
			if (roleService.insertList(idlist, StringUtilsEX.ToInt(roleid)) > 0) {
				item.setCode(0);
				item.setDesc("店铺角色编辑权限成功");
				logger.info(MessageFormat.format("店铺角色编辑权限成功! roleid:{0},菜单ID集合:{1}",
						roleid, menuids));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.添加.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "店铺角色编辑权限页面", "/seller/shoprole/addRoleMenu", "店铺角色编辑权限");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加店铺角色编辑权限操作记录出错! 异常信息:",
    								e, "/seller/shoprole/addRoleMenu");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("店铺角色编辑权限失败");
				logger.warn(MessageFormat.format("店铺角色编辑权限失败! roleid:{0},菜单ID集合:{1}",
						roleid, menuids));
			}

		} catch (Exception e) {
			logger.error("店铺角色编辑权限出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}
	
	/**
	 * 清空角色下权限
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/deleteRoleMenu")
	public @ResponseBody ReusltItem deleteRoleMenu(String roleid){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(roleid) <= 0) {
				item.setCode(-101);
				item.setDesc("角色ID参数错误，roleid:" + roleid);
				return item;
			}
			if (roleService.deleteByRoleID(StringUtilsEX.ToInt(roleid)) > 0) {
				item.setCode(0);
				item.setDesc("店铺角色清空权限成功");
				logger.info(MessageFormat.format("店铺角色清空权限成功! roleid:{0}",
						roleid));
				SessionUser user=SessionState.GetCurrentUser();
                //异步操作 不影响正常流程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    			cachedThreadPool.execute(new Runnable() {
    				@Override
    				public void run() {
    					try{
    						operaterecordsService.insertOperaterecords(
                            		OperateRecordsTypeEnum.删除.getValue(), OperateRecordsFromEnum.卖家中心.getValue(), 
                            		user.getUserId(), user.getLoginName(), "店铺角色清空权限页面", "/seller/shoprole/deleteRoleMenu", "店铺角色清空权限");
    					}
    					catch(Exception e){
    						LogHandle.error(LogType.OperateRecords,"添加店铺角色清空权限操作记录出错! 异常信息:",
    								e, "/seller/shoprole/deleteRoleMenu");
    					}
    					
    				}
    			});
			} else {
				item.setCode(-200);
				item.setDesc("店铺角色清空权限失败");
				logger.warn(MessageFormat.format("店铺角色清空权限失败! roleid:{0}",
						roleid));
			}
		} catch (Exception e) {
			logger.error("店铺角色清空权限出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}
	/**
	 * 根据角色ID获取权限
	 * @param roleid
	 * @return
	 */
	@RequestMapping("/getMenuByRoleID")
	public @ResponseBody ReusltItem getMenuByRoleID(String roleid){
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(roleid) <= 0) {
				item.setCode(-101);
				item.setDesc("角色ID参数错误，roleid:" + roleid);
				return item;
			}
			item.setData(roleService.selectByRoleID(StringUtilsEX.ToInt(roleid)));
			item.setCode(0);
			
		} catch (Exception e) {
			logger.error("根据角色ID获取权限出现的异常信息:" + e.getMessage(), e);
			item.setCode(-900);
			item.setDesc("异常：" + e.getLocalizedMessage());
		}
		return item;
	}
	
}
