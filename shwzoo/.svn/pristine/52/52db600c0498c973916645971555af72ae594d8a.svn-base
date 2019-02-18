/*
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springfremarke.bean.prezo.BeanUtils;

import com.yinlian.pc.dto.FreightParamDto;
import com.yinlian.pc.dto.FreightReturnDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.FreightAttrMapper;
import com.yinlian.wssc.web.mapper.FreightMapper;
import com.yinlian.wssc.web.mapper.SendtemplateMapper;
import com.yinlian.wssc.web.mapper.SpuMapper;
import com.yinlian.wssc.web.mapper.V_FreightsMapper;
import com.yinlian.wssc.web.po.Freight;
import com.yinlian.wssc.web.po.FreightAttr;
import com.yinlian.wssc.web.po.FreightAttrExample;
import com.yinlian.wssc.web.po.FreightExample;
import com.yinlian.wssc.web.po.Sendtemplate;
import com.yinlian.wssc.web.po.SendtemplateExample;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.V_Freights;
import com.yinlian.wssc.web.service.FreightService;
import com.yinlian.wssc.web.service.OrderService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaSendTemplate;
import com.yinlian.wssc.web.util.PageBeanUtil;

/**
 * 模板业务类
 * 
 * @author Administrator
 * @version $Id: FreightServiceImpl.java, v 0.1 2016年3月7日 下午7:49:54
 *          Administrator Exp $
 */
@Component("freightService")
public class FreightServiceImpl implements FreightService {

