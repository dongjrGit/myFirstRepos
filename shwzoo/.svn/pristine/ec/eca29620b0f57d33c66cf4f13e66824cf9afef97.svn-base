package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.Api_TopicBrandDto;
import com.yinlian.api.app.dto.Api_TopicByShopDto;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.api.app.dto.TopicDto;
import com.yinlian.pc.dto.PCTopicDto;
import com.yinlian.wssc.web.dto.FindRelationDto;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.po.TopicExample;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.TopicCriteria;

public interface TopicMapper {
    int countByExample(TopicExample example);

    int deleteByExample(TopicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    List<Topic> selectByExample(TopicExample example);

    Topic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Topic record,
                                 @Param("example") TopicExample example);

    int updateByExample(@Param("record") Topic record, @Param("example") TopicExample example);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);

    /**
     * 通过唯一值mark查询专题
     * @param mark
     * @return
     */
    Topic queryByMark(Integer mark);

    /**
     * 通过唯一值name查询专题
     * @param name
     * @return
     */
    Topic queryByName(String name);

    /**
     * 分页查询子专题
     * 
     * @param pageBeanUtil
     * @return
     */
	List<Topic> selectTopicByPage(PageBeanUtil pageBeanUtil);
	
	List<Topic > querySubTopic(Integer mark);
	
	/**
	 * 通过mark,type查询专题
	 * @param type
	 * @param mark
	 * @return
	 */
	Topic select(Integer type,Integer mark);

	/**
	 * 分页查询子专题
	 * 
	 * @param pageBeanUtil
	 * @return
	 */
	List<Topic> selectCtopicByPage(PageBeanUtil pageBeanUtil);
	
	/**
	 * 查询所有的专题
	 * @return
	 */
	List<Topic> selectAll();

	List<TopicDto> getTopicChild(Integer imark) throws Exception;

	List<Api_TopicByShopDto> getTopicByShopPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<Api_TopicBySpuDto> getTopicBySpuPage(PageBeanUtil pageBeanUtil);
	
	List<Topic> selectByMark(Integer mark) throws Exception;	
	
	List<Topic> selectByName(String name) throws Exception;
	
	List<Api_TopicBySpuDto> getTopicSpuList(@Param("pagetag") Integer pagetag,@Param("mark") Integer mark,
            @Param("type") Integer type,@Param("webset") String webset,@Param("size") Integer size)throws Exception;
	//wap获取专题品牌列表
	List<Api_TopicBrandDto> getTopicBrandList(@Param("pagetag") Integer pagetag,@Param("mark") Integer mark,
            @Param("type") Integer type,@Param("webset") String webset,@Param("size") Integer size,@Param("issys")boolean issys)throws Exception;
	//pc 获取专题分页
	List<Api_TopicBySpuDto> getPCTopicPage(PageBeanUtil pageBeanUtil);
	
	List<PCTopicDto> getTopicSpuByPagetag(@Param("pagetag") Integer pagetag,@Param("webset") String webset)throws Exception;

	List<FindRelationDto> seleteFindRelationByPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<Topic> getSpuStartWithNames(String name) throws Exception;

	/**
	 * 条件查询专题
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	List<Topic> selectTopicCriteria(TopicCriteria criteria) throws Exception;

	List<Topic> gettopicStartWithName(@Param("name")String name,@Param("webset")Integer webset)throws Exception;

	List<Api_TopicBySpuDto> getTopicSpuListPage(PageBeanUtil pBeanUtil) throws Exception;
	
	/**
	 * 根据专题标识和专题名称获取专题列表
	 * @param name
	 * @param mark
	 * @return
	 * @throws Exception
	 */
	List<Topic> getTopicStartWithNameByMark(@Param("name")String name,@Param("mark")Integer mark,@Param("pagemark")Integer pagemark)throws Exception;


}