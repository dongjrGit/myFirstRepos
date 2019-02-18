package com.yinlian.wssc.web.mapper;

import java.util.List;

import com.yinlian.api.app.dto.OrderCommentCountDto;
import com.yinlian.api.app.dto.OrderMemberDto;
import com.yinlian.pc.dto.MemberOrderDto;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface OrderMemberMapper {

	public List<OrderMemberDto> getMemberUserListOrderPage(PageBeanUtil pageBeanUtil) throws Exception;
	
	public List<OrderMemberDto> pcselectByStatusPage(PageBeanUtil pageBeanUtil) throws Exception;
	
	public List<OrderMemberDto> pccancelorderPage(PageBeanUtil pageBeanUtil) throws Exception;
	
	public List<OrderMemberDto> pccommentorderPage(PageBeanUtil pageBeanUtil) throws Exception;
	
	public List<OrderCommentCountDto> queryCount(Integer buyerid) throws Exception;
	
	public List<OrderMemberDto> byorderidcomment(Integer buyerid,Integer orderid) throws Exception;
	
	public List<OrderMemberDto> getMemberBytimeOrder(Integer buyerid,String start,String end) throws Exception;
	
	public List<OrderMemberDto> pcselectByStatus(Integer buyerid) throws Exception;
	
	public List<MemberOrderDto> getMemberListOrderPage(PageBeanUtil pageBeanUtil) throws Exception;
	
}