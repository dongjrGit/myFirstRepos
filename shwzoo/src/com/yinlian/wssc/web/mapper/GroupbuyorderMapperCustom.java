package com.yinlian.wssc.web.mapper;

import java.util.List;

import com.yinlian.api.app.dto.GroupOrderDto;
import com.yinlian.wssc.web.dto.GroupbuyorderDto;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface GroupbuyorderMapperCustom {

	List<GroupOrderDto> getGroupOrderPage(PageBeanUtil pageBeanUtil)throws Exception;
	
	List<GroupOrderDto> getGroupOrderList(List<Integer> list)throws Exception;
	List<Integer> getByIDPage(PageBeanUtil pageBeanUtil)throws Exception;
	List<GroupbuyorderDto> getGroupOrderByToTalPage(PageBeanUtil pageBeanUtil) throws Exception;

}
