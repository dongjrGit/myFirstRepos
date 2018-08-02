package com.mobile.application.service.workbench;

import com.mobile.application.entity.TBasisMessage;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

public interface IMessageService {

	CommonVO saveMessage(TBasisMessage bBasisMessage, boolean pushFlag, SessionVO sessionVO);

	CommonVO qryUsers(String userCode, String userName, String pageIndex,
			String pageSize);

	CommonVO qryUnreadMessage(String userId, String replyUserId);

}
