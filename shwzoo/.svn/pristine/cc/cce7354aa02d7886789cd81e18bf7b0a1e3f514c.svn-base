/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.Api_MessageDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.MessagesMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.po.Messages;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.MessageService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaMessage;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;

/**
 * 站内信的业务实现类
 * MessageServiceImpl.java
 * @author Administrator
 * @version $Id: MessageServiceImpl.java, v 0.1 2016年3月21日 下午8:03:54 Administrator Exp $
 */
@Component("messageService")
public class MessageServiceImpl implements MessageService {

    /**
     * 日子类
     */
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
    @Autowired
    private MessagesMapper      messagesMapper;
    @Autowired
    private UsersMapper         usersMapper;

    /**
     * @see com.yinlian.wssc.web.service.MessageService#selectMessagesByUserIdPage(com.yinlian.wssc.web.util.CriteriaMessage, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public PageBean selectMessagesByUserIdPage(CriteriaMessage criteria, Integer pc, Integer ps)
                                                                                                throws Exception {

        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);//还可以 设置其他的参数  多条件查询
        PageBean pageBean = pageBeanUtil.getPage();
        List<Messages> beanList = messagesMapper.selectMessageByUserIdPage(pageBeanUtil);
        pageBean.setBeanList(beanList);
        return pageBean;
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageService#deleteById(java.lang.Integer)
     */
    @Override
    public int deleteById(Integer id) throws Exception {

        return messagesMapper.deleteByPrimaryKey(id);
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageService#deleteByIds(java.lang.String)
     */
    @Override
    public int deleteByIds(String ids) throws Exception {
        String[] array = ids.split(",");

        return messagesMapper.deleteByIds(array);
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageService#updateByIds(java.lang.String)
     */
    @Override
    public int updateByIds(String ids) throws Exception {
        String[] array = ids.split(",");
        return messagesMapper.updateByIds(array);
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageService#queryById(java.lang.Integer)
     */
    @Override
    public Messages queryById(Integer id) throws Exception {
        Messages record = messagesMapper.selectByPrimaryKey(id);
        record.setStatus(0);
        messagesMapper.updateByPrimaryKey(record);
        return record;
    }

    /**
     * @see com.yinlian.wssc.web.service.MessageService#insert(java.lang.String[], java.lang.String, java.lang.String)
     */
    @Override
    public int insertBacth(String[] array, String title, String content) throws Exception {
        List<Messages> list = new ArrayList<Messages>();
        for (String id : array) {
            Users users = usersMapper.selectByPrimaryKey(StringUtilsEX.ToInt(id));
            Messages messages = new Messages();
            messages.setUserid(StringUtilsEX.ToInt(id));
            messages.setContent(content);
            messages.setTitle(title);
            messages.setSendtime(new Date());
            messages.setSenduserid(1); // 平台的发送的id
            messages.setStatus(1); //表示未读的
            messages.setSendusername(users.getUsername());
            list.add(messages);
        }
        return messagesMapper.insertBacth(list);
    }

    /**
     * 根据userid获取消息
     * @see com.yinlian.wssc.web.service.MessageService#queryByUserId(java.lang.Integer)
     */
    @Override
    public List<Messages> queryByUserId(Integer userid) throws Exception {
       /* MessagesExample example = new MessagesExample();
        MessagesExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(userid);
        return messagesMapper.selectByExample(example);*/
    	return messagesMapper.selectByUserId(userid);
    }

    /**
     * 根据id更改站内信
     * @see com.yinlian.wssc.web.service.MessageService#updateById(com.yinlian.wssc.web.po.Messages)
     */
    @Override
    public int updateById(Messages messages) throws Exception {
        return messagesMapper.updateByPrimaryKey(messages);
    }
    
    /**
     * 未读消息数量
     */
	@Override
	public int getCount(int userid) throws Exception {
		
		return messagesMapper.selectCount(userid);
	}

	  @Override
	  public int deleteByUserid(int userid) throws Exception {

	        return messagesMapper.deleteByUserid(userid);
	    }

	@Override
	public PageBean getMessagesByUser(Criteria criteria, Integer page,
			Integer size) throws Exception {
		PageBeanUtil pageBeanUtil=new PageBeanUtil(criteria,page,size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Api_MessageDto> list=messagesMapper.getMessagesByUserPage(pageBeanUtil);
		pageBean.setBeanList(list);
		return pageBean;
	}
	@Override 
	public int updateSetMes(Integer uid) throws Exception{
		return messagesMapper.updateSetMes(uid);
	}
}
