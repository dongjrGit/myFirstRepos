package com.mobile.application.service.apk.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.util.CompressUtil;
import com.mobile.application.commmon.util.DateUtil;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.IAPKDao;
import com.mobile.application.entity.TBasisApk;
import com.mobile.application.service.apk.IAPKService;
import com.mobile.application.vo.CommonVO;
import com.mobile.application.vo.session.SessionVO;

@Service
public class APKServiceImpl implements IAPKService{

	@Autowired
	private IAPKDao apkDao;
	
	@Override
	@Transactional(readOnly = true)
	public CommonVO qryApks(int indexPage, int indexSize, String dealName) {
		String apkCount = apkDao.qryApksCount(dealName);
		List<?> apkList = apkDao.qryApks(indexPage, indexSize, dealName);
		return new CommonVO(true, apkList, apkCount);
	}
	
	@Override
	@Transactional(readOnly = true)
	public CommonVO qryApkById(String dealId) { 
		List<?> apkInfo = apkDao.qryApkById(dealId);
		return new CommonVO(true, apkInfo, null);
	}

	@Override
	@Transactional(readOnly = true)
	public CommonVO qryChannel(String busintypeid) {
		List<?> channels = apkDao.qryChannel(busintypeid);
		return new CommonVO(true, channels, channels.size() + "");
	}

	@Override
	@Transactional
	public void saveApkInfo(MultipartFile file, TBasisApk tBasisApk, SessionVO sessionVO) throws BusinessException, IOException {
		
		String dealId = tBasisApk.getDealId();
		String currentTime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		String rootPath = SpringProperty.props.getProperty("RootPath");
		String apkIconPath = (String) SpringProperty.props.get("ApkIconPath");
		String apkIconFullPath = (String) SpringProperty.props.get("RootPath") + apkIconPath + "/Icons";
		String oldApkIconPath = "";
//		String apkIconName = "";
//		String picSuffix = "";
		
		
		//文件存放路径不存在，则创建之
		File iconDir = new File(apkIconFullPath);
		if(!iconDir.exists()){
			iconDir.mkdirs();
		}
		
		//更新操作
		if(StringUtils.isNotBlank(tBasisApk.getDealId())){
			
			TBasisApk oldTBasisApk = apkDao.get(TBasisApk.class, tBasisApk.getDealId());
			tBasisApk.setDelFlag(oldTBasisApk.getDelFlag());
			tBasisApk.setUpdateTime(currentTime);
			tBasisApk.setUpdateUserId(sessionVO.getUserId());
			if(file.isEmpty()){
				tBasisApk.setImgLength(oldTBasisApk.getImgLength());
				tBasisApk.setImgPath(oldTBasisApk.getImgPath());
				tBasisApk.setImgUpdateTime(oldTBasisApk.getImgUpdateTime());
			} else {
				oldApkIconPath = oldTBasisApk.getImgPath();
				String picSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				String apkIconName = tBasisApk.getDealId() + picSuffix;
				tBasisApk.setImgLength(String.valueOf(file.getSize()));
				tBasisApk.setImgPath(apkIconPath + "/Icons/" + apkIconName);
				tBasisApk.setImgUpdateTime(currentTime);
			}
			try {
				BeanUtils.copyProperties(oldTBasisApk, tBasisApk);
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("500", "插入数据库失败");
			} 
			apkDao.update(oldTBasisApk);
			//新增操作
		} else {
			tBasisApk.setUpdateTime(currentTime);
			tBasisApk.setUpdateUserId(sessionVO.getUserId());
			tBasisApk.setImgLength(String.valueOf(file.getSize()));
			tBasisApk.setImgUpdateTime(currentTime);
			apkDao.save(tBasisApk);
			dealId = tBasisApk.getDealId();
			String picSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String apkIconName = dealId + picSuffix;
			tBasisApk.setImgPath(apkIconPath + "/Icons/" + apkIconName);
		}
		
		//如果有上传图片，保存图片到本地
		if(!file.isEmpty()){
			//删除旧的图标
			if(StringUtils.isNotBlank(oldApkIconPath)){
				File oldIconFile = new File(rootPath + oldApkIconPath);
				if(oldIconFile.exists())
					oldIconFile.delete();
			}
			
			String picSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String apkIconName = dealId + picSuffix;
			
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(apkIconFullPath + "/" + apkIconName);
				fos.write(file.getBytes());
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("500", "文件写入失败");
			} finally {
				fos.close();
			}
			
			String zipFilePath = rootPath + apkIconPath + File.separator + "Icons.zip";
			File zipFile = new File(zipFilePath);
			if(zipFile.exists())
			zipFile.delete();
			CompressUtil.zip(apkIconFullPath, zipFilePath, false, null);
		}
		
//		apkDao.saveApkInfo()
		
	}

	@Override
	@Transactional
	public CommonVO removeApkInfo(String dealId) {
		
		String rootPath = SpringProperty.props.getProperty("RootPath");
		String apkIconPath = (String) SpringProperty.props.get("ApkIconPath");
		
		//根据交易ID 获取交易信息
		TBasisApk tBasisApk = (TBasisApk) apkDao.get(TBasisApk.class, "dealId", dealId).get(0);
		//根据交易ID删除交易
		apkDao.removeApkInfo(dealId);
		
		//删除交易图标
		File iconFile = new File(rootPath + tBasisApk.getImgPath());
		if(iconFile.exists())
			iconFile.delete();
		
		//压缩交易图标
		String zipFilePath = rootPath + apkIconPath + File.separator + "Icons.zip";
		File zipFile = new File(zipFilePath);
		if(zipFile.exists())
		zipFile.delete();
		CompressUtil.zip(rootPath + apkIconPath + "/Icons", zipFilePath, false, null);
		
		return new CommonVO(true, "删除成功");
	}

	@Override
	@Transactional
	public CommonVO checkDealName(TBasisApk tBasisApk) {
		 // 检验交易名称是否存在
	    Long count = this.apkDao.checkDealName(tBasisApk);
	    if (0 != count) {
		   return  new CommonVO(false, "交易名称已经存在，请确认！");
	    }
	      return  new CommonVO(true,null );
	}
	
