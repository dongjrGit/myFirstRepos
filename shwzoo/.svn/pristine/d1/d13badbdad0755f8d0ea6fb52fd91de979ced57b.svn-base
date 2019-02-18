/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.convert;

import com.yinlian.wssc.platform.vo.AccountsVo;
import com.yinlian.wssc.web.dto.AccountsDTO;

/**
 * 
 * @author Administrator
 * @version $Id: AccountsDTOtoVo.java, v 0.1 2016年2月25日 下午5:18:00 Administrator Exp $
 */
public class AccountsConvert {

    public static AccountsVo convert(AccountsDTO source, AccountsVo target) {
        target.setDepartID(source.getDepartmentid());
        target.setDepartName(source.getDepartmentname());
        target.setEmail(source.getEmail());
        target.setID(source.getId());
        target.setMobile(source.getPhone());
        target.setNickName("");
        target.setPassword(source.getPassword());
        target.setRoleID(source.getRoleid());
        target.setRoleName(source.getRolename());
        target.setStatus(source.getStatus());
        target.setUserName(source.getLoginname());
        return target;
    }
}
