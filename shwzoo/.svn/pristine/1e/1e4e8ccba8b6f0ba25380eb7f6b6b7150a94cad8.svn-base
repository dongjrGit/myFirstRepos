package com.yinlian.wssc.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.dto.ThOrder;
import com.yinlian.wssc.web.po.Applyforcancelorder;
import com.yinlian.wssc.web.po.ApplyforcancelorderExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface ApplyforcancelorderMapper {
    int countByExample(ApplyforcancelorderExample example) throws Exception;

    int deleteByExample(ApplyforcancelorderExample example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Applyforcancelorder record) throws Exception;

    int insertSelective(Applyforcancelorder record) throws Exception;

    List<Applyforcancelorder> selectByExample(ApplyforcancelorderExample example) throws Exception;

    Applyforcancelorder selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Applyforcancelorder record,
                                 @Param("example") ApplyforcancelorderExample example);

    int updateByExample(@Param("record") Applyforcancelorder record,
                        @Param("example") ApplyforcancelorderExample example);

    int updateByPrimaryKeySelective(Applyforcancelorder record) throws Exception;

    int updateByPrimaryKey(Applyforcancelorder record) throws Exception;

    Applyforcancelorder getbyOrderAndType(int orderid, int type) throws Exception;
    /**
     * 根据订单ID或者订单detailID和订单状态获取申请记录
     * @param orderid 订单id
     * @param status 订单状态
     * @return
     * @throws Exception
     */
    Applyforcancelorder getbyOrderAndStatus(int orderid, int status) throws Exception;
    /**
     * 
     * @param applyforcancelorder
     */
    int insertForPrimaryKey(Applyforcancelorder applyforcancelorder) throws Exception;
    
    /**
     * 查询退款退货列表
     * @param pageBeanUtil
     * @return
     */
    List<ThOrder> getThListByPage(PageBeanUtil pageBeanUtil);
    
    /**
     * 查询卖家退款退货列表
     * @param pageBeanUtil
     * @return
     */
	List<ThOrder> getSellerThListByPage(PageBeanUtil pageBeanUtil);
	
	Applyforcancelorder getbyOrderAndUser(int orderid, int userid) throws Exception;
	/**
     * 根据订单ID或者订单detailID和用户id获取申请记录
     * @param orderid 订单id
     * @param userid 用户id
     * @return
     * @throws Exception
     */
	List<Applyforcancelorder> getApplyList(int orderid, int userid) throws Exception;
	/**
     * 根据订单ID获取申请记录
     * @param orderid 订单id
     * @throws Exception
     */
	List<Applyforcancelorder> getApplyListByOid(int orderid) throws Exception;
}