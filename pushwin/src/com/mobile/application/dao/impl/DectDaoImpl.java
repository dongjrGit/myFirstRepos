package com.mobile.application.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.IDectDAO;
import com.mobile.application.entity.TBasisDict;
import com.mobile.application.entity.TBasisType;

@Repository
public class DectDaoImpl extends BaseDAOImpl<TBasisType> implements IDectDAO{
	
	
	@Override
	public List<?> dictlist(int indexPage,int indexSize,String businTypeName)
	{
		StringBuffer hql = new StringBuffer("select new map(e.busintypeid as id ,e.busintypename as name,e.busintypeid as num,e.businremark as desc,e.updatetime as time) from TBasisType e where 1=1");
		if(StringUtils.isNotBlank(businTypeName))
		{
			hql.append(" and e.busintypename like '%"+businTypeName+"%'");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		hqlQuery.setFirstResult((indexPage)*indexSize);  
		hqlQuery.setMaxResults(indexSize);  
		List<?> list = hqlQuery.list();
		return list;
	}
	
	/**
	 *  分页查询全部数据字典
	 *  姜瑞
	 *  2015-05-27
	 * */
	@Override
	public List<?> dictlistSize(String businTypeName)
	{
		StringBuffer hql = new StringBuffer("select new map(e.busintypeid as id ,e.busintypename as name,e.busintypeid as num,e.businremark as desc,e.updatetime as time) from TBasisType e where 1=1");
		if(StringUtils.isNotBlank(businTypeName))
		{
			hql.append(" and e.busintypename like '%"+businTypeName+"%'");
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql.toString());
		List<?> list = hqlQuery.list();
		return list;
	}

	@Override
	public List<?> listItem(int indexPage, int indexSize, String businTypeId) {
		String hql = "select new map(e.id.businid as id ,e.businname as name,e.id.businid as num,e.id.busintypeid as dictid, e.dictremark as desc,e.updatetime as times) from TBasisDict e where e.id.busintypeid ='"+businTypeId+"'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setFirstResult((indexPage)*indexSize);  
		hqlQuery.setMaxResults(indexSize);  
		List<?> list = hqlQuery.list();
		return list;
	}

	@Override
	public List<?> dictItemSize(String businTypeId) {
		String hql = "select new map(e.id.businid as id ,e.businname as name,e.id.businid as num,e.id.busintypeid as dictid, e.dictremark as desc,e.updatetime as times) from TBasisDict e where e.id.busintypeid ='"+businTypeId+"'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list = hqlQuery.list();
		return list;
	}

	@Override
	public List<?> qryDictByName(String dictName) {
		String hql = "from TBasisType e where e.busintypename = '"+dictName+"'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list =hqlQuery.list();
		return list;
	}

	@Override
	public List<?> qryDictByNO(String dictNum) {
		String hql = "from TBasisType e where e.busintypeid = '"+dictNum+"'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List<?> list =hqlQuery.list();
		return list;
	}

	@Override
	public void saveType(TBasisType dict) {
		this.getCurrentSession().save(dict);
	}
	
	@Override
	public List<?> dialogType(String id)
	{
		String hql ="select new map(e.busintypeid as id ,e.busintypename as dictName,e.busintypeid as dictNum,e.businremark as dictDesc) from TBasisType e where e.id =:id ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("id", id);
		List<?> list = hqlQuery.list();	
		return list;
	}
	
	@Override
	public TBasisType qryTypeById(String id)
	{
		String hql ="from TBasisType e where e.busintypeid =:id ";;
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("id", id);
		List<?> list = hqlQuery.list();	
		TBasisType t = (TBasisType)list.get(0);
		return t;
	}
	@Override
	public List qryTypeByName(String dictTypeName)
	{
		String hql = "from TBasisType e where e.busintypename =:name ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("name", dictTypeName);
		List<?> list =hqlQuery.list();
		return list;
	}
	
	/**
	 * 根据ID删除数据
	 * 姜瑞
	 * 2014-3-26
	 * */
	@Override
	public void deleteItem(String id,String dictId)
	{
		String[] dIds = id.split(",");
		for(int i = 0 ; i<dIds.length; i++)
		{
			String sql = "delete from T_BASIS_DICT where BUSINID = '"+dIds[i]+"' and  BUSINTYPEID = '"+dictId+"'";
			Query query = this.getCurrentSession().createSQLQuery(sql);
			query.executeUpdate();
		}
	}
	
	@Override
	public List<?> dialogItem(String typeId, String itemId) { 
		String hql ="select new map(e.dictremark as dictDesc,e.id.businid as dictsonId ,e.businname as dictName,e.id.businid as dictNum,e.id.busintypeid as id) from TBasisDict e where e.id.businid =:itemId and e.id.busintypeid =:typeId ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("itemId", itemId).setParameter("typeId", typeId);
		List<?> list = hqlQuery.list();	
		return list;
	}

	@Override
	public TBasisDict qryItemById(String typeId, String itemId) {
		String hql ="from TBasisDict e where e.id.businid =:itemId  and  e.id.busintypeid =:typeId ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("itemId", itemId).setParameter("typeId", typeId);
		List<?> list = hqlQuery.list();
		if(list==null||list.size()<=0)
			return null;
		TBasisDict t = (TBasisDict)list.get(0);
		return t;
	}
	
	/**
	 * 根据父级ID判断数据是否重复（名称）
	 * 梁伯肇
	 * 2014-3-26
	 * */
	@Override
	public List<?> dictNameSonlist(String name,String id)
	{
		String hql = "from TBasisDict e where e.businname =:name  and e.id.busintypeid = :id ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("name", name).setParameter("id", id);
		List<?> list =hqlQuery.list();
		return list;
	}

	@Override
	public void updateItem(TBasisDict dict) {
		this.getCurrentSession().saveOrUpdate(dict);
	}
	
	/**
	 * 根据ID查看字典下是否有关联信息
	 * 梁伯肇
	 * 2014-3-26
	 * */
	public long queryDictUser(String id)
	{
		String hql = " Select count(e.status) from TBasisDict e where e.id.busintypeid=:dictId";
		Query query  = this.getCurrentSession().createQuery(hql);
		Long count = (Long) query.setString("dictId", id).uniqueResult();
		return count;	
	}
	
	/**
	 * 根据ID删除字典
	 * 梁伯肇
	 * 2014-3-26
	 * */
	public void delType(String id)
	{
		String sql  = "DELETE from T_BASIS_TYPE where busintypeid =:DICTID";
		Query queryOrg = this.getCurrentSession().createSQLQuery(sql);
		queryOrg.setString("DICTID", id);
		queryOrg.executeUpdate();
	}
	
	
	/**
	 * 根据父级ID判断数据是否重复（编号）
	 * 梁伯肇
	 * 2014-3-26
	 * */
	public List dictNumSonlist(String num,String id)
	{
		String hql = "from TBasisDict e where e.id.businid = '"+num+"' and e.id.busintypeid = '"+id+"'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		List list =hqlQuery.list();
		return list;
	}
	
	/**
	 * 保存数据子级数据字典
	 */
	public void saveItem(TBasisDict dictSon){
		this.getCurrentSession().saveOrUpdate(dictSon);
	}
	
	@Override
	public List<TBasisDict> qryAllDict(){
		String hql = "from TBasisDict";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> qryDicts(Object obj){
		JSONObject resJson = JSONObject.fromObject(obj);
		String lastUpdateTime = (resJson).optString("lastUpdateTime");
		String bankFlag = (resJson).optString("bankFlag");
		String parematerflag = SpringProperty.props.getProperty("parematerflag");
		//public List<?> qryDicts(String lastUpdateTime){
		Map<String,Object> map = new HashMap<String, Object>();
		String updateTimeHql = "select new map(max(updatetime) as updateTime) from TBasisDict";
		Query updateTimeQuery = this.getCurrentSession().createQuery(updateTimeHql);
		
		Map<String, String> updateTimeMap = (Map<String, String>) updateTimeQuery.uniqueResult();
		Date updatetime = DateUtil.getDateTimeNJ(updateTimeMap.get("updateTime"));
		//推荐人
		if(StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime()&&"1".equals(parematerflag)){	
//			String hql = "select new map(tbd.id.businid as dictId, tbd.businname as businname,tbd.dictremark as dictremark,tbd.busintypeid as busintypeid) from TBasisBusSale tbd where tbd.id.erea='301' order by tbd.id.businid";
			String hql = "";
			if( "NJCB".equals(bankFlag) ){
				hql = "select new map(tbd.id.businid as dictId, tbd.businname as businname,tbd.dictremark as dictremark,tbd.busintypeid as busintypeid) from TBasisBusSale tbd where tbd.id.erea='301' and tbd.busintypeid != '555' order by tbd.id.businid";
			}else if( "KSLC".equals(bankFlag) ){
				hql = "select new map(tbd.id.businid as dictId, tbd.businname as businname,tbd.dictremark as dictremark,tbd.busintypeid as busintypeid) from TBasisBusSale tbd where tbd.id.erea='301' and tbd.busintypeid = '555' order by tbd.id.businid";
			}else if( "RZCB".equals(bankFlag) ){
				hql = "select new map(tbd.id.businid as dictId, tbd.businname as businname,tbd.dictremark as dictremark,tbd.busintypeid as busintypeid) from TBasisBusSale tbd where tbd.id.erea='302' order by tbd.id.businid";
			}
			Query query = this.getCurrentSession().createQuery(hql);
			 map.put("SALE",query.list() );
		}
		//优惠信息
		if((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())&&"1".equals(parematerflag)){	
			//TBasisBusFavorablemessag
			String hql = "";
			if( "NJCB".equals(bankFlag) ){
				hql = "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark) from TBasisBusFavorablemessag tbd where tbd.area='" + 301 + "' and tbd.dictremark='NJCB' order by tbd.busintypeid";
			}else if( "KSLC".equals(bankFlag) ){
				hql = "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark) from TBasisBusFavorablemessag tbd where tbd.area='" + 301 + "' and tbd.dictremark='KSLC' order by tbd.busintypeid";
			}else if( "RZCB".equals(bankFlag) ){
				hql = "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark) from TBasisBusFavorablemessag tbd where tbd.area='" + 302 + "' order by tbd.busintypeid";
			}
		//               "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark) from TBasisDictBranch tbd where tbd.busintypeid='" + typeid + "' order by tbd.busintypeid";
			Query query = this.getCurrentSession().createQuery(hql);
			 map.put("FavorableMessage",query.list() );
		}
		//贷款通途
		if((StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime())&&"1".equals(parematerflag)){	
			//TBasisBusPurchasecode
//				String hql = "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark) from TBasisBusPurchasecode tbd where tbd.area='" + 301 + "' order by tbd.busintypeid";
				String hql = "";
				if( "NJCB".equals(bankFlag) ){
					hql = "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark) from TBasisBusPurchasecode tbd where tbd.area='" 
							+ 301 
							+ "' and tbd.updateuser='NJCB' order by tbd.busintypeid";
				}else if( "KSLC".equals(bankFlag) ){
					hql = "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark) from TBasisBusPurchasecode tbd where tbd.area='" 
							+ 301 
							+ "' and tbd.updateuser ='KSLC' order by tbd.busintypeid";
				}else if( "RZCB".equals(bankFlag) ){
					hql = "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark) from TBasisBusPurchasecode tbd where tbd.area='" 
							+ 302 
							+ "' order by tbd.busintypeid";
				}
				//               "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark) from TBasisDictBranch tbd where tbd.busintypeid='" + typeid + "' order by tbd.busintypeid";
					Query query = this.getCurrentSession().createQuery(hql);
					 map.put("PurchaseCode",query.list() );
		}
		if(StringUtils.isBlank(lastUpdateTime) || updatetime.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime()){		
			StringBuffer sqltype = new StringBuffer( "FROM TBasisType AS t where t.busintypeid <>'EducationLevel' " +
					//"and t.busintypeid <>'CompanyType'  "+// 单位性质
					"and t.busintypeid <>'JobTeacherLevel'  "+// 教师级别
					"and t.busintypeid <>'MaritalStatus'   "+//婚姻状况
					"and t.busintypeid <>'ACCOUNT'   "+//
					"and t.busintypeid <>'OCCCD'   "+//
					"and t.busintypeid <>'MaritalStatus'       "+//MaritalStatus	婚姻状况
					"and t.busintypeid <>'OWNHM'   "+//OWNHM	住宅状况
					"and t.busintypeid <>'CDFRMR'   "+//CDFRMR	附卡版面
					"and t.busintypeid <>'BEFD'   "+//BEFD	账单日
					"and t.busintypeid <>'CREDIT_CARD_TYPE'   "+//CREDIT_CARD_TYPE	信用卡卡类
					"and t.busintypeid <>'ETHNCMORE'   "+//ETHNCMORE	更多证件类型                
					//"and t.busintypeid <>'CompanyType'  "+// NOTE_REMIND	是否短信提醒              
					"and t.busintypeid <>'NOTE_REMIND'  "+// AUTO_REPAYL	是否自动还款              
					"and t.busintypeid <>'FEECD'   "+//FEECD	卡片费用类型                    
					"and t.busintypeid <>'NATON'   "+//NATON	国籍                            
					"and t.busintypeid <>'NATCD'  "+// NATCD	国家                            
					"and t.busintypeid <>'INTRL'   "+//INTRL	推广人员与申请人关系            
					"and t.busintypeid <>'REPAY'   "+//REPAY	自动还款方式                    
					"and t.busintypeid <>'CONTACT_RELATION'  "+// CONTACT_RELATION	直系与申请人关系    
					"and t.busintypeid <>'NEED_PASSWORD'   "+//NEED_PASSWORD	刷卡是否凭密码          
					"and t.busintypeid <>'OTHER_RELATION'  "+// OTHER_RELATION	其他与申请人关系  
					"and t.busintypeid <>'SPREADER_CODE' "+//SPREADER_CODE	推广人与申请人关系代码   
					"and t.busintypeid <>'SUBCARD_PAYWAY' "+//SUBCARD_PAYWAY	附属卡信用额度使用方式 
					"and t.busintypeid <>'SEX' "+//SEX	性别                               
					"and t.busintypeid <>'CART' "+//CART	车辆类型                         
					"and t.busintypeid <>'RESVE' "+//RESVE	预留问题                         
					"and t.busintypeid <>'CDFRM' "+//CDFRM	主卡版面                         
					"and t.busintypeid <>'BKREL' "+//BKREL	申请人与本行社往来关系           
					"and t.busintypeid <>'BASIS_DEADLINE'"+// BASIS_DEADLINE	密码过期时间           
					"and t.busintypeid <>'STMCD' "+//STMCD	账单寄送方式                     
					"and t.busintypeid <>'ADREL'"+// ADREL	"+//与主卡人关系                     
					"and t.busintypeid <>'SUBCARD_RATE' "+//SUBCARD_RATE	附属卡信用额度比例       
					"and t.busintypeid <>'DRIVING' "+//DRIVING	有无行驶证                     
					"and t.busintypeid <>'CARD_CONDITION' "+//CARD_CONDITION	信用卡情况             
					"and t.busintypeid <>'SCHE' "+//SCHE	专案代码选择                     
					"and t.busintypeid <>'BASIS_APK_CHANNEL'  "+// BASIS_APK_CHANNEL	渠道类型
					"and t.busintypeid <>'APQUE'  "+// APQUE	类别                
					"and t.busintypeid <>'BASIS_APK_CHANNEL'"+// BASIS_APK_CHANNEL	渠道类型        
					"and t.busintypeid <>'APQUE' "+//APQUE	类别                        
					"and t.busintypeid <>'BASIS_APK_TYPE' "+//BASIS_APK_TYPE	业务类型          
					"and t.busintypeid <>'ABSOU' "+//ABSOU	进件来源                    
					"and t.busintypeid <>'CDESP' "+//CDESP	卡片寄送方式                
					"and t.busintypeid <>'BASIS_CREDIT_STATUS' "+//BASIS_CREDIT_STATUS	信用卡进件状态
					"and t.busintypeid <>'C_IDEN' "+//C_IDEN	申请卡标识                
					"and t.busintypeid <>'C_IDEN' "+//C_IDEN	申请卡标识  
					"and t.busintypeid <>'ADDRESS'"+// ADDRESS	账单寄送地址
					"and t.busintypeid <>'HASCAR' "+//HASCAR	车辆情况    
					"and t.busintypeid <>'devicebrand' "+//devicebrand	"+//设备品牌
					"and t.busintypeid <>'单位性质' "+//单位性质	"+//设备品牌
					"and t.busintypeid <>'ETHNC' "+//ETHNC	"+//设备品牌
					"and t.busintypeid <>'AUTO_REPAYL' "+//AUTO_REPAYL	"+//设备品牌
					"and t.busintypeid <>'CompanyMemo' "+//AUTO_REPAYL	"+//行业信息
					"and t.busintypeid <>'WHTAX' "+//WHTAX	"+//设备品牌
					//-------------end
					"and t.busintypeid <>'deviceos' " //设备系统	"+//设备品牌
					);
					//new paramter
					if("1".equals(parematerflag)){
						//推荐受理人
						sqltype.append(	"and t.busintypeid <>'SALE' ");//WHTAX	"+//推荐受理人
						sqltype.append(	"and t.busintypeid <>'FavorableMessage' " );//WHTAX	"+//推荐受理人
						sqltype.append(	"and t.busintypeid <>'PurchaseCode' " );//WHTAX	"+//推荐受理人
					}
				
				
			List<TBasisType> ditcdes =  this.getCurrentSession().createQuery(sqltype.toString())
											.list();
			for (TBasisType tBasisDict : ditcdes) {
				List list = qryDectByTypeWithDescAllDesc(tBasisDict.getBusintypeid());
				List listbracnh  = ParameterBracnch(tBasisDict.getBusintypeid(),bankFlag);
				if(list.size()>0){
					map.put(tBasisDict.getBusintypeid(), list);
				}else{
					map.put(tBasisDict.getBusintypeid(), listbracnh);
				}
				System.out.println(tBasisDict.toString());
			}
			

		/*	String hql = "select new map(dict.id.busintypeid as type, dict.id.businid as key, dict.id.businid.businname as value, dict.id.businid.dictremark as desc) from TBasisDict dict ";
			Query query = this.getCurrentSession().createQuery(hql);
			return query.list();
			*/
			return map;
		} 
		
		else {
			return null;
		}
		
	}

	@Override
	public List<?> qryProvinces(String lastUpdateTime,String bankFlag) {
		String updateTimeHql = "select new map(max(updateTime) as updateTime) from TBasisProvinces";
		Query updateTimeQuery = this.getCurrentSession().createQuery(updateTimeHql);
		Map<String, Date> updateTimeMap = (Map<String, Date>) updateTimeQuery.uniqueResult();
		if(StringUtils.isBlank(lastUpdateTime) || updateTimeMap.get("updateTime").getTime() > DateUtil.getDateTime(lastUpdateTime).getTime()){
			//String hql = "select new map(tbp.provinceId as provinceId, tbp.provinceName as provinceName, tbp.orgId as orgId, tbp.updateUser as updateUser, tbp.updateTime as updateTime) from TBasisProvinces tbp";
			String hql = "select new map(tbc.orgId as orgId, tbc.provinceId as provinceId, tbc.provinceName as provinceName) from TBasisProvinces tbc";
			Query query = this.getCurrentSession().createQuery(hql);
			return query.list();
		} else {
			return null;
		}
	}
	
	@Override
	public List<?> qryCity(String lastUpdateTime,String bankFlag) {
		
		String updateTimeHql = "select new map(max(updateTime) as updateTime) from TBasisCity";
		Query updateTimeQuery = this.getCurrentSession().createQuery(updateTimeHql);
		Map<String, Date> updateTimeMap = (Map<String, Date>) updateTimeQuery.uniqueResult();
		if(StringUtils.isBlank(lastUpdateTime) || updateTimeMap.get("updateTime").getTime() > DateUtil.getDateTime(lastUpdateTime).getTime()){
			//String hql = "select new map(tbc.cityId as cityId, tbc.TBasisProvinces.provinceId as provinceId, tbc.cityName as cityName, tbc.zipcode as zipcode, tbc.updateUser as updateUser, tbc.updateTime as updateTime) from TBasisCity tbc";
			String hql = "select new map(tbc.cityId as cityId, tbc.TBasisProvinces.provinceId as provinceId, tbc.cityName as cityName) from TBasisCity tbc";
			Query query = this.getCurrentSession().createQuery(hql);
			return query.list();
		} else {
			return null;
		}
		
	}
	
	@Override
	public List<?> qryDistrict(String lastUpdateTime,String bankFlag) {
		
		String updateTimeHql = "select new map(max(updateTime) as updateTime) from TBasisDistrict";
		Query updateTimeQuery = this.getCurrentSession().createQuery(updateTimeHql);
		Map<String, Date> updateTimeMap = (Map<String, Date>) updateTimeQuery.uniqueResult();
		if(StringUtils.isBlank(lastUpdateTime) || updateTimeMap.get("updateTime").getTime() > DateUtil.getDateTime(lastUpdateTime).getTime()){
		//String hql = "select new map(tbd.districtId as districtId, tbd.TBasisCity.cityId as cityId, tbd.districtName as districtName, tbd.updateUser as updateUser, tbd.updateTime as updateTime) from TBasisDistrict tbd";
			String hql = "select new map(tbd.districtId as districtId, tbd.TBasisCity.cityId as cityId, tbd.districtName as districtName) from TBasisDistrict tbd";
			Query query = this.getCurrentSession().createQuery(hql);
			return query.list();
		} else {
			return null;
		}
		
	}
	@Override
	public List<?> qryDectByTypeWithDesc(String typeId) {
		String hql = "select new map(tbd.id.businid as dictId, tbd.dictremark||'——'||tbd.businname as businname) from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";
		if("ACCOUNT".equals(typeId)){
				 hql = "select new map(tbd.id.businid as dictId, tbd.businname as businname) from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";	
		}
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@Override
	public List<?> qryDectByType(String typeId) {
		String hql = "select new map(tbd.id.businid as dictId, tbd.businname as businname) from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	
	@Override
	public List<?> qryDectByTypeWithDescAllDesc(String typeId) {
/*		String hql = "from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();*/
		String hql = "select new map(tbd.id.businid as dictId, tbd.businname as businname,tbd.dictremark as dictremark) from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	
/*	String hql = "select new map(tbd.id.businid as dictId, tbd.businname as businname) from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";
	Query query = this.getCurrentSession().createQuery(hql);
	return query.list();*/
	
	public List<?> ParameterBracnch(String typeid,String bankFlag) {
		String hql = "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark,tbd.productCode as productcode) from TBasisDictBranch tbd where tbd.busintypeid='" + typeid + "' order by tbd.busintypeid";
		if("CustClass".equals(typeid)){
			hql = "select new map(tbd.id.businid as updateuser,tbd.id.businidBracnch as status,tbd.businname as businname,tbd.dictremark as dictremark,tbd.productCode as productcode) from TBasisDictBranch tbd where tbd.dictremark='"+bankFlag+"' and tbd.busintypeid='" + typeid + "' order by tbd.busintypeid";
		}
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	public List<?> qryDectByTypeWithDescCompany(String typeId) {
/*		String hql = "from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();*/
		String hql = "select new map(tbd.id.businid as businid, tbd.businname as businname ,tbd.orgArea as orgArea) from TBasisDictCompabymemo tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	public List<?> qryDectByTypeWithDescAllPlanStore(String typeId) {
/*		String hql = "from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();*///businidbranchbusinidbranch
		String hql = "select new map(tbd.id.businid as businid,tdb.id.businidbranch as beizhu1) from TBasisDictPlanstore tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	public List<?> qryDectByTypeWithDescAllPlanType(String typeId) {
/*		String hql = "from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();*///businidbranchbusinidbranchtb
		String hql = "select new map(tbd.id.businid as businid,tdb.businname as businname,tdb.low as low,tdb.floorlow as floorlow,tdb.cusclas as cusclas,tdb.panpro as panpro) from TBasisDictPlantype tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	public List<?> qryDectByTypeWithDescAlStores(String typeId) {
/*		String hql = "from TBasisDict tbd where tbd.id.busintypeid='" + typeId + "' order by tbd.id.businid";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();*///TBasisDictStores
		String hql = "select new map(tbd.businid as businid," +
				"tbd.storesdis  as storesdis ," +
				"tbd.businidbranch  as businidbranch," +
				"tbd.storetype  as storetype," +
				"tbd.orapo as orapo," +
				"tbd.businname as businname ," +
				"tbd.orgArea as orgArea,tbd.businame as businame,tbd.dictremark as dictremark) from TBasisDictCompabymemo tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> qryOrg(String lastUpdateTime,String bankFlag) {
		String updateTimeHql = "select new map(max(updaTime) as updaTime) from TBasisBusBank";
		Query updateTimeQuery = this.getCurrentSession().createQuery(updateTimeHql);
		Map<String, String> updateTimeMap = (Map<String, String>) updateTimeQuery.uniqueResult();
		//Date dateone = updateTimeMap.get("updaTime");
		Date updatetimestoreGoods = DateUtil.getDateTimeNJ(updateTimeMap.get("updaTime") + " 00:00:00");
		if(StringUtils.isBlank(lastUpdateTime) || updatetimestoreGoods.getTime() > DateUtil.getDateTime(lastUpdateTime).getTime()){
			String hql = "select new map(tbd.orgCode as orgCode," +
				"tbd.orgName as orgName ," +
				"tbd.orgPid as orgPid) from TBasisOrg tbd ";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
		}else{
			return null;
		}
	}
	public static void main(String[] args) throws ParseException {
		String ss = "20160301";
		//Date updatetime = DateUtil.getDateTime(ss);
		
		SimpleDateFormat	dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date	updatetime  = dateFormat.parse(ss);
		System.out.println(updatetime.getTime());
	}
}
