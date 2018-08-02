package com.mobile.application.service.endpoint;

import com.mobile.application.vo.CommonVO;

public interface IEndpointNoticeService {

	CommonVO qryNoticeList(String userId, String noticeType, String pageIndex, String indexSize);

	CommonVO qryNoticeCount(String userId, String noticeType);

	CommonVO readNotice(String pushNoticeId);
	
	CommonVO qryNoRedNoticeList(String userId, String noticeType);

}
