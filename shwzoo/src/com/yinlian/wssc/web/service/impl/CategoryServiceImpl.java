/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.api.app.dto.ClassDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.CategoryDTO;
import com.yinlian.wssc.web.dto.TopicRelateInfo;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.CategoryMapper;
import com.yinlian.wssc.web.mapper.CategoryMapperCustom;
import com.yinlian.wssc.web.mapper.ProductSpecsMapper;
import com.yinlian.wssc.web.mapper.ShopClassMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.po.Category;
import com.yinlian.wssc.web.po.ProductSpecs;
import com.yinlian.wssc.web.po.ShopClass;
import com.yinlian.wssc.web.po.ShopClassExample;
import com.yinlian.wssc.web.po.SpuExample;
import com.yinlian.wssc.web.po.SpuWithBLOBs;
import com.yinlian.wssc.web.service.CategoryService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 分类的业务类
 * 
 * @author Administrator
 * @version $Id: CategoryServiceImpl.java, v 0.1 2016年2月26日 下午3:17:47
 *          Administrator Exp $
 */
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private CategoryMapperCustom categoryMapperCustom;

	@Autowired
	private ProductSpecsMapper productSpecsMapper;
	@Autowired
	private ShopClassMapper shopClassMapper;

	@Autowired
	private SpuMapper spuMapper;

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	/*
	*//**
	 * @see com.yinlian.wssc.web.service.CategoryService#selectAllCategorys()
	 *//*
	*//**
	 * 获取平台分类
	 *//*
	@Override
	public List<CategoryDTO> selectAllCategorys(Integer fatherid) throws Exception {
		// 得到所有的一级分类
		return selectAll(fatherid);
	}*/
	
	@Override
	public List<CategoryDTO> selectAll(int fatherid) throws Exception {
		List<CategoryDTO> list = categoryMapperCustom.selectAll(fatherid);
		List<CategoryDTO> res = new ArrayList<CategoryDTO>();
		if (list != null && list.size() > 0) {
			List<CategoryDTO> list1 = list.stream().filter(x->x.getFatherid() == 0).collect(Collectors.toList());
			for (CategoryDTO categoryDTO : list1) {
				if (categoryDTO != null) {
					List<CategoryDTO> list2 = list.stream().filter(x->x.getFatherid().equals(categoryDTO.getId())).collect(Collectors.toList());
					for (CategoryDTO categoryDTO2 : list2) {
						List<CategoryDTO> list3 = list.stream().filter(x->x.getFatherid().equals(categoryDTO2.getId())).collect(Collectors.toList());
						categoryDTO2.setChildren(list3);
						categoryDTO2.setImageUrl();
					}
					categoryDTO.setChildren(list2);
					categoryDTO.setImageUrl();
				}
				res.add(categoryDTO);
			}
		}
		return res;
	}
	
