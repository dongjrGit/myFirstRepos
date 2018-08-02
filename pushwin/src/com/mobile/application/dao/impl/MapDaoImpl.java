package com.mobile.application.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.IMapDao;
import com.mobile.application.entity.TBasisPosition;
import com.mobile.application.vo.session.SessionVO;

@Repository
public class MapDaoImpl extends BaseDAOImpl<TBasisPosition> implements IMapDao{
	@Override
	public List<?> qryDevicePosition(String userId,String updateTime){
		String hql = "select new map(tbp.latitude as LATITUDE, tbp.longitude as LONGITUDE, tbp.updateTime as POSITION_TIME, tbp.TBasisUser.userCode as USER_CODE, tbp.TBasisUser.userName as USER_NAME, tbp.TBasisUser.TBasisOrg.orgCode as ORG_CODE, tbp.TBasisUser.TBasisOrg.orgName as ORG_NAME) from TBasisPosition tbp where tbp.TBasisUser.userId=:userId and substring(tbp.updateTime, 0, 10)=:updateTime";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setParameter("userId", userId).setParameter("updateTime", updateTime);
		List<?> list = query.list();
		return query.list();
	}

	@Override
	public List<?> qryRealTimePosition(String day,String orgId,SessionVO sessionVO) {
		String sql ="select w.LATITUDE,w.LONGITUDE,w.DEVICE_PIN,w.UPDATE_TIME,O.ORG_CODE,O.ORG_NAME,U.USER_NAME ,u.USER_CODE " +
				" from T_BASIS_POSITION w,t_basis_user U,t_basis_org O where w.UPDATE_USER = U.USER_ID and U.ORG_ID=O.ORG_ID  and  w.ID in (select t.ID from T_BASIS_POSITION t,(select p.device_pin as DEVICE_PIN ,max(p.UPDATE_TIME) as UPDATE_TIME from T_BASIS_POSITION p group by p.DEVICE_PIN) n where n.DEVICE_PIN=t.DEVICE_PIN and n.UPDATE_TIME=t.UPDATE_TIME)";
		sql +=" and w.UPDATE_TIME like '%"+day+"%'";
		if(!"".equals(orgId)&&orgId!=null)
		{
			sql+="	and U.ORG_ID='"+ orgId +"' ";
		}else if(!"1".equals(sessionVO.getIsAdmin())){
			sql+="	and (U.ORG_ID in (select distinct ud.ORG_ID from T_BASIS_USER_DATA ud where ud.USER_ID = '"+ sessionVO.getUserId()+"') or U.ORG_ID = '"+sessionVO.getOrgId()+"' )";
		}
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		List<?> list1 = q.list();
				
		Object[] obj = new Object[list1.size()];
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		for(int i = 0; i<list1.size();i++)
		{
			Map<String,Object> map = new HashMap<String,Object>();
			obj = (Object[])list1.get(i);
			map.put("LATITUDE", obj[0]);  
			map.put("LONGITUDE", obj[1]);
			map.put("DEVICE_PIN", obj[2]);
			map.put("POSITION_TIME", obj[3]);
			map.put("ORG_CODE", obj[4]);
			map.put("ORG_NAME", obj[5]);
			map.put("USER_NAME", obj[6]);
			map.put("USER_CODE", obj[7]);
			mapList.add(map);
		}
		return mapList;
	}
}
