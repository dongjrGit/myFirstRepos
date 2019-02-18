package com.yinlian.wssc.web.mapper;

import java.util.List;

import com.yinlian.wssc.web.po.Specstype;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface SpecstypeMapperCustom {

    int updateStatus(Integer status,Integer id);
    
    int updateOrder(Integer orderby,Integer id);
    
    List<Specstype> getByClassID(Integer classid);
    
    Specstype getByID(Integer id);
    
    int updateOrderList(List<Specstype> list);
    
    List<Specstype> selectSpecsPage(PageBeanUtil pageBeanUtil);

    List<Specstype> selectSonPage(PageBeanUtil pageBeanUtil);
    
    int deleteList(List<Integer> list);

	List<Specstype> getBySuperiorClassID(Criteria criteria);
}
