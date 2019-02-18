package com.yinlian.wssc.web.util;

import com.yinlian.wssc.search.ICriteria;
import com.yinlian.wssc.web.interceptor.PageBean;

/**
 * 分页工具类  传入拦截器中的
 * @author msl
 *
 */
public class PageBeanUtil {

    private PageBean  page;    //分页信息的类

    private ICriteria criteria; //表达式 存放 条件查询的 字段属性 多条件查询

    public PageBeanUtil() {
        this.page = new PageBean();
    }

    /**
     * Criteria 类型的构造器
     * @param criteria
     * @param pc
     * @param ps
     */
    public PageBeanUtil(Criteria criteria, Integer pc, Integer ps) {
        this.page = new PageBean(pc, ps);
        if (criteria != null) {
            this.criteria = criteria;
        }
    }

    /**
     * ICriteria 接口方式的构造器
     * @param criteria
     * @param pc
     * @param ps
     */
    public PageBeanUtil(ICriteria criteria, Integer pc, Integer ps) {
        this.page = new PageBean(pc, ps);
        if (criteria != null) {
            this.criteria = criteria;
        }
    }

    public PageBeanUtil(Integer pc, Integer ps) {
        this.page = new PageBean(pc, ps);
    }

    /**
     * Getter method for property <tt>page</tt>.
     * 
     * @return property value of page
     */
    public PageBean getPage() {
        return page;
    }

    /**
     * Setter method for property <tt>page</tt>.
     * 
     * @param page value to be assigned to property page
     */
    public void setPage(PageBean page) {
        this.page = page;
    }

    /**
     * Getter method for property <tt>criteria</tt>.
     * 
     * @return property value of criteria
     */
    public ICriteria getCriteria() {
        return criteria;
    }

    /**
     * Setter method for property <tt>criteria</tt>.
     * 
     * @param criteria value to be assigned to property criteria
     */
    public void setCriteria(ICriteria criteria) {
        this.criteria = criteria;
    }

}
