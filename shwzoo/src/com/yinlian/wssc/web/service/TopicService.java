package com.yinlian.wssc.web.service;

import java.util.Date;
import java.util.List;

import com.yinlian.api.app.dto.Api_FirstPageTopicDto;
import com.yinlian.api.app.dto.Api_TopicBrandDto;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.pc.dto.PCTopicDto;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.search.Pc_TopicCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.util.CriteriaFindRelate;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.TopicCriteria;

public interface TopicService {

    /**
     * 根据标识查询
     * 
     * @param mark
     * @return
     * @throws Exception
     */
    public Topic selectByMark(Integer mark) throws Exception;

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    Topic queryById(Integer id) throws Exception;



	 /**
	  * 通过name查询专题
	  * @param name
	  * @return
	  * @throws Exception
	  */
    List<Topic> queryByName(String name) throws Exception;

    /**
     * 分页查询子专题
     * 
     * @param criteria
     * @param pc
     * @param ps
     * @return
     * @throws Exception
     */
	public PageBean queryTopicListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps) throws Exception;
	/**
	 * 插入一个专题
	 * @param topic
	 * @return
	 * @throws Exception
	 */
	int insert(Topic topic) throws Exception;
	
	/**
	 * 删除一个专题
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delete(Integer id) throws Exception;

	/**
	 * 根据主键删除专题
	 * 
	 * @param topic
	 * @return
	 * @throws Exception
	 */
	public int delTopicById(Topic topic) throws Exception;
	
	/**
	 * 修改专题信息
	 * @param topic
	 * @return
	 * @throws Exception
	 */
	public int update(Topic topic) throws Exception;

	/**
	 * 根据fatherid查询子专题
	 * 
	 * @param fatherid
	 * @return
	 * @throws Exception
	 */
	public List<Topic> selectByTopicFatherId(int fatherid) throws Exception;
	
	/**
	 * 查询子专题
	 * @param mark
	 * @return
	 * @throws Exception
	 */
	public List<Topic> selectSubTopic(Integer mark) throws Exception;
	
	/**
	 * 通过type和mark查询专题
	 * @param type
	 * @param mark
	 * @return
	 * @throws Exception
	 */
	public Topic query(Integer type,Integer mark) throws Exception;

	/**
	 * 分页查询子专题
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	public PageBean queryCtopicListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps) throws Exception;
	
	/**
	 * 查询所有的专题
	 * @return
	 * @throws Exception
	 */
	public List<Topic> queryAll() throws Exception;

	List<Api_FirstPageTopicDto> getTopic(Integer imark) throws Exception;

	PageBean getTopicByShop(Integer page, Integer size)throws Exception;

	 PageBean getTopicBySpu(Api_TopicBySpuCriteria atc, int page,
			int size);
	 
	 /**
	  * 通过mark查询专题
	  * @param mark
	  * @return
	  * @throws Exception
	  */
	 List<Topic> queryByMark(Integer mark) throws Exception;
	 /**
	  * 获取首页专题
	  * @param pagetag 页面标识
	  * @param mark 具体类型（位置）
	  * @param type 主题类型
	  * @param webset 站点
	  * */
	 List<Api_TopicBySpuDto> getIndexTopic(Integer pagetag,Integer mark,Integer type,String webset,Integer size) throws Exception;
	 /**
	  * 获取专题品牌
	  * @param pagetag 页面标识
	  * @param mark 具体类型（位置）
	  * @param type 主题类型
	  * @param webset 站点
	  * @param issys 是否系统专题
	  * */
	 List<Api_TopicBrandDto> getTopicBrand(Integer pagetag,Integer mark,Integer type,String webset,Integer size,boolean issys) throws Exception;
	 /**
	  * 获取PC页面专题（分页）
	  * @param criteria 条件
	  * @param pc 起始页
	  * @param ps 每页行数
	  * */
	 public PageBean getPcTopByPage(Pc_TopicCriteria criteria, Integer pc, Integer ps) throws Exception;
	 /**
	  * 获取专题 (针对商品)
	  * @param pagetag 页面标识
	  * @param webset 站点
	  * */
	 List<PCTopicDto> getTopicSpuByPagetag(Integer pagetag,String webset)throws Exception;

	public PageBean queryFindRelationByCriteria(CriteriaFindRelate criteria, Integer pc, Integer ps) throws Exception;

	public List<Topic> getSpuStartWithNames(String name)throws Exception;

	/**
	 * 
	 * 条件获取专题
	 * 
	 * @since 2016年11月8日
	 * @param pagemark 页标识
	 * @param topicmark 专题标识
	 * @param topictype 专题类型
	 * @param webset
	 * @return
	 */
	public List<Topic> findTopicCriteria(int pagemark, int topicmark, int topictype, String webset);
	
	
	/**
	 * 
	 * 条件获取专题
	 * 
	 * @since 2016年11月8日
	 * @param pagemark 页标识
	 * @param topicmark 专题标识
	 * @param topictype 专题类型
	 * @param webset
	 * @return
	 */
	public List<Topic> findTopicCriteria(TopicCriteria criteria)throws Exception;
	/**
	 * 模糊检索获取所有专题列表
	 * @param name
	 * @param webset 
	 * @return
	 * @throws Exception
	 */
	List<Topic>  getTopicStartWithName(String name,Integer webset)throws Exception;

	/**
	 * 根据专题标识获取关联商品列表(不分页)
	 * @param value
	 * @param toInt
	 * @param string
	 * @param sizei
	 * @return
	 */
	public List<Api_TopicBySpuDto> getApiTopicSpu(int value, Integer toInt,
			String string, Integer sizei,Date date)throws Exception;
	
	/**
	 * 根据专题ID获取关联商品列表(分页)
	 * @param value
	 * @param toInt
	 * @param string
	 * @param sizei
	 * @return
	 */
	public PageBean getApiTopicSpuPage(CriteriaTopic criteria,
			int page1, int size1)throws Exception;
	
	/**
	 * 根据专题标识和专题名称获取专题列表
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Topic> getTopicStartWithNameByMark(String name)throws Exception;
}
