package com.yinlian.wssc.web.util;

import javax.validation.constraints.NotNull;

import com.yinlian.wssc.search.ICriteria;

/**
 * 自定义的 查询条件的 pojo 传递条件
 * @author msl
 *  这个类就是表单或者是通过个get方式传递过来的查询参数
 *  就是数据库的字段
 */
public class Criteria implements ICriteria {
    //排序条件
    private String  orderByClause; //order by子句 如果不为空就排序 否则就不排序

    private String  sort;         //排序方式 默认是升序

    //------以下是 查询条件

    @NotNull(message = "{criteria.classid.isNUll}")
    private Integer classid;      // 商品的分类信息
    
    private Integer classid2;
    
    private Integer classid3;

    private Integer shopid;       //直营商品店铺的id

    /**
     * Getter method for property <tt>orderByClause</tt>.
     * 
     * @return property value of orderByClause
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * Setter method for property <tt>orderByClause</tt>.
     * 
     * @param orderByClause value to be assigned to property orderByClause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * Getter method for property <tt>sort</tt>.
     * 
     * @return property value of sort
     */
    public String getSort() {
        if (("").equals(sort) || sort == null) {
            sort = "ASC"; // 默认是升序
        }
        return sort;
    }

    /**
     * Setter method for property <tt>sort</tt>.
     * 
     * @param sort value to be assigned to property sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * Getter method for property <tt>classid</tt>.
     * 
     * @return property value of classid
     */
    public Integer getClassid() {
        return classid;
    }

    /**
     * Setter method for property <tt>classid</tt>.
     * 
     * @param classid value to be assigned to property classid
     */
    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    /**
     * Getter method for property <tt>shopid</tt>.
     * 
     * @return property value of shopid
     */
    public Integer getShopid() {
        return shopid;
    }

    /**
     * Setter method for property <tt>shopid</tt>.
     * 
     * @param shopid value to be assigned to property shopid
     */
    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

	public Integer getClassid2() {
		return classid2;
	}

	public void setClassid2(Integer classid2) {
		this.classid2 = classid2;
	}

	public Integer getClassid3() {
		return classid3;
	}

	public void setClassid3(Integer classid3) {
		this.classid3 = classid3;
	}
}
