package com.mobile.application.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.entity.TBasisApk;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisOrgRole;
import com.mobile.application.entity.TBasisPage;
import com.mobile.application.entity.TBasisRole;
import com.mobile.application.entity.TBasisRoleApk;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisUserRole;
import com.mobile.application.service.org.IOrgService;
import com.mobile.application.service.role.IRoleService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

@Controller
@RequestMapping("/role")
public class RoleController {

	private Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IOrgService orgService;

	@RequestMapping("/query")
	@ResponseBody
	public CommonVO query(TBasisPage page, String roleName,String roleType,HttpSession session) {
		Map<String, Object> dataMap = roleService.queryRoleList(roleName,roleType, page,session);
		return new CommonVO(true, dataMap.get("roleList"), dataMap.get(
				"roleCount").toString());
	}

	 /**
	  * Description : 初始化角色管理页面
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @return 角色管理页面
	  * @exception BusinessException
	  */
	@RequestMapping("/init")
	public ModelAndView init() {
		Map<String, Object> auth = new HashMap<String, Object>();
		return new ModelAndView("role/role",auth);
	}

	 /**
	  * Description : 查询角色下现有用户
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param page 
	  * @param roleId 角色ID
	  * @param roleName 角色名称
	  * @return
	  * @exception BusinessException
	  */
	@RequestMapping("/queryRoleUser")
	@ResponseBody
	public CommonVO queryRoleUser(TBasisPage page, String roleId,
			String roleName) {
		Map<String, Object> dataMap = roleService.queryRoleUserList(roleId,
				roleName, page);
		return new CommonVO(true, dataMap.get("roleUserList"), dataMap.get(
				"roleUserCount").toString());
	}

