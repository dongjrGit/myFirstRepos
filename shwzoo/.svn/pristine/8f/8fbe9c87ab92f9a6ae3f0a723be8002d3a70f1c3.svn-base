package com.yinlian.wssc.web.interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页参数类
 * 
 */
public class PageBean {

    public static final Integer DEFAULT_PAGE_SIZE = 10; //默认的ps 每页记录数

    private Integer             ps;                    //每页记录数
    private Integer             pc;                    //当前页
    private Integer             prePage;               //上一页
    private Integer             nextPage;              //下一页
    private Integer             tp;                    //总页数
    private Integer             tr;                    //总记录数
    private String              url;                   //页面需要的 超链接  如果在页面通过其他方法 实现这里就可以不用赋值。
    private List<?>             beanList;
    private Object              data;

    /**
     * 对于sqlserver 2005 排序在拦截器中 生成sql语句
     * 而对于 oracel 和mysql sql2000不影响
     */
    private String              iSortCol;              //排序索引
    private String              sSortDir;              //排序默认asc

    public PageBean() {
        this.pc = 1;
        this.ps = DEFAULT_PAGE_SIZE;
        beanList = new ArrayList<>();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 自定义的ps的page
     * @param currentPage
     * @param pageSize
     */
    public PageBean(Integer pc, Integer ps) {
        if (pc != null && pc != 0) {
            this.pc = pc;
        } else {
            this.pc = 1;
        }
        if (ps != null) {
            this.ps = ps;
        } else {
            this.ps = DEFAULT_PAGE_SIZE;
        }
        beanList = new ArrayList<>();
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }

    public Integer getPc() {
        return pc;
    }

    public void setPc(Integer pc) {
        this.pc = pc;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getTp() {
        int totalPage = tr / ps;
        tp = (tr % ps == 0 ? totalPage : totalPage + 1);
        return tp;
    }

    public void setTp(Integer tp) {
        this.tp = tp;
    }

    public Integer getTr() {
        return tr;
    }

    public void setTr(Integer tr) {
        this.tr = tr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<?> beanList) {
        this.beanList = beanList;
    }

    public String getiSortCol() {
        return iSortCol;
    }

    public void setiSortCol(String iSortCol) {
        this.iSortCol = iSortCol;
    }

    //-----------sqlserver 2005排序的字段
    public String getsSortDir() {
        return sSortDir;
    }

    public void setsSortDir(String sSortDir) {
        this.sSortDir = sSortDir;
    }

}
