package com.mobile.application.service.activityPut.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.IActivityDao;
import com.mobile.application.entity.TBasisActivity;
import com.mobile.application.entity.TBasisActivityCheck;
import com.mobile.application.entity.TBasisActivityFile;
import com.mobile.application.entity.TBasisActivityOrg;
import com.mobile.application.entity.TBasisOrg;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.service.activityPut.IActivityService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;
@Service
public class ActivityServiceImpl implements IActivityService {
	@Autowired
	private IActivityDao activityDao;

	@Override
	@Transactional(readOnly = true)
	public CommonVO queryActivity(HttpSession session,int pageIndex,
			int pageSize, String startTime, String endTime,
			String activityName, String issuer, String status, String orgId,
			HttpServletRequest request) {
		SessionVO sessionvo = (SessionVO)session.getAttribute("sessionVO");
		TBasisUser user = (TBasisUser)this.activityDao.getByID(TBasisUser.class, sessionvo.getUserId());
		List<?> list = activityDao.qryActivityList(pageIndex,pageSize,activityName,orgId,issuer,status,startTime,endTime);
		String activitysSize = activityDao.qryActivitySize(activityName,orgId,issuer,status,startTime,endTime).size()+"";
		return new CommonVO(true, list, activitysSize);
	}

	@Override
	public SessionVO getUser(HttpSession session) {
		SessionVO sessionvo = (SessionVO)session.getAttribute("sessionVO");
		return sessionvo;
	}

