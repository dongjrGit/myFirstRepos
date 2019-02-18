/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.AccountsVo;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.convert.AccountsConvert;
import com.yinlian.wssc.web.dto.AccountsDTO;
import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.po.AccountsExample;
import com.yinlian.wssc.web.redis.RedisUserInfo;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.UserService;
import com.yinlian.wssc.web.util.ConstanValue;
import com.yinlian.wssc.web.util.CriteriaAccounts;
import com.yinlian.wssc.web.util.DEndecryptUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.SessionState;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * The class is for Account Service. 账号信息的控制类
 * 
 * @author Administrator
 * @version $Id: AccountsController.java, v 0.1 2016年2月25日 下午1:10:31
 *          Administrator Exp $
 */
@Controller
@RequestMapping("/platform/accounts")
public class AccountsController {

	@Autowired
	private AccountsService accountsService;

	@Autowired
	private UserService userService;

	/**
	 * 查询账户表
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/queryAllAccounts")
	public @ResponseBody ReusltItem queryAllAccounts(AccountsVo vo) {
		ReusltItem item = new ReusltItem();
		try {
			List<AccountsVo> beans = new ArrayList<AccountsVo>();
			AccountsVo bean = new AccountsVo();
			List<AccountsDTO> list;
			list = accountsService.queryAllAccounts(vo);
			for (AccountsDTO accountsDTO : list) {
				bean = AccountsConvert.convert(accountsDTO, bean);
				beans.add(bean);
			}
			item.setData(beans);
			item.setCode(1);
			item.setDesc("success");

		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取查询账户表的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.User, "获取查询账户表的信息出错! 异常信息:", e,
					"/accounts/queryAllAccounts");

		}

		return item;
	}

	@RequestMapping("/login")
	public @ResponseBody String Login(String key) {
		UUID uuid = UUID.randomUUID();
		RedisUserInfo.SetToken(key, uuid.toString());
		return uuid.toString();
	}

	@RequestMapping("/glogin")
	public @ResponseBody String GetLogin(String key) {

		return RedisUserInfo.GetToken(key);

	}

	@RequestMapping("/dlogin")
	public @ResponseBody void DelLogin(String key) {
		RedisUserInfo.DetToken(key);
	}

	/**
	 * 手机号验证
	 * 
	 * @param usertype
	 * @param mobile
	 * @return
	 */
	@RequestMapping("/checkMoblieRepeat")
	public @ResponseBody ReusltItem checkUserRepeat(String usertype,
			String mobile) {
		ReusltItem item = new ReusltItem();
		try {
			AccountsExample example = new AccountsExample();
			AccountsExample.Criteria criteria = example.createCriteria();
			criteria.andUsertypeEqualTo(StringUtilsEX.ToInt(usertype));
			criteria.andPhoneEqualTo(mobile);
			criteria.andIsdelEqualTo(false);
			List<Accounts> result = accountsService.queryByMobile(example);
			if (result != null && result.size() == 0) {
				item.setCode(0);
			} else {
				item.setCode(-200);
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取手机号验证的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.User, "获取手机号验证的信息出错! 异常信息:", e,
					"/accounts/checkMoblieRepeat");
		}

		return item;
	}

	/**
	 * 是否存在账户验证
	 * 
	 * @return
	 */
	@RequestMapping("/checkAccountRepeat")
	public @ResponseBody ReusltItem checkAccountRepeat(String value, String id) {
		ReusltItem item = new ReusltItem();
		try {
			CriteriaAccounts cAccounts = new CriteriaAccounts();
			cAccounts.setLoginname(value);
			if (StringUtilsEX.ToInt(id) > 0) {
				cAccounts.setId(StringUtilsEX.ToInt(id));
			}
			if (accountsService.isExistAcc(cAccounts) > 0) {
				item.setCode(-201);
				item.setDesc("该账号已存在!");
				return item;
			}
			// List<Accounts> list = accountsService.selectByName(value,
			// StringUtilsEX.ToInt(usertype));
			// if (list != null && list.size() == 0) {
			// item.setCode(0);
			// } else {
			// item.setCode(-200);
			// }
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc("获取是否存在账户验证的信息出错：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.User, "获取是否存在账户验证的信息出错! 异常信息:", e,
					"/platform/accounts/checkAccountRepeat");
		}
		return item;
	}

