/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.config;

/**
 * 分页查询的条件类
 * @author Administrator
 * @version $Id: Criteria.java, v 0.1 2016年2月29日 上午10:16:57 Administrator Exp $
 */
public class Criteria {

    private int ps = 10;
    private int pc = 1;
    private int offer;
    private int tp;
    private int tr;

    /**
     * Getter method for property <tt>ps</tt>.
     * 
     * @return property value of ps
     */
    public int getPs() {
        return ps;
    }

    /**
     * Setter method for property <tt>ps</tt>.
     * 
     * @param ps value to be assigned to property ps
     */
    public void setPs(int ps) {
        this.ps = ps;
    }

    /**
     * Getter method for property <tt>pc</tt>.
     * 
     * @return property value of pc
     */
    public int getPc() {

        return pc;
    }

    /**
     * Setter method for property <tt>pc</tt>.
     * 
     * @param pc value to be assigned to property pc
     */
    public void setPc(int pc) {
        this.pc = pc;
    }

    /**
     * Getter method for property <tt>offer</tt>.
     * 
     * @return property value of offer
     */
    public int getOffer() {
        offer = (pc - 1) * ps;
        return offer;
    }

    /**
     * Setter method for property <tt>offer</tt>.
     * 
     * @param offer value to be assigned to property offer
     */
    public void setOffer(int offer) {
        this.offer = offer;
    }

    /**
     * Getter method for property <tt>tp</tt>.
     * 
     * @return property value of tp
     */
    public int getTp() {
        int totalPage = tr / ps;
        tp = (tr % ps == 0 ? totalPage : totalPage + 1);
        return tp;
    }

    /**
     * Setter method for property <tt>tp</tt>.
     * 
     * @param tp value to be assigned to property tp
     */
    public void setTp(int tp) {
        this.tp = tp;
    }

    /**
     * Getter method for property <tt>tr</tt>.
     * 
     * @return property value of tr
     */
    public int getTr() {
        return tr;
    }

    /**
     * Setter method for property <tt>tr</tt>.
     * 
     * @param tr value to be assigned to property tr
     */
    public void setTr(int tr) {
        this.tr = tr;
    }

}
