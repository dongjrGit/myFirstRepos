/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.SessionUser;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.MessagerecordsMapper;
import com.yinlian.wssc.web.mapper.MessagesMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.po.Messagerecords;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.MessageRecordService;
import com.yinlian.wssc.web.util.CriteriaMessage;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.SmsUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yinlian.wssc.web.util.ZooSmsUtil;

/**
 * 短信记录接口实现类
 * MessageRecordServiceImpl.java
 * @author Administrator
 * @version $Id: MessageRecordServiceImpl.java, v 0.1 2016年3月22日 下午7:53:20 Administrator Exp $
 */
@Component("messageRecordService")
public class MessageRecordServiceImpl implements MessageRecordService {

    @Autowired
    private MessagerecordsMapper messagerecordsMapper;
    @Autowired
    private UsersMapper          usersMapper;
    @Autowired
    private MessagesMapper       messagesMapper;

    /**
     * @see com.yinlian.wssc.web.service.MessageRecordService#insertBacth(java.lang.String[], java.lang.String, java.lang.String)
     */
    @Override
    public int insertBacth(Integer type,SessionUser	sessionUser,String[] array, String title, String content) throws Exception {
        //List<Messagerecords> list = new ArrayList<Messagerecords>();
        List<Messages> list2 = new ArrayList<Messages>();
        int userid=sessionUser.getUserId();
		Users users = usersMapper.selectByPrimaryKey(userid);
        for (String id : array) {
            Messages messages = new Messages();
            messages.setUserid(StringUtilsEX.ToInt(id));
            messages.setContent(content);
            messages.setType(type);
            messages.setTitle(title);
            messages.setSendtime(new Date());
            messages.setSenduserid(userid); // 平台的发送的id
            messages.setStatus(1); //表示未读的
            messages.setSendusername(users.getUsername());
            list2.add(messages);

            /*Messagerecords messagerecords = new Messagerecords();
            messagerecords.setUserid(StringUtilsEX.ToInt(id));
            messagerecords.setContent(content);
            messagerecords.setSendtime(new Date());
            messagerecords.setTitle(title);
            messagerecords.setSenduser(1); //平台发送
            messagerecords.setType(2); //表示站内信
            messagerecords.setMobile(users.getMobile());
            list.add(messagerecords);*/
            //SmsUtil.sendMessage(users.getMobile(), content); //发送短息 上线使用
        }
        
        return messagesMapper.insertBacth(list2);
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageRecordService#selectMessagesListByPage(com.yinlian.wssc.web.util.CriteriaMessage, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectMessagesListByPage(CriteriaMessage criteria, Integer pc, Integer ps)
                                                                                              throws Exception {

        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
        PageBean pageBean = pageBeanUtil.getPage();
        List<Messagerecords> beanList = messagerecordsMapper
            .selectMessagerecordsByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageRecordService#deleteById(java.lang.Integer)
     */
    @Override
    public int deleteById(Integer id) throws Exception {

        return messagerecordsMapper.deleteByPrimaryKey(id);
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageRecordService#deleteBacth(java.lang.String[])
     */
    @Override
    public int deleteBacth(String[] array) throws Exception {

        return messagerecordsMapper.deleteByIds(array);
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageRecordService#insertList(java.lang.Integer, java.lang.String[], java.lang.String, java.lang.String[])
     */
    @Override
    public int insertList(SessionUser	sessionUser,Integer type, String[] moblieArray, String content, String[] idArray,
                          String title) throws Exception {
        List<Messagerecords> list = new ArrayList<Messagerecords>();
        int sendid = sessionUser.getId();
        for (int i = 0; i < idArray.length; i++) {
            Messagerecords messagerecords = new Messagerecords();
            messagerecords.setUserid(StringUtilsEX.ToInt(idArray[i]));
            messagerecords.setContent(content);
            messagerecords.setSendtime(new Date());
            messagerecords.setTitle(title);
            messagerecords.setSenduser(sendid); //平台发送  发送用户id可以从当前session中获取
            messagerecords.setType(type); //表示短信
            messagerecords.setMobile(moblieArray[i]);
            list.add(messagerecords);
            ZooSmsUtil.sendMessage(moblieArray[i], content); //发送短息 上线使用
        }
        return messagerecordsMapper.insertBacth(list);
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageRecordService#insertOne(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int insertOne(SessionUser	sessionUser,Integer type, String mobile, String content, Integer userid, String title)
                                                                                                   throws Exception {
    	int userids=sessionUser.getUserId();
        Messagerecords messagerecords = new Messagerecords();
        messagerecords.setUserid(userid);
        messagerecords.setContent(content);
        messagerecords.setSendtime(new Date());
        messagerecords.setTitle(title);
        messagerecords.setSenduser(userids); //平台发送  发送用户id可以从当前session中获取
        messagerecords.setType(type); //表示短信
        messagerecords.setMobile(mobile);
        ZooSmsUtil.sendMessage(mobile, content);  //发送短息 上线使用
        //SmsUtil.sendMessage(mobile, content);  //发送短息 上线使用
        return messagerecordsMapper.insert(messagerecords);
    }

    /**
     * 查询已发送短信
     * @see com.yinlian.wssc.web.service.MessageRecordService#selectMessagescordsListByPage(com.yinlian.wssc.web.util.CriteriaMessage, java.lang.Integer, java.lang.Integer)
     */
	@Override
	public PageBean selectMessagescordsListByPage(CriteriaMessage criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
        PageBean pageBean = pageBeanUtil.getPage();
        List<Messagerecords> beanList = messagerecordsMapper
            .selectMessagerecordByPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
	}
}
