/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.dto;

import java.util.List;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月28日 下午6:04:25 Exp $
 */
public class TrackQueryApi {

    private String      shippercode;
    private String      logisticcode;
    private String      success;
    private Integer     state;
    private List<Trace> traces;

    public static class Trace {
        public String accepttime;
        public String acceptstation;

        public static Trace getTrace() {
            Trace trace = new Trace();
            return trace;
        }

        public String getAccepttime() {
            return accepttime;
        }

        public void setAccepttime(String accepttime) {
            this.accepttime = accepttime;
        }

        public String getAcceptstation() {
            return acceptstation;
        }

        public void setAcceptstation(String acceptstation) {
            this.acceptstation = acceptstation;
        }

    }

    public String getShippercode() {
        return shippercode;
    }

    public void setShippercode(String shippercode) {
        this.shippercode = shippercode;
    }

    public String getLogisticcode() {
        return logisticcode;
    }

    public void setLogisticcode(String logisticcode) {
        this.logisticcode = logisticcode;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }

}
