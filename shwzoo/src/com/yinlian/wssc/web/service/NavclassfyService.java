package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.po.Navclassfy;

/**
 * 文章类目的业务类
 * NavclassfyService.java
 * @author Administrator
 * @version $Id: NavclassfyService.java, v 0.1 2016年5月18日 下午4:09:58 Administrator Exp $
 */
public interface NavclassfyService {
	/**
	 * 获取文章类目一级分类
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Navclassfy> queryfirstClass() throws Exception;
	/**
	 * 根据首项获取分类子项
	 * @return
	 * @throws Exception
	 */
	List<Navclassfy> querychildbyfid(Integer parentid) throws Exception;
	/**
	 * 查询分类
	 * 
	 * @return
	 * @throws Exception
	 */
	List<NavclassfyDto> queryClassTree(Integer parentid) throws Exception;
	List<NavclassfyDto> selectByPIdType(Integer parentid,Integer type) throws Exception;
	
	/**
	 *根据id删除类型 
	 * @param toInt
	 * @return
	 * @throws Exception
	 */
	Integer delNavClassById(Integer id) throws Exception;
	/**
	 * 根据id获取分类表单
	 * 
	 * @param toInt
	 * @return
	 * @throws Exception
	 */
	Navclassfy queryArticleClassById(Integer id) throws Exception;
	/**
	 * 添加分类
	 * 
	 * @param navclassfy
	 * @return
	 * @throws Exception
	 */
	Integer addNavClass(Navclassfy navclassfy) throws Exception;
	/**
	 * 修改分类
	 * 
	 * @param navclassfy
	 * @return
	 * @throws Exception
	 */
	Integer editNavClass(Navclassfy navclassfy) throws Exception;
	/**
	 * 根据子类id查询父类id
	 * @param toInt
	 * @return
	 * @throws Exception
	 */
	Navclassfy getFirstId(Integer childid) throws Exception;
	
	List<Navclassfy> queryfirstClassbyPart(Integer partid) throws Exception;
	
	/**
	 * 获取一级分类
	 * 
	 * @author kh.wang
	 * @since 2016年8月14日
	 * @return
	 */
	List<NavclassfyDto> queryStair();
	
	/**
	 * 获取分类所有子集
	 * 
	 * @author kh.wang
	 * @since 2016年8月14日
	 * @param id
	 * @return
	 */
	List<NavclassfyDto> querySon(Integer id);
	
	Navclassfy queryById(Integer id);
	
	List<Navclassfy> queryAll();
	
	List<NavclassfyDto> queryRelevance(int value, int value2);
	
	List<Navclassfy> queryById(String[] ids);
}
