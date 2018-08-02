package com.mobile.application.service.user.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.tools.MD5Util;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.IDeviceDao;
import com.mobile.application.dao.IUserDao;
import com.mobile.application.dao.impl.BaseDAOImpl;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisRole;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisUserData;
import com.mobile.application.entity.TBasisUserOrg;
import com.mobile.application.entity.TBasisUserRole;
import com.mobile.application.service.org.IOrgService;
import com.mobile.application.service.user.IUserService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;


@Service
public class UserServiceImpl extends BaseDAOImpl<TBasisUser> implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IDeviceDao deviceDao;
	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#qryUserList(int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public CommonVO qryUserList(HttpSession session,int indexPage, int indexSize, String orgId, String userCode,String userName){
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		String userId =sessionVO.getUserId(); 
		TBasisUser tBasisUser = userDao.get(TBasisUser.class,userId);
		List<?> list  = userDao.qryUser(tBasisUser,indexPage,indexSize,orgId, userCode, userName);
		//获取总页数
		String userCount = userDao.qryUserCount( tBasisUser,orgId, userCode, userName);
		return new CommonVO(true, list, userCount);
	}
	
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#qryUserRoleList(int, int, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public CommonVO qryUserRoleList(int indexPage, int indexSize, String userId){
		List<?> list  = userDao.qryUserRole(indexPage,indexSize,userId);
		String userRoleCount = userDao.userRoleCount(userId);
		return new CommonVO(true, list, userRoleCount);
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#qryDitUserData(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, String> qryDitUserData(String userId){
		List<?> list =  userDao.qryditUserData(userId);
		List<Map<String, String>> roleMaps = (List<Map<String, String>>) userDao.getRoleUser(userId);
		Map<String, String> userMap = (Map<String, String>) list.get(0);
		JSONArray roleNameArray = new JSONArray();
		JSONArray roleIdArray = new JSONArray();
		for(int i=0; i < roleMaps.size(); i++){
			roleNameArray.add(roleMaps.get(i).get("roleName"));
			roleIdArray.add(roleMaps.get(i).get("roleId"));
		}
		userMap.put("roleNames", roleNameArray.join(",", true));  
		userMap.put("roleIds", roleIdArray.join(",", true));
	
		if("true".equals(userMap.get("isClose"))){
			userMap.put("isClose", "是");
		}else{
			userMap.put("isClose", "否");
		}
	
		return  userMap;
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#modifyPwd(java.lang.String, java.lang.String, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional
	public CommonVO modifyPwd(String oldPwd, String newPwd, HttpSession session,HttpServletRequest request) {
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		TBasisUser tBasisUser = userDao.get(TBasisUser.class,sessionVO.getUserId());
		if (tBasisUser.getUserPwd().equals(oldPwd)) {

			if(!oldPwd.equals(newPwd)){
				tBasisUser.setUserPwd(newPwd);
				SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置日期格式
				String time = dfs.format(new Date());//为获取当前系统时间
				tBasisUser.setUpPwdTime(time);
				tBasisUser.setNoLogin("0");
				userDao.updateUser(tBasisUser);
				
				session.removeAttribute("sessionVO");
				return new CommonVO(true, "修改成功");
			}else{
				return new CommonVO(false,"新密码与原密码不能一致");
			}

		} else {
			return new CommonVO(false, "原始密码不正确");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#teamOrg()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<?> teamOrg(){
		List<?> list = this.userDao.ComTypeList();
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#qryUserRole(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public JSONArray qryUserRole(String userId,String orgId) {
		
		List<?> listUser = userDao.getRoleUser(userId);
		List<?> list = userDao.getRole(orgId);
		JSONArray jsonU = JSONArray.fromObject(listUser);
		JSONArray json = JSONArray.fromObject(list);
		JSONObject jsonObjMax = null;
		JSONObject jsonObjCheck = null;
		for(int i =0;i<json.size();i++)
		{
			jsonObjMax = json.getJSONObject(i);
			for(int j =0;j<jsonU.size();j++)
			{
				jsonObjCheck = jsonU.getJSONObject(j);
				if(jsonObjCheck.getString("roleId").equals(jsonObjMax.getString("roleId")))
				{
					jsonObjMax.put("check","true");
					break;
				}else
				{
					jsonObjMax.put("check","false");
				}
			}
		}
		
			return  json;
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#delUserData(java.lang.String, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional
	public CommonVO delUserData(String userIds,HttpSession session,HttpServletRequest request) {
		int m = 0;
		try
		{
			//获取登录者ID
			SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
			String[] user = userIds.split(",");
		    String userCord = sessionVO.getUserId();
		    
			for(int i=0;i<user.length;i++)
			{
				List<?> list = this.deviceDao.qryDeviceUser(user[i]);
				if(user[i].equals(userCord)){
					m = 1;
				}else if(list.size()>0){
					m = 2;
				}else{
				this.userDao.removeUserData(user[i]);
				TBasisUser u = this.userDao.get(TBasisUser.class,user[i]);
				u.setIsDisable("true");
				this.userDao.save(u);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return new CommonVO(false, "系统异常");
			
		}
		if(m == 1){
			return new CommonVO(false, "不能删除本操作用户");
		}
		if(m==2){
			return new CommonVO(false, "用户存在未归还设备，不能删除该用户");
		}
		
		 return new CommonVO(true, "删除成功");
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#delUserData(java.lang.String, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional
	public CommonVO ableUserData(String userIds,HttpSession session,HttpServletRequest request) {
		int m = 0;
		try
		{
			//获取登录者ID
			SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
			String[] user = userIds.split(",");
			String userCord = sessionVO.getUserId();
			
			for(int i=0;i<user.length;i++)
			{
				if(user[i].equals(userCord)){
					m = 1;
				}else{
					TBasisUser u = this.userDao.get(TBasisUser.class,user[i]);
					u.setIsClose("false");
					this.userDao.save(u);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return new CommonVO(false, "系统异常");
			
		}
		if(m == 1){
			return new CommonVO(false, "不能启用本操作用户");
		}
		
		return new CommonVO(true, "启用成功");
	}
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#delUserData(java.lang.String, javax.servlet.http.HttpSession, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional
	public CommonVO disableUserData(String userIds,HttpSession session,HttpServletRequest request) {
		int m = 0;
		try
		{
			//获取登录者ID
			SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
			String[] user = userIds.split(",");
			String userCord = sessionVO.getUserId();
			
			for(int i=0;i<user.length;i++)
			{
				if(user[i].equals(userCord)){
					m = 1;
				}else{
					TBasisUser u = this.userDao.get(TBasisUser.class,user[i]);
					u.setIsClose("true");
					this.userDao.save(u);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return new CommonVO(false, "系统异常");
			
		}
		if(m == 1){
			return new CommonVO(false, "不能禁用本操作用户");
		}
		
		return new CommonVO(true, "禁用成功");
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#saveUserData(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional
	public void saveUserData(MultipartFile file,HttpSession session,String json, String roleIds, String orgId,
			String orgCodes,HttpServletRequest request)throws BusinessException, IOException {
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
        String currentTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		String IconPath = (String) SpringProperty.props.get("UserIconPath");
		String IconFullPath = (String) SpringProperty.props.get("RootPath") + IconPath;
		String IconName = "";
		String picSuffix = "";
		
		

		    String initpwd = SpringProperty.props.getProperty("initpwd");
			JSONObject objJson = JSONObject.fromObject(json);
			String userCode = objJson.getString("userCode");//员工号orgCodes
			String userName = objJson.getString("userName");//员工姓名
			String userCard = objJson.getString("userCard");//手机号码
			String userEmail = objJson.getString("userEmail");//邮编
		
				TBasisUser user = new TBasisUser();
				TBasisOrg org = new TBasisOrg();
				user.setUserCode(userCode);
				user.setUserPhone(userCard);
				user.setUserName(userName);
				user.setUserType("1");//管理员类型 0 普通 1 管理员
				user.setIsDisable("false");
				user.setIsClose("false");
//				user.setNoLogin("0");
				user.setUserEmail(userEmail);
				String password = MD5Util.md5(initpwd);
				user.setUserPwd(password);
				org.setOrgId(orgId);
				user.setTBasisOrg(org);
				user.setUpdateUser(sessionVO.getUserId());
				user.setUpdateTime(currentTime);
				
///////////////////////////////	
				
				
				//文件存放路径不存在，则创建之
				File iconDir = new File(IconFullPath);
				if(!iconDir.exists()){
					iconDir.mkdirs();
				}
				//如果有上传图片，保存图片到本地
				if(!file.isEmpty()){
					picSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					IconName = user.getUserCode() + picSuffix;
					
					FileOutputStream fos = null;
					try {
						fos = new FileOutputStream(IconFullPath + "/" + IconName);
						fos.write(file.getBytes());
						fos.flush();
						fos.close();
					} catch (Exception e) {
						e.printStackTrace();
//						throw new BusinessException("500", "文件写入失败");
					} finally {
						fos.close();
					}
					
				user.setIconTime(currentTime);
				user.setIconLength(String.valueOf(file.getSize()));
				user.setUserIcon(IconPath + "/" + IconName);
				user.setIconName(IconName);
				}
///////////////////////////////
				
				
				
				this.userDao.save(user);
				
				//添加默认权限
				JSONArray jsonArray = JSONArray.fromObject(orgCodes);
				String  orgCode2 = "";
				for(int j = 0; j <= jsonArray.size(); j++ ){	
					if(j != jsonArray.size()){
		        	JSONObject object = JSONObject.fromObject(jsonArray.get(j));
		        	  orgCode2 = (String) object.get("id");
		        	}else{
		             orgCode2 =orgId;
		        	}	
				TBasisUserOrg uOrg = new TBasisUserOrg();
				TBasisOrg orgCode1 = new TBasisOrg();
				orgCode1.setOrgId(orgCode2);
				uOrg.setTBasisOrg(orgCode1);
				uOrg.setTBasisUser(user);
				this.userDao.save(TBasisUserOrg.class,uOrg);
				}
				
				String[] roles = roleIds.split(",");
				
				for(int i=0;i<roles.length;i++)
				{
					TBasisUserRole uRole = new TBasisUserRole();
					TBasisRole role = new TBasisRole();
					role.setRoleId(roles[i]);
					uRole.setTBasisUser(user);
					uRole.setTBasisRole(role);
					this.userDao.save(TBasisUserRole.class,uRole);
				}	
				 
			
		
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#saveDitUser(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional
	public void saveDitUser(MultipartFile file,HttpSession session,String json, String roleIds,String userId,HttpServletRequest request)throws  IOException {
		SessionVO sessionVO = (SessionVO) session.getAttribute("sessionVO");
		TBasisUser tbasisUser = this.userDao.get(TBasisUser.class, sessionVO.getUserId());
		Date date = new Date();
/////////////////////////		
		String currentTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");	
		String IconPath = (String) SpringProperty.props.get("UserIconPath");
		String IconFullPath = (String) SpringProperty.props.get("RootPath") + IconPath;
		String IconName = "";
		String picSuffix = "";
///////////////////////////		
		JSONObject objJson = JSONObject.fromObject(json);
		String userCode = objJson.getString("userCodeData");//员工号
		String userName = objJson.getString("userNameData");//员工姓名
		String userCard = objJson.getString("userCardData");//手机号码
		String userEmail = objJson.getString("userEmailData");//邮编
		
			TBasisUser user = this.userDao.get(TBasisUser.class,userId);
			user.setUserCode(userCode);
			user.setUserPhone(userCard);
			user.setUserName(userName);
			user.setUserEmail(userEmail);
			user.setUpdateUser(tbasisUser.getUserCode());
			user.setUpdateTime(DateUtil.format(date,"yyyy-MM-dd"));
			
//////////////////////////			
			
			//文件存放路径不存在，则创建之
			File iconDir = new File(IconFullPath);
			if(!iconDir.exists()){
				iconDir.mkdirs();
			}
			//如果有上传图片，保存图片到本地
			if(!file.isEmpty()){
				picSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				IconName = user.getUserCode() + picSuffix;
				
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(IconFullPath + "/" + IconName);
					fos.write(file.getBytes());
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					fos.close();
				}
			}			
			if(!file.isEmpty()){
					user.setUserIcon(IconPath + "/" + IconName);
					user.setIconLength(String.valueOf(file.getSize()));
					user.setIconTime(currentTime);
					user.setIconName(IconName);
					
			}
			
				
///////////////////////////////////////		
			
			
			this.userDao.save(user);
			
				
			//删除原有角色
				String[] roles = roleIds.split(",");
				this.userDao.removeUserRole(user.getUserId());
				for(int i=0;i<roles.length;i++)
				{
					TBasisUserRole uRole = new TBasisUserRole();
					TBasisRole role = new TBasisRole();
					role.setRoleId(roles[i]);
					uRole.setTBasisUser(user);
					uRole.setTBasisRole(role);
					this.userDao.save(TBasisUserRole.class,uRole);
				}	
			
		
	}

	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#changePwd(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@Transactional
	public CommonVO changePwd(HttpSession session,String userId, String pwd,HttpServletRequest request) {
		try{
			String initpwd = SpringProperty.props.getProperty("initpwd");
			String[] userIds = userId.split(",");
			for(int i=0;i<userIds.length;i++){
				TBasisUser user = this.userDao.get(TBasisUser.class,userIds[i]);
				user.setUpPwdTime(null);
				user.setUserPwd(MD5Util.md5(initpwd));
				user.setNoLogin(null);
				this.userDao.save(user);
			}
		return new CommonVO(true, "重置成功");
		}catch(Exception e){
			e.printStackTrace();
			return new CommonVO(false, "系统异常");
		} 
	}
	
	
	/* (non-Javadoc)
	 * @see com.mobile.application.service.user.IUserService#queryUsers(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<?> queryUsers(String roleId,String userCode){
		return userDao.getUsers(userCode,roleId);
	}


	@Override
	@Transactional
	public CommonVO checkUserCode(HttpSession session, String json,String userId, 
			HttpServletRequest request) {
		if(userId!=null){
		JSONObject objJson = JSONObject.fromObject(json);
		String userCode = objJson.getString("userCodeData");//员工号
		//判断员工是否存在
		List<?> list = this.userDao.qryUserByCode(userCode);
		JSONArray jsonL = JSONArray.fromObject(list);
		if((list.size() == 1 && jsonL.getJSONObject(0).getString("userId").equals(userId)||list.size() == 0) )
		{
	
			return new CommonVO(true, "保存成功");	
		}else{
			return new CommonVO(false, "员工号重复");
		}
		}else{
			JSONObject objJson = JSONObject.fromObject(json);
			String userCode = objJson.getString("userCode");//员工号
			List<?> list = this.userDao.qryUserByCode(userCode);
			if((list.size() == 0) )
			{
				return new CommonVO(true, " 保存成功");	
			}else{
				return new CommonVO(false, "员工号重复");
			}
		}
		
	}


	@Override
	public void exportUserModel(HttpServletResponse response) {
		WritableWorkbook wwb;
	      try {    
	    	  response.setHeader("Content-disposition", "attachment; filename=Excel.xls");// 设定输出文件头   
	    	  response.setContentType("application/vnd.ms-excel");// 定义输出类型 
	    	  OutputStream os = response.getOutputStream();// 取得输出流   
	    	  wwb = Workbook.createWorkbook(os);
	    	  WritableSheet ws = wwb.createSheet("用户信息表",10);// 创建一个工作表
	    	  //设置单元格的文字格式
	    	  WritableFont wf = new WritableFont(WritableFont.createFont("宋体"),11,WritableFont.NO_BOLD,false);
	    	  WritableFont wf_top = new WritableFont(WritableFont.createFont("宋体"),11, WritableFont.NO_BOLD, false);
	    	  WritableCellFormat wcf = new WritableCellFormat(wf);
	    	  WritableCellFormat wcf_top = new WritableCellFormat(wf_top);
	    	  wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
	    	  wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
	    	  wcf.setAlignment(Alignment.LEFT); 
	    	  wcf_top.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
	    	  wcf_top.setVerticalAlignment(VerticalAlignment.CENTRE); 
	    	  wcf_top.setAlignment(Alignment.CENTRE); 
	    	  wcf_top.setWrap(true);  
	    	  ws.setColumnView(0,25);
			  ws.setColumnView(1,25);
			  ws.setColumnView(2,25);
			  ws.setColumnView(3,25);
			  ws.setColumnView(4,25);
			  ws.setColumnView(5,25);
			  ws.setColumnView(6,25);
	    	  int vv=0;
	    	  ws.addCell(new Label(vv,0,"员工号",wcf));
	    	  ws.addCell(new Label(vv,1,"123",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"员工姓名",wcf));
	    	  ws.addCell(new Label(vv,1,"新增用户",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"邮箱",wcf_top));
	    	  ws.addCell(new Label(vv,1,"jerryqqq@163.com",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"手机",wcf_top));
	    	  ws.addCell(new Label(vv,1,"15201001101",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"机构编号",wcf_top));
	    	  ws.addCell(new Label(vv,1,"rs",wcf));
	    	  vv++;
	    	  wwb.write();
	    	  wwb.close();
	      }catch(Exception e){
	    	  e.printStackTrace();
	      } 
		
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public CommonVO importUser(MultipartFile file, HttpSession session, HttpServletRequest request) throws IOException{
		SessionVO sessionVO= (SessionVO)session.getAttribute("sessionVO");
		Date date=new Date();
		Workbook readwb = null; 
		CommonVO common = new CommonVO();
		Integer errorNum = 0;
		Integer total = 0;
		Integer doneNum = 0;
		try 
		{ 
			InputStream instream=file.getInputStream();	
//			String rootPath =ServletActionContext.getServletContext().getRealPath("/"); 
////			 构建Workbook对象, 只读Workbook对象 
////			 直接从本地文件创建Workbook 
//			InputStream instream = new FileInputStream(rootPath + "/examples/AppColumns.xls"); 

			readwb = Workbook.getWorkbook(instream); 

			// Sheet的下标是从0开始 
			// 获取第一张Sheet表 
			Sheet readsheet = readwb.getSheet(0); 

			// 获取Sheet表中所包含的总列数 
//			int rsColumns = readsheet.getColumns(); 

			// 获取Sheet表中所包含的总行数 
			int rsRows = readsheet.getRows(); 
			total = rsRows-1;
			// 获取指定单元格的对象引用 
			JSONArray dataArray = new JSONArray(); 
			for (int j = 1; j < rsRows; j++) 
			{ 
				JSONObject dataJson = new JSONObject(); 
				Cell cell_0 = readsheet.getCell(0, j); 
				dataJson.put("userCode", cell_0.getContents().trim()); 
				
				Cell cell_1 = readsheet.getCell(1, j); 
				dataJson.put("userName", cell_1.getContents().trim()); 

				Cell cell_2 = readsheet.getCell(2, j); 
				dataJson.put("userEmail", cell_2.getContents().trim()); 

				Cell cell_3 = readsheet.getCell(3, j); 
				dataJson.put("userPhone", cell_3.getContents().trim()); 
				
				Cell cell_4 = readsheet.getCell(4, j); 
				dataJson.put("orgCode", cell_4.getContents().trim());
				
				dataArray.add(dataJson); 
			} 
			for(int i=0; i<dataArray.size();i++){
				JSONObject data = dataArray.getJSONObject(i);
				String userCode = data.getString("userCode");
				String userName = data.getString("userName");
				String email = data.getString("userEmail");
				String phone = data.getString("userPhone");
				String orgCode = data.getString("orgCode");
				if("".equals(userCode) || userCode.length() != 7){
					errorNum++;
					continue;
				}
				if(!"".equals(userCode) && !isUserCode(userCode)){
					errorNum++;
					continue;
				}
				if("".equals(userName)){
					errorNum++;
					continue;
				}
				if("".equals(email)){
					errorNum++;
					continue;
				}
				if(!"".equals(email) && !isEmail(email)){
					errorNum++;
					continue;
				}
				if("".equals(phone)){
					errorNum++;
					continue;
				}
				if(!"".equals(phone) && !isMobile(phone)){
					errorNum++;
					continue;
				}
				if("".equals(orgCode)){
					errorNum++;
					continue;
				}
				List<TBasisUser> list =(List<TBasisUser>) this.userDao.get(TBasisUser.class, "userCode", userCode);
				if(list.isEmpty()){
					list.add(new TBasisUser());
				}
				TBasisUser user = list.get(0);
				List<TBasisOrg> orgList = (List<TBasisOrg>) this.userDao.get(TBasisOrg.class, "orgCode", orgCode);
				//判断机构是否存在
				if(orgList.isEmpty()){
					errorNum++;
					continue;
				}
				//将信息保存到人员表和数据权限表中
				else{
					TBasisOrg org = orgList.get(0);
					user.setUserCode(userCode);
					user.setUserName(userName);
					user.setUserEmail(email);
					user.setUserPhone(phone);
					user.setUserType("1");
					user.setIsDisable("false");
					user.setUserPwd(MD5Util.md5("TCPW1234"));
					user.setUpdateTime(DateUtil.format(date));
					user.setUpdateUser(sessionVO.getUserCode());
					user.setTBasisOrg(org);
					this.userDao.saveOrUpdate(user);
					
					Set<TBasisUserData> tBasisUserDatas = user.getTBasisUserDatas();
					if(!tBasisUserDatas.isEmpty()){
						this.userDao.delUserData(user.getUserId());
					}
					List<?> orgDataList = this.orgService.qryOrgChildrenTree(org.getOrgId());
					for(int j=0;j<orgDataList.size();j++){
						TBasisUserData tud = new TBasisUserData();
						TBasisOrg to = (TBasisOrg)this.userDao.getByID(TBasisOrg.class, orgDataList.get(j).toString());
						tud.setTBasisOrg(to);
						tud.setTBasisUser(user);
						this.save(tud);
					}
				}
				doneNum = i+1;
			}
			Integer successNum = total - errorNum;
//			Integer undoNum = total - successNum - errorNum;
			common = new CommonVO(false,"共"+total.toString()+"行,"+successNum.toString()+"行导入成功,"+errorNum.toString()+"行导入失败");
		}catch (Exception e) { 
			e.printStackTrace(); 
		} finally {
			if(null != readwb)
			readwb.close(); 
		}
		return common;
		
	}
	
	/**
	 * 邮箱验证
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email)
	    {
	        boolean tag = true;
	        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	        final Pattern pattern = Pattern.compile(pattern1);
	        final Matcher mat = pattern.matcher(email);
	        if (!mat.find()) {
	            tag = false;
	        }
	        return tag;
	    }
	
	
	/**
	 * 手机号验证
	 * @param  str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String str) { 
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches(); 
		return b;
	}
	
	public static boolean isUserCode(String str){
		Pattern p = null;
		Matcher m = null;
		boolean b = false; 
		p = Pattern.compile("^[0-9]{7}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches(); 
		return b;
	}

}