	@Override
	@Transactional(readOnly = true)
	public CommonVO queryActivityList(HttpSession session,
			int pageIndex, int pageSize, String startTime, String endTime,
			String activityName, String issuer, String status, String orgId,
			HttpServletRequest request) {
		SessionVO sessionvo = (SessionVO)session.getAttribute("sessionVO");
		TBasisUser user = (TBasisUser)this.activityDao.getByID(TBasisUser.class, sessionvo.getUserId());
		if(orgId==null|| "".equals(orgId)){
			TBasisOrg	org = user.getTBasisOrg();
			orgId = org.getOrgId();
		}
		List<TBasisActivity> list = (List<TBasisActivity>) activityDao.qryActivityListAll(pageIndex,pageSize,activityName,orgId,issuer,status,startTime,endTime);
		String activitysSize = activityDao.qryActivitySize(activityName,orgId,issuer,status,startTime,endTime).size()+"";
		return new CommonVO(true, list, activitysSize);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CommonVO queryCheckActivityList(HttpSession session,
			int pageIndex, int pageSize, String startTime, String endTime,
			String activityName, String issuer, String status, String orgId,
			HttpServletRequest request) {
		SessionVO sessionvo = (SessionVO)session.getAttribute("sessionVO");
		TBasisUser user = (TBasisUser)this.activityDao.getByID(TBasisUser.class, sessionvo.getUserId());
		if(orgId==null|| "".equals(orgId)){
			TBasisOrg	org = user.getTBasisOrg();
			orgId = org.getOrgId();
		}
		List<TBasisActivity> list = (List<TBasisActivity>) activityDao.qryCheckActivityListAll(pageIndex,pageSize,activityName,orgId,issuer,status,startTime,endTime);
		String activitysSize = activityDao.qryCheckActivitySize(activityName,orgId,issuer,status,startTime,endTime).size()+"";
		return new CommonVO(true, list, activitysSize);
	}
	
	@Transactional
	public Map<String, Object> activity_pictures(String productId) throws IOException {
	/*	Map<String, Object> picMap = new HashMap<String, Object>();
		List<Map<String, String>> picList = new ArrayList<Map<String, String>>();
		if(StringUtils.isNotBlank(productId)){
			List<?> products = activityDao.get(TBasisActivityFile.class, "id", productId);
			TBasisProduct product = (TBasisProduct) products.get(0);
			picMap.put("product", product);
			picMap.put("productMenu", product.getTBasisProductMenu());
			TBasisProductTemplate TBasisProductTemplate = product.getTBasisProductMenu().getTBasisProductTemplate();
			picMap.put("productTemplate", product.getTBasisProductMenu().getTBasisProductTemplate());
			String gegDesc  = "";
			String rootPath = (String) SpringProperty.props.get("RootPath");
			String activytPath = (String) SpringProperty.props.get("activytPath");
			//////////////////////
			String pictureDescPath = rootPath + activytPath + "/picDesc/"+product.getId()+".txt";
			File pictureDescFile = new File(pictureDescPath);
			FileInputStream fis = null;
			try{
				if(pictureDescFile.getParentFile().exists()){
					fis = new FileInputStream(pictureDescFile);
					byte[] resByte = StreamUtils.getBytes(fis);
					 gegDesc = new String(resByte);
				}
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(null != fis)
					fis.close();
			}
			//////////////////////////
			JSONObject pictureDesc = new JSONObject();
			if(StringUtils.isNotBlank(gegDesc))
				pictureDesc = JSONObject.fromObject(gegDesc);
			if(StringUtils.isNotBlank(product.getProductFolder())){
				File productFolder = new File(rootPath + product.getProductFolder() + "/pic");
				if(!productFolder.exists()){
					picMap.put("picList", picList);
					return picMap;
				}
				for(File file : productFolder.listFiles()){
					Map<String, String> picFileMap = new HashMap<String, String>();
					picFileMap.put("picPath", product.getProductFolder() + "/pic/" + file.getName());
					picFileMap.put("picName", file.getName());
					picFileMap.put("picDesc", pictureDesc.optString(file.getName()));
					picList.add(picFileMap);
				}
			}
		}
		picMap.put("picList", picList);*/
		return null;
	}

	@Override
	@Transactional
	public CommonVO saveProductInfo(String orgId,TBasisActivity tBasisActivity,MultipartFile pic, String productId,
			HttpSession session) {
		//获取用户登录session
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		//获取文件存放根目录
		String rootPath = (String) SpringProperty.props.get("RootPath");
		//获取文件存放根目录
		String activytPath = (String) SpringProperty.props.get("activytPath");
		//根据产品编号查出当前操作的产品信息
		//List<?> products = activityDao.get(TBasisActivity.class, "id", productId);
		//TBasisActivity product = tBasisActivity;
		TBasisActivity	tBasisActivitys =  activityDao.updateActivityByCode(tBasisActivity.getActivityCode());
		Date currentTime = new Date();
		String currentTimeStr = DateUtil.format(currentTime);
		//活动代码为空更新
		if(null!=tBasisActivitys){
			tBasisActivitys.setUpdatetime(currentTimeStr);
			tBasisActivitys.setActivityName(tBasisActivity.getActivityName());
			tBasisActivitys.setActivityStartTime(tBasisActivity.getActivityStartTime());
			tBasisActivitys.setActivityEndTime(tBasisActivity.getActivityEndTime());
			//产品下架编辑再次提交
			if("2".equals(tBasisActivity.getStatus())){
				tBasisActivitys.setStatus("2");
			}
			activityDao.saveOrUpdate(tBasisActivitys);
		}else{
			//更新产品信息
		
			tBasisActivity.setCreatetime(currentTimeStr);
			tBasisActivity.setUpdateuser(sessionVO.getUserId());
			tBasisActivity.setUpdatetime(currentTimeStr);
			if(StringUtils.isBlank(tBasisActivity.getStatus()))
			tBasisActivity.setStatus("1");
			activityDao.saveOrUpdate(tBasisActivity);
		}
		//写入图片文件
/*		String suffix = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
		String picPath = rootPath + activytPath + "/pic/" + DateUtil.format(currentTime, "yyyyMMddHHmmssSSS")  + suffix;
		File picWriteFile = new File(picPath);
		if(!picWriteFile.getParentFile().exists())
			picWriteFile.getParentFile().mkdirs();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(picPath);
			fos.write(pic.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new BusinessException("500", "文件写入失败");
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
/*		TBasisActivityFile file = new TBasisActivityFile();
		file .setActivityId(tBasisActivity.getActivityId());
		//file.setContent();
		file.setFilelen(picPath);
		file.setFilename(picPath);
		try {
			file.setFilelen(pic.getSize() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		activityDao.save(file);*/
		//循环保存机构活动
		//orgId []
		//String orgIds = "1,H001" ;
		String[] orgIdss  = orgId.split(",");
		for (String string : orgIdss) {
			TBasisActivityOrg org = new TBasisActivityOrg();
			if(null!=tBasisActivitys){
				activityDao.delActivtyByOrgCode(tBasisActivitys.getActivityCode());
				org.setActivityId(tBasisActivitys.getActivityCode());
				org.setOrgId(string);
				activityDao.saveOrUpdate(org);
			}
			else{
				org.setActivityId(tBasisActivity.getActivityCode());
				org.setOrgId(string);
				activityDao.save(org);
			}
		}
		//activityDao.saveProductPic(productId);
		return null;
	}

	@Override
	@Transactional
	public CommonVO saveAcitivityPic(TBasisActivityFile tBasisActivityFile,
			MultipartFile pic, HttpSession session,String activityCode) {
		
		
		//更新产品信息
		Date currentTime = new Date();
		String currentTimeStr = DateUtil.format(currentTime);
		//获取文件存放根目录
		String rootPath = (String) SpringProperty.props.get("RootPath");
		//获取文件存放根目录
		String activytPath = (String) SpringProperty.props.get("activytPath");
		//写入图片文件
		String suffix = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));
		String filename = DateUtil.format(currentTime, "yyyyMMddHHmmssSSS")  + suffix;
		//String picPath = rootPath + activytPath + "/pic/" +"10000001/"+ DateUtil.format(currentTime, "yyyyMMddHHmmssSSS")  + suffix;
		//String picPath = rootPath + activytPath +"/10000001/"+   + suffix;
		String picPath = rootPath + activytPath +"/"+filename;
		File picWriteFile = new File(picPath);
		if(!picWriteFile.getParentFile().exists())
			picWriteFile.getParentFile().mkdirs();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(picPath);
			fos.write(pic.getBytes());
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new BusinessException("500", "文件写入失败");
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
/*		FileInputStream tempFileInputStream;
		String tempDesc = "" ;
		try {
			tempFileInputStream = new FileInputStream("D:/pushwin/activity/10000001/10000001.txt");
			 tempDesc = new String(StreamUtils.getBytes(tempFileInputStream), "utf-8");
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JSONArray tempArray = JSONArray.fromObject(tempDesc);
		JSONObject objectpic  =new JSONObject();
		objectpic.put("picPath", picPath.substring(10,picPath.length()));
		objectpic.put("description", "11");
		tempArray.add(objectpic);
		String ss = tempArray.toString();
		File filext  = new File("D:/pushwin/activity/10000001/10000001.txt");
		FileOutputStream os = null;
		try {
			 os  = new FileOutputStream(filext);
			 os.write(ss.getBytes());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		//file.setContent();
		tBasisActivityFile.setContent(tBasisActivityFile.getContent().trim());
		tBasisActivityFile.setFilename(filename);
		tBasisActivityFile.setFilepath(activytPath +"/"+filename);
		//tBasisActivityFile.setContent(tBasisActivityFile);
		tBasisActivityFile.setActivityId(activityCode);
		try {
			tBasisActivityFile.setFilelen(pic.getSize() + "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		activityDao.save(tBasisActivityFile);
		return null;
	}

	@Override
	@Transactional
	public CommonVO saveActivityCheck(TBasisActivityCheck check,
			String proguctid, HttpSession session) {
		//获取文件存放根目录
		String rootPath = (String) SpringProperty.props.get("RootPath");
		//获取文件存放根目录
		String activytPath = (String) SpringProperty.props.get("activytPath");
		//check.setActivityId(proguctid);
		//获取用户登录session
		SessionVO sessionVO = (SessionVO)session.getAttribute("sessionVO");
		Date currentTime = new Date();
		String filename = DateUtil.format(currentTime, "yyyy-MM-dd HH:mm:ss");
		check.setCheckUser(sessionVO.getUserId());
		check.setCheckTime(filename);
		TBasisActivity tBasisActivity = (TBasisActivity) activityDao.get(TBasisActivity.class, "activityCode", check.getActivityId()).get(0);
		List<TBasisActivityFile> tBasisActivityFiles = (List<TBasisActivityFile>) activityDao.get(TBasisActivityFile.class, "activityId", check.getActivityId());
		if("1".equals(check.getStatus())){
			ZipParameters parameters = new ZipParameters();  
		    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);           // 压缩方式  
		    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);    // 压缩级别  
		    ZipFile zipFile = null;
		    try{
				zipFile = new ZipFile(rootPath + activytPath + "/" + check.getActivityId() + ".zip");
				for (TBasisActivityFile tBasisActivityFile : tBasisActivityFiles) {
					File picFile = new File(rootPath + tBasisActivityFile.getFilepath());
					zipFile.addFile(picFile, parameters);
				}
		    } catch (Exception e) {
				return new CommonVO(false, "文件压缩出错");
			}
		    tBasisActivity.setZipPath(activytPath + "/" + check.getActivityId() + ".zip");
		    tBasisActivity.setZipLength(zipFile.getFile().length() + "");
		    tBasisActivity.setStatus("4");
//        	activityDao.updateActivityStatusByCode(check.getActivityId(), "4");
        }else{
        	tBasisActivity.setStatus("3");
//        	activityDao.updateActivityStatusByCode(check.getActivityId(), "3");
        }
		activityDao.update(tBasisActivity);
		activityDao.saveOrUpdate(check);
		return new CommonVO(true, "保存成功");
	}

	@Override
	@Transactional
	public CommonVO queryActivityCheck(String proguctid, HttpSession session) {
		//activityDao.queryActivityCheck(proguctid);
		return null;
	}

	@Override
	@Transactional
	public List<?> queryPiclist(String activitycode) {
		List<?>  listpic = activityDao.queryPiclist(activitycode);
		return listpic;
	}

	@Override
	@Transactional
	public String delOnPge(String picPath) {
		String s = activityDao.delOnPge(picPath);
		return null ;
	}

	@Override
	@Transactional
	public String setFacePage(String picPath, String activityid) {
		String ss = activityDao.setFacePage( picPath, activityid);
		return ss;
	}

	@Override
	@Transactional
	public JSONObject getActiviyOneByid(String activityId) {
		JSONObject map = activityDao.getActiviyOneByid(activityId);
		return map;
	}
	@Override
	@Transactional(readOnly = true)
	public Map<String, String> qryActivityOrgs(String activityId){
		List<Map<String, String>> orgMapList = activityDao.qryActivityOrgs(activityId);
		List<String> orgIdList = new ArrayList<String>();
		List<String> orgNameList = new ArrayList<String>();
		for (Map<String, String> map : orgMapList) {
			orgIdList.add(map.get("orgId"));
			orgNameList.add(map.get("orgName"));
		}
		Map<String, String> orgMap = new HashMap<String, String>();
		orgMap.put("orgIds", StringUtils.join(orgIdList, ","));
		orgMap.put("orgNames", StringUtils.join(orgNameList, ","));
		return orgMap;
	}
	
	@Override
	@Transactional
	public String delactivityOne(String activityid) {
		String ss =	activityDao.delactivityOne(activityid);
		return ss;
	}

	@Override
	@Transactional
	public String queryPicListMax(String activityid) {
		String ss =	activityDao.queryPicListMax(activityid);
		return ss;
	}

	@Override
	@Transactional
	public String updateActivityStatus(String activityid, String status) {
		String ss = activityDao.updateActivityStatus(activityid, status);
		return ss;
	}
	
	@Override
	@Transactional
	public String queryAcitvityCheckFlag(String activityid) {
		String ss = activityDao.queryAcitvityCheckFlag(activityid);
		return ss;
	}
	public static void main(String[] args) throws ZipException {
		ZipParameters parameters = new ZipParameters();  
	    parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);           // 压缩方式  
	    parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);    // 压缩级别  
		ZipFile zipFile = new ZipFile("D:/pic.zip");
		File srcFile = new File("D:/pushwin/activity/20160427114545239.png");
		zipFile.addFile(srcFile, parameters);
		File srcFile2 = new File("D:/pushwin/activity/20160427135533971.png");
		zipFile.addFile(srcFile2, parameters);
	}

	@Override
	@Transactional
	public CommonVO setTop(String activityid) {
		TBasisActivity tBasisActivity = (TBasisActivity) activityDao.getByID(TBasisActivity.class, activityid);
		tBasisActivity.setCreatetime(DateUtil.format(new Date()));
		activityDao.update(tBasisActivity);
		return new CommonVO(true, "保存成功");
	}

	@Override
	@Transactional
	public String deleleActivityCheckInfo(String activitycode) {
		TBasisActivity acit = (TBasisActivity) activityDao.getByID(TBasisActivity.class, activitycode);
		String ss =  activityDao.deleleActivityCheckInfo(acit.getActivityCode());
		return ss;
	}
	
}
