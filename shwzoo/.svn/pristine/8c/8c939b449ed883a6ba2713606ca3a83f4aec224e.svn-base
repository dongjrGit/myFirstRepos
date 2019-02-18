package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.NavclassfyDto;
import com.yinlian.wssc.web.po.Navclassfy;
import com.yinlian.wssc.web.po.NavclassfyExample;

public interface NavclassfyMapper {
    int countByExample(NavclassfyExample example);

    int deleteByExample(NavclassfyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Navclassfy record);

    int insertSelective(Navclassfy record);

    List<Navclassfy> selectByExample(NavclassfyExample example);

    Navclassfy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Navclassfy record, @Param("example") NavclassfyExample example);

    int updateByExample(@Param("record") Navclassfy record, @Param("example") NavclassfyExample example);

    int updateByPrimaryKeySelective(Navclassfy record);

    int updateByPrimaryKey(Navclassfy record);
    /**
     * 查询一级分类
     * 
     * @param parentid
     * @return
     */
	List<NavclassfyDto> selectByParentId(Integer parentid);
	List<NavclassfyDto> selectTotal();
	List<NavclassfyDto> selectByPIdType(@Param("parentid") Integer parentid,@Param("navtype") Integer navtype);

	List<Navclassfy> selectAll();

	List<NavclassfyDto> queryRelevance(int value, int value2);

	NavclassfyDto queryByDtoId(Integer classid);

	List<Navclassfy> queryById(@Param("ids")String[] ids);
}