package com.mobile.application.service.activityPut;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.entity.TBasisActivity;
import com.mobile.application.entity.TBasisActivityCheck;
import com.mobile.application.entity.TBasisActivityFile;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

public interface IActivityService {

	CommonVO queryActivity(HttpSession session,int pageIndex, int pageSize,
			String startTime, String endTime, String activityName,
			String issuer, String status, String orgId,
			HttpServletRequest request);

	SessionVO getUser(HttpSession session);
	
	CommonVO queryActivityList(HttpSession session,int pageIndex, int pageSize,
			String startTime, String endTime, String activityName,
			String issuer, String status, String orgId,
			HttpServletRequest request);
	CommonVO queryCheckActivityList(HttpSession session,int pageIndex, int pageSize,
			String startTime, String endTime, String activityName,
			String issuer, String status, String orgId,
			HttpServletRequest request);
	
	CommonVO saveProductInfo(String orgId,TBasisActivity tBasisActivity,MultipartFile pic, String productId,HttpSession session);
	
	
	CommonVO saveAcitivityPic(TBasisActivityFile tBasisActivityFile,MultipartFile pic,HttpSession session,String activityCode);
	/**
	 * 审核意见
	 * @param check
	 * @param proguctid
	 * @param session
	 * @return
	 */
	CommonVO saveActivityCheck(TBasisActivityCheck check,String proguctid,HttpSession session);
	
	/**
	 * 查询单个活动
	 * @param proguctid
	 * @param session
	 */
	CommonVO queryActivityCheck(String proguctid,HttpSession session);
	
	List<?> queryPiclist(String activitycode);
	
	
	 String delOnPge(String picPath);
	 
	String setFacePage(String picPath,String activityid);
	
	
	JSONObject getActiviyOneByid(String activityId);
	 
	String  delactivityOne(String activityid);
	/**
	 * queryMaxListSize
	 * @param activityid 
	 * @return
	 */
	String queryPicListMax(String activityid);
	/**
	 *  update Activity Stasus  1 unsubmit 2  checking 3 nopass 4 nonocitc 5 newarriay
	 *  case a.STATUS when '1' then '未提交' when '2' then '审核中' when '3' then '审核未通过' when '4' then '未发布' when '5' then '已上架' when '6' then '已下架' end as STATUS
	 * @param activityid
	 * @return
	 */
	
	 String updateActivityStatus(String activityid,String status);
	 /**
	  *  query activity is check
	  * @param activityid
	  * @return
	  */
	 
	 String queryAcitvityCheckFlag(String activityid);

	Map<String, String> qryActivityOrgs(String activityId);

	CommonVO setTop(String activityid);
	
	String deleleActivityCheckInfo(String activitycode);
}
