package com.mobile.application.service.user;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONArray;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.vo.CommonVO;

public interface IUserService {

	/**
	 * Description : 根据机构和查询条件查询用户列表
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param orgId 机构ID
	 * @param userCode 员工号
	 * @param userName 员工号
	 * @return 查询结果集
	 * @exception 
	 */
	CommonVO qryUserList(HttpSession session,int indexPage, int indexSize, String orgId , String userCode,String userName);

	/**
	 * Description : 根据用户查询角色列表
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId 用户ID
	 * @return 查询结果集
	 * @exception 
	 */
	CommonVO qryUserRoleList(int indexPage, int indexSize, String userId);

	/**
	 * Description : 根据用户id查询编辑数据
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param userId 用户ID
	 * @return 查询结果集
	 * @exception 
	 */
	Map<String, String> qryDitUserData(String userId);
	
	/**
	 * Description : 根据用户机构列出角色数据
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId 用户ID
	 * @param orgId 机构ID
	 * @return 角色列表
	 * @exception 
	 */
	JSONArray qryUserRole(String userId,String orgId);

	/**
	 * Description : 密码修改
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	CommonVO modifyPwd(String oldPwd, String newPwd, HttpSession session,HttpServletRequest request);

	List<?> teamOrg();

	/**
	 * Description : 根据删除用户数据
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userIds 用户ID
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	CommonVO delUserData(String userIds,HttpSession session,HttpServletRequest request);

	/**
	 * Description : 启用用户
	 * @author weiq
	 * @version 1.01
	 * @see N/A
	 * @param userIds 用户ID
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	CommonVO ableUserData(String userIds,HttpSession session,HttpServletRequest request);
	
	/**
	 * Description : 禁用用户
	 * @author weiq
	 * @version 1.01
	 * @see N/A
	 * @param userIds 用户ID
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	CommonVO disableUserData(String userIds,HttpSession session,HttpServletRequest request);
	
	/**
	 * Description : 保存用户数据
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param json 用户数据
	 * @param roleIds 角色
	 * @param orgId 用户ID
	 * @param orgCodes 员工号
	 * @exception 
	 */
	void saveUserData(MultipartFile file,HttpSession session,String json, String roleIds, String orgId,
			String orgCodes,HttpServletRequest request) throws BusinessException, IOException;

	/**
	 * @param file 
	 * Description : 保存用户编辑数据
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param json 用户数据
	 * @param roleIds 角色
	 * @param userId 用户ID
	 * @exception 
	 */
	void saveDitUser(MultipartFile file, HttpSession session,String json, String roleIds,String userId,HttpServletRequest request)throws BusinessException, IOException;

	/**
	 * Description : 重置密码
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId 用户ID
	 * @param pwd 新密码
	 * @return 操作执行结果
	 * @exception 
	 */
	CommonVO changePwd(HttpSession session,String userId, String pwd,HttpServletRequest request);
	
	/**
	 * @param userCode2 
	  * Description : 查询用户信息
	  * @author 罗杨
	  * @version 1.01
	  * @see N/A
	  * @param userCode 员工号
	  * @return 查询结果集
	  * @exception 
	  */
	List<?> queryUsers(String userCode, String userCode2);
	/**
	  * Description : 确认是否重复
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param session
	  * @param json 用户数据
	  * @param userId 用户ID
	  * @param request
	  * @return
	  * @throws BusinessException
	  * @throws IOException
	  * @exception BusinessException
	  */
	CommonVO checkUserCode(HttpSession session, String json,
			String userId, HttpServletRequest request);

	void exportUserModel(HttpServletResponse response);

	CommonVO importUser(MultipartFile file, HttpSession session,
			HttpServletRequest request) throws IOException;


}
