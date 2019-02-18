package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.search.IdCardCriteria;
import com.yinlian.wssc.web.po.Idcardinfo;
import com.yinlian.wssc.web.po.IdcardinfoExample;

public interface IdcardinfoMapper {
	int countByExample(IdcardinfoExample example);

	int deleteByExample(IdcardinfoExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Idcardinfo record);

	int insertSelective(Idcardinfo record);

	List<Idcardinfo> selectByExample(IdcardinfoExample example);

	Idcardinfo selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Idcardinfo record, @Param("example") IdcardinfoExample example);

	int updateByExample(@Param("record") Idcardinfo record, @Param("example") IdcardinfoExample example);

	int updateByPrimaryKeySelective(Idcardinfo record);

	int updateByPrimaryKey(Idcardinfo record);

	Integer checkcardinfo(@Param("criteria") IdCardCriteria criteria) throws Exception;
	
	Idcardinfo getByGroupCode(String groupcode)throws Exception;

	List<Idcardinfo> quertByUserId(Integer userid)throws Exception;

	int delCardInfo(Idcardinfo idcardinfo)throws Exception;
	
}