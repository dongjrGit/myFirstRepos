package com.yl.soft.seach;

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

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
    
}
