/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.pc.dto.FreightParamDto;
import com.yinlian.pc.dto.FreightReturnDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.po.FreightAttr;
import com.yinlian.wssc.web.po.Sendtemplate;
import com.yinlian.wssc.web.po.V_Freights;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaSendTemplate;

/**
 * 模板业务类接口
 * 
 * @author Administrator
 * @version $Id: FreightService.java, v 0.1 2016年3月7日 下午7:49:22 Administrator
 *          Exp $
 */
public interface FreightService {

    /**
     * 查询所有的模板集合
     * 
     * @return
     */
    List<Freight> queryAll() throws Exception;

    /**
     * 分页查询所有的模板集合
     * 
     * @param pc
     *            当前页码
     * @param ps
     *            每页大小
     * @return
     * @throws Exception
     */
    PageBean queryFreightByShopId(Criteria criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 根据id删除模板
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public int deleteFreightManagerById(Integer id) throws Exception;

    /**
     * 根据店铺id查询所有模板的每页列表
     * 
     * @param criteria
     * @param pc
     *            当前页码
     * @param ps
     *            每页大小
     * @return
     */
    PageBean selectListByPage(Criteria criteria, Integer pc, Integer ps) throws Exception;

    /**
     * 添加模板
     * 
     * @param shopid
     *            店铺id
     * @param name
     *            模板名称
     * @param isExemptionPostage
     *            是否包邮
     * @param pricingModel
     *            计件方式
     * @param transportModel
     *            运送方式
     * @param setNum
     *            计件数值
     * @param description
     * @return
     */
    int insert(Integer shopid, String name, Integer isExemptionPostage, Integer pricingModel,
               Integer transportModel, Integer setNum,Integer isCondition, String description) throws Exception;

    /**
     * 设置默认的模板
     * 
     * @param id
     *            模板id
     * @param shopid
     *            店铺id
     * @return
     */
    int updateStatusDefault(Integer id, Integer shopid) throws Exception;

    /**
     * 根据主键查询信息
     * 
     * @param id
     *            模板id
     * @return
     */
    Freight selectById(Integer id) throws Exception;

    /**
     * 根据id修改模板信息
     * 
     * @param id
     *            模板id
     * @param shopid
     *            店铺id
     * @param name
     *            模板名称
     * @param isExemptionPostage
     *            是否包邮
     * @param pricingModel
     *            计件方式
     * @param transportModel
     *            运送方式
     * @param setNum
     *            计件的数值
     * @param description
     * @param freightAttr 
     * @return
     */
    int update(Integer id, Integer shopid, String name, Integer isExemptionPostage,
               Integer pricingModel, Integer transportModel, Integer setNum, Integer iscondition,String description,
               List<FreightAttr> freightAttr) throws Exception;

    /**
     * 根据主键删除
     * 
     * @param id
     *            模板id
     * @return
     */
    int delete(Integer id) throws Exception;

    /**
     * 添加模板详细配置
     * 
     * @param freightid
     *            模板id
     * @param areas
     *            区域
     * @param firstcount
     *            首件数量
     * @param firstprice
     *            首件价格
     * @param elsecount
     *            续件数量
     * @param elseprice
     *            续件价格
     * @param atInNum
     *            是否少于数值
     * @return
     */
    int insertAttr(Integer freightid, String areas, Integer firstcount, Float firstprice,
                   Integer elsecount, Float elseprice) throws Exception;

    /**
     * 删除模板详细信息
     * 
     * @param id
     *            模板详细信息的id
     * @return
     */
    int deleteAttr(Integer id) throws Exception;

    /**
     * 根据店铺Id和省份名称 获取运费模板
     * 
     * @param id
     *            店铺Id
     * @param provincename
     *            省份名称
     * @return
     */
    V_Freights getByShopID(Integer Shopid, String provincename) throws Exception;

    /**
     * 根据店铺ID获取运费模板
     * 
     * @param shopid
     * @return
     * @throws Exception
     */
    List<Freight> selectByShop(Integer shopid) throws Exception;

    /**
     * 添加发送模板
     * 
     * @param type
     * @param content
     * @param ctype
     * @param sort
     * @return
     */
    int insertSendTemplate(Integer type, String content, Integer ctype, Integer sort,Integer tag)
                                                                                     throws Exception;

    /**
     * 根据id删除发送模板
     * @param id
     * @return
     */
    int deletSendTemplate(Integer toInt) throws Exception;

    /**
     * 根据id查询发送模板信息
     * @param id
     * @return
     */
    Sendtemplate querySendTemplate(Integer id) throws Exception;

    /**
     * 分页查询
     * @param criteria  查询条件类
     * @param pc 当前页
     * @param ps 每页大小
     * @return
     */
    PageBean querySendTemplateList(CriteriaSendTemplate criteria, Integer pc, Integer ps)
                                                                                         throws Exception;

    /**
     * 
     * @param ids
     * @return
     */
    int deletSendTemplateList(String[] array) throws Exception;

    /**
     * 
     * @param toInt
     * @return
     */
    int setDefaultSendTemplate(Integer id) throws Exception;

    /**
     * 
     * @param toInt
     * @param content
     * @param toInt2
     * @param toInt3
     * @param toInt4
     * @return
     */
    int updateSendTemplate(Integer id, String content, Integer ctype, Integer sort, Integer type,Integer tag)
                                                                                                 throws Exception;
    /**
     * 模板替换
     * @param username       用户名
     * @param ordercode      订单编号 
     * @param logisticsname  物流公司
     * @param logisticscode  物流编号
     * @param ordertime      时间
     * @return
     * @throws Exception
     */
    String[] exchangeTemplate(String username,String ordercode,String logisticsname,String logisticscode,String ordertime,String [] content)throws Exception;
    
    /**
     * 根据模板类型和内容类型获取发送信息模板
     * @param templatetype 模板类型 (0邮件 1短信 2系统短信 3系统推送)
     * @param contenttype 内容类型(0订单 1促销)
     * @param tag 模板标识 （详细见枚举TemplateTagEnum）
     * @return
     * @throws Exception
     */
    Sendtemplate getbyType(int templatetype,int contenttype,int tag)throws Exception;
    
    /**
     * 计算订单总运费
     * @param shopid
     * @param proNo
     * @return
     */
	List<V_Freights> getFreightPrice(List<Integer> shopids, String provinceName) throws Exception;
	/**
	 * 查询默认模板
	 * 
	 * @param shopid
	 * @return
	 * @throws Exception
	 */
	Freight selectMfreightByShopId(int shopid) throws Exception;

	/**
	 * 计算运费
	 * @param shopids
	 * @param provinceName
	 * @param spuids 所有商品spuid集合
	 * @return
	 * @throws Exception
	 */
	List<FreightReturnDto> getFreightPriceNew(List<FreightParamDto> shopids, String provinceName,List<Integer> spuids) throws Exception;
}
