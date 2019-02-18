package com.pushwin.batchwork.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


/**
 *  文件移动备份
 * @author admin
 *
 */

public class FileMv {
	
	private static Logger logger4j = Logger.getLogger(FileMv.class);
	/**
	 * 移动文件到指定目录
	 * @param filemv 移动到指定木木
	 * @param file  移动的文件
	 */
	public static void mvFileToDir(String filemv,File file){
		String time =  DateUtil.batchTime() ;
		File filemvdir = new File(filemv+"/" + time);
		if(!filemvdir.exists()){
			filemvdir.mkdir();
		}
		try {
			FileUtils.moveToDirectory(file, filemvdir, true);
		} catch (IOException e) {
			logger4j.error("文件移动失败" + file.getName());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		File file = new File("D:\\mywork\\NJBANK\\njcb\\20160301\\PlanType20160408.txt");
		String filemv = "D:\\mywork\\NJBANK\\njcb\\20160301\\";
		mvFileToDir(filemv,file);
	}
}
