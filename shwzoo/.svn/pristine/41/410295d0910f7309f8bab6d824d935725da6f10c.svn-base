package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.InvoiceDto;
import com.yinlian.wssc.web.dto.OrderInvoiceDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.InvoiceMapper;
import com.yinlian.wssc.web.po.Invoice;
import com.yinlian.wssc.web.service.InvoiceService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceMapper  invoiceMapper;
	@Override
	public int insert(Invoice invoice) throws Exception {
		return invoiceMapper.insert(invoice);
	}

	/**
	 * 订单结算-获取会员最近使用的3条发票抬头信息
	 */
	public List<InvoiceDto> getByUserId(Integer userid)throws Exception{
		
		List<InvoiceDto> invlist=invoiceMapper.getByUserId(userid);
		if(invlist==null){
			invlist=new ArrayList<InvoiceDto>();
		}
		return invlist;
	}

	@Override
	public Invoice selectByOrderId(int orderid) throws Exception {
		
		return invoiceMapper.queryByOrderId(orderid);
	}

	@Override
	public PageBean getByOrderPage(Criteria criteria, int page, int size)
			throws Exception {
		PageBeanUtil pageBeanUtil=new PageBeanUtil(criteria, page, size);
		PageBean pageBean=pageBeanUtil.getPage();
		List<OrderInvoiceDto> list=invoiceMapper.getByOrderPage(pageBeanUtil);
		pageBean.setBeanList(list);
		
		return pageBean;
	}

	@Override
	public int updateStatus(Integer id) throws Exception {
		Invoice inv=new Invoice();
		inv=invoiceMapper.selectByPrimaryKey(id);
		if(inv!=null){
			inv.setStatus(1);
			inv.setPrintdate(new Date());
			return invoiceMapper.updateByPrimaryKey(inv);
		}else{
			return 0;
		}
		
	}
}
