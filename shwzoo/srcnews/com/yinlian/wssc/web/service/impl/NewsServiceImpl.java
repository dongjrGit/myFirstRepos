package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.app.dto.NewsBaseDto;
import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.search.NewsProCriteria;
import com.yinlian.wssc.web.dto.NewsProDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.NewsProductMapper;
import com.yinlian.wssc.web.mapper.SnewsClassMapper;
import com.yinlian.wssc.web.mapper.SnewsImgMapper;
import com.yinlian.wssc.web.mapper.SnewsMapper;
import com.yinlian.wssc.web.mapper.SnewsRImgsMapper;
import com.yinlian.wssc.web.po.CriteriaSnews;
import com.yinlian.wssc.web.po.NewsProduct;
import com.yinlian.wssc.web.po.Snews;
import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.SnewsClassExample;
import com.yinlian.wssc.web.po.SnewsImgWithBLOBs;
import com.yinlian.wssc.web.po.SnewsRImgs;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.service.NewsService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("NewsService")
public class NewsServiceImpl implements NewsService {

	@Autowired
	private SnewsClassMapper snewsClassMapper;

	@Autowired
	private SnewsMapper snewsMapper;

	@Autowired
	private SnewsImgMapper snewsImgMapper;

	@Autowired
	private SnewsRImgsMapper snewsRImgsMapper;
	
	@Autowired
	private NewsProductMapper newsProductMapper;

	private List<SnewsClassExample> totalList;
	

	/**
	 * 获取新闻分类
	 * 
	 * @see com.yinlian.wssc.web.service.NavclassfyService#queryClassTree()
	 */
	@Override
	public List<SnewsClassExample> queryClassTree(Integer pid, Integer type) throws Exception {
		totalList = snewsClassMapper.queryClassTree(type);
		List<SnewsClassExample> list = totalList.stream().filter(n -> n.getPid() == pid).collect(Collectors.toList());
		querySonLoad(list);
		return list;
	}

	private void querySonLoad(List<SnewsClassExample> list) throws Exception {
		for (SnewsClassExample snewsClassExample : list) {
			snewsClassExample.setList(queryTree(snewsClassExample.getId()));
		}
	}

	private List<SnewsClassExample> queryTree(Integer pid) throws Exception {
		List<SnewsClassExample> dto = totalList.stream().filter(t -> t.getPid().equals(pid)).collect(Collectors.toList());
		querySonLoad(dto);
		return dto;
	}

