package com.mobile.application.service.checkcard.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.util.SpringProperty;
import com.mobile.application.dao.ICheckCardDao;
import com.mobile.application.dao.impl.BaseDAOImpl;
import com.mobile.application.entity.TBasisCheckmodle;
import com.mobile.application.service.checkcard.ICheckCardService;
import com.mobile.application.vo.CommonVO;

@Service
public class CheckCardServiceImpl extends BaseDAOImpl<TBasisCheckmodle> implements ICheckCardService {

	@Autowired
	private ICheckCardDao checkCardDao;
	
	@Override
	@Transactional
	public void exportModle(HttpServletResponse response,String modleCode) {
		try {
			List<Map<String, String>> list =  (List<Map<String, String>>) this.checkCardDao.qrymodle(modleCode);
			
		String getpath = list.get(0).get("modlePath"); //
		
            // path是指欲下载的文件的路径。
			String path =(String) SpringProperty.props.get("RootPath")+getpath; //"D:/test/22.txt";//
			File file = new File(path);
			if(list.size()==0||!file.exists()){
//				return new CommonVO(false,"不存在该模板");
			}
            
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
//            throw new BusinessException("500", "文件写入失败");
        }
//        return new CommonVO(true,"");
	}

	@Override
	@Transactional(readOnly = true) 
	public CommonVO importmodle(MultipartFile file, HttpSession session,
			HttpServletRequest request,String modleCode)throws BusinessException, IOException {
		List<Map<String, String>> list =  (List<Map<String, String>>) this.checkCardDao.qrymodle(modleCode);
		String getpath = list.get(0).get("modlePath"); //
		
        // path是指欲下载的文件的路径。
		String modlePath =(String) SpringProperty.props.get("RootPath")+getpath; //"D:/test/22.txt";
		//文件存放路径不存在，则创建之
		
		String modleParentPath = new File(modlePath).getParent();
		File modlePathDir = new File(modleParentPath);
		if(!modlePathDir.exists()){
			modlePathDir.mkdirs();
		}
//		如果有上传图片，保存图片到本地
		if(!file.isEmpty()){
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(modlePath);
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
		return new CommonVO(true,"上传成功");
	}

	@Override
	@Transactional(readOnly = true) 
	public List<?> qrymodle() {
		return checkCardDao.qrymodle(null);
	}

}
