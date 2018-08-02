package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisNotice;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.vo.CommonVO;

public interface INoticeDao extends IBaseDAO<TBasisNotice> {


	List<?> queryRoleByOrg(int pageIndex, int pageSize, String orgId);

	String queryRoleByOrg(String orgId);
	
	
	List<?> queryAllNotice();

	List<?> queryNotice(TBasisUser user, int pageIndex, int pageSize, String startTime,
			String endTime, String userCode, String userName,
			String noticeType, String orgId);

	List<?> queryUserByRole(int pageIndex, int pageSize, String orgIds,
			String roleIds, String userName, String userCode);

	List<?> queryNotice(TBasisUser user, String startTime, String endTime, String userCode,
			String userName, String noticeType, String orgId);

	String queryUserCount(String orgIds, String roleIds, String userName,
			String userCode);

	List<?> qryOrgRoleRalate(String orgIds, String roleIds);

	List<?> queryUserByUserId(String userIds);

	List<?> qryNoticeList(String userId, String noticeType, String pageIndex, String indexSize);

	String qryNoticeCount(String userId, String noticeType, boolean readFlag);

	void readNotice(String pushNoticeId);

	List<?> getPushListByNoticeId(String noticeId);
	
	List<?>  qryNoRedNoticeList(String userId, String noticeType); 
}