	 /**
	  * Description : 删除角色下用户
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleuser 角色下的用户集合
	  * @return 操作结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/delRoleUser")
	@ResponseBody
	public CommonVO RoleUserDel(String roleuser) {
		JSONArray jsonMenu = JSONArray.fromObject(roleuser);
		int iSize = jsonMenu.size();
		JSONObject jsonObj = null;
		String userids = "";
		String roleids = "";
		for (int i = 0; i < iSize; i++) {
			jsonObj = jsonMenu.getJSONObject(i);
			String userid = jsonObj.getString("userid");
			userids += userid + ",";
			roleids = jsonObj.getString("roleid");
		}
		String num = String.valueOf(roleService.delRoleUser(userids, roleids));
		return new CommonVO(true, num);

	}

	 /**
	  * Description : 查询用户所有角色列表
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param userId 用户ID
	  * @return 查询角色列表结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/queryRoleByUserId")
	@ResponseBody
	public CommonVO queryRoleByUserId(String userId) {
		Map<String, Object> dataMap = roleService.queryRoleByUserId(userId);
		return new CommonVO(true, dataMap.get("roleList"), null);

	}

	 /**
	  * Description : 后台新建角色保存
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param jsonData 角色信息
	  * @return 操作结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/saveRole")
	@ResponseBody
	public CommonVO RoleSave(HttpSession session, String jsonData) {
		SessionVO user = (SessionVO)session.getAttribute("sessionVO");
		JSONObject object = JSONObject.fromObject(jsonData);
		String roleName = object.getString("roleName");
		String roleDesc = object.getString("roleDesc");
		String orgId = object.getString("id");
		String roleId = object.getString("roleid");
		String optType = object.getString("opt");
		String roleType = object.getString("roleType");
		String[] orgs = orgId.split(",");
		String num = "0";
		List<?> list = this.roleService.queryRoleByName(roleId,roleName,orgId);
		TBasisRole role = new TBasisRole();
		role.setRoleDesc(roleDesc);
		role.setRoleName(roleName);
		role.setRoleType(roleType);
		if(list.size() != 0){
			num = "1";
		}
		else if ("0".equals(optType)) {
			this.roleService.saveRole(role);
			for (int i = 0; i < orgs.length; i++) {
				TBasisOrgRole roleOrg = new TBasisOrgRole();
				TBasisOrg org = (TBasisOrg) orgService.queryOrg(orgs[i]);
				roleOrg.settBasisOrg(org);
				roleOrg.settBasisRole(role);
				this.roleService.saveRoleOrg(roleOrg);
				num = "2";
			}
		} else{
			TBasisRole roleObj = roleService.getRoleEnity(roleId);
			roleObj.setRoleDesc(roleDesc);
			roleObj.setRoleName(roleName);
			this.roleService.saveRole(roleObj);
			if (!"null".equals(orgId)) {
				this.roleService.delOrgByRoleId(roleId);
			}
			for (int i = 0; i < orgs.length; i++) {
				TBasisOrgRole roleOrg = new TBasisOrgRole();
				TBasisOrg org = (TBasisOrg) orgService.queryOrg(orgs[i]);
				roleOrg.settBasisOrg(org);
				roleOrg.settBasisRole(roleObj);
				this.roleService.saveRoleOrg(roleOrg);
			}

			num = "3";
		} 
		//删除对应用户
		List<?> u = this.roleService.qryRoleUser(roleId);
		JSONArray uu = JSONArray.fromObject(u);
		String userids="";
		for(int j=0;j<u.size();j++){
			String orgId1= (String) uu.getJSONObject(j).get("orgId");
			String userid = (String) uu.getJSONObject(j).get("userid");
			int t = 0;
			for (int i = 0; i < orgs.length; i++) {
				if(orgs[i].equals(orgId1)){
					t=1;
				}
			}
			if(t==0){
			userids+=userid+",";
			if(j==u.size()-1){
			userids+=userid;
			}
			}
		}
		roleService.delRoleUser(userids, roleId);
		return new CommonVO(true, num);
	}

	 /**
	  * Description : 后台角色删除
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @return 操作结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/delRoleById")
	@ResponseBody
	public CommonVO delRole(String roleId) {
		long count = this.roleService.queryRoleHasUser(roleId);
		if (count == 0) {
			this.roleService.delRoleRelInfo(roleId);
		}
		return new CommonVO(true, String.valueOf(count));
	}

	 /**
	  * Description : 通过角色ID查询角色信息
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @return 查询结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/queryRoleById")
	@ResponseBody
	public CommonVO queryRoleById(String roleId) {
		TBasisRole role = roleService.getRoleEnity(roleId);
		TBasisRole roleData = new TBasisRole();
		roleData.setRoleDesc(role.getRoleDesc());
		roleData.setRoleName(role.getRoleName());
		roleData.setRoleId(role.getRoleId());
		return new CommonVO(true, roleData, null);
	}

	 /**
	  * Description : 保存角色用户
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param data 用户数据集
	  * @param roleId 角色ID
	  * @return 操作结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/saveRoleUser")
	@ResponseBody
	public CommonVO saveRoleUser(String data, String roleId) {
		TBasisRole tr = this.roleService.getRoleEnity(roleId);
		roleService.delUserRoleByRoleId(roleId);
		JSONArray jsonMenu = JSONArray.fromObject(data);
		int iSize = jsonMenu.size();
		JSONObject jsonObj = null;
		for (int i = 0; i < iSize; i++) {
			jsonObj = jsonMenu.getJSONObject(i);
			String userId = jsonObj.getString("userid");
			TBasisUserRole tur = new TBasisUserRole();
			TBasisUser tu = this.roleService.findUserById(userId);
			tur.setTBasisRole(tr);
			tur.setTBasisUser(tu);
			this.roleService.saveUserRole(tur);
		}
		return new CommonVO(true, "1");
	}

	 /**
	  * Description : 查询角色已有用户
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @return
	  * @exception BusinessException
	  */
	@RequestMapping("/queryChkUserRole")
	@ResponseBody
	public CommonVO queryChkUserRole(String roleId) {
		List<?> userRole = roleService.findRolerById(roleId);
		return new CommonVO(true, userRole, null);

	}

