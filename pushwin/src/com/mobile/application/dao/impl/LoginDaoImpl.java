package com.mobile.application.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mobile.application.dao.ILoginDao;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.vo.login.LoginUserVO;
import com.mobile.application.vo.login.UserMenuVO;


@Repository
public class LoginDaoImpl extends BaseDAOImpl<TBasisUser> implements ILoginDao {
	
	@Override
	public List<TBasisUser> qryUser(String userCode){
		Query query = this.getCurrentSession().createQuery("from TBasisUser where userCode=:userCode and isDisable='false'");
		query.setParameter("userCode", userCode);
		List<TBasisUser> tBasisUsers = query.list();
		return tBasisUsers;
	}
	
	@Override
	public List<UserMenuVO> qryUserMenu(TBasisUser user){
		String hql = "select distinct new com.mobile.application.vo.login.UserMenuVO(u.menuId as menuId,u.menuName as menuName, u.menuPid as menuPid, u.menuIcon as menuIcon, u.menuUrl as menuUrl, u.menuSort as menuSort, u.iconPosition as iconPosition) from TBasisMenu u ";
				if(user.getIsAdmin()==null){
					hql +=	",TBasisUserRole ur,TBasisRoleMenu rm where ur.TBasisUser.userId='"+user.getUserId()+"' and u.menuId = rm.TBasisMenu.menuId and rm.TBasisRole = ur.TBasisRole ) ";
				}
		Query query = this.getCurrentSession().createQuery(hql);
		List<UserMenuVO> menu = query.list();
		return  menu;
	}
	
	@Override
	public List<String> qryUserAction(Set<String> menuIds) {
		String hql = "select tma.TBasisSystemAction.actionUrl from TBasisMenuAction tma where tma.TBasisMenu.menuId in (:menuIds)";
		return this.getCurrentSession().createQuery(hql).setParameterList("menuIds", menuIds).list();
	}
}
