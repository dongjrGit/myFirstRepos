package com.mobile.application.service.workbench.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.dao.IMessageDao;
import com.mobile.application.entity.TBasisMessage;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.service.workbench.IMessageService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;
import com.mobile.application.vo.tpush.MessageContent;
import com.techown.tpush.api.MessageResult;
import com.techown.tpush.api.TPushClient;
import com.techown.tpush.http.BaseURL;

@Service
public class MessageServiceImpl implements IMessageService {

	@Autowired
	IMessageDao messageDao;
	@Autowired
	TPushClient tPush;
	
	@Override
	@Transactional
	public CommonVO qryUnreadMessage(String userId, String sendUserId) {
		List<TBasisMessage> messageList = messageDao.qryUnreadMessage(userId, sendUserId);
		messageDao.updateReadMsg(userId, sendUserId);
		return new CommonVO(true, messageList, null);
	}

	@Override
	@Transactional
	public CommonVO saveMessage(TBasisMessage tBasisMessage, boolean pushFlag, SessionVO sessionVO) {
		tBasisMessage.setIsRead("0");
		tBasisMessage.setMessageTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		messageDao.saveMessage(tBasisMessage);
		if(pushFlag){
			//查询接收人信息
			TBasisUser receiveUser = (TBasisUser) messageDao.getByID(TBasisUser.class, tBasisMessage.getReceiveUserId());
			//风装消息内容
			MessageContent messageContent = new MessageContent("1", sessionVO.getUserCode(), sessionVO.getUserName(), receiveUser.getUserCode(), receiveUser.getUserName(), tBasisMessage.getMessageContent(), tBasisMessage.getMessageTime());
			JSONObject messageJson = JSONObject.fromObject(messageContent);
			//调用TPush
			MessageResult messageResult = tPush.sendMessageWithAlias(receiveUser.getUserCode(), "测试消息", messageJson.toString());
			if(0 != messageResult.getErrcode()){
				return new CommonVO(false, "消息保存成功，但推送失败。");
			}
		}
		return new CommonVO(true, "发送成功");
	}
	
	@Override
	@Transactional
	public CommonVO qryUsers(String userCode, String userName, String pageIndex, String pageSize) {
		String userCount = messageDao.qryUserCount(userCode, userName);
		List<?> userList = messageDao.qryUser(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), userCode, userName);
		return new CommonVO(true, userList, userCount);
	}
}
