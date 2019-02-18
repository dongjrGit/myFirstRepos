package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.SpuDto;
import com.yinlian.wssc.web.dto.BrowseHistoryDto;
import com.yinlian.wssc.web.po.Browsehistory;
import com.yinlian.wssc.web.po.BrowsehistoryExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface BrowsehistoryMapper {
    int countByExample(BrowsehistoryExample example);

    int deleteByExample(BrowsehistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Browsehistory record);

    int insertSelective(Browsehistory record);

    List<Browsehistory> selectByExample(BrowsehistoryExample example);

    Browsehistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Browsehistory record, @Param("example") BrowsehistoryExample example);

    int updateByExample(@Param("record") Browsehistory record, @Param("example") BrowsehistoryExample example);

    int updateByPrimaryKeySelective(Browsehistory record);

    int updateByPrimaryKey(Browsehistory record);
    /**
     * 分页查询最新会员浏览历史记录
     * @param pageBeanUtil
     * @return
     */
	List<BrowseHistoryDto> selectBrowsehistoryByPage(PageBeanUtil pageBeanUtil);

	/**
	 * 查询会员浏览记录明细
	 * 
	 * @param userid
	 * @return
	 */
	List<BrowseHistoryDto> selectDetailByUserId(Integer userid);

}