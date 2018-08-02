package com.mobile.application.service.notice;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mobile.application.vo.CommonVO;

public interface INoticeService {

	Map<String, Object> queryNotice(HttpSession session, int pageIndex, int pageSize,String startTime, String endTime, String userCode, String userName, String noticeType, String orgId, HttpServletRequest request);

	Map<String,Object> queryRoleByOrg(int pageIndex, int pageSize, String orgId);

	CommonVO saveNotice(HttpSession session, String receiveUser, String receiveOrg, String receiveRole, String noticeTitle, String textArea);

	Map<String, String> queryNoticeDetail(String noticeId);

	List<?> queryAllNotice(HttpSession session);

	CommonVO queryUserByRole(int pageIndex, int pageSize,
			String orgIds, String roleIds, String userName, String userCode);

}
