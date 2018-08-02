package com.mobile.application.dao;

import java.util.List;
import java.util.Set;

import com.mobile.application.entity.TBasisUser;
import com.mobile.application.vo.login.UserMenuVO;


public interface ILoginDao extends IBaseDAO<TBasisUser> {

	List<UserMenuVO> qryUserMenu(TBasisUser user);

	List<TBasisUser> qryUser(String userCode);

	List<String> qryUserAction(Set<String> menuIds);

}
