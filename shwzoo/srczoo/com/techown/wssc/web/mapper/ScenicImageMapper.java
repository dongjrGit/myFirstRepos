package com.techown.wssc.web.mapper;

import java.util.List;

import com.techown.wssc.web.po.ScenicImage;

public interface ScenicImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScenicImage record);

    int insertSelective(ScenicImage record);

    ScenicImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScenicImage record);

    int updateByPrimaryKey(ScenicImage record);
    
    int insertByBatch(List<ScenicImage> list);

	List<ScenicImage> selectByImageId(String imageid);

	void deleteByImageId(String imageid);
}