/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.config;

import java.util.Map;

import org.springframework.util.AntPathMatcher;

/**
 * 
 * @author Administrator
 * @version $Id: CaseInsensitivePathMatcher.java, v 0.1 2016年3月1日 下午12:10:57 Administrator Exp $
 */
public class CaseInsensitivePathMatcher extends AntPathMatcher {

    /** 
     * @see org.springframework.util.AntPathMatcher#doMatch(java.lang.String, java.lang.String, boolean, java.util.Map)
     */
    @Override
    protected boolean doMatch(String arg0, String arg1, boolean arg2, Map<String, String> arg3) {
        return super.doMatch(arg0.toLowerCase(), arg1.toLowerCase(), arg2, arg3);
    }

}