	/**
	 * 删除分类信息
	 */
	@Override
	public int delNewsClassById(Integer id) throws Exception {
		return snewsClassMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 查询新闻分类子集信息
	 */
	@Override
	public List<SnewsClass> querySon(Integer pid, Integer ctype) throws Exception {
		List<SnewsClass> list = snewsClassMapper.selectByPId(pid, ctype);
		return list;
	}

	/**
	 * 根据id查询新闻分类
	 */
	@Override
	public SnewsClass queryClassById(Integer id) {
		SnewsClass snewsClass = snewsClassMapper.queryClassById(id);
		return snewsClass;
	}

	/**
	 * 根据ctype查询新闻分类
	 */
	@Override
	public List<SnewsClass> queryStair(Integer ctype) {
		return snewsClassMapper.getByTypeAllList(ctype);
	}

	/**
	 * 根据pid查询新闻分类
	 */
	@Override
	public List<SnewsClass> queryStairByPid(Integer ctype) {
		return snewsClassMapper.getByTypeByPid(ctype);
	}

	/**
	 * 添加分类信息
	 */
	@Override
	public int addNewsClass(SnewsClass snewsClass) throws Exception {
		return snewsClassMapper.insertSelective(snewsClass);
	}

	/**
	 * 编辑分类信息
	 */
	@Override
	public int editNewsClass(SnewsClass snewsClass) throws Exception {
		return snewsClassMapper.updateByPrimaryKeySelective(snewsClass);
	}

	/**
	 * 查询关于我们
	 */
	@Override
	public SnewsWithBLOBs selSingle(Integer ctype) {
		return snewsMapper.selSingle(ctype);
	}

	/**
	 * 添加关于我们
	 */
	@Override
	public int addSingle(SnewsWithBLOBs swb) throws Exception {
		return snewsMapper.insertSelective(swb);
	}

	/**
	 * 修改关于我们
	 */
	@Override
	public int editSingle(SnewsWithBLOBs seb) throws Exception {
		return snewsMapper.updateByPrimaryKeySelective(seb);
	}

	/**
	 * 获取公告列表
	 */
	@Override
	public PageBean querySlist(CriteriaSnews criteria, Integer index, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<SnewsWithBLOBs> list = snewsMapper.querySlistPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	/**
	 * 添加公告列表
	 */
	@Override
	public int addslist(SnewsWithBLOBs swb) throws Exception {
		return snewsMapper.insertSelective(swb);
	}

	/**
	 * 删除公告列表
	 */
	@Override
	public int delSlist(Integer id) throws Exception {
		return snewsMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id获取公告列表
	 */
	@Override
	public SnewsWithBLOBs selSlistById(Integer id) throws Exception {
		SnewsWithBLOBs swb = snewsMapper.selectByPrimaryKey(id);
		return swb;
	}

	/**
	 * 修改公告列表
	 */
	@Override
	public int updateSlistById(SnewsWithBLOBs swb) throws Exception {
		return snewsMapper.updateByPrimaryKeySelective(swb);
	}

	/**
	 * 添加新闻
	 */
	@Override
	public int addList(SnewsWithBLOBs snewsWithBLOBs) throws Exception {
		return snewsMapper.insertSelective(snewsWithBLOBs);
	}

	/**
	 * 修改新闻
	 */
	@Override
	public int updateListById(SnewsWithBLOBs snewsWithBLOBs) throws Exception {
		return snewsMapper.updateByPrimaryKeySelective(snewsWithBLOBs);
	}

	/**
	 * 更改发布状态
	 */
	@Override
	public int setstate(Integer id, Integer state) throws Exception {
		return snewsMapper.setstate(id, state);
	}

	/**
	 * 更改头条状态
	 */
	@Override
	public int sethead(Integer id, Integer head) throws Exception {
		return snewsMapper.sethead(id, head);
	}

	/**
	 * 更改首页状态
	 */
	@Override
	public int setindex(Integer id, Integer index) throws Exception {
		return snewsMapper.setindex(id, index);
	}

	/**
	 * 更改推荐状态
	 */
	@Override
	public int setrecommend(Integer id, Integer recommend) throws Exception {
		return snewsMapper.setrecommend(id, recommend);
	}

	/**
	 * 更改置顶状态
	 */
	@Override
	public int settop(Integer id, Integer top) throws Exception {
		return snewsMapper.settop(id, top);
	}

	/**
	 * 获取图片新闻列表
	 */
	@Override
	public PageBean queryNewsImg(CriteriaSnews criteria, Integer index, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<SnewsImgWithBLOBs> list = snewsImgMapper.queryNewsImgPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	/**
	 * 更改图片新闻发布状态
	 */
	@Override
	public int setimgstate(Integer id, Integer state) throws Exception {
		return snewsImgMapper.setstate(id, state);
	}

	/**
	 * 更改图片新闻头条状态
	 */
	@Override
	public int setimghead(Integer id, Integer head) throws Exception {
		return snewsImgMapper.sethead(id, head);
	}

	/**
	 * 更改图片新闻首页状态
	 */
	@Override
	public int setimgindex(Integer id, Integer index) throws Exception {
		return snewsImgMapper.setindex(id, index);
	}

	/**
	 * 更改图片新闻推荐状态
	 */
	@Override
	public int setimgrecommend(Integer id, Integer recommend) throws Exception {
		return snewsImgMapper.setrecommend(id, recommend);
	}

	/**
	 * 更改图片新闻置顶状态
	 */
	@Override
	public int setimgtop(Integer id, Integer top) throws Exception {
		return snewsImgMapper.settop(id, top);
	}

	/**
	 * 删除图片新闻
	 */
	@Override
	public int delNewsImg(Integer id) throws Exception {
		snewsImgMapper.deleteByPrimaryKey(id);
		return snewsRImgsMapper.delBySnewsid(id);
	}

	/**
	 * 根据id获取图片新闻
	 */
	@Override
	public SnewsImgWithBLOBs selNewsImgById(Integer id) throws Exception {
		SnewsImgWithBLOBs swb = snewsImgMapper.selectByPrimaryKey(id);
		return swb;
	}

	/**
	 * 添加图片新闻
	 */
	@Override
	public int addNewsImg(SnewsImgWithBLOBs snewsImgWithBLOBs, String imgs) throws Exception {
		snewsImgMapper.insertSelective(snewsImgWithBLOBs);
		SnewsRImgs sri = new SnewsRImgs();
		String[] strs = imgs.split("\\|");
		int snewsid = snewsImgWithBLOBs.getId();
		if (strs.length <= 0) {
			for (int i = 0; i < strs.length; i++) {
				sri.setSnewsid(snewsid);
				sri.setImg(strs[i]);
				sri.setSort(i);
				sri.setCreatetime(new Date());
				snewsRImgsMapper.insertSelective(sri);
			}
		}
		return 0;
	}

	/**
	 * 修改图片新闻
	 */
	@Override
	public int updateNewsImgById(SnewsImgWithBLOBs snewsImgWithBLOBs, String imgs) throws Exception {
		snewsRImgsMapper.delBySnewsid(snewsImgWithBLOBs.getId());
		SnewsRImgs sri = new SnewsRImgs();
		String[] strs = imgs.split("\\|");
		int snewsid = snewsImgWithBLOBs.getId();
		if (strs.length > 0) {
			for (int i = 0; i < strs.length; i++) {
				sri.setSnewsid(snewsid);
				sri.setImg(strs[i]);
				sri.setSort(i);
				sri.setCreatetime(new Date());
				snewsRImgsMapper.insertSelective(sri);
			}
		}
		return snewsImgMapper.updateByPrimaryKeySelective(snewsImgWithBLOBs);
	}

	/**
	 * 根据snewsid获取图片新闻的多图片
	 */
	@Override
	public List<SnewsRImgs> selRNewsImgBySid(Integer sid) throws Exception {
		return snewsRImgsMapper.selRNewsImgBySid(sid);
	}

	/**
	 * app 查询新闻 （例如中绿资讯）
	 */
	@Override
	public PageBean getApplist(AppNewsCriteria criteria, Integer page, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<NewsBaseDto> list = snewsMapper.getapplistPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public List<SnewsClass> getClassByType(int type) {
		return snewsClassMapper.getClassByType(type);
	}

	@Override
	public SnewsWithBLOBs getById(Integer id) throws Exception {
		return snewsMapper.getById(id);
	}

	@Override
	public List<Snews> getByRecommend() throws Exception {
		return snewsMapper.getByRecommend();
	}

	@Override
	public List<Snews> getProvinceList() throws Exception {
		return snewsMapper.getProvinceList();
	}

	@Override
	public List<Snews> getProvincebyCode(Integer code) throws Exception {
		return snewsMapper.getProvincebyCode(code);
	}

	@Override
	public List<SnewsWithBLOBs> findByCType(Integer ctype) throws Exception {
		return snewsMapper.selectByNewsCtype(ctype);
	}

	@Override
	public PageBean queryProPage(NewsProCriteria criteria, Integer page, Integer size) throws Exception {
		PageBeanUtil beanUtil=new PageBeanUtil(criteria, page, size);
		List<NewsProDto> beanlist=newsProductMapper.selectByPage(beanUtil);
		PageBean bean=beanUtil.getPage();
		bean.setBeanList(beanlist);
		return bean;
	}

	@Override
	public Integer insertNewsPro(NewsProduct product) throws Exception {
		return newsProductMapper.insertSelective(product);
	}

	@Override
	public Integer delNewsSpu(Integer id) throws Exception {
		return newsProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<com.yinlian.pc.dto.NewsProDto> queryProAll(Integer newsid) throws Exception {
		return newsProductMapper.selectProPcList(newsid);
	}
}
