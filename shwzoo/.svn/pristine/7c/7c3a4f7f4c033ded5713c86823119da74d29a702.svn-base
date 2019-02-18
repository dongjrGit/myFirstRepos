/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import java.io.InputStream;
import java.util.List;

import com.yinlian.wssc.web.po.Users;

/**
 * XmlParser.java
 * @author Administrator
 * @version $Id: XmlParser.java, v 0.1 2016年3月31日 下午2:23:47 Administrator Exp $
 */
public interface XmlParser {

    /**
     * 反序列化输入流 得到集合
     * 
     * @param is
     * @return
     * @throws Exception
     */
    public List<Users> parse(InputStream is) throws Exception;

    /**
     * 序列化集合得到xml形式字符串
     * 
     * @param list
     * @return
     * @throws Exception
     */
    public String serialize(List<Users> list) throws Exception;
}
