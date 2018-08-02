package com.mobile.application.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IActivityDao;
import com.mobile.application.entity.TBasisActivity;
import com.mobile.application.entity.TBasisActivityCheck;
import com.mobile.application.vo.CommonVO;
@Repository
public  class ActivityDaoImpl extends BaseDAOImpl<TBasisActivity> implements IActivityDao {

	@SuppressWarnings("rawtypes")
	@Override
	public List<?> qryActivityList(int pageIndex, int pageSize,
			String activityName, String orgId, String issuer, String status,
			String startTime, String endTime) {
		if(orgId==null){
			orgId="xz";
		}
		String sql = "select distinct a.ACTIVITY_ID as activityId,a.ACTIVITY_NAME as activityName,a.ISSUER as issuer,a.ACTIVITY_CODE as activityCode,a.ACTIVITY_START_TIME as activityStartTime,a.ACTIVITY_END_TIME as activityEndTime,a.ACTIVITY_PHOTO_NAME as activityPhotoName,a.ACTIVITY_PHOTO_LEN as activityPhotoLen,a.ACTIVITY_PHOTO_PATH as activityPhotoPath,case a.STATUS when '1' then '未提交' when '2' then '审核中' when '3' then '审核未通过' when '4' then '未发布' when '5' then '已上架' when '6' then '已下架' end as status,a.CREATE_TIME as createtime from T_BASIS_ACTIVITY a ";
		sql += " where a.ACTIVITY_CODE in (select ao.ACTIVITY_ID from T_BASIS_ACTIVITY_ORG ao where ao.ORG_ID = '"+orgId+"') ";
		if(StringUtils.isNotBlank(activityName)){
			sql += " and a.ACTIVITY_NAME like '%"+activityName+"%' ";
		}
		if(StringUtils.isNotBlank(issuer)){
			sql += " and a.ISSUER like '%"+issuer+"%' ";
		}
		if(StringUtils.isNotBlank(status)){
			sql += " and a.STATUS = '"+status+"' ";
		}
		if(StringUtils.isNotBlank(startTime)){
			sql += " and a.ACTIVITY_START_TIME = '"+startTime+"' ";
		}
		if(StringUtils.isNotBlank(endTime)){
			sql += " and a.ACTIVITY_END_TIME = '"+endTime+"' ";
		}
		sql += "order by a.CREATE_TIME desc";
		Query query = this.getCurrentSession().createSQLQuery(sql).setFirstResult((pageIndex)*pageSize).setMaxResults(pageSize);
		List lsit = query.list();
		return lsit;
	}

	
	@Override
	public List<?> qryActivitySize(String activityName, String orgId,
			String issuer, String status, String startTime, String endTime) {
		if(orgId==null){
			orgId="xz";
		}
	//	String sql = "select   a.ACTIVITY_ID as activityId,a.ACTIVITY_NAME as activityName,a.ISSUER as issuer,a.ACTIVITY_CODE as activityCode,a.ACTIVITY_START_TIME as activityStartTime from T_BASIS_ACTIVITY a ";
	//	sql += " where a.ACTIVITY_ID in (select ao.ACTIVITY_ID from T_BASIS_ACTIVITY_ORG ao where ao.ORG_ID = '"+orgId+"') ";
		String sql = "select distinct  a.ACTIVITY_ID as ACTIVITY_ID,a.ACTIVITY_NAME as ACTIVITY_NAME,a.ISSUER as ISSUER,a.ACTIVITY_CODE as ACTIVITY_CODE,a.ACTIVITY_START_TIME as ACTIVITY_START_TIME,a.ACTIVITY_END_TIME as ACTIVITY_END_TIME,a.ACTIVITY_PHOTO_NAME as ACTIVITY_PHOTO_NAME,a.ACTIVITY_PHOTO_LEN as ACTIVITY_PHOTO_LEN,a.ACTIVITY_PHOTO_PATH as ACTIVITY_PHOTO_PATH,case a.STATUS when '1' then '未提交' when '2' then '审核中' when '3' then '审核未通过' when '4' then '未发布' when '5' then '已上架' when '6' then '已下架' end as STATUS,a.CREATE_TIME as CREATE_TIME from T_BASIS_ACTIVITY a ";
		sql += " where a.ACTIVITY_CODE in (select ao.ACTIVITY_ID from  T_BASIS_ACTIVITY_ORG ao where ao.ORG_ID = '"+orgId+"') ";
		
		if(StringUtils.isNotBlank(activityName)){
			sql += " and a.ACTIVITY_NAME like '%"+activityName+"%' ";
		}
		if(StringUtils.isNotBlank(issuer)){
			sql += " and a.ISSUER like '%"+issuer+"%' ";
		}
		if(StringUtils.isNotBlank(status)){
			sql += " and a.STATUS = '"+status+"' ";
		}
		if(StringUtils.isNotBlank(startTime)){
			sql += " and a.ACTIVITY_START_TIME = '"+startTime+"' ";
		}
		if(StringUtils.isNotBlank(endTime)){
			sql += " and a.ACTIVITY_END_TIME = '"+endTime+"' ";
		}
		Query query = this.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

	@Override
	public List<?> qryCheckActivitySize(String activityName, String orgId,
			String issuer, String status, String startTime, String endTime) {
		if(orgId==null){
			orgId="xz";
		}
	//	String sql = "select   a.ACTIVITY_ID as activityId,a.ACTIVITY_NAME as activityName,a.ISSUER as issuer,a.ACTIVITY_CODE as activityCode,a.ACTIVITY_START_TIME as activityStartTime from T_BASIS_ACTIVITY a ";
	//	sql += " where a.ACTIVITY_ID in (select ao.ACTIVITY_ID from T_BASIS_ACTIVITY_ORG ao where ao.ORG_ID = '"+orgId+"') ";
		String sql = "select distinct  a.ACTIVITY_ID as ACTIVITY_ID,a.ACTIVITY_NAME as ACTIVITY_NAME,a.ISSUER as ISSUER,a.ACTIVITY_CODE as ACTIVITY_CODE,a.ACTIVITY_START_TIME as ACTIVITY_START_TIME,a.ACTIVITY_END_TIME as ACTIVITY_END_TIME,a.ACTIVITY_PHOTO_NAME as ACTIVITY_PHOTO_NAME,a.ACTIVITY_PHOTO_LEN as ACTIVITY_PHOTO_LEN,a.ACTIVITY_PHOTO_PATH as ACTIVITY_PHOTO_PATH,case a.STATUS when '1' then '未提交' when '2' then '审核中' when '3' then '审核未通过' when '4' then '未发布' when '5' then '已上架' when '6' then '已下架' end as STATUS,a.CREATE_TIME as CREATE_TIME from T_BASIS_ACTIVITY a ";
		sql += " where a.ACTIVITY_CODE in (select ao.ACTIVITY_ID from  T_BASIS_ACTIVITY_ORG ao where ao.ORG_ID = '"+orgId+"') and a.STATUS='2' ";
		
		if(StringUtils.isNotBlank(activityName)){
			sql += " and a.ACTIVITY_NAME like '%"+activityName+"%' ";
		}
		if(StringUtils.isNotBlank(issuer)){
			sql += " and a.ISSUER like '%"+issuer+"%' ";
		}
		if(StringUtils.isNotBlank(status)){
			sql += " and a.STATUS = '"+status+"' ";
		}
		if(StringUtils.isNotBlank(startTime)){
			sql += " and a.ACTIVITY_START_TIME = '"+startTime+"' ";
		}
		if(StringUtils.isNotBlank(endTime)){
			sql += " and a.ACTIVITY_END_TIME = '"+endTime+"' ";
		}
		Query query = this.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

	@Override
	public List<TBasisActivity> qryActivityListAll(int pageIndex, int pageSize,
			String activityName, String orgId, String issuer, String status,
			String startTime, String endTime) {
		if(orgId==null||"".equals(orgId)){
			orgId="xz";
		}
		String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//orgId = "xz" ;
		//String sql = "select distinct a.ACTIVITY_ID as activityId,a.ACTIVITY_NAME as activityName,a.ISSUER as issuer,a.ACTIVITY_CODE as activityCode,a.ACTIVITY_START_TIME as activityStartTime,a.ACTIVITY_END_TIME as activityEndTime,a.ACTIVITY_PHOTO_NAME as activityPhotoName,a.ACTIVITY_PHOTO_LEN as activityPhotoLen,a.ACTIVITY_PHOTO_PATH as activityPhotoPath,case a.STATUS when '1' then '未提交' when '2' then '审核中' when '3' then '审核未通过' when '4' then '未发布' when '5' then '已上架' when '6' then '已下架' end as status,a.CREATE_TIME as createtime from T_BASIS_ACTIVITY a ";
		//sql += " where a.ACTIVITY_ID in (select ao.ACTIVITY_ID from T_BASIS_ACTIVITY_ORG ao where ao.ORG_ID = '"+orgId+"') ";
		String sql = "select distinct a.ZIP_PATH as ZIP_PATH, a.ZIP_LENGTH as ZIP_LENGTH, a.ACTIVITY_ID as ACTIVITY_ID,a.ACTIVITY_NAME as ACTIVITY_NAME,a.ISSUER as ISSUER,a.ACTIVITY_CODE as ACTIVITY_CODE,a.ACTIVITY_START_TIME as ACTIVITY_START_TIME,a.ACTIVITY_END_TIME as ACTIVITY_END_TIME,a.ACTIVITY_PHOTO_NAME as ACTIVITY_PHOTO_NAME,a.ACTIVITY_PHOTO_LEN as ACTIVITY_PHOTO_LEN,a.ACTIVITY_PHOTO_PATH as ACTIVITY_PHOTO_PATH,case when a.STATUS='1' then '未提交' when a.STATUS='2' then '审核中' when a.STATUS='3' then '审核未通过' when a.STATUS='6' then '已下架'  when (a.STATUS='4' and a.ACTIVITY_END_TIME<'" + nowDate + "') then '已下架'  when (a.STATUS='4' and a.ACTIVITY_START_TIME>'" + nowDate + "') then '未发布'  when a.STATUS='4' then '已上架' end as STATUS,a.CREATE_TIME as CREATE_TIME,a.UPDATETIME, a.UPDATEUSER from T_BASIS_ACTIVITY a ";
		sql += " where a.ACTIVITY_CODE in (select ao.ACTIVITY_ID from  T_BASIS_ACTIVITY_ORG ao where ao.ORG_ID = '"+orgId+"') ";
		
		if(StringUtils.isNotBlank(activityName)){
			sql += " and a.ACTIVITY_NAME like '%"+activityName+"%' ";
		}
		if(StringUtils.isNotBlank(issuer)){
			sql += " and a.ISSUER like '%"+issuer+"%' ";
		}
		if(StringUtils.isNotBlank(status)){
			sql += " and a.STATUS = '"+status+"' ";
		}
		if(StringUtils.isNotBlank(startTime)){
			sql += " and a.ACTIVITY_START_TIME >= '"+startTime+"' ";
		}
		if(StringUtils.isNotBlank(endTime)){
			sql += " and a.ACTIVITY_END_TIME <= '"+endTime+"' ";
		}
		sql += "order by a.CREATE_TIME desc";

		@SuppressWarnings("unchecked")
		List<TBasisActivity> lsit = this.getCurrentSession().createSQLQuery(sql)
				.addEntity(TBasisActivity.class)
				.setFirstResult((pageIndex)*pageSize).setMaxResults(pageSize).list();
		return lsit;
	}

	@Override
	public List<TBasisActivity> qryCheckActivityListAll(int pageIndex, int pageSize,
			String activityName, String orgId, String issuer, String status,
			String startTime, String endTime) {
		if(orgId==null||"".equals(orgId)){
			orgId="xz";
		}
		String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		//orgId = "xz" ;
		//String sql = "select distinct a.ACTIVITY_ID as activityId,a.ACTIVITY_NAME as activityName,a.ISSUER as issuer,a.ACTIVITY_CODE as activityCode,a.ACTIVITY_START_TIME as activityStartTime,a.ACTIVITY_END_TIME as activityEndTime,a.ACTIVITY_PHOTO_NAME as activityPhotoName,a.ACTIVITY_PHOTO_LEN as activityPhotoLen,a.ACTIVITY_PHOTO_PATH as activityPhotoPath,case a.STATUS when '1' then '未提交' when '2' then '审核中' when '3' then '审核未通过' when '4' then '未发布' when '5' then '已上架' when '6' then '已下架' end as status,a.CREATE_TIME as createtime from T_BASIS_ACTIVITY a ";
		//sql += " where a.ACTIVITY_ID in (select ao.ACTIVITY_ID from T_BASIS_ACTIVITY_ORG ao where ao.ORG_ID = '"+orgId+"') ";
		String sql = "select distinct a.ZIP_PATH as ZIP_PATH, a.ZIP_LENGTH as ZIP_LENGTH, a.ACTIVITY_ID as ACTIVITY_ID,a.ACTIVITY_NAME as ACTIVITY_NAME,a.ISSUER as ISSUER,a.ACTIVITY_CODE as ACTIVITY_CODE,a.ACTIVITY_START_TIME as ACTIVITY_START_TIME,a.ACTIVITY_END_TIME as ACTIVITY_END_TIME,a.ACTIVITY_PHOTO_NAME as ACTIVITY_PHOTO_NAME,a.ACTIVITY_PHOTO_LEN as ACTIVITY_PHOTO_LEN,a.ACTIVITY_PHOTO_PATH as ACTIVITY_PHOTO_PATH,case when a.STATUS='1' then '未提交' when a.STATUS='2' then '审核中' when a.STATUS='3' then '审核未通过' when a.STATUS='6' then '已下架'  when (a.STATUS='4' and a.ACTIVITY_END_TIME<'" + nowDate + "') then '已下架'  when (a.STATUS='4' and a.ACTIVITY_START_TIME>'" + nowDate + "') then '未发布'  when a.STATUS='4' then '已上架' end as STATUS,a.CREATE_TIME as CREATE_TIME,a.UPDATETIME, a.UPDATEUSER from T_BASIS_ACTIVITY a ";
		sql += " where a.ACTIVITY_CODE in (select ao.ACTIVITY_ID from  T_BASIS_ACTIVITY_ORG ao where ao.ORG_ID = '"+orgId+"') and a.STATUS='2' ";
		
		if(StringUtils.isNotBlank(activityName)){
			sql += " and a.ACTIVITY_NAME like '%"+activityName+"%' ";
		}
		if(StringUtils.isNotBlank(issuer)){
			sql += " and a.ISSUER like '%"+issuer+"%' ";
		}
		if(StringUtils.isNotBlank(status)){
			sql += " and a.STATUS = '"+status+"' ";
		}
		if(StringUtils.isNotBlank(startTime)){
			sql += " and a.ACTIVITY_START_TIME >= '"+startTime+"' ";
		}
		if(StringUtils.isNotBlank(endTime)){
			sql += " and a.ACTIVITY_END_TIME <= '"+endTime+"' ";
		}
		sql += "order by a.CREATE_TIME desc";

		@SuppressWarnings("unchecked")
		List<TBasisActivity> lsit = this.getCurrentSession().createSQLQuery(sql)
				.addEntity(TBasisActivity.class)
				.setFirstResult((pageIndex)*pageSize).setMaxResults(pageSize).list();
		return lsit;
	}


	@Override
	public CommonVO saveProductPic(String productId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<?> queryPiclist(String activitycode) {
		List<?> listpiclist = this.getCurrentSession().createQuery("from TBasisActivityFile as P where P.activityId=:activitycode ").setParameter("activitycode", activitycode).list();
		return listpiclist;
	}


	@Override
	public String delOnPge(String picPath) {
		this.getCurrentSession().createSQLQuery("delete from T_BASIS_ACTIVITY_FILE where FILEPATH=:picPath").setParameter("picPath", picPath).executeUpdate();
		return null;
	}


	@Override
	public String setFacePage(String picPath, String activityid) {
		this.getCurrentSession().createSQLQuery("update T_BASIS_ACTIVITY_FILE set TYPE=null where ACTIVITY_ID=:activityid ").setParameter("activityid", activityid).executeUpdate();
		return this.getCurrentSession().createSQLQuery("update T_BASIS_ACTIVITY_FILE set TYPE='1' where FILEPATH=:picPath ").setParameter("picPath", picPath).executeUpdate()+"";
	
	}


	@Override
	public JSONObject getActiviyOneByid(String activityId) {
		JSONObject map = new JSONObject();
		TBasisActivity tBasisActivity= this.get(TBasisActivity.class, activityId);
		JSONObject objectactivity = JSONObject.fromObject(tBasisActivity);
		map.put("tBasisActivity", objectactivity);
		@SuppressWarnings("unchecked")
		List<TBasisActivityCheck> listcheck = this.getCurrentSession()
													//.createSQLQuery("from TBasisActivityCheck as C where C.activityId=:activitycode")
														 .createSQLQuery("SELECT ID, ACTIVITY_ID, OPINION, CHECK_TIME,(select USER_NAME from T_BASIS_USER where USER_CODE=C.CHECK_USER) as  CHECK_USER, case C.STATUS when '1' then '通过'  when '2' then '未通过' end as STATUS   FROM T_BASIS_ACTIVITY_CHECK C WHERE C.ACTIVITY_ID=:activitycode")
														 .addEntity(TBasisActivityCheck.class)
														 .setParameter("activitycode", tBasisActivity.getActivityCode())
															.list();
	/*	List<TBasisActivityFile> listfile =this.getCurrentSession()
													.createQuery("from TBasisActivityFile as C where C.activityId=:activitycode")
															.setParameter("activitycode", tBasisActivity.getActivityCode())
																	.list();*/
		JSONArray checklistarray = JSONArray.fromObject(listcheck);
		//JSONArray activityfilearray = JSONArray.fromObject(listfile);
		map.put("checklistarray", checklistarray);
		//map.put("activityfilearray", activityfilearray);
		return map;
	}

	@Override
	public List<Map<String, String>> qryActivityOrgs(String activityId){
		String hql = "select distinct  new map(a.orgId as orgId, b.orgName as orgName) from TBasisActivityOrg a,TBasisOrg b where a.orgId=b.orgId and a.activityId=:activityId";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("activityId", activityId);
		return query.list();
	}

	@Override
	public String delactivityOne(String activityid) {
		this.getCurrentSession().createSQLQuery("delete from T_BASIS_ACTIVITY where ACTIVITY_ID=:activityid").setParameter("activityid", activityid).executeUpdate();
		return null;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public String queryPicListMax(String activityid) {
	 List listPicMax =  this.getCurrentSession()
			 	.createQuery("from TBasisActivityFile as P where P.activityId=:activityid")
			 			.setParameter("activityid", activityid)
			 			.list();
		return listPicMax.size()+"";
	}


	@Override
	public String updateActivityStatus(String activityid,String status) {
	int j = this.getCurrentSession().createSQLQuery("update  T_BASIS_ACTIVITY A set A.STATUS=:status  where ACTIVITY_ID=:activityid")
						.setParameter("activityid", activityid)
							.setParameter("status", status)
								.executeUpdate();
		return j + "";
	}


	@SuppressWarnings("unchecked")
	@Override
	public String queryAcitvityCheckFlag(String activityid) {
		List<TBasisActivityCheck> listcheck = this.getCurrentSession()
					 .createSQLQuery("SELECT ID, ACTIVITY_ID, OPINION, CHECK_TIME,(select USER_NAME from T_BASIS_USER where USER_CODE=C.CHECK_USER) as  CHECK_USER, case C.STATUS when '1' then '通过'  when '2' then '未通过' end as STATUS   FROM T_BASIS_ACTIVITY_CHECK C WHERE C.ACTIVITY_ID=:activitycode")
					 .addEntity(TBasisActivityCheck.class)
					 .setParameter("activitycode", activityid)
						.list();
		return listcheck.size()+"";
	}
	@Override
	public String updateActivityStatusByCode(String activityid,String status) {
	int j = this.getCurrentSession().createSQLQuery("update  T_BASIS_ACTIVITY A set A.STATUS=:status  where ACTIVITY_CODE=:activityid")
						.setParameter("activityid", activityid)
							.setParameter("status", status)
								.executeUpdate();
		return j + "";
	}


	@Override
	public TBasisActivity updateActivityByCode(String activitycode) {
		TBasisActivity tBasisActivity = (TBasisActivity) this.getCurrentSession()
			 		.createQuery("from TBasisActivity as P where P.activityCode=:activitycode")
	 				.setParameter("activitycode", activitycode)
	 				.uniqueResult();
		return tBasisActivity;
	}


	@Override
	public String delActivtyByOrgCode(String activitycode) {
		int j = this.getCurrentSession().createSQLQuery("delete from T_BASIS_ACTIVITY_ORG A  where A.ACTIVITY_ID=:activitycode")
				.setParameter("activitycode", activitycode)
						.executeUpdate();
		return j + "";
	}


	@Override
	public String deleleActivityCheckInfo(String activitycode) {
		int j =	this.getCurrentSession().createSQLQuery("delete from T_BASIS_ACTIVITY_CHECK A  where A.ACTIVITY_ID=:activitycode")
		.setParameter("activitycode", activitycode)
				.executeUpdate();
		return j+"";
	}



}
