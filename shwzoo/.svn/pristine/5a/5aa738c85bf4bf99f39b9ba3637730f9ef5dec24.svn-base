package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.api.app.dto.OrderCountDto;
import com.yinlian.wssc.web.po.Groupbuy;
import com.yinlian.wssc.web.po.GroupbuyExample;
import com.yinlian.wssc.web.po.GroupbuyWithBLOBs;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface GroupbuyMapper {
    int countByExample(GroupbuyExample example);

    int deleteByExample(GroupbuyExample example);

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(GroupbuyWithBLOBs record)throws Exception;

    int insertSelective(GroupbuyWithBLOBs record);

    List<GroupbuyWithBLOBs> selectByExampleWithBLOBs(GroupbuyExample example)throws Exception;

    List<Groupbuy> selectByExample(GroupbuyExample example)throws Exception;

    GroupbuyWithBLOBs selectByPrimaryKey(Integer id)throws Exception;

    int updateByExampleSelective(@Param("record") GroupbuyWithBLOBs record, @Param("example") GroupbuyExample example);

    int updateByExampleWithBLOBs(@Param("record") GroupbuyWithBLOBs record, @Param("example") GroupbuyExample example);

    int updateByExample(@Param("record") Groupbuy record, @Param("example") GroupbuyExample example);

    int updateByPrimaryKeySelective(GroupbuyWithBLOBs record)throws Exception;

    int updateByPrimaryKeyWithBLOBs(GroupbuyWithBLOBs record)throws Exception;

    int updateByPrimaryKey(Groupbuy record)throws Exception;
    
    List<Groupbuy> getListByPage(PageBeanUtil pageBeanUtil)throws Exception;
    
    int deleteGroup(Groupbuy record)throws Exception;
    
    int updateStatus(int status,int id)throws Exception;
    
    GroupbuyWithBLOBs selectByHaCode(String hacode)throws Exception;
    /**
	 * 得到团购订单个状态的数量
	 * @param userid
	 * @return
	 */
	OrderCountDto getCount(Integer userid);
}