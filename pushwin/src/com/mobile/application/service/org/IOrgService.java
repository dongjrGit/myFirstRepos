package com.mobile.application.service.org;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;



import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TOrgTreeNode;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

public interface IOrgService {

	/**
	 * Description : 显示当前登录人数据权限机构  公共机构树
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param sessionVO 当前登录用户session
	 * @return 机构查询结果集
	 * @throws Exception 
	 * @exception BusinessException
	 */
	List<?> qryOrgTree(SessionVO sessionVO) throws Exception;

	/**
	 * Description : 通过机构id获取该机构的一条记录
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param session
	 * @return 查询结果集
	 * @exception BusinessException
	 */
	List<?> qryOrgByID(String orgId);


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
	Map<String, Object> showUserInfoAjax(HttpSession session,int indexPage, int indexSize, String deptId,String key);

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
	CommonVO saveOrgNode(HttpSession session, String orgId, String orgName, String orgCode,
			String orgDesc);

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
	CommonVO updOrgNode(HttpSession session, String orgId, String pid,
			String orgName, String orgCode, String orgDesc);

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
	CommonVO delOrgNode(HttpSession session, String orgId);

	/**
	 * Description : 导出机构树
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param response
	 * @return 机构树excel
	 * @exception BusinessException
	 */
	void exportOrgReportExcel(HttpSession session, HttpServletResponse response);
		
	/**
	 * Description : 根据机构id获取机构实体类
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgId : 机构id
	 * @return 查询数据集
	 * @exception BusinessException
	 */
	public TBasisOrg queryOrg(String orgId);

	/**
	 * Description : 通过机构id查询出所有子机构
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgId ： 机构id
	 * @return 查询结果集
	 * @exception BusinessException
	 */
	List<TOrgTreeNode> qryChildrenOrg(String orgId);

	/**
	 * Description : 通过机构id查询出机构树
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgId ： 机构id
	 * @return 查询结果集
	 * @throws Exception 
	 * @exception BusinessException
	 */
	List<?> qryOrgTree(String orgId) throws Exception;

	/**
	 * Description : 通过机构id查询出父级机构id列表（包括本机构）
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgId ： 机构id
	 * @return 查询结果集
	 * @exception
	 */
	List<?> qryOrgParentTree(String orgId);

	/**
	 * Description : 通过机构id查询出子机构id列表（包括本机构）
	 * @author 罗杨
	 * @version 1.01
	 * @see N/A
	 * @param orgId ： 机构id
	 * @return 查询结果集
	 * @exception
	 */
	List<?> qryOrgChildrenTree(String orgId);

	List<?> queryOrgList(SessionVO sessionVO);

	void exportOrgModel(HttpServletResponse response);

	CommonVO importOrg(MultipartFile file, HttpSession session,
			HttpServletRequest request)throws BusinessException, IOException ;

	List<?> queryOrgByRoleId(String roleId);

	

	List<?> qryOrgByProductId(String productId);

	List<?> qryUserDataOrg(SessionVO sessionVO, String userId);

	CommonVO saveUserDataOrg(String orgIds, String userId);




}
