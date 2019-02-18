/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * FileUtils.java
 * @author Administrator
 * @version $Id: FileUtils.java, v 0.1 2016年3月21日 上午9:23:40 Administrator Exp $
 */
public class FileUtil {

    /**
     * 文件格式
     */
    private static String[] array = { "jpg", "png", "txt" };

    /**
     * 上传
     * 
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static boolean uploadFile(MultipartFile file) throws IllegalStateException, IOException {
        if (file != null) {
            String name = file.getOriginalFilename();
            int index = name.indexOf(".");
            String type = name.substring(index + 1);
            boolean flag = false;
            for (String string : array) {
                if (type.equalsIgnoreCase(string)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("文件格式不正确!");
                return false;
            }
            Properties properties = PropertiesUtil.getProperties("cfg.properties");
            String filePath = (String) properties.get("filePath");
            File newFile = new File(filePath + name);
            if (!newFile.exists()) {
                newFile.mkdir();
            }
            file.transferTo(newFile);
        }

        return true;
    }

    /**
     * 下载
     * 
     * @param name
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> downloadFile(String name) throws IOException {
        Properties properties = PropertiesUtil.getProperties("cfg.properties");
        String filePath = (String) properties.get("filePath");
        String path = filePath + name;
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("ss.jpg".getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers,
            HttpStatus.CREATED);
    }


}
