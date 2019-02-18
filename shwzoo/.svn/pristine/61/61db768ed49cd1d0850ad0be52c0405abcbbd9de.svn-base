package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.InvoiceDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Invoice;
import com.yinlian.wssc.web.util.Criteria;

public interface InvoiceService {

	int  insert(Invoice invoice) throws Exception;
	
	List<InvoiceDto> getByUserId(Integer userid)throws Exception;
	
	//通过orderid查询发票
	Invoice selectByOrderId(int orderid) throws Exception;
	
	/**
	 * 获取订单发票列表
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean getByOrderPage(Criteria criteria,int page,int size)throws Exception;

	/**
	 * 更新发票打印状态
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int updateStatus(Integer id)throws Exception;
}
