package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.InvoiceDto;
import com.yinlian.wssc.web.dto.OrderInvoiceDto;
import com.yinlian.wssc.web.po.Invoice;
import com.yinlian.wssc.web.po.InvoiceExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface InvoiceMapper {
    int countByExample(InvoiceExample example);

    int deleteByExample(InvoiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Invoice record);

    int insertSelective(Invoice record);

    List<Invoice> selectByExample(InvoiceExample example);

    Invoice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    int updateByExample(@Param("record") Invoice record, @Param("example") InvoiceExample example);

    int updateByPrimaryKeySelective(Invoice record);

    int updateByPrimaryKey(Invoice record);
    
    List<InvoiceDto> getByUserId(Integer userid)throws Exception;
    
    Invoice queryByOrderId(int orderid) throws Exception;
    
    List<OrderInvoiceDto> getByOrderPage(PageBeanUtil pageBeanUtil)throws Exception;
}