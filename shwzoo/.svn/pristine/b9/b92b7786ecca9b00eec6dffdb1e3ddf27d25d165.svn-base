package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.GoodConsultDto;
import com.yinlian.wssc.web.po.Goodconsult;
import com.yinlian.wssc.web.po.GoodconsultExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface GoodconsultMapper {
    int countByExample(GoodconsultExample example);

    int deleteByExample(GoodconsultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goodconsult record);

    int insertSelective(Goodconsult record);

    List<Goodconsult> selectByExample(GoodconsultExample example);

    Goodconsult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goodconsult record,
                                 @Param("example") GoodconsultExample example);

    int updateByExample(@Param("record") Goodconsult record,
                        @Param("example") GoodconsultExample example);

    int updateByPrimaryKeySelective(Goodconsult record);

    int updateByPrimaryKey(Goodconsult record);

    /**
     * 分页查询咨询列表
     * @param pageBeanUtil
     * @return
     */
    List<GoodConsultDto> selectGoodConsuByPage(PageBeanUtil pageBeanUtil);

	List<GoodConsultDto> selectUserGoodConsuByPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<GoodConsultDto> queryPage(PageBeanUtil pageBeanUtil);
}