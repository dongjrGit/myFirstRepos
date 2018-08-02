package com.mobile.application.service.org.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.dao.IOrgDao;
import com.mobile.application.dao.IUserDao;
import com.mobile.application.entity.LogModuleOperation;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.entity.TBasisUserData;
import com.mobile.application.entity.TOrgTreeNode;
import com.mobile.application.service.org.IOrgService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

@Service
public class OrgServiceImpl implements IOrgService {

	@Autowired
	private IOrgDao orgDao;
	@Autowired
	private IUserDao userDao;

	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.org.IOrgService#qryOrgTree(com.mobile.application.vo.session.SessionVO)
	 */
	@Override
	@Transactional(readOnly = true) 
	public List<?> qryOrgTree(SessionVO sessionVO) throws Exception {
		String userId = sessionVO.getUserId();
		String orgId = null;
		//用户不是管理员
		if(!"1".equals(sessionVO.getIsAdmin())){
			orgId = this.orgDao.findOrgByUser(userId).get(0).toString();
			return this.qryOrgTree(orgId);
		}
		//用户是管理员，显示全部机构
		return orgDao.qryOrgTree(sessionVO,orgId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.org.IOrgService#qryOrgByID(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<?> qryOrgByID(String orgId) {
		return orgDao.qryOrgByID(orgId);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.org.IOrgService#showUserInfoAjax(javax.servlet.http.HttpSession, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String
	 */
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> showUserInfoAjax(HttpSession session,int indexPage, int indexSize, String deptId, String key){
		SessionVO user= (SessionVO)session.getAttribute("sessionVO");
		List<?> list = this.orgDao.listUser(deptId,key,indexPage,indexSize,user);
		//查询总数
		List<?> listSize = this.orgDao.listUser(deptId,key,user);
		//将集合转换成json数组
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", list);
		map.put("total",listSize.size());
		return map;
	}
	
    /*
     * (non-Javadoc)
     * @see com.mobile.application.service.org.IOrgService#saveOrgNode(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
	@Override
	@Transactional
	public CommonVO saveOrgNode(HttpSession session,String orgId, String orgName, String orgCode,String orgDesc){
		//验证机构名称是否重复
		Long nameCount = this.orgDao.checkOrgNameDouble(orgName);
		Long codeCount=this.orgDao.checkOrgCodeDouble(orgCode);
		if(nameCount == 0)
		{
			if (codeCount==0) {
			//根据传入的ID查询出父级节点
			TBasisOrg o = (TBasisOrg)this.orgDao.getByID(TBasisOrg.class, orgId);
			int Level = Integer.parseInt(o.getOrgLevel()) + 1 ;
			//保存节点
			TBasisOrg org = new TBasisOrg();
			org.setOrgName(orgName);
			org.setOrgDesc(orgDesc);
			org.setOrgPid(orgId);
			org.setOrgCode(orgCode);
			org.setOrgLevel(String.valueOf(Level));
			org.setIsDisable("0");
			this.orgDao.save(org);
			SessionVO user= (SessionVO)session.getAttribute("sessionVO");
			//获取权限 list 
			List<TBasisOrg> listOrg = this.orgDao.queryOrgsByUser(user);
			listOrg.add(org);
			//判断是否是admin 登陆
			if(!user.getUserCode().equals("admin"))
			{			
				this.orgDao.addOrg(user.getUserId(),org.getOrgId());
				
			}
		  }
			else{
				return new CommonVO(false,"已有相同机构ID");
			}
		}
		else
		{
			return new CommonVO(false,"已有相同机构名称");
		}


		return new CommonVO(true,"添加成功");
         
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.org.IOrgService#updOrgNode(javax.servlet.http.HttpSession, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public CommonVO updOrgNode(HttpSession session, String orgId, String pid,String orgName, String orgCode, String orgDesc){
		TBasisOrg org = (TBasisOrg)this.orgDao.getByID(TBasisOrg.class, orgId);
		org.setOrgPid(pid);
		org.setOrgId(orgId);
		org.setOrgName(orgName);
		org.setOrgDesc(orgDesc);
		org.setOrgCode(orgCode);
		this.orgDao.save(org);
		return new CommonVO(true,"编辑成功");
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.org.IOrgService#delOrgNode(javax.servlet.http.HttpSession, java.lang.String)
	 */
	@Override
	@Transactional
	public CommonVO delOrgNode(HttpSession session,String orgId){
		long countUser = this.orgDao.queryRoleHasUser(orgId);
		long countDevice = this.orgDao.queryOrgHasDevice(orgId);
		SessionVO user= (SessionVO)session.getAttribute("sessionVO");
		TBasisUser us = this.userDao.get(TBasisUser.class,user.getUserId());//当前登录用户
		if(countUser == 0)
		{
			if(countDevice == 0){
				this.orgDao.delOrg(orgId);
				
				//保存系统操作日志
				LogModuleOperation log = new LogModuleOperation();
				log.setModuleId("ORG");
				log.setOperateCode(us.getUserCode());
				log.setOperateOrg(us.getTBasisOrg());
				log.setOperateTime(new Date());
				log.setOperateType("删除");
				log.setDescription("删除机构");
				log.setOperateUser(us);
				this.orgDao.save(LogModuleOperation.class, log);
				
				return new CommonVO(true,"删除成功");
			}
			return new CommonVO(true,"机构下有设备,无法删除");
		}
		return new CommonVO(false,"机构下有人员,无法删除");
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.org.IOrgService#exportOrgReportExcel(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@Transactional
	public void exportOrgReportExcel(HttpSession session,HttpServletResponse response) {
		SessionVO user= (SessionVO)session.getAttribute("sessionVO");
		 WritableWorkbook wwb;
	      try {    
	    	  response.setHeader("Content-disposition", "attachment; filename=Excel.xls");// 设定输出文件头   
	    	  response.setContentType("application/vnd.ms-excel");// 定义输出类型 
	    	  OutputStream os = response.getOutputStream();// 取得输出流   
	    	  wwb = Workbook.createWorkbook(os);
	    	  WritableSheet ws = wwb.createSheet("机构信息表",10);// 创建一个工作表
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
			  ws.setColumnView(7,25);
			  ws.setColumnView(8,25);
	    	  int vv=0;
	    	  ws.addCell(new Label(vv,0,"机构编号",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"机构名称",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"机构等级",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"机构描述",wcf_top));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"父机构",wcf_top));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"父机构编号",wcf_top));
	    	  vv++;
	    	  String userId = "";
	    	  if(!"1".equals(user.getIsAdmin())){
	    		  userId = user.getUserId();
	    	  }
	    	  List<TBasisOrg> list = this.orgDao.getAll(userId);
				for (TBasisOrg tbo : list) {
					if(tbo.getOrgPid() != null){
					TBasisOrg tboP = (TBasisOrg)this.orgDao.getByID(TBasisOrg.class, tbo.getOrgPid());
					if (tboP!=null) {
						tbo.setColumn1(tboP.getOrgName());
						tbo.setColumn2(tboP.getOrgCode());
					}
					}
				}
	    	  TBasisOrg org = new TBasisOrg();
	    	  if(list != null && list.size()>0){
	    		  for (int i = 0; i < list.size(); i++){
	    			  int vvv=0;
	    			  org = (TBasisOrg)list.get(i);
	    			  ws.addCell(new Label(vvv,i+1,org.getOrgCode()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,org.getOrgName()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,org.getOrgLevel()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,org.getOrgDesc()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,org.getColumn1()+"",wcf));
	    			  vvv++;
	    			  ws.addCell(new Label(vvv,i+1,org.getColumn2()+"",wcf));
	    			  vvv++;
	    		}
	    	  }
	    	  wwb.write();
            wwb.close();
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }  
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.org.IOrgService#queryOrg(java.lang.String)
	 */
	@Transactional
	public TBasisOrg queryOrg(String orgId){
		return (TBasisOrg)orgDao.getByID(TBasisOrg.class, orgId);
	}


	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.org.IOrgService#qryChildrenOrg(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<TOrgTreeNode> qryChildrenOrg(String orgId) {
		List<TOrgTreeNode> nodes = new ArrayList<TOrgTreeNode>();
		TOrgTreeNode rootNode = (TOrgTreeNode) orgDao.getByID(
				TOrgTreeNode.class, orgId);
		qryChildrenOrg(nodes, rootNode);
		return nodes;
	}

	private void qryChildrenOrg(List<TOrgTreeNode> nodes, TOrgTreeNode node) {
		nodes.add(node);
		for (TOrgTreeNode children : node.getChildren()) {
			qryChildrenOrg(nodes, children);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mobile.application.service.org.IOrgService#qryOrgTree(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<?> qryOrgTree(String orgId) throws Exception {
		List<Map<String, String>> nodes = new ArrayList<Map<String, String>>();
		TOrgTreeNode rootNode = (TOrgTreeNode) orgDao.getByID(TOrgTreeNode.class, orgId);
		qryOrgTree(nodes, rootNode);
		listSort(nodes);
		return nodes;
	}

	private void qryOrgTree(List<Map<String, String>> nodes, TOrgTreeNode node) {
		Map<String, String> treeMap = new HashMap<String, String>();
		treeMap.put("id", node.getOrgId());
		treeMap.put("pid", node.getOrgPid());
		treeMap.put("name", node.getOrgName());
		treeMap.put("orgLevel", node.getOrgLevel());
		nodes.add(treeMap);
		for (TOrgTreeNode children : node.getChildren()) {
			qryOrgTree(nodes, children);
		}
	}
	
	private void qryOrgParentTree(List<String> nodes,TOrgTreeNode node){
		if(node != null){
			nodes.add(node.getOrgId());
			qryOrgParentTree(nodes, node.getParent());
		}

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<?> qryOrgParentTree(String orgId){
		List<String> nodes = new ArrayList<String>();
		TOrgTreeNode rootNode = (TOrgTreeNode) orgDao.getByID(TOrgTreeNode.class, orgId);
		qryOrgParentTree(nodes, rootNode);
		return nodes;
	}
	
	
	public void listSort(List<Map<String,String>> resultList) throws Exception{
		Collections.sort(resultList,new Comparator<Map<String,String>>(){
			public int compare(Map<String, String> o1,Map<String, String> o2){
				 String s1 = o1.get("name");  
	             String s2 = o2.get("name");  
	             return s1.compareTo(s2);
	               
			}
		});
	}
	
	
	private void qryOrgChildrenTree(List<String> nodes,TOrgTreeNode node){
		if(node != null){
			nodes.add(node.getOrgId());
			for (TOrgTreeNode children : node.getChildren()) {
				qryOrgChildrenTree(nodes, children);
			}
		}

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<?> qryOrgChildrenTree(String orgId){
		List<String> nodes = new ArrayList<String>();
		TOrgTreeNode rootNode = (TOrgTreeNode) orgDao.getByID(TOrgTreeNode.class, orgId);
		qryOrgChildrenTree(nodes, rootNode);
		return nodes;
	}
	
	
	@Override
	@Transactional
	public List<?> queryOrgList(SessionVO sessionVo) {
		return orgDao.queryOrg(sessionVo);
	}

	@Override
	@Transactional
	public void exportOrgModel(HttpServletResponse response) {
		WritableWorkbook wwb;
	      try {    
	    	  response.setHeader("Content-disposition", "attachment; filename=orgexcel.xls");// 设定输出文件头   
	    	  response.setContentType("application/vnd.ms-excel");// 定义输出类型 
	    	  OutputStream os = response.getOutputStream();// 取得输出流   
	    	  wwb = Workbook.createWorkbook(os);
	    	  WritableSheet ws = wwb.createSheet("机构信息表",10);// 创建一个工作表
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
	    	  int vv=0;
	    	  ws.addCell(new Label(vv,0,"机构编号",wcf));
	    	  ws.addCell(new Label(vv,1,"11111",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"机构名称",wcf));
	    	  ws.addCell(new Label(vv,1,"无双",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"机构描述",wcf_top));
	    	  ws.addCell(new Label(vv,1,"天下无双",wcf));
	    	  vv++;
	    	  ws.addCell(new Label(vv,0,"父机构编号",wcf_top));
	    	  ws.addCell(new Label(vv,1,"6",wcf));
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
	public CommonVO importOrg(MultipartFile file, HttpSession session,
			HttpServletRequest request)throws BusinessException, IOException {
		SessionVO user= (SessionVO)session.getAttribute("sessionVO");
		Date date=new Date();
		Workbook readwb = null; 
		try 
		{ 
			InputStream instream=file.getInputStream();	
//		String rootPath =ServletActionContext.getServletContext().getRealPath("/"); 
////		 构建Workbook对象, 只读Workbook对象 
////		 直接从本地文件创建Workbook 
//		InputStream instream = new FileInputStream(rootPath + "/examples/AppColumns.xls"); 

		readwb = Workbook.getWorkbook(instream); 

		// Sheet的下标是从0开始 
		// 获取第一张Sheet表 
		Sheet readsheet = readwb.getSheet(0); 

		// 获取Sheet表中所包含的总列数 
		int rsColumns = readsheet.getColumns(); 

		// 获取Sheet表中所包含的总行数 
		int rsRows = readsheet.getRows(); 
		if(rsColumns!=4||!"机构编号".equals(readsheet.getCell(0, 0).getContents())){
			return new CommonVO(false, "导入失败,导入文件错误");
		}

		// 获取指定单元格的对象引用 
		JSONArray dataArray = new JSONArray(); 
		JSONArray dataArray1 = new JSONArray(); 
		for (int j = 1; j < rsRows; j++) 
		{ 
		JSONObject dataJson = new JSONObject(); 
		JSONObject dataJson1 = new JSONObject(); 
		Cell cell_0 = readsheet.getCell(0, j); 
		dataJson.put("orgCode", cell_0.getContents()); 
		dataJson1.put("orgCode", cell_0.getContents());
		
		Cell cell_1 = readsheet.getCell(1, j); 
		dataJson.put("orgName", cell_1.getContents()); 

		Cell cell_2 = readsheet.getCell(2, j); 
		dataJson.put("orgDesc", cell_2.getContents()); 

		Cell cell_3 = readsheet.getCell(3, j); 
		dataJson.put("orgPid", cell_3.getContents()); 
		dataJson1.put("orgPid", cell_3.getContents()); 
		
		dataArray.add(dataJson); 
		dataArray1.add(dataJson1); 
		} 
		int t = dataArray.size();
		int note = 0; //用于标记父机构编号在添加表中
        for(int i = 0;i<dataArray.size();i++){
        	if(i > t*t){
        		return new CommonVO(false, "导入失败");
        	}
        	JSONObject orgdata = dataArray.getJSONObject(i);
        	for(int j = i+1;j<dataArray1.size();j++){
        		
        		//判断是否重复
        		if(orgdata.getString("orgCode").equals(dataArray1.getJSONObject(j).getString("orgCode"))){
        			return new CommonVO(false, "导入失败，第"+i+1+"行机构编号重复");
        		}
        		//是否父机构在表中
        		if(orgdata.getString("orgPid").equals(dataArray1.getJSONObject(j).getString("orgCode"))){
        			note = 1;
        		}
        	}
        	//机构是否存在数据库
        	Long checkOrgCode = orgDao.checkOrgCodeDouble(orgdata.getString("orgCode"));
        	Long checkOrgPid = orgDao.checkOrgCodeDouble(orgdata.getString("orgPid"));
        	if(checkOrgCode==1){//机构在数据库中，修改数据
        		List<TBasisOrg> org = (List<TBasisOrg>) orgDao.get(TBasisOrg.class, "orgCode", orgdata.getString("orgCode"));
        		org.get(0).setOrgDesc(orgdata.getString("orgDesc"));
        		org.get(0).setOrgName(orgdata.getString("orgName"));
//        		org.get(0).setUpdateTime(DateUtil.format(date,"yyyy-MM-dd HH:mm:ss"));
        		org.get(0).setUpdateUser(user.getUserId());
        		orgDao.save(org.get(0));
        	}else if(checkOrgPid==1){//父机构在数据库中，保存机构
        		List<TBasisOrg> org = (List<TBasisOrg>) orgDao.get(TBasisOrg.class, "orgCode", orgdata.getString("orgPid"));
        		TBasisOrg updorg = new TBasisOrg();
        		updorg.setOrgCode(orgdata.getString("orgCode"));
        		updorg.setOrgDesc(orgdata.getString("orgDesc"));
        		updorg.setOrgName(orgdata.getString("orgName"));
        		updorg.setOrgLevel(Integer.toString((Integer.parseInt(org.get(0).getOrgLevel())+1)));
        		updorg.setOrgPid(org.get(0).getOrgId());
        		orgDao.save(updorg);
        	}else if(note==1){//父机构不在数据库但是在表中，放在列表末尾
        		dataArray.add(orgdata);
        	}else{//父机构不在
        		return new CommonVO(false,"导入失败，第"+i+1+"行父机构不存在");
        	}
        }
		} catch (Exception e) { 
		e.printStackTrace(); 
		return new CommonVO(false,"导入错误文件");
		} finally { 
		readwb.close(); 
		}
		return new CommonVO(true,"导入成功");
	}

	@Override
	@Transactional
	public List<?> queryOrgByRoleId(String roleId) {
		return orgDao.queryOrgByRoleId(roleId);
	}
	
	@Override
	@Transactional
	public List<?> qryOrgByProductId(String productId) {
		return orgDao.qryOrgByProductId(productId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<?> qryUserDataOrg(SessionVO sessionVO, String userId) {
		List<Map<String, Object>> allOrgs = (List<Map<String, Object>>) orgDao.qryOrgTree(sessionVO, null);
		if(StringUtils.isNotBlank(userId)){
			List<String> userDataOrgs = orgDao.qryUserDataOrg(userId);
			for (Map<String, Object> orgMap : allOrgs) {
				if(userDataOrgs.contains(orgMap.get("id"))){
					orgMap.put("checked", true);
				}
			}
		}
		return allOrgs;
	}

	@Override
	@Transactional
	public CommonVO saveUserDataOrg(String orgIds, String userId) {
		orgDao.deleteUserDataOrg(userId);
		String[] orgIdArray = orgIds.split(",");
		for (String orgId : orgIdArray) {
			TBasisOrg TBasisOrg = new TBasisOrg();
			TBasisOrg.setOrgId(orgId);
			TBasisUser TBasisUser = new TBasisUser();
			TBasisUser.setUserId(userId);
			TBasisUserData TBasisUserData = new TBasisUserData(TBasisOrg, TBasisUser);
			orgDao.save(TBasisUserData);
		}
		return new CommonVO(true, "保存成功");
	}
}