	/**
	 * 日志输出的类
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(FreightServiceImpl.class);
	@Autowired
	private FreightMapper freightMapper;
	@Autowired
	private FreightAttrMapper freightAttrMapper;
	@Autowired
	private V_FreightsMapper v_FreightsMapper;
	@Autowired
	private SendtemplateMapper sendtemplateMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SpuMapper spuMapper;

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#queryAll()
	 */
	@Override
	public List<Freight> queryAll() throws Exception {
		FreightExample example = new FreightExample();

		return freightMapper.selectByExample(example);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#queryFreightAll(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public PageBean queryFreightByShopId(Criteria criteria, Integer pc,
			Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Freight> beanList = freightMapper.selectByShopIdPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public int deleteFreightManagerById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		if (id == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("参数为空");
				throw new IllegalArgumentException("The parameter id is null");
			}
		}
		// 先删除运费模板详情
		freightAttrMapper.deleteByFreightID(id);
		return freightMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#selectListByPage(com.yinlian.wssc.web.util.Criteria,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean selectListByPage(Criteria criteria, Integer pc, Integer ps)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Freight> beanList = freightMapper.selectByShopIdPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#insert(java.lang.Integer,
	 *      java.lang.String, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int insert(Integer shopid, String name, Integer isExemptionPostage,
			Integer pricingModel, Integer transportModel, Integer setNum,
			Integer isCondition, String description) throws Exception {
		Freight record = new Freight();
		record.setShopid(shopid);
		record.setIsexemptionpostage(isExemptionPostage);
		record.setPricingmode(pricingModel);
		record.setTransportmode(transportModel);
		if (setNum == -1) {
			record.setNum(0);
		} else {
			record.setNum(setNum);
		}
		record.setName(name);
		record.setCreatetime(new Date());
		record.setStatus(0); // 0 表示非默认 1 表示默认
		record.setIscondition(isCondition);
		record.setDescription(description);
		return freightMapper.insertSelective(record);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#updateStatusDefault(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public int updateStatusDefault(Integer id, Integer shopid) throws Exception {
		FreightExample example = new FreightExample();
		FreightExample.Criteria criteria = example.createCriteria();
		if (shopid != null && shopid != -1) {
			criteria.andShopidEqualTo(shopid);
		}
		List<Freight> list = freightMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for (Freight freight : list) {
				Integer status = freight.getStatus();
				if (status == 1) {
					freight.setStatus(0);
					freightMapper.updateByPrimaryKey(freight);
				}
			}
		}
		Freight record = freightMapper.selectByPrimaryKey(id);
		record.setStatus(1);
		return freightMapper.updateByPrimaryKey(record);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#selectById(java.lang.Integer)
	 */
	@Override
	public Freight selectById(Integer id) throws Exception {
		Freight record = freightMapper.selectByPrimaryKey(id);
		FreightAttrExample example = new FreightAttrExample();
		FreightAttrExample.Criteria criteria = example.createCriteria();
		criteria.andFreightidEqualTo(id);
		List<FreightAttr> lits = freightAttrMapper.selectByExample(example);
		record.setFreightAttrs(lits);
		return record;
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#update(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.Integer,
	 *      java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public int update(Integer id, Integer shopid, String name,
			Integer isExemptionPostage, Integer pricingModel,
			Integer transportModel, Integer setNum, Integer iscondition,
			String description, List<FreightAttr> list) throws Exception {
		Freight record = freightMapper.selectByPrimaryKey(id);
		record.setShopid(shopid);
		record.setName(name);
		record.setIsexemptionpostage(isExemptionPostage);
		record.setPricingmode(pricingModel);
		record.setTransportmode(transportModel);
		record.setNum(setNum);
		record.setIscondition(iscondition);
		record.setDescription(description);
		if (list != null && list.size() > 0) {
			for (FreightAttr freightAttr : list) {
				FreightAttr attr = freightAttrMapper
						.selectByPrimaryKey(freightAttr.getId());
				BeanUtils.copyProperties(freightAttr, attr);
				freightAttrMapper.updateByPrimaryKey(attr);
			}
		}
		return freightMapper.updateByPrimaryKey(record);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) throws Exception {
		return freightMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#insertAttr(java.lang.Integer,
	 *      java.lang.String, java.lang.Integer, java.lang.Float,
	 *      java.lang.Integer, java.lang.Float, java.lang.Integer)
	 */
	@Override
	public int insertAttr(Integer freightid, String areas, Integer firstcount,
			Float firstprice, Integer elsecount, Float elseprice)
			throws Exception {
		FreightAttr attr = new FreightAttr();
		attr.setFreightid(freightid);
		attr.setAreas(areas);
		attr.setFirstcount(firstcount);
		attr.setFirstprice(firstprice);
		attr.setElsecount(elsecount);
		attr.setElseprice(elseprice);
		Freight freight = freightMapper.selectByPrimaryKey(freightid);
		attr.setStatus(freight.getStatus());
		return freightAttrMapper.insert(attr);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#deleteAttr(java.lang.Integer)
	 */
	@Override
	public int deleteAttr(Integer id) throws Exception {

		return freightAttrMapper.deleteByPrimaryKey(id);
	}

	@Override
	public V_Freights getByShopID(Integer shopid, String provincename)
			throws Exception {
		V_Freights v_FreightsD = v_FreightsMapper.getByShopID(shopid,provincename); 
		if (v_FreightsD != null
				&& v_FreightsD.getAreas().contains(provincename)) {
			return v_FreightsD;
		} else {
			v_FreightsD =v_FreightsMapper.getDefaultByShopID(shopid);
			if (v_FreightsD != null) {
				return v_FreightsD;
			} else {
				return null;
			}
		}
	}

	public List<Freight> selectByShop(Integer shopid) throws Exception {
		return freightMapper.selectByShop(shopid);
	}

	@Override
	public int insertSendTemplate(Integer type, String content, Integer ctype,
			Integer sort, Integer tag) throws Exception {
		Sendtemplate sendTemplate = new Sendtemplate();
		sendTemplate.setType(type);
		sendTemplate.setContent(content);
		sendTemplate.setCtype(ctype);
		if (sort > 0) {
			sendTemplate.setSort(sort);
		}
		sendTemplate.setIsdefault(0);
		sendTemplate.setTag(tag);
		return sendtemplateMapper.insert(sendTemplate);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#deletSendTemplate(java.lang.Integer)
	 */
	@Override
	public int deletSendTemplate(Integer id) throws Exception {

		return sendtemplateMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#querySendTemplate(java.lang.Integer)
	 */
	@Override
	public Sendtemplate querySendTemplate(Integer id) throws Exception {

		return sendtemplateMapper.selectByPrimaryKey(id);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#querySendTemplateList(com.yinlian.wssc.web.util.CriteriaSendTemplate,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean querySendTemplateList(CriteriaSendTemplate criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Sendtemplate> beanList = sendtemplateMapper
				.selectPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#deletSendTemplateList(java.lang.String)
	 */
	@Override
	public int deletSendTemplateList(String[] array) throws Exception {

		return sendtemplateMapper.deleteList(array);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#setDefaultSendTemplate(java.lang.Integer)
	 */
	@Override
	public int setDefaultSendTemplate(Integer id) throws Exception {
		Sendtemplate record = sendtemplateMapper.selectByPrimaryKey(id);
		SendtemplateExample example = new SendtemplateExample();
		SendtemplateExample.Criteria criteria = example.createCriteria();
		criteria.andCtypeEqualTo(record.getCtype());
		criteria.andTypeEqualTo(record.getType());
		criteria.andTagEqualTo(record.getTag());
		List<Sendtemplate> list = sendtemplateMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			for (Sendtemplate sendtemplate : list) {
				if (sendtemplate.getIsdefault() == 1) {
					sendtemplate.setIsdefault(0);
					sendtemplateMapper.updateByPrimaryKey(sendtemplate);
				}
			}
		}

		record.setIsdefault(1);
		return sendtemplateMapper.updateByPrimaryKey(record);
	}

	/**
	 * @see com.yinlian.wssc.web.service.FreightService#updateSendTemplate(java.lang.Integer,
	 *      java.lang.String, java.lang.Integer, java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public int updateSendTemplate(Integer id, String content, Integer ctype,
			Integer sort, Integer type, Integer tag) throws Exception {

		Sendtemplate record = sendtemplateMapper.selectByPrimaryKey(id);
		record.setContent(content);
		record.setType(type);
		record.setCtype(ctype);
		if (sort > 0) {
			record.setSort(sort);
		}
		record.setTag(tag);
		return sendtemplateMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public String[] exchangeTemplate(String username, String ordernum,
			String logisticsname, String logisticscode, String ordertime,
			String[] content) throws Exception {

		if (content[0].indexOf("[username]") >= 0) {
			content[0] = content[0].replace("[username]", username);
		}
		if (content[0].indexOf("[ordernum]") >= 0) {
			content[0] = content[0].replace("[ordernum]", ordernum);
		}
		if (content[0].indexOf("[ordertime]") >= 0) {
			content[0] = content[0].replace("[ordertime]", ordertime);
		}
		if (content[0].indexOf("[logisticsname]") >= 0) {
			content[0] = content[0].replace("[logisticsname]", logisticsname);
		}
		if (content[0].indexOf("[logisticscode]") >= 0) {
			content[0] = content[0].replace("[logisticscode]", logisticscode);
		}

		return content;
	}

	@Override
	public Sendtemplate getbyType(int templatetype, int contenttype, int tag)
			throws Exception {
		return sendtemplateMapper.getByType(templatetype, contenttype, tag);
	}

	@Override
	public List<V_Freights> getFreightPrice(List<Integer> shopids,
			String provinceName) throws Exception {
		List<V_Freights> flist = new ArrayList<V_Freights>();
		for (Integer shopid : shopids) {
			V_Freights dto = getByShopID(shopid, provinceName);
			if (dto != null) {
				flist.add(dto);
			} else {
				return null;
			}
		}
		return flist;
	}

	/**
	 * 查询默认模板
	 * 
	 * @see com.yinlian.wssc.web.service.FreightService#selectMfreightByShopId(int)
	 */
	@Override
	public Freight selectMfreightByShopId(int shopid) throws Exception {
		FreightExample example = new FreightExample();
		FreightExample.Criteria criteria = example.createCriteria();
		criteria.andShopidEqualTo(shopid);
		criteria.andStatusEqualTo(1);
		List<Freight> list = new ArrayList<Freight>();
		list = freightMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return new Freight();
		}

	}

	@Override
	public List<FreightReturnDto> getFreightPriceNew(
			List<FreightParamDto> shopids, String provinceName,
			List<Integer> spuids) throws Exception {
		List<FreightReturnDto> dtolList = new ArrayList<FreightReturnDto>();
		FreightReturnDto rDto = null;
		List<Spu> spulist = spuMapper.getBySpuIDs(spuids);
		if (shopids.size() > 0 && spulist.size() > 0) {
			for (FreightParamDto paramDto : shopids) {
				if (!spulist.stream().filter(x -> paramDto.getShopid().equals(x.getShopid())).allMatch(y -> y.getIspostage())) {

					java.math.BigDecimal bd1 = new java.math.BigDecimal(
							paramDto.getProprice());
					V_Freights dto = getByShopID(paramDto.getShopid(),
							provinceName);
					if (dto != null) {
						rDto = new FreightReturnDto();
						rDto.setId(dto.getFreightID());
						rDto.setShopid(dto.getShopID());
						Double fm = orderService.freightMoney(dto,
								paramDto.getProcount(), bd1, 0);
						rDto.setPrice(fm < 0 ? 0 : fm);
						dtolList.add(rDto);
					} else {
						rDto = new FreightReturnDto();
						rDto.setId(0);
						rDto.setShopid(paramDto.getShopid());
						rDto.setPrice(0.0);
						dtolList.add(rDto);
					}

				}else {
					rDto = new FreightReturnDto();
					rDto.setId(0);
					rDto.setShopid(paramDto.getShopid());
					rDto.setPrice(0.0);
					dtolList.add(rDto);
				}
			}
		}
		return dtolList;
	}

}
