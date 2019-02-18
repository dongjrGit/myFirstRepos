/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.yinlian.api.app.dto.TrackQueryApi;
import com.yinlian.api.app.dto.TrackQueryDto;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月28日 下午6:07:51 Exp $
 */
public class TrackQueryConvert {

    public static void convertDto(TrackQueryDto dto, TrackQueryApi po) {
        Assert.notNull(dto, "Source must not be null");
        Assert.notNull(po, "Target must not be null");
        po.setLogisticcode(dto.getLogisticCode());
        po.setShippercode(dto.getShipperCode());
        po.setState(dto.getState());
        po.setSuccess(dto.getSuccess());
        List<TrackQueryApi.Trace> list = new ArrayList<TrackQueryApi.Trace>();
        for (TrackQueryDto.Trace record : dto.getTraces()) {
            TrackQueryApi.Trace source = TrackQueryApi.Trace.getTrace();
            source.setAcceptstation(record.getAcceptStation());
            source.setAccepttime(record.getAcceptTime());
            list.add(source);
        }
        po.setTraces(list);
    }
}
