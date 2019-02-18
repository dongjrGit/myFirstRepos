/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.platform.vo.AccountsVo;
import com.yinlian.wssc.web.dto.AccountsDTO;
import com.yinlian.wssc.web.dto.UsersInfoDto;
import com.yinlian.wssc.web.po.Accounts;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 自定义的mapper接口
 * @author Administrator
 * @version $Id: AccountsMapperCustom.java, v 0.1 2016年2月25日 下午2:55:42 Administrator Exp $
 */
public interface AccountsMapperCustom {

    public List<AccountsDTO> selectAllAccounts(AccountsVo accountsVo) throws Exception;

    //获取管理员列表（分页）
    public List<AccountsDTO> getAccountsByPage(PageBeanUtil pageBeanUtil) throws Exception;

    AccountsDTO selectByID(Integer id) throws Exception;

    UsersInfoDto querySellerById(Map<String, Object> map) throws Exception;
    
    List<Accounts> getUserStartWithName(int usertype,@Param("name") String name)throws Exception;

	public List<Accounts> getUserName(@Param("name") String name) throws Exception;
}
