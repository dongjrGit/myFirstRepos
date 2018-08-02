package com.mobile.application.service.notice.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.dao.INoticeDao;
import com.mobile.application.entity.TBasisNotice;
import com.mobile.application.entity.TBasisNoticePushlist;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisRole;
import com.mobile.application.entity.TBasisRoleNotice;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.service.notice.INoticeService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;
import com.mobile.application.vo.tpush.MessageContent;
import com.techown.tpush.api.MessageResult;
import com.techown.tpush.api.MsgTypeEnum;
import com.techown.tpush.api.TPushClient;

@Service
public class NoticeServiceImpl implements INoticeService {

	@Autowired
	private INoticeDao noticeDao;
	@Autowired
	TPushClient tPush;

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> queryNotice(HttpSession session,int pageIndex, int pageSize,String startTime, String endTime, String userCode, String userName, String noticeType, String orgId, HttpServletRequest request){
		SessionVO sessionvo = (SessionVO)session.getAttribute("sessionVO");
		TBasisUser user = (TBasisUser)this.noticeDao.getByID(TBasisUser.class, sessionvo.getUserId());
		List<?> list = this.noticeDao.queryNotice(user,pageIndex,pageSize,startTime,endTime,userCode,userName,noticeType,orgId);
		List<?> listSize = this.noticeDao.queryNotice(user,startTime,endTime,userCode,userName,noticeType,orgId);
		List<?> noticeList = this.noticeDao.queryNotice(user,null, null,null, null,null, null);
		request.setAttribute("noticeList", noticeList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", list);
		map.put("total",listSize.size());
		return map;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String,Object> queryRoleByOrg(int pageIndex, int pageSize, String orgIds) {
		List<?> list = this.noticeDao.queryRoleByOrg(pageIndex,pageSize,orgIds);
		String count = this.noticeDao.queryRoleByOrg(orgIds);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", list);
		map.put("total", count);
		return map;
	}
	
	@Transactional
	public CommonVO saveNotice(HttpSession session, String receiveUser, String receiveOrg, String receiveRole, String noticeTitle, String noticeContent) {
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		String noticeType = StringUtils.isNotBlank(receiveUser) ? "2" : "1";
		TBasisNotice notice = new TBasisNotice(noticeTitle, noticeContent, DateUtil.format(new Date()), noticeType, new TBasisUser(sessionVO.getUserId()));
		this.noticeDao.save(notice);
		//通过选择的机构和角色，获取到机构角色关系列表
		//if(StringUtils.isBlank(receiveUser)){
			List<Map<String, String>> tBasisOrgRoles = (List<Map<String, String>>) noticeDao.qryOrgRoleRalate(receiveOrg, receiveRole);
			for (Map<String, String> tBasisOrgRole : tBasisOrgRoles) {
				TBasisOrg tBasisOrg = new TBasisOrg();
				tBasisOrg.setOrgId(tBasisOrgRole.get("orgId"));
				TBasisRoleNotice tBasisRoleNotice = new TBasisRoleNotice(tBasisOrg, new TBasisRole(tBasisOrgRole.get("roleId")), notice);
				noticeDao.save(tBasisRoleNotice);
			}
		//}
		List<Map<String, String>> pushUserList = null;
		//根据机构角色查询推送的用户
		if(StringUtils.isNotBlank(receiveUser)){
			pushUserList = (List<Map<String, String>>) noticeDao.queryUserByUserId(receiveUser);
		} else {
			pushUserList = (List<Map<String, String>>) noticeDao.queryUserByRole(0, 0, receiveOrg, receiveRole, null, null);
		}
		for (Map<String, String> pushUser : pushUserList) {
			TBasisNoticePushlist tBasisNoticePushlist = new TBasisNoticePushlist(notice, pushUser.get("id"), null);
			noticeDao.save(tBasisNoticePushlist);
			//风装消息内容
			//MessageContent messageContent = new MessageContent(noticeType, sessionVO.getUserCode(), sessionVO.getUserName(), noticeTitle, noticeContent, notice.getNoticeTime());
			//JSONObject messageJson = JSONObject.fromObject(messageContent);
			//调用TPush
			//MessageResult messageResult = tPush.sendMessageWithAlias(pushUser.get("userCode"), noticeTitle, messageJson.toString());
			//MessageResult messageResult = tPush.sendCustomMessageWithAlias(pushUser.get("userCode"), noticeTitle, messageJson.toString(), MsgTypeEnum.NOTIFY, "");
		}
		return new CommonVO(true,"发送成功");
	}

	@Override
	@Transactional
	public Map<String,String> queryNoticeDetail(String noticeId) {
		TBasisNotice tn = this.noticeDao.get(TBasisNotice.class, noticeId);
		Set<String> roleSet = new HashSet<String>();
		Set<String> orgSet = new HashSet<String>();
		Set<TBasisRoleNotice> TBasisRoleNotices = tn.getTBasisRoleNotices();
		for(TBasisRoleNotice tBasisRoleNotice:TBasisRoleNotices){
			roleSet.add(tBasisRoleNotice.getTBasisRole().getRoleName());
			orgSet.add(tBasisRoleNotice.getTBasisOrg().getOrgName());
		}
		JSONArray orgArray = JSONArray.fromObject(orgSet);
		JSONArray roleArray = JSONArray.fromObject(roleSet);
		JSONArray userArray = new JSONArray();
		if("2".equals(tn.getNoticeType())){
		List<?> pushlist = this.noticeDao.getPushListByNoticeId(noticeId);
		for(int i=0;i<pushlist.size();i++){
			userArray.add(pushlist.get(i));
		}
		}
		Map<String,String> map = new HashMap<String,String>();
        map.put("noticeId", tn.getNoticeId());
        map.put("noticeTitle", tn.getNoticeTitle());
        map.put("noticeTime", tn.getNoticeTime());
        map.put("noticeContent", tn.getNoticeContent());
        map.put("noticeOrg", orgArray.join(",",true));
        map.put("receiveRole", roleArray.join(",",true));
        map.put("receiveUser", userArray.join(",", true));
		return map;
	}

	@Override
	@Transactional
	public List<?> queryAllNotice(HttpSession session) {
		List<?> list = this.noticeDao.queryAllNotice();
		return list;
	}

	@Override
	@Transactional
	public CommonVO queryUserByRole(int pageIndex, int pageSize, String orgIds,
			String roleIds, String userName, String userCode) {
		String usercount = noticeDao.queryUserCount(orgIds, roleIds, userName, userCode);
		List<?> userList = noticeDao.queryUserByRole(pageIndex, pageSize, orgIds, roleIds, userName, userCode);
		return new CommonVO(true, userList, usercount);
	}
	
}
