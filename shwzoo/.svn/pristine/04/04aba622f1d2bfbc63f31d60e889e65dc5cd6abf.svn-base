package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.RecommendClassDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.RecommclassMapper;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.Recommclass;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.service.RecommclassService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("recommclassService")
public class RecommclassServiceImpl implements RecommclassService {

	@Autowired
	private RecommclassMapper recommclassMapper;
	
	@Autowired
	private CategoryService categoryService;
	//删除关联分类
	public int deleteByPrimaryKey(Integer id)throws Exception{
		return recommclassMapper.deleteByPrimaryKey(id);
	}

		//根据ID获取关联分类
	public Recommclass selectByPrimaryKey(Integer id)throws Exception{
		return recommclassMapper.selectByPrimaryKey(id);
	}

	    //添加关联分类
	public int insert(Recommclass record)throws Exception{
		return recommclassMapper.insert(record);
	}

	//修改关联分类
	public int updateByPrimaryKey(Recommclass record)throws Exception{
		return recommclassMapper.updateByPrimaryKey(record);
	}
	    
	    //获取列表
	public PageBean getListByPage(Criteria criteria,Integer page,Integer size)throws Exception{
		PageBeanUtil pBeanUtil=new PageBeanUtil(criteria,page,size);
		PageBean pBean=pBeanUtil.getPage();
		List<RecommendClassDto> list=recommclassMapper.getListByPage(pBeanUtil);
		Category category=new Category();
		for (RecommendClassDto Dto : list) {
			category=categoryService.selectByPrimaryKey(Dto.getClassid());
			Dto.setClassname(category.getName());
			Dto.setClasspathname(categoryService.GetFullNamePath(category.getFullpath()));
		}
		
		pBean.setBeanList(list);
		
		return pBean;
	}
	    
	    //修改排序
	public int updateOrder(int orderby,int id)throws Exception{
		return recommclassMapper.updateOrder(orderby, id);
	}
	    
	    //批量修改排序
	public int updateOrderList(List<Integer> idList,List<Integer> orderList)throws Exception{
		List<Recommclass> list=new ArrayList<Recommclass>();
		Recommclass rc=null;
		for(int i=0;i<idList.size();i++){
			rc=new Recommclass();
			rc.setId(idList.get(i));
			rc.setOrderby(orderList.get(i));
			list.add(rc);
		}		
		return recommclassMapper.updateOrderList(list);
	}
}
