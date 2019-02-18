package com.yinlian.wssc.web.service.impl;

import java.text.MessageFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.FinanceType;
import com.yinlian.Enums.FinanceTypeEnum;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.mapper.HistorybeansMapper;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.po.Historybeans;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.HistoryService;
import com.yinlian.wssc.web.util.GetIpAddresss;
import com.yl.soft.log.LogHandle;

@Component("historyService")
public class HistoryServiceImpl implements HistoryService {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServiceImpl.class);

    @Autowired
    private HistorybeansMapper  historybeansMapper;

    @Autowired
    private UserfinanceMapper   userfinanceMapper;

    @Autowired
    private UsersMapper         UsersMapper;

    @Override
    public int addexchangePoint(int userid, int points, int beans, int remainPoint)
                                                                                   throws Exception {

        Users users = UsersMapper.selectByPrimaryKey(userid);

        users.setPoints(users.getPoints() - points + remainPoint);
        users.setTotalbeans(users.getTotalbeans() + beans);
        users.setAvailablebeans(users.getAvailablebeans() + beans);

        int temp1 = UsersMapper.updateByPrimaryKeySelective(users);

        //用户资金变更表插入一条精彩豆记录
        Userfinance userfinance = new Userfinance();
        userfinance.setUserid(userid);
        userfinance.setBalance((double) (users.getAvailablebeans()));
        userfinance.setMoney((double) (beans));
        userfinance.setType(FinanceTypeEnum.充值.getValue());
        userfinance.setCreatetime(new Date());
        userfinance.setStatus(UserFinance_Type.已支付.getValue());
        userfinance.setUserip(GetIpAddresss.getIpAddr());
        userfinance.setDescription("积分兑换经彩豆");
        userfinance.setFinancetype(FinanceType.经采豆.getValue());
        int temp2 = userfinanceMapper.insertSelective(userfinance);

        //用户资金变更表插入一条积分记录
        Userfinance userfinance2 = new Userfinance();
        userfinance2.setUserid(userid);
        userfinance2.setBalance((double) (users.getPoints()));
        userfinance2.setMoney((double) (points - remainPoint));
        userfinance.setType(FinanceTypeEnum.充值.getValue());
        userfinance2.setCreatetime(new Date());
        userfinance2.setStatus(UserFinance_Type.已支付.getValue());
        userfinance2.setUserip(GetIpAddresss.getIpAddr());
        userfinance2.setDescription("积分兑换经彩豆");
        userfinance2.setFinancetype(FinanceType.积分.getValue());
        int temp3 = userfinanceMapper.insertSelective(userfinance2);

        //历史兑换记录表插入一条数据
        Historybeans historybeans = new Historybeans();
        historybeans.setUserid(userid);
        historybeans.setBeans(beans);
        historybeans.setCreattime(new Date());
        historybeans.setPoints((points - remainPoint));
        int temp4 = historybeansMapper.insertSelective(historybeans);

        try {//TODO： 原支撑系统 要修改
           /* Map<String, Object> map = WebserviceClient.exchangeBeans(WebServiceTypeEnum.经彩豆兑换,
                users.getUsername(), String.valueOf(beans), ExchangeType.增加经彩豆);
            if (map != null && "1".equals(map.get("ResultCode"))) {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice经彩豆兑换! 提交参数信息:{0},Webservice账户注册返回信息:{0},{1}", beans,
                    map.get("ResultCode"), map.get("ResultMsg")),
                    "historyServiceImpl/addexchangePoint");
            } else {
                LogHandle.error(LogType.WEBSEVICE, MessageFormat.format(
                    "Webservice经彩豆兑换错误! 提交参数信息:{0},{1},Webservice账户注册返回信息:{0},{1}", beans,
                    map.get("ResultCode"), map.get("ResultMsg")),
                    "historyServiceImpl/addexchangePoint");
            }*/
        } catch (Exception e) {
            LogHandle.error(LogType.WEBSEVICE,
                MessageFormat.format("Webservice经彩豆兑换异常! 异常信息:{0}", e.getMessage()),
                "historyServiceImpl/addexchangePoint");
        }

        if ((temp1 > 0) && (temp2 > 0) && (temp3 > 0) && (temp4 > 0)) {
            return 1;
        } else {
            return 0;
        }
    }

}