/*这种循环递归里面查库的垃圾代码不要再写出来了！
 * 	private List<CategoryDTO> queryCategory(Integer fatherid) throws Exception {
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		list = categoryMapperCustom.selectAllCategorys(fatherid);
		if (list != null && list.size() > 0) {
			for (CategoryDTO categoryDTO : list) {
				if (categoryDTO != null) {
					List<CategoryDTO> list2 = queryCategory(categoryDTO.getId());
					categoryDTO.setChildren(list2);
					categoryDTO.setImageUrl();
				}
			}
		}
		return list;
	}*/

	/**
	 * @see com.yinlian.wssc.web.service.CategoryService#queryById(java.lang.Integer)
	 */
	@Override
	public CategoryDTO queryById(Integer id) throws Exception {
		if (id == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException("The parameter id is null!");
			}
		}

		CategoryDTO dto = new CategoryDTO();

		Category category = categoryMapper.selectByPrimaryKey(id);
		if (category != null) {
			BeanUtils.copyProperties(category, dto);
			// int fatherid = category.getFatherid();

		}

		return dto;
	}

	/**
	 * @see com.yinlian.wssc.web.service.CategoryService#updateCategory(com.yinlian.wssc.web.po.Category)
	 */
	@Override
	public int updateCategory(Category category) throws Exception {
		if (category == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException("The parameter category is null!");
			}
		}
		return categoryMapper.updateByPrimaryKey(category);
	}

	/**
	 * @see com.yinlian.wssc.web.service.CategoryService#deletCatefory(java.lang.Integer)
	 */
	@Override
	public int deletCatefory(Integer id) throws Exception {
		CategoryDTO dto = queryById(id);
		for (CategoryDTO categoryDTO : dto.getChildren()) {
			deletCatefory(categoryDTO.getId());
		}
		dto.setIsdel(true);
		return categoryMapper.updateByPrimaryKey(dto);
		// categoryMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @see com.yinlian.wssc.web.service.CategoryService#seletcProductSpecsByClassId(java.lang.Integer)
	 */
	@Override
	public PageBean seletcProductSpecsPageByClassId(Criteria criteria, Integer pc, Integer ps) throws Exception {
		if (criteria == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException("The parameter Criteria is null!");
			}
		}

		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<ProductSpecs> beanList = productSpecsMapper.selectProductSpecsByClassIdPage(pageBeanUtil);
		for (ProductSpecs s : beanList) {
			if (s.getClassFullpath()!= null) {
				s.setClassFullName(GetFullNamePath(s.getClassFullpath()));

			}
		}
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<CategoryDTO> getByFatherID(Integer fatherid) throws Exception {
		List<CategoryDTO> list = categoryMapperCustom.selectAllCategorys(fatherid);
		return list;
	}

	public List<CategoryDTO> getBySonID(Integer fatherid) throws Exception {
		List<CategoryDTO> list = categoryMapperCustom.selectSonCategorys(fatherid);
		return list;
	}

	public int addCategory(Category category) throws Exception {
		int returns = categoryMapperCustom.addCategory(category);
		Category categorys = new Category();
		if (returns > 0) {
			Integer classid = category.getId();
			categorys.setId(classid);
			if (category.getFatherid() == 0) {
				categorys.setFullpath(classid.toString());
				categorys.setSearchpath("," + classid.toString());
			} else {
				categorys.setFullpath(category.getFullpath() + "," + classid);
				categorys.setSearchpath(category.getSearchpath() + classid + ",");
			}

			returns = categoryMapperCustom.updateFullpath(categorys);
		}
		return returns;
	}

	/**
	 * 删除分类
	 */
	public int delCategory(Integer id, Integer userid, ReusltItem item) throws Exception {
		Category category = categoryMapper.selectByPrimaryKey(id);
		if (category == null) {
			item.setDesc("根据分类ID未能获取到数据，分类ID：" + id);
			return 0;
		}
		ShopClassExample example = new ShopClassExample();
		SpuExample spuExample = new SpuExample();
		if (category.getClasslever() == 3) {
			// 判断卖家是否关联经营范围
			ShopClassExample.Criteria criteria = example.createCriteria();
			criteria.andClassfullpathEqualTo(category.getFullpath());
			List<ShopClass> sc = shopClassMapper.selectByExample(example);
			if (sc != null && sc.size() > 0) {
				item.setDesc("分类已关联到商家经营范围，不能删除");
				return 0;
			}
			SpuExample.Criteria criteria2 = spuExample.createCriteria();
			criteria2.andIsdelEqualTo(false);
			criteria2.andClassidEqualTo(category.getId());
			List<SpuWithBLOBs> spulist = spuMapper.selectByExampleWithBLOBs(spuExample);
			if (spulist != null && spulist.size() > 0) {
				item.setDesc("分类下已添加商品，不能删除");
				return 0;
			}
		} else {
			List<Category> cList = categoryMapper.getbyFullpath(category.getFullpath());
			if (cList != null && cList.size() > 0) {
				item.setDesc("分类下已添加子分类不能删除");
				return 0;
			}
		}
		Date now = new Date();
		category.setIsdel(true);
		category.setDeluserid(userid);
		category.setDeltime(now);
		return categoryMapperCustom.delCategory(category);

	}

	public Category selectByPrimaryKey(Integer id) throws Exception {
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public String GetFullNamePath(String ids) throws Exception {
		String[] s = ids.split(",");
		String rsl = "";
		for (String id : s) {
			Category cate = categoryMapper.selectByPrimaryKey(Integer.parseInt(id));
			if (cate != null) {
				if (rsl == "")
					rsl = rsl.concat(cate.getName());
				else
					rsl = rsl.concat("->" + cate.getName());
			}
		}
		return rsl;
	}

	/**
	 * 根据分类和店铺ID获取自定义分类
	 * 
	 * @param classid
	 * @param shopid
	 * @return
	 * @throws Exception
	 */
	public List<CategoryDTO> getByClassShop(Integer classid, Integer shopid) throws Exception {

		return categoryMapperCustom.getByClassShop(classid, shopid);
	}

	/**
	 * 根据店铺ID获取自定义分类（分页）
	 * 
	 * @param pageBeanUtil
	 * @return
	 * @throws Exception
	 */
	public PageBean getClassByShopPage(Criteria a, Integer page, Integer size) throws Exception {

		PageBeanUtil pageBeanUtil = new PageBeanUtil(a, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<CategoryDTO> beanList = categoryMapperCustom.getClassByShopPage(pageBeanUtil);
		for (CategoryDTO dto : beanList) {
			String fullp = dto.getFullpath();
			if (dto.getFullpath().length() > 5)
				fullp = dto.getFullpath().substring(0, 5);
			dto.setFullname(GetFullNamePath(fullp));
		}
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * 获取店铺分类
	 * 
	 * @param fatherid
	 * @param shopid
	 * @return
	 * @throws Exception
	 */
	public List<CategoryDTO> selectCategoryByShop(Integer fatherid, Integer shopid) throws Exception {
		// 得到所有的一级分类
		return queryCategoryShop(fatherid, shopid);
	}

	private List<CategoryDTO> queryCategoryShop(Integer fatherid, Integer shopid) throws Exception {
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		list = categoryMapperCustom.selectCategoryByShop(shopid);

		if (list != null && list.size() > 0) {
			for (CategoryDTO categoryDTO : list) {
				if (categoryDTO != null) {
					List<CategoryDTO> list2 = selectAll(categoryDTO.getId());
					categoryDTO.setChildren(list2);
				}
			}
		}
		return list;
	}

	private List<CategoryDTO> getList(List<CategoryDTO> dtolist, Integer fatherid) throws Exception {
		List<CategoryDTO> listdto=new ArrayList<>();
		List<String> ids1=new ArrayList<>();//只选择到一级营销范围
		List<String> ids2=new ArrayList<>();//只选择到二级营销范围
		List<String> ids3=new ArrayList<>();//只选择到三级营销范围
		for (CategoryDTO item : dtolist) {
			String[] ids = item.getFullpath().split(",");
			if (ids.length == 1) {
				ids1.add(item.getFullpath());
			}else if (ids.length ==2) {
				ids2.add(item.getFullpath());
			}else if (ids.length == 3) {
				ids3.add(item.getFullpath());
			}
		}
		if (ids1.size()>0)listdto.addAll(categoryMapperCustom.getByDtoIds1(ids1,fatherid));
		if (ids2.size()>0)listdto.addAll(categoryMapperCustom.getByDtoIds2(ids2,fatherid));
		if (ids3.size()>0)listdto.addAll(categoryMapperCustom.getByDtoIds3(ids3,fatherid));
		return listdto;
	}

	/**
	 * 获取店铺分类
	 * 
	 * @param fatherid
	 * @param shopid
	 * @return
	 * @throws Exception
	 */
	public List<CategoryDTO> getFatherByShop(Integer fatherid, Integer shopid) throws Exception {
		// 得到所有的一级分类

		List<CategoryDTO> dtolist = categoryMapperCustom.selectCategoryByShop(shopid);

		dtolist = getList(dtolist, fatherid);
		return dtolist;
	}

	/**
	 * 自定义分类提交审核
	 */
	public int updateStatus(Category category) throws Exception {
		return categoryMapperCustom.updateStatus(category);
	}

	/**
	 * 自定义分类批量审核
	 */
	public int updateStatusList(Integer status, List<Integer> idlist) throws Exception {

		List<Category> cateList = new ArrayList<Category>();
		Category cate = null;
		for (int i = 0; i < idlist.size(); i++) {
			cate = new Category();
			cate.setStatus(status);
			cate.setId(idlist.get(i));
			cateList.add(cate);
		}
		return categoryMapperCustom.updateStatusList(cateList);
	}

	/**
	 * 获取自定义分类审核列表
	 * 
	 * @param c
	 * @param page
	 * @param size
	 * @return
	 */
	public PageBean getCheckList(Criteria c, Integer page, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(c, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<CategoryDTO> beanList = categoryMapperCustom.getCustomCheckPage(pageBeanUtil);
		for (CategoryDTO dto : beanList) {
			String fullp = dto.getFullpath();
			if (dto.getFullpath().length() > 5)
				fullp = dto.getFullpath().substring(0, 5);
			dto.setFullname(GetFullNamePath(fullp));
		}
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * 手机端 专用 获取分类列表
	 */
	@Override
	public List<ClassDto> getApiList(Integer fid1) throws Exception {
		ClassDto dto = null;
		List<ClassDto> list = new ArrayList<ClassDto>();
		if (fid1 == null || fid1 < 0) {
			// 取推荐分类和所有一级分类
			List<ClassDto> list2 = categoryMapper.getRecommClass();
			dto = new ClassDto();
			ClassDto dto1 = new ClassDto();
			//dto.setId(-1);
			//dto.setName("推荐分类");
			List<ClassDto> list3 = new ArrayList<ClassDto>();
			dto1.setId(1);
			dto1.setImg("");
			dto1.setName("热门分类");
			dto1.getNextclass().addAll(list2);
			list3.add(dto1);
			//dto.getNextclass().addAll(list3);
			//dto.setSort(-1);
			//list.add(dto);
			List<CategoryDTO> list4 = getByFatherID(0);
			for (CategoryDTO c : list4) {
				dto = new ClassDto();
				dto.setId(c.getId());
				dto.setImg(c.getImageurl());
				dto.setName(c.getName());
				dto.setSort(c.getSort());
				list.add(dto);
			}
			// TODO 取广告 未实现
		} else {
			WeakHashMap<Integer, List<Category>> t = new WeakHashMap<Integer, List<Category>>();
			for (Category item : categoryMapper.getAcByFatherId(fid1)) {
				switch (item.getClasslever()) {
				case 2:
					dto = new ClassDto();
					dto.setId(item.getId());
					dto.setImg(item.getImageurl());
					dto.setName(item.getName());
					dto.setSort(item.getSort());
					list.add(dto);
					break;
				case 3:
					if (t.containsKey(item.getFatherid())) {
						t.get(item.getFatherid()).add(item);
					} else {
						List<Category> s = new ArrayList<Category>();
						s.add(item);
						t.put(item.getFatherid(), s);
					}
					break;
				default:
					break;
				}

			}

			list.forEach((x) -> {
				int key = x.getId();
				if (t.containsKey(key)) {
					List<Category> s = t.get(key);
					ClassDto dto1 = null;
					for (Category c : s) {
						dto1 = new ClassDto();
						dto1.setId(c.getId());
						dto1.setImg(c.getImageurl());
						dto1.setName(c.getName());
						dto1.setSort(c.getSort());
						x.getNextclass().add(dto1);
					}
				}
			});
			// 取当前分类下所有二级及三级分类
		}
		return list;
	}

	/**
	 * 分頁查詢分類專題
	 * 
	 * @see com.yinlian.wssc.web.service.CategoryService#queryTopicRelateListByCriteria(com.yinlian.wssc.web.util.CriteriaTopic,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryTopicRelateListByCriteria(CriteriaTopic criteria, Integer pc, Integer ps) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<TopicRelateInfo> beanList = categoryMapper.selectTopicByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<Category> queryFirstClass() throws Exception {

		return categoryMapper.selectFirstClass();
	}

	@Override
	public List<Category> queryChildrenClass(Integer fatherid) throws Exception {

		return categoryMapper.selectChildrenClass(fatherid);
	}

	public int deleteCatetory(Integer id, Integer userid) throws Exception {
		Category category = categoryMapper.selectByPrimaryKey(id);
		if (category == null) {
			return 0;
		}
		Date now = new Date();
		category.setIsdel(true);
		category.setDeluserid(userid);
		category.setDeltime(now);
		return categoryMapper.updateByPrimaryKey(category);
		// categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageBean seletcProductSpecsPageBySonClassId(Criteria criteria, Integer pc, Integer ps) throws Exception {
		if (criteria == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为null");
				throw new IllegalArgumentException("The parameter Criteria is null!");
			}
		}

		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<ProductSpecs> beanList = productSpecsMapper.seletcProductSpecsPageBySonClassId(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<Category> queryAllList() throws Exception {
		return categoryMapper.queryAllList();
	}

	@Override
	public List<Category> querySan() throws Exception {
		return categoryMapper.querySan();
	}

	@Override
	public Category queryFatherClass(Integer id) throws Exception {
		
		return categoryMapper.selectByPrimaryKey(id);
	}
}
