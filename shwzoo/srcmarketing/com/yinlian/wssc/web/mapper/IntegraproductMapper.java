package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Integraproduct;
import com.yinlian.wssc.web.po.IntegraproductExample;
import com.yinlian.wssc.web.po.IntegraproductWithBLOBs;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface IntegraproductMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int countByExample(IntegraproductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int deleteByExample(IntegraproductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int insert(IntegraproductWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int insertSelective(IntegraproductWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	List<IntegraproductWithBLOBs> selectByExampleWithBLOBs(IntegraproductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	List<Integraproduct> selectByExample(IntegraproductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	IntegraproductWithBLOBs selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int updateByExampleSelective(@Param("record") IntegraproductWithBLOBs record,
			@Param("example") IntegraproductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int updateByExampleWithBLOBs(@Param("record") IntegraproductWithBLOBs record,
			@Param("example") IntegraproductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int updateByExample(@Param("record") Integraproduct record, @Param("example") IntegraproductExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int updateByPrimaryKeySelective(IntegraproductWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int updateByPrimaryKeyWithBLOBs(IntegraproductWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table integraproduct
	 * 
	 * @mbggenerated Sun Sep 18 10:55:26 CST 2016
	 */
	int updateByPrimaryKey(Integraproduct record);

	List<IntegraproductWithBLOBs> queryIntegraPage(PageBeanUtil pageBeanUtil) throws Exception;

}