package com.pushwin.batchwork.service.impl;

import java.io.File;
import java.io.IOException;



import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipFile;
import org.springframework.stereotype.Service;
import com.pushwin.batchwork.common.SpringProperty;

@Service("importMovePath")
public class ImportMovePath {
	
	/**
	 * 日志
	 */
	
	private static Logger logger4j = Logger.getLogger(ImportMovePath.class);
	

	public synchronized void importDictValueMany() {
		String upfilepath = SpringProperty.props.getProperty("upfilepath");
		String cpfilepath = SpringProperty.props.getProperty("cpfilepath");
		String mvfilepath = SpringProperty.props.getProperty("mvfilepath");
		File fileup = new File(upfilepath);
		File filemv = new File(mvfilepath);
		File filecp = new File(cpfilepath);
		 File[] filelist = fileup .listFiles();
		 String[] fileStrings=null;
		for (File file : filelist) {
			try {
				ZipFile zip = new ZipFile(file);
				 zip.close();
			} catch (Throwable e) {
				logger4j.info("#########file name："+file.getName());
				logger4j.info("#########ZipFile Exception："+e.getMessage());
				continue;
			}
			try {
				String fString=file.getName();
				
				FileUtils.copyFileToDirectory(file, filecp);
				FileUtils.moveToDirectory(file, filemv, true);
				
				//移动压缩包后，创建同名标示文件，告诉行方压缩包已经移动结束
				fileStrings=fString.split("\\.");
				if(fileStrings.length>0){
					fString=fileStrings[0]+".flg";
					File fileFlag=new File(filemv+"//"+fString);
					fileFlag.createNewFile();
				}
			} catch (IOException e) {
				logger4j.info("[移动文件批处理任务]移动文件发生异常：" + file.getName(), e);
				e.printStackTrace();
			}
		}
		
	}
	

}
