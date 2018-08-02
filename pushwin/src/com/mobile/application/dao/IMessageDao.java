package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisMessage;

public interface IMessageDao  extends IBaseDAO<TBasisMessage>{

	public void saveMessage(TBasisMessage tBasisMessage);

	String qryUserCount(String userCode, String userName);

	List<?> qryUser(int indexPage, int indexSize, String userCode,
			String userName);

	void updateReadMsg(String userId, String sendUserId);

	List<TBasisMessage> qryUnreadMessage(String userId, String sendUserId);

}
