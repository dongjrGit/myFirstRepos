package com.mobile.application.controller.activityPut;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.entity.TBasisActivity;
import com.mobile.application.entity.TBasisActivityCheck;
import com.mobile.application.entity.TBasisActivityFile;
import com.mobile.application.service.activityPut.IActivityService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;



@Controller
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	private IActivityService sctivityPutService;
		
	/*
	 * 进入活动发布页面
	 * */
	@RequestMapping("/init")
	public String init(){
		return "activity/activity_list";
	}
	
	/*
	 * 进入活动审核页面
	 * */
	@RequestMapping("/check")
	public String check(){
		return "activity/checkactivity_list";
	}
	/*
	 * 进入活动审核页面
	 * */
	@RequestMapping("/upfile")
	public String upfile(){
		return "activity/activitypic_up";
	}
	
	
	/*
	 * 进入活动发布页面
	 * */
	@RequestMapping("/initActivityPicList")
	public ModelAndView initActivityPicList(String seq,String acitivitycode, boolean qryFlag){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			/*FileInputStream tempFileInputStream = new FileInputStream("D:/pushwin/activity/10000001/10000001.txt");
				String tempDesc = new String(StreamUtils.getBytes(tempFileInputStream), "utf-8");*/
				List<?>	 listpics =sctivityPutService.queryPiclist(acitivitycode);
				JSONArray tempArray = JSONArray.fromObject(listpics);
				result.put("tempArray", tempArray);
				result.put("qryFlag", qryFlag);
		} catch (Exception e) {
			  e.printStackTrace();
		}
		return new ModelAndView("activity/activity_piclist",result);
	}
	
	@RequestMapping("/queryActivity")
	@ResponseBody
	public CommonVO queryActivity(HttpSession session,int pageIndex,int pageSize,String orgId ,String startTime,String endTime,String activityName,String issuer,String status,HttpServletRequest request){
		return sctivityPutService.queryActivityList(session,pageIndex,pageSize,startTime,endTime,activityName,issuer,status,orgId,request);
	}
	
	@RequestMapping("/queryCheckActivity")
	@ResponseBody
	public CommonVO queryCheckActivity(HttpSession session,int pageIndex,int pageSize,String orgId ,String startTime,String endTime,String activityName,String issuer,String status,HttpServletRequest request){
		return sctivityPutService.queryCheckActivityList(session,pageIndex,pageSize,startTime,endTime,activityName,issuer,status,orgId,request);
	}
	@RequestMapping("/activityAddInit")
	public ModelAndView noticeAddInit(HttpSession session,HttpServletRequest request){
		SessionVO sessionVo = sctivityPutService.getUser(session);
		Map<String,String> map = new HashMap<String,String>();
		map.put("username", sessionVo.getUserName());
		map.put("usercode", sessionVo.getUserCode());
		String activitydateTime =  new SimpleDateFormat("yyyyMMddhhmmssS").format(new Date());
		map.put("activitydateTime", activitydateTime);
		return new ModelAndView("activity/activity_info",map);
	}
	
	@RequestMapping("/loadActivityOne")
	public ModelAndView loadActivityOne(String activityid,boolean qryFlag ,HttpSession session,HttpServletRequest request){
		//SessionVO sessionVo = sctivityPutService.getUser(session);c
		Map<String,Object> map = new HashMap<String,Object>();
	     JSONObject mapdata = 	sctivityPutService.getActiviyOneByid(activityid);
	     Map<String, String> orgMapList = sctivityPutService.qryActivityOrgs(mapdata.optJSONObject("tBasisActivity").optString("activityCode"));
	     //String activityOrg = sctivityPutService.getOrgIds(activityid);
	     map.put("activitydata", mapdata);
	     map.put("qryFlag", qryFlag);
	     map.put("orgInfos", orgMapList);
	     //map.put("activityOrg", activityOrg);
		return new ModelAndView("activity/activity_detail",map);
	}
	
	@RequestMapping("/upActivityOne")
	public ModelAndView upActivityOne(String activityid,boolean qryFlag ,HttpSession session,HttpServletRequest request){
		//SessionVO sessionVo = sctivityPutService.getUser(session);c
		Map<String,Object> map = new HashMap<String,Object>();
	     JSONObject mapdata = 	sctivityPutService.getActiviyOneByid(activityid);
	     Map<String, String> orgMapList = sctivityPutService.qryActivityOrgs(mapdata.optJSONObject("tBasisActivity").optString("activityCode"));
	     //String activityOrg = sctivityPutService.getOrgIds(activityid);
	     
	     map.put("activitydata", mapdata);
	     map.put("qryFlag", qryFlag);
	     map.put("orgInfos", orgMapList);
	     //map.put("activityOrg", activityOrg);
		return new ModelAndView("activity/activity_update",map);
	}
	
	@RequestMapping("/orgSelecter")
	public String selectOrgTreeWindow(){
		return "activity/product_addOrg";
	}
	@RequestMapping("/selUser")
	public String selectUser(HttpSession session){
		return "activity/org_selecter";
	}
	
	@RequestMapping("/saveActivityPic")
	@ResponseBody
	public ModelAndView saveActivityPic(String actjson,String orgscopeadd,TBasisActivity activitycontent, String productId,HttpSession session){
		//CommonVO co = new CommonVO();
		//return sctivityPutService.queryActivity(session,pageIndex,pageSize,startTime,endTime,activityName,issuer,status,orgId,request);
		/*	co.setTotal("1");
			List<TBasisActivity> lsitac  = sctivityPutService.queryActivityList(session,pageIndex,pageSize,startTime,endTime,activityName,issuer,status,orgId,request);
			co.setData(lsitac);*/
		String ss = "";
		System.out.println(ss);
		//SessionVO sessionVo = sctivityPutService.getUser(session);
		 sctivityPutService.saveProductInfo(orgscopeadd,activitycontent, null,  productId, session);
		 SessionVO sessionVo = sctivityPutService.getUser(session);
			Map<String,String> map = new HashMap<String,String>();
			map.put("username", sessionVo.getUserName());
			map.put("usercode", sessionVo.getUserCode());
			
		 return new ModelAndView("activity/activity_list",map);
	}
	
	@RequestMapping("/saveActivityPicture")
	@ResponseBody
	public CommonVO saveActivityPicture(TBasisActivityFile activitityFile,@RequestParam(value = "picFile_0", required = false)MultipartFile file,String activityCode,String content,String fileValue3, String productId,HttpSession session) throws IOException{
		//byte[] reqByte = StreamUtils.getBytes(request.getInputStream());
		//System.out.println(new String(reqByte));
		String ss = new String(fileValue3.getBytes(),"utf-8");
		String postrl = URLDecoder.decode(fileValue3);
		System.out.println(postrl);
		//SessionVO sessionVo = sctivityPutService.getUser(session);
		return sctivityPutService.saveAcitivityPic(activitityFile, file, session,activityCode);
	}
	
	@RequestMapping("/removeProductPic")
	@ResponseBody
	public String removeProductPic(String picPath,HttpSession session){


		//SessionVO sessionVo = sctivityPutService.getUser(session);
	/*	File picWriteFile = new File(picPath);
		picWriteFile.delete();
		FileInputStream tempFileInputStream;
		String tempDesc = "" ;
		try {
			tempFileInputStream = new FileInputStream("D:/pushwin/activity/10000001/10000001.txt");
			 tempDesc = new String(StreamUtils.getBytes(tempFileInputStream), "utf-8");
		} catch (Exception e2) {
			
			e2.printStackTrace();
		}
		
		JSONArray tempArray = JSONArray.fromObject(tempDesc);
        for (int i = 0; i < tempArray.size(); i++) {
			JSONObject object = (JSONObject) tempArray.get(i);
			if(picPath.endsWith(object.optString(picPath))){
				tempArray.remove(object);
			}
		}
		File filext  = new File("D:/pushwin/activity/10000001/10000001.txt");
		FileOutputStream os = null;
		try {
			 os  = new FileOutputStream(filext);
			 os.write(tempArray.toString().getBytes());
		} catch (Exception e2) {
			
			e2.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}*/
		return sctivityPutService.delOnPge(picPath)	;
	}
	
	@RequestMapping("/saveActivityCheck")
	@ResponseBody
	public CommonVO saveActivityCheck(TBasisActivityCheck acti, String productId,HttpSession session){
		return sctivityPutService.saveActivityCheck(acti,  productId, session);
	}
	
	
	@RequestMapping("/queryActivityCheck")
	@ResponseBody
	public CommonVO queryActivityCheck(String productId,HttpSession session){

		return sctivityPutService.queryActivityCheck( productId, session);
	}

	@RequestMapping("/setFacePage")
	@ResponseBody
	public String setFacePage(String picPath,String activityid){
		return sctivityPutService.setFacePage( picPath,activityid);
	}

	@RequestMapping("/delactivityOne")
	@ResponseBody
	public String delactivityOne(String activityid){
		return sctivityPutService.delactivityOne(activityid);
	}
	
	@RequestMapping("/queryPicListMax")
	@ResponseBody
	public String queryPicListMax(String activityid){
		String pilistMax =  sctivityPutService.queryPicListMax(activityid);
		return  pilistMax;
	}
	
	@RequestMapping("/updateActivityStatus")
	@ResponseBody
	public String updateActivityStatus(String activityid,String status){
		//活动下架删除以前审核评论等待重新审核
		if("6".equals(status)){
			sctivityPutService.deleleActivityCheckInfo(activityid);
		}
		String pilistMax =  sctivityPutService.updateActivityStatus(activityid, status);
		return  pilistMax;
	}

	@RequestMapping("/queryAcitvityCheckFlag")
	@ResponseBody
	public String queryAcitvityCheckFlag(String activityid){
		String pilistMax =  sctivityPutService.queryAcitvityCheckFlag(activityid);
		return  pilistMax;
	}
	
	@RequestMapping("/setTop")
	@ResponseBody
	public CommonVO setTop(String activityid){
		try{
			//return new CommonVO(false, "保存失败");
			return sctivityPutService.setTop(activityid);
		} catch (Exception e) {
			return new CommonVO(false, "保存失败");
		}
	}
	
	
}
