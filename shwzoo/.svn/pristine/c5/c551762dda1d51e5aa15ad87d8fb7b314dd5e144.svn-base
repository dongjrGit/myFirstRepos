package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.web.dto.ActivityDto;
import com.yinlian.wssc.web.po.ActivityMarket;
import com.yinlian.wssc.web.po.ActivityMarketExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ActivityMarketMapper {
	int countByExample(ActivityMarketExample example);

	int deleteByExample(ActivityMarketExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(ActivityMarket record);

	int insertSelective(ActivityMarket record);

	List<ActivityMarket> selectByExample(ActivityMarketExample example);

	ActivityMarket selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") ActivityMarket record,
			@Param("example") ActivityMarketExample example);

	int updateByExample(@Param("record") ActivityMarket record,
			@Param("example") ActivityMarketExample example);

	int updateByPrimaryKeySelective(ActivityMarket record);

	int updateByPrimaryKey(ActivityMarket record);

	int deleteLogicById(int userid, int id) throws Exception;

	int isCheck(int ischeck, int id) throws Exception;

	List<ActivityMarket> getListPage(PageBeanUtil pageBeanUtil)
			throws Exception;

	int updateStockByIds(List<UpDateActDto> ugiftdtos);

	int changeStatus(int status, int id) throws Exception;

	int updateCheckList(List<Integer> list) throws Exception;
	
	List<ActivityMarket> getAvailableActivityBySpu(@Param("list")List<Integer> spuidlist,@Param("usesites")int usesites)throws Exception;
	
	List<ActivityMarket> getAvailableActivityByShop(@Param("list")List<Integer> shopidlist,@Param("usesites")int usesites)throws Exception;
	
	List<ActivityDto> getActivityDtoByIDs(List<Integer> list)throws Exception;
	
	ActivityDto getActivityDtoByID(Integer id)throws Exception;
}