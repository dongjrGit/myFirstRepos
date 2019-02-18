package com.yinlian.wssc.web.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 获取配置信息类
 * ReadFileUrlUtil.java
 * @author Administrator
 * @version $Id: ReadFileUrlUtil.java, v 0.1 2016年3月14日 下午6:00:43 Administrator Exp $
 */
public class ReadFileUrlUtil {
    public String fileUrl() {
        String path = "";
        try {
            Properties pro = new Properties();
            InputStream inStream = this.getClass().getClassLoader()
                .getResourceAsStream("cfg.properties");
            pro.load(inStream);
            path = pro.getProperty("image_src");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }

    /**
     * 获取某个配置文件里面某个key的value
     * 
     * @param name 对应的是classpath下的properties文件
     * @param key  里面的key
     * @return
     */
    public String fileUrl(String name, String key) {
        String path = "";
        try {
            Properties pro = new Properties();
            InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(name);
            pro.load(inStream);
            path = pro.getProperty(key);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }

}
