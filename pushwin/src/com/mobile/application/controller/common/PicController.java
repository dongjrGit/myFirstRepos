package com.mobile.application.controller.common;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobile.application.commmon.exception.BusinessException;
import com.mobile.application.commmon.util.SpringProperty;

@Controller
@RequestMapping("/pic")
public class PicController {
	
	
	/**
	 * 根据图片路径显示图片
	 * @param picPath 图片路径
	 * @param response
	 * @throws BusinessException
	 * @throws IOException
	 */
	@RequestMapping("/display")
	public void checkDealName(String picPath, HttpServletResponse response) throws BusinessException, IOException {
		OutputStream os = response.getOutputStream();
		picPath = new String(picPath.getBytes("iso-8859-1"),"utf-8");
		//picPath = URLDecoder.decode(picPath, "utf-8");
		try{
			response.reset();
			response.setContentType("image/jpeg");
			String rootPath = (String) SpringProperty.props.get("RootPath");
			File file = new File(rootPath + picPath);
			os.write(FileUtils.readFileToByteArray(file));
			os.flush();
		} finally {
			if (os != null) {  
		       os.close();  
		    }  
		}
	}
}
