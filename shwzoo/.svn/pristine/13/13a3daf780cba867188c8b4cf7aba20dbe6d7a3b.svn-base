/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.FinanceTypeEnum;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.wssc.web.mapper.UsercapitalMapper;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.UsercapitalExample;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.service.UsercapitalService;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 用户的账户余额的业务类
 * @author Administrator
 * @version $Id: UsercapitalServiceImpl.java, v 0.1 2016年3月12日 下午2:04:10 Administrator Exp $
 */
public class UsercapitalServiceImpl implements UsercapitalService {

    /**
     * 日志输出的类
     */
    private static final Logger logger = LoggerFactory.getLogger(UsercapitalServiceImpl.class);
    @Autowired
    private UsercapitalMapper   usercapitalMapper;
    @Autowired
    private UserfinanceMapper   userfinanceMapper;

    /** 
     * @see com.yinlian.wssc.web.service.UsercapitalService#queryByUserId(int)
     */
    @Override
    public Usercapital queryByUserId(int userid) throws Exception {
        UsercapitalExample example = new UsercapitalExample();
        UsercapitalExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        List<Usercapital> list = usercapitalMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            Usercapital usercapital = new Usercapital();
            usercapital.setUserid(userid);
            usercapital.setBalance(0.0d);
            usercapital.setBond(0.0d);
            usercapital.setFreezemoney(0.0d);
            usercapital.setStatus(UserFinance_Type.未支付.getValue());
            usercapitalMapper.insert(usercapital);
            return usercapital;
        }
    }

    /**
     * 添加新会员的账户信息
     * @see com.yinlian.wssc.web.service.UsercapitalService#addUsercapital(com.yinlian.wssc.web.po.Usercapital)
     */
    @Override
    public int addUsercapital(Usercapital usercapital) throws Exception {

        return usercapitalMapper.insert(usercapital);
    }

    /**
     * 账户充值
     * @see com.yinlian.wssc.web.service.UsercapitalService#recharge(java.lang.Float, java.lang.Integer)
     */
    @Override
    public int recharge(Double money1, String type, Integer userid, String userip) throws Exception {
        Usercapital usercapital = new Usercapital();
        UsercapitalExample example = new UsercapitalExample();
        UsercapitalExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        List<Usercapital> list = usercapitalMapper.selectByExample(example);

        int result = 0;
        if (list.size() <= 0) {
            if ("0".equals(type)) { // 账户余额
                usercapital.setBalance(money1);
                usercapital.setUserid(userid);
                usercapital.setBond(StringUtilsEX.ToDouble("0"));
                usercapital.setFreezemoney(StringUtilsEX.ToDouble("0"));
                usercapital.setStatus(1);
                result = usercapitalMapper.insert(usercapital);
                // 资金变动记录
                financeAdd(userid, money1, money1, "会员充值", "", userip,
                    CapitalChange_Type.充值.getValue(), "", UserFinance_Type.已支付.getValue(),FinanceTypeEnum.充值.getValue());
            } else if ("1".equals(type)) { //保证金
                usercapital.setBond(money1);
                usercapital.setBalance(StringUtilsEX.ToDouble("0"));
                usercapital.setUserid(userid);
                usercapital.setFreezemoney(StringUtilsEX.ToDouble("0"));
                usercapital.setStatus(1);
                result = usercapitalMapper.insert(usercapital);
            }
        } else {
            int id = list.get(0).getId();
            usercapital = usercapitalMapper.selectByPrimaryKey(id);
            
            BigDecimal b1 = new BigDecimal(money1);
    		BigDecimal b2 = new BigDecimal(usercapital.getBalance());
    		BigDecimal b3 = new BigDecimal(usercapital.getBond());
    		
            double money = b1.add(b2).doubleValue();
            if ("0".equals(type)) { // 账户余额
                usercapital.setBalance(money);
                result = usercapitalMapper.updateByPrimaryKey(usercapital);
                // 资金变动记录
                financeAdd(userid, money, money1, "会员充值", "", userip,
                    CapitalChange_Type.充值.getValue(), "", UserFinance_Type.已支付.getValue(),FinanceTypeEnum.充值.getValue());
            } else if ("1".equals(type)) { //保证金
            	double bmoney =  b1.add(b3).doubleValue();
                usercapital.setBond(bmoney);
            }
            result = usercapitalMapper.updateByPrimaryKey(usercapital);
            return result;
        }

        return result;
    }

    /**
     * 资金变动记录
     * 
     * @param userid    用户id
     * @param balance   用户余额
     * @param money     发生金额 
     * @param desc		描述
     * @param paynum	支付单号
     * @param userip	用户ip
     * @param type		类型	
     * @param ordercode 订单编号
     * @param status    状态
     * @return
     * @throws Exception
     */
    private int financeAdd(int userid, double balance, double money, String desc, String paynum,
                           String userip, int type, String ordercode, int status,int financetype) throws Exception {
        Userfinance userfinance = new Userfinance();
        userfinance.setUserid(userid);
        userfinance.setCreatetime(new Date());
        userfinance.setBalance(balance);
        userfinance.setMoney(money);
        userfinance.setDescription(desc);
        userfinance.setPaynum(paynum);
        userfinance.setUserip(userip);
        userfinance.setType(type);
        userfinance.setNumber(ordercode);
        userfinance.setStatus(status);
        userfinance.setFinancetype(financetype);
        return userfinanceMapper.insert(userfinance);
    }

    /**
     * 根据userid删除会员的账户表
     * @see com.yinlian.wssc.web.service.UsercapitalService#deleteMemberByUserId(java.lang.Integer)
     */
    @Override
    public int deleteMemberByUserId(Integer userid) throws Exception {
        UsercapitalExample example = new UsercapitalExample();
        UsercapitalExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        return usercapitalMapper.deleteByExample(example);
    }

    /**
     * 根据id查询
     * @see com.yinlian.wssc.web.service.UsercapitalService#queryById(java.lang.Integer)
     */

    @Override
    public Usercapital queryById(Integer id) throws Exception {
        return usercapitalMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改账户冻结状态
     * @see com.yinlian.wssc.web.service.UsercapitalService#updatStatus(com.yinlian.wssc.web.po.Usercapital)
     */

    @Override
    public int updatStatus(Integer id, Integer status) throws Exception {
        return usercapitalMapper.updateStatus(id, status);
    }

}
