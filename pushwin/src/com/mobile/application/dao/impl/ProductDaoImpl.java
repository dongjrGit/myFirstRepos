package com.mobile.application.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.dao.IProductDao;
import com.mobile.application.entity.TBasisProductMenu;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.product.ProductMenuVO;

@Repository
public class ProductDaoImpl extends BaseDAOImpl<TBasisProductMenu> implements IProductDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductMenuVO> qryProductMenu() {
//		this.getCurrentSession().createQuery("select new map(count(tpm.menuId)) from TBasisProductMenu tpm join tpm.TBasisProductTemplate as tpt").list();
//		return null;
		return this.getCurrentSession().createQuery("select new com.mobile.application.vo.product.ProductMenuVO(tpm.menuId, tpm.menuName, tpm.menuDesc, tpm.menuPid, tpm.menuLevel, tpm.menuSort, tpm.menuIcon, tpm.keyWords, tpt.templateId, tpt.templateName, tpt.templateDesc, tpt.templatePic, tbo.orgName as updateOrg ,u.userCode as updateUser,tpm.createTime) from TBasisProductMenu tpm left join tpm.TBasisProductTemplate tpt, TBasisUser u left join u.TBasisOrg tbo where u.userId = tpm.updateUser order by tpm.menuSort").list();
	}
	
	@Override
	public List<ProductMenuVO> qryProductMenuForPad(String orgCode) {
//		this.getCurrentSession().createQuery("select new map(count(tpm.menuId)) from TBasisProductMenu tpm join tpm.TBasisProductTemplate as tpt").list();
//		return null;
		List<Map<String, String>> menuIdMap = this.getCurrentSession().createQuery("select new map(tpm.menuId as menuId, tpm.menuPid as menuPid) from TBasisProductOrg tpo left join tpo.TBasisProduct tp left join tp.TBasisProductMenu tpm where tpo.TBasisOrg.orgCode='" + orgCode + "' and tp.status='5' ").list();
		if(null != menuIdMap && menuIdMap.size() > 0){
			Set<String> menuIdSet = new HashSet<String>();
			for (Map<String, String> map : menuIdMap) {
				menuIdSet.add(map.get("menuId"));
				menuIdSet.add(map.get("menuPid"));
			}
			Query query = this.getCurrentSession().createQuery("select new com.mobile.application.vo.product.ProductMenuVO(tpm.menuId, tpm.menuName, tpm.menuDesc, tpm.menuPid, tpm.menuLevel, tpm.menuSort, tpm.menuIcon, tpm.keyWords, tpt.templateId, tpt.templateName, tpt.templateDesc, tpt.templatePic) from TBasisProductMenu tpm left join tpm.TBasisProductTemplate tpt where tpm.menuId in (:menuIdSet) order by tpm.menuSort");
			query.setParameterList("menuIdSet", menuIdSet);
			return query.list();
		} else {
			return new ArrayList<ProductMenuVO>();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductMenuVO> qryMenuByName(ProductMenuVO menuVO){
		String hql = "select new com.mobile.application.vo.product.ProductMenuVO(menuId, menuName, menuDesc, menuPid, menuLevel, menuSort, menuIcon,keyWords) from TBasisProductMenu where menuName=:menuName";
		return this.getCurrentSession().createQuery(hql).setParameter("menuName", menuVO.getMenuName()).list();
	}

	@Override
	public List<?> qryProductList(int pageIndex, int pageSize,String userName,String orgId,String productName,String menuId,String onlineTime,String offlineTime,String keyWord,TBasisUser user) {
		String hql = "select distinct new map(p.id as id, p.TBasisProductMenu.menuId as menuId, p.TBasisProductMenu.menuName as menuName, case p.status when '1' then '已保存' when '2' then '待审核' when '3' then '审核通过' when '4' then '审核不通过' when '5' then '已上架' when '6' then '已下架' end as statusDesc, p.status as status, tpt.menuName as menuName, p.productName as productName, p.updateTime as updateTime, u.userCode as userCode, u.userName as userName, p.onlineTime as onlineTime, p.offlineTime as offlineTime, p.keyWord as keyWord, template.templateName as templateName) from TBasisProductOrg po left join po.TBasisProduct p, TBasisUser u left join p.TBasisProductMenu tpt left join tpt.TBasisProductTemplate template ";
		if(!"1".equals( user.getIsAdmin())){
			hql += " ,TBasisUserData  ud  where p.updateUser=u.userId and ud.TBasisUser.userId='"+user.getUserId()+"' and po.TBasisOrg.orgId=ud.TBasisOrg.orgId";
		}else{
		hql += " where p.updateUser=u.userId";
		}
		if(StringUtils.isNotBlank(userName)){
			hql += " and u.userName like '%"+userName+"%' ";
		}
		if(StringUtils.isNotBlank(orgId)){
			hql += " and po.TBasisOrg.orgId = '"+orgId+"' ";		
		}
		if(StringUtils.isNotBlank(productName)){
			hql += " and p.productName = '"+productName+"' ";
		}
		if(StringUtils.isNotBlank(menuId)){
			hql += " and p.TBasisProductMenu.menuId = '"+menuId+"' ";
		}
		if(StringUtils.isNotBlank(onlineTime)){
			hql += " and p.onlineTime = '"+onlineTime+"' ";
		}
		if(StringUtils.isNotBlank(offlineTime)){
			hql += " and p.offlineTime = '"+offlineTime+"' ";
		}
		if(StringUtils.isNotBlank(keyWord)){
			hql += " and p.keyWord like '%"+keyWord+"%' ";
		}
		Query query = this.getCurrentSession().createQuery(hql).setFirstResult((pageIndex)*pageSize).setMaxResults(pageSize);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String qryProductSize(String userName,String orgId,String productName,String menuId,String onlineTime,String offlineTime,String keyWord,TBasisUser user){
		String hql = "select  new map( count(distinct p.id) as ProductSize) from TBasisProductOrg po left join po.TBasisProduct p, TBasisUser u left join p.TBasisProductMenu tpt left join tpt.TBasisProductTemplate template ";
		
		if(!"1".equals( user.getIsAdmin())){
			hql += " ,TBasisUserData  ud  where p.updateUser=u.userId and ud.TBasisUser.userId='"+user.getUserId()+"' and po.TBasisOrg.orgId=ud.TBasisOrg.orgId";
		}else{
		    hql += " where p.updateUser=u.userId";
		}
		if(StringUtils.isNotBlank(userName)){
			hql += " and u.userName like '%"+userName+"%' ";
		}
		if(StringUtils.isNotBlank(orgId)){
			hql += " and po.TBasisOrg.orgId = '"+orgId+"' ";		
		}
		if(StringUtils.isNotBlank(productName)){
			hql += " and p.productName = '"+productName+"' ";
		}
		if(StringUtils.isNotBlank(menuId)){
			hql += " and p.TBasisProductMenu.menuId = '"+menuId+"' ";
		}
		if(StringUtils.isNotBlank(onlineTime)){
			hql += " and p.onlineTime = '"+onlineTime+"' ";
		}
		if(StringUtils.isNotBlank(offlineTime)){
			hql += " and p.offlineTime = '"+offlineTime+"' ";
		}
		if(StringUtils.isNotBlank(keyWord)){
			hql += " and p.keyWord like '%"+keyWord+"%' ";
		}
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		Map<String, Long> userCountMap = (Map<String, Long>) hqlQuery.uniqueResult(); // 		
		return userCountMap.get("ProductSize").toString();
	}
	
	@Override
	public Map<String, String> qryProduct(String productId) {
		String hql = "select new map(p.id as id, p.TBasisProductMenu.menuId as menuId, p.TBasisProductMenu.menuName as menuName, p.TBasisProductMenu.keyWords as menuKeywords, p.productName as productName, p.productDesc as productDesc,  p.updateTime as updateTime, u.userCode as userCode, u.userName as userName, p.onlineTime as onlineTime, p.offlineTime as offlineTime, p.keyWord as keyWord,p.status as status,p.facePage as facePage ,p.updateTime as updateTime,template.templateName as templateName, template.templateName.queryUrl as queryUrl) from TBasisProduct p, TBasisUser u left join p.TBasisProductMenu tpt left join tpt.TBasisProductTemplate template  where p.updateUser=u.userId and p.id=:productId";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("productId", productId);
		return (Map<String, String>) query.uniqueResult();
	}

	@Override
	public CommonVO saveProductPic(String productId) {
		return null;
	}

	@Override
	public List<?> qryTemplate() {
		String hql = "select new map(tpt.templateId as templateId, tpt.templatePic as templatePic, tpt.updateUser as updateUser, " +
				"tpt.updateTime as updateTime, tpt.templateDesc as templateDesc, tpt.queryUrl as queryUrl, tpt.modifyUrl as modifyUrl, tpt.templateName as templateName) from TBasisProductTemplate tpt";
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void delorg(String productId) {
		String sql="delete  from T_BASIS_PRODUCT_ORG where PRODUCT_ID ='"+productId+"' ";
		Query query = this.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	@Override
	public List<?> qryOrgByProductId(String productId) {
		String hql = " Select new map(po.TBasisOrg.orgId as orgId, po.TBasisOrg.orgName as orgName) from TBasisProductOrg po where po.TBasisProduct.id=:productId";
		Query query = this.getCurrentSession().createQuery(hql).setParameter("productId", productId);
		List<?> list=query.list();
		return list;
	}

	@Override
	public List<Map<String, String>> qryCheckHistory(String productId) {
		String hql = " Select new map(pc.opinion as opinion,pc.checkTime as checkTime,u.userCode as checkUser) from TBasisProductCheck pc,TBasisUser u where u.userId=pc.checkUser and pc.TBasisProduct.id=:productId";
		Query query = this.getCurrentSession().createQuery(hql).setParameter("productId", productId);
		List<Map<String, String>> list=query.list();
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> qryProductCheckList(int pageIndex, int pageSize,TBasisUser user) {
		Query query;
		String hql = "select distinct new map(p.id as id, p.TBasisProductMenu.menuId as menuId, p.TBasisProductMenu.menuName as menuName, case p.status  when '2' then '待审核'  end as statusDesc, p.status as status, tpt.menuName as menuName, p.productName as productName, p.updateTime as updateTime, u.userCode as userCode, u.userName as userName, p.onlineTime as onlineTime, p.offlineTime as offlineTime, p.keyWord as keyWord, template.templateName as templateName)  from  TBasisProduct p, TBasisUser u left join p.TBasisProductMenu tpt left join tpt.TBasisProductTemplate template ";
		if(!"1".equals( user.getIsAdmin())){
			String orglisthql = "select distinct ud.TBasisOrg.orgId from TBasisUserData ud where ud.TBasisUser.userId='"+user.getUserId()+"'  ";
			List<String> orglist = this.getCurrentSession().createQuery(orglisthql).list();
			hql += " ,TBasisUser u1 where  u1.TBasisOrg.orgId in (:userDataList) and u1.userId = p.createUser  and p.updateUser=u.userId and p.status='2'  ";
			 query = this.getCurrentSession().createQuery(hql).setParameterList("userDataList", orglist).setFirstResult((pageIndex)*pageSize).setMaxResults(pageSize);
		}else{
		    hql += " where  p.updateUser=u.userId and  p.status='2' ";
		     query = this.getCurrentSession().createQuery(hql).setFirstResult((pageIndex)*pageSize).setMaxResults(pageSize);
		}
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String qryProductCheckSize(TBasisUser user){
		Query hqlQuery;
		String hql = "select new map( count(distinct p.id) as ProductSize) from TBasisProduct p, TBasisUser u left join p.TBasisProductMenu tpt left join tpt.TBasisProductTemplate template  ";
		if(!"1".equals( user.getIsAdmin())){
			String orglisthql = "select ud.TBasisOrg.orgId from TBasisUserData ud where ud.TBasisUser.userId='"+user.getUserId()+"'  ";
			List<String> orglist = this.getCurrentSession().createQuery(orglisthql).list();
			hql += ",TBasisUser u1 where  u1.TBasisOrg.orgId in (:userDataList) and u1.userId = p.createUser  and p.updateUser=u.userId and p.status='2'  ";
			hqlQuery = this.getCurrentSession().createQuery(hql).setParameterList("userDataList", orglist);
		}else{
		    hql += " where  p.updateUser=u.userId and  p.status='2' ";
		    hqlQuery = this.getCurrentSession().createQuery(hql);
		}
		Map<String, Long> userCountMap = (Map<String, Long>) hqlQuery.uniqueResult(); // 		
		return userCountMap.get("ProductSize").toString();
	}

	@Override
	public List<?> checkUpdateProduct(String orgCode) {
		String hql = "select new map(tpo.TBasisProduct.id as productId, tpo.TBasisProduct.zipUptime as updateTime) from TBasisProductOrg tpo where tpo.TBasisOrg.orgCode = :orgCode and tpo.TBasisProduct.status='5'";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("orgCode", orgCode);
		return hqlQuery.list();
	}
	
	@Override
	public List<Map<String, Object>> qryProductForDownload(String productIds) {
		String[] productArray = productIds.split(",");
		String hql = "select new map(tp.id as productId, tp.zipUptime as updateTime, tp.productName as productName, tpm.menuId as menuId, " +
				"tp.productZip as productZip, tp.facePage as facePage, tp.keyWord as keyWord, tp.zipLength as zipLength," +
				"template.templateId as templateId, template.templateDesc as templateDesc, template.templateName as templateName, tp.productDesc as productDesc) " +
				"from TBasisProduct tp left join tp.TBasisProductMenu tpm left join tpm.TBasisProductTemplate template where tp.id in (:productIds)";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameterList("productIds", productArray);
		return hqlQuery.list();
	}

	@Override
	public List<?> qryApkId() {
		String hql = "select new map(apk.dealId as apkId,apk.dealName as apkName) from TBasisApk apk ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		return hqlQuery.list();
	}
	@Override
	public List<?> qryProductById(String menuId) {
		String hql = "select new map(p.id as productId) from TBasisProduct p where p.TBasisProductMenu.menuId=:menuId ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("menuId", menuId);
		return hqlQuery.list();
	}

	@Override
	public void updateProdutStatus(String status, String productId) {
		String hql = "update TBasisProduct set status='" + status + "'";
		if("6".equals(status))
			hql += ", offlineTime='" + DateUtil.formatDate(new Date()) + "'";
			hql += " where id='" + productId + "'";
		this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String isAuthority(TBasisUser user, String menuId) {
		String hql = "select new map (count(distinct pm.menuId) as countmenuId)from TBasisProductMenu pm ,TBasisUserData ud ,TBasisUser u where ud.TBasisUser.userId = :userId and ud.TBasisOrg.orgId=u.TBasisOrg.orgId and u.userId=pm.updateUser and pm.menuId =:menuId  ";
		Query hqlQuery = this.getCurrentSession().createQuery(hql);
		hqlQuery.setParameter("userId", user.getUserId());
		hqlQuery.setParameter("menuId", menuId);
		Map<String, Long> isAuthority = (Map<String, Long>) hqlQuery.uniqueResult(); // 		
		return isAuthority.get("countmenuId").toString();
	}
}
