/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.platform.controller;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.service.HistorybeansService;
import com.yinlian.wssc.web.util.CriteriaHistoryBeans;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.StringUtils;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

/**
 * 经采豆的控制类
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月26日 下午9:34:18 Exp $
 */
@Controller
@RequestMapping("/platform/beans")
public class BeansController {

    @Autowired
    private HistorybeansService historybeansService;

    @RequestMapping("/showHistorybeans")
    public String showHistorybeans() {

        return "platform/beans/historybean_list";
    }

    /**
     * 获取历史经采豆信息
     * 
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryHistorybeans")
    public @ResponseBody ReusltItem queryHistorybeans(String userid, String begin, String end,
                                                      String page, String size) {
        ReusltItem item = new ReusltItem();
        try {
            Integer pInteger = StringUtilsEX.ToInt(page);
            Integer sInteger = StringUtilsEX.ToInt(size);
            if (pInteger < 0 || sInteger < 0) {
                item.setCode(-101);
                item.setDesc("分页参数错误:page" + page + ",size" + size);
                return item;
            }
            CriteriaHistoryBeans criteria = new CriteriaHistoryBeans();
            Integer _userid = StringUtilsEX.ToInt(userid);
            if (_userid >= 0) {
                criteria.setUserid(_userid);
            }
            if (StringUtils.isNotNull(begin)) {
                criteria.setBegin(DateUtil.stringConvert(begin));
            }
            if (StringUtils.isNotNull(end)) {
                criteria.setBegin(DateUtil.stringConvert(end));
            }
            PageBean pageBean = historybeansService.selectPage(criteria, pInteger, sInteger);
            item.setCode(0);
            item.setData(pageBean.getBeanList());
            item.setMaxRow(pageBean.getTr());
            item.setPageIndex(pageBean.getPc());
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("获取历史经采豆信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User,
                MessageFormat.format(" 获取历史经采豆信息异常! 异常信息:{0}", e.getMessage()),
                "beans/queryHistorybeans");
        }
        return item;
    }

    /**
     * 删除
     *@param id
     *@return
     */
    @RequestMapping("/delete")
    public @ResponseBody ReusltItem delete(String id) {
        ReusltItem item = new ReusltItem();
        try {
            if (StringUtilsEX.ToInt(id) < 0) {
                item.setCode(-101);
                item.setDesc("参数id错误");
                return item;
            }
            if (historybeansService.deleteById(StringUtilsEX.ToInt(id)) > 0) {
                item.setCode(0);
                item.setDesc("删除成功");
            } else {
                item.setCode(-200);
                item.setDesc("删除失败");
                LogHandle.error(LogType.User, MessageFormat.format(" 删除历史经采豆信息失败! 参数信息:{id}", id),
                    "beans/delete");
            }
        } catch (Exception e) {
        	item.setCode(-900);
            if (DebugConfig.BLUETOOTH_DEBUG) {
            	item.setDesc("删除历史经采豆信息异常：" + e.getMessage());
			} else {
				item.setDesc("系统错误！");
			}
            LogHandle.error(LogType.User," 删除历史经采豆信息异常! 异常信息:{0}", e, "/platform/beans/delete");
        }
        return item;
    }
}