//	/**
//	 * 添加交易
//	 * 宋岩
//	 * 2015-6-06
//	 * */
//	@SuppressWarnings("deprecation")
//	public String submit(){
//		try {
//			    
//			    String dealName = logo.getDealName();
//			    Date date = new Date();
//			    
//			    // 检验交易名称是否存在
//			    Long count = this.basisTransactionService.checkDealName(dealName);
//			    if (0 != count) {
//				    msg.set("交易名称已经存在，请确认！", "basisTransactionlist.do?id=" + menuId);
//				    return "msg";
//			    }
//			
//			    long fileSize = 1024 * 1024 * 10;// 限制文件上传大小10M
//			
//			    if(myfilelogin != null)
//			    {
//				    if (myfilelogin.length() > fileSize)
//				    {
//					    msg.set("图标超过10M请重新上传", "basisTransactionlist.do?id=" + menuId);
//					    return "msg";// 应该是返回一个错误页面
//				    }
//				    
//				    // 处理图片名称唯一性
//				    String body = "";
//				    String ext = "";
//				    
//				    int pot = myfileloginFileName.lastIndexOf(".");
//				    if (pot != -1) 
//				    {
//				    	body = date.getTime() + "";
//				    	ext = myfileloginFileName.substring(pot);
//				    	
//				    } 
//				    else 
//				    {
//				    	body = (new Date()).getTime() + "";
//				    	ext = "";
//				    }
//				 //   String newName = body + ext;
//				    String newName =myfileloginFileName;
//				    String wjmc = uploadFile(myfilelogin, newName);
//				    
//				    // 保存图标消息
//				    logo.setDealTypeCode("1002");
//				    logo.setDealImgName(newName);// 交易图标名称
//				    logo.setDealImg(wjmc + newName);// 新增图标路径
//				    logo.setUserPhotoUpdatetime(new Date());// 交易图标更新时间
//				    // 保存交易消息 
//				    String useClass=getClassName(logo.getRelatedApk());
//				    logo.setPageApk(useClass);
//				    this.basisTransactionService.save(logo);
//				    
//				    String drive = ((Map)((Map) this.getApplication().getAttribute("dict")).get("D_9999")).get("6").toString();
//					//产品打包上传
//					//全部压缩成文件夹
//			        String files = wjmc;// 要压缩的文件
////			        String zipStr= "D:/srcb/tranPic/";
//			        String zipStr = drive;
//			        
//			        String[] zipStrs = zipStr.split("/");
//			        zipStr="";
//			        for (int i = 0; i < zipStrs.length; i++) {
//			        	zipStr += zipStrs[i]+"/";
//					}
//			        String ZipFile ="/opt/"+zipStr + logo.getDealId()+".zip";
//			        logger.info("************************zipStr="+zipStr+"*****files="+files);
//					// 先删除以前的压缩包文件的数据
//			        delAllFile(zipStr);
//			        // 压缩文件返回绝对路径
//			        logger.info("************************");
//			        String zipPath = CompressUtil.zip(files,ZipFile, "");
//			        logger.info("************************zipPath="+zipPath);
//			        // 获取zip包的长度
//				    File file = new File(zipPath);
//				    // 保存压缩包数据
//				    TSysZip sysZip = this.basisTransactionService.getZip();
////				    TSysZip sysZip = new TSysZip();
//				    sysZip.setZipPath(zipPath.substring(5,zipPath.length()));
//				    sysZip.setZipName(logo.getDealId() + ".zip");// zip包的名称
//				    sysZip.setZipLen(String.valueOf(file.length()));// zip包的长度
//				    sysZip.setZipUpdatetime(new Date());
//				    
//				    // 保存交易消息 
//				    this.basisTransactionService.save(sysZip);
//				    
//				    //保存日志到系统表中
//				    LogModuleOperation log = new LogModuleOperation();
//				    log.setModuleId("DEAL");
//				    log.setOperateCode(user.getUserCode());
////				    log.setOperateOrg(org);
//				    log.setOperateTime(date);
//				    log.setOperateType("新建交易");
//				    log.setOperateUser(user);
//				    this.basisTransactionService.save(log);
//				
//				    msg.set("操作成功", "basisTransactionlist.do?id=" + menuId);
//				    return "msg";
//			    } 
//			    if(myfilelogin == null)
//			    {
//				    // 保存交易消息 
//				    this.basisTransactionService.save(logo);
//				    //保存日志到系统表中
//				    LogModuleOperation log = new LogModuleOperation();
//				    log.setModuleId("DEAL");
//				    log.setOperateCode(user.getUserCode());
////				    log.setOperateOrg(org);
//				    log.setOperateTime(date);
//				    log.setOperateType("新建交易");
//				    log.setOperateUser(user);
//				    this.basisTransactionService.save(log);
//				    msg.set("操作成功", "basisTransactionlist.do?id=" + menuId);
//				    return "msg";
//			    }
//		    } catch (Exception e)
//		    {
//			    e.printStackTrace();
//		    }
//		    return "sysindex";
//	}
}
