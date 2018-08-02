package com.mobile.application.controller.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.service.role.IRoleService;
import com.mobile.application.service.user.IUserService;
import com.mobile.application.vo.CommonVO;
/** 
 * Copy Right Information   :Techown, 2015
 * Project                  : pushwin
 * Author					: 姜瑞
 * JDK version used         : jdk1.6
 * Comments                 : 用户管理 
 * Version                  : 1.01
 * Modification history     : 2015.9
 * Sr Date   Modified By   Why & What is modified
 * 1. 2015.9.27 姜瑞     新建 
 **/
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private IRoleService roleService;
	

	/**
	 * Description : 用户管理配置，初始化页面
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param session 
	 * @return  用户管理页面
	 * @exception 
	 */
	@RequestMapping("/initQry")
	public ModelAndView init(HttpSession session, HttpServletRequest request) {
		Map<String, Object> auth = new HashMap<String, Object>();
		return new ModelAndView("user/qryUser",auth);
	}
	
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
	@RequestMapping("/userList")
	@ResponseBody
	public CommonVO qryUserList(HttpSession session, HttpServletRequest request,String pageIndex, String pageSize, String orgId,String userCode,String userName) throws IOException{
		return userService.qryUserList(session,Integer.parseInt(pageIndex), Integer.parseInt(pageSize), orgId, userCode, userName);
	}

	/**
	 * Description : 根据用户查询角色列表
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId 用户ID
	 * @return 查询结果集
	 * @exception 
	 */
	@RequestMapping("/userRoleList")
	@ResponseBody
	public CommonVO qryUserRoleList(String pageIndex, String pageSize, String userId) throws IOException{
		return userService.qryUserRoleList(Integer.parseInt(pageIndex), Integer.parseInt(pageSize), userId);
	}

	/**
	 * Description : 根据用户id查询编辑数据
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId 用户ID
	 * @return 查询结果集
	 * @exception 
	 */
	@RequestMapping("/ditUserData")
	@ResponseBody
	public Map<String, String> qryDitUserData(String userId) throws IOException{
		return  userService.qryDitUserData(userId);
	}
	
	/**
	 * Description : 密码修改，初始化页面
	 * @author 姜瑞
	 * @version 1.01
	 * @see N/A
	 * @param N/A
	 * @return 密码修改界面
	 * @exception 
	 */
	@RequestMapping("/modifyPwdInit")
	public String modifyPwdInit(){
		return "user/modifyPwd";
	}
	
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
	@RequestMapping("/modifyPwd")
	@ResponseBody
	public CommonVO modifyPwd(String oldPwd, String newPwd, HttpSession session,HttpServletRequest request){
		return userService.modifyPwd(oldPwd, newPwd, session,request);
	}
	
	@RequestMapping("/teamOrg")
	@ResponseBody
	public List<?> teamOrg(){
		return userService.teamOrg();
	}

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
	@RequestMapping("/addUserRole")
	@ResponseBody
	public JSONArray qryUserRole(String userId,String orgId,HttpServletRequest request) throws IOException{
		return  userService.qryUserRole(userId,orgId);
	}

	/**
	 * Description : 根据删除用户数据
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userIds 用户ID
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	@RequestMapping("/delUserData")
	@ResponseBody
	public CommonVO delUserData( String userIds,HttpSession session,HttpServletRequest request) throws IOException{
		return  userService.delUserData(userIds,session,request);
	}
	
	/**
	 * Description : 根据启用用户
	 * @author weiq
	 * @version 1.01
	 * @see N/A
	 * @param userIds 用户ID
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	@RequestMapping("/ableUserData")
	@ResponseBody
	public CommonVO ableUserData( String userIds,HttpSession session,HttpServletRequest request) throws IOException{
		return  userService.ableUserData(userIds,session,request);
	}
	
	/**
	 * Description : 根据禁用用户
	 * @author weiq
	 * @version 1.01
	 * @see N/A
	 * @param userIds 用户ID
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	@RequestMapping("/disableUserData")
	@ResponseBody
	public CommonVO disableUserData( String userIds,HttpSession session,HttpServletRequest request) throws IOException{
		return  userService.disableUserData(userIds,session,request);
	}
	
	/**
	 * Description : 保存用户数据
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param json 用户数据
	 * @param roleIds 角色
	 * @param orgId 机构ID
	 * @param orgCodes 机构编码
	 * @return 操作执行结果
	 * @exception 
	 */
	@RequestMapping("/saveUserData")
	public ModelAndView saveUserData(@RequestParam(value = "myfilelogin", required = false)MultipartFile file, HttpSession session,String json,String roleIds,String orgId,String orgCodes,HttpServletRequest request) throws BusinessException, IOException{
		userService.saveUserData(file,session,json,roleIds,orgId,orgCodes,request);
		Map<String, Object> auth = new HashMap<String, Object>();
		return new ModelAndView("user/qryUser",auth);
	}
	/**
	 * Description : 保存用户编辑数据
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param json 用户数据
	 * @param roleIds 角色
	 * @param userId 用户ID
	 * @return 操作执行结果
	 * @exception 
	 */
	@RequestMapping("/saveDitUser")
	public ModelAndView saveDitUser(@RequestParam(value = "myfilelogin", required = false)MultipartFile file,HttpSession session,String jsonData,String roleIdsData,String userIdData,HttpServletRequest request) throws BusinessException, IOException{
		  userService.saveDitUser(file,session,jsonData,roleIdsData,userIdData,request);
		  Map<String, Object> auth = new HashMap<String, Object>();
			return new ModelAndView("user/qryUser",auth);
	}

	/**
	 * Description : 重置密码
	 * @author 徐雪萍
	 * @version 1.01
	 * @see N/A
	 * @param userId 用户ID
	 * @param pwd 密码
	 * @return 操作执行结果
	 * @exception 
	 */
	@RequestMapping("/changePwd")
	@ResponseBody
	public CommonVO changePwd( HttpSession session,String userId,String pwd,HttpServletRequest request) throws IOException{
		return  userService.changePwd(session,userId,pwd,request);
	}
	
	
	 /**
	  * Description : 查询用户信息
	  * @author 罗杨
	  * @version 1.01
	  * @see N/A
	  * @param userCode 员工号
	  * @return 查询结果集
	  * @exception 
	  */
	@RequestMapping("/queryUsers")
	@ResponseBody
	public CommonVO queryUsers(String roleId,String userCode){
		List<?> users=userService.queryUsers(roleId,userCode);
		return new CommonVO(true, users, null);
		
	}

	 /**
	  * Description : 确认是否重复
	  * @author 徐雪萍
	  * @version 1.01
	  * @see N/A
	  * @param session
	  * @param json 用户数据
	  * @param userId 用户id
	  * @param request
	  * @return
	  * @throws BusinessException
	  * @throws IOException
	  * @exception BusinessException
	  */
	@RequestMapping("/checkUserCode")
	@ResponseBody
	public CommonVO checkUserCode( HttpSession session,String json,String userId,HttpServletRequest request) throws BusinessException, IOException{
		return userService.checkUserCode(session,json,userId,request);
		}
	
	/**
	  * Description : 导入用户excel模板下载
	  * @author 罗杨
	  * @version 1.01
	  * @see N/A
	  * @param response
	  * @exception
	  */
	@RequestMapping("/exportUserModel")
	public void exportUserModel(HttpServletResponse response) {
		this.userService.exportUserModel(response);
	}
	
	/**
	 * Description : 导入用户
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param file
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws BusinessException
	 */
	@RequestMapping("/importUser")
	@ResponseBody
	public CommonVO importUser(@RequestParam(value = "excelfile", required = false)MultipartFile file, HttpSession session,HttpServletRequest request) throws IOException{
		return this.userService.importUser(file,session,request);
		//return "";
	}
}
