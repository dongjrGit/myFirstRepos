package com.mobile.application.controller.org;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.service.org.IOrgService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;


/** 
 * Copy Right Information   :Techown, 2015
 * Project                  : pushwin
 * Author					: 罗杨
 * JDK version used         : jdk1.6
 * Comments                 : 机构管理，通用机构树
 * Version                  : 1.01
 * Modification history     : 2015.9.27
 * Sr Date   Modified By   Why & What is modified
 * 1. 2015.9.27 罗杨     新建 
 **/
@Controller
@RequestMapping("/org")
public class OrgController {

	@Autowired
	private IOrgService orgService;

	/**
	 * Description : 机构列表初始化页面
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param request
	 * @return 机构管理页面
	 * @exception BusinessException
	 */
	@RequestMapping("/init")
	public String init(HttpSession session, HttpServletRequest request) {
		return "org/org";
	}

	/**
	 * Description : 显示当前登录人数据权限机构  公共机构树
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 查询结果集
	 * @throws Exception 
	 * @exception BusinessException
	 */
	@RequestMapping("/orgTree")
	@ResponseBody
	public List<?> qryOrgTree(HttpSession session) throws Exception {
		// 获取当前登录人机构ID
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		List<?> list = orgService.qryOrgTree(sessionVO);
		return list;
	}

	/**
	 * Description : 通过机构id获取该机构的一条记录
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 查询结果集
	 * @exception BusinessException
	 */
	@RequestMapping("/qryOrgByID")
	@ResponseBody
	public List<?> qryOrgByID(String orgID) {
		return orgService.qryOrgByID(orgID);
	}


	/**
	 * Description : 根据机构和查询条件获取该机构下的人员信息
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param pageIndex : 分页
	 * @param pageSize : 每页大小
	 * @param deptId ：机构id
	 * @param key : 查询条件
	 * @return 查询结果集
	 * @exception BusinessException
	 */
	@RequestMapping("/showUserInfoAjax")
	@ResponseBody
	public Map<String, Object> showUserInfoAjax(HttpSession session, int pageIndex,
			int pageSize, String deptId, String key) {
		return orgService.showUserInfoAjax(session, pageIndex, pageSize,
				deptId, key);

	}

	/**
	 * Description : 新增机构保存
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param orgId ： 机构id 
	 * @param orgName ：机构名称
	 * @param orgCode ： 机构号
	 * @param orgDesc ： 机构描述
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	@RequestMapping("/saveOrgNode")
	@ResponseBody
	public CommonVO saveOrgNode(HttpSession session, String orgId,String orgName, String orgCode, String orgDesc) {
		return orgService.saveOrgNode(session, orgId, orgName, orgCode, orgDesc);
	}

	/**
	 * Description : 编辑机构保存
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param orgId ： 机构id 
	 * @param orgName ：机构名称
	 * @param orgCode ： 机构号
	 * @param orgDesc ： 机构描述
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	@RequestMapping("/updOrgNode")
	@ResponseBody
	public CommonVO updOrgNode(HttpSession session, String orgId, String pid,String orgName, String orgCode, String orgDesc) {
		return orgService.updOrgNode(session, orgId, pid, orgName, orgCode,orgDesc);
	}

	/**
	 * Description : 删除机构
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @param orgId ： 机构id 
	 * @return 操作执行结果
	 * @exception BusinessException
	 */
	@RequestMapping("/delOrgNode")
	@ResponseBody
	public CommonVO delOrgNode(HttpSession session,String orgId) {
		return orgService.delOrgNode(session,orgId);
	}

	/**
	 * Description : 导出机构树
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param response
	 * @return 机构树excel
	 * @exception BusinessException
	 */
	@RequestMapping("/exportOrgReportExcel")
	@ResponseBody
	public void exportOrgReportExcel(HttpSession session,HttpServletResponse response) {
		orgService.exportOrgReportExcel(session,response);
	}

	/**
	 * Description : 角色部分新增和编辑显示机构树
	 * @author 杨习超
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 查询结果集
	 * @exception
	 */
	@RequestMapping("/queryOrg")
	@ResponseBody
	public List<?> showOrgTreeAjax(HttpSession session) {
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		List<?> list = orgService.queryOrgList(sessionVO);
		return list;
	}
	
	@RequestMapping("/queryCheckOrg")
	@ResponseBody
	public List<?> queryCheckOrg(String roleId){
		return orgService.queryOrgByRoleId(roleId);
	}

//	@RequestMapping("/qryChildrenOrg")
//	@ResponseBody
//	public CommonVO qryChildrenOrg(String orgId){
//		return new CommonVO(true, orgService.qryOrgTree(orgId), "");
//	}
//	
//	@RequestMapping("/qryParentOrg")
//	@ResponseBody
//	public CommonVO qryParentOrg(String orgId){
//		return new CommonVO(true,orgService.qryOrgParentTree(orgId),"");
//	}
	@RequestMapping("/exportOrgModel")
	@ResponseBody
	public void exportOrgModel(HttpServletResponse response) {
		orgService.exportOrgModel(response);
	}
	@RequestMapping("/importOrg")
	@ResponseBody
	public CommonVO  importOrg(@RequestParam(value = "excelfile", required = false)MultipartFile file, HttpSession session,HttpServletRequest request) throws BusinessException, IOException{
		return orgService.importOrg(file,session,request);
	}
	
	@RequestMapping("/qryProductCheckOrg")
	@ResponseBody
	public List<?> qryProductCheckOrg(String productId){
		return orgService.qryOrgByProductId(productId);
	}
	
	@RequestMapping("/initUserOrg")
	public ModelAndView initUserOrg(String userId) {
		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("userId", userId);
		return new ModelAndView("user/user_org", userMap);
	}
	
	@RequestMapping("/qryUserDataOrg")
	@ResponseBody
	public List<?> qryUserDataOrg(HttpSession session, String userId){
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		return orgService.qryUserDataOrg(sessionVO, userId);
	}
	@RequestMapping("/saveUserDataOrg")
	@ResponseBody
	public CommonVO saveUserDataOrg(String orgIds, String userId,  HttpSession session){
		return orgService.saveUserDataOrg(orgIds, userId);
	}
}
