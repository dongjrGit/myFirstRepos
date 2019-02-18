/*
 * yinlian.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.api.app.dto;

import java.util.List;

/**
 * 
 * @authorn mashao
 * @version $Id:v 0.1 2016年4月28日 下午3:55:50 Exp $
 */
public class TrackQueryDto {

    private String      ShipperCode;
    private String      LogisticCode;
    private String      Success;
    private Integer     State;
    private List<Trace> Traces;

    public class Trace {
        public String AcceptTime;
        public String AcceptStation;

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String acceptTime) {
            AcceptTime = acceptTime;
        }

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String acceptStation) {
            AcceptStation = acceptStation;
        }

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Trace [AcceptTime=" + AcceptTime + ", AcceptStation=" + AcceptStation + "]";
        }

    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public List<Trace> getTraces() {
        return Traces;
    }

    public void setTraces(List<Trace> traces) {
        Traces = traces;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TrackQueryDto [ShipperCode=" + ShipperCode + ", LogisticCode=" + LogisticCode
               + ", Success=" + Success + ", State=" + State + ", Traces=" + Traces + "]";
    }

}
