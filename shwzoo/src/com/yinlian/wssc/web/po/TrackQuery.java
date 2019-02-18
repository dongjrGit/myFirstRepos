/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.po;

import com.yinlian.api.app.dto.TrackQueryApi;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月28日 下午6:04:25 Exp $
 */
public class TrackQuery extends TrackQueryApi {

    private String name; //物流公司的名字

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