	/**
	 * 验证验证码
	 * 
	 * @param imgverification
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkVerification")
	public @ResponseBody ReusltItem checkVerification(String imgverification) {
		ReusltItem item = new ReusltItem();
		// 验证 页面验证码
		try {
			String vCode = RedisUserInfo.Get(ConstanValue.VALIDATA_CODE);
			if (vCode.equalsIgnoreCase(imgverification)) {
				item.setCode(0);
				item.setDesc("验证成功");
			} else {
				item.setCode(-200);
				item.setDesc("验证码错误");
			}
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.User, "验证验证码错误", e,
					"/platform/accounts/checkVerification");
		}

		return item;
	}

	/**
	 * 模糊检索用户列表
	 * 
	 * @param usertype
	 * @param name
	 * @return
	 */
	@RequestMapping("/getUserStartWithName")
	public @ResponseBody ReusltItem getUserStartWithName(String usertype,
			String name) {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.ToInt(usertype) < 0) {
				item.setCode(-101);
				item.setDesc("用户类型参数错误！");
				return item;
			}
			item.setData(accountsService.getUserStartWithName(
					StringUtilsEX.ToInt(usertype), name));
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.User, "模糊检索用户列表错误：{0}", e,
					"/platform/accounts/getUserStartWithName");
		}
		return item;
	}

	/**
	 * 模糊检索用户列表
	 * 
	 * @param usertype
	 * @param name
	 * @return
	 */
	@RequestMapping("/getUserName")
	public @ResponseBody ReusltItem getUserName(String name) {
		ReusltItem item = new ReusltItem();
		try {

			item.setData(accountsService.getUserName(name));
			item.setCode(0);
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.User, "模糊检索用户列表错误：{0}", e,
					"/platform/accounts/getUserStartWithName");
		}
		return item;
	}

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param oldpwd
	 * @param newPwd
	 * @param renewPwd
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/updatPwd")
	public @ResponseBody ReusltItem updatPwd(String oldpwd,String newPwd, String renewPwd) throws Exception {
		ReusltItem item = new ReusltItem();
		try {
			if (StringUtilsEX.IsNullOrWhiteSpace(oldpwd)) {
				item.setCode(-101);
				item.setDesc("当前密码(oldpwd)不能为空！");
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(newPwd)) {
				item.setCode(-102);
				item.setDesc("新密码(newPwd)不能为空！");
				return item;
			}
			if (StringUtilsEX.IsNullOrWhiteSpace(renewPwd)) {
				item.setCode(-103);
				item.setDesc("密码(renewPwd)不能为空！");
				return item;
			}
			if (newPwd.trim().length() < 6 || newPwd.trim().length() > 32) {
				item.setCode(-104);
				item.setDesc("密码(password)长度不符");
				return item;
			}
			SessionUser user = SessionState.GetCurrentUser();
			Integer userid=user.getUserId();
			String pwd =accountsService.getPwdByUserid(userid,user.getUtype());
			
			if (!pwd.equals(DEndecryptUtil.get_instances().passwordEncrypt(
					oldpwd))) {
				item.setCode(-106);
				item.setDesc("密码(oldpwd)填写有误");
				return item;
			}
			if (!newPwd.equals(renewPwd)) {
				item.setCode(-105);
				item.setDesc("两次密码不一致");
				return item;
			}
			userService.updatePlatPwd(userid,newPwd);

			item.setCode(0);
			item.setDesc("密码修改成功");
			
		} catch (Exception e) {
			item.setCode(-900);
			if (DebugConfig.BLUETOOTH_DEBUG) {
				item.setDesc(e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
			LogHandle.error(LogType.User,"修改密码错误：", e,
					"/accounts/updatPwd");
		}

		return item;
	}
    
}