	 /**
	  * Description : 列出角色菜单权限树
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @return 菜单权限信息列表
	  * @exception BusinessException
	  */
	@RequestMapping("/showMenuTree")
	@ResponseBody
	public List<?> showMenuTree(String roleId) {
		List<?> list = this.roleService.showMenu();
		List<?> listMenu = this.roleService.showMenuOrg(roleId);
		JSONArray jsonMenu = null;
		jsonMenu = JSONArray.fromObject(listMenu);
		JSONArray json = null;
		json = JSONArray.fromObject(list);
		JSONArray dataAry = new JSONArray();
		int iSize = json.size();
		int jSize = jsonMenu.size();
		JSONObject jsonObj = null;
		for (int i = 0; i < iSize; i++) {
			jsonObj = json.getJSONObject(i);
			for (int j = 0; j < jSize; j++) {
				JSONObject jsonObjMenu = jsonMenu.getJSONObject(j);
				if (jsonObjMenu.get("id").equals(jsonObj.get("id"))
						&& !jsonObj.get("pid").equals("1")) {
					jsonObj.put("checked", "true");
				}
			}
			dataAry.add(jsonObj);
		}
		return dataAry;
	}

	 /**
	  * Description :  保存菜单权限
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @param menuIds 选中菜单信息
	  * @return 操作结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/saveRoleMenu")
	@ResponseBody
	public CommonVO saveRoleMenu(String roleId, String menuIds) {
		this.roleService.addRoleMenu(roleId, menuIds);
		return new CommonVO();
	}

	 /**
	  * Description : 查询角色可关联apk
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A 
	  * @return 返回查询结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/queryApk")
	@ResponseBody
	public CommonVO queryApk() {
		List<?> apk = roleService.queryApk();
		return new CommonVO(true, apk, null);
	}

	 /**
	  * Description : 
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param data apk数据
	  * @param roleId  角色ID
	  * @return 操作结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/saveRoleApk")
	@ResponseBody
	public CommonVO saveRoleApk(String data, String roleId) {
		TBasisRole tr = this.roleService.getRoleEnity(roleId);
		roleService.deleteRoleApk(roleId);
		JSONArray jsonMenu = JSONArray.fromObject(data);
		int iSize = jsonMenu.size();
		JSONObject jsonObj = null;
		for (int i = 0; i < iSize; i++) {
			jsonObj = jsonMenu.getJSONObject(i);
			String dealId = jsonObj.getString("dealId");
			TBasisRoleApk roleApk = new TBasisRoleApk();
			TBasisApk apk = this.roleService.findApkById(dealId);
			roleApk.setTBasisRole(tr);
			roleApk.setTBasisApk(apk);
			this.roleService.saveRoleApk(roleApk);
		}
		return new CommonVO(true, "1");
	}

	 /**
	  * Description : 查询角色现有apk
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	  * @param roleId 角色ID
	  * @return 查询结果集
	  * @exception BusinessException
	  */
	@RequestMapping("/queryChkRoleApk")
	@ResponseBody
	public CommonVO queryChkRoleApk(String roleId) {
		List<?> roleApk = roleService.queryRoleApk(roleId);
		return new CommonVO(true, roleApk, null);

	}
	
	/**
	  * Description : 查询出当前登录用户机构
	  * @author 杨习超
	  * @version 1.01
	  * @see N/A
	 * @param session
	 * @return
	 */
	@RequestMapping("/querySessionOrg")
	@ResponseBody
	public CommonVO querySessionOrg(HttpSession session){
		SessionVO user = (SessionVO) session.getAttribute("sessionVO");
		String orgId = user.getOrgId();
		if(orgId == null){
			return new CommonVO(true,"");
		}
		else{
			return new CommonVO(true,orgId);
		}
		
	}
	

}
