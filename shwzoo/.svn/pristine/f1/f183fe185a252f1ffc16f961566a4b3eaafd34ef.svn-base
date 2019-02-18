package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Recommclass;
import com.yinlian.wssc.web.util.Criteria;

public interface RecommclassService {

	//删除关联分类
	int deleteByPrimaryKey(Integer id)throws Exception;

	//添加关联分类
    int insert(Recommclass record)throws Exception;

    //根据ID获取关联分类
    Recommclass selectByPrimaryKey(Integer id)throws Exception;

    //修改关联分类
    int updateByPrimaryKey(Recommclass record)throws Exception;
    
    //获取列表
    PageBean getListByPage(Criteria criteria, Integer page,Integer size)throws Exception;
    
    //修改排序
    int updateOrder(int orderby,int id)throws Exception;
    
    //批量修改排序
    int updateOrderList(List<Integer> idList,List<Integer> orderList)throws Exception;
}
