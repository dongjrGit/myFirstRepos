package com.mobile.application.dao;

import java.text.ParseException;
import java.util.List;

import com.mobile.application.entity.TBasisDevice;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisOrg;

import com.mobile.application.vo.session.SessionVO;

public interface IDeviceDao extends IBaseDAO<TBasisDevice>{

	/**
	 * Description : 根据查询条件和机构id查找出设备列表信息、
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param deptId ： 机构id
	 * @param pageIndex ： 分页
	 * @param pageSize : 分页大小
	 * @param userCode : 员工号
	 * @param pinCode ： 设备pin号
	 * @param simCode ： 设备sim号
	 * @param userName ： 员工姓名
	 * @param startTime ： 开始时间
	 * @param endTime ： 结束时间
	 * @return 查询结果集
	 * @throws ParseException
	 */
	List<?> findDevice(String deptId, int pageIndex, int pageSize,
			String userCode, String pinCode, String simCode, String userName,
			String startTime, String endTime) throws ParseException;

	/**
	 * Description : 返回设备列表记录数
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param deptId ： 机构id
	 * @param userCode : 员工号
	 * @param pinCode ： 设备pin号
	 * @param simCode ： 设备sim号
	 * @param userName ： 员工姓名
	 * @param startTime ： 开始时间
	 * @param endTime ： 结束时间
	 * @return 查询结果集
	 * @throws ParseException
	 */
	List<?> findDevice(String deptId, String userCode, String pinCode,
			String simCode, String userName, String startTime, String endTime) throws ParseException;

	/**
	 * Description : 根据登录人及其所在机构查询其数据权限机构
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param user ： 登录人session
	 * @param orgId ： 机构id
	 * @return 查询结果集
	 * @exception
	 */
	List<?> findOrgTree(SessionVO user, String orgId);

	/**
	 * Description : 根据查询条件和机构id查询出人员信息
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgId ： 机构id
	 * @param pageIndex ： 分页
	 * @param pageSize ： 分页大小
	 * @param key
	 * @return 查询结果集
	 * @exception
	 */
	List<?> findUser(String orgId, int pageIndex, int pageSize, String key);

	/**
	 * Description : 人员信息记录数
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgId ： 机构id
	 * @param key
	 * @return 查询结果集
	 * @exception
	 */
	List<?> findUser(String orgId, String key);

	/**
	 * Description : 根据员工号获取一个用户实体类
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userCode ： 员工号
	 * @param password ： 密码
	 * @return 用户实体类
	 * @exception
	 */
	TBasisUser getUserById(String userCode,String password);

	/**
	 * Description ： 根据用户id获取用户信息
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId ： 员工id
	 * @return 查询结果集
	 * @exception
	 */
	List<?> qryDeviceUser(String userId);

	TBasisDevice getDeviceById(String deviceId);

	void save1(TBasisDevice pad);

	TBasisUser getUserByCode(String userCode);
	
	/**
	 * Description : 通过当前机构id获取所有父级机构
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param orgId ： 机构id
	 * @return 查询结果集
	 * @exception
	 */
	List<?> getParentOrg(String orgId);
	
	/**
	 * Description ： 根据设备pin号获取历史记录
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param pinId : 设备pin号
	 * @param indexPage ： 分页
	 * @param indexSize ： 分页大小
	 * @return 查询结果集
	 * @exception
	 */
	List<?> findRecord(String pinId, int indexPage, int indexSize);

	/**
	 * Description ： 历史记录数
     * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param pinId : 设备pin号
	 * @return 查询结果集
	 * @exception
	 */
	List<?> findRecord(String pinId);

	/**
	 * Description : 获取登录用户所在机构信息
     * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId ： 用户id
	 * @return 查询结果集
	 * @exception
	 */
	List<?> findOrgByUser(String userId);



}
