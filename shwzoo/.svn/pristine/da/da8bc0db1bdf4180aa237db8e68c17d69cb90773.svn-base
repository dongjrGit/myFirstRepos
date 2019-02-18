package com.yl.soft.uitl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {
	 /**
     * 获取
     * 
     * @return
     * @throws IOException
     */
    public static Properties getProperties(String propertyname) throws IOException {
        Properties pro = new Properties();
        InputStream inStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(
            propertyname);
        pro.load(inStream);

        return pro;
    }
}
