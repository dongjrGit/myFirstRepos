package com.mobile.application.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.mobile.application.entity.TBasisActivity;
import com.mobile.application.vo.CommonVO;

public interface IActivityDao extends IBaseDAO<TBasisActivity> {

	List<?> qryActivityList(int pageIndex, int pageSize, String activityName,
			String orgId, String issuer, String status, String startTime,
			String endTime);

	List<?> qryActivitySize(String activityName, String orgId, String issuer,
			String status, String startTime, String endTime);
	
	List<TBasisActivity> qryCheckActivityListAll(int pageIndex, int pageSize, String activityName,
			String orgId, String issuer, String status, String startTime,
			String endTime);
	
	List<?> qryCheckActivitySize(String activityName, String orgId, String issuer,
			String status, String startTime, String endTime);

	
	List<TBasisActivity> qryActivityListAll(int pageIndex, int pageSize, String activityName,
			String orgId, String issuer, String status, String startTime,
			String endTime);
	
	CommonVO saveProductPic (String productId);
	List<?> queryPiclist(String activitycode);
	String delOnPge(String picPath);
	
	String setFacePage(String picPath,String activityid);
	
	JSONObject getActiviyOneByid(String activityId);
	
	String delactivityOne(String activityid);
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
	 
	 /**
	  *  query activity is check
	  * @param activityid
	  * @return
	  */
	 
	 String updateActivityStatusByCode(String activityid,String status);
	 
	 
	 /**
	  *  query activity is check
	  * @param activitycode
	  * @return
	  */
	 
	 TBasisActivity updateActivityByCode(String activitycode);
	 /**
	  * dele activity by activitycode
	  * @param activitycode
	  * @return
	  */
	 
	String  delActivtyByOrgCode(String activitycode);

	List<Map<String, String>> qryActivityOrgs(String activityId);
	/**
	 * delete activityCheckInfo
	 * @param activitycode
	 * @return
	 */
	
	String deleleActivityCheckInfo(String activitycode);

}