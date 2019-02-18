package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.po.Images;
import com.yinlian.wssc.web.po.ImagesExample;

public interface ImagesMapper {
    int countByExample(ImagesExample example);

    int deleteByExample(ImagesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Images record);

    int insertSelective(Images record);

    List<Images> selectByExample(ImagesExample example);

    Images selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Images record, @Param("example") ImagesExample example);

    int updateByExample(@Param("record") Images record, @Param("example") ImagesExample example);

    int updateByPrimaryKeySelective(Images record);

    int updateByPrimaryKey(Images record);
    
    List<Images> getByFKid(int fkid)throws Exception;

    int insertList(@Param("list")List<Images> list1)throws Exception;

	List<Images> getImages(Integer id, int value) throws Exception;
}