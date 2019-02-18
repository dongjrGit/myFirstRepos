package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.mapper.ArticlePartMapper;
import com.yinlian.wssc.web.mapper.NavclassfyMapper;
import com.yinlian.wssc.web.po.ArticlePart;
import com.yinlian.wssc.web.po.Navclassfy;
import com.yinlian.wssc.web.po.NavclassfyExample;
import com.yinlian.wssc.web.service.ClassfyArticleService;
import com.yinlian.wssc.web.service.NavclassfyService;
/**
 * 文章类目的实现类
 * NavclassfyServiceImpl.java
 * @author Administrator
 * @version $Id: NavclassfyServiceImpl.java, v 0.1 2016年5月18日 下午4:13:22 Administrator Exp $
 */
@Component("navclassfyService")
public class NavclassfyServiceImpl implements NavclassfyService {
	/**
	 * 获取文章类目一级分类
	 * @see com.yinlian.wssc.web.service.NavclassfyService#queryfirstClass()
	 */
	@Autowired
	private NavclassfyMapper navclassfyMapper;
	@Autowired
	private ArticlePartMapper articlePartMapper; 
	@Autowired
	private ClassfyArticleService classfyArticleService;
	@Override
	public List<Navclassfy> queryfirstClass() throws Exception {
		NavclassfyExample example = new NavclassfyExample();
		NavclassfyExample.Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(0);
		return navclassfyMapper.selectByExample(example);
	}
	/**
	 * 根据首项获取分类子项
	 * @see com.yinlian.wssc.web.service.NavclassfyService#querychildbyfid(java.lang.Integer)
	 */
	@Override
	public List<Navclassfy> querychildbyfid(Integer parentid) throws Exception {
		NavclassfyExample example = new NavclassfyExample();
		NavclassfyExample.Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(parentid);
		return navclassfyMapper.selectByExample(example);
	}
	
	private List<NavclassfyDto> totalList;
	/**
	 * 查询分类信息
	 * @see com.yinlian.wssc.web.service.NavclassfyService#queryClassTree()
	 */
	@Override
	public List<NavclassfyDto> queryClassTree(Integer parentid) throws Exception {
		totalList = navclassfyMapper.selectTotal();
		List<NavclassfyDto> list=totalList.stream().filter(n->n.getParentid()==parentid).collect(Collectors.toList());
		querySonLoad(list);
		return list;
	}
	private void querySonLoad(List<NavclassfyDto> list) throws Exception{
		for (NavclassfyDto navclassfyDto : list) {
					navclassfyDto.setList(queryTree(navclassfyDto.getId()));
		}
	}
	
	private List<NavclassfyDto> queryTree(Integer parentid) throws Exception{
		List<NavclassfyDto> dto=totalList.stream().filter(t->t.getParentid()==parentid).collect(Collectors.toList());
		querySonLoad(dto);
		return dto;
	}
	
	
	/**
	 * 根据分类类型查询分类信息
	 * 
	 */
	@Override
	public List<NavclassfyDto> selectByPIdType(Integer parentid ,Integer type) throws Exception {
		List<NavclassfyDto> list = new ArrayList<NavclassfyDto>();
		list = navclassfyMapper.selectByPIdType(parentid, type);
		for (NavclassfyDto navclassfyDto : list) {
			if (navclassfyDto != null) {
				List<NavclassfyDto> list2 = selectByPIdType(navclassfyDto.getId(),type);
				navclassfyDto.setList(list2);
			}
		}
		return list;
	}
	/**
	 * 根据id删除类型
	 * @see com.yinlian.wssc.web.service.NavclassfyService#delNavClassById(java.lang.Integer)
	 */
	@Override
	public Integer delNavClassById(Integer id) throws Exception {
		
		return navclassfyMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 根据id获取分类表单
	 * @see com.yinlian.wssc.web.service.NavclassfyService#queryArticleClassById(java.lang.Integer)
	 */
	@Override
	public Navclassfy queryArticleClassById(Integer id) throws Exception {
		
		return navclassfyMapper.selectByPrimaryKey(id);
	}
	/**
	 * 添加分类
	 * @see com.yinlian.wssc.web.service.NavclassfyService#addNavClass(com.yinlian.wssc.web.po.Navclassfy)
	 */
	@Override
	public Integer addNavClass(Navclassfy navclassfy) throws Exception {
		
		return navclassfyMapper.insert(navclassfy);
	}
	/**
	 * 修改分类
	 * @see com.yinlian.wssc.web.service.NavclassfyService#editNavClass(com.yinlian.wssc.web.po.Navclassfy)
	 */
	@Override
	public Integer editNavClass(Navclassfy navclassfy) throws Exception {
		// TODO Auto-generated method stub
		return navclassfyMapper.updateByPrimaryKey(navclassfy);
	}
	/**
	 * 根据子类id查询父类id
	 * @see com.yinlian.wssc.web.service.NavclassfyService#getFirstId(java.lang.Integer)
	 */
	@Override
	public Navclassfy getFirstId(Integer childid) throws Exception {
		
		return navclassfyMapper.selectByPrimaryKey(childid);
	}
	@Override
	public List<Navclassfy> queryfirstClassbyPart(Integer partid) throws Exception {
		NavclassfyExample example = new NavclassfyExample();
		NavclassfyExample.Criteria criteria = example.createCriteria();
		criteria.andParentidEqualTo(0);
		criteria.andNavtypeEqualTo(partid);
		return navclassfyMapper.selectByExample(example);
	}
	@Override
	public List<NavclassfyDto> queryStair() {
		return navclassfyMapper.selectByParentId(0);
	}
	@Override
	public List<NavclassfyDto> querySon(Integer id) {
		List<NavclassfyDto> nav=navclassfyMapper.selectByParentId(id);
		/*for (NavclassfyDto navclassfyDto : nav) {
			navclassfyDto.setArticles(classfyArticleService.queryByClassfyId(navclassfyDto.getId()));
		}*/
		return nav;
	}
	@Override
	public Navclassfy queryById(Integer id) {
		Navclassfy navs=navclassfyMapper.selectByPrimaryKey(id);
		return navs;
	}
	@Override
	public List<Navclassfy> queryAll() {
		return navclassfyMapper.selectAll();
	}
	@Override
	public List<NavclassfyDto> queryRelevance(int value, int value2) {
		return navclassfyMapper.queryRelevance(value,value2);
	}
	
	@Override
	public List<Navclassfy> queryById(String[] ids) {
		return navclassfyMapper.queryById(ids);
	}

}
