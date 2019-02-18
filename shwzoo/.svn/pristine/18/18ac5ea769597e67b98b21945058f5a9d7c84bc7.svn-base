package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.pc.dto.NewsProDto;
import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.search.NewsProCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaSnews;
import com.yinlian.wssc.web.po.NewsProduct;
import com.yinlian.wssc.web.po.Snews;
import com.yinlian.wssc.web.po.SnewsClass;
import com.yinlian.wssc.web.po.SnewsClassExample;
import com.yinlian.wssc.web.po.SnewsImgWithBLOBs;
import com.yinlian.wssc.web.po.SnewsRImgs;
import com.yinlian.wssc.web.po.SnewsWithBLOBs;
import com.yinlian.wssc.web.po.users_news;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface NewsService {

	/**
	 * 获取新闻分类
	 * 
	 * @param pid
	 * @param type
	 * @return
	 * @throws Exception
	 */
	List<SnewsClassExample> queryClassTree(Integer pid, Integer type) throws Exception;

	/**
	 * 删除分类信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delNewsClassById(Integer id) throws Exception;

	/**
	 * 查询新闻分类子集信息
	 * 
	 * @param pid
	 * @param ctype
	 * @return
	 * @throws Exception
	 */
	List<SnewsClass> querySon(Integer pid, Integer ctype) throws Exception;

	/**
	 * 根据id查询新闻分类
	 * 
	 * @param id
	 * @return
	 */
	SnewsClass queryClassById(Integer id);

	/**
	 * 根据ctype查询新闻分类
	 * 
	 * @param ctype
	 * @return
	 */
	List<SnewsClass> queryStair(Integer ctype);

	/**
	 * 根据pid查询新闻分类
	 * 
	 * @param ctype
	 * @return
	 */
	List<SnewsClass> queryStairByPid(Integer ctype);

	/**
	 * 添加分类信息
	 * 
	 * @param snewsClass
	 * @return
	 * @throws Exception
	 */
	int addNewsClass(SnewsClass snewsClass) throws Exception;

	/**
	 * 编辑分类信息
	 * 
	 * @param snewsClass
	 * @return
	 * @throws Exception
	 */
	int editNewsClass(SnewsClass snewsClass) throws Exception;

	/**
	 * 查询关于我们
	 * 
	 * @param ctype
	 * @return
	 */
	SnewsWithBLOBs selSingle(Integer ctype);

	/**
	 * 添加关于我们
	 * 
	 * @param seb
	 * @return
	 * @throws Exception
	 */
	int addSingle(SnewsWithBLOBs seb) throws Exception;

	/**
	 * 修改关于我们
	 * 
	 * @param seb
	 * @return
	 * @throws Exception
	 */
	int editSingle(SnewsWithBLOBs seb) throws Exception;

	/**
	 * 获取公告列表
	 * 
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean querySlist(CriteriaSnews criteria, Integer index, Integer size) throws Exception;

	/**
	 * 添加公告列表
	 * 
	 * @param swb
	 * @return
	 * @throws Exception
	 */
	int addslist(SnewsWithBLOBs swb) throws Exception;

	/**
	 * 删除公告列表
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delSlist(Integer id) throws Exception;

	/**
	 * 根据id获取公告列表
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SnewsWithBLOBs selSlistById(Integer id) throws Exception;

	/**
	 * 修改公告列表
	 * 
	 * @param swb
	 * @return
	 * @throws Exception
	 */
	int updateSlistById(SnewsWithBLOBs swb) throws Exception;

	/**
	 * 添加新闻
	 * 
	 * @param snewsWithBLOBs
	 * @return
	 * @throws Exception
	 */
	int addList(SnewsWithBLOBs snewsWithBLOBs) throws Exception;

	/**
	 * 修改新闻
	 * 
	 * @param snewsWithBLOBs
	 * @return
	 * @throws Exception
	 */
	int updateListById(SnewsWithBLOBs snewsWithBLOBs) throws Exception;

	/**
	 * 更改发布状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 * @throws Exception
	 */
	int setstate(Integer id, Integer state) throws Exception;

	/**
	 * 更改头条状态
	 * 
	 * @param id
	 * @param head
	 * @return
	 * @throws Exception
	 */
	int sethead(Integer id, Integer head) throws Exception;

	/**
	 * 更改首页状态
	 * 
	 * @param id
	 * @param index
	 * @return
	 * @throws Exception
	 */
	int setindex(Integer id, Integer index) throws Exception;

	/**
	 * 更改推荐状态
	 * 
	 * @param id
	 * @param recommend
	 * @return
	 * @throws Exception
	 */
	int setrecommend(Integer id, Integer recommend) throws Exception;

	/**
	 * 更改置顶状态
	 * 
	 * @param id
	 * @param top
	 * @return
	 * @throws Exception
	 */
	int settop(Integer id, Integer top) throws Exception;

	/**
	 * 获取图片新闻列表
	 * 
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean queryNewsImg(CriteriaSnews criteria, Integer index, Integer size) throws Exception;

	/**
	 * 更改图片新闻发布状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 * @throws Exception
	 */
	int setimgstate(Integer id, Integer state) throws Exception;

	/**
	 * 更改图片新闻头条状态
	 * 
	 * @param id
	 * @param head
	 * @return
	 * @throws Exception
	 */
	int setimghead(Integer id, Integer head) throws Exception;

	/**
	 * 更改图片新闻首页状态
	 * 
	 * @param id
	 * @param index
	 * @return
	 * @throws Exception
	 */
	int setimgindex(Integer id, Integer index) throws Exception;

	/**
	 * 更改图片新闻推荐状态
	 * 
	 * @param id
	 * @param recommend
	 * @return
	 * @throws Exception
	 */
	int setimgrecommend(Integer id, Integer recommend) throws Exception;

	/**
	 * 更改图片新闻置顶状态
	 * 
	 * @param id
	 * @param top
	 * @return
	 * @throws Exception
	 */
	int setimgtop(Integer id, Integer top) throws Exception;

	/**
	 * 删除图片新闻
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delNewsImg(Integer id) throws Exception;

	/**
	 * 根据id获取图片新闻
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SnewsImgWithBLOBs selNewsImgById(Integer id) throws Exception;

	/**
	 * 添加图片新闻
	 * 
	 * @param snewsImgWithBLOBs
	 * @param imgs
	 * @return
	 * @throws Exception
	 */
	int addNewsImg(SnewsImgWithBLOBs snewsImgWithBLOBs, String imgs) throws Exception;

	/**
	 * 修改图片新闻
	 * 
	 * @param snewsImgWithBLOBs
	 * @param imgs
	 * @return
	 * @throws Exception
	 */
	int updateNewsImgById(SnewsImgWithBLOBs snewsImgWithBLOBs, String imgs) throws Exception;

	/**
	 * 根据snewsid获取图片新闻的多图片
	 * 
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	List<SnewsRImgs> selRNewsImgBySid(Integer sid) throws Exception;

	/**
	 * app 查询新闻 （例如中绿资讯）
	 * 
	 * @param criteria
	 * @param page
	 * @param zise
	 * @throws Exception
	 */
	PageBean getApplist(AppNewsCriteria criteria, Integer page, Integer size) throws Exception;

	/**
	 * 获取分类 根据 类型
	 * @param type
	 * @return
	 */
	List<SnewsClass> getClassByType(int type);

	/**
	 * 根据id 查询新闻
	 * @param id
	 * @return
	 * @throws Exception
	 */
	SnewsWithBLOBs getById(Integer id) throws Exception;

	List<Snews> getByRecommend() throws Exception;
	
	List<Snews> getProvinceList()throws Exception;
	
	List<Snews> getProvincebyCode(Integer code)throws Exception;

	List<SnewsWithBLOBs> findByCType(Integer ctype)throws Exception;
	
	/**
	 * 新闻商品
	 * @param page
	 * @param size
	 * @return
	 */
	PageBean queryProPage(NewsProCriteria criteria,Integer page,Integer size) throws Exception;
	
	/**
	 * 添加新闻商品
	 * @param product
	 * @return
	 * @throws Exception
	 */
	Integer insertNewsPro(NewsProduct product) throws Exception;

	/**
	 * 删除新闻商品
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Integer delNewsSpu(Integer id)throws Exception;

	List<NewsProDto> queryProAll(Integer newsid)throws Exception;
}
