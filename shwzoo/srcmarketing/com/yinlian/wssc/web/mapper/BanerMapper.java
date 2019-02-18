package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Baner;
import com.yinlian.wssc.web.po.BanerExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface BanerMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	int countByExample(BanerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	int deleteByExample(BanerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	int insert(Baner record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	int insertSelective(Baner record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	List<Baner> selectByExample(BanerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	Baner selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	int updateByExampleSelective(@Param("record") Baner record, @Param("example") BanerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	int updateByExample(@Param("record") Baner record, @Param("example") BanerExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	int updateByPrimaryKeySelective(Baner record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table baner
	 * @mbggenerated  Wed Sep 21 14:34:25 CST 2016
	 */
	int updateByPrimaryKey(Baner record);

	List<Baner> queryBanerPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<Baner> getList()throws Exception;

}