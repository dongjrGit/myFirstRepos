package com.mobile.application.service.sys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.ISysDao;
import com.mobile.application.entity.TBasisSystem;

@Service
public class SysServiceImpl implements ISysService{
	
	@Autowired
	private ISysDao sysDao;
	
	@Override
	@Transactional
	public Map<String, String> qrySysSetting(){
		return (Map<String, String>) sysDao.qrySysSetting().get(0);
	}
	
	@Override
	@Transactional
	public void saveSysSetting(MultipartFile file, TBasisSystem tBasisSystem) throws BusinessException, IOException{
		String rootPath = (String) SpringProperty.props.get("RootPath");
		String sysLogoPath = (String) SpringProperty.props.get("SysLogoPath");
		
		//文件存放路径不存在，则创建之
		File sysLogoFile = new File(rootPath + sysLogoPath);
		if(!sysLogoFile.getParentFile().exists()){
			sysLogoFile.getParentFile().mkdirs();
		}
		//如果有上传图片，保存图片到本地
		if(!file.isEmpty()){
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(sysLogoFile);
				fos.write(file.getBytes());
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("500", "文件写入失败");
			} finally {
				fos.close();
			}
		}
		
		tBasisSystem.setLogoImg(sysLogoPath);
		
		if(StringUtils.isBlank(tBasisSystem.getId())){
			sysDao.save(tBasisSystem);
		} else {
			sysDao.update(tBasisSystem);
		}
	}
}
