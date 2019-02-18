/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesUtil.java
 * @author Administrator
 * @version $Id: PropertiesUtil.java, v 0.1 2016年3月21日 上午9:21:20 Administrator Exp $
 */
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
