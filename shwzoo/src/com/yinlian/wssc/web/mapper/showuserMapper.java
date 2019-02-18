package com.yinlian.wssc.web.mapper;

import com.yinlian.wssc.web.po.showarticle;
import com.yinlian.wssc.web.po.showuser;
import com.yinlian.wssc.web.po.showuserExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface showuserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    int countByExample(showuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    int deleteByExample(showuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    int insert(showuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    int insertSelective(showuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    List<showuser> selectByExample(showuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    showuser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    int updateByExampleSelective(@Param("record") showuser record, @Param("example") showuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    int updateByExample(@Param("record") showuser record, @Param("example") showuserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    int updateByPrimaryKeySelective(showuser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table show_user
     *
     * @mbggenerated Thu Jul 07 12:12:56 CST 2016
     */
    int updateByPrimaryKey(showuser record);

	List<showarticle> getLikePage(PageBeanUtil pageBeanUtil) throws Exception;

	int selectCount(Integer showid) throws Exception;
}