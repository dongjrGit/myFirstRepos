package com.mobile.application.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.util.StreamUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.controller.endpoint.EndpointController;
import com.mobile.application.dao.IEndpointDao;
import com.mobile.application.entity.Paduser;
import com.mobile.application.entity.Puwshlogin;
import com.mobile.application.entity.TBasisActivity;
import com.mobile.application.entity.TBasisActivityFile;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.njbank.entity.NjDatCity;
import com.mobile.application.njbank.entity.NjDatDistrict;
import com.mobile.application.njbank.entity.NjDatProvince;
import com.mobile.application.until.SqlUtil;
import com.mobile.application.vo.activity.ActivityPicVO;
import com.mobile.application.vo.activity.ActivityResponseVO;
import com.mobile.application.vo.activity.ActivityVO;


@Repository
public class EndpointDaoImpl extends BaseDAOImpl<TBasisUser> implements
		IEndpointDao {
	private static Logger logger4j = Logger.getLogger(EndpointDaoImpl.class);


	/**
	 * 通过设备pin号判断该设备是否出库
	 */
	public List<?> IsDeviceOut(String devicePin) {
		String hql = "from TBasisDevice td where td.pinId = '" + devicePin
				+ "' and td.padStatus = '0'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list = hqlQuery.list();
		return list;
	}

	@Override
	public TBasisUser getUserByCode(String userCode) {
		String hql = "from TBasisUser u where u.userCode =:userCode  and u.isDisable <> 'true'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("userCode", userCode);
		TBasisUser tBasisUser = (TBasisUser) hqlQuery.uniqueResult();
		return tBasisUser;
	}

	@Override
	public Paduser puwshlogin(Puwshlogin puwshlogin) {
		Paduser user = (Paduser) this
				.getCurrentSession()
				.createQuery(
						"from Paduser p where p.userid=:userid and p.password=:pwd and p.status='1'")
				.setParameter("userid", puwshlogin.getAccess_id())
				.setParameter("pwd", puwshlogin.getPassword()).uniqueResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String iosVersion(String bankFlag) {
		// 获取版本信息
//		String hqlString="select new map(version,upFlag) from ( from TBasisVersion where bank='"+bankFlag+"' order by id desc) where rownum <=2";
		String hqlString="select new map(tbv.version as version,tbv.upFlag as upFlag) from TBasisVersion tbv where tbv.bank='"+bankFlag+"' order by to_number(id) desc";
		Query result = this.getCurrentSession().createQuery(hqlString);
		String iosVersion="";
		List<Map<String, String>> list=result.list();
		if(list.size()>1){
			
			iosVersion=list.get(0).get("version")+":"+list.get(0).get("upFlag")
					+","+
					list.get(1).get("version")+":"+list.get(1).get("upFlag");
		}
		
		return iosVersion;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean qrydevice(String userId,String pinId) {
		// 获取版本信息
//		String hqlString="select version,upFlag from (select version,upFlag from TBasisVersion where bank='"+bankFlag+"' order by id desc) where rownum <=2";
		String hql="from TBasisDevice tbd where tbd.tBasisUser.userId=:userId and tbd.pinId=:pinId";
		Query result = this.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId).setParameter("pinId", pinId);
		List<Map<String, String>> list=result.list();
		if(list.size()>0){
			return true;
		}
		
		return false;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> Parameter(Object lastUpdateTimeO) {
		JSONObject resJson = JSONObject.fromObject(lastUpdateTimeO);
		String lastUpdateTime = resJson.optString("lastUpdateTime");
		String usercode = resJson.optString("usercode");
		String bankFlag = resJson.optString("bankFlag");
		String parematerflag = SpringProperty.props
				.getProperty("parematerflag");
		Map<String, Object> map = new HashMap<String, Object>();
		
		String updateTimeHqlCompabymemo = "select new map(max(updatetime) as updateTime) from TBasisDictCompabymemo";
		String updateTimeHqlStores = "select new map(max(updatetime) as updateTime) from TBasisBusStores";
		String updateTimeHqlPlanType = "select new map(max(updatetime) as updateTime) from TBasisBusPlantype";
		String updateTimeHqlPlanStore = "select new map(max(updatetime) as updateTime) from TBasisBusPlanstore";
		String updateTimeHqlIgroup = "select new map(max(updateTime) as updateTime) from TBasisBusIgroup";
		String updateTimeHqlstoreGoods = "select new map(max(updatetime) as updateTime) from TBasisBusStoregoogs";
		String updateTimeHqlPlanstoregood = "select new map(max(updatetime) as updateTime) from TBasisBusPlanstoregood";
		String updateTimeHqlsingBranch = "select new map(max(createDate) as updateDate) from SignBranch";
		
		String updateTimeHqluserstore = "select new map(max(updatetime) as updatetime) from TBasisBusUserStore";
		String updateTimeHqlBusBank = "select new map(max(updaTime) as updaTime) from TBasisBusBank";// 受理机构
		String updateTimeHqlBusSubbank = "select new map(max(updaTime) as updaTime) from TBasisBusSubbank";// 受理支行
		String updateTimeHqlBusSale = "select new map(max(updatetime) as updatetime) from TBasisBusSale";// 受理人

		// String updateTimeHql =
		// "select new map(max(updatetime) as updateTime) from businidbranch";
		// String updateTimeHql =
		// "select new map(max(updatetime) as updateTime) from TBasisDictCompabymemo";
		Query updateTimeQueryCompabymemo = this.getCurrentSession()
				.createQuery(updateTimeHqlCompabymemo);
		Map<String, String> updateTimeMapCompabymemo = (Map<String, String>) updateTimeQueryCompabymemo
				.uniqueResult();
		Date updatetimeCompabymemo = DateUtil
				.getDateTimeNJ(updateTimeMapCompabymemo.get("updateTime"));
		// map.put("updatetimeCompabymemo",
		// updateTimeMapCompabymemo.get("updateTime"));

		// 受理机构
		Query updateTimeQueryBusBank = this.getCurrentSession().createQuery(
				updateTimeHqlBusBank);
		Map<String, String> updateTimeMapBusBank = (Map<String, String>) updateTimeQueryBusBank
				.uniqueResult();
		Date updatetimeBusBank = DateUtil.getDateTimeNJ(updateTimeMapBusBank
				.get("updaTime"));
		// 受理支行
		Query updateTimeQueryBusSubbank = this.getCurrentSession().createQuery(
				updateTimeHqlBusSubbank);
		Map<String, String> updateTimeMapBusSubbank = (Map<String, String>) updateTimeQueryBusSubbank
				.uniqueResult();
		Date updatetimeBusSubbank = DateUtil
				.getDateTimeNJ(updateTimeMapBusSubbank.get("updaTime"));
		// 受理人
		Query updateTimeQueryBusSale = this.getCurrentSession().createQuery(
				updateTimeHqlBusSale);
		Map<String, String> updateTimeMapBusSale = (Map<String, String>) updateTimeQueryBusSale
				.uniqueResult();
		Date updatetimeBusSale = DateUtil.getDateTimeNJ(updateTimeMapBusSale
				.get("updatetime"));

		Query updateTimeQueryStores = this.getCurrentSession().createQuery(
				updateTimeHqlStores);
		Map<String, String> updateTimeMapStores = (Map<String, String>) updateTimeQueryStores
				.uniqueResult();
		Date updatetimeStores = DateUtil.getDateTimeNJ(updateTimeMapStores
				.get("updateTime"));
		// map.put("updatetimeStores",
		// updateTimeMapCompabymemo.get("updateTime"));

		Query updateTimeQueryPlanType = this.getCurrentSession().createQuery(
				updateTimeHqlPlanType);
		Map<String, String> updateTimeMapPlanType = (Map<String, String>) updateTimeQueryPlanType
				.uniqueResult();
		Date updateTimePlanType = DateUtil.getDateTimeNJ(updateTimeMapPlanType
				.get("updateTime"));
		// map.put("updateTimePlanType",
		// updateTimeMapCompabymemo.get("updateTime"));

		Query updateTimeQueryPlanStore = this.getCurrentSession().createQuery(
				updateTimeHqlPlanStore);
		Map<String, String> updateTimeMapPlanStore = (Map<String, String>) updateTimeQueryPlanStore
				.uniqueResult();
		Date updatetimePlanStore = DateUtil
				.getDateTimeNJ(updateTimeMapPlanStore.get("updateTime"));
		// map.put("updatetimePlanStore",
		// updateTimeMapCompabymemo.get("updateTime"));

		Query updateTimeQueryIgroup = this.getCurrentSession().createQuery(
				updateTimeHqlIgroup);
		Map<String, String> updateTimeMapIgroup = (Map<String, String>) updateTimeQueryIgroup
				.uniqueResult();
		Date updatetimeIgroup = DateUtil.getDateTimeNJ(updateTimeMapIgroup
				.get("updateTime"));
		// map.put("updatetimeIgroup",
		// updateTimeMapCompabymemo.get("updateTime"));

		Query updateTimeQuerystoreGoods = this.getCurrentSession().createQuery(
				updateTimeHqlstoreGoods);
		Map<String, String> updateTimeMapstoreGoods = (Map<String, String>) updateTimeQuerystoreGoods
				.uniqueResult();
		Date updatetimestoreGoods = DateUtil
				.getDateTimeNJ(updateTimeMapstoreGoods.get("updateTime"));
		// map.put("updatetimestoreGoods",
		// updateTimeMapCompabymemo.get("updateTime"));

		Query updateTimeQueryPlanstoregood = this.getCurrentSession()
				.createQuery(updateTimeHqlPlanstoregood);
		Map<String, String> updateTimeMapPlanstoregood = (Map<String, String>) updateTimeQueryPlanstoregood
				.uniqueResult();
		Date updatetimePlanstoregood = DateUtil
				.getDateTimeNJ(updateTimeMapPlanstoregood.get("updateTime"));
		// map.put("updatetimePlanstoregood",
		// updateTimeMapCompabymemo.get("updateTime"));
		// 地址
		Query updateTimeQuerySingBranch = this.getCurrentSession().createQuery(
				updateTimeHqlsingBranch);
		Map<String, Date> updateTimeMapSingBranch = (Map<String, Date>) updateTimeQuerySingBranch
				.uniqueResult();
		Date updatetimeSingBranch = updateTimeMapSingBranch.get("updateDate");
		// map.put("updatetimeSingBranch",
		// updateTimeMapCompabymemo.get("updateTime"));

		Query updateTimeQueryuserstore = this.getCurrentSession().createQuery(
				updateTimeHqluserstore);
		Map<String, String> updateTimeMapuserstore = (Map<String, String>) updateTimeQueryuserstore
				.uniqueResult();
		Date updatetimeuserstore = DateUtil
				.getDateTimeNJ(updateTimeMapuserstore.get("updatetime"));
		// map.put("updatetimeSingBranch",
		// updateTimeMapCompabymemo.get("updateTime"));

		/* chenji 添加T_BASIS_BUS_STORE_SPECIALCODE查询 20170210* --start-- */
		String queryTbasisBusStoreSpecialCodeHql = "select new map(tbb.storeNumber as storeNumber,tbb.specialCode as specialCode,tbb.orgCode as orgCode) from TbasisBusStoreSpecialCode tbb";
		if ("1".equals(parematerflag)) {
			Query query = this.getCurrentSession().createQuery(
					queryTbasisBusStoreSpecialCodeHql);
			map.put("TbasisBusStoreSpecialCode", query.list());
		} else {
			map.put("TbasisBusStoreSpecialCode", "");
		}

		/*----end-------*/

		/* chenji 添加 T_BASIS_BUS_SPECIALCOST_ADD 查询 20170224* --start-- */
		String queryT_BASIS_BUS_SPECIALCOST_ADDHql = "select new map(tbb.busInId as busInId,tbb.busInName as busInName,tbb.busInId_Bracnch as busInId_Bracnch,tbb.busInTypeId as busInTypeId) from TBasisiBusSpecialCostAdd tbb";
		if ("1".equals(parematerflag)) {
			Query query = this.getCurrentSession().createQuery(
					queryT_BASIS_BUS_SPECIALCOST_ADDHql);
			ArrayList<HashMap<String,String>>  tempList=new ArrayList<HashMap<String,String>>();
			tempList=(ArrayList<HashMap<String, String>>) query.list();
			map.put("tBasisBusSpecialCostAdd", SqlUtil.getSqlList(tempList));
		} else {
			map.put("tBasisBusSpecialCostAdd", "");
		}
		/*----end-------*/

		// 受理机构
		if ((StringUtils.isBlank(lastUpdateTime) || updatetimeBusBank.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			String hql ="";
			if( "NJCB".equals(bankFlag) ){
				hql = "select new map(tbb.id.orgId as orgId, tbb.orgName as orgName ,tbb.orgCode as orgCode) from TBasisBusBank tbb where tbb.erea='301' and tbb.id.orgId not in('7201','720A')";
			}else if( "KSLC".equals(bankFlag) ){
				hql = "select new map(tbb.id.orgId as orgId, tbb.orgName as orgName ,tbb.orgCode as orgCode) from TBasisBusBank tbb where tbb.erea='301' and tbb.id.orgId in('7201','720A')";
			}else if( "RZCB".equals(bankFlag) ){
				hql = "select new map(tbb.id.orgId as orgId, tbb.orgName as orgName ,tbb.orgCode as orgCode) from TBasisBusBank tbb where tbb.erea='302' ";
			}
			Query query = this.getCurrentSession().createQuery(hql);
			map.put("BusBank", query.list());
		} else {
			map.put("BusBank", "");
		}

		// 受理支行
		if ((StringUtils.isBlank(lastUpdateTime) || updatetimeBusSubbank
				.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
//			String hql = "select new map(tbb.id.orgId as orgId, tbb.orgName as orgName ,tbb.orgCode as orgCode) from TBasisBusSubbank tbb ";
			String hql ="";
			if( "NJCB".equals(bankFlag) ){
				hql = "select new map(tbb.id.orgId as orgId, tbb.orgName as orgName ,tbb.orgCode as orgCode) from TBasisBusSubbank tbb where tbb.erea='301' and tbb.id.orgId not in('7201','720A')";
			}else if( "KSLC".equals(bankFlag) ){
				hql = "select new map(tbb.id.orgId as orgId, tbb.orgName as orgName ,tbb.orgCode as orgCode) from TBasisBusSubbank tbb where tbb.erea='301' and tbb.id.orgId in('7201','720A')";
			}else if( "RZCB".equals(bankFlag) ){
				hql = "select new map(tbb.id.orgId as orgId, tbb.orgName as orgName ,tbb.orgCode as orgCode) from TBasisBusSubbank tbb where tbb.erea='302' ";
			}
			Query query = this.getCurrentSession().createQuery(hql);
			map.put("BusSubbank", query.list());
		} else {
			map.put("BusSubbank", "");
		}

		// 受理人
		if ((StringUtils.isBlank(lastUpdateTime) || updatetimeBusSale.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
//			String hql = "select new map(tbb.id.businid as businid, tbb.id.erea as erea, tbb.businname as businname, tbb.busintypeid as busintypeid , tbb.dictremark as dictremark , tbb.saledistinct as saledistinct , tbb.updateuser as updateuser , tbb.updatetime as updatetime ) from TBasisBusSale tbb ";
			String hql ="";
			if( "NJCB".equals(bankFlag) ){
				hql = "select new map(tbb.id.businid as businid, tbb.id.erea as erea, tbb.businname as businname, tbb.busintypeid as busintypeid , tbb.dictremark as dictremark , tbb.saledistinct as saledistinct , tbb.updateuser as updateuser , tbb.updatetime as updatetime ) from TBasisBusSale tbb where tbb.id.erea='301' and tbb.busintypeid !='555' ";
			}else if( "KSLC".equals(bankFlag) ){
				hql = "select new map(tbb.id.businid as businid, tbb.id.erea as erea, tbb.businname as businname, tbb.busintypeid as busintypeid , tbb.dictremark as dictremark , tbb.saledistinct as saledistinct , tbb.updateuser as updateuser , tbb.updatetime as updatetime ) from TBasisBusSale tbb where tbb.id.erea='301' and tbb.busintypeid='555' ";
			}else if( "RZCB".equals(bankFlag) ){
				hql = "select new map(tbb.id.businid as businid, tbb.id.erea as erea, tbb.businname as businname, tbb.busintypeid as busintypeid , tbb.dictremark as dictremark , tbb.saledistinct as saledistinct , tbb.updateuser as updateuser , tbb.updatetime as updatetime ) from TBasisBusSale tbb where tbb.id.erea='302' ";
			}
			Query query = this.getCurrentSession().createQuery(hql);
			map.put("BusSale", query.list());
		} else {
			map.put("BusSale", "");
		}

		if ((StringUtils.isBlank(lastUpdateTime) || updatetimeuserstore
				.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
//			String hql = "select new map(tbd.id.usercode as usercode, tbd.id.storenumber as storenumber ,tbd.orgcode as orgcode) "
//					+ "from TBasisBusUserStore tbd  where tbd.id.usercode=:usercode ";
			String hql ="";
			if( "NJCB".equals(bankFlag) ){
				hql = "select new map(tbd.id.usercode as usercode, tbd.id.storenumber as storenumber ,tbd.orgcode as orgcode) "
						+ "from TBasisBusUserStore tbd  where tbd.id.usercode=:usercode and tbd.orgcode='301' and tbd.beizhu1 !='555' ";
			}else if( "KSLC".equals(bankFlag) ){
				hql = "select new map(tbd.id.usercode as usercode, tbd.id.storenumber as storenumber ,tbd.orgcode as orgcode) "
						+ "from TBasisBusUserStore tbd  where tbd.id.usercode=:usercode and tbd.orgcode='301' and tbd.beizhu1 ='555' ";
			}else if( "RZCB".equals(bankFlag) ){
				hql = "select new map(tbd.id.usercode as usercode, tbd.id.storenumber as storenumber ,tbd.orgcode as orgcode) "
						+ "from TBasisBusUserStore tbd  where tbd.id.usercode=:usercode and tbd.orgcode='302' ";
			}
			Query query = this.getCurrentSession().createQuery(hql)
					.setParameter("usercode", usercode);
			map.put("userStores", query.list());
		} else {
			map.put("userStores", "");
		}

		if ((StringUtils.isBlank(lastUpdateTime) || updatetimeCompabymemo
				.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			String hql = "select new map(tbd.id.businid as businid, tbd.businname as businname ,tbd.orgArea as orgArea) from TBasisBusCompabymemo tbd ";
			Query query = this.getCurrentSession().createQuery(hql);
			map.put("Compabymemo", query.list());
		} else {
			/*
			 * String hql =
			 * "select new map(tbd.id.businid as businid, tbd.businname as businname ,tbd.orgArea as orgArea) from TBasisDictCompabymemo tbd "
			 * ; Query query = this.getCurrentSession().createQuery(hql); List
			 * list = query.list(); List list =qryDectByTypeWithDescCompany("");
			 * map.put("Compabymemo",list);
			 */
			map.put("Compabymemo", "");
		}

		if ((StringUtils.isBlank(lastUpdateTime) || updatetimeStores.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			// String hql = " From TBasisDictStores";
			// = qryDectByTypeWithDescAlStores("");
			//beizhu2 购易贷受托支付标识
			//beizhu3,beizhu4,strooption 一起构成受托支付商户是否为风险商户 chenji 2017/10/13
//		   	  String hql = "select new map(tbd.businid as businid,"
//					+ "tbd.storesdis  as storesdis ,"
//					+ "tbd.businidbranch  as businidbranch,"
//					+ "tbd.storetype  as storetype,"
//					+ "tbd.strooption  as strooption,"
//					+ "tbd.beizhu1  as beizhu1," + "tbd.beizhu2  as beizhu2,"
//					+ "tbd.beizhu3  as beizhu3,"
//					+"tbd.beizhu4  as beizhu4,"   
//					+ "tbd.orapo as orapo) from TBasisBusStores tbd ";
		   	  
		   	String hql ="";
			if( "NJCB".equals(bankFlag) ){
				hql = "select new map(tbd.businid as businid,"
						+ "tbd.storesdis  as storesdis ,"
						+ "tbd.businidbranch  as businidbranch,"
						+ "tbd.storetype  as storetype,"
						+ "tbd.strooption  as strooption,"
						+ "tbd.beizhu1  as beizhu1," + "tbd.beizhu2  as beizhu2,"
						+ "tbd.beizhu3  as beizhu3,"
						+"tbd.beizhu4  as beizhu4,"   
						+ "tbd.orapo as orapo) from TBasisBusStores tbd "
						+ "where tbd.oraarea='301' and tbd.storesdis !='555' ";
			}else if( "KSLC".equals(bankFlag) ){
				hql = "select new map(tbd.businid as businid,"
						+ "tbd.storesdis  as storesdis ,"
						+ "tbd.businidbranch  as businidbranch,"
						+ "tbd.storetype  as storetype,"
						+ "tbd.strooption  as strooption,"
						+ "tbd.beizhu1  as beizhu1," + "tbd.beizhu2  as beizhu2,"
						+ "tbd.beizhu3  as beizhu3,"
						+"tbd.beizhu4  as beizhu4,"   
						+ "tbd.orapo as orapo) from TBasisBusStores tbd "
						+ "where tbd.oraarea='301' and tbd.storesdis ='555' ";
			}else if( "RZCB".equals(bankFlag) ){
				hql = "select new map(tbd.businid as businid,"
						+ "tbd.storesdis  as storesdis ,"
						+ "tbd.businidbranch  as businidbranch,"
						+ "tbd.storetype  as storetype,"
						+ "tbd.strooption  as strooption,"
						+ "tbd.beizhu1  as beizhu1," + "tbd.beizhu2  as beizhu2,"
						+ "tbd.beizhu3  as beizhu3,"
						+"tbd.beizhu4  as beizhu4,"   
						+ "tbd.orapo as orapo) from TBasisBusStores tbd "
						+ "where tbd.oraarea='302' ";
			}
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("Stores", list);
		} else {
			// List list = qryDectByTypeWithDescAlStores("");
			// map.put("Stores", list);

			map.put("Stores", "");
		}
		//风险商户数据查询
		if ( "1".equals(parematerflag) ) {
			String hql = "select new map(tbd.store_number as store_number,"
					+ "tbd.store_name  as store_name ,"
					+ "tbd.account_name  as account_name,"
					+ "tbd.account as account) from TBasisBusRiskStores tbd ";
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("RiskStores", list);
		} else {

			map.put("RiskStores", "");
		}
		//贴息商户及定价数据查询
		if ( "1".equals(parematerflag) ) {
			String hql = "select new map(sd.store_number as store_number,"
					+ "sd.plan_number  as plan_number)"
					+ "from StoreDiscount sd "
					+ "where sd.status='1' ";
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("StoreDiscount", list);
		} else {
			
			map.put("StoreDiscount", "");
		}
		
		if ((StringUtils.isBlank(lastUpdateTime) || updateTimePlanType
				.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
//			String hql = "select " + "new map("
//					+ "tbd.id.busintypeid as dictremark,"
//					+ "tbd.businname as businname," + "tbd.low as low,"
//					+ "tbd.floorlow as floorlow,"
//					+ "tbd.id.businidbranch as cusclas,"
//					+ "tbd.panpro as panpro,"
//					+ "tbd.child_product as childproduct,"
//					+ "tbd.payment_type as paymenttype,"
//					+ "tbd.youhui_code as youhuicode) "
//					+ " from TBasisBusPlantype tbd ";
			String hql ="";
			if( "NJCB".equals(bankFlag) ){
				hql = "select " + "new map("
						+ "tbd.id.busintypeid as dictremark,"
						+ "tbd.businname as businname," + "tbd.low as low,"
						+ "tbd.floorlow as floorlow,"
						+ "tbd.id.businidbranch as cusclas,"
						+ "tbd.panpro as panpro,"
						+ "tbd.child_product as childproduct,"
						+ "tbd.payment_type as paymenttype,"
						+ "tbd.youhui_code as youhuicode) "
						+ " from TBasisBusPlantype tbd "
						+ " where tbd.id.oraarea='301' and (tbd.youhui_code !='KS' or tbd.youhui_code is null)";
			}else if( "KSLC".equals(bankFlag) ){
				hql = "select " + "new map("
						+ "tbd.id.busintypeid as dictremark,"
						+ "tbd.businname as businname," + "tbd.low as low,"
						+ "tbd.floorlow as floorlow,"
						+ "tbd.id.businidbranch as cusclas,"
						+ "tbd.panpro as panpro,"
						+ "tbd.child_product as childproduct,"
						+ "tbd.payment_type as paymenttype,"
						+ "tbd.youhui_code as youhuicode) "
						+ " from TBasisBusPlantype tbd "
						+ " where tbd.id.oraarea='301' and tbd.youhui_code ='KS' ";
			}else if( "RZCB".equals(bankFlag) ){
				hql = "select " + "new map("
						+ "tbd.id.busintypeid as dictremark,"
						+ "tbd.businname as businname," + "tbd.low as low,"
						+ "tbd.floorlow as floorlow,"
						+ "tbd.id.businidbranch as cusclas,"
						+ "tbd.panpro as panpro,"
						+ "tbd.child_product as childproduct,"
						+ "tbd.payment_type as paymenttype,"
						+ "tbd.youhui_code as youhuicode) "
						+ " from TBasisBusPlantype tbd "
						+ " where tbd.id.oraarea='302' ";
			}
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("PlanType", list);
		} else {
			// List list = qryDectByTypeWithDescAllPlanType("");
			// map.put("PlanType", list);
			map.put("PlanType", "");
		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetimePlanStore
				.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
//			String hql = "select new map(tbd.id.businid as businid,tbd.id.businidbranch as beizhu1) from TBasisBusPlanstore tbd ";
			String hql ="";
			if( "NJCB".equals(bankFlag) ){
				hql = "select new map(tbd.id.businid as businid,tbd.id.businidbranch as beizhu1) from TBasisBusPlanstore tbd where tbd.oraarea='301' and tbd.beizhu1 !='555' ";
			}else if( "KSLC".equals(bankFlag) ){
				hql = "select new map(tbd.id.businid as businid,tbd.id.businidbranch as beizhu1) from TBasisBusPlanstore tbd where tbd.oraarea='301' and tbd.beizhu1 ='555' ";
			}else if( "RZCB".equals(bankFlag) ){
				hql = "select new map(tbd.id.businid as businid,tbd.id.businidbranch as beizhu1) from TBasisBusPlanstore tbd where tbd.oraarea='302' ";
			}
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("PlanStore", list);
		} else {
			// List list = qryDectByTypeWithDescAllPlanStore("");
			// map.put("PlanStore", list);
			map.put("PlanStore", "");
		}

		if ((StringUtils.isBlank(lastUpdateTime) || updatetimeIgroup.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			String hqlTuan = "select new map(tbd.id.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.groupYou as groupYou ,"
					+ " tbd.id.orgCode as orgCode  ) from TBasisBusIgroup tbd where tbd.groupTuan='Y'";
			String hqlYou = "select new map(tbd.id.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.id.orgCode as orgCode  ) from TBasisBusIgroup tbd where tbd.groupYou='1'";
			String hqlDai = "select new map(tbd.id.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.id.orgCode as orgCode  ) from TBasisBusIgroup tbd where tbd.groupDai='Y'";
			String hqlMing = "select new map(tbd.id.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.id.orgCode as orgCode  ) from TBasisBusIgroup tbd where tbd.groupYou='2'";
			
			if( "NJCB".equals(bankFlag) ){
				hqlTuan =hqlTuan+ " and tbd.id.orgCode = '301' and (tbd.beizhu1 != '555' or tbd.beizhu1 is null) ";
				hqlYou =hqlYou+ " and tbd.id.orgCode = '301' and (tbd.beizhu1 != '555' or tbd.beizhu1 is null) ";
				hqlDai =hqlDai+ " and tbd.id.orgCode = '301' and (tbd.beizhu1 != '555' or tbd.beizhu1 is null) ";
				hqlMing =hqlMing+ " and tbd.id.orgCode = '301' and (tbd.beizhu1 != '555' or tbd.beizhu1 is null) ";
			}else if( "KSLC".equals(bankFlag) ){
				hqlTuan =hqlTuan+ " and tbd.id.orgCode = '301' and tbd.beizhu1 = '555'  ";
				hqlYou =hqlYou+ " and tbd.id.orgCode = '301' and tbd.beizhu1 = '555'  ";
				hqlDai =hqlDai+ " and tbd.id.orgCode = '301' and tbd.beizhu1 = '555'  ";
				hqlMing =hqlMing+ " and tbd.id.orgCode = '301' and tbd.beizhu1 = '555'  ";
			}else if( "RZCB".equals(bankFlag) ){
				hqlTuan =hqlTuan+ " and tbd.id.orgCode = '302' ";
				hqlYou =hqlYou+ " and tbd.id.orgCode = '302' ";
				hqlDai =hqlDai+ " and tbd.id.orgCode = '302' ";
				hqlMing =hqlMing+ " and tbd.id.orgCode = '302' ";
			}
			
			map.put("IgroupDai", this.getCurrentSession().createQuery(hqlDai)
					.list());
			map.put("IgroupYou", this.getCurrentSession().createQuery(hqlYou)
					.list());
			map.put("IgroupTuan", this.getCurrentSession().createQuery(hqlTuan)
					.list());
			map.put("IgroupMing", this.getCurrentSession().createQuery(hqlMing)
					.list());
		} else {
			/*
			 * String hqlTuan = "select new map(tbd.groupNo as groupNo," +
			 * " tbd.groupName as groupName ," +
			 * " tbd.groupDistrict as groupDistrict ," +
			 * " tbd.groupPro as groupPro ," + " tbd.groupTuan as groupTuan ," +
			 * " tbd.orgCode as orgCode  ) from TBasisDictIgroup tbd where tbd.groupTuan='Y'"
			 * ; String hqlYou = "select new map(tbd.groupNo as groupNo," +
			 * " tbd.groupName as groupName ," +
			 * " tbd.groupDistrict as groupDistrict ," +
			 * " tbd.groupPro as groupPro ," + " tbd.groupTuan as groupTuan ," +
			 * " tbd.orgCode as orgCode  ) from TBasisDictIgroup tbd where tbd.groupYou='1'"
			 * ; String hqlDai = "select new map(tbd.groupNo as groupNo," +
			 * " tbd.groupName as groupName ," +
			 * " tbd.groupDistrict as groupDistrict ," +
			 * " tbd.groupPro as groupPro ," + " tbd.groupTuan as groupTuan ," +
			 * " tbd.orgCode as orgCode  ) from TBasisDictIgroup tbd where tbd.groupDai='Y'"
			 * ; map.put("IgroupDai",
			 * this.getCurrentSession().createQuery(hqlDai).list());
			 * map.put("IgroupYou",
			 * this.getCurrentSession().createQuery(hqlYou).list());
			 * map.put("IgroupTuan",
			 * this.getCurrentSession().createQuery(hqlTuan).list());
			 */
			map.put("IgroupDai", "");
			map.put("IgroupYou", "");
			map.put("IgroupTuan", "");
			map.put("IgroupMing", "");

		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetimestoreGoods
				.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			// String hql = " From TBasisDictStores";
			String hql = "select new map(tbd.businid as businid,"
					+ "tbd.storeid  as storeid ,"
					+ "tbd.storetypeid  as storetypeid,"
					+ "tbd.area  as area) from TBasisBusStoregoogs tbd ";
			
			if( "NJCB".equals(bankFlag) ){
				hql =hql+ " where tbd.area = '301' and tbd.beizhu1 != '555' ";
			}else if( "KSLC".equals(bankFlag) ){
				hql =hql+ " where tbd.area = '301' and tbd.beizhu1 = '555' ";
			}else if( "RZCB".equals(bankFlag) ){
				hql =hql+ " where tbd.area = '302' ";
			}
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("storeGoods", list);
		} else {
			// List list = qryDectByTypeWithDescAlStoreGoods("");
			// map.put("storeGoods", list);
			map.put("storeGoods", "");
		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetimePlanstoregood
				.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			// String hql = " From TBasisDictStores";
			String hql = "select new map(tbd.id.businid as beizhu1,"
					+ "tbd.id.storetypeid  as  beizhu2,"
					+ "tbd.area  as area) from TBasisBusPlanstoregood tbd ";
			
			if( "NJCB".equals(bankFlag) ){
				hql =hql+ " where tbd.area = '301' and tbd.beizhu1 != '555' ";
			}else if( "KSLC".equals(bankFlag) ){
				hql =hql+ " where tbd.area = '301' and tbd.beizhu1 = '555' ";
			}else if( "RZCB".equals(bankFlag) ){
				hql =hql+ " where tbd.area = '302' ";
			}
			
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("planStoresGood", list);
		} else {
			// List list = qryDectByTypeWithDescAlPlanStoresGood("");
			// map.put("planStoresGood", list);
			map.put("planStoresGood", "");
		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetimeSingBranch
				.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			// String hql = " From TBasisDictStores";

			String hql = "select new map(tbd.dbId as dbId,"
					+ " tbd.orgCode as orgCode ,"
					+ " tbd.branchType as branchType ,"
					+ " tbd.branchDistrict as branchDistrict ,"
					+ " tbd.branchValue as branchValue ,"
					+ " tbd.branchName as branchName ,"
					+ " tbd.subBranchValue as subBranchValue ,"
					+ " tbd.subBranchName as subBranchName ,"
					+ " tbd.address as address ,"
					+ " tbd.telephone as telephone ,"
					+ "tbd.createDate as createDate) from SignBranch tbd ";
			// com.mobile.application.entity.SignBranch
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("singBranch", list);
		} else {
			map.put("singBranch", "");
		}
		map.put("lastUpdateTime", DateUtil.format(new Date()));
		return map;
	}

	@Override
	@SuppressWarnings({ "rawtypes" })
	public Map<String, Object> ParameterNew(String lastUpdateTime,
			String usercode, String orgcode, String bei) {
		String parematerflag = SpringProperty.props
				.getProperty("parematerflag");
		Map<String, Object> map = new HashMap<String, Object>();
		String updateTimeHql = "select new map(max(updatetime) as updateTime) from TBasisDictCompabymemo";
		// String updateTimeHql =
		// "select new map(max(updatetime) as updateTime) from businidbranch";
		// String updateTimeHql =
		// "select new map(max(updatetime) as updateTime) from TBasisDictCompabymemo";
		Query updateTimeQuery = this.getCurrentSession().createQuery(
				updateTimeHql);
		Map<String, String> updateTimeMap = (Map<String, String>) updateTimeQuery
				.uniqueResult();
		Date updatetime = DateUtil.getDateTimeNJ(updateTimeMap
				.get("updateTime"));
		// qryDectByTypeWithDescPlanType("");
		if ((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			String hql = "select new map(tbd.id.businid as businid, tbd.businname as businname ,tbd.orgArea as orgArea) from TBasisBusCompabymemo tbd ";
			Query query = this.getCurrentSession().createQuery(hql);
			map.put("Compabymemo", query.list());
		} else {
			/*
			 * String hql =
			 * "select new map(tbd.id.businid as businid, tbd.businname as businname ,tbd.orgArea as orgArea) from TBasisDictCompabymemo tbd "
			 * ; Query query = this.getCurrentSession().createQuery(hql); List
			 * list = query.list();
			 */
			List list = qryDectByTypeWithDescCompany("");
			map.put("Compabymemo", list);
		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			String hql = "select new map(tbd.id.usercode as usercode, tbd.id.storenumber as storenumber ,tbd.orgcode as orgcode) "
					+ "from TBasisBusUserStore tbd  where tbd.id.usercode=:usercode ";
			// TBasisBusUserStore
			Query query = this.getCurrentSession().createQuery(hql)
					.setParameter("usercode", usercode);
			map.put("userStores", query.list());
		}

		if ((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			// String hql = " From TBasisDictStores";
			// = qryDectByTypeWithDescAlStores("");
			String hql = "select new map(tbd.businid as businid,"
					+ "tbd.storesdis  as storesdis ,"
					+ "tbd.businidbranch  as businidbranch,"
					+ "tbd.storetype  as storetype,"
					+ "tbd.strooption  as strooption,"
					+ "tbd.orapo as orapo) from TBasisBusStores tbd ";
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("Stores", list);
		} else {
			List list = qryDectByTypeWithDescAlStores("");
			map.put("Stores", list);
		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			String hql = "select " + "new map("
					+ "tbd.id.busintypeid as dictremark,"
					+ "tbd.businname as businname," + "tbd.low as low,"
					+ "tbd.floorlow as floorlow,"
					+ "tbd.id.businidbranch as cusclas,"
					+ "tbd.panpro as panpro) " + " from TBasisBusPlantype tbd ";
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("PlanType", list);
		} else {
			List list = qryDectByTypeWithDescAllPlanType("");
			map.put("PlanType", list);
		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			String hql = "select new map(tbd.id.businid as businid,tbd.id.businidbranch as beizhu1) from TBasisBusPlanstore tbd ";
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("PlanStore", list);
		} else {
			List list = qryDectByTypeWithDescAllPlanStore("");
			map.put("PlanStore", list);
		}

		if ((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			String hqlTuan = "select new map(tbd.id.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.id.orgCode as orgCode  ) from TBasisBusIgroup tbd where tbd.groupTuan='Y'";
			String hqlYou = "select new map(tbd.id.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.id.orgCode as orgCode  ) from TBasisBusIgroup tbd where tbd.groupYou='1'";
			String hqlDai = "select new map(tbd.id.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.id.orgCode as orgCode  ) from TBasisBusIgroup tbd where tbd.groupDai='Y'";
			map.put("IgroupDai", this.getCurrentSession().createQuery(hqlDai)
					.list());
			map.put("IgroupYou", this.getCurrentSession().createQuery(hqlYou)
					.list());
			map.put("IgroupTuan", this.getCurrentSession().createQuery(hqlTuan)
					.list());
		} else {
			String hqlTuan = "select new map(tbd.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.orgCode as orgCode  ) from TBasisDictIgroup tbd where tbd.groupTuan='Y'";
			String hqlYou = "select new map(tbd.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.orgCode as orgCode  ) from TBasisDictIgroup tbd where tbd.groupYou='1'";
			String hqlDai = "select new map(tbd.groupNo as groupNo,"
					+ " tbd.groupName as groupName ,"
					+ " tbd.groupDistrict as groupDistrict ,"
					+ " tbd.groupPro as groupPro ,"
					+ " tbd.groupTuan as groupTuan ,"
					+ " tbd.orgCode as orgCode  ) from TBasisDictIgroup tbd where tbd.groupDai='Y'";
			map.put("IgroupDai", this.getCurrentSession().createQuery(hqlDai)
					.list());
			map.put("IgroupYou", this.getCurrentSession().createQuery(hqlYou)
					.list());
			map.put("IgroupTuan", this.getCurrentSession().createQuery(hqlTuan)
					.list());
		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			// String hql = " From TBasisDictStores";
			String hql = "select new map(tbd.businid as businid,"
					+ "tbd.storeid  as storeid ,"
					+ "tbd.storetypeid  as storetypeid,"
					+ "tbd.area  as area) from TBasisBusStoregoogs tbd ";
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("storeGoods", list);
		} else {
			List list = qryDectByTypeWithDescAlStoreGoods("");
			map.put("storeGoods", list);
		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			// String hql = " From TBasisDictStores";
			String hql = "select new map(tbd.id.businid as beizhu1,"
					+ "tbd.id.storetypeid  as  beizhu2,"
					+ "tbd.area  as area) from TBasisBusPlanstoregood tbd ";
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("planStoresGood", list);
		} else {
			List list = qryDectByTypeWithDescAlPlanStoresGood("");
			map.put("planStoresGood", list);
		}
		if ((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil
				.getDateTime(lastUpdateTime).getTime())
				&& "1".equals(parematerflag)) {
			// String hql = " From TBasisDictStores";

			String hql = "select new map(tbd.dbId as dbId,"
					+ " tbd.orgCode as orgCode ,"
					+ " tbd.branchType as branchType ,"
					+ " tbd.branchDistrict as branchDistrict ,"
					+ " tbd.branchValue as branchValue ,"
					+ " tbd.branchName as branchName ,"
					+ " tbd.subBranchValue as subBranchValue ,"
					+ " tbd.subBranchName as subBranchName ,"
					+ " tbd.address as address ,"
					+ " tbd.telephone as telephone ,"
					+ "tbd.createDate as createDate) from SignBranch tbd ";
			// com.mobile.application.entity.SignBranch
			Query query = this.getCurrentSession().createQuery(hql);
			List list = query.list();
			map.put("singBranch", list);
		}
		return map;
	}

	public List<?> qryDectByTypeWithDescCompany(String typeId) {
		/*
		 * String hql = "from TBasisDict tbd where tbd.id.busintypeid='" +
		 * typeId + "' order by tbd.id.businid"; Query query =
		 * this.getCurrentSession().createQuery(hql); return query.list();
		 */
		String hql = "select new map(tbd.id.businid as businid, tbd.businname as businname ,tbd.orgArea as orgArea) from TBasisDictCompabymemo tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<?> qryDectByTypeWithDescAllPlanStore(String typeId) {
		/*
		 * String hql = "from TBasisDict tbd where tbd.id.busintypeid='" +
		 * typeId + "' order by tbd.id.businid"; Query query =
		 * this.getCurrentSession().createQuery(hql); return query.list();
		 */// businidbranchbusinidbranch
		String hql = "select new map(tbd.id.businid as businid,tbd.id.businidbranch as beizhu1) from TBasisDictPlanstore tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<?> qryDectByTypeWithDescAllPlanType(String typeId) {
		/*
		 * String hql = "from TBasisDict tbd where tbd.id.busintypeid='" +
		 * typeId + "' order by tbd.id.businid"; Query query =
		 * this.getCurrentSession().createQuery(hql); return query.list();
		 */// businidbranchbusinidbranchtb
		String hql = "select " + "new map("
				+ "tbd.id.busintypeid as dictremark,"
				+ "tbd.businname as businname," + "tbd.low as low,"
				+ "tbd.floorlow as floorlow,"
				+ "tbd.id.businidbranch as cusclas," + "tbd.panpro as panpro) "
				+ " from TBasisDictPlantype tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<?> qryDectByTypeWithDescAlStores(String typeId) {
		/*
		 * String hql = "from TBasisDict tbd where tbd.id.busintypeid='" +
		 * typeId + "' order by tbd.id.businid"; Query query =
		 * this.getCurrentSession().createQuery(hql); return query.list();
		 */// TBasisDictStores
		String hql = "select new map(tbd.businid as businid,"
				+ "tbd.storesdis  as storesdis ,"
				+ "tbd.businidbranch  as businidbranch,"
				+ "tbd.storetype  as storetype,"
				+ "tbd.strooption  as strooption,"
				+ "tbd.orapo as orapo) from TBasisDictStores tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<?> qryDectByTypeWithDescAlStoreGoods(String typeId) {
		/*
		 * String hql = "from TBasisDict tbd where tbd.id.busintypeid='" +
		 * typeId + "' order by tbd.id.businid"; Query query =
		 * this.getCurrentSession().createQuery(hql); return query.list();
		 */// TBasisDictStores
		String hql = "select new map(tbd.businid as businid,"
				+ "tbd.storeid  as storeid ,"
				+ "tbd.storetypeid  as storetypeid,"
				+ "tbd.area  as area) from TBasisDictStoregoogs tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<?> qryDectByTypeWithDescAlPlanStoresGood(String typeId) {
		/*
		 * String hql = "from TBasisDict tbd where tbd.id.busintypeid='" +
		 * typeId + "' order by tbd.id.businid"; Query query =
		 * this.getCurrentSession().createQuery(hql); return query.list();
		 */// TBasisDictStores
		String hql = "select new map(tbd.id.businid as beizhu1,"
				+ "tbd.id.storetypeid  as  beizhu2,"
				+ "tbd.area  as area) from TBasisDictPlanstoregood tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<?> qryDectByTypeIgroup(String typeId) {
		String hql = "select new map(tbd.id.businid as businid,"
				+ " tbd.businname as businname ,"
				+ "tbd.orgArea as orgArea) from TBasisDictCompabymemo tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	public List<?> qryDectByTypeWithSingBRANCH(String typeId) {
		String hql = "select new map(tbd.dbId as dbId,"
				+ " tbd.orgCode as orgCode ,"
				+ " tbd.branchType as branchType ,"
				+ " tbd.branchDistrict as branchDistrict ,"
				+ " tbd.branchValue as branchValue ,"
				+ " tbd.branchName as branchName ,"
				+ " tbd.subBranchValue as subBranchValue ,"
				+ " tbd.subBranchName as subBranchName ,"
				+ " tbd.address as address ," + " tbd.telephone as telephone ,"
				+ "tbd.createDate as createDate) from SignBranch tbd ";
		// com.mobile.application.entity.SignBranch
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	/*
	 * public List<?> qryDectByTypeWithDescAllORG(String typeId) { String hql =
	 * "from TBasisDict tbd where tbd.id.busintypeid='" + typeId +
	 * "' order by tbd.id.businid"; Query query =
	 * this.getCurrentSession().createQuery(hql); return query.list();TBasisOrg
	 * String hql =
	 * "select new map(tbd.id.businid as businid, tbd.businname as businname ,tbd.orgArea as orgArea) from TBasisOrg tbd where tbd.busintypeid='"
	 * + typeId + "' order by tbd.id.businid"; Query query =
	 * this.getCurrentSession().createQuery(hql); return query.list(); }
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getParameter(Object parameterstring) {
		String[] SALES = new String[] {};
		String[] STORE_SALES = new String[] {};
		String[] VISIT = new String[] {};
		// 结果
		JSONObject data = JSONObject.fromObject(parameterstring);
		// 业务员编号
		String staffId = data.optString("staff_id").toUpperCase();
		// 角色
		String role = data.optString("roles").toUpperCase();
		JSONArray array = data.getJSONArray("paramter");
		JSONObject object = new JSONObject();
		List<?> listcity = null;
		if ("SALES".equals(role)) {
			JSONObject saleRole = new JSONObject();
			NjDatCity city = (NjDatCity) this.getCurrentSession()
					.createQuery("form NjDatCity c where c.updatetime=:time")
					.setParameter("time", data.get("City")).uniqueResult();
			if (null == city) {
				listcity = this.getCurrentSession()
						.createQuery("form NjDatCity").list();
				saleRole.put("city", listcity);
			}
			NjDatDistrict NjDatDistrict = (NjDatDistrict) this
					.getCurrentSession()
					.createQuery(
							"form NjDatDistrict c where c.updatetime=:time")
					.setParameter("time", data.get("District")).uniqueResult();
			if (null == NjDatDistrict) {
				listcity = this.getCurrentSession()
						.createQuery("form NjDatDistrict").list();
				saleRole.put("District", listcity);
			}
			NjDatProvince NjDatProvince = (NjDatProvince) this
					.getCurrentSession()
					.createQuery(
							"form NjDatProvince c where c.updatetime=:time")
					.setParameter("time", data.get("Province")).uniqueResult();
			if (null == NjDatProvince) {
				listcity = this.getCurrentSession()
						.createQuery("form NjDatProvince").list();
				saleRole.put("city", listcity);
			}
			object.put("SALES", saleRole);
		}
		if ("STORE_SALES".equals(role)) {

		}
		if ("VISIT".equals(role)) {

		}

		return object;
	}

	@Override
	public void updatePADUserPassword(String userpassword, String userid) {
		this.getCurrentSession()
				.createSQLQuery(
						"UPDATE T_BASIS_USER T set t.USER_PWD=:userpassword where t.USER_CODE=:userid")
				.setParameter("userpassword", userpassword)
				.setParameter("userid", userid).executeUpdate();

	}
	@Override
	public void updatePADUserPassword2(String userpassword, String userid) {
		this.getCurrentSession()
		.createSQLQuery(
				"UPDATE T_BASIS_USER T set t.USER_PWD=:userpassword, t.NO_LOGIN=null, t.UP_PWD_TIME='2030-07-15 11:00:00' " +
				"where t.USER_CODE=:userid")
				.setParameter("userpassword", userpassword)
				.setParameter("userid", userid).executeUpdate();
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TBasisActivity> queryactivityMessageByCode(List activitycode,
			String updateTime) {
		String valDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		List<TBasisActivity> listUp = this
				.getCurrentSession()
				.createQuery(
						"from TBasisActivity as P where P.updatetime=:updateTime and P.activityStartTime>=:activityStartTime and P.activityEndTime=:activityEndTime and P.status='4'")
				.setParameter("updateTime", updateTime)
				.setParameter("activityStartTime", valDate)
				.setParameter("activityEndTime", valDate)
				.setParameter("activityEndTime", valDate).list();
		if (listUp.size() == 0) {
			List<TBasisActivity> list = this
					.getCurrentSession()
					.createQuery(
							"from TBasisActivity as P where P.activityStartTime<=:activityStartTime and P.activityEndTime>=:activityEndTime and P.status='4' and P.activityCode in (:activitycode )")
					.setParameterList("activitycode", activitycode)
					.setParameter("activityStartTime", valDate)
					.setParameter("activityEndTime", valDate)
					.setParameter("activityEndTime", valDate).list();
			return list;
		}
		return null;
	}

	@Override
	public List<ActivityResponseVO> qryActivitys(String orgCode,
			String updateTime) throws ParseException, IllegalAccessException,
			InvocationTargetException {
		String maxDatehql = "select max(tba.updatetime) from TBasisActivity tba,TBasisActivityOrg tao,TBasisOrg to where tba.activityCode=tao.activityId and tao.orgId=to.orgId and tba.status='4' and to.orgCode='"
				+ orgCode + "' ";
		Query query = this.getCurrentSession().createQuery(maxDatehql);
		String maxDate = (String) query.uniqueResult();
		if (StringUtils.isBlank(maxDate))
			return null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		long serviceTime = dateFormat.parse(maxDate).getTime();
		long padTime = dateFormat.parse(updateTime).getTime();
		if (serviceTime <= padTime)
			return null;
		// String activityhql =
		// "select distinct new com.mobile.application.vo.activity.ActivityVO(tba.activityId as activityId,tba.issuer as issuer,tba.activityName as activityName,tba.activityCode as activityCode,tba.activityStartTime as activityStartTime,tba.activityEndTime as activityEndTime,tba.activityPhotoName as activityPhotoName,tba.activityPhotoPath as activityPhotoPath,tba.activityPhotoLen as activityPhotoLen,tba.status as status,tba.createtime as createtime,tba.updatetime as updatetime,tba.updateuser as updateuser,tba.zipPath as zipPath,tba.zipLength as zipLength, taf.filename as filename, faf.content as content, taf.type as type) "
		// +
		// "from TBasisActivity tba,TBasisActivityOrg tao,TBasisOrg to, TBasisActivityFile taf where tba.activityCode=tao.activityId and tao.orgId=to.orgId and tba.activityCode=taf.activityId and tba.status='4' and to.orgCode='"
		// + orgCode + "' " + //and taf.type='1' " +
		// "and tba.activityStartTime<=:nowDate and tba.activityEndTime>=:nowDate";
		String activityhql = "select distinct new com.mobile.application.vo.activity.ActivityVO(tba.activityId,tba.issuer,tba.activityName,tba.activityCode,tba.activityStartTime,tba.activityEndTime,tba.activityPhotoName,tba.activityPhotoPath,tba.activityPhotoLen,tba.status,tba.createtime,tba.updatetime,tba.updateuser,tba.zipPath,tba.zipLength, taf.filename, taf.content, taf.type) "
				+ "from TBasisActivity tba,TBasisActivityOrg tao,TBasisOrg to, TBasisActivityFile taf where tba.activityCode=tao.activityId and tao.orgId=to.orgId and tba.activityCode=taf.activityId and tba.status='4' and to.orgCode='"
				+ orgCode + "' " + // and taf.type='1' " +
				"and tba.activityStartTime<=:nowDate and tba.activityEndTime>=:nowDate";
		List<ActivityVO> activityMap = this.getCurrentSession()
				.createQuery(activityhql).setParameter("nowDate", nowDate)
				.list();
		Map<String, ActivityResponseVO> tempMap = new HashMap<String, ActivityResponseVO>();
		for (ActivityVO activityVO : activityMap) {
			ActivityPicVO activityPicVO = new ActivityPicVO();
			activityPicVO.setContent(activityVO.getContent());
			activityPicVO.setFilename(activityVO.getFilename());
			activityPicVO.setType(activityVO.getType());
			if (!tempMap.containsKey(activityVO.getActivityId())) {
				ActivityResponseVO activityResponseVO = new ActivityResponseVO();
				List<ActivityPicVO> activityPicVOs = new ArrayList<ActivityPicVO>();
				BeanUtils.copyProperties(activityResponseVO, activityVO);
				activityPicVOs.add(activityPicVO);
				activityResponseVO.setActivityPicVOs(activityPicVOs);
				tempMap.put(activityVO.getActivityId(), activityResponseVO);
			} else {
				ActivityResponseVO activityResponseVO = tempMap.get(activityVO
						.getActivityId());
				activityResponseVO.getActivityPicVOs().add(activityPicVO);
			}
		}
		Set<String> keyset = tempMap.keySet();
		List<ActivityResponseVO> resultList = new ArrayList<ActivityResponseVO>();
		for (String string : keyset) {
			resultList.add(tempMap.get(string));
		}
		return resultList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List queryactivityMessageFileByCode(List activitycode) {
		List<TBasisActivityFile> list = this
				.getCurrentSession()
				.createQuery(
						"from TBasisActivityFile as P where P.activityId in (:activitycode)")
				.setParameterList("activitycode", activitycode).list();
		return list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryactivityMessageOrgByCode(String orgcode) {
		String sql = "select ORG_ID from  " // , ORG_CODE, ORG_NAME, ORG_DESC,
											// ORG_PID, ORG_LEVEL, IS_DISABLE,
											// ORG_ADD, UPDATE_USER,
											// UPDATE_TIME, COLUMN1, COLUMN2
											// from "
				+ " T_BASIS_ORG sub "
				+ " start with "
				+ " sub.ORG_ID = (select t.ORG_ID   from T_BASIS_ORG t  where t.ORG_CODE=:orgcode)  connect by prior sub.ORG_PID = sub.ORG_ID ";
		List list = this.getCurrentSession().createSQLQuery(sql)
				.setParameter("orgcode", orgcode).list();

		List activityCode = this
				.getCurrentSession()
				.createSQLQuery(
						"SELECT  ACTIVITY_ID   FROM T_BASIS_ACTIVITY_ORG  where ORG_ID in (:activitycode)")
				.setParameterList("activitycode", list).list();
		return activityCode;
	}

}
